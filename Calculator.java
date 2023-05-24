import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

public class Calculator {

    private JFrame window;
    private JTextField textField;

    private int width, height;

    private boolean solved = false;

    private JButton[] buttons;

    public Calculator(int w, int h) {
        final int NUM_BUTTONS = 20;
        final int BUTTON_COLS = 4;
        final int BUTTON_ROWS = (int) Math.ceilDiv(NUM_BUTTONS, BUTTON_COLS);

        width = w;
        height = h;

        window = new JFrame("Taschenrechner");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setUndecorated(true);
        window.setSize(width, height);
        window.setVisible(true);
        window.getContentPane().setBackground(Color.DARK_GRAY);
        window.setLocationRelativeTo(null);
        window.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        window.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                onKeyPressed(e.getKeyChar());
            }

            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {

            }
        });

        Font font = new Font("SansSerif", Font.BOLD, 20);

        int tx = 0;// (int) (width * 0.08);
        int ty = 0;// (int) (height * 0.03);
        int tw = width;// (int) (width * 0.8);
        int th = (int) (height * 0.15);
        textField = new JTextField();
        textField.setEditable(false);
        textField.setBounds(tx, ty, tw, th);
        textField.setHorizontalAlignment(JTextField.LEFT);
        textField.setBackground(new Color(150, 150, 150));
        textField.setFont(font);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textField.setVisible(true);
        window.add(textField);

        ActionListener numberButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                numberButtonPressed(((JButton) event.getSource()).getText());
            }
        };
        ActionListener operatorButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                operatorButtonPressed(((JButton) event.getSource()).getText());
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
        JButton bPow = new JButton("Off");
        b1.addActionListener(numberButtonListener);
        b2.addActionListener(numberButtonListener);
        b3.addActionListener(numberButtonListener);
        b4.addActionListener(numberButtonListener);
        b5.addActionListener(numberButtonListener);
        b6.addActionListener(numberButtonListener);
        b7.addActionListener(numberButtonListener);
        b8.addActionListener(numberButtonListener);
        b9.addActionListener(numberButtonListener);
        b0.addActionListener(numberButtonListener);
        bAdd.addActionListener(operatorButtonListener);
        bSub.addActionListener(operatorButtonListener);
        bMul.addActionListener(operatorButtonListener);
        bDiv.addActionListener(operatorButtonListener);
        bP.addActionListener(numberButtonListener);
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
        bPow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                offButtonPressed();
            }
        });

        buttons = new JButton[] {
                b1, b2, b3, bAdd,
                b4, b5, b6, bSub,
                b7, b8, b9, bMul,
                bNeg, b0, bP, bDiv,
                bDel, bClr, bEqu, bPow
        };

        int yOff = ty + th;
        int bw = (int) (width / BUTTON_COLS);
        int bh = (int) (height - yOff) / BUTTON_ROWS;

        for (int i = 0; i < NUM_BUTTONS; i++) {
            JButton b = buttons[i];
            b.setVisible(true);
            b.setBackground(new Color(200, 200, 200));
            b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            b.setFont(font);

            int col = i % BUTTON_COLS;
            int row = Math.floorDiv(i, 4);

            b.setBounds(
                    col * bw, row * bh + yOff,
                    bw, bh);
            window.add(b);
        }
        window.repaint();
    }

    private void numberButtonPressed(String number) {
        if (solved) {
            solved = false;
            textField.setText("");
        }
        String t = textField.getText();
        textField.setText(t + number);
    }

    private void operatorButtonPressed(String operator) {
        String t = textField.getText();
        textField.setText(t + operator);

        if (solved)
            solved = false;
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
        numberButtonPressed("-");
    }

    private void equalsButtonPressed() {
        String equation = textField.getText();
        textField.setText(Solver.solve(equation));
        solved = true;
    }

    private void offButtonPressed() {
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }

    private void onKeyPressed(char key) {
        if (Character.isDigit(key) || key == '.') {
            numberButtonPressed(key + "");
        }
    }
}