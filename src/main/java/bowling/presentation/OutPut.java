package bowling.presentation;

import bowling.domain.Board;
import bowling.domain.Player;
import bowling.domain.frame.Frame;

import java.util.List;

import static bowling.presentation.ScoreFormat.*;

public class OutPut {

    public static void board(Board board, Player player) {
        System.out.printf(DELIMITER.format() + centerAlignFormat(5), "NAME");
        board.frames()
                .forEach(frame -> {
                    String number = String.format("%02d", frame.index());
                    System.out.printf(DELIMITER.format() + centerAlignFormat(4), number);
                });
        System.out.print("|\n");
        System.out.printf(DELIMITER.format() + centerAlignFormat(4), player.name());
        board.frames()
                .forEach(frame -> System.out.printf("|%s", getScoreFormat(frame.scores())));
        System.out.println("|\n");
    }

    private static String centerAlignFormat(int size) {
        int MAX = 6;
        if (size > MAX) {
            throw new IllegalArgumentException(String.format("size는 %d보다 작아야 합나디. - %d", MAX, size));
        }
        return "%" + size + "s" + " ".repeat(MAX - size);
    }

    // TODO(jack.comeback) : scores 일급콜렉션
    private static String getScoreFormat(List<Integer> scores) {
        if (scores.size() == 1) {
            return String.format(centerAlignFormat(3), getSingleScoreFormat(scores));
        }
        if (scores.size() == 2) {
            return String.format(centerAlignFormat(4), getDoubleScoreFormat(scores));
        }
        if (scores.size() == 3) {
            return String.format("%4s|%1s", getDoubleScoreFormat(scores.subList(0,2)), getSingleScoreFormat(scores.subList(2,3)));
        }
        return String.format(centerAlignFormat(6), "");
    }

    private static String getDoubleScoreFormat(List<Integer> scores) {
        if (scores.stream().mapToInt(i -> i).sum() == 10) {
            return scores.get(0) + DELIMITER.format() + SPARE.format();
        }
        if (scores.get(1) == 0) {
            return scores.get(0) + DELIMITER.format() + GUTTER.format();
        }
        return scores.get(0) + DELIMITER.format() + scores.get(1);
    }

    private static String getSingleScoreFormat(List<Integer> scores) {
        return scores.get(0) == 10 ? STRIKE.format() : scores.get(0).toString();
    }

    public static void score(Frame frame, int score) {
        System.out.printf("%s프레임 투구 : %d", frame.index(), score);
        System.out.println();
    }
}
