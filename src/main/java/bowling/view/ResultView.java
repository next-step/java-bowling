package bowling.view;

import bowling.domain.GameResult;
import bowling.domain.NormalFrame;

import java.util.List;

import static bowling.domain.GameResult.MAX_FRAME_SIZE;

public class ResultView {

    private static final String BOWLING_SCORE_TOP_FORMAT = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String EMPTY_SCORE_FORMAT = "      |";
    private static final String SCORE_SEPERATE_LINE = "|";
    private static final String SPACE = " ";


    public static void printScore(GameResult gameResult) {
        System.out.println(BOWLING_SCORE_TOP_FORMAT);

        StringBuilder format = new StringBuilder(SCORE_SEPERATE_LINE);
        format.append(String.format("%5s", gameResult.getUser())).append(SPACE);
        format.append(SCORE_SEPERATE_LINE);

        format.append(makeFrameFormat(gameResult.getFrames()));

        format.append(makeFinalFrameFormat(gameResult));
        format.append(makeEmptyScoreFormat(gameResult.getFrames().size()));

        System.out.println(format);
    }

    private static String makeFrameFormat(List<NormalFrame> frames) {
        StringBuilder format = new StringBuilder();
        for (NormalFrame frame : frames) {
            format.append(frame.getScoreFormat());
            format.append(SCORE_SEPERATE_LINE);
        }

        return format.toString();
    }

    private static String makeFinalFrameFormat(GameResult gameResult) {
        if (gameResult.hasNotFinalFrame()) {
            return EMPTY_SCORE_FORMAT;
        }

        return gameResult.getFinalScoreFormat() + SCORE_SEPERATE_LINE;
    }

    private static String makeEmptyScoreFormat(int size) {
        int count = MAX_FRAME_SIZE - size;
        StringBuilder format = new StringBuilder();
        for (int i = 0; i < count; i++) {
            format.append(EMPTY_SCORE_FORMAT);
        }
        return format.toString();
    }


}
