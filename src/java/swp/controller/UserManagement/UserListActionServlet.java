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
@WebServlet(name = "UserListActionServlet", urlPatterns =
{
    "/UserListActionServlet"
})
public class UserListActionServlet extends HttpServlet
{
    private final String ERROR_PAGE = "notFoundPage"; //lẻ ra là lỗi 500 ko phải 404
    private final String USER_CONTROL_PANEL = "userListPage";
    private final String RESULT_PAGE = "userListResultPage"; //This again? YES
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
        Map<String, String> roadmap = (Map<String, String>) context.getAttribute("ROADMAP");
        String url = roadmap.get(ERROR_PAGE);
        //receiving parameter list
        //String email = request.getParameter("victimEmail");
        //String button = request.getParameter("btAction");
        String actionline = request.getParameter("searchAction"); //ditme DOM giờ chỉ còn mỗi mình em
        //server side declaration
        HttpSession session = request.getSession(false);
        AccountDTO currentadmin = (AccountDTO)session.getAttribute("CURRENT_USER");
        
        try
        {
            //ko cần check session null khi cái attribute login đã null
            if(currentadmin != null) //check đăng nhập và thành công
            {
                String checkRole = currentadmin.getRole();
                if(checkRole.equals("A")) //check tài khoản là admin và thành công
                {
                    if(actionline != null) //tôi tính làm thêm hàm tránh lặp lại mà dcm thậm chí C# còn dùng delegate để khai báo bừa bãi việc gì phải làm thế (:
                    {
                        String[] araara = actionline.split("%"); //true parameter is here not line 56 in this class lol
                        String action = araara[0];
                        String gmail = araara[1];
                        String search = araara[2];
                        //and we want to remain the user list that stupid user interacted.
                        if(action.equals("updating"))
                        {
                            String role = araara[3];
                            UserlistDAO dao = new UserlistDAO();
                            if(role.equals("M"))
                            {
                                int cateID = Integer.parseInt(araara[4]);
                                if(dao.updateRoleMentor(gmail, cateID))
                                {
                                    if(search.equals("all"))
                                    {
                                        ArrayList<UserlistDTO> newlist = dao.getUserList();
                                        request.setAttribute("USER_LIST", newlist);
                                        url = roadmap.get(USER_CONTROL_PANEL);
                                    }
                                    else
                                    {
                                        ArrayList<UserlistDTO> newlist = dao.searchAll(search);
                                        request.setAttribute("SEARCH_TEXT", search);
                                        request.setAttribute("USER_LIST", newlist);
                                        url = roadmap.get(USER_CONTROL_PANEL);
                                    }
                                }
                                
                            }
                            else //update cho 2 role khác
                            {
                                if(dao.updateRoleAccount(gmail, role))
                                {
                                    if(search.equals("all"))
                                    {
                                        ArrayList<UserlistDTO> newlist = dao.getUserList();
                                        request.setAttribute("USER_LIST", newlist);
                                        url = roadmap.get(USER_CONTROL_PANEL);
                                    }
                                    else
                                    {
                                        ArrayList<UserlistDTO> newlist = dao.searchAll(search);
                                        request.setAttribute("SEARCH_TEXT", search);
                                        request.setAttribute("USER_LIST", newlist);
                                        url = roadmap.get(USER_CONTROL_PANEL);
                                    }
                                }
                            }
                        }//kết thúc việc update complex mother fucker shitty gang bang
                        else if(action.equals("banning")) //nếu họ muốn ban
                        {
                            String reason = araara[3];
                            UserlistDAO dao = new UserlistDAO();
                            boolean IsItUpdate = dao.banAccount(gmail, reason);
                            if(IsItUpdate)
                            {
                                if(search.equals("all"))
                                {
                                    ArrayList<UserlistDTO> newlist = dao.getUserList();
                                    request.setAttribute("USER_LIST", newlist);
                                    url = roadmap.get(USER_CONTROL_PANEL);
                                }
                                else
                                {
                                    ArrayList<UserlistDTO> newlist = dao.searchAll(search);
                                    request.setAttribute("SEARCH_TEXT", search);
                                    request.setAttribute("USER_LIST", newlist);
                                    url = roadmap.get(USER_CONTROL_PANEL);
                                }
                            }
                        }
                        else if(action.equals("unbaning")) //nếu họ muốn ban
                        {
                            UserlistDAO dao = new UserlistDAO();
                            boolean IsItUpdate = dao.unbanAccount(gmail);
                            if(IsItUpdate)
                            {
                                if(search.equals("all"))
                                {
                                    ArrayList<UserlistDTO> newlist = dao.getUserList();
                                    request.setAttribute("USER_LIST", newlist);
                                    url = roadmap.get(USER_CONTROL_PANEL);
                                }
                                else
                                {
                                    ArrayList<UserlistDTO> newlist = dao.searchAll(search);
                                    request.setAttribute("SEARCH_TEXT", search);
                                    request.setAttribute("USER_LIST", newlist);
                                    url = roadmap.get(USER_CONTROL_PANEL);
                                }
                            }
                        }
                    }//kết thúc việc múa mây java brace ở dưới đống cho việc check admin
                }// kết thúc tất cả việc muốn làm ở servlet này (nếu có thêm action chuyển qua switch case
            }
        }
        catch(SQLException ex)
        {
            log("SQL _ " + ex.getMessage());
        }
        catch(NamingException ex)
        {
            log("Naming _ " + ex.getMessage());
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
            Logger.getLogger(UserListActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(UserListActionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserListActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(UserListActionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
