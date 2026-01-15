package awt.awt_coordinates.prof;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowClosingAdapter extends WindowAdapter {
    Frame frame;

    public WindowClosingAdapter(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        frame.dispose();
    }
}
