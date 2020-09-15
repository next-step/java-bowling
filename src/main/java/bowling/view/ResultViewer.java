package bowling.view;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultViewer {
    private static final int SHOW_FRAME_NUMBER = 10;
    private static final String STAGE_PREFIX_STRING = "| NAME |";
    private static final String FRAME_FORMAT = "  %02d  |";
    private static final String NAME_FORMAT = "| %4s |";
    private static final String GAME_RESULT_FORMAT = "  %-3s |";
    private static final String GAME_RESULT_DELIMITER = "|";

    public static void print(int hitCount, GameView gameView) {
        ResultViewer.showHead();
        ResultViewer.showResultFrames(gameView.getName(), gameView.hit(hitCount));
        ResultViewer.showResultScores(gameView.getSumScores());
    }

    private static void showHead() {
        System.out.print(STAGE_PREFIX_STRING);

        IntStream.rangeClosed(1, SHOW_FRAME_NUMBER)
                .forEach(frame -> System.out.printf(String.format(FRAME_FORMAT, frame)));

        System.out.println();
    }

    private static void showResultFrames(String name, List<List<String>> frames) {
        showFramesBody(name, SHOW_FRAME_NUMBER, frames);
        System.out.println();
    }

    private static void showFramesBody(String name, int frameCount, List<List<String>> frames) {
        System.out.print(String.format(NAME_FORMAT, name));

        IntStream.rangeClosed(1, frameCount)
                .forEach(frame -> System.out.printf(framesToString(frame, frames)));
    }

    private static String framesToString(int frameNumber, List<List<String>> frames) {
        if (frames.size() < frameNumber) {
            return String.format(GAME_RESULT_FORMAT, "");
        }

        return String.format(GAME_RESULT_FORMAT, scoresToString(frames.get(frameNumber - 1)));
    }

    private static String scoresToString(List<String> results) {
        return results.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(GAME_RESULT_DELIMITER));
    }

    private static void showResultScores(List<Integer> scores) {
        System.out.print(String.format(NAME_FORMAT, ""));

        IntStream.rangeClosed(1, SHOW_FRAME_NUMBER)
                .forEach(frame -> System.out.printf(scoresToString(frame, scores)));

        System.out.println();
    }

    private static String scoresToString(int frameNumber, List<Integer> scores) {
        if (scores.size() < frameNumber) {
            return String.format(GAME_RESULT_FORMAT, "");
        }

        return String.format(GAME_RESULT_FORMAT, scores.get(frameNumber - 1));
    }
}
