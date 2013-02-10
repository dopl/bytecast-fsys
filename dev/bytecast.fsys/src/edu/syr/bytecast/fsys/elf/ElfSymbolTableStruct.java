package edu.syr.bytecast.fsys.elf;

public class ElfSymbolTableStruct {

    //--------------------------------------------------------------------------
    // Fields 
    //--------------------------------------------------------------------------    
    int     st_name;    /* Symbol Name */
    char    st_info;    /* Type and Binding Attributes */
    char    st_other;   /* Reserved */
    short   st_shndx;   /* Section Table Index */
    long    st_value;   /* Symbol Value */
    long    st_size;    /* Size of object (e.g., common) */
    
    //These are the sizes, in bytes, of each field (in order)
    public static final int[] ELF32_FIELD_SIZES = {4, 1, 1, 2, 8, 8};
    public static final int[] ELF64_FIELD_SIZES = {4, 4, 8, 8, 8, 8, 4, 4, 8, 8};
    public static final short ELF32_ENTRY_SIZE = 40;
    public static final short ELF64_ENTRY_SIZE = 64;   
}
