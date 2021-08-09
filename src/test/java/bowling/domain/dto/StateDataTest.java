package bowling.domain.dto;

import bowling.domain.pin.Pins;
import bowling.domain.state.pitching.FirstPitching;
import bowling.domain.state.result.Miss;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class StateDataTest {

    @DisplayName("CommonState로 StateData를 만들 수 있다")
    @Test
    void should_make_state_data() {
        //arrange, act, assert
        assertThat(StateData.of(Miss.of(Pins.of(1), Pins.of(2)))).isInstanceOf(StateData.class);
    }

    @DisplayName("CommonState로 type을 반환 할 수 있다")
    @Test
    void should_return_type_state_with_miss() {
        //arrange
        StateData stateData = StateData.of(FirstPitching.of());

        //act, assert
        assertThat(stateData.getType()).isEqualTo(FirstPitching.class.getSimpleName());
    }

    @DisplayName("CommonState Miss일 경우 first, second pins를 반환 할 수 있다")
    @Test
    void should_return_pins_count_first_second() {
        //arrange
        StateData stateData = StateData.of(Miss.of(Pins.of(1), Pins.of(2)));

        //act, assert
        assertAll(
                () -> assertThat(stateData.getCountFirstPins()).isEqualTo(1),
                () -> assertThat(stateData.getCountSecondPins()).isEqualTo(2)
        );
    }

}