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

    public Frame getNextFrame() {
        return nextFrame;
    }

    public void setNextFrame(Frame nextFrame) {
        if (this.frameType == FrameType.FINAL) {
            throw new IllegalArgumentException(FRAMES_OVER_TEN);
        }
        this.nextFrame = nextFrame;
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
        GameType gameType = getGameType();
        if (gameType == GameType.SPARE) {
            int next = getNextScore(1);
            if (next < 0) {
                return -1;
            }
            return sumScore() + getNextScore(1);
        }
        if (gameType == GameType.STRIKE) {
            int next = getNextScore(2);
            if (next < 0) {
                return -1;
            }
            return sumScore() + getNextScore(2);
        }
        if (scores.size() < 2) {
            return -1;
        }
        return sumScore();
    }

    private int getNextScore(int size) {
        if (frameType == FrameType.FINAL) {
            if (size == 1 && bonus != null) {
                return bonus.getScores().get(0).getScore();
            }
            if (size == 2 && bonus != null) {
                if (bonus.getScores().get(0).getScore() != 10) {
                    return bonus.getScores().get(0).getScore();
                }
                if (bonus.getScores().size() == 2) {
                    return bonus.sumScore();
                }
            }
        } else {
            Frame next = this.nextFrame;
            if (size == 1 && next != null) {
                return next.getScores().get(0).getScore();
            }
            if (size == 2 && next != null) {
                if (next.getGameType() == GameType.STRIKE) {
                    if (next.frameType == FrameType.FINAL && next.bonus != null) {
                        return next.getScores().get(0).getScore() + next.bonus.getScores().get(0).getScore();
                    }
                    if (next.frameType == FrameType.NORMAL && next.nextFrame != null) {
                        return next.getScores().get(0).getScore() + next.nextFrame.getScores().get(0).getScore();
                    }
                } else if (next.getGameType() != GameType.STRIKE && next.getScores().size() > 1) {
                    return next.sumScore();
                }
            }
        }
        return -1;
    }
}
