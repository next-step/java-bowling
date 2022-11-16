package bowling.step3.domain;

public abstract class Frame {

    final Pitches pitches;

    private Score score = null;

    public Frame() {
        this.pitches = new Pitches();
    }

    public abstract Boolean isEndedFrame();

    public abstract Boolean isFinalFrame();

    public void add(int count) {
        pitches.add(count);
    }

    public int first() {
        return this.pitches.first();
    }

    public int second() {
        return this.pitches.second();
    }

    public void addPoint(int BonusPoint, int totalPoint) {
        int score = BonusPoint + totalPoint + this.pitches.sum();
        this.score = new Score(score);
    }

    public Pitches pitches() {
        return pitches;
    }

    public Score score() {
        return score;
    }
}
