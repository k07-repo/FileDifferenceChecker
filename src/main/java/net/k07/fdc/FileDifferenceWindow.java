package net.k07.fdc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileDifferenceWindow extends JFrame {

    private File directory1, directory2;
    public static final JTextField folder1TextField = new JTextField();
    public static final JTextField folder2TextField = new JTextField();
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
        c7.gridwidth = 6;
        c7.gridheight = 1;
        c7.fill = GridBagConstraints.HORIZONTAL;
        c7.ipadx = 0;
        c7.ipady = 0;
        c7.insets = new Insets(0, 0, 0, 0);
        c7.anchor = GridBagConstraints.CENTER;
        c7.weightx = 0.8;
        c7.weighty = 0.1;

        JButton traverseButton = new JButton("Start");
        traverseButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.traverse(directory1, directory2);
            }
        });
        this.add(traverseButton, c7);



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
}
