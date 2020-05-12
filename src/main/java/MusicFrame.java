import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicFrame {
    JFrame mFrame;
    MusicPanel mPanel;
    MusicToolbar mToolbar;
    MusicPlayer mPlayer;


    /**
     *
     */
    public MusicFrame(){

        mFrame = new JFrame("JSMP");
        mPanel = new MusicPanel();
        mToolbar = new MusicToolbar();


        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));
        createActionListeners();



        mFrame.setSize(500,200);
        mFrame.add(mToolbar, BorderLayout.NORTH);
        mFrame.add(mPanel);

        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setVisible(true);
        mPlayer = new MusicPlayer();
    }

    /**
     *
     */
    private void createActionListeners(){
        mPanel.getPlay().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mPlayer.play();
            }
        });
        mPanel.getPause().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mPlayer.pause();
            }
        });

        mPanel.getStop().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mPlayer.stop();
            }
        });

        mPanel.getFastForward().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mPlayer.fastfoward();
            }
        });

        mPanel.getRewind().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mPlayer.rewind();
            }
        });

        mPanel.getBegin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mPlayer.begin();
            }
        });

        mPanel.getEnd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mPlayer.end();
            }
        });

    }


}
