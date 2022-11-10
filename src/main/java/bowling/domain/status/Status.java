package bowling.domain.status;

import bowling.domain.Pin;
import bowling.domain.Score;

public abstract class Status {

    Pin first;
    Pin second;

    public abstract Status bowl(Pin pin);
    public abstract boolean isFinished();
    public abstract Score getScore();
    public int getCountOfFirst() {
        return first.getCountOfPin();
    }
    public int getCountOfSecond() {
        return second.getCountOfPin();
    }


}
