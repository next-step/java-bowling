package bowling.domain;

public class Bowling {
    private static final int MAX_PIN = 10;
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";
    private Score score;
    private User user;

    public Bowling(User user) {
        this.score = new Score();
        this.user = user;
    }

    public boolean hasRemainPin(Frame frame) {
        return getRemainPin(frame) != 0;
    }

    public String userName() {
        return user.name();
    }

    public int pitching(Frame frame, BowlingStrategy bowlingStrategy) {
        int frameScore;
        if (frame.isInitFrame()) {
            frameScore = bowlingStrategy.pitchingBall(MAX_PIN);
            score.recordScore(frame, frameScore);
            return frameScore;
        }

        frameScore = bowlingStrategy.pitchingBall(getRemainPin(frame.beforeFrame()));
        score.recordScore(frame, frameScore);
        return frameScore;
    }

    public String mark(Frame frame) {
        if (isStrike(frame)) {
            return STRIKE;
        }

        if(isSpare(frame) && isStrike(frame.beforeFrame())) {
            return " ";
        }

        if (isSpare(frame)) {
            return SPARE;
        }

        if (isGutter(frame)) {
            return GUTTER;
        }

        return String.valueOf(score.frameScore(frame));
    }

    public boolean isStrike(Frame frame) {
        return frame.isInitFrame() && !hasRemainPin(frame);
    }

    private boolean isSpare(Frame frame) {
        return !frame.isInitFrame() && !hasRemainPin(frame);
    }

    private boolean isGutter(Frame frame) {
        if (frame.isInitFrame()) {
            return getRemainPin(frame) == MAX_PIN;
        }

        return getRemainPin(frame.beforeFrame()) == getRemainPin(frame);
    }

    private int getRemainPin(Frame frame) {
        if (frame.isInitFrame()) {
            return MAX_PIN - score.frameScore(frame);
        }
        return MAX_PIN - score.frameScore(frame.beforeFrame()) - score.frameScore(frame);
    }
}
