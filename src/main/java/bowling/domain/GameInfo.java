package bowling.domain;

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

    public void run(PitchNumberStrategy numberStrategy) {
        frames.run(numberStrategy);
    }

    public boolean isGameEnd() {
        return frames.isGameEnd();
    }

    public Player player() {
        return player;
    }

    public List<Frame> frames() {
        return frames.frames();
    }

    public List<String> frameResults() {
        return frames.frameResults();
    }

    public int currentFrameNo() {
        return frames.currentEndedFrameNo();
    }
}
