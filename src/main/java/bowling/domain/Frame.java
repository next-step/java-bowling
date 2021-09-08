package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Frame {
    private static final int MAX_PIN_NUMBER = 10;

    private final Score score;
    private Status status;
    private Frame nextFrame;

    public Frame(int firstBall) {
        if (firstBall == MAX_PIN_NUMBER) {
            status = Status.STRIKE;
        }
        score = new Score(firstBall);
    }

    public int firstScore() {
        return score.firstScore();
    }

    public void secondBall(int secondBall) {
        if (secondBall + score.firstScore() == MAX_PIN_NUMBER) {
            status = Status.SPARE;
        }
        score.secondBall(secondBall);
    }

    public int secondScore() {
        return score.secondScore();
    }

    public Frame nextFrame(int firstBall) {
        nextFrame = new Frame(firstBall);
        return nextFrame;
    }

    public List<String> scores() {
        return score.scores()
                .stream()
                .map(score -> Integer.toString(score))
                .collect(Collectors.toList());
    }

    public boolean isStrike() {
        if (Status.STRIKE.equals(status)) {
            return true;
        }
        return false;
    }

    public boolean isSpare() {
        if (Status.SPARE.equals(status)) {
            return true;
        }
        return false;
    }
}
