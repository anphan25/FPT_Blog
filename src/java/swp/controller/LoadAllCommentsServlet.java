/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swp.category.CategoryDAO;
import swp.category.CategoryDTO;
import swp.comment.CommentDAO;
import swp.comment.CommentDTO;
import swp.post.PostDAO;
import swp.post.PostDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "LoadAllCommentsServlet", urlPatterns = {"/LoadAllCommentsServlet"})
public class LoadAllCommentsServlet extends HttpServlet {

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

        ServletContext context = request.getServletContext();
        Map<String, String> roadmap = (Map<String, String>) context.getAttribute("ROADMAP");
        String url = roadmap.get("commentManagementPage");
        try {
            CommentDAO commentDAO = new CommentDAO();
            ArrayList<CommentDTO> commentList = commentDAO.getAllComments();
            int totalComments = commentDAO.getTotalComments();
            if (commentList.isEmpty()) {
                log("Don't have any comment in system! Maybe error at LoadAllCommentsServlet!!!");
            } else {
                for (CommentDTO c : commentList) {
                    log("Title: " + c.getPostName() + ", comment: " + c.getContent() + " by Email: " + c.getEmailComment());
                }
            }
            log("Total comment is: " + totalComments);
            request.setAttribute("COMMENT_LIST", commentList);
            request.setAttribute("TOTAL_COMMENTS", totalComments);
        } catch (Exception e) {
            log("Error at LoadAllCommentsServlet: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
