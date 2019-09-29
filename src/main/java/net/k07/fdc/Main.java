package net.k07.fdc;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        JFileChooser firstChooser = new JFileChooser();
        JFileChooser secondChooser = new JFileChooser();

        firstChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        secondChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result1 = firstChooser.showOpenDialog(null);
        int result2 = secondChooser.showOpenDialog(null);
        
        File file1 = firstChooser.getSelectedFile();
        File file2 = secondChooser.getSelectedFile();

        traverse(file1, file2);

    }

    public static String getStringToStrip(String path) {
        int location = path.lastIndexOf("\\");
        return path.substring(0, location);

    }
    public static void traverse(File directory1, File directory2) {
        if(directory1.isDirectory()) {
            File[] directory1Files = directory1.listFiles();
            File[] directory2Files = directory2.listFiles();

            for(File file1: directory1Files) {
                String file1Name = file1.getName();
                File file2 = getFileByName(file1Name, directory2Files);

                if(!(file2 == null)) {
                    if (file1.isDirectory()) {
                        if (file2.isDirectory()) {
                            traverse(file1, file2);
                        } else {
                            System.out.println("Directory " + file1.getPath() + " exists, but no such directory was found in the other folder!");
                        }
                    } else {
                        try {
                            System.out.println(file1.getPath() + " " + file2.getPath() + " are equal: " + FileUtils.contentEquals(file1, file2));
                        } catch (IOException e) {
                            System.out.println("rip");
                        }
                    }
                }
                else {
                    System.out.println("File " + file1.getPath() + " exists, but no such file was found in the other folder!");
                }


            }
        }
    }

    public static File getFileByName(String name, File[] files) {
        for(File file: files) {
            if(name.equals(file.getName())) {
                return file;
            }
        }
        return null;
    }
}
