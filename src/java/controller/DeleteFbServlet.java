/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.FeedbackDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Book;

/**
 *
 * @author QuocCuong
 */
@WebServlet(name = "DeleteFbServlet", urlPatterns = {"/deleteFb"})
public class DeleteFbServlet extends HttpServlet {

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
            out.println("<title>Servlet DeleteFbServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteFbServlet at " + request.getContextPath() + "</h1>");
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

        if (account != null && !account.isIsStaff()) {
            String fid = request.getParameter("fid");
            try {
                int id = Integer.parseInt(fid);
                FeedbackDAO fd = new FeedbackDAO();
                fd.deleteFeedback(id);
                String bookId = request.getParameter("bookId");
                response.sendRedirect("updateFeedback?bid=" + bookId);
            } catch (Exception e) {
                response.sendRedirect("start");
            }
        } else {
            response.sendRedirect("start");
        }
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
//        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
//        FeedbackDAO feedbackDAO = new FeedbackDAO();
//        boolean success = feedbackDAO.deleteFeedback(feedbackId);
//        if (success) {
//            response.sendRedirect("updateFeedback?bid=" + request.getParameter("bid"));
//        } else {
//            response.getWriter().println("Xóa phản hồi thất bại!");
//        }
        HttpSession session = request.getSession(true);
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            boolean success = feedbackDAO.deleteFeedback(feedbackId);

            if (success) {
                response.sendRedirect("updateFeedback?bid=" + request.getParameter("bid")); // Redirect back to the item page
            } else {
                // Handle error
                response.sendRedirect("start"); // Redirect to an error page if deletion fails
            }
        } else {
            response.sendRedirect("start");
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
