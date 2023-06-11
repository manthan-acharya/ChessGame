//chess board class which holds the chess pieces in a chess board object

public class ChessBoard {

   private ChessPiece[][] board;


   public ChessPiece[][] getBoard()
   {
      return board;
   }

   /**
    * Sets the chess board to a given board
    * @param board
    */
   public void setBoard(ChessPiece[][] board)
   {
      this.board = board.clone();
   }

   public ChessBoard() {
      board = new ChessPiece[8][8];
   }
   
   public void printBoard() {
        System.out.println("---------------------------------");
        for (int row = 0; row<8; row++) {
            for (int col = 0; col<=7; col++) {
                System.out.print("| ");
                if (isPieceAtLocation(row, col))
                {
                    
                }
                else if ((row + col) % 2 == 0)
                {
                    System.out.print('\u25A0');
                }
                else
                {
                    System.out.print('\u25A1');
                }
                System.out.print(" ");
            }
            System.out.println("|");
            System.out.println("---------------------------------");
        }
    }

    public boolean isPieceAtLocation(int row, int col)
    {
        for(ChessPiece[] boardRow : board){
            for(ChessPiece peice : boardRow)
                {
                    if (piece.getLocation() == {row, col}
                        return true;
                }
        }
        return false;
    }
}
