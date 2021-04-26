package bowling.domain.frame;

import bowling.domain.pin.NormalPins;
import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.exception.IllegalNormalFrameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {

    @Test
    @DisplayName("RoundNumber와 FrameScore를 가지고 생성한다.")
    void create() {
        // given
        final RoundNumber roundNumber = new RoundNumber(1);
        final NormalFrameScore frameScore = new NormalFrameScore();

        // when
        final Frame frame = NormalFrame.of(roundNumber, frameScore);

        // then
        assertThat(frame).isEqualTo(NormalFrame.of(roundNumber, frameScore));
    }

    @Test
    @DisplayName("첫 프레임을 생성한다.")
    void createFirstFrame() {
        // given
        final int firstRoundNumber = 1;
        final RoundNumber roundNumber = new RoundNumber(firstRoundNumber);
        final NormalFrameScore frameScore = new NormalFrameScore();

        // when
        final Frame frame = NormalFrame.createFirstFrame();

        // then
        assertThat(frame).isEqualTo(NormalFrame.of(roundNumber, frameScore));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    @DisplayName("다음 프레임을 생성한다.")
    void nextFrame(final int roundNumberSource) {
        // given
        final RoundNumber roundNumber = new RoundNumber(roundNumberSource);
        final RoundNumber nextRoundNumber = new RoundNumber(roundNumberSource + 1);
        final NormalFrameScore frameScore = new NormalFrameScore();
        final Frame frame = NormalFrame.of(roundNumber, frameScore);

        // when
        final Frame nextFrame = frame.createNextFrame();

        // then
        assertThat(nextFrame).isEqualTo(NormalFrame.of(nextRoundNumber, frameScore));
    }

    @Test
    @DisplayName("Normal Frame은 10라운드가 될 수 없다.")
    void NormalFrameCannotBeTenRound() {
        assertThatThrownBy(() -> NormalFrame.of(new RoundNumber(10), new NormalFrameScore()))
                .isInstanceOf(IllegalNormalFrameException.class)
                .hasMessage(IllegalNormalFrameException.ILLEGAL_NORMAL_FRAME_ROUND);
    }

    @Test
    @DisplayName("9프레임의 다음 프레임은 FinalFrame이다.")
    void NineFrameNextFrame() {
        // given
        final RoundNumber nineRoundNumber = new RoundNumber(9);
        final NormalFrameScore frameScore = new NormalFrameScore();
        final Frame nineFrame = NormalFrame.of(nineRoundNumber, frameScore);

        // when
        final Frame nextFrame = nineFrame.createNextFrame();

        // then
        assertThat(nextFrame).isEqualTo(FinalFrame.from(frameScore));
    }

    @Test
    @DisplayName("NormalFrame은 FinalFrame이 아니다.")
    void isFinalFrame() {
        assertThat(NormalFrame.createFirstFrame().isFinalFrame()).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"10,0,X", "9,1,9|/", "3,4,3|4", "0,0,-|-"})
    @DisplayName("NormalFrame은 조건별로 출력이 다르다.")
    void status(int firstPinCount, int secondPinCount, String expected) {
        // given
        final Pin firstPin = new Pin(firstPinCount);
        final Pin secondPin = new Pin(secondPinCount);
        final Pins pins = new NormalPins(firstPin, secondPin);
        final Frame frame = NormalFrame.of(RoundNumber.firstRoundNumber(), new NormalFrameScore(pins));

        // when
        final String status = frame.status();

        // then
        assertThat(status).isEqualTo(expected);
    }
}
