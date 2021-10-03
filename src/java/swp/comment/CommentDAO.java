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
    public ArrayList<CommentDTO> getAllCommentOfPost(String postId) throws SQLException, ClassNotFoundException, NamingException{
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<CommentDTO> list = new ArrayList<>();
        try{
            conn = DBHelper.makeConnection();
            if(conn != null){
                String sql = "select c.ID, c.EmailComment, c.PostID, DATEPART(hour, c.Date) as commentHour, DATEPART(minute, c.Date) as commentMinute, "
                        +"Day(c.Date) as commentDay, month(c.Date) as commentMonth, year(c.Date) as commentYear, "
                        +"c.Comment, a.Image, a.Name from tblComments c left join tblAccounts a on EmailComment = Email "
                        +"where postId = ? AND c.StatusComment = 1 order by c.Date desc";
                stm = conn.prepareStatement(sql);
                stm.setString(1, postId);
                rs = stm.executeQuery();
                while(rs.next()){
                    String commentID = rs.getString("ID");
                    String emailComment = rs.getString("EmailComment");
                    String idPost = rs.getString("PostID");
                    String commentDate = Style.convertToDateFormat(rs.getInt("commentDay"), rs.getInt("commentMonth"),
                            rs.getInt("commentYear"), rs.getInt("commentHour"), rs.getInt("commentMinute"));
                    String content = rs.getString("Comment");
                    String avatar = rs.getString("Image");
                    String name = rs.getString("Name");
                    CommentDTO cmt = new CommentDTO(commentID, emailComment, idPost, commentDate, content, avatar, name);
                    list.add(cmt);
                    
                }
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
        return list;
    }
    
    public boolean insertComment(String postId, String email, String content) throws SQLException, NamingException{
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        
        try{
            conn = DBHelper.makeConnection();
            if(conn != null){
                String sql = "insert into tblComments(ID,EmailComment,PostID,Date,Comment,StatusComment) "
                        +"values(NEWID(), ?, ?, getdate(), ?, 1)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, postId);
                stm.setNString(3, content);
                check = stm.executeUpdate()>0;
            }
        }finally{
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
//    public CommentDTO loadNewComment(String postId, String email) throws SQLException, NamingException{
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        
//        try{
//           conn = DBHelper.makeConnection();
//           if(conn != null){
//               String sql = "select * from tblComments where ";
//           }
//        }finally{
//            
//        }
//    }
}
