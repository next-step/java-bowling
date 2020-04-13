package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NormalFrameTest {

    @Test
    void of() {
        assertThatCode(() -> NormalFrame.init())
                .doesNotThrowAnyException();
    }

    @Test
    void shot() {
        NormalFrame normalFrame = NormalFrame.init();
        assertThat(normalFrame.shot(4))
                .isEqualTo(ShotScore.of(4));

        assertThat(normalFrame.shot(6))
                .isEqualTo(ShotScore.of(4).next(6));

        assertThatThrownBy(() -> normalFrame.shot(5))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void next() {
        NormalFrame normalFrame = NormalFrame.init();
        assertThatCode(() -> normalFrame.next(5))
                .doesNotThrowAnyException();

        assertThatThrownBy(() -> normalFrame.next(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

}