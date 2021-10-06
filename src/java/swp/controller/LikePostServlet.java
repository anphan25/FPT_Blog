/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swp.post.PostDAO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "LikePostServlet", urlPatterns = {"/LikePostServlet"})
public class LikePostServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String postId = request.getParameter("postId");
        String email = request.getParameter("emailLike");
        HttpSession session = request.getSession();
        try {
            if (session != null) {
                PostDAO dao = new PostDAO();
                if (dao.checkLike(postId, email)) {//Có thông tin trong tblLikes > unlike
                    if (dao.deleteLike(postId, email)) {
                        log("Email: " + email + " unliked postID: " + postId + " successfully!");
                    } else {
                        log("Email: " + email + " unliked postID: " + postId + " failed!");
                    }
                    out.println(dao.getLikeCounting(postId));
                } else {//like
                    if (dao.insertLike(postId, email)) {
                        log("Email: " + email + " liked postID: " + postId + " successfully!");
                    } else {
                        log("Email: " + email + " liked postID: " + postId + " failed!");
                    }
                    out.println(dao.getLikeCounting(postId));
                }
            }
        } catch (SQLException ex) {
            log("Error at LikeServlet _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error at LikeServlet _ Naming " + ex.getMessage());
        } finally {

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
        processRequest(request, response);
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
