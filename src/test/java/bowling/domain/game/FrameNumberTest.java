package bowling.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 10})
    @DisplayName("프레임 번호는 1 ~ 10까지의 숫자만 가능하다.")
    void create_frameNumber(int number) {
        assertThat(FrameNumber.of(number).getNumber()).isEqualTo(number);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 11})
    @DisplayName("프레임 번호는 1 ~ 10까지의 숫자가 아닌 경우 생성할 수 없다.")
    void dont_create_frameNumber(int number) {
        assertThatThrownBy(() -> FrameNumber.of(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 번호입니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = 10)
    @DisplayName("프레임 번호 출력 시 10의 단위인 경우 그대로 출력한다.")
    void toString_ten(int number) {
        assertThat(FrameNumber.of(number).toString()).isEqualTo(Integer.toString(number));
    }
}