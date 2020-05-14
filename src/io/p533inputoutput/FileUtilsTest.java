package io.p533inputoutput;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author nuc8
 * @date 2020/5/14 8:28 上午
 */
public class FileUtilsTest {
    public static void main(String[] args) {
        File srcFile = new File("src/io/p533inputoutput/1.jpg");
        File destFile = new File("src/io/p533inputoutput/4.jpg");

        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
