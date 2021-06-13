package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FinalFrameTest {
    @Test
    public void init() {
        final Frame frame = FinalFrame.init();
        assertThat(frame.playing()).isTrue();
    }

    @Test
    public void invalid_투구_3개_초과() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            final Frame frame = FinalFrame.init();

            frame.play(10)
                    .play(10)
                    .play(10)
                    .play(10);
        });
    }

    @Test
    @DisplayName("스트라이커도 스페어도 아닌데 투구가 2개 초과일 때")
    public void invalid_투구_2개_초과() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            final Frame frame = FinalFrame.init();
            frame.play(3)
                    .play(3)
                    .play(3);
        });
    }

    @Test
    public void play_일반() {
        Frame frame = FinalFrame.init();
        assertThat(frame.playing()).isTrue();

        frame = frame.play(7);
        assertThat(frame.playing()).isTrue();

        frame = frame.play(2);
        assertThat(frame.playing()).isFalse();
    }

    @Test
    public void play_첫번째_스트라이크() {
        Frame frame = FinalFrame.init();
        assertThat(frame.playing()).isTrue();

        frame = frame.play(10);
        assertThat(frame.playing()).isTrue();

        frame = frame.play(7);
        assertThat(frame.playing()).isTrue();

        frame = frame.play(2);
        assertThat(frame.playing()).isFalse();
    }

    @Test
    public void play_두번째_스페어() {
        Frame frame = FinalFrame.init();
        assertThat(frame.playing()).isTrue();

        frame = frame.play(7);
        assertThat(frame.playing()).isTrue();

        frame = frame.play(3);
        assertThat(frame.playing()).isTrue();

        frame = frame.play(10);
        assertThat(frame.playing()).isFalse();
    }

    @Test
    public void next() {
        assertThatIllegalArgumentException().isThrownBy(() -> FinalFrame.init().next());
    }

    @Test
    public void last() {
        assertThatIllegalArgumentException().isThrownBy(() -> FinalFrame.init().last());
    }


    @Test
    public void score_open() {
        Frame frame = FinalFrame.init();
        frame = frame.play(7);
        frame = frame.play(2);

        assertThat(frame.score(Collections.singletonList(frame))).isEqualTo(new Score(9, 0));
    }

    @Test
    public void score_spare() {
        Frame frame = FinalFrame.init();
        frame = frame.play(7);
        frame = frame.play(3);
        frame = frame.play(7);

        assertThat(frame.score(Collections.singletonList(frame))).isEqualTo(new Score(17, 0));
    }

    @Test
    public void score_strike() {
        Frame frame = FinalFrame.init();
        frame = frame.play(10);
        frame = frame.play(10);
        frame = frame.play(10);

        assertThat(frame.score(Collections.singletonList(frame))).isEqualTo(new Score(30, 0));
    }

    @Test
    public void additionalScore_before_spare() {
        Frame frame = NormalFrame.init();
        frame = frame.play(7);
        frame = frame.play(3);

        Frame last = frame.last();
        last = last.play(10);

        assertThat(frame.score(Arrays.asList(frame, last))).isEqualTo(new Score(20, 0));
    }

    @Test
    public void additionalScore_before_strike() {
        Frame frame = NormalFrame.init();
        frame = frame.play(10);

        Frame last = frame.last();
        last = last.play(10);
        last = last.play(10);

        assertThat(frame.score(Arrays.asList(frame, last))).isEqualTo(new Score(30, 0));
    }
}
