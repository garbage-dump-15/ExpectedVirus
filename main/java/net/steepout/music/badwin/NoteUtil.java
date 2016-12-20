package net.steepout.music.badwin;

public class NoteUtil {

	final static String sequence[] = new String[] { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };

	public static void main(String[] args) {
		System.out.println(NoteUtil.toName(61));
		System.out.println(NoteUtil.toNote("C#5"));
	}

	static int position(char c) {
		c = Character.toUpperCase(c);
		switch (c) {
		case 'C':
			return 1;
		case 'D':
			return 3;
		case 'E':
			return 5;
		case 'F':
			return 6;
		case 'G':
			return 8;
		case 'A':
			return 10;
		case 'B':
			return 12;
		}
		return -1;
	}

	public static String toName(int note) {
		int step = note / 12;
		int rm = note % 12;
		return new StringBuilder(NoteUtil.sequence[rm]).append(step).toString();
	}

	public static int toNote(String str) {
		char c = str.charAt(0);
		char d = str.charAt(1);
		if (d == '#')
			d = str.charAt(2);
		int step = (d - '0');
		step *= 12;
		step += NoteUtil.position(c);
		if (str.length() > 2) {
			step++;
		}
		return --step;
	}

}
