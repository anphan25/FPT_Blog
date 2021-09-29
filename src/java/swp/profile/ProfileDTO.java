/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.profile;
/**
 *
 * @author ADMIN
 */
public class ProfileDTO
{
    String name, campus, email, role, imageURL;
    int awards;
    String gender;
    //String statusaccount; fuck this profile package am going to another package

    public ProfileDTO(String name, String campus, String email, String role, String imageURL, int awards, String gender)
    {
        this.name = name;
        this.campus = campus;
        this.email = email;
        this.role = role;
        this.imageURL = imageURL;
        this.awards = awards;
        this.gender = gender;
    }
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCampus()
    {
        return campus;
    }

    public void setCampus(String campus)
    {
        this.campus = campus;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getImageURL()
    {
        return imageURL;
    }

    public void setImageURL(String imageURL)
    {
        this.imageURL = imageURL;
    }

    public int getAwards()
    {
        return awards;
    }

    public void setAwards(int awards)
    {
        this.awards = awards;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    @Override
    public String toString()
    {
        return "ProfileDTO{" + "name=" + name + ", campus=" + campus + ", email=" + email + ", role=" + role + ", imageURL=" + imageURL + ", awards=" + awards + ", gender=" + gender + '}';
    }
    
}
