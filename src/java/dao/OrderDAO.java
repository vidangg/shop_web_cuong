/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Book;
import model.Cart;
import model.CartItems;
import model.Order;
import model.OrderItems;

/**
 *
 * @author Admin
 */
public class OrderDAO extends DBContext {

    public List<Order> getAll() {
        List<Order> listO = new ArrayList<>();
        String sql = "select OrderID, AccountID, Total_Price, OrderDate, Status from Orders";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("OrderID"));
                o.setAccountID(rs.getInt("AccountID"));
                o.setTotalPrice(rs.getFloat("Total_Price"));
                o.setOrderDate(rs.getString("OrderDate"));
                o.setStatus(rs.getString("Status"));
                listO.add(o);
            }
        } catch (SQLException e) {
            return null;
        }
        return listO;
    }

    public List<Order> getAllByStatus(String status) {
        List<Order> listSO = new ArrayList<>();
        String sql = "select OrderID, AccountID, Total_Price, OrderDate, Status from Orders\n"
                + "where Status like N'%" + status + "%'\n"
                + "order by OrderID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("OrderID"));
                o.setAccountID(rs.getInt("AccountID"));
                o.setTotalPrice(rs.getFloat("Total_Price"));
                o.setOrderDate(rs.getString("OrderDate"));
                o.setStatus(rs.getString("Status"));
                listSO.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listSO;
    }

    public List<Order> getAllByAccountId(int id) {
        List<Order> listOA = new ArrayList<>();
        String sql = "select OrderID, AccountID, Total_Price, OrderDate, Status from Orders\n"
                + "where AccountID = ?\n"
                + "order by OrderID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("OrderID"));
                o.setAccountID(rs.getInt("AccountID"));
                o.setTotalPrice(rs.getFloat("Total_Price"));
                o.setOrderDate(rs.getString("OrderDate"));
                o.setStatus(rs.getString("Status"));
                listOA.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listOA;
    }

    public void addCartOrder(int accountId, Cart cart) {
        LocalDate curDate = LocalDate.now();
        BookDAO bd = new BookDAO();
        String date = curDate.toString();
        String sql1 = "INSERT INTO Orders (AccountID, Total_Price, OrderDate, Status) VALUES (?, ?, ?, ?)";
        String sql2 = "select OrderID, AccountID, Total_Price, OrderDate, Status from Orders\n"
                + "order by OrderID desc\n"
                + "limit 1";
        String sql3 = "INSERT INTO OrderItems (OrderID, BookID, Quantity, Unit_Price) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
            PreparedStatement st2 = connection.prepareStatement(sql2);
            PreparedStatement st3 = connection.prepareStatement(sql3);
            st1.setInt(1, accountId);
            double totalPrice = 0;
            List<CartItems> listI = cart.getItems();
            for (CartItems i : listI) {
                totalPrice += i.getPrice();
            }
            st1.setDouble(2, totalPrice);
            st1.setString(3, date);
            st1.setString(4, "Chờ xử lý");
            st1.executeUpdate();
            ResultSet rs = st2.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt("OrderID");
                for (CartItems i : listI) {
                    st3.setInt(1, orderId);
                    st3.setInt(2, i.getBook().getId());
                    st3.setInt(3, i.getQuantity());
                    st3.setDouble(4, i.getBook().getPrice());
                    st3.executeUpdate();
                    bd.updateBoughtBook(i);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addItemOrder(int accountId, CartItems item) {
        LocalDate curDate = LocalDate.now();
        BookDAO bd = new BookDAO();
        String date = curDate.toString();
        String sql1 = "INSERT INTO Orders (AccountID, Total_Price, OrderDate, Status) VALUES (?, ?, ?, ?)";
        String sql2 = "select OrderID, AccountID, Total_Price, OrderDate, Status from Orders\n"
                + "order by OrderID desc\n"
                + "limit 1";
        String sql3 = "INSERT INTO OrderItems (OrderID, BookID, Quantity, Unit_Price) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
            PreparedStatement st2 = connection.prepareStatement(sql2);
            PreparedStatement st3 = connection.prepareStatement(sql3);
            st1.setInt(1, accountId);
            st1.setDouble(2, item.getPrice());
            st1.setString(3, date);
            st1.setString(4, "Chờ xử lý");
            st1.executeUpdate();
            ResultSet rs = st2.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt("OrderID");
                st3.setInt(1, orderId);
                st3.setInt(2, item.getBook().getId());
                st3.setInt(3, item.getQuantity());
                st3.setDouble(4, item.getBook().getPrice());
                st3.executeUpdate();
                bd.updateBoughtBook(item);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void cancelOrder(int oid, String msg) {
        String sql1 = "select BookID, Quantity from OrderItems WHERE OrderID = ?";
//        String sql2 = "DELETE FROM OrderItems WHERE OrderID = ?";

        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
//            PreparedStatement st2 = connection.prepareStatement(sql2);
            st1.setInt(1, oid);
            ResultSet rs = st1.executeQuery();
            BookDAO bd = new BookDAO();
            while (rs.next()) {
                int bid = rs.getInt("BookID");
                int quantity = rs.getInt("Quantity");
                bd.updateReturnedBook(bid, quantity);
            }
//            st2.setInt(1, oid);
//            st2.executeUpdate();
            updateOrder(oid, msg);
        } catch (Exception e) {
            return;
        }
    }

    public void updateOrder(int oid, String msg) {
        String sql1 = "select OrderID, AccountID, Total_Price, OrderDate, Status from Orders\n"
                + "WHERE OrderID = ?";
        String sql2 = "UPDATE Orders SET AccountID = ?, Total_Price = ?, OrderDate = ?, Status = ?\n"
                + " WHERE OrderID = ?";
        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st1.setInt(1, oid);
            ResultSet rs = st1.executeQuery();
            if (rs.next()) {
                st2.setInt(1, rs.getInt("AccountID"));
                st2.setFloat(2, rs.getFloat("Total_Price"));
                st2.setString(3, rs.getString("OrderDate"));
                st2.setString(4, msg);
                st2.setInt(5, oid);
                st2.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Order getOrderByID(int orderId) {
        Order order = null;
        String sql = "SELECT OrderID, AccountID, Total_Price, OrderDate, Status "
                + "FROM Orders "
                + "WHERE OrderID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("OrderID"));
                order.setAccountID(rs.getInt("AccountID"));
                order.setTotalPrice(rs.getFloat("Total_Price"));
                order.setOrderDate(rs.getString("OrderDate"));
                order.setStatus(rs.getString("Status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return order;
    }
    
    public boolean hasPurchased(int accountId, int bookId) {
        String sql = "SELECT COUNT(*) FROM Orders o JOIN OrderItems oi ON o.OrderID = oi.OrderID WHERE o.AccountID = ? AND oi.BookID =?";
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
    
    public boolean checkBalance(int accountId, double totalPrice) {
        String sql = "SELECT Payments FROM Accounts WHERE AccountID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                double payments = rs.getDouble("Payments");
                return payments >= totalPrice;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public double getOrderTotalPrice(int orderId) {
        double totalPrice = 0.0;
        String sql = "SELECT Total_Price FROM Orders WHERE OrderID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                totalPrice = rs.getDouble("Total_Price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalPrice;
    }
}
