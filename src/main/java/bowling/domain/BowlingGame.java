package bowling.domain;

import bowling.exception.GameOverException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class BowlingGame {
    private final Player player;
    private final Frames frames;

    public BowlingGame(String playerName) {
        this.player = new Player(playerName);
        this.frames = new Frames();
    }

    public void pitch(int count) {
        if (isEnd()) {
            throw new GameOverException();
        }

        frames.pitch(count);
    }

    public boolean isEnd() {
        return frames.isEnd();
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    public int getFrameCount() {
        return frames.getIndex();
    }

    public List<Integer> getScore() {
        List<Integer> totalScores = new ArrayList<>();
        int totalScore = 0;

        for (Integer score : frames.getScores()) {
            totalScore += score;
            totalScores.add(totalScore);
        }

        return totalScores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame that = (BowlingGame) o;
        return Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player);
    }
}
