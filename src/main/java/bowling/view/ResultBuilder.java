package bowling.view;

import bowling.domain.FrameNo;
import bowling.dto.BowlingResult;

import java.util.List;

public class ResultBuilder {

    private static final int EMPTY_SIZE = 0;
    private static final String BLANK_LIME = "\n";
    private static final String HEADER_UNIT_NAME = "|  NAME  |";
    private static final String HEADER_UNIT_FRAME_NO = "   %02d   |";
    private static final String BODY_UNIT_NAME = "|   %s  |";
    private static final String BODY_UNIT_SCORE_ONE = "  %-5s |";
    private static final String BODY_UNIT_EMPTY_SCORE = "        |";
    private static final String FOOTER_UNIT_EMPTY_SCORE = "|        |";
    private static final String FOOTER_UNIT_SCORE = "  %3s   |";

    private static final StringBuilder sb = new StringBuilder();

    public static String bowlingResultChart(BowlingResult bowlingResult) {
        String header = renderChartHeader(bowlingResult.getFrameNos());
        String body = renderChartBody(bowlingResult.getDescriptions(), bowlingResult.getPlayerName());
        String footer = renderChartFooter(bowlingResult.getScores());
        initializeStringBuilder();
        return sb.append(header)
                .append(BLANK_LIME)
                .append(body)
                .append(BLANK_LIME)
                .append(footer)
                .toString();
    }

    private static String renderChartHeader(List<Integer> frameNos) {
        initializeStringBuilder();

        sb.append(HEADER_UNIT_NAME);
        for (int frameNo : frameNos) {
            sb.append(String.format(HEADER_UNIT_FRAME_NO, frameNo));
        }

        for (int i = frameNos.size() + 1; i <= FrameNo.LAST_FRAME_NO; i++) {
            sb.append(String.format(HEADER_UNIT_FRAME_NO, i));
        }

        return sb.toString();
    }

    private static String renderChartBody(List<String> descriptions, String playerName) {
        initializeStringBuilder();

        sb.append(String.format(BODY_UNIT_NAME, playerName));
        for (String description : descriptions) {
            sb.append(String.format(BODY_UNIT_SCORE_ONE, description));
        }

        for (int i = descriptions.size() + 1; i <= FrameNo.LAST_FRAME_NO; i++) {
            sb.append(BODY_UNIT_EMPTY_SCORE);
        }
        return sb.toString();
    }

    private static String renderChartFooter(List<String> scores) {
        initializeStringBuilder();

        sb.append(FOOTER_UNIT_EMPTY_SCORE);
        for (String score : scores) {
            sb.append(String.format(FOOTER_UNIT_SCORE, score));
        }

        for (int i = scores.size() + 1; i <= FrameNo.LAST_FRAME_NO; i++) {
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
