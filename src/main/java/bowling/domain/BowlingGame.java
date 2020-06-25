package bowling.domain;

import bowling.domain.dto.StateDtos;
import bowling.domain.frame.Frames;
import bowling.domain.pin.PinCount;
import bowling.domain.player.Player;

import java.util.List;

public class BowlingGame {

    private final Player player;
    private final Frames frames;

    private BowlingGame(final Player player) {
        this.player = player;
        frames = Frames.newInstance();
    }

    public static BowlingGame of(final Player player) {
        return new BowlingGame(player);
    }

    public void play(final int hitCount) {
        frames.bowl(PinCount.of(hitCount));
    }

    public boolean isGameOver() {
        return this.frames.isGameOver();
    }

    public String getPlayerName() {
        return this.player.getName();
    }

    public int getFrameNumber() {
        return this.frames.getFrameNumber();
    }

    public List<StateDtos> getFrameResults() {
        return frames.getFrameResult();
    }
}
