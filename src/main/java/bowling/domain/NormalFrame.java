package bowling.domain;

import bowling.exception.CannotCreateException;

public class NormalFrame extends Frame{

    private FrameStatus status = FrameStatus.READY;
    private int score;

    private Frame next;

    @Override
    public int score() {
        return 0;
    }

    @Override
    public FrameStatus status() {
        return this.status;
    }

    @Override
    public void firstShot(PinScore pinScore){
        this.score = pinScore.value();
        firstShotStatus();
    }

    @Override
    public void secondShot(PinScore pinScore) throws CannotCreateException {
        this.score += pinScore.value();
        secondShotStatus();
    }

    private void firstShotStatus(){
        if(score == PinScore.MAX){
            status = FrameStatus.STRIKE;
        }
    }

    private void secondShotStatus() throws CannotCreateException {
        validation(score);
        if(score == PinScore.MIN){
            status = FrameStatus.GUTTER;
            return;
        }
        if(score == PinScore.MAX){
            status = FrameStatus.SPARE;
            return;
        }
        status = FrameStatus.MISS;
    }

    private void validation(int score) throws CannotCreateException {
        if(score > PinScore.MAX){
            throw new CannotCreateException(CannotCreateException.SECOND_SCORE_ERROR_MSG);
        }
    }

    public void linkNextFrame(Frame next){
        this.next = next;
    }

    public Frame next(){
        return next;
    }

}
