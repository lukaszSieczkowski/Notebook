package com.company.app;

import com.company.app.menu.*;
import com.company.app.tollBar.ToolBar;
import com.company.bean.Tab;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class Application extends JFrame {

	private int selectedIndex;
	private ArrayList<Tab> table = new ArrayList<>();
	private static JTabbedPane jTabbedPane;

	private JPanel contentPane;
	private JMenuBar menu;
	private JMenu fileMenu, editMenu, formatMenu, windowMenu, helpMenu;
	private JToolBar toolBar;

	/**
	 * Create new Application object
	 */
	public Application() {

		this.setTitle("Notebook");
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		menu = new JMenuBar();
		fileMenu = new FileMenu("Plik", this);
		menu.add(fileMenu);

		editMenu = new EditMenu("Edit", this);
		menu.add(editMenu);

		formatMenu = new FormatMenu("Format", this);
		menu.add(formatMenu);

		windowMenu = new WindowMenu("Window");
		menu.add(windowMenu);

		menu.add(Box.createHorizontalGlue());

		helpMenu = new HelpMenu("Help");
		menu.add(helpMenu);

		this.setJMenuBar(menu);

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		toolBar = new ToolBar(this);
		contentPane.add(toolBar, BorderLayout.NORTH);

		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		jTabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {

				Application.this.setSelectedIndex(jTabbedPane.getSelectedIndex());
			}
		});
		this.getContentPane().add(jTabbedPane, BorderLayout.CENTER);

		FileMenu menu = new FileMenu(null, this);
		menu.open();

		this.setVisible(true);
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public ArrayList<Tab> getTable() {
		return table;
	}

	public JTabbedPane getjTabbedPane() {
		return jTabbedPane;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
}