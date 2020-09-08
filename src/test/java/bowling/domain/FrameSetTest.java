package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameSetTest {
    @Test
    void constructor() {
        assertThat(FrameSet.from("yongdae")).isNotNull();
    }

    @Test
    void isPlayingWithNoCount() {
        FrameSet frameSet = FrameSet.from("yongdae");
        int tryCount = 0;

        while (frameSet.isPlaying()) {
            frameSet.record(0);
            tryCount++;
        }

        assertThat(tryCount).isEqualTo(20);
    }

    @Test
    void isPlayingWithAllStrike() {
        FrameSet frameSet = FrameSet.from("yongdae");
        int tryCount = 0;

        while (frameSet.isPlaying()) {
            frameSet.record(10);
            tryCount++;
        }

        assertThat(tryCount).isEqualTo(11);
    }
}
