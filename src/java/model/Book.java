
package model;

public class Book {
    int id;
    String title;
    String publisher;
    double price;
    int quantity;
    String imgLink;

    public Book() {
    }

    public Book(int id, String title, String publisher, double price, int quantity, String imgLink) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.price = price;
        this.quantity = quantity;
        this.imgLink = imgLink;
    }

    public Book( String title, double price, int quantity, String imgLink) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.imgLink = imgLink;
    }

    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }
    
    

}
