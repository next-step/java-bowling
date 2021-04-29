package bowling.domain;

import java.util.List;

public abstract class Frame {

    private final int number;
    private final Pitches pitches;

    protected Frame(int number) {
        this(number, new Pitches());
    }

    protected Frame(int number, Pitches pitches) {
        this.number = number;
        this.pitches = pitches;
    }

    public int number() {
        return number;
    }

    public Pitches pitches() {
        return pitches;
    }

    abstract public Frame next();
    abstract public void pitch(Pitch pitch);
    abstract public boolean isFinished();
    abstract public List<String> getScoreBoards();
}
