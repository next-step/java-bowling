package bowling.domain.state.end.second;

import bowling.domain.state.progress.SecondProgress;

/**
 * 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
 */
public class Spare extends EndOfSecondState {

    private static final String MARKER = "/";

    public Spare(SecondProgress secondProgress) {
        super(secondProgress);
    }

    @Override
    public String getPrintMark() {
        return getSecondPrintMark(MARKER);
    }
}
