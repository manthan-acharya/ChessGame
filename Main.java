import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
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
        // Create a board for each chess piece
        ArrayList<ChessBoard> boards = new ArrayList<>();

        ArrayList<ChessPiece> pieces = new ArrayList<>();
        for(ChessPiece.ChessPieceType type : ChessPiece.ChessPieceType.values())
        {
            pieces.add(new ChessPiece(type));
        }

        for(ChessPiece piece : pieces)
        {
            boards.add(new ChessBoard());
        }

        for(int i = 0; i < pieces.size(); i++)
        {
            ChessPiece piece = pieces.get(i);
            ChessBoard board = boards.get(i);

            // get all possible moves for piece
            Point[] moves = piece.getValidMoves(new Point(3, 3));

            // Add all moves to board
            for (Point move : moves)
            {
                board.getBoard()[move.x][move.y] = new ChessPiece(piece.getType());
            }

            // Print board
            System.out.println(board);
        }
    }
}