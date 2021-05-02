package bowling.domain.frame;

import bowling.domain.TestFixture;
import bowling.domain.pin.NormalPins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    @DisplayName("Frames를 initialize 메서드를 사용해 생성한다.")
    void initialize() {
        // given
        // when
        final Frames frames = Frames.create();

        // then
        assertThat(frames).isEqualTo(Frames.create());
    }

    @Test
    @DisplayName("isEnded를 사용하여 지정된 Frame이 종료되었는지 확인할 수 있다.")
    void isEnded() {
        // given
        final Frames frames = Frames.create();

        // when
        final boolean ended = frames.isEnded(new RoundNumber(1));

        // then
        assertThat(ended).isFalse();
    }

    @Test
    @DisplayName("knockDownPin을 사용하여 지정된 Frame의 Pin을 쓰러뜨릴 수 있다.")
    void knockDownPin() {
        // given
        final Frames frames = Frames.create();
        final RoundNumber roundNumber = new RoundNumber(1);

        // when
        frames.knockDownPin(roundNumber, TestFixture.STRIKE_PIN);

        // then
        final Frame expectedFrame = NormalFrame.of(roundNumber, NormalPins.of(TestFixture.STRIKE_PIN));
        assertThat(frames.getFrame(roundNumber)).isEqualTo(expectedFrame);
    }

    @Test
    @DisplayName("1 라운드는 마지막 프레임이 아니다.")
    void isFinalFrame() {
        // given
        final Frames frames = Frames.create();
        final RoundNumber firstRound = RoundNumber.firstRoundNumber();

        // when
        final Frame firstFrame = frames.getFrame(firstRound);

        // then
        assertThat(firstFrame.isFinalFrame()).isFalse();
    }

    @Test
    @DisplayName("10 라운드는 마지막 프레임이다.")
    void finalFrame() {
        // given
        final Frames frames = Frames.create();
        final RoundNumber finalRound = new RoundNumber(10);

        // when
        final Frame finalFrame = frames.getFrame(finalRound);

        // then
        assertThat(finalFrame.isFinalFrame()).isTrue();
    }
}
