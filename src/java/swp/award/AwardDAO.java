/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.award;

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
public class AwardDAO implements Serializable {

    public ArrayList<AwardDTO> getAwardStandard() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<AwardDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT AwardID, AwardName, Standard FROM tblAwards";
                stm = con.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("AwardID");
                    String name = rs.getString("AwardName");
                    int standard = rs.getInt("Standard");

                    AwardDTO dto = new AwardDTO(id, name, standard);
                    list.add(dto);
                }

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public void adjustAwardStandard(int newStandard, int id) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblAwards SET Standard = ? WHERE AwardID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, newStandard);
                stm.setInt(2, id);
                stm.executeUpdate();

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

}
