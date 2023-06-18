import java.awt.Point;
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
        public static ChessPieceType getOppositeColor(ChessPieceType type)
        {
            // Get the string value of the type as an array (ex: "KING_WHITE" -> ["KING", "WHITE"])
            String[] typeString = type.toString().split("_");

            // Get the opposite color
            String oppositeColor = typeString[1].equals("WHITE") ? "BLACK" : "WHITE";

            // Return the opposite color type
            return ChessPieceType.valueOf(typeString[0] + "_" + oppositeColor);
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

        // Gets appropriate moves for the piece
        ArrayList<Point> moves = switch (type) {
            case "PAWN" -> pawnMoves(start, isWhite);
            case "ROOK" -> rookMoves(start, isWhite);
            case "KNIGHT" -> knightMoves(start, isWhite);
            case "BISHOP" -> bishopMoves(start, isWhite);
            case "QUEEN" -> queenMoves(start, isWhite);
            case "KING" -> kingMoves(start, isWhite);
            default -> null;
        };

        // prevent null pointer exception
        if(moves == null)
        {
            return null;
        }

        // Sanity Check: check if the point is within the 8x8 board
        ArrayList<Point> movesToRemove = new ArrayList<>();
        for(Point move : moves)
        {
            if((move.x < 0 || move.x > 7) || (move.y < 0 || move.y > 7))
            {
                movesToRemove.add(move);
            }
        }
            // Remove points that are not on the board
        moves.removeAll(movesToRemove);

        // Sanity Check: remove point if equal to start point
        moves.remove(start);


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
        moves.add(new Point(start.x, start.y + (direction)));

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
        ArrayList<Point> moves = new ArrayList<>();

        // Adds the forward moves
        moves.add(new Point(start.x + 1, start.y + 2));
        moves.add(new Point(start.x - 1, start.y + 2));

        // Adds the backward moves
        moves.add(new Point(start.x + 1, start.y - 2));
        moves.add(new Point(start.x - 1, start.y - 2));

        // Adds the left moves
        moves.add(new Point(start.x - 2, start.y + 1));
        moves.add(new Point(start.x - 2, start.y - 1));

        // Adds the right moves
        moves.add(new Point(start.x + 2, start.y + 1));
        moves.add(new Point(start.x + 2, start.y - 1));

        return moves;
    } // end knightMoves(Point, boolean)

    /**
     * Returns the all moves for a bishop piece.
     * @param start (Point) the starting location of the piece
     * @param isWhite (boolean) if the piece is white
     * @return (ArrayList<Point>) all moves for a bishop piece
     */
    private static ArrayList<Point> bishopMoves(Point start, boolean isWhite)
    {
        ArrayList<Point> moves = new ArrayList<>();

        // Adds diagonal left forward moves
        for(int i = start.x, j = start.y; i > 0 && j < 7; i--, j++)
        {
            moves.add(new Point(i - 1, j + 1));
        }

        // Adds diagonal right forward moves
        for(int i = start.x, j = start.y; i < 7 && j < 7; i++, j++)
        {
            moves.add(new Point(i + 1, j + 1));
        }

        // Adds diagonal left backward moves
        for(int i = start.x, j = start.y; i > 0 && j > 0; i--, j--)
        {
            moves.add(new Point(i - 1, j - 1));
        }

        // Adds diagonal right backward moves
        for(int i = start.x, j = start.y; i < 7 && j > 0; i++, j--)
        {
            moves.add(new Point(i + 1, j - 1));
        }

        return moves;
    } // end bishopMoves(Point, boolean)

    /**
     * Returns the all moves for a queen piece.
     * @param start (Point) the starting location of the piece
     * @param isWhite (boolean) if the piece is white
     * @return (ArrayList<Point>) all moves for a queen piece
     */
    private static ArrayList<Point> queenMoves(Point start, boolean isWhite)
    {
        ArrayList<Point> moves = new ArrayList<>();

        // adds all the rook moves
        moves.addAll(rookMoves(start, isWhite));

        // adds all the bishop moves
        moves.addAll(bishopMoves(start, isWhite));

        return moves;
    } // end queenMoves(Point, boolean)

    /**
     * Returns the all moves for a king piece.
     * @param start (Point) the starting location of the piece
     * @param isWhite (boolean) if the piece is white
     * @return (ArrayList<Point>) all moves for a king piece
     */
    private static ArrayList<Point> kingMoves(Point start, boolean isWhite)
    {
        ArrayList<Point> moves = new ArrayList<>();

        // Adds one move in each direction
        for(int i = start.x - 1; i <= start.x + 1; i++)
        {
            for(int j = start.y - 1; j <= start.y + 1; j++)
            {
                Point possibleMove = new Point(i, j);

                if(possibleMove.equals(start))
                {
                    continue;
                }
                moves.add(new Point(i, j));
            }
        }
        return moves;
    } // end kingMoves(Point, boolean)
}
