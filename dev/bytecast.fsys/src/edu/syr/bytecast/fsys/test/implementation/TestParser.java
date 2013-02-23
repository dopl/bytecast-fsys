/*
 * This file is part of Bytecast.
 *
 * Bytecast is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Bytecast is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Bytecast.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package edu.syr.bytecast.fsys.test.implementation;

import edu.syr.bytecast.fsys.test.interfaces.ITestCase;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestParser {
   
   public ITestCase getTestCase()
   {
       Set<String> dependencies = new HashSet<String>(Arrays.asList(new String[]{"linux-vdso.so.1", "/lib/libc.so.6", "/lib64/ld-linux-x86-64.so.2"}));
       ITestCase ret = new FSysTestCase("test_input/a.out", dependencies);
       ret.setTestName("First Test Case");
       return ret;
   }
   
   public static void main(String[] args)
   {
        try {
            TestParser testParser = new TestParser();
            ITestCase testCase = testParser.getTestCase();
            TestGenerator testGenerator = new TestGenerator();
            testGenerator.addTest(testCase);            
            testGenerator.start();
            testGenerator.printResults();
            
        } catch (Exception ex) {
            Logger.getLogger(TestParser.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}