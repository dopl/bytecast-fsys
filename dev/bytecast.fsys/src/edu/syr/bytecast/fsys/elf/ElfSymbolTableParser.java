/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.fsys.elf;

import java.io.IOException;

/**
 *
 * @author shawn
 */
public class ElfSymbolTableParser {
    
    public ElfSymbolTableParser(int arch) throws IOException
    {
         
        if(arch == ElfElfHeaderStruct.ELFCLASS32)
        {
            m_entrySize     = ElfProgramHeaderEntryStruct.ELF32_ENTRY_SIZE;
            m_fieldLengths  = ElfProgramHeaderEntryStruct.ELF32_FIELD_SIZES;
        }
        else if(arch == ElfElfHeaderStruct.ELFCLASS64)
        {
            m_entrySize     = ElfProgramHeaderEntryStruct.ELF64_ENTRY_SIZE;
            m_fieldLengths  = ElfProgramHeaderEntryStruct.ELF64_FIELD_SIZES;
        }
        else
        {
            throw new IOException("Unsupported ELF architecture. ELF32 or "
                                    + "ELF64 supported only");
        }       
    }
    
    private short m_entrySize; 
    private int[] m_fieldLengths;   
}
