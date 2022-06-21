package bowling.presentation;

import bowling.domain.Board;
import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.FallenPins;

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
                .forEach(frame -> System.out.printf("|%s", getScoreFormat(frame.pins())));
        System.out.println("|\n");
    }

    private static String centerAlignFormat(int size) {
        int MAX = 6;
        if (size > MAX) {
            throw new IllegalArgumentException(String.format("size는 %d보다 작아야 합니다. - %d", MAX, size));
        }
        return "%" + size + "s" + " ".repeat(MAX - size);
    }

    private static String getScoreFormat(FallenPins fallenPins) {
        if (fallenPins.size() == 1) {
            return String.format(centerAlignFormat(3), getSingleScoreFormat(fallenPins.first()));
        }
        if (fallenPins.size() == 2) {
            return String.format(centerAlignFormat(4), getDoubleScoreFormat(fallenPins.first(), fallenPins.second()));
        }
        if (fallenPins.size() == 3) {
            if (fallenPins.first() == TEN) {
                return String.format("%2s|%3s", getSingleScoreFormat(fallenPins.first()), getDoubleScoreFormat(fallenPins.second(), fallenPins.third()));
            }
            if (fallenPins.first() == TEN && fallenPins.second() == TEN) {
                return String.format("%2s|%1s|%1s", getSingleScoreFormat(fallenPins.first()), getSingleScoreFormat(fallenPins.second()), getSingleScoreFormat(fallenPins.third()));
            }
            return String.format("%4s|%1s", getDoubleScoreFormat(fallenPins.first(), fallenPins.second()), getSingleScoreFormat(fallenPins.third()));
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
//        System.out.printf("%s프레임 투구 : %d", frame.index(), score);
//        System.out.println();
    }
}
