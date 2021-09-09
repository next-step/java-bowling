package bowling.bowlingdrawing.domain;

import bowling.bowlingdrawing.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class PitchingTest {

    @Test
    @DisplayName("Pitching 생성")
    void create() {
        // given
        int pins = 9;
        // when
        Pitching pitching = new Pitching(pins);
        // then
        assertThat(pitching).isEqualTo(new Pitching(9));
    }

    @ParameterizedTest(name = "Pitching 생성 실패 : 허용 범위 초과")
    @ValueSource(ints = {-1, 11})
    void create_fail(int pins) {
        // when, then
        assertThatThrownBy(() -> new Pitching(pins))
                .isInstanceOf(CustomException.class);
    }

}