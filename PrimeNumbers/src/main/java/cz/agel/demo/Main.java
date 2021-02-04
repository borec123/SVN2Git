package cz.agel.demo;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import cz.agel.demo.gui.Frame;

public class Main {
	public static void main(String[] args) {
		// schedule this for the event dispatch thread (edt)
		
		char ch = '\u039A';
	      System.out.println(ch);
	      
	      
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				displayJFrame();
			}
		});
	}

	static void displayJFrame() {
		setLookAndFeel();
		Frame frame = new Frame();
		frame.pack();
		frame.setVisible(true);
	}

	private static void setLookAndFeel() {
		
		/*
		 * try { UIManager
		 * .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); }
		 * catch (Exception e) { JOptionPane.showMessageDialog(null, e.toString(),
		 * "Chyba !", JOptionPane.ERROR_MESSAGE); }
		 */		
	}
}