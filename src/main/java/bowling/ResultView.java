package bowling;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class ResultView {
    public static void printResult(GameStatus status) {
        System.out.println(getFrameTitle());
        System.out.println(status.toString());
    }

    private static String getFrameTitle() {
        String frameTitle = IntStream.rangeClosed(0, 10)
                .mapToObj(i -> String.format("  %d  ", i))
                .collect(joining("|"));
        return "| NAME |" + frameTitle;
    }
}
