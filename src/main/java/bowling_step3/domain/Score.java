package bowling_step3.domain;

import java.util.List;

public abstract class Score {

    protected Pitch firstPitch;
    protected Pitch secondPitch;

    protected Score(Pitch firstPitch, Pitch secondPitch) {
        this.firstPitch = firstPitch;
        this.secondPitch = secondPitch;
    }

    abstract public void pitch(Pitch pitch);

    abstract public List<Pitch> getPitches();

    abstract public int sum();

    public abstract Pitch getFirstPitch();

    public abstract Pitch getSecondPitch();
}


