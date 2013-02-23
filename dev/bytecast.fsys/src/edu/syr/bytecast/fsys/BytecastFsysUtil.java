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

public class BytecastFsysUtil {

    public static long bytesToLong(List<Byte> bytes) {
        return bytesToLong(0, bytes.size(), bytes);
    }

    public static long bytesToLong(int start, int size, List<Byte> bytes) {
        long ret = 0;
        if ((start + size) > bytes.size()) {
            System.out.println("WARNING: bytesToLong: Index + size"
                    + " falls out of Byte array range");
        } else {
            for (int i = start + size - 1; i >= start; i--) {
                ret = ret << 8;

                //Logical AND to eliminate sign extension.
                ret = ret + (0x00000000000000FF & bytes.get(i));
            }
        }
        return ret;
    }

    public static String parseStringFromBytes(List<Byte> input, int index) {

        String ret = "";
        int curr_pos = index;
        while (curr_pos < input.size()) {
            char val = (char) input.get(curr_pos).byteValue();

            if (val != '\0') {
                ret += val;
            } else {
                break;
            }
            curr_pos++;
        }


        return ret;
    }
//     
//     public static void main(String args[])
//     {
//         List<Byte> test = new ArrayList<Byte>();
//         test.add((byte)'h');
//         test.add((byte)'e');
//         test.add((byte)'l');
//         test.add((byte)'l');
//         test.add((byte)'o');
//         test.add((byte)'\0');
//         test.add((byte)'w');
//         test.add((byte)'o');
//         test.add((byte)'r');
//         test.add((byte)'l');
//         test.add((byte)'d');
//         test.add((byte)'\0');
//        
//         List<String> tmp = parseStringTable(test);
//        
//        for(int i = 0; i < tmp.size(); i++)
//             System.out.println("Entry "+i+": " + tmp.get(i));
//        
//     }
    public String searchForFile(String path, String name){
        String ret = "No File Found";
        File file_contents = new File(path);         
        //see if the path is a directory. 
        if(file_contents.isDirectory() == false)
        {
            return path;
        }
        
        ret = bfsSearch(file_contents,name);
        
        return ret;
    }
    
    private String appendPathName(String path, String name){
        String ret = "";
        ret = path + "/" + name;
        return ret;
    }
    
    private String bfsSearch(File filein, String name)
    {
        String ret = "";
        
        Queue<File> q = new LinkedList<File>();
        
        q.add(filein);
        while(q.isEmpty() == false)
        {
            File dir_contents = q.remove();
            String[] folder_contents = dir_contents.list();
            
            
            
            //search if the file is in the current directory.
            for(int i = 0; i < folder_contents.length; i++)
            {
                if(folder_contents[i].equals(name))
                {
                    ret = appendPathName(dir_contents.getPath(),name);
                    return ret;
                }
                
                File add_file = new File(appendPathName(dir_contents.getPath(),folder_contents[i]));
                if(add_file.isDirectory())
                {
                    q.add(add_file);
                }
            }
        }
        
        return ret;
    }
    
    public static void main(String args[]) {
         BytecastFsysUtil test = new BytecastFsysUtil();
         System.out.println(test.searchForFile("/home/adodds/code/", "a.out"));
         
          System.out.println("FsysUtil Main done");
    }
    
}
