package bowling.domain.state.finish;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertTrue;

import bowling.domain.score.Pin;
import bowling.domain.state.State;
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

    @Test
    @DisplayName("현재 상태가 종료되었는지 확인할 수 있다.")
    void isFinishedTest() {

        // given
        State state = new Strike(Pin.of(10));

        // when
        boolean result = state.isFinished();

        // then
        assertTrue(result);
    }
}
