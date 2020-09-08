package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameSetTest {
    @Test
    void from() {
        assertThat(FrameSet.from("yongdae")).isNotNull();
    }

    @Test
    void of() {
        assertThat(FrameSet.of("yongdae", FrameSet.FRAME_COUNT)).isNotNull();
    }

    @Test
    void ofThrowException() {
        assertThatThrownBy(() -> FrameSet.of("yongdae", FrameSet.FRAME_COUNT + 1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> FrameSet.of("yongdae", FrameSet.FRAME_COUNT - 1))
                .isInstanceOf(IllegalArgumentException.class);
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

    @Test
    void getBowler() {
        String bowler = "yongdae";
        assertThat(FrameSet.from(bowler).getBowler()).isEqualTo(bowler);
    }
}
