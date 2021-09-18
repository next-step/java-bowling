package bowling.view;

import bowling.domain.*;


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
        String result = makeFirstResult(points.findFirstPointScore());

        if (points.findBonusPoint().isPresent() && points.bowlCount() == NormalFrame.MAX_TRY) {
            result = result + makeBonusResult(points);
            return result;
        }
        if (points.bowlCount() == NormalFrame.MAX_TRY) {
            result = result + LINE + makeSecondResult(points.findFirstPointScore(), points.findSecondPointScore());
        }
        if (points.bowlCount() == FinalFrame.MAX_TRY) {
            result = result + LINE + makeSecondResult(points.findFirstPointScore(), points.findSecondPointScore());
            result = result + makeBonusResult(points);
        }
        return result;
    }

    private static String makeFirstResult(int firstPoint) {
        StringBuilder stringBuilder = initResult();

        if (firstPoint == Point.MAX_POINT) {
            stringBuilder.append(ScoreType.STRIKE.value());
            stringBuilder.append("   |");
            return stringBuilder.toString();
        }

        if (firstPoint == Point.MIN_POINT) {
            stringBuilder.append(ScoreType.GUTTER.value());
            return stringBuilder.toString();
        }
        stringBuilder.append(firstPoint);
        return stringBuilder.toString();
    }

    private static String makeSecondResult(int firstPoint, int secondPoint) {
        StringBuilder stringBuilder = new StringBuilder();

        if (secondPoint == Point.MIN_POINT) {
            return makeGutter(stringBuilder);
        }
        if (firstPoint + secondPoint == Point.MAX_POINT) {
            stringBuilder.append(ScoreType.SPARE.value());
            stringBuilder.append(BLANK_LINE);
            return stringBuilder.toString();
        }
        return makeMiss(stringBuilder, secondPoint);

    }

    private static String makeBonusResult(Points points) {
        int bonusPoint = points.findBonusPoint().get().currentPoint();

        StringBuilder stringBuilder = initResult();
        if (bonusPoint == Point.MIN_POINT) {
            return makeGutter(stringBuilder);
        }
        if (bonusPoint == Point.MAX_POINT) {
            stringBuilder.append(ScoreType.STRIKE.value());
            stringBuilder.append(BLANK_LINE);
            return stringBuilder.toString();
        }
        return makeMiss(stringBuilder, bonusPoint);
    }

    private static StringBuilder initResult() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BLANK);
        return stringBuilder;
    }

    private static String makeMiss(StringBuilder stringBuilder, int point) {
        stringBuilder.append(point);
        stringBuilder.append(BLANK_LINE);
        return stringBuilder.toString();
    }

    private static String makeGutter(StringBuilder stringBuilder) {
        stringBuilder.append(ScoreType.GUTTER.value());
        stringBuilder.append(BLANK_LINE);
        return stringBuilder.toString();
    }
}
