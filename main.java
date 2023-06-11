import java.io.Console;
public class main
{
    public static void main(String[] args) {
        //-------------------- used to generate presets --------------------
//        char[][] board = {
//                {'♜', '♞', '♝', '♛', '♚', '♝', '♞', '♜'},
//                {'♟', '♟', '♟', '♟', '♟', '♟', '♟', '♟'},
//                {'0', '0', '0', '0', '0', '0', '0', '0'},
//                {'0', '0', '0', '0', '0', '0', '0', '0'},
//                {'0', '0', '0', '0', '0', '0', '0', '0'},
//                {'0', '0', '0', '0', '0', '0', '0', '0'},
//                {'♙', '♙', '♙', '♙', '♙', '♙', '♙', '♙'},
//                {'♖', '♘', '♗', '♕', '♔', '♗', '♘', '♖'}
//        };
//        // convert string[][] into chessPiece[][]
//        ChessPiece[][] chessBoard = new ChessPiece[8][8];
//        for (int row = 0; row<8; row++) {
//            for (int col = 0; col<8; col++) {
//                chessBoard[row][col] = new ChessPiece(ChessPiece.ChessPieceType.enumLookup(Character.toString(board[row][col])));
//            }
//        }
//        // serialize chessPiece[][] into a file
//        String fileName = "./presets/default.txt";
//        TextSerialize.serialize(chessBoard, fileName);

        //  load default preset
        ChessPiece[][] preset = TextSerialize.deserialize("./presets/default.txt");

        // create a new board with the preset
        ChessBoard board = new ChessBoard(preset);

        // print the board
        System.out.println(board); // implicit toString() call
    }
}
