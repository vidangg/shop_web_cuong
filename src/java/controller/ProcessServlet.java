/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.BookDAO;
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
import model.CartItems;

/**
 *
 * @author Admin
 */
@WebServlet(name="ProcessServlet", urlPatterns={"/process"})
public class ProcessServlet extends HttpServlet {
   
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
            out.println("<title>Servlet ProcessServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessServlet at " + request.getContextPath () + "</h1>");
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
        Cart cart = null;
        int id = Integer.parseInt(request.getParameter("id"));
        int num = Integer.parseInt(request.getParameter("num"));
        Object o = session.getAttribute("cart");
        if(o != null){
            cart = (Cart)o;            
        }else{
            cart = new Cart();
        }
        try{
            BookDAO bd = new BookDAO();
            List<Book> listB = bd.getAll();
            Book b = bd.getBookByID(id, listB);
            double price = b.getPrice();
            CartItems i = new CartItems(b, num, price);
            if(num == -1 && cart.getQuantityById(b.getId()) <= 1){
                cart.removeItem(id);
            }else{
                cart.addItem(i);
            }
        }catch(Exception e){return;
            
        }
        session.setAttribute("cart", cart);
        List<CartItems> listI = cart.getItems();
        session.setAttribute("size", listI.size());
        response.sendRedirect("wishlist");
        //request.getRequestDispatcher("wishlist").forward(request, response);    
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
        Cart cart = null;
        int id = Integer.parseInt(request.getParameter("id"));
        Object o = session.getAttribute("cart");
        if(o != null){
            cart = (Cart)o;            
        }else{
            cart = new Cart();
        }
        try{
            cart.removeItem(id);
        }catch(Exception e){
            return;
        }
        session.setAttribute("cart", cart);
        List<CartItems> listI = cart.getItems();
        session.setAttribute("size", listI.size());
        response.sendRedirect("wishlist");
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
