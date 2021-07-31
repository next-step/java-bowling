package bowling.domain.state;

import bowling.domain.pin.DownedPins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("두 번 투구하여 핀이 모두 쓰러진 상태에 대한 테스트")
class SpareTest {

    @DisplayName("Spare 상태는 첫번째 투구 정보를 가지고 초기화 한다")
    @Test
    void init() {
        assertThat(Spare.from(DownedPins.from(5))).isInstanceOf(Spare.class);
    }
}
