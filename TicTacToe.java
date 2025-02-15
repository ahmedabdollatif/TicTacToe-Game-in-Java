import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame {
    // Game state variables
    private int n;                 // Board size (n x n)
    private char[][] board;        // Game board representation
    private JButton[][] buttons;   // Button grid representing board cells
    private char currentPlayer;    // Current player ('x' or 'o')
    private int spacesLeft;        // Count of remaining moves
    private JLabel statusLabel;    // Displays current status and messages

    // Constructor: Sets up the game and GUI
    public TicTacToe() {
        super("Tic Tac Toe");
        // Prompt user to select a board size (n >= 3)
        while (true) {
            String input = JOptionPane.showInputDialog(this,
                    "Enter board size (n, where n>=3, e.g., 3 for 3x3):",
                    "Board Size", JOptionPane.QUESTION_MESSAGE);
            if (input == null) { // User canceled the input dialog
                System.exit(0);
            }
            try {
                n = Integer.parseInt(input);
                if (n >= 3) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Board size must be at least 3. Please try again.",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Invalid input. Please enter an integer for board size.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Initialize game state
        board = new char[n][n];
        buttons = new JButton[n][n];
        currentPlayer = 'x';
        spacesLeft = n * n;
        initializeBoard();

        // Set up the GUI components
        initGUI();
    }

    // Initialize the board with default values
    private void initializeBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
    }

    // Set up the GUI: create board panel, status label, etc.
    private void initGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create and set up the status label at the top
        statusLabel = new JLabel("Player " + currentPlayer + "'s turn");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(statusLabel, BorderLayout.NORTH);

        // Create panel to hold the grid of buttons
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(n, n));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                JButton btn = new JButton("");
                btn.setFont(new Font("Arial", Font.BOLD, 32));
                buttons[i][j] = btn;
                // Capture row and column for use in the action listener
                int row = i;
                int col = j;
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        makeMove(row, col);
                    }
                });
                boardPanel.add(btn);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        // Finalize the frame setup
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method invoked when a cell button is clicked
    private void makeMove(int row, int col) {
        // Validate the move (cell must be unoccupied)
        if (board[row][col] != '.') {
            JOptionPane.showMessageDialog(this,
                    "Invalid move! Cell is already occupied.",
                    "Invalid Move", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Update the game board and disable the button
        board[row][col] = currentPlayer;
        buttons[row][col].setText(String.valueOf(currentPlayer));
        buttons[row][col].setEnabled(false);
        spacesLeft--;

        // Check if the current move wins the game
        if (checkWin(currentPlayer, row, col)) {
            statusLabel.setText("Player " + currentPlayer + " wins!");
            JOptionPane.showMessageDialog(this,
                    "Player " + currentPlayer + " wins!",
                    "Game Over", JOptionPane.INFORMATION_MESSAGE);
            disableAllButtons();
            askForNewGame();
            return;
        }

        // Check for a tie (no remaining moves)
        if (spacesLeft == 0) {
            statusLabel.setText("It's a tie!");
            JOptionPane.showMessageDialog(this,
                    "It's a tie!",
                    "Game Over", JOptionPane.INFORMATION_MESSAGE);
            askForNewGame();
            return;
        }

        // Switch to the other player and update status
        currentPlayer = (currentPlayer == 'x') ? 'o' : 'x';
        statusLabel.setText("Player " + currentPlayer + "'s turn");
    }

    // Disable all buttons (used when the game ends)
    private void disableAllButtons() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    // Prompt the user to start a new game or exit
    private void askForNewGame() {
        int option = JOptionPane.showConfirmDialog(this,
                "Do you want to play again?",
                "New Game", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    // Reset the game: optionally allow the user to change the board size
    private void resetGame() {
        // Ask if the user wishes to keep the same board size
        int option = JOptionPane.showConfirmDialog(this,
                "Do you want to play with the same board size (" + n + "x" + n + ")?",
                "New Game", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.NO_OPTION) {
            // Prompt for a new board size
            while (true) {
                String input = JOptionPane.showInputDialog(this,
                        "Enter board size (n, where n>=3):",
                        "Board Size", JOptionPane.QUESTION_MESSAGE);
                if (input == null) {
                    System.exit(0);
                }
                try {
                    n = Integer.parseInt(input);
                    if (n >= 3) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Board size must be at least 3. Please try again.",
                                "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this,
                            "Invalid input. Please enter an integer for board size.",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        // Reset game variables and rebuild the GUI
        board = new char[n][n];
        buttons = new JButton[n][n];
        currentPlayer = 'x';
        spacesLeft = n * n;
        initializeBoard();

        getContentPane().removeAll();
        initGUI();
        revalidate();
        repaint();
    }

    // Check whether the current move has resulted in a win
    private boolean checkWin(char player, int lastRow, int lastCol) {
        // Check the row of the last move
        boolean winRow = true;
        for (int j = 0; j < n; j++) {
            if (board[lastRow][j] != player) {
                winRow = false;
                break;
            }
        }
        if (winRow) return true;

        // Check the column of the last move
        boolean winCol = true;
        for (int i = 0; i < n; i++) {
            if (board[i][lastCol] != player) {
                winCol = false;
                break;
            }
        }
        if (winCol) return true;

        // Check the main diagonal (top-left to bottom-right) if applicable
        if (lastRow == lastCol) {
            boolean winDiag1 = true;
            for (int i = 0; i < n; i++) {
                if (board[i][i] != player) {
                    winDiag1 = false;
                    break;
                }
            }
            if (winDiag1) return true;
        }

        // Check the anti-diagonal (top-right to bottom-left) if applicable
        if (lastRow + lastCol == n - 1) {
            boolean winDiag2 = true;
            for (int i = 0; i < n; i++) {
                if (board[i][n - 1 - i] != player) {
                    winDiag2 = false;
                    break;
                }
            }
            if (winDiag2) return true;
        }

        return false;
    }

    // Main method to launch the GUI application
    public static void main(String[] args) {
        // Ensure GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicTacToe();
            }
        });
    }
}

