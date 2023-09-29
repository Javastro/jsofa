/* Generated from Java with JSweet 3.1.0 - http://www.jsweet.org */
 import { JSOFAException } from "./JSOFAException";
    /**
     * Exception signalling a internal error.
     * .
     * @author Paul Harrison (paul.harrison@manchester.ac.uk) 21 Nov 2011
     * @version $Revision$ $date$
     * @param {string} message
     * @param {number} status
     * @class
     * @extends org.jastronomy.jsofa.JSOFAException
     */
    export class JSOFAInternalError extends JSOFAException {
        /**
         * Comment for <code>serialVersionUID</code>
         */
        static __org_jastronomy_jsofa_JSOFAInternalError_serialVersionUID: number = 6067803703985219165;

        public constructor(message: string, status: number) {
            super(message, status);
            (<any>Object).setPrototypeOf(this, JSOFAInternalError.prototype);
        }
    }
    JSOFAInternalError["__class"] = "org.jastronomy.jsofa.JSOFAInternalError";


