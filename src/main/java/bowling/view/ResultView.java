package bowling.view;

import bowling.domain.Game;
import bowling.domain.Name;
import bowling.domain.Point;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;

public class ResultView {

    private static final String BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |   10   |";

    private static final int FINAL_FRAME_INDEX = 9;

    private static final String STRIKE = "x";
    private static final String SPARE = "/";
    private static final String DIVIDER = "|";
    private static final String GUTTER = "-";

    private static final String EMPTY_SPACE_SINGLE = " ";
    private static final String EMPTY_SPACE_DOUBLE = "  ";
    private static final String EMPTY_SPACE_TRIPLE = EMPTY_SPACE_SINGLE + EMPTY_SPACE_DOUBLE;

    private static final String LEFT_BOX_SINGLE_SPACE = String.format("%s%s", DIVIDER, EMPTY_SPACE_SINGLE);
    private static final String RIGHT_BOX_SINGLE_SPACE = String.format("%s%s", EMPTY_SPACE_SINGLE, DIVIDER);
    private static final String RIGHT_BOX_DOUBLE_SPACE = String.format("%s%s", EMPTY_SPACE_DOUBLE, DIVIDER);

    private static final String NORMAL_FRAME_FORMAT = "%s%s%s";
    private static final String FINAL_FRAME_FORMAT = "%s%s%s%s%s";

    public void printScoreBoard(Game game, Name name) {
        System.out.println(BOARD);
        StringBuilder stringBuilder = new StringBuilder();
        nameStringBuilder(name, stringBuilder);
        pointStringBuilder(game, stringBuilder);
        System.out.println(stringBuilder);
    }

    private StringBuilder nameStringBuilder(Name name, StringBuilder stringBuilder) {
        stringBuilder.append(LEFT_BOX_SINGLE_SPACE);
        stringBuilder.append(name.name());
        stringBuilder.append(RIGHT_BOX_DOUBLE_SPACE);
        return stringBuilder;
    }

    private StringBuilder pointStringBuilder(Game game, StringBuilder stringBuilder) {
        for (int count = 1; count <= 9; count++) {
            stringBuilder.append(EMPTY_SPACE_DOUBLE);
            stringBuilder.append(normalFrameString((Frame) game.getFrames()[count - 1]));
            stringBuilder.append(RIGHT_BOX_SINGLE_SPACE);
        }
        stringBuilder.append(EMPTY_SPACE_DOUBLE);
        stringBuilder.append(finalFrameString((FinalFrame) game.getFrames()[FINAL_FRAME_INDEX]));
        stringBuilder.append(RIGHT_BOX_SINGLE_SPACE);

        return stringBuilder;
    }

    private String normalFrameString(Frame frame) {
        if (frame == null) {
            return EMPTY_SPACE_TRIPLE;
        }
        if (frame.ended()) {
            return normalFrameEndedString(frame);
        }
        return String.format(NORMAL_FRAME_FORMAT, gutterIfNeeded(frame.firstPoint()), DIVIDER, EMPTY_SPACE_SINGLE);
    }

    private String normalFrameEndedString(Frame frame) {
        if (frame.striked()) {
            return String.format(NORMAL_FRAME_FORMAT, STRIKE, EMPTY_SPACE_SINGLE, EMPTY_SPACE_SINGLE);
        }
        if (frame.spared()) {
            return String.format(NORMAL_FRAME_FORMAT, gutterIfNeeded(frame.firstPoint()), DIVIDER, SPARE);
        }
        return String.format(NORMAL_FRAME_FORMAT, gutterIfNeeded(frame.firstPoint()), DIVIDER, gutterIfNeeded(frame.secondPoint()));
    }

    private String finalFrameString(FinalFrame frame) {
        if (frame == null) {
            return EMPTY_SPACE_TRIPLE + EMPTY_SPACE_DOUBLE;
        }
        if (frame.ended()) {
            return finalFrameEndedString(frame);
        }
        return finalFrameNotEndedString(frame);
    }

    private String finalFrameEndedString(FinalFrame frame) {
        if (frame.hasExtra() && frame.striked()) {
            return String.format(FINAL_FRAME_FORMAT, STRIKE, DIVIDER, convertIfNeeded(frame.getPoints()[FinalFrame.SECOND]),
                    DIVIDER, convertIfNeeded(frame.getPoints()[FinalFrame.THIRD]));
        }
        if (frame.hasExtra() && frame.spared()) {
            return String.format(FINAL_FRAME_FORMAT, gutterIfNeeded(frame.getPoints()[FinalFrame.FIRST]), DIVIDER, SPARE,
                    DIVIDER, convertIfNeeded(frame.getPoints()[FinalFrame.SECOND]));
        }
        return String.format(FINAL_FRAME_FORMAT, gutterIfNeeded(frame.getPoints()[FinalFrame.FIRST]), DIVIDER, convertIfNeeded(frame.getPoints()[FinalFrame.SECOND]),
                DIVIDER, GUTTER);
    }

    private String finalFrameNotEndedString(FinalFrame frame) {
        if (frame.striked() && !frame.getPoints()[FinalFrame.SECOND].played()) {
            return String.format(FINAL_FRAME_FORMAT, STRIKE, DIVIDER, EMPTY_SPACE_SINGLE,
                    DIVIDER, EMPTY_SPACE_SINGLE);
        }
        if (frame.striked() && !frame.getPoints()[FinalFrame.THIRD].played()) {
            return String.format(FINAL_FRAME_FORMAT, STRIKE, DIVIDER, convertIfNeeded(frame.getPoints()[FinalFrame.SECOND]),
                    DIVIDER, EMPTY_SPACE_SINGLE);
        }
        if (frame.spared()) {
            return String.format(FINAL_FRAME_FORMAT, gutterIfNeeded(frame.getPoints()[FinalFrame.FIRST]), DIVIDER, SPARE,
                    DIVIDER, EMPTY_SPACE_SINGLE);
        }
        return String.format(FINAL_FRAME_FORMAT, gutterIfNeeded(frame.getPoints()[FinalFrame.FIRST]), DIVIDER, EMPTY_SPACE_SINGLE,
                DIVIDER, EMPTY_SPACE_SINGLE);
    }

    private String convertIfNeeded(Point point) {
        if (point.guttered()) {
            return GUTTER;
        }
        if (point.striked()) {
            return STRIKE;
        }
        return String.valueOf(point.point());
    }

    private String gutterIfNeeded(Point point) {
        if (point.guttered()) {
            return GUTTER;
        }
        return String.valueOf(point.point());
    }

}
