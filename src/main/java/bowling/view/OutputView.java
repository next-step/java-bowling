package bowling.view;

import java.util.List;
import java.util.stream.IntStream;

public class OutputView {
    private static final String DELIMITER = "|";
    private static final String NAME_TITLE = "NAME";
    private static final int BLOCK_LENGTH = 6;

    public static void printResult(final String name, final List<String> fallenPinsSymbol) {
        printNameTitle();
        printFrames();
        printUsername(name);
        printFramesResults2(fallenPinsSymbol);
    }

    public static void printNameTitle() {
        Printer.print(DELIMITER + " " + NAME_TITLE + " " + DELIMITER);
    }

    public static void printFrames() {
        IntStream.range(1, 11)
                .forEach(OutputView::printFrame);
        Printer.printNewLine();
    }

    public static void printFrame(int frameNumber) {
        Printer.print("  " + String.format("%02d", frameNumber) + "  " + DELIMITER);
    }

    private static void printUsername(final String username) {
        Printer.print(DELIMITER + centerOrdering(username) + DELIMITER);
    }

    private static void printFramesResults2(final List<String> fallenPinsSymbol) {
        fallenPinsSymbol.stream()
                .map(OutputView::appendDelimiter)
                .map(OutputView::centerOrdering)
                .map(str -> str + DELIMITER)
                .forEach(Printer::print);
        Printer.printNewLine(2);
    }
    
    private static String appendDelimiter(final String word) {
        if (word.length() == 0) {
            return "";
        }

        final StringBuilder result = new StringBuilder("" + word.charAt(0));
        for (int i = 1; i <= word.length() - 1; i++) {
            result.append(DELIMITER).append(word.charAt(i));
        }
        return result.toString();
    }

    private static String centerOrdering(final String word) {
        final int width = word.length() + ((BLOCK_LENGTH - word.length()) / 2) + 1;
        final String formattedStr = String.format("%" + width + "s", word);
        return String.format("%-" + BLOCK_LENGTH + "s", formattedStr);
    }
}
