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
    private static final String GAME_FIRST_ROW;

    static {
        List<String> frames = IntStream.range(0, 10)
                .mapToObj(number -> String.format(" %02d ", number))
                .collect(Collectors.toList());
        GAME_FIRST_ROW = makeGameRow("NAME", frames);
    }

    private static String makeGameRow(final String name, final List<String> frames) {
        List<String> frameWords = new ArrayList<>();
        frameWords.add(name);
        frameWords.addAll(frames);
        return GAME_ROW_PREFIX + String.join(FRAME_DELIMITER, frameWords) + GAME_ROW_SUFFIX;
    }

    public static void printBowlingGame(final BowlingGameResult bowlingGameResult) {
        System.out.println(GAME_FIRST_ROW);

        List<String> scores = bowlingGameResult.getFrameScores()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());

        System.out.println(makeGameRow(bowlingGameResult.getName(), makeScoreWithBlank(scores)));
    }

    private static List<String> makeScoreWithBlank(final List<String> scores) {
        List<String> scoresWithBlank = new ArrayList<>(scores);
        IntStream.range(0, 10 - scores.size())
                .forEach(i -> scoresWithBlank.add("   "));

        return scoresWithBlank;
    }
}
