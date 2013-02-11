package edu.syr.bytecast.fsys.elf;

import edu.syr.bytecast.fsys.*;
import java.io.*;
import java.util.*;

public class ElfExeObjParser implements IBytecastFsys {

    public ElfExeObjParser()
    {
        m_preferSections = false;
    }
    @Override
    public void setFilepath(String file_path) {
        m_filePath = file_path;
    }

    @Override
    public String getFilepath() {
        return m_filePath;
    }

    private boolean wantSections(int num_sections, int num_segments) throws IOException
    {
        boolean ret = false;
        //return true if we prefer sections and we have them
        if(num_sections > 0 && m_preferSections)
            ret = true;
        //or if we have no segments
        if(num_sections > 0 && num_segments == 0)
            ret = true;
        
        if(num_sections <= 0 && num_segments <= 0)
            throw new IOException("Elf file contains no executable code"
                    + "(section header and segment header sizes are zero).");
        
        return ret;
    }
    
    @Override
    public ExeObj parse() throws IOException {
        ElfFileParser elf_file_parser = new ElfFileParser();
        elf_file_parser.setFilePath(m_filePath);
        ExeObj ret = new ExeObj();
        
        if(elf_file_parser.attach())
        {
            ElfElfHeaderStruct elf_header = elf_file_parser.getElfHeader();
            
            if(wantSections(elf_header.e_shnum, elf_header.e_phnum))
            {
                
                //Get the sections
                ElfSectionHeaderStruct sect_header = elf_file_parser.getSectionHeader();
                List<List<Byte>> sections = elf_file_parser.getSections();
                
                //Get the segments
                ret.setSegments(generateSegments(sect_header,sections));
            }
            else
            {

            }
        }
        else
        {
            throw new FileNotFoundException(m_filePath + " could not be opened.");
        }
       
        return new ExeObj();
    }

    private boolean isExecutableSection(int type)
    {
        boolean ret = false;
        if(type == ElfSectionHeaderEntryStruct.SHT_PROGBITS)
            ret = true;
        
        return ret;
    }
    
    private String getLabel(List<Byte> string_table, int string_id, int sh_index)
    {
        
        String ret = m_filePath + "_" + 
                     sh_index;
        
        if(string_id != -1 && string_table != null)
            ret += "_" + 
                   BytecastFsysUtil.parseStringFromBytes(string_table, string_id);
        
        return ret;
            
    }
    private List<ExeObjSegment> generateSegments(ElfSectionHeaderStruct sect_header,
                                                 List<List<Byte>> sections)
    {
        List<ExeObjSegment> ret = new ArrayList<ExeObjSegment>(); 
        
        List<Byte> string_table = null;
        
        //First find the string table (needed for section names)
        for(int i = 0; i < sect_header.m_headerEntries.size(); i++)
        {
            ElfSectionHeaderEntryStruct entry = sect_header.m_headerEntries.get(i);
            if(entry.sh_type == entry.SHT_STRTAB)
            {
                string_table = sections.get(i);
            }
        }       
        
        //Now find executable sections.
        for(int i = 0; i < sect_header.m_headerEntries.size(); i++)
        {
            ElfSectionHeaderEntryStruct entry = sect_header.m_headerEntries.get(i);
            if(isExecutableSection(entry.sh_type))
            {
                ExeObjSegment exe_seg = new ExeObjSegment();
                exe_seg.setLabel(getLabel(string_table, entry.sh_info, i));
                exe_seg.setBytes(sections.get(i));
                exe_seg.setStartAddress(entry.sh_addr);
                ret.add(exe_seg);
            }
        }
        return ret;
    }
    
    private List<ExeObjSegment> generateSegments(ElfFileParser elf_file_parser)
    {
        List<ExeObjSegment> ret = new ArrayList<ExeObjSegment>();
        return ret;
    }
    
    private List<ExeObjDependency> generateDependencies(ElfSectionHeaderStruct sect_header,
                                                     List<List<Byte>> sections,
                                                     char arch)
    {
        List<ExeObjDependency> ret = new ArrayList<ExeObjDependency>(); 
        
        //First find the dynamic table
        for(int i = 0; i < sect_header.m_headerEntries.size(); i++)
        {
            ElfSectionHeaderEntryStruct entry = sect_header.m_headerEntries.get(i);
            if(entry.sh_type == entry.SHT_DYNAMIC)
            {
                //
                // USE ELF DEPENDENCY RESOLVER CLASS HERE!
                //
            }
        }       

        return ret;
    }
    
    public static void main(String args[]) {
        ElfExeObjParser elf_parser = new ElfExeObjParser();
        //elf_parser.setFilepath("/home/adodds/code/bytecast-fsys/documents/testcase1_input_files/libc.so.6");
        //elf_parser.setFilepath("/lib32/libc.so.6");
        elf_parser.setFilepath("/home/adodds/code/bytecast/bytecast-fsys/documents/testcase1_input_files/a.out");
        try {
            elf_parser.parse();
        } catch (FileNotFoundException e) {
            System.out.println("Could not parse file.");
        } catch (IOException e) {
            System.out.println("Error parsing file.");
        }
    }

    private String m_filePath;
    private boolean m_preferSections;
}
