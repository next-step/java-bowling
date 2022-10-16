package bowling.domain.state;

import bowling.domain.Scores;

public class Spare extends Finished {


    public Spare(Scores scores) {
        super(scores);
        if(!scores.isSpare()){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int bonusCount() {
        return 1;
    }

    @Override
    public boolean canGetBonus() {
        return true;
    }

    @Override
    public BowlingRecordState getBowlingState() {
        return BowlingRecordState.SPARE;
    }


}
