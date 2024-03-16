import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FourGameGUI extends javax.swing.JFrame {
    private final FourGame game;
    private final JButton[][] buttons;
    private JLabel statusLabel;
    private int boardSize;

    public FourGameGUI() {
        boardSize = chooseGridSize();
        game = new FourGame(boardSize);
        buttons = new JButton[boardSize][boardSize];
        initComponents();
    }

    private int chooseGridSize() {
//        String[] options = {"3x3", "5x5", "7x7"};
//        int response = JOptionPane.showOptionDialog(null, "Select the grid size", "Grid Size",
//                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
//                null, options, options[0]);
//
//        return switch (response) {
//            case 1 -> 5;
//            case 2 -> 7;
//            default -> 3;
//        };
        //setVisible(false);
        grid dialog = new grid(this);
        dialog.setVisible(true);
        return dialog.getSelectedSize();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                JButton button = new JButton();
                button.addActionListener(new ButtonListener(i, j));
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }

        statusLabel = new JLabel("Red's Turn");
        add(statusLabel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);

        pack();
        setSize(500, 500);
        setLocationRelativeTo(null);
    }

    private class ButtonListener implements ActionListener {
        private final int row;
        private final int col;

        public ButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            game.makeMove(row, col);
            updateBoard();
        }
    }

    private void updateBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                JButton button = buttons[i][j];
                int value = game.getFieldValue(i, j);
                button.setText(String.valueOf(value));
                if (value == 4) {
                    button.setBackground(game.getFieldOwner(i, j) == 1 ? Color.RED : Color.BLUE);
                }
            }
        }

        if (game.isGameOver()) {
            JOptionPane.showMessageDialog(this, game.getWinner(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
            game.reset();
            updateBoard();
        } else {
            statusLabel.setText(game.getCurrentPlayer() == 1 ? "Red's Turn" : "Blue's Turn");
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new FourGameGUI().setVisible(true));
    }
}