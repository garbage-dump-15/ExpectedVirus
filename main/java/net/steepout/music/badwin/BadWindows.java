package net.steepout.music.badwin;

import java.awt.TrayIcon;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.function.Supplier;

import javax.sound.midi.Sequence;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.HWND;

import net.steepout.music.badwin.ui.MainUI;

public class BadWindows {

	public interface MessageBoxInterface extends Library { // Win32 本地窗口类库， 需要链接 JNA
		public static class RECT extends Structure {
			public NativeLong left;
			public NativeLong top;
			public NativeLong right;
			public NativeLong bottom;
		}

		// void _Z12NativeMsgBoxPcS_(String message,String caption);

		static MessageBoxInterface instance = (MessageBoxInterface) Native
				.loadLibrary(Platform.isWindows() ? "User32" : "C", MessageBoxInterface.class);

		public static String randomAddress() {
			String temp = Long.toHexString(((new Random().nextLong() & 0xFFFFFFFFL)));
			while (temp.length() > 8)
				temp = temp.substring(1);
			return "0x" + temp;
		}

		boolean DestroyWindow(HWND window);

		HWND FindWindow(String name, String id);

		boolean GetWindowRect(HWND window, Pointer rect);

		boolean MessageBeep(int type);

		void MessageBoxA(HWND window, String message, String caption, int type);

		boolean SetWindowPos(HWND window, HWND insert, int x, int y, int cx, int cy, int param);

		boolean SetWindowTextA(HWND window, String title);
		
		boolean SetForegroundWindow(HWND window);
	}

	static {
		try {
			BadWindows.initializeUI();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	static MainUI mainFrame;

	static Sequence globalSequence = null;

	static TrayIcon icon = null;

	public static void initializeUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		JDialog.setDefaultLookAndFeelDecorated(true);
		JFrame.setDefaultLookAndFeelDecorated(true);
	}

	public static void main(String[] args) {
		try {
			BadWindows.mainFrame.setVisible(true); // 显示主UI
			System.out.println("[DEBUG] primary UI shown");
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, ((Supplier<String>) () -> { // 收集错误并进行弹窗警告
				ByteArrayOutputStream pipe = new ByteArrayOutputStream();
				t.printStackTrace(new PrintStream(pipe));
				try {
					return new String(pipe.toByteArray(), "UTF-8");
				} catch (Exception e) {
				}
				return "";
			}).get(), "Fatal Error Occured", JOptionPane.ERROR_MESSAGE);
		}
	}
}
