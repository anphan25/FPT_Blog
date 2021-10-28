/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swp.account.AccountDAO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "GiveAwardServlet", urlPatterns = {"/GiveAwardServlet"})
public class GiveAwardServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email");
        int awardID = Integer.parseInt(request.getParameter("awardID"));
        ServletContext context = request.getServletContext();
        Map<String, String> roadmap = (Map<String, String>) context.getAttribute("ROADMAP");
        String url = roadmap.get("loadDashboard");

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                if (awardID != 0) {
                    AccountDAO dao = new AccountDAO();
                    if (!dao.checkAward(email, awardID)) { //kiểm tra coi có award này chưa, có rùi thì báo lỗi
                        if(awardID == 1){
                            if(dao.getTotalPostsByEmail(email) >= dao.getStandardOfAward(awardID)){ //kiểm tra xem user này đủ tiêu chuẩn vs cái award này hay ko
                                dao.giveAward(email, awardID);
                            }else{
                                request.setAttribute("NOTIFY", "unqualified-post");
                                request.setAttribute("STANDARD_POST", dao.getStandardOfAward(awardID));
                            }
                        }
                        if(awardID == 2){
                            if(dao.getTotalLikesByEmail(email) >= dao.getStandardOfAward(awardID)){ //kiểm tra xem user này đủ tiêu chuẩn vs cái award này hay ko
                                dao.giveAward(email, awardID);
                            }else{
                                request.setAttribute("NOTIFY", "unqualified-like");
                                request.setAttribute("STANDARD_LIKE", dao.getStandardOfAward(awardID));
                            }
                        }
                        if(awardID == 3){
                            dao.giveAward(email, awardID);
                        }
                    }else{
                        request.setAttribute("NOTIFY", "error2");
                    }
                }else{
                    request.setAttribute("NOTIFY", "error");
                }

            }

        } catch (SQLException ex) {
            log("GiveAwardSVL _ SQl" + ex.getMessage());
        } catch (NamingException ex) {
            log("GiveAwardSVL _ Naming" + ex.getMessage());
        } finally {
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
        processRequest(request, response);
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
        processRequest(request, response);
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
