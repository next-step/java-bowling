package bowling.model.frame;

import java.util.LinkedList;
import java.util.stream.Collectors;

import bowling.model.CannotBowlException;
import bowling.model.CannotCalculateException;
import bowling.model.GameOverException;
import bowling.model.Score;
import bowling.model.state.State;
import bowling.model.state.StateFactory;

public class FinalFrame implements Frame {
    private LinkedList<State> states = new LinkedList<>();

    public FinalFrame() {
        states.add(StateFactory.ready());
    }

    @Override
    public Frame bowl(int countOfPin) throws CannotBowlException {
        if (isFinish()) {
            throw new GameOverException();
        }

        State currentState = states.getLast();

        if (currentState.isFinish()) {
            states.add(StateFactory.firstBowl(countOfPin));
            return this;
        }

        states.removeLast();
        states.add(currentState.bowl(countOfPin));
        return this;
    }

    public boolean isFinish() {
        try {
            Score score = getScore();
            return score.canCalculateScore();
        } catch (CannotCalculateException e) {
            return false;
        }
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = beforeScore;
        for (State state : states) {
            score = state.calculateAdditionalScore(score);
        }
        return score;
    }

    public FrameResult getFrameResult() {
        if (!isFinish()) {
            return new FrameResult(getDesc(), UN_SCORE_STATE);
        }

        try {
            return new FrameResult(getDesc(), getScore().getScore());
        } catch (CannotCalculateException e) {
            return new FrameResult(getDesc(), UN_SCORE_STATE);
        }
    }

    @Override
    public boolean isEndGame() {
        try {
            return isFinish();
        } catch (CannotCalculateException e) {
            return false;
        }
    }

    @Override
    public void addFrameResult(Board board) {
        board.add(getFrameResult());
    }

    @Override
    public Board createBoard() {
        throw new UnsupportedOperationException();
    }

    public Score getScore() {
        Score score = getFirstScore();
        for (int i = 1; i < states.size(); i++) {
            State state = states.get(i);
            score = state.calculateAdditionalScore(score);
        }

        return score;
    }

    private Score getFirstScore() {
        return states.getFirst().getScore();
    }

    @Override
    public int getNo() {
        return FINAL_FRAME_NO;
    }

    String getDesc() {
        return states.stream()
                        .map(State::getDesc)
                        .collect(Collectors.joining("|"));
    }
}
