package bowling.state.end;

import bowling.state.end.EndStatus;

/**
 * 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
 */
public class Strike implements EndStatus {

    @Override
    public String getPrintMark() {
        return "X";
    }
}
