package bowling.view;

import bowling.domain.BowlType;
import bowling.domain.frame.FrameResultDto;
import bowling.domain.game.Bowling;
import bowling.domain.point.Point;

import java.util.ArrayList;
import java.util.List;


public class OutputView {

    private static final int FRAME_COUNT = 10;
    private static final int STRIKE_POINT = 10;
    private static final int GUTTER_POINT = 0;
    public static final String NAME = "| NAME |";
    public static final String FRAME_NUMBER = "  %02d  |";
    public static final String FRAME_NAME = "|  %3s |";
    public static final String BOWL_TYPE = "  %-4s|";
    public static final String BLANK = "";
    public static final String DELIMITER = "|";
    public static final String GUTTER = "-";
    public static final String STRIKE = "X";
    public static final String SPARE = "/";
    public static final int FIRST_INDEX = 0;

    public static void printResult(Bowling bowling) {
        printFramesRounds();
        printBowlsResult(bowling);
        System.out.print(System.lineSeparator());
    }

    private static void printFramesRounds() {
        StringBuilder builder = new StringBuilder();
        builder.append(NAME);

        for (int i = 0; i < FRAME_COUNT; i++) {
            builder.append(String.format(FRAME_NUMBER, i + 1));
        }
        System.out.println(builder.toString());
    }

    private static void printBowlsResult(Bowling bowling) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(FRAME_NAME, bowling.getPlayerName()));

        for (FrameResultDto frameResult : bowling.getResult()) {
            builder.append(String.format(BOWL_TYPE, createBowlDisplay(frameResult)));
        }

        System.out.println(builder.toString());
    }

    private static String createBowlDisplay(FrameResultDto frameResult) {
        List<Point> fallPoints = frameResult.getPoints();
        if (fallPoints.isEmpty()) {
            return BLANK;
        }

        List<String> builder = new ArrayList<>();


        builder.add(getPoint(fallPoints.get(FIRST_INDEX).getPoint()));

        int fallPointIndex = 1;
        fallPointIndex = printSpare(builder, fallPointIndex, frameResult.getScoreType());

        for (int i = fallPointIndex; i < fallPoints.size(); i++) {
            builder.add(getPoint(fallPoints.get(i).getPoint()));
        }
        return String.join(DELIMITER, builder);
    }

    private static String getPoint(int point) {


        if (point == STRIKE_POINT) {
            return STRIKE;
        }
        if (point == GUTTER_POINT) {
            return GUTTER;
        }
        return String.valueOf(point);
    }

    private static int printSpare(List<String> builder, int downPinIndex, BowlType scoreType) {
        if (scoreType == BowlType.SPARED) {
            builder.add(SPARE);
            downPinIndex++;
        }
        return downPinIndex;
    }


}
