/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.BookDAO;
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
import model.Account;
import model.Book;
import model.Cart;
import model.CartItems;
import model.Order;

/**
 *
 * @author Admin
 */
@WebServlet(name = "BuyServlet", urlPatterns = {"/buy"})
public class BuyServlet extends HttpServlet {

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
            out.println("<title>Servlet BuyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuyServlet at " + request.getContextPath() + "</h1>");
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
        // Get the price, quantity, checkout money of a cart and process purchasing
        HttpSession session = request.getSession();
        int ok = 0;
        String checkout = request.getParameter("checkout");
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");
        Cart cart = null;
        OrderDAO od = new OrderDAO();
        BookDAO bd = new BookDAO();
        AccountDAO ad = new AccountDAO();
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        List<CartItems> listI = cart.getItems();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            session.setAttribute("cart", cart);
            session.setAttribute("size", listI.size());
            response.sendRedirect("login");
        } else {
            int accountId = account.getId();
            double payment = account.getPayment();
            try {
                if (checkout != null) { // Mua toan bo san pham
                    if (payment >= cart.getTotalPrice()) {
                        double aa = cart.getTotalPrice();
                        od.addCartOrder(accountId, cart);
                        cart = new Cart();
                        ok = 1;
                        ad.getAccountDownPrice(accountId, aa);
                        Account a = new AccountDAO().getAccountById(account.getId(), new AccountDAO().getAll());
                        session.setAttribute("account", a);
                    } else {
                        String err = "Hết tiền rồi ông kễnh, nạp zô mà mua tiếp! Chuyển hướng sau 3s";
                        request.getSession().setAttribute("err", err);
                        response.sendRedirect("wishlist");
                        return;
                    }
                } else if (quantity != null && price != null) { //Mua 1 san pham truc tiep tu ben ngoai 
                    int itemQuantity = Integer.parseInt(quantity);
                    float itemPrice = Float.parseFloat(price) * itemQuantity;
                    if (payment >= itemPrice) {
                        ok = 1;
                        String rid = request.getParameter("id");
                        if (rid != null) {
                            int id = Integer.parseInt(rid);
                            List<Book> listB = bd.getAll();
                            Book b = bd.getBookByID(id, listB);
                            CartItems item = new CartItems(b, itemQuantity, itemPrice);
                            od.addItemOrder(accountId, item);
                        }
                        ad.getAccountDownPrice(accountId, itemPrice);
                        Account a = new AccountDAO().getAccountById(account.getId(), new AccountDAO().getAll());
                        session.setAttribute("account", a);
                    } else {
                        String err = "Hết tiền rồi ông kễnh, nạp zô mà mua tiếp! Chuyển hướng sau 3s";
                        request.getSession().setAttribute("err", err);
                        int bookId = Integer.parseInt(request.getParameter("id"));
                        response.sendRedirect("item?id=" + bookId);
                        return;
                    }
                } else { // Mua 1 san pham tu cart
                    String rid = request.getParameter("bid");
                    String bquan = request.getParameter("bquantity");
                    if (rid != null) {
                        int id = Integer.parseInt(rid);
                        CartItems item = cart.getItemById(id);
                        List<Book> listB = bd.getAll();
                        float priceB = bd.getBookPriceByID(id);

                        int itemQuantity = Integer.parseInt(bquan);

                        if (payment >= (priceB) * itemQuantity) {
                            ok = 1;
                            od.addItemOrder(accountId, item);
                            cart.removeItem(id);
                            ad.getAccountDownPrice(accountId, (priceB) * itemQuantity);
                            Account a = new AccountDAO().getAccountById(account.getId(), new AccountDAO().getAll());
                            session.setAttribute("account", a);
                        } else {
                            String err = "Hết tiền rồi ông kễnh, nạp zô mà mua tiếp! Chuyển hướng sau 3s";
                            request.getSession().setAttribute("err", err);
                            response.sendRedirect("wishlist");
                            return;
                        }

                    }
                }
            } catch (NumberFormatException e) {
                return;
            }
            if (ok == 1) {
                listI = cart.getItems();
                session.setAttribute("cart", cart);
                session.setAttribute("size", listI.size());
                response.sendRedirect("orderHistory");
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

}
