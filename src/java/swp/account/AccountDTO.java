package swp.account;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class AccountDTO implements Serializable{
    private String email;
    private String password;
    private String name;
    private boolean gender;
    private String campus;
    private String role;
    private String status;
    private String joinDate;
    private String avatar;

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     * @param password
     * @param name
     * @param gender
     * @param campus
     * @param role
     * @param status
     * @param joinDate
     * @param avatar
     */
    public AccountDTO(String email, String password, String name, boolean gender, String campus, String role,
            String status, String joinDate, String avatar) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.campus = campus;
        this.role = role;
        this.status = status;
        this.joinDate = joinDate;
        this.avatar = avatar;
    }


    public AccountDTO(String email, String name, boolean gender, String campus, String role, String status, String joinDate, String avatar) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.campus = campus;
        this.role = role;
        this.status = status;
        this.joinDate = joinDate;
        this.avatar = avatar;
    }
    
    
    
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the gender
     */
    public boolean isGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(boolean gender) {
        this.gender = gender;
    }

    /**
     * @return the campus
     */
    public String getCampus() {
        return campus;
    }

    /**
     * @param campus the campus to set
     */
    public void setCampus(String campus) {
        this.campus = campus;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the joinDate
     */
    public String getJoinDate() {
        return joinDate;
    }

    /**
     * @param joinDate the joinDate to set
     */
    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
