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

        return players.getFirstPlayerPlayingIn(currentFrameNumber)
                      .map(Player::exportPlayerName)
                      .orElseThrow(() -> new IllegalStateException("모든 플레이어가 경기를 마쳤습니다."));
    }

    public void roll(RollResult rollResult) {
        goToNextFrameIfNecessary();

        players.getFirstPlayerPlayingIn(currentFrameNumber)
               .ifPresent(player -> player.roll(rollResult));
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
        return !players.getFirstPlayerPlayingIn(currentFrameNumber).isPresent();
    }

    public PlayersDto exportPlayers() {
        return players.export();
    }

}
