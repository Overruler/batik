/*

   Copyright 2000-2001  The Apache Software Foundation 

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package org.apache.batik.script;

/**
 * An exception that will be thrown when a problem is encountered in the
 * script by an <code>Interpreter</code> interface implementation.
 * @author <a href="mailto:cjolif@ilog.fr">Christophe Jolif</a>
 * @version $Id$
 */
public class InterpreterException extends Exception {
    private int line = -1; // -1 when unknow
    private int column = -1; // -1 when unknow
    private Exception embedded = null; // null when unknown

    /**
     * Builds an instance of <code>InterpreterException</code>.
     * @param message the <code>Exception</code> message.
     * @param lineno the number of the line the error occurs.
     * @param columno the number of the column the error occurs.
     */
    public InterpreterException(String message, int lineno, int columnno) {
        super(message);
        line = lineno;
        column = columnno;
    }

    /**
     * Builds an instance of <code>InterpreterException</code>.
     * @param exception the embedded exception.
     * @param message the <code>Exception</code> message.
     * @param lineno the number of the line the error occurs.
     * @param columno the number of the column the error occurs.
     */
    public InterpreterException(Exception exception,
                                String message, int lineno, int columnno) {
        this(message, lineno, columnno);
        embedded = exception;
    }

    /**
     * Returns the line number where the error occurs. If this value is not
     * known, returns -1.
     */
    public int getLineNumber() {
        return line;
    }

    /**
     * Returns the column number where the error occurs. If this value is not
     * known, returns -1.
     */
    public int getColumnNumber() {
        return column;
    }

    /**
     * Returns the embedded exception. If no embedded exception is set,
     * returns null.
     */
    public Exception getException() {
        return embedded;
    }

    /**
     * Returns the message of this exception. If an error message has
     * been specified, returns that one. Otherwise, return the error message
     * of enclosed exception or null if any.
     */
    public String getMessage() {
        String msg = super.getMessage();
        if (msg != null) {
            return msg;
        } else if (embedded != null) {
            return embedded.getMessage();
        } else {
            return null;
        }
    }
}
