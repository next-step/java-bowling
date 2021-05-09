package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LastFrameTest {
    @Test
    public void init() {
        final Frame frame = LastFrame.init();
        assertThat(frame.playing()).isTrue();
    }

    @Test
    public void invalid_투구_3개_초과() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            final Frame frame = LastFrame.init();

            frame.play(KnockedPins.from(10))
                    .play(KnockedPins.from(10))
                    .play(KnockedPins.from(10))
                    .play(KnockedPins.from(10));
        });
    }

    @Test
    @DisplayName("스트라이커도 스페어도 아닌데 투구가 2개 초과일 때")
    public void invalid_투구_2개_초과() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            final Frame frame = LastFrame.init();
            frame.play(KnockedPins.from(3))
                    .play(KnockedPins.from(3))
                    .play(KnockedPins.from(3));
        });
    }

    @Test
    public void play_일반() {
        Frame frame = LastFrame.init();
        assertThat(frame.playing()).isTrue();

        frame = frame.play(KnockedPins.from(7));
        assertThat(frame.playing()).isTrue();

        frame = frame.play(KnockedPins.from(2));
        assertThat(frame.playing()).isFalse();
    }

    @Test
    public void play_첫번째_스트라이크() {
        Frame frame = LastFrame.init();
        assertThat(frame.playing()).isTrue();

        frame = frame.play(KnockedPins.from(10));
        assertThat(frame.playing()).isTrue();

        frame = frame.play(KnockedPins.from(7));
        assertThat(frame.playing()).isTrue();

        frame = frame.play(KnockedPins.from(2));
        assertThat(frame.playing()).isFalse();
    }

    @Test
    public void play_두번째_스페어() {
        Frame frame = LastFrame.init();
        assertThat(frame.playing()).isTrue();

        frame = frame.play(KnockedPins.from(7));
        assertThat(frame.playing()).isTrue();

        frame = frame.play(KnockedPins.from(3));
        assertThat(frame.playing()).isTrue();

        frame = frame.play(KnockedPins.from(10));
        assertThat(frame.playing()).isFalse();
    }

    @Test
    public void next() {
        assertThatIllegalArgumentException().isThrownBy(() -> LastFrame.init().next());
    }

    @Test
    public void last() {
        assertThatIllegalArgumentException().isThrownBy(() -> LastFrame.init().last());
    }
}
