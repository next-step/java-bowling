package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;

import java.util.List;
import java.util.stream.IntStream;

import static bowling.util.BowlingFixture.*;

public final class ResultView {

    private static final String SCORE_BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String DELIMITER = "|";
    private static final String GUTTER = "-";
    private static final String BLANK = "   ";
    private static final String NAME_FORMAT = " %4s ";
    private static final String NORMAL_SCORE_FORMAT = "  %-3s ";
    private static final String FINAL_SCORE_FORMAT = " %-5s";
    private static final String NEW_LINE = "\n";

    private static final class ResultViewHolder {
        private static final ResultView instance = new ResultView();
    }

    private ResultView() {
    }

    public static final ResultView getInstance() {
        return ResultViewHolder.instance;
    }

    public final void printScoreBoard(Player player, Frames frames) {
        StringBuilder scoreboardBuilder = new StringBuilder();
        scoreboardBuilder.append(SCORE_BOARD_HEADER);
        scoreboardBuilder.append(nameFormat(player.name()));

        List<Frame> frameList = frames.frames();
        IntStream.range(FRAME_START_INDEX, FRAME_LAST_INDEX)
                .mapToObj(index -> frameList.get(index - ONCE))
                .map(this::getNormalFormatResult)
                .forEach(scoreboardBuilder::append);
        scoreboardBuilder.append(getFinalFrameResult(frameList.get(FRAME_LAST_INDEX - ONCE)));

        System.out.println(scoreboardBuilder);
    }

    private String nameFormat(String sentence) {
        return DELIMITER + String.format(NAME_FORMAT, sentence) + DELIMITER;
    }

    private final String getNormalFormatResult(Frame frame) {
        return normalFormat(getNormalFrame(frame));
    }

    private final String getFinalFrameResult(Frame frame) {
        return finalFormat(getFinalFrame(frame));
    }

    private String getFinalFrame(Frame frame) {
        if (frame.size() == 3) {
            return thirdScore(frame);
        }
        return getNormalFrame(frame);
    }

    private String getNormalFrame(Frame frame) {
        if (frame.size() == 1) {
            return oneScore(frame);
        }
        if (frame.size() == 2) {
            return twiceScore(frame);
        }
        return emptyScore();
    }

    private final String oneScore(Frame frame) {
        return mapToSign(frame.firstCount(), true);
    }

    private final String twiceScore(Frame frame) {
        final String first = mapToSign(frame.firstCount(), false);
        final String second = mapToSign(frame.firstCount() + frame.secondCount(), false);
        return first + DELIMITER + second;
    }

    private String thirdScore(Frame frame) {
        final String first = mapToSign(frame.firstCount(), true);
        final String second = mapToSign(frame.secondCount(), true);
        final String third = mapToSign(frame.thirdCount(), true);
        return first + DELIMITER + second + DELIMITER + third;
    }

    private final String mapToSign(int hitCount, boolean availableStrike) {
        if (hitCount == ALL_PIN_CLEAR && availableStrike) {
            return STRIKE;
        }
        if (hitCount == ALL_PIN_CLEAR) {
            return SPARE;
        }
        if (hitCount == ANYTHING_NOT_HIT) {
            return GUTTER;
        }
        return String.valueOf(hitCount);
    }

    private String emptyScore() {
        return BLANK;
    }

    private String normalFormat(String sentence) {
        return String.format(NORMAL_SCORE_FORMAT, sentence) + DELIMITER;
    }

    private String finalFormat(String sentence) {
        return String.format(FINAL_SCORE_FORMAT, sentence) + DELIMITER + NEW_LINE;
    }

}
