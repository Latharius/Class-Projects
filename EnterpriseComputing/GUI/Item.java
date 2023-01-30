/*
Name: Leith Rabah
Course: CNT 4714 – Fall 2022
Assignment title: Project 1 – Event-driven Enterprise Simulation
Date: September 09, 2022
*/
package EnterpriseComputing.GUI;

public class Item {
    private String ItemID;
    private String Title;
    private double Price;
    private String Stock;

    public String GetItemID() {
        return ItemID;
    }

    public void SetItemID(String ItemID) {
        this.ItemID = ItemID;
    }

    public void SetItemTitle(String Title) {
        this.Title = Title;
    }

    public String GetItemTitle() {
        return Title;
    }

    public void SetItemPrice(double Price) {
        this.Price = Price;
    }

    public double GetItemPrice() {
        return Price;
    }

    public String GetStock() {
        return Stock;
    }

    public void SetStock(String Stock) {
        this.Stock = Stock;
    }
}