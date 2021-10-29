/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.award;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class AwardDTO implements Serializable{
    private int awardID;
    private String name;
    private int standard;

    public AwardDTO(int awardID, String name, int standard) {
        this.awardID = awardID;
        this.name = name;
        this.standard = standard;
    }

    public int getAwardID() {
        return awardID;
    }

    public void setAwardID(int awardID) {
        this.awardID = awardID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }
    
    
    
}
