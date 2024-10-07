
package model;

import java.util.List;

public class Order {
    int id;
    int accountID;
    double totalPrice;
    String orderDate;
    String status;
    String bookTitle;
    List<OrderItems> items;
    public Order() {
    }
    
    public Order(int accountID, double totalPrice, String orderDate, String status) {
        this.accountID = accountID;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Order(int id, int accountID, double totalPrice, String orderDate, String status) {
        this.id = id;
        this.accountID = accountID;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public List<OrderItems> getItems() {
        return items;
    }

    public void setItems(List<OrderItems> items) {
        this.items = items;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    
}
