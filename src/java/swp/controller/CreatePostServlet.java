package swp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swp.account.AccountDTO;
import swp.post.PostDAO;

/**
 *
 * @author macbook
 */
public class CreatePostServlet extends HttpServlet {

    private static final String SUCCESS = "loadBlogs";
    private static final String FAIL = "createPost";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = request.getServletContext();
        Map<String, String> roadmap = (Map<String, String>) context.getAttribute("ROADMAP");
        String url = roadmap.get(FAIL);
        try {
            HttpSession session = request.getSession(false);
            AccountDTO accInfo = (AccountDTO) session.getAttribute("CURRENT_USER");
            String role = accInfo.getRole();
            String email = request.getParameter("email");
            String title = new String(request.getParameter("title").getBytes("iso-8859-1"), "utf-8");
            int category = Integer.parseInt(request.getParameter("category"));
            String tags = new String(request.getParameter("tags").getBytes("iso-8859-1"), "utf-8");
            String content = new String(request.getParameter("content").getBytes("iso-8859-1"), "utf-8");
            PostDAO createPost = new PostDAO();
            boolean check = createPost.insertANewPost(email, tags, title, content, category, role);
            if (check) {
                log("Creating a new post successfully!");
                url = roadmap.get(SUCCESS);
            }
        } catch (Exception e) {
            log("Error at Create new Post servlet: " + e.getMessage());
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