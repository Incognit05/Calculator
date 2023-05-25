import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.lang.Math;

public class Calculator {

    private JFrame window;
    private JTextField textField;
    private int width, height;
    private boolean solved = false;
    private JButton[] buttons;
    private int textFieldHeight;

    private final int NUM_BUTTONS = 20;
    private final int BUTTON_COLS = 4;
    private final int BUTTON_ROWS = (int) Math.ceil(NUM_BUTTONS / BUTTON_COLS);
    private final int BAR_HEIGHT = 25;

    private int mouseXLastFrame, mouseYLastFrame;

    public Calculator(int w, int h) {
        width = w;
        height = h;
        textFieldHeight = (int) (height * 0.15);

        setupWindow();
        setupTextField();
        setupButtons();

        window.repaint();
    }

    private void setupWindow() {
        window = new JFrame("Taschenrechner");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setUndecorated(true);
        window.setSize(width + 5, height + 5 + BAR_HEIGHT);
        window.setVisible(true);
        window.getContentPane().setBackground(Color.DARK_GRAY);
        window.setLocationRelativeTo(null);
        window.getRootPane().setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        window.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                onKeyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
            }
        });
        window.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent event) {
                int x = event.getX();
                int y = event.getY();
                window.setLocation(window.getX() + (x - mouseXLastFrame), window.getY() + (y - mouseYLastFrame));
            }

            public void mouseMoved(MouseEvent event) {
                mouseXLastFrame = event.getX();
                mouseYLastFrame = event.getY();
            }
        });
    }

    private void setupTextField() {
        int ty = BAR_HEIGHT;
        int tw = width;
        final Font fontTextField = new Font("SansSerif", Font.BOLD, (int) (textFieldHeight * 0.3));
        textField = new JTextField();
        textField.setEditable(false);
        textField.setBounds(0, ty, tw, textFieldHeight);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBackground(new Color(150, 150, 150));
        textField.setFont(fontTextField);
        textField.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        textField.setVisible(true);
        window.add(textField);
    }

    private void setupButtons() {
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
        MouseAdapter hoverEffect = new MouseAdapter() {
            public void mouseEntered(MouseEvent event) {
                JButton button = (JButton) event.getSource();
                Color c = button.getBackground();
                Color newColor = new Color(c.getRed() + 20, c.getGreen() + 20, c.getBlue() + 20);
                button.setBackground(newColor);
            }

            public void mouseExited(MouseEvent event) {
                JButton button = (JButton) event.getSource();
                Color c = button.getBackground();
                Color newColor = new Color(c.getRed() - 20, c.getGreen() - 20, c.getBlue() - 20);
                button.setBackground(newColor);
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
        JButton bOff = new JButton("Off");
        b1.addMouseListener(hoverEffect);
        b2.addMouseListener(hoverEffect);
        b3.addMouseListener(hoverEffect);
        b4.addMouseListener(hoverEffect);
        b5.addMouseListener(hoverEffect);
        b5.addMouseListener(hoverEffect);
        b6.addMouseListener(hoverEffect);
        b7.addMouseListener(hoverEffect);
        b8.addMouseListener(hoverEffect);
        b9.addMouseListener(hoverEffect);
        b0.addMouseListener(hoverEffect);
        bAdd.addMouseListener(hoverEffect);
        bSub.addMouseListener(hoverEffect);
        bMul.addMouseListener(hoverEffect);
        bDiv.addMouseListener(hoverEffect);
        bP.addMouseListener(hoverEffect);
        bDel.addMouseListener(hoverEffect);
        bClr.addMouseListener(hoverEffect);
        bNeg.addMouseListener(hoverEffect);
        bEqu.addMouseListener(hoverEffect);
        bOff.addMouseListener(hoverEffect);
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
        bOff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                offButtonPressed();
            }
        });

        buttons = new JButton[] {
                b1, b2, b3, bAdd,
                b4, b5, b6, bSub,
                b7, b8, b9, bMul,
                bNeg, b0, bP, bDiv,
                bDel, bClr, bEqu, bOff
        };

        int yOff = textFieldHeight + BAR_HEIGHT;
        int bw = (int) (width / BUTTON_COLS);
        int bh = (int) (height + BAR_HEIGHT - yOff) / BUTTON_ROWS;
        final Font fontButtons = new Font("SansSerif", Font.BOLD, (int) (bh * 0.3));

        for (int i = 0; i < NUM_BUTTONS; i++) {
            JButton b = buttons[i];
            b.setVisible(true);
            b.setBackground(new Color(200, 200, 200));
            b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            b.setFont(fontButtons);
            b.setFocusable(false);

            int col = i % BUTTON_COLS;
            int row = Math.floorDiv(i, 4);

            b.setBounds(
                    col * bw, row * bh + yOff,
                    bw, bh);
            window.add(b);
        }
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

    private void onKeyPressed(KeyEvent keyEvent) {
        char key = keyEvent.getKeyChar();
        int keyCode = keyEvent.getExtendedKeyCode();
        if (Character.isDigit(key) || key == '.') {
            numberButtonPressed(key + "");
        }
        if (key == ',') {
            numberButtonPressed(".");
        }
        if (key == '+' || key == '-' || key == '*' || key == '/') {
            operatorButtonPressed(key + "");
        }
        if (keyCode == KeyEvent.VK_BACK_SPACE) {
            deleteButtonPressed();
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            offButtonPressed();
        }
        if (key == 'c') {
            clearButtonPressed();
        }
        if (key == '=' || keyCode == KeyEvent.VK_ENTER) {
            equalsButtonPressed();
        }
    }
}