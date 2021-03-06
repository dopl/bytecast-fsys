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
import java.io.IOException;
import java.util.List;

public class ElfSymbolTableParser {
    
    public ElfSymbolTableParser(int arch) throws IOException {
        m_arch = arch;
        if(m_arch == ElfElfHeaderStruct.ELFCLASS32) {
            m_entrySize     = ElfSymbolTableEntryStruct.ELF32_ENTRY_SIZE;
            m_fieldLengths  = ElfSymbolTableEntryStruct.ELF32_FIELD_SIZES;
        } else if(m_arch == ElfElfHeaderStruct.ELFCLASS64) {
            m_entrySize     = ElfSymbolTableEntryStruct.ELF64_ENTRY_SIZE;
            m_fieldLengths  = ElfSymbolTableEntryStruct.ELF64_FIELD_SIZES;
        } else {
            throw new IOException("Unsupported ELF architecture. ELF32 or "
                                    + "ELF64 supported only"); 
        }       
    }

    public ElfSymbolTableStruct parse(List<Byte> data) {
        ElfSymbolTableStruct ret = new ElfSymbolTableStruct();
 
        for (int i = 0; i < data.size() / m_entrySize; i++) {

            ret.m_symbolEntries.add(parseEntry(data, i));
        }
        
        return ret;
    }
 
    public ElfSymbolTableEntryStruct parseEntry(List<Byte> data,int index) {
        ElfSymbolTableEntryStruct ret = new ElfSymbolTableEntryStruct();
    
        int bytes_consumed = 0;
        
        for (int i = 0; i < m_fieldLengths.length; i++) {
            
            //Determine where in "data" the next field starts
            int byte_index = index*m_entrySize+bytes_consumed;
            
            
            long parseResult = BytecastFsysUtil.bytesToLong(byte_index,
                                                            m_fieldLengths[i],
                                                            data);
            bytes_consumed += m_fieldLengths[i];
            
            if(m_arch == ElfElfHeaderStruct.ELFCLASS32)
            {
                switch (i) {
                    case 0: ret.st_name  = (int)   parseResult; break;
                    case 1: ret.st_value = (long)  parseResult; break;
                    case 2: ret.st_size  = (long)  parseResult; break;
                    case 3: ret.st_info  = (char)  parseResult; break;
                    case 4: ret.st_other = (char)  parseResult; break;
                    case 5: ret.st_shndx = (int)   parseResult; break;
                    default: break;
                }      
            }
            else
            {
                switch (i) {
                    case 0: ret.st_name  = (int)   parseResult; break;
                    case 1: ret.st_info  = (char)  parseResult; break;
                    case 2: ret.st_other = (char)  parseResult; break;
                    case 3: ret.st_shndx = (int)   parseResult; break;
                    case 4: ret.st_value = (long)  parseResult; break;
                    case 5: ret.st_size  = (long)  parseResult; break;
                    default: break;
                }                
            }
        }
        
        return ret;
    }
    
    public void printSymbolTable(ElfSymbolTableStruct input)
    {
        for(int i = 0; i < input.m_symbolEntries.size(); i++)
        {            
            System.out.println("------------------------------------------");
            System.out.println("Printing Symbol Table Entry " + i + ":");         
            System.out.println("------------------------------------------");
            printSymbolEntry(input.m_symbolEntries.get(i));
        }
    }

    public void printSymbolEntry(ElfSymbolTableEntryStruct input)
    {
        System.out.printf("st_name: %d\n", input.st_name);
        System.out.printf("st_info: %d\n", input.st_info);
        System.out.printf("st_other: %016x\n", input.st_other);
        System.out.printf("st_shndx: %016x\n", input.st_shndx);
        System.out.printf("st_value: %016x\n", input.st_value);
        System.out.printf("st_size: %016x\n", input.st_size);  
    }
    private int   m_arch;
    private short m_entrySize; 
    private int[] m_fieldLengths;   
}
