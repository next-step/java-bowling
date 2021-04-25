package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PinDownCountTest {

    @Test
    @DisplayName("핀 처리 횟수 정상")
    void create() {
        // given when
        PinDownCount pinDownCount = new PinDownCount(3);

        // then
        assertThat(3).isEqualTo(pinDownCount.value());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("범위를 초과한 횟수 생성")
    void create_overRange(int param) {
        // given when then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PinDownCount(param))
                .withMessageMatching("핀 처리 갯수는 0 이상 10 이하의 수 여야 합니다.");
    }
}