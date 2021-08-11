package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PinsTest {

    @DisplayName("쓰러진 핀의 개수로 Pins를 만든다")
    @Test
    void should_make_pins() {
        //arrange, act, assert
        assertThat(Pins.of(10)).isEqualTo(Pins.of(10));
    }

    @DisplayName("핀이 0개 아래이거나 10이상이면 IllegalArgumentException을 반환한다")
    @ValueSource(ints = {-1, 11})
    @ParameterizedTest
    void should_throw_exception_when_null_or_empty(int count) {
        //arrange, act, assert
        assertThatIllegalArgumentException().isThrownBy(() -> Pins.of(count));
    }

    @DisplayName("쓰러진 핀의 개수 값을 반환한다")
    @Test
    public void should_return_count_pins() throws Exception {
        //arrange
        Pins pins = Pins.of(10);

        //act
        int count = pins.getCountHitPins();

        //assert
        assertThat(count).isEqualTo(10);
    }

    @DisplayName("쓰러진 핀이 모두다이면 true, 아니면 false를 반환한다")
    @CsvSource(value = {"1,false", "10,true"}, delimiter = ',')
    @ParameterizedTest
    public void should_return_is_all_fallen(int count, boolean expected) throws Exception {
        //arrange
        Pins pins = Pins.of(count);

        //act, assert
        assertThat(pins.isAllHit()).isEqualTo(expected);
    }

    @DisplayName("Pins와 Pins를 더하여 총 count pins수를 구할 수 있다")
    @Test
    public void should_return_pins_add_pins() throws Exception {
        //arrange
        Pins pins1 = Pins.of(2);
        Pins pins2 = Pins.of(5);

        //act
        Pins result = pins1.add(pins2);

        //assert
        assertThat(result.getCountHitPins()).isEqualTo(7);
    }

}