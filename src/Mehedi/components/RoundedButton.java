package components;

import java.awt.*;
import javax.swing.*;

public class RoundedButton extends JButton{

    public RoundedButton(String text){

        super(text);

        setFocusPainted(false);

        setForeground(Color.WHITE);

        setBackground(new Color(21,101,192));

        setFont(new Font("Segoe UI",Font.BOLD,16));

        setBorderPainted(false);

        setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

}