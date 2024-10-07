/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.BookDAO;
import dao.CategoryDAO;
import dao.PublisherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Book;
import model.Cart;
import model.Category;
import model.CartItems;
import model.Publisher;

/**
 *
 * @author Admin
 */
@WebServlet(name="SearchServlet", urlPatterns={"/search"})
public class SearchServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet SearchServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession(true);       
        String rpid = request.getParameter("pid");
        String rcid = request.getParameter("cid");
        BookDAO bd = new BookDAO();
        Cart cart = null;       
        Object o = session.getAttribute("cart");
        if(o != null){
            cart = (Cart)o;            
        }else{
            cart = new Cart();
        }
        List<CartItems> listI = cart.getItems();     
        session.setAttribute("cart", cart);
        request.setAttribute("size", listI.size());
        if (rpid != null){
            int pid = Integer.parseInt(rpid);
            PublisherDAO pd = new PublisherDAO();
            List<Publisher> listP = pd.getAll();
            Publisher p = pd.getPublisherByID(pid, listP);
            List<Book> listPB = bd.getBookByPubID(pid);
            session.setAttribute("listBookBySearch", listPB);
            request.setAttribute("publisher", p);
            request.getRequestDispatcher("bookBySearch.jsp").forward(request, response);     
        }              
        if (rcid != null){
            int cid = Integer.parseInt(rcid);
            CategoryDAO cd = new CategoryDAO();
            List<Category> listC = cd.getAll();
            Category c = cd.getCategoryById(cid, listC);
            List<Book> listCB = bd.getBookByCateID(cid);
            session.setAttribute("listBookBySearch", listCB);
            request.setAttribute("category", c);
            request.getRequestDispatcher("bookBySearch.jsp").forward(request, response);     
        }                                   
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String keyword = request.getParameter("keyword");
        BookDAO bd = new BookDAO();
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if(o != null){
            cart = (Cart)o;
        }else{
            cart = new Cart();
        }
        List<CartItems> listI = cart.getItems();
        session.setAttribute("cart", cart);
        request.setAttribute("size", listI.size());
        if (keyword != null){
            List<Book> listKB = bd.getBookByKeyword(keyword);
            session.setAttribute("listBookBySearch", listKB);
            request.setAttribute("keyword", keyword);
            request.getRequestDispatcher("bookBySearch.jsp").forward(request, response);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
