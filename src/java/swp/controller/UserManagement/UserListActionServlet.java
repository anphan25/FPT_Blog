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
    private final String START_AGAIN = "loadUserList";
    //private final String RESULT_PAGE = "userListResultPage"; //This again? YES
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
        //say no
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", -1);
        try
        {
            //ko cần check session null khi cái attribute login đã null
            if(currentadmin != null) //check đăng nhập và thành công
            {
                String checkRole = currentadmin.getRole();
                if(checkRole.equals("A")) //check tài khoản là admin và thành công
                {
                    if(actionline != null)
                    {
                        String[] araara = actionline.split("%"); //true parameter is here
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
                                String selection = araara[5];
                                if(dao.updateRoleMentor(gmail, cateID))
                                {
                                    ArrayList<UserlistDTO> newlist = dao.getUserList();
                                    Userlist cachingTool = new Userlist(newlist);
                                    request.setAttribute("NOTIFY", "Updating");
                                    session.setAttribute("CACHING_USER_LIST", cachingTool);
                                    if(search.equals("all") && selection.equals("all"))
                                    {
                                        request.setAttribute("USER_LIST", newlist);
                                        url = roadmap.get(USER_CONTROL_PANEL);
                                    }
                                    else if(search.equals("all")) //lọc only
                                    {
                                        String[] selected = selection.split("\\.");
                                        newlist = cachingTool.filteredList(selected[0], selected[1], selected[2], selected[3]);
                                        request.setAttribute("SELECTION_TEXT", selected);
                                        request.setAttribute("USER_LIST", newlist);
                                        url = roadmap.get(USER_CONTROL_PANEL);
                                    }
                                    else if(selection.equals("all")) //search only
                                    {
                                        newlist = dao.searchAll(search);
                                        request.setAttribute("SEARCH_TEXT", search);
                                        request.setAttribute("USER_LIST", newlist);
                                        url = roadmap.get(USER_CONTROL_PANEL);
                                    }
                                    else //vừa lọc vừa search
                                    {
                                        String[] selected = selection.split("\\.");
                                        newlist = dao.searchAll(search);
                                        newlist = cachingTool.filteredList(selected[0], selected[1], selected[2], selected[3], newlist);
                                        request.setAttribute("SEARCH_TEXT", search);
                                        request.setAttribute("SELECTION_TEXT", selected);
                                        request.setAttribute("USER_LIST", newlist);
                                        url = roadmap.get(USER_CONTROL_PANEL);
                                    }
                                }
                            }
                            else //update cho 2 role khác
                            {
                                String selection = araara[4];
                                if(dao.updateRoleAccount(gmail, role))
                                {
                                    ArrayList<UserlistDTO> newlist = dao.getUserList();
                                    Userlist cachingTool = new Userlist(newlist);
                                    request.setAttribute("NOTIFY", "Updating");
                                    session.setAttribute("CACHING_USER_LIST", cachingTool);
                                    if(search.equals("all") && selection.equals("all"))
                                    {
                                        request.setAttribute("USER_LIST", newlist);
                                        url = roadmap.get(USER_CONTROL_PANEL);
                                    }
                                    else if(search.equals("all")) //lọc only
                                    {
                                        String[] selected = selection.split("\\.");
                                        newlist = cachingTool.filteredList(selected[0], selected[1], selected[2], selected[3]);
                                        request.setAttribute("SELECTION_TEXT", selected);
                                        request.setAttribute("USER_LIST", newlist);
                                        url = roadmap.get(USER_CONTROL_PANEL);
                                    }
                                    else if(selection.equals("all")) //search only
                                    {
                                        newlist = dao.searchAll(search);
                                        request.setAttribute("SEARCH_TEXT", search);
                                        request.setAttribute("USER_LIST", newlist);
                                        url = roadmap.get(USER_CONTROL_PANEL);
                                    }
                                    else //vừa lọc vừa search
                                    {
                                        String[] selected = selection.split("\\.");
                                        newlist = dao.searchAll(search);
                                        newlist = cachingTool.filteredList(selected[0], selected[1], selected[2], selected[3], newlist);
                                        request.setAttribute("SEARCH_TEXT", search);
                                        request.setAttribute("SELECTION_TEXT", selected);
                                        request.setAttribute("USER_LIST", newlist);
                                        url = roadmap.get(USER_CONTROL_PANEL);
                                    }
                                }
                            }
                        }//kết thúc việc update complex mother fucker shitty gang bang
                        else if(action.equals("banning")) //nếu họ muốn ban
                        {
                            String reason = new String(araara[3].getBytes("iso-8859-1"), "utf-8") ;
                            String selection = araara[4];
                            UserlistDAO dao = new UserlistDAO();
                            boolean IsItUpdate = dao.banAccount(gmail, reason);
                            if(IsItUpdate)
                            {
                                ArrayList<UserlistDTO> newlist = dao.getUserList();
                                Userlist cachingTool = new Userlist(newlist);
                                request.setAttribute("NOTIFY", "Banning");
                                session.setAttribute("CACHING_USER_LIST", cachingTool);
                                if(search.equals("all") && selection.equals("all"))
                                {
                                    request.setAttribute("USER_LIST", newlist);
                                    url = roadmap.get(USER_CONTROL_PANEL);
                                }
                                else if(search.equals("all")) //lọc only
                                {
                                    String[] selected = selection.split("\\.");
                                    newlist = cachingTool.filteredList(selected[0], selected[1], selected[2], selected[3]);
                                    request.setAttribute("SELECTION_TEXT", selected);
                                    request.setAttribute("USER_LIST", newlist);
                                    url = roadmap.get(USER_CONTROL_PANEL);
                                }
                                else if(selection.equals("all")) //search only
                                {
                                    newlist = dao.searchAll(search);
                                    request.setAttribute("SEARCH_TEXT", search);
                                    request.setAttribute("USER_LIST", newlist);
                                    url = roadmap.get(USER_CONTROL_PANEL);
                                }
                                else //vừa lọc vừa search
                                {
                                    String[] selected = selection.split("\\.");
                                    newlist = dao.searchAll(search);
                                    newlist = cachingTool.filteredList(selected[0], selected[1], selected[2], selected[3], newlist);
                                    request.setAttribute("SEARCH_TEXT", search);
                                    request.setAttribute("SELECTION_TEXT", selected);
                                    request.setAttribute("USER_LIST", newlist);
                                    url = roadmap.get(USER_CONTROL_PANEL);
                                }
                            }
                        }
                        else if(action.equals("unbaning")) //nếu họ muốn ban
                        {
                            String selection = araara[3];
                            UserlistDAO dao = new UserlistDAO();
                            boolean IsItUpdate = dao.unbanAccount(gmail);
                            if(IsItUpdate)
                            {
                                ArrayList<UserlistDTO> newlist = dao.getUserList();
                                Userlist cachingTool = new Userlist(newlist);
                                request.setAttribute("NOTIFY", "Unbanning");
                                session.setAttribute("CACHING_USER_LIST", cachingTool);
                                if(search.equals("all") && selection.equals("all"))
                                {
                                    request.setAttribute("USER_LIST", newlist);
                                    url = roadmap.get(USER_CONTROL_PANEL);
                                }
                                else if(search.equals("all")) //lọc only
                                {
                                    String[] selected = selection.split("\\.");
                                    newlist = cachingTool.filteredList(selected[0], selected[1], selected[2], selected[3]);
                                    request.setAttribute("SELECTION_TEXT", selected);
                                    request.setAttribute("USER_LIST", newlist);
                                    url = roadmap.get(USER_CONTROL_PANEL);
                                }
                                else if(selection.equals("all")) //search only
                                {
                                    newlist = dao.searchAll(search);
                                    request.setAttribute("SEARCH_TEXT", search);
                                    request.setAttribute("USER_LIST", newlist);
                                    url = roadmap.get(USER_CONTROL_PANEL);
                                }
                                else //vừa lọc vừa search
                                {
                                    String[] selected = selection.split("\\.");
                                    newlist = dao.searchAll(search);
                                    newlist = cachingTool.filteredList(selected[0], selected[1], selected[2], selected[3], newlist);
                                    request.setAttribute("SEARCH_TEXT", search);
                                    request.setAttribute("SELECTION_TEXT", selected);
                                    request.setAttribute("USER_LIST", newlist);
                                    url = roadmap.get(USER_CONTROL_PANEL);
                                }
                            }
                        }
                    }//kết thúc việc múa mây java brace ở dưới đống cho việc check admin
                    else
                    {
                        url = roadmap.get(START_AGAIN);
                    }
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
