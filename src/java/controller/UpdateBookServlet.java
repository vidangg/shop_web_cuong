/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BookDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import model.Book;

/**
 *
 * @author Admin
 */

@WebServlet(name = "UpdateBookServlet", urlPatterns = {"/updateBook"})
@MultipartConfig
public class UpdateBookServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateBookServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateBookServlet at " + request.getContextPath() + "</h1>");
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
        

        String bid = request.getParameter("bid");
        BookDAO bd = new BookDAO();
        if (bid != null) {
            int bookId = Integer.parseInt(bid);
            List<Book> listB = bd.getAllIncludeRunOut();
            Book b = bd.getBookByID(bookId, listB);
            request.setAttribute("book", b);
            request.getRequestDispatcher("updateBook.jsp").forward(request, response);
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
        BookDAO bd = new BookDAO();
        String rid = request.getParameter("id");
        String title = request.getParameter("title");
        String publisher = request.getParameter("publisher");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Double price = Double.valueOf(request.getParameter("price"));
        Part part = request.getPart("imgLink");
        String realPath = request.getServletContext().getRealPath("/images");
        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

        if (!Files.exists(Paths.get(realPath))) {
            Files.createDirectories(Paths.get(realPath));
        }

        String imagePath = null;
        if (fileName != null && !fileName.isEmpty()) {
            part.write(realPath + "/" + fileName);
            imagePath = "images/" + fileName;
        } else {
            Book b = new Book();
            BookDAO bdao = new BookDAO();
            Book existingBook = bdao.getBookByID(Integer.parseInt(rid),bdao.getAll());
            imagePath = existingBook.getImgLink();
        }
        if (!rid.equals("")) { //Sua sach da co
            int id = Integer.parseInt(rid);
            int pid = Integer.parseInt(request.getParameter("publisherId"));
            Book b = new Book(id, title, publisher, price, quantity, imagePath);
            bd.updateBook(b, pid);
        } else { //Them sach moi
            int pid = Integer.parseInt(request.getParameter("publisherId"));
            Book b = new Book(title, price, quantity, imagePath);
            bd.updateNewBook(b, pid);
        }
        response.sendRedirect("manage?mid=1");
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
