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
 * Exception reporting some form of convergence failure in an algorithm.
 */
public class JSOFAFailedConvergenceException extends JSOFAException {

    /** Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4417087001300889769L;

    public JSOFAFailedConvergenceException(String message, int status) {
        super(message, status);
    }

}


/*
 * $Log$
 */
