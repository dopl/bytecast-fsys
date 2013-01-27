/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.fsys;
import java.util.*;

/**
 *
 * @author adodds
 */
public class ElfMainHeaderParser {
    
    private List<Byte> m_elfData;
    private ElfHeaderStruct m_elfMainHeader;
    
    public ElfMainHeaderParser(){
        m_elfMainHeader = new ElfHeaderStruct();
    };
    
    public void setBinaryData(List<Byte> ElfData){
        m_elfData = ElfData;
    };
    
    public ElfHeaderStruct getElfMainHeader(){
        return m_elfMainHeader;
    };
    
    public boolean ElfMainParser(){
        int ident_length = 16;
        
        if(m_elfData == null || m_elfData.isEmpty()){
            return false;
        }
        
        // update m_ident field. First 16 bytes are the m_ident.
        for(int i = 1; i <= ident_length; i++)
        {
            //System.out.println("For i "+i);
            m_elfMainHeader.e_ident.add(m_elfData.get(i));    
        }
       
       m_elfMainHeader.e_type      = (short)BytecastFsysUtil.bytesToLong(ident_length   , 2,m_elfData);
       m_elfMainHeader.e_machine   = (short)BytecastFsysUtil.bytesToLong(ident_length+2 , 2,m_elfData);
       m_elfMainHeader.e_version   = (int  )BytecastFsysUtil.bytesToLong(ident_length+4 , 4,m_elfData);
       m_elfMainHeader.e_entry     = (long )BytecastFsysUtil.bytesToLong(ident_length+8 , 8,m_elfData);
       m_elfMainHeader.e_phoff     = (long )BytecastFsysUtil.bytesToLong(ident_length+16, 8,m_elfData);
       m_elfMainHeader.e_shoff     = (long )BytecastFsysUtil.bytesToLong(ident_length+24, 8,m_elfData);
       m_elfMainHeader.e_flags     = (int  )BytecastFsysUtil.bytesToLong(ident_length+32, 4,m_elfData);
       m_elfMainHeader.e_ehsize    = (short)BytecastFsysUtil.bytesToLong(ident_length+36, 2,m_elfData);
       m_elfMainHeader.e_phentsize = (short)BytecastFsysUtil.bytesToLong(ident_length+38, 2,m_elfData);
       m_elfMainHeader.e_phnum     = (short)BytecastFsysUtil.bytesToLong(ident_length+40, 2,m_elfData);
       m_elfMainHeader.e_shentsize = (short)BytecastFsysUtil.bytesToLong(ident_length+42, 2,m_elfData);
       m_elfMainHeader.e_shnum     = (short)BytecastFsysUtil.bytesToLong(ident_length+44, 2,m_elfData);
       m_elfMainHeader.e_shstrndx  = (short)BytecastFsysUtil.bytesToLong(ident_length+46, 2,m_elfData); 
       return false;
    }
    
    
    
    
    public static void  main(String args[]){
        ElfMainHeaderParser test = new ElfMainHeaderParser();
        List<Byte> list = new ArrayList() ;
        
        byte x = 1;
        while(x<=64){
            list.add(x);
            x++;
        }
        
        test.setBinaryData(list);
        test.ElfMainParser();
        
    }
}


    