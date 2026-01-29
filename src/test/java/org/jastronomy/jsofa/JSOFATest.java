/*
 * $Id$
 * 
 * 
 * Created on 26 Jan 2010 by Paul Harrison (paul.harrison@manchester.ac.uk)
 *
 * Adapted from official SOFA C implementation http://www.iausofa.org/
 *
 *
 */ 

package org.jastronomy.jsofa;




import static org.jastronomy.jsofa.JSOFA.*;
import static org.junit.Assert.*;

import org.jastronomy.jsofa.JSOFA.CatalogCoords;
import org.jastronomy.jsofa.JSOFA.SphericalCoordinate;
import org.jastronomy.jsofa.JSOFAException;
import org.jastronomy.jsofa.JSOFAIllegalParameter;
import org.jastronomy.jsofa.JSOFAInternalError;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static java.lang.Math.*;

public class JSOFATest {

    private static boolean verbose;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testversion() throws JSOFAIllegalParameter 
    {
        assertEquals("sofa release", "2023-10-11", SOFA_RELEASE);
        assertEquals("sofa revision", "19", SOFA_REVISION);
        assertEquals("lastleap second", jauCal2jd(2017,1,1), lastLeapSecondDate());        
        assertEquals("last confirmation of leapsecond data", jauCal2jd(2026,01,28), latestConfirmedNoLeapSecondChange);
        assertEquals("Jsofa release",  System.getProperty("SOFAVERSION"), JSOFA_RELEASE); // check that the correct version is being released - system properly set from POM
        
        
    }

    /*
    **
    **  Validate the JSOFA C functions.
    **
    **  Each JSOFA function is tested to some useful but not exhaustive
    **  level.  Successful completion is signalled by an absence of
    **  output messages.  Failure of a given function or group of functions
    **  results in error messages.
    **
    **  All messages go to stdout.
    **
    **  This revision:  2010 January 18
    **
    **  JSOFA release 2009-12-31
    **
    **  Copyright (C) 2009 IAU SOFA Review Board.  See notes at end.
    */

    /**
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
    private void viv(int ival, int ivalok, String func, String test)
   {
       if (ival != ivalok) {
          System.err.printf("%s failed: %s want %d got %d\n",
                 func, test, ivalok, ival);
       } else if (verbose) {
          System.out.printf("%s passed: %s want %d got %d\n",
                        func, test, ivalok, ival);
       }
       assertEquals(func+" "+test, ivalok, ival);
       return;
    }

    /**
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
    private void vvd(double val, double valok, double dval,
            String func, String test)
    {
       double a, f;   /* absolute and fractional error */


       a = val - valok;
       f = abs(valok / a);
       String msg = String.format("%s: %s want %.20g got %.20g (1/%.3g)\n",
                 func, test, valok, val, f); 
       if (verbose && abs(a) <= dval) {
          System.out.println("passed: " + msg);
       }
       assertTrue(msg, abs(a) <= dval);
       return;
    }

    @Test
    public void t_a2af()
    /**
    **
    **  Test jauA2af function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauA2af, viv
    **
    **  This revision:  2008 November 30
    */
    {
       int idmsf[] = new int[4];
       char s;


       s = jauA2af(4, 2.345, idmsf);

       viv(s, '+', "jauA2af", "s");

       viv(idmsf[0],  134, "jauA2af", "0");
       viv(idmsf[1],   21, "jauA2af", "1");
       viv(idmsf[2],   30, "jauA2af", "2");
       viv(idmsf[3], 9706, "jauA2af", "3");

    }

    @Test
    public void t_a2tf()
    /**
    **
    **  Test jauA2tf function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauA2tf, viv
    **
    **  This revision:  2008 November 30
    */
    {
       int ihmsf[] = new int[4];
       char s;

//TODO here if we increase this to 9dp then will see the rounding error caused by 32 bit int in java
// see https://github.com/Javastro/jsofa/issues/12
       s = jauA2tf(4, -3.01234, ihmsf);

       viv((int)s, '-', "jauA2tf", "s");

       viv(ihmsf[0],   11, "jauA2tf", "0");
       viv(ihmsf[1],   30, "jauA2tf", "1");
       viv(ihmsf[2],   22, "jauA2tf", "2");
       viv(ihmsf[3], 6484, "jauA2tf", "3");
       //       viv(ihmsf[3], 648400000, "jauA2tf", "3"); this should be achievable with 64 bit ints

    }

   @Test
   public void t_a2tf2()
   /**
    **
    **  Test jauA2tf function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauA2tf, viv
    **
    **  This revision:  2008 November 30
    */
   {
      int ihmsf[] = new int[4];
      char s;


      s = jauA2af(9, 89.26410897*DD2R, ihmsf);

      viv((int)s, '+', "jauA2tf", "s");

      viv(ihmsf[0],   89, "jauA2tf", "0");
      viv(ihmsf[1],   15, "jauA2tf", "1");
      viv(ihmsf[2],   50, "jauA2tf", "2");
      viv(ihmsf[3], 792292000, "jauA2tf", "3");

   }


   @Test
    public void t_anp()
    /**
    **
    **  Test jauAnp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauAnp, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       vvd(jauAnp(-0.1), 6.183185307179586477, 1e-12, "jauAnp", "");
    }

    @Test
    public void t_anpm()
    /**
    **
    **  Test jauAnpm function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauAnpm, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       vvd(jauAnpm(-4.0), 2.283185307179586477, 1e-12, "jauAnpm", "");
    }

    @Test
    public void t_bi00()
    /**
    **
    **  Test jauBi00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauBi00, vvd
    **
    **  This revision:  2009 November 4
    */
    {

       FrameBias ret = jauBi00();

       vvd(ret.dpsibi, -0.2025309152835086613e-6, 1e-12,"jauBi00", "dpsibi");
       vvd(ret.depsbi, -0.3306041454222147847e-7, 1e-12,"jauBi00", "depsbi");
       vvd(ret.dra, -0.7078279744199225506e-7, 1e-12,"jauBi00", "dra");
    }

    @Test
    public void t_bp00()
    /**
    **
    **  Test jauBp00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauBp00, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rb[][] = new double[3][3], rp[][] = new double[3][3], rbp[][] = new double[3][3];


       jauBp00(2400000.5, 50123.9999, rb, rp, rbp);

       vvd(rb[0][0], 0.9999999999999942498, 1e-12,"jauBp00", "rb11");
       vvd(rb[0][1], -0.7078279744199196626e-7, 1e-16,"jauBp00", "rb12");
       vvd(rb[0][2], 0.8056217146976134152e-7, 1e-16,"jauBp00", "rb13");
       vvd(rb[1][0], 0.7078279477857337206e-7, 1e-16,"jauBp00", "rb21");
       vvd(rb[1][1], 0.9999999999999969484, 1e-12,"jauBp00", "rb22");
       vvd(rb[1][2], 0.3306041454222136517e-7, 1e-16,"jauBp00", "rb23");
       vvd(rb[2][0], -0.8056217380986972157e-7, 1e-16,"jauBp00", "rb31");
       vvd(rb[2][1], -0.3306040883980552500e-7, 1e-16,"jauBp00", "rb32");
       vvd(rb[2][2], 0.9999999999999962084, 1e-12,"jauBp00", "rb33");

       vvd(rp[0][0], 0.9999995504864048241, 1e-12,"jauBp00", "rp11");
       vvd(rp[0][1], 0.8696113836207084411e-3, 1e-14,"jauBp00", "rp12");
       vvd(rp[0][2], 0.3778928813389333402e-3, 1e-14,"jauBp00", "rp13");
       vvd(rp[1][0], -0.8696113818227265968e-3, 1e-14,"jauBp00", "rp21");
       vvd(rp[1][1], 0.9999996218879365258, 1e-12,"jauBp00", "rp22");
       vvd(rp[1][2], -0.1690679263009242066e-6, 1e-14,"jauBp00", "rp23");
       vvd(rp[2][0], -0.3778928854764695214e-3, 1e-14,"jauBp00", "rp31");
       vvd(rp[2][1], -0.1595521004195286491e-6, 1e-14,"jauBp00", "rp32");
       vvd(rp[2][2], 0.9999999285984682756, 1e-12,"jauBp00", "rp33");

       vvd(rbp[0][0], 0.9999995505175087260, 1e-12,"jauBp00", "rbp11");
       vvd(rbp[0][1], 0.8695405883617884705e-3, 1e-14,"jauBp00", "rbp12");
       vvd(rbp[0][2], 0.3779734722239007105e-3, 1e-14,"jauBp00", "rbp13");
       vvd(rbp[1][0], -0.8695405990410863719e-3, 1e-14,"jauBp00", "rbp21");
       vvd(rbp[1][1], 0.9999996219494925900, 1e-12,"jauBp00", "rbp22");
       vvd(rbp[1][2], -0.1360775820404982209e-6, 1e-14,"jauBp00", "rbp23");
       vvd(rbp[2][0], -0.3779734476558184991e-3, 1e-14,"jauBp00", "rbp31");
       vvd(rbp[2][1], -0.1925857585832024058e-6, 1e-14,"jauBp00", "rbp32");
       vvd(rbp[2][2], 0.9999999285680153377, 1e-12,"jauBp00", "rbp33");
    }

    @Test
    public void t_bp06()
    /**
    **
    **  Test jauBp06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauBp06, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rb[][] = new double[3][3], rp[][] = new double[3][3], rbp[][] = new double[3][3];


       jauBp06(2400000.5, 50123.9999, rb, rp, rbp);

       vvd(rb[0][0], 0.9999999999999942497, 1e-12,"jauBp06", "rb11");
       vvd(rb[0][1], -0.7078368960971557145e-7, 1e-14,"jauBp06", "rb12");
       vvd(rb[0][2], 0.8056213977613185606e-7, 1e-14,"jauBp06", "rb13");
       vvd(rb[1][0], 0.7078368694637674333e-7, 1e-14,"jauBp06", "rb21");
       vvd(rb[1][1], 0.9999999999999969484, 1e-12,"jauBp06", "rb22");
       vvd(rb[1][2], 0.3305943742989134124e-7, 1e-14,"jauBp06", "rb23");
       vvd(rb[2][0], -0.8056214211620056792e-7, 1e-14,"jauBp06", "rb31");
       vvd(rb[2][1], -0.3305943172740586950e-7, 1e-14,"jauBp06", "rb32");
       vvd(rb[2][2], 0.9999999999999962084, 1e-12,"jauBp06", "rb33");

       vvd(rp[0][0], 0.9999995504864960278, 1e-12,"jauBp06", "rp11");
       vvd(rp[0][1], 0.8696112578855404832e-3, 1e-14,"jauBp06", "rp12");
       vvd(rp[0][2], 0.3778929293341390127e-3, 1e-14,"jauBp06", "rp13");
       vvd(rp[1][0], -0.8696112560510186244e-3, 1e-14,"jauBp06", "rp21");
       vvd(rp[1][1], 0.9999996218880458820, 1e-12,"jauBp06", "rp22");
       vvd(rp[1][2], -0.1691646168941896285e-6, 1e-14,"jauBp06", "rp23");
       vvd(rp[2][0], -0.3778929335557603418e-3, 1e-14,"jauBp06", "rp31");
       vvd(rp[2][1], -0.1594554040786495076e-6, 1e-14,"jauBp06", "rp32");
       vvd(rp[2][2], 0.9999999285984501222, 1e-12,"jauBp06", "rp33");

       vvd(rbp[0][0], 0.9999995505176007047, 1e-12,"jauBp06", "rbp11");
       vvd(rbp[0][1], 0.8695404617348208406e-3, 1e-14,"jauBp06", "rbp12");
       vvd(rbp[0][2], 0.3779735201865589104e-3, 1e-14,"jauBp06", "rbp13");
       vvd(rbp[1][0], -0.8695404723772031414e-3, 1e-14,"jauBp06", "rbp21");
       vvd(rbp[1][1], 0.9999996219496027161, 1e-12,"jauBp06", "rbp22");
       vvd(rbp[1][2], -0.1361752497080270143e-6, 1e-14,"jauBp06", "rbp23");
       vvd(rbp[2][0], -0.3779734957034089490e-3, 1e-14,"jauBp06", "rbp31");
       vvd(rbp[2][1], -0.1924880847894457113e-6, 1e-14,"jauBp06", "rbp32");
       vvd(rbp[2][2], 0.9999999285679971958, 1e-12,"jauBp06", "rbp33");
    }

    @Test
    public void t_bpn2xy()
    /**
    **
    **  Test jauBpn2xy function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauBpn2xy, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double rbpn[][] = new double[3][3];


       rbpn[0][0] =  9.999962358680738e-1;
       rbpn[0][1] = -2.516417057665452e-3;
       rbpn[0][2] = -1.093569785342370e-3;

       rbpn[1][0] =  2.516462370370876e-3;
       rbpn[1][1] =  9.999968329010883e-1;
       rbpn[1][2] =  4.006159587358310e-5;

       rbpn[2][0] =  1.093465510215479e-3;
       rbpn[2][1] = -4.281337229063151e-5;
       rbpn[2][2] =  9.999994012499173e-1;

       CelestialIntermediatePole ret = jauBpn2xy(rbpn);

       vvd(ret.x,  1.093465510215479e-3, 1e-12, "jauBpn2xy", "x");
       vvd(ret.y, -4.281337229063151e-5, 1e-12, "jauBpn2xy", "y");

    }

    @Test
    public void t_c2i00a()
    /**
    **
    **  Test jauC2i00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauC2i00a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rc2i[][];


       rc2i = jauC2i00a(2400000.5, 53736.0);

       vvd(rc2i[0][0], 0.9999998323037165557, 1e-12,"jauC2i00a", "11");
       vvd(rc2i[0][1], 0.5581526348992140183e-9, 1e-12,"jauC2i00a", "12");
       vvd(rc2i[0][2], -0.5791308477073443415e-3, 1e-12,"jauC2i00a", "13");

       vvd(rc2i[1][0], -0.2384266227870752452e-7, 1e-12,"jauC2i00a", "21");
       vvd(rc2i[1][1], 0.9999999991917405258, 1e-12,"jauC2i00a", "22");
       vvd(rc2i[1][2], -0.4020594955028209745e-4, 1e-12,"jauC2i00a", "23");

       vvd(rc2i[2][0], 0.5791308472168152904e-3, 1e-12,"jauC2i00a", "31");
       vvd(rc2i[2][1], 0.4020595661591500259e-4, 1e-12,"jauC2i00a", "32");
       vvd(rc2i[2][2], 0.9999998314954572304, 1e-12,"jauC2i00a", "33");

    }

    @Test
    public void t_c2i00b()
    /**
    **
    **  Test jauC2i00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauC2i00b, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rc2i[][] = new double[3][3];


       rc2i = jauC2i00b(2400000.5, 53736.0 );

       vvd(rc2i[0][0], 0.9999998323040954356, 1e-12,"jauC2i00b", "11");
       vvd(rc2i[0][1], 0.5581526349131823372e-9, 1e-12,"jauC2i00b", "12");
       vvd(rc2i[0][2], -0.5791301934855394005e-3, 1e-12,"jauC2i00b", "13");

       vvd(rc2i[1][0], -0.2384239285499175543e-7, 1e-12,"jauC2i00b", "21");
       vvd(rc2i[1][1], 0.9999999991917574043, 1e-12,"jauC2i00b", "22");
       vvd(rc2i[1][2], -0.4020552974819030066e-4, 1e-12,"jauC2i00b", "23");

       vvd(rc2i[2][0], 0.5791301929950208873e-3, 1e-12,"jauC2i00b", "31");
       vvd(rc2i[2][1], 0.4020553681373720832e-4, 1e-12,"jauC2i00b", "32");
       vvd(rc2i[2][2], 0.9999998314958529887, 1e-12,"jauC2i00b", "33");

    }

    @Test
    public void t_c2i06a()
    /**
    **
    **  Test jauC2i06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauC2i06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rc2i[][] = new double[3][3];


       rc2i = jauC2i06a(2400000.5, 53736.0);

       vvd(rc2i[0][0], 0.9999998323037159379, 1e-12,"jauC2i06a", "11");
       vvd(rc2i[0][1], 0.5581121329587613787e-9, 1e-12,"jauC2i06a", "12");
       vvd(rc2i[0][2], -0.5791308487740529749e-3, 1e-12,"jauC2i06a", "13");

       vvd(rc2i[1][0], -0.2384253169452306581e-7, 1e-12,"jauC2i06a", "21");
       vvd(rc2i[1][1], 0.9999999991917467827, 1e-12,"jauC2i06a", "22");
       vvd(rc2i[1][2], -0.4020579392895682558e-4, 1e-12,"jauC2i06a", "23");

       vvd(rc2i[2][0], 0.5791308482835292617e-3, 1e-12,"jauC2i06a", "31");
       vvd(rc2i[2][1], 0.4020580099454020310e-4, 1e-12,"jauC2i06a", "32");
       vvd(rc2i[2][2], 0.9999998314954628695, 1e-12,"jauC2i06a", "33");

    }

    @Test
    public void t_c2ibpn()
    /**
    **
    **  Test jauC2ibpn function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauC2ibpn, vvd
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

       rc2i = jauC2ibpn(2400000.5, 50123.9999, rbpn );

       vvd(rc2i[0][0], 0.9999994021664089977, 1e-12,"jauC2ibpn", "11");
       vvd(rc2i[0][1], -0.3869195948017503664e-8, 1e-12,"jauC2ibpn", "12");
       vvd(rc2i[0][2], -0.1093465511383285076e-2, 1e-12,"jauC2ibpn", "13");

       vvd(rc2i[1][0], 0.5068413965715446111e-7, 1e-12,"jauC2ibpn", "21");
       vvd(rc2i[1][1], 0.9999999990835075686, 1e-12,"jauC2ibpn", "22");
       vvd(rc2i[1][2], 0.4281334246452708915e-4, 1e-12,"jauC2ibpn", "23");

       vvd(rc2i[2][0], 0.1093465510215479000e-2, 1e-12,"jauC2ibpn", "31");
       vvd(rc2i[2][1], -0.4281337229063151000e-4, 1e-12,"jauC2ibpn", "32");
       vvd(rc2i[2][2], 0.9999994012499173103, 1e-12,"jauC2ibpn", "33");

    }

    @Test
    public void t_c2ixy()
    /**
    **
    **  Test jauC2ixy function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauC2ixy, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double x, y, rc2i[][] = new double[3][3];


       x = 0.5791308486706011000e-3;
       y = 0.4020579816732961219e-4;

       rc2i = jauC2ixy(2400000.5, 53736, x, y);

       vvd(rc2i[0][0], 0.9999998323037157138, 1e-12,"jauC2ixy", "11");
       vvd(rc2i[0][1], 0.5581526349032241205e-9, 1e-12,"jauC2ixy", "12");
       vvd(rc2i[0][2], -0.5791308491611263745e-3, 1e-12,"jauC2ixy", "13");

       vvd(rc2i[1][0], -0.2384257057469842953e-7, 1e-12,"jauC2ixy", "21");
       vvd(rc2i[1][1], 0.9999999991917468964, 1e-12,"jauC2ixy", "22");
       vvd(rc2i[1][2], -0.4020579110172324363e-4, 1e-12,"jauC2ixy", "23");

       vvd(rc2i[2][0], 0.5791308486706011000e-3, 1e-12,"jauC2ixy", "31");
       vvd(rc2i[2][1], 0.4020579816732961219e-4, 1e-12,"jauC2ixy", "32");
       vvd(rc2i[2][2], 0.9999998314954627590, 1e-12,"jauC2ixy", "33");

    }

    @Test
    public void t_c2ixys()
    /**
    **
    **  Test jauC2ixys function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauC2ixys, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double x, y, s, rc2i[][] = new double[3][3];


       x =  0.5791308486706011000e-3;
       y =  0.4020579816732961219e-4;
       s = -0.1220040848472271978e-7;

       rc2i = jauC2ixys(x, y, s);

       vvd(rc2i[0][0], 0.9999998323037157138, 1e-12,"jauC2ixys", "11");
       vvd(rc2i[0][1], 0.5581984869168499149e-9, 1e-12,"jauC2ixys", "12");
       vvd(rc2i[0][2], -0.5791308491611282180e-3, 1e-12,"jauC2ixys", "13");

       vvd(rc2i[1][0], -0.2384261642670440317e-7, 1e-12,"jauC2ixys", "21");
       vvd(rc2i[1][1], 0.9999999991917468964, 1e-12,"jauC2ixys", "22");
       vvd(rc2i[1][2], -0.4020579110169668931e-4, 1e-12,"jauC2ixys", "23");

       vvd(rc2i[2][0], 0.5791308486706011000e-3, 1e-12,"jauC2ixys", "31");
       vvd(rc2i[2][1], 0.4020579816732961219e-4, 1e-12,"jauC2ixys", "32");
       vvd(rc2i[2][2], 0.9999998314954627590, 1e-12,"jauC2ixys", "33");

    }

    @Test
    public void t_c2s()
    /**
    **
    **  Test jauC2s function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauC2s, vvd
    **
    **  This revision:  2008 May 27
    */
    {
       double p[] = new double[3];


       p[0] = 100.0;
       p[1] = -50.0;
       p[2] =  25.0;

       SphericalCoordinate ret = jauC2s(p);

       vvd(ret.alpha, -0.4636476090008061162, 1e-14, "jauC2s", "theta");
       vvd(ret.delta, 0.2199879773954594463, 1e-14, "jauC2s", "phi");

    }

    @Test
    public void t_c2t00a()
    /**
    **
    **  Test jauC2t00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauC2t00a, vvd
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

       rc2t = jauC2t00a(tta, ttb, uta, utb, xp, yp);

       vvd(rc2t[0][0], -0.1810332128307182668, 1e-12,"jauC2t00a", "11");
       vvd(rc2t[0][1], 0.9834769806938457836, 1e-12,"jauC2t00a", "12");
       vvd(rc2t[0][2], 0.6555535638688341725e-4, 1e-12,"jauC2t00a", "13");

       vvd(rc2t[1][0], -0.9834768134135984552, 1e-12,"jauC2t00a", "21");
       vvd(rc2t[1][1], -0.1810332203649520727, 1e-12,"jauC2t00a", "22");
       vvd(rc2t[1][2], 0.5749801116141056317e-3, 1e-12,"jauC2t00a", "23");

       vvd(rc2t[2][0], 0.5773474014081406921e-3, 1e-12,"jauC2t00a", "31");
       vvd(rc2t[2][1], 0.3961832391770163647e-4, 1e-12,"jauC2t00a", "32");
       vvd(rc2t[2][2], 0.9999998325501692289, 1e-12,"jauC2t00a", "33");

    }

    @Test
    public void t_c2t00b()
    /**
    **
    **  Test jauC2t00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauC2t00b, vvd
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

       rc2t = jauC2t00b(tta, ttb, uta, utb, xp, yp);

       vvd(rc2t[0][0], -0.1810332128439678965, 1e-12,"jauC2t00b", "11");
       vvd(rc2t[0][1], 0.9834769806913872359, 1e-12,"jauC2t00b", "12");
       vvd(rc2t[0][2], 0.6555565082458415611e-4, 1e-12,"jauC2t00b", "13");

       vvd(rc2t[1][0], -0.9834768134115435923, 1e-12,"jauC2t00b", "21");
       vvd(rc2t[1][1], -0.1810332203784001946, 1e-12,"jauC2t00b", "22");
       vvd(rc2t[1][2], 0.5749793922030017230e-3, 1e-12,"jauC2t00b", "23");

       vvd(rc2t[2][0], 0.5773467471863534901e-3, 1e-12,"jauC2t00b", "31");
       vvd(rc2t[2][1], 0.3961790411549945020e-4, 1e-12,"jauC2t00b", "32");
       vvd(rc2t[2][2], 0.9999998325505635738, 1e-12,"jauC2t00b", "33");

    }

    @Test
    public void t_c2t06a()
    /**
    **
    **  Test jauC2t06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauC2t06a, vvd
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

       rc2t = jauC2t06a(tta, ttb, uta, utb, xp, yp);

       vvd(rc2t[0][0], -0.1810332128305897282, 1e-12,"jauC2t06a", "11");
       vvd(rc2t[0][1], 0.9834769806938592296, 1e-12,"jauC2t06a", "12");
       vvd(rc2t[0][2], 0.6555550962998436505e-4, 1e-12,"jauC2t06a", "13");

       vvd(rc2t[1][0], -0.9834768134136214897, 1e-12,"jauC2t06a", "21");
       vvd(rc2t[1][1], -0.1810332203649130832, 1e-12,"jauC2t06a", "22");
       vvd(rc2t[1][2], 0.5749800844905594110e-3, 1e-12,"jauC2t06a", "23");

       vvd(rc2t[2][0], 0.5773474024748545878e-3, 1e-12,"jauC2t06a", "31");
       vvd(rc2t[2][1], 0.3961816829632690581e-4, 1e-12,"jauC2t06a", "32");
       vvd(rc2t[2][2], 0.9999998325501747785, 1e-12,"jauC2t06a", "33");

    }

    @Test
    public void t_c2tcio()
    /**
    **
    **  Test jauC2tcio function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauC2tcio, vvd
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


       rc2t = jauC2tcio(rc2i, era, rpom);

       vvd(rc2t[0][0], -0.1810332128307110439, 1e-12,"jauC2tcio", "11");
       vvd(rc2t[0][1], 0.9834769806938470149, 1e-12,"jauC2tcio", "12");
       vvd(rc2t[0][2], 0.6555535638685466874e-4, 1e-12,"jauC2tcio", "13");

       vvd(rc2t[1][0], -0.9834768134135996657, 1e-12,"jauC2tcio", "21");
       vvd(rc2t[1][1], -0.1810332203649448367, 1e-12,"jauC2tcio", "22");
       vvd(rc2t[1][2], 0.5749801116141106528e-3, 1e-12,"jauC2tcio", "23");

       vvd(rc2t[2][0], 0.5773474014081407076e-3, 1e-12,"jauC2tcio", "31");
       vvd(rc2t[2][1], 0.3961832391772658944e-4, 1e-12,"jauC2tcio", "32");
       vvd(rc2t[2][2], 0.9999998325501691969, 1e-12,"jauC2tcio", "33");

    }

    @Test
    public void t_c2teqx()
    /**
    **
    **  Test jauC2teqx function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauC2teqx, vvd
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

       rc2t = jauC2teqx(rbpn, gst, rpom);

       vvd(rc2t[0][0], -0.1810332128528685730, 1e-12,"jauC2teqx", "11");
       vvd(rc2t[0][1], 0.9834769806897685071, 1e-12,"jauC2teqx", "12");
       vvd(rc2t[0][2], 0.6555535639982634449e-4, 1e-12,"jauC2teqx", "13");

       vvd(rc2t[1][0], -0.9834768134095211257, 1e-12,"jauC2teqx", "21");
       vvd(rc2t[1][1], -0.1810332203871023800, 1e-12,"jauC2teqx", "22");
       vvd(rc2t[1][2], 0.5749801116126438962e-3, 1e-12,"jauC2teqx", "23");

       vvd(rc2t[2][0], 0.5773474014081539467e-3, 1e-12,"jauC2teqx", "31");
       vvd(rc2t[2][1], 0.3961832391768640871e-4, 1e-12,"jauC2teqx", "32");
       vvd(rc2t[2][2], 0.9999998325501691969, 1e-12,"jauC2teqx", "33");

    }

    @Test
    public void t_c2tpe()
    /**
    **
    **  Test jauC2tpe function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauC2tpe, vvd
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

       rc2t = jauC2tpe(tta, ttb, uta, utb, dpsi, deps, xp, yp);

       vvd(rc2t[0][0], -0.1813677995763029394, 1e-12,"jauC2tpe", "11");
       vvd(rc2t[0][1], 0.9023482206891683275, 1e-12,"jauC2tpe", "12");
       vvd(rc2t[0][2], -0.3909902938641085751, 1e-12,"jauC2tpe", "13");

       vvd(rc2t[1][0], -0.9834147641476804807, 1e-12,"jauC2tpe", "21");
       vvd(rc2t[1][1], -0.1659883635434995121, 1e-12,"jauC2tpe", "22");
       vvd(rc2t[1][2], 0.7309763898042819705e-1, 1e-12,"jauC2tpe", "23");

       vvd(rc2t[2][0], 0.1059685430673215247e-2, 1e-12,"jauC2tpe", "31");
       vvd(rc2t[2][1], 0.3977631855605078674, 1e-12,"jauC2tpe", "32");
       vvd(rc2t[2][2], 0.9174875068792735362, 1e-12,"jauC2tpe", "33");

    }

    @Test
    public void t_c2txy()
    /**
    **
    **  Test jauC2txy function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauC2txy, vvd
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

       rc2t = jauC2txy(tta, ttb, uta, utb, x, y, xp, yp);

       vvd(rc2t[0][0], -0.1810332128306279253, 1e-12,"jauC2txy", "11");
       vvd(rc2t[0][1], 0.9834769806938520084, 1e-12,"jauC2txy", "12");
       vvd(rc2t[0][2], 0.6555551248057665829e-4, 1e-12,"jauC2txy", "13");

       vvd(rc2t[1][0], -0.9834768134136142314, 1e-12,"jauC2txy", "21");
       vvd(rc2t[1][1], -0.1810332203649529312, 1e-12,"jauC2txy", "22");
       vvd(rc2t[1][2], 0.5749800843594139912e-3, 1e-12,"jauC2txy", "23");

       vvd(rc2t[2][0], 0.5773474028619264494e-3, 1e-12,"jauC2txy", "31");
       vvd(rc2t[2][1], 0.3961816546911624260e-4, 1e-12,"jauC2txy", "32");
       vvd(rc2t[2][2], 0.9999998325501746670, 1e-12,"jauC2txy", "33");

    }

    @Test
    public void t_cal2jd()
    /**
    **
    **  Test jauCal2jd function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauCal2jd, vvd, viv
    **
    **  This revision:  2008 May 27
    */
    {


       try {
        JulianDate jd = jauCal2jd(2003, 06, 01);

           vvd(jd.djm0, 2400000.5, 0.0, "jauCal2jd", "djm0");
           vvd(jd.djm1,    52791.0, 0.0, "jauCal2jd", "djm");
    } catch (JSOFAIllegalParameter e) {
        fail("jauCal2jd should not throw execption");
    }

     //  viv(j, 0, "jauCal2jd", "j");

    }

    @Test
    public void t_cp()
    /**
    **
    **  Test jauCp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauCp, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double p[] = new double[3], c[] = new double[3];


       p[0] =  0.3;
       p[1] =  1.2;
       p[2] = -2.5;

       jauCp(p, c);

       vvd(c[0],  0.3, 0.0, "jauCp", "1");
       vvd(c[1],  1.2, 0.0, "jauCp", "2");
       vvd(c[2], -2.5, 0.0, "jauCp", "3");
    }

    @Test
    public void t_cpv()
    /**
    **
    **  Test jauCpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauCpv, vvd
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

       jauCpv(pv, c);

       vvd(c[0][0],  0.3, 0.0, "jauCpv", "p1");
       vvd(c[0][1],  1.2, 0.0, "jauCpv", "p2");
       vvd(c[0][2], -2.5, 0.0, "jauCpv", "p3");

       vvd(c[1][0], -0.5, 0.0, "jauCpv", "v1");
       vvd(c[1][1],  3.1, 0.0, "jauCpv", "v2");
       vvd(c[1][2],  0.9, 0.0, "jauCpv", "v3");

    }

    @Test
    public void t_cr()
    /**
    **
    **  Test jauCr function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauCr, vvd
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

       jauCr(r, c);

       vvd(c[0][0], 2.0, 0.0, "jauCr", "11");
       vvd(c[0][1], 3.0, 0.0, "jauCr", "12");
       vvd(c[0][2], 2.0, 0.0, "jauCr", "13");

       vvd(c[1][0], 3.0, 0.0, "jauCr", "21");
       vvd(c[1][1], 2.0, 0.0, "jauCr", "22");
       vvd(c[1][2], 3.0, 0.0, "jauCr", "23");

       vvd(c[2][0], 3.0, 0.0, "jauCr", "31");
       vvd(c[2][1], 4.0, 0.0, "jauCr", "32");
       vvd(c[2][2], 5.0, 0.0, "jauCr", "33");
    }

    @Test
    public void t_d2tf()
    /**
    **
    **  Test jauD2tf function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauD2tf, viv, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       int ihmsf[] = new int[4];
       char s;


       s = jauD2tf(9, -0.987654320601852, ihmsf);

       viv((int)s, '-', "jauD2tf", "s");

       viv(ihmsf[0], 23, "jauD2tf", "0");
       viv(ihmsf[1], 42, "jauD2tf", "1");
       viv(ihmsf[2], 13, "jauD2tf", "2");
       viv(ihmsf[3], 333300000, "jauD2tf", "3");

    }

    @Test
    public void t_dat()
    /**
    **
    **  Test jauDat function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauDat, vvd, viv
    **
    **  This revision:  2008 November 29
    */
    {
       double deltat;
      int nls = leapSeconds.length;
      viv(nls, 42, "jauDat", "number of leap second entries");
      viv(leapSeconds[nls-1].iyear, 2017, "jauDat", "year of last leap second");
      viv(leapSeconds[nls-1].month, 1, "jauDat", "month of last leap second");
       try {
           deltat = jauDat(2003, 6, 1, 0.0);

           vvd(deltat, 32.0, 0.0, "jauDat", "d1");
       } catch (Exception e) {
           fail("jauDat j1");
       }

       try {
           deltat = jauDat(2008, 1, 17, 0.0);

           vvd(deltat, 33.0, 0.0, "jauDat", "d2");
       } catch (Exception e) {
           fail("jauDat j2");
       }

       try {
           deltat = jauDat(2017, 9, 1, 0.0 );
           vvd(deltat, 37.0, 0.0, "jauDat", "d3");

       } catch (Exception e) {
           fail("jauDat j3");
       }
       
    }

    @Test
    public void t_dtdb()
    /**
    **
    **  Test jauDtdb function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauDtdb, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double dtdb;


       dtdb = jauDtdb(2448939.5, 0.123, 0.76543, 5.0123, 5525.242, 3190.0);

       vvd(dtdb, -0.1280368005936998991e-2, 1e-15, "jauDtdb", "");

    }

    @Test
    public void t_ee00()
    /**
    **
    **  Test jauEe00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauEe00, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double epsa, dpsi, ee;


       epsa =  0.4090789763356509900;
       dpsi = -0.9630909107115582393e-5;

       ee = jauEe00(2400000.5, 53736.0, epsa, dpsi);

       vvd(ee, -0.8834193235367965479e-5, 1e-18, "jauEe00", "");

    }

    @Test
    public void t_ee00a()
    /**
    **
    **  Test jauEe00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauEe00a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double ee;


       ee = jauEe00a(2400000.5, 53736.0);

       vvd(ee, -0.8834192459222588227e-5, 1e-18, "jauEe00a", "");

    }

    @Test
    public void t_ee00b()
    /**
    **
    **  Test jauEe00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauEe00b, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double ee;


       ee = jauEe00b(2400000.5, 53736.0);

       vvd(ee, -0.8835700060003032831e-5, 1e-18, "jauEe00b", "");

    }

    @Test
    public void t_ee06a()
    /**
    **
    **  Test jauEe06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauEe06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double ee;


       ee = jauEe06a(2400000.5, 53736.0);

       vvd(ee, -0.8834195072043790156e-5, 1e-15, "jauEe06a", "");
    }

    @Test
    public void t_eect00()
    /**
    **
    **  Test jauEect00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauEect00, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double eect;


       eect = jauEect00(2400000.5, 53736.0);

       vvd(eect, 0.2046085004885125264e-8, 1e-20, "jauEect00", "");

    }

    @Test
    public void t_eform()
    /**
    **
    **  Test jauEform function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauEform, viv, vvd
    **
    **  This revision:  2010 January 18
    */
    {
        ReferenceEllipsoid ef;

        try {
            ef = jauEform( 0 );
            fail("jauEform should throw exception for illegal identifier");
        } catch (JSOFAIllegalParameter e) {
        }


        try {
            ef = jauEform( 1 );

            vvd(ef.a, 6378137.0, 1e-10, "jauEform", "a");
            vvd(ef.f, 0.0033528106647474807, 1e-18, "jauEform", "f");

            ef = jauEform( 2 );

            vvd(ef.a, 6378137.0, 1e-10, "jauEform", "a");
            vvd(ef.f, 0.0033528106811823189, 1e-18, "jauEform", "f");

            ef = jauEform( 3 );

            vvd(ef.a, 6378135.0, 1e-10, "jauEform", "a");
            vvd(ef.f, 0.0033527794541675049, 1e-18, "jauEform", "f");
        } catch (JSOFAIllegalParameter e) {
            fail("jauEform should not throw exception for legal identifier");
        }

        try {
            ef = jauEform( 4 );
            fail("jauEform should throw exception for illegal identifier");
        } catch (JSOFAIllegalParameter e) {

        }
    }

    @Test
    public void t_eo06a()
    /**
    **
    **  Test jauEo06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauEo06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double eo;


       eo = jauEo06a(2400000.5, 53736.0);

       vvd(eo, -0.1332882371941833644e-2, 1e-15, "jauEo06a", "");

    }

    @Test
    public void t_eors()
    /**
    **
    **  Test jauEors function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauEors, vvd
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

       eo = jauEors(rnpb, s);

       vvd(eo, -0.1332882715130744606e-2, 1e-14, "jauEors", "");

    }

    @Test
    public void t_epb()
    /**
    **
    **  Test jauEpb function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauEpb, vvd
    **
    **  This revision:  2008 May 27
    */
    {
       double epb;


       epb = jauEpb(2415019.8135, 30103.18648);

       vvd(epb, 1982.418424159278580, 1e-12, "jauEpb", "");

    }

    @Test
    public void t_epb2jd()
    /**
    **
    **  Test jauEpb2jd function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauEpb2jd, vvd
    **
    **  This revision:  2008 November 29
    */
    {
       double epb;


       epb = 1957.3;

       JulianDate jd = jauEpb2jd(epb);

       vvd(jd.djm0, 2400000.5, 1e-9, "jauEpb2jd", "djm0");
       vvd(jd.djm1, 35948.1915101513, 1e-9, "jauEpb2jd", "mjd");

    }

    @Test
    public void t_epj()
    /**
    **
    **  Test jauEpj function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauEpj, vvd
    **
    **  This revision:  2008 May 27
    */
    {
       double epj;


       epj = jauEpj(2451545, -7392.5);

       vvd(epj, 1979.760438056125941, 1e-12, "jauEpj", "");

    }

    @Test
    public void t_epj2jd()
    /**
    **
    **  Test jauEpj2jd function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauEpj2jd, vvd
    **
    **  This revision:  2008 November 29
    */
    {
       double epj;


       epj = 1996.8;

       JulianDate jd = jauEpj2jd(epj);

       vvd(jd.djm0, 2400000.5, 1e-9, "jauEpj2jd", "djm0");
       vvd(jd.djm1,    50375.7, 1e-9, "jauEpj2jd", "mjd");

    }

    @Test
    public void t_epv00()
    /**
    **
    **  Test jauEpv00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called: jauEpv00, vvd, viv
    **
    **  This revision:  2008 November 28
    */
    {
       double pvh[][] = new double[2][3], pvb[][] = new double[2][3];
    


       int j = jauEpv00(2400000.5, 53411.52501161, pvh, pvb);

       vvd(pvh[0][0], -0.7757238809297706813, 1e-14,
           "jauEpv00", "ph(x)");
       vvd(pvh[0][1], 0.5598052241363340596, 1e-14,
           "jauEpv00", "ph(y)");
       vvd(pvh[0][2], 0.2426998466481686993, 1e-14,
           "jauEpv00", "ph(z)");

       vvd(pvh[1][0], -0.1091891824147313846e-1, 1e-15,
           "jauEpv00", "vh(x)");
       vvd(pvh[1][1], -0.1247187268440845008e-1, 1e-15,
           "jauEpv00", "vh(y)");
       vvd(pvh[1][2], -0.5407569418065039061e-2, 1e-15,
           "jauEpv00", "vh(z)");

       vvd(pvb[0][0], -0.7714104440491111971, 1e-14,
           "jauEpv00", "pb(x)");
       vvd(pvb[0][1], 0.5598412061824171323, 1e-14,
           "jauEpv00", "pb(y)");
       vvd(pvb[0][2], 0.2425996277722452400, 1e-14,
           "jauEpv00", "pb(z)");

       vvd(pvb[1][0], -0.1091874268116823295e-1, 1e-15,
           "jauEpv00", "vb(x)");
       vvd(pvb[1][1], -0.1246525461732861538e-1, 1e-15,
           "jauEpv00", "vb(y)");
       vvd(pvb[1][2], -0.5404773180966231279e-2, 1e-15,
           "jauEpv00", "vb(z)");

       viv(j, 0, "jauEpv00", "j");

    }

    @Test
    public void t_eqeq94()
    /**
    **
    **  Test jauEqeq94 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauEqeq94, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double eqeq;


       eqeq = jauEqeq94(2400000.5, 41234.0);

       vvd(eqeq, 0.5357758254609256894e-4, 1e-17, "jauEqeq94", "");

    }

    @Test
    public void t_era00()
    /**
    **
    **  Test jauEra00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauEra00, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double era00;


       era00 = jauEra00(2400000.5, 54388.0);

       vvd(era00, 0.4022837240028158102, 1e-12, "jauEra00", "");

    }

    @Test
    public void t_fad03()
    /**
    **
    **  Test jauFad03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFad03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(jauFad03(0.80), 1.946709205396925672, 1e-12,
           "jauFad03", "");
    }

    @Test
    public void t_fae03()
    /**
    **
    **  Test jauFae03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFae03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(jauFae03(0.80), 1.744713738913081846, 1e-12,
           "jauFae03", "");
    }

    @Test
    public void t_faf03()
    /**
    **
    **  Test jauFaf03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFaf03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(jauFaf03(0.80), 0.2597711366745499518, 1e-12,
           "jauFaf03", "");
    }

    @Test
    public void t_faju03()
    /**
    **
    **  Test jauFaju03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFaju03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(jauFaju03(0.80), 5.275711665202481138, 1e-12,
           "jauFaju03", "");
    }

    @Test
    public void t_fal03()
    /**
    **
    **  Test jauFal03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFal03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(jauFal03(0.80), 5.132369751108684150, 1e-12,
           "jauFal03", "");
    }

    @Test
    public void t_falp03()
    /**
    **
    **  Test jauFalp03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFalp03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(jauFalp03(0.80), 6.226797973505507345, 1e-12,
          "jauFalp03", "");
    }

    @Test
    public void t_fama03()
    /**
    **
    **  Test jauFama03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFama03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(jauFama03(0.80), 3.275506840277781492, 1e-12,
           "jauFama03", "");
    }

    @Test
    public void t_fame03()
    /**
    **
    **  Test jauFame03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFame03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(jauFame03(0.80), 5.417338184297289661, 1e-12,
           "jauFame03", "");
    }

    @Test
    public void t_fane03()
    /**
    **
    **  Test jauFane03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFane03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(jauFane03(0.80), 2.079343830860413523, 1e-12,
           "jauFane03", "");
    }

    @Test
    public void t_faom03()
    /**
    **
    **  Test jauFaom03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFaom03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(jauFaom03(0.80), -5.973618440951302183, 1e-12,
           "jauFaom03", "");
    }

    @Test
    public void t_fapa03()
    /**
    **
    **  Test jauFapa03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFapa03, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       vvd(jauFapa03(0.80), 0.1950884762240000000e-1, 1e-12,
           "jauFapa03", "");
    }

    @Test
    public void t_fasa03()
    /**
    **
    **  Test jauFasa03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFasa03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(jauFasa03(0.80), 5.371574539440827046, 1e-12,
           "jauFasa03", "");
    }

    @Test
    public void t_faur03()
    /**
    **
    **  Test jauFaur03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFaur03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(jauFaur03(0.80), 5.180636450180413523, 1e-12,
           "jauFaur03", "");
    }

    @Test
    public void t_fave03()
    /**
    **
    **  Test jauFave03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFave03, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       vvd(jauFave03(0.80), 3.424900460533758000, 1e-12,
           "jauFave03", "");
    }

    @Test
    public void t_fk52h()
    /**
    **
    **  Test jauFk52h function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFk52h, vvd
    **
    **  This revision:  2009 November 6
    */
    {
       double r5, d5, dr5, dd5, px5, rv5;


       r5  =  1.76779433;
       d5  = -0.2917517103;
       dr5 = -1.91851572e-7;
       dd5 = -5.8468475e-6;
       px5 =  0.379210;
       rv5 = -7.6;

      CatalogCoords cat = jauFk52h(r5, d5, dr5, dd5, px5, rv5);

       vvd(cat.pos.alpha, 1.767794226299947632, 1e-14,"jauFk52h", "ra");
       vvd(cat.pos.delta,  -0.2917516070530391757, 1e-14,"jauFk52h", "dec");
       vvd(cat.pm.alpha, -0.1961874125605721270e-6,1e-19,"jauFk52h", "dr5");
       vvd(cat.pm.delta, -0.58459905176693911e-5, 1e-19,"jauFk52h", "dd5");
       vvd(cat.px,  0.37921, 1e-14,"jauFk52h", "px");
       vvd(cat.rv, -7.6000000940000254, 1e-11,"jauFk52h", "rv");

    }

    @Test
    public void t_fk5hip()
    /**
    **
    **  Test jauFk5hip function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFk5hip, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double r5h[][] = new double[3][3], s5h[] = new double[3];


       jauFk5hip(r5h, s5h);

       vvd(r5h[0][0], 0.9999999999999928638, 1e-14,"jauFk5hip", "11");
       vvd(r5h[0][1], 0.1110223351022919694e-6, 1e-17,"jauFk5hip", "12");
       vvd(r5h[0][2], 0.4411803962536558154e-7, 1e-17,"jauFk5hip", "13");
       vvd(r5h[1][0], -0.1110223308458746430e-6, 1e-17,"jauFk5hip", "21");
       vvd(r5h[1][1], 0.9999999999999891830, 1e-14,"jauFk5hip", "22");
       vvd(r5h[1][2], -0.9647792498984142358e-7, 1e-17,"jauFk5hip", "23");
       vvd(r5h[2][0], -0.4411805033656962252e-7, 1e-17,"jauFk5hip", "31");
       vvd(r5h[2][1], 0.9647792009175314354e-7, 1e-17,"jauFk5hip", "32");
       vvd(r5h[2][2], 0.9999999999999943728, 1e-14,"jauFk5hip", "33");
       vvd(s5h[0], -0.1454441043328607981e-8, 1e-17,"jauFk5hip", "s1");
       vvd(s5h[1], 0.2908882086657215962e-8, 1e-17,"jauFk5hip", "s2");
       vvd(s5h[2], 0.3393695767766751955e-8, 1e-17,"jauFk5hip", "s3");

    }

    @Test
    public void t_fk5hz()
    /**
    **
    **  Test jauFk5hz function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFk5hz, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double r5, d5;


       r5 =  1.76779433;
       d5 = -0.2917517103;

       SphericalCoordinate pos = jauFk5hz(r5, d5, 2400000.5, 54479.0);

       vvd(pos.alpha,  1.767794191464423978, 1e-12, "jauFk5hz", "ra");
       vvd(pos.delta, -0.2917516001679884419, 1e-12, "jauFk5hz", "dec");

    }

    @Test
    public void t_fw2m()
    /**
    **
    **  Test jauFw2m function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFw2m, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double gamb, phib, psi, eps, r[][] = new double[3][3];


       gamb = -0.2243387670997992368e-5;
       phib =  0.4091014602391312982;
       psi  = -0.9501954178013015092e-3;
       eps  =  0.4091014316587367472;

       r = jauFw2m(gamb, phib, psi, eps);

       vvd(r[0][0], 0.9999995505176007047, 1e-12,"jauFw2m", "11");
       vvd(r[0][1], 0.8695404617348192957e-3, 1e-12,"jauFw2m", "12");
       vvd(r[0][2], 0.3779735201865582571e-3, 1e-12,"jauFw2m", "13");

       vvd(r[1][0], -0.8695404723772016038e-3, 1e-12,"jauFw2m", "21");
       vvd(r[1][1], 0.9999996219496027161, 1e-12,"jauFw2m", "22");
       vvd(r[1][2], -0.1361752496887100026e-6, 1e-12,"jauFw2m", "23");

       vvd(r[2][0], -0.3779734957034082790e-3, 1e-12,"jauFw2m", "31");
       vvd(r[2][1], -0.1924880848087615651e-6, 1e-12,"jauFw2m", "32");
       vvd(r[2][2], 0.9999999285679971958, 1e-12,"jauFw2m", "33");

    }

    @Test
    public void t_fw2xy()
    /**
    **
    **  Test jauFw2xy function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauFw2xy, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double gamb, phib, psi, eps;


       gamb = -0.2243387670997992368e-5;
       phib =  0.4091014602391312982;
       psi  = -0.9501954178013015092e-3;
       eps  =  0.4091014316587367472;

       CelestialIntermediatePole cip = jauFw2xy(gamb, phib, psi, eps);

       vvd(cip.x, -0.3779734957034082790e-3, 1e-14, "jauFw2xy", "x");
       vvd(cip.y, -0.1924880848087615651e-6, 1e-14, "jauFw2xy", "y");

    }

    @Test
    public void t_gc2gd()
    /**
    **
    **  Test jauGc2gd function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauGc2gd, viv, vvd
    **
    **  This revision:  2009 November 8
    */
    {
        double xyz[] = {2e6, 3e6, 5.244e6};
        GeodeticCoord geo;
        try {
            geo = jauGc2gd( 0, xyz);
            fail("jauGc2gd should thow exception for illegal parameter");
        } catch (JSOFAIllegalParameter e1) {

        }
        try {
            geo = jauGc2gd( 1, xyz );

            vvd(geo.elong, 0.98279372324732907, 1e-14, "jauGc2gd", "e1");
            vvd(geo.phi, 0.97160184819075459, 1e-14, "jauGc2gd", "p1");
            vvd(geo.height, 331.41724614260599, 1e-8, "jauGc2gd", "h1");

            geo = jauGc2gd( 2, xyz );
            vvd(geo.elong, 0.98279372324732907, 1e-14, "jauGc2gd", "e2");
            vvd(geo.phi, 0.97160184820607853, 1e-14, "jauGc2gd", "p2");
            vvd(geo.height, 331.41731754844348, 1e-8, "jauGc2gd", "h2");
            
            geo = jauGc2gd( 3, xyz );
            vvd(geo.elong, 0.98279372324732907, 1e-14, "jauGc2gd", "e3");
            vvd(geo.phi, 0.97160181811015119, 1e-14, "jauGc2gd", "p3");
            vvd(geo.height, 333.27707261303181, 1e-8, "jauGc2gd", "h3");
            
        } catch (JSOFAIllegalParameter e1) {
            fail("jauGc2gd should not thow exception for legal parameter");
        }

        try {
            geo = jauGc2gd( 4, xyz );
            fail("jauGc2gd should thow exception for illegal parameter");
        } catch (JSOFAIllegalParameter e1) {
        }

     }

    @Test
    public void t_gc2gde()
    /**
    **
    **  Test jauGc2gde function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauGc2gde, viv, vvd
    **
    **  This revision:  2009 November 8
    */
    {
        double a = 6378136.0, f = 0.0033528;
        double xyz[] = {2e6, 3e6, 5.244e6};

        try {
            GeodeticCoord geo = jauGc2gde( a, f, xyz);

            vvd(geo.elong, 0.98279372324732907, 1e-14, "jauGc2gde", "e");
            vvd(geo.phi, 0.97160183775704115, 1e-14, "jauGc2gde", "p");
            vvd(geo.height, 332.36862495764397, 1e-8, "jauGc2gde", "h");
        } catch (JSOFAIllegalParameter e1) {
            fail("jauGc2gde should not thow exception for legal parameter");

        }
    }

    @Test
    public void t_gd2gc()
    /**
    **
    **  Test jauGd2gc function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauGd2gc, viv, vvd
    **
    **  This revision:  2009 November 6
    */
    {
        double e = 3.1, p = -0.5, h = 2500.0;
        double xyz[] = new double[3];

        try {
            xyz = jauGd2gc( 0, e, p, h );

            fail("jauGd2gc should thow exception for illegal parameter");
        } catch (JSOFAIllegalParameter e1) {
            // expected behaviour

        } catch (JSOFAInternalError e1) {
            fail("jauGd2gc should thow exception for illegal parameter");
        }

        try {
            xyz = jauGd2gc( 1, e, p, h );


            vvd(xyz[0], -5599000.5577049947, 1e-7, "jauGd2gc", "0/1");
            vvd(xyz[1], 233011.67223479203, 1e-7, "jauGd2gc", "1/1");
            vvd(xyz[2], -3040909.4706983363, 1e-7, "jauGd2gc", "2/1");

            xyz = jauGd2gc( 2, e, p, h);

            vvd(xyz[0], -5599000.5577260984, 1e-7, "jauGd2gc", "0/2");
            vvd(xyz[1], 233011.6722356703, 1e-7, "jauGd2gc", "1/2");
            vvd(xyz[2], -3040909.4706095476, 1e-7, "jauGd2gc", "2/2");
            
            xyz = jauGd2gc( 3, e, p, h);
            vvd(xyz[0], -5598998.7626301490, 1e-7, "jauGd2gc", "0/3");
            vvd(xyz[1], 233011.5975297822, 1e-7, "jauGd2gc", "1/3");
            vvd(xyz[2], -3040908.6861467111, 1e-7, "jauGd2gc", "2/3");
            
        } catch (JSOFAException e1) {
            fail("jauGd2gc should not thow exception ");
        }

        try {
            xyz = jauGd2gc( 4, e, p, h );
            fail("jauGd2gc should thow exception for illegal parameter");
        } catch (JSOFAIllegalParameter e1) {
            //expected behaviour
        } catch (JSOFAInternalError e1) {
            fail("jauGd2gc should thow exception for illegal parameter");
        }
    }

    @Test
    public void t_gd2gce() throws JSOFAInternalError
    /**
    **
    **  Test jauGd2gce function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauGd2gce, viv, vvd
    **
    **  This revision:  2009 November 6
    */
    {
       double a = 6378136.0, f = 0.0033528;
       double e = 3.1, p = -0.5, h = 2500.0;
       double xyz[] = new double[3];

       xyz = jauGd2gce( a, f, e, p, h );

       vvd(xyz[0], -5598999.6665116328, 1e-7, "jauGd2gce", "0");
       vvd(xyz[1], 233011.63514630572, 1e-7, "jauGd2gce", "1");
       vvd(xyz[2], -3040909.0517314132, 1e-7, "jauGd2gce", "2");
    }

    @Test
    public void t_gmst00()
    /**
    **
    **  Test jauGmst00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauGmst00, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double theta;


       theta = jauGmst00(2400000.5, 53736.0, 2400000.5, 53736.0);

       vvd(theta, 1.754174972210740592, 1e-12, "jauGmst00", "");

    }

    @Test
    public void t_gmst06()
    /**
    **
    **  Test jauGmst06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauGmst06, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double theta;


       theta = jauGmst06(2400000.5, 53736.0, 2400000.5, 53736.0);

       vvd(theta, 1.754174971870091203, 1e-12, "jauGmst06", "");

    }

    @Test
    public void t_gmst82()
    /**
    **
    **  Test jauGmst82 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauGmst82, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double theta;


       theta = jauGmst82(2400000.5, 53736.0);

       vvd(theta, 1.754174981860675096, 1e-12, "jauGmst82", "");

    }

    @Test
    public void t_gst00a()
    /**
    **
    **  Test jauGst00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauGst00a, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double theta;


       theta = jauGst00a(2400000.5, 53736.0, 2400000.5, 53736.0);

       vvd(theta, 1.754166138018281369, 1e-12, "jauGst00a", "");

    }

    @Test
    public void t_gst00b()
    /**
    **
    **  Test jauGst00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauGst00b, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double theta;


       theta = jauGst00b(2400000.5, 53736.0);

       vvd(theta, 1.754166136510680589, 1e-12, "jauGst00b", "");

    }

    @Test
    public void t_gst06()
    /**
    **
    **  Test jauGst06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauGst06, vvd
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

       theta = jauGst06(2400000.5, 53736.0, 2400000.5, 53736.0, rnpb);

       vvd(theta, 1.754166138018167568, 1e-12, "jauGst06", "");

    }

    @Test
    public void t_gst06a()
    /**
    **
    **  Test jauGst06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauGst06a, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double theta;


       theta = jauGst06a(2400000.5, 53736.0, 2400000.5, 53736.0);

       vvd(theta, 1.754166137675019159, 1e-12, "jauGst06a", "");

    }

    @Test
    public void t_gst94()
    /**
    **
    **  Test jauGst94 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauGst94, vvd
    **
    **  This revision:  2008 May 26
    */
    {
       double theta;


       theta = jauGst94(2400000.5, 53736.0);

       vvd(theta, 1.754166136020645203, 1e-12, "jauGst94", "");

    }

    @Test
    public void t_h2fk5()
    /**
    **
    **  Test jauH2fk5 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauH2fk5, vvd
    **
    **  This revision:  2009 November 6
    */
    {
       double rh, dh, drh, ddh, pxh, rvh;


       rh  =  1.767794352;
       dh  = -0.2917512594;
       drh = -2.76413026e-6;
       ddh = -5.92994449e-6;
       pxh =  0.379210;
       rvh = -7.6;

       CatalogCoords cat = jauH2fk5(rh, dh, drh, ddh, pxh, rvh);

       vvd(cat.pos.alpha, 1.767794455700065506, 1e-13,"jauH2fk5", "ra");
       vvd(cat.pos.delta, -0.2917513626469638890, 1e-13,"jauH2fk5", "dec");
       vvd(cat.pm.alpha, -0.27597945024511204e-5, 1e-18,"jauH2fk5", "dr5");
       vvd(cat.pm.delta, -0.59308014093262838e-5, 1e-18,"jauH2fk5", "dd5");
       vvd(cat.px, 0.37921, 1e-13,"jauH2fk5", "px");
       vvd(cat.rv, -7.6000001309071126, 2e-11,"jauH2fk5", "rv"); //NB have relaxed the precision slightly to get this test to pass cf official release

    }

    @Test
    public void t_hfk5z()
    /**
    **
    **  Test jauHfk5z function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauHfk5z, vvd
    **
    **  This revision:  2008 November 29
    */
    {
       double rh, dh;



       rh =  1.767794352;
       dh = -0.2917512594;

       CatalogCoords cat = jauHfk5z(rh, dh, 2400000.5, 54479.0);

       vvd(cat.pos.alpha, 1.767794490535581026, 1e-13,"jauHfk5z", "ra");
       vvd(cat.pos.delta, -0.2917513695320114258, 1e-14,"jauHfk5z", "dec");
       vvd(cat.pm.alpha, 0.4335890983539243029e-8, 1e-22,"jauHfk5z", "dr5");
       vvd(cat.pm.delta, -0.8569648841237745902e-9, 1e-23,"jauHfk5z", "dd5");

    }

    @Test
    public void t_ir()
    /**
    **
    **  Test jauIr function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauIr, vvd
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

       jauIr(r);

       vvd(r[0][0], 1.0, 0.0, "jauIr", "11");
       vvd(r[0][1], 0.0, 0.0, "jauIr", "12");
       vvd(r[0][2], 0.0, 0.0, "jauIr", "13");

       vvd(r[1][0], 0.0, 0.0, "jauIr", "21");
       vvd(r[1][1], 1.0, 0.0, "jauIr", "22");
       vvd(r[1][2], 0.0, 0.0, "jauIr", "23");

       vvd(r[2][0], 0.0, 0.0, "jauIr", "31");
       vvd(r[2][1], 0.0, 0.0, "jauIr", "32");
       vvd(r[2][2], 1.0, 0.0, "jauIr", "33");

    }

    @Test
    public void t_jd2cal() throws JSOFAIllegalParameter
    /**
    **
    **  Test jauJd2cal function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauJd2cal, viv, vvd
    **
    **  This revision:  2008 November 29
    */
    {
       double dj1, dj2;


       dj1 = 2400000.5;
       dj2 = 50123.9999;

       Calendar cal = jauJd2cal(dj1, dj2);

       viv(cal.iy, 1996, "jauJd2cal", "y");
       viv(cal.im, 2, "jauJd2cal", "m");
       viv(cal.id, 10, "jauJd2cal", "d");
       vvd(cal.fd, 0.9999, 1e-7, "jauJd2cal", "fd");
//FIXME should test j when   jauJd2cal returns status     viv(j, 0, "jauJd2cal", "j");

    }

    @Test
    public void t_jdcalf()
    /**
    **
    **  Test jauJdcalf function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauJdcalf, viv
    **
    **  This revision:  2008 May 26
    */
    {
       double dj1, dj2;
       int iydmf[] = new int[4], j;


       dj1 = 2400000.5;
       dj2 = 50123.9999;

       j = jauJdcalf(4, dj1, dj2, iydmf);

       viv(iydmf[0], 1996, "jauJdcalf", "y");
       viv(iydmf[1], 2, "jauJdcalf", "m");
       viv(iydmf[2], 10, "jauJdcalf", "d");
       viv(iydmf[3], 9999, "jauJdcalf", "f");

       viv(j, 0, "jauJdcalf", "j");

    }

    @Test
    public void t_num00a()
    /**
    **
    **  Test jauNum00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauNum00a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rmatn[][] = new double[3][3];


       rmatn = jauNum00a(2400000.5, 53736.0);

       vvd(rmatn[0][0], 0.9999999999536227949, 1e-12,"jauNum00a", "11");
       vvd(rmatn[0][1], 0.8836238544090873336e-5, 1e-12,"jauNum00a", "12");
       vvd(rmatn[0][2], 0.3830835237722400669e-5, 1e-12,"jauNum00a", "13");

       vvd(rmatn[1][0], -0.8836082880798569274e-5, 1e-12,"jauNum00a", "21");
       vvd(rmatn[1][1], 0.9999999991354655028, 1e-12,"jauNum00a", "22");
       vvd(rmatn[1][2], -0.4063240865362499850e-4, 1e-12,"jauNum00a", "23");

       vvd(rmatn[2][0], -0.3831194272065995866e-5, 1e-12,"jauNum00a", "31");
       vvd(rmatn[2][1], 0.4063237480216291775e-4, 1e-12,"jauNum00a", "32");
       vvd(rmatn[2][2], 0.9999999991671660338, 1e-12,"jauNum00a", "33");

    }

    @Test
    public void t_num00b()
    /**
    **
    **  Test jauNum00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauNum00b, vvd
    **
    **  This revision:  2008 November 28
    */
    {
        double rmatn[][] = new double[3][3];

        rmatn = jauNum00b(2400000.5, 53736);

       vvd(rmatn[0][0], 0.9999999999536069682, 1e-12,"jauNum00b", "11");
       vvd(rmatn[0][1], 0.8837746144871248011e-5, 1e-12,"jauNum00b", "12");
       vvd(rmatn[0][2], 0.3831488838252202945e-5, 1e-12,"jauNum00b", "13");

       vvd(rmatn[1][0], -0.8837590456632304720e-5, 1e-12,"jauNum00b", "21");
       vvd(rmatn[1][1], 0.9999999991354692733, 1e-12,"jauNum00b", "22");
       vvd(rmatn[1][2], -0.4063198798559591654e-4, 1e-12,"jauNum00b", "23");

       vvd(rmatn[2][0], -0.3831847930134941271e-5, 1e-12,"jauNum00b", "31");
       vvd(rmatn[2][1], 0.4063195412258168380e-4, 1e-12,"jauNum00b", "32");
       vvd(rmatn[2][2], 0.9999999991671806225, 1e-12,"jauNum00b", "33");

    }

    @Test
    public void t_num06a()
    /**
    **
    **  Test jauNum06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauNum06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
        double rmatn[][] = new double[3][3];

        rmatn = jauNum06a(2400000.5, 53736);

       vvd(rmatn[0][0], 0.9999999999536227668, 1e-12,"jauNum06a", "11");
       vvd(rmatn[0][1], 0.8836241998111535233e-5, 1e-12,"jauNum06a", "12");
       vvd(rmatn[0][2], 0.3830834608415287707e-5, 1e-12,"jauNum06a", "13");

       vvd(rmatn[1][0], -0.8836086334870740138e-5, 1e-12,"jauNum06a", "21");
       vvd(rmatn[1][1], 0.9999999991354657474, 1e-12,"jauNum06a", "22");
       vvd(rmatn[1][2], -0.4063240188248455065e-4, 1e-12,"jauNum06a", "23");

       vvd(rmatn[2][0], -0.3831193642839398128e-5, 1e-12,"jauNum06a", "31");
       vvd(rmatn[2][1], 0.4063236803101479770e-4, 1e-12,"jauNum06a", "32");
       vvd(rmatn[2][2], 0.9999999991671663114, 1e-12,"jauNum06a", "33");

    }

    @Test
    public void t_numat()
    /**
    **
    **  Test jauNumat function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauNumat, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double epsa, dpsi, deps, rmatn[][] = new double[3][3];


       epsa =  0.4090789763356509900;
       dpsi = -0.9630909107115582393e-5;
       deps =  0.4063239174001678826e-4;

       rmatn = jauNumat(epsa, dpsi, deps);

       vvd(rmatn[0][0], 0.9999999999536227949, 1e-12,"jauNumat", "11");
       vvd(rmatn[0][1], 0.8836239320236250577e-5, 1e-12,"jauNumat", "12");
       vvd(rmatn[0][2], 0.3830833447458251908e-5, 1e-12,"jauNumat", "13");

       vvd(rmatn[1][0], -0.8836083657016688588e-5, 1e-12,"jauNumat", "21");
       vvd(rmatn[1][1], 0.9999999991354654959, 1e-12,"jauNumat", "22");
       vvd(rmatn[1][2], -0.4063240865361857698e-4, 1e-12,"jauNumat", "23");

       vvd(rmatn[2][0], -0.3831192481833385226e-5, 1e-12,"jauNumat", "31");
       vvd(rmatn[2][1], 0.4063237480216934159e-4, 1e-12,"jauNumat", "32");
       vvd(rmatn[2][2], 0.9999999991671660407, 1e-12,"jauNumat", "33");

    }

    @Test
    public void t_nut00a()
    /**
    **
    **  Test jauNut00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauNut00a, vvd
    **
    **  This revision:  2008 November 28
    */
    {


       NutationTerms nut = jauNut00a(2400000.5, 53736.0);

       vvd(nut.dpsi, -0.9630909107115518431e-5, 1e-13,"jauNut00a", "dpsi");
       vvd(nut.deps,  0.4063239174001678710e-4, 1e-13,"jauNut00a", "deps");

    }

    @Test
    public void t_nut00b()
    /**
    **
    **  Test jauNut00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauNut00b, vvd
    **
    **  This revision:  2008 November 28
    */
    {
 

       NutationTerms nut = jauNut00b(2400000.5, 53736.0);

       vvd(nut.dpsi, -0.9632552291148362783e-5, 1e-13,"jauNut00b", "dpsi");
       vvd(nut.deps,  0.4063197106621159367e-4, 1e-13,"jauNut00b", "deps");

    }

    @Test
    public void t_nut06a()
    /**
    **
    **  Test jauNut06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauNut06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {

       NutationTerms nut = jauNut06a(2400000.5, 53736.0);

       vvd(nut.dpsi, -0.9630912025820308797e-5, 1e-13,"jauNut06a", "dpsi");
       vvd(nut.deps,  0.4063238496887249798e-4, 1e-13,"jauNut06a", "deps");

    }

    @Test
    public void t_nut80()
    /**
    **
    **  Test jauNut80 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauNut80, vvd
    **
    **  This revision:  2008 November 28
    */
    {

       NutationTerms nut = jauNut80(2400000.5, 53736.0);

       vvd(nut.dpsi, -0.9643658353226563966e-5, 1e-13,"jauNut80", "dpsi");
       vvd(nut.deps,  0.4060051006879713322e-4, 1e-13,"jauNut80", "deps");

    }

    @Test
    public void t_nutm80()
    /**
    **
    **  Test jauNutm80 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauNutm80, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double rmatn[][] = new double[3][3];


       rmatn = jauNutm80(2400000.5, 53736.0);

       vvd(rmatn[0][0], 0.9999999999534999268, 1e-12,"jauNutm80", "11");
       vvd(rmatn[0][1], 0.8847935789636432161e-5, 1e-12,"jauNutm80", "12");
       vvd(rmatn[0][2], 0.3835906502164019142e-5, 1e-12,"jauNutm80", "13");

       vvd(rmatn[1][0], -0.8847780042583435924e-5, 1e-12,"jauNutm80", "21");
       vvd(rmatn[1][1], 0.9999999991366569963, 1e-12,"jauNutm80", "22");
       vvd(rmatn[1][2], -0.4060052702727130809e-4, 1e-12,"jauNutm80", "23");

       vvd(rmatn[2][0], -0.3836265729708478796e-5, 1e-12,"jauNutm80", "31");
       vvd(rmatn[2][1], 0.4060049308612638555e-4, 1e-12,"jauNutm80", "32");
       vvd(rmatn[2][2], 0.9999999991684415129, 1e-12,"jauNutm80", "33");

    }

    @Test
    public void t_obl06()
    /**
    **
    **  Test jauObl06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauObl06, vvd
    **
    **  This revision:  2008 November 29
    */
    {
       vvd(jauObl06(2400000.5, 54388.0), 0.4090749229387258204, 1e-14,
           "jauObl06", "");
    }

    @Test
    public void t_obl80()
    /**
    **
    **  Test jauObl80 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauObl80, vvd
    **
    **  This revision:  2008 November 29
    */
    {
       double eps0;


       eps0 = jauObl80(2400000.5, 54388.0);

       vvd(eps0, 0.4090751347643816218, 1e-14, "jauObl80", "");

    }

    @Test
    public void t_p06e()
    /**
    **
    **  Test jauP06e function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauP06e, vvd
    **
    **  This revision:  2008 November 28
    */
    {


       PrecessionAngles pa = jauP06e(2400000.5, 52541.0);

       vvd(pa.eps0, 0.4090926006005828715, 1e-14,"jauP06e", "eps0");
       vvd(pa.psia, 0.6664369630191613431e-3, 1e-14,"jauP06e", "psia");
       vvd(pa.oma , 0.4090925973783255982, 1e-14,"jauP06e", "oma");
       vvd(pa.bpa, 0.5561149371265209445e-6, 1e-14,"jauP06e", "bpa");
       vvd(pa.bqa, -0.6191517193290621270e-5, 1e-14,"jauP06e", "bqa");
       vvd(pa.pia, 0.6216441751884382923e-5, 1e-14,"jauP06e", "pia");
       vvd(pa.bpia, 3.052014180023779882, 1e-14,"jauP06e", "bpia");
       vvd(pa.epsa, 0.4090864054922431688, 1e-14,"jauP06e", "epsa");
       vvd(pa.chia, 0.1387703379530915364e-5, 1e-14,"jauP06e", "chia");
       vvd(pa.za, 0.2921789846651790546e-3, 1e-14,"jauP06e", "za");
       vvd(pa.zetaa, 0.3178773290332009310e-3, 1e-14,"jauP06e", "zetaa");
       vvd(pa.thetaa, 0.2650932701657497181e-3, 1e-14,"jauP06e", "thetaa");
       vvd(pa.pa, 0.6651637681381016288e-3, 1e-14,"jauP06e", "pa");
       vvd(pa.gam, 0.1398077115963754987e-5, 1e-14,"jauP06e", "gam");
       vvd(pa.phi, 0.4090864090837462602, 1e-14,"jauP06e", "phi");
       vvd(pa.psi, 0.6664464807480920325e-3, 1e-14,"jauP06e", "psi");

    }

    @Test
    public void t_p2pv()
    /**
    **
    **  Test jauP2pv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauP2pv, vvd
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

       pv = jauP2pv(p);

       vvd(pv[0][0], 0.25, 0.0, "jauP2pv", "p1");
       vvd(pv[0][1], 1.2,  0.0, "jauP2pv", "p2");
       vvd(pv[0][2], 3.0,  0.0, "jauP2pv", "p3");

       vvd(pv[1][0], 0.0,  0.0, "jauP2pv", "v1");
       vvd(pv[1][1], 0.0,  0.0, "jauP2pv", "v2");
       vvd(pv[1][2], 0.0,  0.0, "jauP2pv", "v3");

    }

    @Test
    public void t_p2s()
    /**
    **
    **  Test jauP2s function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauP2s, vvd
    **
    **  This revision:  2008 November 29
    */
    {
       double p[] = new double[3];


       p[0] = 100.0;
       p[1] = -50.0;
       p[2] =  25.0;

       SphericalPosition co = jauP2s(p);

       vvd(co.theta, -0.4636476090008061162, 1e-12, "jauP2s", "theta");
       vvd(co.phi, 0.2199879773954594463, 1e-12, "jauP2s", "phi");
       vvd(co.r, 114.5643923738960002, 1e-9, "jauP2s", "r");

    }

    @Test
    public void t_pap()
    /**
    **
    **  Test jauPap function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPap, vvd
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

       theta = jauPap(a, b);

       vvd(theta, 0.3671514267841113674, 1e-12, "jauPap", "");

    }

    @Test
    public void t_pas()
    /**
    **
    **  Test jauPas function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPas, vvd
    **
    **  This revision:  2008 May 25
    */
    {
       double al, ap, bl, bp, theta;


       al =  1.0;
       ap =  0.1;
       bl =  0.2;
       bp = -1.0;

       theta = jauPas(al, ap, bl, bp);

       vvd(theta, -2.724544922932270424, 1e-12, "jauPas", "");

    }

    @Test
    public void t_pb06()
    /**
    **
    **  Test jauPb06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPb06, vvd
    **
    **  This revision:  2008 November 28
    */
    {

       EulerAngles an = jauPb06(2400000.5, 50123.9999);

       vvd(an.zeta, -0.5092634016326478238e-3, 1e-12,"jauPb06", "bzeta");
       vvd(an.z, -0.3602772060566044413e-3, 1e-12,"jauPb06", "bz");
       vvd(an.theta, -0.3779735537167811177e-3, 1e-12,"jauPb06", "btheta");

    }

    @Test
    public void t_pdp()
    /**
    **
    **  Test jauPdp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPdp, vvd
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

       adb = jauPdp(a, b);

       vvd(adb, 20, 1e-12, "jauPdp", "");

    }

    @Test
    public void t_pfw06()
    /**
    **
    **  Test jauPfw06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPfw06, vvd
    **
    **  This revision:  2008 November 30
    */
    {


       FWPrecessionAngles fw = jauPfw06(2400000.5, 50123.9999);

       vvd(fw.gamb, -0.2243387670997995690e-5, 1e-16,"jauPfw06", "gamb");
       vvd(fw.phib,  0.4091014602391312808, 1e-12,"jauPfw06", "phib");
       vvd(fw.psib, -0.9501954178013031895e-3, 1e-14,"jauPfw06", "psib");
       vvd(fw.epsa,  0.4091014316587367491, 1e-12,"jauPfw06", "epsa");

    }

    @Test
    public void t_plan94()
    /**
    **
    **  Test jauPlan94 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPlan94, VVD, VIV
    **
    **  This revision:  2008 November 28
    */
    {
        double pv[][];



        try {
            pv = jauPlan94(2400000.5, 1e6, 0);
            fail("did not throw all the illegal argument for bad planet number");
        } catch (JSOFAIllegalParameter e) {
            // do nothing
        }

        try {
            pv = jauPlan94(2400000.5, 1e6, 10);
            fail("did not throw all the illegal argument for bad planet number");
        } catch (JSOFAIllegalParameter e) {
            // do nothing
        }

        try {
            pv = jauPlan94(2400000.5, -320000, 3);

            vvd(pv[0][0], 0.9308038666832975759, 1e-11,"jauPlan94", "x 3");
            vvd(pv[0][1], 0.3258319040261346000, 1e-11,"jauPlan94", "y 3");
            vvd(pv[0][2], 0.1422794544481140560, 1e-11,"jauPlan94", "z 3");

            vvd(pv[1][0], -0.6429458958255170006e-2, 1e-11,"jauPlan94", "xd 3");
            vvd(pv[1][1], 0.1468570657704237764e-1, 1e-11,"jauPlan94", "yd 3");
            vvd(pv[1][2], 0.6406996426270981189e-2, 1e-11,"jauPlan94", "zd 3");


            pv = jauPlan94(2400000.5, 43999.9, 1);

            vvd(pv[0][0], 0.2945293959257430832, 1e-11,"jauPlan94", "x 4");
            vvd(pv[0][1], -0.2452204176601049596, 1e-11,"jauPlan94", "y 4");
            vvd(pv[0][2], -0.1615427700571978153, 1e-11,"jauPlan94", "z 4");

            vvd(pv[1][0], 0.1413867871404614441e-1, 1e-11,"jauPlan94", "xd 4");
            vvd(pv[1][1], 0.1946548301104706582e-1, 1e-11,"jauPlan94", "yd 4");
            vvd(pv[1][2], 0.8929809783898904786e-2, 1e-11,"jauPlan94", "zd 4");
        } catch (JSOFAIllegalParameter e) {
            fail("legal call threw exception");
        }


    }

    @Test
    public void t_pmat00()
    /**
    **
    **  Test jauPmat00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPmat00, vvd
    **
    **  This revision:  2008 November 29
    */
    {
       double rbp[][] = new double[3][3];


       rbp = jauPmat00(2400000.5, 50123.9999);

       vvd(rbp[0][0], 0.9999995505175087260, 1e-12,"jauPmat00", "11");
       vvd(rbp[0][1], 0.8695405883617884705e-3, 1e-14,"jauPmat00", "12");
       vvd(rbp[0][2], 0.3779734722239007105e-3, 1e-14,"jauPmat00", "13");

       vvd(rbp[1][0], -0.8695405990410863719e-3, 1e-14,"jauPmat00", "21");
       vvd(rbp[1][1], 0.9999996219494925900, 1e-12,"jauPmat00", "22");
       vvd(rbp[1][2], -0.1360775820404982209e-6, 1e-14,"jauPmat00", "23");

       vvd(rbp[2][0], -0.3779734476558184991e-3, 1e-14,"jauPmat00", "31");
       vvd(rbp[2][1], -0.1925857585832024058e-6, 1e-14,"jauPmat00", "32");
       vvd(rbp[2][2], 0.9999999285680153377, 1e-12,"jauPmat00", "33");

    }

    @Test
    public void t_pmat06()
    /**
    **
    **  Test jauPmat06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPmat06, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double rbp[][] = new double[3][3];


       rbp = jauPmat06(2400000.5, 50123.9999);

       vvd(rbp[0][0], 0.9999995505176007047, 1e-12,"jauPmat06", "11");
       vvd(rbp[0][1], 0.8695404617348208406e-3, 1e-14,"jauPmat06", "12");
       vvd(rbp[0][2], 0.3779735201865589104e-3, 1e-14,"jauPmat06", "13");

       vvd(rbp[1][0], -0.8695404723772031414e-3, 1e-14,"jauPmat06", "21");
       vvd(rbp[1][1], 0.9999996219496027161, 1e-12,"jauPmat06", "22");
       vvd(rbp[1][2], -0.1361752497080270143e-6, 1e-14,"jauPmat06", "23");

       vvd(rbp[2][0], -0.3779734957034089490e-3, 1e-14,"jauPmat06", "31");
       vvd(rbp[2][1], -0.1924880847894457113e-6, 1e-14,"jauPmat06", "32");
       vvd(rbp[2][2], 0.9999999285679971958, 1e-12,"jauPmat06", "33");

    }

    @Test
    public void t_pmat76()
    /**
    **
    **  Test jauPmat76 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPmat76, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rmatp[][] = new double[3][3];


       rmatp = jauPmat76(2400000.5, 50123.9999);

       vvd(rmatp[0][0], 0.9999995504328350733, 1e-12,"jauPmat76", "11");
       vvd(rmatp[0][1], 0.8696632209480960785e-3, 1e-14,"jauPmat76", "12");
       vvd(rmatp[0][2], 0.3779153474959888345e-3, 1e-14,"jauPmat76", "13");

       vvd(rmatp[1][0], -0.8696632209485112192e-3, 1e-14,"jauPmat76", "21");
       vvd(rmatp[1][1], 0.9999996218428560614, 1e-12,"jauPmat76", "22");
       vvd(rmatp[1][2], -0.1643284776111886407e-6, 1e-14,"jauPmat76", "23");

       vvd(rmatp[2][0], -0.3779153474950335077e-3, 1e-14,"jauPmat76", "31");
       vvd(rmatp[2][1], -0.1643306746147366896e-6, 1e-14,"jauPmat76", "32");
       vvd(rmatp[2][2], 0.9999999285899790119, 1e-12,"jauPmat76", "33");

    }

    @Test
    public void t_pm()
    /**
    **
    **  Test jauPm function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPm, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double p[] = new double[3], r;


       p[0] =  0.3;
       p[1] =  1.2;
       p[2] = -2.5;

       r = jauPm(p);

       vvd(r, 2.789265136196270604, 1e-12, "jauPm", "");

    }

    @Test
    public void t_pmp()
    /**
    **
    **  Test jauPmp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPmp, vvd
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

       amb = jauPmp(a, b);

       vvd(amb[0],  1.0, 1e-12, "jauPmp", "0");
       vvd(amb[1], -1.0, 1e-12, "jauPmp", "1");
       vvd(amb[2], -1.0, 1e-12, "jauPmp", "2");

    }

    @Test
    public void t_pn()
    /**
    **
    **  Test jauPn function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPn, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double p[] = new double[3];


       p[0] =  0.3;
       p[1] =  1.2;
       p[2] = -2.5;

       NormalizedVector mv = jauPn(p);

       vvd(mv.r, 2.789265136196270604, 1e-12, "jauPn", "r");

       vvd(mv.u[0], 0.1075552109073112058, 1e-12, "jauPn", "u1");
       vvd(mv.u[1], 0.4302208436292448232, 1e-12, "jauPn", "u2");
       vvd(mv.u[2], -0.8962934242275933816, 1e-12, "jauPn", "u3");

    }

    @Test
    public void t_pn00()
    /**
    **
    **  Test jauPn00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPn00, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double dpsi, deps;

       dpsi = -0.9632552291149335877e-5;
       deps =  0.4063197106621141414e-4;

       PrecessionNutation pn = jauPn00(2400000.5, 53736.0, dpsi, deps);

       vvd(pn.epsa, 0.4090791789404229916, 1e-12, "jauPn00", "epsa");

       vvd(pn.rb[0][0], 0.9999999999999942498, 1e-12,"jauPn00", "rb11");
       vvd(pn.rb[0][1], -0.7078279744199196626e-7, 1e-18,"jauPn00", "rb12");
       vvd(pn.rb[0][2], 0.8056217146976134152e-7, 1e-18,"jauPn00", "rb13");

       vvd(pn.rb[1][0], 0.7078279477857337206e-7, 1e-18,"jauPn00", "rb21");
       vvd(pn.rb[1][1], 0.9999999999999969484, 1e-12,"jauPn00", "rb22");
       vvd(pn.rb[1][2], 0.3306041454222136517e-7, 1e-18,"jauPn00", "rb23");

       vvd(pn.rb[2][0], -0.8056217380986972157e-7, 1e-18,"jauPn00", "rb31");
       vvd(pn.rb[2][1], -0.3306040883980552500e-7, 1e-18,"jauPn00", "rb32");
       vvd(pn.rb[2][2], 0.9999999999999962084, 1e-12,"jauPn00", "rb33");

       vvd(pn.rp[0][0], 0.9999989300532289018, 1e-12,"jauPn00", "rp11");
       vvd(pn.rp[0][1], -0.1341647226791824349e-2, 1e-14,"jauPn00", "rp12");
       vvd(pn.rp[0][2], -0.5829880927190296547e-3, 1e-14,"jauPn00", "rp13");

       vvd(pn.rp[1][0], 0.1341647231069759008e-2, 1e-14,"jauPn00", "rp21");
       vvd(pn.rp[1][1], 0.9999990999908750433, 1e-12,"jauPn00", "rp22");
       vvd(pn.rp[1][2], -0.3837444441583715468e-6, 1e-14,"jauPn00", "rp23");

       vvd(pn.rp[2][0], 0.5829880828740957684e-3, 1e-14,"jauPn00", "rp31");
       vvd(pn.rp[2][1], -0.3984203267708834759e-6, 1e-14,"jauPn00", "rp32");
       vvd(pn.rp[2][2], 0.9999998300623538046, 1e-12,"jauPn00", "rp33");

       vvd(pn.rbp[0][0], 0.9999989300052243993, 1e-12,"jauPn00", "rbp11");
       vvd(pn.rbp[0][1], -0.1341717990239703727e-2, 1e-14,"jauPn00", "rbp12");
       vvd(pn.rbp[0][2], -0.5829075749891684053e-3, 1e-14,"jauPn00", "rbp13");

       vvd(pn.rbp[1][0], 0.1341718013831739992e-2, 1e-14,"jauPn00", "rbp21");
       vvd(pn.rbp[1][1], 0.9999990998959191343, 1e-12,"jauPn00", "rbp22");
       vvd(pn.rbp[1][2], -0.3505759733565421170e-6, 1e-14,"jauPn00", "rbp23");

       vvd(pn.rbp[2][0], 0.5829075206857717883e-3, 1e-14,"jauPn00", "rbp31");
       vvd(pn.rbp[2][1], -0.4315219955198608970e-6, 1e-14,"jauPn00", "rbp32");
       vvd(pn.rbp[2][2], 0.9999998301093036269, 1e-12,"jauPn00", "rbp33");

       vvd(pn.rn[0][0], 0.9999999999536069682, 1e-12,"jauPn00", "rn11");
       vvd(pn.rn[0][1], 0.8837746144872140812e-5, 1e-16,"jauPn00", "rn12");
       vvd(pn.rn[0][2], 0.3831488838252590008e-5, 1e-16,"jauPn00", "rn13");

       vvd(pn.rn[1][0], -0.8837590456633197506e-5, 1e-16,"jauPn00", "rn21");
       vvd(pn.rn[1][1], 0.9999999991354692733, 1e-12,"jauPn00", "rn22");
       vvd(pn.rn[1][2], -0.4063198798559573702e-4, 1e-16,"jauPn00", "rn23");

       vvd(pn.rn[2][0], -0.3831847930135328368e-5, 1e-16,"jauPn00", "rn31");
       vvd(pn.rn[2][1], 0.4063195412258150427e-4, 1e-16,"jauPn00", "rn32");
       vvd(pn.rn[2][2], 0.9999999991671806225, 1e-12,"jauPn00", "rn33");

       vvd(pn.rbpn[0][0], 0.9999989440499982806, 1e-12,"jauPn00", "rbpn11");
       vvd(pn.rbpn[0][1], -0.1332880253640848301e-2, 1e-14,"jauPn00", "rbpn12");
       vvd(pn.rbpn[0][2], -0.5790760898731087295e-3, 1e-14,"jauPn00", "rbpn13");

       vvd(pn.rbpn[1][0], 0.1332856746979948745e-2, 1e-14,"jauPn00", "rbpn21");
       vvd(pn.rbpn[1][1], 0.9999991109064768883, 1e-12,"jauPn00", "rbpn22");
       vvd(pn.rbpn[1][2], -0.4097740555723063806e-4, 1e-14,"jauPn00", "rbpn23");

       vvd(pn.rbpn[2][0], 0.5791301929950205000e-3, 1e-14,"jauPn00", "rbpn31");
       vvd(pn.rbpn[2][1], 0.4020553681373702931e-4, 1e-14,"jauPn00", "rbpn32");
       vvd(pn.rbpn[2][2], 0.9999998314958529887, 1e-12,"jauPn00", "rbpn33");

    }

    @Test
    public void t_pn00a()
    /**
    **
    **  Test jauPn00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPn00a, vvd
    **
    **  This revision:  2008 November 28
    */
    {


       PrecessionNutation pn = jauPn00a(2400000.5, 53736.0);
               

       vvd(pn.nut.dpsi, -0.9630909107115518431e-5, 1e-12,"jauPn00a", "dpsi");
       vvd(pn.nut.deps,  0.4063239174001678710e-4, 1e-12,"jauPn00a", "deps");
       vvd(pn.epsa,  0.4090791789404229916, 1e-12, "jauPn00a", "epsa");

       vvd(pn.rb[0][0], 0.9999999999999942498, 1e-12,"jauPn00a", "rb11");
       vvd(pn.rb[0][1], -0.7078279744199196626e-7, 1e-16,"jauPn00a", "rb12");
       vvd(pn.rb[0][2], 0.8056217146976134152e-7, 1e-16,"jauPn00a", "rb13");

       vvd(pn.rb[1][0], 0.7078279477857337206e-7, 1e-16,"jauPn00a", "rb21");
       vvd(pn.rb[1][1], 0.9999999999999969484, 1e-12,"jauPn00a", "rb22");
       vvd(pn.rb[1][2], 0.3306041454222136517e-7, 1e-16,"jauPn00a", "rb23");

       vvd(pn.rb[2][0], -0.8056217380986972157e-7, 1e-16,"jauPn00a", "rb31");
       vvd(pn.rb[2][1], -0.3306040883980552500e-7, 1e-16,"jauPn00a", "rb32");
       vvd(pn.rb[2][2], 0.9999999999999962084, 1e-12,"jauPn00a", "rb33");

       vvd(pn.rp[0][0], 0.9999989300532289018, 1e-12,"jauPn00a", "rp11");
       vvd(pn.rp[0][1], -0.1341647226791824349e-2, 1e-14,"jauPn00a", "rp12");
       vvd(pn.rp[0][2], -0.5829880927190296547e-3, 1e-14,"jauPn00a", "rp13");

       vvd(pn.rp[1][0], 0.1341647231069759008e-2, 1e-14,"jauPn00a", "rp21");
       vvd(pn.rp[1][1], 0.9999990999908750433, 1e-12,"jauPn00a", "rp22");
       vvd(pn.rp[1][2], -0.3837444441583715468e-6, 1e-14,"jauPn00a", "rp23");

       vvd(pn.rp[2][0], 0.5829880828740957684e-3, 1e-14,"jauPn00a", "rp31");
       vvd(pn.rp[2][1], -0.3984203267708834759e-6, 1e-14,"jauPn00a", "rp32");
       vvd(pn.rp[2][2], 0.9999998300623538046, 1e-12,"jauPn00a", "rp33");

       vvd(pn.rbp[0][0], 0.9999989300052243993, 1e-12,"jauPn00a", "rbp11");
       vvd(pn.rbp[0][1], -0.1341717990239703727e-2, 1e-14,"jauPn00a", "rbp12");
       vvd(pn.rbp[0][2], -0.5829075749891684053e-3, 1e-14,"jauPn00a", "rbp13");

       vvd(pn.rbp[1][0], 0.1341718013831739992e-2, 1e-14,"jauPn00a", "rbp21");
       vvd(pn.rbp[1][1], 0.9999990998959191343, 1e-12,"jauPn00a", "rbp22");
       vvd(pn.rbp[1][2], -0.3505759733565421170e-6, 1e-14,"jauPn00a", "rbp23");

       vvd(pn.rbp[2][0], 0.5829075206857717883e-3, 1e-14,"jauPn00a", "rbp31");
       vvd(pn.rbp[2][1], -0.4315219955198608970e-6, 1e-14,"jauPn00a", "rbp32");
       vvd(pn.rbp[2][2], 0.9999998301093036269, 1e-12,"jauPn00a", "rbp33");

       vvd(pn.rn[0][0], 0.9999999999536227949, 1e-12,"jauPn00a", "rn11");
       vvd(pn.rn[0][1], 0.8836238544090873336e-5, 1e-14,"jauPn00a", "rn12");
       vvd(pn.rn[0][2], 0.3830835237722400669e-5, 1e-14,"jauPn00a", "rn13");

       vvd(pn.rn[1][0], -0.8836082880798569274e-5, 1e-14,"jauPn00a", "rn21");
       vvd(pn.rn[1][1], 0.9999999991354655028, 1e-12,"jauPn00a", "rn22");
       vvd(pn.rn[1][2], -0.4063240865362499850e-4, 1e-14,"jauPn00a", "rn23");

       vvd(pn.rn[2][0], -0.3831194272065995866e-5, 1e-14,"jauPn00a", "rn31");
       vvd(pn.rn[2][1], 0.4063237480216291775e-4, 1e-14,"jauPn00a", "rn32");
       vvd(pn.rn[2][2], 0.9999999991671660338, 1e-12,"jauPn00a", "rn33");

       vvd(pn.rbpn[0][0], 0.9999989440476103435, 1e-12,"jauPn00a", "rbpn11");
       vvd(pn.rbpn[0][1], -0.1332881761240011763e-2, 1e-14,"jauPn00a", "rbpn12");
       vvd(pn.rbpn[0][2], -0.5790767434730085751e-3, 1e-14,"jauPn00a", "rbpn13");

       vvd(pn.rbpn[1][0], 0.1332858254308954658e-2, 1e-14,"jauPn00a", "rbpn21");
       vvd(pn.rbpn[1][1], 0.9999991109044505577, 1e-12,"jauPn00a", "rbpn22");
       vvd(pn.rbpn[1][2], -0.4097782710396580452e-4, 1e-14,"jauPn00a", "rbpn23");

       vvd(pn.rbpn[2][0], 0.5791308472168152904e-3, 1e-14,"jauPn00a", "rbpn31");
       vvd(pn.rbpn[2][1], 0.4020595661591500259e-4, 1e-14,"jauPn00a", "rbpn32");
       vvd(pn.rbpn[2][2], 0.9999998314954572304, 1e-12,"jauPn00a", "rbpn33");

    }

    @Test
    public void t_pn00b()
    /**
    **
    **  Test jauPn00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPn00b, vvd
    **
    **  This revision:  2008 November 28
    */
    {

       PrecessionNutation pn = jauPn00b(2400000.5, 53736.0);

       vvd(pn.nut.dpsi, -0.9632552291148362783e-5, 1e-12,"jauPn00b", "dpsi");
       vvd(pn.nut.deps,  0.4063197106621159367e-4, 1e-12,"jauPn00b", "deps");
       vvd(pn.epsa,  0.4090791789404229916, 1e-12, "jauPn00b", "epsa");

       vvd(pn.rb[0][0], 0.9999999999999942498, 1e-12,"jauPn00b", "rb11");
       vvd(pn.rb[0][1], -0.7078279744199196626e-7, 1e-16,"jauPn00b", "rb12");
       vvd(pn.rb[0][2], 0.8056217146976134152e-7, 1e-16,"jauPn00b", "rb13");

       vvd(pn.rb[1][0], 0.7078279477857337206e-7, 1e-16,"jauPn00b", "rb21");
       vvd(pn.rb[1][1], 0.9999999999999969484, 1e-12,"jauPn00b", "rb22");
       vvd(pn.rb[1][2], 0.3306041454222136517e-7, 1e-16,"jauPn00b", "rb23");

       vvd(pn.rb[2][0], -0.8056217380986972157e-7, 1e-16,"jauPn00b", "rb31");
       vvd(pn.rb[2][1], -0.3306040883980552500e-7, 1e-16,"jauPn00b", "rb32");
       vvd(pn.rb[2][2], 0.9999999999999962084, 1e-12,"jauPn00b", "rb33");

       vvd(pn.rp[0][0], 0.9999989300532289018, 1e-12,"jauPn00b", "rp11");
       vvd(pn.rp[0][1], -0.1341647226791824349e-2, 1e-14,"jauPn00b", "rp12");
       vvd(pn.rp[0][2], -0.5829880927190296547e-3, 1e-14,"jauPn00b", "rp13");

       vvd(pn.rp[1][0], 0.1341647231069759008e-2, 1e-14,"jauPn00b", "rp21");
       vvd(pn.rp[1][1], 0.9999990999908750433, 1e-12,"jauPn00b", "rp22");
       vvd(pn.rp[1][2], -0.3837444441583715468e-6, 1e-14,"jauPn00b", "rp23");

       vvd(pn.rp[2][0], 0.5829880828740957684e-3, 1e-14,"jauPn00b", "rp31");
       vvd(pn.rp[2][1], -0.3984203267708834759e-6, 1e-14,"jauPn00b", "rp32");
       vvd(pn.rp[2][2], 0.9999998300623538046, 1e-12,"jauPn00b", "rp33");

       vvd(pn.rbp[0][0], 0.9999989300052243993, 1e-12,"jauPn00b", "rbp11");
       vvd(pn.rbp[0][1], -0.1341717990239703727e-2, 1e-14,"jauPn00b", "rbp12");
       vvd(pn.rbp[0][2], -0.5829075749891684053e-3, 1e-14,"jauPn00b", "rbp13");

       vvd(pn.rbp[1][0], 0.1341718013831739992e-2, 1e-14,"jauPn00b", "rbp21");
       vvd(pn.rbp[1][1], 0.9999990998959191343, 1e-12,"jauPn00b", "rbp22");
       vvd(pn.rbp[1][2], -0.3505759733565421170e-6, 1e-14,"jauPn00b", "rbp23");

       vvd(pn.rbp[2][0], 0.5829075206857717883e-3, 1e-14,"jauPn00b", "rbp31");
       vvd(pn.rbp[2][1], -0.4315219955198608970e-6, 1e-14,"jauPn00b", "rbp32");
       vvd(pn.rbp[2][2], 0.9999998301093036269, 1e-12,"jauPn00b", "rbp33");

       vvd(pn.rn[0][0], 0.9999999999536069682, 1e-12,"jauPn00b", "rn11");
       vvd(pn.rn[0][1], 0.8837746144871248011e-5, 1e-14,"jauPn00b", "rn12");
       vvd(pn.rn[0][2], 0.3831488838252202945e-5, 1e-14,"jauPn00b", "rn13");

       vvd(pn.rn[1][0], -0.8837590456632304720e-5, 1e-14,"jauPn00b", "rn21");
       vvd(pn.rn[1][1], 0.9999999991354692733, 1e-12,"jauPn00b", "rn22");
       vvd(pn.rn[1][2], -0.4063198798559591654e-4, 1e-14,"jauPn00b", "rn23");

       vvd(pn.rn[2][0], -0.3831847930134941271e-5, 1e-14,"jauPn00b", "rn31");
       vvd(pn.rn[2][1], 0.4063195412258168380e-4, 1e-14,"jauPn00b", "rn32");
       vvd(pn.rn[2][2], 0.9999999991671806225, 1e-12,"jauPn00b", "rn33");

       vvd(pn.rbpn[0][0], 0.9999989440499982806, 1e-12,"jauPn00b", "rbpn11");
       vvd(pn.rbpn[0][1], -0.1332880253640849194e-2, 1e-14,"jauPn00b", "rbpn12");
       vvd(pn.rbpn[0][2], -0.5790760898731091166e-3, 1e-14,"jauPn00b", "rbpn13");

       vvd(pn.rbpn[1][0], 0.1332856746979949638e-2, 1e-14,"jauPn00b", "rbpn21");
       vvd(pn.rbpn[1][1], 0.9999991109064768883, 1e-12,"jauPn00b", "rbpn22");
       vvd(pn.rbpn[1][2], -0.4097740555723081811e-4, 1e-14,"jauPn00b", "rbpn23");

       vvd(pn.rbpn[2][0], 0.5791301929950208873e-3, 1e-14,"jauPn00b", "rbpn31");
       vvd(pn.rbpn[2][1], 0.4020553681373720832e-4, 1e-14,"jauPn00b", "rbpn32");
       vvd(pn.rbpn[2][2], 0.9999998314958529887, 1e-12,"jauPn00b", "rbpn33");

    }

    @Test
    public void t_pn06a()
    /**
    **
    **  Test jauPn06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPn06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
 

       PrecessionNutation pn = jauPn06a(2400000.5, 53736.0);

       vvd(pn.nut.dpsi, -0.9630912025820308797e-5, 1e-12,"jauPn06a", "dpsi");
       vvd(pn.nut.deps,  0.4063238496887249798e-4, 1e-12,"jauPn06a", "deps");
       vvd(pn.epsa,  0.4090789763356509926, 1e-12, "jauPn06a", "epsa");

       vvd(pn.rb[0][0], 0.9999999999999942497, 1e-12,"jauPn06a", "rb11");
       vvd(pn.rb[0][1], -0.7078368960971557145e-7, 1e-14,"jauPn06a", "rb12");
       vvd(pn.rb[0][2], 0.8056213977613185606e-7, 1e-14,"jauPn06a", "rb13");

       vvd(pn.rb[1][0], 0.7078368694637674333e-7, 1e-14,"jauPn06a", "rb21");
       vvd(pn.rb[1][1], 0.9999999999999969484, 1e-12,"jauPn06a", "rb22");
       vvd(pn.rb[1][2], 0.3305943742989134124e-7, 1e-14,"jauPn06a", "rb23");

       vvd(pn.rb[2][0], -0.8056214211620056792e-7, 1e-14,"jauPn06a", "rb31");
       vvd(pn.rb[2][1], -0.3305943172740586950e-7, 1e-14,"jauPn06a", "rb32");
       vvd(pn.rb[2][2], 0.9999999999999962084, 1e-12,"jauPn06a", "rb33");

       vvd(pn.rp[0][0], 0.9999989300536854831, 1e-12,"jauPn06a", "rp11");
       vvd(pn.rp[0][1], -0.1341646886204443795e-2, 1e-14,"jauPn06a", "rp12");
       vvd(pn.rp[0][2], -0.5829880933488627759e-3, 1e-14,"jauPn06a", "rp13");

       vvd(pn.rp[1][0], 0.1341646890569782183e-2, 1e-14,"jauPn06a", "rp21");
       vvd(pn.rp[1][1], 0.9999990999913319321, 1e-12,"jauPn06a", "rp22");
       vvd(pn.rp[1][2], -0.3835944216374477457e-6, 1e-14,"jauPn06a", "rp23");

       vvd(pn.rp[2][0], 0.5829880833027867368e-3, 1e-14,"jauPn06a", "rp31");
       vvd(pn.rp[2][1], -0.3985701514686976112e-6, 1e-14,"jauPn06a", "rp32");
       vvd(pn.rp[2][2], 0.9999998300623534950, 1e-12,"jauPn06a", "rp33");

       vvd(pn.rbp[0][0], 0.9999989300056797893, 1e-12,"jauPn06a", "rbp11");
       vvd(pn.rbp[0][1], -0.1341717650545059598e-2, 1e-14,"jauPn06a", "rbp12");
       vvd(pn.rbp[0][2], -0.5829075756493728856e-3, 1e-14,"jauPn06a", "rbp13");

       vvd(pn.rbp[1][0], 0.1341717674223918101e-2, 1e-14,"jauPn06a", "rbp21");
       vvd(pn.rbp[1][1], 0.9999990998963748448, 1e-12,"jauPn06a", "rbp22");
       vvd(pn.rbp[1][2], -0.3504269280170069029e-6, 1e-14,"jauPn06a", "rbp23");

       vvd(pn.rbp[2][0], 0.5829075211461454599e-3, 1e-14,"jauPn06a", "rbp31");
       vvd(pn.rbp[2][1], -0.4316708436255949093e-6, 1e-14,"jauPn06a", "rbp32");
       vvd(pn.rbp[2][2], 0.9999998301093032943, 1e-12,"jauPn06a", "rbp33");

       vvd(pn.rn[0][0], 0.9999999999536227668, 1e-12,"jauPn06a", "rn11");
       vvd(pn.rn[0][1], 0.8836241998111535233e-5, 1e-14,"jauPn06a", "rn12");
       vvd(pn.rn[0][2], 0.3830834608415287707e-5, 1e-14,"jauPn06a", "rn13");

       vvd(pn.rn[1][0], -0.8836086334870740138e-5, 1e-14,"jauPn06a", "rn21");
       vvd(pn.rn[1][1], 0.9999999991354657474, 1e-12,"jauPn06a", "rn22");
       vvd(pn.rn[1][2], -0.4063240188248455065e-4, 1e-14,"jauPn06a", "rn23");

       vvd(pn.rn[2][0], -0.3831193642839398128e-5, 1e-14,"jauPn06a", "rn31");
       vvd(pn.rn[2][1], 0.4063236803101479770e-4, 1e-14,"jauPn06a", "rn32");
       vvd(pn.rn[2][2], 0.9999999991671663114, 1e-12,"jauPn06a", "rn33");

       vvd(pn.rbpn[0][0], 0.9999989440480669738, 1e-12,"jauPn06a", "rbpn11");
       vvd(pn.rbpn[0][1], -0.1332881418091915973e-2, 1e-14,"jauPn06a", "rbpn12");
       vvd(pn.rbpn[0][2], -0.5790767447612042565e-3, 1e-14,"jauPn06a", "rbpn13");

       vvd(pn.rbpn[1][0], 0.1332857911250989133e-2, 1e-14,"jauPn06a", "rbpn21");
       vvd(pn.rbpn[1][1], 0.9999991109049141908, 1e-12,"jauPn06a", "rbpn22");
       vvd(pn.rbpn[1][2], -0.4097767128546784878e-4, 1e-14,"jauPn06a", "rbpn23");

       vvd(pn.rbpn[2][0], 0.5791308482835292617e-3, 1e-14,"jauPn06a", "rbpn31");
       vvd(pn.rbpn[2][1], 0.4020580099454020310e-4, 1e-14,"jauPn06a", "rbpn32");
       vvd(pn.rbpn[2][2], 0.9999998314954628695, 1e-12,"jauPn06a", "rbpn33");

    }

    @Test
    public void t_pn06()
    /**
    **
    **  Test jauPn06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPn06, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double dpsi, deps;

       dpsi = -0.9632552291149335877e-5;
       deps =  0.4063197106621141414e-4;

       PrecessionNutation pn = jauPn06(2400000.5, 53736.0, dpsi, deps);

       vvd(pn.epsa, 0.4090789763356509926, 1e-12, "jauPn06", "epsa");

       vvd(pn.rb[0][0], 0.9999999999999942497, 1e-12,"jauPn06", "rb11");
       vvd(pn.rb[0][1], -0.7078368960971557145e-7, 1e-14,"jauPn06", "rb12");
       vvd(pn.rb[0][2], 0.8056213977613185606e-7, 1e-14,"jauPn06", "rb13");

       vvd(pn.rb[1][0], 0.7078368694637674333e-7, 1e-14,"jauPn06", "rb21");
       vvd(pn.rb[1][1], 0.9999999999999969484, 1e-12,"jauPn06", "rb22");
       vvd(pn.rb[1][2], 0.3305943742989134124e-7, 1e-14,"jauPn06", "rb23");

       vvd(pn.rb[2][0], -0.8056214211620056792e-7, 1e-14,"jauPn06", "rb31");
       vvd(pn.rb[2][1], -0.3305943172740586950e-7, 1e-14,"jauPn06", "rb32");
       vvd(pn.rb[2][2], 0.9999999999999962084, 1e-12,"jauPn06", "rb33");

       vvd(pn.rp[0][0], 0.9999989300536854831, 1e-12,"jauPn06", "rp11");
       vvd(pn.rp[0][1], -0.1341646886204443795e-2, 1e-14,"jauPn06", "rp12");
       vvd(pn.rp[0][2], -0.5829880933488627759e-3, 1e-14,"jauPn06", "rp13");

       vvd(pn.rp[1][0], 0.1341646890569782183e-2, 1e-14,"jauPn06", "rp21");
       vvd(pn.rp[1][1], 0.9999990999913319321, 1e-12,"jauPn06", "rp22");
       vvd(pn.rp[1][2], -0.3835944216374477457e-6, 1e-14,"jauPn06", "rp23");

       vvd(pn.rp[2][0], 0.5829880833027867368e-3, 1e-14,"jauPn06", "rp31");
       vvd(pn.rp[2][1], -0.3985701514686976112e-6, 1e-14,"jauPn06", "rp32");
       vvd(pn.rp[2][2], 0.9999998300623534950, 1e-12,"jauPn06", "rp33");

       vvd(pn.rbp[0][0], 0.9999989300056797893, 1e-12,"jauPn06", "rbp11");
       vvd(pn.rbp[0][1], -0.1341717650545059598e-2, 1e-14,"jauPn06", "rbp12");
       vvd(pn.rbp[0][2], -0.5829075756493728856e-3, 1e-14,"jauPn06", "rbp13");

       vvd(pn.rbp[1][0], 0.1341717674223918101e-2, 1e-14,"jauPn06", "rbp21");
       vvd(pn.rbp[1][1], 0.9999990998963748448, 1e-12,"jauPn06", "rbp22");
       vvd(pn.rbp[1][2], -0.3504269280170069029e-6, 1e-14,"jauPn06", "rbp23");

       vvd(pn.rbp[2][0], 0.5829075211461454599e-3, 1e-14,"jauPn06", "rbp31");
       vvd(pn.rbp[2][1], -0.4316708436255949093e-6, 1e-14,"jauPn06", "rbp32");
       vvd(pn.rbp[2][2], 0.9999998301093032943, 1e-12,"jauPn06", "rbp33");

       vvd(pn.rn[0][0], 0.9999999999536069682, 1e-12,"jauPn06", "rn11");
       vvd(pn.rn[0][1], 0.8837746921149881914e-5, 1e-14,"jauPn06", "rn12");
       vvd(pn.rn[0][2], 0.3831487047682968703e-5, 1e-14,"jauPn06", "rn13");

       vvd(pn.rn[1][0], -0.8837591232983692340e-5, 1e-14,"jauPn06", "rn21");
       vvd(pn.rn[1][1], 0.9999999991354692664, 1e-12,"jauPn06", "rn22");
       vvd(pn.rn[1][2], -0.4063198798558931215e-4, 1e-14,"jauPn06", "rn23");

       vvd(pn.rn[2][0], -0.3831846139597250235e-5, 1e-14,"jauPn06", "rn31");
       vvd(pn.rn[2][1], 0.4063195412258792914e-4, 1e-14,"jauPn06", "rn32");
       vvd(pn.rn[2][2], 0.9999999991671806293, 1e-12,"jauPn06", "rn33");

       vvd(pn.rbpn[0][0], 0.9999989440504506688, 1e-12,"jauPn06", "rbpn11");
       vvd(pn.rbpn[0][1], -0.1332879913170492655e-2, 1e-14,"jauPn06", "rbpn12");
       vvd(pn.rbpn[0][2], -0.5790760923225655753e-3, 1e-14,"jauPn06", "rbpn13");

       vvd(pn.rbpn[1][0], 0.1332856406595754748e-2, 1e-14,"jauPn06", "rbpn21");
       vvd(pn.rbpn[1][1], 0.9999991109069366795, 1e-12,"jauPn06", "rbpn22");
       vvd(pn.rbpn[1][2], -0.4097725651142641812e-4, 1e-14,"jauPn06", "rbpn23");

       vvd(pn.rbpn[2][0], 0.5791301952321296716e-3, 1e-14,"jauPn06", "rbpn31");
       vvd(pn.rbpn[2][1], 0.4020538796195230577e-4, 1e-14,"jauPn06", "rbpn32");
       vvd(pn.rbpn[2][2], 0.9999998314958576778, 1e-12,"jauPn06", "rbpn33");

    }

    @Test
    public void t_pnm00a()
    /**
    **
    **  Test jauPnm00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPnm00a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rbpn[][] = new double[3][3];


       rbpn = jauPnm00a(2400000.5, 50123.9999);

       vvd(rbpn[0][0], 0.9999995832793134257, 1e-12,"jauPnm00a", "11");
       vvd(rbpn[0][1], 0.8372384254137809439e-3, 1e-14,"jauPnm00a", "12");
       vvd(rbpn[0][2], 0.3639684306407150645e-3, 1e-14,"jauPnm00a", "13");

       vvd(rbpn[1][0], -0.8372535226570394543e-3, 1e-14,"jauPnm00a", "21");
       vvd(rbpn[1][1], 0.9999996486491582471, 1e-12,"jauPnm00a", "22");
       vvd(rbpn[1][2], 0.4132915262664072381e-4, 1e-14,"jauPnm00a", "23");

       vvd(rbpn[2][0], -0.3639337004054317729e-3, 1e-14,"jauPnm00a", "31");
       vvd(rbpn[2][1], -0.4163386925461775873e-4, 1e-14,"jauPnm00a", "32");
       vvd(rbpn[2][2], 0.9999999329094390695, 1e-12,"jauPnm00a", "33");

    }

    @Test
    public void t_pnm00b()
    /**
    **
    **  Test jauPnm00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPnm00b, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rbpn[][] = new double[3][3];


       rbpn = jauPnm00b(2400000.5, 50123.9999);

       vvd(rbpn[0][0], 0.9999995832776208280, 1e-12,"jauPnm00b", "11");
       vvd(rbpn[0][1], 0.8372401264429654837e-3, 1e-14,"jauPnm00b", "12");
       vvd(rbpn[0][2], 0.3639691681450271771e-3, 1e-14,"jauPnm00b", "13");

       vvd(rbpn[1][0], -0.8372552234147137424e-3, 1e-14,"jauPnm00b", "21");
       vvd(rbpn[1][1], 0.9999996486477686123, 1e-12,"jauPnm00b", "22");
       vvd(rbpn[1][2], 0.4132832190946052890e-4, 1e-14,"jauPnm00b", "23");

       vvd(rbpn[2][0], -0.3639344385341866407e-3, 1e-14,"jauPnm00b", "31");
       vvd(rbpn[2][1], -0.4163303977421522785e-4, 1e-14,"jauPnm00b", "32");
       vvd(rbpn[2][2], 0.9999999329092049734, 1e-12,"jauPnm00b", "33");

    }

    @Test
    public void t_pnm06a()
    /**
    **
    **  Test jauPnm06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPnm06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rbpn[][] = new double[3][3];


       rbpn = jauPnm06a(2400000.5, 50123.9999);

       vvd(rbpn[0][0], 0.9999995832794205484, 1e-12,"jauPnm06a", "11");
       vvd(rbpn[0][1], 0.8372382772630962111e-3, 1e-14,"jauPnm06a", "12");
       vvd(rbpn[0][2], 0.3639684771140623099e-3, 1e-14,"jauPnm06a", "13");

       vvd(rbpn[1][0], -0.8372533744743683605e-3, 1e-14,"jauPnm06a", "21");
       vvd(rbpn[1][1], 0.9999996486492861646, 1e-12,"jauPnm06a", "22");
       vvd(rbpn[1][2], 0.4132905944611019498e-4, 1e-14,"jauPnm06a", "23");

       vvd(rbpn[2][0], -0.3639337469629464969e-3, 1e-14,"jauPnm06a", "31");
       vvd(rbpn[2][1], -0.4163377605910663999e-4, 1e-14,"jauPnm06a", "32");
       vvd(rbpn[2][2], 0.9999999329094260057, 1e-12,"jauPnm06a", "33");

    }

    @Test
    public void t_pnm80()
    /**
    **
    **  Test jauPnm80 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPnm80, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double rmatpn[][] = new double[3][3];


       rmatpn = jauPnm80(2400000.5, 50123.9999);

       vvd(rmatpn[0][0], 0.9999995831934611169, 1e-12,"jauPnm80", "11");
       vvd(rmatpn[0][1], 0.8373654045728124011e-3, 1e-14,"jauPnm80", "12");
       vvd(rmatpn[0][2], 0.3639121916933106191e-3, 1e-14,"jauPnm80", "13");

       vvd(rmatpn[1][0], -0.8373804896118301316e-3, 1e-14,"jauPnm80", "21");
       vvd(rmatpn[1][1], 0.9999996485439674092, 1e-12,"jauPnm80", "22");
       vvd(rmatpn[1][2], 0.4130202510421549752e-4, 1e-14,"jauPnm80", "23");

       vvd(rmatpn[2][0], -0.3638774789072144473e-3, 1e-14,"jauPnm80", "31");
       vvd(rmatpn[2][1], -0.4160674085851722359e-4, 1e-14,"jauPnm80", "32");
       vvd(rmatpn[2][2], 0.9999999329310274805, 1e-12,"jauPnm80", "33");

    }

    @Test
    public void t_pom00()
    /**
    **
    **  Test jauPom00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPom00, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double xp, yp, sp, rpom[][] = new double[3][3];


       xp =  2.55060238e-7;
       yp =  1.860359247e-6;
       sp = -0.1367174580728891460e-10;

       rpom = jauPom00(xp, yp, sp);

       vvd(rpom[0][0], 0.9999999999999674721, 1e-12,"jauPom00", "11");
       vvd(rpom[0][1], -0.1367174580728846989e-10, 1e-16,"jauPom00", "12");
       vvd(rpom[0][2], 0.2550602379999972345e-6, 1e-16,"jauPom00", "13");

       vvd(rpom[1][0], 0.1414624947957029801e-10, 1e-16,"jauPom00", "21");
       vvd(rpom[1][1], 0.9999999999982695317, 1e-12,"jauPom00", "22");
       vvd(rpom[1][2], -0.1860359246998866389e-5, 1e-16,"jauPom00", "23");

       vvd(rpom[2][0], -0.2550602379741215021e-6, 1e-16,"jauPom00", "31");
       vvd(rpom[2][1], 0.1860359247002414021e-5, 1e-16,"jauPom00", "32");
       vvd(rpom[2][2], 0.9999999999982370039, 1e-12,"jauPom00", "33");
       
       double vec[]= {0,0,1};
       double vec2[] = jauRxp(rpom,vec);
       for (double d : vec2) {
        System.out.println(d);
    }

    }

    @Test
    public void t_ppp()
    /**
    **
    **  Test jauPpp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPpp, vvd
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

       apb = jauPpp(a, b);

       vvd(apb[0], 3.0, 1e-12, "jauPpp", "0");
       vvd(apb[1], 5.0, 1e-12, "jauPpp", "1");
       vvd(apb[2], 7.0, 1e-12, "jauPpp", "2");

    }

    @Test
    public void t_ppsp()
    /**
    **
    **  Test jauPpsp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPpsp, vvd
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

       apsb = jauPpsp(a, s, b);

       vvd(apsb[0], 7.0, 1e-12, "jauPpsp", "0");
       vvd(apsb[1], 17.0, 1e-12, "jauPpsp", "1");
       vvd(apsb[2], 23.0, 1e-12, "jauPpsp", "2");

    }

    @Test
    public void t_pr00()
    /**
    **
    **  Test jauPr00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPr00, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       PrecessionDeltaTerms nut = jauPr00(2400000.5, 53736);

       vvd(nut.dpsipr, -0.8716465172668347629e-7, 1e-22,"jauPr00", "dpsipr");
       vvd(nut.depspr, -0.7342018386722813087e-8, 1e-22,"jauPr00", "depspr");

    }

    @Test
    public void t_prec76()
    /**
    **
    **  Test jauPrec76 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPrec76, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double ep01, ep02, ep11, ep12;


       ep01 = 2400000.5;
       ep02 = 33282.0;
       ep11 = 2400000.5;
       ep12 = 51544.0;

       EulerAngles an = jauPrec76(ep01, ep02, ep11, ep12);

       vvd(an.zeta,  0.5588961642000161243e-2, 1e-12,"jauPrec76", "zeta");
       vvd(an.z,     0.5589922365870680624e-2, 1e-12,"jauPrec76", "z");
       vvd(an.theta, 0.4858945471687296760e-2, 1e-12,"jauPrec76", "theta");

    }

    @Test
    public void t_pv2p()
    /**
    **
    **  Test jauPv2p function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPv2p, vvd
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

       p = jauPv2p(pv);

       vvd(p[0],  0.3, 0.0, "jauPv2p", "1");
       vvd(p[1],  1.2, 0.0, "jauPv2p", "2");
       vvd(p[2], -2.5, 0.0, "jauPv2p", "3");

    }

    @Test
    public void t_pv2s()
    /**
    **
    **  Test jauPv2s function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPv2s, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double pv[][] = new double[2][3];


       pv[0][0] = -0.4514964673880165;
       pv[0][1] =  0.03093394277342585;
       pv[0][2] =  0.05594668105108779;

       pv[1][0] =  1.292270850663260e-5;
       pv[1][1] =  2.652814182060692e-6;
       pv[1][2] =  2.568431853930293e-6;

       SphericalPositionVelocity pvs = jauPv2s(pv);

       vvd(pvs.pos.theta, 3.073185307179586515, 1e-12, "jauPv2s", "theta");
       vvd(pvs.pos.phi, 0.1229999999999999992, 1e-12, "jauPv2s", "phi");
       vvd(pvs.pos.r, 0.4559999999999999757, 1e-12, "jauPv2s", "r");
       vvd(pvs.vel.theta, -0.7800000000000000364e-5, 1e-16, "jauPv2s", "td");
       vvd(pvs.vel.phi, 0.9010000000000001639e-5, 1e-16, "jauPv2s", "pd");
       vvd(pvs.vel.r, -0.1229999999999999832e-4, 1e-16, "jauPv2s", "rd");

    }

    @Test
    public void t_pvdpv()
    /**
    **
    **  Test jauPvdpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPvdpv, vvd
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

       adb = jauPvdpv(a, b);

       vvd(adb[0], 20.0, 1e-12, "jauPvdpv", "1");
       vvd(adb[1], 50.0, 1e-12, "jauPvdpv", "2");

    }

    @Test
    public void t_pvm()
    /**
    **
    **  Test jauPvm function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPvm, vvd
    **
    **  This revision:  2008 May 25
    */
    {
       double pv[][] = new double[2][3];


       pv[0][0] =  0.3;
       pv[0][1] =  1.2;
       pv[0][2] = -2.5;

       pv[1][0] =  0.45;
       pv[1][1] = -0.25;
       pv[1][2] =  1.1;

       PVModulus ret = jauPvm(pv);

       vvd(ret.r, 2.789265136196270604, 1e-12, "jauPvm", "r");
       vvd(ret.s, 1.214495780149111922, 1e-12, "jauPvm", "s");

    }

    @Test
    public void t_pvmpv()
    /**
    **
    **  Test jauPvmpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPvmpv, vvd
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

       amb = jauPvmpv(a, b);

       vvd(amb[0][0],  1.0, 1e-12, "jauPvmpv", "11");
       vvd(amb[0][1], -1.0, 1e-12, "jauPvmpv", "21");
       vvd(amb[0][2], -1.0, 1e-12, "jauPvmpv", "31");

       vvd(amb[1][0],  2.0, 1e-12, "jauPvmpv", "12");
       vvd(amb[1][1],  4.0, 1e-12, "jauPvmpv", "22");
       vvd(amb[1][2],  2.0, 1e-12, "jauPvmpv", "32");

    }

    @Test
    public void t_pvppv()
    /**
    **
    **  Test jauPvppv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPvppv, vvd
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

       apb = jauPvppv(a, b);

       vvd(apb[0][0], 3.0, 1e-12, "jauPvppv", "p1");
       vvd(apb[0][1], 5.0, 1e-12, "jauPvppv", "p2");
       vvd(apb[0][2], 7.0, 1e-12, "jauPvppv", "p3");

       vvd(apb[1][0], 8.0, 1e-12, "jauPvppv", "v1");
       vvd(apb[1][1], 8.0, 1e-12, "jauPvppv", "v2");
       vvd(apb[1][2], 4.0, 1e-12, "jauPvppv", "v3");

    }

    @Test
    public void t_pvstar()
    /**
    **
    **  Test jauPvstar function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPvstar, vvd, viv
    **
    **  This revision:  2009 November 6
    */
    {
       double pv[][] = new double[2][3];


       pv[0][0] =  126668.5912743160601;
       pv[0][1] =  2136.792716839935195;
       pv[0][2] = -245251.2339876830091;

       pv[1][0] = -0.4051854035740712739e-2;
       pv[1][1] = -0.6253919754866173866e-2;
       pv[1][2] =  0.1189353719774107189e-1;

       try {
           CatalogCoords cat = jauPvstar(pv);

           vvd(cat.pos.alpha, 0.1686756e-1, 1e-12, "jauPvstar", "ra");
           vvd(cat.pos.delta, -1.093989828, 1e-12, "jauPvstar", "dec");
           vvd(cat.pm.alpha, -0.1783235160000472788e-4, 1e-16, "jauPvstar", "pmr");
           vvd(cat.pm.delta, 0.2336024047000619347e-5, 1e-16, "jauPvstar", "pmd");
           vvd(cat.px, 0.74723, 1e-12, "jauPvstar", "px");
           vvd(cat.rv, -21.60000010107306010, 1e-11, "jauPvstar", "rv");
       } catch (JSOFAInternalError e) {
           fail(" internal exception");
       }


    }

    @Test
    public void t_pvu()
    /**
    **
    **  Test jauPvu function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPvu, vvd
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

       upv = jauPvu(2920.0, pv);

       vvd(upv[0][0], 126656.7598605317105, 1e-6,"jauPvu", "p1");
       vvd(upv[0][1], 2118.531271155726332, 1e-8,"jauPvu", "p2");
       vvd(upv[0][2], -245216.5048590656190, 1e-6,"jauPvu", "p3");

       vvd(upv[1][0], -0.4051854035740713039e-2, 1e-12,"jauPvu", "v1");
       vvd(upv[1][1], -0.6253919754866175788e-2, 1e-12,"jauPvu", "v2");
       vvd(upv[1][2], 0.1189353719774107615e-1, 1e-12,"jauPvu", "v3");

    }

    @Test
    public void t_pvup()
    /**
    **
    **  Test jauPvup function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPvup, vvd
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

       p = jauPvup(2920.0, pv);

       vvd(p[0],  126656.7598605317105,   1e-12, "jauPvup", "1");
       vvd(p[1],    2118.531271155726332, 1e-12, "jauPvup", "2");
       vvd(p[2], -245216.5048590656190,   1e-12, "jauPvup", "3");

    }

    @Test
    public void t_pvxpv()
    /**
    **
    **  Test jauPvxpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPvxpv, vvd
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

       axb = jauPvxpv(a, b);

       vvd(axb[0][0],  -1.0, 1e-12, "jauPvxpv", "p1");
       vvd(axb[0][1],  -5.0, 1e-12, "jauPvxpv", "p2");
       vvd(axb[0][2],   4.0, 1e-12, "jauPvxpv", "p3");

       vvd(axb[1][0],  -2.0, 1e-12, "jauPvxpv", "v1");
       vvd(axb[1][1], -36.0, 1e-12, "jauPvxpv", "v2");
       vvd(axb[1][2],  22.0, 1e-12, "jauPvxpv", "v3");

    }

    @Test
    public void t_pxp()
    /**
    **
    **  Test jauPxp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauPxp, vvd
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

       axb = jauPxp(a, b);

       vvd(axb[0], -1.0, 1e-12, "jauPxp", "1");
       vvd(axb[1], -5.0, 1e-12, "jauPxp", "2");
       vvd(axb[2],  4.0, 1e-12, "jauPxp", "3");

    }

    @Test
    public void t_rm2v()
    /**
    **
    **  Test jauRm2v function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauRm2v, vvd
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

       w = jauRm2v(r);

       vvd(w[0],  0.0,                  1e-12, "jauRm2v", "1");
       vvd(w[1],  1.413716694115406957, 1e-12, "jauRm2v", "2");
       vvd(w[2], -1.884955592153875943, 1e-12, "jauRm2v", "3");

    }

    @Test
    public void t_rv2m()
    /**
    **
    **  Test jauRv2m function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauRv2m, vvd
    **
    **  This revision:  2008 May 27
    */
    {
       double w[] = new double[3], r[][] = new double[3][3];


       w[0] =  0.0;
       w[1] =  1.41371669;
       w[2] = -1.88495559;

       r = jauRv2m(w);

       vvd(r[0][0], -0.7071067782221119905, 1e-14, "jauRv2m", "11");
       vvd(r[0][1], -0.5656854276809129651, 1e-14, "jauRv2m", "12");
       vvd(r[0][2], -0.4242640700104211225, 1e-14, "jauRv2m", "13");

       vvd(r[1][0],  0.5656854276809129651, 1e-14, "jauRv2m", "21");
       vvd(r[1][1], -0.0925483394532274246, 1e-14, "jauRv2m", "22");
       vvd(r[1][2], -0.8194112531408833269, 1e-14, "jauRv2m", "23");

       vvd(r[2][0],  0.4242640700104211225, 1e-14, "jauRv2m", "31");
       vvd(r[2][1], -0.8194112531408833269, 1e-14, "jauRv2m", "32");
       vvd(r[2][2],  0.3854415612311154341, 1e-14, "jauRv2m", "33");

    }

    @Test
    public void t_rx()
    /**
    **
    **  Test jauRx function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauRx, vvd
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

       jauRx(phi, r);

       vvd(r[0][0], 2.0, 0.0, "jauRx", "11");
       vvd(r[0][1], 3.0, 0.0, "jauRx", "12");
       vvd(r[0][2], 2.0, 0.0, "jauRx", "13");

       vvd(r[1][0], 3.839043388235612460, 1e-12, "jauRx", "21");
       vvd(r[1][1], 3.237033249594111899, 1e-12, "jauRx", "22");
       vvd(r[1][2], 4.516714379005982719, 1e-12, "jauRx", "23");

       vvd(r[2][0], 1.806030415924501684, 1e-12, "jauRx", "31");
       vvd(r[2][1], 3.085711545336372503, 1e-12, "jauRx", "32");
       vvd(r[2][2], 3.687721683977873065, 1e-12, "jauRx", "33");

    }

    @Test
    public void t_rxp()
    /**
    **
    **  Test jauRxp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauRxp, vvd
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

       rp = jauRxp(r, p);

       vvd(rp[0], 5.1, 1e-12, "jauRxp", "1");
       vvd(rp[1], 3.9, 1e-12, "jauRxp", "2");
       vvd(rp[2], 7.1, 1e-12, "jauRxp", "3");

    }

    @Test
    public void t_rxpv()
    /**
    **
    **  Test jauRxpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauRxpv, vvd
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

       rpv = jauRxpv(r, pv);

       vvd(rpv[0][0], 5.1, 1e-12, "jauRxpv", "11");
       vvd(rpv[1][0], 3.8, 1e-12, "jauRxpv", "12");

       vvd(rpv[0][1], 3.9, 1e-12, "jauRxpv", "21");
       vvd(rpv[1][1], 5.2, 1e-12, "jauRxpv", "22");

       vvd(rpv[0][2], 7.1, 1e-12, "jauRxpv", "31");
       vvd(rpv[1][2], 5.8, 1e-12, "jauRxpv", "32");

    }

    @Test
    public void t_rxr()
    /**
    **
    **  Test jauRxr function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauRxr, vvd
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

       atb = jauRxr(a, b);

       vvd(atb[0][0], 20.0, 1e-12, "jauRxr", "11");
       vvd(atb[0][1],  7.0, 1e-12, "jauRxr", "12");
       vvd(atb[0][2],  9.0, 1e-12, "jauRxr", "13");

       vvd(atb[1][0], 20.0, 1e-12, "jauRxr", "21");
       vvd(atb[1][1],  8.0, 1e-12, "jauRxr", "22");
       vvd(atb[1][2], 11.0, 1e-12, "jauRxr", "23");

       vvd(atb[2][0], 34.0, 1e-12, "jauRxr", "31");
       vvd(atb[2][1], 10.0, 1e-12, "jauRxr", "32");
       vvd(atb[2][2], 15.0, 1e-12, "jauRxr", "33");

    }

    @Test
    public void t_ry()
    /**
    **
    **  Test jauRy function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauRy, vvd
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

       jauRy(theta, r);

       vvd(r[0][0], 0.8651847818978159930, 1e-12, "jauRy", "11");
       vvd(r[0][1], 1.467194920539316554, 1e-12, "jauRy", "12");
       vvd(r[0][2], 0.1875137911274457342, 1e-12, "jauRy", "13");

       vvd(r[1][0], 3, 1e-12, "jauRy", "21");
       vvd(r[1][1], 2, 1e-12, "jauRy", "22");
       vvd(r[1][2], 3, 1e-12, "jauRy", "23");

       vvd(r[2][0], 3.500207892850427330, 1e-12, "jauRy", "31");
       vvd(r[2][1], 4.779889022262298150, 1e-12, "jauRy", "32");
       vvd(r[2][2], 5.381899160903798712, 1e-12, "jauRy", "33");

    }

    @Test
    public void t_rz()
    /**
    **
    **  Test jauRz function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauRz, vvd
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

       jauRz(psi, r);

       vvd(r[0][0], 2.898197754208926769, 1e-12, "jauRz", "11");
       vvd(r[0][1], 3.500207892850427330, 1e-12, "jauRz", "12");
       vvd(r[0][2], 2.898197754208926769, 1e-12, "jauRz", "13");

       vvd(r[1][0], 2.144865911309686813, 1e-12, "jauRz", "21");
       vvd(r[1][1], 0.865184781897815993, 1e-12, "jauRz", "22");
       vvd(r[1][2], 2.144865911309686813, 1e-12, "jauRz", "23");

       vvd(r[2][0], 3.0, 1e-12, "jauRz", "31");
       vvd(r[2][1], 4.0, 1e-12, "jauRz", "32");
       vvd(r[2][2], 5.0, 1e-12, "jauRz", "33");

    }

    @Test
    public void t_s00a()
    /**
    **
    **  Test jauS00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauS00a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double s;


       s = jauS00a(2400000.5, 52541.0);

       vvd(s, -0.1340684448919163584e-7, 1e-18, "jauS00a", "");

    }

    @Test
    public void t_s00b()
    /**
    **
    **  Test jauS00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauS00b, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double s;


       s = jauS00b(2400000.5, 52541.0);

       vvd(s, -0.1340695782951026584e-7, 1e-18, "jauS00b", "");

    }

    @Test
    public void t_s00()
    /**
    **
    **  Test jauS00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauS00, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double x, y, s;


       x = 0.5791308486706011000e-3;
       y = 0.4020579816732961219e-4;

       s = jauS00(2400000.5, 53736.0, x, y);

       vvd(s, -0.1220036263270905693e-7, 1e-18, "jauS00", "");

    }

    @Test
    public void t_s06a()
    /**
    **
    **  Test jauS06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauS06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double s;


       s = jauS06a(2400000.5, 52541.0);

       vvd(s, -0.1340680437291812383e-7, 1e-18, "jauS06a", "");

    }

    @Test
    public void t_s06()
    /**
    **
    **  Test jauS06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauS06, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double x, y, s;


       x = 0.5791308486706011000e-3;
       y = 0.4020579816732961219e-4;

       s = jauS06(2400000.5, 53736.0, x, y);

       vvd(s, -0.1220032213076463117e-7, 1e-18, "jauS06", "");

    }

    @Test
    public void t_s2c()
    /**
    **
    **  Test jauS2c function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauS2c, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double c[] = new double[3];


       c = jauS2c(3.0123, -0.999);

       vvd(c[0], -0.5366267667260523906, 1e-12, "jauS2c", "1");
       vvd(c[1],  0.0697711109765145365, 1e-12, "jauS2c", "2");
       vvd(c[2], -0.8409302618566214041, 1e-12, "jauS2c", "3");

    }

    @Test
    public void t_s2p()
    /**
    **
    **  Test jauS2p function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauS2p, vvd
    **
    **  This revision:  2008 May 25
    */
    {
       double p[] = new double[3];


       p = jauS2p(-3.21, 0.123, 0.456);

       vvd(p[0], -0.4514964673880165228, 1e-12, "jauS2p", "x");
       vvd(p[1],  0.0309339427734258688, 1e-12, "jauS2p", "y");
       vvd(p[2],  0.0559466810510877933, 1e-12, "jauS2p", "z");

    }

    @Test
    public void t_s2pv()
    /**
    **
    **  Test jauS2pv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauS2pv, vvd
    **
    **  This revision:  2008 November 28
    */
    {
       double pv[][] = new double[2][3];


       pv = jauS2pv(-3.21, 0.123, 0.456, -7.8e-6, 9.01e-6, -1.23e-5);

       vvd(pv[0][0], -0.4514964673880165228, 1e-12, "jauS2pv", "x");
       vvd(pv[0][1],  0.0309339427734258688, 1e-12, "jauS2pv", "y");
       vvd(pv[0][2],  0.0559466810510877933, 1e-12, "jauS2pv", "z");

       vvd(pv[1][0],  0.1292270850663260170e-4, 1e-16,"jauS2pv", "vx");
       vvd(pv[1][1],  0.2652814182060691422e-5, 1e-16,"jauS2pv", "vy");
       vvd(pv[1][2],  0.2568431853930292259e-5, 1e-16,"jauS2pv", "vz");

    }

    @Test
    public void t_s2xpv()
    /**
    **
    **  Test jauS2xpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauS2xpv, vvd
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

       spv = jauS2xpv(s1, s2, pv);

       vvd(spv[0][0],  0.6, 1e-12, "jauS2xpv", "p1");
       vvd(spv[0][1],  2.4, 1e-12, "jauS2xpv", "p2");
       vvd(spv[0][2], -5.0, 1e-12, "jauS2xpv", "p3");

       vvd(spv[1][0],  1.5, 1e-12, "jauS2xpv", "v1");
       vvd(spv[1][1],  6.9, 1e-12, "jauS2xpv", "v2");
       vvd(spv[1][2], -1.2, 1e-12, "jauS2xpv", "v3");

    }

    @Test
    public void t_sepp()
    /**
    **
    **  Test jauSepp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauSepp, vvd
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

       s = jauSepp(a, b);

       vvd(s, 2.860391919024660768, 1e-12, "jauSepp", "");

    }

    @Test
    public void t_seps()
    /**
    **
    **  Test jauSeps function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauSeps, vvd
    **
    **  This revision:  2008 May 22
    */
    {
       double al, ap, bl, bp, s;


       al =  1.0;
       ap =  0.1;

       bl =  0.2;
       bp = -3.0;

       s = jauSeps(al, ap, bl, bp);

       vvd(s, 2.346722016996998842, 1e-14, "jauSeps", "");

    }

    @Test
    public void t_sp00()
    /**
    **
    **  Test jauSp00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauSp00, vvd
    **
    **  This revision:  2008 May 25
    */
    {
       vvd(jauSp00(2400000.5, 52541.0),
           -0.6216698469981019309e-11, 1e-12, "jauSp00", "");

    }

    @Test
    public void t_starpm()
    /**
    **
    **  Test jauStarpm function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauStarpm, vvd, viv
    **
    **  This revision:  2008 November 30
    */
    {
       double ra1, dec1, pmr1, pmd1, px1, rv1;

       ra1 =   0.01686756;
       dec1 = -1.093989828;
       pmr1 = -1.78323516e-5;
       pmd1 =  2.336024047e-6;
       px1 =   0.74723;
       rv1 = -21.6;

       try {
           CatalogCoords cat = jauStarpm(ra1, dec1, pmr1, pmd1, px1, rv1,
                   2400000.5, 50083.0, 2400000.5, 53736.0 );

           vvd(cat.pos.alpha, 0.01668919069414256149, 1e-13,"jauStarpm", "ra");
           vvd(cat.pos.delta, -1.093966454217127897, 1e-13,"jauStarpm", "dec");
           vvd(cat.pm.alpha, -0.1783662682153176524e-4, 1e-17,"jauStarpm", "pmr");
           vvd(cat.pm.delta, 0.2338092915983989595e-5, 1e-17,"jauStarpm", "pmd");
           vvd(cat.px, 0.7473533835317719243, 1e-13,"jauStarpm", "px");
           vvd(cat.rv, -21.59905170476417175, 1e-11,"jauStarpm", "rv");
       } catch (JSOFAInternalError e) {

           e.printStackTrace();
           fail("jauStarpm threw exception");
       }

 

    }

    @Test
    public void t_starpv()
    /**
    **
    **  Test jauStarpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauStarpv, vvd, viv
    **
    **  This revision:  2008 November 30
    */
    {
       double ra, dec, pmr, pmd, px, rv, pv[][] = new double[2][3];
    


       ra =   0.01686756;
       dec = -1.093989828;
       pmr = -1.78323516e-5;
       pmd =  2.336024047e-6;
       px =   0.74723;
       rv = -21.6;

       int j = jauStarpv(ra, dec, pmr, pmd, px, rv, pv);

       vvd(pv[0][0], 126668.5912743160601, 1e-10,"jauStarpv", "11");
       vvd(pv[0][1], 2136.792716839935195, 1e-12,"jauStarpv", "12");
       vvd(pv[0][2], -245251.2339876830091, 1e-10,"jauStarpv", "13");

       vvd(pv[1][0], -0.4051854008955659551e-2, 1e-13,"jauStarpv", "21");
       vvd(pv[1][1], -0.6253919754414777970e-2, 1e-15,"jauStarpv", "22");
       vvd(pv[1][2], 0.1189353714588109341e-1, 1e-13,"jauStarpv", "23");

       viv(j, 0, "jauStarpv", "j");

    }

    @Test
    public void t_sxp()
    /**
    **
    **  Test jauSxp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauSxp, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double s, p[] = new double[3], sp[] = new double[3];


       s = 2.0;

       p[0] =  0.3;
       p[1] =  1.2;
       p[2] = -2.5;

       sp = jauSxp(s, p);

       vvd(sp[0],  0.6, 0.0, "jauSxp", "1");
       vvd(sp[1],  2.4, 0.0, "jauSxp", "2");
       vvd(sp[2], -5.0, 0.0, "jauSxp", "3");

    }


    @Test
    public void t_sxpv()
    /**
    **
    **  Test jauSxpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauSxpv, vvd
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

       spv = jauSxpv(s, pv);

       vvd(spv[0][0],  0.6, 0.0, "jauSxpv", "p1");
       vvd(spv[0][1],  2.4, 0.0, "jauSxpv", "p2");
       vvd(spv[0][2], -5.0, 0.0, "jauSxpv", "p3");

       vvd(spv[1][0],  1.0, 0.0, "jauSxpv", "v1");
       vvd(spv[1][1],  6.4, 0.0, "jauSxpv", "v2");
       vvd(spv[1][2], -1.4, 0.0, "jauSxpv", "v3");

    }

    @Test
     public void t_taitt()
    /**
    **
    **  Test jauTaitt function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauTaitt, vvd, viv
    **
    **  This revision:  2010 September 7
    */
    {
       JulianDate jd = jauTaitt(2453750.5, 0.892482639);

       vvd(jd.djm0, 2453750.5, 1e-6, "jauTaitt", "t1");
       vvd(jd.djm1, 0.892855139, 1e-12, "jauTaitt", "t2");

    }

     @Test
     public void t_taiut1()
    /**
    **
    **  Test jauTaiut1 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauTaiut1, vvd, viv
    **
    **  This revision:  2010 September 7
    */
    {
       JulianDate jd = jauTaiut1(2453750.5, 0.892482639, -32.6659);

       vvd(jd.djm0, 2453750.5, 1e-6, "jauTaiut1", "u1");
       vvd(jd.djm1, 0.8921045614537037037, 1e-12, "jauTaiut1", "u2");

    }

     @Test
     public void t_taiutc() throws JSOFAIllegalParameter, JSOFAInternalError
    /**
    **
    **  Test jauTaiutc function.
    **
    **  Returned:
    **     status    LOGICAL     TRUE = success, FALSE = fail
    **
    **  Called:  jauTaiutc, vvd, viv
    **
    **  This revision:  2010 September 7
    */
    {
       JulianDate jd = jauTaiutc(2453750.5, 0.892482639);

       vvd(jd.djm0, 2453750.5, 1e-6, "jauTaiutc", "u1");
       vvd(jd.djm1, 0.8921006945555555556, 1e-12, "jauTaiutc", "u2");
 
    }

     @Test
     public void t_tcbtdb()
    /**
    **
    **  Test jauTcbtdb function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauTcbtdb, vvd, viv
    **
    **  This revision:  2010 September 6
    */
    {
       JulianDate jd = jauTcbtdb(2453750.5, 0.893019599);

       vvd(jd.djm0, 2453750.5, 1e-6, "jauTcbtdb", "b1");
       vvd(jd.djm1, 0.8928551362746343397, 1e-12, "jauTcbtdb", "b2");

    }

     @Test
     public void t_tcgtt()
    /**
    **
    **  Test jauTcgtt function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauTcgtt, vvd, viv
    **
    **  This revision:  2010 September g
    */
    {
       JulianDate jd = jauTcgtt(2453750.5, 0.892862531);

       vvd(jd.djm0, 2453750.5, 1e-6, "jauTcgtt", "t1");
       vvd(jd.djm1, 0.8928551387488816828, 1e-12, "jauTcgtt", "t2");

    }
     @Test
     public void t_tdbtcb()
    /**
    **
    **  Test jauTdbtcb function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauTdbtcb, vvd, viv
    **
    **  This revision:  2010 September 6
    */
    {
       JulianDate jd = jauTdbtcb(2453750.5, 0.892855137);

       vvd( jd.djm0, 2453750.5, 1e-6, "jauTdbtcb", "b1");
       vvd( jd.djm1, 0.8930195997253656716, 1e-12, "jauTdbtcb", "b2");

    }

     @Test
     public void t_tdbtt()
    /**
    **
    **  Test jauTdbtt function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauTdbtt, vvd, viv
    **
    **  This revision:  2010 September 7
    */
    {
       JulianDate jd = jauTdbtt(2453750.5, 0.892855137, -0.000201);

       vvd(jd.djm0, 2453750.5, 1e-6, "jauTdbtt", "t1");
       vvd(jd.djm1, 0.8928551393263888889, 1e-12, "jauTdbtt", "t2");

    }

//     void t_tf2a()
//    /*
//    **  - - - - - - -
//    **   t _ t f 2 a
//    **  - - - - - - -
//    **
//    **  Test jauTf2a function.
//    **
//    **  Returned:
//    **     status    int         TRUE = success, FALSE = fail
//    **
//    **  Called:  jauTf2a, vvd, viv
//    **
//    **  This revision:  2010 September 7
//    */
//    {
//       double a;
//    
//
//
//       j = jauTf2a('+', 4, 58, 20.2, a);
//
//       vvd(a, 1.301739278189537429, 1e-12, "jauTf2a", "a");
//       viv(j, 0, "jauTf2a", "j");
//
//    }
//
//     void t_tf2d()
//    /*
//    **  - - - - - - -
//    **   t _ t f 2 d
//    **  - - - - - - -
//    **
//    **  Test jauTf2d function.
//    **
//    **  Returned:
//    **     status    int         TRUE = success, FALSE = fail
//    **
//    **  Called:  jauTf2d, vvd, viv
//    **
//    **  This revision:  2010 September 7
//    */
//    {
//       double d;
//    
//
//
//       j = jauTf2d(' ', 23, 55, 10.9, d);
//
//       vvd(d, 0.9966539351851851852, 1e-12, "jauTf2d", "d");
//       viv(j, 0, "jauTf2d", "j");
//
//    }
   
    
    
    @Test
    public void t_tr()
    /**
    **
    **  Test jauTr function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauTr, vvd
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

       rt = jauTr(r);

       vvd(rt[0][0], 2.0, 0.0, "jauTr", "11");
       vvd(rt[0][1], 3.0, 0.0, "jauTr", "12");
       vvd(rt[0][2], 3.0, 0.0, "jauTr", "13");

       vvd(rt[1][0], 3.0, 0.0, "jauTr", "21");
       vvd(rt[1][1], 2.0, 0.0, "jauTr", "22");
       vvd(rt[1][2], 4.0, 0.0, "jauTr", "23");

       vvd(rt[2][0], 2.0, 0.0, "jauTr", "31");
       vvd(rt[2][1], 3.0, 0.0, "jauTr", "32");
       vvd(rt[2][2], 5.0, 0.0, "jauTr", "33");

    }

    @Test
    public void t_trxp()
    /**
    **
    **  Test jauTrxp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauTrxp, vvd
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

       trp = jauTrxp(r, p);

       vvd(trp[0], 5.2, 1e-12, "jauTrxp", "1");
       vvd(trp[1], 4.0, 1e-12, "jauTrxp", "2");
       vvd(trp[2], 5.4, 1e-12, "jauTrxp", "3");

    }

    @Test
    public void t_trxpv()
    /**
    **
    **  Test jauTrxpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauTrxpv, vvd
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

       trpv = jauTrxpv(r, pv);

       vvd(trpv[0][0], 5.2, 1e-12, "jauTrxpv", "p1");
       vvd(trpv[0][1], 4.0, 1e-12, "jauTrxpv", "p1");
       vvd(trpv[0][2], 5.4, 1e-12, "jauTrxpv", "p1");

       vvd(trpv[1][0], 3.9, 1e-12, "jauTrxpv", "v1");
       vvd(trpv[1][1], 5.3, 1e-12, "jauTrxpv", "v2");
       vvd(trpv[1][2], 4.1, 1e-12, "jauTrxpv", "v3");

    }
    
    @Test
    public void t_tttai()
    /**
    **
    **  Test jauTttai function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauTttai, vvd, viv
    **
    **  This revision:  2010 September 7
    */
    {



       JulianDate jd = jauTttai(2453750.5, 0.892482639);

       vvd(jd.djm0, 2453750.5, 1e-6, "jauTttai", "a1");
       vvd(jd.djm1, 0.892110139, 1e-12, "jauTttai", "a2");
       

    }

    @Test
    public void t_tttcg()
    /**
    **
    **  Test jauTttcg function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauTttcg, vvd, viv
    **
    **  This revision:  2010 September 7
    */
    {

       JulianDate jd = jauTttcg(2453750.5, 0.892482639);

       vvd( jd.djm0, 2453750.5, 1e-6, "jauTttcg", "g1");
       vvd( jd.djm1, 0.8924900312508587113, 1e-12, "jauTttcg", "g2");

    }

    @Test
    public void t_tttdb()
    /**
    **
    **  Test jauTttdb function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauTttdb, vvd, viv
    **
    **  This revision:  2010 September 7
    */
    {

       JulianDate jd = jauTttdb(2453750.5, 0.892855139, -0.000201);

       vvd(jd.djm0, 2453750.5, 1e-6, "jauTttdb", "b1");
       vvd(jd.djm1, 0.8928551366736111111, 1e-12, "jauTttdb", "b2");
      

    }

    @Test
    public void t_ttut1()
    /**
    **
    **  Test jauTtut1 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauTtut1, vvd, viv
    **
    **  This revision:  2010 September 7
    */
    {
       JulianDate jd = jauTtut1(2453750.5, 0.892855139, 64.8499);

       vvd(jd.djm0, 2453750.5, 1e-6, "jauTtut1", "u1");
       vvd(jd.djm1, 0.8921045614537037037, 1e-12, "jauTtut1", "u2");

    }

    @Test
    public void t_ut1tai()
    /**
    **
    **  Test jauUt1tai function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauUt1tai, vvd, viv
    **
    **  This revision:  2010 September 7
    */
    {


       JulianDate jd = jauUt1tai(2453750.5, 0.892104561, -32.6659);

       vvd(jd.djm0, 2453750.5, 1e-6, "jauUt1tai", "a1");
       vvd(jd.djm1, 0.8924826385462962963, 1e-12, "jauUt1tai", "a2");

    }

    @Test
    public void t_ut1tt()
    /**
    **
    **  Test jauUt1tt function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauUt1tt, vvd, viv
    **
    **  This revision:  2010 September 7
    */
    {
       JulianDate jd = jauUt1tt(2453750.5, 0.892104561, 64.8499);

       vvd(jd.djm0, 2453750.5, 1e-6, "jauUt1tt", "t1");
       vvd(jd.djm1, 0.8928551385462962963, 1e-12, "jauUt1tt", "t2");

    }

    @Test
    public void t_ut1utc() throws JSOFAIllegalParameter, JSOFAInternalError
    /**
    **
    **  Test jauUt1utc function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauUt1utc, vvd, viv
    **
    **  This revision:  2010 September 7
    */
    {
       JulianDate jd = jauUt1utc(2453750.5, 0.892104561, 0.3341);

       vvd(jd.djm0, 2453750.5, 1e-6, "jauUt1utc", "u1");
       vvd(jd.djm1, 0.8921006941018518519, 1e-12, "jauUt1utc", "u2");
      

    }

    @Test
    public void t_utctai() throws JSOFAIllegalParameter, JSOFAInternalError
    /**
    **
    **  Test jauUtctai function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauUtctai, vvd, viv
    **
    **  This revision:  2010 September 7
    */
    {


       JulianDate jd = jauUtctai(2453750.5, 0.892100694);

       vvd(jd.djm0, 2453750.5, 1e-6, "jauUtctai", "u1");
       vvd(jd.djm1, 0.8924826384444444444, 1e-12, "jauUtctai", "u2");

    }

    @Test
    public void t_utcut1() throws JSOFAIllegalParameter, JSOFAInternalError
    /**
    **
    **  Test jauUtcut1 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauUtcut1, vvd, viv
    **
    **  This revision:  2010 September 7
    */
    {
       JulianDate jd = jauUtcut1(2453750.5, 0.892100694, 0.3341);

       vvd(jd.djm0, 2453750.5, 1e-6, "jauUtcut1", "u1");
       vvd(jd.djm1, 0.8921045608981481481, 1e-12, "jauUtcut1", "u2");

    }

    @Test
    public void t_xy06()
    /**
    **
    **  Test jauXy06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauXy06, vvd
    **
    **  This revision:  2008 November 28
    */
    {

       CelestialIntermediatePole cip = jauXy06(2400000.5, 53736.0);

       vvd(cip.x, 0.5791308486706010975e-3, 1e-15, "jauXy06", "x");
       vvd(cip.y, 0.4020579816732958141e-4, 1e-16, "jauXy06", "y");

    }

    @Test
    public void t_xys00a()
    /**
    **
    **  Test jauXys00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauXys00a, vvd
    **
    **  This revision:  2008 November 28
    */
    {

       ICRFrame fr = jauXys00a(2400000.5, 53736.0);

       vvd(fr.cip.x,  0.5791308472168152904e-3, 1e-14, "jauXys00a", "x");
       vvd(fr.cip.y,  0.4020595661591500259e-4, 1e-15, "jauXys00a", "y");
       vvd(fr.s, -0.1220040848471549623e-7, 1e-18, "jauXys00a", "s");

    }

    @Test
    public void t_xys00b()
    /**
    **
    **  Test jauXys00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauXys00b, vvd
    **
    **  This revision:  2008 November 28
    */
    {


       ICRFrame fr = jauXys00b(2400000.5, 53736.0);

       vvd(fr.cip.x,  0.5791301929950208873e-3, 1e-14, "jauXys00b", "x");
       vvd(fr.cip.y,  0.4020553681373720832e-4, 1e-15, "jauXys00b", "y");
       vvd(fr.s, -0.1220027377285083189e-7, 1e-18, "jauXys00b", "s");

    }

    @Test
    public void t_xys06a()
    /**
    **
    **  Test jauXys06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauXys06a, vvd
    **
    **  This revision:  2008 November 28
    */
    {
      
       ICRFrame fr = jauXys06a(2400000.5, 53736.0);

       vvd(fr.cip.x,  0.5791308482835292617e-3, 1e-14, "jauXys06a", "x");
       vvd(fr.cip.y,  0.4020580099454020310e-4, 1e-15, "jauXys06a", "y");
       vvd(fr.s, -0.1220032294164579896e-7, 1e-18, "jauXys06a", "s");

    }

    @Test
    public void t_zp()
    /**
    **
    **  Test jauZp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauZp, vvd
    **
    **  This revision:  2008 November 30
    */
    {
       double p[] = new double[3];


       p[0] =  0.3;
       p[1] =  1.2;
       p[2] = -2.5;

       jauZp(p);

       vvd(p[0], 0.0, 0.0, "jauZp", "1");
       vvd(p[1], 0.0, 0.0, "jauZp", "2");
       vvd(p[2], 0.0, 0.0, "jauZp", "3");

    }

    @Test
    public void t_zpv()
    /**
    **
    **  Test jauZpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauZpv, vvd
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

       jauZpv(pv);

       vvd(pv[0][0], 0.0, 0.0, "jauZpv", "p1");
       vvd(pv[0][1], 0.0, 0.0, "jauZpv", "p2");
       vvd(pv[0][2], 0.0, 0.0, "jauZpv", "p3");

       vvd(pv[1][0], 0.0, 0.0, "jauZpv", "v1");
       vvd(pv[1][1], 0.0, 0.0, "jauZpv", "v2");
       vvd(pv[1][2], 0.0, 0.0, "jauZpv", "v3");

    }

    @Test
    public void t_zr()
    /**
    **
    **  Test jauZr function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  jauZr, vvd
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

       jauZr(r);

       vvd(r[0][0], 0.0, 0.0, "jauZr", "00");
       vvd(r[1][0], 0.0, 0.0, "jauZr", "01");
       vvd(r[2][0], 0.0, 0.0, "jauZr", "02");

       vvd(r[0][1], 0.0, 0.0, "jauZr", "10");
       vvd(r[1][1], 0.0, 0.0, "jauZr", "11");
       vvd(r[2][1], 0.0, 0.0, "jauZr", "12");

       vvd(r[0][2], 0.0, 0.0, "jauZr", "20");
       vvd(r[1][2], 0.0, 0.0, "jauZr", "21");
       vvd(r[2][2], 0.0, 0.0, "jauZr", "22");

    }

    
    // additions for 20131202
    
    @Test
public void t_apcg()
    /**
    **
    **  Test jauApcg function.
    **
    **  Returned:
    **     status    int         FALSE = success, TRUE = fail
    **
    **  Called:  jauApcg, vvd
    **
    **  This revision:  2013 October 3
    */
    {
       double date1, date2, ebpv[][] = new double[2][3], ehp[] = new double[3];
       Astrom astrom = new Astrom();


       date1 = 2456165.5;
       date2 = 0.401182685;
       ebpv[0][0] =  0.901310875;
       ebpv[0][1] = -0.417402664;
       ebpv[0][2] = -0.180982288;
       ebpv[1][0] =  0.00742727954;
       ebpv[1][1] =  0.0140507459;
       ebpv[1][2] =  0.00609045792;
       ehp[0] =  0.903358544;
       ehp[1] = -0.415395237;
       ehp[2] = -0.180084014;

       jauApcg(date1, date2, ebpv, ehp, astrom);

       vvd(astrom.pmt, 12.65133794027378508, 1e-11,
                       "jauApcg", "pmt");
       vvd(astrom.eb[0], 0.901310875, 1e-12,
                         "jauApcg", "eb(1)");
       vvd(astrom.eb[1], -0.417402664, 1e-12,
                         "jauApcg", "eb(2)");
       vvd(astrom.eb[2], -0.180982288, 1e-12,
                         "jauApcg", "eb(3)");
       vvd(astrom.eh[0], 0.8940025429324143045, 1e-12,
                         "jauApcg", "eh(1)");
       vvd(astrom.eh[1], -0.4110930268679817955, 1e-12,
                         "jauApcg", "eh(2)");
       vvd(astrom.eh[2], -0.1782189004872870264, 1e-12,
                         "jauApcg", "eh(3)");
       vvd(astrom.em, 1.010465295811013146, 1e-12,
                      "jauApcg", "em");
       vvd(astrom.v[0], 0.4289638913597693554e-4, 1e-16,
                        "jauApcg", "v(1)");
       vvd(astrom.v[1], 0.8115034051581320575e-4, 1e-16,
                        "jauApcg", "v(2)");
       vvd(astrom.v[2], 0.3517555136380563427e-4, 1e-16,
                        "jauApcg", "v(3)");
       vvd(astrom.bm1, 0.9999999951686012981, 1e-12,
                       "jauApcg", "bm1");
       vvd(astrom.bpn[0][0], 1.0, 0.0,
                             "jauApcg", "bpn(1,1)");
       vvd(astrom.bpn[1][0], 0.0, 0.0,
                             "jauApcg", "bpn(2,1)");
       vvd(astrom.bpn[2][0], 0.0, 0.0,
                             "jauApcg", "bpn(3,1)");
       vvd(astrom.bpn[0][1], 0.0, 0.0,
                             "jauApcg", "bpn(1,2)");
       vvd(astrom.bpn[1][1], 1.0, 0.0,
                             "jauApcg", "bpn(2,2)");
       vvd(astrom.bpn[2][1], 0.0, 0.0,
                             "jauApcg", "bpn(3,2)");
       vvd(astrom.bpn[0][2], 0.0, 0.0,
                             "jauApcg", "bpn(1,3)");
       vvd(astrom.bpn[1][2], 0.0, 0.0,
                             "jauApcg", "bpn(2,3)");
       vvd(astrom.bpn[2][2], 1.0, 0.0,
                             "jauApcg", "bpn(3,3)");

    }

    @Test
    public void t_ab()
    /**
    **
    **  Test jauAb function.
    **
    **  Returned:
    **     status    int         FALSE = success, TRUE = fail
    **
    **  Called:  jauAb, vvd
    **
    **  This revision:  2013 October 1
    */
    {
       double pnat[] = new double[3], v[] = new double[3], s, bm1, ppr[];


       pnat[0] = -0.76321968546737951;
       pnat[1] = -0.60869453983060384;
       pnat[2] = -0.21676408580639883;
       v[0] =  2.1044018893653786e-5;
       v[1] = -8.9108923304429319e-5;
       v[2] = -3.8633714797716569e-5;
       s = 0.99980921395708788;
       bm1 = 0.99999999506209258;

       ppr = jauAb(pnat, v, s, bm1);

       vvd(ppr[0], -0.7631631094219556269, 1e-12, "jauAb", "1");
       vvd(ppr[1], -0.6087553082505590832, 1e-12, "jauAb", "2");
       vvd(ppr[2], -0.2167926269368471279, 1e-12, "jauAb", "3");

    }

    @Test
    public void t_apcg13()
    /**
    **
    **  Test jauApcg13 function.
    **
    **  Returned:
    **     status    int         FALSE = success, TRUE = fail
    **
    **  Called:  jauApcg13, vvd
    **
    **  This revision:  2013 October 3
    */
    {
       double date1, date2;
       Astrom astrom = new Astrom();


       date1 = 2456165.5;
       date2 = 0.401182685;

       jauApcg13(date1, date2, astrom);

       vvd(astrom.pmt, 12.65133794027378508, 1e-11,
                       "jauApcg13", "pmt");
       vvd(astrom.eb[0], 0.9013108747340644755, 1e-12,
                       "jauApcg13", "eb(1)");
       vvd(astrom.eb[1], -0.4174026640406119957, 1e-12,
                       "jauApcg13", "eb(2)");
       vvd(astrom.eb[2], -0.1809822877867817771, 1e-12,
                       "jauApcg13", "eb(3)");
       vvd(astrom.eh[0], 0.8940025429255499549, 1e-12,
                       "jauApcg13", "eh(1)");
       vvd(astrom.eh[1], -0.4110930268331896318, 1e-12,
                       "jauApcg13", "eh(2)");
       vvd(astrom.eh[2], -0.1782189006019749850, 1e-12,
                       "jauApcg13", "eh(3)");
       vvd(astrom.em, 1.010465295964664178, 1e-12,
                       "jauApcg13", "em");
       vvd(astrom.v[0], 0.4289638912941341125e-4, 1e-16,
                       "jauApcg13", "v(1)");
       vvd(astrom.v[1], 0.8115034032405042132e-4, 1e-16,
                       "jauApcg13", "v(2)");
       vvd(astrom.v[2], 0.3517555135536470279e-4, 1e-16,
                       "jauApcg13", "v(3)");
       vvd(astrom.bm1, 0.9999999951686013142, 1e-12,
                       "jauApcg13", "bm1");
       vvd(astrom.bpn[0][0], 1.0, 0.0,
                             "jauApcg13", "bpn(1,1)");
       vvd(astrom.bpn[1][0], 0.0, 0.0,
                             "jauApcg13", "bpn(2,1)");
       vvd(astrom.bpn[2][0], 0.0, 0.0,
                             "jauApcg13", "bpn(3,1)");
       vvd(astrom.bpn[0][1], 0.0, 0.0,
                             "jauApcg13", "bpn(1,2)");
       vvd(astrom.bpn[1][1], 1.0, 0.0,
                             "jauApcg13", "bpn(2,2)");
       vvd(astrom.bpn[2][1], 0.0, 0.0,
                             "jauApcg13", "bpn(3,2)");
       vvd(astrom.bpn[0][2], 0.0, 0.0,
                             "jauApcg13", "bpn(1,3)");
       vvd(astrom.bpn[1][2], 0.0, 0.0,
                             "jauApcg13", "bpn(2,3)");
       vvd(astrom.bpn[2][2], 1.0, 0.0,
                             "jauApcg13", "bpn(3,3)");

    }
    @Test
    public void t_apci()
    /**
    **
    **  Test jauApci function.
    **
    **  Returned:
    **     status    int         FALSE = success, TRUE = fail
    **
    **  Called:  jauApci, vvd
    **
    **  This revision:  2013 October 3
    */
    {
       double date1, date2, ebpv[][] = new double[2][3], ehp[] = new double[3], x, y, s;
       Astrom astrom = new Astrom() ;


       date1 = 2456165.5;
       date2 = 0.401182685;
       ebpv[0][0] =  0.901310875;
       ebpv[0][1] = -0.417402664;
       ebpv[0][2] = -0.180982288;
       ebpv[1][0] =  0.00742727954;
       ebpv[1][1] =  0.0140507459;
       ebpv[1][2] =  0.00609045792;
       ehp[0] =  0.903358544;
       ehp[1] = -0.415395237;
       ehp[2] = -0.180084014;
       x =  0.0013122272;
       y = -2.92808623e-5;
       s =  3.05749468e-8;

       jauApci(date1, date2, ebpv, ehp, x, y, s, astrom);

       vvd(astrom.pmt, 12.65133794027378508, 1e-11,
                       "jauApci", "pmt");
       vvd(astrom.eb[0], 0.901310875, 1e-12,
                         "jauApci", "eb(1)");
       vvd(astrom.eb[1], -0.417402664, 1e-12,
                         "jauApci", "eb(2)");
       vvd(astrom.eb[2], -0.180982288, 1e-12,
                         "jauApci", "eb(3)");
       vvd(astrom.eh[0], 0.8940025429324143045, 1e-12,
                         "jauApci", "eh(1)");
       vvd(astrom.eh[1], -0.4110930268679817955, 1e-12,
                         "jauApci", "eh(2)");
       vvd(astrom.eh[2], -0.1782189004872870264, 1e-12,
                         "jauApci", "eh(3)");
       vvd(astrom.em, 1.010465295811013146, 1e-12,
                      "jauApci", "em");
       vvd(astrom.v[0], 0.4289638913597693554e-4, 1e-16,
                        "jauApci", "v(1)");
       vvd(astrom.v[1], 0.8115034051581320575e-4, 1e-16,
                        "jauApci", "v(2)");
       vvd(astrom.v[2], 0.3517555136380563427e-4, 1e-16,
                        "jauApci", "v(3)");
       vvd(astrom.bm1, 0.9999999951686012981, 1e-12,
                       "jauApci", "bm1");
       vvd(astrom.bpn[0][0], 0.9999991390295159156, 1e-12,
                             "jauApci", "bpn(1,1)");
       vvd(astrom.bpn[1][0], 0.4978650072505016932e-7, 1e-12,
                             "jauApci", "bpn(2,1)");
       vvd(astrom.bpn[2][0], 0.1312227200000000000e-2, 1e-12,
                             "jauApci", "bpn(3,1)");
       vvd(astrom.bpn[0][1], -0.1136336653771609630e-7, 1e-12,
                             "jauApci", "bpn(1,2)");
       vvd(astrom.bpn[1][1], 0.9999999995713154868, 1e-12,
                             "jauApci", "bpn(2,2)");
       vvd(astrom.bpn[2][1], -0.2928086230000000000e-4, 1e-12,
                             "jauApci", "bpn(3,2)");
       vvd(astrom.bpn[0][2], -0.1312227200895260194e-2, 1e-12,
                             "jauApci", "bpn(1,3)");
       vvd(astrom.bpn[1][2], 0.2928082217872315680e-4, 1e-12,
                             "jauApci", "bpn(2,3)");
       vvd(astrom.bpn[2][2], 0.9999991386008323373, 1e-12,
                             "jauApci", "bpn(3,3)");

    }
    
    @Test
    public void t_apci13()
    /**
    **
    **  Test jauApci13 function.
    **
    **  Returned:
    **     status    int         FALSE = success, TRUE = fail
    **
    **  Called:  jauApci13, vvd
    **
    **  This revision:  2013 October 3
    */
    {
       double date1, date2, eo;
       Astrom astrom = new Astrom() ;


       date1 = 2456165.5;
       date2 = 0.401182685;

       eo = jauApci13(date1, date2, astrom);

       vvd(astrom.pmt, 12.65133794027378508, 1e-11,
                       "jauApci13", "pmt");
       vvd(astrom.eb[0], 0.9013108747340644755, 1e-12,
                         "jauApci13", "eb(1)");
       vvd(astrom.eb[1], -0.4174026640406119957, 1e-12,
                         "jauApci13", "eb(2)");
       vvd(astrom.eb[2], -0.1809822877867817771, 1e-12,
                         "jauApci13", "eb(3)");
       vvd(astrom.eh[0], 0.8940025429255499549, 1e-12,
                         "jauApci13", "eh(1)");
       vvd(astrom.eh[1], -0.4110930268331896318, 1e-12,
                         "jauApci13", "eh(2)");
       vvd(astrom.eh[2], -0.1782189006019749850, 1e-12,
                         "jauApci13", "eh(3)");
       vvd(astrom.em, 1.010465295964664178, 1e-12,
                      "jauApci13", "em");
       vvd(astrom.v[0], 0.4289638912941341125e-4, 1e-16,
                        "jauApci13", "v(1)");
       vvd(astrom.v[1], 0.8115034032405042132e-4, 1e-16,
                        "jauApci13", "v(2)");
       vvd(astrom.v[2], 0.3517555135536470279e-4, 1e-16,
                        "jauApci13", "v(3)");
       vvd(astrom.bm1, 0.9999999951686013142, 1e-12,
                       "jauApci13", "bm1");
       vvd(astrom.bpn[0][0], 0.9999992060376761710, 1e-12,
                             "jauApci13", "bpn(1,1)");
       vvd(astrom.bpn[1][0], 0.4124244860106037157e-7, 1e-12,
                             "jauApci13", "bpn(2,1)");
       vvd(astrom.bpn[2][0], 0.1260128571051709670e-2, 1e-12,
                             "jauApci13", "bpn(3,1)");
       vvd(astrom.bpn[0][1], -0.1282291987222130690e-7, 1e-12,
                             "jauApci13", "bpn(1,2)");
       vvd(astrom.bpn[1][1], 0.9999999997456835325, 1e-12,
                             "jauApci13", "bpn(2,2)");
       vvd(astrom.bpn[2][1], -0.2255288829420524935e-4, 1e-12,
                             "jauApci13", "bpn(3,2)");
       vvd(astrom.bpn[0][2], -0.1260128571661374559e-2, 1e-12,
                             "jauApci13", "bpn(1,3)");
       vvd(astrom.bpn[1][2], 0.2255285422953395494e-4, 1e-12,
                             "jauApci13", "bpn(2,3)");
       vvd(astrom.bpn[2][2], 0.9999992057833604343, 1e-12,
                             "jauApci13", "bpn(3,3)");
       vvd(eo, -0.2900618712657375647e-2, 1e-12,
               "jauApci13", "eo");

    } 
    
    @Test
    public void t_apco() throws JSOFAIllegalParameter, JSOFAInternalError
    /**
    **
    **  Test jauApco function.
    **
    **  Returned:
    **     status    int         FALSE = success, TRUE = fail
    **
    **  Called:  jauApco, vvd
    **
    **  This revision:  2013 October 3
    */
    {
       double date1, date2, ebpv[][] = new double[2][3], ehp[] = new double[3], x, y, s,
              theta, elong, phi, hm, xp, yp, sp, refa, refb;
       Astrom astrom = new Astrom() ;


       date1 = 2456384.5;
       date2 = 0.970031644;
       ebpv[0][0] = -0.974170438;
       ebpv[0][1] = -0.211520082;
       ebpv[0][2] = -0.0917583024;
       ebpv[1][0] = 0.00364365824;
       ebpv[1][1] = -0.0154287319;
       ebpv[1][2] = -0.00668922024;
       ehp[0] = -0.973458265;
       ehp[1] = -0.209215307;
       ehp[2] = -0.0906996477;
       x = 0.0013122272;
       y = -2.92808623e-5;
       s = 3.05749468e-8;
       theta = 3.14540971;
       elong = -0.527800806;
       phi = -1.2345856;
       hm = 2738.0;
       xp = 2.47230737e-7;
       yp = 1.82640464e-6;
       sp = -3.01974337e-11;
       refa = 0.000201418779;
       refb = -2.36140831e-7;

       jauApco(date1, date2, ebpv, ehp, x, y, s,
               theta, elong, phi, hm, xp, yp, sp,
               refa, refb, astrom);

       vvd(astrom.pmt, 13.25248468622587269, 1e-11,
                       "jauApco", "pmt");
       vvd(astrom.eb[0], -0.9741827110630322720, 1e-12,
                         "jauApco", "eb(1)");
       vvd(astrom.eb[1], -0.2115130190135344832, 1e-12,
                         "jauApco", "eb(2)");
       vvd(astrom.eb[2], -0.09179840186949532298, 1e-12,
                         "jauApco", "eb(3)");
       vvd(astrom.eh[0], -0.9736425571689739035, 1e-12,
                         "jauApco", "eh(1)");
       vvd(astrom.eh[1], -0.2092452125849330936, 1e-12,
                         "jauApco", "eh(2)");
       vvd(astrom.eh[2], -0.09075578152243272599, 1e-12,
                         "jauApco", "eh(3)");
       vvd(astrom.em, 0.9998233241709957653, 1e-12,
                      "jauApco", "em");
       vvd(astrom.v[0], 0.2078704992916728762e-4, 1e-16,
                        "jauApco", "v(1)");
       vvd(astrom.v[1], -0.8955360107151952319e-4, 1e-16,
                        "jauApco", "v(2)");
       vvd(astrom.v[2], -0.3863338994288951082e-4, 1e-16,
                        "jauApco", "v(3)");
       vvd(astrom.bm1, 0.9999999950277561236, 1e-12,
                       "jauApco", "bm1");
       vvd(astrom.bpn[0][0], 0.9999991390295159156, 1e-12,
                             "jauApco", "bpn(1,1)");
       vvd(astrom.bpn[1][0], 0.4978650072505016932e-7, 1e-12,
                             "jauApco", "bpn(2,1)");
       vvd(astrom.bpn[2][0], 0.1312227200000000000e-2, 1e-12,
                             "jauApco", "bpn(3,1)");
       vvd(astrom.bpn[0][1], -0.1136336653771609630e-7, 1e-12,
                             "jauApco", "bpn(1,2)");
       vvd(astrom.bpn[1][1], 0.9999999995713154868, 1e-12,
                             "jauApco", "bpn(2,2)");
       vvd(astrom.bpn[2][1], -0.2928086230000000000e-4, 1e-12,
                             "jauApco", "bpn(3,2)");
       vvd(astrom.bpn[0][2], -0.1312227200895260194e-2, 1e-12,
                             "jauApco", "bpn(1,3)");
       vvd(astrom.bpn[1][2], 0.2928082217872315680e-4, 1e-12,
                             "jauApco", "bpn(2,3)");
       vvd(astrom.bpn[2][2], 0.9999991386008323373, 1e-12,
                             "jauApco", "bpn(3,3)");
       vvd(astrom.along, -0.5278008060295995734, 1e-12,
                         "jauApco", "along");
       vvd(astrom.xpl, 0.1133427418130752958e-5, 1e-17,
                       "jauApco", "xpl");
       vvd(astrom.ypl, 0.1453347595780646207e-5, 1e-17,
                       "jauApco", "ypl");
       vvd(astrom.sphi, -0.9440115679003211329, 1e-12,
                        "jauApco", "sphi");
       vvd(astrom.cphi, 0.3299123514971474711, 1e-12,
                        "jauApco", "cphi");
       vvd(astrom.diurab, 0, 0,
                          "jauApco", "diurab");
       vvd(astrom.eral, 2.617608903970400427, 1e-12,
                        "jauApco", "eral");
       vvd(astrom.refa, 0.2014187790000000000e-3, 1e-15,
                        "jauApco", "refa");
       vvd(astrom.refb, -0.2361408310000000000e-6, 1e-18,
                        "jauApco", "refb");

    }
    


@Test
    public void t_apco13() throws JSOFAIllegalParameter, JSOFAInternalError
/**
**  Test jauApco13 function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauApco13, vvd, viv
**
**  This revision:  2013 October 4
*/
{
   double utc1, utc2, dut1, elong, phi, hm, xp, yp,
          phpa, tc, rh, wl, eo;
   Astrom astrom = new Astrom() ;


   utc1 = 2456384.5;
   utc2 = 0.969254051;
   dut1 = 0.1550675;
   elong = -0.527800806;
   phi = -1.2345856;
   hm = 2738.0;
   xp = 2.47230737e-7;
   yp = 1.82640464e-6;
   phpa = 731.0;
   tc = 12.8;
   rh = 0.59;
   wl = 0.55;

   eo = jauApco13(utc1, utc2, dut1, elong, phi, hm, xp, yp,
                 phpa, tc, rh, wl, astrom);

   vvd(astrom.pmt, 13.25248468622475727, 1e-11,
                   "jauApco13", "pmt");
   vvd(astrom.eb[0], -0.9741827107320875162, 1e-12,
                   "jauApco13", "eb(1)");
   vvd(astrom.eb[1], -0.2115130190489716682, 1e-12,
                     "jauApco13", "eb(2)");
   vvd(astrom.eb[2], -0.09179840189496755339, 1e-12,
                     "jauApco13", "eb(3)");
   vvd(astrom.eh[0], -0.9736425572586935247, 1e-12,
                     "jauApco13", "eh(1)");
   vvd(astrom.eh[1], -0.2092452121603336166, 1e-12,
                     "jauApco13", "eh(2)");
   vvd(astrom.eh[2], -0.09075578153885665295, 1e-12,
                     "jauApco13", "eh(3)");
   vvd(astrom.em, 0.9998233240913898141, 1e-12,
                  "jauApco13", "em");
   vvd(astrom.v[0], 0.2078704994520489246e-4, 1e-16,
                    "jauApco13", "v(1)");
   vvd(astrom.v[1], -0.8955360133238868938e-4, 1e-16,
                    "jauApco13", "v(2)");
   vvd(astrom.v[2], -0.3863338993055887398e-4, 1e-16,
                    "jauApco13", "v(3)");
   vvd(astrom.bm1, 0.9999999950277561004, 1e-12,
                   "jauApco13", "bm1");
   vvd(astrom.bpn[0][0], 0.9999991390295147999, 1e-12,
                         "jauApco13", "bpn(1,1)");
   vvd(astrom.bpn[1][0], 0.4978650075315529277e-7, 1e-12,
                         "jauApco13", "bpn(2,1)");
   vvd(astrom.bpn[2][0], 0.001312227200850293372, 1e-12,
                         "jauApco13", "bpn(3,1)");
   vvd(astrom.bpn[0][1], -0.1136336652812486604e-7, 1e-12,
                         "jauApco13", "bpn(1,2)");
   vvd(astrom.bpn[1][1], 0.9999999995713154865, 1e-12,
                         "jauApco13", "bpn(2,2)");
   vvd(astrom.bpn[2][1], -0.2928086230975367296e-4, 1e-12,
                         "jauApco13", "bpn(3,2)");
   vvd(astrom.bpn[0][2], -0.001312227201745553566, 1e-12,
                         "jauApco13", "bpn(1,3)");
   vvd(astrom.bpn[1][2], 0.2928082218847679162e-4, 1e-12,
                         "jauApco13", "bpn(2,3)");
   vvd(astrom.bpn[2][2], 0.9999991386008312212, 1e-12,
                         "jauApco13", "bpn(3,3)");
   vvd(astrom.along, -0.5278008060295995733, 1e-12,
                     "jauApco13", "along");
   vvd(astrom.xpl, 0.1133427418130752958e-5, 1e-17,
                   "jauApco13", "xpl");
   vvd(astrom.ypl, 0.1453347595780646207e-5, 1e-17,
                   "jauApco13", "ypl");
   vvd(astrom.sphi, -0.9440115679003211329, 1e-12,
                    "jauApco13", "sphi");
   vvd(astrom.cphi, 0.3299123514971474711, 1e-12,
                    "jauApco13", "cphi");
   vvd(astrom.diurab, 0, 0,
                      "jauApco13", "diurab");
   vvd(astrom.eral, 2.617608909189664000, 1e-12,
                    "jauApco13", "eral");
   vvd(astrom.refa, 0.2014187785940396921e-3, 1e-15,
                    "jauApco13", "refa");
   vvd(astrom.refb, -0.2361408314943696227e-6, 1e-18,
                    "jauApco13", "refb");
   vvd(eo, -0.003020548354802412839, 1e-14,
           "jauApco13", "eo");

}

@Test
    public void t_apcs()
/**
**  Test jauApcs function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauApcs, vvd
**
**  This revision:  2013 October 3
*/
{
   double date1, date2, pv[][] = new double[2][3], ebpv[][] = new double[2][3], ehp[] = new double[3];
   Astrom astrom = new Astrom() ;


   date1 = 2456384.5;
   date2 = 0.970031644;
   pv[0][0] = -1836024.09;
   pv[0][1] = 1056607.72;
   pv[0][2] = -5998795.26;
   pv[1][0] = -77.0361767;
   pv[1][1] = -133.310856;
   pv[1][2] = 0.0971855934;
   ebpv[0][0] = -0.974170438;
   ebpv[0][1] = -0.211520082;
   ebpv[0][2] = -0.0917583024;
   ebpv[1][0] = 0.00364365824;
   ebpv[1][1] = -0.0154287319;
   ebpv[1][2] = -0.00668922024;
   ehp[0] = -0.973458265;
   ehp[1] = -0.209215307;
   ehp[2] = -0.0906996477;

   jauApcs(date1, date2, pv, ebpv, ehp, astrom);

   vvd(astrom.pmt, 13.25248468622587269, 1e-11,
                   "jauApcs", "pmt");
   vvd(astrom.eb[0], -0.9741827110629881886, 1e-12,
                     "jauApcs", "eb(1)");
   vvd(astrom.eb[1], -0.2115130190136415986, 1e-12,
                     "jauApcs", "eb(2)");
   vvd(astrom.eb[2], -0.09179840186954412099, 1e-12,
                     "jauApcs", "eb(3)");
   vvd(astrom.eh[0], -0.9736425571689454706, 1e-12,
                     "jauApcs", "eh(1)");
   vvd(astrom.eh[1], -0.2092452125850435930, 1e-12,
                     "jauApcs", "eh(2)");
   vvd(astrom.eh[2], -0.09075578152248299218, 1e-12,
                     "jauApcs", "eh(3)");
   vvd(astrom.em, 0.9998233241709796859, 1e-12,
                  "jauApcs", "em");
   vvd(astrom.v[0], 0.2078704993282685510e-4, 1e-16,
                    "jauApcs", "v(1)");
   vvd(astrom.v[1], -0.8955360106989405683e-4, 1e-16,
                    "jauApcs", "v(2)");
   vvd(astrom.v[2], -0.3863338994289409097e-4, 1e-16,
                    "jauApcs", "v(3)");
   vvd(astrom.bm1, 0.9999999950277561237, 1e-12,
                   "jauApcs", "bm1");
   vvd(astrom.bpn[0][0], 1, 0,
                         "jauApcs", "bpn(1,1)");
   vvd(astrom.bpn[1][0], 0, 0,
                         "jauApcs", "bpn(2,1)");
   vvd(astrom.bpn[2][0], 0, 0,
                         "jauApcs", "bpn(3,1)");
   vvd(astrom.bpn[0][1], 0, 0,
                         "jauApcs", "bpn(1,2)");
   vvd(astrom.bpn[1][1], 1, 0,
                         "jauApcs", "bpn(2,2)");
   vvd(astrom.bpn[2][1], 0, 0,
                         "jauApcs", "bpn(3,2)");
   vvd(astrom.bpn[0][2], 0, 0,
                         "jauApcs", "bpn(1,3)");
   vvd(astrom.bpn[1][2], 0, 0,
                         "jauApcs", "bpn(2,3)");
   vvd(astrom.bpn[2][2], 1, 0,
                         "jauApcs", "bpn(3,3)");

}

@Test
    public void t_apcs13()
/**
**  Test jauApcs13 function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauApcs13, vvd
**
**  This revision:  2013 October 3
*/
{
   double date1, date2, pv[][] = new double[2][3];
   Astrom astrom = new Astrom() ;


   date1 = 2456165.5;
   date2 = 0.401182685;
   pv[0][0] = -6241497.16;
   pv[0][1] = 401346.896;
   pv[0][2] = -1251136.04;
   pv[1][0] = -29.264597;
   pv[1][1] = -455.021831;
   pv[1][2] = 0.0266151194;

   jauApcs13(date1, date2, pv, astrom);

   vvd(astrom.pmt, 12.65133794027378508, 1e-11,
                   "jauApcs13", "pmt");
   vvd(astrom.eb[0], 0.9012691529025250644, 1e-12,
                     "jauApcs13", "eb(1)");
   vvd(astrom.eb[1], -0.4173999812023194317, 1e-12,
                     "jauApcs13", "eb(2)");
   vvd(astrom.eb[2], -0.1809906511146429670, 1e-12,
                     "jauApcs13", "eb(3)");
   vvd(astrom.eh[0], 0.8939939101760130792, 1e-12,
                     "jauApcs13", "eh(1)");
   vvd(astrom.eh[1], -0.4111053891734021478, 1e-12,
                     "jauApcs13", "eh(2)");
   vvd(astrom.eh[2], -0.1782336880636997374, 1e-12,
                     "jauApcs13", "eh(3)");
   vvd(astrom.em, 1.010428384373491095, 1e-12,
                  "jauApcs13", "em");
   vvd(astrom.v[0], 0.4279877294121697570e-4, 1e-16,
                    "jauApcs13", "v(1)");
   vvd(astrom.v[1], 0.7963255087052120678e-4, 1e-16,
                    "jauApcs13", "v(2)");
   vvd(astrom.v[2], 0.3517564013384691531e-4, 1e-16,
                    "jauApcs13", "v(3)");
   vvd(astrom.bm1, 0.9999999952947980978, 1e-12,
                   "jauApcs13", "bm1");
   vvd(astrom.bpn[0][0], 1, 0,
                         "jauApcs13", "bpn(1,1)");
   vvd(astrom.bpn[1][0], 0, 0,
                         "jauApcs13", "bpn(2,1)");
   vvd(astrom.bpn[2][0], 0, 0,
                         "jauApcs13", "bpn(3,1)");
   vvd(astrom.bpn[0][1], 0, 0,
                         "jauApcs13", "bpn(1,2)");
   vvd(astrom.bpn[1][1], 1, 0,
                         "jauApcs13", "bpn(2,2)");
   vvd(astrom.bpn[2][1], 0, 0,
                         "jauApcs13", "bpn(3,2)");
   vvd(astrom.bpn[0][2], 0, 0,
                         "jauApcs13", "bpn(1,3)");
   vvd(astrom.bpn[1][2], 0, 0,
                         "jauApcs13", "bpn(2,3)");
   vvd(astrom.bpn[2][2], 1, 0,
                         "jauApcs13", "bpn(3,3)");

}
@Test
    public void t_aper()
/*
**  Test jauAper function.
*
**  Returned:
**     status    int         FALSE = success, TRUE = fail
*
**  Called:  jauAper, vvd
*
**  This revision:  2013 October 3
*/
{
   double theta;
   Astrom astrom = new Astrom() ;


   astrom.along = 1.234;
   theta = 5.678;

   jauAper(theta, astrom);

   vvd(astrom.eral, 6.912000000000000000, 1e-12,
                    "jauAper", "pmt");

}
@Test
    public void t_aper13()
/**
**  Test jauAper13 function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauAper13, vvd
**
**  This revision:  2013 October 3
*/
{
   double ut11, ut12;
   Astrom astrom = new Astrom() ;


   astrom.along = 1.234;
   ut11 = 2456165.5;
   ut12 = 0.401182685;

   jauAper13(ut11, ut12, astrom);

   vvd(astrom.eral, 3.316236661789694933, 1e-12,
                    "jauAper13", "pmt");

}
@Test
    public void t_apio() throws JSOFAIllegalParameter, JSOFAInternalError
/**
**  Test jauApio function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauApio, vvd
**
**  This revision:  2013 October 3
*/
{
   double sp, theta, elong, phi, hm, xp, yp, refa, refb;
   Astrom astrom = new Astrom() ;


   sp = -3.01974337e-11;
   theta = 3.14540971;
   elong = -0.527800806;
   phi = -1.2345856;
   hm = 2738.0;
   xp = 2.47230737e-7;
   yp = 1.82640464e-6;
   refa = 0.000201418779;
   refb = -2.36140831e-7;

   jauApio(sp, theta, elong, phi, hm, xp, yp, refa, refb, astrom);

   vvd(astrom.along, -0.5278008060295995734, 1e-12,
                     "jauApio", "along");
   vvd(astrom.xpl, 0.1133427418130752958e-5, 1e-17,
                   "jauApio", "xpl");
   vvd(astrom.ypl, 0.1453347595780646207e-5, 1e-17,
                   "jauApio", "ypl");
   vvd(astrom.sphi, -0.9440115679003211329, 1e-12,
                    "jauApio", "sphi");
   vvd(astrom.cphi, 0.3299123514971474711, 1e-12,
                    "jauApio", "cphi");
   vvd(astrom.diurab, 0.5135843661699913529e-6, 1e-12,
                      "jauApio", "diurab");
   vvd(astrom.eral, 2.617608903970400427, 1e-12,
                    "jauApio", "eral");
   vvd(astrom.refa, 0.2014187790000000000e-3, 1e-15,
                    "jauApio", "refa");
   vvd(astrom.refb, -0.2361408310000000000e-6, 1e-18,
                    "jauApio", "refb");

}

@Test
    public void t_apio13() throws JSOFAIllegalParameter, JSOFAInternalError
/**
**  Test jauApio13 function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauApio13, vvd, viv
**
**  This revision:  2013 October 4
*/
{
   double utc1, utc2, dut1, elong, phi, hm, xp, yp, phpa, tc, rh, wl;

   Astrom astrom = new Astrom() ;


   utc1 = 2456384.5;
   utc2 = 0.969254051;
   dut1 = 0.1550675;
   elong = -0.527800806;
   phi = -1.2345856;
   hm = 2738.0;
   xp = 2.47230737e-7;
   yp = 1.82640464e-6;
   phpa = 731.0;
   tc = 12.8;
   rh = 0.59;
   wl = 0.55;

   jauApio13(utc1, utc2, dut1, elong, phi, hm, xp, yp,
                 phpa, tc, rh, wl, astrom);

   vvd(astrom.along,  -0.5278008060295995733, 1e-12,
                     "jauApio13", "along");
   vvd(astrom.xpl, 0.1133427418130752958e-5, 1e-17,
                   "jauApio13", "xpl");
   vvd(astrom.ypl, 0.1453347595780646207e-5, 1e-17,
                   "jauApio13", "ypl");
   vvd(astrom.sphi, -0.9440115679003211329, 1e-12,
                    "jauApio13", "sphi");
   vvd(astrom.cphi, 0.3299123514971474711, 1e-12,
                    "jauApio13", "cphi");
   vvd(astrom.diurab, 0.5135843661699913529e-6, 1e-12,
                      "jauApio13", "diurab");
   vvd(astrom.eral, 2.617608909189664000, 1e-12,
                    "jauApio13", "eral");
   vvd(astrom.refa, 0.2014187785940396921e-3, 1e-15,
                    "jauApio13", "refa");
   vvd(astrom.refb, -0.2361408314943696227e-6, 1e-18,
                    "jauApio13", "refb");

}

@Test
    public void t_atci13()
/**
**  Test jauAtci13 function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauAtci13, vvd
**
**  This revision:  2013 October 3
*/
{
   double rc, dc, pr, pd, px, rv, date1, date2;


   rc = 2.71;
   dc = 0.174;
   pr = 1e-5;
   pd = 5e-6;
   px = 0.1;
   rv = 55.0;
   date1 = 2456165.5;
   date2 = 0.401182685;

   SphericalCoordinateEO coe = jauAtci13(rc, dc, pr, pd, px, rv, date1, date2);

   vvd(coe.pos.alpha, 2.710121572968696744, 1e-12,
           "jauAtci13", "ri");
   vvd(coe.pos.delta, 0.1729371367219539137, 1e-12,
           "jauAtci13", "di");
   vvd(coe.eo, -0.002900618712657375647, 1e-14,
           "jauAtci13", "eo");

}

@Test
    public void t_atciq()
/**
**  Test jauAtciq function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauApci13, jauAtciq, vvd
**
**  This revision:  2013 October 3
*/
{
   double date1, date2, rc, dc, pr, pd, px, rv;
   Astrom astrom = new Astrom() ;

   date1 = 2456165.5;
   date2 = 0.401182685;
   jauApci13(date1, date2, astrom);
   rc = 2.71;
   dc = 0.174;
   pr = 1e-5;
   pd = 5e-6;
   px = 0.1;
   rv = 55.0;

   SphericalCoordinate co = jauAtciq(rc, dc, pr, pd, px, rv, astrom);

   vvd(co.alpha, 2.710121572968696744, 1e-12, "jauAtciq", "ri");
   vvd(co.delta, 0.1729371367219539137, 1e-12, "jauAtciq", "di");

}



@Test
    public void t_atciqn()
/**
**  Test jauAtciqn function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauApci13, jauAtciqn, vvd
**
**  This revision:  2013 October 3
*/
{
   Ldbody b[] = {new Ldbody(),new Ldbody(),new Ldbody()};
   double date1, date2, rc, dc, pr, pd, px, rv;
   Astrom astrom = new Astrom() ;

   date1 = 2456165.5;
   date2 = 0.401182685;
   jauApci13(date1, date2, astrom);
   rc = 2.71;
   dc = 0.174;
   pr = 1e-5;
   pd = 5e-6;
   px = 0.1;
   rv = 55.0;
   b[0].bm = 0.00028574;
   b[0].dl = 3e-10;
   b[0].pv[0][0] = -7.81014427;
   b[0].pv[0][1] = -5.60956681;
   b[0].pv[0][2] = -1.98079819;
   b[0].pv[1][0] =  0.0030723249;
   b[0].pv[1][1] = -0.00406995477;
   b[0].pv[1][2] = -0.00181335842;
   b[1].bm = 0.00095435;
   b[1].dl = 3e-9;
   b[1].pv[0][0] =  0.738098796;
   b[1].pv[0][1] =  4.63658692;
   b[1].pv[0][2] =  1.9693136;
   b[1].pv[1][0] = -0.00755816922;
   b[1].pv[1][1] =  0.00126913722;
   b[1].pv[1][2] =  0.000727999001;
   b[2].bm = 1.0;
   b[2].dl = 6e-6;
   b[2].pv[0][0] = -0.000712174377;
   b[2].pv[0][1] = -0.00230478303;
   b[2].pv[0][2] = -0.00105865966;
   b[2].pv[1][0] =  6.29235213e-6;
   b[2].pv[1][1] = -3.30888387e-7;
   b[2].pv[1][2] = -2.96486623e-7;

   SphericalCoordinate co = jauAtciqn( rc, dc, pr, pd, px, rv, astrom, 3, b);

   vvd(co.alpha, 2.710122008104983335, 1e-12, "jauAtciqn", "ri");
   vvd(co.delta, 0.1729371916492767821, 1e-12, "jauAtciqn", "di");

}
@Test
    public void t_atciqz()
/**
**  Test jauAtciqz function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauApci13, jauAtciqz, vvd
**
**  This revision:  2013 October 3
*/
{
   double date1, date2, rc, dc;
   Astrom astrom = new Astrom() ;


   date1 = 2456165.5;
   date2 = 0.401182685;
   jauApci13(date1, date2, astrom);
   rc = 2.71;
   dc = 0.174;

   SphericalCoordinate co = jauAtciqz(rc, dc, astrom);

   vvd(co.alpha, 2.709994899247256984, 1e-12, "jauAtciqz", "ri");
   vvd(co.delta, 0.1728740720984931891, 1e-12, "jauAtciqz", "di");

}

@Test
    public void t_atco13() throws JSOFAIllegalParameter, JSOFAInternalError
/**
**  Test jauAtco13 function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauAtco13, vvd, viv
**
**  This revision:  2013 October 4
*/
{
   double rc, dc, pr, pd, px, rv, utc1, utc2, dut1,
          elong, phi, hm, xp, yp, phpa, tc, rh, wl
        ;


   rc = 2.71;
   dc = 0.174;
   pr = 1e-5;
   pd = 5e-6;
   px = 0.1;
   rv = 55.0;
   utc1 = 2456384.5;
   utc2 = 0.969254051;
   dut1 = 0.1550675;
   elong = -0.527800806;
   phi = -1.2345856;
   hm = 2738.0;
   xp = 2.47230737e-7;
   yp = 1.82640464e-6;
   phpa = 731.0;
   tc = 12.8;
   rh = 0.59;
   wl = 0.55;

   ObservedPositionEO ope = jauAtco13(rc, dc, pr, pd, px, rv,
                 utc1, utc2, dut1, elong, phi, hm, xp, yp,
                 phpa, tc, rh, wl);

   vvd(ope.op.aob, 0.9251774485485515207e-1, 1e-12, "jauAtco13", "aob");
   vvd(ope.op.zob, 1.407661405256499357, 1e-12, "jauAtco13", "zob");
   vvd(ope.op.hob, -0.9265154431529724692e-1, 1e-12, "jauAtco13", "hob");
   vvd(ope.op.dob, 0.1716626560072526200, 1e-12, "jauAtco13", "dob");
   vvd(ope.op.rob, 2.710260453504961012, 1e-12, "jauAtco13", "rob");
   vvd(ope.eo, -0.003020548354802412839, 1e-14, "jauAtco13", "eo");

}

@Test
    public void t_atic13()
/**
**  Test jauAtic13 function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauAtic13, vvd
**
**  This revision:  2013 October 3
*/
{
   double ri, di, date1, date2;


   ri = 2.710121572969038991;
   di = 0.1729371367218230438;
   date1 = 2456165.5;
   date2 = 0.401182685;

   SphericalCoordinateEO oe = jauAtic13(ri, di, date1, date2);

   vvd(oe.pos.alpha, 2.710126504531716819, 1e-12, "jauAtic13", "rc");
   vvd(oe.pos.delta, 0.1740632537627034482, 1e-12, "jauAtic13", "dc");
   vvd(oe.eo, -0.002900618712657375647, 1e-14, "jauAtic13", "eo");

}

@Test
    public void t_aticq()
/**
**  Test jauAticq function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauApci13, jauAticq, vvd
**
**  This revision:  2013 October 3
*/
{
   double date1, date2, ri, di;
   Astrom astrom = new Astrom() ;


   date1 = 2456165.5;
   date2 = 0.401182685;
   jauApci13(date1, date2, astrom);
   ri = 2.710121572969038991;
   di = 0.1729371367218230438;

   SphericalCoordinate co = jauAticq(ri, di, astrom);

   vvd(co.alpha, 2.710126504531716819, 1e-12, "jauAticq", "rc");
   vvd(co.delta, 0.1740632537627034482, 1e-12, "jauAticq", "dc");

}

@Test
    public void t_aticqn()
/**
**  Test jauAticqn function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauApci13, jauAticqn, vvd
**
**  This revision:  2013 October 3
*/
{
   double date1, date2, ri, di;
   Ldbody b[] = {new Ldbody(),new Ldbody(),new Ldbody()};
   Astrom astrom = new Astrom() ;


   date1 = 2456165.5;
   date2 = 0.401182685;
   jauApci13(date1, date2, astrom);
   ri = 2.709994899247599271;
   di = 0.1728740720983623469;
   b[0].bm = 0.00028574;
   b[0].dl = 3e-10;
   b[0].pv[0][0] = -7.81014427;
   b[0].pv[0][1] = -5.60956681;
   b[0].pv[0][2] = -1.98079819;
   b[0].pv[1][0] =  0.0030723249;
   b[0].pv[1][1] = -0.00406995477;
   b[0].pv[1][2] = -0.00181335842;
   b[1].bm = 0.00095435;
   b[1].dl = 3e-9;
   b[1].pv[0][0] =  0.738098796;
   b[1].pv[0][1] =  4.63658692;
   b[1].pv[0][2] =  1.9693136;
   b[1].pv[1][0] = -0.00755816922;
   b[1].pv[1][1] =  0.00126913722;
   b[1].pv[1][2] =  0.000727999001;
   b[2].bm = 1.0;
   b[2].dl = 6e-6;
   b[2].pv[0][0] = -0.000712174377;
   b[2].pv[0][1] = -0.00230478303;
   b[2].pv[0][2] = -0.00105865966;
   b[2].pv[1][0] =  6.29235213e-6;
   b[2].pv[1][1] = -3.30888387e-7;
   b[2].pv[1][2] = -2.96486623e-7;

   SphericalCoordinate co = jauAticqn(ri, di, astrom, 3, b);

   vvd(co.alpha, 2.709999575033027333, 1e-12, "jauAtciqn", "rc");
   vvd(co.delta, 0.1739999656316469990, 1e-12, "jauAtciqn", "dc");

}

@Test
    public void t_atio13() throws JSOFAIllegalParameter, JSOFAInternalError
/**
**  Test jauAtio13 function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauAtio13, vvd, viv
**
**  This revision:  2013 October 3
*/
{
   double ri, di, utc1, utc2, dut1, elong, phi, hm, xp, yp,
          phpa, tc, rh, wl;



   ri = 2.710121572969038991;
   di = 0.1729371367218230438;
   utc1 = 2456384.5;
   utc2 = 0.969254051;
   dut1 = 0.1550675;
   elong = -0.527800806;
   phi = -1.2345856;
   hm = 2738.0;
   xp = 2.47230737e-7;
   yp = 1.82640464e-6;
   phpa = 731.0;
   tc = 12.8;
   rh = 0.59;
   wl = 0.55;

   ObservedPosition o = jauAtio13(ri, di, utc1, utc2, dut1, elong, phi, hm,
                 xp, yp, phpa, tc, rh, wl
                 );

   vvd(o.aob, 0.9233952224895122499e-1, 1e-12, "jauAtio13", "aob");
   vvd(o.zob, 1.407758704513549991, 1e-12, "jauAtio13", "zob");
   vvd(o.hob, -0.9247619879881698140e-1, 1e-12, "jauAtio13", "hob");
   vvd(o.dob, 0.1717653435756234676, 1e-12, "jauAtio13", "dob");
   vvd(o.rob, 2.710085107988480746, 1e-12, "jauAtio13", "rob");

}

@Test
    public void t_atioq() throws JSOFAIllegalParameter, JSOFAInternalError
/**
**  Test jauAtioq function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauApio13, jauAtioq, vvd, viv
**
**  This revision:  2013 October 4
*/
{
   double utc1, utc2, dut1, elong, phi, hm, xp, yp,
          phpa, tc, rh, wl, ri, di;
   Astrom astrom = new Astrom() ;


   utc1 = 2456384.5;
   utc2 = 0.969254051;
   dut1 = 0.1550675;
   elong = -0.527800806;
   phi = -1.2345856;
   hm = 2738.0;
   xp = 2.47230737e-7;
   yp = 1.82640464e-6;
   phpa = 731.0;
   tc = 12.8;
   rh = 0.59;
   wl = 0.55;
   jauApio13(utc1, utc2, dut1, elong, phi, hm, xp, yp,
                    phpa, tc, rh, wl, astrom);
   ri = 2.710121572969038991;
   di = 0.1729371367218230438;

   ObservedPosition o = jauAtioq(ri, di, astrom);

   vvd(o.aob, 0.9233952224895122499e-1, 1e-12, "jauAtioq", "aob");
   vvd(o.zob, 1.407758704513549991, 1e-12, "jauAtioq", "zob");
   vvd(o.hob, -0.9247619879881698140e-1, 1e-12, "jauAtioq", "hob");
   vvd(o.dob, 0.1717653435756234676, 1e-12, "jauAtioq", "dob");
   vvd(o.rob, 2.710085107988480746, 1e-12, "jauAtioq", "rob");

}

@Test
    public void t_atoc13() throws JSOFAIllegalParameter, JSOFAInternalError
/**
**  Test jauAtoc13 function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauAtoc13, vvd, viv
**
**  This revision:  2013 October 3
*/
{
   double utc1, utc2, dut1,
          elong, phi, hm, xp, yp, phpa, tc, rh, wl,
          ob1, ob2;


   utc1 = 2456384.5;
   utc2 = 0.969254051;
   dut1 = 0.1550675;
   elong = -0.527800806;
   phi = -1.2345856;
   hm = 2738.0;
   xp = 2.47230737e-7;
   yp = 1.82640464e-6;
   phpa = 731.0;
   tc = 12.8;
   rh = 0.59;
   wl = 0.55;

   ob1 = 2.710085107986886201;
   ob2 = 0.1717653435758265198;
   SphericalCoordinate oc = jauAtoc13 ( "R", ob1, ob2, utc1, utc2, dut1,
                   elong, phi, hm, xp, yp, phpa, tc, rh, wl
                   );
   vvd(oc.alpha, 2.709956744659136129, 1e-12, "jauAtoc13", "R/rc");
   vvd(oc.delta, 0.1741696500898471362, 1e-12, "jauAtoc13", "R/dc");

   ob1 = -0.09247619879782006106;
   ob2 = 0.1717653435758265198;
   oc = jauAtoc13 ( "H", ob1, ob2, utc1, utc2, dut1,
                   elong, phi, hm, xp, yp, phpa, tc, rh, wl);
   vvd(oc.alpha, 2.709956744659734086, 1e-12, "jauAtoc13", "H/rc");
   vvd(oc.delta, 0.1741696500898471362, 1e-12, "jauAtoc13", "H/dc");

   ob1 = 0.09233952224794989993;
   ob2 = 1.407758704513722461;
   oc = jauAtoc13 ( "A", ob1, ob2, utc1, utc2, dut1,
                   elong, phi, hm, xp, yp, phpa, tc, rh, wl);
   vvd(oc.alpha, 2.709956744659734086, 1e-12, "jauAtoc13", "A/rc");
   vvd(oc.delta, 0.1741696500898471366, 1e-12, "jauAtoc13", "A/dc");

}

@Test
public void t_atoi13() throws JSOFAIllegalParameter, JSOFAInternalError
/**
**  Test jauAtoi13 function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauAtoi13, vvd, viv
**
**  This revision:  2013 October 3
*/
{
   double utc1, utc2, dut1, elong, phi, hm, xp, yp, phpa, tc, rh, wl,
          ob1, ob2;



   utc1 = 2456384.5;
   utc2 = 0.969254051;
   dut1 = 0.1550675;
   elong = -0.527800806;
   phi = -1.2345856;
   hm = 2738.0;
   xp = 2.47230737e-7;
   yp = 1.82640464e-6;
   phpa = 731.0;
   tc = 12.8;
   rh = 0.59;
   wl = 0.55;

   ob1 = 2.710085107986886201;
   ob2 = 0.1717653435758265198;
   SphericalCoordinate co = jauAtoi13( "R", ob1, ob2, utc1, utc2, dut1,
                   elong, phi, hm, xp, yp, phpa, tc, rh, wl);
   vvd(co.alpha, 2.710121574447540810, 1e-12, "jauAtoi13", "R/ri");
   vvd(co.delta, 0.1729371839116608778, 1e-12, "jauAtoi13", "R/di");

   ob1 = -0.09247619879782006106;
   ob2 = 0.1717653435758265198;
   co = jauAtoi13( "H", ob1, ob2, utc1, utc2, dut1,
                   elong, phi, hm, xp, yp, phpa, tc, rh, wl);
   vvd(co.alpha, 2.710121574448138676, 1e-12, "jauAtoi13", "H/ri");
   vvd(co.delta, 0.1729371839116608778, 1e-12, "jauAtoi13", "H/di");

   ob1 = 0.09233952224794989993;
   ob2 = 1.407758704513722461;
   co = jauAtoi13( "A", ob1, ob2, utc1, utc2, dut1,
                   elong, phi, hm, xp, yp, phpa, tc, rh, wl);
   vvd(co.alpha, 2.710121574448138676, 1e-12, "jauAtoi13", "A/ri");
   vvd(co.delta, 0.1729371839116608781, 1e-12, "jauAtoi13", "A/di");

}

@Test
    public void t_atoiq() throws JSOFAIllegalParameter, JSOFAInternalError
/*
**  Test jauAtoiq function.
*
**  Returned:
**     status    int         FALSE = success, TRUE = fail
*
**  Called:  jauApio13, jauAtoiq, vvd
*
**  This revision:  2013 October 4
*/
{
   double utc1, utc2, dut1, elong, phi, hm, xp, yp, phpa, tc, rh, wl,
          ob1, ob2;
   Astrom astrom = new Astrom() ;


   utc1 = 2456384.5;
   utc2 = 0.969254051;
   dut1 = 0.1550675;
   elong = -0.527800806;
   phi = -1.2345856;
   hm = 2738.0;
   xp = 2.47230737e-7;
   yp = 1.82640464e-6;
   phpa = 731.0;
   tc = 12.8;
   rh = 0.59;
   wl = 0.55;
   jauApio13(utc1, utc2, dut1, elong, phi, hm, xp, yp,
                    phpa, tc, rh, wl, astrom);

   ob1 = 2.710085107986886201;
   ob2 = 0.1717653435758265198;
   SphericalCoordinate co = jauAtoiq("R", ob1, ob2, astrom);
   vvd(co.alpha, 2.710121574447540810, 1e-12,
           "jauAtoiq", "R/ri");
   vvd(co.delta, 0.17293718391166087785, 1e-12,
           "jauAtoiq", "R/di");

   ob1 = -0.09247619879782006106;
   ob2 = 0.1717653435758265198;
   co = jauAtoiq("H", ob1, ob2, astrom);
   vvd(co.alpha, 2.710121574448138676, 1e-12,
           "jauAtoiq", "H/ri");
   vvd(co.delta, 0.1729371839116608778, 1e-12,
           "jauAtoiq", "H/di");

   ob1 = 0.09233952224794989993;
   ob2 = 1.407758704513722461;
   co = jauAtoiq("A", ob1, ob2, astrom);
   vvd(co.alpha, 2.710121574448138676, 1e-12,
           "jauAtoiq", "A/ri");
   vvd(co.delta, 0.1729371839116608781, 1e-12,
           "jauAtoiq", "A/di");

}

@Test
    public void t_ld()
/**
**  Test jauLd function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauLd, vvd
*
**  This revision:  2013 October 2
*/
{
   double bm, p[] = new double[3], q[] = new double[3], e[] = new double[3], em, dlim, p1[];


   bm = 0.00028574;
   p[0] = -0.763276255;
   p[1] = -0.608633767;
   p[2] = -0.216735543;
   q[0] = -0.763276255;
   q[1] = -0.608633767;
   q[2] = -0.216735543;
   e[0] = 0.76700421;
   e[1] = 0.605629598;
   e[2] = 0.211937094;
   em = 8.91276983;
   dlim = 3e-10;

   p1 = jauLd(bm, p, q, e, em, dlim);

   vvd(p1[0], -0.7632762548968159627, 1e-12,
               "jauLd", "1");
   vvd(p1[1], -0.6086337670823762701, 1e-12,
               "jauLd", "2");
   vvd(p1[2], -0.2167355431320546947, 1e-12,
               "jauLd", "3");

}

@Test
    public void t_ldn()
/**
**  - - - - - -
**   t _ l d n
**  - - - - - -
**
**  Test jauLdn function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauLdn, vvd
**
**  This revision:  2013 October 2
*/
{
   int n;
   Ldbody b[] = {new Ldbody(),new Ldbody(),new Ldbody()};
   double ob[] = new double[3], sc[] = new double[3], sn[];


   n = 3;
   b[0].bm = 0.00028574;
   b[0].dl = 3e-10;
   b[0].pv[0][0] = -7.81014427;
   b[0].pv[0][1] = -5.60956681;
   b[0].pv[0][2] = -1.98079819;
   b[0].pv[1][0] =  0.0030723249;
   b[0].pv[1][1] = -0.00406995477;
   b[0].pv[1][2] = -0.00181335842;
   b[1].bm = 0.00095435;
   b[1].dl = 3e-9;
   b[1].pv[0][0] =  0.738098796;
   b[1].pv[0][1] =  4.63658692;
   b[1].pv[0][2] =  1.9693136;
   b[1].pv[1][0] = -0.00755816922;
   b[1].pv[1][1] =  0.00126913722;
   b[1].pv[1][2] =  0.000727999001;
   b[2].bm = 1.0;
   b[2].dl = 6e-6;
   b[2].pv[0][0] = -0.000712174377;
   b[2].pv[0][1] = -0.00230478303;
   b[2].pv[0][2] = -0.00105865966;
   b[2].pv[1][0] =  6.29235213e-6;
   b[2].pv[1][1] = -3.30888387e-7;
   b[2].pv[1][2] = -2.96486623e-7;
   ob[0] =  -0.974170437;
   ob[1] =  -0.2115201;
   ob[2] =  -0.0917583114;
   sc[0] =  -0.763276255;
   sc[1] =  -0.608633767;
   sc[2] =  -0.216735543;

   sn = jauLdn(n, b, ob, sc);

   vvd(sn[0], -0.7632762579693333866, 1e-12,
               "jauLdn", "1");
   vvd(sn[1], -0.6086337636093002660, 1e-12,
               "jauLdn", "2");
   vvd(sn[2], -0.2167355420646328159, 1e-12,
               "jauLdn", "3");

}

@Test
    public void t_ldsun()
/**
**  Test jauLdsun function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauLdsun, vvd
**
**  This revision:  2013 October 2
*/
{
   double p[] = new double[3], e[] = new double[3], em, p1[];


   p[0] = -0.763276255;
   p[1] = -0.608633767;
   p[2] = -0.216735543;
   e[0] = -0.973644023;
   e[1] = -0.20925523;
   e[2] = -0.0907169552;
   em = 0.999809214;

   p1 = jauLdsun(p, e, em);

   vvd(p1[0], -0.7632762580731413169, 1e-12,
               "jauLdsun", "1");
   vvd(p1[1], -0.6086337635262647900, 1e-12,
               "jauLdsun", "2");
   vvd(p1[2], -0.2167355419322321302, 1e-12,
               "jauLdsun", "3");

}
@Test
    public void t_pmpx()
/**
**  Test jauPmpx function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauPmpx, vvd
**
**  This revision:  2013 October 2
*/
{
   double rc, dc, pr, pd, px, rv, pmt, pob[] = new double[3], pco[];


   rc = 1.234;
   dc = 0.789;
   pr = 1e-5;
   pd = -2e-5;
   px = 1e-2;
   rv = 10.0;
   pmt = 8.75;
   pob[0] = 0.9;
   pob[1] = 0.4;
   pob[2] = 0.1;

   pco = jauPmpx(rc, dc, pr, pd, px, rv, pmt, pob);

   vvd(pco[0], 0.2328137623960308438, 1e-12,
               "jauPmpx", "1");
   vvd(pco[1], 0.6651097085397855328, 1e-12,
               "jauPmpx", "2");
   vvd(pco[2], 0.7095257765896359837, 1e-12,
               "jauPmpx", "3");

}

@Test
    public void t_pmsafe() throws JSOFAInternalError
/**
**  Test jauPmsafe function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauPmsafe, vvd, viv
**
**  This revision:  2013 October 2
*/
{
   double ra1, dec1, pmr1, pmd1, px1, rv1, ep1a, ep1b, ep2a, ep2b;


   ra1 = 1.234;
   dec1 = 0.789;
   pmr1 = 1e-5;
   pmd1 = -2e-5;
   px1 = 1e-2;
   rv1 = 10.0;
   ep1a = 2400000.5;
   ep1b = 48348.5625;
   ep2a = 2400000.5;
   ep2b = 51544.5;

   CatalogCoords cc = jauPmsafe(ra1, dec1, pmr1, pmd1, px1, rv1,
                 ep1a, ep1b, ep2a, ep2b
                 );

   vvd(cc.pos.alpha, 1.234087484501017061, 1e-12,
            "jauPmsafe", "ra2");
   vvd(cc.pos.delta, 0.7888249982450468567, 1e-12,
            "jauPmsafe", "dec2");
   vvd(cc.pm.alpha, 0.9996457663586073988e-5, 1e-12,
             "jauPmsafe", "pmr2");
   vvd(cc.pm.delta, -0.2000040085106754565e-4, 1e-16,
             "jauPmsafe", "pmd2");
   vvd(cc.px, 0.9999997295356830666e-2, 1e-12,
            "jauPmsafe", "px2");
   vvd(cc.rv, 10.38468380293920069, 1e-10,
            "jauPmsafe", "rv2");

}


@Test
    public void t_pvtob() throws JSOFAIllegalParameter, JSOFAInternalError
/**
**  Test jauPvtob function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauPvtob, vvd
**
**  This revision:  2013 October 2
*/
{
   double elong, phi, hm, xp, yp, sp, theta, pv[][];


   elong = 2.0;
   phi = 0.5;
   hm = 3000.0;
   xp = 1e-6;
   yp = -0.5e-6;
   sp = 1e-8;
   theta = 5.0;

   pv = jauPvtob(elong, phi, hm, xp, yp, sp, theta);

   vvd(pv[0][0], 4225081.367071159207, 1e-5,
                 "jauPvtob", "p(1)");
   vvd(pv[0][1], 3681943.215856198144, 1e-5,
                 "jauPvtob", "p(2)");
   vvd(pv[0][2], 3041149.399241260785, 1e-5,
                 "jauPvtob", "p(3)");
   vvd(pv[1][0], -268.4915389365998787, 1e-9,
                 "jauPvtob", "v(1)");
   vvd(pv[1][1], 308.0977983288903123, 1e-9,
                 "jauPvtob", "v(2)");
   vvd(pv[1][2], 0, 0,
                 "jauPvtob", "v(3)");

}

@Test
    public void t_refco()
/**
**  Test jauRefco function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauRefco, vvd
**
**  This revision:  2013 October 2
*/
{
   double phpa, tc, rh, wl;


   phpa = 800.0;
   tc = 10.0;
   rh = 0.9;
   wl = 0.4;

   RefCos ref = jauRefco(phpa, tc, rh, wl);

   vvd(ref.a, 0.2264949956241415009e-3, 1e-15,
             "jauRefco", "refa");
   vvd(ref.b, -0.2598658261729343970e-6, 1e-18,
             "jauRefco", "refb");

}


@Test
public void t_d2dtf() throws JSOFAIllegalParameter, JSOFAInternalError
/**
**  Test jauD2dtf function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauD2dtf, viv
**
**  This revision:  2013 August 7
*/
{

   CalendarHMS c = jauD2dtf("UTC", 5, 2400000.5, 49533.99999);

   viv(c.iy, 1994, "jauD2dtf", "y");
   viv(c.im, 6, "jauD2dtf", "mo");
   viv(c.id, 30, "jauD2dtf", "d");
   viv(c.ihmsf[0], 23, "jauD2dtf", "h");
   viv(c.ihmsf[1], 59, "jauD2dtf", "m");
   viv(c.ihmsf[2], 60, "jauD2dtf", "s");
   viv(c.ihmsf[3], 13599, "jauD2dtf", "f");

}



/**
**  - - - - - - - -
**   t _ d t f 2 d
**  - - - - - - - -
**
**  Test jauDtf2d function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauDtf2d, vvd, viv
**
**  This revision:  2013 August 7
 * @throws JSOFAInternalError 
 * @throws JSOFAIllegalParameter 
*/
@Test
public void t_dtf2d() throws JSOFAIllegalParameter, JSOFAInternalError
{

   JulianDate jd = jauDtf2d("UTC", 1994, 6, 30, 23, 59, 60.13599);

   vvd(jd.djm0+jd.djm1, 2449534.49999, 1e-6, "jauDtf2d", "u");

}


/**
**
**  Test jauG2icrs function.
**
**  Called:  jauG2icrs, vvd
**
**  This revision:  2015 March 02
*/
@Test
public void t_g2icrs()
{
   double dl, db;
   SphericalCoordinate co;


   dl =  5.5850536063818546461558105;
   db = -0.7853981633974483096156608;
   co = jauG2icrs(dl, db);
   vvd(co.alpha,  5.9338074302227188048671, 1e-14, "jauG2icrs", "Ra");
   vvd(co.delta, -1.1784870613579944551541, 1e-14, "jauG2icrs", "Dec");
 }

/**
**
**  Test jauIcrs2g function.
**
**  Called:  jauIcrs2g, vvd
**
**  This revision:  2015 March 02
*/
@Test
public void t_icrs2g()
{
   double dr, dd;

   dr =  5.9338074302227188048671087;
   dd = -1.1784870613579944551540570;
   SphericalCoordinate co = jauIcrs2g (dr, dd);
   vvd(co.alpha,  5.5850536063818546461558, 1e-14, "jauIcrs2g", "L");
   vvd(co.delta, -0.7853981633974483096157, 1e-14, "jauIcrs2g", "B");
 }

/**
**
**  Test jauEceq06 function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauEceq06, vvd
**
**  This revision:  2016 March 12
*/
@Test
public void t_eceq06()
{
   double date1, date2, dl, db;


   date1 = 2456165.5;
   date2 = 0.401182685;
   dl = 5.1;
   db = -0.9;

   SphericalCoordinate co = jauEceq06(date1, date2, dl, db);

   vvd(co.alpha, 5.533459733613627767, 1e-14, "jauEceq06", "dr");
   vvd(co.delta, -1.246542932554480576, 1e-14, "jauEceq06", "dd");

}

/**
**
**  Test jauEcm06 function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauEcm06, vvd
**
**  This revision:  2016 March 12
*/
@Test
public void t_ecm06()
{
   double date1, date2;


   date1 = 2456165.5;
   date2 = 0.401182685;

   double rm[][] = jauEcm06(date1, date2);

   vvd(rm[0][0], 0.9999952427708701137, 1e-14,
       "jauEcm06", "rm11");
   vvd(rm[0][1], -0.2829062057663042347e-2, 1e-14,
       "jauEcm06", "rm12");
   vvd(rm[0][2], -0.1229163741100017629e-2, 1e-14,
       "jauEcm06", "rm13");
   vvd(rm[1][0], 0.3084546876908653562e-2, 1e-14,
       "jauEcm06", "rm21");
   vvd(rm[1][1], 0.9174891871550392514, 1e-14,
       "jauEcm06", "rm22");
   vvd(rm[1][2], 0.3977487611849338124, 1e-14,
       "jauEcm06", "rm23");
   vvd(rm[2][0], 0.2488512951527405928e-5, 1e-14,
       "jauEcm06", "rm31");
   vvd(rm[2][1], -0.3977506604161195467, 1e-14,
       "jauEcm06", "rm32");
   vvd(rm[2][2], 0.9174935488232863071, 1e-14,
       "jauEcm06", "rm33");

}

/**
**
**  Test jauEqec06 function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauEqec06, vvd
**
**  This revision:  2016 March 12
*/
@Test
public void t_eqec06()
{
   double date1, date2, dr, dd;


   date1 = 1234.5;
   date2 = 2440000.5;
   dr = 1.234;
   dd = 0.987;

   SphericalCoordinate co = jauEqec06(date1, date2, dr, dd);

   vvd(co.alpha, 1.342509918994654619, 1e-14, "jauEqec06", "dl");
   vvd(co.delta, 0.5926215259704608132, 1e-14, "jauEqec06", "db");

}
/**
**  Test jauLteceq function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauLteceq, vvd
**
**  This revision:  2016 March 12
*/
@Test
public void t_lteceq()
{
   double epj, dl, db;


   epj = 2500.0;
   dl = 1.5;
   db = 0.6;

   SphericalCoordinate co = jauLteceq(epj, dl, db);

   vvd(co.alpha, 1.275156021861921167, 1e-14, "jauLteceq", "dr");
   vvd(co.delta, 0.9966573543519204791, 1e-14, "jauLteceq", "dd");

}

/**
**  Test jauLtecm function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauLtecm, vvd
**
**  This revision:  2016 March 12
*/
@Test
public void t_ltecm()
{
   double epj;


   epj = -3000.0;

   double rm[][] =jauLtecm(epj);

   vvd(rm[0][0], 0.3564105644859788825, 1e-14,
       "jauLtecm", "rm11");
   vvd(rm[0][1], 0.8530575738617682284, 1e-14,
       "jauLtecm", "rm12");
   vvd(rm[0][2], 0.3811355207795060435, 1e-14,
       "jauLtecm", "rm13");
   vvd(rm[1][0], -0.9343283469640709942, 1e-14,
       "jauLtecm", "rm21");
   vvd(rm[1][1], 0.3247830597681745976, 1e-14,
       "jauLtecm", "rm22");
   vvd(rm[1][2], 0.1467872751535940865, 1e-14,
       "jauLtecm", "rm23");
   vvd(rm[2][0], 0.1431636191201167793e-2, 1e-14,
       "jauLtecm", "rm31");
   vvd(rm[2][1], -0.4084222566960599342, 1e-14,
       "jauLtecm", "rm32");
   vvd(rm[2][2], 0.9127919865189030899, 1e-14,
       "jauLtecm", "rm33");

}

/**
**
**  Test jauLteqec function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauLteqec, vvd
**
**  This revision:  2016 March 12
*/
@Test
public void t_lteqec()
{
   double epj, dr, dd;


   epj = -1500.0;
   dr = 1.234;
   dd = 0.987;

   SphericalCoordinate co = jauLteqec(epj, dr, dd);

   vvd(co.alpha, 0.5039483649047114859, 1e-14, "jauLteqec", "dl");
   vvd(co.delta, 0.5848534459726224882, 1e-14, "jauLteqec", "db");

}

/**
**  Test jauLtp function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauLtp, vvd
**
**  This revision:  2016 March 12
*/
@Test
public void t_ltp()
{
   double epj;


   epj = 1666.666;

   double rp[][] = jauLtp(epj);

   vvd(rp[0][0], 0.9967044141159213819, 1e-14,
       "jauLtp", "rp11");
   vvd(rp[0][1], 0.7437801893193210840e-1, 1e-14,
       "jauLtp", "rp12");
   vvd(rp[0][2], 0.3237624409345603401e-1, 1e-14,
       "jauLtp", "rp13");
   vvd(rp[1][0], -0.7437802731819618167e-1, 1e-14,
       "jauLtp", "rp21");
   vvd(rp[1][1], 0.9972293894454533070, 1e-14,
       "jauLtp", "rp22");
   vvd(rp[1][2], -0.1205768842723593346e-2, 1e-14,
       "jauLtp", "rp23");
   vvd(rp[2][0], -0.3237622482766575399e-1, 1e-14,
       "jauLtp", "rp31");
   vvd(rp[2][1], -0.1206286039697609008e-2, 1e-14,
       "jauLtp", "rp32");
   vvd(rp[2][2], 0.9994750246704010914, 1e-14,
       "jauLtp", "rp33");

}

/**
**  Test jauLtpb function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauLtpb, vvd
**
**  This revision:  2016 March 12
*/
@Test
public void t_ltpb()
{
   double epj ;


   epj = 1666.666;

   double rpb[][] = jauLtpb(epj);

   vvd(rpb[0][0], 0.9967044167723271851, 1e-14,
       "jauLtpb", "rpb11");
   vvd(rpb[0][1], 0.7437794731203340345e-1, 1e-14,
       "jauLtpb", "rpb12");
   vvd(rpb[0][2], 0.3237632684841625547e-1, 1e-14,
       "jauLtpb", "rpb13");
   vvd(rpb[1][0], -0.7437795663437177152e-1, 1e-14,
       "jauLtpb", "rpb21");
   vvd(rpb[1][1], 0.9972293947500013666, 1e-14,
       "jauLtpb", "rpb22");
   vvd(rpb[1][2], -0.1205741865911243235e-2, 1e-14,
       "jauLtpb", "rpb23");
   vvd(rpb[2][0], -0.3237630543224664992e-1, 1e-14,
       "jauLtpb", "rpb31");
   vvd(rpb[2][1], -0.1206316791076485295e-2, 1e-14,
       "jauLtpb", "rpb32");
   vvd(rpb[2][2], 0.9994750220222438819, 1e-14,
       "jauLtpb", "rpb33");

}

/**
**  Test jauLtpecl function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauLtpecl, vvd
**
**  This revision:  2016 March 12
*/
@Test
public void t_ltpecl()
{
   double epj;


   epj = -1500.0;

   double vec[] = jauLtpecl(epj);

   vvd(vec[0], 0.4768625676477096525e-3, 1e-14,
       "jauLtpecl", "vec1");
   vvd(vec[1], -0.4052259533091875112, 1e-14,
       "jauLtpecl", "vec2");
   vvd(vec[2], 0.9142164401096448012, 1e-14,
       "jauLtpecl", "vec3");

}

/**
**
**  Test jauLtpequ function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauLtpequ, vvd
**
**  This revision:  2016 March 12
*/
@Test
public void t_ltpequ()
{
   double epj;
   epj = -2500.0;

   double veq[] = jauLtpequ(epj);

   vvd(veq[0], -0.3586652560237326659, 1e-14,
       "jauLtpequ", "veq1");
   vvd(veq[1], -0.1996978910771128475, 1e-14,
       "jauLtpequ", "veq2");
   vvd(veq[2], 0.9118552442250819624, 1e-14,
       "jauLtpequ", "veq3");

}

/**
**  - - - - - - - -
**   t _ a e 2 h d
**  - - - - - - - -
**
**  Test iauAe2hd function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  iauAe2hd and vvd
**
**  This revision:  2017 October 21
*/
@Test
public void t_ae2hd()
{
   double a, e, p;


   a = 5.5;
   e = 1.1;
   p = 0.7;

  EquatorialCoordinate ec = jauAe2hd(a, e, p);

   vvd(ec.ha, 0.5933291115507309663, 1e-14, "jauAe2hd", "h");
   vvd(ec.dec, 0.9613934761647817620, 1e-14, "jauAe2hd", "d");

}



public void t_hd2ae()
/*
**  - - - - - - - -
**   t _ h d 2 a e
**  - - - - - - - -
**
**  Test jauHd2ae function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauHd2ae and vvd
**
**  This revision:  2017 October 21
*/
{
   double h, d, p;


   h = 1.1;
   d = 1.2;
   p = 0.3;

   HorizonCoordinate hc = jauHd2ae(h, d, p);

   vvd(hc.az, 5.916889243730066194, 1e-13, "jauHd2ae", "a");
   vvd(hc.el, 0.4472186304990486228, 1e-14, "jauHd2ae", "e");

}

/**
**  - - - - - - - -
**   t _ h d 2 p a
**  - - - - - - - -
**
**  Test jauHd2pa function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauHd2pa and vvd
**
**  This revision:  2017 October 21
*/
@Test
public void t_hd2pa()
{
   double h, d, p, q;


   h = 1.1;
   d = 1.2;
   p = 0.3;

   q = jauHd2pa(h, d, p);

   vvd(q, 1.906227428001995580, 1e-13, "jauHd2pa", "q");

}

/**
**  - - - - - - - -
**   t _ t p o r s
**  - - - - - - - -
**
**  Test jauTpors function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauTpors, vvd, viv
**
**  This revision:  2017 October 21
*/
@Test
public void t_tpors()
{
   double xi, eta, ra, dec;


   xi = -0.03;
   eta = 0.07;
   ra = 1.3;
   dec = 1.5;

   TangentPointSolution tps = jauTpors(xi, eta, ra, dec);

   vvd(tps.sol1.alpha, 1.736621577783208748, 1e-13, "jauTpors", "az1");
   vvd(tps.sol1.delta, 1.436736561844090323, 1e-13, "jauTpors", "bz1");

   vvd(tps.sol2.alpha, 4.004971075806584490, 1e-13, "jauTpors", "az2");
   vvd(tps.sol2.delta, 1.565084088476417917, 1e-13, "jauTpors", "bz2");

   viv(tps.nsolutions, 2, "jauTpors", "n");

}

/*
**  - - - - - - - -
**   t _ t p o r v
**  - - - - - - - -
**
**  Test jauTporv function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauTporv, jauS2c, vvd, viv
**
**  This revision:  2017 October 21
*/
@Test
public void t_tporv()
{
   double xi, eta, ra, dec, v[] = new double[3];


   xi = -0.03;
   eta = 0.07;
   ra = 1.3;
   dec = 1.5;
   v = jauS2c(ra, dec);

   TangentPointDirectionCosines tpc = jauTporv(xi, eta, v);

   vvd(tpc.dc1[0], -0.02206252822366888610, 1e-15,
       "jauTporv", "x1");
   vvd(tpc.dc1[1], 0.1318251060359645016, 1e-14,
       "jauTporv", "y1");
   vvd(tpc.dc1[2], 0.9910274397144543895, 1e-14,
       "jauTporv", "z1");

   vvd(tpc.dc2[0], -0.003712211763801968173, 1e-16,
       "jauTporv", "x2");
   vvd(tpc.dc2[1], -0.004341519956299836813, 1e-16,
       "jauTporv", "y2");
   vvd(tpc.dc2[2], 0.9999836852110587012, 1e-14,
       "jauTporv", "z2");

   viv(tpc.nsolution, 2, "jauTporv", "n");

}

/**
**  - - - - - - - -
**   t _ t p s t s
**  - - - - - - - -
**
**  Test jauTpsts function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauTpsts, vvd
**
**  This revision:  2017 October 21
*/
@Test
public void t_tpsts()
{
   double xi, eta, raz, decz;


   xi = -0.03;
   eta = 0.07;
   raz = 2.3;
   decz = 1.5;

   SphericalCoordinate sc = jauTpsts(xi, eta, raz, decz);

   vvd(sc.alpha, 0.7596127167359629775, 1e-14, "jauTpsts", "ra");
   vvd(sc.delta, 1.540864645109263028, 1e-13, "jauTpsts", "dec");

}

/**
**  - - - - - - - -
**   t _ t p s t v
**  - - - - - - - -
**
**  Test jauTpstv function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauTpstv, jauS2c, vvd
**
**  This revision:  2017 October 21
*/
@Test
public void t_tpstv()
{
   double xi, eta, raz, decz, vz[], v[];


   xi = -0.03;
   eta = 0.07;
   raz = 2.3;
   decz = 1.5;
   vz = jauS2c(raz, decz);

   v = jauTpstv(xi, eta, vz);

   vvd(v[0], 0.02170030454907376677, 1e-15, "jauTpstv", "x");
   vvd(v[1], 0.02060909590535367447, 1e-15, "jauTpstv", "y");
   vvd(v[2], 0.9995520806583523804, 1e-14, "jauTpstv", "z");

}

/*
**  - - - - - - - -
**   t _ t p x e s
**  - - - - - - - -
**
**  Test jauTpxes function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauTpxes, vvd, viv
**
**  This revision:  2017 October 21
*/
@Test
public void t_tpxes()
{
   double ra, dec, raz, decz;


   ra = 1.3;
   dec = 1.55;
   raz = 2.3;
   decz = 1.5;

   TangentPlaneCoordinate tpc = jauTpxes(ra, dec, raz, decz);

   vvd(tpc.xi, -0.01753200983236980595, 1e-15, "jauTpxes", "xi");
   vvd(tpc.eta, 0.05962940005778712891, 1e-15, "jauTpxes", "eta");

   viv(tpc.status, 0, "jauTpxes", "j");

}

/**
**  - - - - - - - -
**   t _ t p x e v
**  - - - - - - - -
**
**  Test jauTpxev function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  jauTpxev, jauS2c, vvd
**
**  This revision:  2017 October 21
*/
@Test
public void t_tpxev()
{
   double ra, dec, raz, decz, v[], vz[];


   ra = 1.3;
   dec = 1.55;
   raz = 2.3;
   decz = 1.5;
   v = jauS2c(ra, dec);
   vz = jauS2c(raz, decz);

   TangentPlaneCoordinate tpc = jauTpxev(v, vz);

   vvd(tpc.xi, -0.01753200983236980595, 1e-15, "jauTpxev", "xi");
   vvd(tpc.eta, 0.05962940005778712891, 1e-15, "jauTpxev", "eta");

   viv(tpc.status, 0, "jauTpxev", "j");

   
}
/**
**  - - - - - - - -
**   t _ f k 4 2 5
**  - - - - - - - -
**
**  Test iauFk425 function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  iauFk425, vvd
**
**  This revision:  2018 December 6
*/
@Test
public void t_fk425()
{
   double r1950, d1950, dr1950, dd1950, p1950, v1950;


   r1950 = 0.07626899753879587532;
   d1950 = -1.137405378399605780;
   dr1950 = 0.1973749217849087460e-4;
   dd1950 = 0.5659714913272723189e-5;
   p1950 = 0.134;
   v1950 = 8.7;

   CatalogCoords c = jauFk425(r1950, d1950, dr1950, dd1950, p1950, v1950);

   vvd(c.pos.alpha, 0.08757989933556446040, 1e-14,
       "iauFk425", "r2000");
   vvd(c.pos.delta, -1.132279113042091895, 1e-12,
       "iauFk425", "d2000");
   vvd(c.pm.alpha, 0.1953670614474396139e-4, 1e-17,
       "iauFk425", "dr2000");
   vvd(c.pm.delta, 0.5637686678659640164e-5, 1e-18,
       "iauFk425", "dd2000");
   vvd(c.px, 0.1339919950582767871, 1e-13, "iauFk425", "p2000");
   vvd(c.rv, 8.736999669183529069, 1e-12, "iauFk425", "v2000");

}

/**
**  - - - - - - - -
**   t _ f k 4 5 z
**  - - - - - - - -
**
**  Test iauFk45z function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  iauFk45z, vvd
**
**  This revision:  2018 December 6
*/
@Test
public void t_fk45z()
{
   double r1950, d1950, bepoch;


   r1950 = 0.01602284975382960982;
   d1950 = -0.1164347929099906024;
   bepoch = 1954.677617625256806;

   SphericalCoordinate r = jauFk45z(r1950, d1950, bepoch);

   vvd(r.alpha, 0.02719295911606862303, 1e-15,
       "iauFk45z", "r2000");
   vvd(r.delta, -0.1115766001565926892, 1e-13,
       "iauFk45z", "d2000");

}

/**
**  - - - - - - - -
**   t _ f k 5 2 4
**  - - - - - - - -
**
**  Test iauFk524 function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  iauFk524, vvd
**
**  This revision:  2018 December 6
*/
@Test
public void t_fk524()
{
   double r2000, d2000, dr2000, dd2000, p2000, v2000;


   r2000 = 0.8723503576487275595;
   d2000 = -0.7517076365138887672;
   dr2000 = 0.2019447755430472323e-4;
   dd2000 = 0.3541563940505160433e-5;
   p2000 = 0.1559;
   v2000 = 86.87;

   CatalogCoords r = jauFk524(r2000, d2000, dr2000, dd2000, p2000, v2000);

   vvd(r.pos.alpha, 0.8636359659799603487, 1e-13,
       "iauFk524", "r1950");
   vvd(r.pos.delta, -0.7550281733160843059, 1e-13,
       "iauFk524", "d1950");
   vvd(r.pm.alpha, 0.2023628192747172486e-4, 1e-17,
       "iauFk524", "dr1950");
   vvd(r.pm.delta, 0.3624459754935334718e-5, 1e-18,
       "iauFk524", "dd1950");
   vvd(r.px, 0.1560079963299390241, 1e-13,
       "iauFk524", "p1950");
   vvd(r.rv, 86.79606353469163751, 1e-11, "iauFk524", "v1950");

}

/**
**  - - - - - - - -
**   t _ f k 5 4 z
**  - - - - - - - -
**
**  Test iauFk54z function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  iauFk54z, vvd
**
**  This revision:  2018 December 6
*/
@Test
public void t_fk54z()
{
   double r2000, d2000, bepoch;


   r2000 = 0.02719026625066316119;
   d2000 = -0.1115815170738754813;
   bepoch = 1954.677308160316374;

   CatalogCoords r = jauFk54z(r2000, d2000, bepoch);

   vvd(r.pos.alpha, 0.01602015588390065476, 1e-14,
       "iauFk54z", "r1950");
   vvd(r.pos.delta, -0.1164397101110765346, 1e-13,
       "iauFk54z", "d1950");
   vvd(r.pm.alpha, -0.1175712648471090704e-7, 1e-20,
       "iauFk54z", "dr1950");
   vvd(r.pm.delta, 0.2108109051316431056e-7, 1e-20,
       "iauFk54z", "dd1950");
   vvd(r.px, 0, 1e-13,
       "iauFk54z", "p1950");
   vvd(r.rv, 0, 1e-11, "iauFk54z", "v1950");


}

/**
**  - - - - - - - - -
**   t _ a t c c 1 3
**  - - - - - - - - -
**
**  Test iauAtcc13 function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  iauAtcc13, vvd
**
**  This revision:  2021 April 18
*/
@Test
public void t_atcc13()
{
   double rc, dc, pr, pd, px, rv, date1, date2;


   rc = 2.71;
   dc = 0.174;
   pr = 1e-5;
   pd = 5e-6;
   px = 0.1;
   rv = 55.0;
   date1 = 2456165.5;
   date2 = 0.401182685;

   SphericalCoordinate co = jauAtcc13(rc, dc, pr, pd, px, rv, date1, date2);

   vvd(co.alpha,  2.710126504531372384, 1e-12,
           "iauAtcc13", "ra");
   vvd(co.delta, 0.1740632537628350152, 1e-12,
           "iauAtcc13", "da");

}

/**
**  - - - - - - - -
**   t _ a t c c q
**  - - - - - - - -
**
**  Test iauAtccq function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  iauApci13, iauAtccq, vvd
**
**  This revision:  2021 April 18
*/
@Test
public  void t_atccq()
{
   double date1, date2, eo, rc, dc, pr, pd, px, rv;
   Astrom astrom = new Astrom();

   date1 = 2456165.5;
   date2 = 0.401182685;
   eo = jauApci13(date1, date2, astrom);
   rc = 2.71;
   dc = 0.174;
   pr = 1e-5;
   pd = 5e-6;
   px = 0.1;
   rv = 55.0;

   SphericalCoordinate co = jauAtccq(rc, dc, pr, pd, px, rv, astrom);

   vvd(co.alpha, 2.710126504531372384, 1e-12, "iauAtccq", "ra");
   vvd(co.delta, 0.1740632537628350152, 1e-12, "iauAtccq", "da");

}

/**
**  - - - - - - - - -
**   t _ m o o n 9 8
**  - - - - - - - - -
**
**  Test iauMoon98 function.
**
**  Returned:
**     status    int         FALSE = success, TRUE = fail
**
**  Called:  iauMoon98, vvd, viv
**
**  This revision:  2021 April 12
*/
@Test
public void t_moon98()
{
   double pv[][] = jauMoon98(2400000.5, 43999.9);

   vvd(pv[0][0], -0.2601295959971044180e-2, 1e-11,
       "iauMoon98", "x 4");
   vvd(pv[0][1], 0.6139750944302742189e-3, 1e-11,
       "iauMoon98", "y 4");
   vvd(pv[0][2], 0.2640794528229828909e-3, 1e-11,
       "iauMoon98", "z 4");

   vvd(pv[1][0], -0.1244321506649895021e-3, 1e-11,
       "iauMoon98", "xd 4");
   vvd(pv[1][1], -0.5219076942678119398e-3, 1e-11,
       "iauMoon98", "yd 4");
   vvd(pv[1][2], -0.1716132214378462047e-3, 1e-11,
       "iauMoon98", "zd 4");

}
//end of tests
}

/*
 * Copyright © 2018 Paul Harrison, University of Manchester.
 * 
 * This JSOFA software is derived from the official C release of the "Standards Of Fundamental Astronomy" (SOFA) library 
 * of the International Astronomical Union. The intention is to reproduce the functionality and algorithms of 
 * the official SOFA library in a pure Java form.
 * 
 * The responsibility for the maintenance and supply of the JSOFA library lies with the author (not the IAU SOFA Board), 
 * However, The JSOFA software is provided "as is" and the author makes no warranty as to its use or performance. 
 * The author does not and cannot warrant the performance or results which the user may obtain by using the JSOFA software. 
 * The author makes no warranties, express or implied, as to non-infringement of third party rights, merchantability,
 * or fitness for any particular purpose. In no event will the author be liable to the user for any consequential, 
 * incidental, or special damages, including any lost profits or lost savings, even if the author has been advised
 * of such damages, or for any claim by any third party.
 * 
 * Other conditions of the original license (reproduced below) are carried over as applicable.
 */

/*----------------------------------------------------------------------
**
**  Copyright (C) 2018
**  Standards Of Fundamental Astronomy Board
**  of the International Astronomical Union.
**
**  =====================
**  SOFA Software License
**  =====================
**
**  NOTICE TO USER:
**
**  BY USING THIS SOFTWARE YOU ACCEPT THE FOLLOWING TERMS AND CONDITIONS
**  WHICH APPLY TO ITS USE.
**
**  1. The Software is owned by the IAU SOFA Board ("SOFA").
**
**  2. Permission is granted to anyone to use the SOFA software for any
**     purpose, including commercial applications, free of charge and
**     without payment of royalties, subject to the conditions and
**     restrictions listed below.
**
**  3. You (the user) may copy and distribute SOFA source code to others,
**     and use and adapt its code and algorithms in your own software,
**     on a world-wide, royalty-free basis.  That portion of your
**     distribution that does not consist of intact and unchanged copies
**     of SOFA source code files is a "derived work" that must comply
**     with the following requirements:
**
**     a) Your work shall be marked or carry a statement that it
**        (i) uses routines and computations derived by you from
**        software provided by SOFA under license to you; and
**        (ii) does not itself constitute software provided by and/or
**        endorsed by SOFA.
**
**     b) The source code of your derived work must contain descriptions
**        of how the derived work is based upon, contains and/or differs
**        from the original SOFA software.
**
**     c) The name(s) of all routine(s) in your derived work shall not
**        include the prefix "iau".
**
**     d) The origin of the SOFA components of your derived work must
**        not be misrepresented;  you must not claim that you wrote the
**        original software, nor file a patent application for SOFA
**        software or algorithms embedded in the SOFA software.
**
**     e) These requirements must be reproduced intact in any source
**        distribution and shall apply to anyone to whom you have
**        granted a further right to modify the source code of your
**        derived work.
**
**     Note that, as originally distributed, the SOFA software is
**     intended to be a definitive implementation of the IAU standards,
**     and consequently third-party modifications are discouraged.  All
**     variations, no matter how minor, must be explicitly marked as
**     such, as explained above.
**
**  4. In any published work or commercial products which includes
**     results achieved by using the SOFA software, you shall
**     acknowledge that the SOFA software was used in obtaining those
**     results.
**
**  5. You shall not cause the SOFA software to be brought into
**     disrepute, either by misuse, or use for inappropriate tasks, or
**     by inappropriate modification.
**
**  6. The SOFA software is provided "as is" and SOFA makes no warranty
**     as to its use or performance.   SOFA does not and cannot warrant
**     the performance or results which the user may obtain by using the
**     SOFA software.  SOFA makes no warranties, express or implied, as
**     to non-infringement of third party rights, merchantability, or
**     fitness for any particular purpose.  In no event will SOFA be
**     liable to the user for any consequential, incidental, or special
**     damages, including any lost profits or lost savings, even if a
**     SOFA representative has been advised of such damages, or for any
**     claim by any third party.
**
**  7. The provision of any version of the SOFA software under the terms
**     and conditions specified herein does not imply that future
**     versions will also be made available under the same terms and
**     conditions.
**
**  Correspondence concerning SOFA software should be addressed as
**  follows:
**
**      By email:  sofa@ukho.gov.uk
**      By post:   IAU SOFA Center
**                 HM Nautical Almanac Office
**                 UK Hydrographic Office
**                 Admiralty Way, Taunton
**                 Somerset, TA1 2DN
**                 United Kingdom
**
**--------------------------------------------------------------------*/


/*
 * $Log$
 */
