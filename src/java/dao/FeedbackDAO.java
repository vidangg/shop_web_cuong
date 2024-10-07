/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Feedback;
import java.sql.*;

/**
 *
 * @author QuocCuong
 */
public class FeedbackDAO extends DBContext {

    public List<Feedback> getAllFeedback() {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT f.FeedbackID, f.AccountID, f.BookID, f.Feedback, f.FeedbackDate, "
                + "b.Title as BookTitle, a.UserName as UserName "
                + "FROM Feedbacks f "
                + "INNER JOIN Accounts a ON f.AccountID = a.AccountID "
                + "INNER JOIN Books b ON f.BookID = b.BookID ORDER BY f.FeedbackDate DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt("FeedbackID"));
                feedback.setAccountID(rs.getInt("AccountID"));
                feedback.setBookID(rs.getInt("BookID"));
                feedback.setFeedback(rs.getString("Feedback"));
                feedback.setFeedbackDate(rs.getTimestamp("FeedbackDate"));
                feedback.setUserName(rs.getString("UserName")); // Thiết lập tên người dùng
                feedback.setBookTitle(rs.getString("BookTitle")); // Thiết lập tên sách
                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return feedbackList;
    }

    public List<Feedback> getFeedbackByBookId(int bookId) {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT f.FeedbackID, f.AccountID, f.BookID, f.Feedback, f.FeedbackDate, "
                + "a.UserName as UserName, b.Title as BookTitle "
                + "FROM Feedbacks f "
                + "INNER JOIN Accounts a ON f.AccountID = a.AccountID "
                + "INNER JOIN Books b ON f.BookID = b.BookID "
                + "WHERE f.BookID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, bookId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt("FeedbackID"));
                feedback.setAccountID(rs.getInt("AccountID"));
                feedback.setBookID(rs.getInt("BookID"));
                feedback.setFeedback(rs.getString("Feedback"));
                feedback.setFeedbackDate(rs.getTimestamp("FeedbackDate"));
                feedback.setUserName(rs.getString("userName")); // Lấy tên người dùng
                feedback.setBookTitle(rs.getString("bookTitle")); // Lấy tên sách
                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return feedbackList;
    }

    public Feedback getFeedbackById(int feedbackId) {
        Feedback feedback = null;
        String sql = "SELECT f.FeedbackID, f.AccountID, f.BookID, f.Feedback, f.FeedbackDate, "
                + "a.UserName as UserName, b.Title as BookTitle "
                + "FROM Feedbacks f "
                + "INNER JOIN Accounts a ON f.AccountID = a.AccountID "
                + "INNER JOIN Books b ON f.BookID = b.BookID "
                + "WHERE f.FeedbackID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, feedbackId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt("FeedbackID"));
                feedback.setAccountID(rs.getInt("AccountID"));
                feedback.setBookID(rs.getInt("BookID"));
                feedback.setFeedback(rs.getString("Feedback"));
                feedback.setFeedbackDate(rs.getTimestamp("FeedbackDate"));
                feedback.setUserName(rs.getString("UserName"));
                feedback.setBookTitle(rs.getString("BookTitle"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedback;
    }

    public boolean updateFeedback(Feedback feedback) {
        String sql = "UPDATE Feedbacks SET Feedback = ? WHERE FeedbackID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, feedback.getFeedback());
            st.setInt(2, feedback.getFeedbackID());
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức xoá feedback
    public boolean deleteFeedback(int feedbackId) {
        String sql = "DELETE FROM Feedbacks WHERE FeedbackID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, feedbackId);
            int rowsDeleted = st.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addFeedback(Feedback feedback) {
        String sql = "INSERT INTO Feedbacks (AccountID, BookID, Feedback, FeedbackDate) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, feedback.getAccountID());
            st.setInt(2, feedback.getBookID());
            st.setString(3, feedback.getFeedback());
            st.setTimestamp(4, feedback.getFeedbackDate());

            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hasFeedback(int accountId, int bookId) {
        String sql = "SELECT COUNT(*) FROM Feedbacks WHERE AccountID = ? AND BookID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountId);
            st.setInt(2, bookId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Feedback getFeedbackByAccountAndBook(int accountId, int bookId) {
        Feedback feedback = null;
        String sql = "SELECT f.FeedbackID, f.AccountID, f.BookID, f.Feedback, f.FeedbackDate, "
                + "a.UserName as UserName, b.Title as BookTitle "
                + "FROM Feedbacks f "
                + "INNER JOIN Accounts a ON f.AccountID = a.AccountID "
                + "INNER JOIN Books b ON f.BookID = b.BookID "
                + "WHERE f.AccountID = ? AND f.BookID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountId);
            st.setInt(2, bookId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt("FeedbackID"));
                feedback.setAccountID(rs.getInt("AccountID"));
                feedback.setBookID(rs.getInt("BookID"));
                feedback.setFeedback(rs.getString("Feedback"));
                feedback.setFeedbackDate(rs.getTimestamp("FeedbackDate"));
                feedback.setUserName(rs.getString("UserName"));
                feedback.setBookTitle(rs.getString("BookTitle"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedback;
    }

}
