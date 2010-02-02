/**
 * This software is a translation of the official C SOFA software package. {@link http://www.iausofa.org/}. The translation was done 
 * by hand with the intention of retaining the structure of the original software as far as possible.
 * 
 * The translation was made from the 2009-12-31 release of the SOFA Library for ANSI C, as revised on 2010-01-27. Any updates to this software
 * should be made by looking for incremental changes in the original source.
 * 
 * 
 * 
 * Notable Changes
 * <ul>
 * <li> many of the original SOFA routines return multiple values via pointers - the java translation achieves this by returning composite objects </li>
 * <li> introduced simple {@link SOFAException} and children instead of the status return value.</li>
 * <li> the test routines use the JUnit framework.</li>
 * </ul>
 * @since February 2010
 * @author Paul Harrison (paul.harrison@manchester.ac.uk) 1 Feb 2010
 */
package org.iau;
