package edu.syr.bytecast.fsys;

import java.util.*;

public class ElfSectionHeaderStruct {
    public List<ElfSectionHeaderEntryStruct> m_headerEntries;
    
    ElfSectionHeaderStruct(){
        m_headerEntries = new ArrayList();
    }
}
