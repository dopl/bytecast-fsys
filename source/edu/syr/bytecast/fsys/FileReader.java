package edu.syr.bytecast.fsys;

import java.io.*;
import java.util.*;

public class FileReader {

    public void setFilepath(String file_path) {
        m_filepath = file_path;
    }

    public String getFilepath() {
        return m_filepath;
    }

    public boolean openFile() {
        //Open file, setup input stream.
        try { 
            m_inputFile = new RandomAccessFile(m_filepath,"r");                                                                                                                     
            //m_fileIn = new FileInputStream(m_inputFile);
            //m_dataIn = new DataInputStream(m_fileIn);
            m_fileSize = m_inputFile.length();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean closeFile() {
        try {
            m_inputFile.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public long getFileSize() {
        return m_fileSize;
    }

    public List<Byte> getContents() throws IOException {
        return getContents(0, (int)m_fileSize);
    }
    
    public List<Byte> getContents(long offset, int size) throws IOException {

        //Setup return type.
        List<Byte> ret = new ArrayList<Byte>();
        //Setup read buffer. Read block_size when possible for
        //increased performance.
        final int block_size = 16384;
        byte[] buff = new byte[block_size];
        
        //Start reading the file.
        int bytes_read = 0;
        while (bytes_read < size) {
            m_inputFile.seek(offset + bytes_read);
            //Read the block size or what's left (whatever is smaller)
            int read_size = Math.min(size-bytes_read, block_size);
            m_inputFile.readFully(buff, 0, read_size);
            //Copy each byte into ret
            for (int i = 0; i < read_size; i++) {
                ret.add(buff[i]);
            }
            
            bytes_read += read_size;
            
        }
        return ret;
    }
    
    private String m_filepath;
    private long m_fileSize;
    private RandomAccessFile m_inputFile;
}
