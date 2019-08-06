package view.util;

import bowling.domain.FrameNumber;
import bowling.domain.Score;

import java.util.stream.IntStream;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-08-06 19:41
 */
public class PrintUtil {

    private static final String HEADER_DOMAIN_NAME = "NAME";
    private static final String FRAME_SEPARATOR = "|";
    private static final String SINGLE_SPACE = " ";
    private static final String DOUBLE_SPACE = "  ";
    private static final String TRIPLE_SPACE = "   ";
    private static final String FRAME_NUMBER_SUFFIX = "  |";
    private static final String FRAME_IDLE = "      |";
    private static final String PREFIX_DIGIT = "0";
    private static final int TWO_DIGIT = 2;
    private static final int SINGLE_WORD = 1;

    public static String headerArea() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFrameFirstArea(HEADER_DOMAIN_NAME));

        IntStream.rangeClosed(FrameNumber.INIT_FRAME_NUMBER, FrameNumber.LAST_FRAME_NUMBER)
                .mapToObj(frameNumber -> combineTwoDigit(frameNumber))
                .forEach(frameNumber -> sb.append(DOUBLE_SPACE + frameNumber + FRAME_NUMBER_SUFFIX));

        return sb.toString();
    }
    
    public static String playerNameArea(String playName) {
        StringBuilder sb = new StringBuilder();
        sb.append(getFrameFirstArea(playName));

        return sb.toString();
    }

    public static String idleFrameArea(int startIndex) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(startIndex, FrameNumber.LAST_FRAME_NUMBER)
                .mapToObj(idleCount -> getIdleFrame())
                .forEach(idle -> sb.append(idle));

        return sb.toString();
    }

    public static String scoreFrmaeArea(String displayScore) {
        StringBuilder sb = new StringBuilder();
        sb.append(DOUBLE_SPACE);
        sb.append(displayScore);

        if (displayScore.length() == SINGLE_WORD) {
            sb.append(TRIPLE_SPACE);
            sb.append(FRAME_SEPARATOR);

            return sb.toString();
        }

        sb.append(SINGLE_SPACE);
        sb.append(FRAME_SEPARATOR);

        return sb.toString();
    }

    private static String getIdleFrame() {
        return FRAME_IDLE;
    }

    private static String getFrameFirstArea(String Domain) {
        StringBuilder sb = new StringBuilder();
        sb.append(FRAME_SEPARATOR);
        sb.append(SINGLE_SPACE);
        sb.append(Domain);
        sb.append(SINGLE_SPACE);
        sb.append(FRAME_SEPARATOR);

        return sb.toString();
    }

    private static String combineTwoDigit(int frameNumber) {
        int digit = (int) Math.log10(frameNumber) + 1;
        if (digit >= TWO_DIGIT) {
            return String.valueOf(frameNumber);
        }
        return PREFIX_DIGIT + frameNumber;
    }
}
