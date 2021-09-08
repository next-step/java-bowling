package bowling.view;

import bowling.Player;
import bowling.ScoreFrame;
import bowling.ScoreFrames;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
                toCenterAlignedWithFixedPaddedString(player.getNameString()));
    }

    private static String getScores(ScoreFrames scoreFrames) {
        Iterator<ScoreFrame> iterator = scoreFrames.iterator();

        return IntStream.rangeClosed(START_FRAME, END_FRAME)
                .mapToObj(i -> getNextScoreString(iterator))
                .map(s -> String.format("|%s", toCenterAlignedWithFixedPaddedString(s)))
                .collect(Collectors.joining());
    }

    private static String getScoreLabel() {
        return IntStream.rangeClosed(START_FRAME, END_FRAME)
                .mapToObj(i -> String.format("%02d", i))
                .map(s -> String.format("|%s", toCenterAlignedWithFixedPaddedString(s)))
                .collect(Collectors.joining());
    }

    private static String getNextScoreString(Iterator<ScoreFrame> iterator) {
        try {
            return iterator.next().getScoreString();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    private static String toCenterAlignedWithFixedPaddedString(String value) {
        int spaceSize = BowlingOutputView.FRAME_WIDTH - value.length();
        int prefixSize = spaceSize / 2;
        int suffixSize = (spaceSize + 1) / 2;

        return BowlingOutputView.FRAME_WIDTH > value.length() ? getSpace(prefixSize) + value + getSpace(suffixSize) : value;
    }

    private static String getSpace(int prefixSize) {
        return Stream.generate(() -> " ").limit(prefixSize).collect(Collectors.joining());
    }
}
