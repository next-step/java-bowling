package bowling.step4.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class PitchTest {
    @Test
    void 볼링점수가_크기가_잘못되었을_때() {
        assertThatThrownBy(() -> new Pitch(11))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Pitch.SIZE_EXCEPTION);
    }

    @Test
    void 볼링점수가_알맞은_숫자일_때() {
        Pitch pitch = new Pitch(2);
        assertSoftly(softly -> {
            assertThat(pitch.count()).isEqualTo(2);
            assertThat(pitch.isStrike()).isFalse();
        });
    }


    @Test
    void 볼링점수가_스트라이크일_때() {
        Pitch pitch = new Pitch(10);
        assertSoftly(softly -> {
            assertThat(pitch.count()).isEqualTo(10);
            assertThat(pitch.isStrike()).isTrue();
        });
    }
}
