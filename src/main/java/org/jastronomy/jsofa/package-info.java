/**
 * This software is a translation of the official C SOFA software package. <a href="http://www.iausofa.org/">http://www.iausofa.org/</a>. The translation was done 
 * by hand with the intention of retaining the structure of the original software as far as possible. This means that the original JSOFA C functions are translated to static member functions of
 * the main {@link org.jastronomy.jsofa.JSOFA} class.
 * 
 * The translation was made from the 2010-12-01 release of the JSOFA Library for ANSI C. Any updates to this software
 * should be made by looking for incremental changes in the original source.
 * 
 * This javadoc provides a detailed manual on how to use each function - there are also some <a href="http://www.iausofa.org/cookbooks.html">cookbooks on the IAU SOFA site</a> which can 
 * also be used to discover how to use the library bearing in mind the changes outlined below.
 * 
 *<p> Notable changes from the original include:
 * <ul>
 * <li> function names begin with the prefix "jau" rather than "iau"</li>
 * <li> many of the original SOFA routines return multiple values via pointers - the java translation achieves this by returning composite objects 
 *   (e.g. {@link org.jastronomy.jsofa.JSOFA#jauEpb2jd(double)} which
 *    uses {@link org.jastronomy.jsofa.JSOFA.JulianDate} as the function return value), or  a single array (e.g. {@link org.jastronomy.jsofa.JSOFA#jauC2i06a(double, double)} ), 
 *   however in some cases where the original was returning several arrays (that must be supplied in an empty form on calling), that pattern has been retained in the java version. </li>
 * <li> introduced simple {@link org.jastronomy.jsofa.JSOFAException} and children instead of the status return value.</li>
 * <li> the test routines use the JUnit framework.</li>
 * </ul>
 *</p> 
 *<p>There are a number of features of the java translation
 *   <ul>
 *     <li>The "functions" of the library are all static member functions of {@link org.jastronomy.jsofa.JSOFA}</li>
 *     <li>Class members are typically publicly visible and no accessors are used - This makes the library code more "C"-like</li>
 *   </ul>
 *</p>
 * 
 *<h2>List of Routines Grouped by functionality </h2> 
 *  <h3>Calendars</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauCal2jd}    Gregorian calendar to Julian Day number<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauEpb}       Julian Date to Besselian Epoch<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauEpb2jd}    Besselian Epoch to Julian Date<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauEpj}       Julian Date to Julian Epoch<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauEpj2jd}    Julian Epoch to Julian Date<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauJd2cal}    Julian Date to Gregorian year, month, day, fraction<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauJdcalf}    Julian Date to Gregorian date for formatted output<br/>

 <h3>Time scales</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauD2DTF}    format 2-part JD for output<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauDat}      Delta(AT) (=TAI-UTC) for a given UTC date<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauDtdb}     TDB-TT<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauDtf2d}    encode time and date fields into 2-part JD<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauTaitt}    TAI to TT<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauTaiut1}   TAI to UT1<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauTaiutc}   TAI to UTC<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauTcbtdb}   TCB to TDB<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauTcgtt}    TCG to TT<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauTdbtcb}   TDB to TCB<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauTdbtt}    TDB to TT<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauTttai}    TT to TAI<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauTttcg}    TT to TCG<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauTttdb}    TT to TDB<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauTtut1}    TT to UT1<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauUt1tai}   UT1 to TAI<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauUt1tt}    UT1 to TT<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauUt1utc}   UT1 to UTC<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauUtctai}   UTC to TAI<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauUtcut1}   UTC to UT1<br/>




  <h3>Earth rotation angle and sidereal time</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauEe00}      equation of the equinoxes, IAU 2000<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauEe00a}     equation of the equinoxes, IAU 2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauEe00b}     equation of the equinoxes, IAU 2000B<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauEe06a}     equation of the equinoxes, IAU 2006/2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauEect00}    equation of the equinoxes complementary terms, IAU 2000<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauEqeq94}    equation of the equinoxes, IAU 1994<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauEra00}     Earth rotation angle, IAU 2000<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauGmst00}    Greenwich mean sidereal time, IAU 2000<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauGmst06}    Greenwich mean sidereal time, IAU 2006<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauGmst82}    Greenwich mean sidereal time, IAU 1982<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauGst00a}    Greenwich apparent sidereal time, IAU 2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauGst00b}    Greenwich apparent sidereal time, IAU 2000B<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauGst06}     Greenwich apparent ST, IAU 2006, given NPB matrix<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauGst06a}    Greenwich apparent sidereal time, IAU 2006/2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauGst94}     Greenwich apparent sidereal time, IAU 1994<br/>

  <h3>Ephemerides (limited precision)</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauEpv00}     Earth position and velocity<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPlan94}    major-planet position and velocity<br/>

  <h3>Precession, nutation, polar motion</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauBi00}      frame bias components, IAU 2000<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauBp00}      frame bias and precession matrices, IAU 2000<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauBp06}      frame bias and precession matrices, IAU 2006<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauBpn2xy}    extract CIP X,Y coordinates from NPB matrix<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauC2i00a}    celestial-to-intermediate matrix, IAU 2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauC2i00b}    celestial-to-intermediate matrix, IAU 2000B<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauC2i06a}    celestial-to-intermediate matrix, IAU 2006/2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauC2ibpn}    celestial-to-intermediate matrix, given NPB matrix, IAU 2000<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauC2ixy}     celestial-to-intermediate matrix, given X,Y, IAU 2000<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauC2ixys}    celestial-to-intermediate matrix, given X,Y and s<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauC2t00a}    celestial-to-terrestrial matrix, IAU 2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauC2t00b}    celestial-to-terrestrial matrix, IAU 2000B<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauC2t06a}    celestial-to-terrestrial matrix, IAU 2006/2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauC2tcio}    form CIO-based celestial-to-terrestrial matrix<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauC2teqx}    form equinox-based celestial-to-terrestrial matrix<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauC2tpe}     celestial-to-terrestrial matrix given nutation, IAU 2000<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauC2txy}     celestial-to-terrestrial matrix given CIP, IAU 2000<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauEo06a}     equation of the origins, IAU 2006/2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauEors}      equation of the origins, given NPB matrix and s<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauFw2m}      Fukushima-Williams angles to r-matrix<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauFw2xy}     Fukushima-Williams angles to X,Y<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauNum00a}    nutation matrix, IAU 2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauNum00b}    nutation matrix, IAU 2000B<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauNum06a}    nutation matrix, IAU 2006/2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauNumat}     form nutation matrix<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauNut00a}    nutation, IAU 2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauNut00b}    nutation, IAU 2000B<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauNut06a}    nutation, IAU 2006/2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauNut80}     nutation, IAU 1980<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauNutm80}    nutation matrix, IAU 1980<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauObl06}     mean obliquity, IAU 2006<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauObl80}     mean obliquity, IAU 1980<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPb06}      zeta,z,theta precession angles, IAU 2006, including bias<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPfw06}     bias-precession Fukushima-Williams angles, IAU 2006<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPmat00}    precession matrix (including frame bias), IAU 2000<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPmat06}    PB matrix, IAU 2006<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPmat76}    precession matrix, IAU 1976<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPn00}      bias/precession/nutation results, IAU 2000<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPn00a}     bias/precession/nutation, IAU 2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPn00b}     bias/precession/nutation, IAU 2000B<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPn06}      bias/precession/nutation results, IAU 2006<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPn06a}     bias/precession/nutation results, IAU 2006/2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPnm00a}    classical NPB matrix, IAU 2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPnm00b}    classical NPB matrix, IAU 2000B<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPnm06a}    classical NPB matrix, IAU 2006/2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPnm80}     precession/nutation matrix, IAU 1976/1980<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauP06e}      precession angles, IAU 2006, equinox based<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPom00}     polar motion matrix<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPr00}      IAU 2000 precession adjustments<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPrec76}    accumulated precession angles, IAU 1976<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauS00}       the CIO locator s, given X,Y, IAU 2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauS00a}      the CIO locator s, IAU 2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauS00b}      the CIO locator s, IAU 2000B<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauS06}       the CIO locator s, given X,Y, IAU 2006<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauS06a}      the CIO locator s, IAU 2006/2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauSp00}      the TIO locator s', IERS 2003<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauXy06}      CIP, IAU 2006/2000A, from series<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauXys00a}    CIP and s, IAU 2000A<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauXys00b}    CIP and s, IAU 2000B<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauXys06a}    CIP and s, IAU 2006/2000A<br/>

  <h3>Fundamental arguments for nutation etc.</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauFad03}     mean elongation of the Moon from the Sun<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauFae03}     mean longitude of Earth<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauFaf03}     mean argument of the latitude of the Moon<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauFaju03}    mean longitude of Jupiter<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauFal03}     mean anomaly of the Moon<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauFalp03}    mean anomaly of the Sun<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauFama03}    mean longitude of Mars<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauFame03}    mean longitude of Mercury<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauFane03}    mean longitude of Neptune<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauFaom03}    mean longitude of the Moon's ascending node<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauFapa03}    general accumulated precession in longitude<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauFasa03}    mean longitude of Saturn<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauFaur03}    mean longitude of Uranus<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauFave03}    mean longitude of Venus<br/>

  <h3>Star space motion</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauPvstar}    space motion pv-vector to star catalog data<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauStarpv}    star catalog data to space motion pv-vector<br/>

  <h3>Star catalog conversions</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauFk52h}     transform FK5 star data into the Hipparcos system<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauFk5hip}    FK5 to Hipparcos rotation and spin<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauFk5hz}     FK5 to Hipparcos assuming zero Hipparcos proper motion<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauH2fk5}     transform Hipparcos star data into the FK5 system<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauHfk5z}     Hipparcos to FK5 assuming zero Hipparcos proper motion<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauStarpm}    proper motion between two epochs<br/>

  <h3>Geodetic/geocentric</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauEform}     a,f for a nominated Earth reference ellipsoid<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauGc2gd}     geocentric to geodetic for a nominated ellipsoid<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauGc2gde}    geocentric to geodetic given ellipsoid a,f<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauGd2gc}     geodetic to geocentric for a nominated ellipsoid<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauGd2gce}    geodetic to geocentric given ellipsoid a,f<br/>


---

<h2>OPERATIONS INVOLVING P-VECTORS AND R-MATRICES</h2>

  <h3>Initialize</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauZp}        zero p-vector<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauZr}        initialize r-matrix to null<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauIr}        initialize r-matrix to identity<br/>

  <h3>Copy/extend/extract</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauCp}        copy p-vector<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauCr}        copy r-matrix<br/>

  <h3>Build rotations</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauRx}        rotate r-matrix about x<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauRy}        rotate r-matrix about y<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauRz}        rotate r-matrix about z<br/>

  <h3>Spherical/Cartesian conversions</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauS2c}       spherical to unit vector<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauC2s}       unit vector to spherical<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauS2p}       spherical to p-vector<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauP2s}       p-vector to spherical<br/>

  <h3>Operations on vectors</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauPpp}       p-vector plus p-vector<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPmp}       p-vector minus p-vector<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPpsp}      p-vector plus scaled p-vector<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPdp}       inner (=scalar=dot) product of two p-vectors<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPxp}       outer (=vector=cross) product of two p-vectors<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPm}        modulus of p-vector<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPn}        normalize p-vector returning modulus<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauSxp}       multiply p-vector by scalar<br/>

  <h3>Operations on matrices</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauRxr}       r-matrix multiply<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauTr}        transpose r-matrix<br/>

  <h3>Matrix-vector products</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauRxp}       product of r-matrix and p-vector<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauTrxp}      product of transpose of r-matrix and p-vector<br/>

  <h3>Separation and position-angle</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauSepp}      angular separation from p-vectors<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauSeps}      angular separation from spherical coordinates<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPap}       position-angle from p-vectors<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPas}       position-angle from spherical coordinates<br/>

 <h3> Rotation vectors</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauRv2m}      r-vector to r-matrix<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauRm2v}      r-matrix to r-vector<br/>


<h2>OPERATIONS INVOLVING PV-VECTORS</h2>

  <h3>Initialize</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauZpv}       zero pv-vector<br/>

  <h3>Copy/extend/extract</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauCpv}       copy pv-vector<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauP2pv}      append zero velocity to p-vector<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPv2p}      discard velocity component of pv-vector<br/>

  <h3>Spherical/Cartesian conversions</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauS2pv}      spherical to pv-vector<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPv2s}      pv-vector to spherical<br/>

  <h3>Operations on vectors</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauPvppv}     pv-vector plus pv-vector<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPvmpv}     pv-vector minus pv-vector<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPvdpv}     inner (=scalar=dot) product of two pv-vectors<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPvxpv}     outer (=vector=cross) product of two pv-vectors<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPvm}       modulus of pv-vector<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauSxpv}      multiply pv-vector by scalar<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauS2xpv}     multiply pv-vector by two scalars<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPvu}       update pv-vector<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauPvup}      update pv-vector discarding velocity<br/>

  <h3>Matrix-vector products</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauRxpv}      product of r-matrix and pv-vector<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauTrxpv}     product of transpose of r-matrix and pv-vector<br/>


<h3>OPERATIONS ON ANGLES</h3>

     {@link org.jastronomy.jsofa.JSOFA#jauAnp}       normalize radians to range 0 to 2pi<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauAnpm}      normalize radians to range -pi to +pi<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauA2tf}      decompose radians into hms<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauA2af}      decompose radians into d ' "<br/>
     {@link org.jastronomy.jsofa.JSOFA#jauD2tf}      decompose days into hms<br/>



 * 
 * @since February 2010
 * @author Paul Harrison (paul.harrison@manchester.ac.uk) 1 Feb 2010
 */
package org.jastronomy.jsofa;

/*
 * Copyright © 2010 Paul Harrison, University of Manchester.
 * 
 * This JSOFA software is derived from the official C release of the "Standards Of Fundamental Astronomy" (SOFA) library 
 * of the International Astronomical Union. The intention is to reproduce the functionality and algorithms of 
 * the official SOFA library in a pure Java form.
 * 
 * The responsibility for the maintenance and supply of the JSOFA library lies with the author (not the IAU SOFA Board). 
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
**  Copyright (C) 2012
**  Standards Of Fundamental Astronomy Board
**  of the International Astronomical Union.
**
**  =====================
**  SOFA Software License
**  =====================
**
**  NOTICE TO USER:
**
**  BY USING THIS SOFTWARE YOU ACCEPT THE FOLLOWING SIX TERMS AND
**  CONDITIONS WHICH APPLY TO ITS USE.
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
**     c) The names of all routines in your derived work shall not
**        include the prefix "iau" or "sofa" or trivial modifications
**        thereof such as changes of case.
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
**  4. You shall not cause the SOFA software to be brought into
**     disrepute, either by misuse, or use for inappropriate tasks, or
**     by inappropriate modification.
**
**  5. The SOFA software is provided "as is" and SOFA makes no warranty
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
**  6. The provision of any version of the SOFA software under the terms
**     and conditions specified herein does not imply that future
**     versions will also be made available under the same terms and
**     conditions.
*
**  In any published work or commercial product which uses the SOFA
**  software directly, acknowledgement (see www.iausofa.org) is
**  appreciated.
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


