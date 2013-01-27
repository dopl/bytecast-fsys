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
             for(int i = start; i < start + size; i++){
                ret = ret << 8;
                //System.out.printf("%02x\n", bytes.get(i));
                //  System.out.printf("%08x\n", ret);
                ret = ret + bytes.get(i);
             }
         }
         //System.out.printf("%08x\n", ret);
         return ret;
     }
}
