public class ChessPiece {

        //unicode for chess pieces
        public enum ChessPieceType {
                KING_WHITE("♔"), QUEEN_WHITE("♕"), ROOK_WHITE("♖"),
                BISHOP_WHITE("♗"), KNIGHT_WHITE("♘"), PAWN_WHITE("♙"),
                KING_BLACK("♚"), QUEEN_BLACK("♛"), ROOK_BLACK("♜"),
                BISHOP_BLACK("♝"), KNIGHT_BLACK("♞"), PAWN_BLACK("♟"), EMPTY("0");

                private String unicode;
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
        }

        private ChessPieceType type;

        ChessPiece(ChessPieceType type)
        {
                this.type = type;
        };


        public void setType(ChessPieceType pieceType)
        {
                this.type = type;
        }
        public ChessPieceType getType()
        {
                return type;
        }

        public int[][] getLocation(){
            return location;
        }


}
