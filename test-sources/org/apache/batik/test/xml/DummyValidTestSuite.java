/*

   Copyright 2001  The Apache Software Foundation 

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
package org.apache.batik.test.xml;

import org.apache.batik.test.DefaultTestSuite;
 
/**
 * Dummy TestSuite which always passes. Needed for the test infrastructure
 * validation.
 *
 * @author <a href="mailto:vhardy@apache.lorg">Vincent Hardy</a>
 * @version $Id$
 */
public class DummyValidTestSuite extends DefaultTestSuite{
    public DummyValidTestSuite(){
        addTest(new DummyValidTest() {{setId("1");}});
        addTest(new DummyValidTest() {{setId("2");}});
    }
}

