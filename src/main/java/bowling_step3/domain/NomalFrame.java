package bowling_step3.domain;

import bowling_step3.exception.BowlingMaxCountException;

import java.util.ArrayList;
import java.util.List;

public class NomalFrame implements Frame {

    public static final int BOWLING_MAX_NUMBER = 10;

    private static final int MAXIMUM_PITCH = 2;

    private List<Pitch> pitches;

    public NomalFrame(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public static NomalFrame init() {
        List<Pitch> pitches = new ArrayList<>(MAXIMUM_PITCH);
        return new NomalFrame(pitches);
    }

    @Override
    public boolean isFirst() {
        return pitches.size() == 0;
    }

    private int searchKnockDown(int index) {
        return pitches.get(index).getKnockDown();
    }

    private void validate(Pitch pitch) {
        int sum = searchKnockDown(0) + pitch.getKnockDown();
        if (sum > BOWLING_MAX_NUMBER) {
            throw new BowlingMaxCountException();
        }
    }

    @Override
    public void add(Pitch pitch) {
        if (!isFirst()) {
            validate(pitch);
            pitches.add(pitch);
        }

        if (isFirst()) {
            pitches.add(pitch);
        }
    }

    @Override
    public int size() {
        return pitches.size();
    }

    @Override
    public int getFirstOfKnockDown() {
        return searchKnockDown(0);
    }

    @Override
    public int getSecondOfKnockDown() {
        return searchKnockDown(1);
    }
}
