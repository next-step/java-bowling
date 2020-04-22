package bowling.domain.frame;

import bowling.domain.status.running.Ready;

public class NormalFrame extends Frame {
    public NormalFrame() {
        super();
        this.status = Ready.of();
    }
}
