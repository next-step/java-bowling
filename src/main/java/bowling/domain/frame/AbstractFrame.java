package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import java.util.Objects;

public abstract class AbstractFrame implements Frame {

    public static int FIRST_ROUND = 1;
    public static int FINAL_ROUND = 10;

    private final int round;
    private Score score;

    AbstractFrame(int round, Score score) {
        this.round = round;
        this.score = score;
    }

    @Override
    public Frame lastFrame() {
        Frame resultFrame = this;
        while (resultFrame.round() < FINAL_ROUND && Objects.nonNull(resultFrame.nextFrame())) {
            resultFrame = resultFrame.nextFrame();
        }
        return resultFrame;
    }

    @Override
    public Score score() {
        return score;
    }

    @Override
    public boolean isScoreNextStorable() {
        return score.isNextStorable();
    }

    @Override
    public void updateScorePin(Pin pin) {
        score = score.saveNextPin(pin);
    }

    @Override
    public int round() {
        return round;
    }

    public abstract Frame createNextFrame();

    public abstract Frame nextFrame();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractFrame that = (AbstractFrame) o;
        return round == that.round && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(round, score);
    }

}
