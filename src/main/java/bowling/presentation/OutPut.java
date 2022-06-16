package bowling.presentation;

import bowling.domain.Board;
import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Scores;

import static bowling.presentation.ScoreFormat.*;

public class OutPut {
    private static final int TEN = 10;

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
            throw new IllegalArgumentException(String.format("size는 %d보다 작아야 합니다. - %d", MAX, size));
        }
        return "%" + size + "s" + " ".repeat(MAX - size);
    }

    private static String getScoreFormat(Scores scores) {
        if (scores.size() == 1) {
            return String.format(centerAlignFormat(3), getSingleScoreFormat(scores.first()));
        }
        if (scores.size() == 2) {
            return String.format(centerAlignFormat(4), getDoubleScoreFormat(scores.first(), scores.second()));
        }
        if (scores.size() == 3) {
            if (scores.first() == TEN) {
                return String.format("%2s|%3s", getSingleScoreFormat(scores.first()), getDoubleScoreFormat(scores.second(), scores.third()));
            }
            if (scores.first() == TEN && scores.second() == TEN) {
                return String.format("%2s|%1s|%1s", getSingleScoreFormat(scores.first()), getSingleScoreFormat(scores.second()), getSingleScoreFormat(scores.third()));
            }
            return String.format("%4s|%1s", getDoubleScoreFormat(scores.first(), scores.second()), getSingleScoreFormat(scores.third()));
        }
        return String.format(centerAlignFormat(6), "");
    }

    private static String getDoubleScoreFormat(int first, int second) {
        if (first + second == TEN) {
            return first + DELIMITER.format() + SPARE.format();
        }
        if (second == 0) {
            return first + DELIMITER.format() + GUTTER.format();
        }
        return getSingleScoreFormat(first) + DELIMITER.format() + getSingleScoreFormat(second);
    }

    private static String getSingleScoreFormat(int score) {
        return score == TEN ? STRIKE.format() : String.valueOf(score);
    }

    public static void score(Frame frame, int score) {
        System.out.printf("%s프레임 투구 : %d", frame.index(), score);
        System.out.println();
    }
}
