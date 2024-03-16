import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class grid extends JDialog {
    private int selectedSize = 3;

    public grid(JFrame parent) {
        super(parent, "Select Grid Size", true);
        setLayout(new FlowLayout());
        addButtons();
        pack();
        setLocationRelativeTo(parent);
    }

    private void addButtons() {
        JButton size3Button = new JButton("3x3");
        JButton size5Button = new JButton("5x5");
        JButton size7Button = new JButton("7x7");

        size3Button.addActionListener(e -> {
            selectedSize = 3;
            setVisible(false);
        });
        size5Button.addActionListener(e -> {
            selectedSize = 5;
            setVisible(false);
        });
        size7Button.addActionListener(e -> {
            selectedSize = 7;
            setVisible(false);
        });

        add(size3Button);
        add(size5Button);
        add(size7Button);
    }

    public int getSelectedSize() {
        return selectedSize;
    }
}
