package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import bowling.domain.point.Point;
import bowling.domain.point.Points;

import java.util.stream.Collectors;

public class ResultView {
    private static final String MARK_JOIN_DELIMETER = "|";
    private static final String MARK_STRIKE = "X";
    private static final String MARK_SPARE = "/";
    private static final String MARK_GUTTER = "-";
    private static final String MARK_NONE = "";
    private static final String FRAME_HEAD = "|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |";

    public static void viewRetry(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void viewResult(Frames frames) {
        viewFrameHead();
        viewFrames(frames);
    }

    private static void viewFrames(Frames frames) {
        viewPlayerName(frames.getPlayerName());
        String frameViews = frames.getFrames().stream()
                .map(frame -> viewFrame(frame))
                .collect(Collectors.joining("|"));
        System.out.println(frameViews + "|");
    }

    private static String viewFrame(Frame frame) {
        Points points = frame.getPoints();
        if (frame instanceof NormalFrame) {
            return String.format("%5s   ", getScoreMark(points));
        }
        return String.format(" %5s  ", getScoreMark(points));
    }

    private static String getScoreMark(Points points) {
        if (points.getTryCount() == 1) {
            return getMark(points.get(0));
        }
        if (points.getTryCount() == 2) {
            return getMark(points.get(0), points.get(1));
        }
        if (points.getTryCount() == 3) {
            return getMark(points.get(0), points.get(1)) + MARK_JOIN_DELIMETER + getMark(points.get(2));
        }
        return MARK_NONE;
    }

    private static String getMark(Point point1, Point point2) {
        int sumPoint = point1.getPoint() + point2.getPoint();
        if (sumPoint == 10) {
            return getMark(point1) + MARK_JOIN_DELIMETER + MARK_SPARE;
        }
        return getMark(point1) + MARK_JOIN_DELIMETER + getMark(point2);
    }

    private static String getMark(Point point) {
        if (point.getPoint() == 10) {
            return MARK_STRIKE;
        }
        if (point.getPoint() == 0) {
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
