package net.steepout.music.badwin.ui;

import net.steepout.music.badwin.ListenerCallback;

public class NVIDIAPopup extends AbstractNativeDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 626972929062690914L;

	public NVIDIAPopup() {
		iconType |= 0x04;
		title = "NVIDIA OpenGL Driver";
		message = "The NVIDIA OpenGL driver lost connection with the display driver due to exceeding the Windows Time-Out limit and is unable to continue.\nThe application must close.\n\n\nError code: 7\nWould you like to visit\nhttp://nvidia.custhelpscom/cgi-bin/rvidia.cfg/php/enduser/std_adp.php/p_faqid=3007 for help?";
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(ListenerCallback.slpGlobal);
				} catch (InterruptedException e) {
				} finally {
					NVIDIAPopup.this.setVisible(false);
				}
			}
		}.start();
	}

}