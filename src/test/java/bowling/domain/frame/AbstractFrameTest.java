package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.score.Pin;
import bowling.domain.state.running.FirstBowl;
import bowling.domain.state.running.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AbstractFrameTest {

    @Test
    @DisplayName("bowling을 했을 때 상태가 종료되지 않으면 현재 Frame을 다시 반환한다.")
    void bowlingRunningTest() {

        // given
        Pin pin = Pin.of(3);
        Frame frame = NormalFrame.from(1, null, new Ready());

        Frame expected = NormalFrame.from(1, null, new FirstBowl(pin));

        // when
        Frame result = frame.bowling(Pin.of(3));

        // then
        assertThat(result).isEqualTo(expected);
    }

}