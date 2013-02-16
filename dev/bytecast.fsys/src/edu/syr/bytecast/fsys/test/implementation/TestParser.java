/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.fsys.test.implementation;

import edu.syr.bytecast.fsys.test.interfaces.ITestCase;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dhrumin
 */
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