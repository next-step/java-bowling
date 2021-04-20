package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @DisplayName("투구 테스트")
    @Test
    void bowl_투구_테스트() {
        // given
        Frame frame = Frame.init();
        // when
        frame.bowl(3);
        int tryCount = frame.tryCount();
        // then
        assertThat(tryCount).isEqualTo(1);
    }

    @DisplayName("투구 2구 시 시도 횟수 확인 테스트")
    @Test
    void bowl_2구_테스트() {
        // given
        Frame frame = Frame.init();
        // when
        frame.bowl(3);
        frame.bowl(3);
        int tryCount = frame.tryCount();
        assertThat(tryCount).isEqualTo(2);
    }

    @DisplayName("투구 초구(0), 2구(0) GUTTER 확인 테스트")
    @Test
    void bowl_미스_확인테스트() {
        // given
        Frame frame = Frame.init();
        // when
        frame.bowl(0);
        frame.bowl(0);
        State state = frame.state();
        // then
        assertThat(state).isEqualTo(State.GUTTER);
    }

    @DisplayName("투구 스트라이크 테스트")
    @Test
    void bowl_스트라이크_확인테스트() {
        // given
        Frame frame = Frame.init();
        // when
        frame.bowl(10);
        State state = frame.state();
        // then
        assertThat(frame.isNextFrame()).isTrue();
        assertThat(state).isEqualTo(State.STRIKE);
    }

    @DisplayName("투구 스페어 테스트")
    @Test
    void bowl_스페어_확인테스트() {
        // given
        Frame frame = Frame.init();
        // when
        frame.bowl(9);
        frame.bowl(1);
        State state = frame.state();
        // then
        assertThat(frame.isNextFrame()).isTrue();
        assertThat(state).isEqualTo(State.SPARE);
    }
}
