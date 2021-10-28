package swp.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swp.account.AccountDAO;
import swp.account.AccountDTO;
import swp.accountError.AccountError;
import swp.utils.HashPassword;

/**
 *
 * @author macbook
 */
public class RegisterServlet extends HttpServlet {

    private static final String SUCCESS = "firstLoginPage";
    private static final String FAIL = "registerPage";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = request.getServletContext();
        Map<String, String> roadmap = (Map<String, String>) context.getAttribute("ROADMAP");
        String url = roadmap.get(FAIL);
        HttpSession session = request.getSession(true); //performance ko sao vì filter chặn trước khi được vào
        
        try {
            String name = request.getParameter("name");
            boolean gender = "1".equals(request.getParameter("gender"));
            String avatarURL = request.getParameter("avatarURL");
            String campus = request.getParameter("campus");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            byte[] bytesBane = name.getBytes(StandardCharsets.ISO_8859_1);     
            byte[] bytesCampus = campus.getBytes(StandardCharsets.ISO_8859_1);
            name = new String(bytesBane, StandardCharsets.UTF_8);
            campus = new String(bytesCampus, StandardCharsets.UTF_8);
            if(email != null)
            {
                String hash = HashPassword.createHash(password);
                AccountError accountError = new AccountError();
                AccountDTO user = new AccountDTO(name, gender, avatarURL, campus, email, hash);
                AccountDAO dao = new AccountDAO();
                boolean check = dao.registerUser(user);
                if (check) {

                    url = roadmap.get(SUCCESS);
                } else {
                    boolean checkDuplicate = dao.checkDuplicate(email);
                    if (checkDuplicate) {
                        accountError.setNameError(name);
                        accountError.setGenderError(gender);
                        accountError.setEmailError("Email already exists.");
                        request.setAttribute("ACCOUNT_ERROR", accountError);
                    }
                }
            }
            else    //AM USING SESSION TO STORE EMAIL NOT BECAUSE I CANNOT TAKE 
                //EMAIL PARAMETER BUT BECAUSE I DON'T WANT CLIENT TO CHANGE HIDDEN FIELD INPUT TAG FROM DEVELOPER TOOL
            {
                log("WELCUM");
                AccountDTO user = (AccountDTO) session.getAttribute("GMAIL_REGISTER");
                log(user.getEmail());
            }
            //log(email + " _ " + name);
        } catch (Exception e) {
            log(e.getMessage());
        } finally {
            session.removeAttribute("GMAIL_REGISTER");
            request.getRequestDispatcher(url).forward(request, response);
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
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
