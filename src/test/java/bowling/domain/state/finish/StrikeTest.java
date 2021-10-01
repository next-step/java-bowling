package bowling.domain.state.finish;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.domain.score.Pin;
import bowling.exception.state.StrikeStatePinException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StrikeTest {

    @Test
    @DisplayName("10이 아닌 pin이 들어오면 생성할 수 없다.")
    void createStrikeStateExceptionTEst() {

        // given
        Pin pin = Pin.of(5);

        // when & then
        assertThatExceptionOfType(StrikeStatePinException.class)
            .isThrownBy(() -> new Strike(pin))
            .withMessageMatching("Strike는 pin이 한번에 10개가 무너져야합니다.");
    }
}
