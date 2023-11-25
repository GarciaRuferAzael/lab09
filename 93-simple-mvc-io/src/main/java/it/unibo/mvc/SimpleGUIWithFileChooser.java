package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame("2° GUI");

    private SimpleGUIWithFileChooser(final Controller ctrl){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        final JTextArea text = new JTextArea();

        final JButton save = new JButton("save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event){
                try {
                    ctrl.content(text.getText());;
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "An error occure", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(text, BorderLayout.CENTER);
        panel.add(text, BorderLayout.SOUTH);

        final JTextField filepath = new JTextField(ctrl.getCurrentFilePath());
        filepath.setEditable(false);
        final JButton choosefile = new JButton("Browse...");
        choosefile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e){
                final JFileChooser fc = new JFileChooser("Choose where to save");
                fc.setSelectedFile(ctrl.getCurrentFile());
                final int result = fc.showSaveDialog(frame);
                switch (result) {
                    case JFileChooser.APPROVE_OPTION:
                        final File newDest = fc.getSelectedFile();
                        ctrl.setDestination(newDest);
                        filepath.setText(newDest.getPath());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, result, "Mmm...", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        final JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BorderLayout());
        upperPanel.add(filepath, BorderLayout.CENTER);
        upperPanel.add(choosefile, BorderLayout.LINE_END);
        panel.add(upperPanel, BorderLayout.NORTH);

        frame.setContentPane(panel);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 4, sh / 4);
        frame.setLocationByPlatform(true);
    }

    private void display(){
        frame.setVisible(true);
    }

    /**
     * Launches the Application
     * 
     * @param args unused
     */
    public static void main(final String... a) {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.display();
    }

}
