import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class MusicPanel extends JPanel {
    MusicSlider musicSliderBar;
    JButton play, pause, fastForward, rewind, begin, end, stop, nowPlaying;
    JPanel buttonPanel;

    public MusicPanel() {
        createComponents();
        addComponents();
        setMinimumSize(new Dimension(250, 250));
    }

    /**
     * createComponents() - creates components necessary for UI using methods like createButton()
     */
    private void createComponents() {
        buttonPanel = new JPanel();
        musicSliderBar = new MusicSlider();
        play = createButton("icons/play.png");
        pause = createButton("icons/pause.png");
        end = createButton("icons/end.png");
        begin = createButton("icons/skip.png");
        rewind = createButton("icons/rewind.png");
        fastForward = createButton("icons/fastforward.png");
        stop = createButton("icons/stop.png");
    }


    /**
     * addComponents() - adds all components to overall panel.
     */
    private void addComponents() {
        buttonPanel.add(begin);
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

    /**
     * createButton() - takes a path to open icon file, applies & formats on JButton and returns the new JButton.
     *
     * @param path - path to icon
     * @return - JButton
     */
    private JButton createButton(String path) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(path);
        JButton nButton = new JButton();
        try {
            assert is != null;
            Image img = ImageIO.read(is);
            nButton.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
        }

        nButton.setPreferredSize(new Dimension(32, 32));
        nButton.setOpaque(false);
        nButton.setContentAreaFilled(false);

        return nButton;
    }


    private Component createHorizontalSpacing() {
        return Box.createRigidArea(new Dimension(10, 0));
    }

    private Component createVerticalSpacing() {
        return Box.createRigidArea(new Dimension(0, 15));
    }

    public JButton getPlay() {
        return play;
    }

    public JButton getPause() {
        return pause;
    }

    public JButton getFastForward() {
        return fastForward;
    }

    public JButton getRewind() {
        return rewind;
    }

    public JButton getBegin() {
        return begin;
    }

    public JButton getEnd() {
        return end;
    }

    public JButton getStop() {
        return stop;
    }

    public JButton getNowPlaying() {
        return nowPlaying;
    }

    public MusicSlider getMusicSliderBar() {
        return musicSliderBar;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        musicSliderBar.setEnabled(enabled);
        play.setEnabled(enabled);
        pause.setEnabled(enabled);
        stop.setEnabled(enabled);
        fastForward.setEnabled(enabled);
        begin.setEnabled(enabled);
        end.setEnabled(enabled);
        rewind.setEnabled(enabled);
        buttonPanel.setEnabled(enabled);
    }
}
