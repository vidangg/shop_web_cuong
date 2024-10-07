/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Cart;
import model.CartItems;

/**
 *
 * @author QuocCuong
 */
@WebServlet(name = "ConfirmServlet", urlPatterns = {"/confirm"})
public class ConfirmServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConfirmServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfirmServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String oid = request.getParameter("oid");
        int confirmId = Integer.parseInt(request.getParameter("confirmId"));
        String msg = "";
        switch (confirmId) {
            case 1:
                msg = "Đã hoàn thành";
                if (oid != null) {
                    OrderDAO od = new OrderDAO();
                    od.updateOrder(Integer.parseInt(oid), msg);
                    response.sendRedirect("orderHistory");
                }
                break;
            case 2:
                msg = "Đang giao";
                if (oid != null) {
                    OrderDAO od = new OrderDAO();
                    od.updateOrder(Integer.parseInt(oid), msg);
                    response.sendRedirect("staff");
                }
                break;
            case 3:
                msg = "Đã hoàn thành";
                if (oid != null) {
                    OrderDAO od = new OrderDAO();
                    od.updateOrder(Integer.parseInt(oid), msg);
                    response.sendRedirect("staff");
                }
                break;
            default:
                throw new AssertionError();
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
