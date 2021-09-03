package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PitchingTest {

    @Test
    @DisplayName("초구 스트라이크")
    void strike() {
        //given
        Pitching pitching = Pitching.first(10);

        //when

        //then
        assertThat(pitching.isStrike()).isTrue();

    }

    @ParameterizedTest(name = "스페어 처리 {index} [{arguments}]")
    @CsvSource(value = {
            "0,10",
            "1,9",
            "9,1"
    })
    @DisplayName("스페어 처리")
    void spare(int first, int second) {
        //given
        Pitching pitching = Pitching.first(first).second(second);

        //when

        //then
        assertThat(pitching.isSpare()).isTrue();

    }



}
