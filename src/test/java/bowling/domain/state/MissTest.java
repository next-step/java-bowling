package bowling.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MissTest {
    private State miss;

    @BeforeEach
    void setUp() {
        miss = new Miss(5, 4);
    }

    @Test
    @DisplayName("쓰러뜨린 핀 수는 0 이상 이며 두 횟수의 합은 10 이하여야 한다.")
    void init() {
        assertThat(new Miss(0, 5)).isNotNull();
        assertThat(new Miss(2, 8)).isNotNull();

        assertThrows(IllegalArgumentException.class, () -> new Miss(-1, 5));
        assertThrows(IllegalArgumentException.class, () -> new Miss(0, 11));
    }

    @Test
    @DisplayName("Miss 상태에서는 공을 굴릴 수 없다.")
    void isFinish() {
        assertThat(miss.isFinish()).isTrue();
    }

    @Test
    @DisplayName("Miss 상태에서는 공을 굴리려 하면 예외가 발생한다.")
    void bowlException() {
        assertThrows(IllegalStateException.class, () -> miss.bowl(()->5));
    }

    @ParameterizedTest
    @DisplayName("Miss 상태를 출력하면 {첫번째 투구 결과}|{두번째 투구 결과} 가 출력된다.")
    @CsvSource(value = {"5,3,5|3", "0,0,-|-", "9,0,9|-"})
    void print(int firstFallenPinCount, int secondFallenPinCount, String expected) {
        Miss miss = new Miss(firstFallenPinCount, secondFallenPinCount);
        assertThat(miss.print()).isEqualTo(expected);
    }
}
