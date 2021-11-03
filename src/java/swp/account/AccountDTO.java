package swp.account;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class AccountDTO implements Serializable {

    private String email;
    private String password;
    private String name;
    private boolean gender;
    private String campus;
    private String role;
    private String status;
    private String joinDate;
    private String avatar;
    private int totalPosts;
    private int totalLikes;
    private ArrayList<Integer> awards;
    private int CategoryManagement; //chỉ dành cho mentor

    public AccountDTO() {
    }

    public int getCategoryManagement() {
        return CategoryManagement;
    }

    public void setCategoryManagement(int CategoryManagement) {
        this.CategoryManagement = CategoryManagement;
    }

    public int getTotalPosts() {
        return totalPosts;
    }

    public void setTotalPosts(int totalPosts) {
        this.totalPosts = totalPosts;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }

    public ArrayList<Integer> getAwards() {
        return awards;
    }

    public void setAwards(ArrayList<Integer> awards) {
        this.awards = awards;
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

    public AccountDTO(String email, String name, boolean gender, String campus, String role, String status, String joinDate, String avatar, int cm) {//hàm getUser đã thêm Catetory management để phân biệt mentor quản lí loại nào
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.campus = campus;
        this.role = role;
        this.status = status;
        this.joinDate = joinDate;
        this.avatar = avatar;
        this.CategoryManagement = cm;
    }

    public AccountDTO(String name, boolean gender, String avatarURL, String campus, String email, String password) {
        this.name = name;
        this.gender = gender;
        this.avatar = avatarURL;
        this.campus = campus;
        this.email = email;
        this.password = password;
    }

    //constructor này dùng để cache dữ liệu gmail login
    public AccountDTO(String email, String name, String avatar) {
        this.email = email;
        this.name = name;
        this.avatar = avatar;
    }

    public AccountDTO(String email, String name, String avatar, int likes, ArrayList<Integer> awards, int posts) {
        this.email = email;
        this.name = name;
        this.avatar = avatar;
        this.totalPosts = posts;
        this.totalLikes = likes;
        this.awards = awards;
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

    @Override
    public String toString() {
        return "AccountDTO{" + "email=" + email + ", password=" + password + ", name=" + name + ", gender=" + gender + ", campus=" + campus + ", role=" + role + ", status=" + status + ", joinDate=" + joinDate + ", avatar=" + avatar + ", totalPosts=" + totalPosts + ", totalLikes=" + totalLikes + ", total awards=" + awards.size() + '}';
    }

}
