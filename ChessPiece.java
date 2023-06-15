
import java.lang.Object;
import java.awt.geom.Point2D;
import java.awt.Point;
        
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

        private ChessPieceType type = ChessPieceType.EMPTY;
        private boolean hasbeenMoved = false;

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
                hasbeenMoved = true;
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
                return hasbeenMoved;
        }
}
