package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.Content;
import bowling.domain.Frames;
import bowling.domain.Hit;
import bowling.domain.frame.Frame;
import bowling.domain.pin.Pins;

public class ResultBuilder {

    private static final int EMPTY_SIZE = 0;
    private static final String BLANK_LIME = "\n";
    private static final String HEADER_UNIT_NAME = "| NAME |";
    private static final String HEADER_UNIT_FRAME_NO = "  %02d  |";
    private static final String BODY_UNIT_NAME = "|  %s |";
    private static final String BODY_UNIT_SCORE_ONE = "  %s   |";
    private static final String BODY_UNIT_SCORE_TWO = "  %s|%s |";
    private static final String BODY_UNIT_SCORE_THREE = " %s|%s|%s|";
    private static final String BODY_UNIT_EMPTY_SCORE = "      |";

    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";

    private static final StringBuilder sb = new StringBuilder();

    public static String bowlingResultChart(BowlingGame bowlingGame) {
        String header = renderChartHeader(bowlingGame.frames());
        String body = renderChartBody(bowlingGame);
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
            sb.append(String.format(HEADER_UNIT_FRAME_NO, frame.content().frameNo()));
        }

        for (int i = frames.currentFrame().content().frameNo() + 1; i <= Content.LAST_FRAME_NO; i++) {
            sb.append(String.format(HEADER_UNIT_FRAME_NO, i));
        }

        return sb.toString();
    }

    private static String renderChartBody(BowlingGame bowlingGame) {
        initializeStringBuilder();

        sb.append(String.format(BODY_UNIT_NAME, bowlingGame.player()));
        for (Frame frame : bowlingGame.frames().toList()) {
            renderScore(frame);
        }

        for (int i = bowlingGame.frames().currentFrame().content().frameNo() + 1; i <= Content.LAST_FRAME_NO; i++) {
            sb.append(BODY_UNIT_EMPTY_SCORE);
        }
        return sb.toString();
    }

    private static void renderScore(Frame frame) {
        if (frame.pins().hitSize() == 0) {
            sb.append(BODY_UNIT_EMPTY_SCORE);
            return;
        }
        if (frame.pins().hitSize() == 1) {
            sb.append(String.format(BODY_UNIT_SCORE_ONE, renderFirstScore(frame)));
            return;
        }
        if (frame.pins().hitSize() == 2) {
            sb.append(String.format(BODY_UNIT_SCORE_TWO, renderFirstScore(frame), renderSecondScore(frame)));
            return;
        }
        sb.append(String.format(BODY_UNIT_SCORE_THREE, renderFirstScore(frame), renderSecondScore(frame), renderThirdScore(frame)));
    }

    private static String renderFirstScore(Frame frame) {
        if (frame.pins().firstHit() == Hit.MAX_NUMBER) {
            return STRIKE;
        }
        if (frame.pins().firstHit() == Hit.MIN_NUMBER) {
            return GUTTER;
        }
        return String.valueOf(frame.pins().firstHit());
    }

    private static String renderSecondScore(Frame frame) {
        if (!frame.isFinalFrame()) {
            return renderNormalFrameSecondScore(frame.pins());
        }

        if (frame.pins().secondHit() == Hit.MAX_NUMBER) {
            return strikeOrSpare(frame.pins().firstHit());
        }

        if (frame.pins().firstHit() + frame.pins().secondHit() == Hit.MAX_NUMBER) {
            return SPARE;
        }

        if (frame.pins().secondHit() == Hit.MIN_NUMBER) {
            return GUTTER;
        }
        return String.valueOf(frame.pins().secondHit());
    }

    private static String renderNormalFrameSecondScore(Pins pins) {
        if (pins.totalHits() == Hit.MAX_NUMBER) {
            return SPARE;
        }
        if (pins.secondHit() == Hit.MIN_NUMBER) {
            return GUTTER;
        }
        return String.valueOf(pins.secondHit());
    }

    private static String strikeOrSpare(int hit) {
        if (hit == Hit.MAX_NUMBER) {
            return STRIKE;
        }
        return SPARE;
    }

    private static String renderThirdScore(Frame frame) {
        if (!frame.isFinalFrame()) {
            throw new RuntimeException();
        }
        if (frame.pins().thirdHit() == Hit.MAX_NUMBER) {
            return renderFinalFrameThirdScore(frame.pins());
        }
        if (frame.pins().secondHit() + frame.pins().thirdHit() == Hit.MAX_NUMBER) {
            return SPARE;
        }
        if (frame.pins().thirdHit() == Hit.MIN_NUMBER) {
            return GUTTER;
        }
        return String.valueOf(frame.pins().thirdHit());
    }

    private static String renderFinalFrameThirdScore(Pins pins) {
        if (pins.secondHit() == Hit.MAX_NUMBER || pins.firstHit() + pins.secondHit() == Hit.MAX_NUMBER) {
            return STRIKE;
        }
        return SPARE;
    }

    private static void initializeStringBuilder() {
        if (sb.length() != EMPTY_SIZE) {
            sb.setLength(EMPTY_SIZE);
        }
    }
}
