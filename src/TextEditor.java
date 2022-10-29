import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.w3c.dom.Text;

import java.awt.event.*;

import java.util.logging.*;

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

    }
}