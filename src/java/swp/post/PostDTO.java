/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.post;

/**
 *
 * @author Dell
 */
public class PostDTO {

    public String ID;
    public String emailPost;
    public String emailApprover;
    public String statusPost;
    public String createdDate;
    public String tag;
    public String title;
    public String approvedDate;
    public String postContent;
    public String categoryID;
    public String namePost;
    public String avatar;
    public int awardID;
    public int likes;
    public int comments;

    public PostDTO() {
        ID = "";
        namePost = "";
        avatar = "";
        emailPost = "";
        emailApprover = "";
        statusPost = "";
        createdDate = "";
        tag = "";
        title = "";
        approvedDate = "";
        postContent = "";
        categoryID = "";
        awardID = 0;
        likes = 0;
        comments = 0;

    }
    
    public PostDTO(String ID, String emailPost, String tag, String title, String approvedDate, String namePost, String avatar, int awardID, int likes, int comments) {
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

    public PostDTO(String ID, String emailPost, String emailApprover, String statusPost, String createdDate, String tag, String title, String approvedDate, String postContent, String categoryID, String namePost, String avatar, int awardID, int likes, int comments) {
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

    @Override
    public String toString() {
        return "PostDTO{" + "ID=" + ID + ", emailPost=" + emailPost + ", emailApprover=" + emailApprover + ", statusPost=" + statusPost + ", createdDate=" + createdDate + ", tag=" + tag + ", title=" + title + ", approvedDate=" + approvedDate + ", postContent=" + postContent + ", categoryID=" + categoryID + ", namePost=" + namePost + ", avatar=" + avatar + ", awardID=" + awardID + ", likes=" + likes + ", comments=" + comments + '}';
    }


 
}
