package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {

    public static final int MAXIMUM_PITCH = 2;
    public static final int MAXIMUM_SCORE_CONDITION = 10;
    public static final String MAXIMUM_FRAMESCORE = "한 프레임의 점수의 총 합은 10점입니다.";

    private List<Pitch> pitches;
    private int frameScore = 0;

    private NormalFrame(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public static NormalFrame init() {
        List<Pitch> pitches = new ArrayList<>(MAXIMUM_PITCH);
        return new NormalFrame(pitches);
    }

    @Override
    public Frame add(Pitch pitch) {
        addSecondPitch(pitch);
        addFirstPitch(pitch);
        return new NormalFrame(pitches);
    }

    private void addFirstPitch(Pitch pitch) {
        if(pitches.isEmpty()) {
            frameScore += pitch.getScore();
            pitches.add(pitch);
        }
    }

    private void addSecondPitch(Pitch pitch) {
        validateFrameScore(pitch);
        if(!pitches.isEmpty()) {
            frameScore += pitch.getScore();
            pitches.add(pitch);
        }
    }

    private void validateFrameScore(Pitch pitch) {
        if(frameScore + pitch.getScore() > MAXIMUM_SCORE_CONDITION) {
            throw new IllegalArgumentException(MAXIMUM_FRAMESCORE);
        }
    }

    @Override
    public int getPitchSize() {
        return pitches.size();
    }

    @Override
    public boolean isFinish() {
        return frameScore == MAXIMUM_SCORE_CONDITION || pitches.size() == 2;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public int getFirstScore() {
        return pitches.get(0).getScore();
    }

    @Override
    public int getSecondScore() {
        return pitches.get(1).getScore();
    }
}
