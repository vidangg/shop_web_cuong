/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
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
import java.util.List;
import model.Account;
import model.Book;
import model.Feedback;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ManageServlet", urlPatterns = {"/manage"})
public class ManageServlet extends HttpServlet {

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
            out.println("<title>Servlet ManageServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageServlet at " + request.getContextPath() + "</h1>");
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
            AccountDAO ad = new AccountDAO();
            BookDAO bd = new BookDAO();
            FeedbackDAO fd = new FeedbackDAO();
            String mid = request.getParameter("mid");
            if (mid != null) {
                int manageId = Integer.parseInt(mid);
                switch (manageId) {
                    case 1:
                        List<Book> listB = bd.getAllIncludeRunOut();
                        request.setAttribute("listBook", listB);
                        request.getRequestDispatcher("manageBook.jsp").forward(request, response);
                        break;
                    case 2:
                        List<Book> listHB = bd.getHotBook();
                        request.setAttribute("listHotBook", listHB);
                        request.getRequestDispatcher("manageProfit.jsp").forward(request, response);
                        break;
                    case 3:
                        List<Account> listCA = ad.getAccountByRole(0);
                        request.setAttribute("listCustomer", listCA);
                        request.getRequestDispatcher("manageCustomer.jsp").forward(request, response);
                        break;
                    case 4:
                        List<Account> listSA = ad.getAccountByRole(1);
                        request.setAttribute("listStaff", listSA);
                        request.getRequestDispatcher("manageStaff.jsp").forward(request, response);
                        break;
                    case 5:
                        List<Feedback> listFB = fd.getAllFeedback();
                        request.setAttribute("listFeedback", listFB);
                        request.getRequestDispatcher("manageFeedback.jsp").forward(request, response);
                        break;
                    default:
                        throw new AssertionError();
                }
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
        String mid = request.getParameter("mid");
        AccountDAO ad = new AccountDAO();
        if (mid != null) {
            int manageId = Integer.parseInt(mid);
            switch (manageId) {
                case 1:
                    BookDAO bd = bookDAO();
                    
                    List<Book> listB = bd.getAll();
                    request.setAttribute("listB", listB);
                    request.getRequestDispatcher("manageBook.jsp").forward(request, response);
                    break;
                case 2:
                    request.getRequestDispatcher("manageProfit.jsp").forward(request, response);
                    break;
                case 3:
                    List<Account> listCA = ad.getAccountByRole(0);
                    request.setAttribute("listCustomer", listCA);
                    request.getRequestDispatcher("manageCustomer.jsp").forward(request, response);
                    break;
                case 4:
                    List<Account> listSA = ad.getAccountByRole(1);
                    request.setAttribute("listStaff", listSA);
                    request.getRequestDispatcher("manageStaff.jsp").forward(request, response);
                    break;
                case 5:
                    FeedbackDAO fd = feedbackDAO();
                    List<Feedback> listFB = fd.getAllFeedback();
                    request.setAttribute("listFeedback", listFB);
                    request.getRequestDispatcher("manageFeedback.jsp").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
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

    private BookDAO bookDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private FeedbackDAO feedbackDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
