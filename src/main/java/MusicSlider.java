import javax.swing.*;
import java.awt.*;

public class MusicSlider extends JPanel {

    JButton durationText;
    JSlider mSlider;

    public MusicSlider() {
        mSlider = new JSlider();
        durationText = new JButton("00:00");


        durationText.setBorderPainted(false);
        durationText.setContentAreaFilled(false);
        durationText.setFocusPainted(false);
        durationText.setOpaque(false);

        mSlider.setMaximumSize(new Dimension(200, 10));
        mSlider.setMinimum(0);
        mSlider.setValue(0);
        mSlider.setMaximum(1);
        this.add(mSlider);
        this.add(durationText);
    }


    /**
     * Returns public use of JSlider, allowing for out of class usage.
     *
     * @return JSlider mSlider
     */
    public JSlider getmSlider() {
        return mSlider;
    }

    /**
     * Returns a JSlider when provided with a path that is automatically formatted for MusicSlider Class.
     *
     * @param path given a path, uses custom icon for JSlider
     * @return mSlider
     */
    private JSlider createMusicSlider(String path) {
        JSlider mSlider = new JSlider();
        mSlider.setMaximumSize(new Dimension(400, 20));

        return mSlider;
    }

    /**
     * Returns a component with width 10 for horizontal spacing of layout
     *
     * @return Component
     */
    private Component createHorizontalSpacing() {
        return Box.createRigidArea(new Dimension(10, 0));
    }

    /**
     * Returns a component with height 10 for vertical spacing of layout
     *
     * @return Component
     */
    private Component createVerticalSpacing() {
        return Box.createRigidArea(new Dimension(0, 15));
    }

    public void setDurationText(long text) {
        long minutes = text / 60;
        long seconds = text % 60;
        if (minutes < 10 && seconds < 10) {
            durationText.setText(String.format("%02d:%02d", (text / 60), (text % 60)));
        } else if (minutes < 10) {
            durationText.setText(String.format("%02d:%d", (text / 60), (text % 60)));
        } else if (seconds < 10) {
            durationText.setText(String.format("%d:%02d", (text / 60), (text % 60)));
        } else {
            durationText.setText(String.format("%d:%d", (text / 60), (text % 60)));
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        mSlider.setEnabled(enabled);
        durationText.setEnabled(enabled);
    }
}
