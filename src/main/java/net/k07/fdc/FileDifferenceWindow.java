package net.k07.fdc;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileDifferenceWindow extends JFrame {

    private File directory1, directory2;
    public static final JTextField folder1TextField = new JTextField();
    public static final JTextField folder2TextField = new JTextField();
    public static final JTextArea outputArea = new JTextArea();
    public static final JTextArea newFilesArea = new JTextArea();

    public FileDifferenceWindow() {
        this.setLayout(new GridBagLayout());

        GridBagConstraints c1 = new GBCBuilder()
                .gridLocation(0, 0)
                .componentSize(1, 1)
                .internalPadding(0, 0)
                .externalPadding(0, 0, 0, 0)
                .fill(GridBagConstraints.HORIZONTAL)
                .anchor(GridBagConstraints.CENTER)
                .weight(0.1, 0.1)
                .build();

        JLabel folder1Label = new JLabel("Newer folder:");
        this.add(folder1Label, c1);

        GridBagConstraints c2 = new GBCBuilder()
                .gridLocation(1, 0)
                .componentSize(2, 1)
                .internalPadding(0, 0)
                .externalPadding(0, 0, 0, 0)
                .fill(GridBagConstraints.HORIZONTAL)
                .anchor(GridBagConstraints.CENTER)
                .weight(0.3, 0.1)
                .build();

        folder1TextField.setEditable(false);
        this.add(folder1TextField, c2);

        GridBagConstraints c3 = new GBCBuilder()
                .gridLocation(3, 0)
                .componentSize(1, 1)
                .internalPadding(0, 0)
                .externalPadding(0, 0, 0, 0)
                .fill(GridBagConstraints.HORIZONTAL)
                .anchor(GridBagConstraints.CENTER)
                .weight(0.1, 0.1)
                .build();

        JLabel folder2Label = new JLabel("Older folder:");
        this.add(folder2Label, c3);

        GridBagConstraints c4 = new GBCBuilder()
                .gridLocation(4, 0)
                .componentSize(2, 1)
                .internalPadding(0, 0)
                .externalPadding(0, 0, 0, 0)
                .fill(GridBagConstraints.HORIZONTAL)
                .anchor(GridBagConstraints.CENTER)
                .weight(0.3, 0.1)
                .build();

        folder2TextField.setEditable(false);
        this.add(folder2TextField, c4);

        GridBagConstraints c5 = new GBCBuilder()
                .gridLocation(0, 1)
                .componentSize(3, 1)
                .internalPadding(0, 0)
                .externalPadding(0, 0, 0, 0)
                .fill(GridBagConstraints.HORIZONTAL)
                .anchor(GridBagConstraints.CENTER)
                .weight(0.3, 0.1)
                .build();

        JButton folder1Button = new JButton("Select newer folder...");
        folder1Button.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File directory = selectDirectory();
                if(directory != null) {
                    directory1 = directory;
                    folder1TextField.setText(directory1.getPath());
                }
            }
        });
        this.add(folder1Button, c5);

        GridBagConstraints c6 = new GBCBuilder()
                .gridLocation(3, 1)
                .componentSize(3, 1)
                .internalPadding(0, 0)
                .externalPadding(0, 0, 0, 0)
                .fill(GridBagConstraints.HORIZONTAL)
                .anchor(GridBagConstraints.CENTER)
                .weight(0.3, 0.1)
                .build();

        JButton folder2Button = new JButton("Select older folder...");
        folder2Button.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File directory = selectDirectory();
                if(directory != null) {
                    directory2 = directory;
                    folder2TextField.setText(directory2.getPath());
                }
            }
        });
        this.add(folder2Button, c6);

        GridBagConstraints c7 = new GBCBuilder()
                .gridLocation(0, 2)
                .componentSize(3, 2)
                .internalPadding(0, 0)
                .externalPadding(0, 0, 0, 0)
                .fill(GridBagConstraints.BOTH)
                .anchor(GridBagConstraints.CENTER)
                .weight(0.8, 0.1)
                .build();

        JPanel outputPanel = new JPanel();
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));
        JScrollPane pane = new JScrollPane(outputArea);
        outputPanel.setLayout(new GridLayout());
        outputPanel.add(pane);
        this.add(outputPanel, c7);

        GridBagConstraints c8 = new GBCBuilder()
                .gridLocation(3, 2)
                .componentSize(3, 2)
                .internalPadding(0, 0)
                .externalPadding(0, 0, 0, 0)
                .fill(GridBagConstraints.BOTH)
                .anchor(GridBagConstraints.CENTER)
                .weight(0.8, 0.1)
                .build();

        JPanel newFilesPanel = new JPanel();
        newFilesPanel.setBorder(BorderFactory.createTitledBorder("Output"));
        JScrollPane newPane = new JScrollPane(newFilesArea);
        newFilesPanel.setLayout(new GridLayout());
        newFilesPanel.add(newPane);
        this.add(newFilesPanel, c8);

        GridBagConstraints c9 = new GBCBuilder()
                .gridLocation(0, 4)
                .componentSize(6, 1)
                .internalPadding(0, 0)
                .externalPadding(0, 0, 0, 0)
                .fill(GridBagConstraints.HORIZONTAL)
                .anchor(GridBagConstraints.CENTER)
                .weight(0.8, 0.1)
                .build();

        JButton traverseButton = new JButton("Start");
        traverseButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("");
                traverse(directory1, directory2);
            }
        });
        this.add(traverseButton, c9);


    }

    public File selectDirectory() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);

        if(result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        else {
            return null;
        }
    }

    public static void traverse(File directory1, File directory2) {
        if(directory1.isDirectory()) {
            File[] directory1Files = directory1.listFiles();
            File[] directory2Files = directory2.listFiles();

            Set<String> directory1Names = new HashSet<String>();
            Set<String> directory2Names = new HashSet<String>();

            for(File file1: directory1Files) {
                String file1Name = file1.getName();
                directory1Names.add(file1Name);

                File file2 = getFileByName(file1Name, directory2Files);

                if(!(file2 == null)) {
                    if (file1.isDirectory()) {
                        if (file2.isDirectory()) {
                            traverse(file1, file2);
                        } else {
                            outputArea.append("Directory " + file1.getPath() + " exists, but no such directory was found in the other folder!\n");
                        }
                    } else {
                        try {
                            if(!FileUtils.contentEquals(file1, file2)) {
                                outputArea.append(file1.getPath() + " " + file2.getPath() + "differ!\n");
                            }
                        } catch (IOException e) {
                            outputArea.append("IOException occurred!\n");
                        }
                    }
                }
                else {
                    newFilesArea.append("File " + file1.getPath() + " exists in dir1, but no such file was found in dir2!\n");
                }
            }

            for(File file2: directory2Files) {
                directory2Names.add(file2.getName());

            }

            directory2Names.removeAll(directory1Names);
            for(String name: directory2Names) {
                newFilesArea.append("File " + name + " exists in dir2, but no such file was found in dir1!\n");
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
