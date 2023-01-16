package bowling.domain;

import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NormalFrameTest {

    @Test
    void NormalFrame은_maxBowlCount_1로_init_된다() {
        assertThat(NormalFrame.init(1)).isEqualTo(new NormalFrame(1, 1));
    }

    @Test
    void 생성_framenumber_1번_미만() {
        assertThatThrownBy(() -> NormalFrame.init(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 생성_framenumber_9번_초과() {
        assertThatThrownBy(() -> NormalFrame.init(10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void firstPin() {
        Frame frame = NormalFrame.init(1);
        frame.bowl(new Pin(5));

        assertThat(frame.isFinished()).isFalse();
        assertThat(frame.toString()).isEqualTo("5");
    }

    @Test
    void strike() {
        Frame frame = NormalFrame.init(1);
        frame.bowl(new Pin(10));

        assertThat(frame.isFinished()).isTrue();
        assertThat(frame.toString()).isEqualTo(Strike.STRIKE_MESSAGE);
    }

    @Test
    void spare() {
        Frame frame = NormalFrame.init(1);
        frame.bowl(new Pin(4));
        frame.bowl(new Pin(6));

        assertThat(frame.isFinished()).isTrue();
        assertThat(frame.toString()).isEqualTo("4" + Spare.SPARE_MESSAGE);
    }

    @Test
    void miss_non_gutter() {
        Frame frame = NormalFrame.init(1);
        frame.bowl(new Pin(4));
        frame.bowl(new Pin(5));

        assertThat(frame.isFinished()).isTrue();
        assertThat(frame.toString()).isEqualTo("4" + Miss.MISS_MESSAGE + "5");
    }

    @Test
    void miss_gutter() {
        Frame frame = NormalFrame.init(1);
        frame.bowl(new Pin(4));
        frame.bowl(new Pin(0));

        assertThat(frame.isFinished()).isTrue();
        assertThat(frame.toString()).isEqualTo("4" + Miss.MISS_MESSAGE + Pin.GUTTER_MESSAGE);
    }

    @Test
    void 다음_프레임_얻기_현재_프레임이_종료되지않은_경우() {
        Frame frame = NormalFrame.init(1);
        frame.bowl(new Pin(5));

        assertThat(frame.nextFrame().frameNumber()).isEqualTo(1);
        assertThat(frame.nextFrame()).isSameAs(frame);
    }

    @Test
    void 다음_프레임_얻기_현재_프레임이_종료된_경우() {
        Frame frame = NormalFrame.init(1);
        frame.bowl(new Pin(10));

        assertThat(frame.nextFrame().frameNumber()).isEqualTo(2);
    }

    @Test
    void 다음_프레임_얻기_NormalFrame() {
        Frame frame = NormalFrame.init(8);
        frame.bowl(new Pin(10));

        assertThat(frame.nextFrame()).isInstanceOf(NormalFrame.class);
        assertThat(frame.nextFrame().frameNumber()).isEqualTo(9);
    }

    @Test
    void 다음_프레임_얻기_FinalFrame() {
        Frame frame = NormalFrame.init(9);
        frame.bowl(new Pin(10));

        assertThat(frame.nextFrame()).isInstanceOf(FinalFrame.class);
        assertThat(frame.nextFrame().frameNumber()).isEqualTo(10);
    }

    @Test
    void 점수계산_계산불가_Running() {
        Frame frame = NormalFrame.init(1);
        assertThat(frame.calculateScore()).isEqualTo(Optional.empty());

        frame.bowl(new Pin(5));
        assertThat(frame.calculateScore()).isEqualTo(Optional.empty());
    }

    @Test
    void 점수계산_계산불가_Strike() {
        Frame frame = NormalFrame.init(1);
        frame.bowl(new Pin(10));

        assertThat(frame.calculateScore()).isEqualTo(Optional.empty());
    }

    @Test
    void 점수계산_계산불가_Spare() {
        Frame frame = NormalFrame.init(1);
        frame.bowl(new Pin(5));
        frame.bowl(new Pin(5));

        assertThat(frame.calculateScore()).isEqualTo(Optional.empty());
    }

    @Test
    void 점수계산_계산가능_Miss() {
        Frame frame = NormalFrame.init(1);
        frame.bowl(new Pin(5));
        frame.bowl(new Pin(4));

        assertThat(frame.calculateScore()).isEqualTo(Optional.of(9));
    }

    @Test
    void 점수계산_계산가능_Spare() {
        Frame frame = NormalFrame.init(1);
        frame.bowl(new Pin(5));

        assertThat(frame.calculateLastFrameScore(Score.ofSpare())).isEqualTo(Optional.of(15));
    }

    @Test
    void 점수계산_계산가능_Strike() {
        Frame frame = NormalFrame.init(1);
        frame.bowl(new Pin(5));
        assertThat(frame.calculateLastFrameScore(Score.ofStrike())).isEqualTo(Optional.empty());

        frame.bowl(new Pin(3));
        assertThat(frame.calculateLastFrameScore(Score.ofStrike())).isEqualTo(Optional.of(18));
    }
}
