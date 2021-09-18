package bowling.view;

import bowling.domain.Frames;
import bowling.domain.Point;
import bowling.domain.Points;
import bowling.domain.UserName;


public class ResultView {

    private static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String BLANK = "  ";

    private static final String LINE = "|";
    private static final String BLANK_LINE = " |";


    public static void printHeader() {
        System.out.println(HEADER);
    }


    public static void printResult(UserName userName, Frames frames) {
        System.out.print("|  " + userName + " |");

        frames.values().stream()
                .map(frame -> frame.currentFramePoints())
                .map(points -> makePointResult(points))
                .forEach(System.out::print);

        System.out.println();
    }

    private static String makePointResult(Points points) {
        if (points.bowlCount() == 0) {
            return "";
        }
        String result = makeFirstResult(points.findFirstPoint());

        if (points.findBonusPoint().isPresent() && points.isNormalFrameEnd()) {
            result = result + makeBonusResult(points);
            return result;
        }
        if (points.isNormalFrameEnd()) {
            result = result + LINE + makeSecondResult(points.findSecondPoint());
        }
        if (points.isFinalFrameEnd()) {
            result = result + LINE + makeSecondResult(points.findSecondPoint());
            result = result + makeBonusResult(points);
        }
        return result;
    }

    private static String makeFirstResult(Point firstPoint) {
        StringBuilder stringBuilder = initResult();

        stringBuilder.append(firstPoint.findPointResult());
        if (firstPoint.findScoreType().equals(ScoreType.STRIKE)) {
            stringBuilder.append("   |");
        }
        return stringBuilder.toString();
    }

    private static String makeSecondResult(Point secondPoint) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(secondPoint.findPointResult());
        stringBuilder.append(BLANK_LINE);
        return stringBuilder.toString();

    }

    private static String makeBonusResult(Points points) {
        Point bonusPoint = points.findBonusPoint().get();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(bonusPoint.findPointResult());
        stringBuilder.append(BLANK_LINE);
        return stringBuilder.toString();
    }

    private static StringBuilder initResult() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BLANK);
        return stringBuilder;
    }
}
