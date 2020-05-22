import javax.sound.sampled.*;
import java.io.*;


public class MusicPlayer {

    Clip mClip;

    /**
     *
     */
    public MusicPlayer() {
    }


    /**
     * @param file file thats opened
     * @throws IOException                   if file can't be opened/read
     * @throws UnsupportedAudioFileException if file isn't proper format
     * @throws LineUnavailableException      if audio resource isn't avaiable
     */

    private void openSoundFile(File file) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream is = AudioSystem.getAudioInputStream(file);
        mClip = AudioSystem.getClip();
        mClip.open(is);
    }

    public void openFile(File file) {
        try {
            if (mClip != null) {
                mClip.stop();
                mClip.flush();
            }
            this.openSoundFile(file);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
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

    public long getClipDuration() {
        return mClip.getMicrosecondLength();
    }

    public long getClipCurrentTime() {
        return mClip.getMicrosecondPosition();
    }

    public long getClipDurationAsSeconds() {
        return getClipCurrentTime() / 1000000;
    }

    public Clip getmClip() {
        return mClip;
    }

    public void setClipPosition(long position) {
        mClip.stop();
        mClip.setMicrosecondPosition(position);
        mClip.start();
    }

    public Boolean isPlaying() {
        if (mClip != null) {
            return mClip.isRunning();
        } else {
            return false;
        }
    }

}
