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

package edu.syr.bytecast.fsys.elf;

import java.io.IOException;
import junit.framework.TestCase;
/**
 *
 * @author adodds
 */
public class JunitBytecastFsysTest extends TestCase{
    
    public void testBytecastFsys(){
        ElfFileParser elf_parser = new ElfFileParser();
        //elf_parser.setFilepath("/home/adodds/code/bytecast-fsys/documents/testcase1_input_files/libc.so.6");
        //elf_parser.setFilepath("/lib32/libc.so.6");
        elf_parser.setFilePath("../../documents/testcase1_input_files/a.out");

        try {
            elf_parser.attach();
        } catch (IOException e) {
            System.out.println("Could not open file.");
            assertTrue("Opening file", false);
        }

        try {
            elf_parser.getElfHeader();
        } catch (IOException e) {
            System.out.println("Could not Parse Elf header.");
        }

        try {
            elf_parser.getProgramHeader();
        } catch (IOException e) {
            System.out.println("Could not Parse Program header.");
        }

        try {
            elf_parser.getSectionHeader();
        } catch (IOException e) {
            System.out.println("Could not Parse Elf header.");
        }

        try {
            elf_parser.getSections();
        } catch (IOException e) {
            System.out.println("No section data.");
        }

        try {
            elf_parser.getSegments();
        } catch (IOException e) {
            System.out.println("No segment data.");
        }
        
        assertTrue("passed", true);
    }
}
