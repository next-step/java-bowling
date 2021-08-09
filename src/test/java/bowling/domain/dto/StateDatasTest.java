package bowling.domain.dto;

import bowling.domain.pin.Pins;
import bowling.domain.state.pitching.FirstPitching;
import bowling.domain.state.result.Miss;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class StateDatasTest {

    @DisplayName("StateDatas를 CommonState로 만들 수 있다")
    @Test
    void should_make_state_datas() {
        //arrange, act, assert
        assertThat(StateDatas.of(Arrays.asList(FirstPitching.of(), Miss.of(Pins.of(1), Pins.of(2)))))
                .isInstanceOf(StateDatas.class);
    }

}