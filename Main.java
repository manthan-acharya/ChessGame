import java.awt.*;
import java.awt.desktop.SystemSleepEvent;
import java.util.Objects;

public class Main
{
    public static void main(String[] args)
    {
//        testPrintPresets();
//        testPrintNotationKey();
        playGame();
//        testObscure();
    }

    public static void playGame()
    {
        ChessGame game = new ChessGame();
        game.playGame();
    }

    /**
     * Test method for obscure methods in development
     */
    public static void testObscure()
    {
        // Load the presets
        PresetManager.loadPresets();

        // select a preset and load the board
        ChessBoard board = new ChessBoard(Objects.requireNonNull(PresetManager.getPreset("default")));

        System.out.println(board);

        // print result of isPieceWhite
        System.out.println(board.isPieceWhite(new Point(0, 0)));
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