package bowling.domain.rolling;

import bowling.domain.Pin;
import bowling.domain.rolling.NormalRolling;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class NormalRollingTest {


    @Test
    void strikeDesc() {
        NormalRolling normalRolling = new NormalRolling();
        normalRolling.bowl(new Pin(10));
        assertThat(normalRolling.currentFrameStatus()).isEqualTo("X");
    }

    @Test
    void spareDesc() {
        NormalRolling normalRolling = new NormalRolling();
        normalRolling.bowl(new Pin(8));
        normalRolling.bowl(new Pin(2));
        assertThat(normalRolling.currentFrameStatus()).isEqualTo("8|/");
    }

    @Test
    void missDesc() {
        NormalRolling normalRolling = new NormalRolling();
        normalRolling.bowl(new Pin(5));
        normalRolling.bowl(new Pin(2));
        assertThat(normalRolling.currentFrameStatus()).isEqualTo("5|2");
    }

    @Test
    void runDesc() {
        NormalRolling normalRolling = new NormalRolling();
        normalRolling.bowl(new Pin(5));
        assertThat(normalRolling.currentFrameStatus()).isEqualTo("5");
    }

    @Test
    void gutterDesc() {
        NormalRolling normalRolling = new NormalRolling();
        normalRolling.bowl(new Pin(0));
        normalRolling.bowl(new Pin(2));
        assertThat(normalRolling.currentFrameStatus()).isEqualTo("-|2");
    }

    @Test
    void countOfAllPinsOverTenTest() {
        assertThatThrownBy(() -> {
            NormalRolling normalRolling = new NormalRolling();
            normalRolling.bowl(new Pin(5));
            normalRolling.bowl(new Pin(6));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isFinish_Test() {
        NormalRolling normalRolling = new NormalRolling();

        normalRolling.bowl(new Pin(10));

        assertTrue(normalRolling.isFinish());
    }

}
