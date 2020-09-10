package camp.nextstep.edu.rebellion.bowling.domain.status;

import static camp.nextstep.edu.rebellion.bowling.domain.status.FrameSymbol.STRIKE_SYMBOL;

public class Strike implements FrameStatus {
    @Override
    public String makeSymbol() {
        return STRIKE_SYMBOL;
    }
}
