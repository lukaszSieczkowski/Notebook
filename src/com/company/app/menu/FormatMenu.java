package com.company.app.menu;

import com.company.app.Application;
import com.company.tools.Fonts;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormatMenu extends JMenu {

	private Application application;
	private JMenuItem plain, bold, italic;
	private JMenuItem font, style, fontSize, backGroundColor, fontColor;

	/**
	 * Create a new FormatMenu object
	 *
	 * @param name
	 *            FormatMenu name
	 */
	public FormatMenu(String name, Application application) {
		this.application = application;
		this.setText(name);
		font = new JMenuItem("Font Family");
		font.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setFont();
			}
		});
		font.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));
		this.add(font);

		style = new JMenu("Font Style");
		this.add(style);

		plain = new JMenuItem("Plain");
		plain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontType("Plain");
			}
		});
		plain.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
		style.add(plain);

		bold = new JMenuItem("Bold");
		bold.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontType("Bold");
			}
		});
		bold.setAccelerator(KeyStroke.getKeyStroke("ctrl B"));
		style.add(bold);

		italic = new JMenuItem("Italic");
		italic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontType("Italic");
			}
		});
		italic.setAccelerator(KeyStroke.getKeyStroke("ctrl T"));
		style.add(italic);

		fontSize = new JMenuItem("Font Size");
		fontSize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize();
			}
		});
		fontSize.setAccelerator(KeyStroke.getKeyStroke("ctrl I"));
		this.add(fontSize);
		this.addSeparator();

		backGroundColor = new JMenuItem("Background Color");
		backGroundColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setBackGrounColor();
			}
		});
		backGroundColor.setAccelerator(KeyStroke.getKeyStroke("ctrl K"));
		this.add(backGroundColor);

		fontColor = new JMenuItem("Font Color");
		fontColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setFontColor();
			}
		});
		fontColor.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
		this.add(fontColor);
	}

	/**
	 * Methos sets font
	 */
	public void setFont() {
		Fonts fonts = new Fonts();
		String[] fontNames = fonts.getSystemFonts();
		String input = (String) JOptionPane.showInputDialog(null, "Choose Font...", "Pick Font from the List",
				JOptionPane.QUESTION_MESSAGE, null, fontNames, fontNames[0]);
		int fontSize = application.getTable().get(application.getSelectedIndex()).getTextArea().getFont().getSize();
		Font newFont = new Font(input, Font.PLAIN, fontSize);
		application.getTable().get(application.getSelectedIndex()).getTextArea().setFont(newFont);
	}

	/**
	 * Methods sets font type
	 * 
	 * @param param
	 *            font type(Plain,Bold,Italic)
	 */
	public void setFontType(String param) {
		Font font = application.getTable().get(application.getSelectedIndex()).getTextArea().getFont();
		int fontSize = application.getTable().get(application.getSelectedIndex()).getTextArea().getFont().getSize();
		Font newFont = null;
		if (param == "Plain") {
			newFont = new Font(font.getName(), Font.PLAIN, fontSize);
		} else if (param == "Bold") {
			newFont = new Font(font.getName(), Font.BOLD, fontSize);
		} else if (param == "Italic") {
			newFont = new Font(font.getName(), Font.ITALIC, fontSize);
		}
		application.getTable().get(application.getSelectedIndex()).getTextArea().setFont(newFont);
	}

	/**
	 * Method sets font size
	 */
	public void setFontSize() {
		Integer[] fontSize = new Integer[101];
		for (int i = 0; i < 100; i++) {
			fontSize[i] = i;
		}
		int input = (Integer) JOptionPane.showInputDialog(null, "Choose Font...", "Pick Font from the List",
				JOptionPane.QUESTION_MESSAGE, null, fontSize, fontSize[0]);

		String fontName = application.getTable().get(application.getSelectedIndex()).getTextArea().getFont()
				.getFontName();
		int fontStyle = application.getTable().get(application.getSelectedIndex()).getTextArea().getFont().getStyle();

		Font newFont = new Font(fontName, fontStyle, input);
		application.getTable().get(application.getSelectedIndex()).getTextArea().setFont(newFont);
	}

	/**
	 * Method sets background color
	 */
	public void setBackGrounColor() {
		Color color = null;
		color = JColorChooser.showDialog(null, "Pick your color", color);
		application.getTable().get(application.getSelectedIndex()).getTextArea().setBackground(color);
	}

	/**
	 * Method sets font color
	 */
	public void setFontColor() {
		Color color = null;
		color = JColorChooser.showDialog(null, "Pick your color", color);
		application.getTable().get(application.getSelectedIndex()).getTextArea().setForeground(color);
	}
}
