package bowling.domain;

import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class FinalFrameTest {

    @Test
    void 프레임_번호_얻기() {
        assertThat(new FinalFrame().frameNumber()).isEqualTo(10);
    }

    @Test
    void 다음_프레임_얻기() {
        assertThatThrownBy(() -> new FinalFrame().nextFrame())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 게임진행_1회() {
        Frame frame = new FinalFrame();
        frame.bowl(new Pin(5));

        assertThat(frame.isFinished()).isFalse();
        assertThat(frame.toString()).isEqualTo("5");
    }

    @Test
    void 게임진행_2회_strike_spare가_없는_경우() {
        Frame frame = new FinalFrame();
        frame.bowl(new Pin(5));
        frame.bowl(new Pin(1));

        assertThat(frame.isFinished()).isTrue();
        assertThat(frame.toString()).isEqualTo("5" + Miss.MISS_MESSAGE + "1");
    }

    @Test
    void 게임진행_2회_strike() {
        Frame frame = new FinalFrame();
        frame.bowl(new Pin(10));
        frame.bowl(new Pin(1));

        assertThat(frame.isFinished()).isFalse();
        assertThat(frame.toString()).isEqualTo("X" + FinalFrame.FINALFRAME_MESSAGE_DELIMITER + "1");
    }

    @Test
    void 게임진행_2회_spare() {
        Frame frame = new FinalFrame();
        frame.bowl(new Pin(9));
        frame.bowl(new Pin(1));

        assertThat(frame.isFinished()).isFalse();
        assertThat(frame.toString()).isEqualTo("9" + Spare.SPARE_MESSAGE);
    }

    @Test
    void 게임진행_3회() {
        Frame frame = new FinalFrame();
        frame.bowl(new Pin(9));
        frame.bowl(new Pin(1));
        frame.bowl(new Pin(0));

        assertThat(frame.isFinished()).isTrue();
        assertThat(frame.toString()).isEqualTo("9"
                + Spare.SPARE_MESSAGE
                + FinalFrame.FINALFRAME_MESSAGE_DELIMITER
                + Pin.GUTTER_MESSAGE);
    }
}