package com.company.app.tollBar;

import com.company.app.Application;
import com.company.app.menu.FileMenu;
import com.company.tools.Fonts;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToolBar extends JToolBar implements ActionListener {
	
	private final Application application;
	private JButton button;
	private JSpinner spinder;
	private ArrayList<JButton> jButtons = new ArrayList<>();
	private JComboBox listFonts;
	private ImageIcon icon;

	/**
	 * Creates new ToolBar object
	 */
	public ToolBar(Application application) {
		this.application = application;
		final int BAR_NUMBER = 7;
		spinder = new JSpinner();
		this.setSize(600, 40);

		for (int i = 0; i < BAR_NUMBER; i++) {
			icon = new ImageIcon("icons/1" + (i + 1) + ".png");
			button = new JButton(icon);
			jButtons.add(button);
			this.add(button);
			button.addActionListener(this);
			spinder.setMaximumSize(new Dimension(40, 30));
			spinder.setValue(20);
			spinder.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					Font font = application.getTable().get(application.getSelectedIndex()).getTextArea().getFont();
					String fontName = application.getTable().get(application.getSelectedIndex()).getTextArea().getFont()
							.getFontName();

					int fontStyle = font.getStyle();

					int value = (Integer) spinder.getValue();
					if (value == 0) {
						spinder.setValue(1);
					}
					Font newFont = new Font(fontName, fontStyle, value);
					application.getTable().get(application.getSelectedIndex()).getTextArea().setFont(newFont);
				}
			});
		}
		this.add(spinder);

		Fonts fonts = new Fonts();
		String[] fontList = fonts.getSystemFonts();

		listFonts = new JComboBox(fontList);
		listFonts.setMaximumSize(new Dimension(150, 30));
		listFonts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int fontSize = application.getTable().get(application.getSelectedIndex()).getTextArea().getFont()
						.getSize();
				int fontStyle = application.getTable().get(application.getSelectedIndex()).getTextArea().getFont()
						.getStyle();

				String fontName = (String) listFonts.getSelectedItem();
				Font newFont = new Font(fontName, fontStyle, fontSize);
				application.getTable().get(application.getSelectedIndex()).getTextArea().setFont(newFont);
			}
		});
		this.add(listFonts);
	}

	/**
	 * Method responsible for actions performed on toolbar
	 * 
	 * @param e
	 *            ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		FileMenu fileMenu = new FileMenu(null, this.application);

		if (object.equals(jButtons.get(0))) {
			fileMenu.open();
		} else if (object.equals(jButtons.get(1))) {
			fileMenu.openFile();
		} else if (object.equals(jButtons.get(2))) {
			try {
				if (application.getTable().get(application.getSelectedIndex()).getFilePath() == null) {
					String param = "saveAs";
					fileMenu.saveFile(param);
				} else {
					String param = "saveFile";
					fileMenu.saveFile(param);
				}
			} catch (IndexOutOfBoundsException a) {
				JOptionPane.showMessageDialog(null, "Make a new document first");
			}
		} else if (object.equals(jButtons.get(3))) {
			String param = "saveAs";
			try {
				fileMenu.saveFile(param);
			} catch (IndexOutOfBoundsException a) {
				JOptionPane.showMessageDialog(null, "Make a new document first");
			}
		} else if (object.equals(jButtons.get(4))) {
			fileMenu.close();
		} else if (object.equals(jButtons.get(5))) {
			fileMenu.closeAll();
		} else if (object.equals(jButtons.get(6))) {
			System.exit(0);
		}
	}
}