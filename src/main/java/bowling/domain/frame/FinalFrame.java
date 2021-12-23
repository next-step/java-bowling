package bowling.domain.frame;

import bowling.domain.pitch.Pitch;
import bowling.domain.score.Score;
import bowling.domain.state.progress.Start;
import bowling.domain.state.State;
import bowling.domain.state.end.End;
import bowling.strategy.PitchNumberStrategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static bowling.domain.state.end.End.OR;

public class FinalFrame implements Frame {
    private static final int FINAL_FRAME_NO = 9;
    public static final int STATES_FIRST_INDEX = 0;

    private int tryCount;
    private final FrameInfo frameInfo;
    private final List<State> states = new LinkedList<>();

    private FinalFrame() {
        this(FrameInfo.create(FINAL_FRAME_NO));
    }

    private FinalFrame(FrameInfo frameInfo) {
        this.tryCount = 0;
        this.frameInfo = frameInfo;
        states.add(new Start());
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
        if (!isEnd()) {
            addTryCount();
            Pitch pitch = frameInfo.createPitch(numberStrategy);
            changeState(lastState().run(pitch));
            addPitch(pitch);

            checkBonusPitch();
        }
    }

    private void addTryCount() {
        tryCount++;
    }

    private void changeState(State state) {
        states.remove(states.size() - 1);
        states.add(state);
    }

    private void checkBonusPitch() {
        if (lastState().isBonus() && !checkTryCount()) {
            states.add(new Start());
        }
    }

    private boolean checkTryCount() {
        return tryCount == 3;
    }

    private State lastState() {
        return states.get(states.size() - 1);
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
    public int no() {
        return frameInfo.no();
    }

    @Override
    public int currentFallDownPinsCount() {
        return frameInfo.currentFallDownPinsCount();
    }

    @Override
    public State state() {
        return lastState();
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
    public boolean isFinal() {
        return true;
    }

    @Override
    public boolean isEnd() {
        return lastState() instanceof End || checkTryCount();
    }

    @Override
    public String symbol() {
        return states.stream()
                .map(State::symbol)
                .collect(Collectors.joining(OR));
    }

    private static void validateFinalFrameNo(FrameInfo frameInfo) {
        if (!frameInfo.last()) {
            throw new IllegalArgumentException("마지막 프레임을 생성할 수 없습니다.");
        }
    }
}
