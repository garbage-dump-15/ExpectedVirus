package net.steepout.music.badwin.ui;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class FileChooser extends JFileChooser {

	/**
	 *
	 */
	private static final long serialVersionUID = 7288421088734124819L;

	public FileChooser(FileFilter filter) {
		setFileSelectionMode(JFileChooser.FILES_ONLY);
		setFileFilter(filter);
		removeChoosableFileFilter(getAcceptAllFileFilter());
	}

}