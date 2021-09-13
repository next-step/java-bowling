package bowling.model.game;

import bowling.model.frame.Frame;
import bowling.model.frame.Frames;
import bowling.model.player.Player;
import bowling.model.player.PlayerNumber;

import java.util.List;

public class BowlingGame {
    private final Player player;
    private final Frames frames;

    public BowlingGame(Player player) {
        this.player = player;
        this.frames = new Frames();
    }

    public BowlingGame(Player player, List<Frame> frames) {
        this.player = player;
        this.frames = new Frames(frames);
    }

    public void play(int fallenPinCount) {
        frames.play(fallenPinCount);
    }

    public boolean canPlayNextFrame() {
        return frames.canPlayNext();
    }

    public List<Frame> frames() {
        return frames.frames();
    }

    public String playerName() {
        return player.name();
    }

    public boolean isEqualPlayerNumber(PlayerNumber playerNumber) {
        return player.isEqualNumber(playerNumber);
    }

    public PlayerNumber playerNumber() {
        return player.number();
    }

    public boolean isLastFramePitchOver() {
        return frames.isLastFramePitchOver();
    }
}
