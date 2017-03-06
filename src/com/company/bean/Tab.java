package com.company.bean;

import com.company.app.Application;
import com.company.app.menu.PopUpMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tab extends JScrollPane implements MouseListener {

	private final Application application;
	private JTextPane textArea;
	private String name;
	private String filePath;

	/**
	 * Creates new Tab object
	 */
	public Tab(Application application) {
		this.application = application;
		Font font = new Font("Arial", Font.PLAIN, 20);
		textArea = new JTextPane();
		textArea.setFont(font);
		textArea.addMouseListener(this);
	}

	/**
	 * Method gets JTextPane
	 *
	 * @return JTextPane
	 */
	public JTextPane getTextArea() {
		return textArea;
	}

	/**
	 * Method gets Name
	 *
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method sets Name
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method gets filepath
	 *
	 * @return filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Method sets filepath
	 *
	 * @param filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override

	/**
	 * Method responsible for actions performed during right mouse button click
	 */
	public void mouseReleased(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			JPopupMenu popUpMenu = new PopUpMenu(this.application);
			popUpMenu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
