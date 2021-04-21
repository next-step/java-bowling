package bowling.domain.frame;

import bowling.domain.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @DisplayName("프레임 투구 전 확인 테스트")
    @Test
    void testCase1() {
        // given
        NormalFrame frame = new NormalFrame();
        // when
        FrameState actual = frame.state();
        // then
        assertThat(actual).isInstanceOf(Ready.class);
    }

    @DisplayName("프레임 초구 상태 확인 테스트")
    @Test
    void testCase2() {
        // given
        NormalFrame frame = new NormalFrame();
        // when
        frame.bowl(5);
        // then
        assertThat(frame.state()).isInstanceOf(Hit.class);
    }

    @DisplayName("프레임 초구 스트라이크 확인 테스트")
    @Test
    void testCase3() {
        // given
        NormalFrame frame = new NormalFrame();
        // when
        frame.bowl(10);
        // then
        assertThat(frame.state()).isInstanceOf(Strike.class);
    }

    @DisplayName("프레임 2구 스페어 확인 테스트")
    @Test
    void testCase4() {
        // given
        NormalFrame frame = new NormalFrame();
        // when
        frame.bowl(2);
        frame.bowl(8);
        // then
        assertThat(frame.state()).isInstanceOf(Spare.class);
    }

    @DisplayName("프레임 2구 미스 확인 테스트")
    @Test
    void testCase5() {
        // given
        NormalFrame frame = new NormalFrame();
        // when
        frame.bowl(0);
        frame.bowl(0);
        // then
        assertThat(frame.state()).isInstanceOf(Miss.class);
    }

    @DisplayName("프레임 1구 상태 확인 테스트")
    @Test
    void testCase6() {
        // given
        NormalFrame frame = new NormalFrame();
        // when
        frame.bowl(0);
        // then
        assertThat(frame.state()).isInstanceOf(Hit.class);
    }
}
