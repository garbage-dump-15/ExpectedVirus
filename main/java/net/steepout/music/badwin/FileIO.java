package net.steepout.music.badwin;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import net.steepout.music.badwin.ui.FileChooser;

public class FileIO {

	static File lastDirectory = null;

	public static Sequence loadSequence(File f) throws InvalidMidiDataException, IOException {
		return MidiSystem.getSequence(f);
	}

	public static File popupFileChooser(FileFilter filter, Component parent) throws NullPointerException {
		JFileChooser chooser = new FileChooser(filter);
		if (FileIO.lastDirectory != null)
			chooser.setCurrentDirectory(FileIO.lastDirectory);
		int code = chooser.showOpenDialog(parent);
		if (code != JFileChooser.APPROVE_OPTION)
			throw new NullPointerException("File Selection Failed");
		FileIO.lastDirectory = chooser.getCurrentDirectory();
		return chooser.getSelectedFile();
	}
}
