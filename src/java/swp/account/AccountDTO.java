package swp.account;

import java.io.Serializable;

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

    public AccountDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    
    

}