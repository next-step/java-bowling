package bowling.frame;

import bowling.Pins;
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

    @DisplayName("마지막 이전 프레임이면 마지막 프레임을 생성한다.")
    @Test
    void 다음_마지막_프레임_생성() {
        Frame frame = NormalFrame.first();
        Frame lastFrame = frame.bowl(new Pins(10))
                .bowl(new Pins(10))
                .bowl(new Pins(10))
                .bowl(new Pins(10))
                .bowl(new Pins(10))
                .bowl(new Pins(10))
                .bowl(new Pins(10))
                .bowl(new Pins(10))
                .bowl(new Pins(10));

        assertThat(lastFrame).isInstanceOf(LastFrame.class);
        assertThat(lastFrame.getFrameNo()).isEqualTo(10);
    }

    @Test
    void 스트라이크_심볼() {
        Frame frame = NormalFrame.first();
        frame.bowl(new Pins(10));

        assertThat(frame.symbol()).isEqualTo("X");
    }

    @Test
    void 스페어_심볼() {
        Frame frame = NormalFrame.first();
        frame.bowl(new Pins(1)).bowl(new Pins(9));

        assertThat(frame.symbol()).isEqualTo("1|/");
    }

    @Test
    void 미스_심볼() {
        Frame frame = NormalFrame.first();
        frame.bowl(new Pins(1)).bowl(new Pins(2));

        assertThat(frame.symbol()).isEqualTo("1|2");
    }

    @Test
    void 미스_거터_심볼() {
        Frame frame = NormalFrame.first();
        frame.bowl(new Pins(1)).bowl(new Pins(0));

        assertThat(frame.symbol()).isEqualTo("1|-");
    }

    @Test
    void 스페어_거터_심볼() {
        Frame frame = NormalFrame.first();
        frame.bowl(new Pins(0)).bowl(new Pins(10));

        assertThat(frame.symbol()).isEqualTo("-|/");
    }
}