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

    // *** test connection ***
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
                String sql = "SELECT email, name, campus, roleID, statusAccountID, image "
                        + "FROM tblAccounts "
                        + "WHERE email = ? AND password = ?";
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
    
    public static boolean IsItBanned(String email) throws NamingException, SQLException //hàm này chạy rất thường xuyên cùng filter và ko dính liếu hàm khác
    {//vì lí do đó ta ko dùng như bình thường và cũng 1 phần là do không dùng design pattern singleton... sign 21/11/2021 Saphareong
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try
        {
            //cố gắng thông ass DB
            con = DBHelper.makeConnection();
            if(con != null)
            {
                String sql = "SELECT StatusAccountID FROM tblAccounts WHERE Email = ?"; 
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                //trỏ tới dòng sql đầu tiên
                if(rs.next())
                {
                    String WhatIsIt = rs.getString(1);
                    if(WhatIsIt.equals("A")) return false;
                    else return true;
                }
            }
        }
        finally
        {
            if(rs != null)
            {
                rs.close();
            }     
            if(con != null)
            {
                con.close();
            }
            if(stm != null)
            {
                stm.close();
            }
        }
        return false;
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
                String sql = "SELECT Email, Name, Gender, Campus, RoleID, StatusAccountID, CreatedDate, Image, Password, CategoryManagement "
                        + "FROM tblAccounts "
                        + "WHERE email = ?";
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
                String sql = "SELECT email FROM tblAccounts WHERE email = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
//            check = stm.executeUpdate() > 0; Phương đã comment dòng này
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
            String sql = "INSERT INTO tblAccounts(email, password, name, gender, campus, roleID, "
                    + "statusAccountID, CreatedDate, Image, CategoryManagement, Note) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, GETDATE(), ?, ?, NULL)";
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

    public boolean registerForGmail(AccountDTO user) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblAccounts(email, password, name, gender, campus, roleID, statusAccountID, CreatedDate, Image, CategoryManagement, Note) "
                        + "VALUES(?, null, ?, ?, ?, 'S', 'A', GETDATE(), ?, 0,null)";
                stm = con.prepareStatement(sql);
                stm.setString(1, user.getEmail());
                stm.setNString(2, user.getName());
                stm.setBoolean(3, user.isGender());
                stm.setNString(4, user.getCampus());
                stm.setString(5, user.getAvatar());
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

    public boolean checkBan(String email) throws SQLException, NamingException {
        PreparedStatement stm = null;
        Connection conn = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            String sql = "SELECT * FROM tblAccounts WHERE email = ? AND StatusAccountID = 'B' ";
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
                        + "WHERE email = ?";
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

    public boolean giveAward(String email, int awardID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblAwardDetails(AwardDetailID, AwardID, EmailStudent, Date) "
                        + "VALUES(NEWID(), ?, ?, GETDATE())";
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
                String sql = "SELECT myTable.EmailPost, myTable.Total, a.Name, a.Image "
                        + "FROM (SELECT COUNT(PostID) as Total, EmailPost FROM tblPosts WHERE StatusPost = 'A' GROUP BY EmailPost ) myTable LEFT JOIN tblAccounts a "
                        + "ON myTable.EmailPost = a.Email WHERE a.StatusAccountID = 'A' AND a.RoleID = 'S' ORDER BY myTable.Total DESC";
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
                String sql = "SELECT t.Email, a.Name, a.Image, t.Total "
                        + "FROM (SELECT a.Email, COUNT(l.ID) as Total "
                        + "FROM tblAccounts a, tblPosts p, tblLikes l "
                        + "WHERE a.Email = p.EmailPost AND p.PostID = l.PostID AND a.StatusAccountID = 'A' AND a.RoleID = 'S' "
                        + "AND p.StatusPost = 'A' GROUP BY a.Email) t LEFT JOIN tblAccounts a "
                        + "ON t.Email = a.Email ORDER BY Total DESC";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String email = rs.getString("Email");
                    String name = rs.getNString("Name");
                    String avatar = rs.getString("Image");
                    int likes = rs.getInt("Total");
                    ArrayList<Integer> awards = getAwardsByEmail(email);// sửa thành object ko để int idaward nữa
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
                String sql = "SELECT COUNT(PostID) as Total FROM tblPosts WHERE EmailPost = ? AND StatusPost = 'A'";
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
                String sql = "SELECT AwardID FROM tblAwardDetails WHERE EmailStudent = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("AwardID");
                    list.add(id);
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
                String sql = "SELECT COUNT(ID) as Total FROM tblLikes WHERE PostID in "
                        + "(SELECT PostID FROM tblPosts WHERE EmailPost = ? AND StatusPost = 'A')";
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
                String sql = "SELECT AwardDetailID FROM tblAwardDetails WHERE EmailStudent = ? AND AwardID = ? ";
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

    public String getBanReason(String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String reason = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT Note FROM tblAccounts WHERE Email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    reason = rs.getString("Note");
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
        return reason;
    }

    public int getStandardOfAward(int awardId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT Standard FROM tblAwards WHERE AwardID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, awardId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("Standard");
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
        return 0;
    }
}
