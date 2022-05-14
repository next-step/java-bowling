package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.NotThrown;
import bowling.model.frame.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
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

    @Test
    @DisplayName("끝난 상태 여부")
    void isEndState() {
        assertAll(
                () -> assertThat(FrameState.from(Strike.INSTANCE).isEndState()).isTrue(),
                () -> assertThat(FrameState.from(NotThrown.INSTANCE).isEndState()).isFalse()
        );
    }

    @Test
    @DisplayName("안던진 상태에서 10개 던진 상태는 스트라이크 상태")
    void nextState() {
        assertThat(FrameState.from(NotThrown.INSTANCE).nextState(Pins.MAX))
                .isEqualTo(FrameState.of(Strike.INSTANCE, Score.of(0, 2)));
    }

    @Test
    @DisplayName("끝난 상태에서 다음 상태를 가져올 수 없음")
    void nextState_endedState_thrownIllegalStateException() {
        assertThatIllegalStateException().isThrownBy(() -> FrameState.from(Strike.INSTANCE).nextState(Pins.MAX));
    }

    @Test
    @DisplayName("남은 갯수 소유 여부")
    void hasRemainCount() {
        assertAll(
                () -> assertThat(FrameState.init().hasRemainCount()).isTrue(),
                () -> assertThat(FrameState.of(NotThrown.INSTANCE, Score.of(0, 0)).hasRemainCount()).isFalse()
        );
    }

    @Test
    @DisplayName("점수 추가")
    void addScore() {
        assertThat(FrameState.init().addScore(Pins.MAX))
                .isEqualTo(FrameState.of(NotThrown.INSTANCE, Score.of(10, 1)));
    }

    @Test
    @DisplayName("주어진 점수 그대로 반환")
    void score() {
        assertThat(FrameState.init().score())
                .isEqualTo(Score.of(0, 2));
    }

    @Test
    @DisplayName("주어진 상태 그대로 반환")
    void state() {
        assertThat(FrameState.init().state())
                .isEqualTo(NotThrown.INSTANCE);
    }
}
