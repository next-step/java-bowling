package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import bowling.domain.score.Pin;
import bowling.domain.state.running.FirstBowl;
import bowling.domain.state.running.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    @DisplayName("첫번째 frame을 생성할 수 있다.")
    void createFirstFrameTest() {

        // given
        Frame expected = NormalFrame.from(1, null, new Ready());

        // when
        Frame result = NormalFrame.createFirstFrame();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("다음 frame을 생성할 수 있다.")
    void createNextNormalFrameTest() {

        // given
        Frame frame = NormalFrame.from(2, null, new Ready());

        Frame expected = NormalFrame.from(3, null, new Ready());

        // when
        Frame result = frame.createNextFrame();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("다음 프레임이 마지막 프레임이라면 마지막 프레임을 생성할 수 있다.")
    void createNextFinalFrameTest() {

        // given
        Frame frame = NormalFrame.from(9, null, new Ready());

        Frame expected = FinalFrame.from(10, new Ready());

        // when
        Frame result = frame.createNextFrame();

        // then
        assertAll(
            () -> assertThat(result).isEqualTo(expected),
            () -> assertThat(result).isInstanceOf(FinalFrame.class)
        );
    }

    @Test
    @DisplayName("bowling을 했을 때 현재 state를 변화할 수 있다.")
    void bowlingTest() {

        // given
        Pin pin = Pin.of(3);
        Frame frame = NormalFrame.from(2, null, new Ready());

        Frame expected = NormalFrame.from(2, null, new FirstBowl(pin));

        // when
        Frame result = frame.bowling(pin);

        // then
        assertThat(result).isEqualTo(expected);
    }

}