import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class MusicSlider extends JPanel {
    JButton durationText;
    JSlider mSlider;
    int SECONDS_TIME = 60;

    public MusicSlider() {
        mSlider = createMusicSlider();
        durationText = createDurationButton();

        this.add(mSlider);
        this.add(durationText);
    }

    private JSlider createMusicSlider() {
        JSlider slider = new JSlider();
        slider.setMaximumSize(new Dimension(200, 10));
        slider.setMinimum(0);
        slider.setValue(0);
        slider.setMaximum(1);
        return slider;
    }

    private JButton createDurationButton() {
        JButton button = new JButton("00:00");
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
    }

    private void setDefaults() {
        mSlider.setValue(0);
        mSlider.setMinimum(0);
        mSlider.setMaximum(1);
        durationText.setText("00:00");
    }

    public void setDurationText(long microseconds) {
        long minutes = microseconds / SECONDS_TIME;
        long seconds = microseconds % SECONDS_TIME;
        if (minutes < 10 && seconds < 10) {
            durationText.setText(String.format("%02d:%02d", (microseconds / SECONDS_TIME), (microseconds % SECONDS_TIME)));
        } else if (minutes < 10) {
            durationText.setText(String.format("%02d:%d", (microseconds / SECONDS_TIME), (microseconds % SECONDS_TIME)));
        } else if (seconds < 10) {
            durationText.setText(String.format("%d:%02d", (microseconds / SECONDS_TIME), (microseconds % SECONDS_TIME)));
        } else {
            durationText.setText(String.format("%d:%d", (microseconds / SECONDS_TIME), (microseconds % SECONDS_TIME)));
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (!enabled) {
            setDefaults();
        }
        super.setEnabled(enabled);
        mSlider.setEnabled(enabled);
        durationText.setEnabled(enabled);
    }

    public void setSliderValue(int currentValue, int min, int max) {
        mSlider.setMinimum(min);
        mSlider.setMaximum(max);
        mSlider.setValue(currentValue);
    }

    public void setSliderValue(int currentValue) {
        setSliderValue(currentValue, mSlider.getMinimum(), mSlider.getMaximum());
    }

    public void setSliderValue(int min, int max) {
        setSliderValue(mSlider.getValue(), min, max);
    }

    public int getSliderValue() {
        return mSlider.getValue();
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        mSlider.addMouseListener(l);
    }
}
