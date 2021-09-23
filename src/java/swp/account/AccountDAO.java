package swp.account;

import java.io.Serializable;
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
public class AccountDAO implements Serializable{
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
    
    private AccountDTO currentUser;
    public AccountDTO getCurrentUser(){
        return currentUser;
    }
    public void getUser(String email, String password) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select Email, Name, Gender, Campus, RoleID, StatusAccountID, CreatedDate, Image "
                        + "from tblAccounts "
                        + "where email = ? and password = ?";
                pst = con.prepareCall(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String userMail = rs.getString("Email");
                    String userName = rs.getString("Name");
                    boolean userGender = rs.getBoolean("Gender");
                    String userCampus = rs.getString("Campus");
                    String userRole = rs.getString("RoleID");
                    String userStatus = rs.getString("StatusAccountID");
                    String accountCreatedDate = rs.getString("CreatedDate");
                    String userAvatar = rs.getString("Image");
                    AccountDTO obj = new AccountDTO(userMail, userName, userGender, userCampus, userRole, userStatus, accountCreatedDate, userAvatar);
                    this.currentUser = obj;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
            
        }
    }
}

