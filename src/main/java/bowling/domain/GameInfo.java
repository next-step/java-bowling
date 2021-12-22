package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.strategy.PitchNumberStrategy;

import java.util.ArrayList;
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

    public List<String> scoreResults() {
        List<String> result = new ArrayList<>();
        int frameResult = 0;
        List<Score> scores = frames.scoreResults();
        for (Score score : scores) {
            frameResult += score.score();
            result.add(Integer.toString(frameResult));
        }
        return result;
    }

    public int noOf(Frame frame) {
        return frames.noOf(frame);
    }

    public int currentFallDownPinsCountOf(Frame frame) {
        return frames.currentFallDownPinsCountOf(frame);
    }
}
