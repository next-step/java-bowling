package refactor;

import java.util.List;

public class Output {
    public final static String HEADER_STR = "| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |";

    public static void print(String payload) {
        System.out.print(payload);
    }

    public static void printFrames(Frames frames) {
        print(HEADER_STR + "\n");
        printScores(frames);
        printSubtotals(frames);
        System.out.println();
    }

    private static void printSubtotals(Frames frames) {
        String payload = "| NAME |" + frames.frames()
                .stream()
                .map(frame -> maskSubtotal(frame.subtotal()))
                .reduce((acc, cur) -> acc + "|" + cur)
                .orElseThrow(() -> new UnsupportedOperationException())
                + "|\n";
        print(payload);
    }

    private static String maskSubtotal(Subtotal subtotal) {
        if (subtotal.state() != State.DONE) {
            return formatRecord("");
        }
        return formatRecord(subtotal.value() + "");
    }

    private static void printScores(Frames frames) {
        String payload = "| NAME |" + frames.frames()
                .stream()
                .map(frame -> formatScore(frame.scores()))
                .reduce((acc, cur) -> acc + "|" + cur)
                .orElseThrow(() -> new UnsupportedOperationException())
                + "|\n";
        print(payload);
    }

    private static String formatScore(List<Integer> scores) {
        String payload = scores.stream()
                .map(String::valueOf)
                .reduce((acc, cur) -> acc + "/" + cur)
                .orElse("");
        return formatRecord(payload);
    }

    private static String formatRecord(String payload) {
        return String.format("%-8s", "  " + payload);
    }
}
