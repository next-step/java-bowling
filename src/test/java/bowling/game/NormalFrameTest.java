package bowling.game;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class NormalFrameTest {
    private static final Logger log = LoggerFactory.getLogger(NormalFrameTest.class);

    @Test
    public void 프레임이_끝났는지_확인() {
        // given
        // when
        Frame frame = new NormalFrame(PinScore.of(1));

        // then
        assertThat(frame.isEnd()).isFalse();
    }

    @Test
    public void 프레임이_끝났는지_확인_스트라이크() {
        // given
        // when
        Frame frame = new NormalFrame(PinScore.of(10));

        // then
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    public void 프레임이_끝난_후_다음_투구를_추가하면_IllegalStateException() {
        // given
        Frame frame = new NormalFrame(PinScore.of(10));

        // when
        // then
        assertThatIllegalStateException().isThrownBy(() -> frame.pitch(PinScore.of(2)));
    }

    @Test
    public void 두개의_투구의_합이_10을_넘기면_IllegalStateException() {
        // given
        Frame frame = new NormalFrame(PinScore.of(4));

        // when
        // then
        assertThatIllegalStateException().isThrownBy(() -> frame.pitch(PinScore.of(8)));
    }

    @Test
    public void 미스인지_확인() {
        // given
        // when
        NormalFrame frame = new NormalFrame(PinScore.of(0));
        frame.pitch(PinScore.of(3));

        // then
        assertThat(frame.isMiss()).isTrue();
    }

    @Test
    public void 스트라이크인지_확인() {
        // given
        // when
        NormalFrame frame = new NormalFrame(PinScore.of(10));

        // then
        assertThat(frame.isStrike()).isTrue();
    }

    @Test
    public void 스페어인지_확인() {
        // given
        // when
        NormalFrame frame = new NormalFrame(PinScore.of(3));
        frame.pitch(PinScore.of(7));

        // then
        assertThat(frame.isSpare()).isTrue();
    }

    @Test
    public void 스페어인지_확인_스트라이크() {
        // given
        NormalFrame frame = new NormalFrame(PinScore.of(10));

        // when
        // then
        assertThat(frame.isSpare()).isFalse();
    }

    @Test
    public void 첫구_거터_toString() {
        // given
        int first = 0;

        // when
        Frame frame = new NormalFrame(PinScore.of(first));

        // then
        assertThat(frame.toString()).isEqualTo("-");

        log.debug("frame\n{}", frame);
    }

    @Test
    public void 첫구_한개_이상_toString() {
        // given
        int first = 4;

        // when
        Frame frame = new NormalFrame(PinScore.of(first));

        // then
        assertThat(frame.toString()).isEqualTo("4");

        log.debug("frame\n{}", frame);
    }

    @Test
    public void 첫구_스트라이크_toString() {
        // given
        int first = 10;

        // when
        Frame frame = new NormalFrame(PinScore.of(first));

        // then
        assertThat(frame.toString()).isEqualTo("X");

        log.debug("frame\n{}", frame);
    }

    @Test
    public void 두번째구_거터_toString() {
        // given
        Frame frame = new NormalFrame(PinScore.of(0));

        // when
        frame.pitch(PinScore.of(0));

        // then
        assertThat(frame.toString()).isEqualTo("-|-");

        log.debug("frame\n{}", frame);
    }

    @Test
    public void 두번째구_스페어처리_toString() {
        // given
        Frame frame = new NormalFrame(PinScore.of(2));

        // when
        frame.pitch(PinScore.of(8));

        // then
        assertThat(frame.toString()).isEqualTo("2|/");

        log.debug("frame\n{}", frame);
    }

    @Test
    public void 두번째구_미스_toString() {
        // given
        Frame frame = new NormalFrame(PinScore.of(0));

        // when
        frame.pitch(PinScore.of(2));

        // then
        assertThat(frame.toString()).isEqualTo("-|2");

        log.debug("frame\n{}", frame);
    }
}
