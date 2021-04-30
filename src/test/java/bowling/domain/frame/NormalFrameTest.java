package bowling.domain.frame;

import bowling.domain.TestFixture;
import bowling.domain.pin.Pins;
import bowling.exception.IllegalNormalFrameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {

    @Test
    @DisplayName("RoundNumber와 FrameScore를 가지고 생성한다.")
    void create() {
        // given
        final RoundNumber roundNumber = new RoundNumber(1);

        // when
        final Frame frame = NormalFrame.of(roundNumber, Pins.create());

        // then
        assertThat(frame).isEqualTo(NormalFrame.of(roundNumber, Pins.create()));
    }

    @Test
    @DisplayName("첫 프레임을 생성한다.")
    void createFirstFrame() {
        // given
        final int firstRoundNumber = 1;
        final RoundNumber roundNumber = new RoundNumber(firstRoundNumber);

        // when
        final Frame frame = NormalFrame.createFirstFrame();

        // then
        assertThat(frame).isEqualTo(NormalFrame.of(roundNumber, Pins.create()));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    @DisplayName("다음 프레임을 생성한다.")
    void nextFrame(final int roundNumberSource) {
        // given
        final RoundNumber roundNumber = new RoundNumber(roundNumberSource);
        final RoundNumber nextRoundNumber = new RoundNumber(roundNumberSource + 1);
        final Frame frame = NormalFrame.of(roundNumber, Pins.create());

        // when
        final Frame nextFrame = frame.createNextFrame();

        // then
        assertThat(nextFrame).isEqualTo(NormalFrame.of(nextRoundNumber, Pins.create()));
    }

    @Test
    @DisplayName("Normal Frame은 10라운드가 될 수 없다.")
    void NormalFrameCannotBeTenRound() {
        assertThatThrownBy(() -> NormalFrame.of(new RoundNumber(10), Pins.create()))
                .isInstanceOf(IllegalNormalFrameException.class)
                .hasMessage(IllegalNormalFrameException.ILLEGAL_NORMAL_FRAME_ROUND);
    }

    @Test
    @DisplayName("9프레임의 다음 프레임은 FinalFrame이다.")
    void NineFrameNextFrame() {
        // given
        final RoundNumber nineRoundNumber = new RoundNumber(9);
        final Frame nineFrame = NormalFrame.of(nineRoundNumber, Pins.create());

        // when
        final Frame nextFrame = nineFrame.createNextFrame();

        // then
        assertThat(nextFrame).isEqualTo(FinalFrame.from(Pins.create()));
    }

    @Test
    @DisplayName("Frame이 종료되었는지 확인한다.")
    void isEnded() {
        // given
        final Frame firstFrame = NormalFrame.createFirstFrame();

        // when
        final boolean ended = firstFrame.isEnded();

        // then
        assertThat(ended).isFalse();
    }

    @Test
    @DisplayName("프레임에서 투구를 한다. 스트라이크 투구를 한 후 프레임이 종료된다.")
    void knockDownPin() {
        // given
        final Frame firstFrame = NormalFrame.createFirstFrame();

        // when
        firstFrame.knockDownPin(TestFixture.STRIKE_PIN);

        // then
        assertThat(firstFrame.isEnded()).isTrue();
    }
}
