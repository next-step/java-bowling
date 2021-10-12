package bowling.domain.frame;

import bowling.domain.PinCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicFrameTest {
    @DisplayName("플레이 후 프레임이 종료되지 않으면 같은 프레임을 반환")
    @Test
    void play_ReturnSameFrame_IfFrameIsNotFinished() {
        Frame frame = new BasicFrame(FrameNumber.of(FrameNumber.START_NUMBER));
        Frame next = frame.play(PinCount.of(5));
        assertThat(frame).isEqualTo(next);
    }

    @DisplayName("스트라이크 후 다음 프레임 반환")
    @Test
    void play_ReturnNextFrame_IfFrameIsFinishedByStrike() {
        FrameNumber frameNumber = FrameNumber.of(FrameNumber.START_NUMBER);
        Frame frame = new BasicFrame(frameNumber);
        Frame next = frame.play(PinCount.MAX_PINS);
        assertThat(next.getFrameNumber()).isEqualTo(frameNumber.next());
    }

    @DisplayName("두 번 투구 후 다음 프레임 반환")
    @Test
    void play_ReturnNextFrame_IfFrameIsFinishedBySecondPitched() {
        FrameNumber frameNumber = FrameNumber.of(FrameNumber.START_NUMBER);
        Frame frame = new BasicFrame(frameNumber);
        Frame next = frame.play(PinCount.of(5));
        next = next.play(PinCount.of(5));
        assertThat(next.getFrameNumber()).isEqualTo(frameNumber.next());
    }

}
