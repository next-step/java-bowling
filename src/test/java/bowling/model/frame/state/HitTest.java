package bowling.model.frame.state;

import bowling.model.Pin;
import bowling.model.frame.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.model.Pin.MAX;
import static bowling.model.Pin.MIN;
import static bowling.model.frame.state.Hit.MAX_PINS;
import static bowling.model.frame.state.Hit.MIN_PINS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class HitTest {

    @DisplayName("핀이 " + MIN_PINS + "~" + MAX_PINS + "사이 일 시 생성에 성공한다")
    @ParameterizedTest
    @ValueSource(ints = {MIN_PINS, MAX_PINS})
    void createState_success(int pins) {
        // given
        Pin firstPin = Pin.valueOf(pins);

        // when
        State result = Hit.valueOf(firstPin);

        // then
        assertThat(result).isInstanceOf(Hit.class);
    }

    @DisplayName("핀이 " + MIN_PINS + "~" + MAX_PINS + "의 범위를 벗어날 시 생성에 실패한다")
    @ParameterizedTest
    @ValueSource(ints = {MIN, MAX})
    void createHit_fail(int pins) {
        // given
        Pin firstPin = Pin.valueOf(pins);

        // exception
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Hit.valueOf(firstPin));
    }

    public static State ofHit(Pin downPin){
        return Hit.valueOf(downPin);
    }
}