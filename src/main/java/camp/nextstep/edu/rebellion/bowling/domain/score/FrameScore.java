package camp.nextstep.edu.rebellion.bowling.domain.score;

public abstract class FrameScore {
    private static final int STRIKE = 10;
    private static final int TRY_TWO = 2;
    private static final int TRY_ONE = 1;
    private static final int NO_TRY = 0;

    protected Score first;
    protected Score last;

    protected FrameScore() {
        this.first = Score.of(0);
        this.last = Score.of(0);
    }

    public void markFirst(int hits) {
        this.first = Score.of(hits);
    }

    public void markLast(int hits) {
        checkScoreRange(hits);
        this.last = Score.of(hits);
    }

    public boolean isStrike() {
        return this.first.equals(STRIKE);
    }

    public boolean isSpare() {
        int totalScore = this.first.getHits() + this.last.getHits();
        return (this.first.getHits() > 0 && this.last.getHits() > 0) &&
                STRIKE == totalScore;
    }

    public int getFirstScore() {
        return this.first.getHits();
    }

    public int getLastScore() {
        return this.last.getHits();
    }

    public int getHitsScore() {
        return this.first.getHits() + this.last.getHits();
    }

    public int getTryAttempt() {
        return isStrike() ? TRY_TWO :
                isSpare() ? TRY_ONE : NO_TRY;
    }

    protected abstract void checkScoreRange(int addedHits);
}
