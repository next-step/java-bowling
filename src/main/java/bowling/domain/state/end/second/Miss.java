package bowling.domain.state.end.second;

import bowling.domain.state.progress.GeneralProgress;

/**
 * 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
 */
public class Miss extends EndOfSecondState {

    private static final String MARKER = "-";

    public Miss(GeneralProgress generalProgress) {
        super(generalProgress);
    }

    @Override
    public String getPrintMark() {
        return getSecondPrintMark(MARKER);
    }
}
