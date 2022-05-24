package bowling.domain;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public enum HitState {
    START(FALSE),   // 프레임 투구 시작 전 상태
    STRIKE(TRUE),   // 첫번째 투구에 전부 쓰러뜨림
    SPARE(TRUE),    // 첫번째 투구에 몇개 쓰러뜨리고, 두번째에 나머지 다 쓰러뜨림
    MISS(FALSE),    // 첫번째 투구에 몇개 쓰러뜨리고, 두번째에 한개도 쓰러뜨리지 못함
    GUTTER(FALSE),  // 첫번째 투구 결과 아무것도 안닿고 레인 홈통에 빠짐
    SPLIT(FALSE);   // 첫번째 투구 결과 1번 핀 비롯해 몇개 쓰러지고 나머지 핀 몇개 서있는 상태

    private final boolean oneMore;

    HitState(boolean oneMore) {
        this.oneMore = oneMore;
    }

    public boolean hasOneMoreChance() {
        return oneMore;
    }

}
