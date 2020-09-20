package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest {

    @Test
    void playTest_strike() {
        NormalFrame normalFrame = NormalFrame.of();

        normalFrame.play(10);
        assertThat(normalFrame.isTerminate()).isEqualTo(true);
    }

    @Test
    void playTest_spare() {
        NormalFrame normalFrame = NormalFrame.of();

        normalFrame.play(5);
        normalFrame.play(5);
        assertThat(normalFrame.isTerminate()).isEqualTo(true);
    }

    @Test
    void playTest_miss() {
        NormalFrame normalFrame = NormalFrame.of();

        normalFrame.play(0);
        normalFrame.play(0);
        assertThat(normalFrame.isTerminate()).isEqualTo(true);
    }

    @Test
    void exceedMaxTryCount() {
        NormalFrame normalFrame = NormalFrame.of();

        normalFrame.play(0);
        normalFrame.play(0);

        assertThat(normalFrame.isTerminate()).isEqualTo(true);
        assertThatIllegalArgumentException().isThrownBy(()-> normalFrame.play(0));
    }
}