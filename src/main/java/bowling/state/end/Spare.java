package bowling.state.end;

import bowling.state.end.EndStatus;

/**
 * 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
 */
public class Spare implements EndStatus {

    @Override
    public String getPrintMark() {
        return "/";
    }
}
