/*
Name: Leith Rabah
Course: CNT 4714 – Fall 2022
Assignment title: Project 1 – Event-driven Enterprise Simulation
Date: September 09, 2022
*/
package EnterpriseComputing.GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.regex.Matcher;

public class GUI extends JFrame {
    public static void main(String[] args) throws FileNotFoundException {
        GUI GUIFrame = new GUI();
        GUIFrame.pack();
        GUIFrame.setTitle("Project 1");
        GUIFrame.setLocationRelativeTo(null);
        GUIFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GUIFrame.setVisible(true);
    }

    private ArrayList <Item> inventory;
    private Filer Filer = new Filer();

    //text bars
    private JTextField itemIDTF = new JTextField();
    private JTextField itemInfoTF = new JTextField();
    private JTextField quantityTF = new JTextField();
    private JTextField numItemsTF = new JTextField();
    private JTextField totalItemsTF = new JTextField();

    //buttons
    private JButton processItemBut = new JButton("Process Item #1");
    private JButton confirmItemBut = new JButton("Confirm Item #1");
    private JButton viewOrderBut = new JButton("View Order");
    private JButton finishOrderBut = new JButton("Finish Order");
    private JButton newOrderBut = new JButton("New Order");
    private JButton exitBut = new JButton("Exit");

    //labels
    JLabel subtotalLabel = new JLabel("Order Subtotal for 0 item(s):");
    JLabel itemIDLabel = new JLabel("Enter Item ID for Item #1:");
    JLabel quantityLabel = new JLabel("Enter Quantitiy for Item #1:");
    JLabel itemInfoLabel = new JLabel("Item #1 Info:");


    public GUI() throws FileNotFoundException {
        this.getInventoryFromFile();

        //create the first panel for the text inputs and outputs.
        JPanel panel1 = new JPanel(new GridLayout(5, 2));
        panel1.add(itemIDLabel);
        panel1.add(itemIDTF);
        panel1.add(quantityLabel);
        panel1.add(quantityTF);
        panel1.add(itemInfoLabel);
        panel1.add(itemInfoTF);
        panel1.add(subtotalLabel);
        panel1.add(totalItemsTF);
        panel1.setBackground(Color.GREEN);

        //create the second panel and adds the buttons to it.
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel2.add(processItemBut);
        panel2.add(confirmItemBut);
        panel2.add(viewOrderBut);
        panel2.add(finishOrderBut);
        panel2.add(newOrderBut);
        panel2.add(exitBut);
        panel2.setBackground(Color.CYAN);

        this.totalItemsTF.setEnabled(false);
        this.itemInfoTF.setEnabled(false);
        this.confirmItemBut.setEnabled(false);
        this.viewOrderBut.setEnabled(false);
        this.finishOrderBut.setEnabled(false);

        //add Panels
        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.SOUTH);

        //listeners for Buttons.
        processItemBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //retrieve Information
                int quantityOfItem = Integer.parseInt(quantityTF.getText());
                String ItemID = itemIDTF.getText();

                //search for an Item.
                Item Item = linearSearch(ItemID);
                if (Item != null) {
                    Filer.setItemInfo(Item.GetItemID() + "", Item.GetItemTitle(), Item.GetItemPrice() + "", Item.GetStock(),
                            quantityOfItem + "", Filer.getDiscountPercentage(quantityOfItem) + "",
                            Filer.getTotalDiscount(quantityOfItem, Item.GetItemPrice()) + "");

                    String ItemInfo = Item.GetItemID() + Item.GetItemTitle() + " $" + Item.GetItemPrice() + " " + Item.GetStock() + " " +
                            quantityOfItem + " " + Filer.getDiscountPercentage(quantityOfItem) + "% " +
                            Filer.getTotalDiscount(quantityOfItem, Item.GetItemPrice());

                    itemInfoTF.setText(ItemInfo);
                    processItemBut.setEnabled(false);
                    confirmItemBut.setEnabled(true);

                    Filer.setOrderSubtotal(quantityOfItem, Item.GetItemPrice());
                    totalItemsTF.setEnabled(false);
                    itemInfoTF.setEnabled(false);

                    Pattern pattern = Pattern.compile(" false", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(Item.GetStock());
                    boolean matchFound = matcher.find();

                    if (matchFound) {
                        JOptionPane.showMessageDialog(null, "Item out of stock.");
                        itemIDTF.setText("");
                        quantityTF.setText("");
                        processItemBut.setEnabled(true);
                        confirmItemBut.setEnabled(false);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Item ID" + ItemID + " not in file.");
                    itemIDTF.setText("");
                    quantityTF.setText("");
                }
            }
        });

        viewOrderBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, Filer.getViewOrder());
            }
        });

        finishOrderBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Filer.printTransactions();
                    JOptionPane.showMessageDialog(null, Filer.getFinishOrder());

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                GUI.super.dispose();
            }
        });

        confirmItemBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer.parseInt(itemIDTF.getText());
                int quantityOfItem = Integer.parseInt(quantityTF.getText());


                Filer.setCurrentNumItems(quantityOfItem);
                Filer.setTotalItems(Filer.getTotalItems() + 1);
                JOptionPane.showMessageDialog(null, "Item #" + Filer.getTotalItems() + " accepted");
                Filer.prepareTransaction();
                Filer.addToViewOrder(itemInfoTF.getText());


                confirmItemBut.setEnabled(false);
                numItemsTF.setEnabled(false);
                processItemBut.setEnabled(true);
                viewOrderBut.setEnabled(true);
                finishOrderBut.setEnabled(true);

                processItemBut.setText("Process Item #" + (Filer.getTotalItems() + 1));
                confirmItemBut.setText("Confirm Item #" + (Filer.getTotalItems() + 1));

                itemIDTF.setText("");
                quantityTF.setText("");
                totalItemsTF.setText("$" + new DecimalFormat("#0.00").format(Filer.getOrderSubtotal()));

                subtotalLabel.setText("Order subtotal for " + Filer.getCurrentNumItems() + " item(s)");
                itemIDLabel.setText("Enter Item ID for Item #" + (Filer.getTotalItems() + 1) + ":");
                quantityLabel.setText("Enter quantity for Item #" + (Filer.getTotalItems() + 1) + ":");

                if (Filer.getCurrentNumItems() < Filer.getMaxNumItems())
                    itemInfoLabel.setText("Item #" + (Filer.getTotalItems() + 1) + " info:");
            }
        });

        newOrderBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.super.dispose();
                try {
                    GUI.main(null);
                } catch (FileNotFoundException exception) {
                    exception.printStackTrace();
                }
            }
        });

        exitBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.super.dispose();
            }
        });
    }

    //retrieve data from the inventory file.
    public void getInventoryFromFile() throws FileNotFoundException {
        this.inventory = new ArrayList < Item > ();
        File file = new File("inventory.txt");
        Scanner inventoryFile = new Scanner(file);

        while (inventoryFile.hasNextLine()) {
            String Item = inventoryFile.nextLine();

            if (Item.equals(""))
                break;

            String[] ItemInfo = Item.split(",");

            Item item = new Item();
            item.SetItemID(ItemInfo[0]);
            item.SetItemTitle(ItemInfo[1]);
            item.SetStock(ItemInfo[2]);
            item.SetItemPrice(Double.parseDouble(ItemInfo[3]));

            inventory.add(item);
        }
        inventoryFile.close();
    }

    //searches the inventory for the ItemID so that it can return the specific Item with that ID.
    public Item linearSearch(String ItemID) {
        for (Item CurrentItem : inventory) {
            if (CurrentItem.GetItemID().equals(ItemID))
                return CurrentItem;
        }
        return null;
    }

    //returns the array inventory, which holds the data from the file.
    public ArrayList < Item > getInventory() {
        return inventory;
    }

    //stores the array passed inside inventory to be accessed later.
    public void setInventory(ArrayList < Item > inventory) {
        this.inventory = inventory;
    }
}