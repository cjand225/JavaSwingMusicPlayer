import javax.swing.*;
import java.awt.*;

public class MusicFrame {
    JFrame mFrame;
    MusicPanel mPanel;
    MusicToolbar mToolbar;


    public MusicFrame(){
        mFrame = new JFrame("JSMP");
        mPanel = new MusicPanel();
        mToolbar = new MusicToolbar();


        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));



        mFrame.setSize(500,200);
        mFrame.add(mToolbar, BorderLayout.NORTH);
        mFrame.add(mPanel);

        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setVisible(true);
    }
}
