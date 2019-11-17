package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Frame {
    private final FrameType frameType;
    private List<Score> scores;
    private Bonus bonus;

    private Frame(FrameType frameType) {
        this.scores = new ArrayList<>();
        this.frameType = frameType;
    }

    public static Frame of(int score) {
        Frame frame = new Frame(FrameType.NORMAL);
        frame.addScore(score);
        return frame;
    }

    public static Frame finalOf(int score) {
        Frame frame = new Frame(FrameType.FINAL);
        frame.addScore(score);
        return frame;
    }

    public void addScore(int score) {
        if (sumScore() + score > 10) {
            throw new IllegalArgumentException("점수들의 합은 10을 넘길 수 없습니다.");
        }
        this.scores.add(Score.of(score));
    }

    private int sumScore() {
        return scores.stream()
                .map(Score::getScore)
                .reduce(Integer::sum).orElse(0);
    }

    public void addBonus(int bonusScore) {
        if (frameType == FrameType.NORMAL) {
            throw new IllegalArgumentException("보너스 게임은 10번째 frame에서만 가능합니다");
        }
        if (getGameType() == GameType.MISS) {
            throw new IllegalArgumentException("보너스 게임은 스트라이크/스페어에서만 발생합니다");
        }
        if (this.bonus == null) {
            this.bonus = Bonus.of(getGameType(), bonusScore);
        } else {
            this.bonus.addBonus(bonusScore);
        }
    }

    public Bonus getBonus() {
        return this.bonus;
    }

    public GameType getGameType() {
        int sumScore = sumScore();
        return GameType.get(scores.size(), sumScore);
    }

    public List<Score> getScores() {
        return this.scores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(scores, frame.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores);
    }
}
