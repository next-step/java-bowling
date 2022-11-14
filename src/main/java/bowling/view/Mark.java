package bowling.view;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.NormalFrame;
import bowling.domain.Scores;
import bowling.type.BowlingScore;

import java.util.Arrays;

import static bowling.view.ResultView.*;

public class Mark {

    public static String print(Frame frame){
        StringBuilder result = new StringBuilder();
        result.append(getFirstMark(frame.getFirstScore()));
        if(isFirstTry(frame)) {
            return result.toString();
        }
        if(isLastFrame(frame)) {
            return getFinalMarker(frame);
        }
        result.append(MARK_DIVIDER);
        result.append(getDefaultMarker(frame));
        return result.toString();
    }

    private static String getDefaultMarker(Frame frame) {
        if(frame.getBowlingScore() == BowlingScore.SPARE) {
            return SPARE_MARK;
        }
        if(frame.getBowlingScore() == BowlingScore.GUTTER) {
            return GUTTER_MARK;
        }
        return String.valueOf(frame.getSecondScore());
    }

    private static boolean isLastFrame(Frame frame) {
        return frame.getOrder() == MAX_FRAME_NUMBER;
    }

    private static boolean isFirstTry(Frame frame) {
        return frame.getScoreSize() == 1;
    }

    private static String getFinalMarker(Frame frame){
        StringBuilder result = new StringBuilder();
        result.append(getFirstMark(frame.getFirstScore()));
        result.append(MARK_DIVIDER);

        if(frame.getScoreSize() == 2) {
            return result.append(frame.getSecondScore()).toString();
        }

        result.append(getSecondMark(frame));
        if(!hasOneMoreChane(frame)) {
            return result.toString();
        }
        result.append(MARK_DIVIDER);

        result.append(getThirdMark(frame));
        return result.toString();
    }

    private static String getThirdMark(Frame frame) {
        if(frame.getThirdScore() == STRIKE_NUMBER) {
            return STRIKE_MARK;
        }
        if(frame.getFirstScore() == STRIKE_NUMBER &&
                frame.getSecondScore() + frame.getThirdScore() == SPARE_DECIDE_NUMBER) {
            return SPARE_MARK;
        }
        return String.valueOf(frame.getThirdScore());
    }

    private static String getSecondMark(Frame frame) {
        if(frame.getSecondScore() == STRIKE_NUMBER) {
            return STRIKE_MARK;
        }
        if(frame.getFirstScore() + frame.getSecondScore() < SPARE_DECIDE_NUMBER) {
            return String.valueOf(frame.getSecondScore());
        }
        if(frame.getFirstScore() + frame.getSecondScore() == SPARE_DECIDE_NUMBER && frame.getFirstScore() != STRIKE_NUMBER) {
            return SPARE_MARK;
        }
        return String.valueOf(frame.getSecondScore());
    }

    private static boolean hasOneMoreChane(Frame frame) {
        return frame.getFirstScore() == STRIKE_NUMBER || frame.getFirstScore() + frame.getSecondScore() == SPARE_DECIDE_NUMBER;
    }

    private static String getFirstMark(int firstScore) {
        if(firstScore == STRIKE_NUMBER) {
            return STRIKE_MARK;
        }
        return String.valueOf(firstScore);
    }

}
