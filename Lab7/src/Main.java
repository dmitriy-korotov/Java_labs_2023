import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    public static int START_POINT = 0;

    public volatile static boolean IsFinish = false;
    public volatile static boolean IsInterrupted = false;

    static JFrame m_frame = new JFrame("Race");
    static Container m_container = m_frame.getContentPane();

    static List<Racer> m_racers = new ArrayList<>();





    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(Main::CreateAndShowGUI);
    }



    private static void CreateAndShowGUI() {

        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            AddComponentsToPane(m_container);
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }



    private static void AddComponentsToPane(Container pane) throws InterruptedException {

        pane.setLayout(null);

        RaceOn(pane);
        RestartButton(pane);
    }



    public static void RaceOn(Container pane){

        List<JButton> buttons = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 7; i++) {

            int speed = random.nextInt(1, 5);

            Color color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());

            JButton button = new JButton(Integer.toString(i));

            button.setBounds(START_POINT, 20 + i * 100, 50, 50);

            button.setBackground(color);

            pane.add(button);

            m_racers.add(new Racer(speed, i, color, button, m_frame));
        }

        m_frame.setSize(1200,800);
        m_frame.setLocationRelativeTo(null);
        m_frame.setVisible(true);

        for (Racer racer : m_racers) {
            racer.start();
        }
    }

    public static void RestartButton(Container pane){

        JButton SecDown = new JButton("Restart");

        SecDown.setBounds(m_frame.getWidth() / 2 - 100, m_frame.getHeight() - 100 , 200, 50);
        SecDown.setBackground(Color.CYAN);
        pane.add(SecDown);

        SecDown.addActionListener(_event -> {

            IsInterrupted = true;

            pane.setBackground(Color.WHITE);

            pane.removeAll();

            pane.repaint();

            while (true) {
                boolean is_all_interrupted = true;
                for (Racer racer : m_racers)
                    is_all_interrupted &= racer.isInterrupted();
                if (is_all_interrupted)
                    break;
            }

            m_racers.clear();

            RestartButton(pane);

            IsInterrupted = false;
            IsFinish = false;

            RaceOn(pane);
        });
    }
}