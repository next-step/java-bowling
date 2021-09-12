package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    @DisplayName("Ready 상태의 프레임에서 10개 미만의 핀을 쓰러트린 경우, 프레임은 Proceeding 상태가 된다.")
    @Test
    public void nextStateTest() {
        FrameState ready = new Ready();
        FrameState next = ready.bowl(new FallenPinCount(9));
        assertThat(next).isExactlyInstanceOf(Proceeding.class);
    }

    @DisplayName("Ready 상태의 프레임에서 10개의 핀을 쓰러트린 경우, 프레임은 Strike 상태가 된다.")
    @Test
    public void strikeStateTest() {
        FrameState ready = new Ready();
        FrameState next = ready.bowl(new FallenPinCount(10));
        assertThat(next).isExactlyInstanceOf(Strike.class);
    }

}