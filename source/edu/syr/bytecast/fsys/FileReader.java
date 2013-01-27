/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.syr.bytecast.fsys;

import java.io.IOException;

/**
 *
 * @author shawn
 */
public class FileReader {
    public void setFilepath(String file_path)
    {
        m_filepath = file_path;
    }
    
    public String getFilepath()
    {
        return m_filepath;
    }
    
    public void getContents() throws IOException
    {
        
    }
    
    private String m_filepath;
}
