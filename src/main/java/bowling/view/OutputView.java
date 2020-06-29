package bowling.view;

import bowling.dto.BowlingGameResult;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    private static final String FRAME_DELIMITER = " | ";
    private static final String GAME_ROW_PREFIX = "| ";
    private static final String GAME_ROW_SUFFIX = " |";
    private static final String GAME_ROW_BLANK = " ";
    private static final String GAME_FIRST_ROW_FRAME_NUMBER_FORMAT = " %02d ";
    private static final String GAME_FIRST_ROW_NAME_FORMAT = "NAME";
    private static final String GAME_FRAME_SCORE_FORMAT = "%-4s";
    private static final String GAME_FRAME_SCORE_EMPTY_FORMAT = "    ";
    private static final String GAME_FIRST_ROW;
    private static final int MAX_BOWLING_FRAME_SIZE = 10;

    static {
        List<String> frames = IntStream.range(0, MAX_BOWLING_FRAME_SIZE)
            .mapToObj(number -> String.format(GAME_FIRST_ROW_FRAME_NUMBER_FORMAT, number + 1))
            .collect(Collectors.toList());

        GAME_FIRST_ROW = makeGameRow(GAME_FIRST_ROW_NAME_FORMAT, frames);
    }

    public static void printBowlingGame(final BowlingGameResult bowlingGameResult) {
        System.out.println(GAME_FIRST_ROW);

        List<String> scores = bowlingGameResult.getFrameScores()
            .stream()
            .map(frameScoreConsoleResult -> String.format(GAME_FRAME_SCORE_FORMAT, frameScoreConsoleResult.getScoreResult()))
            .collect(Collectors.toList());

        System.out.println(makeGameRow(GAME_ROW_BLANK + bowlingGameResult.getName(), makeScoreWithBlank(scores)));
        System.out.println();
    }

    private static String makeGameRow(final String name, final List<String> frames) {
        List<String> frameWords = new ArrayList<>();
        frameWords.add(name);
        frameWords.addAll(frames);

        return GAME_ROW_PREFIX + String.join(FRAME_DELIMITER, frameWords) + GAME_ROW_SUFFIX;
    }

    private static List<String> makeScoreWithBlank(final List<String> scores) {
        List<String> scoresWithBlank = new ArrayList<>(scores);
        IntStream.range(0, MAX_BOWLING_FRAME_SIZE - scores.size())
            .forEach(i -> scoresWithBlank.add(GAME_FRAME_SCORE_EMPTY_FORMAT));

        return scoresWithBlank;
    }
}
