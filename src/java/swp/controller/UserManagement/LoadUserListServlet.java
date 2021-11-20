/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.controller.UserManagement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;
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
import swp.account.AccountDTO;
import swp.userlist.Userlist;
import swp.userlist.UserlistDAO;
import swp.userlist.UserlistDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoadUserListServlet", urlPatterns =
{
    "/LoadUserListServlet"
})
public class LoadUserListServlet extends HttpServlet
{
    private final String ERROR_PAGE = "notFoundPage"; //lẻ ra là lỗi 500 ko phải 404
    private final String USER_CONTROL_PANEL = "userListPage";
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
        HttpSession session = request.getSession(false);
        
        ServletContext context = request.getServletContext();
        Map<String, String> roadmap = (Map<String, String>) context.getAttribute("ROADMAP");
        
        String url = roadmap.get(ERROR_PAGE);
        
        try
        {
            if(session != null)
            {
                AccountDTO currentlogin = (AccountDTO) session.getAttribute("CURRENT_USER"); //FIX THIS
                if(currentlogin != null)
                {
                    String role = currentlogin.getRole();
                    if(role.equals("A"))
                    {
                        Userlist cached = (Userlist) session.getAttribute("CACHING_USER_LIST"); //UX improvement by Saphareong
                        if(cached != null)
                        {//already there why do i have to call DAO performance +15 social credits
                            ArrayList<UserlistDTO> dto = cached.getFullList();
                            request.setAttribute("USER_LIST", dto);
                            url = roadmap.get(USER_CONTROL_PANEL);
                        }
                        else //first time click on userlist button
                        {
                            UserlistDAO dao = new UserlistDAO();
                            ArrayList<UserlistDTO> dto = dao.getUserList();
                            //url = "resultpage.jsp";
                            //chưa check account đã đăng nhập và chưa check role
                            if(dto != null) //CAUTION nếu tblAccounts trong database không có gì sẽ bị lỗi stackoverflow
                            {
                                request.setAttribute("USER_LIST", dto); //lấy hết 500 anh em vứt vào USER_LIST attwibu
                                Userlist naplandau = new Userlist(dto);
                                session.setAttribute("CACHING_USER_LIST", naplandau);
                                url = roadmap.get(USER_CONTROL_PANEL);
                            }
                        }
                    }
                    
                }//kết thúc check login và admin
            }
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
            Logger.getLogger(LoadUserListServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(LoadUserListServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoadUserListServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(LoadUserListServlet.class.getName()).log(Level.SEVERE, null, ex);
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
