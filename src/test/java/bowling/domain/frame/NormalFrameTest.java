package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @DisplayName("9번 프레임에서 getNextFrame 호출하면 FinalFrame 반환")
    @Test
    void getNextFrame_WhenCalledAtMaxFrameNo_ReturnsFinalFrame() {
        NormalFrame frame = new NormalFrame(9, 0);

        assertThat(frame.getNextFrame(10)).isInstanceOf(FinalFrame.class);
    }

    @ParameterizedTest(name = "1~8번 프레임에서 getNextFrame 호출하면 NormalFrame 반환")
    @ValueSource(ints = {1, 8})
    void getNextFrame_WhenCalledNonMaxFrameNo_ReturnsNormalFrame(int frameNo) {
        NormalFrame frame = new NormalFrame(frameNo, 0);

        assertThat(frame.getNextFrame(0)).isInstanceOf(NormalFrame.class);
    }

    @Test
    void canGetScore_WhenMiss_ReturnsTrueIfFrameIsFull() {
        NormalFrame frame = new NormalFrame(9, 0);

        assertThat(frame.canGetScore()).isFalse();
        frame.addPin(7);
        assertThat(frame.canGetScore()).isTrue();
    }

    @Test
    void canGetScore_WhenSpare_ReturnsTrueIfNextFrameIsPresent() {
        NormalFrame frame = new NormalFrame(9, 0);

        assertThat(frame.canGetScore()).isFalse();
        frame.addPin(10);
        frame.getNextFrame(0);
        assertThat(frame.canGetScore()).isTrue();
    }

    @Test
    void canGetScore_WhenStrike_ReturnsTrueIfNextFrameIsFull() {
        NormalFrame frame = new NormalFrame(9, 0);

        assertThat(frame.canGetScore()).isFalse();
        frame.addPin(10);
        frame.getNextFrame(0).addPin(1);
        assertThat(frame.canGetScore()).isTrue();
    }

    @Test
    void getScore_Miss() {
        NormalFrame frame = new NormalFrame(9, 5);

        frame.addPin(4);

        assertThat(frame.getScore()).isEqualTo(9);
    }

    @Test
    void getScore_Spare() {
        NormalFrame frame = new NormalFrame(9, 5);

        frame.addPin(5);
        frame.getNextFrame(5);

        assertThat(frame.getScore()).isEqualTo(15);
    }

    @Test
    void getScore_Strike() {
        NormalFrame frame = new NormalFrame(9, 10);

        frame.getNextFrame(5).addPin(5);

        assertThat(frame.getScore()).isEqualTo(20);
    }
}
