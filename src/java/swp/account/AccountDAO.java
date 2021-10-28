package swp.account;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                String sql = "Select Email, Name, Gender, Campus, RoleID, StatusAccountID, CreatedDate, Image, Password, CategoryManagement "
                        + "from tblAccounts "
                        + "where email = ?";
                pst = con.prepareCall(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String userMail = rs.getString("Email");
                    String passwordHashed = rs.getString("Password");
                    String userName = rs.getNString("Name"); // get n string
                    boolean userGender = rs.getBoolean("Gender");
                    String userCampus = rs.getString("Campus");
                    String userRole = rs.getString("RoleID");
                    String userStatus = rs.getString("StatusAccountID");
                    String accountCreatedDate = rs.getString("CreatedDate");
                    String userAvatar = rs.getString("Image");
                    int categoryManagement = rs.getInt("CategoryManagement");
                    AccountDTO obj = new AccountDTO(userMail, userName, userGender, userCampus, userRole, userStatus, accountCreatedDate, userAvatar, categoryManagement);
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
            //(P): Ủa sao lại có câu này nhỉ, chưa hiểu lắm///
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
        try {
            conn = DBHelper.makeConnection();
            String sql = "insert into tblAccounts(email, password, name, gender, campus, roleID, "
                    + "statusAccountID, CreatedDate, Image, CategoryManagement, Note) " + "VALUES(?, ?, ?, ?, ?, ?, ?, getdate(), ?, ?,null)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, user.getEmail());
            stm.setString(2, user.getPassword());
            stm.setNString(3, user.getName());
            stm.setBoolean(4, user.isGender());
            stm.setNString(5, user.getCampus());
            stm.setString(6, "S");//Student
            stm.setString(7, "A");//Active
            stm.setNString(8, user.getAvatar());
            stm.setInt(9, 0);//set 0 là mặc định đó là Student

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

    public boolean checkBan(String email) throws SQLException, NamingException {
        PreparedStatement stm = null;
        Connection conn = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            String sql = "select * from tblAccounts where email = ? AND StatusAccountID = 'B' ";
            stm = conn.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
            }
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
        }
        return check;
    }

    public AccountDTO getInformationUserFromEmail(String email) throws NamingException, SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT Email, Name, Gender, Campus, RoleID, StatusAccountID, CreatedDate, Image, CategoryManagement "
                        + "FROM tblAccounts "
                        + "where email = ?";
                pst = con.prepareCall(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String userName = rs.getString("Name");
                    boolean userGender = rs.getBoolean("Gender");
                    String userCampus = rs.getString("Campus");
                    String userRole = rs.getString("RoleID");
                    String userStatus = rs.getString("StatusAccountID");
                    String accountCreatedDate = rs.getString("CreatedDate");
                    String userAvatar = rs.getString("Image");
                    int categoryManagement = rs.getInt("CategoryManagement");
                    AccountDTO info = new AccountDTO(email, userName, userGender, userCampus, userRole, userStatus, accountCreatedDate, userAvatar, categoryManagement);
                    return info;
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
        return null;
    }
    
    /*
    public boolean createAccountForFirstTimeGmail(String email, String name, String url) throws NamingException, SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblAccounts(email, password, name, gender, campus, roleID, statusAccountID, CreatedDate, Image, CategoryManagement, Note) "
                                            + "VALUES(?, null, ?, 1, '', 'S', 'A', GETDATE(), ?, ?,null)";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, name);
                stm.setString(3, url);
                stm.setInt(4, 0);//tạo nick là auto student

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return false;
    }
    */

    public boolean giveAward(String email, int awardID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into tblAwardDetails(AwardDetailID, AwardID, EmailStudent, Date) "
                        + "values(NEWID(), ?, ?,GETDATE())";
                stm = con.prepareStatement(sql);

                stm.setInt(1, awardID);
                stm.setString(2, email);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return false;
    }

    public ArrayList<AccountDTO> getOutStandingUsers() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<AccountDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select myTable.EmailPost, myTable.Total, a.Name, a.Image "
                        + "from (select COUNT(PostID) as Total, EmailPost from tblPosts where StatusPost = 'A' group by EmailPost ) myTable left join tblAccounts a "
                        + "on myTable.EmailPost = a.Email where a.StatusAccountID = 'A' order by myTable.Total desc";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String email = rs.getString("EmailPost");
                    String name = rs.getNString("Name");
                    String avatar = rs.getString("Image");
                    int totalPosts = rs.getInt("Total");
                    ArrayList<Integer> awards = getAwardsByEmail(email);
                    int likes = getTotalLikesByEmail(email);
                    AccountDTO dto = new AccountDTO(email, name, avatar, likes, awards, totalPosts);
                    list.add(dto);
                }
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public ArrayList<AccountDTO> getOutStandingUsersByLikes() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<AccountDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select t.Email, a.Name, a.Image, t.Total "
                        + "from (select a.Email, count(l.ID) as Total "
                        + "from tblAccounts a, tblPosts p, tblLikes l "
                        + "where a.Email = p.EmailPost and p.PostID = l.PostID and a.StatusAccountID = 'A' "
                        + "and p.StatusPost = 'A' group by a.Email) t left join tblAccounts a "
                        + "on t.Email = a.Email order by Total desc";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String email = rs.getString("Email");
                    String name = rs.getNString("Name");
                    String avatar = rs.getString("Image");
                    int likes = rs.getInt("Total");
                    ArrayList<Integer> awards = getAwardsByEmail(email);
                    int posts = getTotalPostsByEmail(email);
                    AccountDTO dto = new AccountDTO(email, name, avatar, likes, awards, posts);
                    list.add(dto);
                }
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }
    
    public int getTotalPostsByEmail(String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int posts = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(PostID) as Total from tblPosts where EmailPost = ? and StatusPost = 'A'";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    posts = rs.getInt("Total");
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return posts;
    }

    public ArrayList<Integer> getAwardsByEmail(String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Integer> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select AwardID from tblAwardDetails where EmailStudent = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(rs.getInt("AwardID"));
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public int getTotalLikesByEmail(String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int likes = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(ID) as Total from tblLikes where PostID in (select PostID from tblPosts where EmailPost = ? and StatusPost = 'A')";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    likes = rs.getInt("Total");
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return likes;
    }

    public boolean checkAward(String email, int awardId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select AwardDetailID from tblAwardDetails where EmailStudent = ? and AwardID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setInt(2, awardId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }
    
    public String getBanReason(String email) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String reason = null;
        try{
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "select Note from tblAccounts where email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if(rs.next()){
                    reason = rs.getString("Note");
                }
            }
            
        }finally{
           if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            } 
        }
        return reason;
    }
}
