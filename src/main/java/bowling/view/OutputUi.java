package bowling.view;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.GameFrames;
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

    private static String setKnockDownExpress(int count) {
        if (count == MIN_NUMBER) {
            return String.format(" %s ", GUTTER);
        }

        if (count == MAX_NUMBER) {
            return String.format("  %s   ", STRIKE);
        }
        return String.format(" %s ", count);
    }

    private static String setFinalKnockDownExpress(int first, int second) {
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

    private static void printOneFrame(StringBuilder sb, Frame frame) {
        if (frame.size() == 1) {
            sb.append(setKnockDownExpress(frame.getFirstOfKnockDown()));
        }
    }

    private static void printSecondFrame(StringBuilder sb, Frame frame) {
        if (frame.size() == 2) {
            sb.append(setFinalKnockDownExpress(frame.getFirstOfKnockDown()
                    , frame.getSecondOfKnockDown()));
        }
    }

    private static void printFinalFrame(StringBuilder sb, Frame frame) {
        if (frame.size() == 3) {
            FinalFrame finalFrame = (FinalFrame) frame;

            String drawFrame =
                    setFinalKnockDownExpress(frame.getFirstOfKnockDown(), frame.getSecondOfKnockDown());
            drawFrame += setKnockDownExpress(finalFrame.getBonusOfKnockDown());
            sb.append(drawFrame);
        }
    }

    public static void printInitBowling(Player player, GameFrames gameFrames) {
        StringBuilder sb = new StringBuilder();
        printFrameFirstLine();
        sb.append(LINE + String.format("  %s ", player.getName()) + LINE);
        IntStream.rangeClosed(1, gameFrames.size())
                .mapToObj(i -> gameFrames.getFrames().get(i))
                .forEach(frame -> {
                    printOneFrame(sb, frame);
                    printSecondFrame(sb, frame);
                    printFinalFrame(sb, frame);
                    sb.append(LINE);
                });

        IntStream.range(0, 10 - gameFrames.size())
                .mapToObj(i -> EMPTY + LINE)
                .forEach(sb::append);

        System.out.print(sb);
        System.out.println();
    }
}
