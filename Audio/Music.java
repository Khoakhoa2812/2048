package Audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.net.URL;

public class Music {
    private static Clip[] clips = new Clip[2];
    private static URL soundURL[] = new URL[2];
    static {
        try {
            soundURL[0] = Music.class.getResource("/Audio/background.wav");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot open audio!"); // show error dialog
        }
    }

    public static void inputAudio(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clips[i] = AudioSystem.getClip();
            clips[i].open(audioInputStream);
        } catch (Exception e) {

        }
    }

    public static void backgroundMusic() {
        inputAudio(0); // Đảm bảo rằng audio đã được load trước khi phát
        if (clips[0] != null) {
            clips[0].start();
            clips[0].loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            System.out.println("Audio clip is null!"); // In ra thông báo lỗi nếu không load được audio
        }
    }
}
