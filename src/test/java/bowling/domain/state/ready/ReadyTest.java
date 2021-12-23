package bowling.domain.state.ready;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.Pin;
import bowling.domain.frame.Frame;
import bowling.domain.frame.GeneralFrame;
import bowling.domain.state.progress.FirstProgress;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    private final Frame frame = new GeneralFrame(null);

    @Test
    @DisplayName("준비 다음 상태는, FirstProgress 이다.")
    void getUpdateStateTest() {
        Ready ready = new Ready();
        assertThat(ready.getUpdateState(frame, Pin.of(10))).isInstanceOf(FirstProgress.class);

    }
}