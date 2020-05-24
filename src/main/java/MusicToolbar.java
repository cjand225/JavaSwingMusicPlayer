import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MusicToolbar extends JMenuBar {
    JMenu file, view, help;
    JMenuItem open, exit, about, album_cover, duration_bar;
    final JFileChooser fileChoose;

    /**
     *
     */
    public MusicToolbar() {
        //Menus
        fileChoose = new JFileChooser();
        FileNameExtensionFilter files = new FileNameExtensionFilter("wav files", "wav");
        fileChoose.setFileFilter(files);
        file = createMenu("File");
        view = createMenu("View");
        help = createMenu("Help");

        //Menu Items
        open = new JMenuItem("Open");
        exit = new JMenuItem("Exit");

        exit.addActionListener(e -> System.exit(0));


        file.add(open);
        file.add(exit);

        this.add(file);
        this.add(view);
        this.add(help);
    }


    private JMenu createMenu(String name) {
        return new JMenu(name);
    }

    private JMenuItem createMenuItem(String name) {
        return new JMenuItem(name);
    }

    public JMenuItem getOpen() {
        return open;
    }

    public JFileChooser getFileChoose() {
        return fileChoose;
    }


}
