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

public class ElfDynamicTableEntryStruct {
    //--------------------------------------------------------------------------
    // Fields 
    //--------------------------------------------------------------------------
    public long      d_tag;        /* Type of entry */
    public long      d_val;        /* Entry Value */
    public long      d_ptr;        /* Address of entry type */
            
    //These are the sizes, in bytes, of each field (in order)
    public static final int[] ELF32_FIELD_SIZES = {4,4};
    public static final int[] ELF64_FIELD_SIZES = {8,8};
    public static final short ELF32_ENTRY_SIZE = 8;
    public static final short ELF64_ENTRY_SIZE = 16;
    
    //--------------------------------------------------------------------------
    // Dynamic Type (d_tag) Enumurations
    //--------------------------------------------------------------------------
    // gnore
    public final static long DT_NULL       = 0;

    // The string table offset of the name of a needed library.
    public final static long DT_NEEDED     = 1; //d_val 
    
    // Total size, in bytes, of the relocation entries associated with
    // the procedure linkage table.
    public final static long DT_PLTRELSZ   = 2; //d_ptr  

    // Contains an address associated with the linkage table. The
    // specific meaning of this field is processor-dependent.
    public final static long DT_PLTGOT     = 3; // d_ptr
    
    // Address of the symbol hash table, described below.
    public final static long DT_HASH       = 4; //d_ptr 

    // Address of the dynamic string table.
    public final static long DT_STRTAB     = 5; //d_ptr 
    
    // Address of the dynamic symbol table.
    public final static long DT_SYMTAB     = 6; //d_ptr  

     // Address of a relocation table with Elf64_Rela entries.
    public final static long DT_RELA       = 7; //d_ptr

    // Total size, in bytes, of the DT_RELA relocation table.
    public final static long DT_RELASZ     = 8; //d_val  
    
    // Size, in bytes, of each DT_RELA relocation entry.
    public final static long DT_RELAENT    = 9; //d_val 

    // Total size, in bytes, of the string table.
    public final static long DT_STRSZ      = 10; 
    
    // Size, in bytes, of each symbol table entry.
    public final static long DT_SYMENT     = 11; //d_val 
    
    // Address of the initialization function.
    public final static long DT_INIT       = 12; //d_ptr   
    
    // Address of the termination function.
    public final static long DT_FINI        = 13; //d_ptr  
    
    // The string table offset of the name of this shared object.
    public final static long DT_SONAME      = 14; // d_val   
    
    // The string table offset of a shared library search path string.
    public final static long DT_RPATH       = 15; // d_val   
    
    // The presence of this dynamic table entry modifies the ignored
    // symbol resolution algorithm for references within the
    // library. Symbols defined within the library are used to
    // resolve references before the dynamic linker searches the
    // usual search path.
    public final static long DT_SYMBOLIC    = 16; // ignore  
    
    // Address of a relocation table with Elf64_Rel entries.
    public final static long DT_REL         = 17; // d_ptr   
    
    // Total size, in bytes, of the DT_REL relocation table.
    public final static long DT_RELSZ       = 18; // d_val
    
    // Size, in bytes, of each DT_REL relocation entry.
    public final static long DT_RELENT      = 19; // d_val   
    
    // Type of relocation entry used for the procedure linkage table. 
    // The d_val member contains either DT_REL or DT_RELA.
    public final static long DT_PLTREL      = 20; // d_val  
    
    // Reserved for debugger use.
    public final static long DT_DEBUG       = 21; // d_ptr 
    
    // The presence of this dynamic table entry signals that the relocation 
    // table contains relocations for a non-writable segment.
    public final static long DT_TEXTREL     = 22; // ignored       
    
    //Address of the relocations associated with the procedure linkage table.
    public final static long DT_JMPREL      = 23; // d_ptr   
    
    // The presence of this dynamic table entry signals that the
    // dynamic loader should process all relocations for this object
    // before transferring control to the program.
    public final static long DT_BIND_NOW    = 24; // ignored 
    
    //Pointer to an array of pointers to initialization functions.
    public final static long DT_INIT_ARRAY  = 25; // d_ptr   
    
    // Pointer to an array of pointers to termination functions.
    public final static long DT_FINI_ARRAY  = 26; // d_ptr  
    
    //Size, in bytes, of the array of initialization functions.
    public final static long DT_INIT_ARRAYSZ= 27; // d_val   
    
    // Size, in bytes, of the array of termination functions.
    public final static long DT_FINI_ARRAYSZ= 28; // d_val
    
    //  Defines a range of dynamic table tags that are reserved for environment-specific use.
    public final static long DT_LOOS       = 0x60000000;      
    public final static long DT_HIOS       = 0x6FFFFFFF;
    
    //Defines a range of dynamic table tags that are reserved for processor-specific use.
    public final static long DT_LOPROC     = 0x70000000;  
    public final static long DT_HIPROC     = 0x7FFFFFFF;

}
