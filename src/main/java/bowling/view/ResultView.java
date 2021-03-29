package bowling.view;

import bowling.dto.ScoreBoard;
import bowling.util.StringUtils;

public class ResultView {

    public static final String HEADER_SEPARATOR = "|";

    private static final int FIXED_HEADER_SPACE = 6;

    private static final String HEADER_PLAYER_NAME_REPRESENTATION = "NAME";

    private static final int HEADER_MAX_FRAME_COUNT = 10;

    public static void printScoreBoard(ScoreBoard scoreBoard) {

        printScoreBoardHeader();
    }

    private static void printScoreBoardHeader() {
        System.out.print(HEADER_SEPARATOR);
        System.out.print(StringUtils.center(HEADER_PLAYER_NAME_REPRESENTATION,FIXED_HEADER_SPACE));
        for (int i = 1; i <= HEADER_MAX_FRAME_COUNT; i++) {
            System.out.print(HEADER_SEPARATOR);
            String twoDigitFrameNumber = String.format("%02d", i);
            String formattedFrameNumber = StringUtils.center(twoDigitFrameNumber,FIXED_HEADER_SPACE);
            System.out.print(formattedFrameNumber);
        }
        System.out.print(HEADER_SEPARATOR);
    }
}
