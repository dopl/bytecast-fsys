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
public class BytecastFsysUtil {
     public static long bytesToLong(int start,int size, List<Byte> bytes){
         long ret = 0;
         if((start+size) > bytes.size()){
             System.out.println("bytesToLong: Index + size falls out of Byte array range");        
         }
         else{
             for(int i = start + size-1; i >= start; i--){
                ret = ret << 8;
                
                //Logical AND to eliminate sign extension.
                ret = ret + (0x00000000000000FF & bytes.get(i));
             }
         }
         //System.out.printf("%08x\n", ret);
         return ret;
     }
}
