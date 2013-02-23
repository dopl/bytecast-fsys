/*
 * This file is part of Bytecast.
 *
 * Bytecast is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Bytecast is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Bytecast.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package edu.syr.bytecast.fsys;

import java.io.*;
import java.util.*;

public class BytecastFileReader {

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
