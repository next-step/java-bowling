package game;

import score.BasicScores;
import score.BonusScores;
import score.Score;

import java.util.List;
import java.util.Objects;

import static game.Frames.FRAMES_OVER_TEN;

public class Frame {
    private static final String BONUS_WITHOUT_FINAL_FRAME = "보너스 게임은 10번째 frame에서만 가능합니다";
    private static final String ROLLING_COUNT_OVER_THREE = "한 프레임에 세번 이상 공을 던질 수 없습니다.";
    private final FrameType frameType;
    private BasicScores scores;
    private BonusScores bonus;
    private Frame nextFrame;

    private Frame(FrameType frameType) {
        this.scores = new BasicScores();
        this.frameType = frameType;
    }

    public void setNextFrame(Frame nextFrame) {
        if (this.frameType == FrameType.FINAL) {
            throw new IllegalArgumentException(FRAMES_OVER_TEN);
        }
        this.nextFrame = nextFrame;
    }

    public Frame getNextFrame() {
        return nextFrame;
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
            throw new IllegalArgumentException(ROLLING_COUNT_OVER_THREE);
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
        return frameType == frame.frameType &&
                Objects.equals(scores, frame.scores) &&
                Objects.equals(bonus, frame.bonus) &&
                Objects.equals(nextFrame, frame.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameType, scores, bonus, nextFrame);
    }

    public int getScoreSum() {
        int sum = sumScore();
        GameType gameType = getGameType();
        if ((gameType == GameType.STRIKE || gameType == GameType.SPARE)) {
            if (frameType == FrameType.FINAL) {
                sum += bonus.getScores().get(0).getScore();
            } else {
                sum += nextFrame.getScores().get(0).getScore();
            }

            if (gameType == GameType.STRIKE) {
                if (frameType == FrameType.FINAL) {
                    if (bonus.getScores().size() == 2) {
                        sum += bonus.getScores().get(1).getScore();
                    }
                } else {
                    if (nextFrame.getGameType() == GameType.STRIKE) {
                        sum += nextFrame.nextFrame.sumScore();
                    } else {
                        sum += nextFrame.getScores().get(1).getScore();
                    }
                }
            }
        }
        return sum;
    }
}
