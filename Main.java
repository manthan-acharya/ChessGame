import java.awt.desktop.SystemSleepEvent;
import java.util.Objects;

public class Main
{
    public static void main(String[] args)
    {
//        testPrintPresets();
//        testPrintNotationKey();
        playGame();
    }

    public static void playGame()
    {
        ChessGame game = new ChessGame();
        game.playGame();
    }

    public static void testPrintPresets()
    {
        // load presets
        PresetManager.loadPresets();

        // print each preset name and the board
        for (String presetName : PresetManager.getPresetNames())
        {
            System.out.println(presetName);
            ChessBoard board = new ChessBoard(Objects.requireNonNull(PresetManager.getPreset(presetName)));
            System.out.println(board);
            System.out.println();
        }
    }

    public static void testPrintNotationKey()
    {
        ChessGame.printNotationKey();
    }
}