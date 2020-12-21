package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {
    public static final int MAXIMUM_PITCH = 3;
    public static final String MAXIMUM_FRAMESCORE = "한 프레임의 점수의 총 합은 10점입니다.";

    private List<Pitch> pitches;
    private int frameScore = 0;

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
            frameScore += pitch.getScore();
            pitches.add(pitch);
        }
    }

    private void addRemainPitch(Pitch pitch) {
        validateFrameScore(pitch);
        if(!pitches.isEmpty()) {
            frameScore += pitch.getScore();
            pitches.add(pitch);
        }
    }

    private void validateFrameScore(Pitch pitch) {
        if(frameScore != 10 && frameScore + pitch.getScore() > 10) {
            throw new IllegalArgumentException(MAXIMUM_FRAMESCORE);
        }
    }

    @Override
    public int getPitchSize() {
        return pitches.size();
    }

    @Override
    public boolean isFinish() {
        return (frameScore < 10 && pitches.size() == 2) || pitches.size() == 3;
    }
}