package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.strategy.PitchNumberStrategy;

import java.util.List;

public class GameInfo {
    private final Player player;
    private final Frames frames;

    private GameInfo(Player player) {
        this(player, Frames.init());
    }

    private GameInfo(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public static GameInfo of(Player player) {
        return new GameInfo(player);
    }

    public Frame run(PitchNumberStrategy numberStrategy) {
        return frames.run(numberStrategy);
    }

    public boolean isGameEnd() {
        return frames.isGameEnd();
    }

    public Player player() {
        return player;
    }

    public List<String> frameResults() {
        return frames.frameResults();
    }

    public int noOf(Frame frame) {
        return frames.noOf(frame);
    }

    public int fallDownPinsCountOf(Frame frame) {
        return frames.fallDownPinsCountOf(frame);
    }
}
