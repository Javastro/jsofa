/* Generated from Java with JSweet 3.1.0 - http://www.jsweet.org */

namespace org.jastronomy.jsofa {
    export class JSOFATest {
        static verbose: boolean = false;

        public static setUpBeforeClass() {
        }

        public setUp() {
        }

        public testversion() {
        }

        /**
         * 
         * Validate an integer result.
         * 
         * Internal function used by t_sofa_c program.
         * 
         * Given:
         * ival     int          value computed by function under test
         * ivalok   int          correct value
         * func     char[]       name of function under test
         * test     char[]       name of individual test
         * 
         * Given and returned:
         * status   int          set to FALSE if test fails
         * 
         * This revision:  2009 November 4
         * @param {number} ival
         * @param {number} ivalok
         * @param {string} func
         * @param {string} test
         * @private
         */
        /*private*/ viv(ival: number, ivalok: number, func: string, test: string) {
            if (ival !== ivalok){
                console.error("%s failed: %s want %d got %d\n");
            } else if (JSOFATest.verbose){
                console.info("%s passed: %s want %d got %d\n");
            }
            return;
        }

        /**
         * 
         * Validate a double result.
         * 
         * Internal function used by t_sofa_c program.
         * 
         * Given:
         * val      double       value computed by function under test
         * valok    double       expected value
         * dval     double       maximum allowable error
         * func     char[]       name of function under test
         * test     char[]       name of individual test
         * 
         * Given and returned:
         * status   int          set to FALSE if test fails
         * 
         * This revision:  2008 June 8
         * @param {number} val
         * @param {number} valok
         * @param {number} dval
         * @param {string} func
         * @param {string} test
         * @private
         */
        /*private*/ vvd(val: number, valok: number, dval: number, func: string, test: string) {
            let a: number;
            let f: number;
            a = val - valok;
            f = Math.abs(valok / a);
            const msg: string = javaemul.internal.StringHelper.format("%s: %s want %.20g got %.20g (1/%.3g)\n", func, test, valok, val, f);
            if (JSOFATest.verbose && Math.abs(a) <= dval){
                console.info("passed: " + msg);
            }
            return;
        }

        public t_a2af() {
            const idmsf: number[] = [0, 0, 0, 0];
            let s: string;
            s = jauA2af(4, 2.345, idmsf);
            this.viv((s).charCodeAt(0), ('+').charCodeAt(0), "jauA2af", "s");
            this.viv(idmsf[0], 134, "jauA2af", "0");
            this.viv(idmsf[1], 21, "jauA2af", "1");
            this.viv(idmsf[2], 30, "jauA2af", "2");
            this.viv(idmsf[3], 9706, "jauA2af", "3");
        }

        public t_a2tf() {
            const ihmsf: number[] = [0, 0, 0, 0];
            let s: string;
            s = jauA2tf(4, -3.01234, ihmsf);
            this.viv((s).charCodeAt(0), ('-').charCodeAt(0), "jauA2tf", "s");
            this.viv(ihmsf[0], 11, "jauA2tf", "0");
            this.viv(ihmsf[1], 30, "jauA2tf", "1");
            this.viv(ihmsf[2], 22, "jauA2tf", "2");
            this.viv(ihmsf[3], 6484, "jauA2tf", "3");
        }

        public t_anp() {
            this.vvd(jauAnp(-0.1), 6.183185307179587, 1.0E-12, "jauAnp", "");
        }

        public t_anpm() {
            this.vvd(jauAnpm(-4.0), 2.2831853071795867, 1.0E-12, "jauAnpm", "");
        }

        public t_bi00() {
            const ret: JSOFA.FrameBias = jauBi00();
            this.vvd(ret.dpsibi, -2.0253091528350866E-7, 1.0E-12, "jauBi00", "dpsibi");
            this.vvd(ret.depsbi, -3.3060414542221477E-8, 1.0E-12, "jauBi00", "depsbi");
            this.vvd(ret.dra, -7.078279744199226E-8, 1.0E-12, "jauBi00", "dra");
        }

        public t_bp00() {
            const rb: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            const rp: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            const rbp: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            jauBp00(2400000.5, 50123.9999, rb, rp, rbp);
            this.vvd(rb[0][0], 0.9999999999999942, 1.0E-12, "jauBp00", "rb11");
            this.vvd(rb[0][1], -7.078279744199197E-8, 1.0E-16, "jauBp00", "rb12");
            this.vvd(rb[0][2], 8.056217146976134E-8, 1.0E-16, "jauBp00", "rb13");
            this.vvd(rb[1][0], 7.078279477857338E-8, 1.0E-16, "jauBp00", "rb21");
            this.vvd(rb[1][1], 0.999999999999997, 1.0E-12, "jauBp00", "rb22");
            this.vvd(rb[1][2], 3.3060414542221364E-8, 1.0E-16, "jauBp00", "rb23");
            this.vvd(rb[2][0], -8.056217380986972E-8, 1.0E-16, "jauBp00", "rb31");
            this.vvd(rb[2][1], -3.3060408839805523E-8, 1.0E-16, "jauBp00", "rb32");
            this.vvd(rb[2][2], 0.9999999999999962, 1.0E-12, "jauBp00", "rb33");
            this.vvd(rp[0][0], 0.9999995504864049, 1.0E-12, "jauBp00", "rp11");
            this.vvd(rp[0][1], 8.696113836207084E-4, 1.0E-14, "jauBp00", "rp12");
            this.vvd(rp[0][2], 3.778928813389333E-4, 1.0E-14, "jauBp00", "rp13");
            this.vvd(rp[1][0], -8.696113818227266E-4, 1.0E-14, "jauBp00", "rp21");
            this.vvd(rp[1][1], 0.9999996218879366, 1.0E-12, "jauBp00", "rp22");
            this.vvd(rp[1][2], -1.6906792630092422E-7, 1.0E-14, "jauBp00", "rp23");
            this.vvd(rp[2][0], -3.778928854764695E-4, 1.0E-14, "jauBp00", "rp31");
            this.vvd(rp[2][1], -1.5955210041952866E-7, 1.0E-14, "jauBp00", "rp32");
            this.vvd(rp[2][2], 0.9999999285984683, 1.0E-12, "jauBp00", "rp33");
            this.vvd(rbp[0][0], 0.9999995505175088, 1.0E-12, "jauBp00", "rbp11");
            this.vvd(rbp[0][1], 8.695405883617885E-4, 1.0E-14, "jauBp00", "rbp12");
            this.vvd(rbp[0][2], 3.779734722239007E-4, 1.0E-14, "jauBp00", "rbp13");
            this.vvd(rbp[1][0], -8.695405990410864E-4, 1.0E-14, "jauBp00", "rbp21");
            this.vvd(rbp[1][1], 0.9999996219494925, 1.0E-12, "jauBp00", "rbp22");
            this.vvd(rbp[1][2], -1.360775820404982E-7, 1.0E-14, "jauBp00", "rbp23");
            this.vvd(rbp[2][0], -3.779734476558185E-4, 1.0E-14, "jauBp00", "rbp31");
            this.vvd(rbp[2][1], -1.925857585832024E-7, 1.0E-14, "jauBp00", "rbp32");
            this.vvd(rbp[2][2], 0.9999999285680153, 1.0E-12, "jauBp00", "rbp33");
        }

        public t_bp06() {
            const rb: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            const rp: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            const rbp: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            jauBp06(2400000.5, 50123.9999, rb, rp, rbp);
            this.vvd(rb[0][0], 0.9999999999999942, 1.0E-12, "jauBp06", "rb11");
            this.vvd(rb[0][1], -7.078368960971557E-8, 1.0E-14, "jauBp06", "rb12");
            this.vvd(rb[0][2], 8.056213977613186E-8, 1.0E-14, "jauBp06", "rb13");
            this.vvd(rb[1][0], 7.078368694637675E-8, 1.0E-14, "jauBp06", "rb21");
            this.vvd(rb[1][1], 0.999999999999997, 1.0E-12, "jauBp06", "rb22");
            this.vvd(rb[1][2], 3.305943742989134E-8, 1.0E-14, "jauBp06", "rb23");
            this.vvd(rb[2][0], -8.056214211620056E-8, 1.0E-14, "jauBp06", "rb31");
            this.vvd(rb[2][1], -3.305943172740587E-8, 1.0E-14, "jauBp06", "rb32");
            this.vvd(rb[2][2], 0.9999999999999962, 1.0E-12, "jauBp06", "rb33");
            this.vvd(rp[0][0], 0.999999550486496, 1.0E-12, "jauBp06", "rp11");
            this.vvd(rp[0][1], 8.696112578855405E-4, 1.0E-14, "jauBp06", "rp12");
            this.vvd(rp[0][2], 3.77892929334139E-4, 1.0E-14, "jauBp06", "rp13");
            this.vvd(rp[1][0], -8.696112560510187E-4, 1.0E-14, "jauBp06", "rp21");
            this.vvd(rp[1][1], 0.9999996218880459, 1.0E-12, "jauBp06", "rp22");
            this.vvd(rp[1][2], -1.6916461689418963E-7, 1.0E-14, "jauBp06", "rp23");
            this.vvd(rp[2][0], -3.778929335557603E-4, 1.0E-14, "jauBp06", "rp31");
            this.vvd(rp[2][1], -1.594554040786495E-7, 1.0E-14, "jauBp06", "rp32");
            this.vvd(rp[2][2], 0.9999999285984501, 1.0E-12, "jauBp06", "rp33");
            this.vvd(rbp[0][0], 0.9999995505176007, 1.0E-12, "jauBp06", "rbp11");
            this.vvd(rbp[0][1], 8.695404617348209E-4, 1.0E-14, "jauBp06", "rbp12");
            this.vvd(rbp[0][2], 3.779735201865589E-4, 1.0E-14, "jauBp06", "rbp13");
            this.vvd(rbp[1][0], -8.695404723772031E-4, 1.0E-14, "jauBp06", "rbp21");
            this.vvd(rbp[1][1], 0.9999996219496027, 1.0E-12, "jauBp06", "rbp22");
            this.vvd(rbp[1][2], -1.3617524970802702E-7, 1.0E-14, "jauBp06", "rbp23");
            this.vvd(rbp[2][0], -3.7797349570340897E-4, 1.0E-14, "jauBp06", "rbp31");
            this.vvd(rbp[2][1], -1.924880847894457E-7, 1.0E-14, "jauBp06", "rbp32");
            this.vvd(rbp[2][2], 0.9999999285679972, 1.0E-12, "jauBp06", "rbp33");
        }

        public t_bpn2xy() {
            const rbpn: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            rbpn[0][0] = 0.9999962358680738;
            rbpn[0][1] = -0.002516417057665452;
            rbpn[0][2] = -0.00109356978534237;
            rbpn[1][0] = 0.002516462370370876;
            rbpn[1][1] = 0.9999968329010883;
            rbpn[1][2] = 4.00615958735831E-5;
            rbpn[2][0] = 0.001093465510215479;
            rbpn[2][1] = -4.281337229063151E-5;
            rbpn[2][2] = 0.9999994012499173;
            const ret: JSOFA.CelestialIntermediatePole = jauBpn2xy(rbpn);
            this.vvd(ret.x, 0.001093465510215479, 1.0E-12, "jauBpn2xy", "x");
            this.vvd(ret.y, -4.281337229063151E-5, 1.0E-12, "jauBpn2xy", "y");
        }

        public t_c2i00a() {
            let rc2i: number[][];
            rc2i = jauC2i00a(2400000.5, 53736.0);
            this.vvd(rc2i[0][0], 0.9999998323037166, 1.0E-12, "jauC2i00a", "11");
            this.vvd(rc2i[0][1], 5.58152634899214E-10, 1.0E-12, "jauC2i00a", "12");
            this.vvd(rc2i[0][2], -5.791308477073444E-4, 1.0E-12, "jauC2i00a", "13");
            this.vvd(rc2i[1][0], -2.3842662278707524E-8, 1.0E-12, "jauC2i00a", "21");
            this.vvd(rc2i[1][1], 0.9999999991917405, 1.0E-12, "jauC2i00a", "22");
            this.vvd(rc2i[1][2], -4.02059495502821E-5, 1.0E-12, "jauC2i00a", "23");
            this.vvd(rc2i[2][0], 5.791308472168153E-4, 1.0E-12, "jauC2i00a", "31");
            this.vvd(rc2i[2][1], 4.0205956615915E-5, 1.0E-12, "jauC2i00a", "32");
            this.vvd(rc2i[2][2], 0.9999998314954572, 1.0E-12, "jauC2i00a", "33");
        }

        public t_c2i00b() {
            let rc2i: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            rc2i = jauC2i00b(2400000.5, 53736.0);
            this.vvd(rc2i[0][0], 0.9999998323040954, 1.0E-12, "jauC2i00b", "11");
            this.vvd(rc2i[0][1], 5.581526349131824E-10, 1.0E-12, "jauC2i00b", "12");
            this.vvd(rc2i[0][2], -5.791301934855394E-4, 1.0E-12, "jauC2i00b", "13");
            this.vvd(rc2i[1][0], -2.3842392854991757E-8, 1.0E-12, "jauC2i00b", "21");
            this.vvd(rc2i[1][1], 0.9999999991917574, 1.0E-12, "jauC2i00b", "22");
            this.vvd(rc2i[1][2], -4.02055297481903E-5, 1.0E-12, "jauC2i00b", "23");
            this.vvd(rc2i[2][0], 5.791301929950209E-4, 1.0E-12, "jauC2i00b", "31");
            this.vvd(rc2i[2][1], 4.0205536813737205E-5, 1.0E-12, "jauC2i00b", "32");
            this.vvd(rc2i[2][2], 0.999999831495853, 1.0E-12, "jauC2i00b", "33");
        }

        public t_c2i06a() {
            let rc2i: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            rc2i = jauC2i06a(2400000.5, 53736.0);
            this.vvd(rc2i[0][0], 0.9999998323037159, 1.0E-12, "jauC2i06a", "11");
            this.vvd(rc2i[0][1], 5.581121329587614E-10, 1.0E-12, "jauC2i06a", "12");
            this.vvd(rc2i[0][2], -5.79130848774053E-4, 1.0E-12, "jauC2i06a", "13");
            this.vvd(rc2i[1][0], -2.3842531694523067E-8, 1.0E-12, "jauC2i06a", "21");
            this.vvd(rc2i[1][1], 0.9999999991917468, 1.0E-12, "jauC2i06a", "22");
            this.vvd(rc2i[1][2], -4.020579392895682E-5, 1.0E-12, "jauC2i06a", "23");
            this.vvd(rc2i[2][0], 5.791308482835292E-4, 1.0E-12, "jauC2i06a", "31");
            this.vvd(rc2i[2][1], 4.0205800994540205E-5, 1.0E-12, "jauC2i06a", "32");
            this.vvd(rc2i[2][2], 0.9999998314954629, 1.0E-12, "jauC2i06a", "33");
        }

        public t_c2ibpn() {
            const rbpn: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            let rc2i: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            rbpn[0][0] = 0.9999962358680738;
            rbpn[0][1] = -0.002516417057665452;
            rbpn[0][2] = -0.00109356978534237;
            rbpn[1][0] = 0.002516462370370876;
            rbpn[1][1] = 0.9999968329010883;
            rbpn[1][2] = 4.00615958735831E-5;
            rbpn[2][0] = 0.001093465510215479;
            rbpn[2][1] = -4.281337229063151E-5;
            rbpn[2][2] = 0.9999994012499173;
            rc2i = jauC2ibpn(2400000.5, 50123.9999, rbpn);
            this.vvd(rc2i[0][0], 0.999999402166409, 1.0E-12, "jauC2ibpn", "11");
            this.vvd(rc2i[0][1], -3.869195948017504E-9, 1.0E-12, "jauC2ibpn", "12");
            this.vvd(rc2i[0][2], -0.0010934655113832852, 1.0E-12, "jauC2ibpn", "13");
            this.vvd(rc2i[1][0], 5.068413965715446E-8, 1.0E-12, "jauC2ibpn", "21");
            this.vvd(rc2i[1][1], 0.9999999990835076, 1.0E-12, "jauC2ibpn", "22");
            this.vvd(rc2i[1][2], 4.281334246452709E-5, 1.0E-12, "jauC2ibpn", "23");
            this.vvd(rc2i[2][0], 0.001093465510215479, 1.0E-12, "jauC2ibpn", "31");
            this.vvd(rc2i[2][1], -4.281337229063151E-5, 1.0E-12, "jauC2ibpn", "32");
            this.vvd(rc2i[2][2], 0.9999994012499173, 1.0E-12, "jauC2ibpn", "33");
        }

        public t_c2ixy() {
            let x: number;
            let y: number;
            let rc2i: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            x = 5.791308486706011E-4;
            y = 4.020579816732961E-5;
            rc2i = jauC2ixy(2400000.5, 53736, x, y);
            this.vvd(rc2i[0][0], 0.9999998323037157, 1.0E-12, "jauC2ixy", "11");
            this.vvd(rc2i[0][1], 5.581526349032241E-10, 1.0E-12, "jauC2ixy", "12");
            this.vvd(rc2i[0][2], -5.791308491611264E-4, 1.0E-12, "jauC2ixy", "13");
            this.vvd(rc2i[1][0], -2.384257057469843E-8, 1.0E-12, "jauC2ixy", "21");
            this.vvd(rc2i[1][1], 0.9999999991917469, 1.0E-12, "jauC2ixy", "22");
            this.vvd(rc2i[1][2], -4.0205791101723245E-5, 1.0E-12, "jauC2ixy", "23");
            this.vvd(rc2i[2][0], 5.791308486706011E-4, 1.0E-12, "jauC2ixy", "31");
            this.vvd(rc2i[2][1], 4.020579816732961E-5, 1.0E-12, "jauC2ixy", "32");
            this.vvd(rc2i[2][2], 0.9999998314954628, 1.0E-12, "jauC2ixy", "33");
        }

        public t_c2ixys() {
            let x: number;
            let y: number;
            let s: number;
            let rc2i: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            x = 5.791308486706011E-4;
            y = 4.020579816732961E-5;
            s = -1.220040848472272E-8;
            rc2i = jauC2ixys(x, y, s);
            this.vvd(rc2i[0][0], 0.9999998323037157, 1.0E-12, "jauC2ixys", "11");
            this.vvd(rc2i[0][1], 5.581984869168499E-10, 1.0E-12, "jauC2ixys", "12");
            this.vvd(rc2i[0][2], -5.791308491611282E-4, 1.0E-12, "jauC2ixys", "13");
            this.vvd(rc2i[1][0], -2.3842616426704402E-8, 1.0E-12, "jauC2ixys", "21");
            this.vvd(rc2i[1][1], 0.9999999991917469, 1.0E-12, "jauC2ixys", "22");
            this.vvd(rc2i[1][2], -4.020579110169669E-5, 1.0E-12, "jauC2ixys", "23");
            this.vvd(rc2i[2][0], 5.791308486706011E-4, 1.0E-12, "jauC2ixys", "31");
            this.vvd(rc2i[2][1], 4.020579816732961E-5, 1.0E-12, "jauC2ixys", "32");
            this.vvd(rc2i[2][2], 0.9999998314954628, 1.0E-12, "jauC2ixys", "33");
        }

        public t_c2s() {
            const p: number[] = [0, 0, 0];
            p[0] = 100.0;
            p[1] = -50.0;
            p[2] = 25.0;
            const ret: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauC2s(p);
            this.vvd(ret.alpha, -0.4636476090008061, 1.0E-14, "jauC2s", "theta");
            this.vvd(ret.delta, 0.21998797739545944, 1.0E-14, "jauC2s", "phi");
        }

        public t_c2t00a() {
            let tta: number;
            let ttb: number;
            let uta: number;
            let utb: number;
            let xp: number;
            let yp: number;
            let rc2t: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            tta = 2400000.5;
            uta = 2400000.5;
            ttb = 53736.0;
            utb = 53736.0;
            xp = 2.55060238E-7;
            yp = 1.860359247E-6;
            rc2t = jauC2t00a(tta, ttb, uta, utb, xp, yp);
            this.vvd(rc2t[0][0], -0.18103321283071827, 1.0E-12, "jauC2t00a", "11");
            this.vvd(rc2t[0][1], 0.9834769806938458, 1.0E-12, "jauC2t00a", "12");
            this.vvd(rc2t[0][2], 6.555535638688342E-5, 1.0E-12, "jauC2t00a", "13");
            this.vvd(rc2t[1][0], -0.9834768134135985, 1.0E-12, "jauC2t00a", "21");
            this.vvd(rc2t[1][1], -0.18103322036495206, 1.0E-12, "jauC2t00a", "22");
            this.vvd(rc2t[1][2], 5.749801116141057E-4, 1.0E-12, "jauC2t00a", "23");
            this.vvd(rc2t[2][0], 5.773474014081406E-4, 1.0E-12, "jauC2t00a", "31");
            this.vvd(rc2t[2][1], 3.9618323917701634E-5, 1.0E-12, "jauC2t00a", "32");
            this.vvd(rc2t[2][2], 0.9999998325501692, 1.0E-12, "jauC2t00a", "33");
        }

        public t_c2t00b() {
            let tta: number;
            let ttb: number;
            let uta: number;
            let utb: number;
            let xp: number;
            let yp: number;
            let rc2t: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            tta = 2400000.5;
            uta = 2400000.5;
            ttb = 53736.0;
            utb = 53736.0;
            xp = 2.55060238E-7;
            yp = 1.860359247E-6;
            rc2t = jauC2t00b(tta, ttb, uta, utb, xp, yp);
            this.vvd(rc2t[0][0], -0.1810332128439679, 1.0E-12, "jauC2t00b", "11");
            this.vvd(rc2t[0][1], 0.9834769806913872, 1.0E-12, "jauC2t00b", "12");
            this.vvd(rc2t[0][2], 6.555565082458416E-5, 1.0E-12, "jauC2t00b", "13");
            this.vvd(rc2t[1][0], -0.9834768134115436, 1.0E-12, "jauC2t00b", "21");
            this.vvd(rc2t[1][1], -0.1810332203784002, 1.0E-12, "jauC2t00b", "22");
            this.vvd(rc2t[1][2], 5.749793922030017E-4, 1.0E-12, "jauC2t00b", "23");
            this.vvd(rc2t[2][0], 5.773467471863534E-4, 1.0E-12, "jauC2t00b", "31");
            this.vvd(rc2t[2][1], 3.961790411549945E-5, 1.0E-12, "jauC2t00b", "32");
            this.vvd(rc2t[2][2], 0.9999998325505636, 1.0E-12, "jauC2t00b", "33");
        }

        public t_c2t06a() {
            let tta: number;
            let ttb: number;
            let uta: number;
            let utb: number;
            let xp: number;
            let yp: number;
            let rc2t: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            tta = 2400000.5;
            uta = 2400000.5;
            ttb = 53736.0;
            utb = 53736.0;
            xp = 2.55060238E-7;
            yp = 1.860359247E-6;
            rc2t = jauC2t06a(tta, ttb, uta, utb, xp, yp);
            this.vvd(rc2t[0][0], -0.18103321283058973, 1.0E-12, "jauC2t06a", "11");
            this.vvd(rc2t[0][1], 0.9834769806938592, 1.0E-12, "jauC2t06a", "12");
            this.vvd(rc2t[0][2], 6.555550962998436E-5, 1.0E-12, "jauC2t06a", "13");
            this.vvd(rc2t[1][0], -0.9834768134136215, 1.0E-12, "jauC2t06a", "21");
            this.vvd(rc2t[1][1], -0.1810332203649131, 1.0E-12, "jauC2t06a", "22");
            this.vvd(rc2t[1][2], 5.749800844905594E-4, 1.0E-12, "jauC2t06a", "23");
            this.vvd(rc2t[2][0], 5.773474024748546E-4, 1.0E-12, "jauC2t06a", "31");
            this.vvd(rc2t[2][1], 3.9618168296326906E-5, 1.0E-12, "jauC2t06a", "32");
            this.vvd(rc2t[2][2], 0.9999998325501748, 1.0E-12, "jauC2t06a", "33");
        }

        public t_c2tcio() {
            const rc2i: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            let era: number;
            const rpom: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            let rc2t: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            rc2i[0][0] = 0.9999998323037165;
            rc2i[0][1] = 5.581526271714304E-10;
            rc2i[0][2] = -5.791308477073444E-4;
            rc2i[1][0] = -2.3842662275247223E-8;
            rc2i[1][1] = 0.9999999991917404;
            rc2i[1][2] = -4.020594955030704E-5;
            rc2i[2][0] = 5.791308472168153E-4;
            rc2i[2][1] = 4.0205956615939944E-5;
            rc2i[2][2] = 0.9999998314954572;
            era = 1.75283325530307;
            rpom[0][0] = 0.9999999999999675;
            rpom[0][1] = -1.367174580728847E-11;
            rpom[0][2] = 2.5506023799999727E-7;
            rpom[1][0] = 1.4146249479570297E-11;
            rpom[1][1] = 0.9999999999982695;
            rpom[1][2] = -1.8603592469988663E-6;
            rpom[2][0] = -2.5506023797412153E-7;
            rpom[2][1] = 1.860359247002414E-6;
            rpom[2][2] = 0.999999999998237;
            rc2t = jauC2tcio(rc2i, era, rpom);
            this.vvd(rc2t[0][0], -0.18103321283071105, 1.0E-12, "jauC2tcio", "11");
            this.vvd(rc2t[0][1], 0.983476980693847, 1.0E-12, "jauC2tcio", "12");
            this.vvd(rc2t[0][2], 6.555535638685467E-5, 1.0E-12, "jauC2tcio", "13");
            this.vvd(rc2t[1][0], -0.9834768134135997, 1.0E-12, "jauC2tcio", "21");
            this.vvd(rc2t[1][1], -0.18103322036494485, 1.0E-12, "jauC2tcio", "22");
            this.vvd(rc2t[1][2], 5.749801116141106E-4, 1.0E-12, "jauC2tcio", "23");
            this.vvd(rc2t[2][0], 5.773474014081407E-4, 1.0E-12, "jauC2tcio", "31");
            this.vvd(rc2t[2][1], 3.961832391772659E-5, 1.0E-12, "jauC2tcio", "32");
            this.vvd(rc2t[2][2], 0.9999998325501692, 1.0E-12, "jauC2tcio", "33");
        }

        public t_c2teqx() {
            const rbpn: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            let gst: number;
            const rpom: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            let rc2t: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            rbpn[0][0] = 0.9999989440476104;
            rbpn[0][1] = -0.0013328817612400115;
            rbpn[0][2] = -5.790767434730085E-4;
            rbpn[1][0] = 0.0013328582543089545;
            rbpn[1][1] = 0.9999991109044506;
            rbpn[1][2] = -4.097782710401556E-5;
            rbpn[2][0] = 5.791308472168153E-4;
            rbpn[2][1] = 4.0205956615939944E-5;
            rbpn[2][2] = 0.9999998314954572;
            gst = 1.7541661380407305;
            rpom[0][0] = 0.9999999999999675;
            rpom[0][1] = -1.367174580728847E-11;
            rpom[0][2] = 2.5506023799999727E-7;
            rpom[1][0] = 1.4146249479570297E-11;
            rpom[1][1] = 0.9999999999982695;
            rpom[1][2] = -1.8603592469988663E-6;
            rpom[2][0] = -2.5506023797412153E-7;
            rpom[2][1] = 1.860359247002414E-6;
            rpom[2][2] = 0.999999999998237;
            rc2t = jauC2teqx(rbpn, gst, rpom);
            this.vvd(rc2t[0][0], -0.18103321285286858, 1.0E-12, "jauC2teqx", "11");
            this.vvd(rc2t[0][1], 0.9834769806897685, 1.0E-12, "jauC2teqx", "12");
            this.vvd(rc2t[0][2], 6.555535639982635E-5, 1.0E-12, "jauC2teqx", "13");
            this.vvd(rc2t[1][0], -0.9834768134095211, 1.0E-12, "jauC2teqx", "21");
            this.vvd(rc2t[1][1], -0.18103322038710237, 1.0E-12, "jauC2teqx", "22");
            this.vvd(rc2t[1][2], 5.749801116126439E-4, 1.0E-12, "jauC2teqx", "23");
            this.vvd(rc2t[2][0], 5.77347401408154E-4, 1.0E-12, "jauC2teqx", "31");
            this.vvd(rc2t[2][1], 3.961832391768641E-5, 1.0E-12, "jauC2teqx", "32");
            this.vvd(rc2t[2][2], 0.9999998325501692, 1.0E-12, "jauC2teqx", "33");
        }

        public t_c2tpe() {
            let tta: number;
            let ttb: number;
            let uta: number;
            let utb: number;
            let dpsi: number;
            let deps: number;
            let xp: number;
            let yp: number;
            let rc2t: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            tta = 2400000.5;
            uta = 2400000.5;
            ttb = 53736.0;
            utb = 53736.0;
            deps = 0.409078976335651;
            dpsi = -9.630909107115582E-6;
            xp = 2.55060238E-7;
            yp = 1.860359247E-6;
            rc2t = jauC2tpe(tta, ttb, uta, utb, dpsi, deps, xp, yp);
            this.vvd(rc2t[0][0], -0.18136779957630295, 1.0E-12, "jauC2tpe", "11");
            this.vvd(rc2t[0][1], 0.9023482206891683, 1.0E-12, "jauC2tpe", "12");
            this.vvd(rc2t[0][2], -0.39099029386410855, 1.0E-12, "jauC2tpe", "13");
            this.vvd(rc2t[1][0], -0.9834147641476805, 1.0E-12, "jauC2tpe", "21");
            this.vvd(rc2t[1][1], -0.16598836354349952, 1.0E-12, "jauC2tpe", "22");
            this.vvd(rc2t[1][2], 0.0730976389804282, 1.0E-12, "jauC2tpe", "23");
            this.vvd(rc2t[2][0], 0.0010596854306732153, 1.0E-12, "jauC2tpe", "31");
            this.vvd(rc2t[2][1], 0.3977631855605079, 1.0E-12, "jauC2tpe", "32");
            this.vvd(rc2t[2][2], 0.9174875068792735, 1.0E-12, "jauC2tpe", "33");
        }

        public t_c2txy() {
            let tta: number;
            let ttb: number;
            let uta: number;
            let utb: number;
            let x: number;
            let y: number;
            let xp: number;
            let yp: number;
            let rc2t: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            tta = 2400000.5;
            uta = 2400000.5;
            ttb = 53736.0;
            utb = 53736.0;
            x = 5.791308486706011E-4;
            y = 4.020579816732961E-5;
            xp = 2.55060238E-7;
            yp = 1.860359247E-6;
            rc2t = jauC2txy(tta, ttb, uta, utb, x, y, xp, yp);
            this.vvd(rc2t[0][0], -0.18103321283062793, 1.0E-12, "jauC2txy", "11");
            this.vvd(rc2t[0][1], 0.983476980693852, 1.0E-12, "jauC2txy", "12");
            this.vvd(rc2t[0][2], 6.555551248057666E-5, 1.0E-12, "jauC2txy", "13");
            this.vvd(rc2t[1][0], -0.9834768134136143, 1.0E-12, "jauC2txy", "21");
            this.vvd(rc2t[1][1], -0.18103322036495292, 1.0E-12, "jauC2txy", "22");
            this.vvd(rc2t[1][2], 5.74980084359414E-4, 1.0E-12, "jauC2txy", "23");
            this.vvd(rc2t[2][0], 5.773474028619264E-4, 1.0E-12, "jauC2txy", "31");
            this.vvd(rc2t[2][1], 3.9618165469116246E-5, 1.0E-12, "jauC2txy", "32");
            this.vvd(rc2t[2][2], 0.9999998325501747, 1.0E-12, "jauC2txy", "33");
        }

        public t_cal2jd() {
            try {
                const jd: JSOFA.JulianDate = jauCal2jd(2003, 6, 1);
                this.vvd(jd.djm0, 2400000.5, 0.0, "jauCal2jd", "djm0");
                this.vvd(jd.djm1, 52791.0, 0.0, "jauCal2jd", "djm");
            } catch(e) {
            }
        }

        public t_cp() {
            const p: number[] = [0, 0, 0];
            const c: number[] = [0, 0, 0];
            p[0] = 0.3;
            p[1] = 1.2;
            p[2] = -2.5;
            jauCp(p, c);
            this.vvd(c[0], 0.3, 0.0, "jauCp", "1");
            this.vvd(c[1], 1.2, 0.0, "jauCp", "2");
            this.vvd(c[2], -2.5, 0.0, "jauCp", "3");
        }

        public t_cpv() {
            const pv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            const c: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            pv[0][0] = 0.3;
            pv[0][1] = 1.2;
            pv[0][2] = -2.5;
            pv[1][0] = -0.5;
            pv[1][1] = 3.1;
            pv[1][2] = 0.9;
            jauCpv(pv, c);
            this.vvd(c[0][0], 0.3, 0.0, "jauCpv", "p1");
            this.vvd(c[0][1], 1.2, 0.0, "jauCpv", "p2");
            this.vvd(c[0][2], -2.5, 0.0, "jauCpv", "p3");
            this.vvd(c[1][0], -0.5, 0.0, "jauCpv", "v1");
            this.vvd(c[1][1], 3.1, 0.0, "jauCpv", "v2");
            this.vvd(c[1][2], 0.9, 0.0, "jauCpv", "v3");
        }

        public t_cr() {
            const r: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            const c: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
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
            this.vvd(c[0][0], 2.0, 0.0, "jauCr", "11");
            this.vvd(c[0][1], 3.0, 0.0, "jauCr", "12");
            this.vvd(c[0][2], 2.0, 0.0, "jauCr", "13");
            this.vvd(c[1][0], 3.0, 0.0, "jauCr", "21");
            this.vvd(c[1][1], 2.0, 0.0, "jauCr", "22");
            this.vvd(c[1][2], 3.0, 0.0, "jauCr", "23");
            this.vvd(c[2][0], 3.0, 0.0, "jauCr", "31");
            this.vvd(c[2][1], 4.0, 0.0, "jauCr", "32");
            this.vvd(c[2][2], 5.0, 0.0, "jauCr", "33");
        }

        public t_d2tf() {
            const ihmsf: number[] = [0, 0, 0, 0];
            let s: string;
            s = jauD2tf(4, -0.987654321, ihmsf);
            this.viv((s).charCodeAt(0), ('-').charCodeAt(0), "jauD2tf", "s");
            this.viv(ihmsf[0], 23, "jauD2tf", "0");
            this.viv(ihmsf[1], 42, "jauD2tf", "1");
            this.viv(ihmsf[2], 13, "jauD2tf", "2");
            this.viv(ihmsf[3], 3333, "jauD2tf", "3");
        }

        public t_dat() {
            let deltat: number;
            const nls: number = org.jastronomy.jsofa.JSOFA.leapSeconds_$LI$().length;
            this.viv(nls, 42, "jauDat", "number of leap second entries");
            this.viv(org.jastronomy.jsofa.JSOFA.leapSeconds_$LI$()[nls - 1].iyear, 2017, "jauDat", "year of last leap second");
            this.viv(org.jastronomy.jsofa.JSOFA.leapSeconds_$LI$()[nls - 1].month, 1, "jauDat", "month of last leap second");
            try {
                deltat = jauDat(2003, 6, 1, 0.0);
                this.vvd(deltat, 32.0, 0.0, "jauDat", "d1");
            } catch(e) {
            }
            try {
                deltat = jauDat(2008, 1, 17, 0.0);
                this.vvd(deltat, 33.0, 0.0, "jauDat", "d2");
            } catch(e) {
            }
            try {
                deltat = jauDat(2017, 9, 1, 0.0);
                this.vvd(deltat, 37.0, 0.0, "jauDat", "d3");
            } catch(e) {
            }
        }

        public t_dtdb() {
            let dtdb: number;
            dtdb = jauDtdb(2448939.5, 0.123, 0.76543, 5.0123, 5525.242, 3190.0);
            this.vvd(dtdb, -0.001280368005936999, 1.0E-15, "jauDtdb", "");
        }

        public t_ee00() {
            let epsa: number;
            let dpsi: number;
            let ee: number;
            epsa = 0.409078976335651;
            dpsi = -9.630909107115582E-6;
            ee = jauEe00(2400000.5, 53736.0, epsa, dpsi);
            this.vvd(ee, -8.834193235367966E-6, 1.0E-18, "jauEe00", "");
        }

        public t_ee00a() {
            let ee: number;
            ee = jauEe00a(2400000.5, 53736.0);
            this.vvd(ee, -8.834192459222587E-6, 1.0E-18, "jauEe00a", "");
        }

        public t_ee00b() {
            let ee: number;
            ee = jauEe00b(2400000.5, 53736.0);
            this.vvd(ee, -8.835700060003032E-6, 1.0E-18, "jauEe00b", "");
        }

        public t_ee06a() {
            let ee: number;
            ee = jauEe06a(2400000.5, 53736.0);
            this.vvd(ee, -8.83419507204379E-6, 1.0E-15, "jauEe06a", "");
        }

        public t_eect00() {
            let eect: number;
            eect = jauEect00(2400000.5, 53736.0);
            this.vvd(eect, 2.046085004885125E-9, 1.0E-20, "jauEect00", "");
        }

        public t_eform() {
            let ef: JSOFA.ReferenceEllipsoid;
            try {
                ef = jauEform(0);
            } catch(e) {
            }
            try {
                ef = jauEform(1);
                this.vvd(ef.a, 6378137.0, 1.0E-10, "jauEform", "a");
                this.vvd(ef.f, 0.0033528106647474805, 1.0E-18, "jauEform", "f");
                ef = jauEform(2);
                this.vvd(ef.a, 6378137.0, 1.0E-10, "jauEform", "a");
                this.vvd(ef.f, 0.003352810681182319, 1.0E-18, "jauEform", "f");
                ef = jauEform(3);
                this.vvd(ef.a, 6378135.0, 1.0E-10, "jauEform", "a");
                this.vvd(ef.f, 0.003352779454167505, 1.0E-18, "jauEform", "f");
            } catch(e) {
            }
            try {
                ef = jauEform(4);
            } catch(e) {
            }
        }

        public t_eo06a() {
            let eo: number;
            eo = jauEo06a(2400000.5, 53736.0);
            this.vvd(eo, -0.0013328823719418337, 1.0E-15, "jauEo06a", "");
        }

        public t_eors() {
            const rnpb: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            let s: number;
            let eo: number;
            rnpb[0][0] = 0.9999989440476104;
            rnpb[0][1] = -0.0013328817612400115;
            rnpb[0][2] = -5.790767434730085E-4;
            rnpb[1][0] = 0.0013328582543089545;
            rnpb[1][1] = 0.9999991109044506;
            rnpb[1][2] = -4.097782710401556E-5;
            rnpb[2][0] = 5.791308472168153E-4;
            rnpb[2][1] = 4.0205956615939944E-5;
            rnpb[2][2] = 0.9999998314954572;
            s = -1.220040848472272E-8;
            eo = jauEors(rnpb, s);
            this.vvd(eo, -0.0013328827151307446, 1.0E-14, "jauEors", "");
        }

        public t_epb() {
            let epb: number;
            epb = jauEpb(2415019.8135, 30103.18648);
            this.vvd(epb, 1982.4184241592786, 1.0E-12, "jauEpb", "");
        }

        public t_epb2jd() {
            let epb: number;
            epb = 1957.3;
            const jd: JSOFA.JulianDate = jauEpb2jd(epb);
            this.vvd(jd.djm0, 2400000.5, 1.0E-9, "jauEpb2jd", "djm0");
            this.vvd(jd.djm1, 35948.1915101513, 1.0E-9, "jauEpb2jd", "mjd");
        }

        public t_epj() {
            let epj: number;
            epj = jauEpj(2451545, -7392.5);
            this.vvd(epj, 1979.7604380561258, 1.0E-12, "jauEpj", "");
        }

        public t_epj2jd() {
            let epj: number;
            epj = 1996.8;
            const jd: JSOFA.JulianDate = jauEpj2jd(epj);
            this.vvd(jd.djm0, 2400000.5, 1.0E-9, "jauEpj2jd", "djm0");
            this.vvd(jd.djm1, 50375.7, 1.0E-9, "jauEpj2jd", "mjd");
        }

        public t_epv00() {
            const pvh: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            const pvb: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            const j: number = jauEpv00(2400000.5, 53411.52501161, pvh, pvb);
            this.vvd(pvh[0][0], -0.7757238809297706, 1.0E-14, "jauEpv00", "ph(x)");
            this.vvd(pvh[0][1], 0.559805224136334, 1.0E-14, "jauEpv00", "ph(y)");
            this.vvd(pvh[0][2], 0.2426998466481687, 1.0E-14, "jauEpv00", "ph(z)");
            this.vvd(pvh[1][0], -0.010918918241473138, 1.0E-15, "jauEpv00", "vh(x)");
            this.vvd(pvh[1][1], -0.01247187268440845, 1.0E-15, "jauEpv00", "vh(y)");
            this.vvd(pvh[1][2], -0.005407569418065039, 1.0E-15, "jauEpv00", "vh(z)");
            this.vvd(pvb[0][0], -0.7714104440491112, 1.0E-14, "jauEpv00", "pb(x)");
            this.vvd(pvb[0][1], 0.5598412061824172, 1.0E-14, "jauEpv00", "pb(y)");
            this.vvd(pvb[0][2], 0.24259962777224525, 1.0E-14, "jauEpv00", "pb(z)");
            this.vvd(pvb[1][0], -0.010918742681168233, 1.0E-15, "jauEpv00", "vb(x)");
            this.vvd(pvb[1][1], -0.012465254617328616, 1.0E-15, "jauEpv00", "vb(y)");
            this.vvd(pvb[1][2], -0.0054047731809662315, 1.0E-15, "jauEpv00", "vb(z)");
            this.viv(j, 0, "jauEpv00", "j");
        }

        public t_eqeq94() {
            let eqeq: number;
            eqeq = jauEqeq94(2400000.5, 41234.0);
            this.vvd(eqeq, 5.357758254609257E-5, 1.0E-17, "jauEqeq94", "");
        }

        public t_era00() {
            let era00: number;
            era00 = jauEra00(2400000.5, 54388.0);
            this.vvd(era00, 0.4022837240028158, 1.0E-12, "jauEra00", "");
        }

        public t_fad03() {
            this.vvd(jauFad03(0.8), 1.9467092053969257, 1.0E-12, "jauFad03", "");
        }

        public t_fae03() {
            this.vvd(jauFae03(0.8), 1.7447137389130818, 1.0E-12, "jauFae03", "");
        }

        public t_faf03() {
            this.vvd(jauFaf03(0.8), 0.25977113667454993, 1.0E-12, "jauFaf03", "");
        }

        public t_faju03() {
            this.vvd(jauFaju03(0.8), 5.275711665202481, 1.0E-12, "jauFaju03", "");
        }

        public t_fal03() {
            this.vvd(jauFal03(0.8), 5.132369751108684, 1.0E-12, "jauFal03", "");
        }

        public t_falp03() {
            this.vvd(jauFalp03(0.8), 6.226797973505508, 1.0E-12, "jauFalp03", "");
        }

        public t_fama03() {
            this.vvd(jauFama03(0.8), 3.2755068402777816, 1.0E-12, "jauFama03", "");
        }

        public t_fame03() {
            this.vvd(jauFame03(0.8), 5.417338184297289, 1.0E-12, "jauFame03", "");
        }

        public t_fane03() {
            this.vvd(jauFane03(0.8), 2.0793438308604135, 1.0E-12, "jauFane03", "");
        }

        public t_faom03() {
            this.vvd(jauFaom03(0.8), -5.973618440951302, 1.0E-12, "jauFaom03", "");
        }

        public t_fapa03() {
            this.vvd(jauFapa03(0.8), 0.0195088476224, 1.0E-12, "jauFapa03", "");
        }

        public t_fasa03() {
            this.vvd(jauFasa03(0.8), 5.371574539440827, 1.0E-12, "jauFasa03", "");
        }

        public t_faur03() {
            this.vvd(jauFaur03(0.8), 5.180636450180414, 1.0E-12, "jauFaur03", "");
        }

        public t_fave03() {
            this.vvd(jauFave03(0.8), 3.424900460533758, 1.0E-12, "jauFave03", "");
        }

        public t_fk52h() {
            let r5: number;
            let d5: number;
            let dr5: number;
            let dd5: number;
            let px5: number;
            let rv5: number;
            r5 = 1.76779433;
            d5 = -0.2917517103;
            dr5 = -1.91851572E-7;
            dd5 = -5.8468475E-6;
            px5 = 0.37921;
            rv5 = -7.6;
            const cat: org.jastronomy.jsofa.JSOFA.CatalogCoords = jauFk52h(r5, d5, dr5, dd5, px5, rv5);
            this.vvd(cat.pos.alpha, 1.7677942262999475, 1.0E-14, "jauFk52h", "ra");
            this.vvd(cat.pos.delta, -0.29175160705303915, 1.0E-14, "jauFk52h", "dec");
            this.vvd(cat.pm.alpha, -1.9618741256057212E-7, 1.0E-19, "jauFk52h", "dr5");
            this.vvd(cat.pm.delta, -5.845990517669391E-6, 1.0E-19, "jauFk52h", "dd5");
            this.vvd(cat.px, 0.37921, 1.0E-14, "jauFk52h", "px");
            this.vvd(cat.rv, -7.600000094000025, 1.0E-11, "jauFk52h", "rv");
        }

        public t_fk5hip() {
            const r5h: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            const s5h: number[] = [0, 0, 0];
            jauFk5hip(r5h, s5h);
            this.vvd(r5h[0][0], 0.9999999999999929, 1.0E-14, "jauFk5hip", "11");
            this.vvd(r5h[0][1], 1.1102233510229197E-7, 1.0E-17, "jauFk5hip", "12");
            this.vvd(r5h[0][2], 4.4118039625365584E-8, 1.0E-17, "jauFk5hip", "13");
            this.vvd(r5h[1][0], -1.1102233084587465E-7, 1.0E-17, "jauFk5hip", "21");
            this.vvd(r5h[1][1], 0.9999999999999892, 1.0E-14, "jauFk5hip", "22");
            this.vvd(r5h[1][2], -9.647792498984142E-8, 1.0E-17, "jauFk5hip", "23");
            this.vvd(r5h[2][0], -4.411805033656962E-8, 1.0E-17, "jauFk5hip", "31");
            this.vvd(r5h[2][1], 9.647792009175315E-8, 1.0E-17, "jauFk5hip", "32");
            this.vvd(r5h[2][2], 0.9999999999999943, 1.0E-14, "jauFk5hip", "33");
            this.vvd(s5h[0], -1.454441043328608E-9, 1.0E-17, "jauFk5hip", "s1");
            this.vvd(s5h[1], 2.908882086657216E-9, 1.0E-17, "jauFk5hip", "s2");
            this.vvd(s5h[2], 3.393695767766752E-9, 1.0E-17, "jauFk5hip", "s3");
        }

        public t_fk5hz() {
            let r5: number;
            let d5: number;
            r5 = 1.76779433;
            d5 = -0.2917517103;
            const pos: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauFk5hz(r5, d5, 2400000.5, 54479.0);
            this.vvd(pos.alpha, 1.767794191464424, 1.0E-12, "jauFk5hz", "ra");
            this.vvd(pos.delta, -0.29175160016798846, 1.0E-12, "jauFk5hz", "dec");
        }

        public t_fw2m() {
            let gamb: number;
            let phib: number;
            let psi: number;
            let eps: number;
            let r: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            gamb = -2.2433876709979924E-6;
            phib = 0.4091014602391313;
            psi = -9.501954178013015E-4;
            eps = 0.40910143165873675;
            r = jauFw2m(gamb, phib, psi, eps);
            this.vvd(r[0][0], 0.9999995505176007, 1.0E-12, "jauFw2m", "11");
            this.vvd(r[0][1], 8.695404617348192E-4, 1.0E-12, "jauFw2m", "12");
            this.vvd(r[0][2], 3.7797352018655825E-4, 1.0E-12, "jauFw2m", "13");
            this.vvd(r[1][0], -8.695404723772016E-4, 1.0E-12, "jauFw2m", "21");
            this.vvd(r[1][1], 0.9999996219496027, 1.0E-12, "jauFw2m", "22");
            this.vvd(r[1][2], -1.3617524968871E-7, 1.0E-12, "jauFw2m", "23");
            this.vvd(r[2][0], -3.7797349570340826E-4, 1.0E-12, "jauFw2m", "31");
            this.vvd(r[2][1], -1.9248808480876157E-7, 1.0E-12, "jauFw2m", "32");
            this.vvd(r[2][2], 0.9999999285679972, 1.0E-12, "jauFw2m", "33");
        }

        public t_fw2xy() {
            let gamb: number;
            let phib: number;
            let psi: number;
            let eps: number;
            gamb = -2.2433876709979924E-6;
            phib = 0.4091014602391313;
            psi = -9.501954178013015E-4;
            eps = 0.40910143165873675;
            const cip: JSOFA.CelestialIntermediatePole = jauFw2xy(gamb, phib, psi, eps);
            this.vvd(cip.x, -3.7797349570340826E-4, 1.0E-14, "jauFw2xy", "x");
            this.vvd(cip.y, -1.9248808480876157E-7, 1.0E-14, "jauFw2xy", "y");
        }

        public t_gc2gd() {
            const xyz: number[] = [2000000.0, 3000000.0, 5244000.0];
            let geo: JSOFA.GeodeticCoord;
            try {
                geo = jauGc2gd(0, xyz);
            } catch(e1) {
            }
            try {
                geo = jauGc2gd(1, xyz);
                this.vvd(geo.elong, 0.982793723247329, 1.0E-14, "jauGc2gd", "e1");
                this.vvd(geo.phi, 0.9716018481907546, 1.0E-14, "jauGc2gd", "p1");
                this.vvd(geo.height, 331.417246142606, 1.0E-8, "jauGc2gd", "h1");
                geo = jauGc2gd(2, xyz);
                this.vvd(geo.elong, 0.982793723247329, 1.0E-14, "jauGc2gd", "e2");
                this.vvd(geo.phi, 0.9716018482060785, 1.0E-14, "jauGc2gd", "p2");
                this.vvd(geo.height, 331.41731754844346, 1.0E-8, "jauGc2gd", "h2");
                geo = jauGc2gd(3, xyz);
                this.vvd(geo.elong, 0.982793723247329, 1.0E-14, "jauGc2gd", "e3");
                this.vvd(geo.phi, 0.9716018181101512, 1.0E-14, "jauGc2gd", "p3");
                this.vvd(geo.height, 333.2770726130318, 1.0E-8, "jauGc2gd", "h3");
            } catch(e1) {
            }
            try {
                geo = jauGc2gd(4, xyz);
            } catch(e1) {
            }
        }

        public t_gc2gde() {
            const a: number = 6378136.0;
            const f: number = 0.0033528;
            const xyz: number[] = [2000000.0, 3000000.0, 5244000.0];
            try {
                const geo: JSOFA.GeodeticCoord = jauGc2gde(a, f, xyz);
                this.vvd(geo.elong, 0.982793723247329, 1.0E-14, "jauGc2gde", "e");
                this.vvd(geo.phi, 0.9716018377570411, 1.0E-14, "jauGc2gde", "p");
                this.vvd(geo.height, 332.368624957644, 1.0E-8, "jauGc2gde", "h");
            } catch(e1) {
            }
        }

        public t_gd2gc() {
            const e: number = 3.1;
            const p: number = -0.5;
            const h: number = 2500.0;
            let xyz: number[] = [0, 0, 0];
            try {
                xyz = jauGd2gc(0, e, p, h);
            } catch(__e) {
                if(__e != null && __e instanceof <any>org.jastronomy.jsofa.JSOFAIllegalParameter) {
                    const e1: org.jastronomy.jsofa.JSOFAIllegalParameter = <org.jastronomy.jsofa.JSOFAIllegalParameter>__e;

                }
                if(__e != null && __e instanceof <any>org.jastronomy.jsofa.JSOFAInternalError) {
                    const e1: org.jastronomy.jsofa.JSOFAInternalError = <org.jastronomy.jsofa.JSOFAInternalError>__e;

                }
            }
            try {
                xyz = jauGd2gc(1, e, p, h);
                this.vvd(xyz[0], -5599000.557704994, 1.0E-7, "jauGd2gc", "0/1");
                this.vvd(xyz[1], 233011.67223479203, 1.0E-7, "jauGd2gc", "1/1");
                this.vvd(xyz[2], -3040909.470698336, 1.0E-7, "jauGd2gc", "2/1");
                xyz = jauGd2gc(2, e, p, h);
                this.vvd(xyz[0], -5599000.557726098, 1.0E-7, "jauGd2gc", "0/2");
                this.vvd(xyz[1], 233011.6722356703, 1.0E-7, "jauGd2gc", "1/2");
                this.vvd(xyz[2], -3040909.4706095476, 1.0E-7, "jauGd2gc", "2/2");
                xyz = jauGd2gc(3, e, p, h);
                this.vvd(xyz[0], -5598998.762630149, 1.0E-7, "jauGd2gc", "0/3");
                this.vvd(xyz[1], 233011.5975297822, 1.0E-7, "jauGd2gc", "1/3");
                this.vvd(xyz[2], -3040908.686146711, 1.0E-7, "jauGd2gc", "2/3");
            } catch(e1) {
            }
            try {
                xyz = jauGd2gc(4, e, p, h);
            } catch(__e) {
                if(__e != null && __e instanceof <any>org.jastronomy.jsofa.JSOFAIllegalParameter) {
                    const e1: org.jastronomy.jsofa.JSOFAIllegalParameter = <org.jastronomy.jsofa.JSOFAIllegalParameter>__e;

                }
                if(__e != null && __e instanceof <any>org.jastronomy.jsofa.JSOFAInternalError) {
                    const e1: org.jastronomy.jsofa.JSOFAInternalError = <org.jastronomy.jsofa.JSOFAInternalError>__e;

                }
            }
        }

        public t_gd2gce() {
            const a: number = 6378136.0;
            const f: number = 0.0033528;
            const e: number = 3.1;
            const p: number = -0.5;
            const h: number = 2500.0;
            let xyz: number[] = [0, 0, 0];
            xyz = jauGd2gce(a, f, e, p, h);
            this.vvd(xyz[0], -5598999.6665116325, 1.0E-7, "jauGd2gce", "0");
            this.vvd(xyz[1], 233011.63514630572, 1.0E-7, "jauGd2gce", "1");
            this.vvd(xyz[2], -3040909.051731413, 1.0E-7, "jauGd2gce", "2");
        }

        public t_gmst00() {
            let theta: number;
            theta = jauGmst00(2400000.5, 53736.0, 2400000.5, 53736.0);
            this.vvd(theta, 1.7541749722107407, 1.0E-12, "jauGmst00", "");
        }

        public t_gmst06() {
            let theta: number;
            theta = jauGmst06(2400000.5, 53736.0, 2400000.5, 53736.0);
            this.vvd(theta, 1.7541749718700912, 1.0E-12, "jauGmst06", "");
        }

        public t_gmst82() {
            let theta: number;
            theta = jauGmst82(2400000.5, 53736.0);
            this.vvd(theta, 1.754174981860675, 1.0E-12, "jauGmst82", "");
        }

        public t_gst00a() {
            let theta: number;
            theta = jauGst00a(2400000.5, 53736.0, 2400000.5, 53736.0);
            this.vvd(theta, 1.7541661380182814, 1.0E-12, "jauGst00a", "");
        }

        public t_gst00b() {
            let theta: number;
            theta = jauGst00b(2400000.5, 53736.0);
            this.vvd(theta, 1.7541661365106807, 1.0E-12, "jauGst00b", "");
        }

        public t_gst06() {
            const rnpb: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            let theta: number;
            rnpb[0][0] = 0.9999989440476104;
            rnpb[0][1] = -0.0013328817612400115;
            rnpb[0][2] = -5.790767434730085E-4;
            rnpb[1][0] = 0.0013328582543089545;
            rnpb[1][1] = 0.9999991109044506;
            rnpb[1][2] = -4.097782710401556E-5;
            rnpb[2][0] = 5.791308472168153E-4;
            rnpb[2][1] = 4.0205956615939944E-5;
            rnpb[2][2] = 0.9999998314954572;
            theta = jauGst06(2400000.5, 53736.0, 2400000.5, 53736.0, rnpb);
            this.vvd(theta, 1.7541661380181677, 1.0E-12, "jauGst06", "");
        }

        public t_gst06a() {
            let theta: number;
            theta = jauGst06a(2400000.5, 53736.0, 2400000.5, 53736.0);
            this.vvd(theta, 1.754166137675019, 1.0E-12, "jauGst06a", "");
        }

        public t_gst94() {
            let theta: number;
            theta = jauGst94(2400000.5, 53736.0);
            this.vvd(theta, 1.7541661360206453, 1.0E-12, "jauGst94", "");
        }

        public t_h2fk5() {
            let rh: number;
            let dh: number;
            let drh: number;
            let ddh: number;
            let pxh: number;
            let rvh: number;
            rh = 1.767794352;
            dh = -0.2917512594;
            drh = -2.76413026E-6;
            ddh = -5.92994449E-6;
            pxh = 0.37921;
            rvh = -7.6;
            const cat: org.jastronomy.jsofa.JSOFA.CatalogCoords = jauH2fk5(rh, dh, drh, ddh, pxh, rvh);
            this.vvd(cat.pos.alpha, 1.7677944557000655, 1.0E-13, "jauH2fk5", "ra");
            this.vvd(cat.pos.delta, -0.2917513626469639, 1.0E-13, "jauH2fk5", "dec");
            this.vvd(cat.pm.alpha, -2.7597945024511204E-6, 1.0E-18, "jauH2fk5", "dr5");
            this.vvd(cat.pm.delta, -5.930801409326283E-6, 1.0E-18, "jauH2fk5", "dd5");
            this.vvd(cat.px, 0.37921, 1.0E-13, "jauH2fk5", "px");
            this.vvd(cat.rv, -7.600000130907112, 2.0E-11, "jauH2fk5", "rv");
        }

        public t_hfk5z() {
            let rh: number;
            let dh: number;
            rh = 1.767794352;
            dh = -0.2917512594;
            const cat: org.jastronomy.jsofa.JSOFA.CatalogCoords = jauHfk5z(rh, dh, 2400000.5, 54479.0);
            this.vvd(cat.pos.alpha, 1.7677944905355811, 1.0E-13, "jauHfk5z", "ra");
            this.vvd(cat.pos.delta, -0.2917513695320114, 1.0E-14, "jauHfk5z", "dec");
            this.vvd(cat.pm.alpha, 4.335890983539243E-9, 1.0E-22, "jauHfk5z", "dr5");
            this.vvd(cat.pm.delta, -8.569648841237746E-10, 1.0E-23, "jauHfk5z", "dd5");
        }

        public t_ir() {
            const r: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
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
            this.vvd(r[0][0], 1.0, 0.0, "jauIr", "11");
            this.vvd(r[0][1], 0.0, 0.0, "jauIr", "12");
            this.vvd(r[0][2], 0.0, 0.0, "jauIr", "13");
            this.vvd(r[1][0], 0.0, 0.0, "jauIr", "21");
            this.vvd(r[1][1], 1.0, 0.0, "jauIr", "22");
            this.vvd(r[1][2], 0.0, 0.0, "jauIr", "23");
            this.vvd(r[2][0], 0.0, 0.0, "jauIr", "31");
            this.vvd(r[2][1], 0.0, 0.0, "jauIr", "32");
            this.vvd(r[2][2], 1.0, 0.0, "jauIr", "33");
        }

        public t_jd2cal() {
            let dj1: number;
            let dj2: number;
            dj1 = 2400000.5;
            dj2 = 50123.9999;
            const cal: JSOFA.Calendar = jauJd2cal(dj1, dj2);
            this.viv(cal.iy, 1996, "jauJd2cal", "y");
            this.viv(cal.im, 2, "jauJd2cal", "m");
            this.viv(cal.id, 10, "jauJd2cal", "d");
            this.vvd(cal.fd, 0.9999, 1.0E-7, "jauJd2cal", "fd");
        }

        public t_jdcalf() {
            let dj1: number;
            let dj2: number;
            const iydmf: number[] = [0, 0, 0, 0];
            let j: number;
            dj1 = 2400000.5;
            dj2 = 50123.9999;
            j = jauJdcalf(4, dj1, dj2, iydmf);
            this.viv(iydmf[0], 1996, "jauJdcalf", "y");
            this.viv(iydmf[1], 2, "jauJdcalf", "m");
            this.viv(iydmf[2], 10, "jauJdcalf", "d");
            this.viv(iydmf[3], 9999, "jauJdcalf", "f");
            this.viv(j, 0, "jauJdcalf", "j");
        }

        public t_num00a() {
            let rmatn: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            rmatn = jauNum00a(2400000.5, 53736.0);
            this.vvd(rmatn[0][0], 0.9999999999536228, 1.0E-12, "jauNum00a", "11");
            this.vvd(rmatn[0][1], 8.836238544090872E-6, 1.0E-12, "jauNum00a", "12");
            this.vvd(rmatn[0][2], 3.830835237722401E-6, 1.0E-12, "jauNum00a", "13");
            this.vvd(rmatn[1][0], -8.83608288079857E-6, 1.0E-12, "jauNum00a", "21");
            this.vvd(rmatn[1][1], 0.9999999991354656, 1.0E-12, "jauNum00a", "22");
            this.vvd(rmatn[1][2], -4.0632408653625E-5, 1.0E-12, "jauNum00a", "23");
            this.vvd(rmatn[2][0], -3.831194272065996E-6, 1.0E-12, "jauNum00a", "31");
            this.vvd(rmatn[2][1], 4.063237480216292E-5, 1.0E-12, "jauNum00a", "32");
            this.vvd(rmatn[2][2], 0.9999999991671661, 1.0E-12, "jauNum00a", "33");
        }

        public t_num00b() {
            let rmatn: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            rmatn = jauNum00b(2400000.5, 53736);
            this.vvd(rmatn[0][0], 0.999999999953607, 1.0E-12, "jauNum00b", "11");
            this.vvd(rmatn[0][1], 8.837746144871248E-6, 1.0E-12, "jauNum00b", "12");
            this.vvd(rmatn[0][2], 3.831488838252203E-6, 1.0E-12, "jauNum00b", "13");
            this.vvd(rmatn[1][0], -8.837590456632305E-6, 1.0E-12, "jauNum00b", "21");
            this.vvd(rmatn[1][1], 0.9999999991354692, 1.0E-12, "jauNum00b", "22");
            this.vvd(rmatn[1][2], -4.0631987985595914E-5, 1.0E-12, "jauNum00b", "23");
            this.vvd(rmatn[2][0], -3.831847930134941E-6, 1.0E-12, "jauNum00b", "31");
            this.vvd(rmatn[2][1], 4.063195412258168E-5, 1.0E-12, "jauNum00b", "32");
            this.vvd(rmatn[2][2], 0.9999999991671806, 1.0E-12, "jauNum00b", "33");
        }

        public t_num06a() {
            let rmatn: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            rmatn = jauNum06a(2400000.5, 53736);
            this.vvd(rmatn[0][0], 0.9999999999536228, 1.0E-12, "jauNum06a", "11");
            this.vvd(rmatn[0][1], 8.836241998111535E-6, 1.0E-12, "jauNum06a", "12");
            this.vvd(rmatn[0][2], 3.8308346084152875E-6, 1.0E-12, "jauNum06a", "13");
            this.vvd(rmatn[1][0], -8.83608633487074E-6, 1.0E-12, "jauNum06a", "21");
            this.vvd(rmatn[1][1], 0.9999999991354658, 1.0E-12, "jauNum06a", "22");
            this.vvd(rmatn[1][2], -4.063240188248455E-5, 1.0E-12, "jauNum06a", "23");
            this.vvd(rmatn[2][0], -3.831193642839398E-6, 1.0E-12, "jauNum06a", "31");
            this.vvd(rmatn[2][1], 4.0632368031014796E-5, 1.0E-12, "jauNum06a", "32");
            this.vvd(rmatn[2][2], 0.9999999991671663, 1.0E-12, "jauNum06a", "33");
        }

        public t_numat() {
            let epsa: number;
            let dpsi: number;
            let deps: number;
            let rmatn: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            epsa = 0.409078976335651;
            dpsi = -9.630909107115582E-6;
            deps = 4.063239174001679E-5;
            rmatn = jauNumat(epsa, dpsi, deps);
            this.vvd(rmatn[0][0], 0.9999999999536228, 1.0E-12, "jauNumat", "11");
            this.vvd(rmatn[0][1], 8.83623932023625E-6, 1.0E-12, "jauNumat", "12");
            this.vvd(rmatn[0][2], 3.830833447458252E-6, 1.0E-12, "jauNumat", "13");
            this.vvd(rmatn[1][0], -8.83608365701669E-6, 1.0E-12, "jauNumat", "21");
            this.vvd(rmatn[1][1], 0.9999999991354654, 1.0E-12, "jauNumat", "22");
            this.vvd(rmatn[1][2], -4.0632408653618574E-5, 1.0E-12, "jauNumat", "23");
            this.vvd(rmatn[2][0], -3.8311924818333855E-6, 1.0E-12, "jauNumat", "31");
            this.vvd(rmatn[2][1], 4.063237480216934E-5, 1.0E-12, "jauNumat", "32");
            this.vvd(rmatn[2][2], 0.9999999991671661, 1.0E-12, "jauNumat", "33");
        }

        public t_nut00a() {
            const nut: JSOFA.NutationTerms = jauNut00a(2400000.5, 53736.0);
            this.vvd(nut.dpsi, -9.630909107115518E-6, 1.0E-13, "jauNut00a", "dpsi");
            this.vvd(nut.deps, 4.063239174001679E-5, 1.0E-13, "jauNut00a", "deps");
        }

        public t_nut00b() {
            const nut: JSOFA.NutationTerms = jauNut00b(2400000.5, 53736.0);
            this.vvd(nut.dpsi, -9.632552291148363E-6, 1.0E-13, "jauNut00b", "dpsi");
            this.vvd(nut.deps, 4.063197106621159E-5, 1.0E-13, "jauNut00b", "deps");
        }

        public t_nut06a() {
            const nut: JSOFA.NutationTerms = jauNut06a(2400000.5, 53736.0);
            this.vvd(nut.dpsi, -9.63091202582031E-6, 1.0E-13, "jauNut06a", "dpsi");
            this.vvd(nut.deps, 4.06323849688725E-5, 1.0E-13, "jauNut06a", "deps");
        }

        public t_nut80() {
            const nut: JSOFA.NutationTerms = jauNut80(2400000.5, 53736.0);
            this.vvd(nut.dpsi, -9.643658353226563E-6, 1.0E-13, "jauNut80", "dpsi");
            this.vvd(nut.deps, 4.060051006879713E-5, 1.0E-13, "jauNut80", "deps");
        }

        public t_nutm80() {
            let rmatn: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            rmatn = jauNutm80(2400000.5, 53736.0);
            this.vvd(rmatn[0][0], 0.9999999999535, 1.0E-12, "jauNutm80", "11");
            this.vvd(rmatn[0][1], 8.847935789636432E-6, 1.0E-12, "jauNutm80", "12");
            this.vvd(rmatn[0][2], 3.8359065021640195E-6, 1.0E-12, "jauNutm80", "13");
            this.vvd(rmatn[1][0], -8.847780042583437E-6, 1.0E-12, "jauNutm80", "21");
            this.vvd(rmatn[1][1], 0.999999999136657, 1.0E-12, "jauNutm80", "22");
            this.vvd(rmatn[1][2], -4.060052702727131E-5, 1.0E-12, "jauNutm80", "23");
            this.vvd(rmatn[2][0], -3.836265729708479E-6, 1.0E-12, "jauNutm80", "31");
            this.vvd(rmatn[2][1], 4.0600493086126384E-5, 1.0E-12, "jauNutm80", "32");
            this.vvd(rmatn[2][2], 0.9999999991684415, 1.0E-12, "jauNutm80", "33");
        }

        public t_obl06() {
            this.vvd(jauObl06(2400000.5, 54388.0), 0.4090749229387258, 1.0E-14, "jauObl06", "");
        }

        public t_obl80() {
            let eps0: number;
            eps0 = jauObl80(2400000.5, 54388.0);
            this.vvd(eps0, 0.4090751347643816, 1.0E-14, "jauObl80", "");
        }

        public t_p06e() {
            const pa: JSOFA.PrecessionAngles = jauP06e(2400000.5, 52541.0);
            this.vvd(pa.eps0, 0.4090926006005829, 1.0E-14, "jauP06e", "eps0");
            this.vvd(pa.psia, 6.664369630191613E-4, 1.0E-14, "jauP06e", "psia");
            this.vvd(pa.oma, 0.4090925973783256, 1.0E-14, "jauP06e", "oma");
            this.vvd(pa.bpa, 5.561149371265209E-7, 1.0E-14, "jauP06e", "bpa");
            this.vvd(pa.bqa, -6.191517193290621E-6, 1.0E-14, "jauP06e", "bqa");
            this.vvd(pa.pia, 6.216441751884383E-6, 1.0E-14, "jauP06e", "pia");
            this.vvd(pa.bpia, 3.0520141800237797, 1.0E-14, "jauP06e", "bpia");
            this.vvd(pa.epsa, 0.4090864054922432, 1.0E-14, "jauP06e", "epsa");
            this.vvd(pa.chia, 1.3877033795309153E-6, 1.0E-14, "jauP06e", "chia");
            this.vvd(pa.za, 2.9217898466517907E-4, 1.0E-14, "jauP06e", "za");
            this.vvd(pa.zetaa, 3.178773290332009E-4, 1.0E-14, "jauP06e", "zetaa");
            this.vvd(pa.thetaa, 2.6509327016574973E-4, 1.0E-14, "jauP06e", "thetaa");
            this.vvd(pa.pa, 6.651637681381016E-4, 1.0E-14, "jauP06e", "pa");
            this.vvd(pa.gam, 1.398077115963755E-6, 1.0E-14, "jauP06e", "gam");
            this.vvd(pa.phi, 0.40908640908374627, 1.0E-14, "jauP06e", "phi");
            this.vvd(pa.psi, 6.664464807480921E-4, 1.0E-14, "jauP06e", "psi");
        }

        public t_p2pv() {
            const p: number[] = [0, 0, 0];
            let pv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            p[0] = 0.25;
            p[1] = 1.2;
            p[2] = 3.0;
            pv[0][0] = 0.3;
            pv[0][1] = 1.2;
            pv[0][2] = -2.5;
            pv[1][0] = -0.5;
            pv[1][1] = 3.1;
            pv[1][2] = 0.9;
            pv = jauP2pv(p);
            this.vvd(pv[0][0], 0.25, 0.0, "jauP2pv", "p1");
            this.vvd(pv[0][1], 1.2, 0.0, "jauP2pv", "p2");
            this.vvd(pv[0][2], 3.0, 0.0, "jauP2pv", "p3");
            this.vvd(pv[1][0], 0.0, 0.0, "jauP2pv", "v1");
            this.vvd(pv[1][1], 0.0, 0.0, "jauP2pv", "v2");
            this.vvd(pv[1][2], 0.0, 0.0, "jauP2pv", "v3");
        }

        public t_p2s() {
            const p: number[] = [0, 0, 0];
            p[0] = 100.0;
            p[1] = -50.0;
            p[2] = 25.0;
            const co: JSOFA.SphericalPosition = jauP2s(p);
            this.vvd(co.theta, -0.4636476090008061, 1.0E-12, "jauP2s", "theta");
            this.vvd(co.phi, 0.21998797739545944, 1.0E-12, "jauP2s", "phi");
            this.vvd(co.r, 114.564392373896, 1.0E-9, "jauP2s", "r");
        }

        public t_pap() {
            const a: number[] = [0, 0, 0];
            const b: number[] = [0, 0, 0];
            let theta: number;
            a[0] = 1.0;
            a[1] = 0.1;
            a[2] = 0.2;
            b[0] = -3.0;
            b[1] = 0.001;
            b[2] = 0.2;
            theta = jauPap(a, b);
            this.vvd(theta, 0.3671514267841114, 1.0E-12, "jauPap", "");
        }

        public t_pas() {
            let al: number;
            let ap: number;
            let bl: number;
            let bp: number;
            let theta: number;
            al = 1.0;
            ap = 0.1;
            bl = 0.2;
            bp = -1.0;
            theta = jauPas(al, ap, bl, bp);
            this.vvd(theta, -2.7245449229322705, 1.0E-12, "jauPas", "");
        }

        public t_pb06() {
            const an: JSOFA.EulerAngles = jauPb06(2400000.5, 50123.9999);
            this.vvd(an.zeta, -5.092634016326479E-4, 1.0E-12, "jauPb06", "bzeta");
            this.vvd(an.z, -3.6027720605660444E-4, 1.0E-12, "jauPb06", "bz");
            this.vvd(an.theta, -3.779735537167811E-4, 1.0E-12, "jauPb06", "btheta");
        }

        public t_pdp() {
            const a: number[] = [0, 0, 0];
            const b: number[] = [0, 0, 0];
            let adb: number;
            a[0] = 2.0;
            a[1] = 2.0;
            a[2] = 3.0;
            b[0] = 1.0;
            b[1] = 3.0;
            b[2] = 4.0;
            adb = jauPdp(a, b);
            this.vvd(adb, 20, 1.0E-12, "jauPdp", "");
        }

        public t_pfw06() {
            const fw: JSOFA.FWPrecessionAngles = jauPfw06(2400000.5, 50123.9999);
            this.vvd(fw.gamb, -2.2433876709979958E-6, 1.0E-16, "jauPfw06", "gamb");
            this.vvd(fw.phib, 0.4091014602391313, 1.0E-12, "jauPfw06", "phib");
            this.vvd(fw.psib, -9.501954178013031E-4, 1.0E-14, "jauPfw06", "psib");
            this.vvd(fw.epsa, 0.40910143165873675, 1.0E-12, "jauPfw06", "epsa");
        }

        public t_plan94() {
            let pv: number[][];
            try {
                pv = jauPlan94(2400000.5, 1000000.0, 0);
            } catch(e) {
            }
            try {
                pv = jauPlan94(2400000.5, 1000000.0, 10);
            } catch(e) {
            }
            try {
                pv = jauPlan94(2400000.5, -320000, 3);
                this.vvd(pv[0][0], 0.9308038666832976, 1.0E-11, "jauPlan94", "x 3");
                this.vvd(pv[0][1], 0.3258319040261346, 1.0E-11, "jauPlan94", "y 3");
                this.vvd(pv[0][2], 0.14227945444811405, 1.0E-11, "jauPlan94", "z 3");
                this.vvd(pv[1][0], -0.00642945895825517, 1.0E-11, "jauPlan94", "xd 3");
                this.vvd(pv[1][1], 0.014685706577042377, 1.0E-11, "jauPlan94", "yd 3");
                this.vvd(pv[1][2], 0.006406996426270981, 1.0E-11, "jauPlan94", "zd 3");
                pv = jauPlan94(2400000.5, 43999.9, 1);
                this.vvd(pv[0][0], 0.29452939592574306, 1.0E-11, "jauPlan94", "x 4");
                this.vvd(pv[0][1], -0.24522041766010497, 1.0E-11, "jauPlan94", "y 4");
                this.vvd(pv[0][2], -0.1615427700571978, 1.0E-11, "jauPlan94", "z 4");
                this.vvd(pv[1][0], 0.014138678714046145, 1.0E-11, "jauPlan94", "xd 4");
                this.vvd(pv[1][1], 0.019465483011047065, 1.0E-11, "jauPlan94", "yd 4");
                this.vvd(pv[1][2], 0.008929809783898904, 1.0E-11, "jauPlan94", "zd 4");
            } catch(e) {
            }
        }

        public t_pmat00() {
            let rbp: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            rbp = jauPmat00(2400000.5, 50123.9999);
            this.vvd(rbp[0][0], 0.9999995505175088, 1.0E-12, "jauPmat00", "11");
            this.vvd(rbp[0][1], 8.695405883617885E-4, 1.0E-14, "jauPmat00", "12");
            this.vvd(rbp[0][2], 3.779734722239007E-4, 1.0E-14, "jauPmat00", "13");
            this.vvd(rbp[1][0], -8.695405990410864E-4, 1.0E-14, "jauPmat00", "21");
            this.vvd(rbp[1][1], 0.9999996219494925, 1.0E-12, "jauPmat00", "22");
            this.vvd(rbp[1][2], -1.360775820404982E-7, 1.0E-14, "jauPmat00", "23");
            this.vvd(rbp[2][0], -3.779734476558185E-4, 1.0E-14, "jauPmat00", "31");
            this.vvd(rbp[2][1], -1.925857585832024E-7, 1.0E-14, "jauPmat00", "32");
            this.vvd(rbp[2][2], 0.9999999285680153, 1.0E-12, "jauPmat00", "33");
        }

        public t_pmat06() {
            let rbp: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            rbp = jauPmat06(2400000.5, 50123.9999);
            this.vvd(rbp[0][0], 0.9999995505176007, 1.0E-12, "jauPmat06", "11");
            this.vvd(rbp[0][1], 8.695404617348209E-4, 1.0E-14, "jauPmat06", "12");
            this.vvd(rbp[0][2], 3.779735201865589E-4, 1.0E-14, "jauPmat06", "13");
            this.vvd(rbp[1][0], -8.695404723772031E-4, 1.0E-14, "jauPmat06", "21");
            this.vvd(rbp[1][1], 0.9999996219496027, 1.0E-12, "jauPmat06", "22");
            this.vvd(rbp[1][2], -1.3617524970802702E-7, 1.0E-14, "jauPmat06", "23");
            this.vvd(rbp[2][0], -3.7797349570340897E-4, 1.0E-14, "jauPmat06", "31");
            this.vvd(rbp[2][1], -1.924880847894457E-7, 1.0E-14, "jauPmat06", "32");
            this.vvd(rbp[2][2], 0.9999999285679972, 1.0E-12, "jauPmat06", "33");
        }

        public t_pmat76() {
            let rmatp: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            rmatp = jauPmat76(2400000.5, 50123.9999);
            this.vvd(rmatp[0][0], 0.9999995504328351, 1.0E-12, "jauPmat76", "11");
            this.vvd(rmatp[0][1], 8.696632209480961E-4, 1.0E-14, "jauPmat76", "12");
            this.vvd(rmatp[0][2], 3.7791534749598884E-4, 1.0E-14, "jauPmat76", "13");
            this.vvd(rmatp[1][0], -8.696632209485112E-4, 1.0E-14, "jauPmat76", "21");
            this.vvd(rmatp[1][1], 0.9999996218428561, 1.0E-12, "jauPmat76", "22");
            this.vvd(rmatp[1][2], -1.6432847761118864E-7, 1.0E-14, "jauPmat76", "23");
            this.vvd(rmatp[2][0], -3.779153474950335E-4, 1.0E-14, "jauPmat76", "31");
            this.vvd(rmatp[2][1], -1.643306746147367E-7, 1.0E-14, "jauPmat76", "32");
            this.vvd(rmatp[2][2], 0.999999928589979, 1.0E-12, "jauPmat76", "33");
        }

        public t_pm() {
            const p: number[] = [0, 0, 0];
            let r: number;
            p[0] = 0.3;
            p[1] = 1.2;
            p[2] = -2.5;
            r = jauPm(p);
            this.vvd(r, 2.7892651361962706, 1.0E-12, "jauPm", "");
        }

        public t_pmp() {
            const a: number[] = [0, 0, 0];
            const b: number[] = [0, 0, 0];
            let amb: number[] = [0, 0, 0];
            a[0] = 2.0;
            a[1] = 2.0;
            a[2] = 3.0;
            b[0] = 1.0;
            b[1] = 3.0;
            b[2] = 4.0;
            amb = jauPmp(a, b);
            this.vvd(amb[0], 1.0, 1.0E-12, "jauPmp", "0");
            this.vvd(amb[1], -1.0, 1.0E-12, "jauPmp", "1");
            this.vvd(amb[2], -1.0, 1.0E-12, "jauPmp", "2");
        }

        public t_pn() {
            const p: number[] = [0, 0, 0];
            p[0] = 0.3;
            p[1] = 1.2;
            p[2] = -2.5;
            const mv: JSOFA.NormalizedVector = jauPn(p);
            this.vvd(mv.r, 2.7892651361962706, 1.0E-12, "jauPn", "r");
            this.vvd(mv.u[0], 0.10755521090731121, 1.0E-12, "jauPn", "u1");
            this.vvd(mv.u[1], 0.43022084362924484, 1.0E-12, "jauPn", "u2");
            this.vvd(mv.u[2], -0.8962934242275934, 1.0E-12, "jauPn", "u3");
        }

        public t_pn00() {
            let dpsi: number;
            let deps: number;
            dpsi = -9.632552291149336E-6;
            deps = 4.0631971066211414E-5;
            const pn: JSOFA.PrecessionNutation = jauPn00(2400000.5, 53736.0, dpsi, deps);
            this.vvd(pn.epsa, 0.409079178940423, 1.0E-12, "jauPn00", "epsa");
            this.vvd(pn.rb[0][0], 0.9999999999999942, 1.0E-12, "jauPn00", "rb11");
            this.vvd(pn.rb[0][1], -7.078279744199197E-8, 1.0E-18, "jauPn00", "rb12");
            this.vvd(pn.rb[0][2], 8.056217146976134E-8, 1.0E-18, "jauPn00", "rb13");
            this.vvd(pn.rb[1][0], 7.078279477857338E-8, 1.0E-18, "jauPn00", "rb21");
            this.vvd(pn.rb[1][1], 0.999999999999997, 1.0E-12, "jauPn00", "rb22");
            this.vvd(pn.rb[1][2], 3.3060414542221364E-8, 1.0E-18, "jauPn00", "rb23");
            this.vvd(pn.rb[2][0], -8.056217380986972E-8, 1.0E-18, "jauPn00", "rb31");
            this.vvd(pn.rb[2][1], -3.3060408839805523E-8, 1.0E-18, "jauPn00", "rb32");
            this.vvd(pn.rb[2][2], 0.9999999999999962, 1.0E-12, "jauPn00", "rb33");
            this.vvd(pn.rp[0][0], 0.9999989300532289, 1.0E-12, "jauPn00", "rp11");
            this.vvd(pn.rp[0][1], -0.0013416472267918243, 1.0E-14, "jauPn00", "rp12");
            this.vvd(pn.rp[0][2], -5.829880927190296E-4, 1.0E-14, "jauPn00", "rp13");
            this.vvd(pn.rp[1][0], 0.001341647231069759, 1.0E-14, "jauPn00", "rp21");
            this.vvd(pn.rp[1][1], 0.999999099990875, 1.0E-12, "jauPn00", "rp22");
            this.vvd(pn.rp[1][2], -3.8374444415837154E-7, 1.0E-14, "jauPn00", "rp23");
            this.vvd(pn.rp[2][0], 5.829880828740958E-4, 1.0E-14, "jauPn00", "rp31");
            this.vvd(pn.rp[2][1], -3.984203267708835E-7, 1.0E-14, "jauPn00", "rp32");
            this.vvd(pn.rp[2][2], 0.9999998300623538, 1.0E-12, "jauPn00", "rp33");
            this.vvd(pn.rbp[0][0], 0.9999989300052244, 1.0E-12, "jauPn00", "rbp11");
            this.vvd(pn.rbp[0][1], -0.0013417179902397037, 1.0E-14, "jauPn00", "rbp12");
            this.vvd(pn.rbp[0][2], -5.829075749891684E-4, 1.0E-14, "jauPn00", "rbp13");
            this.vvd(pn.rbp[1][0], 0.00134171801383174, 1.0E-14, "jauPn00", "rbp21");
            this.vvd(pn.rbp[1][1], 0.9999990998959192, 1.0E-12, "jauPn00", "rbp22");
            this.vvd(pn.rbp[1][2], -3.505759733565421E-7, 1.0E-14, "jauPn00", "rbp23");
            this.vvd(pn.rbp[2][0], 5.829075206857718E-4, 1.0E-14, "jauPn00", "rbp31");
            this.vvd(pn.rbp[2][1], -4.315219955198609E-7, 1.0E-14, "jauPn00", "rbp32");
            this.vvd(pn.rbp[2][2], 0.9999998301093036, 1.0E-12, "jauPn00", "rbp33");
            this.vvd(pn.rn[0][0], 0.999999999953607, 1.0E-12, "jauPn00", "rn11");
            this.vvd(pn.rn[0][1], 8.83774614487214E-6, 1.0E-16, "jauPn00", "rn12");
            this.vvd(pn.rn[0][2], 3.83148883825259E-6, 1.0E-16, "jauPn00", "rn13");
            this.vvd(pn.rn[1][0], -8.837590456633198E-6, 1.0E-16, "jauPn00", "rn21");
            this.vvd(pn.rn[1][1], 0.9999999991354692, 1.0E-12, "jauPn00", "rn22");
            this.vvd(pn.rn[1][2], -4.063198798559574E-5, 1.0E-16, "jauPn00", "rn23");
            this.vvd(pn.rn[2][0], -3.831847930135328E-6, 1.0E-16, "jauPn00", "rn31");
            this.vvd(pn.rn[2][1], 4.0631954122581504E-5, 1.0E-16, "jauPn00", "rn32");
            this.vvd(pn.rn[2][2], 0.9999999991671806, 1.0E-12, "jauPn00", "rn33");
            this.vvd(pn.rbpn[0][0], 0.9999989440499982, 1.0E-12, "jauPn00", "rbpn11");
            this.vvd(pn.rbpn[0][1], -0.0013328802536408484, 1.0E-14, "jauPn00", "rbpn12");
            this.vvd(pn.rbpn[0][2], -5.790760898731087E-4, 1.0E-14, "jauPn00", "rbpn13");
            this.vvd(pn.rbpn[1][0], 0.0013328567469799487, 1.0E-14, "jauPn00", "rbpn21");
            this.vvd(pn.rbpn[1][1], 0.9999991109064769, 1.0E-12, "jauPn00", "rbpn22");
            this.vvd(pn.rbpn[1][2], -4.097740555723064E-5, 1.0E-14, "jauPn00", "rbpn23");
            this.vvd(pn.rbpn[2][0], 5.791301929950205E-4, 1.0E-14, "jauPn00", "rbpn31");
            this.vvd(pn.rbpn[2][1], 4.020553681373703E-5, 1.0E-14, "jauPn00", "rbpn32");
            this.vvd(pn.rbpn[2][2], 0.999999831495853, 1.0E-12, "jauPn00", "rbpn33");
        }

        public t_pn00a() {
            const pn: JSOFA.PrecessionNutation = jauPn00a(2400000.5, 53736.0);
            this.vvd(pn.nut.dpsi, -9.630909107115518E-6, 1.0E-12, "jauPn00a", "dpsi");
            this.vvd(pn.nut.deps, 4.063239174001679E-5, 1.0E-12, "jauPn00a", "deps");
            this.vvd(pn.epsa, 0.409079178940423, 1.0E-12, "jauPn00a", "epsa");
            this.vvd(pn.rb[0][0], 0.9999999999999942, 1.0E-12, "jauPn00a", "rb11");
            this.vvd(pn.rb[0][1], -7.078279744199197E-8, 1.0E-16, "jauPn00a", "rb12");
            this.vvd(pn.rb[0][2], 8.056217146976134E-8, 1.0E-16, "jauPn00a", "rb13");
            this.vvd(pn.rb[1][0], 7.078279477857338E-8, 1.0E-16, "jauPn00a", "rb21");
            this.vvd(pn.rb[1][1], 0.999999999999997, 1.0E-12, "jauPn00a", "rb22");
            this.vvd(pn.rb[1][2], 3.3060414542221364E-8, 1.0E-16, "jauPn00a", "rb23");
            this.vvd(pn.rb[2][0], -8.056217380986972E-8, 1.0E-16, "jauPn00a", "rb31");
            this.vvd(pn.rb[2][1], -3.3060408839805523E-8, 1.0E-16, "jauPn00a", "rb32");
            this.vvd(pn.rb[2][2], 0.9999999999999962, 1.0E-12, "jauPn00a", "rb33");
            this.vvd(pn.rp[0][0], 0.9999989300532289, 1.0E-12, "jauPn00a", "rp11");
            this.vvd(pn.rp[0][1], -0.0013416472267918243, 1.0E-14, "jauPn00a", "rp12");
            this.vvd(pn.rp[0][2], -5.829880927190296E-4, 1.0E-14, "jauPn00a", "rp13");
            this.vvd(pn.rp[1][0], 0.001341647231069759, 1.0E-14, "jauPn00a", "rp21");
            this.vvd(pn.rp[1][1], 0.999999099990875, 1.0E-12, "jauPn00a", "rp22");
            this.vvd(pn.rp[1][2], -3.8374444415837154E-7, 1.0E-14, "jauPn00a", "rp23");
            this.vvd(pn.rp[2][0], 5.829880828740958E-4, 1.0E-14, "jauPn00a", "rp31");
            this.vvd(pn.rp[2][1], -3.984203267708835E-7, 1.0E-14, "jauPn00a", "rp32");
            this.vvd(pn.rp[2][2], 0.9999998300623538, 1.0E-12, "jauPn00a", "rp33");
            this.vvd(pn.rbp[0][0], 0.9999989300052244, 1.0E-12, "jauPn00a", "rbp11");
            this.vvd(pn.rbp[0][1], -0.0013417179902397037, 1.0E-14, "jauPn00a", "rbp12");
            this.vvd(pn.rbp[0][2], -5.829075749891684E-4, 1.0E-14, "jauPn00a", "rbp13");
            this.vvd(pn.rbp[1][0], 0.00134171801383174, 1.0E-14, "jauPn00a", "rbp21");
            this.vvd(pn.rbp[1][1], 0.9999990998959192, 1.0E-12, "jauPn00a", "rbp22");
            this.vvd(pn.rbp[1][2], -3.505759733565421E-7, 1.0E-14, "jauPn00a", "rbp23");
            this.vvd(pn.rbp[2][0], 5.829075206857718E-4, 1.0E-14, "jauPn00a", "rbp31");
            this.vvd(pn.rbp[2][1], -4.315219955198609E-7, 1.0E-14, "jauPn00a", "rbp32");
            this.vvd(pn.rbp[2][2], 0.9999998301093036, 1.0E-12, "jauPn00a", "rbp33");
            this.vvd(pn.rn[0][0], 0.9999999999536228, 1.0E-12, "jauPn00a", "rn11");
            this.vvd(pn.rn[0][1], 8.836238544090872E-6, 1.0E-14, "jauPn00a", "rn12");
            this.vvd(pn.rn[0][2], 3.830835237722401E-6, 1.0E-14, "jauPn00a", "rn13");
            this.vvd(pn.rn[1][0], -8.83608288079857E-6, 1.0E-14, "jauPn00a", "rn21");
            this.vvd(pn.rn[1][1], 0.9999999991354656, 1.0E-12, "jauPn00a", "rn22");
            this.vvd(pn.rn[1][2], -4.0632408653625E-5, 1.0E-14, "jauPn00a", "rn23");
            this.vvd(pn.rn[2][0], -3.831194272065996E-6, 1.0E-14, "jauPn00a", "rn31");
            this.vvd(pn.rn[2][1], 4.063237480216292E-5, 1.0E-14, "jauPn00a", "rn32");
            this.vvd(pn.rn[2][2], 0.9999999991671661, 1.0E-12, "jauPn00a", "rn33");
            this.vvd(pn.rbpn[0][0], 0.9999989440476104, 1.0E-12, "jauPn00a", "rbpn11");
            this.vvd(pn.rbpn[0][1], -0.0013328817612400117, 1.0E-14, "jauPn00a", "rbpn12");
            this.vvd(pn.rbpn[0][2], -5.790767434730086E-4, 1.0E-14, "jauPn00a", "rbpn13");
            this.vvd(pn.rbpn[1][0], 0.0013328582543089547, 1.0E-14, "jauPn00a", "rbpn21");
            this.vvd(pn.rbpn[1][1], 0.9999991109044506, 1.0E-12, "jauPn00a", "rbpn22");
            this.vvd(pn.rbpn[1][2], -4.0977827103965806E-5, 1.0E-14, "jauPn00a", "rbpn23");
            this.vvd(pn.rbpn[2][0], 5.791308472168153E-4, 1.0E-14, "jauPn00a", "rbpn31");
            this.vvd(pn.rbpn[2][1], 4.0205956615915E-5, 1.0E-14, "jauPn00a", "rbpn32");
            this.vvd(pn.rbpn[2][2], 0.9999998314954572, 1.0E-12, "jauPn00a", "rbpn33");
        }

        public t_pn00b() {
            const pn: JSOFA.PrecessionNutation = jauPn00b(2400000.5, 53736.0);
            this.vvd(pn.nut.dpsi, -9.632552291148363E-6, 1.0E-12, "jauPn00b", "dpsi");
            this.vvd(pn.nut.deps, 4.063197106621159E-5, 1.0E-12, "jauPn00b", "deps");
            this.vvd(pn.epsa, 0.409079178940423, 1.0E-12, "jauPn00b", "epsa");
            this.vvd(pn.rb[0][0], 0.9999999999999942, 1.0E-12, "jauPn00b", "rb11");
            this.vvd(pn.rb[0][1], -7.078279744199197E-8, 1.0E-16, "jauPn00b", "rb12");
            this.vvd(pn.rb[0][2], 8.056217146976134E-8, 1.0E-16, "jauPn00b", "rb13");
            this.vvd(pn.rb[1][0], 7.078279477857338E-8, 1.0E-16, "jauPn00b", "rb21");
            this.vvd(pn.rb[1][1], 0.999999999999997, 1.0E-12, "jauPn00b", "rb22");
            this.vvd(pn.rb[1][2], 3.3060414542221364E-8, 1.0E-16, "jauPn00b", "rb23");
            this.vvd(pn.rb[2][0], -8.056217380986972E-8, 1.0E-16, "jauPn00b", "rb31");
            this.vvd(pn.rb[2][1], -3.3060408839805523E-8, 1.0E-16, "jauPn00b", "rb32");
            this.vvd(pn.rb[2][2], 0.9999999999999962, 1.0E-12, "jauPn00b", "rb33");
            this.vvd(pn.rp[0][0], 0.9999989300532289, 1.0E-12, "jauPn00b", "rp11");
            this.vvd(pn.rp[0][1], -0.0013416472267918243, 1.0E-14, "jauPn00b", "rp12");
            this.vvd(pn.rp[0][2], -5.829880927190296E-4, 1.0E-14, "jauPn00b", "rp13");
            this.vvd(pn.rp[1][0], 0.001341647231069759, 1.0E-14, "jauPn00b", "rp21");
            this.vvd(pn.rp[1][1], 0.999999099990875, 1.0E-12, "jauPn00b", "rp22");
            this.vvd(pn.rp[1][2], -3.8374444415837154E-7, 1.0E-14, "jauPn00b", "rp23");
            this.vvd(pn.rp[2][0], 5.829880828740958E-4, 1.0E-14, "jauPn00b", "rp31");
            this.vvd(pn.rp[2][1], -3.984203267708835E-7, 1.0E-14, "jauPn00b", "rp32");
            this.vvd(pn.rp[2][2], 0.9999998300623538, 1.0E-12, "jauPn00b", "rp33");
            this.vvd(pn.rbp[0][0], 0.9999989300052244, 1.0E-12, "jauPn00b", "rbp11");
            this.vvd(pn.rbp[0][1], -0.0013417179902397037, 1.0E-14, "jauPn00b", "rbp12");
            this.vvd(pn.rbp[0][2], -5.829075749891684E-4, 1.0E-14, "jauPn00b", "rbp13");
            this.vvd(pn.rbp[1][0], 0.00134171801383174, 1.0E-14, "jauPn00b", "rbp21");
            this.vvd(pn.rbp[1][1], 0.9999990998959192, 1.0E-12, "jauPn00b", "rbp22");
            this.vvd(pn.rbp[1][2], -3.505759733565421E-7, 1.0E-14, "jauPn00b", "rbp23");
            this.vvd(pn.rbp[2][0], 5.829075206857718E-4, 1.0E-14, "jauPn00b", "rbp31");
            this.vvd(pn.rbp[2][1], -4.315219955198609E-7, 1.0E-14, "jauPn00b", "rbp32");
            this.vvd(pn.rbp[2][2], 0.9999998301093036, 1.0E-12, "jauPn00b", "rbp33");
            this.vvd(pn.rn[0][0], 0.999999999953607, 1.0E-12, "jauPn00b", "rn11");
            this.vvd(pn.rn[0][1], 8.837746144871248E-6, 1.0E-14, "jauPn00b", "rn12");
            this.vvd(pn.rn[0][2], 3.831488838252203E-6, 1.0E-14, "jauPn00b", "rn13");
            this.vvd(pn.rn[1][0], -8.837590456632305E-6, 1.0E-14, "jauPn00b", "rn21");
            this.vvd(pn.rn[1][1], 0.9999999991354692, 1.0E-12, "jauPn00b", "rn22");
            this.vvd(pn.rn[1][2], -4.0631987985595914E-5, 1.0E-14, "jauPn00b", "rn23");
            this.vvd(pn.rn[2][0], -3.831847930134941E-6, 1.0E-14, "jauPn00b", "rn31");
            this.vvd(pn.rn[2][1], 4.063195412258168E-5, 1.0E-14, "jauPn00b", "rn32");
            this.vvd(pn.rn[2][2], 0.9999999991671806, 1.0E-12, "jauPn00b", "rn33");
            this.vvd(pn.rbpn[0][0], 0.9999989440499982, 1.0E-12, "jauPn00b", "rbpn11");
            this.vvd(pn.rbpn[0][1], -0.0013328802536408493, 1.0E-14, "jauPn00b", "rbpn12");
            this.vvd(pn.rbpn[0][2], -5.790760898731092E-4, 1.0E-14, "jauPn00b", "rbpn13");
            this.vvd(pn.rbpn[1][0], 0.0013328567469799496, 1.0E-14, "jauPn00b", "rbpn21");
            this.vvd(pn.rbpn[1][1], 0.9999991109064769, 1.0E-12, "jauPn00b", "rbpn22");
            this.vvd(pn.rbpn[1][2], -4.097740555723082E-5, 1.0E-14, "jauPn00b", "rbpn23");
            this.vvd(pn.rbpn[2][0], 5.791301929950209E-4, 1.0E-14, "jauPn00b", "rbpn31");
            this.vvd(pn.rbpn[2][1], 4.0205536813737205E-5, 1.0E-14, "jauPn00b", "rbpn32");
            this.vvd(pn.rbpn[2][2], 0.999999831495853, 1.0E-12, "jauPn00b", "rbpn33");
        }

        public t_pn06a() {
            const pn: JSOFA.PrecessionNutation = jauPn06a(2400000.5, 53736.0);
            this.vvd(pn.nut.dpsi, -9.63091202582031E-6, 1.0E-12, "jauPn06a", "dpsi");
            this.vvd(pn.nut.deps, 4.06323849688725E-5, 1.0E-12, "jauPn06a", "deps");
            this.vvd(pn.epsa, 0.409078976335651, 1.0E-12, "jauPn06a", "epsa");
            this.vvd(pn.rb[0][0], 0.9999999999999942, 1.0E-12, "jauPn06a", "rb11");
            this.vvd(pn.rb[0][1], -7.078368960971557E-8, 1.0E-14, "jauPn06a", "rb12");
            this.vvd(pn.rb[0][2], 8.056213977613186E-8, 1.0E-14, "jauPn06a", "rb13");
            this.vvd(pn.rb[1][0], 7.078368694637675E-8, 1.0E-14, "jauPn06a", "rb21");
            this.vvd(pn.rb[1][1], 0.999999999999997, 1.0E-12, "jauPn06a", "rb22");
            this.vvd(pn.rb[1][2], 3.305943742989134E-8, 1.0E-14, "jauPn06a", "rb23");
            this.vvd(pn.rb[2][0], -8.056214211620056E-8, 1.0E-14, "jauPn06a", "rb31");
            this.vvd(pn.rb[2][1], -3.305943172740587E-8, 1.0E-14, "jauPn06a", "rb32");
            this.vvd(pn.rb[2][2], 0.9999999999999962, 1.0E-12, "jauPn06a", "rb33");
            this.vvd(pn.rp[0][0], 0.9999989300536855, 1.0E-12, "jauPn06a", "rp11");
            this.vvd(pn.rp[0][1], -0.0013416468862044438, 1.0E-14, "jauPn06a", "rp12");
            this.vvd(pn.rp[0][2], -5.829880933488628E-4, 1.0E-14, "jauPn06a", "rp13");
            this.vvd(pn.rp[1][0], 0.0013416468905697821, 1.0E-14, "jauPn06a", "rp21");
            this.vvd(pn.rp[1][1], 0.999999099991332, 1.0E-12, "jauPn06a", "rp22");
            this.vvd(pn.rp[1][2], -3.835944216374477E-7, 1.0E-14, "jauPn06a", "rp23");
            this.vvd(pn.rp[2][0], 5.829880833027868E-4, 1.0E-14, "jauPn06a", "rp31");
            this.vvd(pn.rp[2][1], -3.985701514686976E-7, 1.0E-14, "jauPn06a", "rp32");
            this.vvd(pn.rp[2][2], 0.9999998300623535, 1.0E-12, "jauPn06a", "rp33");
            this.vvd(pn.rbp[0][0], 0.9999989300056797, 1.0E-12, "jauPn06a", "rbp11");
            this.vvd(pn.rbp[0][1], -0.0013417176505450595, 1.0E-14, "jauPn06a", "rbp12");
            this.vvd(pn.rbp[0][2], -5.829075756493729E-4, 1.0E-14, "jauPn06a", "rbp13");
            this.vvd(pn.rbp[1][0], 0.001341717674223918, 1.0E-14, "jauPn06a", "rbp21");
            this.vvd(pn.rbp[1][1], 0.9999990998963748, 1.0E-12, "jauPn06a", "rbp22");
            this.vvd(pn.rbp[1][2], -3.504269280170069E-7, 1.0E-14, "jauPn06a", "rbp23");
            this.vvd(pn.rbp[2][0], 5.829075211461455E-4, 1.0E-14, "jauPn06a", "rbp31");
            this.vvd(pn.rbp[2][1], -4.316708436255949E-7, 1.0E-14, "jauPn06a", "rbp32");
            this.vvd(pn.rbp[2][2], 0.9999998301093033, 1.0E-12, "jauPn06a", "rbp33");
            this.vvd(pn.rn[0][0], 0.9999999999536228, 1.0E-12, "jauPn06a", "rn11");
            this.vvd(pn.rn[0][1], 8.836241998111535E-6, 1.0E-14, "jauPn06a", "rn12");
            this.vvd(pn.rn[0][2], 3.8308346084152875E-6, 1.0E-14, "jauPn06a", "rn13");
            this.vvd(pn.rn[1][0], -8.83608633487074E-6, 1.0E-14, "jauPn06a", "rn21");
            this.vvd(pn.rn[1][1], 0.9999999991354658, 1.0E-12, "jauPn06a", "rn22");
            this.vvd(pn.rn[1][2], -4.063240188248455E-5, 1.0E-14, "jauPn06a", "rn23");
            this.vvd(pn.rn[2][0], -3.831193642839398E-6, 1.0E-14, "jauPn06a", "rn31");
            this.vvd(pn.rn[2][1], 4.0632368031014796E-5, 1.0E-14, "jauPn06a", "rn32");
            this.vvd(pn.rn[2][2], 0.9999999991671663, 1.0E-12, "jauPn06a", "rn33");
            this.vvd(pn.rbpn[0][0], 0.999998944048067, 1.0E-12, "jauPn06a", "rbpn11");
            this.vvd(pn.rbpn[0][1], -0.001332881418091916, 1.0E-14, "jauPn06a", "rbpn12");
            this.vvd(pn.rbpn[0][2], -5.790767447612043E-4, 1.0E-14, "jauPn06a", "rbpn13");
            this.vvd(pn.rbpn[1][0], 0.0013328579112509892, 1.0E-14, "jauPn06a", "rbpn21");
            this.vvd(pn.rbpn[1][1], 0.9999991109049142, 1.0E-12, "jauPn06a", "rbpn22");
            this.vvd(pn.rbpn[1][2], -4.097767128546785E-5, 1.0E-14, "jauPn06a", "rbpn23");
            this.vvd(pn.rbpn[2][0], 5.791308482835292E-4, 1.0E-14, "jauPn06a", "rbpn31");
            this.vvd(pn.rbpn[2][1], 4.0205800994540205E-5, 1.0E-14, "jauPn06a", "rbpn32");
            this.vvd(pn.rbpn[2][2], 0.9999998314954629, 1.0E-12, "jauPn06a", "rbpn33");
        }

        public t_pn06() {
            let dpsi: number;
            let deps: number;
            dpsi = -9.632552291149336E-6;
            deps = 4.0631971066211414E-5;
            const pn: JSOFA.PrecessionNutation = jauPn06(2400000.5, 53736.0, dpsi, deps);
            this.vvd(pn.epsa, 0.409078976335651, 1.0E-12, "jauPn06", "epsa");
            this.vvd(pn.rb[0][0], 0.9999999999999942, 1.0E-12, "jauPn06", "rb11");
            this.vvd(pn.rb[0][1], -7.078368960971557E-8, 1.0E-14, "jauPn06", "rb12");
            this.vvd(pn.rb[0][2], 8.056213977613186E-8, 1.0E-14, "jauPn06", "rb13");
            this.vvd(pn.rb[1][0], 7.078368694637675E-8, 1.0E-14, "jauPn06", "rb21");
            this.vvd(pn.rb[1][1], 0.999999999999997, 1.0E-12, "jauPn06", "rb22");
            this.vvd(pn.rb[1][2], 3.305943742989134E-8, 1.0E-14, "jauPn06", "rb23");
            this.vvd(pn.rb[2][0], -8.056214211620056E-8, 1.0E-14, "jauPn06", "rb31");
            this.vvd(pn.rb[2][1], -3.305943172740587E-8, 1.0E-14, "jauPn06", "rb32");
            this.vvd(pn.rb[2][2], 0.9999999999999962, 1.0E-12, "jauPn06", "rb33");
            this.vvd(pn.rp[0][0], 0.9999989300536855, 1.0E-12, "jauPn06", "rp11");
            this.vvd(pn.rp[0][1], -0.0013416468862044438, 1.0E-14, "jauPn06", "rp12");
            this.vvd(pn.rp[0][2], -5.829880933488628E-4, 1.0E-14, "jauPn06", "rp13");
            this.vvd(pn.rp[1][0], 0.0013416468905697821, 1.0E-14, "jauPn06", "rp21");
            this.vvd(pn.rp[1][1], 0.999999099991332, 1.0E-12, "jauPn06", "rp22");
            this.vvd(pn.rp[1][2], -3.835944216374477E-7, 1.0E-14, "jauPn06", "rp23");
            this.vvd(pn.rp[2][0], 5.829880833027868E-4, 1.0E-14, "jauPn06", "rp31");
            this.vvd(pn.rp[2][1], -3.985701514686976E-7, 1.0E-14, "jauPn06", "rp32");
            this.vvd(pn.rp[2][2], 0.9999998300623535, 1.0E-12, "jauPn06", "rp33");
            this.vvd(pn.rbp[0][0], 0.9999989300056797, 1.0E-12, "jauPn06", "rbp11");
            this.vvd(pn.rbp[0][1], -0.0013417176505450595, 1.0E-14, "jauPn06", "rbp12");
            this.vvd(pn.rbp[0][2], -5.829075756493729E-4, 1.0E-14, "jauPn06", "rbp13");
            this.vvd(pn.rbp[1][0], 0.001341717674223918, 1.0E-14, "jauPn06", "rbp21");
            this.vvd(pn.rbp[1][1], 0.9999990998963748, 1.0E-12, "jauPn06", "rbp22");
            this.vvd(pn.rbp[1][2], -3.504269280170069E-7, 1.0E-14, "jauPn06", "rbp23");
            this.vvd(pn.rbp[2][0], 5.829075211461455E-4, 1.0E-14, "jauPn06", "rbp31");
            this.vvd(pn.rbp[2][1], -4.316708436255949E-7, 1.0E-14, "jauPn06", "rbp32");
            this.vvd(pn.rbp[2][2], 0.9999998301093033, 1.0E-12, "jauPn06", "rbp33");
            this.vvd(pn.rn[0][0], 0.999999999953607, 1.0E-12, "jauPn06", "rn11");
            this.vvd(pn.rn[0][1], 8.837746921149881E-6, 1.0E-14, "jauPn06", "rn12");
            this.vvd(pn.rn[0][2], 3.831487047682968E-6, 1.0E-14, "jauPn06", "rn13");
            this.vvd(pn.rn[1][0], -8.837591232983692E-6, 1.0E-14, "jauPn06", "rn21");
            this.vvd(pn.rn[1][1], 0.9999999991354692, 1.0E-12, "jauPn06", "rn22");
            this.vvd(pn.rn[1][2], -4.0631987985589314E-5, 1.0E-14, "jauPn06", "rn23");
            this.vvd(pn.rn[2][0], -3.831846139597251E-6, 1.0E-14, "jauPn06", "rn31");
            this.vvd(pn.rn[2][1], 4.063195412258793E-5, 1.0E-14, "jauPn06", "rn32");
            this.vvd(pn.rn[2][2], 0.9999999991671806, 1.0E-12, "jauPn06", "rn33");
            this.vvd(pn.rbpn[0][0], 0.9999989440504506, 1.0E-12, "jauPn06", "rbpn11");
            this.vvd(pn.rbpn[0][1], -0.0013328799131704927, 1.0E-14, "jauPn06", "rbpn12");
            this.vvd(pn.rbpn[0][2], -5.790760923225656E-4, 1.0E-14, "jauPn06", "rbpn13");
            this.vvd(pn.rbpn[1][0], 0.0013328564065957548, 1.0E-14, "jauPn06", "rbpn21");
            this.vvd(pn.rbpn[1][1], 0.9999991109069367, 1.0E-12, "jauPn06", "rbpn22");
            this.vvd(pn.rbpn[1][2], -4.0977256511426416E-5, 1.0E-14, "jauPn06", "rbpn23");
            this.vvd(pn.rbpn[2][0], 5.791301952321297E-4, 1.0E-14, "jauPn06", "rbpn31");
            this.vvd(pn.rbpn[2][1], 4.02053879619523E-5, 1.0E-14, "jauPn06", "rbpn32");
            this.vvd(pn.rbpn[2][2], 0.9999998314958577, 1.0E-12, "jauPn06", "rbpn33");
        }

        public t_pnm00a() {
            let rbpn: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            rbpn = jauPnm00a(2400000.5, 50123.9999);
            this.vvd(rbpn[0][0], 0.9999995832793134, 1.0E-12, "jauPnm00a", "11");
            this.vvd(rbpn[0][1], 8.372384254137809E-4, 1.0E-14, "jauPnm00a", "12");
            this.vvd(rbpn[0][2], 3.639684306407151E-4, 1.0E-14, "jauPnm00a", "13");
            this.vvd(rbpn[1][0], -8.372535226570394E-4, 1.0E-14, "jauPnm00a", "21");
            this.vvd(rbpn[1][1], 0.9999996486491582, 1.0E-12, "jauPnm00a", "22");
            this.vvd(rbpn[1][2], 4.132915262664073E-5, 1.0E-14, "jauPnm00a", "23");
            this.vvd(rbpn[2][0], -3.639337004054318E-4, 1.0E-14, "jauPnm00a", "31");
            this.vvd(rbpn[2][1], -4.163386925461776E-5, 1.0E-14, "jauPnm00a", "32");
            this.vvd(rbpn[2][2], 0.9999999329094391, 1.0E-12, "jauPnm00a", "33");
        }

        public t_pnm00b() {
            let rbpn: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            rbpn = jauPnm00b(2400000.5, 50123.9999);
            this.vvd(rbpn[0][0], 0.9999995832776208, 1.0E-12, "jauPnm00b", "11");
            this.vvd(rbpn[0][1], 8.372401264429655E-4, 1.0E-14, "jauPnm00b", "12");
            this.vvd(rbpn[0][2], 3.6396916814502716E-4, 1.0E-14, "jauPnm00b", "13");
            this.vvd(rbpn[1][0], -8.372552234147138E-4, 1.0E-14, "jauPnm00b", "21");
            this.vvd(rbpn[1][1], 0.9999996486477686, 1.0E-12, "jauPnm00b", "22");
            this.vvd(rbpn[1][2], 4.132832190946053E-5, 1.0E-14, "jauPnm00b", "23");
            this.vvd(rbpn[2][0], -3.639344385341866E-4, 1.0E-14, "jauPnm00b", "31");
            this.vvd(rbpn[2][1], -4.1633039774215225E-5, 1.0E-14, "jauPnm00b", "32");
            this.vvd(rbpn[2][2], 0.999999932909205, 1.0E-12, "jauPnm00b", "33");
        }

        public t_pnm06a() {
            let rbpn: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            rbpn = jauPnm06a(2400000.5, 50123.9999);
            this.vvd(rbpn[0][0], 0.9999995832794205, 1.0E-12, "jauPnm06a", "11");
            this.vvd(rbpn[0][1], 8.372382772630962E-4, 1.0E-14, "jauPnm06a", "12");
            this.vvd(rbpn[0][2], 3.639684771140623E-4, 1.0E-14, "jauPnm06a", "13");
            this.vvd(rbpn[1][0], -8.372533744743683E-4, 1.0E-14, "jauPnm06a", "21");
            this.vvd(rbpn[1][1], 0.9999996486492861, 1.0E-12, "jauPnm06a", "22");
            this.vvd(rbpn[1][2], 4.1329059446110195E-5, 1.0E-14, "jauPnm06a", "23");
            this.vvd(rbpn[2][0], -3.639337469629465E-4, 1.0E-14, "jauPnm06a", "31");
            this.vvd(rbpn[2][1], -4.163377605910664E-5, 1.0E-14, "jauPnm06a", "32");
            this.vvd(rbpn[2][2], 0.999999932909426, 1.0E-12, "jauPnm06a", "33");
        }

        public t_pnm80() {
            let rmatpn: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            rmatpn = jauPnm80(2400000.5, 50123.9999);
            this.vvd(rmatpn[0][0], 0.9999995831934612, 1.0E-12, "jauPnm80", "11");
            this.vvd(rmatpn[0][1], 8.373654045728124E-4, 1.0E-14, "jauPnm80", "12");
            this.vvd(rmatpn[0][2], 3.639121916933106E-4, 1.0E-14, "jauPnm80", "13");
            this.vvd(rmatpn[1][0], -8.373804896118301E-4, 1.0E-14, "jauPnm80", "21");
            this.vvd(rmatpn[1][1], 0.9999996485439674, 1.0E-12, "jauPnm80", "22");
            this.vvd(rmatpn[1][2], 4.13020251042155E-5, 1.0E-14, "jauPnm80", "23");
            this.vvd(rmatpn[2][0], -3.638774789072144E-4, 1.0E-14, "jauPnm80", "31");
            this.vvd(rmatpn[2][1], -4.160674085851722E-5, 1.0E-14, "jauPnm80", "32");
            this.vvd(rmatpn[2][2], 0.9999999329310275, 1.0E-12, "jauPnm80", "33");
        }

        public t_pom00() {
            let xp: number;
            let yp: number;
            let sp: number;
            let rpom: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            xp = 2.55060238E-7;
            yp = 1.860359247E-6;
            sp = -1.3671745807288915E-11;
            rpom = jauPom00(xp, yp, sp);
            this.vvd(rpom[0][0], 0.9999999999999675, 1.0E-12, "jauPom00", "11");
            this.vvd(rpom[0][1], -1.367174580728847E-11, 1.0E-16, "jauPom00", "12");
            this.vvd(rpom[0][2], 2.550602379999972E-7, 1.0E-16, "jauPom00", "13");
            this.vvd(rpom[1][0], 1.4146249479570297E-11, 1.0E-16, "jauPom00", "21");
            this.vvd(rpom[1][1], 0.9999999999982695, 1.0E-12, "jauPom00", "22");
            this.vvd(rpom[1][2], -1.8603592469988663E-6, 1.0E-16, "jauPom00", "23");
            this.vvd(rpom[2][0], -2.5506023797412153E-7, 1.0E-16, "jauPom00", "31");
            this.vvd(rpom[2][1], 1.860359247002414E-6, 1.0E-16, "jauPom00", "32");
            this.vvd(rpom[2][2], 0.999999999998237, 1.0E-12, "jauPom00", "33");
            const vec: number[] = [0, 0, 1];
            const vec2: number[] = jauRxp(rpom, vec);
            for(let index = 0; index < vec2.length; index++) {
                let d = vec2[index];
                {
                    console.info(d);
                }
            }
        }

        public t_ppp() {
            const a: number[] = [0, 0, 0];
            const b: number[] = [0, 0, 0];
            let apb: number[] = [0, 0, 0];
            a[0] = 2.0;
            a[1] = 2.0;
            a[2] = 3.0;
            b[0] = 1.0;
            b[1] = 3.0;
            b[2] = 4.0;
            apb = jauPpp(a, b);
            this.vvd(apb[0], 3.0, 1.0E-12, "jauPpp", "0");
            this.vvd(apb[1], 5.0, 1.0E-12, "jauPpp", "1");
            this.vvd(apb[2], 7.0, 1.0E-12, "jauPpp", "2");
        }

        public t_ppsp() {
            const a: number[] = [0, 0, 0];
            let s: number;
            const b: number[] = [0, 0, 0];
            let apsb: number[] = [0, 0, 0];
            a[0] = 2.0;
            a[1] = 2.0;
            a[2] = 3.0;
            s = 5.0;
            b[0] = 1.0;
            b[1] = 3.0;
            b[2] = 4.0;
            apsb = jauPpsp(a, s, b);
            this.vvd(apsb[0], 7.0, 1.0E-12, "jauPpsp", "0");
            this.vvd(apsb[1], 17.0, 1.0E-12, "jauPpsp", "1");
            this.vvd(apsb[2], 23.0, 1.0E-12, "jauPpsp", "2");
        }

        public t_pr00() {
            const nut: JSOFA.PrecessionDeltaTerms = jauPr00(2400000.5, 53736);
            this.vvd(nut.dpsipr, -8.716465172668348E-8, 1.0E-22, "jauPr00", "dpsipr");
            this.vvd(nut.depspr, -7.342018386722813E-9, 1.0E-22, "jauPr00", "depspr");
        }

        public t_prec76() {
            let ep01: number;
            let ep02: number;
            let ep11: number;
            let ep12: number;
            ep01 = 2400000.5;
            ep02 = 33282.0;
            ep11 = 2400000.5;
            ep12 = 51544.0;
            const an: JSOFA.EulerAngles = jauPrec76(ep01, ep02, ep11, ep12);
            this.vvd(an.zeta, 0.005588961642000161, 1.0E-12, "jauPrec76", "zeta");
            this.vvd(an.z, 0.005589922365870681, 1.0E-12, "jauPrec76", "z");
            this.vvd(an.theta, 0.004858945471687297, 1.0E-12, "jauPrec76", "theta");
        }

        public t_pv2p() {
            const pv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            let p: number[] = [0, 0, 0];
            pv[0][0] = 0.3;
            pv[0][1] = 1.2;
            pv[0][2] = -2.5;
            pv[1][0] = -0.5;
            pv[1][1] = 3.1;
            pv[1][2] = 0.9;
            p = jauPv2p(pv);
            this.vvd(p[0], 0.3, 0.0, "jauPv2p", "1");
            this.vvd(p[1], 1.2, 0.0, "jauPv2p", "2");
            this.vvd(p[2], -2.5, 0.0, "jauPv2p", "3");
        }

        public t_pv2s() {
            const pv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            pv[0][0] = -0.4514964673880165;
            pv[0][1] = 0.03093394277342585;
            pv[0][2] = 0.05594668105108779;
            pv[1][0] = 1.29227085066326E-5;
            pv[1][1] = 2.652814182060692E-6;
            pv[1][2] = 2.568431853930293E-6;
            const pvs: JSOFA.SphericalPositionVelocity = jauPv2s(pv);
            this.vvd(pvs.pos.theta, 3.0731853071795867, 1.0E-12, "jauPv2s", "theta");
            this.vvd(pvs.pos.phi, 0.123, 1.0E-12, "jauPv2s", "phi");
            this.vvd(pvs.pos.r, 0.45599999999999996, 1.0E-12, "jauPv2s", "r");
            this.vvd(pvs.vel.theta, -7.8E-6, 1.0E-16, "jauPv2s", "td");
            this.vvd(pvs.vel.phi, 9.010000000000002E-6, 1.0E-16, "jauPv2s", "pd");
            this.vvd(pvs.vel.r, -1.2299999999999999E-5, 1.0E-16, "jauPv2s", "rd");
        }

        public t_pvdpv() {
            const a: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            const b: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            let adb: number[] = [0, 0];
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
            this.vvd(adb[0], 20.0, 1.0E-12, "jauPvdpv", "1");
            this.vvd(adb[1], 50.0, 1.0E-12, "jauPvdpv", "2");
        }

        public t_pvm() {
            const pv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            pv[0][0] = 0.3;
            pv[0][1] = 1.2;
            pv[0][2] = -2.5;
            pv[1][0] = 0.45;
            pv[1][1] = -0.25;
            pv[1][2] = 1.1;
            const ret: JSOFA.PVModulus = jauPvm(pv);
            this.vvd(ret.r, 2.7892651361962706, 1.0E-12, "jauPvm", "r");
            this.vvd(ret.s, 1.2144957801491119, 1.0E-12, "jauPvm", "s");
        }

        public t_pvmpv() {
            const a: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            const b: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            let amb: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
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
            this.vvd(amb[0][0], 1.0, 1.0E-12, "jauPvmpv", "11");
            this.vvd(amb[0][1], -1.0, 1.0E-12, "jauPvmpv", "21");
            this.vvd(amb[0][2], -1.0, 1.0E-12, "jauPvmpv", "31");
            this.vvd(amb[1][0], 2.0, 1.0E-12, "jauPvmpv", "12");
            this.vvd(amb[1][1], 4.0, 1.0E-12, "jauPvmpv", "22");
            this.vvd(amb[1][2], 2.0, 1.0E-12, "jauPvmpv", "32");
        }

        public t_pvppv() {
            const a: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            const b: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            let apb: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
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
            this.vvd(apb[0][0], 3.0, 1.0E-12, "jauPvppv", "p1");
            this.vvd(apb[0][1], 5.0, 1.0E-12, "jauPvppv", "p2");
            this.vvd(apb[0][2], 7.0, 1.0E-12, "jauPvppv", "p3");
            this.vvd(apb[1][0], 8.0, 1.0E-12, "jauPvppv", "v1");
            this.vvd(apb[1][1], 8.0, 1.0E-12, "jauPvppv", "v2");
            this.vvd(apb[1][2], 4.0, 1.0E-12, "jauPvppv", "v3");
        }

        public t_pvstar() {
            const pv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            pv[0][0] = 126668.59127431606;
            pv[0][1] = 2136.792716839935;
            pv[0][2] = -245251.23398768302;
            pv[1][0] = -0.004051854035740713;
            pv[1][1] = -0.006253919754866174;
            pv[1][2] = 0.011893537197741073;
            try {
                const cat: org.jastronomy.jsofa.JSOFA.CatalogCoords = jauPvstar(pv);
                this.vvd(cat.pos.alpha, 0.01686756, 1.0E-12, "jauPvstar", "ra");
                this.vvd(cat.pos.delta, -1.093989828, 1.0E-12, "jauPvstar", "dec");
                this.vvd(cat.pm.alpha, -1.7832351600004728E-5, 1.0E-16, "jauPvstar", "pmr");
                this.vvd(cat.pm.delta, 2.3360240470006194E-6, 1.0E-16, "jauPvstar", "pmd");
                this.vvd(cat.px, 0.74723, 1.0E-12, "jauPvstar", "px");
                this.vvd(cat.rv, -21.60000010107306, 1.0E-11, "jauPvstar", "rv");
            } catch(e) {
            }
        }

        public t_pvu() {
            const pv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            let upv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            pv[0][0] = 126668.59127431607;
            pv[0][1] = 2136.7927168399356;
            pv[0][2] = -245251.23398768302;
            pv[1][0] = -0.004051854035740713;
            pv[1][1] = -0.006253919754866176;
            pv[1][2] = 0.011893537197741076;
            upv = jauPvu(2920.0, pv);
            this.vvd(upv[0][0], 126656.7598605317, 1.0E-6, "jauPvu", "p1");
            this.vvd(upv[0][1], 2118.5312711557262, 1.0E-8, "jauPvu", "p2");
            this.vvd(upv[0][2], -245216.5048590656, 1.0E-6, "jauPvu", "p3");
            this.vvd(upv[1][0], -0.004051854035740713, 1.0E-12, "jauPvu", "v1");
            this.vvd(upv[1][1], -0.006253919754866176, 1.0E-12, "jauPvu", "v2");
            this.vvd(upv[1][2], 0.011893537197741076, 1.0E-12, "jauPvu", "v3");
        }

        public t_pvup() {
            const pv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            let p: number[] = [0, 0, 0];
            pv[0][0] = 126668.59127431607;
            pv[0][1] = 2136.7927168399356;
            pv[0][2] = -245251.23398768302;
            pv[1][0] = -0.004051854035740713;
            pv[1][1] = -0.006253919754866176;
            pv[1][2] = 0.011893537197741076;
            p = jauPvup(2920.0, pv);
            this.vvd(p[0], 126656.7598605317, 1.0E-12, "jauPvup", "1");
            this.vvd(p[1], 2118.5312711557262, 1.0E-12, "jauPvup", "2");
            this.vvd(p[2], -245216.5048590656, 1.0E-12, "jauPvup", "3");
        }

        public t_pvxpv() {
            const a: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            const b: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            let axb: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
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
            this.vvd(axb[0][0], -1.0, 1.0E-12, "jauPvxpv", "p1");
            this.vvd(axb[0][1], -5.0, 1.0E-12, "jauPvxpv", "p2");
            this.vvd(axb[0][2], 4.0, 1.0E-12, "jauPvxpv", "p3");
            this.vvd(axb[1][0], -2.0, 1.0E-12, "jauPvxpv", "v1");
            this.vvd(axb[1][1], -36.0, 1.0E-12, "jauPvxpv", "v2");
            this.vvd(axb[1][2], 22.0, 1.0E-12, "jauPvxpv", "v3");
        }

        public t_pxp() {
            const a: number[] = [0, 0, 0];
            const b: number[] = [0, 0, 0];
            let axb: number[] = [0, 0, 0];
            a[0] = 2.0;
            a[1] = 2.0;
            a[2] = 3.0;
            b[0] = 1.0;
            b[1] = 3.0;
            b[2] = 4.0;
            axb = jauPxp(a, b);
            this.vvd(axb[0], -1.0, 1.0E-12, "jauPxp", "1");
            this.vvd(axb[1], -5.0, 1.0E-12, "jauPxp", "2");
            this.vvd(axb[2], 4.0, 1.0E-12, "jauPxp", "3");
        }

        public t_rm2v() {
            const r: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            let w: number[] = [0, 0, 0];
            r[0][0] = 0.0;
            r[0][1] = -0.8;
            r[0][2] = -0.6;
            r[1][0] = 0.8;
            r[1][1] = -0.36;
            r[1][2] = 0.48;
            r[2][0] = 0.6;
            r[2][1] = 0.48;
            r[2][2] = -0.64;
            w = jauRm2v(r);
            this.vvd(w[0], 0.0, 1.0E-12, "jauRm2v", "1");
            this.vvd(w[1], 1.413716694115407, 1.0E-12, "jauRm2v", "2");
            this.vvd(w[2], -1.8849555921538759, 1.0E-12, "jauRm2v", "3");
        }

        public t_rv2m() {
            const w: number[] = [0, 0, 0];
            let r: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            w[0] = 0.0;
            w[1] = 1.41371669;
            w[2] = -1.88495559;
            r = jauRv2m(w);
            this.vvd(r[0][0], -0.707106778222112, 1.0E-14, "jauRv2m", "11");
            this.vvd(r[0][1], -0.565685427680913, 1.0E-14, "jauRv2m", "12");
            this.vvd(r[0][2], -0.4242640700104211, 1.0E-14, "jauRv2m", "13");
            this.vvd(r[1][0], 0.565685427680913, 1.0E-14, "jauRv2m", "21");
            this.vvd(r[1][1], -0.09254833945322742, 1.0E-14, "jauRv2m", "22");
            this.vvd(r[1][2], -0.8194112531408834, 1.0E-14, "jauRv2m", "23");
            this.vvd(r[2][0], 0.4242640700104211, 1.0E-14, "jauRv2m", "31");
            this.vvd(r[2][1], -0.8194112531408834, 1.0E-14, "jauRv2m", "32");
            this.vvd(r[2][2], 0.38544156123111545, 1.0E-14, "jauRv2m", "33");
        }

        public t_rx() {
            let phi: number;
            const r: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
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
            this.vvd(r[0][0], 2.0, 0.0, "jauRx", "11");
            this.vvd(r[0][1], 3.0, 0.0, "jauRx", "12");
            this.vvd(r[0][2], 2.0, 0.0, "jauRx", "13");
            this.vvd(r[1][0], 3.8390433882356123, 1.0E-12, "jauRx", "21");
            this.vvd(r[1][1], 3.237033249594112, 1.0E-12, "jauRx", "22");
            this.vvd(r[1][2], 4.5167143790059825, 1.0E-12, "jauRx", "23");
            this.vvd(r[2][0], 1.8060304159245018, 1.0E-12, "jauRx", "31");
            this.vvd(r[2][1], 3.0857115453363724, 1.0E-12, "jauRx", "32");
            this.vvd(r[2][2], 3.6877216839778733, 1.0E-12, "jauRx", "33");
        }

        public t_rxp() {
            const r: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            const p: number[] = [0, 0, 0];
            let rp: number[] = [0, 0, 0];
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
            this.vvd(rp[0], 5.1, 1.0E-12, "jauRxp", "1");
            this.vvd(rp[1], 3.9, 1.0E-12, "jauRxp", "2");
            this.vvd(rp[2], 7.1, 1.0E-12, "jauRxp", "3");
        }

        public t_rxpv() {
            const r: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            const pv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            let rpv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
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
            this.vvd(rpv[0][0], 5.1, 1.0E-12, "jauRxpv", "11");
            this.vvd(rpv[1][0], 3.8, 1.0E-12, "jauRxpv", "12");
            this.vvd(rpv[0][1], 3.9, 1.0E-12, "jauRxpv", "21");
            this.vvd(rpv[1][1], 5.2, 1.0E-12, "jauRxpv", "22");
            this.vvd(rpv[0][2], 7.1, 1.0E-12, "jauRxpv", "31");
            this.vvd(rpv[1][2], 5.8, 1.0E-12, "jauRxpv", "32");
        }

        public t_rxr() {
            const a: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            const b: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            let atb: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
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
            this.vvd(atb[0][0], 20.0, 1.0E-12, "jauRxr", "11");
            this.vvd(atb[0][1], 7.0, 1.0E-12, "jauRxr", "12");
            this.vvd(atb[0][2], 9.0, 1.0E-12, "jauRxr", "13");
            this.vvd(atb[1][0], 20.0, 1.0E-12, "jauRxr", "21");
            this.vvd(atb[1][1], 8.0, 1.0E-12, "jauRxr", "22");
            this.vvd(atb[1][2], 11.0, 1.0E-12, "jauRxr", "23");
            this.vvd(atb[2][0], 34.0, 1.0E-12, "jauRxr", "31");
            this.vvd(atb[2][1], 10.0, 1.0E-12, "jauRxr", "32");
            this.vvd(atb[2][2], 15.0, 1.0E-12, "jauRxr", "33");
        }

        public t_ry() {
            let theta: number;
            const r: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
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
            this.vvd(r[0][0], 0.865184781897816, 1.0E-12, "jauRy", "11");
            this.vvd(r[0][1], 1.4671949205393167, 1.0E-12, "jauRy", "12");
            this.vvd(r[0][2], 0.18751379112744573, 1.0E-12, "jauRy", "13");
            this.vvd(r[1][0], 3, 1.0E-12, "jauRy", "21");
            this.vvd(r[1][1], 2, 1.0E-12, "jauRy", "22");
            this.vvd(r[1][2], 3, 1.0E-12, "jauRy", "23");
            this.vvd(r[2][0], 3.5002078928504274, 1.0E-12, "jauRy", "31");
            this.vvd(r[2][1], 4.7798890222622985, 1.0E-12, "jauRy", "32");
            this.vvd(r[2][2], 5.3818991609037985, 1.0E-12, "jauRy", "33");
        }

        public t_rz() {
            let psi: number;
            const r: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
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
            this.vvd(r[0][0], 2.8981977542089266, 1.0E-12, "jauRz", "11");
            this.vvd(r[0][1], 3.5002078928504274, 1.0E-12, "jauRz", "12");
            this.vvd(r[0][2], 2.8981977542089266, 1.0E-12, "jauRz", "13");
            this.vvd(r[1][0], 2.1448659113096866, 1.0E-12, "jauRz", "21");
            this.vvd(r[1][1], 0.865184781897816, 1.0E-12, "jauRz", "22");
            this.vvd(r[1][2], 2.1448659113096866, 1.0E-12, "jauRz", "23");
            this.vvd(r[2][0], 3.0, 1.0E-12, "jauRz", "31");
            this.vvd(r[2][1], 4.0, 1.0E-12, "jauRz", "32");
            this.vvd(r[2][2], 5.0, 1.0E-12, "jauRz", "33");
        }

        public t_s00a() {
            let s: number;
            s = jauS00a(2400000.5, 52541.0);
            this.vvd(s, -1.3406844489191635E-8, 1.0E-18, "jauS00a", "");
        }

        public t_s00b() {
            let s: number;
            s = jauS00b(2400000.5, 52541.0);
            this.vvd(s, -1.3406957829510266E-8, 1.0E-18, "jauS00b", "");
        }

        public t_s00() {
            let x: number;
            let y: number;
            let s: number;
            x = 5.791308486706011E-4;
            y = 4.020579816732961E-5;
            s = jauS00(2400000.5, 53736.0, x, y);
            this.vvd(s, -1.2200362632709057E-8, 1.0E-18, "jauS00", "");
        }

        public t_s06a() {
            let s: number;
            s = jauS06a(2400000.5, 52541.0);
            this.vvd(s, -1.3406804372918124E-8, 1.0E-18, "jauS06a", "");
        }

        public t_s06() {
            let x: number;
            let y: number;
            let s: number;
            x = 5.791308486706011E-4;
            y = 4.020579816732961E-5;
            s = jauS06(2400000.5, 53736.0, x, y);
            this.vvd(s, -1.220032213076463E-8, 1.0E-18, "jauS06", "");
        }

        public t_s2c() {
            let c: number[] = [0, 0, 0];
            c = jauS2c(3.0123, -0.999);
            this.vvd(c[0], -0.5366267667260524, 1.0E-12, "jauS2c", "1");
            this.vvd(c[1], 0.06977111097651453, 1.0E-12, "jauS2c", "2");
            this.vvd(c[2], -0.8409302618566215, 1.0E-12, "jauS2c", "3");
        }

        public t_s2p() {
            let p: number[] = [0, 0, 0];
            p = jauS2p(-3.21, 0.123, 0.456);
            this.vvd(p[0], -0.4514964673880165, 1.0E-12, "jauS2p", "x");
            this.vvd(p[1], 0.030933942773425867, 1.0E-12, "jauS2p", "y");
            this.vvd(p[2], 0.05594668105108779, 1.0E-12, "jauS2p", "z");
        }

        public t_s2pv() {
            let pv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            pv = jauS2pv(-3.21, 0.123, 0.456, -7.8E-6, 9.01E-6, -1.23E-5);
            this.vvd(pv[0][0], -0.4514964673880165, 1.0E-12, "jauS2pv", "x");
            this.vvd(pv[0][1], 0.030933942773425867, 1.0E-12, "jauS2pv", "y");
            this.vvd(pv[0][2], 0.05594668105108779, 1.0E-12, "jauS2pv", "z");
            this.vvd(pv[1][0], 1.2922708506632602E-5, 1.0E-16, "jauS2pv", "vx");
            this.vvd(pv[1][1], 2.6528141820606914E-6, 1.0E-16, "jauS2pv", "vy");
            this.vvd(pv[1][2], 2.568431853930292E-6, 1.0E-16, "jauS2pv", "vz");
        }

        public t_s2xpv() {
            let s1: number;
            let s2: number;
            const pv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            let spv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            s1 = 2.0;
            s2 = 3.0;
            pv[0][0] = 0.3;
            pv[0][1] = 1.2;
            pv[0][2] = -2.5;
            pv[1][0] = 0.5;
            pv[1][1] = 2.3;
            pv[1][2] = -0.4;
            spv = jauS2xpv(s1, s2, pv);
            this.vvd(spv[0][0], 0.6, 1.0E-12, "jauS2xpv", "p1");
            this.vvd(spv[0][1], 2.4, 1.0E-12, "jauS2xpv", "p2");
            this.vvd(spv[0][2], -5.0, 1.0E-12, "jauS2xpv", "p3");
            this.vvd(spv[1][0], 1.5, 1.0E-12, "jauS2xpv", "v1");
            this.vvd(spv[1][1], 6.9, 1.0E-12, "jauS2xpv", "v2");
            this.vvd(spv[1][2], -1.2, 1.0E-12, "jauS2xpv", "v3");
        }

        public t_sepp() {
            const a: number[] = [0, 0, 0];
            const b: number[] = [0, 0, 0];
            let s: number;
            a[0] = 1.0;
            a[1] = 0.1;
            a[2] = 0.2;
            b[0] = -3.0;
            b[1] = 0.001;
            b[2] = 0.2;
            s = jauSepp(a, b);
            this.vvd(s, 2.8603919190246607, 1.0E-12, "jauSepp", "");
        }

        public t_seps() {
            let al: number;
            let ap: number;
            let bl: number;
            let bp: number;
            let s: number;
            al = 1.0;
            ap = 0.1;
            bl = 0.2;
            bp = -3.0;
            s = jauSeps(al, ap, bl, bp);
            this.vvd(s, 2.3467220169969987, 1.0E-14, "jauSeps", "");
        }

        public t_sp00() {
            this.vvd(jauSp00(2400000.5, 52541.0), -6.216698469981019E-12, 1.0E-12, "jauSp00", "");
        }

        public t_starpm() {
            let ra1: number;
            let dec1: number;
            let pmr1: number;
            let pmd1: number;
            let px1: number;
            let rv1: number;
            ra1 = 0.01686756;
            dec1 = -1.093989828;
            pmr1 = -1.78323516E-5;
            pmd1 = 2.336024047E-6;
            px1 = 0.74723;
            rv1 = -21.6;
            try {
                const cat: org.jastronomy.jsofa.JSOFA.CatalogCoords = jauStarpm(ra1, dec1, pmr1, pmd1, px1, rv1, 2400000.5, 50083.0, 2400000.5, 53736.0);
                this.vvd(cat.pos.alpha, 0.016689190694142562, 1.0E-13, "jauStarpm", "ra");
                this.vvd(cat.pos.delta, -1.093966454217128, 1.0E-13, "jauStarpm", "dec");
                this.vvd(cat.pm.alpha, -1.7836626821531766E-5, 1.0E-17, "jauStarpm", "pmr");
                this.vvd(cat.pm.delta, 2.3380929159839896E-6, 1.0E-17, "jauStarpm", "pmd");
                this.vvd(cat.px, 0.7473533835317719, 1.0E-13, "jauStarpm", "px");
                this.vvd(cat.rv, -21.59905170476417, 1.0E-11, "jauStarpm", "rv");
            } catch(e) {
                console.error(e.message, e);
            }
        }

        public t_starpv() {
            let ra: number;
            let dec: number;
            let pmr: number;
            let pmd: number;
            let px: number;
            let rv: number;
            const pv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            ra = 0.01686756;
            dec = -1.093989828;
            pmr = -1.78323516E-5;
            pmd = 2.336024047E-6;
            px = 0.74723;
            rv = -21.6;
            const j: number = jauStarpv(ra, dec, pmr, pmd, px, rv, pv);
            this.vvd(pv[0][0], 126668.59127431606, 1.0E-10, "jauStarpv", "11");
            this.vvd(pv[0][1], 2136.792716839935, 1.0E-12, "jauStarpv", "12");
            this.vvd(pv[0][2], -245251.23398768302, 1.0E-10, "jauStarpv", "13");
            this.vvd(pv[1][0], -0.00405185400895566, 1.0E-13, "jauStarpv", "21");
            this.vvd(pv[1][1], -0.006253919754414778, 1.0E-15, "jauStarpv", "22");
            this.vvd(pv[1][2], 0.011893537145881093, 1.0E-13, "jauStarpv", "23");
            this.viv(j, 0, "jauStarpv", "j");
        }

        public t_sxp() {
            let s: number;
            const p: number[] = [0, 0, 0];
            let sp: number[] = [0, 0, 0];
            s = 2.0;
            p[0] = 0.3;
            p[1] = 1.2;
            p[2] = -2.5;
            sp = jauSxp(s, p);
            this.vvd(sp[0], 0.6, 0.0, "jauSxp", "1");
            this.vvd(sp[1], 2.4, 0.0, "jauSxp", "2");
            this.vvd(sp[2], -5.0, 0.0, "jauSxp", "3");
        }

        public t_sxpv() {
            let s: number;
            const pv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            let spv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            s = 2.0;
            pv[0][0] = 0.3;
            pv[0][1] = 1.2;
            pv[0][2] = -2.5;
            pv[1][0] = 0.5;
            pv[1][1] = 3.2;
            pv[1][2] = -0.7;
            spv = jauSxpv(s, pv);
            this.vvd(spv[0][0], 0.6, 0.0, "jauSxpv", "p1");
            this.vvd(spv[0][1], 2.4, 0.0, "jauSxpv", "p2");
            this.vvd(spv[0][2], -5.0, 0.0, "jauSxpv", "p3");
            this.vvd(spv[1][0], 1.0, 0.0, "jauSxpv", "v1");
            this.vvd(spv[1][1], 6.4, 0.0, "jauSxpv", "v2");
            this.vvd(spv[1][2], -1.4, 0.0, "jauSxpv", "v3");
        }

        public t_taitt() {
            const jd: JSOFA.JulianDate = jauTaitt(2453750.5, 0.892482639);
            this.vvd(jd.djm0, 2453750.5, 1.0E-6, "jauTaitt", "t1");
            this.vvd(jd.djm1, 0.892855139, 1.0E-12, "jauTaitt", "t2");
        }

        public t_taiut1() {
            const jd: JSOFA.JulianDate = jauTaiut1(2453750.5, 0.892482639, -32.6659);
            this.vvd(jd.djm0, 2453750.5, 1.0E-6, "jauTaiut1", "u1");
            this.vvd(jd.djm1, 0.8921045614537036, 1.0E-12, "jauTaiut1", "u2");
        }

        public t_taiutc() {
            const jd: JSOFA.JulianDate = jauTaiutc(2453750.5, 0.892482639);
            this.vvd(jd.djm0, 2453750.5, 1.0E-6, "jauTaiutc", "u1");
            this.vvd(jd.djm1, 0.8921006945555555, 1.0E-12, "jauTaiutc", "u2");
        }

        public t_tcbtdb() {
            const jd: JSOFA.JulianDate = jauTcbtdb(2453750.5, 0.893019599);
            this.vvd(jd.djm0, 2453750.5, 1.0E-6, "jauTcbtdb", "b1");
            this.vvd(jd.djm1, 0.8928551362746343, 1.0E-12, "jauTcbtdb", "b2");
        }

        public t_tcgtt() {
            const jd: JSOFA.JulianDate = jauTcgtt(2453750.5, 0.892862531);
            this.vvd(jd.djm0, 2453750.5, 1.0E-6, "jauTcgtt", "t1");
            this.vvd(jd.djm1, 0.8928551387488817, 1.0E-12, "jauTcgtt", "t2");
        }

        public t_tdbtcb() {
            const jd: JSOFA.JulianDate = jauTdbtcb(2453750.5, 0.892855137);
            this.vvd(jd.djm0, 2453750.5, 1.0E-6, "jauTdbtcb", "b1");
            this.vvd(jd.djm1, 0.8930195997253657, 1.0E-12, "jauTdbtcb", "b2");
        }

        public t_tdbtt() {
            const jd: JSOFA.JulianDate = jauTdbtt(2453750.5, 0.892855137, -2.01E-4);
            this.vvd(jd.djm0, 2453750.5, 1.0E-6, "jauTdbtt", "t1");
            this.vvd(jd.djm1, 0.8928551393263889, 1.0E-12, "jauTdbtt", "t2");
        }

        public t_tr() {
            const r: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            let rt: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
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
            this.vvd(rt[0][0], 2.0, 0.0, "jauTr", "11");
            this.vvd(rt[0][1], 3.0, 0.0, "jauTr", "12");
            this.vvd(rt[0][2], 3.0, 0.0, "jauTr", "13");
            this.vvd(rt[1][0], 3.0, 0.0, "jauTr", "21");
            this.vvd(rt[1][1], 2.0, 0.0, "jauTr", "22");
            this.vvd(rt[1][2], 4.0, 0.0, "jauTr", "23");
            this.vvd(rt[2][0], 2.0, 0.0, "jauTr", "31");
            this.vvd(rt[2][1], 3.0, 0.0, "jauTr", "32");
            this.vvd(rt[2][2], 5.0, 0.0, "jauTr", "33");
        }

        public t_trxp() {
            const r: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            const p: number[] = [0, 0, 0];
            let trp: number[] = [0, 0, 0];
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
            this.vvd(trp[0], 5.2, 1.0E-12, "jauTrxp", "1");
            this.vvd(trp[1], 4.0, 1.0E-12, "jauTrxp", "2");
            this.vvd(trp[2], 5.4, 1.0E-12, "jauTrxp", "3");
        }

        public t_trxpv() {
            const r: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
            const pv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            let trpv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
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
            this.vvd(trpv[0][0], 5.2, 1.0E-12, "jauTrxpv", "p1");
            this.vvd(trpv[0][1], 4.0, 1.0E-12, "jauTrxpv", "p1");
            this.vvd(trpv[0][2], 5.4, 1.0E-12, "jauTrxpv", "p1");
            this.vvd(trpv[1][0], 3.9, 1.0E-12, "jauTrxpv", "v1");
            this.vvd(trpv[1][1], 5.3, 1.0E-12, "jauTrxpv", "v2");
            this.vvd(trpv[1][2], 4.1, 1.0E-12, "jauTrxpv", "v3");
        }

        public t_tttai() {
            const jd: JSOFA.JulianDate = jauTttai(2453750.5, 0.892482639);
            this.vvd(jd.djm0, 2453750.5, 1.0E-6, "jauTttai", "a1");
            this.vvd(jd.djm1, 0.892110139, 1.0E-12, "jauTttai", "a2");
        }

        public t_tttcg() {
            const jd: JSOFA.JulianDate = jauTttcg(2453750.5, 0.892482639);
            this.vvd(jd.djm0, 2453750.5, 1.0E-6, "jauTttcg", "g1");
            this.vvd(jd.djm1, 0.8924900312508587, 1.0E-12, "jauTttcg", "g2");
        }

        public t_tttdb() {
            const jd: JSOFA.JulianDate = jauTttdb(2453750.5, 0.892855139, -2.01E-4);
            this.vvd(jd.djm0, 2453750.5, 1.0E-6, "jauTttdb", "b1");
            this.vvd(jd.djm1, 0.8928551366736112, 1.0E-12, "jauTttdb", "b2");
        }

        public t_ttut1() {
            const jd: JSOFA.JulianDate = jauTtut1(2453750.5, 0.892855139, 64.8499);
            this.vvd(jd.djm0, 2453750.5, 1.0E-6, "jauTtut1", "u1");
            this.vvd(jd.djm1, 0.8921045614537036, 1.0E-12, "jauTtut1", "u2");
        }

        public t_ut1tai() {
            const jd: JSOFA.JulianDate = jauUt1tai(2453750.5, 0.892104561, -32.6659);
            this.vvd(jd.djm0, 2453750.5, 1.0E-6, "jauUt1tai", "a1");
            this.vvd(jd.djm1, 0.8924826385462963, 1.0E-12, "jauUt1tai", "a2");
        }

        public t_ut1tt() {
            const jd: JSOFA.JulianDate = jauUt1tt(2453750.5, 0.892104561, 64.8499);
            this.vvd(jd.djm0, 2453750.5, 1.0E-6, "jauUt1tt", "t1");
            this.vvd(jd.djm1, 0.8928551385462963, 1.0E-12, "jauUt1tt", "t2");
        }

        public t_ut1utc() {
            const jd: JSOFA.JulianDate = jauUt1utc(2453750.5, 0.892104561, 0.3341);
            this.vvd(jd.djm0, 2453750.5, 1.0E-6, "jauUt1utc", "u1");
            this.vvd(jd.djm1, 0.8921006941018519, 1.0E-12, "jauUt1utc", "u2");
        }

        public t_utctai() {
            const jd: JSOFA.JulianDate = jauUtctai(2453750.5, 0.892100694);
            this.vvd(jd.djm0, 2453750.5, 1.0E-6, "jauUtctai", "u1");
            this.vvd(jd.djm1, 0.8924826384444444, 1.0E-12, "jauUtctai", "u2");
        }

        public t_utcut1() {
            const jd: JSOFA.JulianDate = jauUtcut1(2453750.5, 0.892100694, 0.3341);
            this.vvd(jd.djm0, 2453750.5, 1.0E-6, "jauUtcut1", "u1");
            this.vvd(jd.djm1, 0.8921045608981482, 1.0E-12, "jauUtcut1", "u2");
        }

        public t_xy06() {
            const cip: JSOFA.CelestialIntermediatePole = jauXy06(2400000.5, 53736.0);
            this.vvd(cip.x, 5.791308486706011E-4, 1.0E-15, "jauXy06", "x");
            this.vvd(cip.y, 4.020579816732958E-5, 1.0E-16, "jauXy06", "y");
        }

        public t_xys00a() {
            const fr: JSOFA.ICRFrame = jauXys00a(2400000.5, 53736.0);
            this.vvd(fr.cip.x, 5.791308472168153E-4, 1.0E-14, "jauXys00a", "x");
            this.vvd(fr.cip.y, 4.0205956615915E-5, 1.0E-15, "jauXys00a", "y");
            this.vvd(fr.s, -1.2200408484715497E-8, 1.0E-18, "jauXys00a", "s");
        }

        public t_xys00b() {
            const fr: JSOFA.ICRFrame = jauXys00b(2400000.5, 53736.0);
            this.vvd(fr.cip.x, 5.791301929950209E-4, 1.0E-14, "jauXys00b", "x");
            this.vvd(fr.cip.y, 4.0205536813737205E-5, 1.0E-15, "jauXys00b", "y");
            this.vvd(fr.s, -1.2200273772850832E-8, 1.0E-18, "jauXys00b", "s");
        }

        public t_xys06a() {
            const fr: JSOFA.ICRFrame = jauXys06a(2400000.5, 53736.0);
            this.vvd(fr.cip.x, 5.791308482835292E-4, 1.0E-14, "jauXys06a", "x");
            this.vvd(fr.cip.y, 4.0205800994540205E-5, 1.0E-15, "jauXys06a", "y");
            this.vvd(fr.s, -1.22003229416458E-8, 1.0E-18, "jauXys06a", "s");
        }

        public t_zp() {
            const p: number[] = [0, 0, 0];
            p[0] = 0.3;
            p[1] = 1.2;
            p[2] = -2.5;
            jauZp(p);
            this.vvd(p[0], 0.0, 0.0, "jauZp", "1");
            this.vvd(p[1], 0.0, 0.0, "jauZp", "2");
            this.vvd(p[2], 0.0, 0.0, "jauZp", "3");
        }

        public t_zpv() {
            const pv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            pv[0][0] = 0.3;
            pv[0][1] = 1.2;
            pv[0][2] = -2.5;
            pv[1][0] = -0.5;
            pv[1][1] = 3.1;
            pv[1][2] = 0.9;
            jauZpv(pv);
            this.vvd(pv[0][0], 0.0, 0.0, "jauZpv", "p1");
            this.vvd(pv[0][1], 0.0, 0.0, "jauZpv", "p2");
            this.vvd(pv[0][2], 0.0, 0.0, "jauZpv", "p3");
            this.vvd(pv[1][0], 0.0, 0.0, "jauZpv", "v1");
            this.vvd(pv[1][1], 0.0, 0.0, "jauZpv", "v2");
            this.vvd(pv[1][2], 0.0, 0.0, "jauZpv", "v3");
        }

        public t_zr() {
            const r: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([3, 3]);
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
            this.vvd(r[0][0], 0.0, 0.0, "jauZr", "00");
            this.vvd(r[1][0], 0.0, 0.0, "jauZr", "01");
            this.vvd(r[2][0], 0.0, 0.0, "jauZr", "02");
            this.vvd(r[0][1], 0.0, 0.0, "jauZr", "10");
            this.vvd(r[1][1], 0.0, 0.0, "jauZr", "11");
            this.vvd(r[2][1], 0.0, 0.0, "jauZr", "12");
            this.vvd(r[0][2], 0.0, 0.0, "jauZr", "20");
            this.vvd(r[1][2], 0.0, 0.0, "jauZr", "21");
            this.vvd(r[2][2], 0.0, 0.0, "jauZr", "22");
        }

        public t_apcg() {
            let date1: number;
            let date2: number;
            const ebpv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            const ehp: number[] = [0, 0, 0];
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            date1 = 2456165.5;
            date2 = 0.401182685;
            ebpv[0][0] = 0.901310875;
            ebpv[0][1] = -0.417402664;
            ebpv[0][2] = -0.180982288;
            ebpv[1][0] = 0.00742727954;
            ebpv[1][1] = 0.0140507459;
            ebpv[1][2] = 0.00609045792;
            ehp[0] = 0.903358544;
            ehp[1] = -0.415395237;
            ehp[2] = -0.180084014;
            jauApcg(date1, date2, ebpv, ehp, astrom);
            this.vvd(astrom.pmt, 12.651337940273786, 1.0E-11, "jauApcg", "pmt");
            this.vvd(astrom.eb[0], 0.901310875, 1.0E-12, "jauApcg", "eb(1)");
            this.vvd(astrom.eb[1], -0.417402664, 1.0E-12, "jauApcg", "eb(2)");
            this.vvd(astrom.eb[2], -0.180982288, 1.0E-12, "jauApcg", "eb(3)");
            this.vvd(astrom.eh[0], 0.8940025429324143, 1.0E-12, "jauApcg", "eh(1)");
            this.vvd(astrom.eh[1], -0.41109302686798177, 1.0E-12, "jauApcg", "eh(2)");
            this.vvd(astrom.eh[2], -0.17821890048728703, 1.0E-12, "jauApcg", "eh(3)");
            this.vvd(astrom.em, 1.0104652958110132, 1.0E-12, "jauApcg", "em");
            this.vvd(astrom.v[0], 4.289638913597694E-5, 1.0E-16, "jauApcg", "v(1)");
            this.vvd(astrom.v[1], 8.11503405158132E-5, 1.0E-16, "jauApcg", "v(2)");
            this.vvd(astrom.v[2], 3.517555136380563E-5, 1.0E-16, "jauApcg", "v(3)");
            this.vvd(astrom.bm1, 0.9999999951686013, 1.0E-12, "jauApcg", "bm1");
            this.vvd(astrom.bpn[0][0], 1.0, 0.0, "jauApcg", "bpn(1,1)");
            this.vvd(astrom.bpn[1][0], 0.0, 0.0, "jauApcg", "bpn(2,1)");
            this.vvd(astrom.bpn[2][0], 0.0, 0.0, "jauApcg", "bpn(3,1)");
            this.vvd(astrom.bpn[0][1], 0.0, 0.0, "jauApcg", "bpn(1,2)");
            this.vvd(astrom.bpn[1][1], 1.0, 0.0, "jauApcg", "bpn(2,2)");
            this.vvd(astrom.bpn[2][1], 0.0, 0.0, "jauApcg", "bpn(3,2)");
            this.vvd(astrom.bpn[0][2], 0.0, 0.0, "jauApcg", "bpn(1,3)");
            this.vvd(astrom.bpn[1][2], 0.0, 0.0, "jauApcg", "bpn(2,3)");
            this.vvd(astrom.bpn[2][2], 1.0, 0.0, "jauApcg", "bpn(3,3)");
        }

        public t_ab() {
            const pnat: number[] = [0, 0, 0];
            const v: number[] = [0, 0, 0];
            let s: number;
            let bm1: number;
            let ppr: number[];
            pnat[0] = -0.7632196854673795;
            pnat[1] = -0.6086945398306038;
            pnat[2] = -0.21676408580639883;
            v[0] = 2.1044018893653786E-5;
            v[1] = -8.910892330442932E-5;
            v[2] = -3.863371479771657E-5;
            s = 0.9998092139570879;
            bm1 = 0.9999999950620926;
            ppr = jauAb(pnat, v, s, bm1);
            this.vvd(ppr[0], -0.7631631094219556, 1.0E-12, "jauAb", "1");
            this.vvd(ppr[1], -0.6087553082505591, 1.0E-12, "jauAb", "2");
            this.vvd(ppr[2], -0.21679262693684712, 1.0E-12, "jauAb", "3");
        }

        public t_apcg13() {
            let date1: number;
            let date2: number;
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            date1 = 2456165.5;
            date2 = 0.401182685;
            jauApcg13(date1, date2, astrom);
            this.vvd(astrom.pmt, 12.651337940273786, 1.0E-11, "jauApcg13", "pmt");
            this.vvd(astrom.eb[0], 0.9013108747340645, 1.0E-12, "jauApcg13", "eb(1)");
            this.vvd(astrom.eb[1], -0.417402664040612, 1.0E-12, "jauApcg13", "eb(2)");
            this.vvd(astrom.eb[2], -0.18098228778678177, 1.0E-12, "jauApcg13", "eb(3)");
            this.vvd(astrom.eh[0], 0.8940025429255499, 1.0E-12, "jauApcg13", "eh(1)");
            this.vvd(astrom.eh[1], -0.41109302683318966, 1.0E-12, "jauApcg13", "eh(2)");
            this.vvd(astrom.eh[2], -0.17821890060197498, 1.0E-12, "jauApcg13", "eh(3)");
            this.vvd(astrom.em, 1.0104652959646643, 1.0E-12, "jauApcg13", "em");
            this.vvd(astrom.v[0], 4.289638912941341E-5, 1.0E-16, "jauApcg13", "v(1)");
            this.vvd(astrom.v[1], 8.115034032405042E-5, 1.0E-16, "jauApcg13", "v(2)");
            this.vvd(astrom.v[2], 3.5175551355364706E-5, 1.0E-16, "jauApcg13", "v(3)");
            this.vvd(astrom.bm1, 0.9999999951686013, 1.0E-12, "jauApcg13", "bm1");
            this.vvd(astrom.bpn[0][0], 1.0, 0.0, "jauApcg13", "bpn(1,1)");
            this.vvd(astrom.bpn[1][0], 0.0, 0.0, "jauApcg13", "bpn(2,1)");
            this.vvd(astrom.bpn[2][0], 0.0, 0.0, "jauApcg13", "bpn(3,1)");
            this.vvd(astrom.bpn[0][1], 0.0, 0.0, "jauApcg13", "bpn(1,2)");
            this.vvd(astrom.bpn[1][1], 1.0, 0.0, "jauApcg13", "bpn(2,2)");
            this.vvd(astrom.bpn[2][1], 0.0, 0.0, "jauApcg13", "bpn(3,2)");
            this.vvd(astrom.bpn[0][2], 0.0, 0.0, "jauApcg13", "bpn(1,3)");
            this.vvd(astrom.bpn[1][2], 0.0, 0.0, "jauApcg13", "bpn(2,3)");
            this.vvd(astrom.bpn[2][2], 1.0, 0.0, "jauApcg13", "bpn(3,3)");
        }

        public t_apci() {
            let date1: number;
            let date2: number;
            const ebpv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            const ehp: number[] = [0, 0, 0];
            let x: number;
            let y: number;
            let s: number;
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            date1 = 2456165.5;
            date2 = 0.401182685;
            ebpv[0][0] = 0.901310875;
            ebpv[0][1] = -0.417402664;
            ebpv[0][2] = -0.180982288;
            ebpv[1][0] = 0.00742727954;
            ebpv[1][1] = 0.0140507459;
            ebpv[1][2] = 0.00609045792;
            ehp[0] = 0.903358544;
            ehp[1] = -0.415395237;
            ehp[2] = -0.180084014;
            x = 0.0013122272;
            y = -2.92808623E-5;
            s = 3.05749468E-8;
            jauApci(date1, date2, ebpv, ehp, x, y, s, astrom);
            this.vvd(astrom.pmt, 12.651337940273786, 1.0E-11, "jauApci", "pmt");
            this.vvd(astrom.eb[0], 0.901310875, 1.0E-12, "jauApci", "eb(1)");
            this.vvd(astrom.eb[1], -0.417402664, 1.0E-12, "jauApci", "eb(2)");
            this.vvd(astrom.eb[2], -0.180982288, 1.0E-12, "jauApci", "eb(3)");
            this.vvd(astrom.eh[0], 0.8940025429324143, 1.0E-12, "jauApci", "eh(1)");
            this.vvd(astrom.eh[1], -0.41109302686798177, 1.0E-12, "jauApci", "eh(2)");
            this.vvd(astrom.eh[2], -0.17821890048728703, 1.0E-12, "jauApci", "eh(3)");
            this.vvd(astrom.em, 1.0104652958110132, 1.0E-12, "jauApci", "em");
            this.vvd(astrom.v[0], 4.289638913597694E-5, 1.0E-16, "jauApci", "v(1)");
            this.vvd(astrom.v[1], 8.11503405158132E-5, 1.0E-16, "jauApci", "v(2)");
            this.vvd(astrom.v[2], 3.517555136380563E-5, 1.0E-16, "jauApci", "v(3)");
            this.vvd(astrom.bm1, 0.9999999951686013, 1.0E-12, "jauApci", "bm1");
            this.vvd(astrom.bpn[0][0], 0.9999991390295159, 1.0E-12, "jauApci", "bpn(1,1)");
            this.vvd(astrom.bpn[1][0], 4.978650072505017E-8, 1.0E-12, "jauApci", "bpn(2,1)");
            this.vvd(astrom.bpn[2][0], 0.0013122272, 1.0E-12, "jauApci", "bpn(3,1)");
            this.vvd(astrom.bpn[0][1], -1.1363366537716096E-8, 1.0E-12, "jauApci", "bpn(1,2)");
            this.vvd(astrom.bpn[1][1], 0.9999999995713155, 1.0E-12, "jauApci", "bpn(2,2)");
            this.vvd(astrom.bpn[2][1], -2.92808623E-5, 1.0E-12, "jauApci", "bpn(3,2)");
            this.vvd(astrom.bpn[0][2], -0.0013122272008952603, 1.0E-12, "jauApci", "bpn(1,3)");
            this.vvd(astrom.bpn[1][2], 2.928082217872316E-5, 1.0E-12, "jauApci", "bpn(2,3)");
            this.vvd(astrom.bpn[2][2], 0.9999991386008323, 1.0E-12, "jauApci", "bpn(3,3)");
        }

        public t_apci13() {
            let date1: number;
            let date2: number;
            let eo: number;
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            date1 = 2456165.5;
            date2 = 0.401182685;
            eo = jauApci13(date1, date2, astrom);
            this.vvd(astrom.pmt, 12.651337940273786, 1.0E-11, "jauApci13", "pmt");
            this.vvd(astrom.eb[0], 0.9013108747340645, 1.0E-12, "jauApci13", "eb(1)");
            this.vvd(astrom.eb[1], -0.417402664040612, 1.0E-12, "jauApci13", "eb(2)");
            this.vvd(astrom.eb[2], -0.18098228778678177, 1.0E-12, "jauApci13", "eb(3)");
            this.vvd(astrom.eh[0], 0.8940025429255499, 1.0E-12, "jauApci13", "eh(1)");
            this.vvd(astrom.eh[1], -0.41109302683318966, 1.0E-12, "jauApci13", "eh(2)");
            this.vvd(astrom.eh[2], -0.17821890060197498, 1.0E-12, "jauApci13", "eh(3)");
            this.vvd(astrom.em, 1.0104652959646643, 1.0E-12, "jauApci13", "em");
            this.vvd(astrom.v[0], 4.289638912941341E-5, 1.0E-16, "jauApci13", "v(1)");
            this.vvd(astrom.v[1], 8.115034032405042E-5, 1.0E-16, "jauApci13", "v(2)");
            this.vvd(astrom.v[2], 3.5175551355364706E-5, 1.0E-16, "jauApci13", "v(3)");
            this.vvd(astrom.bm1, 0.9999999951686013, 1.0E-12, "jauApci13", "bm1");
            this.vvd(astrom.bpn[0][0], 0.9999992060376762, 1.0E-12, "jauApci13", "bpn(1,1)");
            this.vvd(astrom.bpn[1][0], 4.124244860106037E-8, 1.0E-12, "jauApci13", "bpn(2,1)");
            this.vvd(astrom.bpn[2][0], 0.0012601285710517097, 1.0E-12, "jauApci13", "bpn(3,1)");
            this.vvd(astrom.bpn[0][1], -1.2822919872221307E-8, 1.0E-12, "jauApci13", "bpn(1,2)");
            this.vvd(astrom.bpn[1][1], 0.9999999997456835, 1.0E-12, "jauApci13", "bpn(2,2)");
            this.vvd(astrom.bpn[2][1], -2.255288829420525E-5, 1.0E-12, "jauApci13", "bpn(3,2)");
            this.vvd(astrom.bpn[0][2], -0.0012601285716613746, 1.0E-12, "jauApci13", "bpn(1,3)");
            this.vvd(astrom.bpn[1][2], 2.2552854229533955E-5, 1.0E-12, "jauApci13", "bpn(2,3)");
            this.vvd(astrom.bpn[2][2], 0.9999992057833604, 1.0E-12, "jauApci13", "bpn(3,3)");
            this.vvd(eo, -0.0029006187126573756, 1.0E-12, "jauApci13", "eo");
        }

        public t_apco() {
            let date1: number;
            let date2: number;
            const ebpv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            const ehp: number[] = [0, 0, 0];
            let x: number;
            let y: number;
            let s: number;
            let theta: number;
            let elong: number;
            let phi: number;
            let hm: number;
            let xp: number;
            let yp: number;
            let sp: number;
            let refa: number;
            let refb: number;
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
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
            y = -2.92808623E-5;
            s = 3.05749468E-8;
            theta = 3.14540971;
            elong = -0.527800806;
            phi = -1.2345856;
            hm = 2738.0;
            xp = 2.47230737E-7;
            yp = 1.82640464E-6;
            sp = -3.01974337E-11;
            refa = 2.01418779E-4;
            refb = -2.36140831E-7;
            jauApco(date1, date2, ebpv, ehp, x, y, s, theta, elong, phi, hm, xp, yp, sp, refa, refb, astrom);
            this.vvd(astrom.pmt, 13.252484686225873, 1.0E-11, "jauApco", "pmt");
            this.vvd(astrom.eb[0], -0.9741827110630322, 1.0E-12, "jauApco", "eb(1)");
            this.vvd(astrom.eb[1], -0.21151301901353448, 1.0E-12, "jauApco", "eb(2)");
            this.vvd(astrom.eb[2], -0.09179840186949532, 1.0E-12, "jauApco", "eb(3)");
            this.vvd(astrom.eh[0], -0.9736425571689739, 1.0E-12, "jauApco", "eh(1)");
            this.vvd(astrom.eh[1], -0.2092452125849331, 1.0E-12, "jauApco", "eh(2)");
            this.vvd(astrom.eh[2], -0.09075578152243273, 1.0E-12, "jauApco", "eh(3)");
            this.vvd(astrom.em, 0.9998233241709957, 1.0E-12, "jauApco", "em");
            this.vvd(astrom.v[0], 2.0787049929167286E-5, 1.0E-16, "jauApco", "v(1)");
            this.vvd(astrom.v[1], -8.955360107151952E-5, 1.0E-16, "jauApco", "v(2)");
            this.vvd(astrom.v[2], -3.863338994288951E-5, 1.0E-16, "jauApco", "v(3)");
            this.vvd(astrom.bm1, 0.9999999950277562, 1.0E-12, "jauApco", "bm1");
            this.vvd(astrom.bpn[0][0], 0.9999991390295159, 1.0E-12, "jauApco", "bpn(1,1)");
            this.vvd(astrom.bpn[1][0], 4.978650072505017E-8, 1.0E-12, "jauApco", "bpn(2,1)");
            this.vvd(astrom.bpn[2][0], 0.0013122272, 1.0E-12, "jauApco", "bpn(3,1)");
            this.vvd(astrom.bpn[0][1], -1.1363366537716096E-8, 1.0E-12, "jauApco", "bpn(1,2)");
            this.vvd(astrom.bpn[1][1], 0.9999999995713155, 1.0E-12, "jauApco", "bpn(2,2)");
            this.vvd(astrom.bpn[2][1], -2.92808623E-5, 1.0E-12, "jauApco", "bpn(3,2)");
            this.vvd(astrom.bpn[0][2], -0.0013122272008952603, 1.0E-12, "jauApco", "bpn(1,3)");
            this.vvd(astrom.bpn[1][2], 2.928082217872316E-5, 1.0E-12, "jauApco", "bpn(2,3)");
            this.vvd(astrom.bpn[2][2], 0.9999991386008323, 1.0E-12, "jauApco", "bpn(3,3)");
            this.vvd(astrom.along, -0.5278008060295996, 1.0E-12, "jauApco", "along");
            this.vvd(astrom.xpl, 1.133427418130753E-6, 1.0E-17, "jauApco", "xpl");
            this.vvd(astrom.ypl, 1.4533475957806462E-6, 1.0E-17, "jauApco", "ypl");
            this.vvd(astrom.sphi, -0.9440115679003211, 1.0E-12, "jauApco", "sphi");
            this.vvd(astrom.cphi, 0.3299123514971475, 1.0E-12, "jauApco", "cphi");
            this.vvd(astrom.diurab, 0, 0, "jauApco", "diurab");
            this.vvd(astrom.eral, 2.6176089039704005, 1.0E-12, "jauApco", "eral");
            this.vvd(astrom.refa, 2.01418779E-4, 1.0E-15, "jauApco", "refa");
            this.vvd(astrom.refb, -2.36140831E-7, 1.0E-18, "jauApco", "refb");
        }

        public t_apco13() {
            let utc1: number;
            let utc2: number;
            let dut1: number;
            let elong: number;
            let phi: number;
            let hm: number;
            let xp: number;
            let yp: number;
            let phpa: number;
            let tc: number;
            let rh: number;
            let wl: number;
            let eo: number;
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            utc1 = 2456384.5;
            utc2 = 0.969254051;
            dut1 = 0.1550675;
            elong = -0.527800806;
            phi = -1.2345856;
            hm = 2738.0;
            xp = 2.47230737E-7;
            yp = 1.82640464E-6;
            phpa = 731.0;
            tc = 12.8;
            rh = 0.59;
            wl = 0.55;
            eo = jauApco13(utc1, utc2, dut1, elong, phi, hm, xp, yp, phpa, tc, rh, wl, astrom);
            this.vvd(astrom.pmt, 13.252484686224758, 1.0E-11, "jauApco13", "pmt");
            this.vvd(astrom.eb[0], -0.9741827107320875, 1.0E-12, "jauApco13", "eb(1)");
            this.vvd(astrom.eb[1], -0.21151301904897166, 1.0E-12, "jauApco13", "eb(2)");
            this.vvd(astrom.eb[2], -0.09179840189496756, 1.0E-12, "jauApco13", "eb(3)");
            this.vvd(astrom.eh[0], -0.9736425572586935, 1.0E-12, "jauApco13", "eh(1)");
            this.vvd(astrom.eh[1], -0.20924521216033362, 1.0E-12, "jauApco13", "eh(2)");
            this.vvd(astrom.eh[2], -0.09075578153885665, 1.0E-12, "jauApco13", "eh(3)");
            this.vvd(astrom.em, 0.9998233240913899, 1.0E-12, "jauApco13", "em");
            this.vvd(astrom.v[0], 2.0787049945204893E-5, 1.0E-16, "jauApco13", "v(1)");
            this.vvd(astrom.v[1], -8.955360133238868E-5, 1.0E-16, "jauApco13", "v(2)");
            this.vvd(astrom.v[2], -3.863338993055887E-5, 1.0E-16, "jauApco13", "v(3)");
            this.vvd(astrom.bm1, 0.999999995027756, 1.0E-12, "jauApco13", "bm1");
            this.vvd(astrom.bpn[0][0], 0.9999991390295148, 1.0E-12, "jauApco13", "bpn(1,1)");
            this.vvd(astrom.bpn[1][0], 4.9786500753155294E-8, 1.0E-12, "jauApco13", "bpn(2,1)");
            this.vvd(astrom.bpn[2][0], 0.0013122272008502934, 1.0E-12, "jauApco13", "bpn(3,1)");
            this.vvd(astrom.bpn[0][1], -1.1363366528124865E-8, 1.0E-12, "jauApco13", "bpn(1,2)");
            this.vvd(astrom.bpn[1][1], 0.9999999995713155, 1.0E-12, "jauApco13", "bpn(2,2)");
            this.vvd(astrom.bpn[2][1], -2.9280862309753673E-5, 1.0E-12, "jauApco13", "bpn(3,2)");
            this.vvd(astrom.bpn[0][2], -0.0013122272017455537, 1.0E-12, "jauApco13", "bpn(1,3)");
            this.vvd(astrom.bpn[1][2], 2.928082218847679E-5, 1.0E-12, "jauApco13", "bpn(2,3)");
            this.vvd(astrom.bpn[2][2], 0.9999991386008312, 1.0E-12, "jauApco13", "bpn(3,3)");
            this.vvd(astrom.along, -0.5278008060295996, 1.0E-12, "jauApco13", "along");
            this.vvd(astrom.xpl, 1.133427418130753E-6, 1.0E-17, "jauApco13", "xpl");
            this.vvd(astrom.ypl, 1.4533475957806462E-6, 1.0E-17, "jauApco13", "ypl");
            this.vvd(astrom.sphi, -0.9440115679003211, 1.0E-12, "jauApco13", "sphi");
            this.vvd(astrom.cphi, 0.3299123514971475, 1.0E-12, "jauApco13", "cphi");
            this.vvd(astrom.diurab, 0, 0, "jauApco13", "diurab");
            this.vvd(astrom.eral, 2.617608909189664, 1.0E-12, "jauApco13", "eral");
            this.vvd(astrom.refa, 2.014187785940397E-4, 1.0E-15, "jauApco13", "refa");
            this.vvd(astrom.refb, -2.3614083149436963E-7, 1.0E-18, "jauApco13", "refb");
            this.vvd(eo, -0.0030205483548024128, 1.0E-14, "jauApco13", "eo");
        }

        public t_apcs() {
            let date1: number;
            let date2: number;
            const pv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            const ebpv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            const ehp: number[] = [0, 0, 0];
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
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
            this.vvd(astrom.pmt, 13.252484686225873, 1.0E-11, "jauApcs", "pmt");
            this.vvd(astrom.eb[0], -0.9741827110629881, 1.0E-12, "jauApcs", "eb(1)");
            this.vvd(astrom.eb[1], -0.2115130190136416, 1.0E-12, "jauApcs", "eb(2)");
            this.vvd(astrom.eb[2], -0.09179840186954412, 1.0E-12, "jauApcs", "eb(3)");
            this.vvd(astrom.eh[0], -0.9736425571689454, 1.0E-12, "jauApcs", "eh(1)");
            this.vvd(astrom.eh[1], -0.20924521258504358, 1.0E-12, "jauApcs", "eh(2)");
            this.vvd(astrom.eh[2], -0.090755781522483, 1.0E-12, "jauApcs", "eh(3)");
            this.vvd(astrom.em, 0.9998233241709796, 1.0E-12, "jauApcs", "em");
            this.vvd(astrom.v[0], 2.0787049932826855E-5, 1.0E-16, "jauApcs", "v(1)");
            this.vvd(astrom.v[1], -8.955360106989405E-5, 1.0E-16, "jauApcs", "v(2)");
            this.vvd(astrom.v[2], -3.863338994289409E-5, 1.0E-16, "jauApcs", "v(3)");
            this.vvd(astrom.bm1, 0.9999999950277562, 1.0E-12, "jauApcs", "bm1");
            this.vvd(astrom.bpn[0][0], 1, 0, "jauApcs", "bpn(1,1)");
            this.vvd(astrom.bpn[1][0], 0, 0, "jauApcs", "bpn(2,1)");
            this.vvd(astrom.bpn[2][0], 0, 0, "jauApcs", "bpn(3,1)");
            this.vvd(astrom.bpn[0][1], 0, 0, "jauApcs", "bpn(1,2)");
            this.vvd(astrom.bpn[1][1], 1, 0, "jauApcs", "bpn(2,2)");
            this.vvd(astrom.bpn[2][1], 0, 0, "jauApcs", "bpn(3,2)");
            this.vvd(astrom.bpn[0][2], 0, 0, "jauApcs", "bpn(1,3)");
            this.vvd(astrom.bpn[1][2], 0, 0, "jauApcs", "bpn(2,3)");
            this.vvd(astrom.bpn[2][2], 1, 0, "jauApcs", "bpn(3,3)");
        }

        public t_apcs13() {
            let date1: number;
            let date2: number;
            const pv: number[][] = <any> (function(dims) { let allocate = function(dims) { if (dims.length === 0) { return 0; } else { let array = []; for(let i = 0; i < dims[0]; i++) { array.push(allocate(dims.slice(1))); } return array; }}; return allocate(dims);})([2, 3]);
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            date1 = 2456165.5;
            date2 = 0.401182685;
            pv[0][0] = -6241497.16;
            pv[0][1] = 401346.896;
            pv[0][2] = -1251136.04;
            pv[1][0] = -29.264597;
            pv[1][1] = -455.021831;
            pv[1][2] = 0.0266151194;
            jauApcs13(date1, date2, pv, astrom);
            this.vvd(astrom.pmt, 12.651337940273786, 1.0E-11, "jauApcs13", "pmt");
            this.vvd(astrom.eb[0], 0.9012691529025251, 1.0E-12, "jauApcs13", "eb(1)");
            this.vvd(astrom.eb[1], -0.4173999812023194, 1.0E-12, "jauApcs13", "eb(2)");
            this.vvd(astrom.eb[2], -0.18099065111464296, 1.0E-12, "jauApcs13", "eb(3)");
            this.vvd(astrom.eh[0], 0.893993910176013, 1.0E-12, "jauApcs13", "eh(1)");
            this.vvd(astrom.eh[1], -0.41110538917340217, 1.0E-12, "jauApcs13", "eh(2)");
            this.vvd(astrom.eh[2], -0.17823368806369974, 1.0E-12, "jauApcs13", "eh(3)");
            this.vvd(astrom.em, 1.0104283843734911, 1.0E-12, "jauApcs13", "em");
            this.vvd(astrom.v[0], 4.279877294121698E-5, 1.0E-16, "jauApcs13", "v(1)");
            this.vvd(astrom.v[1], 7.963255087052121E-5, 1.0E-16, "jauApcs13", "v(2)");
            this.vvd(astrom.v[2], 3.517564013384692E-5, 1.0E-16, "jauApcs13", "v(3)");
            this.vvd(astrom.bm1, 0.9999999952947981, 1.0E-12, "jauApcs13", "bm1");
            this.vvd(astrom.bpn[0][0], 1, 0, "jauApcs13", "bpn(1,1)");
            this.vvd(astrom.bpn[1][0], 0, 0, "jauApcs13", "bpn(2,1)");
            this.vvd(astrom.bpn[2][0], 0, 0, "jauApcs13", "bpn(3,1)");
            this.vvd(astrom.bpn[0][1], 0, 0, "jauApcs13", "bpn(1,2)");
            this.vvd(astrom.bpn[1][1], 1, 0, "jauApcs13", "bpn(2,2)");
            this.vvd(astrom.bpn[2][1], 0, 0, "jauApcs13", "bpn(3,2)");
            this.vvd(astrom.bpn[0][2], 0, 0, "jauApcs13", "bpn(1,3)");
            this.vvd(astrom.bpn[1][2], 0, 0, "jauApcs13", "bpn(2,3)");
            this.vvd(astrom.bpn[2][2], 1, 0, "jauApcs13", "bpn(3,3)");
        }

        public t_aper() {
            let theta: number;
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            astrom.along = 1.234;
            theta = 5.678;
            jauAper(theta, astrom);
            this.vvd(astrom.eral, 6.912, 1.0E-12, "jauAper", "pmt");
        }

        public t_aper13() {
            let ut11: number;
            let ut12: number;
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            astrom.along = 1.234;
            ut11 = 2456165.5;
            ut12 = 0.401182685;
            jauAper13(ut11, ut12, astrom);
            this.vvd(astrom.eral, 3.316236661789695, 1.0E-12, "jauAper13", "pmt");
        }

        public t_apio() {
            let sp: number;
            let theta: number;
            let elong: number;
            let phi: number;
            let hm: number;
            let xp: number;
            let yp: number;
            let refa: number;
            let refb: number;
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            sp = -3.01974337E-11;
            theta = 3.14540971;
            elong = -0.527800806;
            phi = -1.2345856;
            hm = 2738.0;
            xp = 2.47230737E-7;
            yp = 1.82640464E-6;
            refa = 2.01418779E-4;
            refb = -2.36140831E-7;
            jauApio(sp, theta, elong, phi, hm, xp, yp, refa, refb, astrom);
            this.vvd(astrom.along, -0.5278008060295996, 1.0E-12, "jauApio", "along");
            this.vvd(astrom.xpl, 1.133427418130753E-6, 1.0E-17, "jauApio", "xpl");
            this.vvd(astrom.ypl, 1.4533475957806462E-6, 1.0E-17, "jauApio", "ypl");
            this.vvd(astrom.sphi, -0.9440115679003211, 1.0E-12, "jauApio", "sphi");
            this.vvd(astrom.cphi, 0.3299123514971475, 1.0E-12, "jauApio", "cphi");
            this.vvd(astrom.diurab, 5.135843661699913E-7, 1.0E-12, "jauApio", "diurab");
            this.vvd(astrom.eral, 2.6176089039704005, 1.0E-12, "jauApio", "eral");
            this.vvd(astrom.refa, 2.01418779E-4, 1.0E-15, "jauApio", "refa");
            this.vvd(astrom.refb, -2.36140831E-7, 1.0E-18, "jauApio", "refb");
        }

        public t_apio13() {
            let utc1: number;
            let utc2: number;
            let dut1: number;
            let elong: number;
            let phi: number;
            let hm: number;
            let xp: number;
            let yp: number;
            let phpa: number;
            let tc: number;
            let rh: number;
            let wl: number;
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            utc1 = 2456384.5;
            utc2 = 0.969254051;
            dut1 = 0.1550675;
            elong = -0.527800806;
            phi = -1.2345856;
            hm = 2738.0;
            xp = 2.47230737E-7;
            yp = 1.82640464E-6;
            phpa = 731.0;
            tc = 12.8;
            rh = 0.59;
            wl = 0.55;
            jauApio13(utc1, utc2, dut1, elong, phi, hm, xp, yp, phpa, tc, rh, wl, astrom);
            this.vvd(astrom.along, -0.5278008060295996, 1.0E-12, "jauApio13", "along");
            this.vvd(astrom.xpl, 1.133427418130753E-6, 1.0E-17, "jauApio13", "xpl");
            this.vvd(astrom.ypl, 1.4533475957806462E-6, 1.0E-17, "jauApio13", "ypl");
            this.vvd(astrom.sphi, -0.9440115679003211, 1.0E-12, "jauApio13", "sphi");
            this.vvd(astrom.cphi, 0.3299123514971475, 1.0E-12, "jauApio13", "cphi");
            this.vvd(astrom.diurab, 5.135843661699913E-7, 1.0E-12, "jauApio13", "diurab");
            this.vvd(astrom.eral, 2.617608909189664, 1.0E-12, "jauApio13", "eral");
            this.vvd(astrom.refa, 2.014187785940397E-4, 1.0E-15, "jauApio13", "refa");
            this.vvd(astrom.refb, -2.3614083149436963E-7, 1.0E-18, "jauApio13", "refb");
        }

        public t_atci13() {
            let rc: number;
            let dc: number;
            let pr: number;
            let pd: number;
            let px: number;
            let rv: number;
            let date1: number;
            let date2: number;
            rc = 2.71;
            dc = 0.174;
            pr = 1.0E-5;
            pd = 5.0E-6;
            px = 0.1;
            rv = 55.0;
            date1 = 2456165.5;
            date2 = 0.401182685;
            const coe: JSOFA.SphericalCoordinateEO = jauAtci13(rc, dc, pr, pd, px, rv, date1, date2);
            this.vvd(coe.pos.alpha, 2.710121572968697, 1.0E-12, "jauAtci13", "ri");
            this.vvd(coe.pos.delta, 0.1729371367219539, 1.0E-12, "jauAtci13", "di");
            this.vvd(coe.eo, -0.0029006187126573756, 1.0E-14, "jauAtci13", "eo");
        }

        public t_atciq() {
            let date1: number;
            let date2: number;
            let rc: number;
            let dc: number;
            let pr: number;
            let pd: number;
            let px: number;
            let rv: number;
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            date1 = 2456165.5;
            date2 = 0.401182685;
            jauApci13(date1, date2, astrom);
            rc = 2.71;
            dc = 0.174;
            pr = 1.0E-5;
            pd = 5.0E-6;
            px = 0.1;
            rv = 55.0;
            const co: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauAtciq(rc, dc, pr, pd, px, rv, astrom);
            this.vvd(co.alpha, 2.710121572968697, 1.0E-12, "jauAtciq", "ri");
            this.vvd(co.delta, 0.1729371367219539, 1.0E-12, "jauAtciq", "di");
        }

        public t_atciqn() {
            const b: JSOFA.Ldbody[] = [new JSOFA.Ldbody(), new JSOFA.Ldbody(), new JSOFA.Ldbody()];
            let date1: number;
            let date2: number;
            let rc: number;
            let dc: number;
            let pr: number;
            let pd: number;
            let px: number;
            let rv: number;
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            date1 = 2456165.5;
            date2 = 0.401182685;
            jauApci13(date1, date2, astrom);
            rc = 2.71;
            dc = 0.174;
            pr = 1.0E-5;
            pd = 5.0E-6;
            px = 0.1;
            rv = 55.0;
            b[0].bm = 2.8574E-4;
            b[0].dl = 3.0E-10;
            b[0].pv[0][0] = -7.81014427;
            b[0].pv[0][1] = -5.60956681;
            b[0].pv[0][2] = -1.98079819;
            b[0].pv[1][0] = 0.0030723249;
            b[0].pv[1][1] = -0.00406995477;
            b[0].pv[1][2] = -0.00181335842;
            b[1].bm = 9.5435E-4;
            b[1].dl = 3.0E-9;
            b[1].pv[0][0] = 0.738098796;
            b[1].pv[0][1] = 4.63658692;
            b[1].pv[0][2] = 1.9693136;
            b[1].pv[1][0] = -0.00755816922;
            b[1].pv[1][1] = 0.00126913722;
            b[1].pv[1][2] = 7.27999001E-4;
            b[2].bm = 1.0;
            b[2].dl = 6.0E-6;
            b[2].pv[0][0] = -7.12174377E-4;
            b[2].pv[0][1] = -0.00230478303;
            b[2].pv[0][2] = -0.00105865966;
            b[2].pv[1][0] = 6.29235213E-6;
            b[2].pv[1][1] = -3.30888387E-7;
            b[2].pv[1][2] = -2.96486623E-7;
            const co: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauAtciqn(rc, dc, pr, pd, px, rv, astrom, 3, b);
            this.vvd(co.alpha, 2.7101220081049835, 1.0E-12, "jauAtciqn", "ri");
            this.vvd(co.delta, 0.1729371916492768, 1.0E-12, "jauAtciqn", "di");
        }

        public t_atciqz() {
            let date1: number;
            let date2: number;
            let rc: number;
            let dc: number;
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            date1 = 2456165.5;
            date2 = 0.401182685;
            jauApci13(date1, date2, astrom);
            rc = 2.71;
            dc = 0.174;
            const co: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauAtciqz(rc, dc, astrom);
            this.vvd(co.alpha, 2.709994899247257, 1.0E-12, "jauAtciqz", "ri");
            this.vvd(co.delta, 0.17287407209849318, 1.0E-12, "jauAtciqz", "di");
        }

        public t_atco13() {
            let rc: number;
            let dc: number;
            let pr: number;
            let pd: number;
            let px: number;
            let rv: number;
            let utc1: number;
            let utc2: number;
            let dut1: number;
            let elong: number;
            let phi: number;
            let hm: number;
            let xp: number;
            let yp: number;
            let phpa: number;
            let tc: number;
            let rh: number;
            let wl: number;
            rc = 2.71;
            dc = 0.174;
            pr = 1.0E-5;
            pd = 5.0E-6;
            px = 0.1;
            rv = 55.0;
            utc1 = 2456384.5;
            utc2 = 0.969254051;
            dut1 = 0.1550675;
            elong = -0.527800806;
            phi = -1.2345856;
            hm = 2738.0;
            xp = 2.47230737E-7;
            yp = 1.82640464E-6;
            phpa = 731.0;
            tc = 12.8;
            rh = 0.59;
            wl = 0.55;
            const ope: JSOFA.ObservedPositionEO = jauAtco13(rc, dc, pr, pd, px, rv, utc1, utc2, dut1, elong, phi, hm, xp, yp, phpa, tc, rh, wl);
            this.vvd(ope.op.aob, 0.09251774485485516, 1.0E-12, "jauAtco13", "aob");
            this.vvd(ope.op.zob, 1.4076614052564993, 1.0E-12, "jauAtco13", "zob");
            this.vvd(ope.op.hob, -0.09265154431529725, 1.0E-12, "jauAtco13", "hob");
            this.vvd(ope.op.dob, 0.1716626560072526, 1.0E-12, "jauAtco13", "dob");
            this.vvd(ope.op.rob, 2.710260453504961, 1.0E-12, "jauAtco13", "rob");
            this.vvd(ope.eo, -0.0030205483548024128, 1.0E-14, "jauAtco13", "eo");
        }

        public t_atic13() {
            let ri: number;
            let di: number;
            let date1: number;
            let date2: number;
            ri = 2.710121572969039;
            di = 0.17293713672182304;
            date1 = 2456165.5;
            date2 = 0.401182685;
            const oe: JSOFA.SphericalCoordinateEO = jauAtic13(ri, di, date1, date2);
            this.vvd(oe.pos.alpha, 2.7101265045317167, 1.0E-12, "jauAtic13", "rc");
            this.vvd(oe.pos.delta, 0.17406325376270346, 1.0E-12, "jauAtic13", "dc");
            this.vvd(oe.eo, -0.0029006187126573756, 1.0E-14, "jauAtic13", "eo");
        }

        public t_aticq() {
            let date1: number;
            let date2: number;
            let ri: number;
            let di: number;
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            date1 = 2456165.5;
            date2 = 0.401182685;
            jauApci13(date1, date2, astrom);
            ri = 2.710121572969039;
            di = 0.17293713672182304;
            const co: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauAticq(ri, di, astrom);
            this.vvd(co.alpha, 2.7101265045317167, 1.0E-12, "jauAticq", "rc");
            this.vvd(co.delta, 0.17406325376270346, 1.0E-12, "jauAticq", "dc");
        }

        public t_aticqn() {
            let date1: number;
            let date2: number;
            let ri: number;
            let di: number;
            const b: JSOFA.Ldbody[] = [new JSOFA.Ldbody(), new JSOFA.Ldbody(), new JSOFA.Ldbody()];
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            date1 = 2456165.5;
            date2 = 0.401182685;
            jauApci13(date1, date2, astrom);
            ri = 2.709994899247599;
            di = 0.17287407209836234;
            b[0].bm = 2.8574E-4;
            b[0].dl = 3.0E-10;
            b[0].pv[0][0] = -7.81014427;
            b[0].pv[0][1] = -5.60956681;
            b[0].pv[0][2] = -1.98079819;
            b[0].pv[1][0] = 0.0030723249;
            b[0].pv[1][1] = -0.00406995477;
            b[0].pv[1][2] = -0.00181335842;
            b[1].bm = 9.5435E-4;
            b[1].dl = 3.0E-9;
            b[1].pv[0][0] = 0.738098796;
            b[1].pv[0][1] = 4.63658692;
            b[1].pv[0][2] = 1.9693136;
            b[1].pv[1][0] = -0.00755816922;
            b[1].pv[1][1] = 0.00126913722;
            b[1].pv[1][2] = 7.27999001E-4;
            b[2].bm = 1.0;
            b[2].dl = 6.0E-6;
            b[2].pv[0][0] = -7.12174377E-4;
            b[2].pv[0][1] = -0.00230478303;
            b[2].pv[0][2] = -0.00105865966;
            b[2].pv[1][0] = 6.29235213E-6;
            b[2].pv[1][1] = -3.30888387E-7;
            b[2].pv[1][2] = -2.96486623E-7;
            const co: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauAticqn(ri, di, astrom, 3, b);
            this.vvd(co.alpha, 2.7099995750330272, 1.0E-12, "jauAtciqn", "rc");
            this.vvd(co.delta, 0.173999965631647, 1.0E-12, "jauAtciqn", "dc");
        }

        public t_atio13() {
            let ri: number;
            let di: number;
            let utc1: number;
            let utc2: number;
            let dut1: number;
            let elong: number;
            let phi: number;
            let hm: number;
            let xp: number;
            let yp: number;
            let phpa: number;
            let tc: number;
            let rh: number;
            let wl: number;
            ri = 2.710121572969039;
            di = 0.17293713672182304;
            utc1 = 2456384.5;
            utc2 = 0.969254051;
            dut1 = 0.1550675;
            elong = -0.527800806;
            phi = -1.2345856;
            hm = 2738.0;
            xp = 2.47230737E-7;
            yp = 1.82640464E-6;
            phpa = 731.0;
            tc = 12.8;
            rh = 0.59;
            wl = 0.55;
            const o: JSOFA.ObservedPosition = jauAtio13(ri, di, utc1, utc2, dut1, elong, phi, hm, xp, yp, phpa, tc, rh, wl);
            this.vvd(o.aob, 0.09233952224895123, 1.0E-12, "jauAtio13", "aob");
            this.vvd(o.zob, 1.40775870451355, 1.0E-12, "jauAtio13", "zob");
            this.vvd(o.hob, -0.09247619879881698, 1.0E-12, "jauAtio13", "hob");
            this.vvd(o.dob, 0.17176534357562348, 1.0E-12, "jauAtio13", "dob");
            this.vvd(o.rob, 2.7100851079884807, 1.0E-12, "jauAtio13", "rob");
        }

        public t_atioq() {
            let utc1: number;
            let utc2: number;
            let dut1: number;
            let elong: number;
            let phi: number;
            let hm: number;
            let xp: number;
            let yp: number;
            let phpa: number;
            let tc: number;
            let rh: number;
            let wl: number;
            let ri: number;
            let di: number;
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            utc1 = 2456384.5;
            utc2 = 0.969254051;
            dut1 = 0.1550675;
            elong = -0.527800806;
            phi = -1.2345856;
            hm = 2738.0;
            xp = 2.47230737E-7;
            yp = 1.82640464E-6;
            phpa = 731.0;
            tc = 12.8;
            rh = 0.59;
            wl = 0.55;
            jauApio13(utc1, utc2, dut1, elong, phi, hm, xp, yp, phpa, tc, rh, wl, astrom);
            ri = 2.710121572969039;
            di = 0.17293713672182304;
            const o: JSOFA.ObservedPosition = jauAtioq(ri, di, astrom);
            this.vvd(o.aob, 0.09233952224895123, 1.0E-12, "jauAtioq", "aob");
            this.vvd(o.zob, 1.40775870451355, 1.0E-12, "jauAtioq", "zob");
            this.vvd(o.hob, -0.09247619879881698, 1.0E-12, "jauAtioq", "hob");
            this.vvd(o.dob, 0.17176534357562348, 1.0E-12, "jauAtioq", "dob");
            this.vvd(o.rob, 2.7100851079884807, 1.0E-12, "jauAtioq", "rob");
        }

        public t_atoc13() {
            let utc1: number;
            let utc2: number;
            let dut1: number;
            let elong: number;
            let phi: number;
            let hm: number;
            let xp: number;
            let yp: number;
            let phpa: number;
            let tc: number;
            let rh: number;
            let wl: number;
            let ob1: number;
            let ob2: number;
            utc1 = 2456384.5;
            utc2 = 0.969254051;
            dut1 = 0.1550675;
            elong = -0.527800806;
            phi = -1.2345856;
            hm = 2738.0;
            xp = 2.47230737E-7;
            yp = 1.82640464E-6;
            phpa = 731.0;
            tc = 12.8;
            rh = 0.59;
            wl = 0.55;
            ob1 = 2.7100851079868864;
            ob2 = 0.1717653435758265;
            let oc: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauAtoc13("R", ob1, ob2, utc1, utc2, dut1, elong, phi, hm, xp, yp, phpa, tc, rh, wl);
            this.vvd(oc.alpha, 2.709956744659136, 1.0E-12, "jauAtoc13", "R/rc");
            this.vvd(oc.delta, 0.17416965008984714, 1.0E-12, "jauAtoc13", "R/dc");
            ob1 = -0.09247619879782006;
            ob2 = 0.1717653435758265;
            oc = jauAtoc13("H", ob1, ob2, utc1, utc2, dut1, elong, phi, hm, xp, yp, phpa, tc, rh, wl);
            this.vvd(oc.alpha, 2.709956744659734, 1.0E-12, "jauAtoc13", "H/rc");
            this.vvd(oc.delta, 0.17416965008984714, 1.0E-12, "jauAtoc13", "H/dc");
            ob1 = 0.0923395222479499;
            ob2 = 1.4077587045137225;
            oc = jauAtoc13("A", ob1, ob2, utc1, utc2, dut1, elong, phi, hm, xp, yp, phpa, tc, rh, wl);
            this.vvd(oc.alpha, 2.709956744659734, 1.0E-12, "jauAtoc13", "A/rc");
            this.vvd(oc.delta, 0.17416965008984714, 1.0E-12, "jauAtoc13", "A/dc");
        }

        public t_atoi13() {
            let utc1: number;
            let utc2: number;
            let dut1: number;
            let elong: number;
            let phi: number;
            let hm: number;
            let xp: number;
            let yp: number;
            let phpa: number;
            let tc: number;
            let rh: number;
            let wl: number;
            let ob1: number;
            let ob2: number;
            utc1 = 2456384.5;
            utc2 = 0.969254051;
            dut1 = 0.1550675;
            elong = -0.527800806;
            phi = -1.2345856;
            hm = 2738.0;
            xp = 2.47230737E-7;
            yp = 1.82640464E-6;
            phpa = 731.0;
            tc = 12.8;
            rh = 0.59;
            wl = 0.55;
            ob1 = 2.7100851079868864;
            ob2 = 0.1717653435758265;
            let co: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauAtoi13("R", ob1, ob2, utc1, utc2, dut1, elong, phi, hm, xp, yp, phpa, tc, rh, wl);
            this.vvd(co.alpha, 2.7101215744475406, 1.0E-12, "jauAtoi13", "R/ri");
            this.vvd(co.delta, 0.17293718391166088, 1.0E-12, "jauAtoi13", "R/di");
            ob1 = -0.09247619879782006;
            ob2 = 0.1717653435758265;
            co = jauAtoi13("H", ob1, ob2, utc1, utc2, dut1, elong, phi, hm, xp, yp, phpa, tc, rh, wl);
            this.vvd(co.alpha, 2.710121574448139, 1.0E-12, "jauAtoi13", "H/ri");
            this.vvd(co.delta, 0.17293718391166088, 1.0E-12, "jauAtoi13", "H/di");
            ob1 = 0.0923395222479499;
            ob2 = 1.4077587045137225;
            co = jauAtoi13("A", ob1, ob2, utc1, utc2, dut1, elong, phi, hm, xp, yp, phpa, tc, rh, wl);
            this.vvd(co.alpha, 2.710121574448139, 1.0E-12, "jauAtoi13", "A/ri");
            this.vvd(co.delta, 0.17293718391166088, 1.0E-12, "jauAtoi13", "A/di");
        }

        public t_atoiq() {
            let utc1: number;
            let utc2: number;
            let dut1: number;
            let elong: number;
            let phi: number;
            let hm: number;
            let xp: number;
            let yp: number;
            let phpa: number;
            let tc: number;
            let rh: number;
            let wl: number;
            let ob1: number;
            let ob2: number;
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            utc1 = 2456384.5;
            utc2 = 0.969254051;
            dut1 = 0.1550675;
            elong = -0.527800806;
            phi = -1.2345856;
            hm = 2738.0;
            xp = 2.47230737E-7;
            yp = 1.82640464E-6;
            phpa = 731.0;
            tc = 12.8;
            rh = 0.59;
            wl = 0.55;
            jauApio13(utc1, utc2, dut1, elong, phi, hm, xp, yp, phpa, tc, rh, wl, astrom);
            ob1 = 2.7100851079868864;
            ob2 = 0.1717653435758265;
            let co: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauAtoiq("R", ob1, ob2, astrom);
            this.vvd(co.alpha, 2.7101215744475406, 1.0E-12, "jauAtoiq", "R/ri");
            this.vvd(co.delta, 0.17293718391166088, 1.0E-12, "jauAtoiq", "R/di");
            ob1 = -0.09247619879782006;
            ob2 = 0.1717653435758265;
            co = jauAtoiq("H", ob1, ob2, astrom);
            this.vvd(co.alpha, 2.710121574448139, 1.0E-12, "jauAtoiq", "H/ri");
            this.vvd(co.delta, 0.17293718391166088, 1.0E-12, "jauAtoiq", "H/di");
            ob1 = 0.0923395222479499;
            ob2 = 1.4077587045137225;
            co = jauAtoiq("A", ob1, ob2, astrom);
            this.vvd(co.alpha, 2.710121574448139, 1.0E-12, "jauAtoiq", "A/ri");
            this.vvd(co.delta, 0.17293718391166088, 1.0E-12, "jauAtoiq", "A/di");
        }

        public t_ld() {
            let bm: number;
            const p: number[] = [0, 0, 0];
            const q: number[] = [0, 0, 0];
            const e: number[] = [0, 0, 0];
            let em: number;
            let dlim: number;
            let p1: number[];
            bm = 2.8574E-4;
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
            dlim = 3.0E-10;
            p1 = jauLd(bm, p, q, e, em, dlim);
            this.vvd(p1[0], -0.7632762548968159, 1.0E-12, "jauLd", "1");
            this.vvd(p1[1], -0.6086337670823763, 1.0E-12, "jauLd", "2");
            this.vvd(p1[2], -0.2167355431320547, 1.0E-12, "jauLd", "3");
        }

        public t_ldn() {
            let n: number;
            const b: JSOFA.Ldbody[] = [new JSOFA.Ldbody(), new JSOFA.Ldbody(), new JSOFA.Ldbody()];
            const ob: number[] = [0, 0, 0];
            const sc: number[] = [0, 0, 0];
            let sn: number[];
            n = 3;
            b[0].bm = 2.8574E-4;
            b[0].dl = 3.0E-10;
            b[0].pv[0][0] = -7.81014427;
            b[0].pv[0][1] = -5.60956681;
            b[0].pv[0][2] = -1.98079819;
            b[0].pv[1][0] = 0.0030723249;
            b[0].pv[1][1] = -0.00406995477;
            b[0].pv[1][2] = -0.00181335842;
            b[1].bm = 9.5435E-4;
            b[1].dl = 3.0E-9;
            b[1].pv[0][0] = 0.738098796;
            b[1].pv[0][1] = 4.63658692;
            b[1].pv[0][2] = 1.9693136;
            b[1].pv[1][0] = -0.00755816922;
            b[1].pv[1][1] = 0.00126913722;
            b[1].pv[1][2] = 7.27999001E-4;
            b[2].bm = 1.0;
            b[2].dl = 6.0E-6;
            b[2].pv[0][0] = -7.12174377E-4;
            b[2].pv[0][1] = -0.00230478303;
            b[2].pv[0][2] = -0.00105865966;
            b[2].pv[1][0] = 6.29235213E-6;
            b[2].pv[1][1] = -3.30888387E-7;
            b[2].pv[1][2] = -2.96486623E-7;
            ob[0] = -0.974170437;
            ob[1] = -0.2115201;
            ob[2] = -0.0917583114;
            sc[0] = -0.763276255;
            sc[1] = -0.608633767;
            sc[2] = -0.216735543;
            sn = jauLdn(n, b, ob, sc);
            this.vvd(sn[0], -0.7632762579693334, 1.0E-12, "jauLdn", "1");
            this.vvd(sn[1], -0.6086337636093002, 1.0E-12, "jauLdn", "2");
            this.vvd(sn[2], -0.2167355420646328, 1.0E-12, "jauLdn", "3");
        }

        public t_ldsun() {
            const p: number[] = [0, 0, 0];
            const e: number[] = [0, 0, 0];
            let em: number;
            let p1: number[];
            p[0] = -0.763276255;
            p[1] = -0.608633767;
            p[2] = -0.216735543;
            e[0] = -0.973644023;
            e[1] = -0.20925523;
            e[2] = -0.0907169552;
            em = 0.999809214;
            p1 = jauLdsun(p, e, em);
            this.vvd(p1[0], -0.7632762580731414, 1.0E-12, "jauLdsun", "1");
            this.vvd(p1[1], -0.6086337635262647, 1.0E-12, "jauLdsun", "2");
            this.vvd(p1[2], -0.21673554193223213, 1.0E-12, "jauLdsun", "3");
        }

        public t_pmpx() {
            let rc: number;
            let dc: number;
            let pr: number;
            let pd: number;
            let px: number;
            let rv: number;
            let pmt: number;
            const pob: number[] = [0, 0, 0];
            let pco: number[];
            rc = 1.234;
            dc = 0.789;
            pr = 1.0E-5;
            pd = -2.0E-5;
            px = 0.01;
            rv = 10.0;
            pmt = 8.75;
            pob[0] = 0.9;
            pob[1] = 0.4;
            pob[2] = 0.1;
            pco = jauPmpx(rc, dc, pr, pd, px, rv, pmt, pob);
            this.vvd(pco[0], 0.23281376239603085, 1.0E-12, "jauPmpx", "1");
            this.vvd(pco[1], 0.6651097085397856, 1.0E-12, "jauPmpx", "2");
            this.vvd(pco[2], 0.709525776589636, 1.0E-12, "jauPmpx", "3");
        }

        public t_pmsafe() {
            let ra1: number;
            let dec1: number;
            let pmr1: number;
            let pmd1: number;
            let px1: number;
            let rv1: number;
            let ep1a: number;
            let ep1b: number;
            let ep2a: number;
            let ep2b: number;
            ra1 = 1.234;
            dec1 = 0.789;
            pmr1 = 1.0E-5;
            pmd1 = -2.0E-5;
            px1 = 0.01;
            rv1 = 10.0;
            ep1a = 2400000.5;
            ep1b = 48348.5625;
            ep2a = 2400000.5;
            ep2b = 51544.5;
            const cc: org.jastronomy.jsofa.JSOFA.CatalogCoords = jauPmsafe(ra1, dec1, pmr1, pmd1, px1, rv1, ep1a, ep1b, ep2a, ep2b);
            this.vvd(cc.pos.alpha, 1.234087484501017, 1.0E-12, "jauPmsafe", "ra2");
            this.vvd(cc.pos.delta, 0.7888249982450468, 1.0E-12, "jauPmsafe", "dec2");
            this.vvd(cc.pm.alpha, 9.996457663586074E-6, 1.0E-12, "jauPmsafe", "pmr2");
            this.vvd(cc.pm.delta, -2.0000400851067547E-5, 1.0E-16, "jauPmsafe", "pmd2");
            this.vvd(cc.px, 0.009999997295356831, 1.0E-12, "jauPmsafe", "px2");
            this.vvd(cc.rv, 10.3846838029392, 1.0E-10, "jauPmsafe", "rv2");
        }

        public t_pvtob() {
            let elong: number;
            let phi: number;
            let hm: number;
            let xp: number;
            let yp: number;
            let sp: number;
            let theta: number;
            let pv: number[][];
            elong = 2.0;
            phi = 0.5;
            hm = 3000.0;
            xp = 1.0E-6;
            yp = -5.0E-7;
            sp = 1.0E-8;
            theta = 5.0;
            pv = jauPvtob(elong, phi, hm, xp, yp, sp, theta);
            this.vvd(pv[0][0], 4225081.367071159, 1.0E-5, "jauPvtob", "p(1)");
            this.vvd(pv[0][1], 3681943.215856198, 1.0E-5, "jauPvtob", "p(2)");
            this.vvd(pv[0][2], 3041149.3992412607, 1.0E-5, "jauPvtob", "p(3)");
            this.vvd(pv[1][0], -268.49153893659985, 1.0E-9, "jauPvtob", "v(1)");
            this.vvd(pv[1][1], 308.0977983288903, 1.0E-9, "jauPvtob", "v(2)");
            this.vvd(pv[1][2], 0, 0, "jauPvtob", "v(3)");
        }

        public t_refco() {
            let phpa: number;
            let tc: number;
            let rh: number;
            let wl: number;
            phpa = 800.0;
            tc = 10.0;
            rh = 0.9;
            wl = 0.4;
            const ref: JSOFA.RefCos = jauRefco(phpa, tc, rh, wl);
            this.vvd(ref.a, 2.264949956241415E-4, 1.0E-15, "jauRefco", "refa");
            this.vvd(ref.b, -2.598658261729344E-7, 1.0E-18, "jauRefco", "refb");
        }

        public t_d2dtf() {
            const c: JSOFA.CalendarHMS = jauD2dtf("UTC", 5, 2400000.5, 49533.99999);
            this.viv(c.iy, 1994, "jauD2dtf", "y");
            this.viv(c.im, 6, "jauD2dtf", "mo");
            this.viv(c.id, 30, "jauD2dtf", "d");
            this.viv(c.ihmsf[0], 23, "jauD2dtf", "h");
            this.viv(c.ihmsf[1], 59, "jauD2dtf", "m");
            this.viv(c.ihmsf[2], 60, "jauD2dtf", "s");
            this.viv(c.ihmsf[3], 13599, "jauD2dtf", "f");
        }

        /**
         * - - - - - - - -
         * t _ d t f 2 d
         * - - - - - - - -
         * 
         * Test jauDtf2d function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  jauDtf2d, vvd, viv
         * 
         * This revision:  2013 August 7
         * @throws JSOFAInternalError
         * @throws JSOFAIllegalParameter
         */
        public t_dtf2d() {
            const jd: JSOFA.JulianDate = jauDtf2d("UTC", 1994, 6, 30, 23, 59, 60.13599);
            this.vvd(jd.djm0 + jd.djm1, 2449534.49999, 1.0E-6, "jauDtf2d", "u");
        }

        /**
         * 
         * Test jauG2icrs function.
         * 
         * Called:  jauG2icrs, vvd
         * 
         * This revision:  2015 March 02
         */
        public t_g2icrs() {
            let dl: number;
            let db: number;
            let co: org.jastronomy.jsofa.JSOFA.SphericalCoordinate;
            dl = 5.585053606381854;
            db = -0.7853981633974483;
            co = jauG2icrs(dl, db);
            this.vvd(co.alpha, 5.933807430222719, 1.0E-14, "jauG2icrs", "Ra");
            this.vvd(co.delta, -1.1784870613579945, 1.0E-14, "jauG2icrs", "Dec");
        }

        /**
         * 
         * Test jauIcrs2g function.
         * 
         * Called:  jauIcrs2g, vvd
         * 
         * This revision:  2015 March 02
         */
        public t_icrs2g() {
            let dr: number;
            let dd: number;
            dr = 5.933807430222719;
            dd = -1.1784870613579945;
            const co: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauIcrs2g(dr, dd);
            this.vvd(co.alpha, 5.585053606381854, 1.0E-14, "jauIcrs2g", "L");
            this.vvd(co.delta, -0.7853981633974483, 1.0E-14, "jauIcrs2g", "B");
        }

        /**
         * 
         * Test jauEceq06 function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  jauEceq06, vvd
         * 
         * This revision:  2016 March 12
         */
        public t_eceq06() {
            let date1: number;
            let date2: number;
            let dl: number;
            let db: number;
            date1 = 2456165.5;
            date2 = 0.401182685;
            dl = 5.1;
            db = -0.9;
            const co: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauEceq06(date1, date2, dl, db);
            this.vvd(co.alpha, 5.533459733613628, 1.0E-14, "jauEceq06", "dr");
            this.vvd(co.delta, -1.2465429325544806, 1.0E-14, "jauEceq06", "dd");
        }

        /**
         * 
         * Test jauEcm06 function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  jauEcm06, vvd
         * 
         * This revision:  2016 March 12
         */
        public t_ecm06() {
            let date1: number;
            let date2: number;
            date1 = 2456165.5;
            date2 = 0.401182685;
            const rm: number[][] = jauEcm06(date1, date2);
            this.vvd(rm[0][0], 0.9999952427708702, 1.0E-14, "jauEcm06", "rm11");
            this.vvd(rm[0][1], -0.0028290620576630426, 1.0E-14, "jauEcm06", "rm12");
            this.vvd(rm[0][2], -0.0012291637411000176, 1.0E-14, "jauEcm06", "rm13");
            this.vvd(rm[1][0], 0.0030845468769086534, 1.0E-14, "jauEcm06", "rm21");
            this.vvd(rm[1][1], 0.9174891871550392, 1.0E-14, "jauEcm06", "rm22");
            this.vvd(rm[1][2], 0.3977487611849338, 1.0E-14, "jauEcm06", "rm23");
            this.vvd(rm[2][0], 2.488512951527406E-6, 1.0E-14, "jauEcm06", "rm31");
            this.vvd(rm[2][1], -0.39775066041611956, 1.0E-14, "jauEcm06", "rm32");
            this.vvd(rm[2][2], 0.9174935488232863, 1.0E-14, "jauEcm06", "rm33");
        }

        /**
         * 
         * Test jauEqec06 function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  jauEqec06, vvd
         * 
         * This revision:  2016 March 12
         */
        public t_eqec06() {
            let date1: number;
            let date2: number;
            let dr: number;
            let dd: number;
            date1 = 1234.5;
            date2 = 2440000.5;
            dr = 1.234;
            dd = 0.987;
            const co: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauEqec06(date1, date2, dr, dd);
            this.vvd(co.alpha, 1.3425099189946545, 1.0E-14, "jauEqec06", "dl");
            this.vvd(co.delta, 0.5926215259704608, 1.0E-14, "jauEqec06", "db");
        }

        /**
         * Test jauLteceq function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  jauLteceq, vvd
         * 
         * This revision:  2016 March 12
         */
        public t_lteceq() {
            let epj: number;
            let dl: number;
            let db: number;
            epj = 2500.0;
            dl = 1.5;
            db = 0.6;
            const co: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauLteceq(epj, dl, db);
            this.vvd(co.alpha, 1.2751560218619211, 1.0E-14, "jauLteceq", "dr");
            this.vvd(co.delta, 0.9966573543519205, 1.0E-14, "jauLteceq", "dd");
        }

        /**
         * Test jauLtecm function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  jauLtecm, vvd
         * 
         * This revision:  2016 March 12
         */
        public t_ltecm() {
            let epj: number;
            epj = -3000.0;
            const rm: number[][] = jauLtecm(epj);
            this.vvd(rm[0][0], 0.3564105644859789, 1.0E-14, "jauLtecm", "rm11");
            this.vvd(rm[0][1], 0.8530575738617683, 1.0E-14, "jauLtecm", "rm12");
            this.vvd(rm[0][2], 0.381135520779506, 1.0E-14, "jauLtecm", "rm13");
            this.vvd(rm[1][0], -0.934328346964071, 1.0E-14, "jauLtecm", "rm21");
            this.vvd(rm[1][1], 0.3247830597681746, 1.0E-14, "jauLtecm", "rm22");
            this.vvd(rm[1][2], 0.14678727515359408, 1.0E-14, "jauLtecm", "rm23");
            this.vvd(rm[2][0], 0.0014316361912011678, 1.0E-14, "jauLtecm", "rm31");
            this.vvd(rm[2][1], -0.4084222566960599, 1.0E-14, "jauLtecm", "rm32");
            this.vvd(rm[2][2], 0.9127919865189031, 1.0E-14, "jauLtecm", "rm33");
        }

        /**
         * 
         * Test jauLteqec function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  jauLteqec, vvd
         * 
         * This revision:  2016 March 12
         */
        public t_lteqec() {
            let epj: number;
            let dr: number;
            let dd: number;
            epj = -1500.0;
            dr = 1.234;
            dd = 0.987;
            const co: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauLteqec(epj, dr, dd);
            this.vvd(co.alpha, 0.5039483649047115, 1.0E-14, "jauLteqec", "dl");
            this.vvd(co.delta, 0.5848534459726225, 1.0E-14, "jauLteqec", "db");
        }

        /**
         * Test jauLtp function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  jauLtp, vvd
         * 
         * This revision:  2016 March 12
         */
        public t_ltp() {
            let epj: number;
            epj = 1666.666;
            const rp: number[][] = jauLtp(epj);
            this.vvd(rp[0][0], 0.9967044141159214, 1.0E-14, "jauLtp", "rp11");
            this.vvd(rp[0][1], 0.0743780189319321, 1.0E-14, "jauLtp", "rp12");
            this.vvd(rp[0][2], 0.03237624409345603, 1.0E-14, "jauLtp", "rp13");
            this.vvd(rp[1][0], -0.07437802731819618, 1.0E-14, "jauLtp", "rp21");
            this.vvd(rp[1][1], 0.9972293894454534, 1.0E-14, "jauLtp", "rp22");
            this.vvd(rp[1][2], -0.0012057688427235932, 1.0E-14, "jauLtp", "rp23");
            this.vvd(rp[2][0], -0.03237622482766576, 1.0E-14, "jauLtp", "rp31");
            this.vvd(rp[2][1], -0.001206286039697609, 1.0E-14, "jauLtp", "rp32");
            this.vvd(rp[2][2], 0.9994750246704011, 1.0E-14, "jauLtp", "rp33");
        }

        /**
         * Test jauLtpb function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  jauLtpb, vvd
         * 
         * This revision:  2016 March 12
         */
        public t_ltpb() {
            let epj: number;
            epj = 1666.666;
            const rpb: number[][] = jauLtpb(epj);
            this.vvd(rpb[0][0], 0.9967044167723272, 1.0E-14, "jauLtpb", "rpb11");
            this.vvd(rpb[0][1], 0.0743779473120334, 1.0E-14, "jauLtpb", "rpb12");
            this.vvd(rpb[0][2], 0.03237632684841626, 1.0E-14, "jauLtpb", "rpb13");
            this.vvd(rpb[1][0], -0.07437795663437177, 1.0E-14, "jauLtpb", "rpb21");
            this.vvd(rpb[1][1], 0.9972293947500014, 1.0E-14, "jauLtpb", "rpb22");
            this.vvd(rpb[1][2], -0.0012057418659112433, 1.0E-14, "jauLtpb", "rpb23");
            this.vvd(rpb[2][0], -0.03237630543224665, 1.0E-14, "jauLtpb", "rpb31");
            this.vvd(rpb[2][1], -0.0012063167910764854, 1.0E-14, "jauLtpb", "rpb32");
            this.vvd(rpb[2][2], 0.9994750220222439, 1.0E-14, "jauLtpb", "rpb33");
        }

        /**
         * Test jauLtpecl function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  jauLtpecl, vvd
         * 
         * This revision:  2016 March 12
         */
        public t_ltpecl() {
            let epj: number;
            epj = -1500.0;
            const vec: number[] = jauLtpecl(epj);
            this.vvd(vec[0], 4.7686256764770964E-4, 1.0E-14, "jauLtpecl", "vec1");
            this.vvd(vec[1], -0.40522595330918754, 1.0E-14, "jauLtpecl", "vec2");
            this.vvd(vec[2], 0.9142164401096448, 1.0E-14, "jauLtpecl", "vec3");
        }

        /**
         * 
         * Test jauLtpequ function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  jauLtpequ, vvd
         * 
         * This revision:  2016 March 12
         */
        public t_ltpequ() {
            let epj: number;
            epj = -2500.0;
            const veq: number[] = jauLtpequ(epj);
            this.vvd(veq[0], -0.35866525602373267, 1.0E-14, "jauLtpequ", "veq1");
            this.vvd(veq[1], -0.19969789107711286, 1.0E-14, "jauLtpequ", "veq2");
            this.vvd(veq[2], 0.911855244225082, 1.0E-14, "jauLtpequ", "veq3");
        }

        /**
         * - - - - - - - -
         * t _ a e 2 h d
         * - - - - - - - -
         * 
         * Test iauAe2hd function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  iauAe2hd and vvd
         * 
         * This revision:  2017 October 21
         */
        public t_ae2hd() {
            let a: number;
            let e: number;
            let p: number;
            a = 5.5;
            e = 1.1;
            p = 0.7;
            const ec: JSOFA.EquatorialCoordinate = jauAe2hd(a, e, p);
            this.vvd(ec.ha, 0.593329111550731, 1.0E-14, "jauAe2hd", "h");
            this.vvd(ec.dec, 0.9613934761647818, 1.0E-14, "jauAe2hd", "d");
        }

        public t_hd2ae() {
            let h: number;
            let d: number;
            let p: number;
            h = 1.1;
            d = 1.2;
            p = 0.3;
            const hc: JSOFA.HorizonCoordinate = jauHd2ae(h, d, p);
            this.vvd(hc.az, 5.916889243730066, 1.0E-13, "jauHd2ae", "a");
            this.vvd(hc.el, 0.44721863049904864, 1.0E-14, "jauHd2ae", "e");
        }

        /**
         * - - - - - - - -
         * t _ h d 2 p a
         * - - - - - - - -
         * 
         * Test jauHd2pa function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  jauHd2pa and vvd
         * 
         * This revision:  2017 October 21
         */
        public t_hd2pa() {
            let h: number;
            let d: number;
            let p: number;
            let q: number;
            h = 1.1;
            d = 1.2;
            p = 0.3;
            q = jauHd2pa(h, d, p);
            this.vvd(q, 1.9062274280019955, 1.0E-13, "jauHd2pa", "q");
        }

        /**
         * - - - - - - - -
         * t _ t p o r s
         * - - - - - - - -
         * 
         * Test jauTpors function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  jauTpors, vvd, viv
         * 
         * This revision:  2017 October 21
         */
        public t_tpors() {
            let xi: number;
            let eta: number;
            let ra: number;
            let dec: number;
            xi = -0.03;
            eta = 0.07;
            ra = 1.3;
            dec = 1.5;
            const tps: JSOFA.TangentPointSolution = jauTpors(xi, eta, ra, dec);
            this.vvd(tps.sol1.alpha, 1.7366215777832088, 1.0E-13, "jauTpors", "az1");
            this.vvd(tps.sol1.delta, 1.4367365618440904, 1.0E-13, "jauTpors", "bz1");
            this.vvd(tps.sol2.alpha, 4.004971075806584, 1.0E-13, "jauTpors", "az2");
            this.vvd(tps.sol2.delta, 1.565084088476418, 1.0E-13, "jauTpors", "bz2");
            this.viv(tps.nsolutions, 2, "jauTpors", "n");
        }

        public t_tporv() {
            let xi: number;
            let eta: number;
            let ra: number;
            let dec: number;
            let v: number[] = [0, 0, 0];
            xi = -0.03;
            eta = 0.07;
            ra = 1.3;
            dec = 1.5;
            v = jauS2c(ra, dec);
            const tpc: JSOFA.TangentPointDirectionCosines = jauTporv(xi, eta, v);
            this.vvd(tpc.dc1[0], -0.022062528223668887, 1.0E-15, "jauTporv", "x1");
            this.vvd(tpc.dc1[1], 0.1318251060359645, 1.0E-14, "jauTporv", "y1");
            this.vvd(tpc.dc1[2], 0.9910274397144544, 1.0E-14, "jauTporv", "z1");
            this.vvd(tpc.dc2[0], -0.0037122117638019683, 1.0E-16, "jauTporv", "x2");
            this.vvd(tpc.dc2[1], -0.0043415199562998365, 1.0E-16, "jauTporv", "y2");
            this.vvd(tpc.dc2[2], 0.9999836852110587, 1.0E-14, "jauTporv", "z2");
            this.viv(tpc.nsolution, 2, "jauTporv", "n");
        }

        /**
         * - - - - - - - -
         * t _ t p s t s
         * - - - - - - - -
         * 
         * Test jauTpsts function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  jauTpsts, vvd
         * 
         * This revision:  2017 October 21
         */
        public t_tpsts() {
            let xi: number;
            let eta: number;
            let raz: number;
            let decz: number;
            xi = -0.03;
            eta = 0.07;
            raz = 2.3;
            decz = 1.5;
            const sc: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauTpsts(xi, eta, raz, decz);
            this.vvd(sc.alpha, 0.759612716735963, 1.0E-14, "jauTpsts", "ra");
            this.vvd(sc.delta, 1.5408646451092631, 1.0E-13, "jauTpsts", "dec");
        }

        /**
         * - - - - - - - -
         * t _ t p s t v
         * - - - - - - - -
         * 
         * Test jauTpstv function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  jauTpstv, jauS2c, vvd
         * 
         * This revision:  2017 October 21
         */
        public t_tpstv() {
            let xi: number;
            let eta: number;
            let raz: number;
            let decz: number;
            let vz: number[];
            let v: number[];
            xi = -0.03;
            eta = 0.07;
            raz = 2.3;
            decz = 1.5;
            vz = jauS2c(raz, decz);
            v = jauTpstv(xi, eta, vz);
            this.vvd(v[0], 0.021700304549073766, 1.0E-15, "jauTpstv", "x");
            this.vvd(v[1], 0.020609095905353675, 1.0E-15, "jauTpstv", "y");
            this.vvd(v[2], 0.9995520806583523, 1.0E-14, "jauTpstv", "z");
        }

        public t_tpxes() {
            let ra: number;
            let dec: number;
            let raz: number;
            let decz: number;
            ra = 1.3;
            dec = 1.55;
            raz = 2.3;
            decz = 1.5;
            const tpc: JSOFA.TangentPlaneCoordinate = jauTpxes(ra, dec, raz, decz);
            this.vvd(tpc.xi, -0.017532009832369806, 1.0E-15, "jauTpxes", "xi");
            this.vvd(tpc.eta, 0.05962940005778713, 1.0E-15, "jauTpxes", "eta");
            this.viv(tpc.status, 0, "jauTpxes", "j");
        }

        /**
         * - - - - - - - -
         * t _ t p x e v
         * - - - - - - - -
         * 
         * Test jauTpxev function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  jauTpxev, jauS2c, vvd
         * 
         * This revision:  2017 October 21
         */
        public t_tpxev() {
            let ra: number;
            let dec: number;
            let raz: number;
            let decz: number;
            let v: number[];
            let vz: number[];
            ra = 1.3;
            dec = 1.55;
            raz = 2.3;
            decz = 1.5;
            v = jauS2c(ra, dec);
            vz = jauS2c(raz, decz);
            const tpc: JSOFA.TangentPlaneCoordinate = jauTpxev(v, vz);
            this.vvd(tpc.xi, -0.017532009832369806, 1.0E-15, "jauTpxev", "xi");
            this.vvd(tpc.eta, 0.05962940005778713, 1.0E-15, "jauTpxev", "eta");
            this.viv(tpc.status, 0, "jauTpxev", "j");
        }

        /**
         * - - - - - - - -
         * t _ f k 4 2 5
         * - - - - - - - -
         * 
         * Test iauFk425 function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  iauFk425, vvd
         * 
         * This revision:  2018 December 6
         */
        public t_fk425() {
            let r1950: number;
            let d1950: number;
            let dr1950: number;
            let dd1950: number;
            let p1950: number;
            let v1950: number;
            r1950 = 0.07626899753879587;
            d1950 = -1.1374053783996059;
            dr1950 = 1.9737492178490876E-5;
            dd1950 = 5.659714913272723E-6;
            p1950 = 0.134;
            v1950 = 8.7;
            const c: org.jastronomy.jsofa.JSOFA.CatalogCoords = jauFk425(r1950, d1950, dr1950, dd1950, p1950, v1950);
            this.vvd(c.pos.alpha, 0.08757989933556445, 1.0E-14, "iauFk425", "r2000");
            this.vvd(c.pos.delta, -1.132279113042092, 1.0E-12, "iauFk425", "d2000");
            this.vvd(c.pm.alpha, 1.953670614474396E-5, 1.0E-17, "iauFk425", "dr2000");
            this.vvd(c.pm.delta, 5.63768667865964E-6, 1.0E-18, "iauFk425", "dd2000");
            this.vvd(c.px, 0.13399199505827677, 1.0E-13, "iauFk425", "p2000");
            this.vvd(c.rv, 8.736999669183529, 1.0E-12, "iauFk425", "v2000");
        }

        /**
         * - - - - - - - -
         * t _ f k 4 5 z
         * - - - - - - - -
         * 
         * Test iauFk45z function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  iauFk45z, vvd
         * 
         * This revision:  2018 December 6
         */
        public t_fk45z() {
            let r1950: number;
            let d1950: number;
            let bepoch: number;
            r1950 = 0.01602284975382961;
            d1950 = -0.1164347929099906;
            bepoch = 1954.6776176252567;
            const r: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauFk45z(r1950, d1950, bepoch);
            this.vvd(r.alpha, 0.027192959116068623, 1.0E-15, "iauFk45z", "r2000");
            this.vvd(r.delta, -0.1115766001565927, 1.0E-13, "iauFk45z", "d2000");
        }

        /**
         * - - - - - - - -
         * t _ f k 5 2 4
         * - - - - - - - -
         * 
         * Test iauFk524 function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  iauFk524, vvd
         * 
         * This revision:  2018 December 6
         */
        public t_fk524() {
            let r2000: number;
            let d2000: number;
            let dr2000: number;
            let dd2000: number;
            let p2000: number;
            let v2000: number;
            r2000 = 0.8723503576487276;
            d2000 = -0.7517076365138887;
            dr2000 = 2.0194477554304722E-5;
            dd2000 = 3.5415639405051605E-6;
            p2000 = 0.1559;
            v2000 = 86.87;
            const r: org.jastronomy.jsofa.JSOFA.CatalogCoords = jauFk524(r2000, d2000, dr2000, dd2000, p2000, v2000);
            this.vvd(r.pos.alpha, 0.8636359659799604, 1.0E-13, "iauFk524", "r1950");
            this.vvd(r.pos.delta, -0.7550281733160843, 1.0E-13, "iauFk524", "d1950");
            this.vvd(r.pm.alpha, 2.0236281927471725E-5, 1.0E-17, "iauFk524", "dr1950");
            this.vvd(r.pm.delta, 3.6244597549353345E-6, 1.0E-18, "iauFk524", "dd1950");
            this.vvd(r.px, 0.15600799632993903, 1.0E-13, "iauFk524", "p1950");
            this.vvd(r.rv, 86.79606353469164, 1.0E-11, "iauFk524", "v1950");
        }

        /**
         * - - - - - - - -
         * t _ f k 5 4 z
         * - - - - - - - -
         * 
         * Test iauFk54z function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  iauFk54z, vvd
         * 
         * This revision:  2018 December 6
         */
        public t_fk54z() {
            let r2000: number;
            let d2000: number;
            let bepoch: number;
            r2000 = 0.02719026625066316;
            d2000 = -0.11158151707387548;
            bepoch = 1954.6773081603164;
            const r: org.jastronomy.jsofa.JSOFA.CatalogCoords = jauFk54z(r2000, d2000, bepoch);
            this.vvd(r.pos.alpha, 0.016020155883900655, 1.0E-14, "iauFk54z", "r1950");
            this.vvd(r.pos.delta, -0.11643971011107654, 1.0E-13, "iauFk54z", "d1950");
            this.vvd(r.pm.alpha, -1.1757126484710908E-8, 1.0E-20, "iauFk54z", "dr1950");
            this.vvd(r.pm.delta, 2.108109051316431E-8, 1.0E-20, "iauFk54z", "dd1950");
            this.vvd(r.px, 0, 1.0E-13, "iauFk54z", "p1950");
            this.vvd(r.rv, 0, 1.0E-11, "iauFk54z", "v1950");
        }

        /**
         * - - - - - - - - -
         * t _ a t c c 1 3
         * - - - - - - - - -
         * 
         * Test iauAtcc13 function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  iauAtcc13, vvd
         * 
         * This revision:  2021 April 18
         */
        public t_atcc13() {
            let rc: number;
            let dc: number;
            let pr: number;
            let pd: number;
            let px: number;
            let rv: number;
            let date1: number;
            let date2: number;
            rc = 2.71;
            dc = 0.174;
            pr = 1.0E-5;
            pd = 5.0E-6;
            px = 0.1;
            rv = 55.0;
            date1 = 2456165.5;
            date2 = 0.401182685;
            const co: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauAtcc13(rc, dc, pr, pd, px, rv, date1, date2);
            this.vvd(co.alpha, 2.7101265045313725, 1.0E-12, "iauAtcc13", "ra");
            this.vvd(co.delta, 0.17406325376283502, 1.0E-12, "iauAtcc13", "da");
        }

        /**
         * - - - - - - - -
         * t _ a t c c q
         * - - - - - - - -
         * 
         * Test iauAtccq function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  iauApcc13, iauAtccq, vvd
         * 
         * This revision:  2021 April 18
         */
        public t_atccq() {
            let date1: number;
            let date2: number;
            let eo: number;
            let rc: number;
            let dc: number;
            let pr: number;
            let pd: number;
            let px: number;
            let rv: number;
            const astrom: JSOFA.Astrom = new JSOFA.Astrom();
            date1 = 2456165.5;
            date2 = 0.401182685;
            eo = jauApci13(date1, date2, astrom);
            rc = 2.71;
            dc = 0.174;
            pr = 1.0E-5;
            pd = 5.0E-6;
            px = 0.1;
            rv = 55.0;
            const co: org.jastronomy.jsofa.JSOFA.SphericalCoordinate = jauAtccq(rc, dc, pr, pd, px, rv, astrom);
            this.vvd(co.alpha, 2.7101265045313725, 1.0E-12, "iauAtccq", "ra");
            this.vvd(co.delta, 0.17406325376283502, 1.0E-12, "iauAtccq", "da");
        }

        /**
         * - - - - - - - - -
         * t _ m o o n 9 8
         * - - - - - - - - -
         * 
         * Test iauMoon98 function.
         * 
         * Returned:
         * status    int         FALSE = success, TRUE = fail
         * 
         * Called:  iauMoon98, vvd, viv
         * 
         * This revision:  2021 April 12
         */
        public t_moon98() {
            const pv: number[][] = jauMoon98(2400000.5, 43999.9);
            this.vvd(pv[0][0], -0.002601295959971044, 1.0E-11, "iauMoon98", "x 4");
            this.vvd(pv[0][1], 6.139750944302743E-4, 1.0E-11, "iauMoon98", "y 4");
            this.vvd(pv[0][2], 2.640794528229829E-4, 1.0E-11, "iauMoon98", "z 4");
            this.vvd(pv[1][0], -1.244321506649895E-4, 1.0E-11, "iauMoon98", "xd 4");
            this.vvd(pv[1][1], -5.219076942678119E-4, 1.0E-11, "iauMoon98", "yd 4");
            this.vvd(pv[1][2], -1.716132214378462E-4, 1.0E-11, "iauMoon98", "zd 4");
        }
    }
    JSOFATest["__class"] = "org.jastronomy.jsofa.JSOFATest";

}

