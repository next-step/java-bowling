package bowling.model.state.bonusState;

import bowling.model.Score;
import bowling.model.state.State;
import bowling.model.state.finishedState.FinishedState;

public abstract class BonusState extends State {
    private static final String BONUS_STATE_ERROR = "보너스 상태가 될 수 없습니다.";
    private FinishedState state;

    protected BonusState(FinishedState state){
        validState(state);
        this.state = state;
    }

    private void validState(FinishedState state) {
        if (!state.isMaxScore()) {
            throw new IllegalArgumentException(BONUS_STATE_ERROR);
        }
    }

    @Override
    public Score score(){
        return state.score();
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
