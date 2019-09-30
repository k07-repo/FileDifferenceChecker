package net.k07.fdc;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class FileDifferenceWindow extends JFrame {

    private File directory1, directory2;
    public static final JTextField folder1TextField = new JTextField();
    public static final JTextField folder2TextField = new JTextField();
    public static final JTextArea outputArea = new JTextArea();
    public static final JTextArea newFilesArea = new JTextArea();

    public FileDifferenceWindow() {
        this.setLayout(new GridBagLayout());

        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 0;
        c1.gridwidth = 1;
        c1.gridheight = 1;
        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.ipadx = 0;
        c1.ipady = 0;
        c1.insets = new Insets(0, 0, 0, 0);
        c1.anchor = GridBagConstraints.CENTER;
        c1.weightx = 0.1;
        c1.weighty = 0.1;

        JLabel folder1Label = new JLabel("Newer folder:");
        this.add(folder1Label, c1);

        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 1;
        c2.gridy = 0;
        c2.gridwidth = 2;
        c2.gridheight = 1;
        c2.fill = GridBagConstraints.HORIZONTAL;
        c2.ipadx = 0;
        c2.ipady = 0;
        c2.insets = new Insets(0, 0, 0, 0);
        c2.anchor = GridBagConstraints.CENTER;
        c2.weightx = 0.3;
        c2.weighty = 0.1;

        folder1TextField.setEditable(false);
        this.add(folder1TextField, c2);

        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 3;
        c3.gridy = 0;
        c3.gridwidth = 1;
        c3.gridheight = 1;
        c3.fill = GridBagConstraints.HORIZONTAL;
        c3.ipadx = 0;
        c3.ipady = 0;
        c3.insets = new Insets(0, 0, 0, 0);
        c3.anchor = GridBagConstraints.CENTER;
        c3.weightx = 0.1;
        c3.weighty = 0.1;

        JLabel folder2Label = new JLabel("Older folder:");
        this.add(folder2Label, c3);

        GridBagConstraints c4 = new GridBagConstraints();
        c4.gridx = 4;
        c4.gridy = 0;
        c4.gridwidth = 2;
        c4.gridheight = 1;
        c4.fill = GridBagConstraints.HORIZONTAL;
        c4.ipadx = 0;
        c4.ipady = 0;
        c4.insets = new Insets(0, 0, 0, 0);
        c4.anchor = GridBagConstraints.CENTER;
        c4.weightx = 0.3;
        c4.weighty = 0.1;

        folder2TextField.setEditable(false);
        this.add(folder2TextField, c4);

        GridBagConstraints c5 = new GridBagConstraints();
        c5.gridx = 0;
        c5.gridy = 1;
        c5.gridwidth = 3;
        c5.gridheight = 1;
        c5.fill = GridBagConstraints.HORIZONTAL;
        c5.ipadx = 0;
        c5.ipady = 0;
        c5.insets = new Insets(0, 0, 0, 0);
        c5.anchor = GridBagConstraints.CENTER;
        c5.weightx = 0.3;
        c5.weighty = 0.1;

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

        GridBagConstraints c6 = new GridBagConstraints();
        c6.gridx = 3;
        c6.gridy = 1;
        c6.gridwidth = 3;
        c6.gridheight = 1;
        c6.fill = GridBagConstraints.HORIZONTAL;
        c6.ipadx = 0;
        c6.ipady = 0;
        c6.insets = new Insets(0, 0, 0, 0);
        c6.anchor = GridBagConstraints.CENTER;
        c6.weightx = 0.3;
        c6.weighty = 0.1;

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

        GridBagConstraints c7 = new GridBagConstraints();
        c7.gridx = 0;
        c7.gridy = 2;
        c7.gridwidth = 3;
        c7.gridheight = 2;
        c7.fill = GridBagConstraints.BOTH;
        c7.ipadx = 0;
        c7.ipady = 0;
        c7.insets = new Insets(0, 0, 0, 0);
        c7.anchor = GridBagConstraints.CENTER;
        c7.weightx = 0.8;
        c7.weighty = 0.1;

        JPanel outputPanel = new JPanel();
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));
        JScrollPane pane = new JScrollPane(outputArea);
        outputPanel.setLayout(new GridLayout());
        outputPanel.add(pane);
        this.add(outputPanel, c7);

        GridBagConstraints c8 = new GridBagConstraints();
        c8.gridx = 3;
        c8.gridy = 2;
        c8.gridwidth = 3;
        c8.gridheight = 2;
        c8.fill = GridBagConstraints.BOTH;
        c8.ipadx = 0;
        c8.ipady = 0;
        c8.insets = new Insets(0, 0, 0, 0);
        c8.anchor = GridBagConstraints.CENTER;
        c8.weightx = 0.8;
        c8.weighty = 0.1;

        JPanel newFilesPanel = new JPanel();
        newFilesPanel.setBorder(BorderFactory.createTitledBorder("Output"));
        JScrollPane newPane = new JScrollPane(newFilesArea);
        newFilesPanel.setLayout(new GridLayout());
        newFilesPanel.add(newPane);
        this.add(newFilesPanel, c8);

        GridBagConstraints c9 = new GridBagConstraints();
        c9.gridx = 0;
        c9.gridy = 4;
        c9.gridwidth = 6;
        c9.gridheight = 1;
        c9.fill = GridBagConstraints.HORIZONTAL;
        c9.ipadx = 0;
        c9.ipady = 0;
        c9.insets = new Insets(0, 0, 0, 0);
        c9.anchor = GridBagConstraints.CENTER;
        c9.weightx = 0.8;
        c9.weighty = 0.1;

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

            for(File file1: directory1Files) {
                String file1Name = file1.getName();
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
                    newFilesArea.append("File " + file1.getPath() + " exists, but no such file was found in the other folder!\n");
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
