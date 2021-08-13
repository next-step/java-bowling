package bowling.domain.scoreboard;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.player.PlayerGameIndex;

import java.util.Map;

public class RotationPlayerOrderStrategy implements PlayerOrderStrategy {
    private final Map<Player, Frames> framesEachPlayer;
    private int playerCounter;
    private int frameCounter;

    public RotationPlayerOrderStrategy(final Map<Player, Frames> framesEachPlayer) {
        this.framesEachPlayer = framesEachPlayer;

        playerCounter = 0;
        frameCounter = 0;
    }

    @Override
    public Player currentPlayer() {
        PlayerGameIndex playerIndex = currentPlayerIndex();

        //noinspection OptionalGetWithoutIsPresent
        return framesEachPlayer.keySet().stream()
                .filter(iPlayer -> iPlayer.matchesOrder(
                        playerIndex
                ))
                .findFirst().get();
    }


    @Override
    public void checkout() {
        if (isAllCompletedFrame()) {
            raiseFrameCounter();
            return;
        }
        if (isCompletedCurrentPlayerFrame()) {
            raisePlayerCounter();
        }
    }

    private boolean isAllCompletedFrame() {
        return framesEachPlayer.values().stream()
                .allMatch(iFrames -> iFrames.isCompleted(frameCounter));
    }

    private boolean isCompletedCurrentPlayerFrame() {
        return framesEachPlayer.get(currentPlayer())
                .isCompleted(frameCounter);
    }

    private void raiseFrameCounter() {
        ++frameCounter;
        playerCounter = 0;
    }

    private void raisePlayerCounter() {
        ++playerCounter;

        if (isCompletedCurrentPlayerFrame()) {
            raisePlayerCounter();
        }
    }

    private PlayerGameIndex currentPlayerIndex() {
        return new PlayerGameIndex(playerCounter % framesEachPlayer.size());
    }
}
