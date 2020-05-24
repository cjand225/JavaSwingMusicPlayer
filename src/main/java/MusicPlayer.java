import javax.sound.sampled.*;
import java.io.*;
import java.util.concurrent.TimeUnit;


public class MusicPlayer {

    Clip mClip;

    /**
     *
     */
    public MusicPlayer() {
    }


    /**
     * @param file file that is opened
     * @throws IOException                   if file can't be opened/read
     * @throws UnsupportedAudioFileException if file isn't proper format
     * @throws LineUnavailableException      if audio resource isn't available
     */
    private void openSoundFile(File file) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioFileFormat format = AudioSystem.getAudioFileFormat(file);
        if (format.getType() == AudioFileFormat.Type.WAVE){
            AudioInputStream is = AudioSystem.getAudioInputStream(file);
            mClip = AudioSystem.getClip();
            mClip.open(is);
        }
    }

    public void openFile(File file) {
        try {
            if (mClip != null) { cleanClip(); }
            this.openSoundFile(file);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
           // e.printStackTrace();
        }
    }

    public void play() {
        mClip.start();
    }

    public void pause() {
        mClip.stop();
    }

    public void stop() {
        mClip.stop();
        mClip.setMicrosecondPosition(0);
    }

    public void fastForward() {
        mClip.stop();
        mClip.setMicrosecondPosition(mClip.getMicrosecondPosition() + mClip.getFrameLength());
        mClip.start();
    }


    public void rewind() {
        mClip.stop();
        mClip.setMicrosecondPosition(mClip.getMicrosecondPosition() - mClip.getFrameLength());
        mClip.start();
    }


    public void begin() {
        mClip.setMicrosecondPosition(0);
    }

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
        return TimeUnit.MICROSECONDS.toSeconds(getClipDuration());
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

    public boolean isLoaded() {
        return mClip != null;
    }

    private void cleanClip(){
        mClip.flush();
        mClip.close();
    }

    public long getFrameLength(){
        return mClip.getFrameLength();
    }
}
