/*
 * $Id$
 * 
 * Created on 26 Jan 2010 by Paul Harrison (paul.harrison@manchester.ac.uk)
 * Copyright 2010 Astrogrid. All rights reserved.
 *
 * This software is published under the terms of the Astrogrid 
 * Software License, a copy of which has been included 
 * with this distribution in the LICENSE.txt file.  
 *
 */ 

package org.iau;


import org.iau.SOFA.JulianDate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.iau.SOFA.*;
import static java.lang.Math.*;

public class SOFATest {

    private static boolean verbose;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }


    /*
    **  - - - - - - - - -
    **   t _ s o f a _ c
    **  - - - - - - - - -
    **
    **  Validate the SOFA C functions.
    **
    **  Each SOFA function is tested to some useful but not exhaustive
    **  level.  Successful completion is signalled by an absence of
    **  output messages.  Failure of a given function or group of functions
    **  results in error messages.
    **
    **  All messages go to stdout.
    **
    **  This revision:  2010 January 18
    **
    **  SOFA release 2009-12-31
    **
    **  Copyright (C) 2009 IAU SOFA Review Board.  See notes at end.
    */

    private void viv(int ival, int ivalok, String func, String test)
    /*
    **  - - - -
    **   v i v
    **  - - - -
    **
    **  Validate an integer result.
    **
    **  Internal function used by t_sofa_c program.
    **
    **  Given:
    **     ival     int          value computed by function under test
    **     ivalok   int          correct value
    **     func     char[]       name of function under test
    **     test     char[]       name of individual test
    **
    **  Given and returned:
    **     status   int          set to FALSE if test fails
    **
    **  This revision:  2009 November 4
    */
    {
       if (ival != ivalok) {
          System.err.printf("%s failed: %s want %d got %d\n",
                 func, test, ivalok, ival);
       } else if (verbose) {
          System.out.printf("%s passed: %s want %d got %d\n",
                        func, test, ivalok, ival);
       }
       return;
    }

    private void vvd(double val, double valok, double dval,
                    String func, String test)
    /*
    **  - - - -
    **   v v d
    **  - - - -
    **
    **  Validate a double result.
    **
    **  Internal function used by t_sofa_c program.
    **
    **  Given:
    **     val      double       value computed by function under test
    **     valok    double       expected value
    **     dval     double       maximum allowable error
    **     func     char[]       name of function under test
    **     test     char[]       name of individual test
    **
    **  Given and returned:
    **     status   int          set to FALSE if test fails
    **
    **  This revision:  2008 June 8
    */
    {
       double a, f;   /* absolute and fractional error */


       a = val - valok;
       if (abs(a) > dval) {
          f = abs(valok / a);
          System.err.printf("%s failed: %s want %.20g got %.20g (1/%.3g)\n",
                 func, test, valok, val, f);
       } else if (verbose) {
          System.out.printf("%s passed: %s want %.20g got %.20g\n",
                 func, test, valok, val);
       }
       return;
    }

    @Test
    public void t_a2af()
    /*
    **  - - - - - - -
    **   t _ a 2 a f
    **  - - - - - - -
    **
    **  Test iauA2af function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauA2af, viv
    **
    **  This revision:  2008 November 30
    */
    {
       int idmsf[] = new int[4];
       char s;


       s = iauA2af(4, 2.345, idmsf);

       viv(s, '+', "iauA2af", "s");

       viv(idmsf[0],  134, "iauA2af", "0");
       viv(idmsf[1],   21, "iauA2af", "1");
       viv(idmsf[2],   30, "iauA2af", "2");
       viv(idmsf[3], 9706, "iauA2af", "3");

    }

    @Test
    public void t_a2tf()
    /*
    **  - - - - - - -
    **   t _ a 2 t f
    **  - - - - - - -
    **
    **  Test iauA2tf function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauA2tf, viv
    **
    **  This revision:  2008 November 30
    */
    {
       int ihmsf[] = new int[4];
       char s;


       s = iauA2tf(4, -3.01234, ihmsf);

       viv((int)s, '-', "iauA2tf", "s");

       viv(ihmsf[0],   11, "iauA2tf", "0");
       viv(ihmsf[1],   30, "iauA2tf", "1");
       viv(ihmsf[2],   22, "iauA2tf", "2");
       viv(ihmsf[3], 6484, "iauA2tf", "3");

    }

    @Test
    public void t_anp()
    /*
    **  - - - - - -
    **   t _ a n p
    **  - - - - - -
    **
    **  Test iauAnp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauAnp, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       vvd(iauAnp(-0.1), 6.183185307179586477, 1e-12, "iauAnp", "");
    }

    @Test
    public void t_anpm()
    /*
    **  - - - - - - -
    **   t _ a n p m
    **  - - - - - - -
    **
    **  Test iauAnpm function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauAnpm, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       vvd(iauAnpm(-4.0), 2.283185307179586477, 1e-12, "iauAnpm", "");
    }

    @Test
    public void t_bi00()
    /*
    **  - - - - - - -
    **   t _ b i 0 0
    **  - - - - - - -
    **
    **  Test iauBi00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauBi00, vvd
    **
    **  This revision:  2009 November 4
    */
    {
       double dpsibi, depsbi, dra;

       iauBi00(dpsibi, depsbi, dra);

       vvd(dpsibi, -0.2025309152835086613e-6, 1e-12,
          "iauBi00", "dpsibi");
       vvd(depsbi, -0.3306041454222147847e-7, 1e-12,
          "iauBi00", "depsbi");
       vvd(dra, -0.7078279744199225506e-7, 1e-12,
          "iauBi00", "dra");
    }

    @Test
    public void t_bp00()
    /*
    **  - - - - - - -
    **   t _ b p 0 0
    **  - - - - - - -
    **
    **  Test iauBp00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauBp00, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rb[][] = new double[3][3], rp[][] = new double[3][3], rbp[][] = new double[3][3];


       iauBp00(2400000.5, 50123.9999, rb, rp, rbp);

       vvd(rb[0][0], 0.9999999999999942498, 1e-12,
           "iauBp00", "rb11");
       vvd(rb[0][1], -0.7078279744199196626e-7, 1e-16,
           "iauBp00", "rb12");
       vvd(rb[0][2], 0.8056217146976134152e-7, 1e-16,
           "iauBp00", "rb13");
       vvd(rb[1][0], 0.7078279477857337206e-7, 1e-16,
           "iauBp00", "rb21");
       vvd(rb[1][1], 0.9999999999999969484, 1e-12,
           "iauBp00", "rb22");
       vvd(rb[1][2], 0.3306041454222136517e-7, 1e-16,
           "iauBp00", "rb23");
       vvd(rb[2][0], -0.8056217380986972157e-7, 1e-16,
           "iauBp00", "rb31");
       vvd(rb[2][1], -0.3306040883980552500e-7, 1e-16,
           "iauBp00", "rb32");
       vvd(rb[2][2], 0.9999999999999962084, 1e-12,
           "iauBp00", "rb33");

       vvd(rp[0][0], 0.9999995504864048241, 1e-12,
           "iauBp00", "rp11");
       vvd(rp[0][1], 0.8696113836207084411e-3, 1e-14,
           "iauBp00", "rp12");
       vvd(rp[0][2], 0.3778928813389333402e-3, 1e-14,
           "iauBp00", "rp13");
       vvd(rp[1][0], -0.8696113818227265968e-3, 1e-14,
           "iauBp00", "rp21");
       vvd(rp[1][1], 0.9999996218879365258, 1e-12,
           "iauBp00", "rp22");
       vvd(rp[1][2], -0.1690679263009242066e-6, 1e-14,
           "iauBp00", "rp23");
       vvd(rp[2][0], -0.3778928854764695214e-3, 1e-14,
           "iauBp00", "rp31");
       vvd(rp[2][1], -0.1595521004195286491e-6, 1e-14,
           "iauBp00", "rp32");
       vvd(rp[2][2], 0.9999999285984682756, 1e-12,
           "iauBp00", "rp33");

       vvd(rbp[0][0], 0.9999995505175087260, 1e-12,
           "iauBp00", "rbp11");
       vvd(rbp[0][1], 0.8695405883617884705e-3, 1e-14,
           "iauBp00", "rbp12");
       vvd(rbp[0][2], 0.3779734722239007105e-3, 1e-14,
           "iauBp00", "rbp13");
       vvd(rbp[1][0], -0.8695405990410863719e-3, 1e-14,
           "iauBp00", "rbp21");
       vvd(rbp[1][1], 0.9999996219494925900, 1e-12,
           "iauBp00", "rbp22");
       vvd(rbp[1][2], -0.1360775820404982209e-6, 1e-14,
           "iauBp00", "rbp23");
       vvd(rbp[2][0], -0.3779734476558184991e-3, 1e-14,
           "iauBp00", "rbp31");
       vvd(rbp[2][1], -0.1925857585832024058e-6, 1e-14,
           "iauBp00", "rbp32");
       vvd(rbp[2][2], 0.9999999285680153377, 1e-12,
           "iauBp00", "rbp33");
    }

    @Test
    public void t_bp06()
    /*
    **  - - - - - - -
    **   t _ b p 0 6
    **  - - - - - - -
    **
    **  Test iauBp06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauBp06, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rb[][] = new double[3][3], rp[][] = new double[3][3], rbp[][] = new double[3][3];


       iauBp06(2400000.5, 50123.9999, rb, rp, rbp);

       vvd(rb[0][0], 0.9999999999999942497, 1e-12,
           "iauBp06", "rb11");
       vvd(rb[0][1], -0.7078368960971557145e-7, 1e-14,
           "iauBp06", "rb12");
       vvd(rb[0][2], 0.8056213977613185606e-7, 1e-14,
           "iauBp06", "rb13");
       vvd(rb[1][0], 0.7078368694637674333e-7, 1e-14,
           "iauBp06", "rb21");
       vvd(rb[1][1], 0.9999999999999969484, 1e-12,
           "iauBp06", "rb22");
       vvd(rb[1][2], 0.3305943742989134124e-7, 1e-14,
           "iauBp06", "rb23");
       vvd(rb[2][0], -0.8056214211620056792e-7, 1e-14,
           "iauBp06", "rb31");
       vvd(rb[2][1], -0.3305943172740586950e-7, 1e-14,
           "iauBp06", "rb32");
       vvd(rb[2][2], 0.9999999999999962084, 1e-12,
           "iauBp06", "rb33");

       vvd(rp[0][0], 0.9999995504864960278, 1e-12,
           "iauBp06", "rp11");
       vvd(rp[0][1], 0.8696112578855404832e-3, 1e-14,
           "iauBp06", "rp12");
       vvd(rp[0][2], 0.3778929293341390127e-3, 1e-14,
           "iauBp06", "rp13");
       vvd(rp[1][0], -0.8696112560510186244e-3, 1e-14,
           "iauBp06", "rp21");
       vvd(rp[1][1], 0.9999996218880458820, 1e-12,
           "iauBp06", "rp22");
       vvd(rp[1][2], -0.1691646168941896285e-6, 1e-14,
           "iauBp06", "rp23");
       vvd(rp[2][0], -0.3778929335557603418e-3, 1e-14,
           "iauBp06", "rp31");
       vvd(rp[2][1], -0.1594554040786495076e-6, 1e-14,
           "iauBp06", "rp32");
       vvd(rp[2][2], 0.9999999285984501222, 1e-12,
           "iauBp06", "rp33");

       vvd(rbp[0][0], 0.9999995505176007047, 1e-12,
           "iauBp06", "rbp11");
       vvd(rbp[0][1], 0.8695404617348208406e-3, 1e-14,
           "iauBp06", "rbp12");
       vvd(rbp[0][2], 0.3779735201865589104e-3, 1e-14,
           "iauBp06", "rbp13");
       vvd(rbp[1][0], -0.8695404723772031414e-3, 1e-14,
           "iauBp06", "rbp21");
       vvd(rbp[1][1], 0.9999996219496027161, 1e-12,
           "iauBp06", "rbp22");
       vvd(rbp[1][2], -0.1361752497080270143e-6, 1e-14,
           "iauBp06", "rbp23");
       vvd(rbp[2][0], -0.3779734957034089490e-3, 1e-14,
           "iauBp06", "rbp31");
       vvd(rbp[2][1], -0.1924880847894457113e-6, 1e-14,
           "iauBp06", "rbp32");
       vvd(rbp[2][2], 0.9999999285679971958, 1e-12,
           "iauBp06", "rbp33");
    }

    @Test
    public void t_bpn2xy()
    /*
    **  - - - - - - - - -
    **   t _ b p n 2 x y
    **  - - - - - - - - -
    **
    **  Test iauBpn2xy function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauBpn2xy, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double rbpn[][] = new double[3][3], x, y;


       rbpn[0][0] =  9.999962358680738e-1;
       rbpn[0][1] = -2.516417057665452e-3;
       rbpn[0][2] = -1.093569785342370e-3;

       rbpn[1][0] =  2.516462370370876e-3;
       rbpn[1][1] =  9.999968329010883e-1;
       rbpn[1][2] =  4.006159587358310e-5;

       rbpn[2][0] =  1.093465510215479e-3;
       rbpn[2][1] = -4.281337229063151e-5;
       rbpn[2][2] =  9.999994012499173e-1;

       iauBpn2xy(rbpn, x, y);

       vvd(x,  1.093465510215479e-3, 1e-12, "iauBpn2xy", "x");
       vvd(y, -4.281337229063151e-5, 1e-12, "iauBpn2xy", "y");

    }

    @Test
    public void t_c2i00a()
    /*
    **  - - - - - - - - -
    **   t _ c 2 i 0 0 a
    **  - - - - - - - - -
    **
    **  Test iauC2i00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2i00a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rc2i[][] = new double[3][3];


       iauC2i00a(2400000.5, 53736.0, rc2i);

       vvd(rc2i[0][0], 0.9999998323037165557, 1e-12,
           "iauC2i00a", "11");
       vvd(rc2i[0][1], 0.5581526348992140183e-9, 1e-12,
           "iauC2i00a", "12");
       vvd(rc2i[0][2], -0.5791308477073443415e-3, 1e-12,
           "iauC2i00a", "13");

       vvd(rc2i[1][0], -0.2384266227870752452e-7, 1e-12,
           "iauC2i00a", "21");
       vvd(rc2i[1][1], 0.9999999991917405258, 1e-12,
           "iauC2i00a", "22");
       vvd(rc2i[1][2], -0.4020594955028209745e-4, 1e-12,
           "iauC2i00a", "23");

       vvd(rc2i[2][0], 0.5791308472168152904e-3, 1e-12,
           "iauC2i00a", "31");
       vvd(rc2i[2][1], 0.4020595661591500259e-4, 1e-12,
           "iauC2i00a", "32");
       vvd(rc2i[2][2], 0.9999998314954572304, 1e-12,
           "iauC2i00a", "33");

    }

    @Test
    public void t_c2i00b()
    /*
    **  - - - - - - - - -
    **   t _ c 2 i 0 0 b
    **  - - - - - - - - -
    **
    **  Test iauC2i00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2i00b, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rc2i[][] = new double[3][3];


       iauC2i00b(2400000.5, 53736.0, rc2i);

       vvd(rc2i[0][0], 0.9999998323040954356, 1e-12,
           "iauC2i00b", "11");
       vvd(rc2i[0][1], 0.5581526349131823372e-9, 1e-12,
           "iauC2i00b", "12");
       vvd(rc2i[0][2], -0.5791301934855394005e-3, 1e-12,
           "iauC2i00b", "13");

       vvd(rc2i[1][0], -0.2384239285499175543e-7, 1e-12,
           "iauC2i00b", "21");
       vvd(rc2i[1][1], 0.9999999991917574043, 1e-12,
           "iauC2i00b", "22");
       vvd(rc2i[1][2], -0.4020552974819030066e-4, 1e-12,
           "iauC2i00b", "23");

       vvd(rc2i[2][0], 0.5791301929950208873e-3, 1e-12,
           "iauC2i00b", "31");
       vvd(rc2i[2][1], 0.4020553681373720832e-4, 1e-12,
           "iauC2i00b", "32");
       vvd(rc2i[2][2], 0.9999998314958529887, 1e-12,
           "iauC2i00b", "33");

    }

    @Test
    public void t_c2i06a()
    /*
    **  - - - - - - - - -
    **   t _ c 2 i 0 6 a
    **  - - - - - - - - -
    **
    **  Test iauC2i06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2i06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rc2i[][] = new double[3][3];


       iauC2i06a(2400000.5, 53736.0, rc2i);

       vvd(rc2i[0][0], 0.9999998323037159379, 1e-12,
           "iauC2i06a", "11");
       vvd(rc2i[0][1], 0.5581121329587613787e-9, 1e-12,
           "iauC2i06a", "12");
       vvd(rc2i[0][2], -0.5791308487740529749e-3, 1e-12,
           "iauC2i06a", "13");

       vvd(rc2i[1][0], -0.2384253169452306581e-7, 1e-12,
           "iauC2i06a", "21");
       vvd(rc2i[1][1], 0.9999999991917467827, 1e-12,
           "iauC2i06a", "22");
       vvd(rc2i[1][2], -0.4020579392895682558e-4, 1e-12,
           "iauC2i06a", "23");

       vvd(rc2i[2][0], 0.5791308482835292617e-3, 1e-12,
           "iauC2i06a", "31");
       vvd(rc2i[2][1], 0.4020580099454020310e-4, 1e-12,
           "iauC2i06a", "32");
       vvd(rc2i[2][2], 0.9999998314954628695, 1e-12,
           "iauC2i06a", "33");

    }

    @Test
    public void t_c2ibpn()
    /*
    **  - - - - - - - - -
    **   t _ c 2 i b p n
    **  - - - - - - - - -
    **
    **  Test iauC2ibpn function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2ibpn, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rbpn[][] = new double[3][3], rc2i[][] = new double[3][3];


       rbpn[0][0] =  9.999962358680738e-1;
       rbpn[0][1] = -2.516417057665452e-3;
       rbpn[0][2] = -1.093569785342370e-3;

       rbpn[1][0] =  2.516462370370876e-3;
       rbpn[1][1] =  9.999968329010883e-1;
       rbpn[1][2] =  4.006159587358310e-5;

       rbpn[2][0] =  1.093465510215479e-3;
       rbpn[2][1] = -4.281337229063151e-5;
       rbpn[2][2] =  9.999994012499173e-1;

       iauC2ibpn(2400000.5, 50123.9999, rbpn, rc2i);

       vvd(rc2i[0][0], 0.9999994021664089977, 1e-12,
           "iauC2ibpn", "11");
       vvd(rc2i[0][1], -0.3869195948017503664e-8, 1e-12,
           "iauC2ibpn", "12");
       vvd(rc2i[0][2], -0.1093465511383285076e-2, 1e-12,
           "iauC2ibpn", "13");

       vvd(rc2i[1][0], 0.5068413965715446111e-7, 1e-12,
           "iauC2ibpn", "21");
       vvd(rc2i[1][1], 0.9999999990835075686, 1e-12,
           "iauC2ibpn", "22");
       vvd(rc2i[1][2], 0.4281334246452708915e-4, 1e-12,
           "iauC2ibpn", "23");

       vvd(rc2i[2][0], 0.1093465510215479000e-2, 1e-12,
           "iauC2ibpn", "31");
       vvd(rc2i[2][1], -0.4281337229063151000e-4, 1e-12,
           "iauC2ibpn", "32");
       vvd(rc2i[2][2], 0.9999994012499173103, 1e-12,
           "iauC2ibpn", "33");

    }

    @Test
    public void t_c2ixy()
    /*
    **  - - - - - - - -
    **   t _ c 2 i x y
    **  - - - - - - - -
    **
    **  Test iauC2ixy function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2ixy, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double x, y, rc2i[][] = new double[3][3];


       x = 0.5791308486706011000e-3;
       y = 0.4020579816732961219e-4;

       iauC2ixy(2400000.5, 53736, x, y, rc2i);

       vvd(rc2i[0][0], 0.9999998323037157138, 1e-12,
           "iauC2ixy", "11");
       vvd(rc2i[0][1], 0.5581526349032241205e-9, 1e-12,
           "iauC2ixy", "12");
       vvd(rc2i[0][2], -0.5791308491611263745e-3, 1e-12,
           "iauC2ixy", "13");

       vvd(rc2i[1][0], -0.2384257057469842953e-7, 1e-12,
           "iauC2ixy", "21");
       vvd(rc2i[1][1], 0.9999999991917468964, 1e-12,
           "iauC2ixy", "22");
       vvd(rc2i[1][2], -0.4020579110172324363e-4, 1e-12,
           "iauC2ixy", "23");

       vvd(rc2i[2][0], 0.5791308486706011000e-3, 1e-12,
           "iauC2ixy", "31");
       vvd(rc2i[2][1], 0.4020579816732961219e-4, 1e-12,
           "iauC2ixy", "32");
       vvd(rc2i[2][2], 0.9999998314954627590, 1e-12,
           "iauC2ixy", "33");

    }

    @Test
    public void t_c2ixys()
    /*
    **  - - - - - - - - -
    **   t _ c 2 i x y s
    **  - - - - - - - - -
    **
    **  Test iauC2ixys function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2ixys, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double x, y, s, rc2i[][] = new double[3][3];


       x =  0.5791308486706011000e-3;
       y =  0.4020579816732961219e-4;
       s = -0.1220040848472271978e-7;

       iauC2ixys(x, y, s, rc2i);

       vvd(rc2i[0][0], 0.9999998323037157138, 1e-12,
           "iauC2ixys", "11");
       vvd(rc2i[0][1], 0.5581984869168499149e-9, 1e-12,
           "iauC2ixys", "12");
       vvd(rc2i[0][2], -0.5791308491611282180e-3, 1e-12,
           "iauC2ixys", "13");

       vvd(rc2i[1][0], -0.2384261642670440317e-7, 1e-12,
           "iauC2ixys", "21");
       vvd(rc2i[1][1], 0.9999999991917468964, 1e-12,
           "iauC2ixys", "22");
       vvd(rc2i[1][2], -0.4020579110169668931e-4, 1e-12,
           "iauC2ixys", "23");

       vvd(rc2i[2][0], 0.5791308486706011000e-3, 1e-12,
           "iauC2ixys", "31");
       vvd(rc2i[2][1], 0.4020579816732961219e-4, 1e-12,
           "iauC2ixys", "32");
       vvd(rc2i[2][2], 0.9999998314954627590, 1e-12,
           "iauC2ixys", "33");

    }

    @Test
    public void t_c2s()
    /*
    **  - - - - - -
    **   t _ c 2 s
    **  - - - - - -
    **
    **  Test iauC2s function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2s, vvd
    **
    **  This revision:  2008 May 27
    */
    {
       double p[] = new double[3], theta, phi;


       p[0] = 100.0;
       p[1] = -50.0;
       p[2] =  25.0;

       iauC2s(p, theta, phi);

       vvd(theta, -0.4636476090008061162, 1e-14, "iauC2s", "theta");
       vvd(phi, 0.2199879773954594463, 1e-14, "iauC2s", "phi");

    }

    @Test
    public void t_c2t00a()
    /*
    **  - - - - - - - - -
    **   t _ c 2 t 0 0 a
    **  - - - - - - - - -
    **
    **  Test iauC2t00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2t00a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double tta, ttb, uta, utb, xp, yp, rc2t[][] = new double[3][3];


       tta = 2400000.5;
       uta = 2400000.5;
       ttb = 53736.0;
       utb = 53736.0;
       xp = 2.55060238e-7;
       yp = 1.860359247e-6;

       iauC2t00a(tta, ttb, uta, utb, xp, yp, rc2t);

       vvd(rc2t[0][0], -0.1810332128307182668, 1e-12,
           "iauC2t00a", "11");
       vvd(rc2t[0][1], 0.9834769806938457836, 1e-12,
           "iauC2t00a", "12");
       vvd(rc2t[0][2], 0.6555535638688341725e-4, 1e-12,
           "iauC2t00a", "13");

       vvd(rc2t[1][0], -0.9834768134135984552, 1e-12,
           "iauC2t00a", "21");
       vvd(rc2t[1][1], -0.1810332203649520727, 1e-12,
           "iauC2t00a", "22");
       vvd(rc2t[1][2], 0.5749801116141056317e-3, 1e-12,
           "iauC2t00a", "23");

       vvd(rc2t[2][0], 0.5773474014081406921e-3, 1e-12,
           "iauC2t00a", "31");
       vvd(rc2t[2][1], 0.3961832391770163647e-4, 1e-12,
           "iauC2t00a", "32");
       vvd(rc2t[2][2], 0.9999998325501692289, 1e-12,
           "iauC2t00a", "33");

    }

    @Test
    public void t_c2t00b()
    /*
    **  - - - - - - - - -
    **   t _ c 2 t 0 0 b
    **  - - - - - - - - -
    **
    **  Test iauC2t00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2t00b, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double tta, ttb, uta, utb, xp, yp, rc2t[][] = new double[3][3];


       tta = 2400000.5;
       uta = 2400000.5;
       ttb = 53736.0;
       utb = 53736.0;
       xp = 2.55060238e-7;
       yp = 1.860359247e-6;

       iauC2t00b(tta, ttb, uta, utb, xp, yp, rc2t);

       vvd(rc2t[0][0], -0.1810332128439678965, 1e-12,
           "iauC2t00b", "11");
       vvd(rc2t[0][1], 0.9834769806913872359, 1e-12,
           "iauC2t00b", "12");
       vvd(rc2t[0][2], 0.6555565082458415611e-4, 1e-12,
           "iauC2t00b", "13");

       vvd(rc2t[1][0], -0.9834768134115435923, 1e-12,
           "iauC2t00b", "21");
       vvd(rc2t[1][1], -0.1810332203784001946, 1e-12,
           "iauC2t00b", "22");
       vvd(rc2t[1][2], 0.5749793922030017230e-3, 1e-12,
           "iauC2t00b", "23");

       vvd(rc2t[2][0], 0.5773467471863534901e-3, 1e-12,
           "iauC2t00b", "31");
       vvd(rc2t[2][1], 0.3961790411549945020e-4, 1e-12,
           "iauC2t00b", "32");
       vvd(rc2t[2][2], 0.9999998325505635738, 1e-12,
           "iauC2t00b", "33");

    }

    @Test
    public void t_c2t06a()
    /*
    **  - - - - - - - - -
    **   t _ c 2 t 0 6 a
    **  - - - - - - - - -
    **
    **  Test iauC2t06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2t06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double tta, ttb, uta, utb, xp, yp, rc2t[][] = new double[3][3];


       tta = 2400000.5;
       uta = 2400000.5;
       ttb = 53736.0;
       utb = 53736.0;
       xp = 2.55060238e-7;
       yp = 1.860359247e-6;

       iauC2t06a(tta, ttb, uta, utb, xp, yp, rc2t);

       vvd(rc2t[0][0], -0.1810332128305897282, 1e-12,
           "iauC2t06a", "11");
       vvd(rc2t[0][1], 0.9834769806938592296, 1e-12,
           "iauC2t06a", "12");
       vvd(rc2t[0][2], 0.6555550962998436505e-4, 1e-12,
           "iauC2t06a", "13");

       vvd(rc2t[1][0], -0.9834768134136214897, 1e-12,
           "iauC2t06a", "21");
       vvd(rc2t[1][1], -0.1810332203649130832, 1e-12,
           "iauC2t06a", "22");
       vvd(rc2t[1][2], 0.5749800844905594110e-3, 1e-12,
           "iauC2t06a", "23");

       vvd(rc2t[2][0], 0.5773474024748545878e-3, 1e-12,
           "iauC2t06a", "31");
       vvd(rc2t[2][1], 0.3961816829632690581e-4, 1e-12,
           "iauC2t06a", "32");
       vvd(rc2t[2][2], 0.9999998325501747785, 1e-12,
           "iauC2t06a", "33");

    }

    @Test
    public void t_c2tcio()
    /*
    **  - - - - - - - - -
    **   t _ c 2 t c i o
    **  - - - - - - - - -
    **
    **  Test iauC2tcio function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2tcio, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rc2i[][] = new double[3][3], era, rpom[][] = new double[3][3], rc2t[][] = new double[3][3];


       rc2i[0][0] =  0.9999998323037164738;
       rc2i[0][1] =  0.5581526271714303683e-9;
       rc2i[0][2] = -0.5791308477073443903e-3;

       rc2i[1][0] = -0.2384266227524722273e-7;
       rc2i[1][1] =  0.9999999991917404296;
       rc2i[1][2] = -0.4020594955030704125e-4;

       rc2i[2][0] =  0.5791308472168153320e-3;
       rc2i[2][1] =  0.4020595661593994396e-4;
       rc2i[2][2] =  0.9999998314954572365;

       era = 1.75283325530307;

       rpom[0][0] =  0.9999999999999674705;
       rpom[0][1] = -0.1367174580728847031e-10;
       rpom[0][2] =  0.2550602379999972723e-6;

       rpom[1][0] =  0.1414624947957029721e-10;
       rpom[1][1] =  0.9999999999982694954;
       rpom[1][2] = -0.1860359246998866338e-5;

       rpom[2][0] = -0.2550602379741215275e-6;
       rpom[2][1] =  0.1860359247002413923e-5;
       rpom[2][2] =  0.9999999999982369658;


       iauC2tcio(rc2i, era, rpom, rc2t);

       vvd(rc2t[0][0], -0.1810332128307110439, 1e-12,
           "iauC2tcio", "11");
       vvd(rc2t[0][1], 0.9834769806938470149, 1e-12,
           "iauC2tcio", "12");
       vvd(rc2t[0][2], 0.6555535638685466874e-4, 1e-12,
           "iauC2tcio", "13");

       vvd(rc2t[1][0], -0.9834768134135996657, 1e-12,
           "iauC2tcio", "21");
       vvd(rc2t[1][1], -0.1810332203649448367, 1e-12,
           "iauC2tcio", "22");
       vvd(rc2t[1][2], 0.5749801116141106528e-3, 1e-12,
           "iauC2tcio", "23");

       vvd(rc2t[2][0], 0.5773474014081407076e-3, 1e-12,
           "iauC2tcio", "31");
       vvd(rc2t[2][1], 0.3961832391772658944e-4, 1e-12,
           "iauC2tcio", "32");
       vvd(rc2t[2][2], 0.9999998325501691969, 1e-12,
           "iauC2tcio", "33");

    }

    @Test
    public void t_c2teqx()
    /*
    **  - - - - - - - - -
    **   t _ c 2 t e q x
    **  - - - - - - - - -
    **
    **  Test iauC2teqx function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2teqx, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rbpn[][] = new double[3][3], gst, rpom[][] = new double[3][3], rc2t[][] = new double[3][3];


       rbpn[0][0] =  0.9999989440476103608;
       rbpn[0][1] = -0.1332881761240011518e-2;
       rbpn[0][2] = -0.5790767434730085097e-3;

       rbpn[1][0] =  0.1332858254308954453e-2;
       rbpn[1][1] =  0.9999991109044505944;
       rbpn[1][2] = -0.4097782710401555759e-4;

       rbpn[2][0] =  0.5791308472168153320e-3;
       rbpn[2][1] =  0.4020595661593994396e-4;
       rbpn[2][2] =  0.9999998314954572365;

       gst = 1.754166138040730516;

       rpom[0][0] =  0.9999999999999674705;
       rpom[0][1] = -0.1367174580728847031e-10;
       rpom[0][2] =  0.2550602379999972723e-6;

       rpom[1][0] =  0.1414624947957029721e-10;
       rpom[1][1] =  0.9999999999982694954;
       rpom[1][2] = -0.1860359246998866338e-5;

       rpom[2][0] = -0.2550602379741215275e-6;
       rpom[2][1] =  0.1860359247002413923e-5;
       rpom[2][2] =  0.9999999999982369658;

       iauC2teqx(rbpn, gst, rpom, rc2t);

       vvd(rc2t[0][0], -0.1810332128528685730, 1e-12,
           "iauC2teqx", "11");
       vvd(rc2t[0][1], 0.9834769806897685071, 1e-12,
           "iauC2teqx", "12");
       vvd(rc2t[0][2], 0.6555535639982634449e-4, 1e-12,
           "iauC2teqx", "13");

       vvd(rc2t[1][0], -0.9834768134095211257, 1e-12,
           "iauC2teqx", "21");
       vvd(rc2t[1][1], -0.1810332203871023800, 1e-12,
           "iauC2teqx", "22");
       vvd(rc2t[1][2], 0.5749801116126438962e-3, 1e-12,
           "iauC2teqx", "23");

       vvd(rc2t[2][0], 0.5773474014081539467e-3, 1e-12,
           "iauC2teqx", "31");
       vvd(rc2t[2][1], 0.3961832391768640871e-4, 1e-12,
           "iauC2teqx", "32");
       vvd(rc2t[2][2], 0.9999998325501691969, 1e-12,
           "iauC2teqx", "33");

    }

    @Test
    public void t_c2tpe()
    /*
    **  - - - - - - - -
    **   t _ c 2 t p e
    **  - - - - - - - -
    **
    **  Test iauC2tpe function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2tpe, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double tta, ttb, uta, utb, dpsi, deps, xp, yp, rc2t[][] = new double[3][3];


       tta = 2400000.5;
       uta = 2400000.5;
       ttb = 53736.0;
       utb = 53736.0;
       deps =  0.4090789763356509900;
       dpsi = -0.9630909107115582393e-5;
       xp = 2.55060238e-7;
       yp = 1.860359247e-6;

       iauC2tpe(tta, ttb, uta, utb, dpsi, deps, xp, yp, rc2t);

       vvd(rc2t[0][0], -0.1813677995763029394, 1e-12,
           "iauC2tpe", "11");
       vvd(rc2t[0][1], 0.9023482206891683275, 1e-12,
           "iauC2tpe", "12");
       vvd(rc2t[0][2], -0.3909902938641085751, 1e-12,
           "iauC2tpe", "13");

       vvd(rc2t[1][0], -0.9834147641476804807, 1e-12,
           "iauC2tpe", "21");
       vvd(rc2t[1][1], -0.1659883635434995121, 1e-12,
           "iauC2tpe", "22");
       vvd(rc2t[1][2], 0.7309763898042819705e-1, 1e-12,
           "iauC2tpe", "23");

       vvd(rc2t[2][0], 0.1059685430673215247e-2, 1e-12,
           "iauC2tpe", "31");
       vvd(rc2t[2][1], 0.3977631855605078674, 1e-12,
           "iauC2tpe", "32");
       vvd(rc2t[2][2], 0.9174875068792735362, 1e-12,
           "iauC2tpe", "33");

    }

    @Test
    public void t_c2txy()
    /*
    **  - - - - - - - -
    **   t _ c 2 t x y
    **  - - - - - - - -
    **
    **  Test iauC2txy function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2txy, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double tta, ttb, uta, utb, x, y, xp, yp, rc2t[][] = new double[3][3];


       tta = 2400000.5;
       uta = 2400000.5;
       ttb = 53736.0;
       utb = 53736.0;
       x = 0.5791308486706011000e-3;
       y = 0.4020579816732961219e-4;
       xp = 2.55060238e-7;
       yp = 1.860359247e-6;

       iauC2txy(tta, ttb, uta, utb, x, y, xp, yp, rc2t);

       vvd(rc2t[0][0], -0.1810332128306279253, 1e-12,
           "iauC2txy", "11");
       vvd(rc2t[0][1], 0.9834769806938520084, 1e-12,
           "iauC2txy", "12");
       vvd(rc2t[0][2], 0.6555551248057665829e-4, 1e-12,
           "iauC2txy", "13");

       vvd(rc2t[1][0], -0.9834768134136142314, 1e-12,
           "iauC2txy", "21");
       vvd(rc2t[1][1], -0.1810332203649529312, 1e-12,
           "iauC2txy", "22");
       vvd(rc2t[1][2], 0.5749800843594139912e-3, 1e-12,
           "iauC2txy", "23");

       vvd(rc2t[2][0], 0.5773474028619264494e-3, 1e-12,
           "iauC2txy", "31");
       vvd(rc2t[2][1], 0.3961816546911624260e-4, 1e-12,
           "iauC2txy", "32");
       vvd(rc2t[2][2], 0.9999998325501746670, 1e-12,
           "iauC2txy", "33");

    }

    @Test
    public void t_cal2jd()
    /*
    **  - - - - - - - - -
    **   t _ c a l 2 j d
    **  - - - - - - - - -
    **
    **  Test iauCal2jd function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauCal2jd, vvd, viv
    **
    **  This revision:  2008 May 27
    */
    {
       int j;
       double djm0, djm;


       j = iauCal2jd(2003, 06, 01, djm0, djm);

       vvd(djm0, 2400000.5, 0.0, "iauCal2jd", "djm0");
       vvd(djm,    52791.0, 0.0, "iauCal2jd", "djm");

       viv(j, 0, "iauCal2jd", "j");

    }

    @Test
    public void t_cp()
    /*
    **  - - - - -
    **   t _ c p
    **  - - - - -
    **
    **  Test iauCp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauCp, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double p[] = new double[3], c[] = new double[3];


       p[0] =  0.3;
       p[1] =  1.2;
       p[2] = -2.5;

       iauCp(p, c);

       vvd(c[0],  0.3, 0.0, "iauCp", "1");
       vvd(c[1],  1.2, 0.0, "iauCp", "2");
       vvd(c[2], -2.5, 0.0, "iauCp", "3");
    }

    @Test
    public void t_cpv()
    /*
    **  - - - - - -
    **   t _ c p v
    **  - - - - - -
    **
    **  Test iauCpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauCpv, vvd
    **
    **  This revision:  2008 May 25
    */
    {
       double pv[][] = new double[2][3], c[][] = new double[2][3];


       pv[0][0] =  0.3;
       pv[0][1] =  1.2;
       pv[0][2] = -2.5;

       pv[1][0] = -0.5;
       pv[1][1] =  3.1;
       pv[1][2] =  0.9;

       iauCpv(pv, c);

       vvd(c[0][0],  0.3, 0.0, "iauCpv", "p1");
       vvd(c[0][1],  1.2, 0.0, "iauCpv", "p2");
       vvd(c[0][2], -2.5, 0.0, "iauCpv", "p3");

       vvd(c[1][0], -0.5, 0.0, "iauCpv", "v1");
       vvd(c[1][1],  3.1, 0.0, "iauCpv", "v2");
       vvd(c[1][2],  0.9, 0.0, "iauCpv", "v3");

    }

    @Test
    public void t_cr()
    /*
    **  - - - - -
    **   t _ c r
    **  - - - - -
    **
    **  Test iauCr function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauCr, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double r[][] = new double[3][3], c[][] = new double[3][3];


       r[0][0] = 2.0;
       r[0][1] = 3.0;
       r[0][2] = 2.0;

       r[1][0] = 3.0;
       r[1][1] = 2.0;
       r[1][2] = 3.0;

       r[2][0] = 3.0;
       r[2][1] = 4.0;
       r[2][2] = 5.0;

       iauCr(r, c);

       vvd(c[0][0], 2.0, 0.0, "iauCr", "11");
       vvd(c[0][1], 3.0, 0.0, "iauCr", "12");
       vvd(c[0][2], 2.0, 0.0, "iauCr", "13");

       vvd(c[1][0], 3.0, 0.0, "iauCr", "21");
       vvd(c[1][1], 2.0, 0.0, "iauCr", "22");
       vvd(c[1][2], 3.0, 0.0, "iauCr", "23");

       vvd(c[2][0], 3.0, 0.0, "iauCr", "31");
       vvd(c[2][1], 4.0, 0.0, "iauCr", "32");
       vvd(c[2][2], 5.0, 0.0, "iauCr", "33");
    }

    @Test
    public void t_d2tf()
    /*
    **  - - - - - - -
    **   t _ d 2 t f
    **  - - - - - - -
    **
    **  Test iauD2tf function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauD2tf, viv, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       int ihmsf[] = new int[4];
       char s;


       s = iauD2tf(4, -0.987654321, ihmsf);

       viv((int)s, '-', "iauD2tf", "s");

       viv(ihmsf[0], 23, "iauD2tf", "0");
       viv(ihmsf[1], 42, "iauD2tf", "1");
       viv(ihmsf[2], 13, "iauD2tf", "2");
       viv(ihmsf[3], 3333, "iauD2tf", "3");

    }

    @Test
    public void t_dat()
    /*
    **  - - - - - -
    **   t _ d a t
    **  - - - - - -
    **
    **  Test iauDat function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauDat, vvd, viv
    **
    **  This revision:  2008 November 29
    */
    {
       int j;
       double deltat;


       j = iauDat(2003, 6, 1, 0.0, deltat);

       vvd(deltat, 32.0, 0.0, "iauDat", "d1");
       viv(j, 0, "iauDat", "j1");

       j = iauDat(2008, 1, 17, 0.0, deltat);

       vvd(deltat, 33.0, 0.0, "iauDat", "d2");
       viv(j, 0, "iauDat", "j2");

    }

    @Test
    public void t_dtdb()
    /*
    **  - - - - - - -
    **   t _ d t d b
    **  - - - - - - -
    **
    **  Test iauDtdb function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauDtdb, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double dtdb;


       dtdb = iauDtdb(2448939.5, 0.123, 0.76543, 5.0123, 5525.242, 3190.0);

       vvd(dtdb, -0.1280368005936998991e-2, 1e-15, "iauDtdb", "");

    }

    @Test
    public void t_ee00()
    /*
    **  - - - - - - -
    **   t _ e e 0 0
    **  - - - - - - -
    **
    **  Test iauEe00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEe00, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double epsa, dpsi, ee;


       epsa =  0.4090789763356509900;
       dpsi = -0.9630909107115582393e-5;

       ee = iauEe00(2400000.5, 53736.0, epsa, dpsi);

       vvd(ee, -0.8834193235367965479e-5, 1e-18, "iauEe00", "");

    }

    @Test
    public void t_ee00a()
    /*
    **  - - - - - - - -
    **   t _ e e 0 0 a
    **  - - - - - - - -
    **
    **  Test iauEe00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEe00a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double ee;


       ee = iauEe00a(2400000.5, 53736.0);

       vvd(ee, -0.8834192459222588227e-5, 1e-18, "iauEe00a", "");

    }

    @Test
    public void t_ee00b()
    /*
    **  - - - - - - - -
    **   t _ e e 0 0 b
    **  - - - - - - - -
    **
    **  Test iauEe00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEe00b, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double ee;


       ee = iauEe00b(2400000.5, 53736.0);

       vvd(ee, -0.8835700060003032831e-5, 1e-18, "iauEe00b", "");

    }

    @Test
    public void t_ee06a()
    /*
    **  - - - - - - - -
    **   t _ e e 0 6 a
    **  - - - - - - - -
    **
    **  Test iauEe06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEe06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double ee;


       ee = iauEe06a(2400000.5, 53736.0);

       vvd(ee, -0.8834195072043790156e-5, 1e-15, "iauEe06a", "");
    }

    @Test
    public void t_eect00()
    /*
    **  - - - - - - - - -
    **   t _ e e c t 0 0
    **  - - - - - - - - -
    **
    **  Test iauEect00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEect00, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double eect;


       eect = iauEect00(2400000.5, 53736.0);

       vvd(eect, 0.2046085004885125264e-8, 1e-20, "iauEect00", "");

    }

    @Test
    public void t_eform()
    /*
    **  - - - - - - - -
    **   t _ e f o r m
    **  - - - - - - - -
    **
    **  Test iauEform function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEform, viv, vvd
    **
    **  This revision:  2010 January 18
    */
    {
       int j;
       double a, f;

       j = iauEform( 0, a, f );

       viv(j, -1, "iauEform", "j0");

       j = iauEform( 1, a, f );

       viv(j, 0, "iauEform", "j1");
       vvd(a, 6378137.0, 1e-10, "iauEform", "a");
       vvd(f, 0.0033528106647474807, 1e-18, "iauEform", "f");

       j = iauEform( 2, a, f );

       viv(j, 0, "iauEform", "j2");
       vvd(a, 6378137.0, 1e-10, "iauEform", "a");
       vvd(f, 0.0033528106811823189, 1e-18, "iauEform", "f");

       j = iauEform( 3, a, f );

       viv(j, 0, "iauEform", "j2");
       vvd(a, 6378135.0, 1e-10, "iauEform", "a");
       vvd(f, 0.0033527794541675049, 1e-18, "iauEform", "f");

       j = iauEform( 4, a, f );
       viv(j, -1, "iauEform", "j3");
    }

    @Test
    public void t_eo06a()
    /*
    **  - - - - - - - -
    **   t _ e o 0 6 a
    **  - - - - - - - -
    **
    **  Test iauEo06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEo06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double eo;


       eo = iauEo06a(2400000.5, 53736.0);

       vvd(eo, -0.1332882371941833644e-2, 1e-15, "iauEo06a", "");

    }

    @Test
    public void t_eors()
    /*
    **  - - - - - - -
    **   t _ e o r s
    **  - - - - - - -
    **
    **  Test iauEors function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEors, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rnpb[][] = new double[3][3], s, eo;


       rnpb[0][0] =  0.9999989440476103608;
       rnpb[0][1] = -0.1332881761240011518e-2;
       rnpb[0][2] = -0.5790767434730085097e-3;

       rnpb[1][0] =  0.1332858254308954453e-2;
       rnpb[1][1] =  0.9999991109044505944;
       rnpb[1][2] = -0.4097782710401555759e-4;

       rnpb[2][0] =  0.5791308472168153320e-3;
       rnpb[2][1] =  0.4020595661593994396e-4;
       rnpb[2][2] =  0.9999998314954572365;

       s = -0.1220040848472271978e-7;

       eo = iauEors(rnpb, s);

       vvd(eo, -0.1332882715130744606e-2, 1e-14, "iauEors", "");

    }

    @Test
    public void t_epb()
    /*
    **  - - - - - -
    **   t _ e p b
    **  - - - - - -
    **
    **  Test iauEpb function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEpb, vvd
    **
    **  This revision:  2008 May 27
    */
    {
       double epb;


       epb = iauEpb(2415019.8135, 30103.18648);

       vvd(epb, 1982.418424159278580, 1e-12, "iauEpb", "");

    }

    @Test
    public void t_epb2jd()
    /*
    **  - - - - - - - - -
    **   t _ e p b 2 j d
    **  - - - - - - - - -
    **
    **  Test iauEpb2jd function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEpb2jd, vvd
    **
    **  This revision:  2008 November 29
    */
    {
       double epb, djm0, djm;


       epb = 1957.3;

       iauEpb2jd(epb, djm0, djm);

       vvd(djm0, 2400000.5, 1e-9, "iauEpb2jd", "djm0");
       vvd(djm, 35948.1915101513, 1e-9, "iauEpb2jd", "mjd");

    }

    @Test
    public void t_epj()
    /*
    **  - - - - - -
    **   t _ e p j
    **  - - - - - -
    **
    **  Test iauEpj function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEpj, vvd
    **
    **  This revision:  2008 May 27
    */
    {
       double epj;


       epj = iauEpj(2451545, -7392.5);

       vvd(epj, 1979.760438056125941, 1e-12, "iauEpj", "");

    }

    @Test
    public void t_epj2jd()
    /*
    **  - - - - - - - - -
    **   t _ e p j 2 j d
    **  - - - - - - - - -
    **
    **  Test iauEpj2jd function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEpj2jd, vvd
    **
    **  This revision:  2008 November 29
    */
    {
       double epj, djm0, djm;


       epj = 1996.8;

       JulianDate jd = iauEpj2jd(epj);

       vvd(jd.djm0, 2400000.5, 1e-9, "iauEpj2jd", "djm0");
       vvd(jd.djm1,    50375.7, 1e-9, "iauEpj2jd", "mjd");

    }

    @Test
    public void t_epv00()
    /*
    **  - - - - - - - -
    **   t _ e p v 0 0
    **  - - - - - - - -
    **
    **  Test iauEpv00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called: iauEpv00, vvd, viv
    **
    **  This revision:  2008 November 28
    */
    {
       double pvh[][] = new double[2][3], pvb[][] = new double[2][3];
       int j;


       j = iauEpv00(2400000.5, 53411.52501161, pvh, pvb);

       vvd(pvh[0][0], -0.7757238809297706813, 1e-14,
           "iauEpv00", "ph(x)");
       vvd(pvh[0][1], 0.5598052241363340596, 1e-14,
           "iauEpv00", "ph(y)");
       vvd(pvh[0][2], 0.2426998466481686993, 1e-14,
           "iauEpv00", "ph(z)");

       vvd(pvh[1][0], -0.1091891824147313846e-1, 1e-15,
           "iauEpv00", "vh(x)");
       vvd(pvh[1][1], -0.1247187268440845008e-1, 1e-15,
           "iauEpv00", "vh(y)");
       vvd(pvh[1][2], -0.5407569418065039061e-2, 1e-15,
           "iauEpv00", "vh(z)");

       vvd(pvb[0][0], -0.7714104440491111971, 1e-14,
           "iauEpv00", "pb(x)");
       vvd(pvb[0][1], 0.5598412061824171323, 1e-14,
           "iauEpv00", "pb(y)");
       vvd(pvb[0][2], 0.2425996277722452400, 1e-14,
           "iauEpv00", "pb(z)");

       vvd(pvb[1][0], -0.1091874268116823295e-1, 1e-15,
           "iauEpv00", "vb(x)");
       vvd(pvb[1][1], -0.1246525461732861538e-1, 1e-15,
           "iauEpv00", "vb(y)");
       vvd(pvb[1][2], -0.5404773180966231279e-2, 1e-15,
           "iauEpv00", "vb(z)");

       viv(j, 0, "iauEpv00", "j");

    }

    @Test
    public void t_eqeq94()
    /*
    **  - - - - - - - - -
    **   t _ e q e q 9 4
    **  - - - - - - - - -
    **
    **  Test iauEqeq94 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEqeq94, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double eqeq;


       eqeq = iauEqeq94(2400000.5, 41234.0);

       vvd(eqeq, 0.5357758254609256894e-4, 1e-17, "iauEqeq94", "");

    }

    @Test
    public void t_era00()
    /*
    **  - - - - - - - -
    **   t _ e r a 0 0
    **  - - - - - - - -
    **
    **  Test iauEra00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEra00, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double era00;


       era00 = iauEra00(2400000.5, 54388.0);

       vvd(era00, 0.4022837240028158102, 1e-12, "iauEra00", "");

    }

    @Test
    public void t_fad03()
    /*
    **  - - - - - - - -
    **   t _ f a d 0 3
    **  - - - - - - - -
    **
    **  Test iauFad03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFad03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(iauFad03(0.80), 1.946709205396925672, 1e-12,
           "iauFad03", "");
    }

    @Test
    public void t_fae03()
    /*
    **  - - - - - - - -
    **   t _ f a e 0 3
    **  - - - - - - - -
    **
    **  Test iauFae03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFae03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(iauFae03(0.80), 1.744713738913081846, 1e-12,
           "iauFae03", "");
    }

    @Test
    public void t_faf03()
    /*
    **  - - - - - - - -
    **   t _ f a f 0 3
    **  - - - - - - - -
    **
    **  Test iauFaf03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFaf03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(iauFaf03(0.80), 0.2597711366745499518, 1e-12,
           "iauFaf03", "");
    }

    @Test
    public void t_faju03()
    /*
    **  - - - - - - - - -
    **   t _ f a j u 0 3
    **  - - - - - - - - -
    **
    **  Test iauFaju03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFaju03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(iauFaju03(0.80), 5.275711665202481138, 1e-12,
           "iauFaju03", "");
    }

    @Test
    public void t_fal03()
    /*
    **  - - - - - - - -
    **   t _ f a l 0 3
    **  - - - - - - - -
    **
    **  Test iauFal03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFal03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(iauFal03(0.80), 5.132369751108684150, 1e-12,
           "iauFal03", "");
    }

    @Test
    public void t_falp03()
    /*
    **  - - - - - - - - -
    **   t _ f a l p 0 3
    **  - - - - - - - - -
    **
    **  Test iauFalp03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFalp03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(iauFalp03(0.80), 6.226797973505507345, 1e-12,
          "iauFalp03", "");
    }

    @Test
    public void t_fama03()
    /*
    **  - - - - - - - - -
    **   t _ f a m a 0 3
    **  - - - - - - - - -
    **
    **  Test iauFama03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFama03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(iauFama03(0.80), 3.275506840277781492, 1e-12,
           "iauFama03", "");
    }

    @Test
    public void t_fame03()
    /*
    **  - - - - - - - - -
    **   t _ f a m e 0 3
    **  - - - - - - - - -
    **
    **  Test iauFame03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFame03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(iauFame03(0.80), 5.417338184297289661, 1e-12,
           "iauFame03", "");
    }

    @Test
    public void t_fane03()
    /*
    **  - - - - - - - - -
    **   t _ f a n e 0 3
    **  - - - - - - - - -
    **
    **  Test iauFane03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFane03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(iauFane03(0.80), 2.079343830860413523, 1e-12,
           "iauFane03", "");
    }

    @Test
    public void t_faom03()
    /*
    **  - - - - - - - - -
    **   t _ f a o m 0 3
    **  - - - - - - - - -
    **
    **  Test iauFaom03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFaom03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(iauFaom03(0.80), -5.973618440951302183, 1e-12,
           "iauFaom03", "");
    }

    @Test
    public void t_fapa03()
    /*
    **  - - - - - - - - -
    **   t _ f a p a 0 3
    **  - - - - - - - - -
    **
    **  Test iauFapa03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFapa03, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       vvd(iauFapa03(0.80), 0.1950884762240000000e-1, 1e-12,
           "iauFapa03", "");
    }

    @Test
    public void t_fasa03()
    /*
    **  - - - - - - - - -
    **   t _ f a s a 0 3
    **  - - - - - - - - -
    **
    **  Test iauFasa03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFasa03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(iauFasa03(0.80), 5.371574539440827046, 1e-12,
           "iauFasa03", "");
    }

    @Test
    public void t_faur03()
    /*
    **  - - - - - - - - -
    **   t _ f a u r 0 3
    **  - - - - - - - - -
    **
    **  Test iauFaur03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFaur03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(iauFaur03(0.80), 5.180636450180413523, 1e-12,
           "iauFaur03", "");
    }

    @Test
    public void t_fave03()
    /*
    **  - - - - - - - - -
    **   t _ f a v e 0 3
    **  - - - - - - - - -
    **
    **  Test iauFave03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFave03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(iauFave03(0.80), 3.424900460533758000, 1e-12,
           "iauFave03", "");
    }

    @Test
    public void t_fk52h()
    /*
    **  - - - - - - - -
    **   t _ f k 5 2 h
    **  - - - - - - - -
    **
    **  Test iauFk52h function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFk52h, vvd
    **
    **  This revision:  2009 November 6
    */
    {
       double r5, d5, dr5, dd5, px5, rv5, rh, dh, drh, ddh, pxh, rvh;


       r5  =  1.76779433;
       d5  = -0.2917517103;
       dr5 = -1.91851572e-7;
       dd5 = -5.8468475e-6;
       px5 =  0.379210;
       rv5 = -7.6;

       iauFk52h(r5, d5, dr5, dd5, px5, rv5,
                rh, dh, drh, ddh, pxh, rvh);

       vvd(rh, 1.767794226299947632, 1e-14,
           "iauFk52h", "ra");
       vvd(dh,  -0.2917516070530391757, 1e-14,
           "iauFk52h", "dec");
       vvd(drh, -0.19618741256057224e-6,1e-19,
           "iauFk52h", "dr5");
       vvd(ddh, -0.58459905176693911e-5, 1e-19,
           "iauFk52h", "dd5");
       vvd(pxh,  0.37921, 1e-14,
           "iauFk52h", "px");
       vvd(rvh, -7.6000000940000254, 1e-11,
           "iauFk52h", "rv");

    }

    @Test
    public void t_fk5hip()
    /*
    **  - - - - - - - - -
    **   t _ f k 5 h i p
    **  - - - - - - - - -
    **
    **  Test iauFk5hip function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFk5hip, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double r5h[][] = new double[3][3], s5h[] = new double[3];


       iauFk5hip(r5h, s5h);

       vvd(r5h[0][0], 0.9999999999999928638, 1e-14,
           "iauFk5hip", "11");
       vvd(r5h[0][1], 0.1110223351022919694e-6, 1e-17,
           "iauFk5hip", "12");
       vvd(r5h[0][2], 0.4411803962536558154e-7, 1e-17,
           "iauFk5hip", "13");
       vvd(r5h[1][0], -0.1110223308458746430e-6, 1e-17,
           "iauFk5hip", "21");
       vvd(r5h[1][1], 0.9999999999999891830, 1e-14,
           "iauFk5hip", "22");
       vvd(r5h[1][2], -0.9647792498984142358e-7, 1e-17,
           "iauFk5hip", "23");
       vvd(r5h[2][0], -0.4411805033656962252e-7, 1e-17,
           "iauFk5hip", "31");
       vvd(r5h[2][1], 0.9647792009175314354e-7, 1e-17,
           "iauFk5hip", "32");
       vvd(r5h[2][2], 0.9999999999999943728, 1e-14,
           "iauFk5hip", "33");
       vvd(s5h[0], -0.1454441043328607981e-8, 1e-17,
           "iauFk5hip", "s1");
       vvd(s5h[1], 0.2908882086657215962e-8, 1e-17,
           "iauFk5hip", "s2");
       vvd(s5h[2], 0.3393695767766751955e-8, 1e-17,
           "iauFk5hip", "s3");

    }

    @Test
    public void t_fk5hz()
    /*
    **  - - - - - - - -
    **   t _ f k 5 h z
    **  - - - - - - - -
    **
    **  Test iauFk5hz function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFk5hz, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double r5, d5, rh, dh;


       r5 =  1.76779433;
       d5 = -0.2917517103;

       iauFk5hz(r5, d5, 2400000.5, 54479.0, rh, dh);

       vvd(rh,  1.767794191464423978, 1e-12, "iauFk5hz", "ra");
       vvd(dh, -0.2917516001679884419, 1e-12, "iauFk5hz", "dec");

    }

    @Test
    public void t_fw2m()
    /*
    **  - - - - - - -
    **   t _ f w 2 m
    **  - - - - - - -
    **
    **  Test iauFw2m function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFw2m, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double gamb, phib, psi, eps, r[][] = new double[3][3];


       gamb = -0.2243387670997992368e-5;
       phib =  0.4091014602391312982;
       psi  = -0.9501954178013015092e-3;
       eps  =  0.4091014316587367472;

       iauFw2m(gamb, phib, psi, eps, r);

       vvd(r[0][0], 0.9999995505176007047, 1e-12,
           "iauFw2m", "11");
       vvd(r[0][1], 0.8695404617348192957e-3, 1e-12,
           "iauFw2m", "12");
       vvd(r[0][2], 0.3779735201865582571e-3, 1e-12,
           "iauFw2m", "13");

       vvd(r[1][0], -0.8695404723772016038e-3, 1e-12,
           "iauFw2m", "21");
       vvd(r[1][1], 0.9999996219496027161, 1e-12,
           "iauFw2m", "22");
       vvd(r[1][2], -0.1361752496887100026e-6, 1e-12,
           "iauFw2m", "23");

       vvd(r[2][0], -0.3779734957034082790e-3, 1e-12,
           "iauFw2m", "31");
       vvd(r[2][1], -0.1924880848087615651e-6, 1e-12,
           "iauFw2m", "32");
       vvd(r[2][2], 0.9999999285679971958, 1e-12,
           "iauFw2m", "33");

    }

    @Test
    public void t_fw2xy()
    /*
    **  - - - - - - - -
    **   t _ f w 2 x y
    **  - - - - - - - -
    **
    **  Test iauFw2xy function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFw2xy, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double gamb, phib, psi, eps, x, y;


       gamb = -0.2243387670997992368e-5;
       phib =  0.4091014602391312982;
       psi  = -0.9501954178013015092e-3;
       eps  =  0.4091014316587367472;

       iauFw2xy(gamb, phib, psi, eps, x, y);

       vvd(x, -0.3779734957034082790e-3, 1e-14, "iauFw2xy", "x");
       vvd(y, -0.1924880848087615651e-6, 1e-14, "iauFw2xy", "y");

    }

    @Test
    public void t_gc2gd()
    /*
    **  - - - - - - - -
    **   t _ g c 2 g d
    **  - - - - - - - -
    **
    **  Test iauGc2gd function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGc2gd, viv, vvd
    **
    **  This revision:  2009 November 8
    */
    {
       int j;
       double xyz[] = {2e6, 3e6, 5.244e6};
       double e, p, h;

       j = iauGc2gd( 0, xyz, e, p, h );

       viv(j, -1, "iauGc2gd", "j0");

       j = iauGc2gd( 1, xyz, e, p, h );

       viv(j, 0, "iauGc2gd", "j1");
       vvd(e, 0.98279372324732907, 1e-14, "iauGc2gd", "e1");
       vvd(p, 0.97160184819075459, 1e-14, "iauGc2gd", "p1");
       vvd(h, 331.41724614260599, 1e-8, "iauGc2gd", "h1");

       j = iauGc2gd( 2, xyz, e, p, h );

       viv(j, 0, "iauGc2gd", "j2");
       vvd(e, 0.98279372324732907, 1e-14, "iauGc2gd", "e2");
       vvd(p, 0.97160184820607853, 1e-14, "iauGc2gd", "p2");
       vvd(h, 331.41731754844348, 1e-8, "iauGc2gd", "h2");

       j = iauGc2gd( 3, xyz, e, p, h );

       viv(j, -1, "iauGc2gd", "j3");
    }

    @Test
    public void t_gc2gde()
    /*
    **  - - - - - - - - -
    **   t _ g c 2 g d e
    **  - - - - - - - - -
    **
    **  Test iauGc2gde function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGc2gde, viv, vvd
    **
    **  This revision:  2009 November 8
    */
    {
       int j;
       double a = 6378136.0, f = 0.0033528;
       double xyz[] = {2e6, 3e6, 5.244e6};
       double e, p, h;

       j = iauGc2gde( a, f, xyz, e, p, h );

       viv(j, 0, "iauGc2gde", "j");
       vvd(e, 0.98279372324732907, 1e-14, "iauGc2gde", "e");
       vvd(p, 0.97160183775704115, 1e-14, "iauGc2gde", "p");
       vvd(h, 332.36862495764397, 1e-8, "iauGc2gde", "h");
    }

    @Test
    public void t_gd2gc()
    /*
    **  - - - - - - - -
    **   t _ g d 2 g c
    **  - - - - - - - -
    **
    **  Test iauGd2gc function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGd2gc, viv, vvd
    **
    **  This revision:  2009 November 6
    */
    {
       int j;
       double e = 3.1, p = -0.5, h = 2500.0;
       double xyz[] = new double[3];

       j = iauGd2gc( 0, e, p, h, xyz );

       viv(j, -1, "iauGd2gc", "j0");

       j = iauGd2gc( 1, e, p, h, xyz );

       viv(j, 0, "iauGd2gc", "j1");
       vvd(xyz[0], -5599000.5577049947, 1e-7, "iauGd2gc", "0/1");
       vvd(xyz[1], 233011.67223479203, 1e-7, "iauGd2gc", "1/1");
       vvd(xyz[2], -3040909.4706983363, 1e-7, "iauGd2gc", "2/1");

       j = iauGd2gc( 2, e, p, h, xyz );

       viv(j, 0, "iauGd2gc", "j2");
       vvd(xyz[0], -5599000.5577260984, 1e-7, "iauGd2gc", "0/2");
       vvd(xyz[1], 233011.6722356703, 1e-7, "iauGd2gc", "1/2");
       vvd(xyz[2], -3040909.4706095476, 1e-7, "iauGd2gc", "2/2");

       j = iauGd2gc( 3, e, p, h, xyz );

       viv(j, -1, "iauGd2gc", "j3");
    }

    @Test
    public void t_gd2gce()
    /*
    **  - - - - - - - - -
    **   t _ g d 2 g c e
    **  - - - - - - - - -
    **
    **  Test iauGd2gce function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGd2gce, viv, vvd
    **
    **  This revision:  2009 November 6
    */
    {
       int j;
       double a = 6378136.0, f = 0.0033528;
       double e = 3.1, p = -0.5, h = 2500.0;
       double xyz[] = new double[3];

       j = iauGd2gce( a, f, e, p, h, xyz );

       viv(j, 0, "iauGd2gce", "j");
       vvd(xyz[0], -5598999.6665116328, 1e-7, "iauGd2gce", "0");
       vvd(xyz[1], 233011.63514630572, 1e-7, "iauGd2gce", "1");
       vvd(xyz[2], -3040909.0517314132, 1e-7, "iauGd2gce", "2");
    }

    @Test
    public void t_gmst00()
    /*
    **  - - - - - - - - -
    **   t _ g m s t 0 0
    **  - - - - - - - - -
    **
    **  Test iauGmst00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGmst00, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double theta;


       theta = iauGmst00(2400000.5, 53736.0, 2400000.5, 53736.0);

       vvd(theta, 1.754174972210740592, 1e-12, "iauGmst00", "");

    }

    @Test
    public void t_gmst06()
    /*
    **  - - - - - - - - -
    **   t _ g m s t 0 6
    **  - - - - - - - - -
    **
    **  Test iauGmst06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGmst06, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double theta;


       theta = iauGmst06(2400000.5, 53736.0, 2400000.5, 53736.0);

       vvd(theta, 1.754174971870091203, 1e-12, "iauGmst06", "");

    }

    @Test
    public void t_gmst82()
    /*
    **  - - - - - - - - -
    **   t _ g m s t 8 2
    **  - - - - - - - - -
    **
    **  Test iauGmst82 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGmst82, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double theta;


       theta = iauGmst82(2400000.5, 53736.0);

       vvd(theta, 1.754174981860675096, 1e-12, "iauGmst82", "");

    }

    @Test
    public void t_gst00a()
    /*
    **  - - - - - - - - -
    **   t _ g s t 0 0 a
    **  - - - - - - - - -
    **
    **  Test iauGst00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGst00a, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double theta;


       theta = iauGst00a(2400000.5, 53736.0, 2400000.5, 53736.0);

       vvd(theta, 1.754166138018281369, 1e-12, "iauGst00a", "");

    }

    @Test
    public void t_gst00b()
    /*
    **  - - - - - - - - -
    **   t _ g s t 0 0 b
    **  - - - - - - - - -
    **
    **  Test iauGst00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGst00b, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double theta;


       theta = iauGst00b(2400000.5, 53736.0);

       vvd(theta, 1.754166136510680589, 1e-12, "iauGst00b", "");

    }

    @Test
    public void t_gst06()
    /*
    **  - - - - - - - -
    **   t _ g s t 0 6
    **  - - - - - - - -
    **
    **  Test iauGst06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGst06, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rnpb[][] = new double[3][3], theta;


       rnpb[0][0] =  0.9999989440476103608;
       rnpb[0][1] = -0.1332881761240011518e-2;
       rnpb[0][2] = -0.5790767434730085097e-3;

       rnpb[1][0] =  0.1332858254308954453e-2;
       rnpb[1][1] =  0.9999991109044505944;
       rnpb[1][2] = -0.4097782710401555759e-4;

       rnpb[2][0] =  0.5791308472168153320e-3;
       rnpb[2][1] =  0.4020595661593994396e-4;
       rnpb[2][2] =  0.9999998314954572365;

       theta = iauGst06(2400000.5, 53736.0, 2400000.5, 53736.0, rnpb);

       vvd(theta, 1.754166138018167568, 1e-12, "iauGst06", "");

    }

    @Test
    public void t_gst06a()
    /*
    **  - - - - - - - - -
    **   t _ g s t 0 6 a
    **  - - - - - - - - -
    **
    **  Test iauGst06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGst06a, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double theta;


       theta = iauGst06a(2400000.5, 53736.0, 2400000.5, 53736.0);

       vvd(theta, 1.754166137675019159, 1e-12, "iauGst06a", "");

    }

    @Test
    public void t_gst94()
    /*
    **  - - - - - - - -
    **   t _ g s t 9 4
    **  - - - - - - - -
    **
    **  Test iauGst94 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGst94, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double theta;


       theta = iauGst94(2400000.5, 53736.0);

       vvd(theta, 1.754166136020645203, 1e-12, "iauGst94", "");

    }

    @Test
    public void t_h2fk5()
    /*
    **  - - - - - - - -
    **   t _ h 2 f k 5
    **  - - - - - - - -
    **
    **  Test iauH2fk5 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauH2fk5, vvd
    **
    **  This revision:  2009 November 6
    */
    {
       double rh, dh, drh, ddh, pxh, rvh, r5, d5, dr5, dd5, px5, rv5;


       rh  =  1.767794352;
       dh  = -0.2917512594;
       drh = -2.76413026e-6;
       ddh = -5.92994449e-6;
       pxh =  0.379210;
       rvh = -7.6;

       iauH2fk5(rh, dh, drh, ddh, pxh, rvh,
                r5, d5, dr5, dd5, px5, rv5);

       vvd(r5, 1.767794455700065506, 1e-13,
           "iauH2fk5", "ra");
       vvd(d5, -0.2917513626469638890, 1e-13,
           "iauH2fk5", "dec");
       vvd(dr5, -0.27597945024511204e-5, 1e-18,
           "iauH2fk5", "dr5");
       vvd(dd5, -0.59308014093262838e-5, 1e-18,
           "iauH2fk5", "dd5");
       vvd(px5, 0.37921, 1e-13,
           "iauH2fk5", "px");
       vvd(rv5, -7.6000001309071126, 1e-10,
           "iauH2fk5", "rv");

    }

    @Test
    public void t_hfk5z()
    /*
    **  - - - - - - - -
    **   t _ h f k 5 z
    **  - - - - - - - -
    **
    **  Test iauHfk5z function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauHfk5z, vvd
    **
    **  This revision:  2008 November 29
    */
    {
       double rh, dh, r5, d5, dr5, dd5;



       rh =  1.767794352;
       dh = -0.2917512594;

       iauHfk5z(rh, dh, 2400000.5, 54479.0, r5, d5, dr5, dd5);

       vvd(r5, 1.767794490535581026, 1e-13,
           "iauHfk5z", "ra");
       vvd(d5, -0.2917513695320114258, 1e-14,
           "iauHfk5z", "dec");
       vvd(dr5, 0.4335890983539243029e-8, 1e-22,
           "iauHfk5z", "dr5");
       vvd(dd5, -0.8569648841237745902e-9, 1e-23,
           "iauHfk5z", "dd5");

    }

    @Test
    public void t_ir()
    /*
    **  - - - - -
    **   t _ i r
    **  - - - - -
    **
    **  Test iauIr function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauIr, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double r[][] = new double[3][3];


       r[0][0] = 2.0;
       r[0][1] = 3.0;
       r[0][2] = 2.0;

       r[1][0] = 3.0;
       r[1][1] = 2.0;
       r[1][2] = 3.0;

       r[2][0] = 3.0;
       r[2][1] = 4.0;
       r[2][2] = 5.0;

       iauIr(r);

       vvd(r[0][0], 1.0, 0.0, "iauIr", "11");
       vvd(r[0][1], 0.0, 0.0, "iauIr", "12");
       vvd(r[0][2], 0.0, 0.0, "iauIr", "13");

       vvd(r[1][0], 0.0, 0.0, "iauIr", "21");
       vvd(r[1][1], 1.0, 0.0, "iauIr", "22");
       vvd(r[1][2], 0.0, 0.0, "iauIr", "23");

       vvd(r[2][0], 0.0, 0.0, "iauIr", "31");
       vvd(r[2][1], 0.0, 0.0, "iauIr", "32");
       vvd(r[2][2], 1.0, 0.0, "iauIr", "33");

    }

    @Test
    public void t_jd2cal()
    /*
    **  - - - - - - - - -
    **   t _ j d 2 c a l
    **  - - - - - - - - -
    **
    **  Test iauJd2cal function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauJd2cal, viv, vvd
    **
    **  This revision:  2008 November 29
    */
    {
       double dj1, dj2, fd;
       int iy, im, id, j;


       dj1 = 2400000.5;
       dj2 = 50123.9999;

       j = iauJd2cal(dj1, dj2, iy, im, id, fd);

       viv(iy, 1996, "iauJd2cal", "y");
       viv(im, 2, "iauJd2cal", "m");
       viv(id, 10, "iauJd2cal", "d");
       vvd(fd, 0.9999, 1e-7, "iauJd2cal", "fd");
       viv(j, 0, "iauJd2cal", "j");

    }

    @Test
    public void t_jdcalf()
    /*
    **  - - - - - - - - -
    **   t _ j d c a l f
    **  - - - - - - - - -
    **
    **  Test iauJdcalf function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauJdcalf, viv
    **
    **  This revision:  2008 May 26
    */
    {
       double dj1, dj2;
       int iydmf[] = new int[4], j;


       dj1 = 2400000.5;
       dj2 = 50123.9999;

       j = iauJdcalf(4, dj1, dj2, iydmf);

       viv(iydmf[0], 1996, "iauJdcalf", "y");
       viv(iydmf[1], 2, "iauJdcalf", "m");
       viv(iydmf[2], 10, "iauJdcalf", "d");
       viv(iydmf[3], 9999, "iauJdcalf", "f");

       viv(j, 0, "iauJdcalf", "j");

    }

    @Test
    public void t_num00a()
    /*
    **  - - - - - - - - -
    **   t _ n u m 0 0 a
    **  - - - - - - - - -
    **
    **  Test iauNum00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNum00a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rmatn[][] = new double[3][3];


       iauNum00a(2400000.5, 53736.0, rmatn);

       vvd(rmatn[0][0], 0.9999999999536227949, 1e-12,
           "iauNum00a", "11");
       vvd(rmatn[0][1], 0.8836238544090873336e-5, 1e-12,
           "iauNum00a", "12");
       vvd(rmatn[0][2], 0.3830835237722400669e-5, 1e-12,
           "iauNum00a", "13");

       vvd(rmatn[1][0], -0.8836082880798569274e-5, 1e-12,
           "iauNum00a", "21");
       vvd(rmatn[1][1], 0.9999999991354655028, 1e-12,
           "iauNum00a", "22");
       vvd(rmatn[1][2], -0.4063240865362499850e-4, 1e-12,
           "iauNum00a", "23");

       vvd(rmatn[2][0], -0.3831194272065995866e-5, 1e-12,
           "iauNum00a", "31");
       vvd(rmatn[2][1], 0.4063237480216291775e-4, 1e-12,
           "iauNum00a", "32");
       vvd(rmatn[2][2], 0.9999999991671660338, 1e-12,
           "iauNum00a", "33");

    }

    @Test
    public void t_num00b()
    /*
    **  - - - - - - - - -
    **   t _ n u m 0 0 b
    **  - - - - - - - - -
    **
    **  Test iauNum00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNum00b, vvd
    **
    **  This revision:  2008 November 28
    */
    {
        double rmatn[][] = new double[3][3];

        iauNum00b(2400000.5, 53736, rmatn);

       vvd(rmatn[0][0], 0.9999999999536069682, 1e-12,
           "iauNum00b", "11");
       vvd(rmatn[0][1], 0.8837746144871248011e-5, 1e-12,
           "iauNum00b", "12");
       vvd(rmatn[0][2], 0.3831488838252202945e-5, 1e-12,
           "iauNum00b", "13");

       vvd(rmatn[1][0], -0.8837590456632304720e-5, 1e-12,
           "iauNum00b", "21");
       vvd(rmatn[1][1], 0.9999999991354692733, 1e-12,
           "iauNum00b", "22");
       vvd(rmatn[1][2], -0.4063198798559591654e-4, 1e-12,
           "iauNum00b", "23");

       vvd(rmatn[2][0], -0.3831847930134941271e-5, 1e-12,
           "iauNum00b", "31");
       vvd(rmatn[2][1], 0.4063195412258168380e-4, 1e-12,
           "iauNum00b", "32");
       vvd(rmatn[2][2], 0.9999999991671806225, 1e-12,
           "iauNum00b", "33");

    }

    @Test
    public void t_num06a()
    /*
    **  - - - - - - - - -
    **   t _ n u m 0 6 a
    **  - - - - - - - - -
    **
    **  Test iauNum06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNum06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
        double rmatn[][] = new double[3][3];

        iauNum06a(2400000.5, 53736, rmatn);

       vvd(rmatn[0][0], 0.9999999999536227668, 1e-12,
           "iauNum06a", "11");
       vvd(rmatn[0][1], 0.8836241998111535233e-5, 1e-12,
           "iauNum06a", "12");
       vvd(rmatn[0][2], 0.3830834608415287707e-5, 1e-12,
           "iauNum06a", "13");

       vvd(rmatn[1][0], -0.8836086334870740138e-5, 1e-12,
           "iauNum06a", "21");
       vvd(rmatn[1][1], 0.9999999991354657474, 1e-12,
           "iauNum06a", "22");
       vvd(rmatn[1][2], -0.4063240188248455065e-4, 1e-12,
           "iauNum06a", "23");

       vvd(rmatn[2][0], -0.3831193642839398128e-5, 1e-12,
           "iauNum06a", "31");
       vvd(rmatn[2][1], 0.4063236803101479770e-4, 1e-12,
           "iauNum06a", "32");
       vvd(rmatn[2][2], 0.9999999991671663114, 1e-12,
           "iauNum06a", "33");

    }

    @Test
    public void t_numat()
    /*
    **  - - - - - - - -
    **   t _ n u m a t
    **  - - - - - - - -
    **
    **  Test iauNumat function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNumat, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double epsa, dpsi, deps, rmatn[][] = new double[3][3];


       epsa =  0.4090789763356509900;
       dpsi = -0.9630909107115582393e-5;
       deps =  0.4063239174001678826e-4;

       iauNumat(epsa, dpsi, deps, rmatn);

       vvd(rmatn[0][0], 0.9999999999536227949, 1e-12,
           "iauNumat", "11");
       vvd(rmatn[0][1], 0.8836239320236250577e-5, 1e-12,
           "iauNumat", "12");
       vvd(rmatn[0][2], 0.3830833447458251908e-5, 1e-12,
           "iauNumat", "13");

       vvd(rmatn[1][0], -0.8836083657016688588e-5, 1e-12,
           "iauNumat", "21");
       vvd(rmatn[1][1], 0.9999999991354654959, 1e-12,
           "iauNumat", "22");
       vvd(rmatn[1][2], -0.4063240865361857698e-4, 1e-12,
           "iauNumat", "23");

       vvd(rmatn[2][0], -0.3831192481833385226e-5, 1e-12,
           "iauNumat", "31");
       vvd(rmatn[2][1], 0.4063237480216934159e-4, 1e-12,
           "iauNumat", "32");
       vvd(rmatn[2][2], 0.9999999991671660407, 1e-12,
           "iauNumat", "33");

    }

    @Test
    public void t_nut00a()
    /*
    **  - - - - - - - - -
    **   t _ n u t 0 0 a
    **  - - - - - - - - -
    **
    **  Test iauNut00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNut00a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double dpsi, deps;


       iauNut00a(2400000.5, 53736.0, dpsi, deps);

       vvd(dpsi, -0.9630909107115518431e-5, 1e-13,
           "iauNut00a", "dpsi");
       vvd(deps,  0.4063239174001678710e-4, 1e-13,
           "iauNut00a", "deps");

    }

    @Test
    public void t_nut00b()
    /*
    **  - - - - - - - - -
    **   t _ n u t 0 0 b
    **  - - - - - - - - -
    **
    **  Test iauNut00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNut00b, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double dpsi, deps;


       iauNut00b(2400000.5, 53736.0, dpsi, deps);

       vvd(dpsi, -0.9632552291148362783e-5, 1e-13,
           "iauNut00b", "dpsi");
       vvd(deps,  0.4063197106621159367e-4, 1e-13,
           "iauNut00b", "deps");

    }

    @Test
    public void t_nut06a()
    /*
    **  - - - - - - - - -
    **   t _ n u t 0 6 a
    **  - - - - - - - - -
    **
    **  Test iauNut06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNut06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double dpsi, deps;


       iauNut06a(2400000.5, 53736.0, dpsi, deps);

       vvd(dpsi, -0.9630912025820308797e-5, 1e-13,
           "iauNut06a", "dpsi");
       vvd(deps,  0.4063238496887249798e-4, 1e-13,
           "iauNut06a", "deps");

    }

    @Test
    public void t_nut80()
    /*
    **  - - - - - - - -
    **   t _ n u t 8 0
    **  - - - - - - - -
    **
    **  Test iauNut80 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNut80, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double dpsi, deps;


       iauNut80(2400000.5, 53736.0, dpsi, deps);

       vvd(dpsi, -0.9643658353226563966e-5, 1e-13,
           "iauNut80", "dpsi");
       vvd(deps,  0.4060051006879713322e-4, 1e-13,
           "iauNut80", "deps");

    }

    @Test
    public void t_nutm80()
    /*
    **  - - - - - - - - -
    **   t _ n u t m 8 0
    **  - - - - - - - - -
    **
    **  Test iauNutm80 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNutm80, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double rmatn[][] = new double[3][3];


       iauNutm80(2400000.5, 53736.0, rmatn);

       vvd(rmatn[0][0], 0.9999999999534999268, 1e-12,
          "iauNutm80", "11");
       vvd(rmatn[0][1], 0.8847935789636432161e-5, 1e-12,
          "iauNutm80", "12");
       vvd(rmatn[0][2], 0.3835906502164019142e-5, 1e-12,
          "iauNutm80", "13");

       vvd(rmatn[1][0], -0.8847780042583435924e-5, 1e-12,
          "iauNutm80", "21");
       vvd(rmatn[1][1], 0.9999999991366569963, 1e-12,
          "iauNutm80", "22");
       vvd(rmatn[1][2], -0.4060052702727130809e-4, 1e-12,
          "iauNutm80", "23");

       vvd(rmatn[2][0], -0.3836265729708478796e-5, 1e-12,
          "iauNutm80", "31");
       vvd(rmatn[2][1], 0.4060049308612638555e-4, 1e-12,
          "iauNutm80", "32");
       vvd(rmatn[2][2], 0.9999999991684415129, 1e-12,
          "iauNutm80", "33");

    }

    @Test
    public void t_obl06()
    /*
    **  - - - - - - - -
    **   t _ o b l 0 6
    **  - - - - - - - -
    **
    **  Test iauObl06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauObl06, vvd
    **
    **  This revision:  2008 November 29
    */
    {
       vvd(iauObl06(2400000.5, 54388.0), 0.4090749229387258204, 1e-14,
           "iauObl06", "");
    }

    @Test
    public void t_obl80()
    /*
    **  - - - - - - - -
    **   t _ o b l 8 0
    **  - - - - - - - -
    **
    **  Test iauObl80 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauObl80, vvd
    **
    **  This revision:  2008 November 29
    */
    {
       double eps0;


       eps0 = iauObl80(2400000.5, 54388.0);

       vvd(eps0, 0.4090751347643816218, 1e-14, "iauObl80", "");

    }

    @Test
    public void t_p06e()
    /*
    **  - - - - - - -
    **   t _ p 0 6 e
    **  - - - - - - -
    **
    **  Test iauP06e function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauP06e, vvd
    **
    **  This revision:  2008 November 28
    */
    {
        double eps0, psia, oma, bpa, bqa, pia, bpia,
               epsa, chia, za, zetaa, thetaa, pa, gam, phi, psi;


       iauP06e(2400000.5, 52541.0, eps0, psia, oma, bpa,
               bqa, pia, bpia, epsa, chia, za,
               zetaa, thetaa, pa, gam, phi, psi);

       vvd(eps0, 0.4090926006005828715, 1e-14,
           "iauP06e", "eps0");
       vvd(psia, 0.6664369630191613431e-3, 1e-14,
           "iauP06e", "psia");
       vvd(oma , 0.4090925973783255982, 1e-14,
           "iauP06e", "oma");
       vvd(bpa, 0.5561149371265209445e-6, 1e-14,
           "iauP06e", "bpa");
       vvd(bqa, -0.6191517193290621270e-5, 1e-14,
           "iauP06e", "bqa");
       vvd(pia, 0.6216441751884382923e-5, 1e-14,
           "iauP06e", "pia");
       vvd(bpia, 3.052014180023779882, 1e-14,
           "iauP06e", "bpia");
       vvd(epsa, 0.4090864054922431688, 1e-14,
           "iauP06e", "epsa");
       vvd(chia, 0.1387703379530915364e-5, 1e-14,
           "iauP06e", "chia");
       vvd(za, 0.2921789846651790546e-3, 1e-14,
           "iauP06e", "za");
       vvd(zetaa, 0.3178773290332009310e-3, 1e-14,
           "iauP06e", "zetaa");
       vvd(thetaa, 0.2650932701657497181e-3, 1e-14,
           "iauP06e", "thetaa");
       vvd(pa, 0.6651637681381016344e-3, 1e-14,
           "iauP06e", "pa");
       vvd(gam, 0.1398077115963754987e-5, 1e-14,
           "iauP06e", "gam");
       vvd(phi, 0.4090864090837462602, 1e-14,
           "iauP06e", "phi");
       vvd(psi, 0.6664464807480920325e-3, 1e-14,
           "iauP06e", "psi");

    }

    @Test
    public void t_p2pv()
    /*
    **  - - - - - - -
    **   t _ p 2 p v
    **  - - - - - - -
    **
    **  Test iauP2pv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauP2pv, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double p[] = new double[3], pv[][]=new double[2][3];


       p[0] = 0.25;
       p[1] = 1.2;
       p[2] = 3.0;

       pv[0][0] =  0.3;
       pv[0][1] =  1.2;
       pv[0][2] = -2.5;

       pv[1][0] = -0.5;
       pv[1][1] =  3.1;
       pv[1][2] =  0.9;

       iauP2pv(p, pv);

       vvd(pv[0][0], 0.25, 0.0, "iauP2pv", "p1");
       vvd(pv[0][1], 1.2,  0.0, "iauP2pv", "p2");
       vvd(pv[0][2], 3.0,  0.0, "iauP2pv", "p3");

       vvd(pv[1][0], 0.0,  0.0, "iauP2pv", "v1");
       vvd(pv[1][1], 0.0,  0.0, "iauP2pv", "v2");
       vvd(pv[1][2], 0.0,  0.0, "iauP2pv", "v3");

    }

    @Test
    public void t_p2s()
    /*
    **  - - - - - -
    **   t _ p 2 s
    **  - - - - - -
    **
    **  Test iauP2s function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauP2s, vvd
    **
    **  This revision:  2008 November 29
    */
    {
       double p[] = new double[3], theta, phi, r;


       p[0] = 100.0;
       p[1] = -50.0;
       p[2] =  25.0;

       iauP2s(p, theta, phi, r);

       vvd(theta, -0.4636476090008061162, 1e-12, "iauP2s", "theta");
       vvd(phi, 0.2199879773954594463, 1e-12, "iauP2s", "phi");
       vvd(r, 114.5643923738960002, 1e-9, "iauP2s", "r");

    }

    @Test
    public void t_pap()
    /*
    **  - - - - - -
    **   t _ p a p
    **  - - - - - -
    **
    **  Test iauPap function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPap, vvd
    **
    **  This revision:  2008 May 25
    */
    {
       double a[] = new double[3], b[] = new double[3], theta;


       a[0] =  1.0;
       a[1] =  0.1;
       a[2] =  0.2;

       b[0] = -3.0;
       b[1] = 1e-3;
       b[2] =  0.2;

       theta = iauPap(a, b);

       vvd(theta, 0.3671514267841113674, 1e-12, "iauPap", "");

    }

    @Test
    public void t_pas()
    /*
    **  - - - - - -
    **   t _ p a s
    **  - - - - - -
    **
    **  Test iauPas function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPas, vvd
    **
    **  This revision:  2008 May 25
    */
    {
       double al, ap, bl, bp, theta;


       al =  1.0;
       ap =  0.1;
       bl =  0.2;
       bp = -1.0;

       theta = iauPas(al, ap, bl, bp);

       vvd(theta, -2.724544922932270424, 1e-12, "iauPas", "");

    }

    @Test
    public void t_pb06()
    /*
    **  - - - - - - -
    **   t _ p b 0 6
    **  - - - - - - -
    **
    **  Test iauPb06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPb06, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double bzeta, bz, btheta;


       iauPb06(2400000.5, 50123.9999, bzeta, bz, btheta);

       vvd(bzeta, -0.5092634016326478238e-3, 1e-12,
           "iauPb06", "bzeta");
       vvd(bz, -0.3602772060566044413e-3, 1e-12,
           "iauPb06", "bz");
       vvd(btheta, -0.3779735537167811177e-3, 1e-12,
           "iauPb06", "btheta");

    }

    @Test
    public void t_pdp()
    /*
    **  - - - - - -
    **   t _ p d p
    **  - - - - - -
    **
    **  Test iauPdp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPdp, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double a[] = new double[3], b[] = new double[3], adb;


       a[0] = 2.0;
       a[1] = 2.0;
       a[2] = 3.0;

       b[0] = 1.0;
       b[1] = 3.0;
       b[2] = 4.0;

       adb = iauPdp(a, b);

       vvd(adb, 20, 1e-12, "iauPdp", "");

    }

    @Test
    public void t_pfw06()
    /*
    **  - - - - - - - -
    **   t _ p f w 0 6
    **  - - - - - - - -
    **
    **  Test iauPfw06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPfw06, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double gamb, phib, psib, epsa;


       iauPfw06(2400000.5, 50123.9999, gamb, phib, psib, epsa);

       vvd(gamb, -0.2243387670997995690e-5, 1e-16,
           "iauPfw06", "gamb");
       vvd(phib,  0.4091014602391312808, 1e-12,
           "iauPfw06", "phib");
       vvd(psib, -0.9501954178013031895e-3, 1e-14,
           "iauPfw06", "psib");
       vvd(epsa,  0.4091014316587367491, 1e-12,
           "iauPfw06", "epsa");

    }

    @Test
    public void t_plan94()
    /*
    **  - - - - - - - - -
    **   t _ p l a n 9 4
    **  - - - - - - - - -
    **
    **  Test iauPlan94 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPlan94, VVD, VIV
    **
    **  This revision:  2008 November 28
    */
    {
       double pv[][] = new double[2][3];
       int j;


       j = iauPlan94(2400000.5, 1e6, 0, pv);

       vvd(pv[0][0], 0.0, 0.0, "iauPlan94", "x 1");
       vvd(pv[0][1], 0.0, 0.0, "iauPlan94", "y 1");
       vvd(pv[0][2], 0.0, 0.0, "iauPlan94", "z 1");

       vvd(pv[1][0], 0.0, 0.0, "iauPlan94", "xd 1");
       vvd(pv[1][1], 0.0, 0.0, "iauPlan94", "yd 1");
       vvd(pv[1][2], 0.0, 0.0, "iauPlan94", "zd 1");

       viv(j, -1, "iauPlan94", "j 1");

       j = iauPlan94(2400000.5, 1e6, 10, pv);

       viv(j, -1, "iauPlan94", "j 2");

       j = iauPlan94(2400000.5, -320000, 3, pv);

       vvd(pv[0][0], 0.9308038666832975759, 1e-11,
           "iauPlan94", "x 3");
       vvd(pv[0][1], 0.3258319040261346000, 1e-11,
           "iauPlan94", "y 3");
       vvd(pv[0][2], 0.1422794544481140560, 1e-11,
           "iauPlan94", "z 3");

       vvd(pv[1][0], -0.6429458958255170006e-2, 1e-11,
           "iauPlan94", "xd 3");
       vvd(pv[1][1], 0.1468570657704237764e-1, 1e-11,
           "iauPlan94", "yd 3");
       vvd(pv[1][2], 0.6406996426270981189e-2, 1e-11,
           "iauPlan94", "zd 3");

       viv(j, 1, "iauPlan94", "j 3");

       j = iauPlan94(2400000.5, 43999.9, 1, pv);

       vvd(pv[0][0], 0.2945293959257430832, 1e-11,
           "iauPlan94", "x 4");
       vvd(pv[0][1], -0.2452204176601049596, 1e-11,
           "iauPlan94", "y 4");
       vvd(pv[0][2], -0.1615427700571978153, 1e-11,
           "iauPlan94", "z 4");

       vvd(pv[1][0], 0.1413867871404614441e-1, 1e-11,
           "iauPlan94", "xd 4");
       vvd(pv[1][1], 0.1946548301104706582e-1, 1e-11,
           "iauPlan94", "yd 4");
       vvd(pv[1][2], 0.8929809783898904786e-2, 1e-11,
           "iauPlan94", "zd 4");

       viv(j, 0, "iauPlan94", "j 4");

    }

    @Test
    public void t_pmat00()
    /*
    **  - - - - - - - - -
    **   t _ p m a t 0 0
    **  - - - - - - - - -
    **
    **  Test iauPmat00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPmat00, vvd
    **
    **  This revision:  2008 November 29
    */
    {
       double rbp[][] = new double[3][3];


       iauPmat00(2400000.5, 50123.9999, rbp);

       vvd(rbp[0][0], 0.9999995505175087260, 1e-12,
           "iauPmat00", "11");
       vvd(rbp[0][1], 0.8695405883617884705e-3, 1e-14,
           "iauPmat00", "12");
       vvd(rbp[0][2], 0.3779734722239007105e-3, 1e-14,
           "iauPmat00", "13");

       vvd(rbp[1][0], -0.8695405990410863719e-3, 1e-14,
           "iauPmat00", "21");
       vvd(rbp[1][1], 0.9999996219494925900, 1e-12,
           "iauPmat00", "22");
       vvd(rbp[1][2], -0.1360775820404982209e-6, 1e-14,
           "iauPmat00", "23");

       vvd(rbp[2][0], -0.3779734476558184991e-3, 1e-14,
           "iauPmat00", "31");
       vvd(rbp[2][1], -0.1925857585832024058e-6, 1e-14,
           "iauPmat00", "32");
       vvd(rbp[2][2], 0.9999999285680153377, 1e-12,
           "iauPmat00", "33");

    }

    @Test
    public void t_pmat06()
    /*
    **  - - - - - - - - -
    **   t _ p m a t 0 6
    **  - - - - - - - - -
    **
    **  Test iauPmat06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPmat06, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double rbp[][] = new double[3][3];


       iauPmat06(2400000.5, 50123.9999, rbp);

       vvd(rbp[0][0], 0.9999995505176007047, 1e-12,
           "iauPmat06", "11");
       vvd(rbp[0][1], 0.8695404617348208406e-3, 1e-14,
           "iauPmat06", "12");
       vvd(rbp[0][2], 0.3779735201865589104e-3, 1e-14,
           "iauPmat06", "13");

       vvd(rbp[1][0], -0.8695404723772031414e-3, 1e-14,
           "iauPmat06", "21");
       vvd(rbp[1][1], 0.9999996219496027161, 1e-12,
           "iauPmat06", "22");
       vvd(rbp[1][2], -0.1361752497080270143e-6, 1e-14,
           "iauPmat06", "23");

       vvd(rbp[2][0], -0.3779734957034089490e-3, 1e-14,
           "iauPmat06", "31");
       vvd(rbp[2][1], -0.1924880847894457113e-6, 1e-14,
           "iauPmat06", "32");
       vvd(rbp[2][2], 0.9999999285679971958, 1e-12,
           "iauPmat06", "33");

    }

    @Test
    public void t_pmat76()
    /*
    **  - - - - - - - - -
    **   t _ p m a t 7 6
    **  - - - - - - - - -
    **
    **  Test iauPmat76 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPmat76, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rmatp[][] = new double[3][3];


       iauPmat76(2400000.5, 50123.9999, rmatp);

       vvd(rmatp[0][0], 0.9999995504328350733, 1e-12,
           "iauPmat76", "11");
       vvd(rmatp[0][1], 0.8696632209480960785e-3, 1e-14,
           "iauPmat76", "12");
       vvd(rmatp[0][2], 0.3779153474959888345e-3, 1e-14,
           "iauPmat76", "13");

       vvd(rmatp[1][0], -0.8696632209485112192e-3, 1e-14,
           "iauPmat76", "21");
       vvd(rmatp[1][1], 0.9999996218428560614, 1e-12,
           "iauPmat76", "22");
       vvd(rmatp[1][2], -0.1643284776111886407e-6, 1e-14,
           "iauPmat76", "23");

       vvd(rmatp[2][0], -0.3779153474950335077e-3, 1e-14,
           "iauPmat76", "31");
       vvd(rmatp[2][1], -0.1643306746147366896e-6, 1e-14,
           "iauPmat76", "32");
       vvd(rmatp[2][2], 0.9999999285899790119, 1e-12,
           "iauPmat76", "33");

    }

    @Test
    public void t_pm()
    /*
    **  - - - - -
    **   t _ p m
    **  - - - - -
    **
    **  Test iauPm function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPm, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double p[] = new double[3], r;


       p[0] =  0.3;
       p[1] =  1.2;
       p[2] = -2.5;

       r = iauPm(p);

       vvd(r, 2.789265136196270604, 1e-12, "iauPm", "");

    }

    @Test
    public void t_pmp()
    /*
    **  - - - - - -
    **   t _ p m p
    **  - - - - - -
    **
    **  Test iauPmp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPmp, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double a[] = new double[3], b[] = new double[3], amb[] = new double[3];


       a[0] = 2.0;
       a[1] = 2.0;
       a[2] = 3.0;

       b[0] = 1.0;
       b[1] = 3.0;
       b[2] = 4.0;

       iauPmp(a, b, amb);

       vvd(amb[0],  1.0, 1e-12, "iauPmp", "0");
       vvd(amb[1], -1.0, 1e-12, "iauPmp", "1");
       vvd(amb[2], -1.0, 1e-12, "iauPmp", "2");

    }

    @Test
    public void t_pn()
    /*
    **  - - - - -
    **   t _ p n
    **  - - - - -
    **
    **  Test iauPn function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPn, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double p[] = new double[3], r, u[] = new double[3];


       p[0] =  0.3;
       p[1] =  1.2;
       p[2] = -2.5;

       iauPn(p, r, u);

       vvd(r, 2.789265136196270604, 1e-12, "iauPn", "r");

       vvd(u[0], 0.1075552109073112058, 1e-12, "iauPn", "u1");
       vvd(u[1], 0.4302208436292448232, 1e-12, "iauPn", "u2");
       vvd(u[2], -0.8962934242275933816, 1e-12, "iauPn", "u3");

    }

    @Test
    public void t_pn00()
    /*
    **  - - - - - - -
    **   t _ p n 0 0
    **  - - - - - - -
    **
    **  Test iauPn00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPn00, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double dpsi, deps, epsa,
              rb[][] = new double[3][3], rp[][] = new double[3][3], rbp[][] = new double[3][3], rn[][] = new double[3][3], rbpn[][] = new double[3][3];


       dpsi = -0.9632552291149335877e-5;
       deps =  0.4063197106621141414e-4;

       iauPn00(2400000.5, 53736.0, dpsi, deps,
               epsa, rb, rp, rbp, rn, rbpn);

       vvd(epsa, 0.4090791789404229916, 1e-12, "iauPn00", "epsa");

       vvd(rb[0][0], 0.9999999999999942498, 1e-12,
           "iauPn00", "rb11");
       vvd(rb[0][1], -0.7078279744199196626e-7, 1e-18,
           "iauPn00", "rb12");
       vvd(rb[0][2], 0.8056217146976134152e-7, 1e-18,
           "iauPn00", "rb13");

       vvd(rb[1][0], 0.7078279477857337206e-7, 1e-18,
           "iauPn00", "rb21");
       vvd(rb[1][1], 0.9999999999999969484, 1e-12,
           "iauPn00", "rb22");
       vvd(rb[1][2], 0.3306041454222136517e-7, 1e-18,
           "iauPn00", "rb23");

       vvd(rb[2][0], -0.8056217380986972157e-7, 1e-18,
           "iauPn00", "rb31");
       vvd(rb[2][1], -0.3306040883980552500e-7, 1e-18,
           "iauPn00", "rb32");
       vvd(rb[2][2], 0.9999999999999962084, 1e-12,
           "iauPn00", "rb33");

       vvd(rp[0][0], 0.9999989300532289018, 1e-12,
           "iauPn00", "rp11");
       vvd(rp[0][1], -0.1341647226791824349e-2, 1e-14,
           "iauPn00", "rp12");
       vvd(rp[0][2], -0.5829880927190296547e-3, 1e-14,
           "iauPn00", "rp13");

       vvd(rp[1][0], 0.1341647231069759008e-2, 1e-14,
           "iauPn00", "rp21");
       vvd(rp[1][1], 0.9999990999908750433, 1e-12,
           "iauPn00", "rp22");
       vvd(rp[1][2], -0.3837444441583715468e-6, 1e-14,
           "iauPn00", "rp23");

       vvd(rp[2][0], 0.5829880828740957684e-3, 1e-14,
           "iauPn00", "rp31");
       vvd(rp[2][1], -0.3984203267708834759e-6, 1e-14,
           "iauPn00", "rp32");
       vvd(rp[2][2], 0.9999998300623538046, 1e-12,
           "iauPn00", "rp33");

       vvd(rbp[0][0], 0.9999989300052243993, 1e-12,
           "iauPn00", "rbp11");
       vvd(rbp[0][1], -0.1341717990239703727e-2, 1e-14,
           "iauPn00", "rbp12");
       vvd(rbp[0][2], -0.5829075749891684053e-3, 1e-14,
           "iauPn00", "rbp13");

       vvd(rbp[1][0], 0.1341718013831739992e-2, 1e-14,
           "iauPn00", "rbp21");
       vvd(rbp[1][1], 0.9999990998959191343, 1e-12,
           "iauPn00", "rbp22");
       vvd(rbp[1][2], -0.3505759733565421170e-6, 1e-14,
           "iauPn00", "rbp23");

       vvd(rbp[2][0], 0.5829075206857717883e-3, 1e-14,
           "iauPn00", "rbp31");
       vvd(rbp[2][1], -0.4315219955198608970e-6, 1e-14,
           "iauPn00", "rbp32");
       vvd(rbp[2][2], 0.9999998301093036269, 1e-12,
           "iauPn00", "rbp33");

       vvd(rn[0][0], 0.9999999999536069682, 1e-12,
           "iauPn00", "rn11");
       vvd(rn[0][1], 0.8837746144872140812e-5, 1e-16,
           "iauPn00", "rn12");
       vvd(rn[0][2], 0.3831488838252590008e-5, 1e-16,
           "iauPn00", "rn13");

       vvd(rn[1][0], -0.8837590456633197506e-5, 1e-16,
           "iauPn00", "rn21");
       vvd(rn[1][1], 0.9999999991354692733, 1e-12,
           "iauPn00", "rn22");
       vvd(rn[1][2], -0.4063198798559573702e-4, 1e-16,
           "iauPn00", "rn23");

       vvd(rn[2][0], -0.3831847930135328368e-5, 1e-16,
           "iauPn00", "rn31");
       vvd(rn[2][1], 0.4063195412258150427e-4, 1e-16,
           "iauPn00", "rn32");
       vvd(rn[2][2], 0.9999999991671806225, 1e-12,
           "iauPn00", "rn33");

       vvd(rbpn[0][0], 0.9999989440499982806, 1e-12,
           "iauPn00", "rbpn11");
       vvd(rbpn[0][1], -0.1332880253640848301e-2, 1e-14,
           "iauPn00", "rbpn12");
       vvd(rbpn[0][2], -0.5790760898731087295e-3, 1e-14,
           "iauPn00", "rbpn13");

       vvd(rbpn[1][0], 0.1332856746979948745e-2, 1e-14,
           "iauPn00", "rbpn21");
       vvd(rbpn[1][1], 0.9999991109064768883, 1e-12,
           "iauPn00", "rbpn22");
       vvd(rbpn[1][2], -0.4097740555723063806e-4, 1e-14,
           "iauPn00", "rbpn23");

       vvd(rbpn[2][0], 0.5791301929950205000e-3, 1e-14,
           "iauPn00", "rbpn31");
       vvd(rbpn[2][1], 0.4020553681373702931e-4, 1e-14,
           "iauPn00", "rbpn32");
       vvd(rbpn[2][2], 0.9999998314958529887, 1e-12,
           "iauPn00", "rbpn33");

    }

    @Test
    public void t_pn00a()
    /*
    **  - - - - - - - -
    **   t _ p n 0 0 a
    **  - - - - - - - -
    **
    **  Test iauPn00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPn00a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double dpsi, deps, epsa,
              rb[][] = new double[3][3], rp[][] = new double[3][3], rbp[][] = new double[3][3], rn[][] = new double[3][3], rbpn[][] = new double[3][3];


       iauPn00a(2400000.5, 53736.0,
                dpsi, deps, epsa, rb, rp, rbp, rn, rbpn);

       vvd(dpsi, -0.9630909107115518431e-5, 1e-12,
           "iauPn00a", "dpsi");
       vvd(deps,  0.4063239174001678710e-4, 1e-12,
           "iauPn00a", "deps");
       vvd(epsa,  0.4090791789404229916, 1e-12, "iauPn00a", "epsa");

       vvd(rb[0][0], 0.9999999999999942498, 1e-12,
           "iauPn00a", "rb11");
       vvd(rb[0][1], -0.7078279744199196626e-7, 1e-16,
           "iauPn00a", "rb12");
       vvd(rb[0][2], 0.8056217146976134152e-7, 1e-16,
           "iauPn00a", "rb13");

       vvd(rb[1][0], 0.7078279477857337206e-7, 1e-16,
           "iauPn00a", "rb21");
       vvd(rb[1][1], 0.9999999999999969484, 1e-12,
           "iauPn00a", "rb22");
       vvd(rb[1][2], 0.3306041454222136517e-7, 1e-16,
           "iauPn00a", "rb23");

       vvd(rb[2][0], -0.8056217380986972157e-7, 1e-16,
           "iauPn00a", "rb31");
       vvd(rb[2][1], -0.3306040883980552500e-7, 1e-16,
           "iauPn00a", "rb32");
       vvd(rb[2][2], 0.9999999999999962084, 1e-12,
           "iauPn00a", "rb33");

       vvd(rp[0][0], 0.9999989300532289018, 1e-12,
           "iauPn00a", "rp11");
       vvd(rp[0][1], -0.1341647226791824349e-2, 1e-14,
           "iauPn00a", "rp12");
       vvd(rp[0][2], -0.5829880927190296547e-3, 1e-14,
           "iauPn00a", "rp13");

       vvd(rp[1][0], 0.1341647231069759008e-2, 1e-14,
           "iauPn00a", "rp21");
       vvd(rp[1][1], 0.9999990999908750433, 1e-12,
           "iauPn00a", "rp22");
       vvd(rp[1][2], -0.3837444441583715468e-6, 1e-14,
           "iauPn00a", "rp23");

       vvd(rp[2][0], 0.5829880828740957684e-3, 1e-14,
           "iauPn00a", "rp31");
       vvd(rp[2][1], -0.3984203267708834759e-6, 1e-14,
           "iauPn00a", "rp32");
       vvd(rp[2][2], 0.9999998300623538046, 1e-12,
           "iauPn00a", "rp33");

       vvd(rbp[0][0], 0.9999989300052243993, 1e-12,
           "iauPn00a", "rbp11");
       vvd(rbp[0][1], -0.1341717990239703727e-2, 1e-14,
           "iauPn00a", "rbp12");
       vvd(rbp[0][2], -0.5829075749891684053e-3, 1e-14,
           "iauPn00a", "rbp13");

       vvd(rbp[1][0], 0.1341718013831739992e-2, 1e-14,
           "iauPn00a", "rbp21");
       vvd(rbp[1][1], 0.9999990998959191343, 1e-12,
           "iauPn00a", "rbp22");
       vvd(rbp[1][2], -0.3505759733565421170e-6, 1e-14,
           "iauPn00a", "rbp23");

       vvd(rbp[2][0], 0.5829075206857717883e-3, 1e-14,
           "iauPn00a", "rbp31");
       vvd(rbp[2][1], -0.4315219955198608970e-6, 1e-14,
           "iauPn00a", "rbp32");
       vvd(rbp[2][2], 0.9999998301093036269, 1e-12,
           "iauPn00a", "rbp33");

       vvd(rn[0][0], 0.9999999999536227949, 1e-12,
           "iauPn00a", "rn11");
       vvd(rn[0][1], 0.8836238544090873336e-5, 1e-14,
           "iauPn00a", "rn12");
       vvd(rn[0][2], 0.3830835237722400669e-5, 1e-14,
           "iauPn00a", "rn13");

       vvd(rn[1][0], -0.8836082880798569274e-5, 1e-14,
           "iauPn00a", "rn21");
       vvd(rn[1][1], 0.9999999991354655028, 1e-12,
           "iauPn00a", "rn22");
       vvd(rn[1][2], -0.4063240865362499850e-4, 1e-14,
           "iauPn00a", "rn23");

       vvd(rn[2][0], -0.3831194272065995866e-5, 1e-14,
           "iauPn00a", "rn31");
       vvd(rn[2][1], 0.4063237480216291775e-4, 1e-14,
           "iauPn00a", "rn32");
       vvd(rn[2][2], 0.9999999991671660338, 1e-12,
           "iauPn00a", "rn33");

       vvd(rbpn[0][0], 0.9999989440476103435, 1e-12,
           "iauPn00a", "rbpn11");
       vvd(rbpn[0][1], -0.1332881761240011763e-2, 1e-14,
           "iauPn00a", "rbpn12");
       vvd(rbpn[0][2], -0.5790767434730085751e-3, 1e-14,
           "iauPn00a", "rbpn13");

       vvd(rbpn[1][0], 0.1332858254308954658e-2, 1e-14,
           "iauPn00a", "rbpn21");
       vvd(rbpn[1][1], 0.9999991109044505577, 1e-12,
           "iauPn00a", "rbpn22");
       vvd(rbpn[1][2], -0.4097782710396580452e-4, 1e-14,
           "iauPn00a", "rbpn23");

       vvd(rbpn[2][0], 0.5791308472168152904e-3, 1e-14,
           "iauPn00a", "rbpn31");
       vvd(rbpn[2][1], 0.4020595661591500259e-4, 1e-14,
           "iauPn00a", "rbpn32");
       vvd(rbpn[2][2], 0.9999998314954572304, 1e-12,
           "iauPn00a", "rbpn33");

    }

    @Test
    public void t_pn00b()
    /*
    **  - - - - - - - -
    **   t _ p n 0 0 b
    **  - - - - - - - -
    **
    **  Test iauPn00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPn00b, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double dpsi, deps, epsa,
              rb[][] = new double[3][3], rp[][] = new double[3][3], rbp[][] = new double[3][3], rn[][] = new double[3][3], rbpn[][] = new double[3][3];


       iauPn00b(2400000.5, 53736.0, dpsi, deps, epsa,
                rb, rp, rbp, rn, rbpn);

       vvd(dpsi, -0.9632552291148362783e-5, 1e-12,
           "iauPn00b", "dpsi");
       vvd(deps,  0.4063197106621159367e-4, 1e-12,
           "iauPn00b", "deps");
       vvd(epsa,  0.4090791789404229916, 1e-12, "iauPn00b", "epsa");

       vvd(rb[0][0], 0.9999999999999942498, 1e-12,
          "iauPn00b", "rb11");
       vvd(rb[0][1], -0.7078279744199196626e-7, 1e-16,
          "iauPn00b", "rb12");
       vvd(rb[0][2], 0.8056217146976134152e-7, 1e-16,
          "iauPn00b", "rb13");

       vvd(rb[1][0], 0.7078279477857337206e-7, 1e-16,
          "iauPn00b", "rb21");
       vvd(rb[1][1], 0.9999999999999969484, 1e-12,
          "iauPn00b", "rb22");
       vvd(rb[1][2], 0.3306041454222136517e-7, 1e-16,
          "iauPn00b", "rb23");

       vvd(rb[2][0], -0.8056217380986972157e-7, 1e-16,
          "iauPn00b", "rb31");
       vvd(rb[2][1], -0.3306040883980552500e-7, 1e-16,
          "iauPn00b", "rb32");
       vvd(rb[2][2], 0.9999999999999962084, 1e-12,
          "iauPn00b", "rb33");

       vvd(rp[0][0], 0.9999989300532289018, 1e-12,
          "iauPn00b", "rp11");
       vvd(rp[0][1], -0.1341647226791824349e-2, 1e-14,
          "iauPn00b", "rp12");
       vvd(rp[0][2], -0.5829880927190296547e-3, 1e-14,
          "iauPn00b", "rp13");

       vvd(rp[1][0], 0.1341647231069759008e-2, 1e-14,
          "iauPn00b", "rp21");
       vvd(rp[1][1], 0.9999990999908750433, 1e-12,
          "iauPn00b", "rp22");
       vvd(rp[1][2], -0.3837444441583715468e-6, 1e-14,
          "iauPn00b", "rp23");

       vvd(rp[2][0], 0.5829880828740957684e-3, 1e-14,
          "iauPn00b", "rp31");
       vvd(rp[2][1], -0.3984203267708834759e-6, 1e-14,
          "iauPn00b", "rp32");
       vvd(rp[2][2], 0.9999998300623538046, 1e-12,
          "iauPn00b", "rp33");

       vvd(rbp[0][0], 0.9999989300052243993, 1e-12,
          "iauPn00b", "rbp11");
       vvd(rbp[0][1], -0.1341717990239703727e-2, 1e-14,
          "iauPn00b", "rbp12");
       vvd(rbp[0][2], -0.5829075749891684053e-3, 1e-14,
          "iauPn00b", "rbp13");

       vvd(rbp[1][0], 0.1341718013831739992e-2, 1e-14,
          "iauPn00b", "rbp21");
       vvd(rbp[1][1], 0.9999990998959191343, 1e-12,
          "iauPn00b", "rbp22");
       vvd(rbp[1][2], -0.3505759733565421170e-6, 1e-14,
          "iauPn00b", "rbp23");

       vvd(rbp[2][0], 0.5829075206857717883e-3, 1e-14,
          "iauPn00b", "rbp31");
       vvd(rbp[2][1], -0.4315219955198608970e-6, 1e-14,
          "iauPn00b", "rbp32");
       vvd(rbp[2][2], 0.9999998301093036269, 1e-12,
          "iauPn00b", "rbp33");

       vvd(rn[0][0], 0.9999999999536069682, 1e-12,
          "iauPn00b", "rn11");
       vvd(rn[0][1], 0.8837746144871248011e-5, 1e-14,
          "iauPn00b", "rn12");
       vvd(rn[0][2], 0.3831488838252202945e-5, 1e-14,
          "iauPn00b", "rn13");

       vvd(rn[1][0], -0.8837590456632304720e-5, 1e-14,
          "iauPn00b", "rn21");
       vvd(rn[1][1], 0.9999999991354692733, 1e-12,
          "iauPn00b", "rn22");
       vvd(rn[1][2], -0.4063198798559591654e-4, 1e-14,
          "iauPn00b", "rn23");

       vvd(rn[2][0], -0.3831847930134941271e-5, 1e-14,
          "iauPn00b", "rn31");
       vvd(rn[2][1], 0.4063195412258168380e-4, 1e-14,
          "iauPn00b", "rn32");
       vvd(rn[2][2], 0.9999999991671806225, 1e-12,
          "iauPn00b", "rn33");

       vvd(rbpn[0][0], 0.9999989440499982806, 1e-12,
          "iauPn00b", "rbpn11");
       vvd(rbpn[0][1], -0.1332880253640849194e-2, 1e-14,
          "iauPn00b", "rbpn12");
       vvd(rbpn[0][2], -0.5790760898731091166e-3, 1e-14,
          "iauPn00b", "rbpn13");

       vvd(rbpn[1][0], 0.1332856746979949638e-2, 1e-14,
          "iauPn00b", "rbpn21");
       vvd(rbpn[1][1], 0.9999991109064768883, 1e-12,
          "iauPn00b", "rbpn22");
       vvd(rbpn[1][2], -0.4097740555723081811e-4, 1e-14,
          "iauPn00b", "rbpn23");

       vvd(rbpn[2][0], 0.5791301929950208873e-3, 1e-14,
          "iauPn00b", "rbpn31");
       vvd(rbpn[2][1], 0.4020553681373720832e-4, 1e-14,
          "iauPn00b", "rbpn32");
       vvd(rbpn[2][2], 0.9999998314958529887, 1e-12,
          "iauPn00b", "rbpn33");

    }

    @Test
    public void t_pn06a()
    /*
    **  - - - - - - - -
    **   t _ p n 0 6 a
    **  - - - - - - - -
    **
    **  Test iauPn06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPn06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double dpsi, deps, epsa;
       double rb[][] = new double[3][3], rp[][] = new double[3][3], rbp[][] = new double[3][3], rn[][] = new double[3][3], rbpn[][]=new double[3][3];


       iauPn06a(2400000.5, 53736.0, dpsi, deps, epsa,
                rb, rp, rbp, rn, rbpn);

       vvd(dpsi, -0.9630912025820308797e-5, 1e-12,
           "iauPn06a", "dpsi");
       vvd(deps,  0.4063238496887249798e-4, 1e-12,
           "iauPn06a", "deps");
       vvd(epsa,  0.4090789763356509926, 1e-12, "iauPn06a", "epsa");

       vvd(rb[0][0], 0.9999999999999942497, 1e-12,
           "iauPn06a", "rb11");
       vvd(rb[0][1], -0.7078368960971557145e-7, 1e-14,
           "iauPn06a", "rb12");
       vvd(rb[0][2], 0.8056213977613185606e-7, 1e-14,
           "iauPn06a", "rb13");

       vvd(rb[1][0], 0.7078368694637674333e-7, 1e-14,
           "iauPn06a", "rb21");
       vvd(rb[1][1], 0.9999999999999969484, 1e-12,
           "iauPn06a", "rb22");
       vvd(rb[1][2], 0.3305943742989134124e-7, 1e-14,
           "iauPn06a", "rb23");

       vvd(rb[2][0], -0.8056214211620056792e-7, 1e-14,
           "iauPn06a", "rb31");
       vvd(rb[2][1], -0.3305943172740586950e-7, 1e-14,
           "iauPn06a", "rb32");
       vvd(rb[2][2], 0.9999999999999962084, 1e-12,
           "iauPn06a", "rb33");

       vvd(rp[0][0], 0.9999989300536854831, 1e-12,
           "iauPn06a", "rp11");
       vvd(rp[0][1], -0.1341646886204443795e-2, 1e-14,
           "iauPn06a", "rp12");
       vvd(rp[0][2], -0.5829880933488627759e-3, 1e-14,
           "iauPn06a", "rp13");

       vvd(rp[1][0], 0.1341646890569782183e-2, 1e-14,
           "iauPn06a", "rp21");
       vvd(rp[1][1], 0.9999990999913319321, 1e-12,
           "iauPn06a", "rp22");
       vvd(rp[1][2], -0.3835944216374477457e-6, 1e-14,
           "iauPn06a", "rp23");

       vvd(rp[2][0], 0.5829880833027867368e-3, 1e-14,
           "iauPn06a", "rp31");
       vvd(rp[2][1], -0.3985701514686976112e-6, 1e-14,
           "iauPn06a", "rp32");
       vvd(rp[2][2], 0.9999998300623534950, 1e-12,
           "iauPn06a", "rp33");

       vvd(rbp[0][0], 0.9999989300056797893, 1e-12,
           "iauPn06a", "rbp11");
       vvd(rbp[0][1], -0.1341717650545059598e-2, 1e-14,
           "iauPn06a", "rbp12");
       vvd(rbp[0][2], -0.5829075756493728856e-3, 1e-14,
           "iauPn06a", "rbp13");

       vvd(rbp[1][0], 0.1341717674223918101e-2, 1e-14,
           "iauPn06a", "rbp21");
       vvd(rbp[1][1], 0.9999990998963748448, 1e-12,
           "iauPn06a", "rbp22");
       vvd(rbp[1][2], -0.3504269280170069029e-6, 1e-14,
           "iauPn06a", "rbp23");

       vvd(rbp[2][0], 0.5829075211461454599e-3, 1e-14,
           "iauPn06a", "rbp31");
       vvd(rbp[2][1], -0.4316708436255949093e-6, 1e-14,
           "iauPn06a", "rbp32");
       vvd(rbp[2][2], 0.9999998301093032943, 1e-12,
           "iauPn06a", "rbp33");

       vvd(rn[0][0], 0.9999999999536227668, 1e-12,
           "iauPn06a", "rn11");
       vvd(rn[0][1], 0.8836241998111535233e-5, 1e-14,
           "iauPn06a", "rn12");
       vvd(rn[0][2], 0.3830834608415287707e-5, 1e-14,
           "iauPn06a", "rn13");

       vvd(rn[1][0], -0.8836086334870740138e-5, 1e-14,
           "iauPn06a", "rn21");
       vvd(rn[1][1], 0.9999999991354657474, 1e-12,
           "iauPn06a", "rn22");
       vvd(rn[1][2], -0.4063240188248455065e-4, 1e-14,
           "iauPn06a", "rn23");

       vvd(rn[2][0], -0.3831193642839398128e-5, 1e-14,
           "iauPn06a", "rn31");
       vvd(rn[2][1], 0.4063236803101479770e-4, 1e-14,
           "iauPn06a", "rn32");
       vvd(rn[2][2], 0.9999999991671663114, 1e-12,
           "iauPn06a", "rn33");

       vvd(rbpn[0][0], 0.9999989440480669738, 1e-12,
           "iauPn06a", "rbpn11");
       vvd(rbpn[0][1], -0.1332881418091915973e-2, 1e-14,
           "iauPn06a", "rbpn12");
       vvd(rbpn[0][2], -0.5790767447612042565e-3, 1e-14,
           "iauPn06a", "rbpn13");

       vvd(rbpn[1][0], 0.1332857911250989133e-2, 1e-14,
           "iauPn06a", "rbpn21");
       vvd(rbpn[1][1], 0.9999991109049141908, 1e-12,
           "iauPn06a", "rbpn22");
       vvd(rbpn[1][2], -0.4097767128546784878e-4, 1e-14,
           "iauPn06a", "rbpn23");

       vvd(rbpn[2][0], 0.5791308482835292617e-3, 1e-14,
           "iauPn06a", "rbpn31");
       vvd(rbpn[2][1], 0.4020580099454020310e-4, 1e-14,
           "iauPn06a", "rbpn32");
       vvd(rbpn[2][2], 0.9999998314954628695, 1e-12,
           "iauPn06a", "rbpn33");

    }

    @Test
    public void t_pn06()
    /*
    **  - - - - - - -
    **   t _ p n 0 6
    **  - - - - - - -
    **
    **  Test iauPn06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPn06, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double dpsi, deps, epsa,
              rb[][] = new double[3][3], rp[][] = new double[3][3], rbp[][] = new double[3][3], rn[][] = new double[3][3], rbpn[][] = new double[3][3];


       dpsi = -0.9632552291149335877e-5;
       deps =  0.4063197106621141414e-4;

       iauPn06(2400000.5, 53736.0, dpsi, deps,
               epsa, rb, rp, rbp, rn, rbpn);

       vvd(epsa, 0.4090789763356509926, 1e-12, "iauPn06", "epsa");

       vvd(rb[0][0], 0.9999999999999942497, 1e-12,
           "iauPn06", "rb11");
       vvd(rb[0][1], -0.7078368960971557145e-7, 1e-14,
           "iauPn06", "rb12");
       vvd(rb[0][2], 0.8056213977613185606e-7, 1e-14,
           "iauPn06", "rb13");

       vvd(rb[1][0], 0.7078368694637674333e-7, 1e-14,
           "iauPn06", "rb21");
       vvd(rb[1][1], 0.9999999999999969484, 1e-12,
           "iauPn06", "rb22");
       vvd(rb[1][2], 0.3305943742989134124e-7, 1e-14,
           "iauPn06", "rb23");

       vvd(rb[2][0], -0.8056214211620056792e-7, 1e-14,
           "iauPn06", "rb31");
       vvd(rb[2][1], -0.3305943172740586950e-7, 1e-14,
           "iauPn06", "rb32");
       vvd(rb[2][2], 0.9999999999999962084, 1e-12,
           "iauPn06", "rb33");

       vvd(rp[0][0], 0.9999989300536854831, 1e-12,
           "iauPn06", "rp11");
       vvd(rp[0][1], -0.1341646886204443795e-2, 1e-14,
           "iauPn06", "rp12");
       vvd(rp[0][2], -0.5829880933488627759e-3, 1e-14,
           "iauPn06", "rp13");

       vvd(rp[1][0], 0.1341646890569782183e-2, 1e-14,
           "iauPn06", "rp21");
       vvd(rp[1][1], 0.9999990999913319321, 1e-12,
           "iauPn06", "rp22");
       vvd(rp[1][2], -0.3835944216374477457e-6, 1e-14,
           "iauPn06", "rp23");

       vvd(rp[2][0], 0.5829880833027867368e-3, 1e-14,
           "iauPn06", "rp31");
       vvd(rp[2][1], -0.3985701514686976112e-6, 1e-14,
           "iauPn06", "rp32");
       vvd(rp[2][2], 0.9999998300623534950, 1e-12,
           "iauPn06", "rp33");

       vvd(rbp[0][0], 0.9999989300056797893, 1e-12,
           "iauPn06", "rbp11");
       vvd(rbp[0][1], -0.1341717650545059598e-2, 1e-14,
           "iauPn06", "rbp12");
       vvd(rbp[0][2], -0.5829075756493728856e-3, 1e-14,
           "iauPn06", "rbp13");

       vvd(rbp[1][0], 0.1341717674223918101e-2, 1e-14,
           "iauPn06", "rbp21");
       vvd(rbp[1][1], 0.9999990998963748448, 1e-12,
           "iauPn06", "rbp22");
       vvd(rbp[1][2], -0.3504269280170069029e-6, 1e-14,
           "iauPn06", "rbp23");

       vvd(rbp[2][0], 0.5829075211461454599e-3, 1e-14,
           "iauPn06", "rbp31");
       vvd(rbp[2][1], -0.4316708436255949093e-6, 1e-14,
           "iauPn06", "rbp32");
       vvd(rbp[2][2], 0.9999998301093032943, 1e-12,
           "iauPn06", "rbp33");

       vvd(rn[0][0], 0.9999999999536069682, 1e-12,
           "iauPn06", "rn11");
       vvd(rn[0][1], 0.8837746921149881914e-5, 1e-14,
           "iauPn06", "rn12");
       vvd(rn[0][2], 0.3831487047682968703e-5, 1e-14,
           "iauPn06", "rn13");

       vvd(rn[1][0], -0.8837591232983692340e-5, 1e-14,
           "iauPn06", "rn21");
       vvd(rn[1][1], 0.9999999991354692664, 1e-12,
           "iauPn06", "rn22");
       vvd(rn[1][2], -0.4063198798558931215e-4, 1e-14,
           "iauPn06", "rn23");

       vvd(rn[2][0], -0.3831846139597250235e-5, 1e-14,
           "iauPn06", "rn31");
       vvd(rn[2][1], 0.4063195412258792914e-4, 1e-14,
           "iauPn06", "rn32");
       vvd(rn[2][2], 0.9999999991671806293, 1e-12,
           "iauPn06", "rn33");

       vvd(rbpn[0][0], 0.9999989440504506688, 1e-12,
           "iauPn06", "rbpn11");
       vvd(rbpn[0][1], -0.1332879913170492655e-2, 1e-14,
           "iauPn06", "rbpn12");
       vvd(rbpn[0][2], -0.5790760923225655753e-3, 1e-14,
           "iauPn06", "rbpn13");

       vvd(rbpn[1][0], 0.1332856406595754748e-2, 1e-14,
           "iauPn06", "rbpn21");
       vvd(rbpn[1][1], 0.9999991109069366795, 1e-12,
           "iauPn06", "rbpn22");
       vvd(rbpn[1][2], -0.4097725651142641812e-4, 1e-14,
           "iauPn06", "rbpn23");

       vvd(rbpn[2][0], 0.5791301952321296716e-3, 1e-14,
           "iauPn06", "rbpn31");
       vvd(rbpn[2][1], 0.4020538796195230577e-4, 1e-14,
           "iauPn06", "rbpn32");
       vvd(rbpn[2][2], 0.9999998314958576778, 1e-12,
           "iauPn06", "rbpn33");

    }

    @Test
    public void t_pnm00a()
    /*
    **  - - - - - - - - -
    **   t _ p n m 0 0 a
    **  - - - - - - - - -
    **
    **  Test iauPnm00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPnm00a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rbpn[][] = new double[3][3];


       iauPnm00a(2400000.5, 50123.9999, rbpn);

       vvd(rbpn[0][0], 0.9999995832793134257, 1e-12,
           "iauPnm00a", "11");
       vvd(rbpn[0][1], 0.8372384254137809439e-3, 1e-14,
           "iauPnm00a", "12");
       vvd(rbpn[0][2], 0.3639684306407150645e-3, 1e-14,
           "iauPnm00a", "13");

       vvd(rbpn[1][0], -0.8372535226570394543e-3, 1e-14,
           "iauPnm00a", "21");
       vvd(rbpn[1][1], 0.9999996486491582471, 1e-12,
           "iauPnm00a", "22");
       vvd(rbpn[1][2], 0.4132915262664072381e-4, 1e-14,
           "iauPnm00a", "23");

       vvd(rbpn[2][0], -0.3639337004054317729e-3, 1e-14,
           "iauPnm00a", "31");
       vvd(rbpn[2][1], -0.4163386925461775873e-4, 1e-14,
           "iauPnm00a", "32");
       vvd(rbpn[2][2], 0.9999999329094390695, 1e-12,
           "iauPnm00a", "33");

    }

    @Test
    public void t_pnm00b()
    /*
    **  - - - - - - - - -
    **   t _ p n m 0 0 b
    **  - - - - - - - - -
    **
    **  Test iauPnm00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPnm00b, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rbpn[][] = new double[3][3];


       iauPnm00b(2400000.5, 50123.9999, rbpn);

       vvd(rbpn[0][0], 0.9999995832776208280, 1e-12,
           "iauPnm00b", "11");
       vvd(rbpn[0][1], 0.8372401264429654837e-3, 1e-14,
           "iauPnm00b", "12");
       vvd(rbpn[0][2], 0.3639691681450271771e-3, 1e-14,
           "iauPnm00b", "13");

       vvd(rbpn[1][0], -0.8372552234147137424e-3, 1e-14,
           "iauPnm00b", "21");
       vvd(rbpn[1][1], 0.9999996486477686123, 1e-12,
           "iauPnm00b", "22");
       vvd(rbpn[1][2], 0.4132832190946052890e-4, 1e-14,
           "iauPnm00b", "23");

       vvd(rbpn[2][0], -0.3639344385341866407e-3, 1e-14,
           "iauPnm00b", "31");
       vvd(rbpn[2][1], -0.4163303977421522785e-4, 1e-14,
           "iauPnm00b", "32");
       vvd(rbpn[2][2], 0.9999999329092049734, 1e-12,
           "iauPnm00b", "33");

    }

    @Test
    public void t_pnm06a()
    /*
    **  - - - - - - - - -
    **   t _ p n m 0 6 a
    **  - - - - - - - - -
    **
    **  Test iauPnm06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPnm06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rbpn[][] = new double[3][3];


       iauPnm06a(2400000.5, 50123.9999, rbpn);

       vvd(rbpn[0][0], 0.9999995832794205484, 1e-12,
           "iauPnm06a", "11");
       vvd(rbpn[0][1], 0.8372382772630962111e-3, 1e-14,
           "iauPnm06a", "12");
       vvd(rbpn[0][2], 0.3639684771140623099e-3, 1e-14,
           "iauPnm06a", "13");

       vvd(rbpn[1][0], -0.8372533744743683605e-3, 1e-14,
           "iauPnm06a", "21");
       vvd(rbpn[1][1], 0.9999996486492861646, 1e-12,
           "iauPnm06a", "22");
       vvd(rbpn[1][2], 0.4132905944611019498e-4, 1e-14,
           "iauPnm06a", "23");

       vvd(rbpn[2][0], -0.3639337469629464969e-3, 1e-14,
           "iauPnm06a", "31");
       vvd(rbpn[2][1], -0.4163377605910663999e-4, 1e-14,
           "iauPnm06a", "32");
       vvd(rbpn[2][2], 0.9999999329094260057, 1e-12,
           "iauPnm06a", "33");

    }

    @Test
    public void t_pnm80()
    /*
    **  - - - - - - - -
    **   t _ p n m 8 0
    **  - - - - - - - -
    **
    **  Test iauPnm80 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPnm80, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rmatpn[][] = new double[3][3];


       iauPnm80(2400000.5, 50123.9999, rmatpn);

       vvd(rmatpn[0][0], 0.9999995831934611169, 1e-12,
           "iauPnm80", "11");
       vvd(rmatpn[0][1], 0.8373654045728124011e-3, 1e-14,
           "iauPnm80", "12");
       vvd(rmatpn[0][2], 0.3639121916933106191e-3, 1e-14,
           "iauPnm80", "13");

       vvd(rmatpn[1][0], -0.8373804896118301316e-3, 1e-14,
           "iauPnm80", "21");
       vvd(rmatpn[1][1], 0.9999996485439674092, 1e-12,
           "iauPnm80", "22");
       vvd(rmatpn[1][2], 0.4130202510421549752e-4, 1e-14,
           "iauPnm80", "23");

       vvd(rmatpn[2][0], -0.3638774789072144473e-3, 1e-14,
           "iauPnm80", "31");
       vvd(rmatpn[2][1], -0.4160674085851722359e-4, 1e-14,
           "iauPnm80", "32");
       vvd(rmatpn[2][2], 0.9999999329310274805, 1e-12,
           "iauPnm80", "33");

    }

    @Test
    public void t_pom00()
    /*
    **  - - - - - - - -
    **   t _ p o m 0 0
    **  - - - - - - - -
    **
    **  Test iauPom00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPom00, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double xp, yp, sp, rpom[][] = new double[3][3];


       xp =  2.55060238e-7;
       yp =  1.860359247e-6;
       sp = -0.1367174580728891460e-10;

       iauPom00(xp, yp, sp, rpom);

       vvd(rpom[0][0], 0.9999999999999674721, 1e-12,
           "iauPom00", "11");
       vvd(rpom[0][1], -0.1367174580728846989e-10, 1e-16,
           "iauPom00", "12");
       vvd(rpom[0][2], 0.2550602379999972345e-6, 1e-16,
           "iauPom00", "13");

       vvd(rpom[1][0], 0.1414624947957029801e-10, 1e-16,
           "iauPom00", "21");
       vvd(rpom[1][1], 0.9999999999982695317, 1e-12,
           "iauPom00", "22");
       vvd(rpom[1][2], -0.1860359246998866389e-5, 1e-16,
           "iauPom00", "23");

       vvd(rpom[2][0], -0.2550602379741215021e-6, 1e-16,
           "iauPom00", "31");
       vvd(rpom[2][1], 0.1860359247002414021e-5, 1e-16,
           "iauPom00", "32");
       vvd(rpom[2][2], 0.9999999999982370039, 1e-12,
           "iauPom00", "33");

    }

    @Test
    public void t_ppp()
    /*
    **  - - - - - -
    **   t _ p p p
    **  - - - - - -
    **
    **  Test iauPpp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPpp, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double a[] = new double[3], b[] = new double[3], apb[] = new double[3];


       a[0] = 2.0;
       a[1] = 2.0;
       a[2] = 3.0;

       b[0] = 1.0;
       b[1] = 3.0;
       b[2] = 4.0;

       iauPpp(a, b, apb);

       vvd(apb[0], 3.0, 1e-12, "iauPpp", "0");
       vvd(apb[1], 5.0, 1e-12, "iauPpp", "1");
       vvd(apb[2], 7.0, 1e-12, "iauPpp", "2");

    }

    @Test
    public void t_ppsp()
    /*
    **  - - - - - - -
    **   t _ p p s p
    **  - - - - - - -
    **
    **  Test iauPpsp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPpsp, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double a[] = new double[3], s, b[] = new double[3], apsb[] = new double[3];


       a[0] = 2.0;
       a[1] = 2.0;
       a[2] = 3.0;

       s = 5.0;

       b[0] = 1.0;
       b[1] = 3.0;
       b[2] = 4.0;

       iauPpsp(a, s, b, apsb);

       vvd(apsb[0], 7.0, 1e-12, "iauPpsp", "0");
       vvd(apsb[1], 17.0, 1e-12, "iauPpsp", "1");
       vvd(apsb[2], 23.0, 1e-12, "iauPpsp", "2");

    }

    @Test
    public void t_pr00()
    /*
    **  - - - - - - -
    **   t _ p r 0 0
    **  - - - - - - -
    **
    **  Test iauPr00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPr00, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double dpsipr, depspr;

       iauPr00(2400000.5, 53736, dpsipr, depspr);

       vvd(dpsipr, -0.8716465172668347629e-7, 1e-22,
          "iauPr00", "dpsipr");
       vvd(depspr, -0.7342018386722813087e-8, 1e-22,
          "iauPr00", "depspr");

    }

    @Test
    public void t_prec76()
    /*
    **  - - - - - - - - -
    **   t _ p r e c 7 6
    **  - - - - - - - - -
    **
    **  Test iauPrec76 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPrec76, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double ep01, ep02, ep11, ep12, zeta, z, theta;


       ep01 = 2400000.5;
       ep02 = 33282.0;
       ep11 = 2400000.5;
       ep12 = 51544.0;

       iauPrec76(ep01, ep02, ep11, ep12, zeta, z, theta);

       vvd(zeta,  0.5588961642000161243e-2, 1e-12,
           "iauPrec76", "zeta");
       vvd(z,     0.5589922365870680624e-2, 1e-12,
           "iauPrec76", "z");
       vvd(theta, 0.4858945471687296760e-2, 1e-12,
           "iauPrec76", "theta");

    }

    @Test
    public void t_pv2p()
    /*
    **  - - - - - - -
    **   t _ p v 2 p
    **  - - - - - - -
    **
    **  Test iauPv2p function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPv2p, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double pv[][] = new double[2][3], p[] = new double[3];


       pv[0][0] =  0.3;
       pv[0][1] =  1.2;
       pv[0][2] = -2.5;

       pv[1][0] = -0.5;
       pv[1][1] =  3.1;
       pv[1][2] =  0.9;

       iauPv2p(pv, p);

       vvd(p[0],  0.3, 0.0, "iauPv2p", "1");
       vvd(p[1],  1.2, 0.0, "iauPv2p", "2");
       vvd(p[2], -2.5, 0.0, "iauPv2p", "3");

    }

    @Test
    public void t_pv2s()
    /*
    **  - - - - - - -
    **   t _ p v 2 s
    **  - - - - - - -
    **
    **  Test iauPv2s function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPv2s, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double pv[][] = new double[2][3], theta, phi, r, td, pd, rd;


       pv[0][0] = -0.4514964673880165;
       pv[0][1] =  0.03093394277342585;
       pv[0][2] =  0.05594668105108779;

       pv[1][0] =  1.292270850663260e-5;
       pv[1][1] =  2.652814182060692e-6;
       pv[1][2] =  2.568431853930293e-6;

       iauPv2s(pv, theta, phi, r, td, pd, rd);

       vvd(theta, 3.073185307179586515, 1e-12, "iauPv2s", "theta");
       vvd(phi, 0.1229999999999999992, 1e-12, "iauPv2s", "phi");
       vvd(r, 0.4559999999999999757, 1e-12, "iauPv2s", "r");
       vvd(td, -0.7800000000000000364e-5, 1e-16, "iauPv2s", "td");
       vvd(pd, 0.9010000000000001639e-5, 1e-16, "iauPv2s", "pd");
       vvd(rd, -0.1229999999999999832e-4, 1e-16, "iauPv2s", "rd");

    }

    @Test
    public void t_pvdpv()
    /*
    **  - - - - - - - -
    **   t _ p v d p v
    **  - - - - - - - -
    **
    **  Test iauPvdpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPvdpv, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double a[][] = new double[2][3], b[][] = new double[2][3], adb[] = new double[2];


       a[0][0] = 2.0;
       a[0][1] = 2.0;
       a[0][2] = 3.0;

       a[1][0] = 6.0;
       a[1][1] = 0.0;
       a[1][2] = 4.0;

       b[0][0] = 1.0;
       b[0][1] = 3.0;
       b[0][2] = 4.0;

       b[1][0] = 0.0;
       b[1][1] = 2.0;
       b[1][2] = 8.0;

       iauPvdpv(a, b, adb);

       vvd(adb[0], 20.0, 1e-12, "iauPvdpv", "1");
       vvd(adb[1], 50.0, 1e-12, "iauPvdpv", "2");

    }

    @Test
    public void t_pvm()
    /*
    **  - - - - - -
    **   t _ p v m
    **  - - - - - -
    **
    **  Test iauPvm function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPvm, vvd
    **
    **  This revision:  2008 May 25
    */
    {
       double pv[][] = new double[2][3], r, s;


       pv[0][0] =  0.3;
       pv[0][1] =  1.2;
       pv[0][2] = -2.5;

       pv[1][0] =  0.45;
       pv[1][1] = -0.25;
       pv[1][2] =  1.1;

       iauPvm(pv, r, s);

       vvd(r, 2.789265136196270604, 1e-12, "iauPvm", "r");
       vvd(s, 1.214495780149111922, 1e-12, "iauPvm", "s");

    }

    @Test
    public void t_pvmpv()
    /*
    **  - - - - - - - -
    **   t _ p v m p v
    **  - - - - - - - -
    **
    **  Test iauPvmpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPvmpv, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double a[][] = new double[2][3], b[][] = new double[2][3], amb[][] = new double[2][3];


       a[0][0] = 2.0;
       a[0][1] = 2.0;
       a[0][2] = 3.0;

       a[1][0] = 5.0;
       a[1][1] = 6.0;
       a[1][2] = 3.0;

       b[0][0] = 1.0;
       b[0][1] = 3.0;
       b[0][2] = 4.0;

       b[1][0] = 3.0;
       b[1][1] = 2.0;
       b[1][2] = 1.0;

       iauPvmpv(a, b, amb);

       vvd(amb[0][0],  1.0, 1e-12, "iauPvmpv", "11");
       vvd(amb[0][1], -1.0, 1e-12, "iauPvmpv", "21");
       vvd(amb[0][2], -1.0, 1e-12, "iauPvmpv", "31");

       vvd(amb[1][0],  2.0, 1e-12, "iauPvmpv", "12");
       vvd(amb[1][1],  4.0, 1e-12, "iauPvmpv", "22");
       vvd(amb[1][2],  2.0, 1e-12, "iauPvmpv", "32");

    }

    @Test
    public void t_pvppv()
    /*
    **  - - - - - - - -
    **   t _ p v p p v
    **  - - - - - - - -
    **
    **  Test iauPvppv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPvppv, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double a[][] = new double[2][3], b[][] = new double[2][3], apb[][] = new double[2][3];


       a[0][0] = 2.0;
       a[0][1] = 2.0;
       a[0][2] = 3.0;

       a[1][0] = 5.0;
       a[1][1] = 6.0;
       a[1][2] = 3.0;

       b[0][0] = 1.0;
       b[0][1] = 3.0;
       b[0][2] = 4.0;

       b[1][0] = 3.0;
       b[1][1] = 2.0;
       b[1][2] = 1.0;

       iauPvppv(a, b, apb);

       vvd(apb[0][0], 3.0, 1e-12, "iauPvppv", "p1");
       vvd(apb[0][1], 5.0, 1e-12, "iauPvppv", "p2");
       vvd(apb[0][2], 7.0, 1e-12, "iauPvppv", "p3");

       vvd(apb[1][0], 8.0, 1e-12, "iauPvppv", "v1");
       vvd(apb[1][1], 8.0, 1e-12, "iauPvppv", "v2");
       vvd(apb[1][2], 4.0, 1e-12, "iauPvppv", "v3");

    }

    @Test
    public void t_pvstar()
    /*
    **  - - - - - - - - -
    **   t _ p v s t a r
    **  - - - - - - - - -
    **
    **  Test iauPvstar function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPvstar, vvd, viv
    **
    **  This revision:  2009 November 6
    */
    {
       double pv[][] = new double[2][3], ra, dec, pmr, pmd, px, rv;
       int j;


       pv[0][0] =  126668.5912743160601;
       pv[0][1] =  2136.792716839935195;
       pv[0][2] = -245251.2339876830091;

       pv[1][0] = -0.4051854035740712739e-2;
       pv[1][1] = -0.6253919754866173866e-2;
       pv[1][2] =  0.1189353719774107189e-1;

       j = iauPvstar(pv, ra, dec, pmr, pmd, px, rv);

       vvd(ra, 0.1686756e-1, 1e-12, "iauPvstar", "ra");
       vvd(dec, -1.093989828, 1e-12, "iauPvstar", "dec");
       vvd(pmr, -0.178323516e-4, 1e-16, "iauPvstar", "pmr");
       vvd(pmd, 0.2336024047e-5, 1e-16, "iauPvstar", "pmd");
       vvd(px, 0.74723, 1e-12, "iauPvstar", "px");
       vvd(rv, -21.6, 1e-11, "iauPvstar", "rv");

       viv(j, 0, "iauPvstar", "j");

    }

    @Test
    public void t_pvu()
    /*
    **  - - - - - -
    **   t _ p v u
    **  - - - - - -
    **
    **  Test iauPvu function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPvu, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double pv[][] = new double[2][3], upv[][] = new double[2][3];


       pv[0][0] =  126668.5912743160734;
       pv[0][1] =  2136.792716839935565;
       pv[0][2] = -245251.2339876830229;

       pv[1][0] = -0.4051854035740713039e-2;
       pv[1][1] = -0.6253919754866175788e-2;
       pv[1][2] =  0.1189353719774107615e-1;

       iauPvu(2920.0, pv, upv);

       vvd(upv[0][0], 126656.7598605317105, 1e-12,
           "iauPvu", "p1");
       vvd(upv[0][1], 2118.531271155726332, 1e-12,
           "iauPvu", "p2");
       vvd(upv[0][2], -245216.5048590656190, 1e-12,
           "iauPvu", "p3");

       vvd(upv[1][0], -0.4051854035740713039e-2, 1e-12,
           "iauPvu", "v1");
       vvd(upv[1][1], -0.6253919754866175788e-2, 1e-12,
           "iauPvu", "v2");
       vvd(upv[1][2], 0.1189353719774107615e-1, 1e-12,
           "iauPvu", "v3");

    }

    @Test
    public void t_pvup()
    /*
    **  - - - - - - -
    **   t _ p v u p
    **  - - - - - - -
    **
    **  Test iauPvup function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPvup, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double pv[][] = new double[2][3], p[] = new double[3];


       pv[0][0] =  126668.5912743160734;
       pv[0][1] =  2136.792716839935565;
       pv[0][2] = -245251.2339876830229;

       pv[1][0] = -0.4051854035740713039e-2;
       pv[1][1] = -0.6253919754866175788e-2;
       pv[1][2] =  0.1189353719774107615e-1;

       iauPvup(2920.0, pv, p);

       vvd(p[0],  126656.7598605317105,   1e-12, "iauPvup", "1");
       vvd(p[1],    2118.531271155726332, 1e-12, "iauPvup", "2");
       vvd(p[2], -245216.5048590656190,   1e-12, "iauPvup", "3");

    }

    @Test
    public void t_pvxpv()
    /*
    **  - - - - - - - -
    **   t _ p v x p v
    **  - - - - - - - -
    **
    **  Test iauPvxpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPvxpv, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double a[][] = new double[2][3], b[][] = new double[2][3], axb[][] = new double[2][3];


       a[0][0] = 2.0;
       a[0][1] = 2.0;
       a[0][2] = 3.0;

       a[1][0] = 6.0;
       a[1][1] = 0.0;
       a[1][2] = 4.0;

       b[0][0] = 1.0;
       b[0][1] = 3.0;
       b[0][2] = 4.0;

       b[1][0] = 0.0;
       b[1][1] = 2.0;
       b[1][2] = 8.0;

       iauPvxpv(a, b, axb);

       vvd(axb[0][0],  -1.0, 1e-12, "iauPvxpv", "p1");
       vvd(axb[0][1],  -5.0, 1e-12, "iauPvxpv", "p2");
       vvd(axb[0][2],   4.0, 1e-12, "iauPvxpv", "p3");

       vvd(axb[1][0],  -2.0, 1e-12, "iauPvxpv", "v1");
       vvd(axb[1][1], -36.0, 1e-12, "iauPvxpv", "v2");
       vvd(axb[1][2],  22.0, 1e-12, "iauPvxpv", "v3");

    }

    @Test
    public void t_pxp()
    /*
    **  - - - - - -
    **   t _ p x p
    **  - - - - - -
    **
    **  Test iauPxp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPxp, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double a[] = new double[3], b[] = new double[3], axb[] = new double[3];


       a[0] = 2.0;
       a[1] = 2.0;
       a[2] = 3.0;

       b[0] = 1.0;
       b[1] = 3.0;
       b[2] = 4.0;

       iauPxp(a, b, axb);

       vvd(axb[0], -1.0, 1e-12, "iauPxp", "1");
       vvd(axb[1], -5.0, 1e-12, "iauPxp", "2");
       vvd(axb[2],  4.0, 1e-12, "iauPxp", "3");

    }

    @Test
    public void t_rm2v()
    /*
    **  - - - - - - -
    **   t _ r m 2 v
    **  - - - - - - -
    **
    **  Test iauRm2v function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauRm2v, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double r[][] = new double[3][3], w[] = new double[3];


       r[0][0] =  0.00;
       r[0][1] = -0.80;
       r[0][2] = -0.60;

       r[1][0] =  0.80;
       r[1][1] = -0.36;
       r[1][2] =  0.48;

       r[2][0] =  0.60;
       r[2][1] =  0.48;
       r[2][2] = -0.64;

       iauRm2v(r, w);

       vvd(w[0],  0.0,                  1e-12, "iauRm2v", "1");
       vvd(w[1],  1.413716694115406957, 1e-12, "iauRm2v", "2");
       vvd(w[2], -1.884955592153875943, 1e-12, "iauRm2v", "3");

    }

    @Test
    public void t_rv2m()
    /*
    **  - - - - - - -
    **   t _ r v 2 m
    **  - - - - - - -
    **
    **  Test iauRv2m function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauRv2m, vvd
    **
    **  This revision:  2008 May 27
    */
    {
       double w[] = new double[3], r[][] = new double[3][3];


       w[0] =  0.0;
       w[1] =  1.41371669;
       w[2] = -1.88495559;

       iauRv2m(w, r);

       vvd(r[0][0], -0.7071067782221119905, 1e-14, "iauRv2m", "11");
       vvd(r[0][1], -0.5656854276809129651, 1e-14, "iauRv2m", "12");
       vvd(r[0][2], -0.4242640700104211225, 1e-14, "iauRv2m", "13");

       vvd(r[1][0],  0.5656854276809129651, 1e-14, "iauRv2m", "21");
       vvd(r[1][1], -0.0925483394532274246, 1e-14, "iauRv2m", "22");
       vvd(r[1][2], -0.8194112531408833269, 1e-14, "iauRv2m", "23");

       vvd(r[2][0],  0.4242640700104211225, 1e-14, "iauRv2m", "31");
       vvd(r[2][1], -0.8194112531408833269, 1e-14, "iauRv2m", "32");
       vvd(r[2][2],  0.3854415612311154341, 1e-14, "iauRv2m", "33");

    }

    @Test
    public void t_rx()
    /*
    **  - - - - -
    **   t _ r x
    **  - - - - -
    **
    **  Test iauRx function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauRx, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double phi, r[][] = new double[3][3];


       phi = 0.3456789;

       r[0][0] = 2.0;
       r[0][1] = 3.0;
       r[0][2] = 2.0;

       r[1][0] = 3.0;
       r[1][1] = 2.0;
       r[1][2] = 3.0;

       r[2][0] = 3.0;
       r[2][1] = 4.0;
       r[2][2] = 5.0;

       iauRx(phi, r);

       vvd(r[0][0], 2.0, 0.0, "iauRx", "11");
       vvd(r[0][1], 3.0, 0.0, "iauRx", "12");
       vvd(r[0][2], 2.0, 0.0, "iauRx", "13");

       vvd(r[1][0], 3.839043388235612460, 1e-12, "iauRx", "21");
       vvd(r[1][1], 3.237033249594111899, 1e-12, "iauRx", "22");
       vvd(r[1][2], 4.516714379005982719, 1e-12, "iauRx", "23");

       vvd(r[2][0], 1.806030415924501684, 1e-12, "iauRx", "31");
       vvd(r[2][1], 3.085711545336372503, 1e-12, "iauRx", "32");
       vvd(r[2][2], 3.687721683977873065, 1e-12, "iauRx", "33");

    }

    @Test
    public void t_rxp()
    /*
    **  - - - - - -
    **   t _ r x p
    **  - - - - - -
    **
    **  Test iauRxp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauRxp, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double r[][] = new double[3][3], p[] = new double[3], rp[] = new double[3];


       r[0][0] = 2.0;
       r[0][1] = 3.0;
       r[0][2] = 2.0;

       r[1][0] = 3.0;
       r[1][1] = 2.0;
       r[1][2] = 3.0;

       r[2][0] = 3.0;
       r[2][1] = 4.0;
       r[2][2] = 5.0;

       p[0] = 0.2;
       p[1] = 1.5;
       p[2] = 0.1;

       iauRxp(r, p, rp);

       vvd(rp[0], 5.1, 1e-12, "iauRxp", "1");
       vvd(rp[1], 3.9, 1e-12, "iauRxp", "2");
       vvd(rp[2], 7.1, 1e-12, "iauRxp", "3");

    }

    @Test
    public void t_rxpv()
    /*
    **  - - - - - - -
    **   t _ r x p v
    **  - - - - - - -
    **
    **  Test iauRxpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauRxpv, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double r[][] = new double[3][3], pv[][] = new double[2][3], rpv[][] = new double[2][3];


       r[0][0] = 2.0;
       r[0][1] = 3.0;
       r[0][2] = 2.0;

       r[1][0] = 3.0;
       r[1][1] = 2.0;
       r[1][2] = 3.0;

       r[2][0] = 3.0;
       r[2][1] = 4.0;
       r[2][2] = 5.0;

       pv[0][0] = 0.2;
       pv[0][1] = 1.5;
       pv[0][2] = 0.1;

       pv[1][0] = 1.5;
       pv[1][1] = 0.2;
       pv[1][2] = 0.1;

       iauRxpv(r, pv, rpv);

       vvd(rpv[0][0], 5.1, 1e-12, "iauRxpv", "11");
       vvd(rpv[1][0], 3.8, 1e-12, "iauRxpv", "12");

       vvd(rpv[0][1], 3.9, 1e-12, "iauRxpv", "21");
       vvd(rpv[1][1], 5.2, 1e-12, "iauRxpv", "22");

       vvd(rpv[0][2], 7.1, 1e-12, "iauRxpv", "31");
       vvd(rpv[1][2], 5.8, 1e-12, "iauRxpv", "32");

    }

    @Test
    public void t_rxr()
    /*
    **  - - - - - -
    **   t _ r x r
    **  - - - - - -
    **
    **  Test iauRxr function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauRxr, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double a[][] = new double[3][3], b[][] = new double[3][3], atb[][] = new double[3][3];


       a[0][0] = 2.0;
       a[0][1] = 3.0;
       a[0][2] = 2.0;

       a[1][0] = 3.0;
       a[1][1] = 2.0;
       a[1][2] = 3.0;

       a[2][0] = 3.0;
       a[2][1] = 4.0;
       a[2][2] = 5.0;

       b[0][0] = 1.0;
       b[0][1] = 2.0;
       b[0][2] = 2.0;

       b[1][0] = 4.0;
       b[1][1] = 1.0;
       b[1][2] = 1.0;

       b[2][0] = 3.0;
       b[2][1] = 0.0;
       b[2][2] = 1.0;

       iauRxr(a, b, atb);

       vvd(atb[0][0], 20.0, 1e-12, "iauRxr", "11");
       vvd(atb[0][1],  7.0, 1e-12, "iauRxr", "12");
       vvd(atb[0][2],  9.0, 1e-12, "iauRxr", "13");

       vvd(atb[1][0], 20.0, 1e-12, "iauRxr", "21");
       vvd(atb[1][1],  8.0, 1e-12, "iauRxr", "22");
       vvd(atb[1][2], 11.0, 1e-12, "iauRxr", "23");

       vvd(atb[2][0], 34.0, 1e-12, "iauRxr", "31");
       vvd(atb[2][1], 10.0, 1e-12, "iauRxr", "32");
       vvd(atb[2][2], 15.0, 1e-12, "iauRxr", "33");

    }

    @Test
    public void t_ry()
    /*
    **  - - - - -
    **   t _ r y
    **  - - - - -
    **
    **  Test iauRy function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauRy, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double theta, r[][] = new double[3][3];


       theta = 0.3456789;

       r[0][0] = 2.0;
       r[0][1] = 3.0;
       r[0][2] = 2.0;

       r[1][0] = 3.0;
       r[1][1] = 2.0;
       r[1][2] = 3.0;

       r[2][0] = 3.0;
       r[2][1] = 4.0;
       r[2][2] = 5.0;

       iauRy(theta, r);

       vvd(r[0][0], 0.8651847818978159930, 1e-12, "iauRy", "11");
       vvd(r[0][1], 1.467194920539316554, 1e-12, "iauRy", "12");
       vvd(r[0][2], 0.1875137911274457342, 1e-12, "iauRy", "13");

       vvd(r[1][0], 3, 1e-12, "iauRy", "21");
       vvd(r[1][1], 2, 1e-12, "iauRy", "22");
       vvd(r[1][2], 3, 1e-12, "iauRy", "23");

       vvd(r[2][0], 3.500207892850427330, 1e-12, "iauRy", "31");
       vvd(r[2][1], 4.779889022262298150, 1e-12, "iauRy", "32");
       vvd(r[2][2], 5.381899160903798712, 1e-12, "iauRy", "33");

    }

    @Test
    public void t_rz()
    /*
    **  - - - - -
    **   t _ r z
    **  - - - - -
    **
    **  Test iauRz function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauRz, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double psi, r[][] = new double[3][3];


       psi = 0.3456789;

       r[0][0] = 2.0;
       r[0][1] = 3.0;
       r[0][2] = 2.0;

       r[1][0] = 3.0;
       r[1][1] = 2.0;
       r[1][2] = 3.0;

       r[2][0] = 3.0;
       r[2][1] = 4.0;
       r[2][2] = 5.0;

       iauRz(psi, r);

       vvd(r[0][0], 2.898197754208926769, 1e-12, "iauRz", "11");
       vvd(r[0][1], 3.500207892850427330, 1e-12, "iauRz", "12");
       vvd(r[0][2], 2.898197754208926769, 1e-12, "iauRz", "13");

       vvd(r[1][0], 2.144865911309686813, 1e-12, "iauRz", "21");
       vvd(r[1][1], 0.865184781897815993, 1e-12, "iauRz", "22");
       vvd(r[1][2], 2.144865911309686813, 1e-12, "iauRz", "23");

       vvd(r[2][0], 3.0, 1e-12, "iauRz", "31");
       vvd(r[2][1], 4.0, 1e-12, "iauRz", "32");
       vvd(r[2][2], 5.0, 1e-12, "iauRz", "33");

    }

    @Test
    public void t_s00a()
    /*
    **  - - - - - - -
    **   t _ s 0 0 a
    **  - - - - - - -
    **
    **  Test iauS00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS00a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double s;


       s = iauS00a(2400000.5, 52541.0);

       vvd(s, -0.1340684448919163584e-7, 1e-18, "iauS00a", "");

    }

    @Test
    public void t_s00b()
    /*
    **  - - - - - - -
    **   t _ s 0 0 b
    **  - - - - - - -
    **
    **  Test iauS00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS00b, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double s;


       s = iauS00b(2400000.5, 52541.0);

       vvd(s, -0.1340695782951026584e-7, 1e-18, "iauS00b", "");

    }

    @Test
    public void t_s00()
    /*
    **  - - - - - -
    **   t _ s 0 0
    **  - - - - - -
    **
    **  Test iauS00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS00, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double x, y, s;


       x = 0.5791308486706011000e-3;
       y = 0.4020579816732961219e-4;

       s = iauS00(2400000.5, 53736.0, x, y);

       vvd(s, -0.1220036263270905693e-7, 1e-18, "iauS00", "");

    }

    @Test
    public void t_s06a()
    /*
    **  - - - - - - -
    **   t _ s 0 6 a
    **  - - - - - - -
    **
    **  Test iauS06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double s;


       s = iauS06a(2400000.5, 52541.0);

       vvd(s, -0.1340680437291812383e-7, 1e-18, "iauS06a", "");

    }

    @Test
    public void t_s06()
    /*
    **  - - - - - -
    **   t _ s 0 6
    **  - - - - - -
    **
    **  Test iauS06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS06, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double x, y, s;


       x = 0.5791308486706011000e-3;
       y = 0.4020579816732961219e-4;

       s = iauS06(2400000.5, 53736.0, x, y);

       vvd(s, -0.1220032213076463117e-7, 1e-18, "iauS06", "");

    }

    @Test
    public void t_s2c()
    /*
    **  - - - - - -
    **   t _ s 2 c
    **  - - - - - -
    **
    **  Test iauS2c function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS2c, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double c[] = new double[3];


       iauS2c(3.0123, -0.999, c);

       vvd(c[0], -0.5366267667260523906, 1e-12, "iauS2c", "1");
       vvd(c[1],  0.0697711109765145365, 1e-12, "iauS2c", "2");
       vvd(c[2], -0.8409302618566214041, 1e-12, "iauS2c", "3");

    }

    @Test
    public void t_s2p()
    /*
    **  - - - - - -
    **   t _ s 2 p
    **  - - - - - -
    **
    **  Test iauS2p function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS2p, vvd
    **
    **  This revision:  2008 May 25
    */
    {
       double p[] = new double[3];


       iauS2p(-3.21, 0.123, 0.456, p);

       vvd(p[0], -0.4514964673880165228, 1e-12, "iauS2p", "x");
       vvd(p[1],  0.0309339427734258688, 1e-12, "iauS2p", "y");
       vvd(p[2],  0.0559466810510877933, 1e-12, "iauS2p", "z");

    }

    @Test
    public void t_s2pv()
    /*
    **  - - - - - - -
    **   t _ s 2 p v
    **  - - - - - - -
    **
    **  Test iauS2pv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS2pv, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double pv[][] = new double[2][3];


       iauS2pv(-3.21, 0.123, 0.456, -7.8e-6, 9.01e-6, -1.23e-5, pv);

       vvd(pv[0][0], -0.4514964673880165228, 1e-12, "iauS2pv", "x");
       vvd(pv[0][1],  0.0309339427734258688, 1e-12, "iauS2pv", "y");
       vvd(pv[0][2],  0.0559466810510877933, 1e-12, "iauS2pv", "z");

       vvd(pv[1][0],  0.1292270850663260170e-4, 1e-16,
           "iauS2pv", "vx");
       vvd(pv[1][1],  0.2652814182060691422e-5, 1e-16,
           "iauS2pv", "vy");
       vvd(pv[1][2],  0.2568431853930292259e-5, 1e-16,
           "iauS2pv", "vz");

    }

    @Test
    public void t_s2xpv()
    /*
    **  - - - - - - - -
    **   t _ s 2 x p v
    **  - - - - - - - -
    **
    **  Test iauS2xpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS2xpv, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double s1, s2, pv[][] = new double[2][3], spv[][]= new double[2][3];


       s1 = 2.0;
       s2 = 3.0;

       pv[0][0] =  0.3;
       pv[0][1] =  1.2;
       pv[0][2] = -2.5;

       pv[1][0] =  0.5;
       pv[1][1] =  2.3;
       pv[1][2] = -0.4;

       iauS2xpv(s1, s2, pv, spv);

       vvd(spv[0][0],  0.6, 1e-12, "iauS2xpv", "p1");
       vvd(spv[0][1],  2.4, 1e-12, "iauS2xpv", "p2");
       vvd(spv[0][2], -5.0, 1e-12, "iauS2xpv", "p3");

       vvd(spv[1][0],  1.5, 1e-12, "iauS2xpv", "v1");
       vvd(spv[1][1],  6.9, 1e-12, "iauS2xpv", "v2");
       vvd(spv[1][2], -1.2, 1e-12, "iauS2xpv", "v3");

    }

    @Test
    public void t_sepp()
    /*
    **  - - - - - - -
    **   t _ s e p p
    **  - - - - - - -
    **
    **  Test iauSepp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauSepp, vvd
    **
    **  This revision:  2008 November 29
    */
    {
       double a[] = new double[3], b[] = new double[3], s;


       a[0] =  1.0;
       a[1] =  0.1;
       a[2] =  0.2;

       b[0] = -3.0;
       b[1] =  1e-3;
       b[2] =  0.2;

       s = iauSepp(a, b);

       vvd(s, 2.860391919024660768, 1e-12, "iauSepp", "");

    }

    @Test
    public void t_seps()
    /*
    **  - - - - - - -
    **   t _ s e p s
    **  - - - - - - -
    **
    **  Test iauSeps function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauSeps, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       double al, ap, bl, bp, s;


       al =  1.0;
       ap =  0.1;

       bl =  0.2;
       bp = -3.0;

       s = iauSeps(al, ap, bl, bp);

       vvd(s, 2.346722016996998842, 1e-14, "iauSeps", "");

    }

    @Test
    public void t_sp00()
    /*
    **  - - - - - - -
    **   t _ s p 0 0
    **  - - - - - - -
    **
    **  Test iauSp00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauSp00, vvd
    **
    **  This revision:  2008 May 25
    */
    {
       vvd(iauSp00(2400000.5, 52541.0),
           -0.6216698469981019309e-11, 1e-12, "iauSp00", "");

    }

    @Test
    public void t_starpm()
    /*
    **  - - - - - - - - -
    **   t _ s t a r p m
    **  - - - - - - - - -
    **
    **  Test iauStarpm function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauStarpm, vvd, viv
    **
    **  This revision:  2008 November 30
    */
    {
       double ra1, dec1, pmr1, pmd1, px1, rv1;
       double ra2, dec2, pmr2, pmd2, px2, rv2;
       int j;


       ra1 =   0.01686756;
       dec1 = -1.093989828;
       pmr1 = -1.78323516e-5;
       pmd1 =  2.336024047e-6;
       px1 =   0.74723;
       rv1 = -21.6;

       j = iauStarpm(ra1, dec1, pmr1, pmd1, px1, rv1,
                     2400000.5, 50083.0, 2400000.5, 53736.0,
                     ra2, dec2, pmr2, pmd2, px2, rv2);

       vvd(ra2, 0.01668919069414242368, 1e-13,
           "iauStarpm", "ra");
       vvd(dec2, -1.093966454217127879, 1e-13,
           "iauStarpm", "dec");
       vvd(pmr2, -0.1783662682155932702e-4, 1e-17,
           "iauStarpm", "pmr");
       vvd(pmd2, 0.2338092915987603664e-5, 1e-17,
           "iauStarpm", "pmd");
       vvd(px2, 0.7473533835323493644, 1e-13,
           "iauStarpm", "px");
       vvd(rv2, -21.59905170476860786, 1e-11,
           "iauStarpm", "rv");

       viv(j, 0, "iauStarpm", "j");

    }

    @Test
    public void t_starpv()
    /*
    **  - - - - - - - - -
    **   t _ s t a r p v
    **  - - - - - - - - -
    **
    **  Test iauStarpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauStarpv, vvd, viv
    **
    **  This revision:  2008 November 30
    */
    {
       double ra, dec, pmr, pmd, px, rv, pv[][] = new double[2][3];
       int j;


       ra =   0.01686756;
       dec = -1.093989828;
       pmr = -1.78323516e-5;
       pmd =  2.336024047e-6;
       px =   0.74723;
       rv = -21.6;

       j = iauStarpv(ra, dec, pmr, pmd, px, rv, pv);

       vvd(pv[0][0], 126668.5912743160601, 1e-10,
           "iauStarpv", "11");
       vvd(pv[0][1], 2136.792716839935195, 1e-12,
           "iauStarpv", "12");
       vvd(pv[0][2], -245251.2339876830091, 1e-10,
           "iauStarpv", "13");

       vvd(pv[1][0], -0.4051854035740712739e-2, 1e-13,
           "iauStarpv", "21");
       vvd(pv[1][1], -0.6253919754866173866e-2, 1e-15,
           "iauStarpv", "22");
       vvd(pv[1][2], 0.1189353719774107189e-1, 1e-13,
           "iauStarpv", "23");

       viv(j, 0, "iauStarpv", "j");

    }

    @Test
    public void t_sxp()
    /*
    **  - - - - - -
    **   t _ s x p
    **  - - - - - -
    **
    **  Test iauSxp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauSxp, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double s, p[] = new double[3], sp[] = new double[3];


       s = 2.0;

       p[0] =  0.3;
       p[1] =  1.2;
       p[2] = -2.5;

       iauSxp(s, p, sp);

       vvd(sp[0],  0.6, 0.0, "iauSxp", "1");
       vvd(sp[1],  2.4, 0.0, "iauSxp", "2");
       vvd(sp[2], -5.0, 0.0, "iauSxp", "3");

    }


    @Test
    public void t_sxpv()
    /*
    **  - - - - - - -
    **   t _ s x p v
    **  - - - - - - -
    **
    **  Test iauSxpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauSxpv, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double s, pv[][] = new double[2][3], spv[][] = new double[2][3];


       s = 2.0;

       pv[0][0] =  0.3;
       pv[0][1] =  1.2;
       pv[0][2] = -2.5;

       pv[1][0] =  0.5;
       pv[1][1] =  3.2;
       pv[1][2] = -0.7;

       iauSxpv(s, pv, spv);

       vvd(spv[0][0],  0.6, 0.0, "iauSxpv", "p1");
       vvd(spv[0][1],  2.4, 0.0, "iauSxpv", "p2");
       vvd(spv[0][2], -5.0, 0.0, "iauSxpv", "p3");

       vvd(spv[1][0],  1.0, 0.0, "iauSxpv", "v1");
       vvd(spv[1][1],  6.4, 0.0, "iauSxpv", "v2");
       vvd(spv[1][2], -1.4, 0.0, "iauSxpv", "v3");

    }

    @Test
    public void t_tr()
    /*
    **  - - - - -
    **   t _ t r
    **  - - - - -
    **
    **  Test iauTr function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTr, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double r[][] = new double[3][3], rt[][] = new double[3][3];


       r[0][0] = 2.0;
       r[0][1] = 3.0;
       r[0][2] = 2.0;

       r[1][0] = 3.0;
       r[1][1] = 2.0;
       r[1][2] = 3.0;

       r[2][0] = 3.0;
       r[2][1] = 4.0;
       r[2][2] = 5.0;

       iauTr(r, rt);

       vvd(rt[0][0], 2.0, 0.0, "iauTr", "11");
       vvd(rt[0][1], 3.0, 0.0, "iauTr", "12");
       vvd(rt[0][2], 3.0, 0.0, "iauTr", "13");

       vvd(rt[1][0], 3.0, 0.0, "iauTr", "21");
       vvd(rt[1][1], 2.0, 0.0, "iauTr", "22");
       vvd(rt[1][2], 4.0, 0.0, "iauTr", "23");

       vvd(rt[2][0], 2.0, 0.0, "iauTr", "31");
       vvd(rt[2][1], 3.0, 0.0, "iauTr", "32");
       vvd(rt[2][2], 5.0, 0.0, "iauTr", "33");

    }

    @Test
    public void t_trxp()
    /*
    **  - - - - - - -
    **   t _ t r x p
    **  - - - - - - -
    **
    **  Test iauTrxp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTrxp, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double r[][] = new double[3][3], p[] = new double[3], trp[] = new double[3];


       r[0][0] = 2.0;
       r[0][1] = 3.0;
       r[0][2] = 2.0;

       r[1][0] = 3.0;
       r[1][1] = 2.0;
       r[1][2] = 3.0;

       r[2][0] = 3.0;
       r[2][1] = 4.0;
       r[2][2] = 5.0;

       p[0] = 0.2;
       p[1] = 1.5;
       p[2] = 0.1;

       iauTrxp(r, p, trp);

       vvd(trp[0], 5.2, 1e-12, "iauTrxp", "1");
       vvd(trp[1], 4.0, 1e-12, "iauTrxp", "2");
       vvd(trp[2], 5.4, 1e-12, "iauTrxp", "3");

    }

    @Test
    public void t_trxpv()
    /*
    **  - - - - - - - -
    **   t _ t r x p v
    **  - - - - - - - -
    **
    **  Test iauTrxpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTrxpv, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double r[][] = new double[3][3], pv[][] = new double[2][3], trpv[][] = new double[2][3];


       r[0][0] = 2.0;
       r[0][1] = 3.0;
       r[0][2] = 2.0;

       r[1][0] = 3.0;
       r[1][1] = 2.0;
       r[1][2] = 3.0;

       r[2][0] = 3.0;
       r[2][1] = 4.0;
       r[2][2] = 5.0;

       pv[0][0] = 0.2;
       pv[0][1] = 1.5;
       pv[0][2] = 0.1;

       pv[1][0] = 1.5;
       pv[1][1] = 0.2;
       pv[1][2] = 0.1;

       iauTrxpv(r, pv, trpv);

       vvd(trpv[0][0], 5.2, 1e-12, "iauTrxpv", "p1");
       vvd(trpv[0][1], 4.0, 1e-12, "iauTrxpv", "p1");
       vvd(trpv[0][2], 5.4, 1e-12, "iauTrxpv", "p1");

       vvd(trpv[1][0], 3.9, 1e-12, "iauTrxpv", "v1");
       vvd(trpv[1][1], 5.3, 1e-12, "iauTrxpv", "v2");
       vvd(trpv[1][2], 4.1, 1e-12, "iauTrxpv", "v3");

    }

    @Test
    public void t_xy06()
    /*
    **  - - - - - - -
    **   t _ x y 0 6
    **  - - - - - - -
    **
    **  Test iauXy06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauXy06, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double x, y;


       iauXy06(2400000.5, 53736.0, x, y);

       vvd(x, 0.5791308486706010975e-3, 1e-15, "iauXy06", "x");
       vvd(y, 0.4020579816732958141e-4, 1e-16, "iauXy06", "y");

    }

    @Test
    public void t_xys00a()
    /*
    **  - - - - - - - - -
    **   t _ x y s 0 0 a
    **  - - - - - - - - -
    **
    **  Test iauXys00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauXys00a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double x, y, s;


       iauXys00a(2400000.5, 53736.0, x, y, s);

       vvd(x,  0.5791308472168152904e-3, 1e-14, "iauXys00a", "x");
       vvd(y,  0.4020595661591500259e-4, 1e-15, "iauXys00a", "y");
       vvd(s, -0.1220040848471549623e-7, 1e-18, "iauXys00a", "s");

    }

    @Test
    public void t_xys00b()
    /*
    **  - - - - - - - - -
    **   t _ x y s 0 0 b
    **  - - - - - - - - -
    **
    **  Test iauXys00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauXys00b, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double x, y, s;


       iauXys00b(2400000.5, 53736.0, x, y, s);

       vvd(x,  0.5791301929950208873e-3, 1e-14, "iauXys00b", "x");
       vvd(y,  0.4020553681373720832e-4, 1e-15, "iauXys00b", "y");
       vvd(s, -0.1220027377285083189e-7, 1e-18, "iauXys00b", "s");

    }

    @Test
    public void t_xys06a()
    /*
    **  - - - - - - - - -
    **   t _ x y s 0 6 a
    **  - - - - - - - - -
    **
    **  Test iauXys06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauXys06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double x, y, s;


       iauXys06a(2400000.5, 53736.0, x, y, s);

       vvd(x,  0.5791308482835292617e-3, 1e-14, "iauXys06a", "x");
       vvd(y,  0.4020580099454020310e-4, 1e-15, "iauXys06a", "y");
       vvd(s, -0.1220032294164579896e-7, 1e-18, "iauXys06a", "s");

    }

    @Test
    public void t_zp()
    /*
    **  - - - - -
    **   t _ z p
    **  - - - - -
    **
    **  Test iauZp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauZp, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double p[] = new double[3];


       p[0] =  0.3;
       p[1] =  1.2;
       p[2] = -2.5;

       iauZp(p);

       vvd(p[0], 0.0, 0.0, "iauZp", "1");
       vvd(p[1], 0.0, 0.0, "iauZp", "2");
       vvd(p[2], 0.0, 0.0, "iauZp", "3");

    }

    @Test
    public void t_zpv()
    /*
    **  - - - - - -
    **   t _ z p v
    **  - - - - - -
    **
    **  Test iauZpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauZpv, vvd
    **
    **  This revision:  2008 May 25
    */
    {
       double pv[][] = new double[2][3];


       pv[0][0] =  0.3;
       pv[0][1] =  1.2;
       pv[0][2] = -2.5;

       pv[1][0] = -0.5;
       pv[1][1] =  3.1;
       pv[1][2] =  0.9;

       iauZpv(pv);

       vvd(pv[0][0], 0.0, 0.0, "iauZpv", "p1");
       vvd(pv[0][1], 0.0, 0.0, "iauZpv", "p2");
       vvd(pv[0][2], 0.0, 0.0, "iauZpv", "p3");

       vvd(pv[1][0], 0.0, 0.0, "iauZpv", "v1");
       vvd(pv[1][1], 0.0, 0.0, "iauZpv", "v2");
       vvd(pv[1][2], 0.0, 0.0, "iauZpv", "v3");

    }

    @Test
    public void t_zr()
    /*
    **  - - - - -
    **   t _ z r
    **  - - - - -
    **
    **  Test iauZr function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauZr, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double r[][] = new double[3][3];


       r[0][0] = 2.0;
       r[1][0] = 3.0;
       r[2][0] = 2.0;

       r[0][1] = 3.0;
       r[1][1] = 2.0;
       r[2][1] = 3.0;

       r[0][2] = 3.0;
       r[1][2] = 4.0;
       r[2][2] = 5.0;

       iauZr(r);

       vvd(r[0][0], 0.0, 0.0, "iauZr", "00");
       vvd(r[1][0], 0.0, 0.0, "iauZr", "01");
       vvd(r[2][0], 0.0, 0.0, "iauZr", "02");

       vvd(r[0][1], 0.0, 0.0, "iauZr", "10");
       vvd(r[1][1], 0.0, 0.0, "iauZr", "11");
       vvd(r[2][1], 0.0, 0.0, "iauZr", "12");

       vvd(r[0][2], 0.0, 0.0, "iauZr", "20");
       vvd(r[1][2], 0.0, 0.0, "iauZr", "21");
       vvd(r[2][2], 0.0, 0.0, "iauZr", "22");

    }

     
    
}


/*
 * $Log$
 */
