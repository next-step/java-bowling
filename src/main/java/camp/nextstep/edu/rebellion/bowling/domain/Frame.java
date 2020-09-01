package camp.nextstep.edu.rebellion.bowling.domain;

public class Frame {
    private static final int MAX_ATTEMPT_COUNT = 2;
    private static final int FIRST_ATTEMPT = 0;
    private static final int STRIKE = 10;

    private int numberOfAttempts;
    private Score first;
    private Score last;

    private Frame() {
        this.first = Score.of(0);
        this.last = Score.of(0);
    }

    public static Frame ready() {
        return new Frame();
    }

    public void markScore(int hits) {
        if (FIRST_ATTEMPT == numberOfAttempts) {
            this.first = Score.of(hits);
        }

        this.last = Score.of(hits);
        numberOfAttempts++;
    }

    public boolean meetEnd() {
        return first.equals(STRIKE) ||
                (MAX_ATTEMPT_COUNT <= numberOfAttempts);
    }
}
