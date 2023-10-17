import javax.sound.sampled.*;
import java.io.*;

public class MusicPlayer {
    public static void main(String[] args) {
        playSongLoop("C:\\Users\\91990\\Desktop\\GameMusic\\Sneaky-Snitch.mp3");
    }

    public static void playSongLoop(String filePath) {
        try {
            File songFile = new File(filePath);
            if (!songFile.exists()) {
                System.err.println("Song not found: " + filePath);
                return;
            }

            // Open an audio input stream from the file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(songFile);

            // Get the audio format of the file
            AudioFormat format = audioInputStream.getFormat();

            // Create a data line info object for the clip
            DataLine.Info dataLineInfo = new DataLine.Info(Clip.class, format);

            // Get the clip from the audio system
            Clip clip = (Clip) AudioSystem.getLine(dataLineInfo);

            // Open the clip and start playing the song in an infinite loop
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            // Optionally, you can add a delay to let the song play for a certain duration
            Thread.sleep(10000); // Play for 10 seconds

            // Close the clip and audio input stream
            clip.stop();
            clip.close();
            audioInputStream.close();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
