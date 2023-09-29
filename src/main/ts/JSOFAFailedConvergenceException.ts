/* Generated from Java with JSweet 3.1.0 - http://www.jsweet.org */
namespace org.jastronomy.jsofa {
    /**
     * Exception reporting some form of convergence failure in an algorithm.
     * @param {string} message
     * @param {number} status
     * @class
     * @extends org.jastronomy.jsofa.JSOFAException
     */
    export class JSOFAFailedConvergenceException extends org.jastronomy.jsofa.JSOFAException {
        /**
         * Comment for <code>serialVersionUID</code>
         */
        static __org_jastronomy_jsofa_JSOFAFailedConvergenceException_serialVersionUID: number = 4417087001300889769;

        public constructor(message: string, status: number) {
            super(message, status);
            (<any>Object).setPrototypeOf(this, JSOFAFailedConvergenceException.prototype);
        }
    }
    JSOFAFailedConvergenceException["__class"] = "org.jastronomy.jsofa.JSOFAFailedConvergenceException";

}

