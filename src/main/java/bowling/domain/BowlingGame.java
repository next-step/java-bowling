package bowling.domain;

import bowling.domain.frame.Frames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BowlingGame {

    private final Player player;
    private final Frames frames;

    public BowlingGame(String name) {
        this(new Player(name), new Frames());
    }

    public BowlingGame(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public void bowl(int countOfPin) {
        if (isEnd()) {
            throw new IllegalArgumentException("프레임이 종료되었습니다.");
        }

        frames.bowl(countOfPin);
    }

    public boolean isEnd() {
        return frames.isEnd();
    }

    public int getFrameNumber() {
        return frames.getFrameNumber();
    }

    public Frames getFrames() {
        return frames;
    }

    public List<Integer> getScore() {
        List<Integer> frameScore = new ArrayList<>();
        int totalScore = 0;

        for (int score : frames.getScores()) {
            totalScore += score;
            frameScore.add(totalScore);
        }

        return frameScore;
    }
}
