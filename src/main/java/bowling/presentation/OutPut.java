package bowling.presentation;

import bowling.domain.Board;
import bowling.domain.Player;

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
}
