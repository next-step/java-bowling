package bowling.domain.frame;

import bowling.domain.FrameIndex;
import bowling.domain.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FramesTest {

    @DisplayName("초기 프레임들 생성")
    @Test
    void initialize() {
        // given
        Frames frames = Frames.initialize();
        // when & then
        assertAll(
                () -> assertThat(frames.lastFrameIndex()).isEqualTo(FrameIndex.MIN_INDEX),
                () -> assertThat(frames.getFrames()).hasSize(1)
        );
    }

    @DisplayName("프레임이 진행 상태이면, 새로운 프레임이 추가되지 않는다 - 스트라이크가 아닌 첫 투구")
    @Test
    void bowlWithFirstThrowingAndNonStrike() {
        // given
        Frames frames = Frames.initialize();
        // then
        assertThat(frames.getFrames()).hasSize(1);
        // when
        frames.bowl(Pins.create(5));
        // then
        assertThat(frames.getFrames()).hasSize(1);
    }

    @DisplayName("프레임이 종료상태로 변경되면 새로운 프레임이 추가된다 - 스페어")
    @Test
    void bowlWithSpare() {
        // given
        Frames frames = Frames.initialize();
        // then
        assertThat(frames.getFrames()).hasSize(1);
        // when
        frames.bowl(Pins.create(3));
        frames.bowl(Pins.create(7));
        // then
        assertThat(frames.getFrames()).hasSize(2);
    }

    @DisplayName("프레임이 종료상태로 변경되면 새로운 프레임이 추가된다")
    @Test
    void bowlWithStrike() {
        // given
        Frames frames = Frames.initialize();
        // then
        assertThat(frames.getFrames()).hasSize(1);
        // when
        frames.bowl(Pins.create(10));
        // then
        assertThat(frames.getFrames()).hasSize(2);
    }

    @DisplayName("10프레임이면서 종료상태가 아니면 다음이 존재한다")
    @Test
    void hasNext() {
        // given
        Frames frames = Frames.initialize();
        // when & then
        assertThat(frames.hasNext()).isTrue();
    }
}
