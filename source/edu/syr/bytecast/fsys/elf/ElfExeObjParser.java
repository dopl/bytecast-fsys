package edu.syr.bytecast.fsys.elf;

import edu.syr.bytecast.fsys.*;
import java.io.*;
import java.util.*;

public class ElfExeObjParser implements IBytecastFsys {

    @Override
    public void setFilepath(String file_path) {
        m_filePath = file_path;
    }

    @Override
    public String getFilepath() {
        return m_filePath;
    }

    @Override
    public ExeObj parse() throws IOException {

        BytecastFileReader file_reader = new BytecastFileReader();
        file_reader.setFilepath(m_filePath);
        if (file_reader.openFile()) {
            List<Byte> bin_elf_file_header;
            bin_elf_file_header = file_reader.getContents(0, 64);
            
            //Parse the file header.
            ElfElfHeaderParser file_header_parser;
            file_header_parser = new ElfElfHeaderParser();
            file_header_parser.setBinaryData(bin_elf_file_header);
            file_header_parser.parse();
            ElfElfHeaderStruct fheader = file_header_parser.getElfMainHeader();
            
            //Parse the section headers.
            long sh_offset    = fheader.e_shoff;
            int read_amount   = fheader.e_shentsize * fheader.e_shnum;
            
            List<Byte> bin_elf_sec_header;
            bin_elf_sec_header = file_reader.getContents(sh_offset, read_amount);
            ElfSectionHeaderParser shparser = new ElfSectionHeaderParser(fheader.e_ident[4]);
            ElfSectionHeaderStruct shstruct = shparser.parseHeader(bin_elf_sec_header);
            shparser.printHeader(shstruct);

        } else {
            throw new IOException("Error opening file.");
        }

        return new ExeObj();
    }

    public static void main(String args[]) {
        ElfExeObjParser elf_parser = new ElfExeObjParser();
        elf_parser.setFilepath("/lib32/libc.so.6");
        try {
            elf_parser.parse();
        } catch (FileNotFoundException e) {
            System.out.println("Could not parse file.");
        } catch (IOException e) {
            System.out.println("Error parsing file.");
        }
    }
    private String m_filePath;
}