# Tic Tac Toe

## Summary
The Tic Tac Toe application is a Java-based graphical game that allows two players to play Tic Tac Toe on a customizable board. The game supports boards of any size (n x n) where n is at least 3. It features an interactive user interface built with Java Swing, complete with real-time status updates and options to start a new game after a win or tie.

## Features
- **Customizable Board Size:** Players can choose the board size (n x n) with n ≥ 3.
- **Interactive GUI:** A clean and responsive interface using Java Swing.
- **Dynamic Gameplay:** Players take turns clicking on the grid cells to place their marks.
- **Real-Time Status Updates:** A status label displays whose turn it is, or announces the winner/tie.
- **Win/Tie Detection:** The game checks for winning conditions (rows, columns, and diagonals) after every move.
- **New Game Option:** After a game concludes, players can choose to restart with the same or a new board size.
- **Error Handling:** Prevents invalid moves by notifying users when a cell is already occupied.

## How to Use

### Running the Application
1. **Compile the Code:**
   ```bash
   javac TicTacToe.java
   ```
2. **Run the Application:**
   ```bash
   java TicTacToe
   ```

### Game Setup and Play
1. **Board Size Selection:**
   - When the application starts, a dialog will prompt you to enter a board size (n, where n ≥ 3).
   - Enter a valid integer value (e.g., 3 for a 3x3 board) and click **OK**.
2. **Gameplay:**
   - The game window will display a grid of buttons representing the board.
   - A status label at the top indicates which player's turn it is.
   - Click on an empty cell to place your mark ('x' or 'o').
3. **Winning and Tying:**
   - After each move, the game checks for a win or tie.
   - If a player wins, a message is displayed and all buttons are disabled.
   - If all cells are filled without a win, the game declares a tie.
4. **Starting a New Game:**
   - After a game concludes, a prompt will ask if you wish to play again.
   - You can choose to restart with the same board size or select a new board size.

## Prerequisites
- **Java Development Kit (JDK):** Ensure that JDK 8 or later is installed.
- **Java Swing:** This application uses Swing, which is included in the standard JDK.

## Example Usage
Upon launching the application, you will see:
- A dialog asking for the board size.
- A main game window displaying:
  - A status label at the top (e.g., "Player x's turn").
  - An n x n grid of buttons for the game board.
- Gameplay is performed by clicking on the grid buttons. For instance:
  - If Player x clicks on a cell, the cell is marked with 'x' and disabled.
  - The game then switches to Player o and updates the status label.
  - The game continues until a win or tie is detected.
- At the end of the game, a prompt appears asking if you wish to play again.

## Author
Developed by Ahmed Abdellatif.
