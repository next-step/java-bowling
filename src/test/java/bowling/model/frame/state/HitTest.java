package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.model.Pins.MAX;
import static bowling.model.Pins.MIN;
import static bowling.model.frame.state.Hit.MAX_COUNT_OF_PINS_IN_HIT;
import static bowling.model.frame.state.Hit.MIN_COUNT_OF_PINS_IN_HIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class HitTest {

    @DisplayName("핀이 " + MIN_COUNT_OF_PINS_IN_HIT + "~" + MAX_COUNT_OF_PINS_IN_HIT + "사이 일 시 생성에 성공한다")
    @ParameterizedTest
    @ValueSource(ints = {MIN_COUNT_OF_PINS_IN_HIT, MAX_COUNT_OF_PINS_IN_HIT})
    void createState_success(int pins) {
        // given
        Pins firstPins = Pins.valueOf(pins);

        // when
        State result = Hit.valueOf(firstPins);

        // then
        assertThat(result).isInstanceOf(Hit.class);
    }

    @DisplayName("핀이 " + MIN_COUNT_OF_PINS_IN_HIT + "~" + MAX_COUNT_OF_PINS_IN_HIT + "의 범위를 벗어날 시 생성에 실패한다")
    @ParameterizedTest
    @ValueSource(ints = {MIN, MAX})
    void createHit_fail(int pins) {
        // given
        Pins firstPins = Pins.valueOf(pins);

        // exception
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Hit.valueOf(firstPins));
    }

    public static State ofHit(Pins downPins){
        return Hit.valueOf(downPins);
    }
}