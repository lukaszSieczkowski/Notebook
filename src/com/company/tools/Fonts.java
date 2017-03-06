package com.company.tools;

import java.awt.*;

public class Fonts {
	/**
	 * Method get all fonts installed in system
	 * 
	 * @return fonts array
	 */
	public String[] getSystemFonts() {
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font[] fonts = e.getAllFonts();
		int tabSize = fonts.length;
		String[] fontsNames = new String[fonts.length];
		for (int i = 0; i < tabSize; i++) {
			fontsNames[i] = fonts[i].getFontName();
		}
		return fontsNames;
	}
}
