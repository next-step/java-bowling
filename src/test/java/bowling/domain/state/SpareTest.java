package bowling.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SpareTest {
    private State spare;

    @BeforeEach
    void setUp() {
        spare = new Spare(5);
    }

    @Test
    @DisplayName("쓰러뜨린 핀 수는 0 이상 10 미만여야 한다. (10 = Strike)")
    void init() {
        assertThat(new Spare(0)).isNotNull();
        assertThat(new Spare(9)).isNotNull();

        assertThrows(IllegalArgumentException.class, () -> new Spare(-1));
        assertThrows(IllegalArgumentException.class, () -> new Spare(10));
    }

    @Test
    @DisplayName("Spare 상태에서는 공을 굴릴 수 없다.")
    void isBowl() {
        assertThat(spare.isBowl()).isFalse();
    }

    @Test
    @DisplayName("Spare 상태에서는 공을 굴리려 하면 예외가 발생한다.")
    void bowlException() {
        assertThrows(IllegalStateException.class, () -> spare.bowl(5));
    }

    @ParameterizedTest
    @DisplayName("Spare 상태를 출력하면 {첫번째 투구 결과}|/ 가 출력된다.")
    @CsvSource(value = {"5,5|/", "0,-|/", "9,9|/"})
    void print(int firstFallenPinCount, String expected) {
        Spare spare = new Spare(firstFallenPinCount);
        assertThat(spare.print()).isEqualTo(expected);
    }
}
