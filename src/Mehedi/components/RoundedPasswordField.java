package components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class RoundedPasswordField extends JPasswordField {

    private Color normalBorder = new Color(210, 210, 210);
    private Color focusBorder = new Color(21, 101, 192);

    private String placeholder;
    private char defaultEchoChar;

    public RoundedPasswordField(String placeholder) {

        this.placeholder = placeholder;

        setFont(new Font("Segoe UI", Font.PLAIN, 15));
        setBackground(Color.WHITE);
        setForeground(new Color(40, 40, 40));
        setBorder(new LineBorder(normalBorder, 1, true));
        setMargin(new Insets(0, 12, 0, 12));

        defaultEchoChar = getEchoChar();

        showPlaceholder();

        addFocusListener(new java.awt.event.FocusAdapter() {

            @Override
            public void focusGained(java.awt.event.FocusEvent e) {

                setBorder(new LineBorder(focusBorder, 2, true));

                if (getText().equals(placeholder)) {

                    setText("");

                    setForeground(new Color(40, 40, 40));

                    setEchoChar(defaultEchoChar);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {

                setBorder(new LineBorder(normalBorder, 1, true));

                if (getPassword().length == 0) {

                    showPlaceholder();
                }
            }
        });
    }

    private void showPlaceholder() {

        setEchoChar((char) 0);

        setText(placeholder);

        setForeground(new Color(150, 150, 150));
    }
}