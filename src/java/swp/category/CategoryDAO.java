/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.category;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import swp.utils.DBHelper;

/**
 *
 * @author ASUS
 */
public class CategoryDAO implements Serializable{
    private ArrayList<CategoryDTO> categoryList;
    public ArrayList<CategoryDTO> getCategoryList(){
        return categoryList;
    }
    
    public void loadCategoryList() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "select CategoryID, CategoryName from tblCategories";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    String id = rs.getString("CategoryID");
                    String name = rs.getString("CategoryName");
                    if(this.categoryList == null){
                        this.categoryList = new ArrayList<>();
                    }
                    CategoryDTO dto = new CategoryDTO(id, name);
                    this.categoryList.add(dto);
                }
            }
        }finally{
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
    }
    
    public void createCategory(String categoryName) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            con = DBHelper.makeConnection();
            if(con!= null){
                String sql = "insert into tblCategories(CategoryName) values(?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, categoryName);
                int row = stm.executeUpdate();
            }
        }finally{
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
    }
}
