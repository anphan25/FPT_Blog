package swp.listener;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                + "IF @totallike >= 2 "   //here is the standard
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
    
}
