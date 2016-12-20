package net.steepout.music.badwin.ui;

import net.steepout.music.badwin.BadWindows;
import net.steepout.music.badwin.ListenerCallback;

public class MemoryReadPopup extends AbstractNativeDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = -442480773361640006L;

	public MemoryReadPopup() {
		title = "Unity3D.exe - 应用程序错误 ";
		message = BadWindows.MessageBoxInterface.randomAddress() + " 指令引用的 "
				+ BadWindows.MessageBoxInterface.randomAddress() + " 内存。该内存不能为 read。\n\n要终止程序，请单击 ”确定“。";
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(ListenerCallback.slpGlobal);
				} catch (InterruptedException e) {
				} finally {
					MemoryReadPopup.this.setVisible(false);
				}
			}
		}.start();
	}

}
