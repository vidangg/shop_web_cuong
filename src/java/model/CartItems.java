
package model;

public class CartItems {
    public Book book;
    int quantity;
    double price;

    public CartItems() {
    }

    public CartItems(Book book, int quantity, double price) {
        this.book = book;
        this.quantity = quantity;
        this.price = price;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
   
}
