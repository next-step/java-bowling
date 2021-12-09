package bowling.domain.frame;

import bowling.domain.state.FinalFrameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FinalFrameTest {

    private Frame ready;

    @BeforeEach
    void setUp(){
        ready = FinalFrame.readyFrame();
    }

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        assertThat(FinalFrame.readyFrame()).isEqualTo(FinalFrame.of(FinalFrameState.readyState()));
    }

    @DisplayName("3번까지 굴릴 수 있다.")
    @Test
    void bonusTest() {
        assertThat(ready.bowl(Pin.from(5)).bowl(Pin.from(5)).bowl(Pin.from(5)).isFinished()).isTrue();
        assertThat(ready.bowl(Pin.from(10)).bowl(Pin.from(5)).bowl(Pin.from(5)).isFinished()).isTrue();
        assertThat(ready.bowl(Pin.from(10)).bowl(Pin.from(5)).bowl(Pin.from(4)).isFinished()).isTrue();
        assertThat(ready.bowl(Pin.from(10)).bowl(Pin.from(10)).bowl(Pin.from(4)).isFinished()).isTrue();
        assertThat(ready.bowl(Pin.from(10)).bowl(Pin.from(10)).bowl(Pin.from(10)).isFinished()).isTrue();
    }

    @DisplayName("finish 상태에서 bowl을 호출 할 경우 illegal Exception")
    @Test
    void bowlFailTest() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                ready.bowl(Pin.from(10)).bowl(Pin.from(10)).bowl(Pin.from(10)).bowl(Pin.from(5))
        );

        assertThatIllegalArgumentException().isThrownBy(() ->
                ready.bowl(Pin.from(5)).bowl(Pin.from(5)).bowl(Pin.from(5)).bowl(Pin.from(5))
        );

        assertThatIllegalArgumentException().isThrownBy(() ->
                ready.bowl(Pin.from(5)).bowl(Pin.from(4)).bowl(Pin.from(5))
        );
    }
}
