package bowling.view;

import bowling.Player;
import bowling.ScoreFrames;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BowlingOutputView {
    private static int START_FRAME = 1;
    private static int END_FRAME = 10;
    private static int FRAME_WIDTH = 6;

    public static void printFramesStatus(Player player, ScoreFrames scoreFrames) {
        String turns = IntStream.rangeClosed(START_FRAME, END_FRAME)
                .mapToObj(i -> String.format("%02d", i))
                .map(s -> String.format("|%s", getCenterAlignedStringWithFixedPadding(s, FRAME_WIDTH)))
                .collect(Collectors.joining());

        System.out.println("| NAME " + turns + "|");

        String playerName = String.format("|%s", getCenterAlignedStringWithFixedPadding(player.getNameString(), FRAME_WIDTH));

        System.out.println(playerName);
    }

    private static String getCenterAlignedStringWithFixedPadding(String value, int width) {
        int spaceSize = width - value.length();
        int prefixSize = spaceSize / 2;
        int suffixSize = (spaceSize + 1) / 2;

        return width > value.length() ? getSpace(prefixSize) + value + getSpace(suffixSize) : value;
    }

    private static String getSpace(int prefixSize) {
        return Stream.generate(() -> " ").limit(prefixSize).collect(Collectors.joining());
    }
}
