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
import model.Category;

/**
 *
 * @author Admin
 */
public class CategoryDAO extends DBContext {
    
    public List<Category> getAll() {
        List<Category> listC = new ArrayList<>();
        String sql = "select CategoryID, CategoryName from Categories";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("CategoryID"));
                c.setName(rs.getString("CategoryName"));
                listC.add(c);
            }
        } catch (SQLException e) {
            return null;        
        }
        return listC;
    }
    
    public Category getCategoryById(int id, List<Category> listC){
        for (Category c : listC){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }
}
