package bowling.model.frame;

import bowling.model.frame.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("던져진 공")
class FrameStateTest {

    @Test
    @DisplayName("상태와 점수로 생성")
    void instance() {
        assertThatNoException().isThrownBy(() -> FrameState.of(Strike.INSTANCE, Score.init(1)));
    }

    @Test
    @DisplayName("초기 상태로 생성")
    void instance_init() {
        assertThatNoException().isThrownBy(FrameState::init);
    }

    @Test
    @DisplayName("상태만으로 생성")
    void instance_state() {
        assertThatNoException().isThrownBy(() -> FrameState.from(Strike.INSTANCE));
    }

    @Test
    @DisplayName("상태와 점수는 필수")
    void instance_nullArguments_thrownIllegalArgumentException() {
        assertAll(
                () -> assertThatIllegalArgumentException().isThrownBy(() -> FrameState.from(null)),
                () -> assertThatIllegalArgumentException().isThrownBy(() -> FrameState.of(Strike.INSTANCE, null)),
                () -> assertThatIllegalArgumentException().isThrownBy(() -> FrameState.of(null, Score.init(1)))
        );
    }
}
