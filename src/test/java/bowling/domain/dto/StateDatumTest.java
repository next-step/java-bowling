package bowling.domain.dto;

import bowling.domain.pin.Pins;
import bowling.domain.state.pitching.FirstPitching;
import bowling.domain.state.result.Miss;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class StateDatumTest {

    @DisplayName("CommonState로 StateData를 만들 수 있다")
    @Test
    void should_make_state_data() {
        //arrange, act, assert
        assertThat(StateDatum.of(Miss.of(Pins.of(1), Pins.of(2)))).isInstanceOf(StateDatum.class);
    }

    @DisplayName("CommonState로 type을 반환 할 수 있다")
    @Test
    void should_return_type_state_with_miss() {
        //arrange
        StateDatum stateDatum = StateDatum.of(FirstPitching.of());

        //act, assert
        assertThat(stateDatum.getType()).isEqualTo(FirstPitching.class.getSimpleName());
    }

    @DisplayName("CommonState Miss일 경우 first, second pins를 반환 할 수 있다")
    @Test
    void should_return_pins_count_first_second() {
        //arrange
        StateDatum stateDatum = StateDatum.of(Miss.of(Pins.of(1), Pins.of(2)));

        //act, assert
        assertAll(
                () -> assertThat(stateDatum.getCountFirstPins()).isEqualTo(1),
                () -> assertThat(stateDatum.getCountSecondPins()).isEqualTo(2)
        );
    }

}