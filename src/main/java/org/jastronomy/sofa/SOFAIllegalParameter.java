/*
 * $Id$
 * 
 * Created on 1 Feb 2010 by Paul Harrison (paul.harrison@manchester.ac.uk)
 * Copyright 2010 Astrogrid. All rights reserved.
 *
 * This software is published under the terms of the Astrogrid 
 * Software License, a copy of which has been included 
 * with this distribution in the LICENSE.txt file.  
 *
 */ 

package org.jastronomy.sofa;

public class SOFAIllegalParameter extends SOFAException {

    /** Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4164536110692125140L;

    public SOFAIllegalParameter(String message, int status) {
        super(message, status);
    }

}


/*
 * $Log$
 */
