/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Book;

/**
 *
 * @author Admin
 */
public class AccountDAO extends DBContext {

    public List<Account> getAll() {
        List<Account> listA = new ArrayList<>();
        String sql = "select * from Accounts";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("AccountID"));
                a.setUsername(rs.getString("Username"));
                a.setPassword(rs.getString("Password"));
                a.setEmail(rs.getString("Email"));
                a.setIsStaff(rs.getBoolean("Is_Staff"));
                a.setName(rs.getString("Name"));
                a.setPhone(rs.getString("Phone"));
                a.setAddress(rs.getString("Adress"));
                a.setPayment(rs.getDouble("Payments"));
                listA.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listA;
    }

    public List<Account> getAccountByRole(int isStaff) {
        List<Account> listRA = new ArrayList<>();
        String sql = "select * from Accounts where Is_Staff=" + isStaff;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("AccountID"));
                a.setUsername(rs.getString("Username"));
                a.setPassword(rs.getString("Password"));
                a.setEmail(rs.getString("Email"));
                a.setIsStaff(rs.getBoolean("Is_Staff"));
                a.setName(rs.getString("Name"));
                a.setPhone(rs.getString("Phone"));
                a.setAddress(rs.getString("Adress"));
                a.setPayment(rs.getDouble("Payments"));
                listRA.add(a);
            }
        } catch (SQLException e) {
            return null;
        }
        return listRA;
    }

    public Account getInfoByLogin(String name, String pass, List<Account> listA) {
        for (Account a : listA) {
            if (name.equals(a.getUsername()) && pass.equals(a.getPassword())) {
                return a;
            }
        }
        return null;
    }

    public int login(Account a, List<Account> listA) {
        for (int i = 0; i < listA.size(); i++) {
            if (a.getUsername().equals(listA.get(i).getUsername()) && a.getPassword().equals(listA.get(i).getPassword())) {
                if (listA.get(i).isIsStaff()) {
                    return 1;
                } else {
                    return 2;
                }
            }
        }
        return 3;
    }

    public Account getAccountById(int id, List<Account> listA) {
        for (Account a : listA) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public boolean checkExistedAccount(String name, String email) {

        String sql = "SELECT Username ,Password, Email FROM Accounts";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                if (name.equals(rs.getString("Username"))) {
                    return true;
                }
                if (email.equals(rs.getString("Email"))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            return true;
        }
        return false;
    }

    public void updateNewCustomer(Account a) {
        String sql = "INSERT INTO Accounts (Username , Password, Email, is_Staff, Payments) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement st1 = connection.prepareStatement(sql);
            st1.setString(1, a.getUsername());
            st1.setString(2, a.getPassword());
            st1.setString(3, a.getEmail());
            st1.setBoolean(4, a.isIsStaff());
            st1.setDouble(5, a.getPayment());
            st1.executeUpdate();
        } catch (SQLException e) {
            return;
        }
    }

    public void updateNewAdmin(Account a) {
        String sql = "INSERT INTO Accounts (Username , Password, Email, is_Staff) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st1 = connection.prepareStatement(sql);
            st1.setString(1, a.getUsername());
            st1.setString(2, a.getPassword());
            st1.setString(3, a.getEmail());
            st1.setBoolean(4, a.isIsStaff());
            st1.executeUpdate();
        } catch (SQLException e) {
            return;
        }
    }

    public void updateNewAccount(String name, String pass, String email) {
        String sql1 = "INSERT INTO Accounts (Username , Password, Email, is_Staff) VALUES (?, ?, ?, ?)";
//        String sql2 = "SELECT AccountID, Username, Password, Email, Is_Staff FROM Accounts order by AccountID desc limit 1";
//        String sql3 = "INSERT INTO UserInfos (AccountID, Name, Phone, Adress) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
//            PreparedStatement st2 = connection.prepareStatement(sql2);
//            PreparedStatement st3 = connection.prepareStatement(sql3);
            st1.setString(1, name);
            st1.setString(2, pass);
            st1.setString(3, email);
            st1.setBoolean(4, false);
            st1.executeUpdate();
//            ResultSet rs = st2.executeQuery();
//            String blank = "";
//            if (rs.next()) {
//                st3.setInt(1, rs.getInt("AccountID"));
//                st3.setString(2, blank);
//                st3.setString(3, blank);
//                st3.setString(4, blank);
//                st3.executeUpdate();
//            }

        } catch (SQLException e) {
            return;
        }
    }

    public void updateExistedAccount(Account a, int isStaff) {
        String sql1 = "UPDATE Accounts SET Username = ?, Password = ?, Email = ?, Is_Staff = ?, Name = ?, Phone = ?, Adress = ? WHERE AccountID = ?";
        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, a.getUsername());
            st1.setString(2, a.getPassword());
            st1.setString(3, a.getEmail());
            st1.setBoolean(4, a.isIsStaff());
            st1.setString(5, a.getName());
            st1.setString(6, a.getPhone());
            st1.setString(7, a.getAddress());
            st1.executeUpdate();

        } catch (SQLException e) {
            return;
        }
    }

    public void updateExistedAccount(Account a, int isStaff, int id) {
        String sql1 = "UPDATE Accounts SET Username = ?, Password = ?, Email = ?, Is_Staff = ?, Name = ?, Phone = ?, Adress = ?, Payments= ? WHERE AccountID = ?";
        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, a.getUsername());
            st1.setString(2, a.getPassword());
            st1.setString(3, a.getEmail());
            st1.setBoolean(4, a.isIsStaff());
            st1.setString(5, a.getName());
            st1.setString(6, a.getPhone());
            st1.setString(7, a.getAddress());
            st1.setDouble(8, a.getPayment());
            st1.setInt(9, a.getId());
            st1.executeUpdate();
        } catch (SQLException e) {
            return;
        }
    }

    public void updateExistedAdmin(Account a, int isStaff, int id) {
        String sql1 = "UPDATE Accounts SET Username = ?, Password = ?, Email = ?, Is_Staff = ?, Name = ?, Phone = ?, Adress = ? WHERE AccountID = ?";
        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, a.getUsername());
            st1.setString(2, a.getPassword());
            st1.setString(3, a.getEmail());
            st1.setBoolean(4, a.isIsStaff());
            st1.setString(5, a.getName());
            st1.setString(6, a.getPhone());
            st1.setString(7, a.getAddress());
            st1.setInt(8, a.getId());
            st1.executeUpdate();
        } catch (SQLException e) {
            return;
        }
    }

    public void deleteAccount(int accountId) {
        String sql1 = "DELETE FROM Feedbacks WHERE AccountID = ?";
        String sql2 = "DELETE FROM Cart WHERE AccountID = ?";
        String sql3 = "DELETE FROM Orders WHERE AccountID = ?";
        String sql4 = "DELETE FROM Accounts WHERE AccountID = ?";

        try {
            // Xóa các phản hồi của người dùng
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setInt(1, accountId);
            st1.executeUpdate();

            // Xóa giỏ hàng của người dùng
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, accountId);
            st2.executeUpdate();

            // Xóa đơn hàng của người dùng
            PreparedStatement st3 = connection.prepareStatement(sql3);
            st3.setInt(1, accountId);
            st3.executeUpdate();

            // Cuối cùng, xóa tài khoản từ bảng Accounts
            PreparedStatement st4 = connection.prepareStatement(sql4);
            st4.setInt(1, accountId);
            st4.executeUpdate();

            System.out.println("Đã xoá tài khoản thành công.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ
        }
    }

    public void updatePassword(int accountId, String newPassword) {
        String sql = "UPDATE Accounts SET Password = ? WHERE AccountID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setInt(2, accountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAccountDownPrice(int accountId, double price) {
        String sql = "UPDATE Accounts SET Payments = Payments - ? WHERE AccountID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, price);
            statement.setInt(2, accountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateAccountPayments(int accountId, double amount) {
        String sql = "UPDATE Accounts SET Payments = Payments + ? WHERE AccountID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, amount);
            st.setInt(2, accountId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}   
