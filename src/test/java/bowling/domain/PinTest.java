package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PinTest {

    @Test
    @DisplayName("점수가 정상적으로 생성된다.")
    void createTest() {
        assertThatCode(() -> Pin.of(10)).doesNotThrowAnyException();
    }

    @ValueSource(ints = {-1, 11})
    @ParameterizedTest(name = "[{arguments}] Pin수는 0-10 사이가 아니면 예외가 발생한다.")
    void createExceptionTest(int input) {
        assertThatIllegalArgumentException().isThrownBy(() -> Pin.of(input));
    }

    @Test
    @DisplayName("Pin 객체 캐싱이 정상적으로 동작한다.")
    void cacheTest() {
        assertThat(Pin.of(10)).isSameAs(Pin.of(10));
    }

    @Test
    @DisplayName("Pin 합계가 정상적으로 반환된다.")
    void sumTest() {
        Pin sum = Pin.of(5).sum(Pin.of(5));
        assertThat(sum).isEqualTo(Pin.of(10));
    }


    @Test
    @DisplayName("Pin 합계가 10을 넘길 수 없다.")
    void sumValidTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> Pin.of(10).sum(Pin.of(5)));
    }
}
