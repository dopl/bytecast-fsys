package edu.syr.bytecast.fsys;

public class Elf64SectionHeaderEntryStruct {

    //--------------------------------------------------------------------------
    // Fields 
    //--------------------------------------------------------------------------
    public int     sh_name;         /* Section name */
    public int     sh_type;         /* Section type */
    public int     sh_link;         /* Link to other section */
    public int     sh_info;         /* Miscellaneous information */
    public long    sh_flags;        /* Section attributes */
    public long    sh_addr;         /* Virtual address in memory */
    public long    sh_offset;       /* Offset in file */
    public long    sh_size;         /* Size of section */
    public long    sh_addralign;    /* Address alignment boundary */
    public long    sh_entsize;      /* Size of entries, if section has table */
    
    //--------------------------------------------------------------------------
    // Section Type (sh_type) Enumurations
    //--------------------------------------------------------------------------
    public final static int SHT_NULL       = 0;

    // Contains information defined by the program
    public final static int SHT_PROGBITS   = 1;  
    
    // Contains a linker symbol table
    public final static int SHT_SYMTAB     = 2;  

    // Contains a string table
    public final static int SHT_STRTAB     = 3;  

    // Contains “Rela” type relocation entries
    public final static int SHT_RELA       = 4;  

    // Contains a symbol hash table
    public final static int SHT_HASH       = 5;  
    
    // Contains dynamic linking tables
    public final static int SHT_DYNAMIC    = 6;  

     // Contains note information
    public final static int SHT_NOTE       = 7; 

    // Contains uninitialized space; does not occupy any space in the file.
    public final static int SHT_NOBITS     = 8;  
    
    // Contains “Rel” type relocation entries.
    public final static int SHT_REL        = 9;  

    // Reserved
    public final static int SHT_SHLIB      = 10; 
    
    // Contains a dynamic loader symbol table
    public final static int SHT_DYNSYM     = 11; 

    // Env-specific use
    public final static int SHT_LOOS       = 0x60000000;

    // Env-specific use
    public final static int SHT_HIOS       = 0x6FFFFFFF;

    // Proc-specific use
    public final static int SHT_LOPROC     = 0x70000000;
    
    // Processor-specific use
    public final static int SHT_HIPROC     = 0x7FFFFFFF;
    
    //--------------------------------------------------------------------------
    // Section Header Flag (sh_flags) Enumerations
    //--------------------------------------------------------------------------
    // Section contains writable data
    public final static long SHF_WRITE      = 0x1;       
    
    // Section is allocated in memory image of program
    public final static long SHF_ALLOC      = 0x2;
    
    // Section contains executable instructions
    public final static long SHF_EXECINSTR  = 0x4;       
    
    // Environment-specific use
    public final static long SHF_MASKOS     = 0x0F000000;
    
    // Processor-specific use
    public final static long SHF_MASKPROC   = 0xF0000000;


}
