package bowling.game;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class FinalFrameTest {
    private static final Logger log = LoggerFactory.getLogger(FinalFrameTest.class);

    @Test
    public void 프레임이_끝났는지_확인_스페어() {
        // given
        // when
        FinalFrame finalFrame = new FinalFrame(PinScore.of(1));
        finalFrame.pitch(PinScore.of(9));

        // then
        assertThat(finalFrame.isEnd()).isFalse();
    }

    @Test
    public void 프레임이_끝났는지_확인_미스() {
        // given
        // when
        FinalFrame finalFrame = new FinalFrame(PinScore.of(1));
        finalFrame.pitch(PinScore.of(1));

        // then
        assertThat(finalFrame.isEnd()).isTrue();
    }

    @Test
    public void 프레임이_끝났는지_확인_스트라이크() {
        // given
        // when
        FinalFrame finalFrame = new FinalFrame(PinScore.of(10));
        finalFrame.pitch(PinScore.of(10));

        // then
        assertThat(finalFrame.isEnd()).isTrue();
    }

    @Test
    public void 프레임이_끝난_후_다음_투구를_추가하면_IllegalStateException() {
        // given
        Frame frame = new FinalFrame(PinScore.of(0));
        frame.pitch(PinScore.of(0));

        // when
        // then
        assertThatIllegalStateException().isThrownBy(() -> frame.pitch(PinScore.of(2)));
    }

    @Test
    public void 처음_두개의_투구의_합이_10을_넘기면_IllegalStateException() {
        // given
        Frame frame = new FinalFrame(PinScore.of(4));

        // when
        // then
        assertThatIllegalStateException().isThrownBy(() -> frame.pitch(PinScore.of(8)));
    }

    @Test
    public void 첫투구_후_toString() {
        // given
        // when
        Frame frame = new FinalFrame(PinScore.of(4));

        // then
        assertThat(frame.toString()).isEqualTo("4");

        log.debug("FinalFrame\n{}", frame);
    }


    @Test
    public void 첫투구_후_toString_스트라이크() {
        // given
        // when
        Frame frame = new FinalFrame(PinScore.of(10));

        // then
        assertThat(frame.toString()).isEqualTo("X");

        log.debug("FinalFrame\n{}", frame);
    }

    @Test
    public void 두번째_투구_후_toString_미스() {
        // given
        // when
        Frame frame = new FinalFrame(PinScore.of(4));
        frame.pitch(PinScore.of(4));

        // then
        assertThat(frame.toString()).isEqualTo("4|4");

        log.debug("FinalFrame\n{}", frame);
    }

    @Test
    public void 두번째_투구_후_toString_스페어() {
        // given
        // when
        Frame frame = new FinalFrame(PinScore.of(4));
        frame.pitch(PinScore.of(6));

        // then
        assertThat(frame.toString()).isEqualTo("4|/");

        log.debug("FinalFrame\n{}", frame);
    }

    @Test
    public void 세번째_투구_후_toString() {
        // given
        // when
        Frame frame = new FinalFrame(PinScore.of(4));
        frame.pitch(PinScore.of(6));
        frame.pitch(PinScore.of(10));

        // then
        assertThat(frame.toString()).isEqualTo("4|/|X");

        log.debug("FinalFrame\n{}", frame);
    }
}
