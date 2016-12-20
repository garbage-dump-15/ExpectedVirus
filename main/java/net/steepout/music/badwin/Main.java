package net.steepout.music.badwin;

import java.awt.SplashScreen;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JOptionPane;

import net.steepout.music.badwin.ui.BootstrapScreen;
import net.steepout.music.badwin.ui.MainUI;
import net.steepout.music.badwin.ui.OOMPopup;
import net.steepout.music.badwin.ui.Style7Popup;

public class Main {

	static BootstrapScreen bootstrap = null;

	static volatile boolean initialized = false;

	public static boolean classLoadSuccessful(String clz) {
		try {
			Thread.sleep(300); // 睡一觉先_(:з」∠)_
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			Class.forName(clz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to load class " + clz, "Fatal Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			return false;
		}
		Main.bootstrap.updateStatus("Loaded class " + clz);
		return true;
	}

	public static void loadAssociated() {
		Main.bootstrap.setModal(true);
		Main.bootstrap.updateStatus("Initializing...");
		Main.classLoadSuccessful("com.sun.jna.platform.win32.User32");
		Main.classLoadSuccessful("net.steepout.music.badwin.BadWindows$MessageBoxInterface");
		Main.bootstrap.updateStatus("Loaded basical native interface");
		Main.classLoadSuccessful("javax.sound.midi.MidiSystem");
		Main.bootstrap.updateStatus("Loaded MIDI sound support");
		Main.classLoadSuccessful("javax.swing.UIManager");
		Main.bootstrap.updateStatus("Loaded GUI manager");
		Main.bootstrap.updateStatus("Loading associated packages...");
		Main.classLoadSuccessful("net.steepout.music.badwin.BadWindows");
		Main.classLoadSuccessful("net.steepout.music.badwin.ListenerCallback");
		Main.classLoadSuccessful("net.steepout.music.badwin.MidiControl");
		Main.classLoadSuccessful("net.steepout.music.badwin.ui.ScriptEditUI");
		Main.bootstrap.updateStatus("Loading resources...");
		Style7Popup.preparePopupIcon();
		OOMPopup.preparePopupIcon();
		ListenerCallback.engine = new ScriptEngineManager().getEngineByName("javascript");
		Main.bootstrap.updateStatus("Initializing UI...");
		BadWindows.mainFrame = new MainUI(); // 初始化 UI 界面
		if (ListenerCallback.engine == null) {
			System.err.println("No javascript engine found , customize fuction disabled!");
			BadWindows.mainFrame.chkScript.setEnabled(false);
		} else {
			try {
				ListenerCallback.engine
						.eval("function repos(){var Callback = Java.type('net.steepout.music.badwin.ListenerCallback');Callback.randomPos_js();}"
								+ "function reposf(){var Callback = Java.type('net.steepout.music.badwin.ListenerCallback');Callback.randomPos_far();}");
			} catch (ScriptException e) {
			}
		}
		Main.bootstrap.updateStatus("START !");
		Main.initialized = true;
		Main.bootstrap.setModal(false);
		Main.bootstrap.setVisible(false);
	}

	public static void main(String[] args) {
		SplashScreen.getSplashScreen(); // 鬼畜从这里开始
		Main.bootstrap = new BootstrapScreen();
		Main.bootstrap.setVisible(true); // 显示加载界面
		new Thread(() -> {
			Main.loadAssociated(); // 开始资源读取及初始化
		}).start();
		while (!Main.initialized) // 等待并发线程完成
			;
		System.out.println("[DEBUG] entered main thread-pool");
		BadWindows.main(args); // 开始续命！
	}
}
/*
 * if(channel==0||channel==1) return 1; else if(channel==2) return 2; else {
 * repos(); return 4; }
 */
