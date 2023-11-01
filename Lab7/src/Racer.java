import javax.swing.*;
import java.awt.*;

public class Racer extends Thread {

    int m_speed;
    int m_id;
    Color m_color;
    JButton m_button;
    JFrame m_context;




    public Racer(int _speed, int _id, Color _color, JButton _button, JFrame _context) {
        m_color = _color;
        m_id = _id;
        m_speed = _speed;
        m_button = _button;
        m_context = _context;
    }



    public void run() {

        while (true) {

            if (Main.IsFinish) {
                System.out.println("Thread stopped");
                interrupt();
                return;
            }

            if (Main.IsInterrupted) {
                System.out.println("Thread interrupted");
                interrupt();
                return;
            }
            else {
                m_button.setLocation(m_button.getLocation().x + m_speed, m_button.getLocation().y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    return;
                }
            }

            if (m_button.getLocation().x + m_button.getWidth() > m_context.getWidth()) {
                if (!Main.IsFinish) {
                    System.out.println("Thread " + m_id + "win!!! And stopped");
                    Main.IsFinish = true;
                    Main.m_container.setBackground(m_color);
                    Final();
                }
                interrupt();
                return;
            }
        }
    }



    public void Final() {

        JFrame frame = new JFrame("Winner");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container pane = frame.getContentPane();

        JButton ar = new JButton(String.format("Number %d Win!", m_id));

        ar.setFont(new Font("Arial", Font.BOLD, 60));
        ar.setContentAreaFilled(false);
        ar.setBorderPainted(false);
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        pane.add(ar,c);

        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}