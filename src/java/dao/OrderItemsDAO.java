package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderItems;

public class OrderItemsDAO extends DBContext {

    public List<OrderItems> getAll() {
        List<OrderItems> listO = new ArrayList<>();
        String sql = "select OrderItemID, OrderID, BookID, Quantity, Unit_Price from orderitems";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderItems o = new OrderItems();
                o.setOrderItemID(rs.getInt("OrderItemID"));
                o.setOrderID(rs.getInt("OrderID"));
                o.setBookID(rs.getInt("BookID"));
                o.setQuantity(rs.getInt("Quantity"));
                o.setUnit_Price(rs.getFloat("Unit_Price"));
                listO.add(o);
            }
        } catch (SQLException e) {
            return null;
        }
        return listO;
    }

    public List<OrderItems> getByOrderId(int orderId) {
        List<OrderItems> list = new ArrayList<>();
        String sql = "SELECT * FROM orderitems WHERE OrderID = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OrderItems item = new OrderItems();
                item.setOrderItemID(rs.getInt("OrderItemID"));
                item.setOrderID(rs.getInt("OrderID"));
                item.setBookID(rs.getInt("BookID"));
                item.setQuantity(rs.getInt("Quantity"));
                item.setUnit_Price(rs.getFloat("Unit_Price"));
                // Các thông tin khác nếu có

                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
