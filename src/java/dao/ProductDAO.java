/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Publisher;

/**
 *
 * @author Admin
 */
public class ProductDAO extends DBContext {

    public List<Publisher> getAll() {
        List<Publisher> list = new ArrayList<>();
        String sql = "SELECT PublisherID, PublisherName FROM Publishers";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Publisher p = new Publisher();
                p.setId(rs.getInt("PublisherID"));
                p.setName(rs.getString("PublisherName"));
                list.add(p);
            }
        } catch (SQLException e) {
            return null;
        }
        return list;
    }
}
