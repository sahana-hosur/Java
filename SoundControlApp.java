import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SoundControlApp extends JFrame {
    public SoundControlApp() {
        setTitle("Sound Control");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton muteButton = new JButton("Mute System Sounds");
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                muteSystemSounds();
            }
        });

        add(muteButton);

        pack();
        setVisible(true);
    }

    private void muteSystemSounds() {
        try {
            Mixer.Info[] mixers = AudioSystem.getMixerInfo();
            for (Mixer.Info mixerInfo : mixers) {
                Mixer mixer = AudioSystem.getMixer(mixerInfo);
                Line.Info[] lineInfos = mixer.getTargetLineInfo();
                for (Line.Info lineInfo : lineInfos) {
                    Line line = mixer.getLine(lineInfo);
                    line.open();

                    if (line instanceof Clip) {
                        Clip clip = (Clip) line;
                        AudioFormat format = new AudioFormat(44100, 16, 2, true, false);
                        DataLine.Info clipInfo = new DataLine.Info(Clip.class, format);

                        if (AudioSystem.isLineSupported(clipInfo)) {
                            AudioInputStream stream = AudioSystem.getAudioInputStream(format, new AudioInputStream(getClass().getResourceAsStream("sample.wav"), format, AudioSystem.NOT_SPECIFIED));
                            clip.open(stream);
                            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                            float minVolume = volumeControl.getMinimum();
                            volumeControl.setValue(minVolume); // Set volume to the minimum to mute
                            line.close();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SoundControlApp());
    }
}
