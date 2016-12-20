package net.steepout.music.badwin;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public interface CNativeCall extends Library {
	static CNativeCall instance = (CNativeCall) Native.loadLibrary(Platform.isWindows() ? "msvcrt" : "c",
			CNativeCall.class);

	public void exit(int code);

	public void printf(String format, Object... objects);

	public void putchar(char c);
}
