package bowling.view;

import java.util.Map;
import java.util.stream.IntStream;

public class ResultViewer {
    private static final String STAGE_PREFIX_STRING = "| NAME |";
    private static final String FRAME_FORMAT = "  %02d  |";
    private static final String NAME_FORMAT = "| %4s |";
    private static final String GAME_RESULT_FORMAT = "  %-3s |";

    public static void showResult(int frameCount, String name, Map<Integer, String> scores) {
        showHead(frameCount);
        showBody(name, frameCount, scores);
        System.out.println();
    }

    private static void showHead(int frameCount) {
        System.out.print(STAGE_PREFIX_STRING);

        IntStream.rangeClosed(1, frameCount)
                .forEach(frame -> System.out.printf(String.format(FRAME_FORMAT, frame)));

        System.out.println();
    }

    private static void showBody(String name, int frameCount, Map<Integer, String> scores) {
        System.out.print(String.format(NAME_FORMAT, name));

        IntStream.rangeClosed(1, frameCount)
                .forEach(frame -> System.out.printf(framesToString(scores.get(frame))));
    }

    private static String framesToString(String result) {
        if (result == null) {
            return String.format(GAME_RESULT_FORMAT, "");
        }

        return String.format(GAME_RESULT_FORMAT, result);
    }
}
