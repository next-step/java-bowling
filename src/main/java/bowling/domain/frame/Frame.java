package bowling.domain.frame;

import bowling.domain.status.Ready;
import bowling.domain.status.Status;

public abstract class Frame {

     protected int frameNo = 0;
     Status status = new Ready();

    Frame bowl() {
        return null;
    }

}
