package bowling;

import bowling.domain.FrameSet;
import org.junit.jupiter.api.Test;

import static bowling.domain.Frame.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    void playTest() {
        FrameSet strikeSet = new FrameSet();
        assertThat(strikeSet.play(10)).isEqualTo(STRIKE);

        FrameSet spareSet = new FrameSet();
        assertThat(spareSet.play(9)).isEqualTo(HIT);
        assertThat(spareSet.play(1)).isEqualTo(SPARE);

        FrameSet gutterSet = new FrameSet();
        assertThat(gutterSet.play(0)).isEqualTo(NO_HIT);
        assertThat(gutterSet.play(0)).isEqualTo(GUTTER);

        FrameSet missSet = new FrameSet();
        assertThat(missSet.play(0)).isEqualTo(NO_HIT);
        assertThat(missSet.play(4)).isEqualTo(MISS);

        FrameSet missSet2 = new FrameSet();
        assertThat(missSet2.play(4)).isEqualTo(HIT);
        assertThat(missSet2.play(4)).isEqualTo(MISS);

        FrameSet missSet3 = new FrameSet();
        assertThat(missSet3.play(4)).isEqualTo(HIT);
        assertThat(missSet3.play(0)).isEqualTo(MISS);
    }
}
