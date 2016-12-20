package net.steepout.music.badwin;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.steepout.music.badwin.ui.MemoryReadPopup;
import net.steepout.music.badwin.ui.NVIDIAPopup;
import net.steepout.music.badwin.ui.NonResponsePopup;
import net.steepout.music.badwin.ui.OOMPopup;
import net.steepout.music.badwin.ui.ScriptEditUI;
import net.steepout.music.badwin.ui.Style7Popup;
import net.steepout.music.badwin.ui.VCErrorPopup;

public class ListenerCallback extends MidiListener {

	static FileFilter filter = new FileNameExtensionFilter("MIDI Musical Instrument Data Interface (.mid)", "mid");

	static FileFilter iconFilter = new FileNameExtensionFilter("Image File (.png,.jpg,.gif)", "png", "jpg", "gif");

	static int mode;

	static int note;

	static int channel;

	static int posx = 0;

	static int posy = 0;

	static Dimension screen;

	static Random random = new Random();

	static boolean useScript = false;

	public static String defaultScript = "if(channel==0 && note=='C5')\n" + "   return 1;\n" + "else\n"
			+ "   return 0;\n";

	static ScriptEngine engine;

	public static long slpGlobal = 600;

	public static void onTriggerFileSelection() {
		File f;
		try {
			f = FileIO.popupFileChooser(ListenerCallback.filter, BadWindows.mainFrame);
		} catch (NullPointerException e) {
			BadWindows.mainFrame.clearList();
			BadWindows.mainFrame.appendListItem("File Selection Cancelled ...");
			return;
		}
		BadWindows.mainFrame.clearList();
		BadWindows.mainFrame.appendListItem("Opening '" + f.getAbsolutePath() + "'");
		Sequence sequence = null;
		try {
			sequence = FileIO.loadSequence(f);
		} catch (InvalidMidiDataException | IOException e) {
			BadWindows.mainFrame.appendListItem("Failed to load midi sequence : " + e.toString());
			return;
		}
		BadWindows.globalSequence = sequence;
		BadWindows.mainFrame.appendListItem("-- MIDI Sequence Info --");
		BadWindows.mainFrame.appendListItem("Track(s) : " + sequence.getTracks().length);
		BadWindows.mainFrame.appendListItem("Resolution : " + sequence.getResolution());
		BadWindows.mainFrame.appendListItem("Length : " + sequence.getTickLength());
		BadWindows.mainFrame.appendListItem("Division Type : " + sequence.getDivisionType());
		BadWindows.mainFrame.fileName.setText(f.getAbsolutePath());
	}

	public static void openFileManually(File f) {
		BadWindows.mainFrame.clearList();
		BadWindows.mainFrame.appendListItem("Opening '" + f.getAbsolutePath() + "'");
		Sequence sequence = null;
		try {
			sequence = FileIO.loadSequence(f);
		} catch (InvalidMidiDataException | IOException e) {
			BadWindows.mainFrame.appendListItem("Failed to load midi sequence : " + e.toString());
			return;
		}
		BadWindows.globalSequence = sequence;
		BadWindows.mainFrame.appendListItem("-- MIDI Sequence Info --");
		BadWindows.mainFrame.appendListItem("Track(s) : " + sequence.getTracks().length);
		BadWindows.mainFrame.appendListItem("Resolution : " + sequence.getResolution());
		BadWindows.mainFrame.appendListItem("Length : " + sequence.getTickLength());
		BadWindows.mainFrame.appendListItem("Division Type : " + sequence.getDivisionType());
		BadWindows.mainFrame.fileName.setText(f.getAbsolutePath());
	}

	public static void onTriggerIconSelection() {
		File f;
		try {
			f = FileIO.popupFileChooser(ListenerCallback.iconFilter, BadWindows.mainFrame);
		} catch (NullPointerException e) {
			return;
		}
		if (f.isFile()) {
			BadWindows.mainFrame.txtDefault.setText(f.getAbsolutePath());
		}
	}

	public static void onTriggerPreview() {
		if (BadWindows.mainFrame.txtDefault.getText().equalsIgnoreCase("default")) {
			Style7Popup.custom = null;
		} else {
			try {
				Style7Popup.custom = new ImageIcon(new File(BadWindows.mainFrame.txtDefault.getText()).toURI().toURL());
			} catch (MalformedURLException e) {
				Style7Popup.custom = null;
			}
		}
		Style7Popup.application = BadWindows.mainFrame.txtCrashTitle.getText();
		JDialog dialog = null;
		if (BadWindows.mainFrame.Style7.isSelected()) {
			dialog = new Style7Popup();
		} else if (BadWindows.mainFrame.StyleOOM.isSelected()) {
			dialog = new OOMPopup();
		} else if (BadWindows.mainFrame.styleCrash.isSelected()) {
			dialog = new MemoryReadPopup();
		} else if (BadWindows.mainFrame.styleNvidia.isSelected()) {
			dialog = new NVIDIAPopup();
		} else if (BadWindows.mainFrame.styleNonResponse.isSelected()) {
			dialog = new NonResponsePopup();
		} else if (BadWindows.mainFrame.styleVC.isSelected()) {
			dialog = new VCErrorPopup();
		}
		dialog.setModal(true);
		dialog.setVisible(true);
	}

	public static void onTriggerStart() {
		// FIXME check in-need
		if (BadWindows.mainFrame.txtDefault.getText().equalsIgnoreCase("default")) {
			Style7Popup.custom = null;
		} else {
			try {
				Style7Popup.custom = new ImageIcon(new File(BadWindows.mainFrame.txtDefault.getText()).toURI().toURL());
			} catch (MalformedURLException e) {
				Style7Popup.custom = null;
			}
		}
		Style7Popup.application = BadWindows.mainFrame.txtCrashTitle.getText();
		ListenerCallback.mode = BadWindows.mainFrame.Style7.isSelected() ? 1 : 0;
		ListenerCallback.mode = BadWindows.mainFrame.StyleOOM.isSelected() ? 2 : ListenerCallback.mode;
		ListenerCallback.mode = BadWindows.mainFrame.styleCrash.isSelected() ? 3 : ListenerCallback.mode;
		ListenerCallback.mode = BadWindows.mainFrame.styleNvidia.isSelected() ? 4 : ListenerCallback.mode;
		ListenerCallback.mode = BadWindows.mainFrame.styleNonResponse.isSelected() ? 5 : ListenerCallback.mode;
		ListenerCallback.mode = BadWindows.mainFrame.styleVC.isSelected() ? 6 : ListenerCallback.mode;
		ListenerCallback.useScript = BadWindows.mainFrame.chkScript.isSelected();
		ListenerCallback.note = BadWindows.mainFrame.getNote();
		ListenerCallback.channel = Integer.parseInt(BadWindows.mainFrame.spinner.getModel().getValue().toString());
		ListenerCallback.screen = Toolkit.getDefaultToolkit().getScreenSize();
		ListenerCallback.slpGlobal = Long.parseLong(BadWindows.mainFrame.aliveTime.getText());
		ListenerCallback.randomPos();
		EventQueue.invokeLater(() -> {
			SystemTray tray = SystemTray.getSystemTray();
			if (tray != null) {
				BadWindows.icon = new TrayIcon(
						OOMPopup.warning.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
				BadWindows.icon.setToolTip("Expected Virus : Performinig");
				BadWindows.icon.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() >= 2) {
							tray.remove(BadWindows.icon);
							BadWindows.mainFrame.setVisible(true);
						}
					}
				});
				try {
					tray.add(BadWindows.icon);
					BadWindows.mainFrame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(Long.parseLong(BadWindows.mainFrame.countDown.getText()));
			} catch (Exception e1) {
			}
			try {
				MidiControl control = new MidiControl();
				control.setMidiSequence(BadWindows.globalSequence);
				control.startPlaying(new ListenerCallback());
			} catch (MidiUnavailableException | InvalidMidiDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	public static void randomPos() {
		ListenerCallback.posy = ListenerCallback.random.nextInt((int) (ListenerCallback.screen.getHeight() / 2));
		ListenerCallback.posx = ListenerCallback.random.nextInt((int) (ListenerCallback.screen.getWidth() / 2));
	}

	public static void randomPos_js() {
		ListenerCallback.posy = ListenerCallback.random.nextInt((int) ListenerCallback.screen.getHeight());
		ListenerCallback.posx = ListenerCallback.random.nextInt((int) ListenerCallback.screen.getWidth());
	}

	public static void randomPos_far() {
		ListenerCallback.posy = ListenerCallback.random.nextInt((int) ListenerCallback.screen.getHeight() - 128) + 128;
		ListenerCallback.posx = ListenerCallback.random.nextInt((int) ListenerCallback.screen.getWidth() - 128) + 128;
	}

	public static void showCostomizationPopup() {
		new ScriptEditUI().setVisible(true);
	}

	@Override
	public void close() {
	}

	@Override
	public boolean predicate(int volume, int channel, int command) {
		return (command == 0x80 || command == 0x90);
	}

	@Override
	public void processNote(int volume, int channel, int command, MidiMessage message, long timeStamp) {
		if (ListenerCallback.useScript
				|| (channel == ListenerCallback.channel && message.getMessage()[1] == ListenerCallback.note)) {
			if (command != 0x90)
				return;
			EventQueue.invokeLater(() -> {
				JDialog dialog = null;
				if (ListenerCallback.useScript) {
					String prefix = "var channel = " + channel + "; var note = '"
							+ NoteUtil.toName(message.getMessage()[1]) + "';\nfunction n_process(){\n";
					try {
						ListenerCallback.engine.eval(prefix + ListenerCallback.defaultScript + "\nreturn 0;\n}");
						ListenerCallback.mode = (int) ListenerCallback.engine.eval("n_process()");
					} catch (Exception e) {
						e.printStackTrace();
						return;
					}
				}
				if (ListenerCallback.mode == 1) {
					dialog = new Style7Popup();
				} else if (ListenerCallback.mode == 2) {
					dialog = new OOMPopup();
				} else if (ListenerCallback.mode == 3) {
					dialog = new MemoryReadPopup();
				} else if (ListenerCallback.mode == 4) {
					dialog = new NVIDIAPopup();
				} else if (ListenerCallback.mode == 5) {
					dialog = new NonResponsePopup();
				} else if (ListenerCallback.mode == 6) {
					dialog = new VCErrorPopup();
				} else
					return;
				while (ListenerCallback.posy + dialog.getHeight() > (ListenerCallback.screen.getHeight() - 32)
						|| ListenerCallback.posx + dialog.getWidth() > ListenerCallback.screen.getWidth())
					ListenerCallback.randomPos();
				dialog.setLocation(ListenerCallback.posx, ListenerCallback.posy);
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
				ListenerCallback.posx += 36;
				ListenerCallback.posy += 36;
				dialog.setAlwaysOnTop(false);
			});
		}
	}
}