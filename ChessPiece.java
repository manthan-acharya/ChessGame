public class ChessPiece {

        //unicode for chess pieces
        public enum ChessPieceType {
                KING_WHITE("\u2654"), QUEEN_WHITE("\u2655"), ROOK_WHITE("\u2656"),
                BISHOP_WHITE("\u2657"), KNIGHT_WHITE("\u2658"), PAWN_WHITE("\u2659"),
                KING_BLACK("\u265A"), QUEEN_BLACK("\u265B"), ROOK_BLACK("\u265C"),
                BISHOP_BLACK("\u265D"), KNIGHT_BLACK("\u265E"), PAWN_BLACK("\u265F");

                private String unicode;

                ChessPieceType(String unicode) {
                        this.unicode = unicode;
                }

                public String getUnicode() {
                        return unicode;
                }
        }

        private String type;
        private int[][] location;

        ChessPiece(ChessPieceType type, int[] location)
        {

        };

        public void setType(String type)
        {
                if()
                this.type = type;
        }




}
