import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class MusicToolbar extends JMenuBar {
    JMenu file, edit, view, help;

    public MusicToolbar(){
        file = createMenu("File");
        edit = createMenu("Edit");
        view = createMenu("View");
        help = createMenu("Help");

        this.add(file);
        this.add(edit);
        this.add(view);
        this.add(help);
    }

    private JButton createButton(String path) {
        JButton nButton = new JButton(path);

        return nButton;
    }

    private JMenu createMenu(String name){
        JMenu menu = new JMenu(name);

        return menu;
    }
}
