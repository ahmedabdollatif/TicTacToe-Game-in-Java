import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        // take the size from the user
        Scanner in = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = in.nextInt();

        // create an n*n-sized 2D array of '.' char
        char arr[][] = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = '.';
            }
        }

        char pl = 'x'; // for the first player
        int sz = n*n; // the size of the array

        while (sz > 0){
            // create 2 variables for the positions (x,y) that the user inputs
            int x,y;

            // ask the player to input position (x,y)
            System.out.printf("Player %c turn.",pl);
            System.out.print("Enter empty location (x,y): ");
            x = in.nextInt(); y = in.nextInt();

            // if the position is not empty
            if(arr[x][y]!='.'){
                System.out.println("Non-empty position. Try again.");
                continue; // skip every thing and start again
            }

            // if it was an empty position
            arr[x][y] = pl; // change '.' to either 'x' or 'o'

            // check if the player wins
            int win1 = 0; // ups and downs
            int win2 = 0; // rights and lefts
            int win3 = 0; // top rights&lefts & down rights&lefts

            for (int i = 1-n; i < n; i++) {
                // check that it does not exceed the max left or the max right
                if(x+i >=0 && x+i < n)
                    // if the ones next to is the same
                    if(arr[x+i][y] == pl) win1++;

                // check that it does not exceed the max top or the max bottom
                if(y+i >=0 && y+i < n)
                    // if the ones up/down to is the same
                    if(arr[x][y+i] == pl) win2++;

                // check the top right/left or bottom right/left
                if(y+i >=0 && y+i < n && x+i >=0 && x+i < n)
                    if(arr[x+i][y+i] == pl) win3++;
            }

            // show the table
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }

            boolean win = win1==n || win2==n || win3==n;
            if(win)
            {
                System.out.printf("Player %c won!\n", pl);
                break; // the loop
            }

            // otherwise
            sz--;
            if(pl == 'x') pl = 'o';
            else pl = 'x';

            System.out.println();
        }

        // if none won after all of that
        if(sz < 1)
            System.out.println("Tie!");
        return;
    }
}
