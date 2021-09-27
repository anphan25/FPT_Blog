package swp.controller;

import java.io.IOException;
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
import swp.post.PostDAO;
import swp.post.PostDTO;

@WebServlet(name = "LoadAllPostsServlet", urlPatterns = {"/LoadAllPostsServlet"})

public class LoadAllPostsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ServletContext context = request.getServletContext();
        Map<String, String> roadmap = (Map<String, String>) context.getAttribute("ROADMAP");
        String url = roadmap.get("homePage");
        try {
            HttpSession session = request.getSession();
            CategoryDAO cateDAO = new CategoryDAO();
            PostDAO postDAO = new PostDAO();
            int count = postDAO.getTotalPost();
            int endPage = count/10;
            String indexPage = request.getParameter("index"); //lấy trang user muốn
            if(indexPage == null){
                indexPage= "1";
            }
            int index = Integer.parseInt(indexPage);
            
            if(count % 10 != 0){
                endPage++;
            }
            cateDAO.loadCategoryList();
            ArrayList<CategoryDTO> categorylist = cateDAO.getCategoryList();
            ArrayList<PostDTO> listPaging = postDAO.pagingPosts(index);
            request.setAttribute("LIST_PAGING", listPaging);
            request.setAttribute("ENDPAGE", endPage);
            request.setAttribute("CHECK_INDEX", index);
//            ArrayList<PostDTO> list = postDAO.getAllPostList();
            session.setAttribute("CATEGORY_LIST", categorylist);
//            request.setAttribute("ALL_POST", list);
        } catch (Exception e) {
            log("Error at Load all post Servlet Controller: " + e.getMessage());
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
