package com.liqi.file;

import org.junit.Test;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * Title: ReadFileTest
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/12/24
 */
public class ReadFileTest {
    public static void main(String[] args) {
        String filename = "E:\\话单模板\\CDR.GS.IF.2020111823300000000.66.txt";

        // Traditional IO using FileInputStream
        try {
            FileInputStream fis = new FileInputStream(filename);
            int sum = 0;
            int n;
            long t1 = System.currentTimeMillis();
            try {
                while ((n = fis.read()) >= 0) {
                    sum += n;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block 
                e.printStackTrace();
            }
            long t = System.currentTimeMillis() - t1;
            System.out.println("FileInputStream sum:" + sum + "  time:" + t);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block 
            e.printStackTrace();
        }

        // Traditional IO using BufferedInputStream
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            int sum = 0;
            int n;
            long t1 = System.currentTimeMillis();
            try {
                while ((n = bis.read()) >= 0) {
                    sum += n;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block 
                e.printStackTrace();
            }
            long t = System.currentTimeMillis() - t1;
            System.out.println("BufferedInputStream sum:" + sum + "  time:" + t);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block 
            e.printStackTrace();
        }

        // NIO way of using memory mapped buffer
        MappedByteBuffer buffer = null;
        int length = 1253244;
        try {
            buffer = new RandomAccessFile(filename, "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
            int sum = 0;
            int n;
            long t1 = System.currentTimeMillis();
            for (int i = 0; i < length; i++) {
                n = 0x000000ff & buffer.get(i);
                sum += n;
            }
            long t = System.currentTimeMillis() - t1;
            System.out.println("MmoeryMappedBuffer sum:" + sum + "  time:" + t);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block 
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block 
            e.printStackTrace();
        }
    }

    @Test
    public void test(){
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream("E:\\话单模板\\CDR.GS.IF.2020111823300000000.66.txt");
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                 System.out.println(line);
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (sc != null) {
                    sc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
