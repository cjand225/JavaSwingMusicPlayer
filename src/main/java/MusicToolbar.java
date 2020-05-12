import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class MusicToolbar extends JMenuBar {
    JMenu file, edit, view, help;

    /**
     *
     */
    public MusicToolbar() {
        file = createMenu("File");
        edit = createMenu("Edit");
        view = createMenu("View");
        help = createMenu("Help");

        this.add(file);
        this.add(edit);
        this.add(view);
        this.add(help);
    }

    /**
     * @param name name for menu
     * @return returns JMenu to be added
     */
    private JMenu createMenu(String name) {
        JMenu menu = new JMenu(name);

        return menu;
    }

    /**
     * @param name name for menu item.
     * @return mItem returns JMenuItem
     */
    private JMenuItem createMenuItem(String name) {
        JMenuItem mItem = new JMenuItem(name);

        return mItem;
    }


}
