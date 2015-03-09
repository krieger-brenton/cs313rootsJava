/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Brenton
 */
public class Comment {
    private String comment;
    private String user;
    private Date date;
    
    public Comment()
    {
        this.date = new Date();
    }
    
    public Comment(String comment, String user)
    {
        this.comment = comment;
        this.user = user;
        this.date = new Date();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setDate(String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
	String dateInString = date;
        try
        {
            this.date = sdf.parse(dateInString); 
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public String toString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy 'at' HH:mm:ss");
        return comment + "<br/>" + "commented by: " + user + ", on " + sdf.format(date);
    }

    public void getComment(String line) 
    {
        String[] parts = line.split("/endC");
        this.comment = parts[0];
        this.user = parts[1];
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy 'at' HH:mm:ss");
	String dateInString = parts[2];
        
        try
        {
            this.date = sdf.parse(dateInString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }
    
}
