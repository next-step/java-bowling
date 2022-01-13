package bowling;

import bowling.frame.Frame;
import bowling.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    void 프레임을_생성한다() {
        Frame firstFrame = NormalFrame.first();

        assertThat(firstFrame).isNotNull();
    }

    @Test
    void 스트라이크_다음_프레임_생성() {
        Frame frame = NormalFrame.first();
        Frame next = frame.bowl(new Pins(10));

        assertThat(next.getFrameNo()).isEqualTo(2);
    }

    @DisplayName("1투구 시 스트라이크나 스페어가 아니면 현재 프레임을 유지한다.")
    @Test
    void 현재_프레임_유지() {
        Frame frame = NormalFrame.first();
        Frame currentFrame = frame.bowl(new Pins(1));

        assertThat(currentFrame.getFrameNo()).isEqualTo(1);
    }
}