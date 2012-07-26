/*
 * $Id$
 * 
 * Created on 28 Jan 2010 by Paul Harrison (paul.harrison@manchester.ac.uk)
 * Copyright 2010 Paul Harrison. All rights reserved.
 *
 * This software is published under the terms of the
 * software license which has been included 
 * with this distribution in the LICENSE.txt file.  
 *
 */ 

package org.jastronomy.jsofa;

/**
 * Base class for JSOFA exceptions.
 *  .
 * @author Paul Harrison (paul.harrison@manchester.ac.uk) 21 Nov 2011
 * @version $Revision$ $date$
 */
public class JSOFAException extends Exception {

    /** Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4100437038026589596L;
    /** Original JSOFA status value;
     */
    private int status;
 
    public int getStatus() {
        return status;
    }

    public JSOFAException(String message, int status) {
        super(message);
        this.status = status;
    }
}

/*
 * $Log$
 */
