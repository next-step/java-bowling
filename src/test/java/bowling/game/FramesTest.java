package bowling.game;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class FramesTest {
    private static final Logger log = LoggerFactory.getLogger(FramesTest.class);

    @Test
    public void 투구할_수_있는_Frame이_없는지_확인() {
        // given
        Frames frames = new Frames();

        // when
        for (int i = 0; i < 11; i++) {
            assertThat(frames.isEnd()).isFalse();
            frames.pitch(PinScore.TEN);
        }

        // then
        assertThat(frames.isEnd()).isTrue();
    }

    @Test
    public void 투구할_수_있는_Frame이_없을때_투구하면_IllegalStateException() {
        // given
        Frames frames = new Frames();

        for (int i = 0; i < 11; i++) {
            frames.pitch(PinScore.TEN);
        }

        // when
        // then
        assertThatIllegalStateException().isThrownBy(() -> frames.pitch(PinScore.TEN));
    }

    @Test
    public void 투구전_toString() {
        // given
        // when
        Frames frames = new Frames();

        // then
        assertThat(frames.toString()).isEqualTo(
                "|      |      |      |      |      |      |      |      |      |      |");

        log.debug("frames\n{}", frames);
    }

    @Test
    public void 투구후_toString() {
        // given
        Frames frames = new Frames();

        // when
        frames.pitch(PinScore.THREE);
        frames.pitch(PinScore.ZERO);
        frames.pitch(PinScore.TEN);
        frames.pitch(PinScore.TEN);

        // then
        assertThat(frames.toString()).isEqualTo(
                "|  3|- |  X   |  X   |      |      |      |      |      |      |      |");

        log.debug("frames\n{}", frames);
    }

    @Test
    public void 올_스트라이크_toString() {
        // given
        Frames frames = new Frames();

        // when
        for (int i = 0; i < 11; i++) {
            frames.pitch(PinScore.TEN);
        }

        // then
        assertThat(frames.toString()).isEqualTo(
                "|  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|X |");

        log.debug("frames\n{}", frames);
    }
}
