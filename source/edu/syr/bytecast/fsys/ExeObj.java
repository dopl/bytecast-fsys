package edu.syr.bytecast.fsys;
import java.util.*;

public class ExeObj {
    
    public ExeObjSegment getEntryPoint() {
        return m_entryPoint;
    }
     
    void setEntryPoint(ExeObjSegment entry_point) {
        m_entryPoint = entry_point;
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
    
    private ExeObjSegment m_entryPoint;
    private List<ExeObjSegment> m_segments;
    private List<ExeObjDependency> m_dependencies;
    
}
