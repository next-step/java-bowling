package camp.nextstep.edu.rebellion.bowling.domain.status;

import camp.nextstep.edu.rebellion.bowling.util.StringUtil;

public class NotStarted implements FrameStatus {

    @Override
    public String makeSymbol() {
        return StringUtil.EMPTY;
    }
}
