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
import swp.post.PostDTO;
import swp.profile.ProfileDAO;
import swp.profile.ProfileDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoadProfileServlet", urlPatterns =
{
    "/LoadProfileServlet"
})
public class LoadProfileServlet extends HttpServlet
{
    private final String PROFILE_PAGE = "profilePage";
    private final String FAIL = "notFoundPage";
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
            throws ServletException, IOException, NamingException, SQLException, ClassNotFoundException
    {
        response.setContentType("text/html;charset=UTF-8");
        
        String email = request.getParameter("email");
        ServletContext context = request.getServletContext();
        Map<String, String> roadmap = (Map<String, String>) context.getAttribute("ROADMAP");
//        String url = "goto404"; //cái này là vứt trang lỗi 500 hoặc 404 khi nào hoàn thành xong chức năng rồi tính tới việc này.
        String url = roadmap.get("profilePage");
        
        try
        {
            //gọi DAO
            ProfileDAO dao = new ProfileDAO();
            //gọi DTO và gán giá trị
            ProfileDTO dto = dao.getProfile(email);
            dao.LoadThemAll(email);
            ArrayList<PostDTO> bloglist = dao.getAllPost();
            //bắt đầu múa quạt
            if(!bloglist.isEmpty()) request.setAttribute("PROFILE_BLOG", bloglist); //chỉ set attribute khi tài khoản đó có post.
            if(dto == null) {
                url = roadmap.get(FAIL);
            }
            request.setAttribute("PROFILE_INFORMATION", dto);
            
        }
        catch(NamingException ex)
        {
            log(" Naming: " + ex.getMessage());
        }
        catch(SQLException ex)
        {
            log(" SQL: " + ex.getMessage());
        }
        catch(ClassNotFoundException ex)
        {
            log(" CNFE: " + ex.getMessage());
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
        } catch (NamingException ex)
        {
            Logger.getLogger(LoadProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(LoadProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(LoadProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NamingException ex)
        {
            Logger.getLogger(LoadProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(LoadProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(LoadProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
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
