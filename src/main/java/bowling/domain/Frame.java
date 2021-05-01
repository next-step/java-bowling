package bowling.domain;

public abstract class Frame {

    protected static final int NON_BONUS = 0;

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

    public boolean isFinalFrame() {
        return this instanceof FinalFrame;
    }

    public boolean isSpare() {
        return pitches.isSpare();
    }

    abstract public Frame next();
    abstract public void pitch(Pitch pitch);
    abstract public boolean isFinished();
    abstract public int score();
    abstract public int bonusScore(Pitches beforePitches);
    abstract public int doubleBonusScore();
}
