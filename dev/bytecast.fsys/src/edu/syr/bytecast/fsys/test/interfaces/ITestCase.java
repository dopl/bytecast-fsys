/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.fsys.test.interfaces;

import edu.syr.bytecast.fsys.test.implementation.TestResult;

/**
 *
 * @author dhrumin
 */
public interface ITestCase {
    TestResult getResult();
    String getTestName();
}
