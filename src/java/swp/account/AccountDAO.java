package swp.account;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import swp.utils.DBHelper;
import swp.utils.HashPassword;

/**
 *
 * @author Admin
 */
public class AccountDAO implements Serializable {

    private List<AccountDTO> listAccounts;

    public List<AccountDTO> getListAccounts() {
        return listAccounts;
    }

    // ***test connection***
    public boolean test(String email, String pass) {
        if (email.equalsIgnoreCase("a@fu.vn") && pass.equalsIgnoreCase("1")) {
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

    public AccountDTO getCurrentUser() {
        return currentUser;
    }

    public void getUser(String email, String password) throws NamingException, SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select Email, Name, Gender, Campus, RoleID, StatusAccountID, CreatedDate, Image, Password "
                        + "from tblAccounts "
                        + "where email = ?";
                pst = con.prepareCall(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String userMail = rs.getString("Email");
                    String passwordHashed = rs.getString("Password");
                    String userName = rs.getString("Name");
                    boolean userGender = rs.getBoolean("Gender");
                    String userCampus = rs.getString("Campus");
                    String userRole = rs.getString("RoleID");
                    String userStatus = rs.getString("StatusAccountID");
                    String accountCreatedDate = rs.getString("CreatedDate");
                    String userAvatar = rs.getString("Image");
                    AccountDTO obj = new AccountDTO(userMail, userName, userGender, userCampus, userRole, userStatus, accountCreatedDate, userAvatar);
                    boolean comparePassword = HashPassword.validatePassword(password, passwordHashed);
                    this.currentUser = comparePassword == true ? obj : null;
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
    
    public boolean checkDuplicate(String email) throws SQLException {
         boolean check = false;
         PreparedStatement stm = null;
         Connection conn = null;
         ResultSet rs = null;
        try {
            conn = DBHelper.makeConnection();
           if (conn != null) {
               String sql = "SELECT email from tblAccounts where email=?";
               stm = conn.prepareStatement(sql);
               stm.setString(1, email);
               rs = stm.executeQuery();  
               if (rs.next()) {
                   check = true;
               }
           }

            check = stm.executeUpdate() > 0; //wtf ? nó đã false từ đầu rồi mà???
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
            return check;
        }
    }

    public boolean registerUser(AccountDTO user) throws SQLException, NamingException {
        boolean check = false;
        PreparedStatement stm = null;
        Connection conn = null;
        java.util.Date date = new java.util.Date();
        java.sql.Timestamp sqlTimeStamp = new java.sql.Timestamp(date.getTime());
        try {
            conn = DBHelper.makeConnection();
            String sql = "insert into tblAccounts(email, password, name, gender, campus, roleID, "
                    + "statusAccountID, CreatedDate, Image) " + "VALUES(?,?,?,?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, user.getEmail());
            stm.setString(2, user.getPassword());
            stm.setNString(3, user.getName());
            stm.setBoolean(4, user.isGender());
            stm.setNString(5, user.getCampus());
            stm.setString(6, "S");
            stm.setString(7, "A");
            stm.setTimestamp(8, sqlTimeStamp);
            stm.setNString(9, user.getAvatar());

            check = stm.executeUpdate() > 0;
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;

    }
    
    public boolean checkBan(String email) throws SQLException, NamingException{
        PreparedStatement stm = null;
         Connection conn = null;
         ResultSet rs = null;
         boolean check = false;
         try{
             conn = DBHelper.makeConnection();
             String sql = "select * from tblAccounts where email = ? AND StatusAccountID = 'B' ";
             stm = conn.prepareStatement(sql);
             stm.setString(1, email);
             rs = stm.executeQuery();
             if(rs.next()){
                 check = true;
             }
         }finally{
             if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
         }
         return check;
    }
    
    public AccountDTO getInformationUserFromEmail(String email) throws NamingException, SQLException, NoSuchAlgorithmException, InvalidKeySpecException 
    {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try 
        {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT Email, Name, Gender, Campus, RoleID, StatusAccountID, CreatedDate, Image "
                        + "FROM tblAccounts "
                        + "where email = ?";
                pst = con.prepareCall(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) 
                {
                    String userName = rs.getString("Name");
                    boolean userGender = rs.getBoolean("Gender");
                    String userCampus = rs.getString("Campus");
                    String userRole = rs.getString("RoleID");
                    String userStatus = rs.getString("StatusAccountID");
                    String accountCreatedDate = rs.getString("CreatedDate");
                    String userAvatar = rs.getString("Image");
                    AccountDTO info = new AccountDTO(email, userName, userGender, userCampus, userRole, userStatus, accountCreatedDate, userAvatar);
                    return info;
                }
            }
        } 
        finally 
        {
            if (con != null) 
            {
                con.close();
            }
            if (pst != null) 
            {
                pst.close();
            }
            if (rs != null) 
            {
                rs.close();
            }

        }
        return null;
    }

}
