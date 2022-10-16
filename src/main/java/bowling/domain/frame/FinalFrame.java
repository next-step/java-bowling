package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.dto.Record;

import java.util.Optional;

public class FinalFrame extends Frame {

    private Score bonus;

    @Override
    public boolean isFinish() {
        if(state.isFinish() && state.canGetBonus()){
            return bonus != null;
        }
        return state.isFinish() && !state.canGetBonus();
    }

    @Override
    public void bowl(Score score) {
        if (!state.isFinish()) {
            this.state = this.state.bowl(score);
            return;
        }
        if (state.canGetBonus()) {
            this.bonus = score;
            return;
        }
        throw new IllegalStateException();
    }

    @Override
    public Record getRecord() {
        Integer bonusValue = Optional.ofNullable(this.bonus).map(Score::getValue).orElse(null);
        return new Record(KindOfFrame.FINAL, state.getRecord(), bonusValue, state.getBowlingState());
    }

}
