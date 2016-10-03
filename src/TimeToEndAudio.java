import java.io.File;
import java.io.IOException;


import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TimeToEndAudio{
	
	private String filename;
	
	public TimeToEndAudio(String filename) {
		this.filename = filename;
	}

	public void graj() throws LineUnavailableException, IOException, UnsupportedAudioFileException{
		File audioFile = new File(filename);
		
		String inFilePath = audioFile.getAbsolutePath();
		System.out.println(inFilePath);
		
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
        System.out.println(audioFile);
        AudioFormat sourceFormat =  audioInputStream.getFormat();
        
        System.out.println(sourceFormat);
        
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
		
		AudioFormat format = audioStream.getFormat();
		System.out.println(format);
		DataLine.Info info = new DataLine.Info(Clip.class, format);
		Clip audioClip = (Clip) AudioSystem.getLine(info);
		
		audioClip.open(audioStream);
		audioClip.start();
		audioClip.close();
		audioStream.close();
		System.out.println(audioFile.exists());
	}
	
	public static void main(String[] args) {
		try {
			new TimeToEndAudio("audio/syrena.mp3").graj();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
}
