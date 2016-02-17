package gui;

import controller.Controller;
import gui.shift.ShiftPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import model.DairySettings;
import model.Database;

public class MainFrame extends JFrame implements ComponentListener {

	private final JPanel contentPanel;
	private final ShiftPanel shiftPanel;
	private final StartPanel startPanel;
	private final Controller controller;
	private JFileChooser fileChooser;
	private final DairySettings prefs;
	private final Toolbar toolbar;
	private MonthYearDialog monthYearDialog;
	private boolean continueImport;
	private ImportFilter importFilter;

	// Constants for CardLayout panels
	final static String STARTPANEL = "Start";
	final static String SHIFTPANEL = "Shift";
	final static String REGISTERPANEL = "Register";
	final static String INVOICEPANEL = "Invoice";

	public MainFrame() throws Exception {
		super("Dairy Palace of Canton - DairyBooks v3");

		// Check that database exists
		File f = new File("dairydb");	
		if (!f.exists()) {
			Database.init();
		}
			
		prefs = new DairySettings();

		controller = new Controller();

		setLayout(new BorderLayout());

		toolbar = new Toolbar();

		contentPanel = new JPanel(new CardLayout());
		contentPanel.addComponentListener(this);

		startPanel = new StartPanel();

		shiftPanel = new ShiftPanel();

		contentPanel.add(startPanel, STARTPANEL);
		contentPanel.add(shiftPanel, SHIFTPANEL);

		setJMenuBar(createMenuBar());

		toolbar.setToolbarListener((String name1) -> {
			setVisiblePanel(name1);
		});

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				System.gc();
			}
		});

		add(toolbar, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);

		setSize(prefs.getMainFrameSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		setVisiblePanel("Start");
	}

	public ImportFilter getImportFilter() {
		return this.importFilter;
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");

		/**
		 * ******* File Menu *********
		 */
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		/**
		 * ******* Add File Menu Action Listeners *********
		 */
		exitItem.addActionListener((ActionEvent e) -> {
			int action = JOptionPane.showConfirmDialog(
					MainFrame.this, "Do you really want to exit the application?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
			if (action == JOptionPane.OK_OPTION) {
				WindowListener[] listeners = getWindowListeners();
				
				for (WindowListener listener : listeners) {
					listener.windowClosing(new WindowEvent(MainFrame.this, 0));
				}
			}
		});

		/**
		 * ******* View Menu *********
		 */
		JMenu viewMenu = new JMenu("View Data");
		JMenuItem shiftViewItem = new JMenuItem("Shift Data");
		JMenuItem registerViewItem = new JMenuItem("Register Audit");
		JMenuItem invoiceViewItem = new JMenuItem("Invoice");
		JMenuItem inventoryViewItem = new JMenuItem("Inventory");
		viewMenu.add(shiftViewItem);
		viewMenu.add(registerViewItem);
		viewMenu.add(invoiceViewItem);
		viewMenu.add(inventoryViewItem);

		/**
		 * ******* Add View Menu Action Listeners *********
		 */
		shiftViewItem.addActionListener((ActionEvent e) -> {
			System.out.println("View Shift Data");
		});

		registerViewItem.addActionListener((ActionEvent e) -> {
			System.out.println("View Register Data");
		});

		invoiceViewItem.addActionListener((ActionEvent e) -> {
			System.out.println("View Invoice Data");
		});

		inventoryViewItem.addActionListener((ActionEvent e) -> {
			System.out.println("View Inventory Data");
		});

		/**
		 * ******* Data Entry Menu *********
		 */
		JMenu enterMenu = new JMenu("Enter Data");
		JMenuItem shiftEnterItem = new JMenuItem("Shift Data");
		JMenuItem registerEnterItem = new JMenuItem("Register Justification");
		JMenuItem miscInfoEnterItem = new JMenuItem("Miscellaneous Info");
		JMenuItem invoiceEnterItem = new JMenuItem("Invoice");
		JMenuItem inventoryEnterItem = new JMenuItem("Inventory");
		enterMenu.add(shiftEnterItem);
		enterMenu.add(registerEnterItem);
		enterMenu.add(miscInfoEnterItem);
		enterMenu.add(invoiceEnterItem);
		enterMenu.add(inventoryEnterItem);

		/**
		 * ******* Add Enter Menu Action Listeners *********
		 */
		shiftEnterItem.addActionListener((ActionEvent e) -> {
			System.out.println("Enter Shift Data");
		});

		registerEnterItem.addActionListener((ActionEvent e) -> {
			System.out.println("Enter Register Data");
		});

		miscInfoEnterItem.addActionListener((ActionEvent e) -> {
			System.out.println("Enter Misc Info Data");
		});

		invoiceEnterItem.addActionListener((ActionEvent e) -> {
			System.out.println("Enter Invoice Data");
		});

		inventoryEnterItem.addActionListener((ActionEvent e) -> {
			System.out.println("Enter Inventory Data");
		});

		/**
		 * ******* Report Menu *********
		 */
		JMenu reportMenu = new JMenu("Reports");
		JMenuItem registerReportItem = new JMenuItem("Register Audit");
		JMenuItem eodReportItem = new JMenuItem("End of Day ");
		JMenuItem eowReportItem = new JMenuItem("End of Week");

		JMenu monthlyReportMenu = new JMenu("Monthly Reports");
		JMenuItem monthlyAveragesReportItem = new JMenuItem("Monthly Averages");
		JMenuItem compareMonthsReportItem = new JMenuItem("Compare two months");
		JMenuItem compareMonthlyAveragesReportItem = new JMenuItem("Compare monthly averages");
		JMenuItem monthlyAveragesWPastReportItem = new JMenuItem("Monthly averages w/ Past Performance");
		monthlyReportMenu.add(monthlyAveragesReportItem);
		monthlyReportMenu.add(compareMonthsReportItem);
		monthlyReportMenu.add(compareMonthlyAveragesReportItem);
		monthlyReportMenu.add(monthlyAveragesWPastReportItem);

		/**
		 * ******* Add Monthly Reports Sub-menu Action Listeners *********
		 */
		monthlyAveragesReportItem.addActionListener((ActionEvent e) -> {
			System.out.println("Monthly Averages Report");
		});

		compareMonthsReportItem.addActionListener((ActionEvent e) -> {
			System.out.println("Compare Months Report");
		});

		compareMonthlyAveragesReportItem.addActionListener((ActionEvent e) -> {
			System.out.println("Compare Monthly Averages Report");
		});

		monthlyAveragesWPastReportItem.addActionListener((ActionEvent e) -> {
			System.out.println("Compare Monthly Averages with Past Report");
		});

		JMenuItem ytdReportItem = new JMenuItem("Year to Date");

		JMenu vendorReportMenu = new JMenu("Vendor Reports");
		JMenuItem vendorTotalReportItem = new JMenuItem("Vendor Totals");
		JMenuItem vendorComparisonReportItem = new JMenuItem("Vendor Comparisons");
		vendorReportMenu.add(vendorTotalReportItem);
		vendorReportMenu.add(vendorComparisonReportItem);

		/**
		 * ******* Add Vendor Reports Submenu Action Listeners *********
		 */
		vendorTotalReportItem.addActionListener((ActionEvent e) -> {
			System.out.println("Vendor Totals Report");
		});

		vendorComparisonReportItem.addActionListener((ActionEvent e) -> {
			System.out.println("Vendor Comparison Report");
		});

		JMenu inventoryReportMenu = new JMenu("Inventory Reports");
		JMenuItem inventoryReportItem = new JMenuItem("Inventory");
		JMenuItem blankInventoryReportItem = new JMenuItem("Blank Inventory Form");
		inventoryReportMenu.add(inventoryReportItem);
		inventoryReportMenu.add(blankInventoryReportItem);

		/**
		 * ******* Add Inventory Reports Sub-menu Action Listeners *********
		 */
		inventoryReportItem.addActionListener((ActionEvent e) -> {
			System.out.println("Inventory Report");
		});

		blankInventoryReportItem.addActionListener((ActionEvent e) -> {
			System.out.println("Blank Inventory Report");
		});

		JMenuItem compareReportItem = new JMenuItem("Comparisons");
		reportMenu.add(registerReportItem);
		reportMenu.add(eodReportItem);
		reportMenu.add(eowReportItem);
		reportMenu.add(monthlyReportMenu);
		reportMenu.add(ytdReportItem);
		reportMenu.add(vendorReportMenu);
		reportMenu.add(inventoryReportMenu);
		reportMenu.add(compareReportItem);

		/**
		 * ******* Add Reports Menu Action Listeners *********
		 */
		registerReportItem.addActionListener((ActionEvent e) -> {
			System.out.println("Register Report");
		});

		eodReportItem.addActionListener((ActionEvent e) -> {
			System.out.println("End of Day Report");
		});

		eowReportItem.addActionListener((ActionEvent e) -> {
			System.out.println("End of Week Report");
		});

		ytdReportItem.addActionListener((ActionEvent e) -> {
			System.out.println("Year to Date Report");
		});

		compareReportItem.addActionListener((ActionEvent e) -> {
			System.out.println("Comparison Report");
		});

		/**
		 * ******* Utilities Menu *********
		 */
		JMenu utilitiesMenu = new JMenu("Utilities");
		JMenuItem configurationItem = new JMenuItem("Configuration");
		JMenuItem employeeListItem = new JMenuItem("Employee List Maintenance");
		JMenuItem vendorListItem = new JMenuItem("Vendor List Maintenance");
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem userManageItem = new JMenuItem("User Management...");
		JMenuItem initDatabaseItem = new JMenuItem("Initialize Database");
		utilitiesMenu.add(configurationItem);
		utilitiesMenu.add(employeeListItem);
		utilitiesMenu.add(vendorListItem);
		utilitiesMenu.add(exportDataItem);
		utilitiesMenu.add(importDataItem);
		utilitiesMenu.add(userManageItem);
		utilitiesMenu.add(initDatabaseItem);

		/**
		 * ******* Add Utilities Menu Action Listeners *********
		 */
		configurationItem.addActionListener((ActionEvent e) -> {
			System.out.println("Configuration");
		});

		employeeListItem.addActionListener((ActionEvent e) -> {
			System.out.println("Employee List Maintenance");
		});

		vendorListItem.addActionListener((ActionEvent e) -> {
			System.out.println("Vendor List Maintenance");
		});

		exportDataItem.addActionListener((ActionEvent e) -> {
			System.out.println("Export Data");
		});

		importDataItem.addActionListener((ActionEvent e) -> {
			DbfFileFilter filter = new DbfFileFilter();
			fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(prefs.getImportDirectory()));
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
				try {
					prefs.setImportDirectory(fileChooser.getSelectedFile().toString());
				} catch (SQLException ex) {
					Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
				} catch (ClassNotFoundException ex) {
					Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
				}
				try {
					// Flag for whether to procede with import
					continueImport = false;
					
					// Prompt for month and year to go back to
					monthYearDialog = new MonthYearDialog(MainFrame.this, prefs.getImportMonth(), prefs.getImportYear());
					
					monthYearDialog.setMonthYearListener((int month, int year) -> {
						if (monthYearDialog.validateFields()) {
							try {
								MainFrame.this.prefs.setImportMonth(month);
							} catch (SQLException ex) {
								Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
							} catch (ClassNotFoundException ex) {
								Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
							}
							try {
								MainFrame.this.prefs.setImportYear(year);
							} catch (SQLException ex) {
								Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
							} catch (ClassNotFoundException ex) {
								Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
							}
							
							monthYearDialog.setVisible(false);
							continueImport = true;
							
						} else {
							JOptionPane.showMessageDialog(
									MainFrame.this,
									"Please enter a valid month (1-12) and year",
									"Invalid Entry",
									JOptionPane.ERROR_MESSAGE);
						}
					});
					monthYearDialog.setVisible(true);
					
					if (continueImport) {
						MainFrame.this.importFilter =
								new ImportFilter(MainFrame.this.prefs.getImportMonth(), MainFrame.this.prefs.getImportYear());
						File selectedFile = fileChooser.getSelectedFile();
						controller.importDbf(selectedFile.listFiles(filter), MainFrame.this.importFilter);
					}
				} catch (Exception ex) {
					String msg = ex.getMessage();
					JOptionPane.showMessageDialog(
							MainFrame.this,
							"There was an error importing the data:\r\n" + msg,
							"Import Error",
							JOptionPane.ERROR_MESSAGE);
					Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));

		userManageItem.addActionListener((ActionEvent e) -> {
			System.out.println("User Management");
		});

		initDatabaseItem.addActionListener((ActionEvent e) -> {
			try {
				if (Database.init()) {
					JOptionPane.showMessageDialog(MainFrame.this, "Database was successfully initialized");
				}
			} catch (SQLException ex) {
				String msg = ex.getMessage();
				JOptionPane.showMessageDialog(
						MainFrame.this,
						"There was an error initializing the database:\r\n" + msg,
						"Initializing Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (ClassNotFoundException ex) {
				String msg = ex.getMessage();
				JOptionPane.showMessageDialog(
						MainFrame.this,
						"There was an error initializing the database:\r\n" + msg,
						"Initializing Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		menuBar.add(fileMenu);
		menuBar.add(viewMenu);
		menuBar.add(enterMenu);
		menuBar.add(reportMenu);
		menuBar.add(utilitiesMenu);

		return menuBar;
	}

	private void setVisiblePanel(String name) {
		// Names are defined as static string constants at MainFrame declaration
		CardLayout cl = (CardLayout) (contentPanel.getLayout());
		cl.show(contentPanel, name);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		String componentName = e.getComponent().getName();
		String parentName = e.getComponent().getParent().getName();
		Rectangle bounds = e.getComponent().getParent().getBounds();
		Dimension size = e.getComponent().getParent().getBounds().getSize();

		try {
			prefs.setMainFrameSize(e.getComponent().getParent().getParent().getBounds().getSize());
		} catch (SQLException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	public Dimension getMainPanelSize() {
		return this.getPreferredSize();
	}
}
