package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("프레임 번호")
class FrameNumberTest {

    @ParameterizedTest(name = "[{index}] {0} 으로 생성")
    @DisplayName("1 과 10 사이의 숫자로 생성")
    @ValueSource(ints = {1, 5, 10})
    void instance(int number) {
        assertThatNoException().isThrownBy(() -> FrameNumber.from(number));
    }

    @ParameterizedTest(name = "[{index}] {0} 으로 생성 불가능")
    @DisplayName("숫자는 반드시 1 과 10 사이")
    @ValueSource(ints = {0, 11})
    void instance_outOfRange_thrownIllegalArgumentException(int number) {
        assertThatIllegalArgumentException().isThrownBy(() -> FrameNumber.from(number));
    }

    @Test
    @DisplayName("증가")
    void increase() {
        assertThat(FrameNumber.from(1).increase()).isEqualTo(FrameNumber.from(2));
    }

    @Test
    @DisplayName("최대 10까지만 증가 가능")
    void increase_overThanMax_thrownIllegalStateException() {
        assertThatIllegalStateException().isThrownBy(() -> FrameNumber.from(10).increase());
    }

    @ParameterizedTest(name = "[{index}] {0} 은 마지막 번호가 ")
    @DisplayName("마지막 번호 여부")
    @CsvSource({"1,false", "9,false", "10,true"})
    void isLast(int number, boolean expected) {
        assertThat(FrameNumber.from(number).isLast()).isEqualTo(expected);
    }
}
