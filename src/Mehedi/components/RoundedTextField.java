package components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class RoundedTextField extends JTextField {

    private Color normalBorder = new Color(210, 210, 210);
    private Color focusBorder = new Color(21, 101, 192);
    private String placeholder;

    public RoundedTextField(String placeholder) {

        this.placeholder = placeholder;

        setFont(new Font("Segoe UI", Font.PLAIN, 15));
        setBackground(Color.WHITE);
        setForeground(new Color(40, 40, 40));
        setBorder(new LineBorder(normalBorder, 1, true));
        setMargin(new Insets(0, 12, 0, 12));

        showPlaceholder();

        addFocusListener(new java.awt.event.FocusAdapter() {

            @Override
            public void focusGained(java.awt.event.FocusEvent e) {

                setBorder(new LineBorder(focusBorder, 2, true));

                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(new Color(40, 40, 40));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {

                setBorder(new LineBorder(normalBorder, 1, true));

                if (getText().isEmpty()) {
                    showPlaceholder();
                }
            }
        });
    }

    private void showPlaceholder() {

        setText(placeholder);
        setForeground(new Color(150, 150, 150));
    }
}