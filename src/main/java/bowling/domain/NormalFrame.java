package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static bowling.domain.Scoring.SPARE;
import static bowling.domain.Scoring.STRIKE;
import static bowling.util.Lists.getAsOptional;

public class NormalFrame implements Frame {
    private final List<BallThrow> ballThrows = new ArrayList<>();
    private final int number;

    public NormalFrame() {
        this(1);
    }

    NormalFrame(int number) {
        this.number = number;
    }

    @Override
    public Frame throwBall(int fallingPins) {
        assignBallThrow(fallingPins);
        return getNextFrame();
    }

    @Override
    public Optional<Scoring> getScoring() {
        if (isIncomplete()) {
            return Optional.empty();
        }

        Integer first = getFallingPins(ballThrows, 0);
        Integer second = getFallingPins(ballThrows, 1);
        return Scoring.valueOf(first, second);
    }

    @Override
    public FrameStatus getFrameStatus() {
        Integer first = getFallingPins(ballThrows, 0);
        Integer second = getFallingPins(ballThrows, 1);
        Optional<Scoring> scoring = Scoring.valueOf(first, second);

        if (scoring.equals(STRIKE.asOptional())) {
            return FrameStatus.strike();
        }

        if (scoring.equals(SPARE.asOptional())) {
            return FrameStatus.spare(first);
        }

        return FrameStatus.miss(first, second);
    }

    private Integer getFallingPins(List<BallThrow> ballThrows, int index) {
        return getAsOptional(ballThrows, index)
                .map(BallThrow::getFallingPins)
                .orElse(null);
    }

    public int getNumber() {
        return number;
    }

    private void assignBallThrow(int fallingPins) {
        if (ballThrows.isEmpty()) {
            ballThrows.add(new BallThrow(fallingPins));
            return;
        }
        ballThrows.add(ballThrows.get(0).throwSecond(fallingPins));
    }

    private Frame getNextFrame() {
        return getScoring().map(__ -> {
            if (number < 9) {
                return new NormalFrame(number + 1);
            }
            return new LastFrame();
        }).orElse(this);
    }

    private boolean isIncomplete() {
        return ballThrows.isEmpty() || !ballThrows.get(0).isStrike() && ballThrows.size() < 2;
    }
}
