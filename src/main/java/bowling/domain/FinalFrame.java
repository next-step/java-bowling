package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {

    public static final int MAXIMUM_SCORE_CONDITION = 10;
    public static final int NORMAL_PITCH = 2;
    public static final int MAXIMUM_PITCH = 3;
    public static final String MAXIMUM_FRAMESCORE = "한 프레임의 점수의 총 합은 10점입니다.";

    private List<Pitch> pitches;

    private FinalFrame(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public static FinalFrame init() {
        List<Pitch> pitches = new ArrayList<>(MAXIMUM_PITCH);
        return new FinalFrame(pitches);
    }

    public Frame add(Pitch pitch) {
        addRemainPitch(pitch);
        addFirstPitch(pitch);
        return new FinalFrame(pitches);
    }

    private void addFirstPitch(Pitch pitch) {
        if(pitches.isEmpty()) {
            pitches.add(pitch);
        }
    }

    private void addRemainPitch(Pitch pitch) {
        validateFrameScore(pitch);
        if(!pitches.isEmpty()) {
            pitches.add(pitch);
        }
    }

    private void validateFrameScore(Pitch pitch) {
        if(isFirstPitch() && !isFrameScoreEqualTen() && isExceedMaxScore(pitch)) {
            throw new IllegalArgumentException(MAXIMUM_FRAMESCORE);
        }
    }

    private boolean isFirstPitch() {
        return getPitchSize() == 1;
    }

    private boolean isSecondPitch() {
        return getPitchSize() == NORMAL_PITCH;
    }

    private boolean isThirdPitch() {
        return getPitchSize() == MAXIMUM_PITCH;
    }

    private boolean isFrameScoreEqualTen() {
        return getFrameScore() == MAXIMUM_SCORE_CONDITION;
    }

    private boolean isExceedMaxScore(Pitch pitch) {
        return getFrameScore() + pitch.getScore() > MAXIMUM_SCORE_CONDITION;
    }

    private boolean isUnderMaxScore() {
        return getFrameScore() < MAXIMUM_SCORE_CONDITION;
    }

    private boolean isEqualMaxScore() {
        return getFrameScore() == MAXIMUM_SCORE_CONDITION;
    }

    @Override
    public int getPitchSize() {
        return pitches.size();
    }

    @Override
    public boolean isFinish() {
        return (isUnderMaxScore() && isSecondPitch()) || isThirdPitch();
    }

    @Override
    public boolean isSpare() {
        return isEqualMaxScore() && isSecondPitch();
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

    public int getThirdScore() {
        return pitches.get(2).getScore();
    }
}