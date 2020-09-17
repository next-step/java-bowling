package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class NormalRollingResultTest {


    @Test
    void strikeDesc() {
        NormalRollingResult normalRollingResult = new NormalRollingResult();
        normalRollingResult.bowl(new Pin(10));
        assertThat(normalRollingResult.currentFrameStatus()).isEqualTo("X");
    }

    @Test
    void spareDesc() {
        NormalRollingResult normalRollingResult = new NormalRollingResult();
        normalRollingResult.bowl(new Pin(8));
        normalRollingResult.bowl(new Pin(2));
        assertThat(normalRollingResult.currentFrameStatus()).isEqualTo("8|/");
    }

    @Test
    void missDesc() {
        NormalRollingResult normalRollingResult = new NormalRollingResult();
        normalRollingResult.bowl(new Pin(5));
        normalRollingResult.bowl(new Pin(2));
        assertThat(normalRollingResult.currentFrameStatus()).isEqualTo("5|2");
    }

    @Test
    void runDesc() {
        NormalRollingResult normalRollingResult = new NormalRollingResult();
        normalRollingResult.bowl(new Pin(5));
        assertThat(normalRollingResult.currentFrameStatus()).isEqualTo("5");
    }

    @Test
    void gutterDesc() {
        NormalRollingResult normalRollingResult = new NormalRollingResult();
        normalRollingResult.bowl(new Pin(0));
        normalRollingResult.bowl(new Pin(2));
        assertThat(normalRollingResult.currentFrameStatus()).isEqualTo("-|2");
    }

    @Test
    void countOfAllPinsOverTenTest() {
        assertThatThrownBy(() -> {
            NormalRollingResult normalRollingResult = new NormalRollingResult();
            normalRollingResult.bowl(new Pin(5));
            normalRollingResult.bowl(new Pin(6));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isFinish_Test() {
        NormalRollingResult normalRollingResult = new NormalRollingResult();

        normalRollingResult.bowl(new Pin(10));

        assertTrue(normalRollingResult.isFinish());
    }

}
