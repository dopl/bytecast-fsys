package edu.syr.bytecast.fsys;
import java.util.*;

public class BytecastFsysUtil {
     public static long bytesToLong(List<Byte> bytes){
         return bytesToLong(0,bytes.size(),bytes);
     }
     
     public static long bytesToLong(int start,int size, List<Byte> bytes){
         long ret = 0;
         if((start+size) > bytes.size()){
             System.out.println("WARNING: bytesToLong: Index + size"
                     + " falls out of Byte array range");        
         }
         else{
             for(int i = start + size-1; i >= start; i--){
                ret = ret << 8;
                
                //Logical AND to eliminate sign extension.
                ret = ret + (0x00000000000000FF & bytes.get(i));
             }
         }
         return ret;
     }
     public static List<String> parseStringTable(List<Byte> input)
     {
          return parseStringTable(input,0,input.size());
     }
     
     public static List<String> parseStringTable(List<Byte> input, int start_index, int length)
     {
         List<String> ret = new ArrayList<String>();
         
         String curr_string = "";
         for(int i = start_index; i < length; i++)
         {
             char val = (char)input.get(i).byteValue();
             
             if(val != '\0'){
                 curr_string += val;
             }
             else
             {
                 ret.add(curr_string);
                 curr_string = "";
             }
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
}
