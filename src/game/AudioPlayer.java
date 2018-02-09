package game;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class AudioPlayer {

	private Clip clip;
	
	public AudioPlayer(String sound, Boolean loop) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(sound));
			
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
					baseFormat.getSampleRate(),
					16,
					baseFormat.getChannels(),
					baseFormat.getChannels() * 2,
					baseFormat.getSampleRate(),
					false);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
			if(loop) {
				clip.loop(clip.LOOP_CONTINUOUSLY);
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void play() {
		if(clip == null) {
			return;
		}
		stop();
		clip.setFramePosition(0);
		clip.start();			
	}
	public void stop() {
		if(clip.isRunning()) {
			clip.stop();
		}
	}
	public void close() {
		stop();
		clip.close();
	}
	public float getVolume() {
	    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
	    return (float) Math.pow(10f, gainControl.getValue() / 20f);
	}
	public void setVolume(float volume) {
	    if (volume < 0f || volume > 1f) {
	        throw new IllegalArgumentException("Volume not valid: " + volume);
	    }
	    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
	    gainControl.setValue(20f * (float) Math.log10(volume));
	}
}
