package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.score.InvalidMaxScoresException;
import bowling.domain.score.Score;
import bowling.domain.state.FrameReady;
import bowling.domain.state.State;

import java.util.List;
import java.util.stream.Collectors;

public class Frame {
    public static int MAX_TRY_COUNT = 2;

    protected State state;

    protected Frame(State state) {
        this.state = state;
    }

    public static Frame empty() {
        return new Frame(new FrameReady(MAX_TRY_COUNT));
    }

    public static Frame of(State state) {
        return new Frame(state);
    }

    public boolean record(Pin pins) {
        validateMaxScore(pins.getFellPins());
        validateFinished();
        state = state.record(pins);
        return isFinished();
    }

    private void validateFinished() {
        if (isFinished()) {
            throw new InvalidFrameRecordActionException();
        }
    }

    private void validateMaxScore(int pins) {
        if (pins + state.sum() > getMaxScore()) {
            throw new InvalidMaxScoresException();
        }
    }

    protected int getMaxScore() {
        return Score.MAX_SCORE;
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public List<Score> getScores() {
        return state.getScores();
    }

    public Integer calculateScore(Integer previousFrameScore, List<Frame> nextFrames) {
        return state.calculate(previousFrameScore, getNextScores(nextFrames));
    }

    private List<Score> getNextScores(List<Frame> nextFrames) {
        return nextFrames.stream()
                .flatMap(frame -> frame.getScores().stream())
                .collect(Collectors.toList());
    }
}
