package bowling.model.frame.state;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("스페어")
class SpareTest {

    @Test
    @DisplayName("첫번째 핀으로 생성")
    void instance() {
        assertThatNoException().isThrownBy(() -> Spare.from(Pins.ZERO));
    }

    @Test
    @DisplayName("첫번째 핀은 필수")
    void instance_nullArgument_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Spare.from(null));
    }

    @Test
    @DisplayName("첫번째 핀이 10이 될 수 없음")
    void instance_maxPins_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Spare.from(Pins.MAX));
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
    @DisplayName("첫번째 핀이 1의 마크는 1|/")
    void mark() {
        assertThat(Spare.from(Pins.from(1)).mark()).isEqualTo("1|/");
    }

    @Test
    @DisplayName("핀들 더한 값은 항상 10")
    void sumPinsCount() {
        assertThat(Spare.from(Pins.from(1)).sumPinsCount()).isEqualTo(10);
    }
}
