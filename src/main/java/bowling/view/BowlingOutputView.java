package bowling.view;

import bowling.*;
import bowling.util.OutputStringFormatter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingOutputView {
    private static final int START_FRAME = 1;
    private static final int END_FRAME = 10;
    private static final int FRAME_WIDTH = 6;

    public static void printFramesStatus(Player player, ScoreFrames scoreFrames) {
        System.out.println("| NAME " + getScoreLabel() + "|");
        System.out.println(getPlayerName(player) + getScores(scoreFrames) + "|");
        System.out.println("|      " + getCalculatedScores(scoreFrames) + "|");
    }

    private static String getPlayerName(Player player) {
        return String.format("|%s",
                OutputStringFormatter.toCenterAlignedWithFixedPaddedString(player.getNameString(), FRAME_WIDTH));
    }

    private static String getScores(ScoreFrames scoreFrames) {
        List<String> scoreStrings = scoreFrames.getScoreStrings();

        return createLine(scoreStrings);
    }

    private static String getCalculatedScores(ScoreFrames scoreFrames) {
        List<String> calculatedScores = scoreFrames.getCalculatedScores();

        return createLine(calculatedScores);
    }

    private static String getScoreLabel() {
        List<String> scoreLabels = IntStream.rangeClosed(START_FRAME, END_FRAME)
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.toList());

        return createLine(scoreLabels);
    }

    private static String createLine(List<String> elements) {
        return IntStream.rangeClosed(START_FRAME, END_FRAME)
                .mapToObj(i -> getStringOrEmpty(elements, i - 1))
                .map(s -> String.format("|%s",
                        OutputStringFormatter.toCenterAlignedWithFixedPaddedString(s, FRAME_WIDTH)))
                .collect(Collectors.joining());
    }

    private static String getStringOrEmpty(List<String> scoreStrings, int index) {
        if (index < scoreStrings.size()) {
            return scoreStrings.get(index);
        }

        return "";
    }
}
