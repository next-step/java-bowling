package bowling.view;

import bowling.domain.Frames;
import bowling.domain.Points;
import bowling.domain.UserName;

public class ResultView {

    private static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String BLANK = "  ";

    private static final String LINE = "|";


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
        if (points.bowlCount() == 2) {
            result = result + "|" + makeSecondResult(points.findFirstPoint(), points.findSecondPoint());
        }
        if (points.bowlCount() == 3) {
            result = result + "|" + makeSecondResult(points.findFirstPoint(), points.findSecondPoint());
            result = result + makeBonusResult(points.findBonusPoint());
        }
        return result;
    }

    private static String makeFirstResult(int firstPoint) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BLANK);
        if (firstPoint == 10) {
            stringBuilder.append("X");
            stringBuilder.append("   |");
            return stringBuilder.toString();
        }

        if (firstPoint == 0) {
            stringBuilder.append("-");
            return stringBuilder.toString();
        }
        stringBuilder.append(firstPoint);
        return stringBuilder.toString();
    }

    private static String makeSecondResult(int firstPoint, int secondPoint) {
        StringBuilder stringBuilder = new StringBuilder();

        if (secondPoint == 0) {
            stringBuilder.append("-");
            stringBuilder.append(" " + LINE);
            return stringBuilder.toString();
        }
        if (firstPoint + secondPoint == 10) {
            stringBuilder.append("/");
            stringBuilder.append(" " + LINE);
            return stringBuilder.toString();
        }
        stringBuilder.append(secondPoint);
        stringBuilder.append(" " + LINE);
        return stringBuilder.toString();
    }

    private static String makeBonusResult(int bonusPoint) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ");
        if (bonusPoint == 0) {
            stringBuilder.append("-");
            stringBuilder.append(" " + LINE);
            return stringBuilder.toString();
        }
        if (bonusPoint == 10) {
            stringBuilder.append("X");
            stringBuilder.append(" " + LINE);
            return stringBuilder.toString();
        }
        stringBuilder.append(bonusPoint);
        stringBuilder.append(" " + LINE);
        return stringBuilder.toString();
    }
}
