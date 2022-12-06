package bowling.view;

import bowling.domain.Player;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {
    public static void printBoard(List<Player> players) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        for (Player player : players) {
            printPoints(player);
            printTotalPoints(player);
        }
        System.out.println();
    }

    private static void printPoints(Player player) {
        System.out.printf("| %4s |", player.getName());
        List<List<Integer>> pointsOfFrames = player.getPointsOfFrames();
        for (int i = 0; i < 10; i++) {
            if (i < pointsOfFrames.size()) {
                System.out.printf(" %-5s|", printPoints(pointsOfFrames.get(i)));
            } else {
                System.out.print("      |");
            }
        }
        System.out.println();
    }

    private static String printPoints(List<Integer> points) {
        StringJoiner result = new StringJoiner("|");
        int sum = 0;
        for (int point : points) {
            sum += point;
            if (point == 0) {
                result.add("-");
            } else if (point == 10) {
                result.add("X");
            } else if (sum == 10) {
                result.add("/");
            } else {
                result.add(String.valueOf(point));
            }
        }
        return result.toString();
    }

    private static void printTotalPoints(Player player) {
        System.out.print("|      |");
        List<Integer> totalPointsOfFrames = player.getTotalPointsOfFrames();
        for (int i = 0; i < 10; i++) {
            if (i < totalPointsOfFrames.size()) {
                System.out.printf("  %-4d|", totalPointsOfFrames.get(i));
            } else {
                System.out.print("      |");
            }
        }
        System.out.println();
    }

}
