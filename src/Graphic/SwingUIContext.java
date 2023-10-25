package Graphic;

import javax.swing.*;

public class SwingUIContext implements UIContext {

    public SwingUIContext()
    { }

    @Override
    public void run(Runnable _drawing_prosses)
    {
        SwingUtilities.invokeLater(_drawing_prosses);
    }
}
