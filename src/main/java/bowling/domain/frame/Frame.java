package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import java.util.Objects;

public abstract class Frame {

    final int round;
    Score score;

    Frame(int round, Score score) {
        this.round = round;
        this.score = score;
    }

    public static Frame lastFrame(Frame frame) {
        Frame resultFrame = frame;
        while (Objects.nonNull(resultFrame.nextFrame())) {
            resultFrame = resultFrame.nextFrame();
        }
        return resultFrame;
    }

    public abstract Frame createNextFrame();

    public abstract Frame nextFrame();

    public void updateScoreByPin(Pin pin) {
        score = score.nextPin(pin);
    }

    public Score score() {
        return score;
    }

    public boolean isNextScore() {
        return score.isNext();
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
        return round == frame.round && Objects.equals(score, frame.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(round, score);
    }

}
