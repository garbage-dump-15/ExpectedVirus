package net.steepout.music.badwin.ui;

import net.steepout.music.badwin.ListenerCallback;

public class VCErrorPopup extends AbstractNativeDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 387879131803745663L;

	public VCErrorPopup() {
		super();
		title = "Microsoft Visual C++ Runtime Library";
		message = "Runtime Error!\n\nProgram:C:\\Windows\\sysWoW64\\rundll32.exe\n\n\nThis application has requested the Runtime to terminate it in an unusual way.\nPlease contact the application's support team for more information.";
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(ListenerCallback.slpGlobal);
				} catch (InterruptedException e) {
				} finally {
					VCErrorPopup.this.setVisible(false);
				}
			}
		}.start();
	}

}
