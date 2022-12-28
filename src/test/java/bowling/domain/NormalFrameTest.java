package bowling.domain;

import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NormalFrameTest {

    @Test
    void 생성_framenumber_1번_미만() {
        assertThatThrownBy(() -> new NormalFrame(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 생성_framenumber_9번_초과() {
        assertThatThrownBy(() -> new NormalFrame(10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void firstPin() {
        Frame frame = new NormalFrame(1);
        frame.bowl(new Pin(5));

        assertThat(frame.isFinished()).isFalse();
        assertThat(frame.toString()).isEqualTo("5");
    }

    @Test
    void strike() {
        Frame frame = new NormalFrame(1);
        frame.bowl(new Pin(10));

        assertThat(frame.isFinished()).isTrue();
        assertThat(frame.toString()).isEqualTo(Strike.STRIKE_MESSAGE);
    }

    @Test
    void spare() {
        Frame frame = new NormalFrame(1);
        frame.bowl(new Pin(4));
        frame.bowl(new Pin(6));

        assertThat(frame.isFinished()).isTrue();
        assertThat(frame.toString()).isEqualTo("4" + Spare.SPARE_MESSAGE);
    }

    @Test
    void miss_non_gutter() {
        Frame frame = new NormalFrame(1);
        frame.bowl(new Pin(4));
        frame.bowl(new Pin(5));

        assertThat(frame.isFinished()).isTrue();
        assertThat(frame.toString()).isEqualTo("4" + Miss.MISS_MESSAGE + "5");
    }

    @Test
    void miss_gutter() {
        Frame frame = new NormalFrame(1);
        frame.bowl(new Pin(4));
        frame.bowl(new Pin(0));

        assertThat(frame.isFinished()).isTrue();
        assertThat(frame.toString()).isEqualTo("4" + Miss.MISS_MESSAGE + Pin.GUTTER_MESSAGE);
    }

    @Test
    void 다음_프레임_얻기_현재_프레임이_종료되지않은_경우() {
        Frame frame = new NormalFrame(1);
        frame.bowl(new Pin(5));

        assertThat(frame.nextFrame().frameNumber()).isEqualTo(1);
        assertThat(frame.nextFrame()).isSameAs(frame);
    }

    @Test
    void 다음_프레임_얻기_현재_프레임이_종료된_경우() {
        Frame frame = new NormalFrame(1);
        frame.bowl(new Pin(10));

        assertThat(frame.nextFrame().frameNumber()).isEqualTo(2);
    }

}
