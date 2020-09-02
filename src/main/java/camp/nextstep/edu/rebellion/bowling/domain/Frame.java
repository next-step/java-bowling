package camp.nextstep.edu.rebellion.bowling.domain;

public class Frame {
    private static final int MAX_SCORES = 10;
    private static final int MAX_ATTEMPT_COUNTS = 2;
    private static final int FIRST_ATTEMPT = 0;
    private static final int STRIKE = 10;

    private int numberOfAttempts;
    private Score first;
    private Score last;
    private boolean started;

    private Frame(int initAttempt) {
        this.first = Score.of(0);
        this.last = Score.of(0);
        this.numberOfAttempts = initAttempt;
    }

    public static Frame ready() {
        return new Frame(FIRST_ATTEMPT);
    }

    public static Frame readyForBonus() {
        return new Frame(FIRST_ATTEMPT + 1);
    }

    public void markScore(int hits) {
        started = true;
        assignScore(hits);
        checkScoreRange();
        numberOfAttempts++;
    }

    private void assignScore(int hits) {
        if (FIRST_ATTEMPT == numberOfAttempts) {
            this.first = Score.of(hits);
            return;
        }

        this.last = Score.of(hits);
    }

    private void checkScoreRange() {
        if (MAX_SCORES < first.getHits() + last.getHits()) {
            throw new IllegalArgumentException("한 프레임은 최대 10 점을 넘을 수 없습니다");
        }
    }

    public boolean meetEnd() {
        return first.equals(STRIKE) ||
                (MAX_ATTEMPT_COUNTS <= numberOfAttempts);
    }

    public boolean isStrike() {
        return first.equals(STRIKE);
    }

    public boolean isStarted() {
        return started;
    }

    public int getFirstScore() {
        return first.getHits();
    }

    public int getLastScore() {
        return last.getHits();
    }

    public FrameStatus getStatus() {
        return FrameStatusResolver.resolve(this);
    }
}
