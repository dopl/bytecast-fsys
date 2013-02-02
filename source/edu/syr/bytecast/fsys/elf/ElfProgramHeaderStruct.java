/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.fsys.elf;
import java.util.*;

/**
 *
 * @author adodds
 */

public class ElfProgramHeaderStruct {
public List<ElfProgramHeaderEntryStruct> m_headerEntries;   
    ElfProgramHeaderStruct(){
        m_headerEntries = new ArrayList();
    }

}
