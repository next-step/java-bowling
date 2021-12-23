package bowling.state.end;

import bowling.state.end.EndStatus;

/**
 * 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
 */
public class Gutter implements EndStatus {

    @Override
    public String getPrintMark() {
        return "-";
    }
}
