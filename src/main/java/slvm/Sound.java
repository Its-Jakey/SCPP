package slvm;

import javax.sound.sampled.*;
import java.applet.Applet;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Sound { //TODO: Implement volume and pitch adjustment
    private List<Clip> playingSounds = new ArrayList<>();
    private float volume = 1.0f;
    private float pitch = 1.0f;

    public void playSound(String sound) {
        InputStream soundStream = Sound.class.getResourceAsStream("/" + sound + ".wav");
        if (soundStream == null)
            return;
        CountDownLatch syncLatch = new CountDownLatch(1);

        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(soundStream);

            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();

            clip.addLineListener(e -> {
                if (e.getType() == LineEvent.Type.STOP) {
                    syncLatch.countDown();
                }
            });

            syncLatch.await();

            clip.close();
            stream.close();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            throw new VMException(e.toString());
        }
    }

    public void startSound(String sound) {
        InputStream soundStream = Sound.class.getResourceAsStream("/" + sound + ".wav");
        if (soundStream == null)
            return;

        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(soundStream);

            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();

            clip.addLineListener(e -> {
                if (e.getType() == LineEvent.Type.STOP) {
                    clip.close();
                    playingSounds.remove(clip);
                }
            });

            playingSounds.add(clip);

            stream.close();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new VMException(e.toString());
        }
    }

    public void stopSounds() {
        playingSounds.forEach(Clip::stop);
        playingSounds.clear();
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }
}
