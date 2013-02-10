package edu.syr.bytecast.fsys.elf;

public class ElfElfHeaderStruct {
    
    //--------------------------------------------------------------------------
    // Fields
    //--------------------------------------------------------------------------
          
    public byte        e_ident[] = new byte[16];     /* ELF identification */
    public short       e_type;      /* Object file type */
    public short       e_machine;   /* Machine type */
    public int         e_version;   /* Object file version */
    public long        e_entry;     /* Entry point address */
    public long        e_phoff;     /* Program header offset */
    public long        e_shoff;     /* Section he ader offset */
    public int         e_flags;     /* Processor-specific flags */
    public int         e_ehsize;    /* ELF header size */
    public int         e_phentsize; /* Size of program header entry */
    public int         e_phnum;     /* Number of program header entries */
    public int         e_shentsize; /* Size of section header entry */
    public int         e_shnum;     /* Number of section header entries */
    public int         e_shstrndx;  /* Section name string table index */
    
    
    public static final int[] ELF32_FIELD_SIZES = {2,2,4,4,4,4,4,2,2,2,2,2,2};
    public static final int[] ELF64_FIELD_SIZES = {2,2,4,8,8,8,4,2,2,2,2,2,2};
    public static final int   ELF32_FILE_HEADER_SIZE = 52;
    public static final int   ELF64_FILE_HEADER_SIZE = 64;
    
    //--------------------------------------------------------------------------
    // Object File Classes Enumrations
    //--------------------------------------------------------------------------
    
    //32-bit objects
    public static final byte ELFCLASS32             = 0x1;
    
    //64-bit objects
    public static final byte ELFCLASS64             = 0x2;
  
    //--------------------------------------------------------------------------  
    // Data Encodings
    //--------------------------------------------------------------------------
    
    //Object file data structures are little endian
    public static final byte ELFDATA2LSB            = 0x1; 
    
    //Object file data structures are big endian
    public static final byte ELFDATA2MSB            = 0x2;
    
    //--------------------------------------------------------------------------
    // Operating Systems and ABI Identifiers
    //--------------------------------------------------------------------------
    
    //System V ABI
    public static final byte ELFOSABI_SYSV          = 0x0;
    
    //HP-UX Operating System
    public static final byte ELFOSABI_HPUX          = 0x1;
    
    //Standalone (embedded) application
    public static final byte ELFOSABI_STANDALONE    = -0x7F;
        
    //--------------------------------------------------------------------------
    // Object File Types
    //--------------------------------------------------------------------------
    
    //No file type
    public static final short ET_NONE               = 0x0;
    
    //Relocatable object file
    public static final short ET_REL                = 0x1;
    
    //Executable file
    public static final short ET_EXEC               = 0x2;
    
    //Shared Object file
    public static final short ET_DYN                = 0x3;
    
    //Core file
    public static final short ET_CORE               = 0x4;
    
    //Environment specific use
    public static final short ET_LOOS               = -0x7E00;
    
    //Environment specific use
    public static final short ET_HIOS               = -0x7EFF;
    
    //Processor specific use
    public static final short ET_LOPROC             = -0x7F00;
    
    //Processor Specific use
    public static final short ET_HIPROC             = -0x7FFF;
    
}
