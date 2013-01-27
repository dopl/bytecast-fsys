package edu.syr.bytecast.fsys;
import java.util.*;

public class ExeObj {
    
    public int getEntryPointIndex() {
        return m_entryPointIndex;
    }
     
    void setEntryPoint(int entry_point) {
        m_entryPointIndex = entry_point;
    }
    
    public List<ExeObjSegment> getSegments() {
        return m_segments;
    }
    
    public void setSegments(List<ExeObjSegment> segments) {
        m_segments=segments;
    }
    
    public List<ExeObjDependency> getDependencies(){
        return m_dependencies;
    }    
     
    public void setDependencies(List<ExeObjDependency> dependencies){
        m_dependencies = dependencies;
    }   
    
    private int m_entryPointIndex;
    private List<ExeObjSegment> m_segments;
    private List<ExeObjDependency> m_dependencies;
    
}
