import javax.sound.sampled.*;
import java.applet.*;
import java.io.*;


public class MusicPlayer {

    Clip mClip;

    /**
     *
     */
    public MusicPlayer() {
        try {
            this.openFile("sounds/Venom.wav");
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param file path to file that opens
     * @throws IOException if file can't be opened/read
     * @throws UnsupportedAudioFileException if file isn't proper format
     * @throws LineUnavailableException if audio resource isn't avaiable
     */
    public void openFile(String file) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream in = classloader.getResourceAsStream(file);
        assert in != null;
        AudioInputStream is = AudioSystem.getAudioInputStream(in);
        mClip = AudioSystem.getClip();
        mClip.open(is);
    }


    /**
     *
     */
    public void play() {
        mClip.start();
    }

    /**
     *
     */
    public void pause() {
        mClip.stop();
    }

    /**
     *
     */
    public void stop() {
        mClip.stop();
        mClip.setMicrosecondPosition(0);
    }

    /**
     *
     */
    public void fastfoward() {
        mClip.stop();
        mClip.setMicrosecondPosition(mClip.getMicrosecondPosition() + mClip.getFrameLength());
        mClip.start();
    }

    /**
     *
     */
    public void rewind() {
        mClip.stop();
        mClip.setMicrosecondPosition(mClip.getMicrosecondPosition() - mClip.getFrameLength());
        mClip.start();
    }

    /**
     *
     */
    public void begin() {
        mClip.setMicrosecondPosition(0);
    }

    /**
     *
     */
    public void end() {
        mClip.setMicrosecondPosition(mClip.getMicrosecondLength());
    }


}
