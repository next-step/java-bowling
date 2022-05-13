package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("점수")
class ScoreTest {

    @Test
    @DisplayName("초기 점수와 남은 갯수로 생성")
    void instance() {
        assertThatNoException().isThrownBy(() -> Score.of(0, 1));
    }

    @Test
    @DisplayName("남은 갯수만으로 생성")
    void instance_restCount() {
        assertThatNoException().isThrownBy(() -> Score.init(1));
    }

    @Test
    @DisplayName("초기 값과 남은 갯수는 반드시 0 또는 양수")
    void instance_negative_thrownIllegalArgumentException() {
        assertAll(
                () -> assertThatIllegalArgumentException().isThrownBy(() -> Score.of(1, -1)),
                () -> assertThatIllegalArgumentException().isThrownBy(() -> Score.of(-1, 1))
        );
    }

    @ParameterizedTest
    @DisplayName("남은 갯수 잔여 여부")
    @CsvSource({"1,true", "0,false"})
    void hasRestCount(int restCount, boolean expected) {
        assertThat(Score.init(restCount).hasRestCount()).isEqualTo(expected);
    }

    @Test
    @DisplayName("값을 더한 값")
    void addValue() {
        assertThat(Score.of(10, 1).addValue(10)).isEqualTo(Score.of(20, 0));
    }

    @Test
    @DisplayName("남은 갯수가 없는 상태에서 값을 더할 수 없음")
    void addedValue_zeroRestCount_thrownIllegalStateException() {
        assertThatIllegalStateException().isThrownBy(() -> Score.of(10, 0).addValue(10));
    }

    @Test
    @DisplayName("남은 갯수 변경")
    void changedRestCount() {
        assertThat(Score.of(10, 0).changeRestCount(10)).isEqualTo(Score.of(10, 10));
    }

    @Test
    @DisplayName("주어진 값 그대로 반환")
    void value() {
        assertThat(Score.of(10, 0).value()).isEqualTo(10);
    }
}
