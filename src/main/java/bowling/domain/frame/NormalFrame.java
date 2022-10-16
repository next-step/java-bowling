package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.dto.Record;

public class NormalFrame extends Frame {

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public void bowl(Score score) {
        this.state = state.bowl(score);
    }

    @Override
    public Record getRecord(){
        return new Record(KindOfFrame.NORMAL, state.getRecord(), null, state.getBowlingState());
    }

}
