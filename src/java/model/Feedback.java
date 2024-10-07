/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author QuocCuong
 */
public class Feedback {
    int FeedbackID;
    int AccountID;
    int BookID;
    String Feedback;
    Timestamp FeedbackDate;
    private String userName; // Thêm trường userName để lưu tên người dùng
    private String bookTitle; // Thêm trường bookTitle để lưu tên sách
    private String formattedDate; 

    public Feedback(int FeedbackID, int AccountID, int BookID, String Feedback) {
        this.FeedbackID = FeedbackID;
        this.AccountID = AccountID;
        this.BookID = BookID;
        this.Feedback = Feedback;
    }

    public Feedback(int AccountID, int BookID, String Feedback) {
        this.AccountID = AccountID;
        this.BookID = BookID;
        this.Feedback = Feedback;
    }

    public Feedback() {
    }

    public Feedback(int AccountID, int BookID, String Feedback, Timestamp FeedbackDate) {
        this.AccountID = AccountID;
        this.BookID = BookID;
        this.Feedback = Feedback;
        this.FeedbackDate = FeedbackDate;
    }

    public Feedback(int FeedbackID, int AccountID, int BookID, String Feedback, Timestamp FeedbackDate) {
        this.FeedbackID = FeedbackID;
        this.AccountID = AccountID;
        this.BookID = BookID;
        this.Feedback = Feedback;
        this.FeedbackDate = FeedbackDate;
    }

    public Feedback(int AccountID, int BookID, String Feedback, Timestamp FeedbackDate, String userName, String bookTitle) {
        this.AccountID = AccountID;
        this.BookID = BookID;
        this.Feedback = Feedback;
        this.FeedbackDate = FeedbackDate;
        this.userName = userName;
        this.bookTitle = bookTitle;
    }

    public Feedback(int FeedbackID, int AccountID, int BookID, String Feedback, Timestamp FeedbackDate, String userName, String bookTitle) {
        this.FeedbackID = FeedbackID;
        this.AccountID = AccountID;
        this.BookID = BookID;
        this.Feedback = Feedback;
        this.FeedbackDate = FeedbackDate;
        this.userName = userName;
        this.bookTitle = bookTitle;
    }

    public int getFeedbackID() {
        return FeedbackID;
    }

    public void setFeedbackID(int FeedbackID) {
        this.FeedbackID = FeedbackID;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int BookID) {
        this.BookID = BookID;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String Feedback) {
        this.Feedback = Feedback;
    }

    public Timestamp getFeedbackDate() {
        return FeedbackDate;
    }

    public void setFeedbackDate(Timestamp FeedbackDate) {
        this.FeedbackDate = FeedbackDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }
    public void formatFeedbackDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH 'giờ' mm 'phút ngày' dd 'tháng' M 'năm' yyyy", new Locale("vi", "VN"));
        this.formattedDate = dateFormat.format(this.FeedbackDate);
    }
}
