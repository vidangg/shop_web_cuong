/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BookDAO;
import dao.FeedbackDAO;
import dao.OrderDAO;
import dao.OrderItemsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.*;
import java.text.*;
import java.util.*;
import model.Account;
import model.Book;
import model.Cart;
import model.CartItems;
import model.Feedback;
import model.Order;
import model.OrderItems;

/**
 *
 * @author QuocCuong
 */
@WebServlet(name = "UpdateFeedbackServlet", urlPatterns = {"/updateFeedback"})
public class UpdateFeedbackServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateFeedbackServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateFeedbackServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(true);
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("start");
        } else {
            session.setAttribute("account", account);
            String bid = request.getParameter("bid");
            BookDAO bd = new BookDAO();
            FeedbackDAO fd = new FeedbackDAO();
            OrderDAO od = new OrderDAO();
            if (bid != null) {
                int bookId = Integer.parseInt(bid);
                List<Book> listB = bd.getAll();
                Book b = bd.getBookByID(bookId, listB);
                List<Feedback> feedbackList = fd.getFeedbackByBookId(bookId);
                boolean hasFeedback = fd.hasFeedback(account.getId(), bookId);
                boolean hasPurchased = od.hasPurchased(account.getId(), bookId);
                // Định dạng ngày tháng cho từng phản hồi
                for (Feedback feedback : feedbackList) {
                    feedback.setFormattedDate(formatDate(feedback.getFeedbackDate()));
                }
                
                session.setAttribute("account", account);
                request.setAttribute("book", b);
                request.setAttribute("feedbackList", feedbackList);
                request.setAttribute("hasFeedback", hasFeedback);
                request.setAttribute("hasPurchased", hasPurchased);
                request.getRequestDispatcher("updateFeedback.jsp").forward(request, response);
            }
        }
//        HttpSession session = request.getSession(true);
//        Account account = (Account) session.getAttribute("account");
//        if (account == null) {
//            response.sendRedirect("start");
//        } else {
//            session.setAttribute("account", account);
//            String bid = request.getParameter("bid");
//            BookDAO bd = new BookDAO();
//            FeedbackDAO fd = new FeedbackDAO();
//            if (bid != null) {
//                int bookId = Integer.parseInt(bid);
//                List<Book> listB = bd.getAll();
//                Book b = bd.getBookByID((bookId), listB);
//                List<Feedback> feedbackList = fd.getFeedbackByBookId((bookId));
//                session.setAttribute("account", account);
//                request.setAttribute("book", b);
//                request.setAttribute("feedbackList", feedbackList);
//                request.getRequestDispatcher("updateFeedback.jsp").forward(request, response);
//            }
//        }

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
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("start");
            return;
        }

        int accountId = Integer.parseInt(request.getParameter("accountId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String feedbackText = request.getParameter("feedbackText");
        String feedbackTextt = feedbackText.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        Feedback existingFeedback = feedbackDAO.getFeedbackByAccountAndBook(accountId, bookId);

        if (existingFeedback == null) {
            
            // Nếu chưa có phản hồi từ tài khoản này cho sản phẩm này, thêm mới
            Feedback newFeedback = new Feedback(accountId, bookId, feedbackTextt, new Timestamp(System.currentTimeMillis()));
            boolean success = feedbackDAO.addFeedback(newFeedback);

            if (success) {
                response.sendRedirect("updateFeedback?bid=" + bookId);
            } else {
                response.getWriter().println("Thêm phản hồi thất bại!");
            }
        } else {
            // Nếu đã có phản hồi, cập nhật nội dung phản hồi
            existingFeedback.setFeedback(feedbackTextt);
            boolean updated = feedbackDAO.updateFeedback(existingFeedback);

            if (updated) {
                response.sendRedirect("updateFeedback?bid=" + bookId);
            } else {
                response.getWriter().println("Cập nhật phản hồi thất bại!");
            }
        }
//        HttpSession session = request.getSession(true);
//        Account account = (Account) session.getAttribute("account");
//        if (account == null) {
//            response.sendRedirect("start");
//            return;
//        }
//        int accountId = account.getId();
//        int bookId = Integer.parseInt(request.getParameter("bookId"));
//        String feedbackText = request.getParameter("feedbackText");
//
//        FeedbackDAO feedbackDAO = new FeedbackDAO();
//        if (!feedbackDAO.hasFeedback(accountId, bookId)) {
//            Feedback newFeedback = new Feedback(accountId, bookId, feedbackText, new Timestamp(System.currentTimeMillis()));
//            boolean success = feedbackDAO.addFeedback(newFeedback);
//
//            if (success) {
//                response.sendRedirect("updateFeedback?bid=" + bookId);
//            } else {
//                response.getWriter().println("Thêm phản hồi thất bại!");
//            }
//        } else {
//            response.sendRedirect("updateFeedback?bid=" + bookId);
//        }
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
