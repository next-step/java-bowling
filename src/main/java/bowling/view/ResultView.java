package bowling.view;

import bowling.domain.Frame;
import bowling.domain.FrameType;
import bowling.domain.GameRecord;
import bowling.domain.NormalFrame;

import java.util.List;
import java.util.Optional;

import static bowling.domain.NormalFrames.MAX_FRAME_SIZE;

public class ResultView {

    private static final String BOWLING_SCORE_TOP_FORMAT = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String EMPTY_SCORE_FORMAT = "      |";
    private static final String SCORE_SEPERATE_LINE = "|";
    private static final String SPACE = " ";


    public static void printScore(GameRecord gameRecord) {
        System.out.println(BOWLING_SCORE_TOP_FORMAT);

        StringBuilder format = new StringBuilder(SCORE_SEPERATE_LINE);
        format.append(String.format("%5s", gameRecord.getUser())).append(SPACE);
        format.append(SCORE_SEPERATE_LINE);

        format.append(makeFrameFormat(gameRecord.getNormalFrames()));
        format.append(makeFinalFrameFormat(gameRecord));
        format.append(makeEmptyScoreFormat(gameRecord.getNormalFrames().size()));
        format.append(makeResultFormat(gameRecord.getNormalFrames()));

        System.out.println(format);
    }

    private static String makeResultFormat(List<NormalFrame> normalFrames) {
        StringBuilder format = new StringBuilder();
        for (NormalFrame frame : normalFrames) {
            format.append(getNormalScoreFormat(frame));
            format.append(SCORE_SEPERATE_LINE);
        }

        return format.toString();
    }

    private static String makeFrameFormat(List<NormalFrame> frames) {
        StringBuilder format = new StringBuilder();
        for (NormalFrame frame : frames) {
            format.append(getNormalScoreFormat(frame));
            format.append(SCORE_SEPERATE_LINE);
        }

        return format.toString();
    }

    private static String makeFinalFrameFormat(GameRecord gameRecord) {
        if (gameRecord.hasNotFinalFrame()) {
            return EMPTY_SCORE_FORMAT;
        }

        return getFinalScoreFormat(gameRecord.getFinalFrame()) + SCORE_SEPERATE_LINE;
    }

    private static String makeEmptyScoreFormat(int size) {
        int count = MAX_FRAME_SIZE - size;
        StringBuilder format = new StringBuilder();
        for (int i = 0; i < count; i++) {
            format.append(EMPTY_SCORE_FORMAT);
        }
        return format.toString();
    }

    public static String getNormalScoreFormat(Frame frame) {
        String format = String.format("%3s", findScoreFormat(frame, 0));

        if (frame.hasSize(1)) {
            return format + "   ";
        }

        return format + String.format("%s%-2s", "|", findSecondScoreFormat(frame));
    }

    public static String getFinalScoreFormat(Frame frame) {
        String format = String.format("%3s", findScoreFormat(frame, 0));

        if (frame.hasSize(1)) {
            return format + "   ";
        }

        if (frame.hasSize(2)) {
            return format + String.format("%s%-2s", "|", findSecondScoreFormat(frame));
        }

        return String.format("%2s|%s|%s", findScoreFormat(frame, 0), findSecondScoreFormat(frame), findScoreFormat(frame, 2));
    }

    public static String findScoreFormat(Frame frame, int index) {
        int score = frame.getScores().get(index);

        Optional<FrameType> frameType = FrameType.findByScore(score);
        if (frameType.isPresent()) {
            return frameType.get().getCode();
        }
        return String.valueOf(score);
    }

    public static String findSecondScoreFormat(Frame frame) {
        int score = frame.getScores().get(1);
        if (frame.isSpare()) {
            return FrameType.SPARE.getCode();
        }

        Optional<FrameType> frameType = FrameType.findByScore(score);
        if (frameType.isPresent()) {
            return frameType.get().getCode();
        }

        return String.valueOf(score);
    }

}
