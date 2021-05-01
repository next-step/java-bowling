package bowling.domain;

import bowling.domain.engine.frame.FrameNumber;
import bowling.dto.PlayersDto;

public class BowlingGame {

    private final Players players;
    private FrameNumber currentFrameNumber = FrameNumber.firstFrame();

    public BowlingGame(Players players) {
        this.players = players;
    }

    public String getNameOfCurrentPlayer() {
        goToNextFrameIfNecessary();

        Player player = players.getFirstPlayerPlayingIn(currentFrameNumber);

        return player.exportPlayerName();
    }

    public void roll(RollResult rollResult) {
        goToNextFrameIfNecessary();

        Player player = players.getFirstPlayerPlayingIn(currentFrameNumber);
        player.roll(rollResult);
    }

    private void goToNextFrameIfNecessary() {
        if (isAllPlayersPlayedInCurrentFrame()) {
            currentFrameNumber = currentFrameNumber.getNextNumber();
        }
    }

    public boolean isFinished() {
        return isLastFrame() && isAllPlayersPlayedInCurrentFrame();
    }

    private boolean isLastFrame() {
        return currentFrameNumber.equals(FrameNumber.finalFrame());
    }

    private boolean isAllPlayersPlayedInCurrentFrame() {
        return players.isAllPlayersFinishedIn(currentFrameNumber);
    }

    public PlayersDto exportPlayers() {
        return players.export();
    }

}
