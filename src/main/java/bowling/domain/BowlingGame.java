package bowling.domain;

import java.util.function.Consumer;
import java.util.function.Function;

import bowling.ui.result.DisplayBowlingBoard;

public class BowlingGame {
    private final Player player;

    private BowlingGame(String playerName) {
        player = Player.of(playerName);
    }

    public static BowlingGame of(String playerName) {
        return new BowlingGame(playerName);
    }

    public void gameLoop(Function<Integer, Integer> fallenPinsSupplier, Consumer<DisplayBowlingBoard> displayBowlingBoardConsumer){
        for (int fraemIndex = 0; Frames.MAX_FRAMES_SIZE > fraemIndex; ++fraemIndex){
            final int displayFraemIndex = fraemIndex + 1;
            player.whileRoll(fraemIndex, (n) -> fallenPinsSupplier.apply(displayFraemIndex));
            displayBowlingBoardConsumer.accept(new DisplayBowlingBoard(player.toDisplayPlayerBowlingGrade()));
        }
    }
}
