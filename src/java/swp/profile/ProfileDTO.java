/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.profile;

import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class ProfileDTO {

    private String name;
    private String campus;
    private String email;
    private String role;
    private String imageURL;
    private String gender;
    private ArrayList<Integer> awards;

    //String statusaccount; fuck this profile package am going to another package
    public ProfileDTO() {
        name = "";
        campus = "";
        email = "";
        role = "";
        imageURL = "";
        gender = "";
        awards = new ArrayList<Integer>();
    }

    public ProfileDTO(String name, String campus, String email, String role, String imageURL, String gender, ArrayList<Integer> awards) {
        this.name = name;
        this.campus = campus;
        this.email = email;
        this.role = role;
        this.imageURL = imageURL;
        this.gender = gender;
        this.awards = awards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<Integer> getAwards() {
        return awards;
    }

    public void setAwards(ArrayList<Integer> awards) {
        this.awards = awards;
    }

    @Override
    public String toString() {
        return "ProfileDTO{" + "name=" + name + ", campus=" + campus + ", email=" + email + ", role=" + role + ", imageURL=" + imageURL + ", gender=" + gender + ", awards=" + awards + '}';
    }
    
    

}
