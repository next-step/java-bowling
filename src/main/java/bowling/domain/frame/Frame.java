package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.dto.Record;
import bowling.domain.state.Started;
import bowling.domain.state.State;

public abstract class Frame {
    State state;

    protected Frame() {
        this.state = new Started();
    }

    public abstract boolean isFinish();
    public abstract void bowl(Score score);

    public abstract Record getRecord();

    public int getRemainPins(){
        return state.getRemainPins();
    }



}
