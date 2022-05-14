package bowling.model.frame.state;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("스페어")
class SpareTest {

    @Test
    @DisplayName("첫번째 0, 두번째 10으로 생성")
    void instance() {
        assertThatNoException().isThrownBy(() -> Spare.from(Pins.ZERO));
    }

    @Test
    @DisplayName("첫번째 핀들은 필수")
    void instance_nullArgument_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Spare.from(null));
    }

    @Test
    @DisplayName("종료 여부는 항상 참")
    void isEnd() {
        assertThat(Spare.from(Pins.ZERO).isEnd()).isTrue();
    }

    @Test
    @DisplayName("다음 상태는 생성 불가")
    void state_thrownIllegalStateException() {
        assertThatIllegalStateException().isThrownBy(() -> Spare.from(Pins.ZERO).state(Pins.ZERO));
    }

    @Test
    @DisplayName("남은 투구는 1")
    void remainCount() {
        assertThat(Spare.from(Pins.ZERO).remainCount()).isOne();
    }

    @Test
    @DisplayName("주어진 첫번째 핀들 그대로 반환")
    void firstHit() {
        assertThat(Spare.from(Pins.ZERO).firstHit()).isEqualTo(Pins.ZERO);
    }
}
