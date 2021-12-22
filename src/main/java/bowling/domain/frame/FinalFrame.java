package bowling.domain.frame;

import bowling.domain.pitch.Pitch;
import bowling.domain.score.Score;
import bowling.domain.state.Start;
import bowling.domain.state.State;
import bowling.strategy.PitchNumberStrategy;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static bowling.domain.state.end.End.*;

public class FinalFrame extends TemplateFrame {
    private static final int FINAL_FRAME_NO = 9;
    public static final int STATES_SECOND_INDEX = 1;
    public static final int STATES_FIRST_INDEX = 0;

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
    public void run(PitchNumberStrategy numberStrategy) {
        if (state.progressing()) {
            Pitch pitch = frameInfo.createPitch(numberStrategy);
            state = state.run(pitch, this);
        }
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
          return Score.from(frameInfo.sumOfFallDownPins());
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        Score score = beforeScore;
        int size = states.size();
        for (int index = STATES_FIRST_INDEX; index < size && !score.calculated(); index++) {
            State state = states.get(index);
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

    @Override
    public String symbol() {
        return frameInfo.fallDownPinsAll().stream()
                .map(value -> Integer.toString(value))
                .collect(Collectors.joining(OR));
    }

    private static void validateFinalFrameNo(FrameInfo frameInfo) {
        if (!frameInfo.last()) {
            throw new IllegalArgumentException("마지막 프레임을 생성할 수 없습니다.");
        }
    }
}
