package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import bowling.domain.point.Point;
import bowling.domain.point.Points;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class ResultView {
    private static final int TOTAL_FRAME_COUNT = 10;
    private static final int FIRST_TRY_COUNT = 1;
    private static final int SECOND_TRY_COUNT = 2;
    private static final int THIRD_TRY_COUNT = 3;

    private static final int STRIKE_POINT = 10;
    private static final int GUTTER_POINT = 0;

    private static final int DEFAULT_SCORE = 0;

    private static final String MARK_JOIN_DELIMETER = "|";
    private static final String MARK_STRIKE = "X";
    private static final String MARK_SPARE = "/";
    private static final String MARK_GUTTER = "-";
    private static final String MARK_NONE = "";

    private static final String FRAME_HEAD = "|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |";
    private static final String FRAME_LINE = "|";
    private static final String FRAME_EMPTY = "        |";

    public static void viewResult(Frames frames) {
        viewFrameHead();
        viewFrames(frames);
        viewScores(frames);
    }

    private static void viewFrames(Frames frames) {
        viewPlayerName(frames.getPlayerName());
        String frameViews = frames.getFrames().stream()
                .map(frame -> viewFrame(frame))
                .collect(Collectors.joining(FRAME_LINE));
        System.out.print(frameViews + FRAME_LINE);
        printEmptyFrames(TOTAL_FRAME_COUNT - frames.size());
    }

    private static void viewScores(Frames frames) {
        viewPlayerName("");
        StringBuilder sb = new StringBuilder();
        Integer sumScore = DEFAULT_SCORE;
        LinkedList<Frame> linkedFrames = frames.getFrames();
        for (int i = 0; i < frames.size(); i++) {
            sb.append(getScores(linkedFrames.get(i), sumScore));
            sumScore += getSumScore(linkedFrames.get(i).getScore());
        }
        System.out.print(sb.toString());
        printEmptyFrames(TOTAL_FRAME_COUNT - frames.size());
    }

    private static String getScores(Frame preframe, Integer sumScore) {
        Integer score = preframe.getScore();
        if (score != null) {
            return String.format("%5s   |", score + sumScore);
        }
        return FRAME_EMPTY;
    }

    private static int getSumScore(Integer score) {
        if (score != null) {
            return score;
        }
        return DEFAULT_SCORE;
    }

    private static String viewFrame(Frame frame) {
        Points points = frame.getPoints();
        if (frame instanceof NormalFrame) {
            return String.format("%5s   ", getScoreMark(points));
        }
        return String.format(" %5s  ", getScoreMark(points));
    }

    private static void printEmptyFrames(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(FRAME_EMPTY);
        }
        System.out.println(sb.toString());
    }

    private static String getScoreMark(Points points) {
        if (points.isTryCount(FIRST_TRY_COUNT)) {
            return getMark(points.getFirstPoint());
        }
        if (points.isTryCount(SECOND_TRY_COUNT)) {
            return getMark(points.getFirstPoint(), points.getSecondPoint());
        }
        if (points.isTryCount(THIRD_TRY_COUNT)) {
            return getMark(points.getFirstPoint(), points.getSecondPoint()) + MARK_JOIN_DELIMETER + getMark(points.getThirdPoint());
        }
        return MARK_NONE;
    }

    private static String getMark(Point point1, Point point2) {
        int sumPoint = point1.getPoint() + point2.getPoint();
        if (sumPoint == STRIKE_POINT) {
            return getMark(point1) + MARK_JOIN_DELIMETER + MARK_SPARE;
        }
        return getMark(point1) + MARK_JOIN_DELIMETER + getMark(point2);
    }

    private static String getMark(Point point) {
        if (point.getPoint() == STRIKE_POINT) {
            return MARK_STRIKE;
        }
        if (point.getPoint() == GUTTER_POINT) {
            return MARK_GUTTER;
        }
        return String.valueOf(point.getPoint());
    }

    private static void viewPlayerName(String name) {
        System.out.print(String.format("|%6s  |", name));
    }

    private static void viewFrameHead() {
        System.out.println(FRAME_HEAD);
    }
}
