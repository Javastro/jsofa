/*
 * $Id$
 * 
 * Created on 1 Feb 2010 by Paul Harrison (paul.harrison@manchester.ac.uk)
 * Copyright 2010 Paul Harrison. All rights reserved.
 *
 * This software is published under the terms of the
 * software license which has been included 
 * with this distribution in the LICENSE.txt file.  
 *
 */ 

package org.jastronomy.jsofa;
/**
 * Exception signalling a internal error.
 *  .
 * @author Paul Harrison (paul.harrison@manchester.ac.uk) 21 Nov 2011
 * @version $Revision$ $date$
 */
public class JSOFAInternalError extends JSOFAException {

    /** Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6067803703985219165L;

    public JSOFAInternalError(String message, int status) {
        super(message, status);
    }

}


/*
 * $Log$
 */
