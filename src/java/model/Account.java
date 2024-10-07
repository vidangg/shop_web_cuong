package model;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Account {

    int id;
    String username;
    String password;
    String email;
    boolean isStaff;
    String name;
    String phone;
    String address;
    Double payment;
    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, String email, boolean isStaff) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isStaff = isStaff;
    }

    public Account(String username, String password, String email, boolean isStaff, String name, String phone, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isStaff = isStaff;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Account(int id, String username, String password, String email, boolean isStaff, String name, String phone, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isStaff = isStaff;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Account(String username, String password, String email, boolean isStaff, Double payment) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isStaff = isStaff;
        this.payment = payment;
    }

    public Account(String username, String password, String email, boolean isStaff, String name, String phone, String address, Double payment) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isStaff = isStaff;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.payment = payment;
    }

    public Account(int id, String username, String password, String email, boolean isStaff, String name, String phone, String address, Double payment) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isStaff = isStaff;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsStaff() {
        return isStaff;
    }

    public void setIsStaff(boolean isStaff) {
        this.isStaff = isStaff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public static boolean isStrongPassword(String password) {
        // Kiểm tra độ dài của mật khẩu (ít nhất 8 ký tự)
        if (password.length() < 8) {
            return false;
        }
        // Kiểm tra sự hiện diện của ít nhất một ký tự chữ cái
        if (!Pattern.compile("[a-zA-Z]").matcher(password).find()) {
            return false;
        }
        // Kiểm tra sự hiện diện của ít nhất một ký tự số
        if (!Pattern.compile("\\d").matcher(password).find()) {
            return false;
        }
        // Kiểm tra sự hiện diện của ít nhất một ký tự đặc biệt
        if (!Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find()) {
            return false;
        }
        // Nếu mọi tiêu chí đều đáp ứng, mật khẩu được coi là đủ mạnh
        return true;
    }
    
}
