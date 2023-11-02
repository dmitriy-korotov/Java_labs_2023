package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FTPLoaderApplication extends JFrame {

    private final FTPLoader m_loader = new FTPLoader();

    private String m_hostname = null;




    public FTPLoaderApplication(String _title, int _width, int _height) {
        super(_title);
        setSize(_width, _height);
    }



    public void SetHostname(String _hostname) { m_hostname = _hostname; }



    public void Start() {
        SwingUtilities.invokeLater(() -> {
            InitializeComponents();
            Initialize();
        });
    }



    private void InitializeComponents() {

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.setVisible(true);



        JTextField text_field = new JTextField();
        text_field.setForeground(new Color(0, 200, 100));
        text_field.setVisible(true);


        Font font = new Font("Arial", Font.BOLD, 20);


        JLabel error_label = new JLabel("ERROR: Can't load file");
        error_label.setForeground(Color.RED);
        error_label.setVisible(false);
        error_label.setFont(font);



        JLabel label = new JLabel("Input filepath:");
        label.setFont(font);
        label.setVisible(true);


        JButton button = CreateButton(text_field, error_label);


        container.add(label, BorderLayout.NORTH);
        container.add(text_field, BorderLayout.CENTER);
        container.add(error_label, BorderLayout.SOUTH);

        add(container, BorderLayout.NORTH);
        add(button, BorderLayout.SOUTH);
    }



    private JButton CreateButton(JTextField _text_field, JLabel _error_label) {
        JButton button = new JButton("Download");
        button.addActionListener(_event -> {
            assert m_hostname != null;
            try {
                boolean is_loaded = m_loader.load(m_hostname, _text_field.getText());
                if (is_loaded) {
                    _text_field.setForeground(new Color(0, 200, 100));
                    _error_label.setVisible(false);
                }
                else {
                    _text_field.setForeground(Color.RED);
                    _error_label.setVisible(true);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        button.setVisible(true);
        return button;
    }


    private void Initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
