
package swp.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
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
import swp.account.AccountDAO;
import swp.account.AccountDTO;
import swp.utils.ProcessingGoogle;


@WebServlet(name = "LoginGoogleServlet", urlPatterns =
{
    "/LoginGoogleServlet"
})

/*********** CODE NÀY CỦA BẠN NAM có gì lỗi thì kêu tui ***************/
//Nhật ký ngày 1: Tôi làm cái chức năng này giống với coursera hay github hay bát cứ forum nào cần password nếu là 1 gmail mới đến FPT blog, Nhưng client say NO PASSWORD
//Nhật ký ngày 2: Tôi làm cái chức năng này giống những chúa hề DEV vì INSERT mẹ nó và để những thông tin account rất chi là normie
//Nhật ký ngày 3: Tôi làm cái chức năng này quay lại ngày 1 những không cho nhập password

public class LoginGoogleServlet extends HttpServlet
{
    private final String ERROR_PAGE = "notFoundPage";
    //private final String HOME_PAGE = "homePage";
    private final String LOGIN_PAGE = "loginPage";
    private final String LOAD_BLOG = "loadBlogs";
    private final String REGISTER_PAGE = "registerPage";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws GeneralSecurityException
     * @throws SQLException
     * @throws NamingException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, GeneralSecurityException, SQLException, NamingException
    {
        response.setContentType("text/html;charset=UTF-8");
        //get parameter
        String idtoken = request.getParameter("token");
        //serverside declare
        ServletContext context = request.getServletContext();
        Map<String, String> roadmap = (Map<String, String>) context.getAttribute("ROADMAP");
        String url = roadmap.get(ERROR_PAGE);
        HttpSession session = request.getSession(true); //take my hand
        try
        {
            if(idtoken != null)
            {
                ProcessingGoogle STAND_USER = new ProcessingGoogle(idtoken); //dòng này sẽ quăng generalsecurity
                if(STAND_USER.isCheckingstatus()) //back-end đã lấy được thông tin token thành công
                {
                    String email = STAND_USER.getEmail();
                    String name = STAND_USER.getName();
                    String picurl = STAND_USER.getPicUrl();
                    AccountDAO dao = new AccountDAO();
                    //dưới đây chỉ check nếu là 1 email có duy nhất 1 dấu @ (dấu thứ 2 thì ăn lz)
                    int start = email.indexOf("@") + 1;
                    int end = email.length();
                    String emailTale = email.substring(start, end);
                    if(emailTale.equals("fpt.edu.vn") || emailTale.equals("fu.edu.vn"))
                    {
                        if(dao.checkDuplicate(email)) //email đã có ở database
                        {
                            if(dao.checkBan(email))
                            {
                                String reasonBan = dao.getBanReason(email);
                                request.setAttribute("BAN", "banned");
                                request.setAttribute("REASON_BAN", reasonBan);
                                url = roadmap.get(LOGIN_PAGE);
                            }
                            else
                            {
                                AccountDTO information_login = dao.getInformationUserFromEmail(email);
                                session.setAttribute("LOGIN", "logined");
                                session.setAttribute("CURRENT_USER", information_login);
                                url = roadmap.get(LOAD_BLOG);
                            }
                        }
                        else //email chưa có ở database
                        {
                            AccountDTO cachingInfo = new AccountDTO(email, name, picurl);
                            request.setAttribute("GMAIL_REGISTER", cachingInfo);
                            session.setAttribute("CACHING_GMAIL", cachingInfo.getEmail());
                            // rule number one never trust client input so we will use this little SHIT
                            url = roadmap.get(REGISTER_PAGE);
                            /*
                            boolean checkregister = dao.createAccountForFirstTimeGmail(email, name, picurl);
                            if(checkregister)
                            {
                                AccountDTO information_login = dao.getInformationUserFromEmail(email);// thêm CM
                                request.setAttribute("", dao);
                                session.setAttribute("LOGIN", "logined");
                                session.setAttribute("CURRENT_USER", information_login);
                                url = roadmap.get(LOAD_BLOG);
                            }
                            else
                            {
                                //set attribute cho request show lỗi bên error page in the fucking future or maybe never
                                url = roadmap.get(ERROR_PAGE);
                            }
                            */
                        }
                    }
                    else
                    {
                        request.setAttribute("FPT", "NOTFPT");
                        url = roadmap.get(LOGIN_PAGE);
                    }
                }//kết thúc nếu lấy được dữ liệu google thành công
                //dòng này sẽ else thêm để có thể quăng lỗi cho người dùng biết rằng hệ thống google login bị lỗi nên là đăng nhập bằng form đi thằng lz
            }
        }
        catch(GeneralSecurityException ex)
        {
            log("General Security _ " + ex.getMessage());
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
        } catch (GeneralSecurityException ex)
        {
            Logger.getLogger(LoginGoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(LoginGoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex)
        {
            Logger.getLogger(LoginGoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (GeneralSecurityException ex)
        {
            Logger.getLogger(LoginGoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(LoginGoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex)
        {
            Logger.getLogger(LoginGoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
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
