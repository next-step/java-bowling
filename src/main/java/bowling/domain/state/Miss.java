package bowling.domain.state;

import bowling.domain.Scores;

public class Miss extends Finished {

    public Miss(Scores scores) {
        super(scores);
        if(!scores.isMiss()){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int bonusCount() {
        return 0;
    }

    @Override
    public boolean canGetBonus() {
        return false;
    }

    @Override
    public BowlingRecordState getBowlingState() {
        return BowlingRecordState.MISS;
    }

}
