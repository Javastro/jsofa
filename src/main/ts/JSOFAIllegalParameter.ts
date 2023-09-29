/* Generated from Java with JSweet 3.1.0 - http://www.jsweet.org */
namespace org.jastronomy.jsofa {
    /**
     * Various forms of illegal parameters are reported with this execption.
     * .
     * @author Paul Harrison (paul.harrison@manchester.ac.uk) 21 Nov 2011
     * @version $Revision$ $date$
     * @param {string} message
     * @param {number} status
     * @class
     * @extends org.jastronomy.jsofa.JSOFAException
     */
    export class JSOFAIllegalParameter extends org.jastronomy.jsofa.JSOFAException {
        /**
         * Comment for <code>serialVersionUID</code>
         */
        static __org_jastronomy_jsofa_JSOFAIllegalParameter_serialVersionUID: number = 4164536110692125140;

        public constructor(message: string, status: number) {
            super(message, status);
            (<any>Object).setPrototypeOf(this, JSOFAIllegalParameter.prototype);
        }
    }
    JSOFAIllegalParameter["__class"] = "org.jastronomy.jsofa.JSOFAIllegalParameter";

}

