/* Generated from Java with JSweet 3.1.0 - http://www.jsweet.org */
namespace org.jastronomy.jsofa {
    /**
     * Base class for JSOFA exceptions.
     * .
     * @author Paul Harrison (paul.harrison@manchester.ac.uk) 21 Nov 2011
     * @version $Revision$ $date$
     * @param {string} message
     * @param {number} status
     * @class
     * @extends Error
     */
    export class JSOFAException extends Error {
        /**
         * Comment for <code>serialVersionUID</code>
         */
        static serialVersionUID: number = 4100437038026589596;

        /**
         * Original JSOFA status value;
         */
        /*private*/ status: number;

        public getStatus(): number {
            return this.status;
        }

        public constructor(message: string, status: number) {
            super(message); this.message=message;
            (<any>Object).setPrototypeOf(this, JSOFAException.prototype);
            if (this.status === undefined) { this.status = 0; }
            this.status = status;
        }
    }
    JSOFAException["__class"] = "org.jastronomy.jsofa.JSOFAException";

}

