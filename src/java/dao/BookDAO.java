package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Book;
import model.CartItems;
import model.Publisher;

public class BookDAO extends DBContext {

    public List<Book> getAll() {
        List<Book> listB = new ArrayList<>();
        String sql = "select BookID, Title, PublisherName, Price, Quantity, Image_Link\n"
                + "from Books b, Publishers p\n"
                + "where b.PublisherID = p.PublisherID and Quantity != 0\n"
                + "order by BookID desc;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("BookID"));
                b.setTitle(rs.getString("Title"));
                b.setPublisher(rs.getString("PublisherName"));
                b.setPrice(rs.getFloat("Price"));
                b.setQuantity(rs.getInt("Quantity"));
                b.setImgLink(rs.getString("Image_Link"));
                listB.add(b);
            }
        } catch (SQLException e) {
            return null;
        }
        return listB;
    }

    public float getBookPriceByID(int bookID) {
        String sql = "SELECT Price FROM Books WHERE BookID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, bookID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getFloat("Price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Return -1 if the book is not found or an error occurs
    }

    public List<Book> getAllIncludeRunOut() {
        List<Book> listB = new ArrayList<>();
        String sql = "SELECT BookID, Title, PublisherName, Price, Quantity, Image_Link\n"
                + "FROM Books b, Publishers p  \n"
                + "WHERE b.PublisherID = p.PublisherID\n"
                + "ORDER BY BookID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("BookID"));
                b.setTitle(rs.getString("Title"));
                b.setPublisher(rs.getString("PublisherName"));
                b.setPrice(rs.getFloat("Price"));
                b.setQuantity(rs.getInt("Quantity"));
                b.setImgLink(rs.getString("Image_Link"));
                listB.add(b);
            }
        } catch (SQLException e) {
            return null;
        }
        return listB;
    }

    public List<Book> getBookByKeyword(String keyword) {
        List<Book> listKB = new ArrayList<>();
        String sql = "SELECT BookID, Title, PublisherName, Price, Quantity, Image_Link\n"
                + "FROM Books b, Publishers p\n"
                + "WHERE b.PublisherID = p.PublisherID and Quantity != 0 and Title LIKE ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + keyword + "%"); // Set the keyword with wildcard characters
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("BookID"));
                b.setTitle(rs.getString("Title"));
                b.setPublisher(rs.getString("PublisherName"));
                b.setPrice(rs.getFloat("Price"));
                b.setQuantity(rs.getInt("Quantity"));
                b.setImgLink(rs.getString("Image_Link"));
                listKB.add(b);
            }
        } catch (SQLException e) {
            return null;
        }
        return listKB;
    }

//    public List<Book> getBookByKeyword(String keyword) {
//        List<Book> listKB = new ArrayList<>();
//        String sql = "SELECT BookID, Title, PublisherName, Price, Quantity, Image_Link\n"
//                + "FROM Books b, Publishers p\n"
//                + "WHERE b.PublisherID = p.PublisherID and Quantity != 0 and Title LIKE N'%" + keyword + "%'";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                Book b = new Book();
//                b.setId(rs.getInt("BookID"));
//                b.setTitle(rs.getString("Title"));
//                b.setPublisher(rs.getString("PublisherName"));
//                b.setPrice(rs.getFloat("Price"));
//                b.setQuantity(rs.getInt("Quantity"));
//                b.setImgLink(rs.getString("Image_Link"));
//                listKB.add(b);
//            }
//        } catch (SQLException e) {
//            return null;        
//        }
//        return listKB;
//    }
    
    public Book getBookByID(int id, List<Book> listB) {
        for (Book b : listB) {
            if (id == b.getId()) {
                return b;
            }
        }
        return null;
    }

    public List<Book> getBookByPubID(int id) {
        List<Book> listPB = new ArrayList<>();
        String sql = "SELECT BookID, Title, PublisherName, Price, Quantity, Image_Link\n"
                + "FROM Books b, Publishers p  \n"
                + "WHERE b.PublisherID = p.PublisherID AND Quantity != 0 and p.PublisherID = ?  \n"
                + "ORDER BY BookID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("BookID"));
                b.setTitle(rs.getString("Title"));
                b.setPublisher(rs.getString("PublisherName"));
                b.setPrice(rs.getFloat("Price"));
                b.setImgLink(rs.getString("Image_Link"));
                listPB.add(b);
            }
            return listPB;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Book> getBookByCateID(int id) {
        List<Book> listCB = new ArrayList<>();
        String sql = "select b.BookID, b.Title, PublisherName, b.Price, b.Quantity, b.Image_Link\n"
                + "from Books b, Categories c, Books_Categories bc, Publishers p\n"
                + "where b.BookID = bc.BookID and c.CategoryID = bc.CategoryID and p.PublisherID = b.PublisherID and b.Quantity != 0 and c.CategoryID = ? \n"
                + "ORDER BY b.BookID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("BookID"));
                b.setTitle(rs.getString("Title"));
                b.setPublisher(rs.getString("PublisherName"));
                b.setPrice(rs.getFloat("Price"));
                b.setImgLink(rs.getString("Image_Link"));
                listCB.add(b);
            }
            return listCB;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Book> getRelatedBook(int id) {
        List<Book> listRB = new ArrayList<>();
        String sql1 = "select b.BookID, Title, c.CategoryID\n"
                + "from Books b, Categories c, Books_Categories bc\n"
                + "where b.BookID = bc.BookID and c.CategoryID = bc.CategoryID and Quantity != 0 and b.BookID = ?\n"
                + "LIMIT 1";//Truy vấn để lấy CategoryID của cuốn sách có BookID được chỉ định.
        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setInt(1, id);
            ResultSet rs1 = st1.executeQuery();
            if (rs1.next()) {
                int categoryId = rs1.getInt("CategoryID");
                String sql2 = "select b.BookID, Title, PublisherName, Price, Quantity, Image_Link\n"
                        + "from Books b, Books_Categories bc, Publishers p\n"
                        + "where b.BookID = bc.BookID and b.PublisherID = p.PublisherID and Quantity != 0 and bc.CategoryID = ?\n"
                        + "order by BookID\n"
                        + "LIMIT 4";//Truy vấn sau đó để lấy các sách khác cùng loại (CategoryID tương tự) từ bảng Books, sắp xếp theo BookID và giới hạn chỉ lấy 4 cuốn sách.
                PreparedStatement st2 = connection.prepareStatement(sql2);
                st2.setInt(1, categoryId);
                ResultSet rs2 = st2.executeQuery();
                while (rs2.next()) {
                    Book b = new Book();
                    b.setId(rs2.getInt("BookID"));
                    b.setTitle(rs2.getString("Title"));
                    b.setPublisher(rs2.getString("PublisherName"));
                    b.setPrice(rs2.getFloat("Price"));
                    b.setImgLink(rs2.getString("Image_Link"));
                    listRB.add(b);
                }
                return listRB;
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    public void updateNewBook(Book b, int pId) {
        String sql = "INSERT INTO Books (Title, PublisherID , Price, Quantity, Image_Link) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, b.getTitle());
            st.setInt(2, pId);
            st.setDouble(3, b.getPrice());
            st.setInt(4, b.getQuantity());
            st.setString(5, b.getImgLink());
            st.executeUpdate();
        } catch (SQLException e) {
            return;
        }
    }

    public int updateBook(Book b, int pId) {
        String sql = "UPDATE Books SET Title = ?, PublisherID = ?, Price = ?, Quantity = ?, Image_Link = ? WHERE BookID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, b.getTitle());
            st.setInt(2, pId);
            st.setDouble(3, b.getPrice());
            st.setInt(4, b.getQuantity());
            st.setString(5, b.getImgLink());
            st.setInt(6, b.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
        return 1;
    }

    public int updateBoughtBook(CartItems item) {
        String sql = "UPDATE Books SET Title = ?, PublisherID = ?, Price = ?, Quantity = ?, Image_Link = ? WHERE BookID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, item.getBook().getTitle());
            PublisherDAO pd = new PublisherDAO();
            List<Publisher> listP = pd.getAll();
            int pId = pd.getIdByName(item.getBook().getPublisher(), listP);
            st.setInt(2, pId);
            st.setDouble(3, item.getBook().getPrice());
            int newQuantity = item.getBook().getQuantity() - item.getQuantity();
            if (newQuantity <= 0) {
                newQuantity = 0;
            }
            st.setInt(4, newQuantity);
            st.setString(5, item.getBook().getImgLink());
            st.setInt(6, item.getBook().getId());
            st.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
        return 1;
    }

    public void updateReturnedBook(int bId, int quantity) {
        String sql = "UPDATE Books SET Title = ?, PublisherID = ?, Price = ?, Quantity = ?, Image_Link = ? WHERE BookID = ?";
        try {
            List<Book> listB = getAllIncludeRunOut();
            Book b = getBookByID(bId, listB);
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, b.getTitle());
            PublisherDAO pd = new PublisherDAO();
            List<Publisher> listP = pd.getAll();
            int pId = pd.getIdByName(b.getPublisher(), listP);
            st.setInt(2, pId);
            st.setDouble(3, b.getPrice());
            int newQuantity = b.getQuantity() + quantity;
            st.setInt(4, newQuantity);
            st.setString(5, b.getImgLink());
            st.setInt(6, b.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            return;
        }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM Books WHERE BookID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            return;
        }
    }

    public List<Book> getHotBook() {
        List<Book> listHB = new ArrayList<>();
        String sql = "select b.BookID, Title, PublisherName, Price, b.Quantity, Image_Link\n"
                + "from Books b, OrderItems oi, Publishers p\n"
                + "where b.BookID = oi.BookID and b.PublisherID = p. PublisherID and b.Quantity != 0\n"
                + "group by b.BookID, Title, PublisherName, Price, b.Quantity, Image_Link\n"
                + "order by SUM(oi.Quantity) desc\n"
                + "limit 12";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("BookID"));
                b.setTitle(rs.getString("Title"));
                b.setPublisher(rs.getString("PublisherName"));
                b.setPrice(rs.getFloat("Price"));
                b.setQuantity(rs.getInt("Quantity"));
                b.setImgLink(rs.getString("Image_Link"));
                listHB.add(b);
            }
        } catch (SQLException e) {
            return null;
        }
        return listHB;
    }

    public List<Book> getNewBook() {
        List<Book> listNB = new ArrayList<>();
        String sql = "select b.BookID, Title, PublisherName, Price, b.Quantity, Image_Link\n"
                + "from Books b, Publishers p\n"
                + "where b.PublisherID = p. PublisherID and b.Quantity != 0\n"
                + "order by BookID desc\n"
                + "limit 12";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("BookID"));
                b.setTitle(rs.getString("Title"));
                b.setPublisher(rs.getString("PublisherName"));
                b.setPrice(rs.getFloat("Price"));
                b.setQuantity(rs.getInt("Quantity"));
                b.setImgLink(rs.getString("Image_Link"));
                listNB.add(b);
            }
        } catch (SQLException e) {
            return null;
        }
        return listNB;
    }
}
