package edu.syr.bytecast.fsys;

import java.io.*;
import java.util.*;

public class FileReader {
    public void setFilepath(String file_path)
    {
        m_filepath = file_path;
    }
    
    public String getFilepath()
    {
        return m_filepath;
    }
    
    public List<Byte> getContents() throws IOException
    {
        //Open file, setup input stream.
        File input_file = new File (m_filepath);
        FileInputStream file_in = new FileInputStream(input_file);
        DataInputStream data_in = new DataInputStream (file_in );
        
        //Setup return type.
        List<Byte> ret = new ArrayList<Byte>();
        
        //Setup read buffer.
        final int block_size = 16384;
        byte [] buff = new byte[block_size];

        //Determine the file size and initialize the amount to read.
        int bytes_left = data_in.available();
        int amount_to_read = Math.min(bytes_left, block_size);
        
        while(amount_to_read > 0) {
            try {
                data_in.readFully(buff);
            }
            catch(EOFException eof) {
                //do nothing.
            }
            finally {
                for(int i = 0; i < amount_to_read; i++)
                {
                    ret.add(buff[i]);
                }
                bytes_left = data_in.available();
                amount_to_read = Math.min(bytes_left, block_size);
            }
        }
        return ret;
    }
    
//    public static void main(String[] args)
//    {
//        FileReader test = new FileReader();
//        test.setFilepath(("/home/shawn/test.bin"));
//        List<Byte> result = new ArrayList<Byte>();
//        try{
//            result = test.getContents();
//        }
//        catch(Exception e)
//        {
//            System.out.println("error");
//        }
//        for(int i = 0; i < result.size(); i++)
//        {
//            System.out.println(Byte.toString(result.get(i)));
//        }
//        System.out.println("Done");
//    }
    private String m_filepath;
}
