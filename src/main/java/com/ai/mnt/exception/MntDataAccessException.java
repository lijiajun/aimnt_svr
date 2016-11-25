package com.ai.mnt.exception;

import org.springframework.dao.DataAccessException;

public class MntDataAccessException extends DataAccessException {

    /**
     * 
     */
    private static final long serialVersionUID = -4363138108496549448L;

    public MntDataAccessException(String msg) {
        super(msg);
    }
    
}
