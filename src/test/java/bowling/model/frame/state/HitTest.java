package bowling.model.frame.state;

import bowling.model.DownPin;
import bowling.model.frame.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.model.DownPin.MAX;
import static bowling.model.DownPin.MIN;
import static org.assertj.core.api.Assertions.assertThat;

public class HitTest {

    @DisplayName("핀이 " + MIN + "~" + MAX + "사이 일 시 생성에 성공한다")
    @ParameterizedTest
    @ValueSource(ints = {MIN, MAX})
    void createState_success(int pins) {
        // given
        DownPin firstDownPin = DownPin.valueOf(pins);

        // when
        State result = Hit.valueOf(firstDownPin);

        // then
        assertThat(result).isInstanceOf(Hit.class);
    }
}