package game;

import score.BasicScores;
import score.BonusScores;
import score.Score;

import java.util.List;
import java.util.Objects;

public class Frame {
    private static final String BONUS_WITHOUT_FINAL_FRAME = "보너스 게임은 10번째 frame에서만 가능합니다";
    private final FrameType frameType;
    private BasicScores scores;
    private BonusScores bonus;

    private Frame(FrameType frameType) {
        this.scores = new BasicScores();
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
        if (this.scores.size() > 1) {
            throw new IllegalArgumentException("한 프레임에 세번 이상 공을 던질 수 없습니다.");
        }
        this.scores.addScore(score);
    }

    public void addBonus(int bonusScore) {
        if (frameType == FrameType.NORMAL) {
            throw new IllegalArgumentException(BONUS_WITHOUT_FINAL_FRAME);
        }
        if (this.bonus == null) {
            this.bonus = new BonusScores();
        }
        this.bonus.addScore(getGameType(), bonusScore);
    }

    private int sumScore() {
        return scores.sumScore();
    }

    public BonusScores getBonus() {
        return this.bonus;
    }

    public GameType getGameType() {
        int sumScore = sumScore();
        return GameType.get(scores.size(), sumScore);
    }

    public List<Score> getScores() {
        return this.scores.getScores();
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
