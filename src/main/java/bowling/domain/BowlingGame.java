package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.List;

public class BowlingGame {
    private final Player player;
    private final Frames frames;

    public BowlingGame(Player player, Frames frames) {
        validatePlayerAndFrames(player, frames);
        this.player = player;
        this.frames = frames;
    }

    private void validatePlayerAndFrames(Player player, Frames frames) {
        if (player == null) {
            throw new IllegalArgumentException("플레이어는 null 일 수 없습니다.");
        }
        if (frames == null) {
            throw new IllegalArgumentException("frames 는 null 일 수 없습니다.");
        }
    }

    public static BowlingGame create(Player player) {
        return new BowlingGame(player, Frames.initialize());
    }

    public int getCurrentFrameNumber() {
        return frames.getCurrentFrameNumber();
    }

    public boolean isRunning() {
        return !frames.isFinalFrameEnd();
    }

    public void bowl(Pins pins) {
        frames.bowl(pins);
    }

    public String playerName() {
        return player.name();
    }

    public List<Frame> frames() {
        return frames.frames();
    }
}