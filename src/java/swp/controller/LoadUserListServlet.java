/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import swp.profile.ProfileDAO;
import swp.profile.ProfileDTO;

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
        String url = "goto404";
        
        try
        {
            ProfileDAO dao = new ProfileDAO();
            ArrayList<ProfileDTO> dto = dao.getUserList();
            //chưa check account đã đăng nhập và chưa check role
            if(dto != null) //wow có list và ko có lỗi
            {
                request.setAttribute("USER_LIST", dto); //lấy hết 500 anh em vứt vào USER_LIST attwibu
                url = "userlistPage.html"; //ai edit servlet này mà để url như thế này thì assignment 0 điểm
            }
            else
            {
                //do nothing. Who care if the executive of FPTU is screaming for error.
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
