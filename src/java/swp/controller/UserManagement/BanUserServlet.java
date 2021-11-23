package swp.controller.UserManagement;

import java.io.IOException;
import java.io.PrintWriter;
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

/*** Created By Saphareong ***/
@WebServlet(name="BanUserServlet", urlPatterns={"/BanUserServlet"})
public class BanUserServlet extends HttpServlet 
{
    private final String ERROR_PAGE = "notFoundPage";
    private final String PROGRESS_RESULT_PAGE = "userListResultPage";
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, NamingException, SQLException 
    {
        response.setContentType("text/html;charset=UTF-8");
        //Parameters
        String email = request.getParameter("txtEmail");
        String reason = request.getParameter("txtReason");
        String search = request.getParameter("txtSearch");
        String selection = request.getParameter("txtSelection");
        //Serverside declaration
        HttpSession session = request.getSession(false);
        ServletContext context = request.getServletContext();
        Map<String, String> roadmap = (Map<String, String>) context.getAttribute("ROADMAP");
        String url = roadmap.get(ERROR_PAGE);
        
        try
        {
            AccountDTO someone = (AccountDTO) session.getAttribute("CURRENT_USER");
            if(someone != null) //có đăng nhập
            {
                if(someone.getRole().equals("A")) //admin
                {
                    Userlist cachetool = (Userlist) session.getAttribute("CACHING_USER_LIST");
                    if(reason.isEmpty()) return; //learn to love again boy i wont let you comethrough my servlet.
                    if(cachetool != null) //phải có gì đó từ userlist
                    {
                        UserlistDAO dao = new UserlistDAO();
                        boolean IsItBanned = dao.banAccount(email, reason);
                        if(IsItBanned)
                        {
                            cachetool.BanFullList(email);
                            if(search.equals("all") && selection.equals("all"))
                            {
                                ArrayList<UserlistDTO> currentlist = cachetool.getFullList();
                                request.setAttribute("USER_LIST", currentlist);
                            }
                            else if(search.equals("all")) //filtering
                            {
                                String[] selected = selection.split("\\.");
                                ArrayList<UserlistDTO> currentlist = cachetool.filteredList(selected[0], selected[1], selected[2], selected[3]);
                                request.setAttribute("USER_LIST", currentlist);
                                request.setAttribute("IS_IT_MENTOR", selected[2]);
                            }
                            else if(selection.equals("all")) //searching
                            {
                                if(search.contains("@"))
                                {
                                    if(search.matches(".*@$")) //tìm kiếm tất cả domain
                                    {
                                        ArrayList<UserlistDTO> currentlist = cachetool.searchSpecificEmail(search.substring(0, search.length() - 1));
                                        request.setAttribute("USER_LIST", currentlist);
                                    }
                                    else
                                    {
                                        String[] emailSpliter = search.split("@"); //limitation you can't read the email with 2 @ symbols
                                        ArrayList<UserlistDTO> currentlist = cachetool.searchSpecificEmail(emailSpliter[0], emailSpliter[1]);
                                        request.setAttribute("USER_LIST", currentlist);
                                    }
                                }
                                else
                                {
                                     ArrayList<UserlistDTO> currentlist = cachetool.searchAll(search);
                                    //url = "resultpage.jsp";
                                    request.setAttribute("USER_LIST", currentlist);
                                }
                            }
                            else //searching and filtering
                            {
                                String[] selected = selection.split("\\.");
                                //ArrayList<UserlistDTO> currentlist = cachetool.filteredList(selected[0], selected[1], selected[2], selected[3]);
                                if(search.contains("@"))
                                {
                                    if(search.matches(".*@$")) //tìm kiếm tất cả domain
                                    {
                                        ArrayList<UserlistDTO> currentlist = cachetool.searchSpecificEmail(search.substring(0, search.length() - 1));
                                        currentlist = cachetool.filteredList(selected[0], selected[1], selected[2], selected[3], currentlist);
                                        request.setAttribute("USER_LIST", currentlist);
                                        request.setAttribute("IS_IT_MENTOR", selected[2]);
                                    }
                                    else
                                    {
                                        String[] emailSpliter = search.split("@"); //limitation you can't read the email with 2 @ symbols
                                        ArrayList<UserlistDTO> currentlist = cachetool.searchSpecificEmail(emailSpliter[0], emailSpliter[1]);
                                        currentlist = cachetool.filteredList(selected[0], selected[1], selected[2], selected[3], currentlist);
                                        request.setAttribute("USER_LIST", currentlist);
                                        request.setAttribute("IS_IT_MENTOR", selected[2]);
                                    }
                                }
                                else
                                {
                                    ArrayList<UserlistDTO> currentlist = cachetool.searchAll(search);
                                    currentlist = cachetool.filteredList(selected[0], selected[1], selected[2], selected[3], currentlist);
                                    request.setAttribute("IS_IT_MENTOR", selected[2]);
                                    request.setAttribute("USER_LIST", currentlist);
                                }
                            }
                            url = roadmap.get(PROGRESS_RESULT_PAGE);
                        }
                    }
                }
            }
        }
        catch(NamingException ex)
        {
            log("Naming: " + ex.getMessage());
        }
        catch(SQLException ex)
        {
            log("SQL: " + ex.getMessage());
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try
        {
            processRequest(request, response);
        } catch (NamingException ex)
        {
            Logger.getLogger(BanUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(BanUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try
        {
            processRequest(request, response);
        } catch (NamingException ex)
        {
            Logger.getLogger(BanUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(BanUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
