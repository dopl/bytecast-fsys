package edu.syr.bytecast.fsys.elf;

import edu.syr.bytecast.fsys.BytecastFsysUtil;
import java.io.IOException;
import java.util.List;

public class ElfSymbolTableParser {
    
    public ElfSymbolTableParser(int arch) throws IOException {
        m_arch = arch;
        if(m_arch == ElfElfHeaderStruct.ELFCLASS32) {
            m_entrySize     = ElfProgramHeaderEntryStruct.ELF32_ENTRY_SIZE;
            m_fieldLengths  = ElfProgramHeaderEntryStruct.ELF32_FIELD_SIZES;
        } else if(m_arch == ElfElfHeaderStruct.ELFCLASS64) {
            m_entrySize     = ElfProgramHeaderEntryStruct.ELF64_ENTRY_SIZE;
            m_fieldLengths  = ElfProgramHeaderEntryStruct.ELF64_FIELD_SIZES;
        } else {
            throw new IOException("Unsupported ELF architecture. ELF32 or "
                                    + "ELF64 supported only"); 
        }       
    }


    public ElfSymbolTableStruct parse(List<Byte> data) {
        ElfSymbolTableStruct ret = new ElfSymbolTableStruct();
        int bytes_consumed = 0;
        
        for (int i = 0; i < m_fieldLengths.length; i++) {
            
            long parseResult = BytecastFsysUtil.bytesToLong(bytes_consumed,
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
                    case 5: ret.st_shndx = (short) parseResult; break;
                    default: break;
                }      
            }
            else
            {
                switch (i) {
                    case 0: ret.st_name  = (int)   parseResult; break;
                    case 1: ret.st_info  = (char)  parseResult; break;
                    case 2: ret.st_other = (char)  parseResult; break;
                    case 3: ret.st_shndx = (short) parseResult; break;
                    case 4: ret.st_value = (long)  parseResult; break;
                    case 5: ret.st_size  = (long)  parseResult; break;
                    default: break;
                }                
            }
        }
        
        return ret;
    }

    private int   m_arch;
    private short m_entrySize; 
    private int[] m_fieldLengths;   
}
