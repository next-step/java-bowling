package bowling.domain.frame;

import bowling.domain.TestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    @DisplayName("Frames를 생성한다.")
    void create() {
        // given
        final Frames frames = Frames.initialize();

        // when
        // then
        assertThat(frames).isEqualTo(Frames.initialize());
    }

    @Test
    @DisplayName("지정된 Frame이 종료되었는지 확인한다.")
    void isEnded() {
        // given
        final Frames frames = Frames.initialize();

        // when
        final boolean ended = frames.isEnded(new RoundNumber(1));

        // then
        assertThat(ended).isFalse();
    }

    @Test
    @DisplayName("지정된 Frame의 Pin을 쓰러뜨린다.")
    void knockDownPin() {
        // given
        final Frames frames = Frames.initialize();
        final RoundNumber roundNumber = new RoundNumber(1);

        // when
        frames.knockDownPin(roundNumber, TestFixture.STRIKE_PIN);

        // then
        assertThat(frames.isEnded(roundNumber)).isTrue();
    }

    @Test
    @DisplayName("10 라운드는 마지막 프레임이다.")
    void finalFrame() {
        // given
        final Frames frames = Frames.initialize();
        final RoundNumber finalRound = new RoundNumber(10);

        // when
        final Frame finalFrame = frames.getFrame(finalRound);

        // then
        assertThat(finalFrame.isFinalFrame()).isTrue();
    }
}
