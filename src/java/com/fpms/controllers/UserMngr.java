/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpms.controllers;

import com.fpms.controllers.Exceptions.ChangePasswordException;
import com.fpms.controllers.util.MD5;
import com.fpms.persistence.entities.User;
import com.fpms.persistence.mngrs.UserEntityMngrLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author aabello
 */
@Stateless
public class UserMngr implements UserMngrLocal{
    
    @EJB
    private UserEntityMngrLocal userEntityMngr;

    @Override
    public void changePassword(String userId, String newPassword, 
    String comfirmPassword) throws ChangePasswordException {
        
        User user = getUser(userId);
        
        if (newPassword.length() < 8 ) {
            throw new ChangePasswordException
                    ("Password must be a minimum of atleast 8 characters");
            
        }
        
        if(!newPassword.equals(comfirmPassword)){
            throw new ChangePasswordException
                    ("confirm password not the same with new password");
        }
        
        String hashpw = MD5.hash(newPassword);
        user.setPassword(hashpw);
        userEntityMngr.update(user);
        
    }

    @Override
    public User getUser(String userId) {
        return userEntityMngr.get(userId);
    }

    
}
