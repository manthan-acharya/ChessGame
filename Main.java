import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Main
{
    public static void main(String[] args)
    {
//        testPrintPresets();
//        testPrintNotationKey();
//        playGame();
//        testObscure();
        testChessPieceMoves();
    }

    public static void playGame()
    {
        ChessGame game = new ChessGame();
        game.playGame();
    }

    /**
     * Test method for obscure methods in development
     */
    public static void testObscure()
    {
        // Load the presets
        PresetManager.loadPresets();

        // select a preset and load the board
        ChessBoard board = new ChessBoard(Objects.requireNonNull(PresetManager.getPreset("default")));

        System.out.println(board);

        // print result of isPieceWhite
        System.out.println(board.isPieceWhite(new Point(0, 0)));
    }

    public static void testPrintPresets()
    {
        // load presets
        PresetManager.loadPresets();

        // print each preset name and the board
        for (String presetName : PresetManager.getPresetNames())
        {
            System.out.println(presetName);
            ChessBoard board = new ChessBoard(Objects.requireNonNull(PresetManager.getPreset(presetName)));
            System.out.println(board);
            System.out.println();
        }
    }

    public static void testPrintNotationKey()
    {
        ChessGame.printNotationKey();
    }

    public static void testChessPieceMoves()
    {
        // Create an array list of boards and pieces
        ArrayList<ChessBoard> boards = new ArrayList<>();
        ArrayList<ChessPiece> pieces = new ArrayList<>();

        // Add each chess piece type to pieces
        for(ChessPiece.ChessPieceType type : ChessPiece.ChessPieceType.values())
        {
            pieces.add(new ChessPiece(type));
        }

        // Create a board for each piece
        for(ChessPiece piece : pieces)
        {
            boards.add(new ChessBoard());
        }

        // Loop through number of pieces/boards, and print all possible moves for each piece
        for(int i = 0; i < pieces.size() - 1; i++)
        {
            // Get piece and board at index
            ChessPiece piece = pieces.get(i);
            ChessBoard board = boards.get(i);

            // original piece starting point to get possible moves
            Point startingPoint = new Point(7, 3);

            // invert point cuz board indexes as array (top left down), instead of chess (bottom left up)
            startingPoint.y = 7 - startingPoint.y;

            // get all possible moves for piece
            Point[] moves = piece.getValidMoves(startingPoint);

            // Add all moves to board as opposite color
            for (Point move : moves)
            {
                // Get opposite color
                ChessPiece.ChessPieceType oppositeColor = ChessPiece.ChessPieceType.getOppositeColor(piece.getType());

                // Add move as opposite color
                board.getBoard()[move.y][move.x] = new ChessPiece(oppositeColor);
            }

            // Add original starting point as original color
            board.getBoard()[startingPoint.y][startingPoint.x] = new ChessPiece(piece.getType());

            // Print piece name
            System.out.println(piece.getType());
            // Print board
            System.out.println(board);
        }
    }
}