package swp.listener.speaker;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.NamingException;
import swp.utils.DBHelper;

/*** CAN YOU FEEL MY HEART ***/
public class Apocalypse implements Serializable
{
    private final String BURN_TRIGGER = "IF OBJECT_ID ('AwardTrigger', 'TR') IS NOT NULL "
                                            + "DROP TRIGGER AwardTrigger; ";
    public void deleteTrigger() throws NamingException, SQLException
    {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try 
        {
            con = DBHelper.makeConnection();
            stm = con.createStatement();
            String sql = BURN_TRIGGER;
            stm.execute(sql);
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
