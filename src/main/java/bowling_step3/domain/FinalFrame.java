package bowling_step3.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {
    private static final int MAXIMUM_PITCH = 3;

    private List<Pitch> pitches;

    private FinalFrame(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public static FinalFrame init() {
        List<Pitch> pitches = new ArrayList<>(MAXIMUM_PITCH);
        return new FinalFrame(pitches);
    }

    private int searchKnockDown(int index) {
        return pitches.get(index).getKnockDown();
    }

    @Override
    public boolean isFirst() {
        return pitches.size() == 0;
    }

    @Override
    public void add(Pitch pitch) {
        pitches.add(pitch);
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

    public int getBonusOfKnockDown() {
        return searchKnockDown(2);
    }
}
