package bowling.view;

import bowling.domain.NomalFrame;
import bowling.domain.Player;

import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OutputUi {
    private static final String NAME = " NAME ";
    private static final String LINE = " | ";
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


    private static void printFrameFirstLine() {
        System.out.print(LINE + NAME + LINE);
        Stream.of(FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH, SEVENTH, EIGHTH, NINTH, TENTH)
                .map(s -> s + LINE)
                .forEach(System.out::print);
        System.out.println();
    }

    public static void printInitFrame(String player) {
        printFrameFirstLine();
        System.out.print(LINE + String.format("  %s ", player) + LINE);
        IntStream.range(0, 10)
                .mapToObj(i -> EMPTY + LINE)
                .forEach(System.out::print);
        System.out.println();
    }

    private static void printPlayFrame(String player, Map<Integer, NomalFrame> nomalFrames) {

        StringBuilder sb = new StringBuilder();
        printFrameFirstLine();
        sb.append(LINE + String.format("  %s ", player) + LINE);

        for (int i = 1; i <= nomalFrames.size(); i++) {
            String knockDown = nomalFrames.get(i).getBowlingKnockDowns().getKnockDownExpression();
            sb.append(knockDown + LINE);
        }

        IntStream.range(0, 10 - nomalFrames.size())
                .mapToObj(i -> EMPTY + LINE)
                .forEach(sb::append);

        System.out.print(sb);
        System.out.println();
    }

    public static void printBowling(Player player, Map<Integer, NomalFrame> nomalFrames) {
        printPlayFrame(player.getName(), nomalFrames);
    }
}
