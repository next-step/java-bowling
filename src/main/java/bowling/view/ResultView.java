package bowling.view;

import bowling.domain.GameRecord;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.List;

import static bowling.domain.frame.NormalFrames.MAX_FRAME_SIZE;

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

        System.out.println(format);
    }

    private static String makeFrameFormat(List<NormalFrame> frames) {
        StringBuilder format = new StringBuilder();
        for (NormalFrame frame : frames) {
            StatusDto statusDto = StatusDto.statusToDto(frame.getStatus());
            format.append(statusDto.getScoreFormat());
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

    private static String getFinalScoreFormat(FinalFrame finalFrame) {
        StatusDto statusDto = StatusDto.statusToDto(finalFrame.getStatus());
        return statusDto.getFinalScoreFormat();
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
