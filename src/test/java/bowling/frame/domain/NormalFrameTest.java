package bowling.frame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @DisplayName("일반 프레임은 첫번째 시도에 스트라이크를 치면 프레임이 종료된다.")
    @Test
    public void allStrikeTest() {
        Frame frame = NormalFrame.of();

        frame.bowl(10);
        assertThat(frame.isFinished()).isTrue();
    }

    @DisplayName("일반 프레임의 두번째 시도에 스페어를 치면 프레임이 종료된다.")
    @Test
    public void secondSpareTest() {
        Frame frame = NormalFrame.of();

        frame.bowl(0);
        assertThat(frame.isFinished()).isFalse();
        frame.bowl(10);
        assertThat(frame.isFinished()).isTrue();
    }

    @DisplayName("일반 프레임의 두번째 시도에 미스를 하면 프레임이 종료된다.")
    @Test
    public void secondMissTest() {
        Frame frame = NormalFrame.of();

        frame.bowl(5);
        assertThat(frame.isFinished()).isFalse();
        frame.bowl(1);
        assertThat(frame.isFinished()).isTrue();
    }

    @DisplayName("일반 프레임의 두번째 시도까지 거터를 하면 프레임이 종료된다.")
    @Test
    public void secondGutterTest() {
        Frame frame = NormalFrame.of();

        frame.bowl(0);
        assertThat(frame.isFinished()).isFalse();
        frame.bowl(0);
        assertThat(frame.isFinished()).isTrue();
    }

}
