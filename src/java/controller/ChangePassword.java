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
import java.util.regex.Pattern;
import model.Account;

/**
 *
 * @author QuocCuong
 */
@WebServlet(name = "ChangePassword", urlPatterns = {"/changePassword"})
public class ChangePassword extends HttpServlet {

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
            out.println("<title>Servlet ChangePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword at " + request.getContextPath() + "</h1>");
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
        Account account1 = (Account) session.getAttribute("account");
        if (account1 != null) {
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
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
        Account account1 = (Account) session.getAttribute("account");
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        AccountDAO ad = new AccountDAO();
        if (account1 != null) {
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");

            // Get current user from session or database
            Account currentAccount = (Account) request.getSession().getAttribute("account");
            if (Account.isStrongPassword(newPassword)) {
                if (currentAccount != null) {
                    if (currentAccount.getPassword().equals(currentPassword)) {
                        AccountDAO accountDAO = new AccountDAO();
                        accountDAO.updatePassword(currentAccount.getId(), newPassword);
                        response.sendRedirect(request.getContextPath() + "/info");
                    } else {
                        request.setAttribute("err", "Bạn đã nhập sai mật khẩu.");
                        request.getRequestDispatcher("changepassword.jsp").forward(request, response);
                    }
                } else {
                    // No user in session, handle error or redirect to login
                    response.sendRedirect(request.getContextPath() + "/login");
                }
            }
            else{
                String err = "Mật khẩu không đủ mạnh!";
                    request.setAttribute("err", err);
                    request.getRequestDispatcher("changepassword.jsp").forward(request, response);
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
