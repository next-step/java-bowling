package bowling.view;

import bowling.domain.BowlingKnockDown;
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

    private static void drawFirstKnockDown(BowlingKnockDown bowlingKnockDown, StringBuilder sb) {
        if (bowlingKnockDown.getNextOfKnockDown() == null) {
            sb.append(initDownExpress(bowlingKnockDown.getCurrentOfKnockDown()));
        }
    }

    private static String drawBonusKnockDown(BowlingKnockDown bowlingKnockDown, String sumKnockDown) {
        if (bowlingKnockDown.getBonusKnockDown() != null) {
            return String.format("%s%s%s ", sumKnockDown, LINE, initDownExpress(bowlingKnockDown.getBonusKnockDown()));
        }
        return sumKnockDown;
    }

    private static void drawFinishKnockDown(BowlingKnockDown bowlingKnockDown, StringBuilder sb) {
        if (bowlingKnockDown.getCurrentOfKnockDown() != null && bowlingKnockDown.getNextOfKnockDown() != null) {
            String sumKnockDown = finalKnockDownExpress(bowlingKnockDown.getCurrentOfKnockDown(), bowlingKnockDown.getNextOfKnockDown());
            sumKnockDown = drawBonusKnockDown(bowlingKnockDown, sumKnockDown);
            sb.append(sumKnockDown);
        }
    }

    private static void printPlayFrames(Player player, Frames frames) {
        StringBuilder sb = new StringBuilder();
        printFrameFirstLine();
        sb.append(LINE + String.format("  %s ", player.getName()) + LINE);

        IntStream.rangeClosed(1, frames.size())
                .mapToObj(i -> frames.getBowlingKnockDownMap().get(i))
                .forEach(bowlingKnockDown -> {
                    drawFirstKnockDown(bowlingKnockDown, sb);
                    drawFinishKnockDown(bowlingKnockDown, sb);
                    sb.append(LINE);
                });

        IntStream.range(0, 10 - frames.size())
                .mapToObj(i -> EMPTY + LINE)
                .forEach(sb::append);

        System.out.print(sb);
        System.out.println();
    }

    public static void printBowling(Player player, Frames frames) {
        printPlayFrames(player, frames);
    }

    private static String initDownExpress(int count) {
        if (count == MIN_NUMBER) {
            return String.format(" %s ", GUTTER);
        }

        if (count == MAX_NUMBER) {
            return String.format("  %s   ", STRIKE);
        }
        return String.format(" %s ", count);
    }

    private static String finalKnockDownExpress(int first, int second) {
        if (first + second == MAX_NUMBER) {
            return String.format("%s%s%s ", first, LINE, SPARE);
        }

        if (first + second == MIN_NUMBER) {
            return String.format("%s%s%s ", GUTTER, LINE, GUTTER);
        }

        if (second == MIN_NUMBER) {
            return String.format(" %s%s%s ", first, LINE, GUTTER);
        }

        if (first == MIN_NUMBER) {
            return String.format(" %s%s%s ", GUTTER, LINE, second);
        }

        if (first == MAX_NUMBER && second == MAX_NUMBER) {
            return String.format(" %s%s%s ", STRIKE, LINE, STRIKE);
        }

        return String.format(" %s%s%d ", first, LINE, second);
    }
}
