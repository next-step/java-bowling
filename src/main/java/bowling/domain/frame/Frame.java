package bowling.domain.frame;

import bowling.domain.score.InvalidMaxScoresException;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import bowling.domain.state.FrameReady;
import bowling.domain.state.State;

import java.util.List;
import java.util.stream.Collectors;

public class Frame {
    public static int MAX_TRY_COUNT = 2;

    protected Scores scores;
    protected State state;

    protected Frame(Scores scores, State state) {
        this.scores = scores;
        this.state = state;
    }

    public static Frame empty() {
        return new Frame(Scores.empty(), new FrameReady(MAX_TRY_COUNT));
    }

    public static Frame of(List<Score> scores, State state) {
        return new Frame(Scores.of(scores), state);
    }

    public void record(int pins) {
        validateMaxScore(pins);
        validateFinished();
        state = state.record(pins);
        scores = scores.add(state.getScore());
    }

    private void validateFinished() {
        if (isFinished()) {
            throw new InvalidFrameRecordActionException();
        }
    }

    private void validateMaxScore(int pins) {
        if (pins + scores.sum() > getMaxScore()) {
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
        return scores.getScores();
    }

    public Integer calculateScore(Integer previousFrameScore, List<Frame> nextFrames) {
        if (state.isFinished()) {
            return calculate(previousFrameScore, nextFrames);
        }
        return null;
    }

    protected Integer calculate(Integer previousFrameScore, List<Frame> nextFrames) {
        return scores.calculate(previousFrameScore, getNextScores(nextFrames));
    }

    private List<Score> getNextScores(List<Frame> nextFrames) {
        return nextFrames.stream()
                .flatMap(frame -> frame.getScores().stream())
                .collect(Collectors.toList());
    }
}
