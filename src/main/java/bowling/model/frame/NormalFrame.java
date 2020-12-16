package bowling.model.frame;

import bowling.model.Score;

import java.util.Optional;

public class NormalFrame extends Frame {
    private Score score = Score.of(0 ,2);
    private Frame next;

    private NormalFrame(FrameNumber frameNumber) {
        super(frameNumber);
    }

    public static NormalFrame createFirstFrame() {
        return new NormalFrame(FrameNumber.from(1));
    }

    private Frame next() {
        next = new NormalFrame(frameNumber.next());
        return next;
    }

    @Override
    public Frame bowling(int fallenPins) {
        states.bowling(fallenPins);

        if(states.isFinished()){
            score = states.last().score();
        }

        if (frameNumber.beforeLast() && states.isFinished()) {
            next = new FinalFrame();
            return next;
        }

        return states.isFinished() ? next() : this;
    }

    @Override
    public Optional<String> getScore() {
        if(score.canCalculate()){
            return Optional.of(score.toString());
        }

        if(next != null){
            score = next.addScore(score);
        }

        return score.canCalculate() ? Optional.of(score.toString()) : Optional.empty();
    }

    @Override
    public String toString() {
        return states.toString();
    }
}
