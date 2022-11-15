package bowling.step3.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class PitchesTest {
    @Test
    void 스트라이크_일_때() {
        Pitches pitches = new Pitches();
        pitches.add(10);
        assertSoftly(softly -> {
            assertThat(pitches.hasSpare()).isFalse();
            assertThat(pitches.hasStrike()).isTrue();
            assertThat(pitches.getSize()).isEqualTo(1);
        });
    }

    @Test
    void 스페어_일_때() {
        Pitches pitches = new Pitches();
        pitches.add(10);
        pitches.add(2);
        pitches.add(8);
        assertSoftly(softly -> {
            assertThat(pitches.hasSpare()).isTrue();
            assertThat(pitches.hasStrike()).isTrue();
            assertThat(pitches.getSize()).isEqualTo(3);
        });
    }
}
