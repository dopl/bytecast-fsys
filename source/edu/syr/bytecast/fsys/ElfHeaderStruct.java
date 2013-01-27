/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.fsys;
import java.util.*;

/* @author adodds
Elf64_Addr    8 8 Unsigned program address
Elf64_Off     8 8 Unsigned file offset
Elf64_Half    2 2 Unsigned medium integer
Elf64_Word    4 4 Unsigned integer
Elf64_Sword   4 4 Signed integer
Elf64_Xword   8 8 Unsigned long integer
Elf64_Sxword  8 8 Signed long integer

/* unsigned char e_ident[16];  ELF identification 
   Elf64_Half    e_type;      Object file type 
   Elf64_Half    e_machine;    Machine type 
   Elf64_Word    e_version;    Object file version 
   Elf64_Addr    e_entry;      Entry point address 
   Elf64_Off     e_phoff;      Program header offset 
   Elf64_Off     e_shoff;      Section header offset 
   Elf64_Word    e_flags;      Processor-specific flags 
   Elf64_Half    e_ehsize;     ELF header size 
   Elf64_Half    e_phentsize;  Size of program header entry 
   Elf64_Half    e_phnum;      Number of program header entries 
   Elf64_Half    e_shentsize;  Size of section header entry 
   Elf64_Half    e_shnum;      Number of section header entries 
   Elf64_Half    e_shstrndx;   Section name string table index */

public class ElfHeaderStruct {
          
    public List<Byte>  e_ident;     /* ELF identification */
    public short       e_type;      /* Object file type */
    public short       e_machine;   /* Machine type */
    public int         e_version;   /* Object file version */
    public long        e_entry;     /* Entry point address */
    public long        e_phoff;     /* Program header offset */
    public long        e_shoff;     /* Section header offset */
    public int         e_flags;     /* Processor-specific flags */
    public short       e_ehsize;    /* ELF header size */
    public short       e_phentsize; /* Size of program header entry */
    public short       e_phnum;     /* Number of program header entries */
    public short       e_shentsize; /* Size of section header entry */
    public short       e_shnum;     /* Number of section header entries */
    public short       e_shstrndx;  /* Section name string table index */
          
    public ElfHeaderStruct(){
        e_ident = new ArrayList();
    }
}
