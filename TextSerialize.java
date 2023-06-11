import java.io.*;

public class TextSerialize
{
    /**
     * main method in TextSerialize class.
     * This method is used for development testing.
     * @param args (String[]) command line arguments
     */
    public static void main(String[] args)
    {
        testSerialAndDeserialize();
    } // end main(String[]);

    /**
     * Tests if the serialize and deserialize methods create the same board.
     * Deserializes board from file, serializes it to a new file, and compares the two.
     */
    private static void testSerialAndDeserialize()
    {
        // Create a chess board from file
        ChessPiece[][] board = deserialize("./test.txt");

        // Create a new file from previous board (Should be the same as test.txt)
        serialize(board, "./test2.txt");

        // Check if the boards are equal by looping through each piece
        boolean isEqual = areFilesEqual("./test.txt", "./test2.txt");

        // Print the result
        System.out.println("Serialized and deserialized boards work properly: " + isEqual);
    } // end testSerialAndDeserialize();

    /**
     * Saves the current state of the chess board to a text file.
     * This method will overwrite the file if it already exists.
     * @param board (ChessPiece[][]) the chess board to be saved
     * @param absolutePath (String) the file path to save the chess board to
     */
    public static void serialize(ChessPiece[][] board, String absolutePath)
    {
        // Deletes the file if it already exists
        deleteFile(absolutePath);

        // Iterate through each row of the chess board
        for(ChessPiece[] row : board)
        {
            // Create a string of the row
            StringBuilder rowString = new StringBuilder();

            // Iterate through each piece in the row and append to string
            for(ChessPiece piece : row)
            {
                rowString.append(piece.getType().getUnicode());
            }
            // If the file fails to save, return
            if(!appendToFile(absolutePath, rowString + "\n"))
            {
                return;
            }
        }
    } // end serialize(ChessPiece[][], String);

    /**
     * Deserializes a text file into a chess board
     * @param absolutePath (String) the file to deserialize
     * @return (ChessPiece[][]) the chess board
     */
    public static ChessPiece[][] deserialize(String absolutePath)
    {
        ChessPiece[][] board = new ChessPiece[8][8];
        String[] fileContents = readFile(absolutePath).split("\n");

        // Get the number of rows and columns in the file
        int numberOfRows = fileContents.length;
        int numberOfColumns = fileContents[0].length();

        // Iterate through each row and column
        for(int row = 0; row < numberOfRows; row++)
        {
            for(int column = 0; column < numberOfColumns; column++)
            {
                // Get the piece at the current position as a string
                String pieceAtPos = String.valueOf(fileContents[row].charAt(column));

                // Get the enum value of the piece
                ChessPiece piece = new ChessPiece(ChessPiece.ChessPieceType.enumLookup(pieceAtPos));

                // Add the piece to the board
                board[row][column] = piece;
            }
        }
        return board;
    } // end deserialize(String);

    /**
     * Reads a file and returns the contents as a string (lines separated by \n)
     * @param absolutePath (String) the file to read
     * @return (String) the contents of the file
     */
    public static String readFile(String absolutePath)
    {
        StringBuilder line = new StringBuilder();
        try
        {
            // Create a file reader and buffered reader
            FileReader fr = new FileReader(absolutePath);
            BufferedReader br = new BufferedReader(fr);

            // 8 lines for 8 rows in a chess board
            for(int i = 0; i < 8; i++)
            {
                // Append each line to the string
                line.append(br.readLine()).append("\n");
            }
            br.close();
            return line.toString();
        }
        // log any errors reading the file
        catch(IOException e)
        {
            System.out.println("Error reading file: " + e);
        }
        return line.toString();
    } // end readFile(String);

    /**
     * Appends a string to a file
     * @param absolutePath (String) the file to append to
     * @param text (String) the text to append
     * @return (boolean) if the file was successfully appended to
     */
    private static boolean appendToFile(String absolutePath, String text)
    {
        try
        {
            // Create a file writer and buffered writer for appending
            FileWriter fw = new FileWriter(absolutePath, true);
            BufferedWriter bw = new BufferedWriter(fw);

            // Append the text to the file
            bw.write(text);

            // Close the buffered writer and file writer
            bw.close();
            fw.close();
        }
        catch(IOException e) // log any errors appending to the file
        {
            System.out.println("Error saving to file: " + e);
            return false;
        }
        return true;
    } // end appendToFile(String, String);

    /**
     * Deletes a file
     * @param absolutePath (String) the file to delete
     * @return (boolean) if the file was successfully deleted
     */
    private static boolean deleteFile(String absolutePath)
    {
        File file = new File(absolutePath);
        return file.delete();
    } // end deleteFile(String);

    /** Reads and compares if two files are equal contents
     * @param file1 (String) the first file to compare
     * @param file2 (String) the second file to compare
     * @return (boolean) if the files are equal
     */
    private static boolean areFilesEqual(String file1, String file2)
    {
        // Read the contents of each file
        String file1Contents = readFile(file1);
        String file2Contents = readFile(file2);

        // Compare the contents of each file
        return file1Contents.equals(file2Contents);
    } // end areFilesEqual(String, String);
}

