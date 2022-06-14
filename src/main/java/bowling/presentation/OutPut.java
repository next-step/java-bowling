package bowling.presentation;

import bowling.domain.Board;
import bowling.domain.Player;
import bowling.domain.frame.Frame;

import java.util.List;

public class OutPut {

    public static void board(Board board, Player player) {
        System.out.printf("|%5s ", "NAME");
        board.frames()
                .forEach(frame -> {
                    String number = String.format("%02d", frame.index());
                    System.out.printf("|%4s  ", number);
                });
        System.out.print("|\n");
        System.out.printf("|%4s  ", player.name());
        board.frames()
                .forEach(frame -> System.out.printf("|%s", getScoreFormat(frame.scores())));
        System.out.println("|\n");
    }

    // TODO(jack.comeback) : scores 일급콜렉션
    private static String getScoreFormat(List<Integer> scores) {
        if (scores.size() == 1) {
            return String.format("%3s   ", getSingleScoreFormat(scores));
        }
        if (scores.size() == 2) {
            return String.format("%4s  ", getDoubleScoreFormat(scores));
        }
        if (scores.size() == 3) {
            return String.format("%4s|%1s", getDoubleScoreFormat(scores.subList(0,2)), getSingleScoreFormat(scores.subList(2,3)));
        }
        return String.format("%6s", "");
    }

    private static String getDoubleScoreFormat(List<Integer> scores) {
        if (scores.stream().mapToInt(i -> i).sum() == 10) {
            return scores.get(0) + "|/";
        }
        if (scores.get(1) == 0) {
            return scores.get(0) + "|-";
        }
        return scores.get(0) + "|" + scores.get(1);
    }

    private static String getSingleScoreFormat(List<Integer> scores) {
        return scores.get(0) == 10 ? "X" : scores.get(0).toString();
    }

    public static void score(Frame frame, int score) {
        System.out.printf("%s프레임 투구 : %d", frame.index(), score);
        System.out.println();
    }
}
