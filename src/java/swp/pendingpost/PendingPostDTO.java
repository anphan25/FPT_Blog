/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.pendingpost;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public class PendingPostDTO
{
    String title, imageURL, dateCreated, name, postID, emailPost, statusPost;
        private List<String> tag;

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public PendingPostDTO(String title, String imageURL, String dateCreated, String name, String postID, String emailPost, String statusPost, List<String> tag) {
        this.title = title;
        this.imageURL = imageURL;
        this.dateCreated = dateCreated;
        this.name = name;
        this.postID = postID;
        this.emailPost = emailPost;
        this.statusPost = statusPost;
        this.tag = tag;
    }



    public String getStatusPost() {
        return statusPost;
    }

    public void setStatusPost(String statusPost) {
        this.statusPost = statusPost;
    }

    
    
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }


    public String getImageURL()
    {
        return imageURL;
    }

    public void setImageURL(String imageURL)
    {
        this.imageURL = imageURL;
    }

    public String getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPostID()
    {
        return postID;
    }

    public void setPostID(String postID)
    {
        this.postID = postID;
    }

    public String getEmailPost()
    {
        return emailPost;
    }

    public void setEmailPost(String emailPost)
    {
        this.emailPost = emailPost;
    }

    

}
