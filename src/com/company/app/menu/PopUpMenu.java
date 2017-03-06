package com.company.app.menu;

import com.company.app.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpMenu extends JPopupMenu {

	private final Application application;

	/**
	 * Creates new PopUpMenu Object
	 */
	public PopUpMenu(Application application) {
		this.application = application;
		EditMenu editMenu = new EditMenu("Edit", this.application);

		JMenuItem cut = new JMenuItem("Cut");
		cut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editMenu.cut();
			}
		});
		this.add(cut);

		JMenuItem copy = new JMenuItem("Copy");
		copy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editMenu.copy();
			}
		});
		this.add(copy);

		JMenuItem paste = new JMenuItem("Paste");
		paste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editMenu.paste();
			}
		});
		this.add(paste);

		this.addSeparator();

		JMenuItem delete = new JMenuItem("Delete");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editMenu.delete();
			}
		});
		this.add(delete);
	}
}
