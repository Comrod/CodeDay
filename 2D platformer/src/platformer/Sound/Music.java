package platformer.Sound;

import java.io.File;
import java.util.Random;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;


public class Music implements Runnable {

	public Music() {
		
	}

	public void playMusic(final int loop, final String URL) {
		this.URL = URL;
		this.loop = loop;
		if(sequencer == null)
			try {
				sequencer = MidiSystem.getSequencer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		pplay(URL, loop);
	}

	public void pplay(final String URL, final int loop) {
		try {
			File url = new File(URL);

			sequence = MidiSystem.getSequence(url);
			//sequencer.setTempoFactor(0.3f);
			sequencer.open();
			sequencer.setSequence(sequence);
			sequencer.start();
			sequencer.setLoopCount(loop);
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}

	public void stop() {
		if(sequencer != null && sequencer.isOpen()) {
			sequencer.stop();
			sequencer.close();
		}
	}

	Random ran = new Random();
	public Sequence sequence;
	public static Sequencer sequencer;
	public int loop;
	public String URL;
}
