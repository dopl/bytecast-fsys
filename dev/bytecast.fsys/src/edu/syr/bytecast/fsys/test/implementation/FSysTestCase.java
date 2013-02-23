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

import edu.syr.bytecast.interfaces.fsys.ExeObjDependency;
import edu.syr.bytecast.interfaces.fsys.IBytecastFsys;
import edu.syr.bytecast.fsys.elf.ElfExeObjParser;
import edu.syr.bytecast.fsys.test.interfaces.ITestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FSysTestCase implements ITestCase {

    private TestResult m_result;
    private String m_inputFilePath;
    private Set <String> m_inputDependencies;
    private String m_testName;
    
    public FSysTestCase(String inputFilePath)
    {
        m_testName = "Default Test Name";
        m_inputFilePath = inputFilePath;
        m_inputDependencies = new HashSet<String>();        
        m_result = new TestResult();
    }
    
    public FSysTestCase(String inputFilePath, String testName)
    {
        m_testName = testName;
        m_inputFilePath = inputFilePath;
        m_inputDependencies = new HashSet<String>();        
        m_result = new TestResult();
    }
    
    public FSysTestCase(String inputFilePath, Set<String> dependencies)
    {
        m_testName = "Default Name";
        m_inputFilePath = inputFilePath;
        this.m_inputDependencies = dependencies;        
        m_result = new TestResult();
    }
    
    public void addDependencyInput(String dependency)
    {
       m_inputDependencies.add(dependency);
    }
    
    @Override
    public TestResult getResult() {
        try {
            
            //Capturing Console Output
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream old_ps = System.out;
            System.setOut(ps);
            
            IBytecastFsys testObject = new ElfExeObjParser();
            testObject.setFilepath(m_inputFilePath);

            List<ExeObjDependency> lists =   testObject.parse().getDependencies();
            if(lists != null && lists.isEmpty() && m_inputDependencies.size() > 0)
            {
                System.out.println("No Input Dependency Returned");
                m_result.setPassed(false);
            }
            
            for(ExeObjDependency dep : lists)
            {
                if(!m_inputDependencies.contains(dep.getDependencyName()))
                {
                    System.out.println("Dependency " + dep + " Not Found");
                    m_result.setPassed(false);
                } else
                    m_inputDependencies.remove(dep.getDependencyName());
            }
            
            for(String notCheckedDependencies : m_inputDependencies)
            {
                System.out.println("Dependency " + notCheckedDependencies + " not returned ");
                m_result.setPassed(false);
            }
          m_result.appendMessage(baos.toString());
          System.setOut(old_ps);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
        
        return m_result;
    }

    @Override
    public String getTestName() {
        return m_testName;
    }
    
    @Override
    public void setTestName(String name)
    {
        this.m_testName = name;
    }
    
}
