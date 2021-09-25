
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


public class ProfileDAO implements Serializable
{
    public ProfileDTO getProfile(String email) throws NamingException, SQLException
    {
        String status = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try
        {
            //cố gắng thông ass DB
            con = DBHelper.makeConnection();
            if(con != null)
            {
                String sql = "SELECT a.Name, a.Gender, a.Campus, r.RoleName, a.Image "
                            + "FROM tblAccounts a, tblRoles r "
                            + "WHERE a.RoleID = r.RoleID AND a.Email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                //trỏ tới dòng sql đầu tiên
                if(rs.next())
                {
                    String name = rs.getString(1);
                    boolean gioitinh = rs.getBoolean(2);
                    String gender;
                    if(gioitinh) gender = "Male";
                    else gender = "Female";
                    String campus = rs.getString(3);
                    String RoleID = rs.getString(4);
                    String URL = rs.getString(5);
                    int Awards = totalReward(email);
                    ProfileDTO dto = new ProfileDTO(name, campus, email, RoleID, URL, Awards, gender);
                    return dto;
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
        return null;
    }
    
    private int totalReward(String email) throws NamingException, SQLException
    {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try
        {
            //cố gắng thông ass DB
            con = DBHelper.makeConnection();
            if(con != null)
            {
                String sql = "SELECT COUNT(p.PostID) AS TOTAL "
                        +   "FROM tblAccounts a LEFT JOIN tblposts p "
                        +   "ON p.AwardID is not null AND a.Email = p.EmailPost "
                        +   "GROUP BY a.Email HAVING a.Email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                //trỏ tới dòng sql đầu tiên
                if(rs.next())
                {
                    return rs.getInt("TOTAL");
                }
                else
                {
                    //dòng này chỉ throw KHI email truyền vào ko có ở database (email để xem profile)
                    throw new SQLException ("oi dit me co hacker a ??");
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
        return 0; //nếu querry ở trên có thể bị lỗi thì ít ra vẫn chạy nuột (phần này sẽ test sau)
    }
    
    private ArrayList<PostDTO> list = new ArrayList<>();
    
    public ArrayList<PostDTO> getAllPost()
    {
        return list;
    }
    
    public void LoadThemAll(String email) throws NamingException, SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try
        {
            //cố gắng thông ass DB
            con = DBHelper.makeConnection();
            if(con != null)
            {
                String sql = "SELECT tag, title, postid, Day(ApprovedDate) AS ApprovedDay, month(ApprovedDate) AS ApprovedMonth, year(ApprovedDate) AS ApprovedYear, AwardID "
                        +   "FROM tblPosts WHERE EmailPost = ? AND StatusPost = 'A' "
                        +   "ORDER BY ApprovedDate desc";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                //trỏ tới dòng sql đầu tiên
                //GaRBagE Cô lét sần
                PostDAO dao = new PostDAO(); //gọi DAO trong DAO. DAO-ception
                while(rs.next())
                {
                    String postid = rs.getString("postid");
                    String title = rs.getString("title");
                    String tag = rs.getString("tag");
                    String ApproveDate = rs.getString("ApprovedDay") + "/" + rs.getString("ApprovedMonth") + "/" + rs.getString("ApprovedYear");
                    int likes = dao.getLikeCounting(postid);
                    int comments = dao.getCommentCounting(postid);
                    int awards = rs.getInt("AwardID");
                    PostDTO dto = new PostDTO(postid, Style.convertTagToArrayList(tag), title, ApproveDate, awards, likes, comments);
                    boolean check = list.add(dto);
                    if(!check)
                    {
                        throw new SQLException("EY YO WTF");
                    }
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
    }
}
