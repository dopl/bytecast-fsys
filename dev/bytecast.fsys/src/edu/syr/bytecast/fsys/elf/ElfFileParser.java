package edu.syr.bytecast.fsys.elf;
import java.util.*;

public class ElfFileParser {
    
    private String m_filePath;
    private List<List<String>> m_sections;
    private List<List<String>> m_segments;
    
    public void setFilePath(String filePath)
    {
        m_filePath = filePath;
    }
    public String getFilePath()
    {
        return m_filePath;  
    }
    public boolean attach()
    {return false;}
    public boolean detach()
    {return false;}
    public ElfElfHeaderStruct getElfHeader()
    {return null;}
    public ElfProgramHeaderStruct getProgramHeader()
    {return null;}
    public ElfSectionHeaderStruct getSectionHeader()
    {return null;};
    public List<List<Byte>> getSections()
    {return null;}
    public List<List<Byte>> getSegments()
    {return null;}
}
