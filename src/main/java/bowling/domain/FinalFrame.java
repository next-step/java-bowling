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
        if(getPitchSize() == 1 && getFrameScore() != 10 && getFrameScore() + pitch.getScore() > 10) {
            throw new IllegalArgumentException(MAXIMUM_FRAMESCORE);
        }
    }

    @Override
    public int getPitchSize() {
        return pitches.size();
    }

    @Override
    public boolean isFinish() {
        return (getFrameScore() < MAXIMUM_SCORE_CONDITION && pitches.size() == NORMAL_PITCH) || pitches.size() == MAXIMUM_PITCH;
    }

    @Override
    public boolean isSpare() {
        return getFrameScore() == MAXIMUM_SCORE_CONDITION && pitches.size() == NORMAL_PITCH;
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