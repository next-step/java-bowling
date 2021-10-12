package bowling.domain;

import bowling.exception.CannotCreateException;

public class FinalFrame extends Frame {

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
        return status;
    }

    @Override
    public void firstShot(PinScore pinScore) {
        this.firstScore = pinScore;
        checkStatus();
    }

    private void checkStatus() {
        status = FrameStatus.END;
        if(firstScore.value() == PinScore.MAX){
            status = FrameStatus.STRIKE;
        }
    }

    @Override
    public void secondShot(PinScore pinScore) throws CannotCreateException {
        this.secondScore = pinScore;
    }

    public void linkNextFrame(Frame next) {
        this.next = next;
    }

    public Frame next() {
        return next;
    }

    public void endGame() {
        this.status = FrameStatus.END;
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "status=" + status +
                ",score=" + score()
                + '}';
    }
}
