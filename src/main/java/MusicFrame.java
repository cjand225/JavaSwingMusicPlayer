import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class MusicFrame {
    final String TOOLBAR_TITLE = "JSMP";
    boolean sliderMoving;
    JFrame mFrame;
    MusicPanel mPanel;
    MusicToolbar mToolbar;
    MusicPlayer mPlayer;
    Timer updateTick;

    public MusicFrame() {
        mFrame = createMusicFrame(TOOLBAR_TITLE);
        mPanel = createMusicPanel();
        mToolbar = new MusicToolbar();
        mPlayer = new MusicPlayer();
        updateTick = new Timer(100, e -> updateSlider());

        createActionListeners();
        mFrame.add(mToolbar, BorderLayout.NORTH);
        mFrame.add(mPanel);
        mFrame.setVisible(true);
    }

    private JFrame createMusicFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setMinimumSize(new Dimension(300, 200));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private MusicPanel createMusicPanel(){
        MusicPanel panel = new MusicPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setEnabled(false);
        return panel;
    }

    private void updateSlider() {
        if (mPlayer.isPlaying()) {
            mPanel.getMusicSliderBar().setSliderValue(0, (int) mPlayer.getClipDuration());
            mPanel.getMusicSliderBar().setDurationText(mPlayer.getClipDurationAsSeconds());
            if (!sliderMoving) {
                mPanel.getMusicSliderBar().setSliderValue((int) mPlayer.getClipCurrentTime());
            }
        }
    }

    private void createActionListeners() {
        mPanel.getPlay().addActionListener(e -> mPlayer.play());
        mPanel.getPause().addActionListener(e -> mPlayer.pause());
        mPanel.getStop().addActionListener(e -> mPlayer.stop());
        mPanel.getFastForward().addActionListener(e -> mPlayer.fastForward());
        mPanel.getRewind().addActionListener(e -> mPlayer.rewind());
        mPanel.getBegin().addActionListener(e -> mPlayer.begin());
        mPanel.getEnd().addActionListener(e -> mPlayer.end());
        mToolbar.getOpen().addActionListener(e -> openSong());

        mPanel.getMusicSliderBar().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                sliderMoving = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (mPanel.isEnabled()) {
                    long sliderValue = mPanel.getMusicSliderBar().getSliderValue();
                    if (sliderValue >= 0 && sliderValue < mPlayer.getClipDuration()) {
                        mPlayer.setClipPosition(mPanel.getMusicSliderBar().getSliderValue());
                    } else if (sliderValue >= mPlayer.getClipDuration()) {
                        mPlayer.setClipPosition(mPlayer.getClipDuration() - mPlayer.getFrameLength());
                    } else if (sliderValue < 0) {
                        mPlayer.setClipPosition(0);
                    }
                    sliderMoving = false;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private void openSong() {
        int returnVal = mToolbar.getFileChoose().showOpenDialog(mPanel);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = mToolbar.getFileChoose().getSelectedFile();
            mPlayer.openFile(file);
            if (mPlayer.isLoaded()) {
                mPanel.setEnabled(true);
                updateTick.start();
                mPlayer.play();
            } else {
                mPanel.setEnabled(false);
                updateTick.stop();
            }
        }
    }


}
