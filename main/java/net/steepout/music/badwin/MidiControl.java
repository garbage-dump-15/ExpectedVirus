package net.steepout.music.badwin;

import java.util.function.Function;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;

public class MidiControl {
	Sequencer sequencer;

	Transmitter transmitter;

	MidiDevice device;

	public MidiControl() throws MidiUnavailableException {
		sequencer = MidiSystem.getSequencer();
		transmitter = sequencer.getTransmitter();
		device = MidiSystem.getMidiDevice(sequencer.getDeviceInfo());
	}

	public void setMidiSequence(Sequence sequence) throws InvalidMidiDataException {
		if (sequencer.isOpen())
			sequencer.close();
		sequencer.setSequence(sequence);
		/*
		 * try { sequencer.getTransmitter().setReceiver(new Receiver() {
		 * 
		 * @Override public void send(MidiMessage message, long timeStamp) {
		 * System.out.println("接收到midi信息，类型是 " +
		 * message.getClass().getSimpleName()); }
		 * 
		 * @Override public void close() { System.out.println("Midi接收器被关闭！"); }
		 * }); } catch (MidiUnavailableException e) { e.printStackTrace(); }
		 */
	}

	public void startPlaying(MidiListener recv) throws MidiUnavailableException {
		this.startPlaying(recv, (sequencer) -> {
			return sequencer;
		});
	}

	public void startPlaying(MidiListener recv, Function<Sequencer, Sequencer> process)
			throws MidiUnavailableException {
		if (!sequencer.isOpen())
			sequencer.open();
		transmitter.setReceiver(recv);
		sequencer = process.apply(sequencer);
		sequencer.start();
	}

	public void stopPlaying() {
		sequencer.stop();
		sequencer.close();
	}

}
