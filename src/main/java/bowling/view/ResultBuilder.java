package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.FrameNo;
import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.domain.frame.Frame;

public class ResultBuilder {

    private static final int EMPTY_SIZE = 0;
    private static final String BLANK_LIME = "\n";
    private static final String HEADER_UNIT_NAME = "|  NAME  |";
    private static final String HEADER_UNIT_FRAME_NO = "   %02d   |";
    private static final String BODY_UNIT_NAME = "|   %s  |";
    private static final String BODY_UNIT_SCORE_ONE = "  %-5s |";
    private static final String BODY_UNIT_EMPTY_SCORE = "        |";

    private static final StringBuilder sb = new StringBuilder();

    public static String bowlingResultChart(BowlingGame bowlingGame) {
        String header = renderChartHeader(bowlingGame.frames());
        String body = renderChartBody(bowlingGame.frames(), bowlingGame.player());
        initializeStringBuilder();
        return sb.append(header)
                .append(BLANK_LIME)
                .append(body)
                .toString();
    }

    private static String renderChartHeader(Frames frames) {
        initializeStringBuilder();

        sb.append(HEADER_UNIT_NAME);
        for (Frame frame : frames.toList()) {
            sb.append(String.format(HEADER_UNIT_FRAME_NO, frame.frameNo().toInt()));
        }

        for (int i = frames.currentFrame().frameNo().toInt() + 1; i <= FrameNo.LAST_FRAME_NO; i++) {
            sb.append(String.format(HEADER_UNIT_FRAME_NO, i));
        }

        return sb.toString();
    }

    private static String renderChartBody(Frames frames, Player player) {
        initializeStringBuilder();

        sb.append(String.format(BODY_UNIT_NAME, player));
        for (Frame frame : frames.toList()) {
            sb.append(String.format(BODY_UNIT_SCORE_ONE, frame));
        }

        for (int i = frames.currentFrame().frameNo().toInt() + 1; i <= FrameNo.LAST_FRAME_NO; i++) {
            sb.append(BODY_UNIT_EMPTY_SCORE);
        }
        return sb.toString();
    }

    private static void initializeStringBuilder() {
        if (sb.length() != EMPTY_SIZE) {
            sb.setLength(EMPTY_SIZE);
        }
    }
}
