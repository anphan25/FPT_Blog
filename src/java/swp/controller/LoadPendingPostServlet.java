/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namth.PendingPost;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
import javax.servlet.http.HttpSession;
import namth.Authenticate.AccountDTO;
import our.Controller.RoadMap;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoadPendingPostServlet", urlPatterns =
{
    "/LoadPendingPostServlet"
})
public class LoadPendingPostServlet extends HttpServlet
{
    private final String HOME_PAGE = "homePage";
    private final String PENDING_PAGE = "pendingPostListPage";
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
            throws ServletException, IOException, NamingException, SQLException
    {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = request.getServletContext();
        RoadMap rm = (RoadMap) context.getAttribute("ROAD_MAP");
        String url = rm.getResource(HOME_PAGE);
        
        try
        {
            HttpSession session = request.getSession(false);
            if(session != null)
            {
                //chỗ này sẽ cần phải thay đổi cách thức hđ DAO
                if(session.getAttribute("ACCOUNT") != null)
                {
                    AccountDTO accInfo = (AccountDTO) session.getAttribute("ACCOUNT");
                    String role = accInfo.getRoleID();
                    if(role.equals("M"))
                    {
                        PendingPostDAO dao = new PendingPostDAO();
                        ArrayList<PendingPostDTO> dto = dao.getAllWaitingPost();
                        if(!dto.isEmpty())
                        {
                            request.setAttribute("PENDING_LIST", dto);
                            url = rm.getResource(PENDING_PAGE);
                        }//kết thúc nếu dto có dữ liệu
                        //có thể thêm else để bổ sung popup thông báo ko có pending post.
                    }//kết thúc check mentor
                }//kết thúc việc check đăng nhập
            }//kết thúc check session
        }
        catch(NamingException ex)
        {
            log(" Naming: " + ex.getMessage());
        }
        catch(SQLException ex)
        {
            log(" SQL: " + ex.getMessage());
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
            Logger.getLogger(LoadPendingPostServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(LoadPendingPostServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoadPendingPostServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(LoadPendingPostServlet.class.getName()).log(Level.SEVERE, null, ex);
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
