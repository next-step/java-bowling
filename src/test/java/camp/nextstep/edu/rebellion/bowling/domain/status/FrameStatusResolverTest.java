package camp.nextstep.edu.rebellion.bowling.domain.status;

import camp.nextstep.edu.rebellion.bowling.domain.frame.Frame;
import camp.nextstep.edu.rebellion.bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FrameStatusResolverTest {
    @DisplayName("아직 투구 전인 프레임일 경우 상태가 바르게 반환되는지 확인")
    @Test
    public void notSTartedTest() {
        // given
        Frame frame = new NormalFrame();

        // when & then
        assertThat(FrameStatusResolver.resolve(frame) instanceof NotStarted).isTrue();
        assertThat(frame.getStatus() instanceof NotStarted).isTrue();

    }

    @DisplayName("투구 중인 프레임일 경우 상태가 바르게 반환되는지 확인")
    @Test
    public void playingTest() {
        // given
        Frame frame = new NormalFrame();
        frame.markScore(5);

        // when & then
        assertThat(FrameStatusResolver.resolve(frame) instanceof Playing).isTrue();
        assertThat(frame.getStatus() instanceof Playing).isTrue();
    }

    @DisplayName("Strike 인 경우 상태가 바르게 반환되는지 확인")
    @Test
    public void strikeTest() {
        // given
        Frame frame = new NormalFrame();
        frame.markScore(10);

        // when & then
        assertThat(FrameStatusResolver.resolve(frame) instanceof Strike).isTrue();
        assertThat(frame.getStatus() instanceof Strike).isTrue();
    }

    @DisplayName("Spare 인 경우 상태가 바르게 반환되는지 확인")
    @Test
    public void spareTest() {
        // given
        Frame frame = new NormalFrame();
        frame.markScore(9);
        frame.markScore(1);

        // when & then
        assertThat(FrameStatusResolver.resolve(frame) instanceof Spare).isTrue();
        assertThat(frame.getStatus() instanceof Spare).isTrue();
    }

    @DisplayName("Miss 인 경우 상태가 바르게 반환되는지 확인")
    @Test
    public void missTest() {
        // given
        Frame frame = new NormalFrame();
        frame.markScore(1);
        frame.markScore(2);

        // when & then
        assertThat(FrameStatusResolver.resolve(frame) instanceof Miss).isTrue();
        assertThat(frame.getStatus() instanceof Miss).isTrue();
    }
}
