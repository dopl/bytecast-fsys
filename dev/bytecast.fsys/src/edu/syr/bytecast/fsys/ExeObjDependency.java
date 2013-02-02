package edu.syr.bytecast.fsys;

public class ExeObjDependency {
    
    public enum ExeObjDependencyType {
        KERNEL, FILE
    }
    
    public String getDependencyName()
    {
        return m_name;
    }
    
    public void setDependencyName(String name)
    {
        m_name = name;
    }
    
    public ExeObjDependencyType getDependencyType(){
        return m_type;
    }
    
    public void setDependencyType(ExeObjDependencyType type)
    {
        m_type = type;
    }
    
    public String getDependencyPath()
    {
        return m_path;
    }
    
    public void setDependencyPath(String path)
    {
        m_path = path;
    }
    
    public long getStartOffset()
    {
        return m_startOffset;
    }
    
    public void setStartOffset(long start_offset)
    {
        m_startOffset = start_offset;
    }
    
    private String m_name;
    private String m_path;
    private long m_startOffset;
    private ExeObjDependencyType m_type;
}
