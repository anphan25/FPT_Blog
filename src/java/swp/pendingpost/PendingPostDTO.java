/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namth.PendingPost;

/**
 *
 * @author ADMIN
 */
public class PendingPostDTO
{
    String title, tag, imageURL, dateCreated, name;

    public PendingPostDTO(String title, String tag, String imageURL, String dateCreated, String name)
    {
        this.title = title;
        this.tag = tag;
        this.imageURL = imageURL;
        this.dateCreated = dateCreated;
        this.name = name;
    }
    
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTag()
    {
        return tag;
    }

    public void setTag(String tag)
    {
        this.tag = tag;
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
    
}
