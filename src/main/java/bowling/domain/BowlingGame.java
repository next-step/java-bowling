package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.List;
import java.util.Objects;

public class BowlingGame {
    private final Player player;
    private final Frames frames;

    private BowlingGame(Player player, Frames frames) {
        validate(player, frames);
        this.player = player;
        this.frames = frames;
    }

    public static BowlingGame create(Player player) {
        return new BowlingGame(player, Frames.initialize());
    }

    public void bowl(Pins pins) {
        frames.bowl(pins);
    }

    public String getPlayerName() {
        return player.getName();
    }

    public boolean hasNextPitching() {
        return frames.hasNextPitching();
    }

    public int getCurrentFrameIndex() {
        return frames.lastFrameIndex();
    }

    public boolean hasFrameInProgress(FrameIndex index) {
        return frames.hasFrameInProgress(index);
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    private void validate(Player player, Frames frames) {
        if (Objects.isNull(player)) {
            throw new IllegalArgumentException("전달된 플레이어가 null입니다.");
        }
        if (Objects.isNull(frames)) {
            throw new IllegalArgumentException("전달된 볼링의 프레임들이 null입니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame that = (BowlingGame) o;
        return Objects.equals(player, that.player) && Objects.equals(frames, that.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, frames);
    }
}
