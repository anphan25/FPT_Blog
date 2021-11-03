package swp.post;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import swp.library.Style;
import swp.utils.DBHelper;

public class PostDAO implements Serializable {

    public ArrayList<PostDTO> getAllPostList() throws SQLException, ClassNotFoundException, NamingException {
        // tên, avt, email người đăng, title, id, số like, số cmt, approvedTime
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<PostDTO> list = new ArrayList<>();
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "SELECT [Views], Tag, Title, PostID, EmailPost, DATEPART(HOUR, ApprovedDate) as ApprovedHour, DATEPART(MINUTE, ApprovedDate) as ApprovedMinute"
                        + ", Day(ApprovedDate) as ApprovedDay, MONTH(ApprovedDate) as ApprovedMonth, YEAR(ApprovedDate) as ApprovedYear"
                        + ", a.Name, a.Image"
                        + " FROM tblPosts p LEFT JOIN tblAccounts a ON emailpost = email WHERE StatusPost = ? ORDER BY ApprovedDate DESC";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "A");

                rs = stm.executeQuery();
                while (rs.next()) {
                    String postID = rs.getString("PostID");
                    String emailPost = rs.getString("EmailPost");
                    //day:monnth:year  hour:minute
                    String approvedDate = Style.convertToDateFormat(rs.getInt("ApprovedDay"), rs.getInt("ApprovedMonth"),
                            rs.getInt("ApprovedYear"), rs.getInt("ApprovedHour"), rs.getInt("ApprovedMinute"));
                    String tag = rs.getString("Tag");
                    String title = rs.getString("Title");
                    String namePoster = rs.getString("Name");
                    String avatar = rs.getString("Image");
                    int like = getLikeCounting(postID);
                    int comments = getCommentCounting(postID);
                    int views = rs.getInt("Views");
                    PostDTO p = new PostDTO(postID, emailPost, Style.convertTagToArrayList(tag), title, approvedDate,
                            namePoster, avatar, like, comments, views);
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

    public PostDTO getRejectedPost(String postId) throws SQLException, ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        PostDTO post = null;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "SELECT [Views], Tag, Title, p.Note, PostContent, DATEPART(HOUR, ApprovedDate) as ApprovedHour, DATEPART(MINUTE, ApprovedDate) as ApprovedMinute"
                        + ", Day(ApprovedDate) as ApprovedDay, MONTH(ApprovedDate) as ApprovedMonth, YEAR(ApprovedDate) as ApprovedYear"
                        + ", Name, Image, Email"
                        + " FROM tblPosts p LEFT JOIN tblAccounts a ON EmailApprover = Email WHERE PostID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, postId);
                rs = stm.executeQuery();

                if (rs.next()) {
                    String emailMentor = rs.getString("Email");
                    //day:monnth:year  hour:minute
                    String approvedDate = Style.convertToDateFormat(rs.getInt("ApprovedDay"), rs.getInt("ApprovedMonth"),
                            rs.getInt("ApprovedYear"), rs.getInt("ApprovedHour"), rs.getInt("ApprovedMinute"));
                    String tag = rs.getString("Tag");
                    String title = rs.getString("Title");
                    String nameMentor = rs.getString("Name");
                    String avatarMentor = rs.getString("Image");
                    String note = rs.getNString("Note");
                    String postContent = rs.getNString("PostContent");
                    int views = rs.getInt("Views");
                    post = new PostDTO(postId, emailMentor, Style.convertTagToArrayList(tag), title, approvedDate, nameMentor, avatarMentor, postContent, note, views);
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
        return post;
    }

    public int getCommentCounting(String postID) throws SQLException, ClassNotFoundException, NamingException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(ID) as TOTAL FROM tblComments WHERE postid = ? AND StatusComment = 1";
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

    public int getLikeCounting(String postID) throws SQLException, NamingException {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(ID) as TOTAL FROM tbllikes WHERE postid = ? ";
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

    public boolean insertANewPost(String email, String tag, String title, String content, int categoryID, String roleID)
            throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                if (roleID.equalsIgnoreCase("M")) {// mentor auto approve lên
                    String sql = "INSERT INTO tblPosts(PostID, EmailPost, EmailApprover, StatusPost, createdDate, Tag, Title, ApprovedDate, "
                            + "PostContent, CategoryID, NewContent, Note, [Views]) "
                            + "VALUES(NEWID(), ?, ?, ?, GETDATE(), ?, ?, GETDATE(), ?, ?, NULL, NULL, 1)";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, email);
                    stm.setString(2, email);
                    stm.setString(3, "A");
                    stm.setNString(4, tag.trim());
                    stm.setNString(5, title);
                    stm.setNString(6, content);
                    stm.setInt(7, categoryID);
                    check = stm.executeUpdate() > 0;
                } else if (roleID.equalsIgnoreCase("S")) {//Student đăng bài thì chờ duyệt
                    String sql = "INSERT INTO tblPosts(PostID, EmailPost, EmailApprover, StatusPost, createdDate, Tag, Title, ApprovedDate, PostContent, CategoryID, NewContent, [Views]) "
                            + "VALUES(NEWID(), ?, NULL, ?, GETDATE(), ?, ?, NULL, ?, ?, NULL, 1)";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, email);
                    stm.setString(2, "WFA");
                    stm.setNString(3, tag.trim());
                    stm.setNString(4, title);
                    stm.setNString(5, content);
                    stm.setInt(6, categoryID);
                    check = stm.executeUpdate() > 0;
                } else {
                    check = false;
                }
            }
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

    public ArrayList<PostDTO> getPostsByTitle(String title)
            throws SQLException, ClassNotFoundException, NamingException {
        ArrayList<PostDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
//                String sql = "SELECT Title, Tag, PostID, EmailPost, DATEPART(HOUR, ApprovedDate) as ApprovedHour, DATEPART(MINUTE, ApprovedDate) as ApprovedMinute"
//                        + ", Day(ApprovedDate) as ApprovedDay, MONTH(ApprovedDate) as ApprovedMonth, YEAR(ApprovedDate) as ApprovedYear"
//                        + ", Name, Image FROM tblPosts p, tblAccounts a WHERE EmailPost = Email"
//                        + " AND Title like ? AND StatusPost = ? ORDER BY ApprovedDate DESC";
//              CHỖ NÀY XÀI LEFT JOIN ĐỂ TĂNG HIỆU SUẤT
                String sql = "SELECT [Views], Title, Tag, PostID, EmailPost, DATEPART(HOUR, ApprovedDate) as ApprovedHour, DATEPART(MINUTE, ApprovedDate) as ApprovedMinute"
                        + ", Day(ApprovedDate) as ApprovedDay, MONTH(ApprovedDate) as ApprovedMonth, YEAR(ApprovedDate) as ApprovedYear"
                        + ", Name, Image FROM tblPosts p LEFT JOIN tblAccounts a ON EmailPost = Email"
                        + " WHERE Title like ? AND StatusPost = ? ORDER BY ApprovedDate DESC";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + title + "%");
                stm.setString(2, "A");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String postID = rs.getString("PostID");
                    String emailPost = rs.getString("EmailPost");
                    //day:monnth:year  hour:minute
                    String approvedDate = Style.convertToDateFormat(rs.getInt("ApprovedDay"), rs.getInt("ApprovedMonth"),
                            rs.getInt("ApprovedYear"), rs.getInt("ApprovedHour"), rs.getInt("ApprovedMinute"));
                    String tag = rs.getString("Tag");
                    String namePoster = rs.getString("Name");
                    String avatar = rs.getString("Image");
                    String titleColumn = rs.getString("Title");
                    int likes = getLikeCounting(postID);
                    int comments = getCommentCounting(postID);
                    int views = rs.getInt("Views");
                    PostDTO p = new PostDTO(postID, emailPost, Style.convertTagToArrayList(tag), titleColumn,
                            approvedDate, namePoster, avatar, likes, comments, views);
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

    public PostDTO getPostById(String id)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        PostDTO post = null;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "SELECT [Views], PostID, Title, Tag, DATEPART(HOUR, ApprovedDate) as ApprovedHour, DATEPART(MINUTE, ApprovedDate) as ApprovedMinute,"
                        + " Day(ApprovedDate) as ApprovedDay, MONTH(ApprovedDate) as ApprovedMonth, YEAR(ApprovedDate) as ApprovedYear, PostContent,"
                        + " Name, Image, EmailPost"
                        + " FROM tblPosts p LEFT JOIN tblAccounts a"
                        + " ON Email = EmailPost WHERE PostID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String postID = rs.getString("postID");
                    String title = rs.getString("title");
                    //day:monnth:year  hour:minute
                    String approvedDate = Style.convertToDateFormat(rs.getInt("ApprovedDay"), rs.getInt("ApprovedMonth"),
                            rs.getInt("ApprovedYear"), rs.getInt("ApprovedHour"), rs.getInt("ApprovedMinute"));
                    String tags = rs.getString("tag");
                    String avatar = rs.getString("Image"); // bên common ko lấy được image, name, email
                    String name = rs.getNString("Name");
                    String content = rs.getString("PostContent");
                    String email = rs.getString("EmailPost");
                    int like = getLikeCounting(id);
                    int comments = getCommentCounting(id);
                    ArrayList<Integer> awards = getAwardsByEmail(email);
                    int views = rs.getInt("Views");
                    post = new PostDTO(postID, email, Style.convertTagToArrayList(tags), title, approvedDate, content, name, avatar, like, comments, awards, views);
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
        return post;
    }

    public ArrayList<PostDTO> getPostByCategory(String category)
            throws SQLException, ClassNotFoundException, NamingException {
        ArrayList<PostDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                int cateID = Integer.parseInt(category); // database chỉ nhận int
                String sql = "SELECT [Views], Title, Tag, PostID, EmailPost, DATEPART(HOUR, ApprovedDate) as ApprovedHour, DATEPART(MINUTE, ApprovedDate) as ApprovedMinute,"
                        + " Day(ApprovedDate) AS ApprovedDay, MONTH(ApprovedDate) AS ApprovedMonth, YEAR(ApprovedDate) AS ApprovedYear,"
                        + " Name, Image FROM tblPosts p LEFT JOIN tblAccounts a"
                        + " ON EmailPost = Email WHERE CategoryID = ? AND StatusPost = 'A'"
                        + " ORDER BY ApprovedDate DESC";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, cateID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String postID = rs.getString("PostID");
                    String emailPost = rs.getString("EmailPost");
                    String approvedDate = Style.convertToDateFormat(rs.getInt("ApprovedDay"), rs.getInt("ApprovedMonth"),
                            rs.getInt("ApprovedYear"), rs.getInt("ApprovedHour"), rs.getInt("ApprovedMinute"));
                    String tag = rs.getString("Tag");
                    String namePoster = rs.getString("Name");
                    String avatar = rs.getString("Image");
                    String titleColumn = rs.getString("Title");
                    int like = getLikeCounting(postID);
                    int comments = getCommentCounting(postID);
                    int views = rs.getInt("Views");
                    PostDTO p = new PostDTO(postID, emailPost, Style.convertTagToArrayList(tag), titleColumn,
                            approvedDate, namePoster, avatar, like, comments, views);
                    boolean check = list.add(p);
                    if (!check) {
                        throw new SQLException("Error: We have something wrong at getPostByCategory (PostDAO)!");
                    }
                }
                return list;
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
        return null;
    }

    public ArrayList<PostDTO> getPostByTags(String tag)
            throws SQLException, ClassNotFoundException, NamingException {
        ArrayList<PostDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "SELECT [Views], Title, Tag, PostID, EmailPost, DATEPART(HOUR, ApprovedDate) as ApprovedHour, DATEPART(MINUTE, ApprovedDate) as ApprovedMinute,"
                        + " Day(ApprovedDate) AS ApprovedDay, MONTH(ApprovedDate) AS ApprovedMonth, YEAR(ApprovedDate) AS ApprovedYear,"
                        + " Name, Image FROM tblPosts p LEFT JOIN tblAccounts a"
                        + " ON EmailPost = Email WHERE Tag LIKE ? AND StatusPost = 'A'"
                        + " ORDER BY ApprovedDate DESC";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + tag + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String postID = rs.getString("PostID");
                    String emailPost = rs.getString("EmailPost");
                    //day:monnth:year  hour:minute
                    String approvedDate = Style.convertToDateFormat(rs.getInt("ApprovedDay"), rs.getInt("ApprovedMonth"),
                            rs.getInt("ApprovedYear"), rs.getInt("ApprovedHour"), rs.getInt("ApprovedMinute"));
                    String tagFound = rs.getString("Tag");
                    String namePoster = rs.getString("Name");
                    String avatar = rs.getString("Image");
                    String titleColumn = rs.getString("Title");
                    int like = getLikeCounting(postID);
                    int comments = getCommentCounting(postID);
                    int views = rs.getInt("Views");
                    PostDTO p = new PostDTO(postID, emailPost, Style.convertTagToArrayList(tagFound), titleColumn,
                            approvedDate, namePoster, avatar, like, comments, views);
                    list.add(p);
                }
                return list;
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
        return null;
    }

    public PostDTO getPendingPostById(String id)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {

                String sql = "SELECT [Views], p.Note, NewContent , Title, Tag, DATEPART(HOUR, p.createdDate) as CreatedHour, DATEPART(MINUTE, p.createdDate) as CreatedMinute"
                        + ", Day(p.createdDate) as createdDay, MONTH(p.createdDate) as createdMonth, YEAR(p.createdDate) as createdYear, PostContent"
                        + ", StatusPost, CategoryID, Name, Image, Email"
                        + " FROM tblPosts p LEFT JOIN tblAccounts a"
                        + " ON Email = EmailPost WHERE PostID = ?";

                stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {

                    String title = rs.getString("Title");
                    //day:monnth:year  hour:minute
                    String createdAt = Style.convertToDateFormat(rs.getInt("createdDay"), rs.getInt("createdMonth"),
                            rs.getInt("createdYear"), rs.getInt("CreatedHour"), rs.getInt("CreatedMinute"));
                    String tags = rs.getString("Tag");
                    String avatar = rs.getString("Image");
                    String name = rs.getString("Name");

                    String content = rs.getString("PostContent");
                    String statusPost = rs.getString("StatusPost");
                    String email = rs.getString("Email");
                    String categoryID = rs.getString("CategoryID");
                    String newContent = rs.getString("NewContent");
                    String note = rs.getString("Note");
                    int views = rs.getInt("Views");
                    PostDTO post = new PostDTO(id, email, statusPost, createdAt, Style.convertTagToArrayList(tags),
                            title, content, categoryID, name, avatar, newContent, note, views);
                    return post;
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
        return null;
    }

    public ArrayList<PostDTO> pagingPosts(int index) throws SQLException, ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<PostDTO> list = new ArrayList<>();
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "SELECT [Views], Title, Tag, PostID, EmailPost, DATEPART(HOUR, ApprovedDate) as ApprovedHour, DATEPART(MINUTE, ApprovedDate) as ApprovedMinute"
                        + ", Day(ApprovedDate) AS ApprovedDay, MONTH(ApprovedDate) AS ApprovedMonth, year(ApprovedDate) AS ApprovedYear"
                        + ", Name, Image FROM tblPosts p LEFT JOIN tblAccounts a"
                        + " ON emailpost = email WHERE StatusPost = 'A'"
                        + " ORDER BY ApprovedDate DESC OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, (index - 1) * 10);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String postID = rs.getString("PostID");
                    String emailPost = rs.getString("EmailPost");
                    //day:monnth:year  hour:minute
                    String approvedDate = Style.convertToDateFormat(rs.getInt("ApprovedDay"), rs.getInt("ApprovedMonth"),
                            rs.getInt("ApprovedYear"), rs.getInt("ApprovedHour"), rs.getInt("ApprovedMinute"));
                    String tag = rs.getString("Tag");
                    String title = rs.getString("Title");
                    String namePoster = rs.getString("Name");
                    String avatar = rs.getString("Image");
                    int likes = getLikeCounting(postID);
                    int comments = getCommentCounting(postID);
                    ArrayList<Integer> awards = getAwardsByEmail(emailPost);
                    int views = rs.getInt("Views");
                    PostDTO post = new PostDTO(postID, emailPost, Style.convertTagToArrayList(tag), title, approvedDate,
                            namePoster, avatar, likes, comments, awards, views);
                    list.add(post);
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

    public int getTotalPost() throws SQLException, ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(PostID) as Total FROM tblPosts WHERE StatusPost = 'A'";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("Total");
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
        return 0;
    }

    public boolean setNewStatusPost(String postID, String emailMentor, String newStatus) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "UPDATE tblposts SET StatusPost = ?, EmailApprover = ?, ApprovedDate = GETDATE()"
                        + " WHERE PostID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, newStatus);
                stm.setString(2, emailMentor);
                stm.setString(3, postID);

                check = stm.executeUpdate() > 0;
            }
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

    public boolean deletePost(String postId, String delReason) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "UPDATE tblPosts SET StatusPost = 'WFD', Note = ? WHERE PostID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setNString(1, delReason);
                stm.setString(2, postId);
                check = stm.executeUpdate() > 0;
            }
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

    public boolean adminDeletePost(String postId) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "UPDATE tblPosts SET StatusPost = 'D' WHERE PostID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, postId);

                check = stm.executeUpdate() > 0;
            }
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

    public boolean checkOwnerPost(String email, String postId) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "SELECT PostID FROM tblPosts WHERE PostID = ? AND EmailPost = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, postId);
                stm.setString(2, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public PostDTO loadOldContent(String postId, String email) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        PostDTO post = null;

        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "SELECT Title, PostContent FROM tblPosts WHERE PostID = ? AND EmailPost = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, postId);
                stm.setString(2, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String title = rs.getNString("Title");
                    String postContent = rs.getNString("PostContent");
                    post = new PostDTO(postId, email, title, postContent);
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
        return post;
    }

    public boolean checkLike(String postId, String email) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "SELECT * FROM tblLikes WHERE PostID = ? AND EmailLike = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, postId);
                stm.setString(2, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public boolean deleteLike(String postId, String email) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "DELETE FROM tblLikes WHERE PostID = ? AND EmailLike = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, postId);
                stm.setString(2, email);
                stm.executeUpdate();
                check = true;
            }
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

    public boolean insertLike(String postId, String email) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblLikes(ID, PostID, EmailLike, Date) "
                        + "VALUES(NEWID(), ?, ?, GETDATE())";
                stm = conn.prepareStatement(sql);
                stm.setString(1, postId);
                stm.setString(2, email);
                check = stm.executeUpdate() > 0;
            }
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

    public boolean insertNewContentPost(String postID, String newContent) throws NamingException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "UPDATE tblPosts SET NewContent = ?, StatusPost = 'WFU' WHERE PostID = ?";
                stm = conn.prepareStatement(sql);
                stm.setNString(1, newContent);
                stm.setString(2, postID);
                check = stm.executeUpdate() > 0;
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

    public boolean adminUpdatePost(String postID, String newContent) throws NamingException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "UPDATE tblPosts SET PostContent = ? WHERE PostID = ?";
                stm = conn.prepareStatement(sql);
                stm.setNString(1, newContent);
                stm.setString(2, postID);
                check = stm.executeUpdate() > 0;
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

    public boolean approveUpdateContentPost(String postID) throws NamingException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "UPDATE tblPosts SET PostContent = NewContent, StatusPost = 'A', NewContent = NULL WHERE PostID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, postID);
                check = stm.executeUpdate() > 0;
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

    public boolean rejectUpdateContentPost(String postID) throws NamingException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "UPDATE tblPosts SET StatusPost = 'A', NewContent = NULL WHERE PostID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, postID);
                check = stm.executeUpdate() > 0;
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

    public boolean rejectDeletedPost(String postID, String newStatus) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "UPDATE tblposts SET StatusPost = ? ,Note = NULL WHERE PostID = ?";
                //CHỈ CHỈNH LẠI STATUS LÀ A, VÀ GIỮ NGUYÊN EMAIL MENTOR DUYỆT BAN ĐẦU
                //KHÔNG UPDATE APPROVEDDATE & EMAILMENTOR !!!
                stm = conn.prepareStatement(sql);
                stm.setString(1, newStatus);
                stm.setString(2, postID);

                check = stm.executeUpdate() > 0;
            }
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

    public ArrayList<PostDTO> getCommonPosts() throws NamingException, SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<PostDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT [Views], p.PostID, myTable.Total, p.Title, p.Tag, p.ApprovedDate "
                        + "FROM (SELECT PostID, COUNT(DISTINCT EmailLike) as Total FROM tblLikes GROUP BY PostID) myTable "
                        + "LEFT JOIN tblPosts p "
                        + "ON myTable.PostID = p.PostID "
                        + "WHERE p.StatusPost = 'A' "
                        + "ORDER BY myTable.Total DESC";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                int count = 0;
                while (rs.next() && count < 7) {
                    ++count;
                    String postID = rs.getString("PostID");
                    int totalLikes = rs.getInt("Total");
                    String title = rs.getString("Title");
                    int totalComments = getCommentCounting(postID);
                    int views = rs.getInt("Views");
                    PostDTO dto = new PostDTO(postID, title, totalComments, totalLikes, views);
                    list.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public void insertRejectReason(String postId, String reason) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblPosts SET Note = ? WHERE PostID = ? ";
                stm = con.prepareStatement(sql);
                stm.setNString(1, reason);
                stm.setString(2, postId);
                stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public ArrayList<PostDTO> LoadRejectdPosts(String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<PostDTO> list = new ArrayList<>();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT [Views], PostID, Title, Tag, EmailApprover, DATEPART(HOUR, ApprovedDate) as ApprovedHour, DATEPART(MINUTE, ApprovedDate) as ApprovedMinute,"
                        + " Day(ApprovedDate) AS ApprovedDay, MONTH(ApprovedDate) AS ApprovedMonth, YEAR(ApprovedDate) AS ApprovedYear"
                        + " FROM tblPosts"
                        + " WHERE EmailPost = ? AND StatusPost = 'R' ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String postID = rs.getString("PostID");
                    String title = rs.getString("Title");
                    String tags = rs.getString("Tag");
                    String emailApprover = rs.getString("EmailApprover");
                    String approvedDate = Style.convertToDateFormat(rs.getInt("ApprovedDay"), rs.getInt("ApprovedMonth"),
                            rs.getInt("ApprovedYear"), rs.getInt("ApprovedHour"), rs.getInt("ApprovedMinute"));
                    int views = rs.getInt("Views");
                    PostDTO dto = new PostDTO(postID, Style.convertTagToArrayList(tags), title, approvedDate, emailApprover, views);
                    list.add(dto);
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
        return list;
    }

    public void increaseViews(String postID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblPosts SET Views = Views + 1 WHERE PostID = ? ";
                stm = con.prepareStatement(sql);
                stm.setNString(1, postID);
                stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
