package edu.syr.bytecast.fsys;

import java.util.*;

public class Elf64SectionHeaderParser {

    //Size of the section header table.
    private final int ENTRY_SIZE = 512;
    
    public Elf64SectionHeaderStruct parseHeader(List<Byte> data) {
        Elf64SectionHeaderStruct ret = new Elf64SectionHeaderStruct();

        for (int i = 0; i < data.size() / ENTRY_SIZE; i++) {
            ret.m_headerEntries.add(parseEntry(data, i));
        }

        return ret;
    }

    private Elf64SectionHeaderEntryStruct parseEntry(List<Byte> data, int index) {
        
        Elf64SectionHeaderEntryStruct ret = new Elf64SectionHeaderEntryStruct();
       
        int bytes_consumed  = 0;
        int current_field   = 0;
        
        //These are the sizes, in bytes, of each field (in order)
        int[] sizes = {4, 4, 8, 8, 8, 8, 4, 4, 8, 8};
        
        //Parse all fields until the entry had been consumed.
        while(bytes_consumed < ENTRY_SIZE)
        {
            //Determine where in "data" the next field starts
            int byte_index = index*ENTRY_SIZE+bytes_consumed;
            
            //Parse that position by the size of the field.
            long parseResult = BytecastFsysUtil.bytesToLong(byte_index, 
                                                        sizes[current_field], 
                                                        data);
            //Increment the number of bytes consumed by the field size
            bytes_consumed += sizes[current_field];
            
            //Determine which variable gets the parsed result
            switch(current_field)
            {
                case 0: ret.sh_name         = (int) parseResult; break;
                case 1: ret.sh_type         = (int) parseResult; break;        
                case 2: ret.sh_flags        = parseResult;       break;
                case 3: ret.sh_addr         = parseResult;       break;
                case 4: ret.sh_offset       = parseResult;       break;
                case 5: ret.sh_size         = parseResult;       break;
                case 6: ret.sh_link         = (int) parseResult; break;
                case 7: ret.sh_info         = (int) parseResult; break;
                case 8: ret.sh_addralign    = parseResult;       break;
                case 9: ret.sh_entsize      = parseResult;       break;
                default: break;
            }
            
            //go to the next field.
            current_field++;
        }
        
        return ret;
    }
}
