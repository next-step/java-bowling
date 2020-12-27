package step2.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {

    private static final int MAX_SIZE = 3;
    private static final int NORMAL_FRAME_SIZE = 2;

    private final List<Pitch> pitches;

    public FinalFrame(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public static Frame init() {
        List<Pitch> pitches = new ArrayList<>(MAX_SIZE);
        return new FinalFrame(pitches);
    }

    @Override
    public void bowl(Pitch pitch) {
        pitches.add(pitch);
    }

    @Override
    public boolean isFinish() {
        if (getSize() < NORMAL_FRAME_SIZE) {
            return false;
        }
        return isStrikeAndFinish() || isSpareAndFinish() || isMissAndFinish();
    }

    private boolean isStrikeAndFinish() {
        return isStrike() && isMaxSize();
    }

    private boolean isSpareAndFinish() {
        return isSpare() && isMaxSize();
    }

    private boolean isMissAndFinish() {
        return isMiss() && getSize() == NORMAL_FRAME_SIZE;
    }

    private boolean isStrike() {
        return pitches.get(0).getScore() == Pitch.MAX_SCORE;
    }

    private boolean isSpare() {
        int totalScore = pitches.get(0).getScore() + pitches.get(1).getScore();
        return totalScore == Pitch.MAX_SCORE;
    }

    private boolean isMiss() {
        int totalScore = pitches.get(0).getScore() + pitches.get(1).getScore();
        return totalScore < Pitch.MAX_SCORE;
    }

    private boolean isMaxSize() {
        return getSize() == MAX_SIZE;
    }

    @Override
    public int getSize() {
        return pitches.size();
    }
}
