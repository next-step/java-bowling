package bowling.domain;

import bowling.engine.Frame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NormalFrame implements Frame {
    public static final int INITIAL_INDEX = 0;
    public static final int FINAL_SIZE = 2;

    private final List<Pitching> pitchings;

    private NormalFrame(List<Pitching> pitchings) {
        this.pitchings = pitchings;
    }

    public static NormalFrame first(int firstPitchingNumber) {
        return new NormalFrame(new ArrayList<>(Arrays.asList(new Pitching(firstPitchingNumber))));
    }

    public NormalFrame second(int secondPitchingNumber) {
        this.pitchings.add(new Pitching(secondPitchingNumber));
        return new NormalFrame(this.pitchings);
    }

    @Override
    public boolean isEnd() {
        return this.pitchings.size() == FINAL_SIZE;
    }

    public boolean isStrike() {
        return this.pitchings.get(INITIAL_INDEX).isStrike();
    }

    public List<Pitching> getPitchings() {
        return pitchings;
    }
}
