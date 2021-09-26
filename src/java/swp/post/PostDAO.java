package swp.post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import swp.library.Style;
import swp.utils.DBHelper;

public class PostDAO {

    public ArrayList<PostDTO> getAllPostList() throws SQLException, ClassNotFoundException, NamingException {
        // tên, avt, email người đăng, title, id, số like, số cmt, approvedTime
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<PostDTO> list = new ArrayList<>();
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "select tag, title, postid, emailpost"
                        + ", Day(p.ApprovedDate) as ApprovedDay, month(p.ApprovedDate) as ApprovedMonth, year(p.ApprovedDate) as ApprovedYear"
                        + ", a.name, a.image, p.AwardID"
                        + " from tblPosts p left join tblAccounts a on p.emailpost = a.email where p.StatusPost = ? order by p.ApprovedDate desc";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "A");

                rs = stm.executeQuery();
                while (rs.next()) {
                    String postID = rs.getString("PostID");
                    String emailPost = rs.getString("EmailPost");
                    String approvedDate = rs.getString("ApprovedDay") + "-" + rs.getString("ApprovedMonth") + "-"
                            + rs.getString("ApprovedYear");
                    String tag = rs.getString("Tag");
                    String title = rs.getString("Title");
                    String namePoster = rs.getString("Name");
                    String avatar = rs.getString("Image");
                    int like = getLikeCounting(postID);
                    int awardID = rs.getInt("AwardID");// bug 30%
                    int comments = getCommentCounting(postID);
                    PostDTO p = new PostDTO(postID, emailPost, Style.convertTagToArrayList(tag), title, approvedDate, namePoster, avatar, awardID,
                            like, comments);
                    list.add(p);
                }
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
        return list;
    }

    public int getCommentCounting(String postID) throws SQLException, ClassNotFoundException, NamingException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "select count(id) as TOTAL from tblComments where postid = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, postID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("TOTAL");
                }
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
        return count;
    }

    public int getLikeCounting(String postID) throws SQLException, ClassNotFoundException, NamingException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "select count(id) as TOTAL from tbllikes where LikeStatus = 1 and postid = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, postID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("TOTAL");
                }
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
        return count;
    }

    public ArrayList<PostDTO> getPostsByTitle(String title)
            throws SQLException, ClassNotFoundException, NamingException {
        ArrayList<PostDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
//                String sql = "select p.title, tag, postid, emailpost"
//                        + ", Day(p.ApprovedDate) as ApprovedDay, month(p.ApprovedDate) as ApprovedMonth, year(p.ApprovedDate) as ApprovedYear"
//                        + ", a.name, a.image, p.AwardID"
//                        + " from tblPosts p left join tblAccounts a on p.emailpost = a.email"
//                        + " and title like ? and p.StatusPost = ? order by p.ApprovedDate desc";
                String sql = "select p.title, tag, postid, emailpost"
                        + ", Day(p.ApprovedDate) as ApprovedDay, month(p.ApprovedDate) as ApprovedMonth, year(p.ApprovedDate) as ApprovedYear"
                        + ", a.name, a.image, p.AwardID"
                        + " from tblPosts p, tblAccounts a where p.emailpost = a.email"
                        + " and title like ? and p.StatusPost = ? order by p.ApprovedDate desc";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + title + "%");
                stm.setString(2, "A");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String postID = rs.getString("PostID");
                    String emailPost = rs.getString("EmailPost");
                    String approvedDate = rs.getString("ApprovedDay") + "-" + rs.getString("ApprovedMonth") + "-" + rs.getString("ApprovedYear");
                    String tag = rs.getString("Tag");
                    String namePoster = rs.getString("Name");
                    String avatar = rs.getString("Image");
                    String titleColumn = rs.getString("Title");
                    int like = getLikeCounting(postID);
                    int awardID = rs.getInt("AwardID");
                    int comments = getCommentCounting(postID);
                    PostDTO p = new PostDTO(postID, emailPost, Style.convertTagToArrayList(tag), titleColumn, approvedDate, namePoster, avatar, awardID,
                            like, comments);
                    list.add(p);
                }
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
        return list;
    }
    
    public ArrayList<PostDTO> getPostByCategory(String category) // đây ko phải static
            throws SQLException, ClassNotFoundException, NamingException 
    {
        ArrayList<PostDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) 
            {
                int cateID = Integer.parseInt(category); // database chỉ nhận int
                String sql = "SELECT p.title, tag, postid, emailpost, Day(p.ApprovedDate) AS ApprovedDay, "
                        +    "month(p.ApprovedDate) AS ApprovedMonth, year(p.ApprovedDate) AS ApprovedYear, "
                        +    "a.name, a.image, p.AwardID "
                        +    "FROM tblPosts p, tblAccounts a "
                        +    "WHERE p.emailpost = a.email AND p.CategoryID = ? AND p.StatusPost = 'A'"
                        +    "ORDER BY p.ApprovedDate asc"; //sắp xếp ngày gần đây nhất
                stm = conn.prepareStatement(sql);
                stm.setInt(1, cateID);
                rs = stm.executeQuery();
                while (rs.next()) 
                {
                    String postID = rs.getString("PostID");
                    String emailPost = rs.getString("EmailPost");
                    String approvedDate = rs.getString("ApprovedDay") + "-" + rs.getString("ApprovedMonth") + "-" + rs.getString("ApprovedYear");
                    String tag = rs.getString("Tag");
                    String namePoster = rs.getString("Name");
                    String avatar = rs.getString("Image");
                    String titleColumn = rs.getString("Title");
                    int like = getLikeCounting(postID);
                    int awardID = rs.getInt("AwardID");
                    int comments = getCommentCounting(postID);
                    PostDTO p = new PostDTO(postID, emailPost, Style.convertTagToArrayList(tag), titleColumn, approvedDate, namePoster, avatar, awardID,
                            like, comments);
                    boolean check = list.add(p);
                    if(!check)
                    {
                        throw new SQLException("getPostCategory bi ngu lz");
                    }
                } //sau khi add vô hết rồi thì return
                return list;
            }
        } finally 
        {
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
        return null; //nếu bị lỗi dòng nào đó thì nó sẽ xuống đâys
    }
}
