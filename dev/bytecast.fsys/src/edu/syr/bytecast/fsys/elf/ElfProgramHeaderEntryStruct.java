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

public class ElfProgramHeaderEntryStruct {
    //--------------------------------------------------------------------------
    // Fields 
    //--------------------------------------------------------------------------
    public int       p_type;          /* Type of segment */
    public long      p_offset;        /* Offset in file */
    public long      p_vaddr;         /* Virtual address in memory */
    public long      p_paddr;         /* Reserved */
    public long      p_filesz;        /* Size of segment in file */
    public long      p_memsz;         /* Size of segment in memory */
    public int       p_flags;         /* Segment attributes */
    public long      p_align;         /* Alignment of segment */
 
            
    //These are the sizes, in bytes, of each field (in order)
    public static final int[] ELF32_FIELD_SIZES = {4,4,4,4,4,4,4,4 };
    public static final int[] ELF64_FIELD_SIZES = {4,4,8,8,8,8,8,8 };
    public static final short ELF32_ENTRY_SIZE = 32;
    public static final short ELF64_ENTRY_SIZE = 56;
    
    //--------------------------------------------------------------------------
    // Segment Type (p_type) Enumurations
    //--------------------------------------------------------------------------
    public final static int PT_NULL       = 0;

    // Contains a loadable segment
    public final static int PT_LOAD       = 1;  
    
    // Contains dynamic linking table
    public final static int PT_DYNAMIC    = 2;  

    // Contains a program interpreter path name
    public final static int PT_INTERP     = 3;  

    // Contains notes sections
    public final static int PT_NOTE       = 4;  

    // Reserved
    public final static int PT_SHLIP      = 5;  
    
    // Contains a program header table
    public final static int PT_PHDR       = 6;  

     // Enviroment specific use, Low
    public final static int PT_LOOS       = 0x60000000; 

    // Enviroment specific use, High
    public final static int PT_HIOS       = 0x6fffffff;  
    
    // Processor specific use, Low
    public final static int PT_LOPROC     = 0x70000000;  

    // Processor specific use, High
    public final static int PT_HIPROC     = 0x7fffffff; 
    
    //--------------------------------------------------------------------------
    // Program Header Flag (p_flags) Enumerations
    //--------------------------------------------------------------------------
    // Program header has execute permissions
    public final static long PF_X          = 0x1;       
    
    // Program header has write permissions
    public final static long PF_W          = 0x2;
    
    // Program header has read permissions
    public final static long PF_R          = 0x4;       
    
    // Environment-specific use
    public final static long PF_MASKOS     = 0x00FF0000;
    
    // Processor-specific use
    public final static long PF_MASKPROC   = 0xFF000000;
}
