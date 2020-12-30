package bowling.domain;

import bowling.domain.state.*;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {

    public static final int MAXIMUM_PITCH = 2;
    public static final int MAXIMUM_SCORE_CONDITION = 10;
    public static final String MAXIMUM_FRAMESCORE = "한 프레임의 점수의 총 합은 10점입니다.";

    private List<Pitch> pitches;
    private State state = new Ready();

    private NormalFrame(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public static NormalFrame init() {
        List<Pitch> pitches = new ArrayList<>(MAXIMUM_PITCH);
        return new NormalFrame(pitches);
    }

    @Override
    public void playPitch(Pitch pitch) {
        addSecondPitch(pitch);
        addFirstPitch(pitch);
    }

    private void addFirstPitch(Pitch pitch) {
        if(pitches.isEmpty()) {
            pitches.add(pitch);
        }
    }

    private void addSecondPitch(Pitch pitch) {
        validateFrameScore(pitch);
        if(!pitches.isEmpty()) {
            pitches.add(pitch);
        }
    }

    private void validateFrameScore(Pitch pitch) {
        if(getFrameScore() + pitch.getScore() > MAXIMUM_SCORE_CONDITION) {
            throw new IllegalArgumentException(MAXIMUM_FRAMESCORE);
        }
    }

    @Override
    public State bowl(Pitch pitch) {
        state = state.bowl(pitch);
        return state;
    }

    @Override
    public int getPitchSize() {
        return pitches.size();
    }

    @Override
    public boolean isFinish() {
        return isScoreEqualTen() || isTwicePitch();
    }

    private boolean isScoreEqualTen() {
        return getFrameScore() == MAXIMUM_SCORE_CONDITION;
    }

    private boolean isTwicePitch() {
        return pitches.size() == MAXIMUM_PITCH;
    }

    @Override
    public boolean isSpare() {
        return isScoreEqualTen() && isTwicePitch();
    }

    @Override
    public int getFrameScore() {
        return pitches.stream()
                .mapToInt(Pitch::getScore)
                .sum();
    }

    @Override
    public int getFirstScore() {
        return pitches.get(0).getScore();
    }

    @Override
    public int getSecondScore() {
        return pitches.get(1).getScore();
    }

    @Override
    public State getState() {
        return state;
    }
}
