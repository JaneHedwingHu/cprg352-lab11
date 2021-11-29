package services;

import dataaccess.UserDB;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class AccountService {
    
    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);
                
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public void resetPassword(String email, String path, String url) {
        UserDB userDB = new UserDB();
        String uuid = UUID.randomUUID().toString();
        try {
            User user = userDB.get(email);
            user.setUuid(uuid);
            userDB.update(user);
            String to = email;
            String subject = "Please reset your password";
            String template = path + "/emailtemplates/resetpassword.html";
            String link = url + "?uuid=" + uuid;
            
            HashMap<String, String> tags = new HashMap<>();
            tags.put("firstname", user.getFirstName());
            tags.put("lastname", user.getLastName());
            tags.put("link", link);
                
            GmailService.sendMail(to, subject, template, tags);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public boolean changePassword(String uuid, String password) {
        UserDB userDB = new UserDB();
        try {
            User user = userDB.getByUuid(uuid);
            user.setPassword(password);
            user.setUuid(null);
            userDB.update(user);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }
}
