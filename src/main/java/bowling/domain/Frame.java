package bowling.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Frame {
    private static final int MAX_PIN_NUMBER = 10;

    protected Score score;
    protected Status status;
    private Frame nextFrame;

    public Frame(int hitNumberOfPin) {
        if (hitNumberOfPin == MAX_PIN_NUMBER) {
            status = Status.STRIKE;
        }
        score = new Score(hitNumberOfPin);
    }

    public Frame() {
    }

    public int firstScore() {
        return score.firstScore();
    }

    public void secondBall(int hitNumberOfPin) {
        score.secondBall(hitNumberOfPin);
        status = score.frameStatus();
    }

    public int secondScore() {
        return score.secondScore();
    }

    public Frame nextFrame(int hitNumberOfPin) {
        nextFrame = new Frame(hitNumberOfPin);
        return nextFrame;
    }

    public List<String> scores() {
        return score.scores()
                .stream()
                .map(pin -> Integer.toString(pin))
                .collect(Collectors.toList());
    }

    public boolean isSpare() {
        return Status.SPARE.equals(status);
    }

    public boolean isStrike() {
        return Status.STRIKE.equals(status);
    }

    public FinalFrame finalFrame(int hitNumberOfPin) {
        FinalFrame finalFrame = new FinalFrame(hitNumberOfPin);
        nextFrame = finalFrame;
        return finalFrame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(score, frame.score) && status == frame.status && Objects.equals(nextFrame, frame.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, status, nextFrame);
    }
}
