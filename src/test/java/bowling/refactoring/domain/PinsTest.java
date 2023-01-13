package bowling.refactoring.domain;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

class PinsTest {
    @Test
    void 볼링점수가_크기가_잘못되었을_때() {
        assertThatThrownBy(() -> new Pins(11))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Pins.SIZE_EXCEPTION);
    }

    @Test
    void 볼링점수가_알맞은_숫자일_때() {
        Pins pitch = new Pins(2);
        assertSoftly(softly -> {
            assertThat(pitch.count()).isEqualTo(2);
            assertThat(pitch.isStrike()).isFalse();
        });
    }


    @Test
    void 볼링점수가_스트라이크일_때() {
        Pins pitch = new Pins(10);
        assertSoftly(softly -> {
            assertThat(pitch.count()).isEqualTo(10);
            assertThat(pitch.isStrike()).isTrue();
        });
    }
}
