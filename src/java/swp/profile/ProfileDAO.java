package swp.profile;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import swp.library.Style;
import swp.utils.DBHelper;
import swp.post.PostDTO;
import swp.post.PostDAO;

public class ProfileDAO implements Serializable {

    public ProfileDTO getProfile(String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT a.Name, a.Gender, a.Campus, r.RoleName, a.Image "
                        + "FROM tblAccounts a, tblRoles r "
                        + "WHERE a.RoleID = r.RoleID AND a.Email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getNString("Name");
                    String gender = rs.getBoolean("Gender") ? "Male" : "Female";
                    String campus = rs.getNString("Campus");
                    String roleID = rs.getString("RoleName");
                    String imageURL = rs.getString("Image");
                    ArrayList<Integer> awards = totalReward(email);
                    ProfileDTO dto = new ProfileDTO(name, campus, email, roleID, imageURL, gender, awards);
                    return dto;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return null;
    }

    private ArrayList<Integer> totalReward(String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Integer> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT awardid "
                        + "FROM tblAwardDetails "
                        + "WHERE EmailStudent = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(rs.getInt("AwardID"));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return list; //n???u query ??? tr??n c?? th??? b??? l???i th?? ??t ra v???n ch???y nu???t (ph???n n??y s??? test sau)
        //update db > Ch??? n??y awards l?? list n?? c?? th??? nh???n ???????c m???ng null, 1, 2, 3 ph???n t???
    }

    private ArrayList<PostDTO> list = new ArrayList<>();

    public ArrayList<PostDTO> getAllPost() {
        return list;
    }

    public void LoadThemAll(String email) throws NamingException, SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //c??? g???ng th??ng ass DB
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT [Views], tag, title, postid, DATEPART(hour, ApprovedDate) as ApprovedHour, DATEPART(minute, ApprovedDate) as ApprovedMinute"
                        + ", Day(ApprovedDate) AS ApprovedDay, month(ApprovedDate) AS ApprovedMonth, year(ApprovedDate) AS ApprovedYear"
                        + " FROM tblPosts WHERE EmailPost = ? AND StatusPost = 'A'"
                        + " ORDER BY ApprovedDate desc";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                //tr??? t???i d??ng sql ?????u ti??n
                //GaRBagE C?? l??t s???n
                PostDAO dao = new PostDAO(); //g???i DAO trong DAO. DAO-ception
                while (rs.next()) {
                    String postid = rs.getString("PostID");
                    String title = rs.getNString("Title");
                    String tag = rs.getNString("Tag");
                    //day:monnth:year  hour:minute
                    String approvedDate = Style.convertToDateFormat(rs.getInt("ApprovedDay"), rs.getInt("ApprovedMonth"),
                            rs.getInt("ApprovedYear"), rs.getInt("ApprovedHour"), rs.getInt("ApprovedMinute"));
                    int likes = dao.getLikeCounting(postid);
                    int comments = dao.getCommentCounting(postid);
                    int views = rs.getInt("Views");
                    PostDTO dto = new PostDTO(postid, Style.convertTagToArrayList(tag), title, approvedDate, likes, comments, views);
                    boolean check = list.add(dto);
                    if (!check) {
                        throw new SQLException("Adding a PostDTO failed!");
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
    }
}
