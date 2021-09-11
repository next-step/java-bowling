package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

import java.util.List;
import java.util.stream.Collectors;

public final class BowlingGame {

    private final Player player;
    private final Frames frames;

    public BowlingGame(final String playerName) {
        this.player = Player.valueOf(playerName);
        this.frames = Frames.of();
    }

    public void input(final int knockDownNumber) {
        frames.add(knockDownNumber);
    }

    public int nextRoundNumber() {
        return frames.nextTurnRoundNumber();
    }

    public boolean isGameOver() {
        return frames.isFinished();
    }

    public List<Frame> getCanCalculateFrames() {
        return getFrames().stream()
                .filter(Frame::canCalculateScore)
                .collect(Collectors.toList());
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    public Player getPlayer() {
        return player;
    }
}
