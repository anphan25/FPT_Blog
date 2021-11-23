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
import swp.userlist.UserlistDTO;

/**
 *
 * @author Saphareong
 */
@WebServlet(name = "SearchFilteringServlet", urlPatterns =
{
    "/SearchFilteringServlet"
})
public class SearchFilteringServlet extends HttpServlet //servlet này chỉ redirect tới 1 page jsp render ko phải 1 page UI đàng hoàng
        //if you are trying to use this ask Nam before even trying.
{
    private final String ERROR_PAGE = "notFoundPage"; //lẻ ra là lỗi 500 ko phải 404
    private final String SEARCH_RESULT_PAGE = "userListResultPage";
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
        //modify url
        ServletContext context = request.getServletContext();
        Map<String, String> roadmap = (Map<String, String>) context.getAttribute("ROADMAP");
        String url = roadmap.get(ERROR_PAGE);
        //get parameter list
        String search = request.getParameter("txtSearch");
        String gender = request.getParameter("selectedGender");
        String status = request.getParameter("selectedStatus");
        String role = request.getParameter("selectedRole");
        String major = request.getParameter("selectedMajor");
        //server side decleration
        HttpSession session = request.getSession(false);
        AccountDTO currentadmin = (AccountDTO)session.getAttribute("CURRENT_USER");
        //if(search.trim().isEmpty()) return; //performance tăng 0.0000000000000000000000000000000000000000000000000001x check bên js ez boi
        //khá là conflict khi phải check này nếu người dùng nhập thẳng url
        try
        {
            //ko cần check session null khi cái attribute login đã null
            if(currentadmin != null) //check đăng nhập và thành công
            {
                String checkRole = currentadmin.getRole();
                if(checkRole.equals("A")) //check tài khoản là admin và thành công
                {//first thing first is apply button
                    //wow now you dont have a chance to get into my servlet again hehe
                    Userlist cachetool = (Userlist)session.getAttribute("CACHING_USER_LIST");
                    if(cachetool != null) //check if the user has pressed userlist button and prevent the none logic flow
                    {
                        if(search != null) // One servlet do all the thing 
                        {
                            if(search.contains("@"))
                            {
                                if(search.matches(".*@$")) //tìm kiếm tất cả domain
                                {
                                    ArrayList<UserlistDTO> newlist = cachetool.searchSpecificEmail(search.substring(0, search.length() - 1));
                                    newlist = cachetool.filteredList(gender, status, role, major, newlist);
                                    url = roadmap.get(SEARCH_RESULT_PAGE);
                                    request.setAttribute("USER_LIST", newlist);
                                    request.setAttribute("IS_IT_MENTOR", role);
                                    //log("true condition");
                                }
                                else
                                {
                                    String[] emailSpliter = search.split("@"); //limitation you can't read the email with 2 @ symbols
                                    //ArrayList<UserlistDTO> newlist = dao.searchSpecificEmail(emailSpliter[0], emailSpliter[1]);
                                    ArrayList<UserlistDTO> newlist = cachetool.searchSpecificEmail(emailSpliter[0], emailSpliter[1]);
                                    newlist = cachetool.filteredList(gender, status, role, major, newlist);
                                    url = roadmap.get(SEARCH_RESULT_PAGE);
                                    //url = "resultpage.jsp";
                                    request.setAttribute("USER_LIST", newlist);
                                    request.setAttribute("IS_IT_MENTOR", role);
                                    //log("false condition");
                                }
                            }
                            else
                            {
                                 ArrayList<UserlistDTO> newlist = cachetool.searchAll(search);
                                newlist = cachetool.filteredList(gender, status, role, major, newlist);
                                url = roadmap.get(SEARCH_RESULT_PAGE);
                                //url = "resultpage.jsp";
                                request.setAttribute("USER_LIST", newlist);
                                request.setAttribute("IS_IT_MENTOR", role);
                            }
                        }
                        else //this else block will execute the apply button AND search with filtered
                        {//as well as Search icon
                            ArrayList<UserlistDTO> newlist = cachetool.filteredList(gender, status, role, major);
                            if(newlist != null)
                            {
                                request.setAttribute("USER_LIST", newlist);
                                request.setAttribute("IS_IT_MENTOR", role);
                                url = roadmap.get(SEARCH_RESULT_PAGE);
                            }
                        }//the end of filtering the list leaving the result
                    }
                }
            }// kết thúc tất cả việc muốn làm ở servlet này (nếu có thêm action chuyển qua switch case)
        }
        /*
        catch(SQLException ex)
        {
            log("SQL _ " + ex.getMessage());
        }
        catch(NamingException ex)
        {
            log("Naming _ " + ex.getMessage());
        }
        */
        catch(Exception ex)
        {
            log("Shit happend: " + ex.toString());
        }
        finally
        {
            //actually it wont go to here but if there is someone try to write the url we will need the response
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
            Logger.getLogger(SearchFilteringServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(SearchFilteringServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SearchFilteringServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(SearchFilteringServlet.class.getName()).log(Level.SEVERE, null, ex);
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
