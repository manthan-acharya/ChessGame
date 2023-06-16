import java.io.File;
import java.util.ArrayList;

public class PresetManager
{
    // folder where chess puzzles are stored
    private static final String presetsPath = "./presets/";

    // Arraylists containing the saves chessboards and their names (filename excluding extension)
    private static ArrayList<ChessPiece[][]> presets = new ArrayList<>();
    private static ArrayList<String> presetNames = new ArrayList<>();

    /**
     * Adds a chessboard preset to the presetsPath folder.
     * @param name (String) the name of the preset
     * @param preset (ChessPiece[][]) the chessboard to save
     */
    public static void addPreset(String name, ChessPiece[][] preset)
    {
        String fileName = presetsPath + name + ".txt";
        ChessSerialize.serialize(preset, fileName);
    } // end addPreset(String, ChessPiece[][])

    /**
     * Adds a chessboard char[][] preset to the presets folder
     * @param name (String) the name of the preset
     * @param preset (char[][]) the chessboard to save
     */
    public static void addPreset(String name, char[][] preset)
    {
        // Create new chessPiece[][] from char[][]
        ChessPiece[][] chessBoard = new ChessPiece[8][8];
        for (int row = 0; row<8; row++)
        {
            for (int col = 0; col<8; col++)
            {
                // Create new chessPiece from char, add to board
                chessBoard[row][col] = new ChessPiece(ChessPiece.ChessPieceType.enumLookup(Character.toString(preset[row][col])));
            }
        }
        // serialize chessPiece[][] into a file
        String fileName = presetsPath + name + ".txt";
        ChessSerialize.serialize(chessBoard, fileName);
    } // end addPreset(String, char[][])

    /**
     * Loads all presets from the presets folder into arraylist
     */
    public static void loadPresets()
    {
        // Read all files in presets folder
        File folder = new File(presetsPath);

        // Get all files in folder
        File[] files = folder.listFiles();

        // Files to process after validation
        ArrayList<File> validPresets = new ArrayList<>();

        // Check if all files are valid presets and add them to the validPresets list
        for(File file : files)
        {
            String[] fileContents = ChessSerialize.readFile(file.getAbsolutePath()).split("\n");
            // Check if file is valid
            if(isValidPreset(fileContents))
            {
                validPresets.add(file);
            } // else ignore file
        }

        // Serialize all valid presets and add them to the presets list
        for(File file : validPresets)
        {
            presets.add(ChessSerialize.deserialize(file.toString()));
            presetNames.add(file.getName().replace(".txt", ""));
        }
    } // end loadPresets()

    /**
     * Gets all presets.
     * loadPresets() MUST be called before this method.
     * @return (ArrayList<ChessPiece[][]>) all presets
     */
    public static ArrayList<ChessPiece[][]> getPresets()
    {
        return new ArrayList<>(presets);
    } // end getPresets()

    /**
     * Gets the names of all presets.
     * loadPresets() MUST be called before this method.
     * @return (ArrayList<String>) the names of all presets
     */
    public static ArrayList<String> getPresetNames()
    {
        return new ArrayList<>(presetNames);
    } // end getPresetNames()

    /**
     * Gets a ChessPiece[][] by name.
     * loadPresets() MUST be called before this method.
     * @param name (String) the name of the preset
     * @return (ChessPiece[][]) the preset (null if not found)
     */
    public static ChessPiece[][] getPreset(String name)
    {
        name = name.toLowerCase();
        int index = presetNames.indexOf(name);
        if(index ==  -1)
        {
            return null;
        }
        return presets.get(index);
    } // end getPreset(String)

    /**
     * Checks if given preset is valid
     * @return (boolean) if the preset is valid
     */
    private static boolean isValidPreset(String[] preset)
    {
        // check if preset has 8 rows
        if (preset.length != 8)
        {
            return false;
        }

        // check if each row is 8 chars long
        for (String row : preset)
        {
            if (row.length() != 8)
            {
                return false;
            }
        }

        // check if preset has valid characters
        for (String row : preset)
        {
            for (char piece : row.toCharArray())
            {
                // use enumLookup to see if enum has the piece
                if (ChessPiece.ChessPieceType.enumLookup(Character.toString(piece)) ==  null)
                {
                    return false;
                }
            }
        }

        // if all checks pass, return true
        return true;
    } // end isValidPreset(String[])
}
