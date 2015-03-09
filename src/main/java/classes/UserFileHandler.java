/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author Brenton
 */
public class UserFileHandler 
{
    private String filename;
    public UserFileHandler()
    {
        filename = null;
    }
    public UserFileHandler(String filename)
    {
        this.filename = filename;
    }
    
    public List<User> getUsers()
    {
        List<User> users = new ArrayList<User>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(getFilename()));
            
            String line;
            
            while ((line = reader.readLine()) != null) 
            {
                User user = new User();
                user.loadFromFileString(line);
                users.add(user);
            }
            
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return users;
    }
    
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
}
