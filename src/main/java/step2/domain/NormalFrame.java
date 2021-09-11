package step2.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import step2.exceptions.BowlingPintNumberError;

public class NormalFrame implements Frame {
    private static final int INITIAL_NUM_OF_BOWLING_PINS = 10;
    private static final int MAX_TURN = 2;

    private List<Integer> frame;

    public NormalFrame() {
        this.frame = new ArrayList<>();
    }

    @Override
    public void knockDown(int numOfPin) {
        frame.add(numOfPin);
        isPossible();
    }

    @Override
    public void isPossible() {
        if (score() > 10) {
            throw new BowlingPintNumberError();
        }
    }

    @Override
    public int score() {
        return frame.stream()
                .reduce(0, Integer::sum);
    }

    @Override
    public boolean isFinish() {
        return score() == INITIAL_NUM_OF_BOWLING_PINS || frame.size() == MAX_TURN;
    }

    @Override
    public List<Integer> scoreInfo() {
        return frame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(frame, that.frame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frame);
    }
}
