
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


public class PendingPostDAO implements Serializable
{
    public ArrayList<PendingPostDTO> getAllWaitingPost() throws NamingException, SQLException
    {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<PendingPostDTO> dto = new ArrayList<>();
        try 
        {
            conn = DBHelper.makeConnection();
            if (conn != null) 
            {
                String sql = "SELECT StatusPost, tag, title, postid, emailpost "
                        + ", Day(p.createdDate) as createdDay, month(p.createdDate) as createdMonth, year(p.createdDate) as createdYear"
                        + ", a.name, a.image, p.PostID, p.EmailPost "
                        + "FROM tblPosts p, tblAccounts a "
                        + "WHERE p.emailpost = a.email and p.StatusPost in ('WFA','WFD','WFU') "
                        + "ORDER BY p.createdDate desc";
                //sau lày nếu hệ thống chạy chậm hơn con rùa trước nhà t thì hãy chuyển sàng dùng join và on
                //vòng lặp while ở dưới sẽ có chữ continue
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) 
                {
                    String tag = rs.getString("tag");
                    String title = rs.getString("title");
                    String createDate = rs.getString("createdDay") + "-" + rs.getString("createdMonth") + "-" + rs.getString("createdYear");
                    String name = rs.getString("name");
                    String url = rs.getString("image");
                    String postID = rs.getString("PostID");
                    String EmailPost = rs.getString("EmailPost");
                    String statusPost = rs.getString("StatusPost");
                    PendingPostDTO dummy = new PendingPostDTO(title, url, createDate, name, postID, EmailPost, statusPost, Style.convertTagToArrayList(tag));
                    boolean checker = dto.add(dummy);
                    if(!checker)
                    {
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
    
    //private boolean checkResult
}
