package bowling.domain.state.end.second;

import bowling.domain.state.progress.SecondProgress;

public class Normal extends EndOfSecondState {


    public Normal(SecondProgress secondProgress) {
        super(secondProgress);
    }

    @Override
    public String getPrintMark() {
        return getSecondPrintMark(String.valueOf(secondProgress.getHitCount()));
    }

}
