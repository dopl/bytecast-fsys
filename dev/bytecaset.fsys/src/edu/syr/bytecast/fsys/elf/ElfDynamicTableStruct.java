/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.fsys.elf;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adodds
 */
public class ElfDynamicTableStruct {
    public List<ElfDynamicTableEntryStruct> m_headerEntries; 
    
    ElfDynamicTableStruct(){
        m_headerEntries = new ArrayList();
    }
    
}
