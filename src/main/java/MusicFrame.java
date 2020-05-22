import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class MusicFrame {
    JFrame mFrame;
    MusicPanel mPanel;
    MusicToolbar mToolbar;
    MusicPlayer mPlayer;
    Timer updateTick;
    boolean sliderMoving;


    /**
     *
     */
    public MusicFrame() {

        mFrame = new JFrame("JSMP");
        mPanel = new MusicPanel();
        mToolbar = new MusicToolbar();
        updateTick = new Timer(100, e -> updateSlider());


        createActionListeners();
        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));

        mFrame.setMinimumSize(new Dimension(300, 200));
        mFrame.setResizable(false);
        mFrame.add(mToolbar, BorderLayout.NORTH);
        mFrame.add(mPanel);

        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setVisible(true);
        mPlayer = new MusicPlayer();
        mPanel.setEnabled(false);
    }

    /**
     *
     */

    private void updateSlider() {
        if (mPanel.getMusicSliderBar().isEnabled()) {
            mPanel.getMusicSliderBar().getmSlider().setMinimum(0);
            mPanel.getMusicSliderBar().getmSlider().setMaximum((int) mPlayer.getClipDuration());
            if (!sliderMoving) {
                mPanel.getMusicSliderBar().getmSlider().setValue((int) mPlayer.getClipCurrentTime());
            }
            mPanel.getMusicSliderBar().setDurationText(mPlayer.getClipDurationAsSeconds());
        }
    }

    private void createActionListeners() {
        mPanel.getPlay().addActionListener(e -> mPlayer.play());
        mPanel.getPause().addActionListener(e -> mPlayer.pause());

        mPanel.getStop().addActionListener(e -> mPlayer.stop());

        mPanel.getFastForward().addActionListener(e -> mPlayer.fastfoward());

        mPanel.getRewind().addActionListener(e -> mPlayer.rewind());

        mPanel.getBegin().addActionListener(e -> mPlayer.begin());

        mPanel.getEnd().addActionListener(e -> mPlayer.end());

        mToolbar.getOpen().addActionListener(e -> openSong());


        mPanel.getMusicSliderBar().getmSlider().addMouseListener(new MouseListener() {
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
                    long sliderValue = mPanel.getMusicSliderBar().getmSlider().getValue();
                    if (sliderValue >= 0 && sliderValue < mPlayer.getClipDuration() - 1) {
                        mPlayer.setClipPosition(mPanel.getMusicSliderBar().getmSlider().getValue());
                    } else if (sliderValue >= mPlayer.getClipDuration()) {
                        mPlayer.setClipPosition(mPlayer.getClipDuration() - mPlayer.getmClip().getFrameLength());
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
            mPanel.setEnabled(true);
            updateTick.start();
            mPlayer.play();
        }
    }


}
