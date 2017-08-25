/*
 * 12/08/2012
 */

package com.fpms.controllers.Exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author faizbash
 */
@ApplicationException( rollback=false)
public class ChangePasswordException extends Exception {

    public ChangePasswordException(String msg) {
        super(msg);
    }

    public ChangePasswordException() {
        super();
    }

}
