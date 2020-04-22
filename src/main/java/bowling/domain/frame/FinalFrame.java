package bowling.domain.frame;

import bowling.domain.status.running.FinalReady;

public class FinalFrame extends Frame {
    public FinalFrame() {
        super();
        this.status = FinalReady.of();
    }
}
