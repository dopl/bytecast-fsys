package edu.syr.bytecast.fsys;

import java.util.*;

public class ExeObjSegment {

    public long getStartAddress() {
        return m_startAddress;
    }

    public void setStartAddress(long start_address) {
        m_startAddress = start_address;
    }

    public String getLabel() {
        return m_label;
    }
    
    public void setLabel(String label){
        m_label = label;
    }

    public List<Byte> getBytes() {
        return m_bytes;
    }
    
    public void setBytes(List<Byte> bytes)
    {
        bytes = m_bytes;
    }
    
    private long m_startAddress;
    private String m_label;
    private List<Byte> m_bytes;
}
