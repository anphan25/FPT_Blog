/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swp.comment.CommentDAO;
import swp.comment.CommentDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "CommentServlet", urlPatterns = {"/CommentServlet"})
public class CommentServlet extends HttpServlet {

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
        String postId = request.getParameter("postId");
        String ownerCmtEmail = request.getParameter("ownerCmtEmail");
//        String cmtContent = new String(request.getParameter("cmtContent").getBytes("iso-8859-1"), "utf-8");
        String cmtContent = request.getParameter("cmtContent");
//        ServletContext context = request.getServletContext();
//        Map<String, String> roadmap = (Map<String, String>) context.getAttribute("ROADMAP");
////        String url = roadmap.get("loadPostContent");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                CommentDAO dao = new CommentDAO();
                boolean result = dao.insertComment(postId, ownerCmtEmail, cmtContent);
                if (result) {
                    CommentDTO newComment = dao.loadNewComment(postId, ownerCmtEmail);
//                                        \"user-avt\">\n"
                    out.println("<div class=\"others-comments\" id=\"comment-div-"+ newComment.getID()+"\">\n"
                            + "                                        <div class=\"user-avt\">\n"
                            + "                                            <img\n"
                            + "                                                class=\"avt-img\"\n"
                            + "                                                src=\"" + newComment.getAvatar() + "\"\n"
                            + "                                                alt=\"\"\n"
                            + "                                                />\n"
                            + "                                        </div>\n"
                            + "                                        <div id=\"comment-item-" + newComment.getID() + "\" class=\"comment-item\">\n"
                            + "                                            <div id=\"comment-info-" + newComment.getID() + "\" class=\"comment-info\">\n"
                            + "                                                <a href=\"loadProfile?email=" + newComment.getEmailComment() + "\">\n"
                            + "                                                    <div class=\"name\">" + newComment.getName() + "</div>\n"
                            + "                                                </a>\n"
                            + "                                                <div class=\"comment-time\">" + newComment.getDate() + "</div>\n"
                            + "                                            </div>\n"
                            + "                                            <div id=\"comment-content-" + newComment.getID() + "\" class=\"comment-content\">\n"
                            + "                                                <p id=\"comment-content-p-" + newComment.getID() + "\">" + newComment.getContent() + "</p>\n"
                            + "                                            </div>\n"
                            + "                                             <div class=\"editForm\" id=\"edit-form-" + newComment.getID() + "\">\n"
                            + "                                                <form action=\"\">\n"
                            + "                                                    <textarea\n"
                            + "                                                        name=\"cmt\"\n"
                            + "                                                        minlength=\"1\"\n"
                            + "                                                        id=\"textarea-cmt-edit-" + newComment.getID() + "\"\n"
                            + "                                                        class=\"textarea-cmt-edit\"\n"
                            + "                                                        cols=\"30\"\n"
                            + "                                                        rows=\"10\"\n"
                            + "                                                        placeholder=\"Edit your comment here...\"\n"
                            + "                                                        >" + newComment.getContent() + "</textarea>\n"
                            + "                                                    <div class=\"container-button-edit\">\n"
                            + "                                                        <div class=\"container-icon-close\" id=\"" + newComment.getID() + "\">\n"
                            + "                                                            <i class=\"fas fa-times\" id=\"" + newComment.getID() + "\"></i>\n"
                            + "                                                        </div>\n"
                            + "                                                        <div class=\"container-icon-edit\" id=\"" + newComment.getID() + "\">\n"
                            + "                                                            <i class=\"fas fa-check\" id=\"" + newComment.getID() + "\"></i>\n"
                            + "                                                        </div>\n"
                            + "                                                    </div>\n"
                            + "                                                </form>\n"
                            + "                                            </div>"
                            + "                                                </div>   \n"
                            + "                                                <div id=\"edit-delete-" + newComment.getID() + "\" class=\"edit-delete\">\n"
                            + "                                                    <button id=\"" + newComment.getID() + "\" class=\"editButton\"><i class=\"fas fa-pen\"></i> Edit</button>\n"
                            + "                                                    <button class=\"deleteButton\" id=\""+newComment.getID()+"\"><i class=\"fas fa-trash-alt\"></i> Delete</button>\n"
                            + "                                                </div>   \n"
                            + "                                    </div>");
                }
            }
        } catch (SQLException ex) {
            log("CommenrServlet _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("CommenrServlet _ Naming " + ex.getMessage());
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
