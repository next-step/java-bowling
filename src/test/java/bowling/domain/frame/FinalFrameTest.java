package bowling.domain.frame;

import bowling.domain.engine.State;
import bowling.domain.state.finish.Spare;
import bowling.domain.state.finish.Strike;
import bowling.domain.state.run.Hit;
import bowling.exception.CanNotBonusException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

class FinalFrameTest {

    @DisplayName("10 프레임 투구 시 라운드 확인 테스트")
    @Test
    void testCase1() {
        // given
        FinalFrame frame = new FinalFrame();
        // when
        frame.bowl(10);
        frame.bowl(10);
        // then
        assertThat(frame.round()).isEqualTo(10);
    }

    @DisplayName("10 프레임 3투구 시 상태 확인 테스트")
    @Test
    void testCase2() {
        // given
        FinalFrame frame = new FinalFrame();
        // when
        frame.bowl(5);
        frame.bowl(5);
        frame.bowl(10);
        // then
        List<State> status = frame.status();

        assertAll(
                () -> assertThat(status.get(0)).isInstanceOf(Hit.class),
                () -> assertThat(status.get(1)).isInstanceOf(Spare.class),
                () -> assertThat(status.get(2)).isInstanceOf(Strike.class)
        );
    }

    @DisplayName("10 프레임 스페어 또는 스트라이크 시 3구 시도 가능 예외처리 테스트")
    @Test
    void testCase3() {
        // given
        FinalFrame frame = new FinalFrame();
        // when

        // then
        assertThatExceptionOfType(CanNotBonusException.class)
                .isThrownBy(() -> {
                    frame.bowl(2);
                    frame.bowl(7);
                    frame.bowl(2);
                });
    }

    @DisplayName("10 프레임 다음 프레임 생성 여부 확인 테스트")
    @Test
    void testCase4() {
        // given
        FinalFrame frame = new FinalFrame();
        // when
        boolean nextFrame = frame.isNextFrame();
        // then
        assertThat(nextFrame).isFalse();
    }

    @DisplayName("10 프레임 터키 테스트")
    @Test
    void testCase5() {
        // given
        FinalFrame frame = new FinalFrame();
        // when
        frame.bowl(10);
        frame.bowl(10);
        frame.bowl(10);
        // then
        List<State> status = frame.status();

        assertAll(
                () -> assertThat(status.get(0)).isInstanceOf(Strike.class),
                () -> assertThat(status.get(1)).isInstanceOf(Strike.class),
                () -> assertThat(status.get(2)).isInstanceOf(Strike.class)
        );
    }

    @DisplayName("10 프레임 3구 가능 여부 테스트")
    @Test
    void testCase6() {
        // given
        FinalFrame frame = new FinalFrame();
        // when
        frame.bowl(2);
        frame.bowl(5);
        // then
        assertThat(frame.isEnd()).isTrue();
    }
}
