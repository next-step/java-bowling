package bowling.domain;

import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class FinalFrameTest {

    @Test
    void FinalFrame은_bowlCount_3_frameNumber_10으로_init_된다() {
        assertThat(FinalFrame.init()).isEqualTo(new FinalFrame(3, 10));
    }

    @Test
    void 프레임_번호_얻기() {
        assertThat(FinalFrame.init().frameNumber()).isEqualTo(10);
    }

    @Test
    void 다음_프레임_얻기() {
        assertThatThrownBy(() -> FinalFrame.init().nextFrame())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 게임진행_1회() {
        Frame frame = FinalFrame.init();
        frame.bowl(new Pin(5));

        assertThat(frame.isFinished()).isFalse();
        assertThat(frame.toString()).isEqualTo("5");
    }

    @Test
    void 게임진행_2회_strike_spare가_없는_경우() {
        Frame frame = FinalFrame.init();
        frame.bowl(new Pin(5));
        frame.bowl(new Pin(1));

        assertThat(frame.isFinished()).isTrue();
        assertThat(frame.toString()).isEqualTo("5" + Miss.MISS_MESSAGE + "1");
    }

    @Test
    void 게임진행_2회_strike() {
        Frame frame = FinalFrame.init();
        frame.bowl(new Pin(10));
        frame.bowl(new Pin(1));

        assertThat(frame.isFinished()).isFalse();
        assertThat(frame.toString()).isEqualTo("X" + FinalFrame.MESSAGE_DELIMITER + "1");
    }

    @Test
    void 게임진행_2회_spare() {
        Frame frame = FinalFrame.init();
        frame.bowl(new Pin(9));
        frame.bowl(new Pin(1));

        assertThat(frame.isFinished()).isFalse();
        assertThat(frame.toString()).isEqualTo("9" + Spare.SPARE_MESSAGE);
    }

    @Test
    void 게임진행_3회() {
        Frame frame = FinalFrame.init();
        frame.bowl(new Pin(9));
        frame.bowl(new Pin(1));
        frame.bowl(new Pin(0));

        assertThat(frame.isFinished()).isTrue();
        assertThat(frame.toString()).isEqualTo("9"
                + Spare.SPARE_MESSAGE
                + FinalFrame.MESSAGE_DELIMITER
                + Pin.GUTTER_MESSAGE);
    }

    @Test
    void 점수계산_계산불가_Spare가_있는_경우() {
        Frame frame = FinalFrame.init();
        assertThat(frame.calculateScore()).isEqualTo(Optional.empty());

        frame.bowl(new Pin(1));
        assertThat(frame.calculateScore()).isEqualTo(Optional.empty());

        frame.bowl(new Pin(9));
        assertThat(frame.calculateScore()).isEqualTo(Optional.empty());
    }

    @Test
    void 점수계산_계산불가_Strike가_있는_경우() {
        Frame frame = FinalFrame.init();
        assertThat(frame.calculateScore()).isEqualTo(Optional.empty());

        frame.bowl(new Pin(10));
        assertThat(frame.calculateScore()).isEqualTo(Optional.empty());

        frame.bowl(new Pin(0));
        assertThat(frame.calculateScore()).isEqualTo(Optional.empty());
    }

    @Test
    void 점수계산_계산가능_Strike나_Spare가_없는_경우() {
        Frame frame = FinalFrame.init();
        frame.bowl(new Pin(5));
        frame.bowl(new Pin(4));

        assertThat(frame.calculateScore()).isEqualTo(Optional.of(9));
    }

    @Test
    void 점수계산_계산가능_Strike가_있는_경우() {
        Frame frame = FinalFrame.init();
        frame.bowl(new Pin(10));
        frame.bowl(new Pin(5));
        frame.bowl(new Pin(4));

        assertThat(frame.calculateScore()).isEqualTo(Optional.of(19));
    }

    @Test
    void 점수계산_계산가능_Spare가_있는_경우() {
        Frame frame = FinalFrame.init();
        frame.bowl(new Pin(5));
        frame.bowl(new Pin(5));
        frame.bowl(new Pin(4));

        assertThat(frame.calculateScore()).isEqualTo(Optional.of(14));
    }

    @Test
    void NormalFrame에서_위임된_점수계산_Strike() {
        Frame frame = FinalFrame.init();
        assertThat(frame.calculateLastFrameScore(Score.ofStrike())).isEqualTo(Optional.empty());

        frame.bowl(new Pin(5));
        assertThat(frame.calculateLastFrameScore(Score.ofStrike())).isEqualTo(Optional.empty());

        frame.bowl(new Pin(5));
        assertThat(frame.calculateLastFrameScore(Score.ofStrike())).isEqualTo(Optional.of(20));
    }

    @Test
    void NormalFrame에서_위임된_점수계산_Spare() {
        Frame frame = FinalFrame.init();
        assertThat(frame.calculateLastFrameScore(Score.ofSpare())).isEqualTo(Optional.empty());

        frame.bowl(new Pin(5));
        assertThat(frame.calculateLastFrameScore(Score.ofSpare())).isEqualTo(Optional.of(15));
    }
}