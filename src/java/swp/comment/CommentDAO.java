/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import swp.library.Style;
import swp.utils.DBHelper;

/**
 *
 * @author ASUS
 */
public class CommentDAO {

    public ArrayList<CommentDTO> getAllCommentOfPost(String postId) throws SQLException, ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<CommentDTO> list = new ArrayList<>();
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "SELECT c.ID, c.EmailComment, c.PostID, DATEPART(hour, c.Date) as commentHour, DATEPART(minute, c.Date) as commentMinute, "
                        + "Day(c.Date) as commentDay, month(c.Date) as commentMonth, year(c.Date) as commentYear, "
                        + "c.Comment, a.Image, a.Name FROM tblComments c LEFT JOIN tblAccounts a on EmailComment = Email "
                        + "WHERE postId = ? AND c.StatusComment = 1 ORDER BY c.Date desc";
                stm = conn.prepareStatement(sql);
                stm.setString(1, postId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String commentID = rs.getString("ID");
                    String emailComment = rs.getString("EmailComment");
                    String idPost = rs.getString("PostID");
                    String commentDate = Style.convertToDateFormat(rs.getInt("commentDay"), rs.getInt("commentMonth"),
                            rs.getInt("commentYear"), rs.getInt("commentHour"), rs.getInt("commentMinute"));
                    String content = rs.getNString("Comment");
                    String avatar = rs.getString("Image");
                    String name = rs.getString("Name");
                    ArrayList<Integer> awards = getAwardsByEmail(emailComment);
                    CommentDTO cmt = new CommentDTO(commentID, emailComment, idPost, commentDate, content, avatar, name, awards);
                    list.add(cmt);
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

    public boolean insertComment(String postId, String email, String content) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;

        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblComments(ID, EmailComment, PostID, Date, Comment, StatusComment) "
                        + "VALUES(NEWID(), ?, ?, GETDATE(), ?, 1)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, postId);
                stm.setNString(3, content);
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

    public boolean editComment(String commentID, String content) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;

        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "UPDATE tblComments "
                        + "SET Comment = ? WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setNString(1, content);
                stm.setString(2, commentID);
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

    public CommentDTO loadNewComment(String postId, String email) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CommentDTO dto = null;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "SELECT c.ID, c.EmailComment, c.PostID, DATEPART(HOUR, c.Date) as commentHour, DATEPART(MINUTE, c.Date) as commentMinute, "
                        + "Day(c.Date) as commentDay, MONTH(c.Date) as commentMonth, YEAR(c.Date) as commentYear, "
                        + "c.Comment, a.Image, a.Email, a.Name "
                        + "FROM tblComments c LEFT JOIN tblAccounts a ON EmailComment = Email WHERE Date = "
                        + "(SELECT MAX(date) FROM tblComments) AND PostID = ? AND EmailComment = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, postId);
                stm.setString(2, email);
                rs = stm.executeQuery();

                if (rs.next()) {
                    String commentID = rs.getString("ID");
                    String emailComment = rs.getString("EmailComment");
                    String idPost = rs.getString("PostID");
                    String commentDate = Style.convertToDateFormat(rs.getInt("commentDay"), rs.getInt("commentMonth"),
                            rs.getInt("commentYear"), rs.getInt("commentHour"), rs.getInt("commentMinute"));
                    String content = rs.getNString("Comment");
                    String avatar = rs.getString("Image");
                    String name = rs.getString("Name");
                    dto = new CommentDTO(commentID, emailComment, idPost, commentDate, content, avatar, name);
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
        return dto;
    }

    //chuyen trang thai comment thanh disabled/deleted 
    //can specify comment nao? cua ai? tren post nao?
    public boolean disableComment(String commentID) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;

        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "UPDATE tblComments "
                        + "SET StatusComment = 0 "
                        + "WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, commentID);
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

    public ArrayList<CommentDTO> getAllComments(int index) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<CommentDTO> list = new ArrayList<>();
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "SELECT my.Image, my.Name, my.EmailComment, my.Comment, my.ID, my.PostID, p.Title, "
                        + "DATEPART(DAY, my.Date) as DayComment, DATEPART(MONTH, my.Date) as MonthComment, "
                        + "DATEPART(YEAR, my.Date) as YearComment, DATEPART(HOUR, my.Date) as HourComment, "
                        + "DATEPART(MINUTE, my.Date) as MinuteComment "
                        + "FROM (SELECT a.Image, a.Name, c.EmailComment, c.Comment, c.ID, c.PostID, c.Date "
                        + "FROM tblComments c LEFT JOIN tblAccounts a "
                        + "ON c.EmailComment = a.Email WHERE c.StatusComment = 1) my "
                        + "LEFT JOIN tblPosts p on my.PostID = p.PostID WHERE p.StatusPost = 'A' "
                        + "ORDER BY my.Date DESC OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, (index - 1) * 20);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String commentID = rs.getString("ID");
                    String emailComment = rs.getString("EmailComment");
                    String postID = rs.getString("PostID");
                    String commentDate = Style.convertToDateFormat(rs.getInt("DayComment"), rs.getInt("MonthComment"),
                            rs.getInt("YearComment"), rs.getInt("HourComment"), rs.getInt("MinuteComment"));
                    String content = rs.getNString("Comment");
                    String avatar = rs.getString("Image");
                    String name = rs.getString("Name");
                    String title = rs.getNString("Title");
                    ArrayList<Integer> awards = getAwardsByEmail(emailComment);
                    CommentDTO cmt = new CommentDTO(commentID, emailComment, postID, title, commentDate, content, avatar, name, awards);
                    list.add(cmt);
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

    public int getTotalComments() throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(c.ID) as Total "
                        + "FROM tblComments c "
                        + "LEFT JOIN tblPosts p ON c.PostID = p.PostID "
                        + "WHERE p.StatusPost = 'a' AND c.StatusComment = 1";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("Total");
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
}
