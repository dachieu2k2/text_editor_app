import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;

import org.w3c.dom.Text;

import java.awt.event.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.logging.*;

import java.awt.Component;

/**
 * TextEditor
 */
public class TextEditor extends JFrame implements ActionListener {
    private static JTextArea area;
    private static JFrame frame;
    private static int returnValue = 0;

    public void run() {
        frame = new JFrame("Text Edit");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            Logger.getLogger(Text.class.getName()).log(Level.SEVERE, null, e);
        }
        // Build the menu
        JMenuBar menuMain = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        JMenuItem menuItemNew = new JMenuItem("New");
        JMenuItem menuItemOpen = new JMenuItem("Open");
        JMenuItem menuItemSave = new JMenuItem("Save");
        JMenuItem menuItemQuit = new JMenuItem("Quit");

        // action listerner
        menuItemNew.addActionListener(this);
        menuItemOpen.addActionListener(this);
        menuItemSave.addActionListener(this);
        menuItemQuit.addActionListener(this);

        menuMain.add(menuFile);
        menuFile.add(menuItemNew);
        menuFile.add(menuItemOpen);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemQuit);

        frame.setJMenuBar(menuMain);

        // buil text area
        area = new JTextArea();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(area);
        frame.setSize(640, 480);
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        String ingest = null;
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose destination.");
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        String ae = e.getActionCommand();
        switch (ae) {
            case "Open": {
                returnValue = jfc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File f = new File(jfc.getSelectedFile().getAbsolutePath());
                    try {
                        FileReader read = new FileReader(f);
                        Scanner scan = new Scanner(read);
                        while (scan.hasNextLine()) {
                            String line = scan.nextLine();
                            ingest += line;
                        }
                        area.setText(ingest);
                        scan.close();
                    } catch (FileNotFoundException ex) {
                        ex.getStackTrace();
                    }
                }
                break;
            }
            case "Save": {
                returnValue = jfc.showSaveDialog(null);
                try {
                    File f = new File(jfc.getSelectedFile().getAbsolutePath());
                    FileWriter out = new FileWriter(f);
                    out.write(area.getText());
                    out.close();
                } catch (FileNotFoundException ex) {
                    Component f = null;
                    JOptionPane.showMessageDialog(f, "File not found.");

                } catch (IOException ex) {
                    Component f = null;
                    JOptionPane.showMessageDialog(f, "Error");
                }
                break;
            }
            case "New": {
                area.setText("");
                break;
            }
            case "Quit": {
                System.exit(0);
                break;
            }

            default:
                System.exit(0);
                break;
        }

    }

}
