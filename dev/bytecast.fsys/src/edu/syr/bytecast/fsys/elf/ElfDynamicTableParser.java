/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.fsys.elf;

import edu.syr.bytecast.fsys.BytecastFsysUtil;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author adodds
 */
public class ElfDynamicTableParser {
    
    private short m_entrySize; 
    private int[] m_fieldLengths;
    private long  m_stringTableAddr;
    private long  m_stringTableLen;
    
    public ElfDynamicTableParser(int arch) throws IOException
    {
         
        if(arch == ElfElfHeaderStruct.ELFCLASS32)
        {
            m_entrySize     = ElfDynamicTableEntryStruct.ELF32_ENTRY_SIZE;
            m_fieldLengths  = ElfDynamicTableEntryStruct.ELF32_FIELD_SIZES;
        }
        else if(arch == ElfElfHeaderStruct.ELFCLASS64)
        {
            m_entrySize     = ElfDynamicTableEntryStruct.ELF64_ENTRY_SIZE;
            m_fieldLengths  = ElfDynamicTableEntryStruct.ELF64_FIELD_SIZES;
        }
        else
        {
            throw new IOException("Unsupported ELF architecture. ELF32 or "
                                    + "ELF64 supported only");
        }       
    }
    
    public ElfDynamicTableStruct parse(List<Byte> data){
        ElfDynamicTableStruct ret = new ElfDynamicTableStruct();
        
        for (int i = 0; i < data.size() / m_entrySize; i++) {

            ret.m_headerEntries.add(parseEntry(data, i));
        }
        return ret;      
    }
     
    private ElfDynamicTableEntryStruct parseEntry(List<Byte> data, int index) {
  
        ElfDynamicTableEntryStruct ret = new ElfDynamicTableEntryStruct();
        
        int bytes_consumed  = 0;
        int current_field   = 0;

        //Parse all fields until the entry had been consumed.
        while(bytes_consumed < m_entrySize)
        {
            //Determine where in "data" the next field starts
            int byte_index = index*m_entrySize+bytes_consumed;
            
            //Parse that position by the size of the field.
            long parseResult = BytecastFsysUtil.bytesToLong(byte_index, 
                                                 m_fieldLengths[current_field], 
                                                        data);
            //System.out.printf("ParseResult: %016x\n", parseResult);
            //Increment the number of bytes consumed by the field size
            bytes_consumed += m_fieldLengths[current_field];
            
            switch (current_field) {
                case 0:
                    ret.d_tag   = (int) parseResult;
                    break;
                case 1:                 
                    ret.d_val  = (int) parseResult;
                    ret.d_ptr = (long) parseResult;
                    //set up variables for where the symbol table is in memory.
                    //these values come from two entries in the dynamic table.
                    if(ret.d_tag == ElfDynamicTableEntryStruct.DT_STRTAB){
                        m_stringTableAddr = ret.d_ptr;
                    }
                    if(ret.d_tag == ElfDynamicTableEntryStruct.DT_STRSZ){
                        m_stringTableLen = ret.d_val;
                    }
                    break;
            }
            
            //go to the next field.
            current_field++;
        }
        return ret;
        
    }
    
    public long getStringTableAddr(){
        return m_stringTableAddr;
    }
    
    public long getStringTableLen(){
        return m_stringTableLen;
    }
}
