package bowling.domain;

import bowling.domain.frame.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PinTest {

    @DisplayName("생성 테스트")
    @Test
    void createTest() {
        assertThat(Pin.from(5)).isEqualTo(Pin.from(5));
    }

    @DisplayName("쓰러진 핀의 갯수는 0~10 범위를 벗어나면 expception")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void createFailTest(int count) {
        assertThatIllegalArgumentException().isThrownBy(() -> Pin.from(count));
    }

    @DisplayName("add 후 0~10 범위를 벗어나면 expception")
    @Test
    void addFailTest() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                Pin.from(5).add(Pin.from(6))
        );
    }

    @DisplayName("add 는 count 를 합친 Pin을 반환한다.")
    @Test
    void addTest() {
        assertThat(Pin.from(5).add(Pin.from(5))).isEqualTo(Pin.from(10));
    }

    @DisplayName("isMaxCount() 쓰러진 핀의 개수가 10개이면 true를 반환한다. ")
    @Test
    void isMaxCountTest() {
        assertThat(Pin.from(5).isMaxCount()).isFalse();
        assertThat(Pin.from(10).isMaxCount()).isTrue();
    }
}
