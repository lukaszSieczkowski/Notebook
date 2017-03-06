package com.company.app.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpMenu extends JMenu {

	private JMenuItem aboutAutor;

	/**
	 * Create a new HelpMenu object
	 * 
	 * @param name
	 *            HelpMenu name
	 */
	public HelpMenu(String name) {
		this.setText(name);
		aboutAutor = new JMenuItem("About Author");
		aboutAutor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showInfo();
			}
		});
		aboutAutor.setAccelerator(KeyStroke.getKeyStroke("ctrl U"));
		this.add(aboutAutor);
	}

	/**
	 * Method shows info about author
	 */
	public void showInfo() {
		JFrame info = new JFrame();
		info.setLayout(new GridLayout(2, 1));
		info.setBounds(300, 300, 200, 200);
		info.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		info.setLocationRelativeTo(null);

		JLabel autor = new JLabel();
		autor.setHorizontalAlignment(0);
		autor.setText("Autor: Ĺ�ukasz Sieczkowski");
		info.add(autor);

		JLabel version = new JLabel();
		version.setHorizontalAlignment(0);
		version.setText("Version: 1.2");
		info.add(version);

		info.setVisible(true);
	}
}
