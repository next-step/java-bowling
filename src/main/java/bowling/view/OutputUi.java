package bowling.view;

import bowling.domain.Frames;
import bowling.domain.Player;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OutputUi {
    private static final String NAME = " NAME ";
    private static final String FIRST = "  01  ";
    private static final String SECOND = "  02  ";
    private static final String THIRD = "  03  ";
    private static final String FOURTH = "  04  ";
    private static final String FIFTH = "  05  ";
    private static final String SIXTH = "  06  ";
    private static final String SEVENTH = "  07  ";
    private static final String EIGHTH = "  08  ";
    private static final String NINTH = "  09  ";
    private static final String TENTH = "  10  ";
    private static final String EMPTY = "      ";

    private static final int MIN_NUMBER = 0;
    public static final int MAX_NUMBER = 10;
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";
    private static final String LINE = " | ";

    private OutputUi() {

    }

    private static void printFrameFirstLine() {
        System.out.print(LINE + NAME + LINE);
        Stream.of(FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH, SEVENTH, EIGHTH, NINTH, TENTH)
                .map(s -> s + LINE)
                .forEach(System.out::print);
        System.out.println();
    }

    public static void printInitFrame(Player player) {
        printFrameFirstLine();
        System.out.print(LINE + String.format("  %s ", player.getName()) + LINE);
        IntStream.range(0, 10)
                .mapToObj(i -> EMPTY + LINE)
                .forEach(System.out::print);
        System.out.println();
    }

    private static void printPlayFrames(Player player, Frames frames) {
        StringBuilder sb = new StringBuilder();
        printFrameFirstLine();
        sb.append(LINE + String.format("  %s ", player.getName()) + LINE);

        IntStream.rangeClosed(1, frames.size())
                .mapToObj(i -> frames.bowlingKnockDownExpression(i))
                .map(knockDown -> knockDown + LINE)
                .forEach(sb::append);

        IntStream.range(0, 10 - frames.size())
                .mapToObj(i -> EMPTY + LINE)
                .forEach(sb::append);

        System.out.print(sb);
        System.out.println();
    }

    public static void printBowling(Player player, Frames frames) {
        printPlayFrames(player, frames);
    }

    private String initDownExpress(int count) {
        if (count == MIN_NUMBER) {
            return String.format(" %s ", GUTTER);
        }

        if (count == MAX_NUMBER) {
            return String.format("   %s  ", STRIKE);
        }

        return String.format("  %d   ", count);
    }

    private String finalKnockDownExpress(String currentKnockDownExpression, int count) {

        if (count == MAX_NUMBER) {
            return String.format("%s%s%s ", currentKnockDownExpression, LINE, SPARE);
        }

        if (count == MIN_NUMBER) {
            return String.format(" %s%s%s ", currentKnockDownExpression, LINE, GUTTER);
        }

        if (count == MAX_NUMBER) {
            return String.format(" %s%s%s ", currentKnockDownExpression, LINE, STRIKE);
        }

        return String.format(" %s%s%d ", currentKnockDownExpression, LINE, count);
    }
}
