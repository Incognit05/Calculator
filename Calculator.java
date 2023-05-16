import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Calculator {

    private JFrame window;
    private JTextField textField;

    private int width, height;

    public Calculator(int w, int h) {
        width = w;
        height = h;

        window = new JFrame("Taschenrechner");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setSize(width, height);
        window.setVisible(true);

        textField = new JTextField();
        textField.setEditable(false);
        textField.setBounds((int) (width * 0.08), (int) (height * 0.05), (int) (width * 0.8), (int) (height * 0.1));
        textField.setHorizontalAlignment(JTextField.LEFT);
        window.add(textField);

    }

    private JButton createButton(String text) {
        JButton b = new JButton(text);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPressed(text);
            }
        });

        return b;
    }

    private void buttonPressed(String buttonText) {
        String t = textField.getText();
        textField.setText(t + buttonText);
    }
}