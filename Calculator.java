import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

public class Calculator {

    private JFrame window;
    private JTextField textField;

    private int width, height;

    // 1 2 3 +
    // 4 5 6 -
    // 7 8 9 *
    // (-) 0 . /
    // d c =

    private JButton[] buttons;

    public Calculator(int w, int h) {
        final int NUM_BUTTONS = 19;
        final int BUTTON_COLS = 4;
        final int BUTTON_ROWS = (int) Math.ceilDiv(NUM_BUTTONS, BUTTON_COLS);

        width = w;
        height = h;

        window = new JFrame("Taschenrechner");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setSize(width, height);
        window.setVisible(true);
        window.getContentPane().setBackground(Color.DARK_GRAY);

        int tx = 0;// (int) (width * 0.08);
        int ty = 0;// (int) (height * 0.03);
        int tw = width;// (int) (width * 0.8);
        int th = (int) (height * 0.1);
        textField = new JTextField();
        textField.setEditable(false);
        textField.setBounds(tx, ty, tw, th);
        textField.setHorizontalAlignment(JTextField.LEFT);
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textField.setVisible(true);
        window.add(textField);

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                buttonPressed(((JButton) event.getSource()).getText());
            }
        };
        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");
        JButton b7 = new JButton("7");
        JButton b8 = new JButton("8");
        JButton b9 = new JButton("9");
        JButton b0 = new JButton("0");
        JButton bAdd = new JButton("+");
        JButton bSub = new JButton("-");
        JButton bMul = new JButton("*");
        JButton bDiv = new JButton("/");
        JButton bP = new JButton(".");
        JButton bDel = new JButton("Del");
        JButton bClr = new JButton("Clr");
        JButton bNeg = new JButton("(-)");
        JButton bEqu = new JButton("=");
        b1.addActionListener(al);
        b2.addActionListener(al);
        b3.addActionListener(al);
        b4.addActionListener(al);
        b5.addActionListener(al);
        b6.addActionListener(al);
        b7.addActionListener(al);
        b8.addActionListener(al);
        b9.addActionListener(al);
        b0.addActionListener(al);
        bAdd.addActionListener(al);
        bSub.addActionListener(al);
        bMul.addActionListener(al);
        bDiv.addActionListener(al);
        bP.addActionListener(al);
        bDel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteButtonPressed();
            }
        });
        bClr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearButtonPressed();
            }
        });
        bNeg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                negativeButtonPressed();
            }
        });
        bEqu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                equalsButtonPressed();
            }
        });

        buttons = new JButton[] {
                b1, b2, b3, bAdd,
                b4, b5, b6, bSub,
                b7, b8, b9, bMul,
                bNeg, b0, bP, bDiv,
                bDel, bClr, bEqu
        };

        int yOff = ty + th;
        int bw = (int) (width / (BUTTON_COLS));
        int bh = (int) (height - yOff) / BUTTON_ROWS;

        for (int i = 0; i < NUM_BUTTONS; i++) {
            JButton b = buttons[i];
            b.setVisible(true);
            b.setBackground(Color.LIGHT_GRAY);
            b.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            int col = i % BUTTON_COLS;
            int row = Math.floorDiv(i, 4);

            // int marginW = (int) (bw * 0.1);
            // int marginH = (int) (bh * 0.1);

            b.setLocation(col * bw, row * bh + yOff);
            b.setSize(bw, bh);
            // b.setBounds(
            // (int) (col * bw + marginW * 0.5),
            // (int) ((row * bh + yOff) + marginH * 0.5),
            // (int) (bw - marginW * 2),
            // (int) (bh - marginH * 2));
            window.add(b);
        }
        window.repaint();
    }

    private void buttonPressed(String number) {
        String t = textField.getText();
        textField.setText(t + number);
    }

    private void clearButtonPressed() {
        textField.setText("");
    }

    private void deleteButtonPressed() {
        String t = textField.getText();

        if (t.equals(""))
            return;

        t = t.substring(0, t.length() - 1);
        textField.setText(t);
    }

    private void negativeButtonPressed() {
        buttonPressed("-");
    }

    private void equalsButtonPressed() {
        String equation = textField.getText();
        textField.setText(Solver.solve(equation));
    }
}