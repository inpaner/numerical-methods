package ui;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.*;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    private final String TITLE = "Numerical Methods";
    private final int HEIGHT = 800;
    private final int WIDTH = 600;
    private JComponent component;
    
    public MainFrame() {
        final String look = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(look);
                } 
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        setTitle(TITLE);
        setSize(HEIGHT, WIDTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

    public void closeWindow() {
        WindowEvent event = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(event);
    }

    public void setPanel(final JComponent component) {
        this.getContentPane().removeAll();
        this.component = component;
        Thread controller = new setThread();
        controller.start();
    }
    
    private class setThread extends Thread {
        @Override
        public void run() {
            SwingUtilities.invokeLater(new setInvoke());
        }
    }

    private class setInvoke implements Runnable {
        @Override
        public void run() {
            getContentPane().add(component);
            invalidate();
            validate();
        }
    }
}
