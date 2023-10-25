package Graphic;

import javax.swing.*;

public class Window extends JFrame {

    public Window(String _title, Integer _width, Integer _height)
    {
        setTitle(_title);
        setSize(_width, _height);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
