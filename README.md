# TicTacToe Game

## Summary
This Java program implements a console-based Tic-Tac-Toe game. Players take turns placing their symbols ('x' or 'o') on an n x n grid, aiming to achieve a row, column, or diagonal filled with their symbol. The program checks for a winner or a tie after each move.

## How to Play
1. Run the program.
2. Enter the size of the board (n) when prompted.
3. Players take turns entering coordinates (x, y) to place their symbol on the board.
4. The program displays the updated board after each move.
5. The game continues until a player wins, or the board is full, resulting in a tie.

## Code Overview
- User Input: Utilizes the Scanner class to receive user input for board size and move coordinates.
- Board Initialization: Creates an n x n 2D array representing the game board and initializes it with '.' characters.
- Player Turns: Alternates between 'x' and 'o' for each player's turn.
- Move Validation: Checks if the chosen position is empty before allowing a move. If the position is not empty, the player is prompted to try again.
- Win Conditions: Checks for win conditions after each move, including rows, columns, and diagonals.
- Display: Prints the current state of the board after each move.

Feel free to explore the code, modify it, and enjoy playing Tic-Tac-Toe in the console!
