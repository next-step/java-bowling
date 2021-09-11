package bowling.view;

import bowling.Player;
import bowling.ScoreFrame;
import bowling.ScoreFrames;
import bowling.util.OutputStringFormatter;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingOutputView {
    private static final int START_FRAME = 1;
    private static final int END_FRAME = 10;
    private static final int FRAME_WIDTH = 6;

    public static void printFramesStatus(Player player, ScoreFrames scoreFrames) {
        System.out.println("| NAME " + getScoreLabel() + "|");
        System.out.println(getPlayerName(player) + getScores(scoreFrames) + "|");
    }

    private static String getPlayerName(Player player) {
        return String.format("|%s",
                OutputStringFormatter.toCenterAlignedWithFixedPaddedString(player.getNameString(), FRAME_WIDTH));
    }

    private static String getScores(ScoreFrames scoreFrames) {
        Iterator<ScoreFrame> iterator = scoreFrames.iterator();

        return IntStream.rangeClosed(START_FRAME, END_FRAME)
                .mapToObj(i -> getNextScoreString(iterator))
                .map(s -> String.format("|%s",
                        OutputStringFormatter.toCenterAlignedWithFixedPaddedString(s, FRAME_WIDTH)))
                .collect(Collectors.joining());
    }

    private static String getScoreLabel() {
        return IntStream.rangeClosed(START_FRAME, END_FRAME)
                .mapToObj(i -> String.format("%02d", i))
                .map(s -> String.format("|%s",
                        OutputStringFormatter.toCenterAlignedWithFixedPaddedString(s, FRAME_WIDTH)))
                .collect(Collectors.joining());
    }

    private static String getNextScoreString(Iterator<ScoreFrame> iterator) {
        if (iterator.hasNext()) {
            return iterator.next().getScoreString();
        }

        return "";
    }
}
