/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.account;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import swp.utils.DBHelper;

/**
 *
 * @author Admin
 */
public class AccountDAO {
    private List<AccountDTO> listAccounts;

    public List<AccountDTO> getListAccounts() {
        return listAccounts;
    }

    // ***test connection***
    public boolean test(String email, String pass){
        if(email.equalsIgnoreCase("a@fu.vn" )&& pass.equalsIgnoreCase("1")){
        return true;
        }
        return false;
    }

    public ResultSet checkLogin(String email, String password) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            if (con == null) {
                con = DBHelper.makeConnection();
                String sql = "Select email, name, campus, roleID, statusAccountID, image " + "from tblAccounts "
                        + "Where email = ? and password = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    //@chilliambush need confirm
                    return rs;
                }
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public AccountDTO getUser(String email, String password) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select email, name, gender, campus, roleID, statusAccountID, createdDate, image "
                        + "from tblAccounts "
                        + "where email = ? and password = ?";
                pst = con.prepareCall(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String userMail = rs.getString("email");
//                    String userPassword = rs.getString("password");
                    String userName = rs.getString("name");
                    boolean userGender = rs.getBoolean("gender");
                    String userCampus = rs.getString("campus");
                    String userRole = rs.getString("roleID");
                    String userStatus = rs.getString("statusAccountID");
                    String accountCreatedDate = rs.getString("createdDate");
                    String userAvatar = rs.getString("image");
                    AccountDTO obj = new AccountDTO(email, userName, userGender, userCampus, userRole, userStatus, accountCreatedDate, userAvatar);
                    return obj;
                }
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
}
