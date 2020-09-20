package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {

    private static final String FINAL_FRAME_RANGE = "FinalFrame의 인덱스는 10이여야합니다.";

    private LinkedList<State> states = new LinkedList<>();

    public FinalFrame(int frameIndex) {
        super(frameIndex);
        states.add(new Ready());
    }

    @Override
    protected void validationFrameIndex(int frameIndex) {
        if (frameIndex != MAX_FRAME_INDEX) {
            throw new IllegalArgumentException(FINAL_FRAME_RANGE);
        }
    }

    @Override
    public boolean rollingEnd() {
        return states.getLast().isFinish();
    }

    @Override
    public void bowl(Pin pin) {
        State currentState = states.getLast();

        if (currentState.isFinish()) {
            states.add(new Ready().bowl(pin));
            return;
        }
        states.removeLast();
        states.add(currentState.bowl(pin));
    }

    @Override
    public boolean isEndAllFrame() {
        State first = states.getFirst();
        if (!first.canBowlFinalFrame()) {
            return true;
        }
        if (isEqualSize(3)) {
            return true;
        }
        if (isEqualSize(2) && containsSpare()) {
            return true;
        }

        if (isEqualSize(2) && onlyFirstStrike() && isSecondFinish()) {
            return true;
        }
        return false;
    }

    private boolean isEqualSize(int size) {
        return states.size() == size;
    }

    private boolean isSecondFinish() {
        return states.get(1).isFinish();
    }

    private boolean onlyFirstStrike() {
        return getStateByIndex(0).isStrike() && !getStateByIndex(1).isStrike();
    }

    private State getStateByIndex(int index) {
        return states.get(index);
    }

    private boolean containsSpare() {
        return getStateByIndex(0).isSpare();
    }

    @Override
    public String index() {
        return String.valueOf(MAX_FRAME_INDEX);
    }

    @Override
    public String currentFrameStatus() {
        return states.stream()
                .map(State::record)
                .collect(Collectors.joining("|"));
    }

    public Score score() {
        Score score = getFirstScore();
        for (int i = 1; i < states.size(); i++) {
            State state = states.get(i);
            score = state.calculateAdditionalScore(score);
        }
        return score;
    }

    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = getStateByIndex(0).calculateAdditionalScore(beforeScore);

        if(score.canCalculateScore() || states.size() == 1){
            return score;
        }

        State secondState = getStateByIndex(1);
        return secondState.calculateAdditionalScore(score);

    }

    private Score getFirstScore() {
        return states.getFirst().getScore();
    }


}
