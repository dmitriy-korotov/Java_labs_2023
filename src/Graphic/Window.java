package Graphic;

import javax.swing.*;
import java.awt.event.WindowListener;

public class Window extends JFrame {


    public Window(String _title)
    {
        setTitle(_title);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setUndecorated(true);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Window(String _title, Integer _width, Integer _height)
    {
        setTitle(_title);
        setSize(_width, _height);

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
