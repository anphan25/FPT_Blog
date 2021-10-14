/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.comment;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class CommentDTO implements Serializable {

    private String ID;
    private String emailComment;
    private String postID;
    private String date;
    private String content;
    private String avatar;
    private String name;
    private ArrayList<Integer> awards;

    public CommentDTO() {
    }

    public CommentDTO(String ID, String emailComment, String postID, String date, String content, String avatar, String name, ArrayList<Integer> awards) {
        this.ID = ID;
        this.emailComment = emailComment;
        this.postID = postID;
        this.date = date;
        this.content = content;
        this.avatar = avatar;
        this.name = name;
        this.awards = awards;
    }
    public CommentDTO(String ID, String emailComment, String postID, String date, String content, String avatar, String name) {
        this.ID = ID;
        this.emailComment = emailComment;
        this.postID = postID;
        this.date = date;
        this.content = content;
        this.avatar = avatar;
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmailComment() {
        return emailComment;
    }

    public void setEmailComment(String emailComment) {
        this.emailComment = emailComment;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
