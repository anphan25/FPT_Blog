
package swp.userlist;

import java.io.Serializable;

public class UserlistDTO implements Serializable, Comparable<UserlistDTO>
{
    private String email, name, campus, role, gender, statusaccount, major;
    private int categoryID;
    //now noone will touch my package

    //constructor for viewing all role.
    public UserlistDTO(String email, String name, String campus, String role, String gender, String statusaccount)
    {
        this.email = email;
        this.name = name;
        this.campus = campus;
        this.role = role;
        this.gender = gender;
        this.statusaccount = statusaccount;
    }
    
    //constructor for view selected mentor
    public UserlistDTO(String email, String name, String campus, String role, String gender, String statusaccount, String major)
    {
        this.email = email;
        this.name = name;
        this.campus = campus;
        this.role = role;
        this.gender = gender;
        this.statusaccount = statusaccount;
        this.major = major;
    }

    //full list
    public UserlistDTO(String email, String name, String campus, String role, String gender, String statusaccount, String major, int categoryID)
    {
        this.email = email;
        this.name = name;
        this.campus = campus;
        this.role = role;
        this.gender = gender;
        this.statusaccount = statusaccount;
        this.major = major;
        this.categoryID = categoryID;
    }
    
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getStatusaccount()
    {
        return statusaccount;
    }

    public void setStatusaccount(String statusaccount)
    {
        this.statusaccount = statusaccount;
    }

    public String getMajor()
    {
        return major;
    }

    public void setMajor(String major)
    {
        this.major = major;
    }
    
    @Override
    public int compareTo(UserlistDTO obj) {
        // sort student's name by ASC
        return this.getName().compareTo(obj.getName());
    }

    @Override
    public String toString()
    {
        return "UserlistDTO{" + "email=" + email + ", name=" + name + ", campus=" + campus + ", role=" + role + ", gender=" + gender + ", statusaccount=" + statusaccount + '}';
    }

    
}
