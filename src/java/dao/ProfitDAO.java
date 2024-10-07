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
import model.Profit;

/**
 *
 * @author Admin
 */
public class ProfitDAO extends DBContext {

    public List<Profit> getAll() {
        List<Profit> listP = new ArrayList<>();
        String sql = "select SUM(total_price) as revenue, SUM(total_price)*30/100 as profit , month(OrderDate) as month, year(OrderDate) as year\n" +
                    "from Orders\n" +
                    "group by month(OrderDate), year(OrderDate)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Profit p = new Profit();
                p.setRevenue(rs.getFloat("revenue"));
                p.setProfit(rs.getFloat("profit"));
                String month = rs.getString("month");
                String year = rs.getString("year");
                p.setPeriod( month+ "/" + year);
                listP.add(p);
            }
        } catch (SQLException e) {
            return null;
        }
        return listP;
    }
}
