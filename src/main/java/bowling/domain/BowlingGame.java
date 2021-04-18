package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BowlingGame {
    private final Player player;
    private final Frames frames;
    public static final String INVALID_END_PLAY = "더이상 진행 할 수 없습니다.";

    public BowlingGame(String name) {
        this(new Player(name), new Frames());
    }

    public BowlingGame(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public void play(int count) {
        if (isEnd()) {
            throw new IllegalArgumentException(INVALID_END_PLAY);
        }

        frames.play(count);
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
