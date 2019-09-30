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
    public static final JTextArea changedFiles = new JTextArea();
    public static final JTextArea uniqueToFolder1 = new JTextArea();
    public static final JTextArea uniqueToFolder2 = new JTextArea();

    public FileDifferenceWindow() {
        this.setTitle("File Difference Checker");
        this.setLayout(new GridBagLayout());

        JLabel folder1Label = new JLabel("Newer folder:");
        this.add(folder1Label, ConstraintsList.c1);

        folder1TextField.setEditable(false);
        this.add(folder1TextField, ConstraintsList.c2);

        JLabel folder2Label = new JLabel("Older folder:");
        this.add(folder2Label, ConstraintsList.c3);

        folder2TextField.setEditable(false);
        this.add(folder2TextField, ConstraintsList.c4);

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
        this.add(folder1Button, ConstraintsList.c5);

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
        this.add(folder2Button, ConstraintsList.c6);

        this.add(wrapInScrollPaneAndPanel(changedFiles, "Changed Files"), ConstraintsList.c7);
        this.add(wrapInScrollPaneAndPanel(uniqueToFolder1, "Unique to Folder 1"), ConstraintsList.c8);
        this.add(wrapInScrollPaneAndPanel(uniqueToFolder2, "Unique to Folder 2"), ConstraintsList.c9);

        JButton traverseButton = new JButton("Start");
        traverseButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingWorker worker = new SwingWorker<Void, Void>() {
                    @Override
                    public Void doInBackground() {
                        clearAllOutputs();
                        traverse(directory1, directory2);
                        return null;
                    }
                };

                worker.execute();
            }
        });
        this.add(traverseButton, ConstraintsList.c10);
    }

    public void clearAllOutputs() {
        changedFiles.setText("");
        uniqueToFolder1.setText("");
        uniqueToFolder2.setText("");
    }

    public JPanel wrapInScrollPaneAndPanel(Component c, String name) {
        JScrollPane pane = new JScrollPane(c);
        JPanel panel = new JPanel(new GridLayout());
        panel.setBorder(BorderFactory.createTitledBorder(name));
        panel.add(pane);
        return panel;
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
                            uniqueToFolder1.append(file1.getPath() + " (directory)\n");
                        }
                    } else {
                        try {
                            if(!FileUtils.contentEquals(file1, file2)) {
                                changedFiles.append(file1.getPath() + " " + file2.getPath() + "differ!\n");
                            }
                        } catch (IOException e) {
                            changedFiles.append("IOException occurred!\n");
                        }
                    }
                }
                else {
                    uniqueToFolder1.append(file1.getPath() + "\n");
                }
            }

            for(File file2: directory2Files) {
                directory2Names.add(file2.getName());

            }

            directory2Names.removeAll(directory1Names);
            for(String name: directory2Names) {
                uniqueToFolder2.append(name + "\n");
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
