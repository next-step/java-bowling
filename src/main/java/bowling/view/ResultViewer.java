package bowling.view;

import java.util.List;
import java.util.stream.IntStream;

public class ResultViewer {
    private static final String STAGE_PREFIX_STRING = "| NAME |";
    private static final String FRAME_FORMAT = "  %02d  |";
    private static final String NAME_FORMAT = "| %4s |";

    public static void showResult(int frameCount, String name, List<Integer> score) {
        showHead(frameCount);
        showBody(name, score);
        System.out.println();
    }

    private static void showHead(int frameCount) {
        System.out.print(STAGE_PREFIX_STRING);

        IntStream.rangeClosed(1, frameCount)
                .forEach(frame -> System.out.printf(String.format(FRAME_FORMAT, frame)));

        System.out.println();
    }

    private static void showBody(String name, List<Integer> scores) {
        System.out.print(String.format(NAME_FORMAT, name));

        scores.stream()
                .forEach(score -> System.out.print(score));
    }
}
