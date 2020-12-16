package bowling.model.frame;

import bowling.model.Score;

import java.util.Optional;

public class FinalFrame extends Frame {
    private int countOfTry = 0;
    private Score score = Score.of(0, 2);

    public FinalFrame() {
        super(FrameNumber.from(FrameNumber.MAX_FRAME_NUMBER));
    }

    @Override
    public Frame bowling(int fallenPins) {
        countOfTry++;
        states.bowling(fallenPins);

        if(isBonusStart()){
            states.changeLastToBonusStart();
        }

        if(isBonusOpen()){
            states.changeLastToBonusOpen();
        }

        return this;
    }

    @Override
    public Optional<String> getScore() {
        Score calculatedScore = states.calculate(score);
        return calculatedScore.canCalculate() ? Optional.of(calculatedScore.toString()) : Optional.empty();
    }

    private boolean isBonusStart(){
        return states.isFinished() && countOfTry == 1;
    }

    private boolean isBonusOpen(){
        return states.isFinished() && states.isMaxScore() && countOfTry == 2;
    }
}
