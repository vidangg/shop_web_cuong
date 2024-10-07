/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Account;
import model.Cart;
import model.CartItems;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
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
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        List<CartItems> listI = cart.getItems();
        session.setAttribute("cart", cart);
        session.setAttribute("size", listI.size());
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        AccountDAO ad = new AccountDAO();
        String name = request.getParameter("user");
        String pass = request.getParameter("pass");
        String confirmPass = request.getParameter("confirmPass");
        String email = request.getParameter("email");

        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            if (pass.equals(confirmPass)) {
                if (Account.isStrongPassword(pass)) {
                    boolean exist = ad.checkExistedAccount(name, email);
                    if (exist == false) {
                        ad.updateNewAccount(name, pass, email);
                        Account a = new Account(name, pass);
                        List<Account> listA = ad.getAll();
                        Account account = ad.getInfoByLogin(name, pass, listA);
                        session.setAttribute("account", account);
                        Cart cart = null;
                        Object o = session.getAttribute("cart");
                        if (o != null) {
                            cart = (Cart) o;
                        } else {
                            cart = new Cart();
                        }
                        List<CartItems> listI = cart.getItems();
                        session.setAttribute("cart", cart);
                        session.setAttribute("size", listI.size());
                        response.sendRedirect("start");
                    } else {
                        String err = "Tài khoản hoặc email đã tồn tại!";
                        request.setAttribute("err", err);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }
                } else {
                    String err = "Mật khẩu không đủ mạnh!";
                    request.setAttribute("err", err);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            } else {
                String err = "Mật khẩu xác nhận không trùng khớp!";
                request.setAttribute("err", err);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else {
            String err = "Email không đúng định dạng!";
            request.setAttribute("err", err);
            request.getRequestDispatcher("register.jsp").forward(request, response);
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
