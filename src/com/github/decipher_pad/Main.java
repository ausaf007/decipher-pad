package com.github.decipher_pad;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import static com.github.decipher_pad.FileEncryptor.*;

public class Main extends JFrame implements ActionListener {

    JTextArea textarea;
    JScrollPane pane;
    String text;
    int textsize = 20;

    Main() {

        // Main Res
        setBounds(0, 0, 1920, 1080);

        // MenuBar
        JMenuBar menubar = new JMenuBar();

        // File Menu with Sub-items
        JMenu file = new JMenu("File");

        JMenuItem newdoc = new JMenuItem("New");
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newdoc.addActionListener(this);

        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);

        JMenuItem decrypt = new JMenuItem("Decrypt");
        decrypt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        decrypt.addActionListener(this);

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);

        JMenuItem encryptAndSave = new JMenuItem("Encrypt and Save");
        encryptAndSave
                .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.SHIFT_MASK + InputEvent.CTRL_MASK));
        encryptAndSave.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        exit.addActionListener(this);

        file.add(newdoc);
        file.add(open);
        file.add(decrypt);
        file.add(save);
        file.add(encryptAndSave);
        file.add(exit);

        // Edit Menu with Sub-items
        JMenu file1 = new JMenu("Edit");

        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);

        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);

        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);

        JMenuItem select = new JMenuItem("Select All");
        select.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        select.addActionListener(this);

        file1.add(copy);
        file1.add(cut);
        file1.add(paste);
        file1.add(select);

        // View Menu with Sub-items
        JMenu file21 = new JMenu("View");

        JMenuItem zoomin = new JMenuItem("Zoom In");
        zoomin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, ActionEvent.CTRL_MASK));
        zoomin.addActionListener(this);

        JMenuItem zoomout = new JMenuItem("Zoom Out");
        zoomout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, ActionEvent.CTRL_MASK));
        zoomout.addActionListener(this);

        file21.add(zoomin);
        file21.add(zoomout);

        // About Menu with Sub-items
        JMenu file2 = new JMenu("About");

        JMenuItem about = new JMenuItem("Info");
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        about.addActionListener(this);

        file2.add(about);

        // Adding all the menus to menu bar
        menubar.add(file);
        menubar.add(file1);
        menubar.add(file21);
        menubar.add(file2);

        // Setting JFrame MenuBar as JMenuBar
        setJMenuBar(menubar);

        // Text Area Config
        textarea = new JTextArea();
        pane = new JScrollPane(textarea);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane, BorderLayout.CENTER);

        textarea.setFont(new Font("SAN_SERIF", Font.PLAIN, textsize));
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });

    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getActionCommand().equals("Decrypt")) {

            JFileChooser openfile = new JFileChooser("/");

            openfile.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .encrypted files", "encrypted");
            openfile.setApproveButtonText("Decrypt");
            openfile.addChoosableFileFilter(restrict);
            int action = openfile.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;

            }

            File filename = openfile.getSelectedFile();
            String password = JOptionPane.showInputDialog(this, "Enter Password:");

            try {
                // Decrypting the selected file
                decryptFile(openfile.getSelectedFile() + "", password);
                JOptionPane.showMessageDialog(this, "The file was Decrypted Successfully.");

                // Opening the Decrypted file
                String fileName = filename + "";
                BufferedReader reader = new BufferedReader(
                        new FileReader(fileName.substring(0, fileName.lastIndexOf('.'))));
                textarea.read(reader, null);

            } catch (Exception e) {
                // Wrong Password Entered
                JOptionPane.showMessageDialog(this, "Wrong Password Entered.", "Warning!", JOptionPane.WARNING_MESSAGE);

            }

        } else {
            if (ae.getActionCommand().equals("Encrypt and Save")) {

                JFileChooser saveas = new JFileChooser();

                saveas.setApproveButtonText("Encrypt and Save");
                int action = saveas.showOpenDialog(this);

                if (action != JFileChooser.APPROVE_OPTION) {
                    return;
                }

                File filename = new File(saveas.getSelectedFile() + ".txt");

                BufferedWriter writer = null;

                try {
                    writer = new BufferedWriter(new FileWriter(filename));
                    textarea.write(writer);
                    writer.close();

                } catch (Exception e) {
                    // TODO: handle exception
                }

                try {
                    // Encrypting Plaintext file to Encrypted file
                    String password = JOptionPane.showInputDialog(this, "Enter a new Password");
                    encryptFile(saveas.getSelectedFile() + ".txt", password);
                    JOptionPane.showMessageDialog(this, "The file was Encrypted Successfully.");

                    // Deleting Plain Text file
                    filename.delete();
                    textarea.setText("");

                } catch (Exception e) {
                }

            } else {
                if (ae.getActionCommand().equals("New")) {
                    textarea.setText("");

                } else {
                    if (ae.getActionCommand().equals("Open")) {

                        JFileChooser openfile = new JFileChooser("/");

                        openfile.setAcceptAllFileFilterUsed(false);
                        FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
                        openfile.setApproveButtonText("Open");
                        openfile.addChoosableFileFilter(restrict);
                        int action = openfile.showOpenDialog(this);
                        if (action != JFileChooser.APPROVE_OPTION) {
                            return;

                        }

                        File filename = openfile.getSelectedFile();

                        try {
                            BufferedReader reader = new BufferedReader(new FileReader(filename));

                            textarea.read(reader, null);
                        } catch (Exception e) {
                        }

                    } else {
                        if (ae.getActionCommand().equals("Save")) {

                            JFileChooser saveas = new JFileChooser();
                            saveas.setApproveButtonText("Save");
                            int action = saveas.showOpenDialog(this);
                            if (action != JFileChooser.APPROVE_OPTION) {
                                return;

                            }

                            File filename = new File(saveas.getSelectedFile() + ".txt");
                            BufferedWriter writer = null;

                            try {
                                writer = new BufferedWriter(new FileWriter(filename));
                                textarea.write(writer);
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                        } else {

                            if (ae.getActionCommand().equals("Exit")) {
                                System.exit(0);
                            } else {
                                if (ae.getActionCommand().equals("Copy")) {
                                    text = textarea.getSelectedText();
                                } else {
                                    if (ae.getActionCommand().equals("Paste")) {
                                        textarea.insert(text, textarea.getCaretPosition());
                                    } else {
                                        if (ae.getActionCommand().equals("Cut")) {
                                            text = textarea.getSelectedText();
                                            textarea.replaceRange("", textarea.getSelectionStart(),
                                                    textarea.getSelectionEnd());
                                        } else {
                                            if (ae.getActionCommand().equals("Select All")) {
                                                textarea.selectAll();
                                            } else {

                                                if (ae.getActionCommand().equals("Select All")) {
                                                    textarea.selectAll();
                                                } else {

                                                    if (ae.getActionCommand().equals("Zoom In")) {
                                                        textsize += 5;
                                                        textarea.setFont(new Font("SAN_SERIF", Font.PLAIN, textsize));
                                                    } else {
                                                        if (ae.getActionCommand().equals("Zoom Out")) {
                                                            textsize -= 5;
                                                            textarea.setFont(
                                                                    new Font("SAN_SERIF", Font.PLAIN, textsize));
                                                        } else {
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }

            }

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new Main().setVisible(true);
        // TODO code application logic here
    }

}

