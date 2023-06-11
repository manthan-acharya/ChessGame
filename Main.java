import java.util.Objects;

public class Main
{
    public static void main(String[] args) {

        // Load the presets
        PresetManager.loadPresets();

        // Print out the names of the presets
        for(String name : PresetManager.getPresetNames())
        {
            System.out.println(name);
        }
        System.out.println();

        // Create a new chess board with the default preset
        ChessBoard board = new ChessBoard(PresetManager.getPreset("default"));

        // Print out the board
        System.out.println(board);
    }
}
