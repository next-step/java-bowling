package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.Score;

import java.util.Optional;

public class NormalFrame extends Frame {
    private Score score = Score.init();
    private Frame next;

    private NormalFrame(FrameNumber frameNumber) {
        super(frameNumber);
    }

    public static NormalFrame createFirstFrame() {
        return new NormalFrame(FrameNumber.from(1));
    }

    private Frame next() {
        next = frameNumber.beforeLast() ? new FinalFrame() : new NormalFrame(frameNumber.next());
        return next;
    }

    @Override
    public Frame bowling(Pins fallenPins) {
        states.bowling(fallenPins);

        updateScore();

        return states.isFinished() ? next() : this;
    }

    private void updateScore(){
        if(states.isFinished()){
            score = states.last().score();
        }
    }

    @Override
    public Optional<Integer> getScore() {
        if (score.canCalculate()) {
            return Optional.of(score.getScore());
        }

        Score result = canNextCalculate() ? next.addScore(score) : score;

        return result.canCalculate() ? Optional.of(result.getScore()) : Optional.empty();
    }

    protected Score addScore(Score score) {
        Score result = states.calculate(score);

        if (result.canCalculate()) {
            return result;
        }

        return canNextCalculate() ? next.addScore(result) : result;
    }

    private boolean canNextCalculate() {
        return next != null && !next.isNewFrame();
    }

    @Override
    public String toString() {
        return states.toString();
    }

}
