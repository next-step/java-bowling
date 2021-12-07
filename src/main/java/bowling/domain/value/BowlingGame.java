package bowling.domain.value;

import bowling.annotations.GetterForUI;
import bowling.domain.frame.Frame;
import bowling.utils.Preconditions;

public class BowlingGame {
    private final Frames frames;
    private final Player player;

    private BowlingGame(Frames frames, Player player) {
        Preconditions.checkNotNull(frames, "frames은 필수값입니다.");
        Preconditions.checkNotNull(player, "player는 필수값입니다.");

        this.frames = frames;
        this.player = player;
    }

    public static BowlingGame of(Frames frames, Player player) {
        return new BowlingGame(frames, player);
    }

    public void pitch(Pins pins) {
        frames.pitch(pins);
    }

    public boolean isFrameOver(int playFrameNumber) {
        return currentFrameNumber() != playFrameNumber;
    }

    public int currentFrameNumber() {
        return frames.currentFrameNumber();
    }

    public boolean isGameOver() {
        return frames.isGameOver();
    }

    @GetterForUI
    public Frame getFrames(int frameNumber) {
        return frames.getFrame(frameNumber);
    }

    @GetterForUI
    public String getScore(int frameNumber) {
        return frames.getScore(frameNumber);
    }

    @GetterForUI
    public Player getPlayer() {
        return player;
    }

    @GetterForUI
    public String getPlayerName() {
        return player.getName();
    }
}
