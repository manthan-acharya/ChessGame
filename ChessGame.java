import java.util.Scanner;


public class ChessGame
{
    Scanner input = new Scanner(System.in);

    public void playGame()
    {
        // Load the presets
        PresetManager.loadPresets();

        // select a preset and load the board
        ChessBoard board = new ChessBoard(PresetManager.getPreset("Default"));

        while(!hasWon())
        {
            // Print the board
            // Prompt the user for a move
            // Get the next move
            // Check if the input is valid notation
            // Check if the move is valid
            // Move the piece
            // Check if the game has been won
        }
    }

    /**
     * Checks if the game has been won
     * @return (boolean) - true if the game has been won, false otherwise
     */
    public boolean hasWon()
    {
        return false;
    }


    // Returns the next user inputted line in the terminal without a prompt
    private String getInput()
    {
        return input.nextLine();
    }

    private void promptInput(String prompt)
    {
        System.out.println();
        System.out.print(prompt);
        System.out.println(':');
    }

    /**
     * Prints the notation key used in this game
     */
    public static void printNotationKey()
    {
        System.out.println("Notation Key (not exactly the same as regular chess notation):");
        System.out.println("  - Each piece is represented by a letter");
        System.out.println("  - The letters are as follows:");
        System.out.println("    - K: King");
        System.out.println("    - Q: Queen");
        System.out.println("    - B: Bishop");
        System.out.println("    - N: Knight");
        System.out.println("    - R: Rook");
        System.out.println("    - P: Pawn");
        System.out.println("  - The letter is followed by the location of the piece");
        System.out.println("    - The location is represented by a letter then a number (The column and row)");
        System.out.println("\nAn example of this notation is: kd4 which means the white king is move to d4");
    }

    /**
     * Checks if the given input is a valid chess notation
     * @param input (String) - the input to check
     * @return (boolean) - if the input is valid
     */
    private boolean isValidChessNotation(String input)
    {
        // Check if the input is 3 characters long
        if(input.length() != 3)
        {
            return false;
        }

        // Check if the first character is a letter (K, Q, B, N, R, P)
        String validPieces = "KQBNRP";
        if(!validPieces.contains(input.substring(0, 1).toUpperCase()))
        {
            return false;
        }

        // Check if the second character is a letter (a-h)
        String validColumns = "ABCDEFGH";
        if(!validColumns.contains(input.substring(1, 2).toUpperCase()))
        {
            return false;
        }

        // Check if the third character is a number (1-8)
        if(Character.isDigit(input.charAt(2)) && ((int) input.charAt(2) >=  1 && (int) input.charAt(2) <=  8))
        {
            return false;
        }

        return true;
    }
}