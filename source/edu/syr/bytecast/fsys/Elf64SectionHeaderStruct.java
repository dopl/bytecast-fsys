package edu.syr.bytecast.fsys;

import java.util.*;

public class Elf64SectionHeaderStruct {
    public List<Elf64SectionHeaderEntryStruct> m_headerEntries;
    
    Elf64SectionHeaderStruct(){
        m_headerEntries = new ArrayList();
    }
}
