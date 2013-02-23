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

package edu.syr.bytecast.fsys.elf;

import edu.syr.bytecast.fsys.BytecastFsysUtil;
import java.util.*;
import java.io.*;

public class ElfSectionHeaderParser {
    
    private short m_entrySize; 
    private int[] m_fieldLengths;
    
    public ElfSectionHeaderParser(int arch) throws IOException
    {
         
        if(arch == ElfElfHeaderStruct.ELFCLASS32)
        {
            m_entrySize     = ElfSectionHeaderEntryStruct.ELF32_ENTRY_SIZE;
            m_fieldLengths  = ElfSectionHeaderEntryStruct.ELF32_FIELD_SIZES;
        }
        else if(arch == ElfElfHeaderStruct.ELFCLASS64)
        {
            m_entrySize     = ElfSectionHeaderEntryStruct.ELF64_ENTRY_SIZE;
            m_fieldLengths  = ElfSectionHeaderEntryStruct.ELF64_FIELD_SIZES;
        }
        else
        {
            throw new IOException("Unsupported ELF architecture. ELF32 or "
                                    + "ELF64 supported only");
        }       
    }
    
    public ElfSectionHeaderStruct parseHeader(List<Byte> data){
        ElfSectionHeaderStruct ret = new ElfSectionHeaderStruct();
 
        for (int i = 0; i < data.size() / m_entrySize; i++) {

            ret.m_headerEntries.add(parseEntry(data, i));
        }

        return ret;
    }
    
    public void printHeader(ElfSectionHeaderStruct input)
    {
        for(int i = 0; i < input.m_headerEntries.size(); i++)
        {            
            System.out.println("------------------------------------------");
            System.out.println("Printing Section Header Entry " + i + ":");         
            System.out.println("------------------------------------------");
            printHeaderEntry(input.m_headerEntries.get(i));
        }
    }

    public void printHeaderEntry(ElfSectionHeaderEntryStruct input)
    {
        System.out.printf("sh_name: %d\n", input.sh_name);
        System.out.printf("sh_type: %d\n", input.sh_type);
        System.out.printf("sh_link: %08x\n", input.sh_link);
        System.out.printf("sh_info: %08x\n", input.sh_info);
        System.out.printf("sh_flags: %016x\n", input.sh_flags);
        System.out.printf("sh_addr: %016x\n", input.sh_addr);
        System.out.printf("sh_offset: %016x\n", input.sh_offset);
        System.out.printf("sh_size: %016x\n", input.sh_size);
        System.out.printf("sh_addralign: %016x\n", input.sh_addralign);
        System.out.printf("sh_entsize: %016x\n", input.sh_entsize);      
    }
    
    private ElfSectionHeaderEntryStruct parseEntry(List<Byte> data, int index) {
        
        ElfSectionHeaderEntryStruct ret = new ElfSectionHeaderEntryStruct();
       
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
            //Increment the number of bytes consumed by the field size
            bytes_consumed += m_fieldLengths[current_field];
            
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
