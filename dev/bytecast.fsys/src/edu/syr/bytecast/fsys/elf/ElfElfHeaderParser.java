package edu.syr.bytecast.fsys.elf;

import edu.syr.bytecast.fsys.BytecastFsysUtil;
import java.io.*;
import java.util.*;

public class ElfElfHeaderParser {

    private List<Byte> m_elfData;
    private ElfElfHeaderStruct m_elfElfHeader;

    public ElfElfHeaderParser() {
        m_elfElfHeader = new ElfElfHeaderStruct();
    }

    ;
    
    public void setBinaryData(List<Byte> ElfData) {
        m_elfData = ElfData;
    }

    ;
    
    public ElfElfHeaderStruct getElfMainHeader() {
        return m_elfElfHeader;
    }

    ;
    
    public void parse() throws IOException {
        int ident_length = m_elfElfHeader.e_ident.length;

        if (m_elfData == null || m_elfData.isEmpty() || m_elfData.size() < 16) {
            throw new IOException("ELF file header less than expected size");
        }

        // update m_ident field. First 16 bytes are the m_ident.
        for (int i = 0; i < ident_length; i++) {
            //System.out.println("For i "+i);
            m_elfElfHeader.e_ident[i] = m_elfData.get(i);
        }

        //Check to see if the file is an ELF file.
        if (m_elfElfHeader.e_ident[0] != 0x7F
                || m_elfElfHeader.e_ident[1] != 'E'
                || m_elfElfHeader.e_ident[2] != 'L'
                || m_elfElfHeader.e_ident[3] != 'F') {
            throw new IOException("Input is not an ELF header");
        }

        int[] field_sizes;
        if (m_elfElfHeader.e_ident[4] == m_elfElfHeader.ELFCLASS32) {
            field_sizes = m_elfElfHeader.ELF32_FIELD_SIZES;
        } else if (m_elfElfHeader.e_ident[4] == m_elfElfHeader.ELFCLASS64) {
            field_sizes = m_elfElfHeader.ELF64_FIELD_SIZES;
        } else {
            throw new IOException("Unsupported ELF architecture. ELF32 or "
                    + "ELF64 supported only");
        }

        int bytes_consumed = ident_length;
        for (int i = 0; i < field_sizes.length; i++) {
            long parseResult = BytecastFsysUtil.bytesToLong(bytes_consumed,
                    field_sizes[i],
                    m_elfData);
            bytes_consumed += field_sizes[i];
            switch (i) {
                case 0:
                    m_elfElfHeader.e_type = (short) parseResult;
                    break;
                case 1:
                    m_elfElfHeader.e_machine = (short) parseResult;
                    break;
                case 2:
                    m_elfElfHeader.e_version = (int) parseResult;
                    break;
                case 3:
                    m_elfElfHeader.e_entry = (long) parseResult;
                    break;
                case 4:
                    m_elfElfHeader.e_phoff = (long) parseResult;
                    break;
                case 5:
                    m_elfElfHeader.e_shoff = (long) parseResult;
                    break;
                case 6:
                    m_elfElfHeader.e_flags = (int) parseResult;
                    break;
                case 7:
                    m_elfElfHeader.e_ehsize = (short) parseResult;
                    break;
                case 8:
                    m_elfElfHeader.e_phentsize = (short) parseResult;
                    break;
                case 9:
                    m_elfElfHeader.e_phnum = (short) parseResult;
                    break;
                case 10:
                    m_elfElfHeader.e_shentsize = (short) parseResult;
                    break;
                case 11:
                    m_elfElfHeader.e_shnum = (short) parseResult;
                    break;
                case 12:
                    m_elfElfHeader.e_shstrndx = (short) parseResult;
                    break;
                default:
                    break;
            }
        }
    }

    public void printHeader() {
        for (int i = 0; i < 16; i++) {
            System.out.printf("e_ident: %02x\n", m_elfElfHeader.e_ident[i]);
        }
        System.out.printf("e_type: %d\n", m_elfElfHeader.e_type);
        System.out.printf("e_machine: %016x\n", m_elfElfHeader.e_machine);
        System.out.printf("e_version: %d\n", m_elfElfHeader.e_version);
        System.out.printf("e_entry: %016x\n", m_elfElfHeader.e_entry);
        System.out.printf("e_phoff: %d (bytes into file)\n", m_elfElfHeader.e_phoff);
        System.out.printf("e_shoff: %d (bytes into file)\n", m_elfElfHeader.e_shoff);
        System.out.printf("e_flags: %02x\n", m_elfElfHeader.e_flags);
        System.out.printf("e_ehsize: %d (bytes)\n", m_elfElfHeader.e_ehsize);
        System.out.printf("e_phentsize: %d (bytes)\n", m_elfElfHeader.e_phentsize);
        System.out.printf("e_phnum: %d\n", m_elfElfHeader.e_phnum);
        System.out.printf("e_shentsize: %d (bytes)\n", m_elfElfHeader.e_shentsize);
        System.out.printf("e_shnum: %d\n", m_elfElfHeader.e_shnum);
        System.out.printf("e_shstrndx: %d\n", m_elfElfHeader.e_shstrndx);
    }
}
