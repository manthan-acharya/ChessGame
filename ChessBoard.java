//chess board class which holds the chess pieces in a chess board object

import java.awt.*;

public class ChessBoard
{
    // Board[row][col]
   private ChessPiece[][] board;

   /**
    * Creates a new chess board with the given board
    * @param copyBoard (ChessPiece[][]) - the board to clone this board to
    */
   public ChessBoard(ChessPiece[][] copyBoard)
   {
       // Create a copy of the board without a memory reference
      this.board = new ChessPiece[copyBoard.length][copyBoard[0].length];

       // Iterate through each row of the board
       for(int row = 0; row < copyBoard.length; row++)
       {
           // Iterate through each column of the board
           for(int col = 0; col < copyBoard[row].length; col++)
           {
             // Get the piece at the current location
             ChessPiece piece = copyBoard[row][col];

             this.board[row][col] = new ChessPiece(piece.getType());
           }
       }
   } // end setBoard(ChessPiece[][]);

    /**
     * Creates a new chess board with an empty board
     */
    public ChessBoard()
   {
       board = new ChessPiece[8][8];

       // set each piece to empty to avoid null pointer exceptions
       for(int row = 0; row < board.length; row++)
       {
           for(int col = 0; col < board[row].length; col++)
           {
               board[row][col] = new ChessPiece(ChessPiece.ChessPieceType.EMPTY);
           }
       }
   } // end ChessBoard();

    /**
     * Gets this chess board
     * @return (ChessPiece[][]) - this board
     */
   public ChessPiece[][] getBoard()
   {
      return board;
   } // end getBoard();

    /**
     * Moves a piece from one location to another
     * @param pieceLocation (Point) - the location of the piece to move
     * @param dest (Point) - the location to move the piece to
     * @implNote will overwrite the piece at the destination, no safety checks are performed
     */
    public void movePiece(Point pieceLocation, Point dest)
    {
        // Invert the y values of the points
        pieceLocation = invertPointY(pieceLocation);
        dest = invertPointY(dest);

        // Move the piece by changing the type of the piece at the destination to the type of the piece at the piece location
        ChessPiece.ChessPieceType pieceType = board[pieceLocation.y][pieceLocation.x].getType(); // get type before overwriting
        board[pieceLocation.y][pieceLocation.x].setType(ChessPiece.ChessPieceType.EMPTY); // overwrite piece
        board[dest.y][dest.x].setType(pieceType); // set destination to type
    }

    /**
     * Inverts the y value of a point.
     * This is used because the board is stored in a 2D array, and the array is indexed from the top left.
     * @param point (Point) - the point to invert
     * @return (Point) - the inverted point
     */
    private Point invertPointY(Point point)
    {
        return new Point(point.x, 7 - point.y);
    }

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
            boardString.append("\n  ").append(rowDivider()).append("\n");
        }
        // add column numbering
        boardString.append("    a   b   c   d   e   f   g   h\n");

        return boardString.toString();
    } // end toString();

    public boolean isPieceWhite(Point location)
    {
        // Invert the y value of the point
        location = invertPointY(location);

        String pieceEnumName = board[location.y][location.x].getType().toString();
        return pieceEnumName.contains("WHITE");
    }

    /**
     * Determines if a piece is present at a given location
     * @param location (Point) - the location to check
     * @return (boolean) - if a piece is at the location
     */
    public boolean isLocationOccupied(Point location)
    {
        // Invert the y value of the point
        location = invertPointY(location);

        // Get the piece at the location
        ChessPiece pieceAtLocation = board[location.y][location.x];

        // Check if the piece is empty
        boolean isPieceEmpty = pieceAtLocation.getType().equals(ChessPiece.ChessPieceType.EMPTY);
        return !isPieceEmpty;
    } // end isLocationOccupied(Point);

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
        rowDivider = rowDivider.repeat(numberOfDashes);
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