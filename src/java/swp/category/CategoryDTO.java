/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.category;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class CategoryDTO implements Serializable{
    private String ID;
    private String name;

    public CategoryDTO(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public CategoryDTO() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
