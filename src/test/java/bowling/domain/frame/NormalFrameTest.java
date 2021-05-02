package bowling.domain.frame;

import bowling.domain.TestFixture;
import bowling.domain.pin.FinalPins;
import bowling.domain.pin.NormalPins;
import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.exception.FramePinCountException;
import bowling.exception.IllegalNormalFrameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {

    @Test
    @DisplayName("NormalFrame은 RoundNumber와 NormalPins를 가지고 생성한다.")
    void create() {
        // given
        final RoundNumber roundNumber = new RoundNumber(1);

        // when
        final Frame frame = NormalFrame.of(roundNumber, NormalPins.create());

        // then
        assertThat(frame).isEqualTo(NormalFrame.of(roundNumber, NormalPins.create()));
    }

    @Test
    @DisplayName("createFirstFrame을 사용하면 첫 프레임을 생성한다.")
    void createFirstFrame() {
        // given
        final int firstRoundNumber = 1;
        final RoundNumber roundNumber = new RoundNumber(firstRoundNumber);

        // when
        final Frame frame = NormalFrame.createFirstFrame();

        // then
        assertThat(frame).isEqualTo(NormalFrame.of(roundNumber, NormalPins.create()));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    @DisplayName("NormalFrame은 다음 NormalFrame을 생성할 수 있다.")
    void nextFrame(final int roundNumberSource) {
        // given
        final RoundNumber roundNumber = new RoundNumber(roundNumberSource);
        final RoundNumber nextRoundNumber = new RoundNumber(roundNumberSource + 1);
        final Frame frame = NormalFrame.of(roundNumber, NormalPins.create());

        // when
        frame.createNextFrame();

        // then
        assertThat(frame.nextFrame()).isEqualTo(NormalFrame.of(nextRoundNumber, NormalPins.create()));
    }

    @Test
    @DisplayName("9프레임의 다음 프레임은 FinalFrame이다.")
    void NineFrameNextFrame() {
        // given
        final RoundNumber nineRoundNumber = new RoundNumber(9);
        final Frame nineFrame = NormalFrame.of(nineRoundNumber, NormalPins.create());

        // when
        nineFrame.createNextFrame();

        // then
        assertThat(nineFrame.nextFrame()).isEqualTo(FinalFrame.from(FinalPins.create()));
    }

    @Test
    @DisplayName("Normal Frame은 10라운드가 될 수 없다.")
    void NormalFrameCannotBeTenRound() {
        assertThatThrownBy(() -> NormalFrame.of(new RoundNumber(10), NormalPins.create()))
                .isInstanceOf(IllegalNormalFrameException.class)
                .hasMessage(IllegalNormalFrameException.ILLEGAL_NORMAL_FRAME_ROUND);
    }

    @Test
    @DisplayName("isEnded를 사용해 NormalFrame이 종료되었는지 확인할 수 있다.")
    void isEnded() {
        // given
        final Frame firstFrame = NormalFrame.createFirstFrame();

        // when
        final boolean ended = firstFrame.isEnded();

        // then
        assertThat(ended).isFalse();
    }

    @Test
    @DisplayName("knockDownPin을 사용해 투구를 한다. 스트라이크 투구를 한 후엔 프레임이 종료된다.")
    void knockDownPin() {
        // given
        final Frame firstFrame = NormalFrame.createFirstFrame();

        // when
        firstFrame.knockDownPin(TestFixture.STRIKE_PIN);

        // then
        assertThat(firstFrame.isEnded()).isTrue();
    }

    @Test
    @DisplayName("NormalFrame은 총합이 10개를 넘어가는 Pin이 입력되면 예외가 반환된다.")
    void validateNormalFrame() {
        // given
        final Frame frame = NormalFrame.createFirstFrame();
        frame.knockDownPin(new Pin(9));

        // when
        // then
        assertThatThrownBy(() -> frame.knockDownPin(new Pin(3)))
                .isInstanceOf(FramePinCountException.class)
                .hasMessage(FramePinCountException.FRAME_PIN_COUNT_EXCEEDED);
    }

    @Test
    @DisplayName("처음 생성된 NormalFrame의 점수는 산정할 수 없다.")
    void noScore() {
        // given
        final Frame frame = NormalFrame.createFirstFrame();

        // when
        final Score score = frame.score();

        // then
        assertThat(score.canCalculate()).isFalse();
    }

    @Test
    @DisplayName("일반 구를 1회 투구한 NormalFrame의 점수는 산정할 수 없다.")
    void noScoreOneThrow() {
        // given
        final Frame frame = NormalFrame.createFirstFrame();
        frame.knockDownPin(new Pin(5));

        // when
        final Score score = frame.score();

        // then
        assertThat(score.canCalculate()).isFalse();
    }

    @Test
    @DisplayName("첫 투구와 두번째 투구의 합이 점수가 된다.")
    void scoreOfFrame() {
        // given
        final Frame frame = NormalFrame.createFirstFrame();
        frame.knockDownPin(new Pin(5));
        frame.knockDownPin(new Pin(2));

        // when
        final Score score = frame.score();

        // then
        assertThat(score.calculate()).isEqualTo(7);
    }

    @Test
    @DisplayName("Strike인 NormalFrame이 주어지면 투구 2회를 실행하기 전까지 점수를 산정할 수 없다.")
    void strikeNoScore() {
        // given
        final Frame firstFrame = NormalFrame.createFirstFrame();
        firstFrame.knockDownPin(TestFixture.STRIKE_PIN);

        // when
        final Score score = firstFrame.score();

        // then
        assertThat(score.canCalculate()).isFalse();
    }

    @Test
    @DisplayName("Strike인 NormalFrame이 주어지고, 해당 투구와 다음 두 투구의 합이 점수가 된다.")
    void strikeScore() {
        // given
        final Frame firstFrame = NormalFrame.createFirstFrame();
        firstFrame.createNextFrame();
        final Frame secondFrame = firstFrame.nextFrame();

        firstFrame.knockDownPin(TestFixture.STRIKE_PIN);
        secondFrame.knockDownPin(new Pin(5));
        secondFrame.knockDownPin(new Pin(2));

        // when
        final Score score = firstFrame.score();

        // then
        assertThat(score.calculate()).isEqualTo(17);
    }

    @Test
    @DisplayName("Strike인 NormalFrame이 주어지고, 다음 두 투구가 모두 스트라이크인 경우 30점이 된다.")
    void tripleStrikeScore() {
        // given
        final Frame firstFrame = NormalFrame.createFirstFrame();
        firstFrame.createNextFrame();
        final Frame secondFrame = firstFrame.nextFrame();
        secondFrame.createNextFrame();
        final Frame thirdFrame = secondFrame.nextFrame();

        firstFrame.knockDownPin(TestFixture.STRIKE_PIN);
        secondFrame.knockDownPin(TestFixture.STRIKE_PIN);
        thirdFrame.knockDownPin(TestFixture.STRIKE_PIN);

        // when
        final Score score = firstFrame.score();

        // then
        assertThat(score.calculate()).isEqualTo(30);
    }

    @Test
    @DisplayName("Spare NormalFrame이 주어지면 다음 투구 1회를 실행하기 전까지 점수를 산정할 수 없다.")
    void spareNoScore() {
        // given
        final Frame firstFrame = NormalFrame.createFirstFrame();
        firstFrame.knockDownPin(new Pin(8));
        firstFrame.knockDownPin(new Pin(2));

        // when
        final Score score = firstFrame.score();

        // then
        assertThat(score.canCalculate()).isFalse();
    }

    @Test
    @DisplayName("Spare NormalFrame이 주어지면 해당 투구와 다음 투구의 합이 점수가 된다.")
    void spare() {
        // given
        final Frame firstFrame = NormalFrame.createFirstFrame();
        firstFrame.createNextFrame();
        final Frame secondFrame = firstFrame.nextFrame();

        firstFrame.knockDownPin(new Pin(8));
        firstFrame.knockDownPin(new Pin(2));
        secondFrame.knockDownPin(new Pin(5));

        // when
        final Score score = firstFrame.score();

        // then
        assertThat(score.calculate()).isEqualTo(15);
    }
}
