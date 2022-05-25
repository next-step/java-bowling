package bowling.domain;

import java.util.List;
import java.util.Objects;

public class BowlingGame {
    private final Player player;
    private final Frames frames;

    public BowlingGame(Player player, Frames frames) {
        validateNull(player, frames);
        this.player = player;
        this.frames = frames;
    }

    public BowlingGame(Player player) {
        this(player, Frames.create());
    }

    private void validateNull(Player player, Frames frames) {
        if (Objects.isNull(player)) {
            throw new IllegalArgumentException("플레이어가 null입니다.");
        }

        if (Objects.isNull(frames)) {
            throw new IllegalArgumentException("프레임들이 null입니다.");
        }
    }

    public void bowl(Pitching pitching) {
        frames.bowl(pitching);
    }

    public String getPlayerName() {
        return player.toString();
    }

    public boolean isNextPitching() {
        return frames.isNextPitching();
    }

    public int getCurrentFrameNumber() {
        return frames.getCurrentFrameNumber();
    }

    public List<Frame> getFrames() {
        return frames.getValues();
    }
}
