package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PitchTest {

    @Test
    @DisplayName("핀 처리 횟수 정상")
    void create() {
        // given when
        Pitch pitch = new Pitch(3);

        // then
        assertThat(3).isEqualTo(pitch.value());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("범위를 초과한 횟수 생성")
    void create_overRange(int param) {
        // given when then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Pitch(param))
                .withMessageMatching("핀 처리 갯수는 0 이상 10 이하의 수 여야 합니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1", "0,-", "10,X"})
    void valueToString(int pinDownCount, String expected) {
        // given when
        Pitch pitch = new Pitch(pinDownCount);

        // then
        assertThat(expected).isEqualTo(pitch.toString());
    }
}