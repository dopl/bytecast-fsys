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

public class ElfSymbolTableStruct {

    //--------------------------------------------------------------------------
    // Fields 
    //--------------------------------------------------------------------------    
    int     st_name;    /* Symbol Name */
    char    st_info;    /* Type and Binding Attributes */
    char    st_other;   /* Reserved */
    int     st_shndx;   /* Section Table Index */
    long    st_value;   /* Symbol Value */ 
    long    st_size;    /* Size of object (e.g., common) */
    
    //These are the sizes, in bytes, of each field (in order)
    public static final int[] ELF32_FIELD_SIZES = {4, 4, 4, 1, 1, 2};
    public static final int[] ELF64_FIELD_SIZES = {4, 1, 1, 2, 8, 8};
    public static final short ELF32_ENTRY_SIZE = 16;
    public static final short ELF64_ENTRY_SIZE = 24;   
    
    //--------------------------------------------------------------------------
    // Symbol Binding Enumerations 
    //-------------------------------------------------------------------------- 
    // Not visible outside the object file
    public static final char STB_LOCAL  = 0;
    
    // Global symbol, visible to all object files
    public static final char STB_GLOBAL = 1;
    
    //Global scope, but with lower precedence than global symbols
    public static final char STB_WEAK   = 2;
    
    //Environment-specific use
    public static final char STB_LOOS   = 10;
    
    //Environment-specific use
    public static final char STB_HIOS   = 12;
    
    //Processor-specific use
    public static final char STB_LOPROC = 13; 
    
    //Processor-specific use
    public static final char STB_HIPROC = 15;

    
    //--------------------------------------------------------------------------
    // Symbol Type Enumerations 
    //--------------------------------------------------------------------------
    //No type specified (e.g., an absolute symbol)
    public static final char STT_NOTYPE     = 0;
    
    //Data object
    public static final char STT_OBJECT     = 1;
    
    //Function entry point
    public static final char STT_FUNC       = 2;
    
    //Symbol is associated with a section
    public static final char STT_SECTION    = 3;
    
    //Source file associated with the object file
    public static final char STT_FILE       = 4;
    
    //Environment-specific use
    public static final char STT_LOOS       = 10;
            
    //Environment-specific use
    public static final char STT_HIOS       = 12;
    
    //Processor-specific use
    public static final char STT_LOPROC     = 13;
    
    //Processor-specific use
    public static final char STT_HIPROC     = 15;


}
