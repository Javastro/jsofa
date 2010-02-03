/**
 * This software is a translation of the official C SOFA software package. <a href="http://www.iausofa.org/">http://www.iausofa.org/</a>. The translation was done 
 * by hand with the intention of retaining the structure of the original software as far as possible.
 * 
 * The translation was made from the 2009-12-31 release of the SOFA Library for ANSI C, as revised on 2010-01-27. Any updates to this software
 * should be made by looking for incremental changes in the original source.
 * 
 * 
 * 
 * Notable Changes
 * <ul>
 * <li> function names begin with the prefix "jau" rather than "iau"</li>
 * <li> many of the original SOFA routines return multiple values via pointers - the java translation achieves this by returning composite objects, 
 *   however in many cases where the original was returning an array (that must be supplied in an empty form on calling), that idiom has been retained in the java version. </li>
 * <li> introduced simple {@link SOFAException} and children instead of the status return value.</li>
 * <li> the test routines use the JUnit framework.</li>
 * </ul>
 * 
 *   Calendars

     CAL2JD    Gregorian calendar to Julian Day number
     EPB       Julian Date to Besselian Epoch
     EPB2JD    Besselian Epoch to Julian Date
     EPJ       Julian Date to Julian Epoch
     EPJ2JD    Julian Epoch to Julian Date
     JD2CAL    Julian Date to Gregorian year, month, day, fraction
     JDCALF    Julian Date to Gregorian date for formatted output

  Time scales

     DAT       Delta(AT) (=TAI-UTC) for a given UTC date
     DTDB      TDB-TT

  Earth rotation angle and sidereal time

     EE00      equation of the equinoxes, IAU 2000
     EE00A     equation of the equinoxes, IAU 2000A
     EE00B     equation of the equinoxes, IAU 2000B
     EE06A     equation of the equinoxes, IAU 2006/2000A
     EECT00    equation of the equinoxes complementary terms, IAU 2000
     EQEQ94    equation of the equinoxes, IAU 1994
     ERA00     Earth rotation angle, IAU 2000
     GMST00    Greenwich mean sidereal time, IAU 2000
     GMST06    Greenwich mean sidereal time, IAU 2006
     GMST82    Greenwich mean sidereal time, IAU 1982
     GST00A    Greenwich apparent sidereal time, IAU 2000A
     GST00B    Greenwich apparent sidereal time, IAU 2000B
     GST06     Greenwich apparent ST, IAU 2006, given NPB matrix
     GST06A    Greenwich apparent sidereal time, IAU 2006/2000A
     GST94     Greenwich apparent sidereal time, IAU 1994

  Ephemerides (limited precision)

     EPV00     Earth position and velocity
     PLAN94    major-planet position and velocity

  Precession, nutation, polar motion

     BI00      frame bias components, IAU 2000
     BP00      frame bias and precession matrices, IAU 2000
     BP06      frame bias and precession matrices, IAU 2006
     BPN2XY    extract CIP X,Y coordinates from NPB matrix
     C2I00A    celestial-to-intermediate matrix, IAU 2000A
     C2I00B    celestial-to-intermediate matrix, IAU 2000B
     C2I06A    celestial-to-intermediate matrix, IAU 2006/2000A
     C2IBPN    celestial-to-intermediate matrix, given NPB matrix, IAU 2000
     C2IXY     celestial-to-intermediate matrix, given X,Y, IAU 2000
     C2IXYS    celestial-to-intermediate matrix, given X,Y and s
     C2T00A    celestial-to-terrestrial matrix, IAU 2000A
     C2T00B    celestial-to-terrestrial matrix, IAU 2000B
     C2T06A    celestial-to-terrestrial matrix, IAU 2006/2000A
     C2TCIO    form CIO-based celestial-to-terrestrial matrix
     C2TEQX    form equinox-based celestial-to-terrestrial matrix
     C2TPE     celestial-to-terrestrial matrix given nutation, IAU 2000
     C2TXY     celestial-to-terrestrial matrix given CIP, IAU 2000
     EO06A     equation of the origins, IAU 2006/2000A
     EORS      equation of the origins, given NPB matrix and s
     FW2M      Fukushima-Williams angles to r-matrix
     FW2XY     Fukushima-Williams angles to X,Y
     NUM00A    nutation matrix, IAU 2000A
     NUM00B    nutation matrix, IAU 2000B
     NUM06A    nutation matrix, IAU 2006/2000A
     NUMAT     form nutation matrix
     NUT00A    nutation, IAU 2000A
     NUT00B    nutation, IAU 2000B
     NUT06A    nutation, IAU 2006/2000A
     NUT80     nutation, IAU 1980
     NUTM80    nutation matrix, IAU 1980
     OBL06     mean obliquity, IAU 2006
     OBL80     mean obliquity, IAU 1980
     PB06      zeta,z,theta precession angles, IAU 2006, including bias
     PFW06     bias-precession Fukushima-Williams angles, IAU 2006
     PMAT00    precession matrix (including frame bias), IAU 2000
     PMAT06    PB matrix, IAU 2006
     PMAT76    precession matrix, IAU 1976
     PN00      bias/precession/nutation results, IAU 2000
     PN00A     bias/precession/nutation, IAU 2000A
     PN00B     bias/precession/nutation, IAU 2000B
     PN06      bias/precession/nutation results, IAU 2006
     PN06A     bias/precession/nutation results, IAU 2006/2000A
     PNM00A    classical NPB matrix, IAU 2000A
     PNM00B    classical NPB matrix, IAU 2000B
     PNM06A    classical NPB matrix, IAU 2006/2000A
     PNM80     precession/nutation matrix, IAU 1976/1980
     P06E      precession angles, IAU 2006, equinox based
     POM00     polar motion matrix
     PR00      IAU 2000 precession adjustments
     PREC76    accumulated precession angles, IAU 1976
     S00       the CIO locator s, given X,Y, IAU 2000A
     S00A      the CIO locator s, IAU 2000A
     S00B      the CIO locator s, IAU 2000B
     S06       the CIO locator s, given X,Y, IAU 2006
     S06A      the CIO locator s, IAU 2006/2000A
     SP00      the TIO locator s', IERS 2003
     XY06      CIP, IAU 2006/2000A, from series
     XYS00A    CIP and s, IAU 2000A
     XYS00B    CIP and s, IAU 2000B
     XYS06A    CIP and s, IAU 2006/2000A

  Fundamental arguments for nutation etc.

     FAD03     mean elongation of the Moon from the Sun
     FAE03     mean longitude of Earth
     FAF03     mean argument of the latitude of the Moon
     FAJU03    mean longitude of Jupiter
     FAL03     mean anomaly of the Moon
     FALP03    mean anomaly of the Sun
     FAMA03    mean longitude of Mars
     FAME03    mean longitude of Mercury
     FANE03    mean longitude of Neptune
     FAOM03    mean longitude of the Moon's ascending node
     FAPA03    general accumulated precession in longitude
     FASA03    mean longitude of Saturn
     FAUR03    mean longitude of Uranus
     FAVE03    mean longitude of Venus

  Star space motion

     PVSTAR    space motion pv-vector to star catalog data
     STARPV    star catalog data to space motion pv-vector

  Star catalog conversions

     FK52H     transform FK5 star data into the Hipparcos system
     FK5HIP    FK5 to Hipparcos rotation and spin
     FK5HZ     FK5 to Hipparcos assuming zero Hipparcos proper motion
     H2FK5     transform Hipparcos star data into the FK5 system
     HFK5Z     Hipparcos to FK5 assuming zero Hipparcos proper motion
     STARPM    proper motion between two epochs

  Geodetic/geocentric

     EFORM     a,f for a nominated Earth reference ellipsoid
     GC2GD     geocentric to geodetic for a nominated ellipsoid
     GC2GDE    geocentric to geodetic given ellipsoid a,f
     GD2GC     geodetic to geocentric for a nominated ellipsoid
     GD2GCE    geodetic to geocentric given ellipsoid a,f


---

OPERATIONS INVOLVING P-VECTORS AND R-MATRICES

  Initialize

     ZP        zero p-vector
     ZR        initialize r-matrix to null
     IR        initialize r-matrix to identity

  Copy/extend/extract

     CP        copy p-vector
     CR        copy r-matrix

  Build rotations

     RX        rotate r-matrix about x
     RY        rotate r-matrix about y
     RZ        rotate r-matrix about z

  Spherical/Cartesian conversions

     S2C       spherical to unit vector
     C2S       unit vector to spherical
     S2P       spherical to p-vector
     P2S       p-vector to spherical

  Operations on vectors

     PPP       p-vector plus p-vector
     PMP       p-vector minus p-vector
     PPSP      p-vector plus scaled p-vector
     PDP       inner (=scalar=dot) product of two p-vectors
     PXP       outer (=vector=cross) product of two p-vectors
     PM        modulus of p-vector
     PN        normalize p-vector returning modulus
     SXP       multiply p-vector by scalar

  Operations on matrices

     RXR       r-matrix multiply
     TR        transpose r-matrix

  Matrix-vector products

     RXP       product of r-matrix and p-vector
     TRXP      product of transpose of r-matrix and p-vector

  Separation and position-angle

     SEPP      angular separation from p-vectors
     SEPS      angular separation from spherical coordinates
     PAP       position-angle from p-vectors
     PAS       position-angle from spherical coordinates

  Rotation vectors

     RV2M      r-vector to r-matrix
     RM2V      r-matrix to r-vector


OPERATIONS INVOLVING PV-VECTORS

  Initialize

     ZPV       zero pv-vector

  Copy/extend/extract

     CPV       copy pv-vector
     P2PV      append zero velocity to p-vector
     PV2P      discard velocity component of pv-vector

  Spherical/Cartesian conversions

     S2PV      spherical to pv-vector
     PV2S      pv-vector to spherical

  Operations on vectors

     PVPPV     pv-vector plus pv-vector
     PVMPV     pv-vector minus pv-vector
     PVDPV     inner (=scalar=dot) product of two pv-vectors
     PVXPV     outer (=vector=cross) product of two pv-vectors
     PVM       modulus of pv-vector
     SXPV      multiply pv-vector by scalar
     S2XPV     multiply pv-vector by two scalars
     PVU       update pv-vector
     PVUP      update pv-vector discarding velocity

  Matrix-vector products

     RXPV      product of r-matrix and pv-vector
     TRXPV     product of transpose of r-matrix and pv-vector


OPERATIONS ON ANGLES

     ANP       normalize radians to range 0 to 2pi
     ANPM      normalize radians to range -pi to +pi
     A2TF      decompose radians into hms
     A2AF      decompose radians into d ' "
     D2TF      decompose days into hms



 * 
 * @since February 2010
 * @author Paul Harrison (paul.harrison@manchester.ac.uk) 1 Feb 2010
 */
package org.jastronomy.sofa;
