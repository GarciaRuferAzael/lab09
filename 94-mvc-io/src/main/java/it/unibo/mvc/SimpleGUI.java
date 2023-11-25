package it.unibo.mvc;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle.Control;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.tools.Tool;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final String PRINT = "Print";
    private static final String SHOW_HISTORY = "Show History";

    private final JFrame frame = new JFrame();
    private final Controller controller;

    /**
     * builds a new {@link SimpleGUI}.
     * @param controller the controller instance.
     */
    //@SuppressFBWarnings{
    //    value = { "EI_EXPOSE_REP2" },
    //    justification = "The controller is designed to be manipulated this way."
    //}

    public SimpleGUI(final Controller controller){
        this.controller = controller;
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());

        final JTextField textField = new JTextField();
        textField.setBackground(Color.lightGray);
        canvas.add(textField, BorderLayout.NORTH);

        final JTextArea textArea = new JTextArea();
        textArea.setEditable(true);
        canvas.add(textArea, BorderLayout.CENTER);

        final JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        canvas.add(southPanel, BorderLayout.SOUTH);

        final JButton print = new JButton(PRINT);
        final JButton showHistory = new JButton(SHOW_HISTORY);
        southPanel.add(print);
        southPanel.add(showHistory);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         * Handlers
         */
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event){
                SimpleGUI.this.controller.setNextStringToPrint(textField.getText());
                SimpleGUI.this.controller.printCurrentString();
            }
        });

        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event){
                final StringBuilder text = new StringBuilder();
                final List<String> history = SimpleGUI.this.controller.getPrintedStringsHistory();
                for (final String print: history) {
                    text.append(print);
                    text.append('\n');
                }
                if (!history.isEmpty()) {
                    text.deleteCharAt(text.length() - 1);
                }
                textArea.setText(text.toString());
            }
        });

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);

        frame.setLocationByPlatform(true);
    }

    private void display(){
        frame.setVisible(true);
    }

    public static void main(final String... a) {
        new SimpleGUI(new SimpleController()).display();
    }

}
