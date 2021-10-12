package bowling.domain;

import bowling.exception.CannotCreateException;

public class NormalFrame extends Frame {

    private FrameStatus status = FrameStatus.READY;
    private PinScore firstScore;
    private PinScore secondScore;
    private Frame next;

    @Override
    public int score() {
        if (secondScore == null) {
            return firstScore.value();
        }
        return firstScore.value() + secondScore.value();
    }

    @Override
    public FrameStatus status() {
        return this.status;
    }

    @Override
    public void firstShot(PinScore pinScore) throws CannotCreateException {
        firstScore = pinScore;
        firstShotStatus();
    }

    @Override
    public void secondShot(PinScore pinScore) throws CannotCreateException {
        secondScore = pinScore;
        secondShotStatus();
    }

    public void linkNextFrame(Frame next) {
        this.next = next;
    }

    @Override
    public Frame next() {
        return next;
    }

    private void firstShotStatus() throws CannotCreateException {
        if (firstScore.value() == PinScore.MAX) {
            status = FrameStatus.STRIKE;
            secondScore = new PinScore(0);
        }
    }

    private void secondShotStatus() throws CannotCreateException {
        int addScore = firstScore.add(secondScore);
        validation(addScore);
        if (addScore == PinScore.MIN) {
            status = FrameStatus.GUTTER;
            return;
        }
        if (addScore == PinScore.MAX) {
            status = FrameStatus.SPARE;
            return;
        }
        status = FrameStatus.MISS;
    }

    private void validation(int score) throws CannotCreateException {
        if (score > PinScore.MAX) {
            throw new CannotCreateException(CannotCreateException.SECOND_SCORE_ERROR_MSG);
        }
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "status=" + status +
                ",score=" + score()
                + '}';
    }
}
