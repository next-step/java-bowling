package bowling.view;

import bowling.domain.Frame;
import bowling.domain.KnockedPinCounts;
import bowling.domain.Player;
import bowling.domain.Players;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {
    private static final String MAIN_BOARD_HEAD_MESSAGE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String MAIN_BOARD_EMPTY_MESSAGE = "|      |      |      |      |      |      |      |      |      |      |      |";
    private static final String SCORE_BOARD_NAME_TEMPLATE = "|  %-4s|";
    private static final String SCORE_BOARD_EMPTY_TEMPLATE = "|      |";
    private static final String SCORE_BOARD_MARK_TEMPLATE = "  %-4s|";
    private static final String SCORE_BOARD_SCORE_TEMPLATE = "  %-4d|";

    private static final String STRIKE_MARK = "X";
    private static final String SPARE_MARK = "/";
    private static final String GUTTER_MARK = "-";
    private static final String SEPARATOR = "|";
    private static final String EMPTY = "";
    private static final StringBuilder sb = new StringBuilder();

    private static final int ZERO = 0;
    private static final int TEN = 10;

    private OutputView() {}

    public static void printBowlingBoard(Players players) {
        System.out.println(MAIN_BOARD_HEAD_MESSAGE);
        players.value()
                .forEach(OutputView::printMainBoardBody);
        System.out.println();
    }

    private static void printMainBoardBody(Player player) {
        printScoreMark(player);
        printScore(player);
    }

    private static void printScoreMark(Player player) {
        clearStringBuilder();
        sb.append(String.format(SCORE_BOARD_NAME_TEMPLATE, player.name()));
        sb.append(IntStream.range(ZERO, TEN)
                .mapToObj(index -> String.format(SCORE_BOARD_MARK_TEMPLATE, scoreMark(index, player)))
                .collect(Collectors.joining()));

        System.out.println(sb);
    }

    private static String scoreMark(int index, Player player) {
        if (index < player.frames().size()) {
            Frame frame = player.frames().get(index);
            return makeScoreMark(frame.getKnockedPinCounts());
        }

        return EMPTY;
    }

    private static String makeScoreMark(KnockedPinCounts knockedPinCounts) {
        if (knockedPinCounts.isFinal()) {
            return joiningMark(knockedPinCounts);
        }
        return makeNormalScoreMark(knockedPinCounts);
    }

    private static String makeNormalScoreMark(KnockedPinCounts knockedPinCounts) {
        if (knockedPinCounts.isSpare()) {
            return toMark(knockedPinCounts.getFirst()) + SEPARATOR + SPARE_MARK;
        }
        return joiningMark(knockedPinCounts);
    }

    private static String joiningMark(KnockedPinCounts knockedPinCounts) {
        return knockedPinCounts.getValues().stream()
                .map(knockedPinCount -> toMark(knockedPinCount.value()))
                .collect(Collectors.joining(SEPARATOR));
    }

    private static String toMark(int knockOutCount) {
        if (knockOutCount == TEN) {
            return STRIKE_MARK;
        }
        if (knockOutCount == ZERO) {
            return GUTTER_MARK;
        }
        return String.valueOf(knockOutCount);
    }

    private static void printScore(Player player) {
        System.out.println(MAIN_BOARD_EMPTY_MESSAGE);
    }

    private static void clearStringBuilder() {
        sb.setLength(ZERO);
    }
}
