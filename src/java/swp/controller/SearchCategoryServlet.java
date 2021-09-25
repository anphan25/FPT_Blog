/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import swp.post.PostDAO;
import swp.post.PostDTO;


/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchCategoryServlet", urlPatterns =
{
    "/SearchCategoryServlet"
})
public class SearchCategoryServlet extends HttpServlet
{
    private final String SEARCH_RESULT_PAGE = "searchResultPage";
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
            throws ServletException, IOException, SQLException, ClassNotFoundException, NamingException
    {
        response.setContentType("text/html;charset=UTF-8");
        String url = "goto404";//ko cần giải thích vì quá rõ ràng url này sẽ bắn ra khi dao móc fail
        String categoryID = request.getParameter("categoryId");
        
        ServletContext context = request.getServletContext();
        Map<String, String> roadmap = (Map<String, String>) context.getAttribute("ROADMAP");
        
        try
        {
            //gọi dao và dto
            PostDAO dao = new PostDAO();
            ArrayList<PostDTO> dto = dao.getPostByCategory(categoryID);
            //múa theo tình huống éo le
            if(dto != null)
            {
                request.setAttribute("SEARCHLIST_CATEGORY", dto);
                url = roadmap.get(SEARCH_RESULT_PAGE);
            }
            if(dto == null) url = roadmap.get(SEARCH_RESULT_PAGE);
        }
        catch(SQLException ex)
        {
            log(" SQL: " + ex.getMessage());
        }
        catch(ClassNotFoundException ex)
        {
            log(" CNFE: " + ex.getMessage());
        }
        catch(NamingException ex)
        {
            log(" Naming: " + ex.getMessage());
        }
        finally
        {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        } catch (SQLException ex)
        {
            Logger.getLogger(SearchCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(SearchCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex)
        {
            Logger.getLogger(SearchCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        } catch (SQLException ex)
        {
            Logger.getLogger(SearchCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(SearchCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex)
        {
            Logger.getLogger(SearchCategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
