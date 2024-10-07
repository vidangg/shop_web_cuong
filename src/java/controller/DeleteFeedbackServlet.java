/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
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

/**
 *
 * @author QuocCuong
 */
@WebServlet(name = "DeleteFeedbackServlet", urlPatterns = {"/deleteFeedback"})
public class DeleteFeedbackServlet extends HttpServlet {

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
            out.println("<title>Servlet DeleteFeedbackServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteFeedbackServlet at " + request.getContextPath() + "</h1>");
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
        if (account != null && account.isIsStaff()) {
            String fid = request.getParameter("fid");
            try {
                int id = Integer.parseInt(fid);
                FeedbackDAO fd = new FeedbackDAO();
                fd.deleteFeedback(id);
            } catch (Exception e) {
                return;
            }
            response.sendRedirect("manage?mid=5");
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
        HttpSession session = request.getSession(true);
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));

            FeedbackDAO feedbackDAO = new FeedbackDAO();
            boolean success = feedbackDAO.deleteFeedback(feedbackId);

            if (success) {
                response.sendRedirect("item?id=" + request.getParameter("bookId")); // Redirect back to the item page
            } else {
                // Handle error
                response.sendRedirect("start"); // Redirect to an error page if deletion fails
            }
        } else {
            response.sendRedirect("start");
        }

//        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
//
//        FeedbackDAO feedbackDAO = new FeedbackDAO();
//        boolean success = feedbackDAO.deleteFeedback(feedbackId);
//
//        if (success) {
//            response.sendRedirect("item?id=" + request.getParameter("bookId")); // Redirect back to the item page
//        } else {
//            // Handle error
//            response.sendRedirect("error.jsp"); // Redirect to an error page if deletion fails
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
