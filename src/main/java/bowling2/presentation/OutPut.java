package bowling2.presentation;

import bowling2.domain.Board;
import bowling2.domain.Player;

import java.util.Collections;
import java.util.stream.IntStream;

import static bowling.presentation.PinFormat.DELIMITER;

public class OutPut {
    private final static int COUNT_OF_TOTAL_FRAME = 10;

    public static void printBoard(Board board, Player player) {
        printTheHead();
        printThePins(board, player);
        printTheScores(board, player);
    }

    private static void printTheHead() {
        System.out.print(Format.nameProperty());
        IntStream.rangeClosed(1, 10)
                .forEach(index -> System.out.print(Format.frameIndex(index)));
        System.out.print("\n");
    }

    private static void printThePins(Board board, Player player) {
        System.out.print(Format.playerName(player.name()));

        int countOfFrame = board.frameSize();
        int countOfEmptyFrame = COUNT_OF_TOTAL_FRAME - countOfFrame;
        board.frames().forEach(frame -> System.out.printf(Format.fallenPinsFormat(frame.getFallenPins()) + DELIMITER.format()));
        IntStream.rangeClosed(1, countOfEmptyFrame)
                .forEach(index -> System.out.printf(Format.fallenPinsFormat(Collections.emptyList()) + DELIMITER.format()));
        System.out.print("\n");
    }

    private static void printTheScores(Board board, Player player) {
        System.out.print(Format.playerName(player.name()));

        int countOfFrame = board.frameSize();
        int countOfEmptyFrame = COUNT_OF_TOTAL_FRAME - countOfFrame;
        board.frames().forEach(frame -> System.out.printf(Format.scoreFormat(frame.score()) + DELIMITER.format()));
        IntStream.rangeClosed(1, countOfEmptyFrame)
                .forEach(index -> System.out.printf(Format.emptyFormat() + DELIMITER.format()));
        System.out.print("\n");
    }
}
