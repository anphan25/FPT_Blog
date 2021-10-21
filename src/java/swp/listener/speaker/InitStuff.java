package swp.listener.speaker;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.NamingException;
import swp.utils.DBHelper;

/*** THIS WRITED BY SAPHAREONG BEDE ***/ //canh sat chínhh tả đây, mày đã bị bắt

public class InitStuff implements Serializable
{
    private final String AWARDS_TRIGGER_CHECKING = "SELECT 12 FROM sys.triggers WHERE Name = 'AwardTrigger'";
    private final String AWARDS_TRIGGER = "CREATE TRIGGER AwardTrigger ON tblLikes "
            + "AFTER INSERT AS "
            + "BEGIN "
                + "DECLARE @postid uniqueidentifier "
                + "SELECT @postid = inserted.PostID "
                + "FROM inserted "
                + "DECLARE @totallike int "
                + "SELECT @totallike = COUNT(PostID) "
                + "FROM tblLikes "
                + "WHERE PostID = @postid "
                + "IF @totallike >= 5 "   //here is the standard
                + "BEGIN "
                    + "DECLARE @email varchar(50) "
                    + "SELECT @email = EmailPost "
                    + "FROM tblPosts WHERE PostID = @postid "
                    + "IF NOT EXISTS (SELECT AwardID FROM tblAwardDetails WHERE EmailStudent = @email AND AwardID = 2) "
                    + "BEGIN "
                        + "INSERT INTO tblAwardDetails(AwardDetailID, AwardID, EmailStudent, Date) " // here is the reward.
                        + "VALUES(NEWID(), 2, @email, GETDATE()) "
                    + "END "
                + "END "
            + "END";
    private final String GET_ALL_POST_THAT_NOT_MAKE_SENSE = 
            "SELECT po.EmailPost, COUNT(li.PostID) AS totallike, ad.AwardID "
          + "FROM tblPosts po LEFT JOIN tblLikes li ON po.PostID = li.PostID "
          + "LEFT JOIN tblAwardDetails ad ON po.EmailPost = ad.EmailStudent "
          + "GROUP BY po.EmailPost, ad.AwardID "
          + "HAVING ad.AwardID IS NULL AND COUNT(li.PostID) >= 5";
    public void loadAwardTrigger() throws NamingException, SQLException
    {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try 
        {
            con = DBHelper.makeConnection();
            stm = con.createStatement();
            String sql = AWARDS_TRIGGER_CHECKING;
            rs = stm.executeQuery(sql);
            if(!rs.next()) //trigger does not exist
            {
                sql = AWARDS_TRIGGER;
                stm.execute(sql);
            }
        }
        finally 
        {
            if (rs != null) 
            {
                rs.close();
            }
            if (stm != null) 
            {
                stm.close();
            }
            if (con != null) 
            {
                con.close();
            }
        }
    }
    
    public int getNumberOfRowForRickRoll() throws NamingException, SQLException
    {
        int numbereffect = 0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try 
        {
            con = DBHelper.makeConnection();
            ArrayList<AwardDTO> Dishonor_List = getDishonorList();
            for(AwardDTO dto : Dishonor_List)
            {
                String sql = "INSERT INTO tblAwardDetails(AwardDetailID, AwardID, EmailStudent, Date) " +
                            "VALUES(NEWID(), 2, ?, GETDATE())";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getEmail());
                //stm.executeUpdate();
                numbereffect += 1;
            }
        }
        finally 
        {
            if (rs != null) 
            {
                rs.close();
            }
            if (stm != null) 
            {
                stm.close();
            }
            if (con != null) 
            {
                con.close();
            }
        }
        return numbereffect;
    }
    
    private ArrayList<AwardDTO> getDishonorList() throws NamingException, SQLException
    {
        ArrayList<AwardDTO> horny_jail = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try 
        {
            con = DBHelper.makeConnection();
            String sql = GET_ALL_POST_THAT_NOT_MAKE_SENSE;
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next())
            {
                String email = rs.getString(1);
                int likes = rs.getInt(2);
                int awardNOTs = rs.getInt(3);
                horny_jail.add(new AwardDTO(email, awardNOTs, likes));
            }
            return horny_jail;
        }
        finally 
        {
            if (rs != null) 
            {
                rs.close();
            }
            if (stm != null) 
            {
                stm.close();
            }
            if (con != null) 
            {
                con.close();
            }
        }
        //return null;
    }
    
}

class AwardDTO implements Serializable
{
    private String email;
    private int AwardID, totallike;

    public AwardDTO(String email, int AwardID, int totallike)
    {
        this.email = email;
        this.AwardID = AwardID;
        this.totallike = totallike;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getAwardID()
    {
        return AwardID;
    }

    public void setAwardID(int AwardID)
    {
        this.AwardID = AwardID;
    }

    public int getTotallike()
    {
        return totallike;
    }

    public void setTotallike(int totallike)
    {
        this.totallike = totallike;
    }
    
}