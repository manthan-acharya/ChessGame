import java.awt.*;
import java.util.Scanner;


public class ChessGame
{
    Scanner input = new Scanner(System.in);

    public void playGame()
    {
        // Load the presets
        PresetManager.loadPresets();


        // select a preset and load the board
        ChessBoard board = new ChessBoard(PresetManager.getPreset("default"));

        // Check if the game has been won, if not, continue playing
        while(!hasWon())
        {
            // Print the board
//            System.out.println(board);

            // Get user move
            String userMove = getMove();

            // Check if the input is valid, if not, prompt the user again
            while(!isValidChessNotation(userMove))
            {
                System.out.println("Invalid input, please try again");
                userMove = getMove();

            }

            // Get the start and end points from the notation
            Point[] notationPoints = getNotationPoints(userMove);

            // Check if the play is valid


            // Play the move
            board.movePiece(notationPoints[0], notationPoints[1]);


            // Print the board
            System.out.println(board);
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

    /**
     * Prompts the user for a move, and returns user input
     * @return (String) - the user input
     */
    private String getMove()
    {
        System.out.println();
        System.out.print("Enter a move: ");
        return input.nextLine();
    }

    /**
     * Prints the notation key used in this game
     */
    public static void printNotationKey()
    {
        System.out.println("Notation Key (not exactly the same as regular chess notation): ");
        System.out.println("  - The notation is two pairs of coordinates.");
        System.out.println("  - The first pair is the start location, the second pair is the end location.");
        System.out.println("  - The coordinate format is as follows (letter)(number).");
        System.out.println("  - Next to the board is a key showing the letter and number for each square.");
        System.out.println("  - The letter is the column ranging from a-h, the number is the row ranging from 1-8.");
        System.out.println("\nAn example of this notation is: d2d4 which means move piece at d2 to d4.");
    }

    /**
     * Gets the start and end points from the notation
     * @param notation (String) - the notation to get the points from
     * @return (Point[]) - the start point (index 0) and end point (index 1)
     */
    public Point[] getNotationPoints(String notation)
    {
        Point start = new Point();
        Point end = new Point();

        // Capitalize the notation, this is to prevent errors while checking against the valid columns
        notation = notation.toUpperCase();

        // Get the column for each point (start at 0)
        String validColumns = "ABCDEFGH";
        start.x = validColumns.indexOf(notation.charAt(0));
        end.x = validColumns.indexOf(notation.charAt(2));

        // Get the row for each point (start at 0)
        start.y = Character.getNumericValue(notation.charAt(1) - 1);
        end.y = Character.getNumericValue(notation.charAt(3) - 1);

        return new Point[]{start, end};
    }

    /**
     * Checks if the given input is a valid chess notation
     * @param input (String) - the input to check
     * @return (boolean) - if the input is valid
     */
    public boolean isValidChessNotation(String input)
    {
        // Check if the input is 4 characters long
        if(input.length() !=  4)
        {
            return false;
        }

        // Check if the first point are valid (a-h, 1-8)
        if (!(isValidColumnInput(input.charAt(0)) && isValidRowInput(input.charAt(1))))
        {
            return false;
        }

        // Check if the second point are valid (a-h, 1-8)
        if (!(isValidColumnInput(input.charAt(2)) && isValidRowInput(input.charAt(3))))
        {
            return false;
        }

        // return true if all the checks pass
        return true;
    }

    /**
     * Checks if the given input is a valid column
     * @param input (char) - the input to check
     * @return (boolean) - if the input is within a-h
     */
    private boolean isValidColumnInput(char input)
    {
        String validColumns = "ABCDEFGH";
        return validColumns.contains(Character.toString(input).toUpperCase());
    }

    /**
     * Checks if the given input is a valid row
     * @param input (char) - the input to check
     * @return (boolean) - if the input is within 1-8
     */
    private boolean isValidRowInput(char input)
    {
        int row = Character.getNumericValue(input);
        return (Character.isDigit(input)) && (row >=  1 && row <=  8);
    }
}