package camp.nextstep.edu.rebellion.bowling.domain.score;

public abstract class FrameScore {
    private static final int STRIKE = 10;
    private static final int TRY_TWO = 2;
    private static final int TRY_ONE = 1;
    private static final int NO_TRY = 0;

    Score first;
    Score last;

    FrameScore() {
        this.first = Score.of(0);
        this.last = Score.of(0);
    }

    abstract void checkScoreRange(int addedHits);

    public void markFirst(int hits) {
        first = Score.of(hits);
    }

    public void markLast(int hits) {
        checkScoreRange(hits);
        last = Score.of(hits);
    }

    public boolean isStrike() {
        return first.equals(STRIKE);
    }

    public boolean isSpare() {
        int totalScore = first.getHits() + last.getHits();
        return (first.getHits() > 0 && last.getHits() > 0) &&
                STRIKE == totalScore;
    }

    public int getFirstScore() {
        return first.getHits();
    }

    public int getLastScore() {
        return last.getHits();
    }

    public int getHitsScore() {
        return first.getHits() + last.getHits();
    }

    public int getTryAttempt() {
        return isStrike() ? TRY_TWO :
                isSpare() ? TRY_ONE : NO_TRY;
    }
}
