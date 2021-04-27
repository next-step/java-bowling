package bowling.domain;

import java.util.List;

public abstract class Frame {

    private final int number;
    private final Pitches pitches;
    private Frame before;

    protected Frame(int number, Frame before) {
        this(number, new Pitches());
        this.before = before;
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

    public Frame before() {
        if (!hasBefore()) {
            throw new IllegalStateException("이전 프레임이 존재하지 않습니다.");
        }
        return this.before;
    }

    private boolean hasBefore() {
        return this.before != null;
    }

    abstract public Frame next();
    abstract public void pitch(Pitch pitch);
    abstract public boolean isFinished();
    abstract public List<String> getScoreBoards();
}
