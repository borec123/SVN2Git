package cz.agel.demo.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.NumberFormatter;

import cz.agel.demo.Constants;
import cz.agel.demo.datamodel.ApplicationDataModelImpl;
import cz.agel.demo.export.ExcelExporter;
import cz.agel.demo.helper.Helper;

/**
 * GUI implementation.
 */
public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	protected static final CharSequence POSTFIX_XLS = ".xls";

	JPanel panelTable = new JPanel();
	JPanel panelActions = new JPanel();
	JTable table = new JTable();
	JLabel label = new JLabel();
	JFormattedTextField textField;
	JButton buttonGenerateExcel = new JButton();
	JButton buttonEnd = new JButton();
	JButton buttonStop = new JButton();
	ApplicationDataModelImpl tableModel = new ApplicationDataModelImpl();
	JFileChooser fileChooser = new JFileChooser();

	@Override
	protected void frameInit() {
		super.frameInit();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// setPreferredSize(new Dimension(1000, 600));
		setTitle("Demo pro Agel a.s.");
		// --velikost-----------------
		// Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		// screen.height = screen.height - 50;
		// setSize(screen);
		// pack();
	}

	private void setUIComponents() {
		initializeTextField();
		setLayout(new BorderLayout());
		panelTable.setLayout(new BorderLayout());
		panelTable.setBorder(new TitledBorder(Constants.TABLE_LABEL));
		table.setModel(tableModel);
		table.setDefaultRenderer(Object.class, new PrimeNumberCellRenderer(tableModel));
		panelTable.add(table, BorderLayout.CENTER);
		add(panelTable, BorderLayout.CENTER);
		label.setText("\u010C\u00EDsla od:");
		textField.setValue(new Integer(1));
		
		textField.setPreferredSize(new Dimension(50, textField.getPreferredSize().height));
		textField.setToolTipText("Vlo\u017E \u010D\u00EDslo a zm\u00E1\u010Dkni Enter.");
		
		buttonGenerateExcel.setText("Generovat excel");
		buttonEnd.setText("Ukon\u010Dit aplikaci");
		buttonStop.setText("Ukon\u010Dit v\u00FDpo\u010Det");
		panelActions.add(label);
		panelActions.add(textField);
		panelActions.add(buttonStop);
		panelActions.add(buttonGenerateExcel);
		panelActions.add(buttonEnd);
		add(panelActions, BorderLayout.SOUTH);
		textField.addKeyListener(new KeyAdapterTextField());
		/*textField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				textField.select(0, textField.getText().length() - 1);
			}
		});*/
		buttonEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		buttonGenerateExcel.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileChooser.showSaveDialog(Frame.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					if(!file.toString().contains(POSTFIX_XLS)) {
						file = new File(file.toString() + POSTFIX_XLS);
					}
					try {
						ExcelExporter.export(file, tableModel);
					}
					catch(Throwable ex) {
						error(ex);
					}
				}
			}
		});
		fileChooser.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return "Soubor MS Excel";
			}
			
			@Override
			public boolean accept(File f) {
				if(f.isDirectory()) {
					return true;
				}
				return f.getName().toLowerCase().contains(POSTFIX_XLS);
			}
		});
	}

	private void initializeTextField() {
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		//formatter.setMinimum(1);
		//formatter.setMaximum(PrimeNumberDetectorImpl.RANGE);
		// If you want the value to be committed on each keystroke instead of
		// focus lost
		formatter.setCommitsOnValidEdit(true);
		textField = new JFormattedTextField(formatter);
	}

	public Frame() throws HeadlessException {
		super();
		setUIComponents();
	}

	private void rangeError(Exception ex) {
		JOptionPane.showMessageDialog(this, 
				String.format("Zadan\u00E9 \u010D\u00EDslo mus\u00ED b\u00FDt mezi 1 a %d.", Helper.getMaxStartingNumber()), 
				"Chyba !",
				JOptionPane.ERROR_MESSAGE);
	}

	private void error(Throwable ex) {
		JOptionPane.showMessageDialog(this, 
				ex.toString(), 
				"Chyba !",
				JOptionPane.ERROR_MESSAGE);
	}

	class KeyAdapterTextField extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			try {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tableModel
							.setStartingNumber((Integer) textField.getValue());
				}
			} catch (IndexOutOfBoundsException ex) {
				rangeError(ex);
			}
		}
	}

}
