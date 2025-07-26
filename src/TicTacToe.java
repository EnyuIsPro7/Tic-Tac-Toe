import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {

    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    // Play Again button
    public JButton replayButton = new JButton("Play Again");

    JButton [][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;

    int turns = 0;

    TicTacToe(){
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        replayButton.setBackground(Color.green);
        replayButton.setForeground(Color.black);
        replayButton.setFont(new Font("Arial", Font.BOLD, 30));
        replayButton.setFocusable(false);
        replayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        frame.add(replayButton, BorderLayout.SOUTH);

        for (int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                                currentPlayer = currentPlayer == playerX ?      playerO : playerX;
                            textLabel.setText(currentPlayer + "'s turn");
                            }                          
                        }
                    }
                });
            }
        }
    }
    void checkWinner(){
        // horizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "") continue;

            if (board[r][0].getText() == board[r][1].getText() &&
                board[r][1].getText() == board[r][2].getText()) {
                    for (int i = 0; i < 3; i++){
                        setWinner(board[r][i]);
                    }
                gameOver = true;
                return;
            }
        }
        // vertical
        for(int c = 0; c < 3;c ++){
            if (board[0][c].getText() == "") continue;

            if (board[0][c].getText() == board[1][c].getText() &&
                board[1][c].getText() == board[2][c].getText()) {
                    for (int i = 0; i < 3; i++){
                        setWinner(board[i][c]);
                    }
                gameOver = true;
                return;
            }
        }

        // diagonally
        if (board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() &&
            board[0][0].getText() != "") {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][i]);
                }
                gameOver = true;
                return;
        }
        // anti-diagonally
        if (board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != "") {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][2 - i]);
                }
                gameOver = true;
                return;
        }
        if (turns == 9){
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
            
        }
    }

    void setWinner(JButton tile){
        tile.setBackground(Color.gray);
        tile.setForeground(Color.green);
        textLabel.setText(currentPlayer + " is the winner!");
    }

    void setTie(JButton tile){
        tile.setBackground(Color.gray);
        tile.setForeground(Color.orange);
        textLabel.setText("Tie!");
    }
    void resetGame() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");
                board[r][c].setBackground(Color.darkGray);
                board[r][c].setForeground(Color.white);
            }
        }
        gameOver = false;
        turns = 0;
        currentPlayer = playerX;
        textLabel.setText("Tic-Tac-Toe");
    }
}