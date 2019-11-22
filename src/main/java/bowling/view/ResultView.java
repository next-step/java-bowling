package bowling.view;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.GameResult;

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

        for (Frame frame1 : gameResult.getFrames()) {
            format.append(frame1.getScoreFormat());
            format.append(SCORE_SEPERATE_LINE);
        }

        format.append(makeFinalFrameFormat(gameResult));
        format.append(makeEmptyScoreFormat(gameResult.getFrames().size()));

        System.out.println(format);
    }

    private static String makeFinalFrameFormat(GameResult gameResult) {
        if (gameResult.hasNotFinalFrame()) {
            return EMPTY_SCORE_FORMAT;
        }

        return gameResult.getFinalScoreFormat() + SCORE_SEPERATE_LINE;
    }

    private static String makeEmptyScoreFormat(int size) {
        int count = 9 - size;
        StringBuilder format = new StringBuilder();
        for (int i = 0; i < count; i++) {
            format.append(EMPTY_SCORE_FORMAT);
        }
        return format.toString();
    }


}
