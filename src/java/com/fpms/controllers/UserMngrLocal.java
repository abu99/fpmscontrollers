/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpms.controllers;

import com.fpms.controllers.Exceptions.ChangePasswordException;
import com.fpms.persistence.entities.User;
import javax.ejb.Local;
/**
 *
 * @author aabello
 */
@Local
public interface UserMngrLocal {
    
    void changePassword(String userId, String newPassword, 
            String comfirmPassword) throws ChangePasswordException;
    
    User getUser(String userId);
    
}
