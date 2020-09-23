package bowling.domain;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import bowling.domain.frame.Frames;
import bowling.ui.result.DisplayBowlingBoard;

import static java.util.stream.Collectors.toList;

public class BowlingGame {
    private final List<Player> players;
    private final DisplayBowlingBoard displayBowlingBoard;

    private BowlingGame(List<String> playerNames) {
        displayBowlingBoard = new DisplayBowlingBoard(playerNames);
        players = playerNames.stream()
                             .map(Player::of)
                             .collect(toList());
    }


    public static BowlingGame of(List<String> playerNames) {
        return new BowlingGame(playerNames);
    }

    public void gameLoop(final Function<String, Integer> fallenPinsSupplier, final Consumer<DisplayBowlingBoard> displayBowlingBoardConsumer){
        for (int fraemIndex = 0; Frames.MAX_FRAMES_SIZE > fraemIndex; ++fraemIndex){

            for(Player player : players) {
                player.whileRollAndDisplay(fraemIndex,
                                                (n) -> fallenPinsSupplier.apply(player.getName()),
                                                (g) -> displayBowlingBoardConsumer.accept(displayBowlingBoard.updateDisplayPlayerBowlingGrade(
                                                    player.getName(),
                                                    g))
                );
            }
        }
    }
}
