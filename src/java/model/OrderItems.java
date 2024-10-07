package model;

public class OrderItems {
    int OrderItemID;
    int OrderID;
    int BookID;
    int Quantity;
    double Unit_Price;

    public OrderItems() {
    }

    public OrderItems(int OrderItemID, int OrderID, int BookID, int Quantity, double Unit_Price) {
        this.OrderItemID = OrderItemID;
        this.OrderID = OrderID;
        this.BookID = BookID;
        this.Quantity = Quantity;
        this.Unit_Price = Unit_Price;
    }

    public int getOrderItemID() {
        return OrderItemID;
    }

    public void setOrderItemID(int OrderItemID) {
        this.OrderItemID = OrderItemID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int BookID) {
        this.BookID = BookID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getUnit_Price() {
        return Unit_Price;
    }

    public void setUnit_Price(double Unit_Price) {
        this.Unit_Price = Unit_Price;
    }
    
    
}
