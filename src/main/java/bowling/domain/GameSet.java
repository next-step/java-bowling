package bowling.domain;

import bowling.domain.dto.ScoreDto;
import bowling.domain.dto.StateDtos;
import bowling.domain.frame.Frames;
import bowling.domain.pin.PinCount;
import bowling.domain.player.Player;

import java.util.List;

public class GameSet {

    private final Player player;
    private final Frames frames;

    private GameSet(final Player player) {
        this.player = player;
        frames = Frames.newInstance();
    }

    public static GameSet of(final Player player) {
        return new GameSet(player);
    }

    public void play(final int hitCount) {
        frames.bowl(PinCount.of(hitCount));
    }

    public boolean isGameOver() {
        return this.frames.isGameOver();
    }

    public boolean isTurnOver() {
        return this.frames.isTurnOver();
    }

    public String getPlayerName() {
        return this.player.getName();
    }

    public List<StateDtos> getFrameResults() {
        return frames.getFrameResult();
    }

    public List<ScoreDto> getScoreResults() {
        return frames.getScoreResult();
    }
}
