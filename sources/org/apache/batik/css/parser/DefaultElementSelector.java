/*

   Copyright 2002  The Apache Software Foundation 

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
package org.apache.batik.css.parser;

/**
 * This class implements the {@link org.w3c.css.sac.ElementSelector} interface.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id$
 */
public class DefaultElementSelector extends AbstractElementSelector {
    /**
     * Creates a new ElementSelector object.
     */
    public DefaultElementSelector(String uri, String name) {
	super(uri, name);
    }

    /**
     * <b>SAC</b>: Implements {@link
     * org.w3c.css.sac.Selector#getSelectorType()}.
     */
    public short getSelectorType() {
	return SAC_ELEMENT_NODE_SELECTOR;
    }

    /**
     * Returns a representation of the selector.
     */
    public String toString() {
	String name = getLocalName();
	if (name == null) {
	    return "*";
	}
	return name;
    }
}
