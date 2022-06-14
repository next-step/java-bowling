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
            return String.format(centerAlignFormat(3), getSingleScoreFormat(scores.get(0)));
        }
        if (scores.size() == 2) {
            return String.format(centerAlignFormat(4), getDoubleScoreFormat(scores.get(0), scores.get(1)));
        }
        if (scores.size() == 3) {
            // TODO(jack.comeback) : 3자리수 출력 개선 필요 ex. X|5|5, 7|/|3
            return String.format("%4s|%1s", getDoubleScoreFormat(scores.get(0), scores.get(1)), getSingleScoreFormat(scores.get(2)));
        }
        return String.format(centerAlignFormat(6), "");
    }

    private static String getDoubleScoreFormat(int first, int second) {
        if (first + second == 10) {
            return first + DELIMITER.format() + SPARE.format();
        }
        if (second == 0) {
            return first + DELIMITER.format() + GUTTER.format();
        }
        return getSingleScoreFormat(first) + DELIMITER.format() + getSingleScoreFormat(second);
    }

    private static String getSingleScoreFormat(int score) {
        return score == 10 ? STRIKE.format() : String.valueOf(score);
    }

    public static void score(Frame frame, int score) {
        System.out.printf("%s프레임 투구 : %d", frame.index(), score);
        System.out.println();
    }
}
