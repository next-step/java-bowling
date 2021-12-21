package bowling;

import bowling.domain.scores.HitScores;
import java.util.Objects;

public class Frame {

    private final HitScores hitScores;
    private final Score score; // 점수.

    public Frame(HitScores hitScores) {
        this(hitScores, hitScores.sumScore());
    }

    private Frame(HitScores hitScores, int score) {
        this(hitScores, Score.of(score));
    }

    private Frame(HitScores hitScores, Score score) {
        this.hitScores = hitScores;
        this.score = score;
    }

    public Frame updateScore(int hitCount) {
        HitScores tempScore = this.hitScores.add(hitCount);
        return new Frame(tempScore, tempScore.sumScore());
    }

    public boolean isClosedStroke() {
        return hitScores.isClosed();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frame frame = (Frame) o;
        return Objects.equals(hitScores, frame.hitScores) && Objects.equals(score,
            frame.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hitScores, score);
    }
}
