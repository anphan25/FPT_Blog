package swp.pendingpost;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import swp.library.Style;
import swp.utils.DBHelper;

public class PendingPostDAO implements Serializable {

    public ArrayList<PendingPostDTO> getAllWaitingPost(String postStatus, int categoryManagement) throws NamingException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<PendingPostDTO> dto = new ArrayList<>();
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "SELECT CategoryID, StatusPost, tag, title, postid, emailpost, DATEPART(hour, p.createdDate) as CreatedHour, DATEPART(minute, p.createdDate) as CreatedMinute"
                        + ", DATEPART(day, p.createdDate) as CreatedDay, DATEPART(month, p.createdDate) as CreatedMonth, DATEPART(year, p.createdDate) as CreatedYear"
                        + ", name, image, p.PostID, p.EmailPost "
                        + "FROM tblPosts p LEFT JOIN tblAccounts a "
                        + "ON emailpost = email "
                        + "WHERE p.StatusPost = ? and p.CategoryID = ? "
                        + "ORDER BY p.createdDate desc";
                //sau lày nếu hệ thống chạy chậm hơn con rùa trước nhà t thì hãy chuyển sàng dùng join và on
                //vòng lặp while ở dưới sẽ có chữ continue
                stm = conn.prepareStatement(sql);
                stm.setString(1, postStatus);
                stm.setInt(2, categoryManagement);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String tag = rs.getString("tag");
                    String title = rs.getString("title");
                    String createdAt = Style.convertToDateFormat(rs.getInt("CreatedDay"), rs.getInt("CreatedMonth"),
                            rs.getInt("CreatedYear"), rs.getInt("CreatedHour"), rs.getInt("CreatedMinute"));
                    String name = rs.getString("name");
                    String url = rs.getString("image");
                    String postID = rs.getString("PostID");
                    String EmailPost = rs.getString("EmailPost");
                    String statusPost = rs.getString("StatusPost");
                    int categoryID = rs.getInt("CategoryID");
                    PendingPostDTO dummy = new PendingPostDTO(title, url, createdAt, name, postID, EmailPost, statusPost, Style.convertTagToArrayList(tag),categoryID);
                    boolean checker = dto.add(dummy);
                    if (!checker) {
                        throw new SQLException("oi dit me cuoc doi");
                    }
                }
                return dto;
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
    
    public String getCategoryName(int categoryManagement) throws NamingException, SQLException{
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            conn = DBHelper.makeConnection();
            if(conn != null){
                String sql = "select CategoryName from tblCategories where CategoryID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, categoryManagement);
                rs = stm.executeQuery();
                if(rs.next()){
                    return rs.getString("CategoryName");
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
        return null;
    }

    //private boolean checkResult
}
