package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.NotThrown;
import bowling.model.frame.state.SecondThrown;
import bowling.model.frame.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("프레임 상태")
class FrameStateTest {

    @Test
    @DisplayName("상태와 보너스 상태들로 생성")
    void instance() {
        assertThatNoException().isThrownBy(() -> FrameState.of(Strike.INSTANCE, Collections.singletonList(Strike.INSTANCE)));
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
    @DisplayName("상태와 보너스 상태들은 필수")
    void instance_nullArguments_thrownIllegalArgumentException() {
        assertAll(
                () -> assertThatIllegalArgumentException().isThrownBy(() -> FrameState.from(null)),
                () -> assertThatIllegalArgumentException().isThrownBy(() -> FrameState.of(Strike.INSTANCE, null)),
                () -> assertThatIllegalArgumentException().isThrownBy(() -> FrameState.of(null, Collections.singletonList(Strike.INSTANCE)))
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
                .isEqualTo(FrameState.from(Strike.INSTANCE));
    }

    @Test
    @DisplayName("끝난 상태에서 다음 상태를 가져올 수 없음")
    void nextState_endedState_thrownIllegalStateException() {
        assertThatIllegalStateException().isThrownBy(() -> FrameState.from(SecondThrown.of(Pins.ZERO, Pins.ZERO)).nextState(Pins.MAX));
    }

    @Test
    @DisplayName("남은 갯수 소유 여부")
    void hasRemainCount() {
        assertAll(
                () -> assertThat(FrameState.init().hasRemainCount()).isTrue(),
                () -> assertThat(FrameState.of(Strike.INSTANCE, Arrays.asList(Strike.INSTANCE, Strike.INSTANCE)).hasRemainCount()).isFalse()
        );
    }

    @Test
    @DisplayName("보너스 핀 추가")
    void addBonusPins() {
        assertThat(FrameState.from(Strike.INSTANCE).addBonusPins(Pins.MAX))
                .isEqualTo(FrameState.of(Strike.INSTANCE, Collections.singletonList(Strike.INSTANCE)));
    }

    @Test
    @DisplayName("핀들을 합친 갯수")
    void sumPinsCount() {
        assertThat(FrameState.of(Strike.INSTANCE, Collections.singletonList(Strike.INSTANCE)).sumPinsCount()).isEqualTo(20);
    }

    @Test
    @DisplayName("마크")
    void mark() {
        assertThat(FrameState.from(Strike.INSTANCE).mark()).isEqualTo("x");
    }

    @Test
    @DisplayName("보너스가 포함된 마크")
    void markWithBonus() {
        assertThat(FrameState.of(Strike.INSTANCE, Collections.singletonList(Strike.INSTANCE)).markWithBonus())
                .isEqualTo("x|x");
    }
}
