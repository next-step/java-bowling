package bowling.presentation;

import bowling.domain.Board;
import bowling.domain.Player;
import bowling.domain.frame.FallenPins;

import java.util.stream.IntStream;

import static bowling.presentation.ScoreFormat.DELIMITER;

public class OutPut {
    public static void board(Board board, Player player) {
        printTheUpper();
        printTheBottom(board, player);
    }

    private static void printTheUpper() {
        System.out.print(Format.nameProperty());
        IntStream.rangeClosed(1, 10)
                .forEach(index -> System.out.print(Format.frameIndex(index)));
        System.out.print("\n");
    }

    private static void printTheBottom(Board board, Player player) {
        System.out.print(Format.playerName(player.name()));

        int countOfFrame = board.frames().size();
        int countOfEmptyFrame = 10 - countOfFrame;
        board.frames().forEach(frame -> System.out.printf(Format.fallenPinsFormat(frame.pins()) + DELIMITER.format()));
        IntStream.rangeClosed(1, countOfEmptyFrame)
                .forEach(index -> System.out.printf(Format.fallenPinsFormat(FallenPins.emptyPins()) + DELIMITER.format()));
        System.out.print("\n");
    }
}
