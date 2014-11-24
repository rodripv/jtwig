/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lyncode.jtwig.acceptance;

import com.lyncode.jtwig.exception.CompileException;
import com.lyncode.jtwig.exception.ParseException;
import org.junit.Test;

import static com.lyncode.jtwig.util.SyntacticSugar.then;
import static com.lyncode.jtwig.util.SyntacticSugar.when;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ImportTest extends AbstractJtwigTest {
    @Test
    public void basicExample() throws Exception {
        when(jtwigRenders(templateResource("templates/acceptance/import/import.twig")));
        then(theRenderedTemplate().trim(), is(equalTo("text (test)\n\n\npassword (pass)\n\n\npassword (password)")));
    }
    
    @Test(expected = ParseException.class)
    public void ensureInvalidImportStatementThrowsException() throws Exception {
        when(jtwigRenders(templateResource("templates/acceptance/import/multiple-import-as.twig")));
    }
    
    @Test
    public void ensureImportSelfWorks() throws Exception {
        when(jtwigRenders(templateResource("templates/acceptance/import/import-self.twig")));
        then(theRenderedTemplate().trim(), is(equalTo("jtwig")));
    }
    
    @Test
    public void ensureNestedSelfImportWorks() throws Exception {
        when(jtwigRenders(templateResource("templates/acceptance/import/nested-import-self.twig")));
        then(theRenderedTemplate().trim(), is(equalTo("<input type=\"password\" name=\"jtwig\">")));
    }
    
//    @Test(expected = CompileException.class)
//    public void ensureInvalidFromStatementThrowsException() throws Exception {
//        when(jtwigRenders(templateResource("templates/acceptance/import/import-string-name.twig")));
//    }
}