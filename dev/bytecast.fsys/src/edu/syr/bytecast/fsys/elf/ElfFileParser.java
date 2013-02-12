package edu.syr.bytecast.fsys.elf;

import edu.syr.bytecast.fsys.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ElfFileParser {

    private ElfElfHeaderStruct m_elfHeader;
    private List<List<Byte>> m_sections;
    private List<List<Byte>> m_segments;
    private BytecastFileReader m_bytecastFileReader;
    
    //constructor
    ElfFileParser()
    {
        m_bytecastFileReader = new BytecastFileReader();
    }
    
    //set file path of binary data
    public void setFilePath(String filePath)
    {
        m_bytecastFileReader.setFilepath(filePath);
    }
    
    //get file path of binary data
    public String getFilePath()
    {
        return m_bytecastFileReader.getFilepath();  
    }
    
    //open file 
    public boolean attach() throws IOException
    {
        if(m_bytecastFileReader.openFile())
        {
            return true;
        }
        else 
        {
            throw new IOException("Error opening file.");
        }
        
    }
    //close the file.
    public boolean detach() throws IOException
    {
        if(m_bytecastFileReader.closeFile())
        {
            return true;
        }
        else 
        {
            throw new IOException("Error closing file.");
        }
    }
    
    public ElfElfHeaderStruct getElfHeader() throws IOException
    {   
        List<Byte> bin_elf_file_header;
        //read in the first 64 bytes and put it in a byte array
        bin_elf_file_header = m_bytecastFileReader.getContents(0, 64);
        ElfElfHeaderParser file_header_parser = new ElfElfHeaderParser();
        //parse the elf header.
        file_header_parser.setBinaryData(bin_elf_file_header);
        file_header_parser.parse();
        m_elfHeader = file_header_parser.getElfMainHeader();
        
        return m_elfHeader;
    }
    public ElfProgramHeaderStruct getProgramHeader() throws IOException
    {
        //Parse the program header
        long ph_offset     = m_elfHeader.e_phoff; //Program header offset
        //Size of program headers and Number of Program headers
        int ph_read_amount = m_elfHeader.e_phentsize*m_elfHeader.e_phnum; 
            
        List<Byte> bin_elf_prog_header;
        bin_elf_prog_header = m_bytecastFileReader.getContents(ph_offset, ph_read_amount);
        //use e_ident field to find out if its 64 or 32 bit elf file. 
        ElfProgramHeaderParser phparser = new ElfProgramHeaderParser(m_elfHeader.e_ident[4]);
        ElfProgramHeaderStruct phstruct = phparser.parse(bin_elf_prog_header);
        
        m_segments = new ArrayList();
        //pull out the segment data defined by the program headers.
        for(int i = 0; i < phstruct.m_headerEntries.size(); i++)
        {
            //segment address in file
            long segment_offset = phstruct.m_headerEntries.get(i).p_offset;
            //segment size in file
            long segment_size = phstruct.m_headerEntries.get(i).p_filesz;
            
            //there is no segment data for this segment header so add null.
            if(segment_size == 0)
            {
                m_segments.add(null);
            }
            else
            {
                m_segments.add(m_bytecastFileReader.getContents(segment_offset,(int)segment_size));
            }
        }
        
        return phstruct;
    }
    public ElfSectionHeaderStruct getSectionHeader() throws IOException
    {
        //Parse the section headers.
        long sh_offset    = m_elfHeader.e_shoff; //Address of the section headers
        //size of the section headers and the number of section headers.
        int read_amount   = m_elfHeader.e_shentsize * m_elfHeader.e_shnum;
            
        List<Byte> bin_elf_sec_header;
        bin_elf_sec_header = m_bytecastFileReader.getContents(sh_offset, read_amount);
        //use e_ident field to find out if its 64 or 32 bit elf file.
        ElfSectionHeaderParser shparser = new ElfSectionHeaderParser(m_elfHeader.e_ident[4]);
        //parse the section headers.
        ElfSectionHeaderStruct shstruct = shparser.parseHeader(bin_elf_sec_header);
        
        m_sections = new ArrayList();
        //pull out the section data defined by the program headers.
        for(int i = 0; i < shstruct.m_headerEntries.size(); i++)
        {
            //segment address in file
            long section_offset = shstruct.m_headerEntries.get(i).sh_offset;
            //segment size in file
            long section_size = shstruct.m_headerEntries.get(i).sh_size;
            
            //there is no section data for this section header so add null.
            if(section_size == 0)
            {
                m_sections.add(null);
            }
            else
            {
                m_sections.add(m_bytecastFileReader.getContents(section_offset,(int)section_size));
            }
        }
        
        return shstruct;
    }
    public List<List<Byte>> getSections() throws IOException
    {
        if(m_sections != null)
        {
            return m_sections;
        }
        else
        {
            throw new IOException("No sections are loaded.");
        }
    }
    public List<List<Byte>> getSegments() throws IOException
    {
        if(m_segments != null)
        {
            return m_segments;
        }
        else
        {
            throw new IOException("No segments are loaded.");
        }
    }
    
    public static void main(String args[]) 
    {
        ElfFileParser elf_parser = new ElfFileParser();
        //elf_parser.setFilepath("/home/adodds/code/bytecast-fsys/documents/testcase1_input_files/libc.so.6");
        //elf_parser.setFilepath("/lib32/libc.so.6");
        elf_parser.setFilePath("/home/adodds/code/bytecast/bytecast-fsys/documents/testcase1_input_files/a.out");
        
        try {
            elf_parser.attach();
        } catch (IOException e) {
           System.out.println("Could not open file.");
        }
        
        try{
            elf_parser.getElfHeader();
        }
        catch (IOException e) {
           System.out.println("Could Parse Elf header.");
        }
        
        try{
            elf_parser.getProgramHeader();
        }
        catch(IOException e) {
           System.out.println("Could Parse Program header.");
        }
        
        try{
            elf_parser.getSectionHeader();
        }
        catch(IOException e) {
           System.out.println("Could Parse Elf header.");
        }
        
        try{
            elf_parser.getSections();
        }
        catch(IOException e){
            System.out.println("No section data.");
        }
        
        try{
            elf_parser.getSegments();
        }
        catch(IOException e){
            System.out.println("No segment data.");
        }
    }
}
