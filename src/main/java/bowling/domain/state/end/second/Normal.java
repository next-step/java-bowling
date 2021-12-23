package bowling.domain.state.end.second;

import bowling.domain.state.progress.GeneralProgress;

public class Normal extends EndOfSecondState {


    public Normal(GeneralProgress generalProgress) {
        super(generalProgress);
    }

    @Override
    public String getPrintMark() {
        return getSecondPrintMark(String.valueOf(generalProgress.getHitCount()));
    }

}
