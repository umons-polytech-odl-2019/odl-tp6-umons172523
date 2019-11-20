package be.ac.umons.util;

/**
 * Utility class for printing in color
 */
public class ColorPrint {

    /**
     * Print error message in red.
     * @param message the message to display
     */
    public static void printError(String message) {
        System.out.println(AnsiColor.RED + message + AnsiColor.RESET);
    }

    /**
     * Print debug message in blue.
     * @param message the message to display
     */
    public static void printDebug(String message) {
        System.out.println(AnsiColor.BLUE + message + AnsiColor.RESET);
    }
}
