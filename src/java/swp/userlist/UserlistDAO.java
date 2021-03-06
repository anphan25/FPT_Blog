/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.userlist;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import swp.account.AccountDTO;
import swp.utils.DBHelper;

/**
 *
 * @author ADMIN
 */
public class UserlistDAO implements Serializable
{
    public ArrayList<UserlistDTO> getUserList() throws NamingException, SQLException
    {
        //this is a stupid line for a lazy man
        ArrayList<UserlistDTO> lazylist = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try
        {
            //cố gắng thông ass DB
            con = DBHelper.makeConnection();
            if(con != null)
            {
                String sql = "SELECT a.Email, a.Name, a.Gender, a.Campus, r.RoleName, sa.StatusName, a.CategoryManagement, c.CategoryName "
                            + "FROM tblAccounts a LEFT JOIN tblRoles r ON a.RoleID = r.RoleID "
                            + "LEFT JOIN tblStatusAccounts sa ON a.StatusAccountID = sa.StatusAccountID "
                            + "LEFT JOIN tblCategories c ON c.CategoryID = a.CategoryManagement"; 
                        //cân nhắc vứt mẹ bảng tblroles và tblstatusAccount đi nếu hệ thống bị chậm trong tương lai
                        //chẳng ai dùng 1 bảng với 1 mối quan hệ duy nhất chỉ để show ra selection. (việc show selection có thể thông qua việc múa javascript
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                //trỏ tới dòng sql đầu tiên
                while(rs.next())
                {
                    String email = rs.getString(1);
                    String name = rs.getString(2);
                    boolean gioitinh = rs.getBoolean(3);
                    String gender;
                    if(gioitinh) gender = "Male";
                    else gender = "Female";
                    String campus = rs.getString(4);
                    String RoleID = rs.getString(5);
                    String statusacc = rs.getString(6);
                    int CatID = rs.getInt(7);
                    String CatName = rs.getString(8);
                    UserlistDTO dummy = new UserlistDTO(email, name, campus, RoleID, gender, statusacc, CatName, CatID);
                    boolean check = lazylist.add(dummy);
                    if(!check)
                    {
                        throw new SQLException("Monday left me broken.");
                    }
                }
                return lazylist;
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
    
    public boolean updateRoleAccount(String email, String roleID) throws NamingException, SQLException
    { //only 2 division
        Connection con = null;
        PreparedStatement stm = null;
        try
        {
            //cố gắng thông ass DB
            con = DBHelper.makeConnection();
            if(con != null)
            {
                String sql = "UPDATE tblAccounts "
                            + "SET RoleID = ?, CategoryManagement = 0 "
                            + "WHERE Email = ?";
                        
                stm = con.prepareStatement(sql);
                stm.setString(1, roleID);
                stm.setString(2, email);
                int row = stm.executeUpdate();
                if(row == 1)
                {
                    return true;
                }//nếu có vấn đề gì về việc row có tận 2 dòng thì holly fuck
            }
        }
        finally
        {
            if(con != null)
            {
                con.close();
            }
            if(stm != null)
            {
                stm.close();
            }
        }
        return false;
    }
    
    public boolean updateRoleMentor(String email, int categoryID) throws NamingException, SQLException
    { //only mentor
        Connection con = null;
        PreparedStatement stm = null;
        try
        {
            //cố gắng thông ass DB
            con = DBHelper.makeConnection();
            if(con != null)
            {
                String sql = "UPDATE tblAccounts "
                            + "SET RoleID = 'M', CategoryManagement = ? "
                            + "WHERE Email = ?";
                        
                stm = con.prepareStatement(sql);
                stm.setInt(1, categoryID);
                stm.setString(2, email);
                int row = stm.executeUpdate();
                if(row == 1)
                {
                    return true;
                }//nếu có vấn đề gì về việc row có tận 2 dòng thì holly fuck
            }
        }
        finally
        {
            if(con != null)
            {
                con.close();
            }
            if(stm != null)
            {
                stm.close();
            }
        }
        return false;
    }
    
    public boolean banAccount(String email, String reason) throws NamingException, SQLException
    {
        Connection con = null;
        PreparedStatement stm = null;
        try
        {
            con = DBHelper.makeConnection();
            if(con != null)
            {
                String sql = "UPDATE tblAccounts "
                            + "SET StatusAccountID = 'B', Note = ? "
                            + "WHERE Email = ?";
                        
                stm = con.prepareStatement(sql);
                stm.setNString(1, reason);
                stm.setString(2, email);
                int row = stm.executeUpdate();
                if(row == 1)
                {
                    return true;
                }
            }
        }
        finally
        {  
            if(con != null)
            {
                con.close();
            }
            if(stm != null)
            {
                stm.close();
            }
        }
        return false;
    }
    
    public boolean unbanAccount(String email) throws NamingException, SQLException
    {
        Connection con = null;
        PreparedStatement stm = null;
        try
        {
            //cố gắng thông ass DB
            con = DBHelper.makeConnection();
            if(con != null)
            {
                String sql = "UPDATE tblAccounts "
                            + "SET StatusAccountID = 'A', Note = NULL "
                            + "WHERE Email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                int row = stm.executeUpdate();
                if(row == 1)
                {
                    return true;
                }//nếu có vấn đề gì về việc row có tận 2 dòng thì holly fuck
            }
        }
        finally
        {  
            if(con != null)
            {
                con.close();
            }
            if(stm != null)
            {
                stm.close();
            }
        }
        return false;
    }
    
    public ArrayList<UserlistDTO> searchAll(String searchtext) throws NamingException, SQLException
    {
        //this is a stupid line for a lazy man
        ArrayList<UserlistDTO> lazylist = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try
        {
            //cố gắng thông ass DB
            con = DBHelper.makeConnection();
            if(con != null)
            {
                String sql = "SELECT a.Email, a.Name, a.Gender, a.Campus, r.RoleName, sa.StatusName "
                            + "FROM tblAccounts a LEFT JOIN tblRoles r ON a.RoleID = r.RoleID "
                            + "LEFT JOIN tblStatusAccounts sa ON a.StatusAccountID = sa.StatusAccountID "
                            + "WHERE a.Email like ? OR a.Name like ?";  //chỉ cần giống tên hoặc email vứt mẹ vô lazylist
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchtext + "%");
                stm.setString(2, "%" + searchtext + "%");
                rs = stm.executeQuery();
                //trỏ tới dòng sql đầu tiên
                while(rs.next())
                {
                    String email = rs.getString(1);
                    String name = rs.getString(2);
                    boolean gioitinh = rs.getBoolean(3);
                    String gender;
                    if(gioitinh) gender = "Male";
                    else gender = "Female";
                    String campus = rs.getString(4);
                    String RoleID = rs.getString(5);
                    String statusacc = rs.getString(6);
                    UserlistDTO dummy = new UserlistDTO(email, name, campus, RoleID, gender, statusacc);
                    boolean check = lazylist.add(dummy);
                    if(!check)
                    {
                        throw new SQLException("Monday left me broken.");
                    }
                }
                return lazylist;
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
    
    public ArrayList<UserlistDTO> searchSpecificEmail(String localpart, String domain) throws NamingException, SQLException
    {
        ArrayList<UserlistDTO> lazylist = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try
        {
            //cố gắng thông ass DB
            con = DBHelper.makeConnection();
            if(con != null)
            {
                String sql = "SELECT a.Email, a.Name, a.Gender, a.Campus, r.RoleName, sa.StatusName "
                            + "FROM tblAccounts a LEFT JOIN tblRoles r ON a.RoleID = r.RoleID "
                            + "LEFT JOIN tblStatusAccounts sa ON a.StatusAccountID = sa.StatusAccountID "
                            + "WHERE a.Email like ?";  //chỉ cần giống tên hoặc email vứt mẹ vô lazylist
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + localpart + "%" + domain + "%");
                rs = stm.executeQuery();
                //trỏ tới dòng sql đầu tiên
                while(rs.next())
                {
                    String email = rs.getString(1);
                    String name = rs.getString(2);
                    boolean gioitinh = rs.getBoolean(3);
                    String gender;
                    if(gioitinh) gender = "Male";
                    else gender = "Female";
                    String campus = rs.getString(4);
                    String RoleID = rs.getString(5);
                    String statusacc = rs.getString(6);
                    UserlistDTO dummy = new UserlistDTO(email, name, campus, RoleID, gender, statusacc);
                    boolean check = lazylist.add(dummy);
                    if(!check)
                    {
                        throw new SQLException("Monday left me broken.");
                    }
                }
                return lazylist;
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
}
