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
import java.util.function.Predicate;
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
import swp.userlist.UserlistDAO;
import swp.userlist.UserlistDTO;

/**
 *
 * @author ADMIN
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
                {
                    if(gender == null && status == null)
                    {//all code in this block is using database
                        if(search.contains("@"))
                        {   
                            String[] emailSpliter = search.split("@"); //limitation you can't read the email with 2 @ symbols
                            UserlistDAO dao = new UserlistDAO();
                            ArrayList<UserlistDTO> newlist = dao.searchSpecificEmail(emailSpliter[0], emailSpliter[1]);
                            url = roadmap.get(SEARCH_RESULT_PAGE);
                            //url = "resultpage.jsp";
                            request.setAttribute("USER_LIST", newlist);
                        }
                        else //if the search doesn't find any @ symbol
                        {
                            UserlistDAO dao = new UserlistDAO();
                            ArrayList<UserlistDTO> newlist = dao.searchAll(search);
                            url = roadmap.get(SEARCH_RESULT_PAGE);
                            //url = "resultpage.jsp";
                            request.setAttribute("USER_LIST", newlist);
                        }
                    }
                    else //this else block will execute the apply button
                    {//all code in this block will not using the database
                        ArrayList<UserlistDTO> oldlist = (ArrayList<UserlistDTO>)session.getAttribute("CACHING_USER_LIST");
                        //PHẢI CẤP PHÁT 1 VÙNG NHỚ CHO NEWLIST NẾU KHÔNG MECHANIC CỦA SESSION SẼ TỰ ĐỘNG CẬP NHẬT SAU KHI MODIFY CÁI OLDLIST DÙ KHÔNG SET ATTRIBUTE
                        ArrayList<UserlistDTO> newlist = new ArrayList<>(oldlist);
                        //it's 2021 AND OF COURSE I HAVE READ A LOT OF DOCUMENT SO I WILL USE IT
                        // IF THERE IS ANYONE WHO STAND IN MY WAY SAYING ABOUT AM NOT UNDERSTANDING WHILE USING THOSE SYNTAX THEN FUCK OFF
                        // mostly using lambda it can use anonymous class but I prefer C# syntax so...
                        boolean checksearch = search.equals("");//search all với cái filt
                        //MAKE SURE EACH BLOCK CONDITION BELOW EXECUTE ONLY ONE TIME
                        if(gender.equals("all") && !status.equals("all")) //filt with status
                        {
                            if(checksearch)
                            {
                                newlist.removeIf(thestatus -> !thestatus.getStatusaccount().equals(status));
                            }
                            else
                            {
                                newlist.removeIf(thestatus -> !thestatus.getStatusaccount().equals(status));
                                //this is anonymous class practice from me dont mind it
                                Predicate<UserlistDTO> condition = new Predicate<UserlistDTO>()
                                {
                                    public boolean test(UserlistDTO user)
                                    {
                                        return !user.getEmail().contains(search) && !user.getName().contains(search);
                                    }
                                };
                                newlist.removeIf(condition);
                            }
                        }
                        else if(status.equals("all") && !gender.equals("all")) //filt with gender
                        {
                            if(checksearch)
                            {
                                newlist.removeIf(thegender -> !thegender.getGender().equals(gender));
                            }
                            else
                            {
                                newlist.removeIf(thegender -> !thegender.getGender().equals(gender));
                                Predicate<UserlistDTO> condition = user -> !user.getEmail().contains(search) && !user.getName().contains(search);
                                newlist.removeIf(condition);
                                //tách ra cho dễ kiểm soát
                            }
                        }
                        else //cả 2 đều được chọn
                        {
                            if(checksearch)//search ko có gì cả
                            {
                                if(status.equals("all") && gender.equals("all"))
                                {
                                    //the list is not change if it execute this block only in the whole if else storm
                                }
                                else
                                {
                                    Predicate<UserlistDTO> condition = user -> !user.getGender().equals(gender) || !user.getStatusaccount().equals(status);
                                    newlist.removeIf(condition);
                                }
                            }
                            else //có search
                            {
                                if(status.equals("all") && gender.equals("all"))
                                {
                                    Predicate<UserlistDTO> conditionsearch = user -> !user.getEmail().contains(search) && !user.getName().contains(search);
                                    newlist.removeIf(conditionsearch);
                                }
                                else
                                {
                                    Predicate<UserlistDTO> conditionchoice = user -> !user.getGender().equals(gender) || !user.getStatusaccount().equals(status);
                                    Predicate<UserlistDTO> conditionsearch = user -> !user.getEmail().contains(search) && !user.getName().contains(search);
                                    newlist.removeIf(conditionchoice);
                                    newlist.removeIf(conditionsearch);
                                }
                            }
                        }
                        //thế là ko phải gọi tới DAO ez game
                        request.setAttribute("USER_LIST", newlist);
                        url = roadmap.get(SEARCH_RESULT_PAGE);
                    }//the end of filtering the list leaving the result
                }// kết thúc tất cả việc muốn làm ở servlet này (nếu có thêm action chuyển qua switch case)
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
