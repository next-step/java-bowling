package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

class FinalFlameTest {

    @Test
    void create() {
        assertThat(new FinalFlame()).isInstanceOf(FinalFlame.class);
    }

    @Test
    void 스트라이크시3번가능() {
        Flame finalFlame = new FinalFlame();
        finalFlame = finalFlame.addScore(Pins.of(10));
        finalFlame = finalFlame.addScore(Pins.of(10));
        assertThat(finalFlame.addScore(Pins.of(10))).isInstanceOf(FinalFlame.class);
    }

    @Test
    void 스페어일경우3번가능() {
        Flame finalFlame = new FinalFlame();
        finalFlame = finalFlame.addScore(Pins.of(0));
        finalFlame = finalFlame.addScore(Pins.of(10));
        assertThat(finalFlame.addScore(Pins.of(10))).isInstanceOf(FinalFlame.class);
    }

    @Test
    void 스트라이크_스페어_둘다아닐경우_2번만가능() {
        Flame finalFlame = new FinalFlame();
        finalFlame.addScore(Pins.of(0));
        finalFlame.addScore(Pins.of(2));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> finalFlame.addScore(Pins.of(4)));
    }

    @Test
    void 횟수가4번이상은불가능() {
        Flame finalFlame = new FinalFlame();
        finalFlame.addScore(Pins.of(10));
        finalFlame.addScore(Pins.of(10));
        finalFlame.addScore(Pins.of(10));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> finalFlame.addScore(Pins.of(10)));
    }

}