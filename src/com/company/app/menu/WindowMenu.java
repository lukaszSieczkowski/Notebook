package com.company.app.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowMenu extends JMenu {

	private JMenu skin;
	private JMenuItem windows, nimbus, gtk, motif;

	/**
	 * Creates new WindowMenu object
	 * 
	 * @param name
	 *            WindowMenu name
	 */
	public WindowMenu(String name) {
		this.setText(name);

		skin = new JMenu("Skin");
		this.add(skin);

		windows = new JMenuItem("Windows");
		windows.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String param = windows.getText();
				setLookAndFeel(param);
				SwingUtilities.updateComponentTreeUI(getRootPane());

			}
		});
		skin.add(windows);

		nimbus = new JMenuItem("Nimbus");
		nimbus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String param = nimbus.getText();
				setLookAndFeel(param);
				SwingUtilities.updateComponentTreeUI(getRootPane());
			}
		});
		skin.add(nimbus);

		gtk = new JMenuItem("Metal");
		gtk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String param = gtk.getText();
				setLookAndFeel(param);
				SwingUtilities.updateComponentTreeUI(getRootPane());

			}
		});
		skin.add(gtk);

		motif = new JMenuItem("Motif");
		motif.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String param = motif.getText();
				setLookAndFeel(param);
				SwingUtilities.updateComponentTreeUI(getRootPane());

			}
		});
		skin.add(motif);
	}

	/**
	 * Method sets skin
	 * 
	 * @param param
	 *            skin name(Windows,Nimbus,Metal,Motif)
	 */
	public void setLookAndFeel(String param) {
		String name = null;

		if (param == "Windows") {
			name = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		} else if (param == "Nimbus") {
			name = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
		} else if (param == "Metal") {
			name = "javax.swing.plaf.metal.MetalLookAndFeel";
		} else if (param == "Motif") {
			name = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		}
		try {
			UIManager.setLookAndFeel(name);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
	}
}
