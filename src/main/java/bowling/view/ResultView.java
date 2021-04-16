package bowling.view;

import bowling.domain.Game;
import bowling.domain.Name;
import bowling.domain.Point;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;

public class ResultView {

    private static final String BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |   10   |";

    private static final String STRIKE_STRING = "x";
    private static final String SPARE_STRING = "/";
    private static final String DIVIDER = "|";

    private static final String EMPTY_SPACE_SINGLE = " ";
    private static final String EMPTY_SPACE_DOUBLE = "  ";
    private static final String EMPTY_SPACE_NORMAL_FRAME = EMPTY_SPACE_SINGLE + EMPTY_SPACE_DOUBLE;
    private static final String EMPTY_SPACE_FINAL_FRAME = EMPTY_SPACE_DOUBLE + EMPTY_SPACE_NORMAL_FRAME;

    private static final String LEFT_BOX_NAME = String.format("%s%s", DIVIDER, EMPTY_SPACE_DOUBLE);
    private static final String RIGHT_BOX_NAME = String.format("%s%s", EMPTY_SPACE_DOUBLE, DIVIDER);

    private static final String RIGHT_BOX_POINT = String.format("%s%s", EMPTY_SPACE_SINGLE, DIVIDER);

//    public void printScoreBoard(Game game, Name name) {
//        System.out.println(BOARD);
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder = nameStringBuilder(name, stringBuilder);
//        stringBuilder = pointStringBuilder(game, stringBuilder);
//        System.out.println(stringBuilder);
//    }
//
//    private StringBuilder nameStringBuilder(Name name, StringBuilder stringBuilder) {
//        stringBuilder.append(LEFT_BOX_NAME);
//        stringBuilder.append(name.name());
//        stringBuilder.append(RIGHT_BOX_NAME);
//        return stringBuilder;
//    }
//
//    private StringBuilder pointStringBuilder(Game game, StringBuilder stringBuilder) {
//        for (int count = 1; count <= 10; count++) {
//            stringBuilder.append(EMPTY_SPACE_SINGLE);
//            stringBuilder.append(stringFramePoint(game, count));
//            stringBuilder.append(RIGHT_BOX_POINT);
//        }
//        return stringBuilder;
//    }
//
//    private String stringFramePoint(Game game, int count) {
//        if (count > game.frameCount()) {
//            return EMPTY_SPACE_SINGLE;
//        }
//        if (count == 10) {
//            return stringFinalFramePoint(game);
//        }
//        return stringNormalFramePoint(game, count);
//    }
//
//    private String stringFinalFramePoint(Game game) {
//        FinalFrame finalFrame = game.gameFrames()
//                .finalFrame();
//        if (finalFrame.ended()) {
//            return stringFinalFrameEndedPoint(finalFrame);
//        }
//        return stringFinalFrameNotEndedPoint(finalFrame);
//    }
//
//    private String stringFinalFrameEndedPoint(FinalFrame finalFrame) {
//        Frame frame = finalFrame.frame();
//        Point bonusPoint = finalFrame.bonusPoint();
//        if (frame.striked()) {
//            return stringFinalFrameEndedStrikePoint(bonusPoint);
//        }
//        if (frame.isSpare()) {
//            return stringFinalFrameEndedSparePoint(frame, bonusPoint);
//        }
//        return frame.firstPoint().point() + "|" + frame.secondPoint().point();
//    }
//
//    private String stringFinalFrameEndedStrikePoint(Point point) {
//        if (point.striked()) {
//            return "x|x";
//        }
//        if (point.guttered()) {
//            return "x|-";
//        }
//        return "x|" + point.point();
//    }
//
//    private String stringFinalFrameEndedSparePoint(Frame frame, Point point) {
//        if (point.striked()) {
//            return frame.firstPoint().point() + "|/|x";
//        }
//        if (point.guttered()) {
//            return frame.firstPoint().point() + "|/|-";
//        }
//        return frame.firstPoint().point() + "|/|" + point.point();
//    }
//
//    private String stringFinalFrameNotEndedPoint(FinalFrame finalFrame) {
//        Frame frame = finalFrame.frame();
//        if (frame.firstPoint().played() == Point.NOT_PLAYED) {
//            return EMPTY_SPACE_SINGLE;
//        }
//        if (frame.striked()) {
//            return "x| ";
//        }
//        if (frame.isSpare() && frame.firstPoint().guttered()) {
//            return "-|/|";
//        }
//        if (frame.isSpare() && !frame.firstPoint().guttered()) {
//            return frame.firstPoint().point() + "|/|";
//        }
//        if (frame.firstPoint().guttered()) {
//            return "-|";
//        }
//        return frame.firstPoint().point() + "|";
//    }
//
//    private String stringNormalFramePoint(Game game, int count) {
//        Frame frame = game.gameFrames()
//                .normalFrames()
//                .frames()
//                .get(count - 1);
//        if (frame.ended()) {
//            return stringNormalFrameEndedPoint(frame);
//        }
//        return stringNormalFramedNotEndedPoint(frame);
//    }
//
//    private String stringNormalFrameEndedPoint(Frame frame) {
//        if (frame.striked()) {
//            return STRIKE_STRING;
//        }
//        if (frame.isSpare() && frame.firstPoint().guttered()) {
//            return "-|/";
//        }
//        if (frame.isSpare() && !frame.firstPoint().guttered()) {
//            return frame.firstPoint().point() + "|/";
//        }
//        if (frame.firstPoint().guttered()) {
//            return "-|" + frame.secondPoint().point();
//        }
//        if (frame.secondPoint().guttered()) {
//            return frame.firstPoint().point() + "|-";
//        }
//        return frame.firstPoint().point() + "|" + frame.secondPoint().point();
//    }
//
//    private String stringNormalFramedNotEndedPoint(Frame frame) {
//        if (frame.firstPoint().played() == Point.NOT_PLAYED) {
//            return EMPTY_SPACE_SINGLE;
//        }
//        if (frame.firstPoint().guttered()) {
//            return "-| ";
//        }
//        return frame.firstPoint().point() + "| ";
//    }

}
