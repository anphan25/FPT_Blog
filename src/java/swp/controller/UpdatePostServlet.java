/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.controller;

//import com.sun.org.apache.xpath.internal.operations.Equals;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swp.account.AccountDTO;
import swp.post.PostDAO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "UpdatePostServlet", urlPatterns = {"/UpdatePostServlet"})
public class UpdatePostServlet extends HttpServlet {

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
        String url = roadmap.get("loadBlogs");
        HttpSession session = request.getSession(false);
        String newContent = new String(request.getParameter("newContent").getBytes("iso-8859-1"), "utf-8");
        String postId = request.getParameter("postId");// Conversion failed when converting from a character string to uniqueidentifier.

        try {
            if (postId.isEmpty()) {
                log("PostID is empty");
            } else {
                log("PostID: " + postId);
            }
            PostDAO dao = new PostDAO();
            AccountDTO dto = (AccountDTO) session.getAttribute("CURRENT_USER");
            if (dto.getRole().equals("M")) {
                if (dao.adminUpdatePost(postId, newContent)) {//Student update post and waiting for approving
                    log("Admin update PostContent " + newContent + ", Post ID: " + postId + " successfully!");
                } else {
                    log("Admin update PostContent " + newContent + ", Post ID: " + postId + " failed!");
                }
            } else if (dto.getRole().equals("S")) {
                if (dao.insertNewContentPost(postId, newContent)) {//Student update post and waiting for approving
                    log("Settine new content " + newContent + ", Post ID: " + postId + " successfully!");
                } else {
                    log("Settine new content" + newContent + ", Post ID: " + postId + " failed!");
                }
            } else {
                log("Updating FAILED because roleID is not S or M");
            }
        } catch (Exception e) {
            log("Error at Update post servlet: " + e.getMessage());
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
