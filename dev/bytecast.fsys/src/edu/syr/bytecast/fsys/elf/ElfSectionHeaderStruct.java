package edu.syr.bytecast.fsys.elf;

import java.util.*;

public class ElfSectionHeaderStruct {
    public List<ElfSectionHeaderEntryStruct> m_headerEntries;   
    ElfSectionHeaderStruct(){
        m_headerEntries = new ArrayList();
    }
}
