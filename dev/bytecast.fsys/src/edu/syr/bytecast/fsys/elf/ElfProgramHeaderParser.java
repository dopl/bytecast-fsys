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

public class ElfProgramHeaderParser {
    
    private short m_entrySize; 
    private int[] m_fieldLengths;
    
    public ElfProgramHeaderParser(int arch) throws IOException
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
    
    public ElfProgramHeaderStruct parse(List<Byte> data){
        ElfProgramHeaderStruct ret = new ElfProgramHeaderStruct();
 
        for (int i = 0; i < data.size() / m_entrySize; i++) {

            ret.m_headerEntries.add(parseEntry(data, i));
        }

        return ret;
        
    }
     
    private ElfProgramHeaderEntryStruct parseEntry(List<Byte> data, int index) {
  
        ElfProgramHeaderEntryStruct ret = new ElfProgramHeaderEntryStruct();
       
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
            //32 bit elf format has flags second to last. 64 bit elf has it second
            if( m_entrySize == ElfProgramHeaderEntryStruct.ELF64_ENTRY_SIZE)
            {
                switch (current_field) {
                case 0:
                    ret.p_type   = (int) parseResult;
                    break;
                case 1:                 
                    ret.p_flags  = (int) parseResult;
                    break;
                case 2:
                    ret.p_offset = (long) parseResult;
                    break;
                case 3:
                    ret.p_vaddr  = (long) parseResult;
                    break;
                case 4:
                    ret.p_paddr  = (long) parseResult;
                    break;
                case 5:
                    ret.p_filesz = (long) parseResult;
                    break;
                case 6:
                    ret.p_memsz  = (long) parseResult;
                    break;
                case 7:
                    ret.p_align  = (long) parseResult;
                    break;
                default:
                    break;
                }
            }
            else // 32 bit elf
            {
                switch (current_field) {
                case 0:
                    ret.p_type   = (int) parseResult;
                    break;
                case 1:
                    ret.p_offset = (long) parseResult;
                    break;
                case 2:
                    ret.p_vaddr  = (long) parseResult;
                    break;
                case 3:
                    ret.p_paddr  = (long) parseResult;
                    break;
                case 4:
                    ret.p_filesz = (long) parseResult;
                    break;
                case 5:
                    ret.p_memsz  = (long) parseResult;
                    break;
                case 6:                 
                    ret.p_flags  = (int) parseResult;
                    break;    
                case 7:
                    ret.p_align  = (long) parseResult;
                    break;
                default:
                    break;
                }
            }
            
            //go to the next field.
            current_field++;
        }
        
        return ret;
    }
    
    // Search for the dynamic program header
    public ElfProgramHeaderEntryStruct getDynamicTable(ElfProgramHeaderStruct input) 
    {
        ElfProgramHeaderEntryStruct ret;
        for(int i = 0; i < input.m_headerEntries.size(); i++)
        {
            //Program header with type 2 or PT_DYNAMIC 
            if(input.m_headerEntries.get(i).p_type == ElfProgramHeaderEntryStruct.PT_DYNAMIC)
            {
                ret = input.m_headerEntries.get(i);
                return ret;  
            }
        }
        // If no dynamic program header exists return null.
        return null;   
    }
    
    public void printHeader(ElfProgramHeaderStruct input)
    {
        for(int i = 0; i < input.m_headerEntries.size(); i++)
        {            
            System.out.println("------------------------------------------");
            System.out.println("Printing Program Header Entry " + i + ":");         
            System.out.println("------------------------------------------");
            printHeaderEntry(input.m_headerEntries.get(i));
        }
    }

    public void printHeaderEntry(ElfProgramHeaderEntryStruct input)
    {
        System.out.printf("p_type: %016x\n", input.p_type);
        System.out.printf("p_flags: %016x\n", input.p_flags);
        System.out.printf("p_offset: %016x\n", input.p_offset);
        System.out.printf("p_vaddr: %016x\n", input.p_vaddr);
        System.out.printf("p_paddr: %016x\n", input.p_paddr);
        System.out.printf("p_filesz: %016x\n", input.p_filesz);
        System.out.printf("p_memsz: %016x\n", input.p_memsz);
        System.out.printf("p_align: %016x\n", input.p_align);       
    }

}
