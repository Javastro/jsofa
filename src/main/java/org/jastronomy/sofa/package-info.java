/**
 * This software is a translation of the official C SOFA software package. <a href="http://www.iausofa.org/">http://www.iausofa.org/</a>. The translation was done 
 * by hand with the intention of retaining the structure of the original software as far as possible. This means that the original SOFA C functions are translated to static member functions of
 * the main {@link org.jastronomy.sofa.SOFA} class.
 * 
 * The translation was made from the 2009-12-31 release of the SOFA Library for ANSI C, as revised on 2010-01-27. Any updates to this software
 * should be made by looking for incremental changes in the original source.
 * 
 * 
 * 
 *<p> Notable changes from the original include
 * <ul>
 * <li> function names begin with the prefix "jau" rather than "iau"</li>
 * <li> many of the original SOFA routines return multiple values via pointers - the java translation achieves this by returning composite objects, or  a single array, 
 *   however in some cases where the original was returning several arrays (that must be supplied in an empty form on calling), that pattern has been retained in the java version. </li>
 * <li> introduced simple {@link org.jastronomy.sofa.SOFAException} and children instead of the status return value.</li>
 * <li> the test routines use the JUnit framework.</li>
 * </ul>
 *</p> 
 * 
 *<h2>List of Routines Grouped by functionality </h2> 
 *  <h3>Calendars</h3>

     {@link org.jastronomy.sofa.SOFA#jauCal2jd}    Gregorian calendar to Julian Day number<br/>
     {@link org.jastronomy.sofa.SOFA#jauEpb}       Julian Date to Besselian Epoch<br/>
     {@link org.jastronomy.sofa.SOFA#jauEpb2jd}    Besselian Epoch to Julian Date<br/>
     {@link org.jastronomy.sofa.SOFA#jauEpj}       Julian Date to Julian Epoch<br/>
     {@link org.jastronomy.sofa.SOFA#jauEpj2jd}    Julian Epoch to Julian Date<br/>
     {@link org.jastronomy.sofa.SOFA#jauJd2cal}    Julian Date to Gregorian year, month, day, fraction<br/>
     {@link org.jastronomy.sofa.SOFA#jauJdcalf}    Julian Date to Gregorian date for formatted output<br/>

 <h3>Time scales</h3>

     {@link org.jastronomy.sofa.SOFA#jauDat}       Delta(AT) (=TAI-UTC) for a given UTC date<br/>
     {@link org.jastronomy.sofa.SOFA#jauDtdb}      TDB-TT<br/>

  <h3>Earth rotation angle and sidereal time</h3>

     {@link org.jastronomy.sofa.SOFA#jauEe00}      equation of the equinoxes, IAU 2000<br/>
     {@link org.jastronomy.sofa.SOFA#jauEe00a}     equation of the equinoxes, IAU 2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauEe00b}     equation of the equinoxes, IAU 2000B<br/>
     {@link org.jastronomy.sofa.SOFA#jauEe06a}     equation of the equinoxes, IAU 2006/2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauEect00}    equation of the equinoxes complementary terms, IAU 2000<br/>
     {@link org.jastronomy.sofa.SOFA#jauEqeq94}    equation of the equinoxes, IAU 1994<br/>
     {@link org.jastronomy.sofa.SOFA#jauEra00}     Earth rotation angle, IAU 2000<br/>
     {@link org.jastronomy.sofa.SOFA#jauGmst00}    Greenwich mean sidereal time, IAU 2000<br/>
     {@link org.jastronomy.sofa.SOFA#jauGmst06}    Greenwich mean sidereal time, IAU 2006<br/>
     {@link org.jastronomy.sofa.SOFA#jauGmst82}    Greenwich mean sidereal time, IAU 1982<br/>
     {@link org.jastronomy.sofa.SOFA#jauGst00a}    Greenwich apparent sidereal time, IAU 2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauGst00b}    Greenwich apparent sidereal time, IAU 2000B<br/>
     {@link org.jastronomy.sofa.SOFA#jauGst06}     Greenwich apparent ST, IAU 2006, given NPB matrix<br/>
     {@link org.jastronomy.sofa.SOFA#jauGst06a}    Greenwich apparent sidereal time, IAU 2006/2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauGst94}     Greenwich apparent sidereal time, IAU 1994<br/>

  <h3>Ephemerides (limited precision)</h3>

     {@link org.jastronomy.sofa.SOFA#jauEpv00}     Earth position and velocity<br/>
     {@link org.jastronomy.sofa.SOFA#jauPlan94}    major-planet position and velocity<br/>

  <h3>Precession, nutation, polar motion</h3>

     {@link org.jastronomy.sofa.SOFA#jauBi00}      frame bias components, IAU 2000<br/>
     {@link org.jastronomy.sofa.SOFA#jauBp00}      frame bias and precession matrices, IAU 2000<br/>
     {@link org.jastronomy.sofa.SOFA#jauBp06}      frame bias and precession matrices, IAU 2006<br/>
     {@link org.jastronomy.sofa.SOFA#jauBpn2xy}    extract CIP X,Y coordinates from NPB matrix<br/>
     {@link org.jastronomy.sofa.SOFA#jauC2i00a}    celestial-to-intermediate matrix, IAU 2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauC2i00b}    celestial-to-intermediate matrix, IAU 2000B<br/>
     {@link org.jastronomy.sofa.SOFA#jauC2i06a}    celestial-to-intermediate matrix, IAU 2006/2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauC2ibpn}    celestial-to-intermediate matrix, given NPB matrix, IAU 2000<br/>
     {@link org.jastronomy.sofa.SOFA#jauC2ixy}     celestial-to-intermediate matrix, given X,Y, IAU 2000<br/>
     {@link org.jastronomy.sofa.SOFA#jauC2ixys}    celestial-to-intermediate matrix, given X,Y and s<br/>
     {@link org.jastronomy.sofa.SOFA#jauC2t00a}    celestial-to-terrestrial matrix, IAU 2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauC2t00b}    celestial-to-terrestrial matrix, IAU 2000B<br/>
     {@link org.jastronomy.sofa.SOFA#jauC2t06a}    celestial-to-terrestrial matrix, IAU 2006/2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauC2tcio}    form CIO-based celestial-to-terrestrial matrix<br/>
     {@link org.jastronomy.sofa.SOFA#jauC2teqx}    form equinox-based celestial-to-terrestrial matrix<br/>
     {@link org.jastronomy.sofa.SOFA#jauC2tpe}     celestial-to-terrestrial matrix given nutation, IAU 2000<br/>
     {@link org.jastronomy.sofa.SOFA#jauC2txy}     celestial-to-terrestrial matrix given CIP, IAU 2000<br/>
     {@link org.jastronomy.sofa.SOFA#jauEo06a}     equation of the origins, IAU 2006/2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauEors}      equation of the origins, given NPB matrix and s<br/>
     {@link org.jastronomy.sofa.SOFA#jauFw2m}      Fukushima-Williams angles to r-matrix<br/>
     {@link org.jastronomy.sofa.SOFA#jauFw2xy}     Fukushima-Williams angles to X,Y<br/>
     {@link org.jastronomy.sofa.SOFA#jauNum00a}    nutation matrix, IAU 2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauNum00b}    nutation matrix, IAU 2000B<br/>
     {@link org.jastronomy.sofa.SOFA#jauNum06a}    nutation matrix, IAU 2006/2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauNumat}     form nutation matrix<br/>
     {@link org.jastronomy.sofa.SOFA#jauNut00a}    nutation, IAU 2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauNut00b}    nutation, IAU 2000B<br/>
     {@link org.jastronomy.sofa.SOFA#jauNut06a}    nutation, IAU 2006/2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauNut80}     nutation, IAU 1980<br/>
     {@link org.jastronomy.sofa.SOFA#jauNutm80}    nutation matrix, IAU 1980<br/>
     {@link org.jastronomy.sofa.SOFA#jauObl06}     mean obliquity, IAU 2006<br/>
     {@link org.jastronomy.sofa.SOFA#jauObl80}     mean obliquity, IAU 1980<br/>
     {@link org.jastronomy.sofa.SOFA#jauPb06}      zeta,z,theta precession angles, IAU 2006, including bias<br/>
     {@link org.jastronomy.sofa.SOFA#jauPfw06}     bias-precession Fukushima-Williams angles, IAU 2006<br/>
     {@link org.jastronomy.sofa.SOFA#jauPmat00}    precession matrix (including frame bias), IAU 2000<br/>
     {@link org.jastronomy.sofa.SOFA#jauPmat06}    PB matrix, IAU 2006<br/>
     {@link org.jastronomy.sofa.SOFA#jauPmat76}    precession matrix, IAU 1976<br/>
     {@link org.jastronomy.sofa.SOFA#jauPn00}      bias/precession/nutation results, IAU 2000<br/>
     {@link org.jastronomy.sofa.SOFA#jauPn00a}     bias/precession/nutation, IAU 2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauPn00b}     bias/precession/nutation, IAU 2000B<br/>
     {@link org.jastronomy.sofa.SOFA#jauPn06}      bias/precession/nutation results, IAU 2006<br/>
     {@link org.jastronomy.sofa.SOFA#jauPn06a}     bias/precession/nutation results, IAU 2006/2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauPnm00a}    classical NPB matrix, IAU 2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauPnm00b}    classical NPB matrix, IAU 2000B<br/>
     {@link org.jastronomy.sofa.SOFA#jauPnm06a}    classical NPB matrix, IAU 2006/2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauPnm80}     precession/nutation matrix, IAU 1976/1980<br/>
     {@link org.jastronomy.sofa.SOFA#jauP06e}      precession angles, IAU 2006, equinox based<br/>
     {@link org.jastronomy.sofa.SOFA#jauPom00}     polar motion matrix<br/>
     {@link org.jastronomy.sofa.SOFA#jauPr00}      IAU 2000 precession adjustments<br/>
     {@link org.jastronomy.sofa.SOFA#jauPrec76}    accumulated precession angles, IAU 1976<br/>
     {@link org.jastronomy.sofa.SOFA#jauS00}       the CIO locator s, given X,Y, IAU 2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauS00a}      the CIO locator s, IAU 2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauS00b}      the CIO locator s, IAU 2000B<br/>
     {@link org.jastronomy.sofa.SOFA#jauS06}       the CIO locator s, given X,Y, IAU 2006<br/>
     {@link org.jastronomy.sofa.SOFA#jauS06a}      the CIO locator s, IAU 2006/2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauSp00}      the TIO locator s', IERS 2003<br/>
     {@link org.jastronomy.sofa.SOFA#jauXy06}      CIP, IAU 2006/2000A, from series<br/>
     {@link org.jastronomy.sofa.SOFA#jauXys00a}    CIP and s, IAU 2000A<br/>
     {@link org.jastronomy.sofa.SOFA#jauXys00b}    CIP and s, IAU 2000B<br/>
     {@link org.jastronomy.sofa.SOFA#jauXys06a}    CIP and s, IAU 2006/2000A<br/>

  <h3>Fundamental arguments for nutation etc.</h3>

     {@link org.jastronomy.sofa.SOFA#jauFad03}     mean elongation of the Moon from the Sun<br/>
     {@link org.jastronomy.sofa.SOFA#jauFae03}     mean longitude of Earth<br/>
     {@link org.jastronomy.sofa.SOFA#jauFaf03}     mean argument of the latitude of the Moon<br/>
     {@link org.jastronomy.sofa.SOFA#jauFaju03}    mean longitude of Jupiter<br/>
     {@link org.jastronomy.sofa.SOFA#jauFal03}     mean anomaly of the Moon<br/>
     {@link org.jastronomy.sofa.SOFA#jauFalp03}    mean anomaly of the Sun<br/>
     {@link org.jastronomy.sofa.SOFA#jauFama03}    mean longitude of Mars<br/>
     {@link org.jastronomy.sofa.SOFA#jauFame03}    mean longitude of Mercury<br/>
     {@link org.jastronomy.sofa.SOFA#jauFane03}    mean longitude of Neptune<br/>
     {@link org.jastronomy.sofa.SOFA#jauFaom03}    mean longitude of the Moon's ascending node<br/>
     {@link org.jastronomy.sofa.SOFA#jauFapa03}    general accumulated precession in longitude<br/>
     {@link org.jastronomy.sofa.SOFA#jauFasa03}    mean longitude of Saturn<br/>
     {@link org.jastronomy.sofa.SOFA#jauFaur03}    mean longitude of Uranus<br/>
     {@link org.jastronomy.sofa.SOFA#jauFave03}    mean longitude of Venus<br/>

  <h3>Star space motion</h3>

     {@link org.jastronomy.sofa.SOFA#jauPvstar}    space motion pv-vector to star catalog data<br/>
     {@link org.jastronomy.sofa.SOFA#jauStarpv}    star catalog data to space motion pv-vector<br/>

  <h3>Star catalog conversions</h3>

     {@link org.jastronomy.sofa.SOFA#jauFk52h}     transform FK5 star data into the Hipparcos system<br/>
     {@link org.jastronomy.sofa.SOFA#jauFk5hip}    FK5 to Hipparcos rotation and spin<br/>
     {@link org.jastronomy.sofa.SOFA#jauFk5hz}     FK5 to Hipparcos assuming zero Hipparcos proper motion<br/>
     {@link org.jastronomy.sofa.SOFA#jauH2fk5}     transform Hipparcos star data into the FK5 system<br/>
     {@link org.jastronomy.sofa.SOFA#jauHfk5z}     Hipparcos to FK5 assuming zero Hipparcos proper motion<br/>
     {@link org.jastronomy.sofa.SOFA#jauStarpm}    proper motion between two epochs<br/>

  <h3>Geodetic/geocentric</h3>

     {@link org.jastronomy.sofa.SOFA#jauEform}     a,f for a nominated Earth reference ellipsoid<br/>
     {@link org.jastronomy.sofa.SOFA#jauGc2gd}     geocentric to geodetic for a nominated ellipsoid<br/>
     {@link org.jastronomy.sofa.SOFA#jauGc2gde}    geocentric to geodetic given ellipsoid a,f<br/>
     {@link org.jastronomy.sofa.SOFA#jauGd2gc}     geodetic to geocentric for a nominated ellipsoid<br/>
     {@link org.jastronomy.sofa.SOFA#jauGd2gce}    geodetic to geocentric given ellipsoid a,f<br/>


---

<h2>OPERATIONS INVOLVING P-VECTORS AND R-MATRICES</h2>

  <h3>Initialize</h3>

     {@link org.jastronomy.sofa.SOFA#jauZp}        zero p-vector<br/>
     {@link org.jastronomy.sofa.SOFA#jauZr}        initialize r-matrix to null<br/>
     {@link org.jastronomy.sofa.SOFA#jauIr}        initialize r-matrix to identity<br/>

  <h3>Copy/extend/extract</h3>

     {@link org.jastronomy.sofa.SOFA#jauCp}        copy p-vector<br/>
     {@link org.jastronomy.sofa.SOFA#jauCr}        copy r-matrix<br/>

  <h3>Build rotations</h3>

     {@link org.jastronomy.sofa.SOFA#jauRx}        rotate r-matrix about x<br/>
     {@link org.jastronomy.sofa.SOFA#jauRy}        rotate r-matrix about y<br/>
     {@link org.jastronomy.sofa.SOFA#jauRz}        rotate r-matrix about z<br/>

  <h3>Spherical/Cartesian conversions</h3>

     {@link org.jastronomy.sofa.SOFA#jauS2c}       spherical to unit vector<br/>
     {@link org.jastronomy.sofa.SOFA#jauC2s}       unit vector to spherical<br/>
     {@link org.jastronomy.sofa.SOFA#jauS2p}       spherical to p-vector<br/>
     {@link org.jastronomy.sofa.SOFA#jauP2s}       p-vector to spherical<br/>

  <h3>Operations on vectors</h3>

     {@link org.jastronomy.sofa.SOFA#jauPpp}       p-vector plus p-vector<br/>
     {@link org.jastronomy.sofa.SOFA#jauPmp}       p-vector minus p-vector<br/>
     {@link org.jastronomy.sofa.SOFA#jauPpsp}      p-vector plus scaled p-vector<br/>
     {@link org.jastronomy.sofa.SOFA#jauPdp}       inner (=scalar=dot) product of two p-vectors<br/>
     {@link org.jastronomy.sofa.SOFA#jauPxp}       outer (=vector=cross) product of two p-vectors<br/>
     {@link org.jastronomy.sofa.SOFA#jauPm}        modulus of p-vector<br/>
     {@link org.jastronomy.sofa.SOFA#jauPn}        normalize p-vector returning modulus<br/>
     {@link org.jastronomy.sofa.SOFA#jauSxp}       multiply p-vector by scalar<br/>

  <h3>Operations on matrices</h3>

     {@link org.jastronomy.sofa.SOFA#jauRxr}       r-matrix multiply<br/>
     {@link org.jastronomy.sofa.SOFA#jauTr}        transpose r-matrix<br/>

  <h3>Matrix-vector products</h3>

     {@link org.jastronomy.sofa.SOFA#jauRxp}       product of r-matrix and p-vector<br/>
     {@link org.jastronomy.sofa.SOFA#jauTrxp}      product of transpose of r-matrix and p-vector<br/>

  <h3>Separation and position-angle</h3>

     {@link org.jastronomy.sofa.SOFA#jauSepp}      angular separation from p-vectors<br/>
     {@link org.jastronomy.sofa.SOFA#jauSeps}      angular separation from spherical coordinates<br/>
     {@link org.jastronomy.sofa.SOFA#jauPap}       position-angle from p-vectors<br/>
     {@link org.jastronomy.sofa.SOFA#jauPas}       position-angle from spherical coordinates<br/>

 <h3> Rotation vectors</h3>

     {@link org.jastronomy.sofa.SOFA#jauRv2m}      r-vector to r-matrix<br/>
     {@link org.jastronomy.sofa.SOFA#jauRm2v}      r-matrix to r-vector<br/>


<h2>OPERATIONS INVOLVING PV-VECTORS</h2>

  <h3>Initialize</h3>

     {@link org.jastronomy.sofa.SOFA#jauZpv}       zero pv-vector<br/>

  <h3>Copy/extend/extract</h3>

     {@link org.jastronomy.sofa.SOFA#jauCpv}       copy pv-vector<br/>
     {@link org.jastronomy.sofa.SOFA#jauP2pv}      append zero velocity to p-vector<br/>
     {@link org.jastronomy.sofa.SOFA#jauPv2p}      discard velocity component of pv-vector<br/>

  <h3>Spherical/Cartesian conversions</h3>

     {@link org.jastronomy.sofa.SOFA#jauS2pv}      spherical to pv-vector<br/>
     {@link org.jastronomy.sofa.SOFA#jauPv2s}      pv-vector to spherical<br/>

  <h3>Operations on vectors</h3>

     {@link org.jastronomy.sofa.SOFA#jauPvppv}     pv-vector plus pv-vector<br/>
     {@link org.jastronomy.sofa.SOFA#jauPvmpv}     pv-vector minus pv-vector<br/>
     {@link org.jastronomy.sofa.SOFA#jauPvdpv}     inner (=scalar=dot) product of two pv-vectors<br/>
     {@link org.jastronomy.sofa.SOFA#jauPvxpv}     outer (=vector=cross) product of two pv-vectors<br/>
     {@link org.jastronomy.sofa.SOFA#jauPvm}       modulus of pv-vector<br/>
     {@link org.jastronomy.sofa.SOFA#jauSxpv}      multiply pv-vector by scalar<br/>
     {@link org.jastronomy.sofa.SOFA#jauS2xpv}     multiply pv-vector by two scalars<br/>
     {@link org.jastronomy.sofa.SOFA#jauPvu}       update pv-vector<br/>
     {@link org.jastronomy.sofa.SOFA#jauPvup}      update pv-vector discarding velocity<br/>

  <h3>Matrix-vector products</h3>

     {@link org.jastronomy.sofa.SOFA#jauRxpv}      product of r-matrix and pv-vector<br/>
     {@link org.jastronomy.sofa.SOFA#jauTrxpv}     product of transpose of r-matrix and pv-vector<br/>


<h3>OPERATIONS ON ANGLES</h3>

     {@link org.jastronomy.sofa.SOFA#jauAnp}       normalize radians to range 0 to 2pi<br/>
     {@link org.jastronomy.sofa.SOFA#jauAnpm}      normalize radians to range -pi to +pi<br/>
     {@link org.jastronomy.sofa.SOFA#jauA2tf}      decompose radians into hms<br/>
     {@link org.jastronomy.sofa.SOFA#jauA2af}      decompose radians into d ' "<br/>
     {@link org.jastronomy.sofa.SOFA#jauD2tf}      decompose days into hms<br/>



 * 
 * @since February 2010
 * @author Paul Harrison (paul.harrison@manchester.ac.uk) 1 Feb 2010
 */
package org.jastronomy.sofa;

/*----------------------------------------------------------------------
 **
 **  Copyright (C) 2009
 **  Standards Of Fundamental Astronomy Review Board
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
 **  1. The Software is owned by the IAU SOFA Review Board ("SOFA").
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
 **        include the prefix "iau_".
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
 **      By email:  sofa@rl.ac.uk
 **      By post:   IAU SOFA Center
 **                 STFC Rutherford Appleton Laboratory
 **                 Harwell Science and Innovation Campus
 **                 Didcot, Oxfordshire, OX11 0QX
 **                 United Kingdom
 **
 **--------------------------------------------------------------------*/


