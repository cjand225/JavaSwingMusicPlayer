import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class MusicPanel extends JPanel {
    JSlider musicSliderBar;
    JButton play;
    JButton pause;
    JButton fastForward;
    JButton rewind;
    JButton skip;
    JButton end;
    JButton stop;
    JButton nowPlaying;
    JPanel buttonPanel;

    public MusicPanel() {
        createComponents();
        addComponents();
        this.setSize(300, 200);
    }

    private void createComponents(){
        buttonPanel = new JPanel();
        musicSliderBar = createMusicSlider("");
        play = createButton("icons/play.png");
        pause = createButton("icons/pause.png");
        end = createButton("icons/end.png");
        skip = createButton("icons/skip.png");
        rewind = createButton("icons/rewind.png");
        fastForward = createButton("icons/fastforward.png");
        stop = createButton("icons/stop.png");
    }

    private void addComponents(){
        buttonPanel.add(skip);
        buttonPanel.add(rewind);
        buttonPanel.add(play);
        buttonPanel.add(pause);
        buttonPanel.add(stop);
        buttonPanel.add(fastForward);
        buttonPanel.add(end);

        this.add(createVerticalSpacing());
        this.add(createVerticalSpacing());
        this.add(musicSliderBar, BorderLayout.CENTER);
        this.add(buttonPanel);
    }

    private JButton createButton(String path) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(path);
        JButton nButton = new JButton();
        try {
            Image img = ImageIO.read(is);
            nButton.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println(e);
        }

        nButton.setPreferredSize(new Dimension(32,32));
        nButton.setOpaque(false);
        nButton.setContentAreaFilled(false);
        //nButton.setBorderPainted(false);

        return nButton;
    }

    private JSlider createMusicSlider(String path){
        JSlider mSlider = new JSlider();
        mSlider.setMaximumSize(new Dimension(400,20));

        return mSlider;
    }

    private Component createHorizontalSpacing() {
        return Box.createRigidArea(new Dimension(10, 0));
    }

    private Component createVerticalSpacing() {
        return Box.createRigidArea(new Dimension(0, 15));
    }


}
