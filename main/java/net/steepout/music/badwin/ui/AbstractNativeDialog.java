package net.steepout.music.badwin.ui;

import java.awt.Dimension;
import java.util.UUID;

import javax.swing.JDialog;

import com.sun.jna.platform.WindowUtils;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;

import net.steepout.music.badwin.BadWindows;

public class AbstractNativeDialog extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 4348262730991553994L;

	String title = "";

	String message = "";

	int iconType = 0x10;

	HWND handle;

	int x;

	int y;

	public AbstractNativeDialog() {

	}

	@Override
	public int getHeight() {
		return 360;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(360, 360);
	}

	@Override
	public int getWidth() {
		return 360;
	}

	@Override
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// 就是这样的辣 =-=
	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			String uuid = UUID.randomUUID().toString();
			Thread popup = new Thread(() -> {
				BadWindows.MessageBoxInterface.instance.MessageBoxA(null, message, title + uuid, iconType);
			});
			popup.start();
			while (popup.getState() != Thread.State.RUNNABLE)
				;
			while (handle == null)
				handle = User32.INSTANCE.FindWindow(null, title + uuid);
			BadWindows.MessageBoxInterface.instance.SetWindowPos(handle, null, x, y, 0, 0, 0x01);
			BadWindows.MessageBoxInterface.instance.SetWindowTextA(handle, title);
			BadWindows.MessageBoxInterface.instance.SetForegroundWindow(handle);
		} else {
			User32.INSTANCE.PostMessage(handle, 0x12, null, null);
			User32.INSTANCE.PostMessage(handle, 0x112, new WinDef.WPARAM(0xF060), null);
		}
		// BadWindows.MessageBoxInterface.RECT rect = new
		// BadWindows.MessageBoxInterface.RECT();
	}

}
