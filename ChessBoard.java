//chess board class which holds the chess pieces in a chess board object

import java.awt.*;

public class ChessBoard
{
   private ChessPiece[][] board;

   /**
    * Creates a new chess board with the given board
    * @param board (ChessPiece[][]) - the board to clone this board to
    */
   public ChessBoard(ChessPiece[][] board)
   {
      this.board = board.clone();
   } // end setBoard(ChessPiece[][]);

    /**
     * Creates a new chess board with an empty board
     */
    public ChessBoard()
   {
      board = new ChessPiece[8][8];
   } // end ChessBoard();

    /**
     * Gets this chess board
     * @return (ChessPiece[][]) - this board
     */
   public ChessPiece[][] getBoard()
   {
      return board;
   } // end getBoard();

   public void printBoard() {
        System.out.println("---------------------------------");
        for (int row = 0; row<8; row++) {
            for (int col = 0; col<=7; col++) {
                System.out.print("| ");
                Point location = new Point(row, col);
                if (isLocationOccupied(location))
                {
                    
                }
                else if ((row + col) % 2 == 0)
                {
                    System.out.print('■');
                }
                else
                {
                    System.out.print('□');
                }
                System.out.print(" ");
            }
            System.out.println("|");
            System.out.println("---------------------------------");
        }
    }

    /**
     * Determines if a piece is present at a given location
     * @param location (Point) - the location to check
     * @return (boolean) - if a piece is at the location
     */
    public boolean isLocationOccupied(Point location)
    {
        ChessPiece pieceAtLocation = board[location.x][location.y];
        boolean isPieceEmpty = pieceAtLocation.getType().equals(ChessPiece.ChessPieceType.EMPTY);
        return !isPieceEmpty;
    } // end isLocationOccupied(Point);

    /**
     * Converts this board to a string
     * @return (String) - the string representation of this board
     */
    public String toString()
    {
        // Create stringBuilder to append to, and add top row divider
        StringBuilder boardString = new StringBuilder();
        boardString.append("  ").append(rowDivider()).append("\n");

        // Iterate through each row of the board
        int rowNumber = board.length;
        for(ChessPiece[] row : board)
        {
            // Add row number and decrement it by one
            boardString.append(rowNumber--).append(" | ");

            // Iterate through each column of the board
            for(ChessPiece piece : row)
            {
                // Get piece character
                String chessPiece = piece.getType().getUnicode();

                // If the piece is empty, set the character to a space
                if(piece.getType().equals(ChessPiece.ChessPieceType.EMPTY))
                {
                    chessPiece = " ";
                }

                // Append the character of the piece to the string with a column divider
                boardString.append(chessPiece).append(" | ");
            }
            // add row divider
            boardString.append("  ").append(rowDivider()).append("\n");
        }
        // add column numbering
        boardString.append("    a   b   c   d   e   f   g   h\n");

        return boardString.toString();
    } // end toString();

    /**
     * Returns the row divider for this board.
     * Uses the length of the board to determine the number of dashes to add,
     * account for the column dividers and the spaces between the pieces.
     * @return (String) - the row divider
     */
    private String rowDivider()
    {
        // Calculate the number of dashes needed
        int numberOfDashes = (board.length * 4) + 1;

        // Create a stringBuilder to append to, and add the dashes
        String rowDivider = "-";
        rowDivider.repeat(numberOfDashes);
        return rowDivider.toString();
    } // end rowDivider();

    /**
     * Returns the piece at a given location
     * Uses ChessPiece memory location to compare pieces
     * @param piece (ChessPiece) - the piece to find
     * @return (Point) - the location of the piece
     */
    private Point getPieceLocation(ChessPiece piece)
    {
        // Iterate through each row of the board
        for(int boardRow = 0; boardRow < board.length; boardRow++)
        {
            // Iterate through each column of the board
            for(int boardCol = 0; boardCol < board[boardRow].length; boardCol++)
                {
                    // Compare the memory location of the piece to the memory location of the piece at the current location
                    if (piece ==  board[boardRow][boardCol])
                    {
                        return new Point(boardRow, boardCol);
                    }
                }
        }
        return null;
    } // end getPieceLocation(ChessPiece);
}
