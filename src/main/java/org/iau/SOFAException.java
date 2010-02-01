/*
 * $Id$
 * 
 * Created on 28 Jan 2010 by Paul Harrison (paul.harrison@manchester.ac.uk)
 * Copyright 2010 Astrogrid. All rights reserved.
 *
 * This software is published under the terms of the Astrogrid 
 * Software License, a copy of which has been included 
 * with this distribution in the LICENSE.txt file.  
 *
 */ 

package org.iau;

public class SOFAException extends Exception {

    /** Original SOFA status value;
     */
    private int status;
 
    public int getStatus() {
        return status;
    }

    public SOFAException(String message, int status) {
        super(message);
        this.status = status;
    }
}


/*
 * $Log$
 */
