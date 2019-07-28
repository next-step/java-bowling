package view;

import bowling.domain.BowlingCenter;
import bowling.domain.FinalFrame;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-20 15:11
 */
public class ConsoleOutView {
    private static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String SPACE = " ";
    private static final String DOUBLE_SPACE = "  ";
    private static final String SPACE_PREFIX = "| ";
    private static final String SPACE_SUFFIX = "|";
    private static final String IDLE_FRAME = "      |";
    private static final String SINGLE_WORD_SPACE = "   ";
    private static final String SCORE_FRAME_SPACE = "|      ";
    private static final int SINGLE_WORD = 1;

    // TODO Result OUTPUT 로직 개선 필요..
    public static void printFrameResult(BowlingCenter center) {
        printHeader();
        combinePlayerAndFrameState(center);
        combineScore(center);
    }

    private static void combinePlayerAndFrameState(BowlingCenter center) {
        StringBuilder sb = new StringBuilder();
        sb.append(SPACE_PREFIX);
        sb.append(center.playerName());
        sb.append(DOUBLE_SPACE + SPACE_SUFFIX + DOUBLE_SPACE);

        sb.append(extractState(center));
        sb.append(SPACE_SUFFIX);

        sb.append(combineIdleFrame(center));
        System.out.println(sb.toString());
    }

    private static void combineScore(BowlingCenter center) {
        StringBuilder sb = new StringBuilder();
        sb.append(SCORE_FRAME_SPACE);
        sb.append(SPACE_PREFIX + SPACE);

        sb.append(extractScore(center));
        sb.append(SPACE + SPACE_SUFFIX);

        sb.append(combineIdleFrame(center));

        System.out.println(sb.toString());
        println();
    }

    private static String extractScore(BowlingCenter center) {
        return center.displayScore().stream()
                .map(score -> combineSpace(String.valueOf(score)))
                .collect(Collectors.joining(SPACE + SPACE_SUFFIX + DOUBLE_SPACE));
    }

    private static String extractState(BowlingCenter center) {
        return center.displayState().stream()
                .map(state -> combineSpace(state))
                .collect(Collectors.joining(SPACE_SUFFIX + DOUBLE_SPACE));
    }

    private static String combineIdleFrame(BowlingCenter center) {
        return IntStream.range(center.playFrameNumber(), FinalFrame.LAST_FRAME_NUMBER)
                .mapToObj(space -> IDLE_FRAME)
                .collect(Collectors.joining());
    }

    private static String combineSpace(String word) {
        if (word.length() == SINGLE_WORD) {
            return word + SINGLE_WORD_SPACE;
        }
        return word + SPACE;
    }

    private static void printHeader() {
        System.out.println(HEADER);
    }

    private static void println() {
        System.out.println();
    }
}
