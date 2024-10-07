/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BookDAO;
import dao.FeedbackDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import model.Account;
import model.Book;
import model.Cart;
import model.CartItems;
import model.Feedback;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ItemServlet", urlPatterns = {"/item"})
public class ItemServlet extends HttpServlet {
    private String formatDate(Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH 'giờ' mm 'phút ngày' dd 'tháng' M 'năm' yyyy", new Locale("vi", "VN"));
        return dateFormat.format(timestamp);
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ItemServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ItemServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookID = request.getParameter("id");
        BookDAO bd = new BookDAO();
        FeedbackDAO fd = new FeedbackDAO();
        List<Book> listB = bd.getAll();
        Book b = bd.getBookByID(Integer.parseInt(bookID), listB);
        List<Book> listRB = bd.getRelatedBook(Integer.parseInt(bookID));
        List<Feedback> feedbackList = fd.getFeedbackByBookId(Integer.parseInt(bookID));
        for (Feedback feedback : feedbackList) {
            feedback.setFormattedDate(formatDate(feedback.getFeedbackDate()));
        }
        request.setAttribute("listRelatedBook", listRB);
        request.setAttribute("book", b);
        request.setAttribute("feedbackList", feedbackList);
        request.getRequestDispatcher("item.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        List<CartItems> listI = cart.getItems();
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            session.setAttribute("cart", cart);
            session.setAttribute("size", listI.size());
            response.sendRedirect("login");
        } else {
            String quantity;
            String price;
            try {
                String id = request.getParameter("id");
                BookDAO bd = new BookDAO();
                List<Book> listB = bd.getAll();
                Book b = bd.getBookByID(Integer.parseInt(id), listB);
                quantity = request.getParameter("quantity");
                price = request.getParameter("price");
                request.setAttribute("book", b);
                int itemQuantity = Integer.parseInt(quantity);
                float itemPrice = Float.parseFloat(price) * itemQuantity;
                CartItems item = new CartItems(b, itemQuantity, itemPrice);
                cart.addItem(item);
            } catch (NumberFormatException e) {
                return;
            }
            listI = cart.getItems();
            session.setAttribute("cart", cart);
            session.setAttribute("size", listI.size());
            doGet(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
