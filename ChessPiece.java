import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChessPiece
{
    // unicode enum for chess pieces
    public enum ChessPieceType {
        KING_WHITE("♔"), QUEEN_WHITE("♕"), ROOK_WHITE("♖"),
        BISHOP_WHITE("♗"), KNIGHT_WHITE("♘"), PAWN_WHITE("♙"),
        KING_BLACK("♚"), QUEEN_BLACK("♛"), ROOK_BLACK("♜"),
        BISHOP_BLACK("♝"), KNIGHT_BLACK("♞"), PAWN_BLACK("♟"), EMPTY("0");

        private final String unicode;
        ChessPieceType(String unicode) {
            this.unicode = unicode;
        }

        public String getUnicode()
        {
            return unicode;
        }
        public static ChessPieceType enumLookup(String value)
        {
            // Iterate through each enum value
            for(ChessPiece.ChessPieceType type : ChessPiece.ChessPieceType.values())
            {
                // If the unicode value matches the value passed in, return the enum
                if(type.getUnicode().equals(value))
                {
                    return type;
                }
            }
            return null;
        }
    } // end ChessPieceType

    private ChessPieceType type;
    private boolean hasMoved = false;

    /**
     * Creates a new chess piece of a given type
     * @param type (ChessPieceType) the type of piece to create
     */
    ChessPiece(ChessPieceType type)
    {
        this.type = type;
    } // end ChessPiece(ChessPieceType)
    /**
     * Sets the type of this piece.
     * Can be used to upgrade pawns.
     * @param pieceType (ChessPieceType) the type of piece to set this
     */
    public void setType(ChessPieceType pieceType)
    {
        this.type = pieceType;
        hasMoved = true;
    } // end setType(ChessPieceType)

    /**
     * Returns the type of the piece
     * @return (ChessPieceType) the type of this piece
     */
    public ChessPieceType getType()
    {
        return this.type;
    } // end getType()

    /**
     * If the piece has been moved since the instantiation of the piece
     * @return (boolean) if the piece has been moved (determined if setType has been called)
     */
    public boolean hasBeenMoved()
    {
        return hasMoved;
    }

    /**
     * Returns the all moves for a piece.
     * All moves also includes moves that may overlap with other pieces, but not moves that would go off the board.
     * @param start (Point) the starting location of the piece
     * @return (Point[]) all moves for a piece
     */
    public Point[] getValidMoves(Point start)
    {
        // Determines which side the piece is on
        boolean isWhite = this.getType().toString().contains("WHITE");

        // Determines the type of piece
        String type = this.getType().toString().split("_")[0];

        ArrayList<Point> moves;

        // Gets appropriate moves for the piece
        switch(type)
        {
            case "PAWN":
                moves = pawnMoves(start, isWhite);
            case "ROOK":
                moves = rookMoves(start, isWhite);
            case "KNIGHT":
                moves = knightMoves(start, isWhite);
            case "BISHOP":
                moves = bishopMoves(start, isWhite);
            case "QUEEN":
                moves = queenMoves(start, isWhite);
            case "KING":
                moves = kingMoves(start, isWhite);
            default:
                moves = null;
        }

        // Check if the point is within the 8x8 board
        for(Point move : moves)
        {
            if((move.x < 0 || move.x > 7) || (move.y < 0 || move.y > 7))
            {
                moves.remove(move);
            }
        }

        // convert ArrayList to array
        Point[] validMoves = new Point[moves.size()];
        validMoves = moves.toArray(validMoves);

        return validMoves;
    } // end getValidMoves(Point)

    /**
     * Returns the all moves for a pawn piece.
     * @param start (Point) the starting location of the piece
     * @param isWhite (boolean) if the piece is white
     * @return (ArrayList<Point>) all moves for a pawn piece
     */
    private static ArrayList<Point> pawnMoves(Point start, boolean isWhite)
    {
        // Creates an array of points to store the moves
        ArrayList<Point> moves = new ArrayList<>();

        // Determines the direction the pawn moves
        int direction = isWhite ? 1 : -1;

        // Adds the forward move
        moves.add(new Point(start.x, start.y + direction));

        // Adds the double forward move
        moves.add(new Point(start.x, start.y + (direction * 2)));

        // Adds the diagonal moves
        moves.add(new Point(start.x + 1, start.y + direction));
        moves.add(new Point(start.x - 1, start.y + direction));

        return moves;
    } // end pawnMoves(Point, boolean)

    /**
     * Returns the all moves for a rook piece.
     * @param start (Point) the starting location of the piece
     * @param isWhite (boolean) if the piece is white
     * @return (ArrayList<Point>) all moves for a rook piece
     */
    private static ArrayList<Point> rookMoves(Point start, boolean isWhite)
    {
        // Creates an array of points to store the moves
        ArrayList<Point> moves = new ArrayList<>();

        // Adds the forward moves
        for(int i = start.y; i < 7; i++)
        {
            moves.add(new Point(start.x, i + 1));
        }

        // Adds the backward moves
        for(int i = start.y; i > 0; i--)
        {
            moves.add(new Point(start.x, i - 1));
        }

        // Adds the left moves
        for(int i = start.x; i > 0; i--)
        {
            moves.add(new Point(i - 1, start.y));
        }

        // Adds the right moves
        for(int i = start.x; i < 7; i++)
        {
            moves.add(new Point(i + 1, start.y));
        }

        return moves;
    } // end rookMoves(Point, boolean)

    /**
     * Returns the all moves for a knight piece.
     * @param start (Point) the starting location of the piece
     * @param isWhite (boolean) if the piece is white
     * @return (ArrayList<Point>) all moves for a knight piece
     */
    private static ArrayList<Point> knightMoves(Point start, boolean isWhite)
    {
        return null;
    } // end knightMoves(Point, boolean)

    /**
     * Returns the all moves for a bishop piece.
     * @param start (Point) the starting location of the piece
     * @param isWhite (boolean) if the piece is white
     * @return (ArrayList<Point>) all moves for a bishop piece
     */
    private static ArrayList<Point> bishopMoves(Point start, boolean isWhite)
    {
        return null;
    } // end bishopMoves(Point, boolean)

    /**
     * Returns the all moves for a queen piece.
     * @param start (Point) the starting location of the piece
     * @param isWhite (boolean) if the piece is white
     * @return (ArrayList<Point>) all moves for a queen piece
     */
    private static ArrayList<Point> queenMoves(Point start, boolean isWhite)
    {
        return null;
    } // end queenMoves(Point, boolean)

    /**
     * Returns the all moves for a king piece.
     * @param start (Point) the starting location of the piece
     * @param isWhite (boolean) if the piece is white
     * @return (ArrayList<Point>) all moves for a king piece
     */
    private static ArrayList<Point> kingMoves(Point start, boolean isWhite)
    {
        return null;
    } // end kingMoves(Point, boolean)
}
