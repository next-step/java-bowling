package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.domain.state.finish.Strike;
import bowling.domain.state.running.FirstBowl;
import bowling.domain.state.running.Ready;
import bowling.exception.frame.NextFrameNotFoundException;
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

    @Test
    @DisplayName("bowling을 했을 때 완료되면 다음 frame을 반환할 수 있다.")
    void bowlingFinishedTest() {

        // given
        Pin pin = Pin.of(10);
        Frame frame = NormalFrame.from(2, null, new Ready());

        Frame expected = NormalFrame.from(3, null, new Ready());

        // when
        Frame result = frame.bowling(pin);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("현재 state가 바로 계산가능한 score를 반환할 수 있는 경우 반환한다.")
    void scoreReturnTest() {

        // given
        Pin pin = Pin.of(1);
        NormalFrame frame = (NormalFrame) NormalFrame.from(1, null, new Miss(pin, pin));

        Score expected = Score.from(2, 0);

        // when
        Score result = frame.score();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("beforeScore를 받아 현재 frame의 state score를 더해 반환할 수 있다.")
    void calculateAdditionalScoreTest() {

        // given
        Pin first = Pin.of(3);
        Pin second = Pin.of(7);
        NormalFrame nextFrame = (NormalFrame) NormalFrame.from(2, null, new Strike(Pin.of(10)));
        NormalFrame frame = (NormalFrame) NormalFrame.from(1, nextFrame, new Spare(first, second));

        Score expected = Score.from(20, 0);

        // when
        Score result = frame.score();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("nextFrame이 없는데 다음 frame state score를 더해 계산하려고하면 excpetion이 발생해야한다.")
    void scoreExceptionByNullNextFrameTest() {

        // given
        NormalFrame frame = (NormalFrame) NormalFrame.from(1, null, new Strike(Pin.of(10)));

        // when & then
        assertThatExceptionOfType(NextFrameNotFoundException.class)
            .isThrownBy(() -> frame.score())
            .withMessageMatching("next frame가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("calculateAdditionalScore 반환 시 nextFrame이 없는 경우 exception이 발생해야 한다.")
    void calculateAdditionalScoreExceptionByNullNextFrameTest() {

        // given
        NormalFrame frame = (NormalFrame) NormalFrame.from(2, null, new Strike(Pin.of(10)));
        Score beforeScore = Score.from(10, 2);

        // when & then
        assertThatExceptionOfType(NextFrameNotFoundException.class)
            .isThrownBy(() -> frame.calculateAdditionalScore(beforeScore))
            .withMessageMatching("next frame가 존재하지 않습니다.");
    }

}