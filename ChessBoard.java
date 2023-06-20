//chess board class which holds the chess pieces in a chess board object

import java.awt.*;

public class ChessBoard
{
    // Board[row][col]
   private final ChessPiece[][] board;

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
        // Move the piece by changing the type of the piece at the destination to the type of the piece at the piece location
        ChessPiece.ChessPieceType pieceType = board[pieceLocation.y][pieceLocation.x].getType(); // get type before overwriting
        board[pieceLocation.y][pieceLocation.x].setType(ChessPiece.ChessPieceType.EMPTY); // overwrite piece
        board[dest.y][dest.x].setType(pieceType); // set destination to type
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

    /**
     * Determines if a piece is empty at a given location
     * @param location (Point) - the location to check
     * @return (boolean) - if the piece is empty
     */
    public boolean isPieceEmpty(Point location)
    {
        String pieceEnumName = board[location.y][location.x].getType().toString();
        return pieceEnumName.contains("EMPTY");
    }

    /**
     * Determines if a piece is the same color at two locations
     * @param start (Point) - the start location
     * @param end (Point) - the end location
     * @return (boolean) - if the pieces are the same color
     */
    public boolean isPieceSameColor(Point start, Point end)
    {
        // Get the pieces at the start and end locations
        ChessPiece startPiece = board[start.y][start.x];
        ChessPiece endPiece = board[end.y][end.x];

        // Check if both pieces contain the same color
        String[] colors = {"WHITE", "BLACK", "EMPTY"};
        for (String color : colors)
        {
            // If both pieces contain the same color
            if(startPiece.getType().toString().contains(color) && endPiece.getType().toString().contains(color))
            {
                return true;
            }
        }
        // else false
        return false;
    }

    /**
     * Determines if a piece is white at a given location.
     * @implNote this method will return false for empty pieces
     * @param location (Point) - the location to check
     * @return (boolean) - if the piece is white
     */
    public boolean isPieceWhite(Point location)
    {
        String pieceEnumName = board[location.y][location.x].getType().toString();
        return pieceEnumName.contains("WHITE");
    }

    /**
     * Determines if a move is valid for a piece at given locations.
     * @implNote Must have a piece at the pieceLocation
     * @param pieceLocation (Point) - the location of the piece to move
     * @param dest (Point) - the location to move the piece to
     * @return (boolean) - if the move follows the rules of chess
     * */
    public boolean isValidMove(Point pieceLocation, Point dest)
    {
        // Get the piece at the piece location
        ChessPiece piece = board[pieceLocation.y][pieceLocation.x];

        Point[] validPoints = piece.getValidMoves(pieceLocation);
        boolean isValid = false;

        // Check if the destination is in the valid points array
        for(Point validPoint : validPoints)
        {
            if (dest.equals(validPoint))
            {
                isValid = true;
                break;
            }
        }

        // ------ Pawn Diagonal Special Case ------
        // Check if the destination is diagonal from the piece (x and y are one space away)
        int direction = isPieceWhite(pieceLocation) ? -1 : 1;
        boolean isDiagonal = Math.abs(dest.x - pieceLocation.x) ==  1 && pieceLocation.y + direction ==  dest.y;

        // Check if piece is a pawn and if the destination is diagonal (moving diagonally one space)
        if(piece.getType().toString().toUpperCase().contains("PAWN") && isDiagonal)
        {

            // Check if the destination is occupied by an enemy piece
            if(!isPieceSameColor(pieceLocation, dest))
            {
                isValid = true;
            }
        }

        return isValid;
    }

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
        return rowDivider;
    } // end rowDivider();

    /**
     * Returns the piece at a given location
     * @param location (Point) - the location to get the piece from
     * @return (ChessPiece) - the piece at the location (copy of object) or null if empty
     */
    public ChessPiece getPiece(Point location)
    {
        ChessPiece piece = board[location.y][location.x];

        // Return null if the piece is empty
        if(piece.getType().equals(ChessPiece.ChessPieceType.EMPTY))
        {
            return null;
        }

        // Return a new object of the piece at the location
        return new ChessPiece(piece);
    }
}