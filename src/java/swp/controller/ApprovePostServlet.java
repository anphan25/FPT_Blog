/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import swp.post.PostDAO;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ApprovePostServlet", urlPatterns = {"/ApprovePostServlet"})
public class ApprovePostServlet extends HttpServlet {

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
        String url = roadmap.get("loadPendingPosts");

        try {
            //CHỈ DUYỆT DELETE - APPROVE POST
            String postID = request.getParameter("postID");
            String emailMentor = request.getParameter("emailMentor");
            String statusPost = request.getParameter("statusPost");
            String mentorDecision = request.getParameter("mentorDecision");
            
            
            String newStatus = null;
            boolean isSetDate = true;
            PostDAO dao = new PostDAO();
            switch (mentorDecision) {
                case "approve": {
                    if (statusPost.equalsIgnoreCase("WFA")) {
                        newStatus = "A";
                    } else if (statusPost.equalsIgnoreCase("WFD")) {
                        newStatus = "D";
                    }
                    break;
                }
                case "reject": {
                    if (statusPost.equalsIgnoreCase("WFA")) {
                        String rejectReason = new String(request.getParameter("reason").getBytes("iso-8859-1"), "utf-8");
                        newStatus = "R";
                        dao.insertRejectReason(postID, rejectReason);
                        
                    } else if (statusPost.equalsIgnoreCase("WFD")) {
                        newStatus = "A";
                        isSetDate = false;//t push được chưa nhỉ?
                    }
                    break;
                }
                default: {
                    log("Value or status post is invalid");
                    break;
                }
            }

            if (statusPost.equalsIgnoreCase("WFU")) {
                if (mentorDecision.equalsIgnoreCase("approve")) {
                    if (dao.approveUpdateContentPost(postID)) {
                        log("Settine new content for Post ID: " + postID + " successfully!");
                    } else {
                        log("Settine new content for Post ID: " + postID + " failed!");
                    }
                } else {
                    if (dao.rejectUpdateContentPost(postID)) {
                        log("Reject updating content for Post ID: " + postID + " successfully!");
                    } else {
                        log("Reject updating content for Post ID: " + postID + " falied!");
                    }
                }
            } else {
                if (isSetDate) {
                    if (dao.setNewStatusPost(postID, emailMentor, newStatus)) {
                        log("Updating successfuly! PostID: " + postID + ", status post: '" + statusPost + "' to new status post: '" + newStatus + "' by Mentor: " + emailMentor);
                    } else {
                        log("Updating failed! PostID: " + postID + ", status post: " + statusPost + " to new status post: " + newStatus + " by Mentor: " + emailMentor);
                    }
                } else {//KHÔNG GETDATE() VÌ NÓ REJECT-DELETE > STATUS "A" NHƯ CŨ 
                    if (dao.rejectDeletedPost(postID, newStatus)) {
                        log("Reject deleted post successfully! PostID: " + postID + ", status post: '" + statusPost + "' to new status post: '" + newStatus + "' by Mentor: " + emailMentor);
                    } else {
                        log("Reject deleted post failed! PostID: " + postID + ", status post: " + statusPost + " to new status post: " + newStatus + " by Mentor: " + emailMentor);
                    }
                }
            }

        } catch (Exception e) {
            log("Error at Approve Post ServletContext: " + e.getMessage());
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
