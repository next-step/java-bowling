package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NormalFrameTest {
    @DisplayName("1~9 사이의 범위의 숫자를 가진 일반 프레임을 생성한다.")
    @ParameterizedTest(name = "{displayName}; number: {0}")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void create(int frameNumber) {
        new NormalFrame(frameNumber);
    }

    @DisplayName("범위 밖의 숫자가 주어지면 예외를 던진다.")
    @ParameterizedTest(name = "{displayName}; number: {0}")
    @ValueSource(ints = {-1, 0, 10, 11})
    void createWithInvalidNumber(int frameNumber) {
        assertThatThrownBy(() -> new NormalFrame(frameNumber))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("일반 프레임의 숫자는 1과 9 사이의 숫자여야 합니다");
    }

    @Test
    @DisplayName("스트라이크를 쳤을 때 한번의 시도로 프레임이 끝나야 한다.")
    void bowlWithStrike() {
        Frame frame = new NormalFrame(1);
        frame.bowl(10);
        assertThat(frame.isFrameFinish()).isTrue();
    }

    @Test
    @DisplayName("스페어를 쳤을 때 두번의 시도로 프레임이 끝나야 한다.")
    void bowlWithSpare() {
        Frame frame = new NormalFrame(1);
        frame.bowl(0);
        assertThat(frame.isFrameFinish()).isFalse();
        frame.bowl(10);
        assertThat(frame.isFrameFinish()).isTrue();
    }

    @Test
    @DisplayName("Miss를 쳤을 때 두번의 시도로 프레임이 끝나야 한다.")
    void bowlWithMiss() {
        Frame frame = new NormalFrame(1);
        frame.bowl(3);
        assertThat(frame.isFrameFinish()).isFalse();
        frame.bowl(4);
        assertThat(frame.isFrameFinish()).isTrue();
    }

    @DisplayName("다음 라운드에 대해, 마지막 라운드를 제외한 라운드는 일반 프레임으로 생성된다.")
    @ParameterizedTest(name = "round = {0}")
    @ValueSource(ints = {1, 2, 7, 8})
    void createNextNormalFrame(int frameNumber) {
        Frame nextFrame = new NormalFrame(frameNumber).createNextFrame();
        assertThat(nextFrame instanceof NormalFrame).isTrue();
        assertThat(nextFrame.getFrameNumber()).isEqualTo(frameNumber + 1);
    }

    @Test
    @DisplayName("마지막 라운드는 LastFrame 인스턴스로 생성된다.")
    void createLastFrame() {
        Frame nextFrame = new NormalFrame(Frame.LAST_FRAME - 1).createNextFrame();
        assertThat(nextFrame instanceof LastFrame).isTrue();
    }
}
