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
    
    public List<Byte> getBytes(int offset, int size)
    {
        List<Byte> ret = new ArrayList<Byte>();
        for(int i = offset; i < offset+size; i++)
        {
            ret.add(m_bytes.get(i));
        }
        return ret;
    }
    public void setBytes(List<Byte> bytes)
    {
        m_bytes = bytes;
    }
    
    public int getSize()
    {
        return m_bytes.size();
    }
    
    public long getEndAddress()
    {
        return m_startAddress + m_bytes.size();
    }
    private long m_startAddress;
    private String m_label;
    private List<Byte> m_bytes;
}
