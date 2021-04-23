package bowling.view;

import bowling.domain.Game;
import bowling.domain.Name;
import bowling.domain.Playable;
import bowling.domain.frame.Frames;
import bowling.domain.point.FinalPoints;
import bowling.domain.point.Point;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.point.Points;

import java.util.List;

import static bowling.domain.frame.Frames.NORMAL_FRAME_COUNT;
import static bowling.domain.frame.Frames.TOTAL_FRAME_COUNT;

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
        normalFrameStrings(game.getFrames(), stringBuilder);
        stringBuilder.append(finalFrameStrings(game.getFrames()));
        return stringBuilder;
    }

    private void normalFrameStrings(Frames frames, StringBuilder stringBuilder){
        int normalFrameCount = Math.min(frames.getFrames().size(), NORMAL_FRAME_COUNT);
        for (int count = 1; count <= normalFrameCount; count++) {
            stringBuilder.append(EMPTY_SPACE_DOUBLE);
            Frame frame = (Frame) frames.getFrames().get(count-1);
            Points points = frame.getPoints();
            stringBuilder.append(normalPointsString(points));
            stringBuilder.append(RIGHT_BOX_SINGLE_SPACE);
        }
        for(int count = normalFrameCount+1;count<=NORMAL_FRAME_COUNT;count++){
            stringBuilder.append(EMPTY_SPACE_DOUBLE);
            stringBuilder.append(EMPTY_SPACE_TRIPLE);
            stringBuilder.append(RIGHT_BOX_SINGLE_SPACE);
        }
    }

    private String finalFrameStrings(Frames frames){
        if(frames.getFrames().size()<=NORMAL_FRAME_COUNT){
            return String.format(NORMAL_FRAME_FORMAT,
                    EMPTY_SPACE_TRIPLE,EMPTY_SPACE_TRIPLE,RIGHT_BOX_DOUBLE_SPACE);
        }
        FinalFrame finalFrame = (FinalFrame)frames.getFrames().get(FINAL_FRAME_INDEX);
        if(finalFrame.ended()){
            return String.format(NORMAL_FRAME_FORMAT, EMPTY_SPACE_DOUBLE, finalFrameEndedString(finalFrame), RIGHT_BOX_SINGLE_SPACE);
        }
        return String.format(NORMAL_FRAME_FORMAT, EMPTY_SPACE_DOUBLE, finalFrameNotEndedString(finalFrame), RIGHT_BOX_SINGLE_SPACE);
    }

    private String normalPointsString(Points points){
        if(points.ended()){
            return normalPointsEndedString(points);
        }
        return String.format(NORMAL_FRAME_FORMAT, gutterIfNeeded(points.getFirstPoint()), DIVIDER, EMPTY_SPACE_SINGLE);
    }

    private String normalPointsEndedString(Points points){
        if (points.striked()) {
            return String.format(NORMAL_FRAME_FORMAT, STRIKE, EMPTY_SPACE_SINGLE, EMPTY_SPACE_SINGLE);
        }
        if (points.spared()) {
            return String.format(NORMAL_FRAME_FORMAT, gutterIfNeeded(points
                    .getFirstPoint()), DIVIDER, SPARE);
        }
        return String.format(NORMAL_FRAME_FORMAT, gutterIfNeeded(points
                .getFirstPoint()), DIVIDER, gutterIfNeeded(points.getSecondPoint()));
    }

    private String finalFrameEndedString(FinalFrame frame) {
        FinalPoints finalPoints = frame.getFinalPoints();
        if (finalPoints.hasExtra() && frame.striked()) {
            return String.format(FINAL_FRAME_FORMAT, STRIKE, DIVIDER, convertIfNeeded(finalPoints.getPoints().get(FinalPoints.SECOND)),
                    DIVIDER, convertIfNeeded(finalPoints.getPoints().get(FinalPoints.THIRD)));
        }
        if (finalPoints.hasExtra() && finalPoints.spared()) {
            return String.format(FINAL_FRAME_FORMAT, gutterIfNeeded(finalPoints.getPoints().get(FinalPoints.FIRST)),
                    DIVIDER, SPARE, DIVIDER, convertIfNeeded(finalPoints.getPoints().get(FinalPoints.SECOND)));
        }
        return String.format(FINAL_FRAME_FORMAT, gutterIfNeeded(finalPoints.getPoints().get(FinalPoints.FIRST)),
                DIVIDER, convertIfNeeded(finalPoints.getPoints().get(FinalPoints.SECOND)), DIVIDER, GUTTER);
    }

    private String finalFrameNotEndedString(FinalFrame frame) {
        FinalPoints finalPoints = frame.getFinalPoints();
        if (frame.striked() && !finalPoints.getPoints().get(FinalPoints.SECOND).played()) {
            return String.format(FINAL_FRAME_FORMAT, STRIKE, DIVIDER, EMPTY_SPACE_SINGLE,
                    DIVIDER, EMPTY_SPACE_SINGLE);
        }
        if (frame.striked() && !finalPoints.getPoints().get(FinalPoints.THIRD).played()) {
            return String.format(FINAL_FRAME_FORMAT, STRIKE, DIVIDER,
                    convertIfNeeded(finalPoints.getPoints().get(FinalPoints.SECOND)), DIVIDER, EMPTY_SPACE_SINGLE);
        }
        if (frame.spared()) {
            return String.format(FINAL_FRAME_FORMAT, gutterIfNeeded(finalPoints.getPoints().get(FinalPoints.FIRST)), DIVIDER, SPARE,
                    DIVIDER, EMPTY_SPACE_SINGLE);
        }
        return String.format(FINAL_FRAME_FORMAT, gutterIfNeeded(finalPoints.getPoints().get(FinalPoints.FIRST)), DIVIDER, EMPTY_SPACE_SINGLE,
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
