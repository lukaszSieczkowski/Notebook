package com.company.app.menu;

import com.company.app.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditMenu extends JMenu {

	private Application application;
	private JMenuItem cut, copy, paste, delete;

	/**
	 * Creates a new EditMenu object
	 */
	public EditMenu() {

	}

	/**
	 * Creates a new EditMenu object
	 * 
	 * @param name
	 *            EditMenu name
	 */
	public EditMenu(String name, Application application) {
		this.application = application;
		this.setText(name);
		cut = new JMenuItem("Cut");
		cut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cut();
			}
		});
		cut.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
		this.add(cut);

		copy = new JMenuItem("Copy");
		copy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				copy();
			}
		});
		copy.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
		this.add(copy);

		paste = new JMenuItem("Paste");
		paste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paste();
			}
		});
		paste.setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
		this.add(paste);

		this.addSeparator();

		delete = new JMenuItem("Delete");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		delete.setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
		this.add(delete);

	}

	/**
	 * Method cuts a part of selected text
	 */
	public void cut() {
		application.getTable().get(application.getSelectedIndex()).getTextArea().cut();
	}

	/**
	 * Method paste a part of selected text
	 */
	public void paste() {
		application.getTable().get(application.getSelectedIndex()).getTextArea().paste();
	}

	/**
	 * Method copy a part of selected text
	 */
	public void copy() {

		application.getTable().get(application.getSelectedIndex()).getTextArea().copy();
	}

	/**
	 * Method copy a part of selected text
	 */

	public void delete() {
		String text;
		String selectedText;
		String newText;
		int startIndex;
		int length;
		int endIndex;

		text = application.getTable().get(application.getSelectedIndex()).getTextArea().getText();
		selectedText = application.getTable().get(application.getSelectedIndex()).getTextArea().getSelectedText();
		StringBuilder stringBuilder = new StringBuilder(text);

		startIndex = stringBuilder.indexOf(selectedText);
		length = selectedText.length();
		endIndex = startIndex + length;

		stringBuilder.delete(startIndex, endIndex);
		newText = stringBuilder.toString();
		application.getTable().get(application.getSelectedIndex()).getTextArea().setText(newText);

	}
}
