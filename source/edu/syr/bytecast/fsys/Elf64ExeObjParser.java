package edu.syr.bytecast.fsys;

import java.io.*;
import java.util.*;

public class Elf64ExeObjParser implements IBytecastFsys {

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

        FileReader file_reader = new FileReader();
        file_reader.setFilepath(m_filePath);
        if (file_reader.openFile()) {
            List<Byte> bin_elf_file_header;
            bin_elf_file_header = file_reader.getContents(0, 64);

            Elf64FileHeaderParser file_header_parser;
            file_header_parser = new Elf64FileHeaderParser();
            file_header_parser.setBinaryData(bin_elf_file_header);

            try {
                file_header_parser.parse();
                file_header_parser.printHeader();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } else {
            throw new IOException("Error opening file.");
        }

        return new ExeObj();
    }

    public static void main(String args[]) {
        Elf64ExeObjParser elf_parser = new Elf64ExeObjParser();
        elf_parser.setFilepath("/home/shawn/code/bytecast-fsys/documents/testcase1_input_files/a.out");
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