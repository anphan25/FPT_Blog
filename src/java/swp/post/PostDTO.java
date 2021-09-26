package swp.post;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostDTO implements Serializable {

    private String ID;
    private String emailPost;
    private String emailApprover;
    private String statusPost;
    private String createdDate;
    private List<String> tag;
    private String title;
    private String approvedDate;
    private String postContent;
    private String categoryID;
    private String namePost;
    private String avatar;
    private int awardID;
    private int likes;
    private int comments;

    public PostDTO() {
        ID = "";
        namePost = "";
        avatar = "";
        emailPost = "";
        emailApprover = "";
        statusPost = "";
        createdDate = "";
        tag = new ArrayList<String>();
        title = "";
        approvedDate = "";
        postContent = "";
        categoryID = "";
        awardID = 0;
        likes = 0;
        comments = 0;

    }

    public PostDTO(String ID, String emailPost, List<String> tag, String title, String approvedDate, String namePost,
            String avatar, int awardID, int likes, int comments) {
        this.ID = ID;
        this.emailPost = emailPost;
        this.tag = tag;
        this.title = title;
        this.approvedDate = approvedDate;
        this.namePost = namePost;
        this.avatar = avatar;
        this.awardID = awardID;
        this.likes = likes;
        this.comments = comments;
    }

    public PostDTO(String ID, String emailPost, String emailApprover, String statusPost, String createdDate, List<String> tag,
            String title, String approvedDate, String postContent, String categoryID, String namePost, String avatar,
            int awardID, int likes, int comments) {
        this.ID = ID;
        this.emailPost = emailPost;
        this.emailApprover = emailApprover;
        this.statusPost = statusPost;
        this.createdDate = createdDate;
        this.tag = tag;
        this.title = title;
        this.approvedDate = approvedDate;
        this.postContent = postContent;
        this.categoryID = categoryID;
        this.namePost = namePost;
        this.avatar = avatar;
        this.awardID = awardID;
        this.likes = likes;
        this.comments = comments;
    }

    // this contstructor for profileDAO
    public PostDTO(String ID, List<String> tag, String title, String approvedDate, int awardID, int likes, int comments) {
        this.ID = ID;
        this.tag = tag;
        this.title = title;
        this.approvedDate = approvedDate;
        this.awardID = awardID;
        this.likes = likes;
        this.comments = comments;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmailPost() {
        return emailPost;
    }

    public void setEmailPost(String emailPost) {
        this.emailPost = emailPost;
    }

    public String getEmailApprover() {
        return emailApprover;
    }

    public void setEmailApprover(String emailApprover) {
        this.emailApprover = emailApprover;
    }

    public String getStatusPost() {
        return statusPost;
    }

    public void setStatusPost(String statusPost) {
        this.statusPost = statusPost;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getNamePost() {
        return namePost;
    }

    public void setNamePost(String namePost) {
        this.namePost = namePost;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getAwardID() {
        return awardID;
    }

    public void setAwardID(int awardID) {
        this.awardID = awardID;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "PostDTO{" + "ID=" + ID + ", emailPost=" + emailPost + ", emailApprover=" + emailApprover
                + ", statusPost=" + statusPost + ", createdDate=" + createdDate + ", tag=" + tag.toString() + ", title=" + title
                + ", approvedDate=" + approvedDate + ", postContent=" + postContent + ", categoryID=" + categoryID
                + ", namePost=" + namePost + ", avatar=" + avatar + ", awardID=" + awardID + ", likes=" + likes
                + ", comments=" + comments + '}';
    }
}
