/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.fsys.test.implementation;

import edu.syr.bytecast.fsys.test.interfaces.ITestCase;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dhrumin
 */
public class TestGenerator {
    
    private List<ITestCase> m_testCases = new ArrayList<ITestCase>();
    
    public void addTest(ITestCase testCase)
    {
        m_testCases.add(testCase);
    }
    
    public void start()
    {
        for(ITestCase testCase : m_testCases)
        {
            testCase.getResult();
        }
    }
    
    public void printResults()
    {
        System.out.println("Printing Result");
        for(ITestCase testCase : m_testCases)
        {
            System.out.println("Test Name : " + testCase.getTestName());
            if(testCase.getResult().getPassed())
            {
                System.out.println("Test Passed!!");                
            }
            else
            {
                System.out.println("Test Failed!");
                System.out.println("Error Messsage : " + testCase.getResult().getMessage());
            }
        }
    }    
}
