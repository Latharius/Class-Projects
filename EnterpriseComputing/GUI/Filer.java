/*
Name: Leith Rabah
Course: CNT 4714 – Fall 2022
Assignment title: Project 1 – Event-driven Enterprise Simulation
Date: September 09, 2022
*/
package EnterpriseComputing.GUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Filer {
    private int CurrentNumItems = 0;
    private int MaxNumberOfItems = -1;
    private int TotalItems = 0;
    private double OrderSubtotal = 0;
    private double OrderTotal = 0;

    private String filename = "transactions.txt";

    private ArrayList <String> items = new ArrayList < > ();
    private StringBuilder viewOrder = new StringBuilder();
    private StringBuilder finishOrder = new StringBuilder();

    File file = new File(filename);
    String[] itemInfo = new String[7];

    public String getFinishOrder() {
        return this.finishOrder.toString();
    }

    public void setFinishOrder(String Date, String Time) {
        this.setOrderTotal();
        this.finishOrder.append("Date: ").append(Date).append(" ").append(Time);
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append("Number of line items: ").append(this.getTotalItems());
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append("Item# /ID / Price / Qty / Disc %/ Subtotal");
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append(this.getViewOrder());
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append("Order subtotal:   $").append(new DecimalFormat("#0.00").format(this.getOrderSubtotal()));
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append("Tax rate:     6%");
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append("Order total:      $").append(new DecimalFormat("#0.00").format(this.getOrderTotal()));
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append("Thanks for shopping at StoreStore!");
    }

    public String getViewOrder() {
        return this.viewOrder.toString();
    }

    public void addToViewOrder(String Order) {
        viewOrder.append(this.getTotalItems()).append(". ").append(Order);
        viewOrder.append(System.getProperty("line.separator"));
    }

    public String[] getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String ItemID, String Title, String Price, String QuantityOfItem, String DiscountPercentage, String TotalDiscount, String Stock) {
        itemInfo[0] = ItemID;
        itemInfo[1] = Title;
        itemInfo[2] = Stock;
        itemInfo[3] = Price;
        itemInfo[4] = QuantityOfItem;
        itemInfo[5] = DiscountPercentage;
        itemInfo[6] = TotalDiscount;
    }

    public double getTotalDiscount(int Quantity, double ItemPrice) {
        if (Quantity >= 1 && Quantity <= 4)
            return (Quantity * ItemPrice);
        if (Quantity >= 5 && Quantity <= 9)
            return .10 * (Quantity * ItemPrice);
        if (Quantity >= 10 && Quantity <= 14)
            return .15 * (Quantity * ItemPrice);
        if (Quantity >= 15)
            return .20 * (Quantity * ItemPrice);

        return 0.0;
    }

    public int getDiscountPercentage(int Quantity) {
        if (Quantity >= 1 && Quantity <= 4)
            return 0;
        if (Quantity >= 5 && Quantity <= 9)
            return 10;
        if (Quantity >= 10 && Quantity <= 14)
            return 15;
        if (Quantity >= 15)
            return 20;

        return 0;
    }

    public String ViewOrder() {
        return filename;
    }

    public void prepareTransaction() {
        StringBuilder lineItem = new StringBuilder("");
        for (String s : this.itemInfo) {
            lineItem.append(s).append(", ");
        }
        items.add(lineItem.toString());
    }

    public void printTransactions() throws IOException {
        SimpleDateFormat TransactionID = new SimpleDateFormat("yyMMddyyHHmm");
        SimpleDateFormat DateFormat = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat Time = new SimpleDateFormat("hh:mm:ss a z");

        Calendar calendar = Calendar.getInstance();
        Date Date = calendar.getTime();

        this.setFinishOrder(DateFormat.format(Date), Time.format(Date));

        if (!file.exists())
            file.createNewFile();

        PrintWriter OutputStream = new PrintWriter(new FileWriter(filename, true));

        for (String item : this.items) {
            OutputStream.append(TransactionID.format(Date)).append(", ");
            OutputStream.append(item);
            OutputStream.append(DateFormat.format(Date)).append(", ");
            OutputStream.append(Time.format(Date));
            OutputStream.println();
        }
        OutputStream.flush();
        OutputStream.close();
    }

    public int getCurrentNumItems() {
        return CurrentNumItems;
    }

    public void setCurrentNumItems(int CurrentNumItems) {
        this.CurrentNumItems = this.CurrentNumItems + CurrentNumItems;
    }

    public void setTotalItems(int TotalItems) {
        this.TotalItems = TotalItems;
    }

    public int getTotalItems() {
        return TotalItems;
    }

    public void setMaxNumItems(int MaxNumberOfItems) {
        this.MaxNumberOfItems = MaxNumberOfItems;
    }

    public int getMaxNumItems() {
        return MaxNumberOfItems;
    }

    public double getOrderSubtotal() {
        return OrderSubtotal;
    }

    public void setOrderSubtotal(int Quantity, double ItemPrice) {
        this.OrderSubtotal = this.OrderSubtotal + this.getTotalDiscount(Quantity, ItemPrice);
    }

    public double getOrderTotal() {
        return OrderTotal;
    }

    public void setOrderTotal() {
        this.OrderTotal = this.OrderSubtotal + (.06 * this.OrderSubtotal);
    }
}