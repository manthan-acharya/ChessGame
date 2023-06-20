import java.awt.*;
import java.util.Objects;
import java.util.Scanner;


public class ChessGame
{
    Scanner input = new Scanner(System.in);

    public void playGame()
    {
        // Load the presets
        PresetManager.loadPresets();


        // select a preset and load the board
        ChessBoard board = new ChessBoard(Objects.requireNonNull(PresetManager.getPreset("default")));

        boolean isWhiteTurn = true;//white goes first

        // Check if the game has been won, if not, continue playing
        while(!hasWon())
        {
            // Print the board
            System.out.println(board);

            // Get user move
            String userMove = getValidMove(isWhiteTurn, board);

            // Get the start and end points from the notation
            Point[] notationPoints = getNotationPoints(userMove);

            // Play the move
            board.movePiece(notationPoints[0], notationPoints[1]);

            // Switch turns
            isWhiteTurn = !isWhiteTurn;
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
     * Gets move from user, and does safety checks.
     * If not valid, prompts user for another move using recursion
     * @param isWhiteTurn (boolean) if it is white's turn
     * @return (String) - the user's move
     */
    private String getValidMove(boolean isWhiteTurn, ChessBoard board)
    {
        String move = getMove(isWhiteTurn);

        // Check if the notation is valid
        if(!isValidChessNotation(move))
        {
            System.out.println("Invalid notation, please try again");
            move = getValidMove(isWhiteTurn, board);
        }

        // Get the start and end points from the notation
        Point[] points = getNotationPoints(move);
        Point startPoint = points[0];
        Point endPoint = points[1];

        // Check if selected piece is empty
        if(board.isPieceEmpty(startPoint))
        {
            System.out.println("No piece selected, please try again");
            move = getValidMove(isWhiteTurn, board);
        }

        // Check if the piece is the correct color
        if(board.isPieceWhite(startPoint) !=  isWhiteTurn)
        {
            System.out.println("Wrong color selected, please try again");
            move = getValidMove(isWhiteTurn, board);
        }

        // Check if destination location is same color as start location
        if(board.isPieceSameColor(startPoint, endPoint))
        {
            System.out.println("Destination location cannot be the same color as start location, please try again");
            move = getValidMove(isWhiteTurn, board);
        }

        // Check if the move follows the piece's possible moves
        if(!board.isValidMove(startPoint, endPoint))
        {
            // Gets the piece, then the type, then just the name of the piece, then makes it lowercase
            String pieceName = board.getPiece(startPoint).getType().toString().split("_")[0].toLowerCase();
            System.out.printf("Invalid move for %s, please try again", pieceName);
            move = getValidMove(isWhiteTurn, board);
        }
        return move;
    }

    /**
     * Prompts the user for a move, and returns user input
     * @return (String) - the user input
     */
    private String getMove(boolean isWhiteTurn)
    {
        String color = isWhiteTurn ? "White" : "Black";
        System.out.println();
        System.out.print("Enter a move " + color + ": ");
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
        System.out.println("\nAn example of this notation is: d2d4 or D2D4 which means move piece at d2 to d4.");
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

        // Get the column for each point (starts at 0)
        String validColumns = "ABCDEFGH";
        start.x = validColumns.indexOf(notation.charAt(0));
        end.x = validColumns.indexOf(notation.charAt(2));

        // Get the row for each point (starts at 0)
        start.y = Character.getNumericValue(notation.charAt(1) - 1);
        end.y = Character.getNumericValue(notation.charAt(3) - 1);

        // Invert the y values, this is because the board is drawn from top to bottom, but the notation is from bottom to top
        start.y = 7 - start.y;
        end.y = 7 - end.y;

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