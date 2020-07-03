package bowling.step3.domain.score;

import java.util.Objects;

public class Score {
    private static final int READY_POINT = -1;
    private static final int READY_REMAIN_POINT = 0;
    private static final int STRIKE_POINT = 10;
    private static final int STRIKE_REMAIN_POINT = 2;
    private static final int SPARE_POINT = 10;
    private static final int SPARE_REMAIN_POINT = 1;
    private static final int DEFAULT_REMAIN_POINT = 0;
    private static final int DECREASE = 1;

    private int score;
    private int remainCount;

    public Score(int score, int remainCount) {
        this.score = score;
        this.remainCount = remainCount;
    }

    public static Score readyScore() {
        return new Score(READY_POINT, READY_REMAIN_POINT);
    }

    public static Score strike() {
        return new Score(STRIKE_POINT, STRIKE_REMAIN_POINT);
    }

    public static Score spare() {
        return new Score(SPARE_POINT, SPARE_REMAIN_POINT);
    }

    public int getScore() {
        return score;
    }

    public boolean isFinishedScore(){
        return remainCount == DEFAULT_REMAIN_POINT;
    }

    public Score addScore(Score addScore) {
        return new Score(score + addScore.getScore(), remainCount - DECREASE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score &&
                remainCount == score1.remainCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, remainCount);
    }
}
