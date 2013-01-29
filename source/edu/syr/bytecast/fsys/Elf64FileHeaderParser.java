/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.fsys;
import java.util.*;
import java.io.*;

/**
 *
 * @author adodds
 */
public class Elf64FileHeaderParser {
    
    private List<Byte> m_elfData;
    private Elf64FileHeaderStruct m_elfMainHeader;
    
    public Elf64FileHeaderParser(){
        m_elfMainHeader = new Elf64FileHeaderStruct();
    };
    
    public void setBinaryData(List<Byte> ElfData){
        m_elfData = ElfData;
    };
    
    public Elf64FileHeaderStruct getElfMainHeader(){
        return m_elfMainHeader;
    };
    
    public boolean parse(){
        int ident_length = 16;
        
        if(m_elfData == null || m_elfData.isEmpty() ||  m_elfData.size() < 64){
            return false;
        }
        
        // update m_ident field. First 16 bytes are the m_ident.
        for(int i = 0; i < ident_length; i++)
        {
            //System.out.println("For i "+i);
            m_elfMainHeader.e_ident.add(m_elfData.get(i));    
        }
       //ident_length = ident_length+3;
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
       return true;
    } 
    
    public void printHeader(){
        for(int i = 0; i < m_elfMainHeader.e_ident.size(); i++)
        {
          System.out.printf("e_type: %02x\n", m_elfMainHeader.e_ident.get(i) );   
        }
        System.out.printf("e_type: %d\n", m_elfMainHeader.e_type );
        System.out.printf("e_machine: %016x\n", m_elfMainHeader.e_machine);
        System.out.printf("e_version: %d\n", m_elfMainHeader.e_version);
        System.out.printf("e_entry: %016x\n", m_elfMainHeader.e_entry);
        System.out.printf("e_phoff: %d (bytes into file)\n", m_elfMainHeader.e_phoff);
        System.out.printf("e_shoff: %d (bytes into file)\n", m_elfMainHeader.e_shoff);
        System.out.printf("e_flags: %02x\n", m_elfMainHeader.e_flags);
        System.out.printf("e_ehsize: %d (bytes)\n", m_elfMainHeader.e_ehsize);
        System.out.printf("e_phentsize: %d (bytes)\n", m_elfMainHeader.e_phentsize);
        System.out.printf("e_phnum: %d\n", m_elfMainHeader.e_phnum);
        System.out.printf("e_shentsize: %d (bytes)\n", m_elfMainHeader.e_shentsize);
        System.out.printf("e_shnum: %d\n", m_elfMainHeader.e_shnum);
        System.out.printf("e_shstrndx: %d\n", m_elfMainHeader.e_shstrndx);
    }

//Test header parser with generic data. Byte array with 1-64 values in it
/*    public static void  main(String args[]){
        ElfMainHeaderParser test = new ElfMainHeaderParser();
        List<Byte> list = new ArrayList() ;
        
        byte x = 1;
        while(x<=64){
            list.add(x);
            x++;
        }
        
        test.setBinaryData(list);
        test.ElfMainParser();
        
    }*/
    @SuppressWarnings("empty-statement")
    public static void  main(String args[]){
        Elf64FileHeaderParser test = new Elf64FileHeaderParser();
        List<Byte> list = new ArrayList() ;
        FileReader rd = new FileReader();
        rd.setFilepath("/home/shawn/code/bytecast-fsys/documents/testcase1_input_files/a.out");
        if(rd.openFile())
        {
          try{
            list = rd.getContents();
            }
            catch(IOException status){
                System.out.println("Main: Error reading file");
            }          
        }
        else
        {
            System.out.println("Could not open file for reading");
        }

        test.setBinaryData(list);
        test.parse();
        test.printHeader();
        
    }
}


    