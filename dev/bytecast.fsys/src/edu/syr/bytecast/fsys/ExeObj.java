package edu.syr.bytecast.fsys;
import java.util.*;

public class ExeObj {
    
    public int getEntryPointIndex() {
        return m_entryPointIndex;
    }
     
    void setEntryPointIndex(int entry_point) {
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
    
    public void printExeObj(){
        
        System.out.printf("entryPointIndex: %016x\n", m_entryPointIndex);
        System.out.printf("::Segment Data::");
         
        for(int i = 0; i <  m_segments.size(); i++)
        {
            System.out.printf("Label:  %016x\n", m_segments.get(i).getLabel());
            System.out.printf("StartAddress:  %016x\n", m_segments.get(i).getStartAddress());
            System.out.printf("Bytes:  %016x\n", m_segments.get(i).getBytes());
        }  
        
        for(int i = 0; i <  m_dependencies.size(); i++)
        {
            System.out.printf("Name:  %016x\n", m_dependencies.get(i).getDependencyName());
            System.out.printf("DependencyPath:  %016x\n", m_dependencies.get(i).getDependencyPath());
            System.out.printf("Type:  %016x\n", m_dependencies.get(i).getDependencyType());
            System.out.printf("StartOffset:  %016x\n", m_dependencies.get(i).getStartOffset());
        }  
        
    }
    private int m_entryPointIndex;
    private List<ExeObjSegment> m_segments;
    private List<ExeObjDependency> m_dependencies;
    
}
