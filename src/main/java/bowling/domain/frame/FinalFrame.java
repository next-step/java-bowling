package bowling.domain.frame;

import bowling.domain.Pitch;
import bowling.domain.Score;
import bowling.domain.state.Start;
import bowling.domain.state.State;

import java.util.LinkedList;
import java.util.List;

public class FinalFrame extends TemplateFrame {
    private static final int FINAL_FRAME_NO = 9;
    public static final int STATES_SECOND_INDEX = 1;
    public static final int STATES_FIRST_INDEX = 0;
    public static final int STATE_THIRD_INDEX = 3;

    private List<State> states = new LinkedList<>();

    private FinalFrame() {
        super(FrameInfo.create(FINAL_FRAME_NO), new Start());
    }

    private FinalFrame(FrameInfo frameInfo) {
        super(frameInfo, new Start());
    }

    public static Frame create() {
        return new FinalFrame();
    }

    public static Frame create(FrameInfo frameInfo) {
        validateFinalFrameNo(frameInfo);
        return new FinalFrame(frameInfo);
    }

    @Override
    public Frame next() {
        throw new IllegalArgumentException("마지막 프레임입니다.");
    }

    @Override
    public void addPitch(Pitch pitch) {
        frameInfo.addPitch(pitch);
    }

    @Override
    public boolean isThirdPitch() {
        return frameInfo.isThirdPitch();
    }

    @Override
    public Score score() {
        if (states.size() == STATES_SECOND_INDEX) {
            return null;
        }
        Score score = states.get(STATES_SECOND_INDEX).score();
        for (int index = STATE_THIRD_INDEX; index < states.size(); index++) {
            Score nextScore = states.get(index).score();
            score = score.add(nextScore);
        }
        return score;
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        Score score = beforeScore;
        if (score.calculated()) {
            return score;
        }
        int index = STATES_SECOND_INDEX;
        for (int count = STATES_FIRST_INDEX; count < score.bonusCount(); count++) {
            State state = states.get(index++);
            score = state.calculateBonusScore(score);
        }
        return score;
    }

    @Override
    public State state() {
        return state;
    }

    @Override
    public boolean isFinal() {
        return true;
    }

    @Override
    public void addState(State state) {
        states.add(state);
    }

    @Override
    public State currentState() {
        return states.get(states.size() - STATES_SECOND_INDEX);
    }

    private static void validateFinalFrameNo(FrameInfo frameInfo) {
        if (!frameInfo.last()) {
            throw new IllegalArgumentException("마지막 프레임을 생성할 수 없습니다.");
        }
    }
}
