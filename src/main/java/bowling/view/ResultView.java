package bowling.view;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class ResultView {
    public static void printResults(List<GameStatus> statusList) {
        System.out.println(getFrameTitle());
        statusList.forEach(status -> {
            System.out.println(status.getAllFrameStatus());
            System.out.println(status.getAllScore());
        });
    }

    private static String getFrameTitle() {
        String frameTitle = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> String.format("  %02d  ", i))
                .collect(joining("|"));
        return "| NAME |" + frameTitle + "|";
    }
}
