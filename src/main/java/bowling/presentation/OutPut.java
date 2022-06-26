package bowling.presentation;

import bowling.domain.Board;
import bowling.domain.Player;

import java.util.stream.IntStream;

import static bowling.presentation.ScoreFormat.DELIMITER;

public class OutPut {
    public static void board(Board board, Player player) {
        System.out.print(Format.nameProperty());
        board.frames()
                .forEach(frame -> System.out.print(Format.frameIndex(frame.index())));
        System.out.print("\n");
        System.out.print(Format.playerName(player.name()));
        board.frames()
                .forEach(frame -> System.out.printf(Format.fallenPinsFormat(frame.pins()) + DELIMITER.format()));
        System.out.println("\n");
    }

    public static void board2(Board board, Player player) {
        System.out.print(Format.nameProperty());
        IntStream.rangeClosed(1, 10)
                .forEach(index -> System.out.print(Format.frameIndex(index)));
        System.out.print("\n");
        System.out.print(Format.playerName(player.name()));
        IntStream.rangeClosed(1, 10)
                .forEach(index -> {
                    // board.frame() 없으면 "" 출력
                });


    }
}
