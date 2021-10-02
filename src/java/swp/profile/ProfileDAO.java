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
            //cố gắng thông ass DB
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT a.Name, a.Gender, a.Campus, r.RoleName, a.Image "
                        + "FROM tblAccounts a, tblRoles r "
                        + "WHERE a.RoleID = r.RoleID AND a.Email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                //trỏ tới dòng sql đầu tiên
                if (rs.next()) {
                    String name = rs.getString(1);
                    boolean gioitinh = rs.getBoolean(2);
                    String gender;
                    if (gioitinh) {
                        gender = "Male";
                    } else {
                        gender = "Female";
                    }
                    String campus = rs.getString(3);
                    String RoleID = rs.getString(4);
                    String URL = rs.getString(5);
                    int Awards = totalReward(email);
                    ProfileDTO dto = new ProfileDTO(name, campus, email, RoleID, URL, Awards, gender);
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

    private int totalReward(String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //cố gắng thông ass DB
            con = DBHelper.makeConnection();
            if (con != null) {
//                String sql = "SELECT COUNT(p.PostID) AS TOTAL "
//                        +   "FROM tblAccounts a LEFT JOIN tblposts p "
//                        +   "ON p.AwardID is not null AND a.Email = p.EmailPost "
//                        +   "GROUP BY a.Email HAVING a.Email = ?";
                String sql = "SELECT COUNT(AwardDetailID) AS TOTAL "
                        + "FROM tblAwardDetails "
                        + "WHERE EmailStudent = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                //trỏ tới dòng sql đầu tiên
                if (rs.next()) {
                    return rs.getInt("TOTAL");
                } else {
                    //dòng này chỉ throw KHI email truyền vào ko có ở database (email để xem profile)
                    throw new SQLException("oi dit me co hacker a ??");
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
        return 0; //nếu query ở trên có thể bị lỗi thì ít ra vẫn chạy nuột (phần này sẽ test sau)
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
            //cố gắng thông ass DB
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT tag, title, postid, DATEPART(hour, ApprovedDate) as ApprovedHour, DATEPART(minute, ApprovedDate) as ApprovedMinute"
                        + ", Day(ApprovedDate) AS ApprovedDay, month(ApprovedDate) AS ApprovedMonth, year(ApprovedDate) AS ApprovedYear"
                        + " FROM tblPosts WHERE EmailPost = ? AND StatusPost = 'A'"
                        + " ORDER BY ApprovedDate desc";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                //trỏ tới dòng sql đầu tiên
                //GaRBagE Cô lét sần
                PostDAO dao = new PostDAO(); //gọi DAO trong DAO. DAO-ception
                while (rs.next()) {
                    String postid = rs.getString("postid");
                    String title = rs.getString("title");
                    String tag = rs.getString("tag");
                    //day:monnth:year  hour:minute
                    String approvedDate = Style.convertToDateFormat(rs.getInt("ApprovedDay"), rs.getInt("ApprovedMonth"),
                            rs.getInt("ApprovedYear"), rs.getInt("ApprovedHour"), rs.getInt("ApprovedMinute"));
                    int likes = dao.getLikeCounting(postid);
                    int comments = dao.getCommentCounting(postid);
                    PostDTO dto = new PostDTO(postid, Style.convertTagToArrayList(tag), title, approvedDate, likes, comments);
                    boolean check = list.add(dto);
                    if (!check) {
                        throw new SQLException("EY YO WTF");
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
