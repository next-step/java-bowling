package bowling.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PinsTest {

    @Test
    void from_정상() {
        assertThat(Pins.from(0).isMinScore()).isTrue();
        assertThat(Pins.from(10).isMaxScore()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void from_비정상(int errorNumber) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Pins.from(errorNumber))
                .withMessage("허용할 수 없는 값입니다.");
    }

    @Test
    void add_정상() {
        Pins pins = Pins.from(0);
        IntStream.rangeClosed(0, 10)
                .mapToObj(Pins::from)
                .forEach(secondScore -> assertThat(pins.add(secondScore)).isEqualTo(secondScore));

    }

    @Test
    void add_비정상() {
        Pins pins = Pins.from(10);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pins.add(Pins.from(1)))
                .withMessage("허용할 수 없는 값입니다.");
    }
}