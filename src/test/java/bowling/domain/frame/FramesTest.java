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

    @DisplayName("프레임 진행 중에 다음 투구가 가능한지 확인")
    @Test
    void hasNext() {
        // given
        Frames frames = Frames.initialize();
        // when & then
        assertThat(frames.hasNextPitching()).isTrue();
    }
}
