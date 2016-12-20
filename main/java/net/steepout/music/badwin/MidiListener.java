package net.steepout.music.badwin;

import java.util.Arrays;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

public abstract class MidiListener implements Receiver {

	@Override
	public abstract void close();

	private int[] decryptStatus(int status) {
		int command = status & 0xF0;
		int channel = status & 0x0F;
		return new int[] { command, channel };
	}

	public abstract boolean predicate(int volume, int channel, int command);

	public abstract void processNote(int volume, int channel, int command, MidiMessage message, long timeStamp);

	@Override
	public void send(MidiMessage message, long timeStamp) {
		int status[] = decryptStatus(message.getStatus());
		int command = status[0], channel = status[1];
		//System.out.println("[MIDI Message] " + message.getClass().getSimpleName() + " : [Command" + " : " + command
		//		+ " , channel : " + channel + " , data : " + Arrays.toString(message.getMessage()) + "]");
		if (command == 0x90 || command == 0x80) {
			if (predicate(message.getMessage()[2], channel, command)) {
				processNote(message.getMessage()[2], channel, command, message, timeStamp);
			}
		}
	}

}
