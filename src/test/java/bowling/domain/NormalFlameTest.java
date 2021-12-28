package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;


public class NormalFlameTest {

    @Test
    void create() {
        assertThat(new NormalFlame()).isInstanceOf(NormalFlame.class);
    }

    @Test
    void isNextScore() {
        NormalFlame score = new NormalFlame();
        score = score.addScore(Pins.of(9));
        assertThat(score.isStrike()).isFalse();
    }

    @Test
    void 스트라이크확인() {
        NormalFlame score = new NormalFlame();
        score.addScore(Pins.of(10));
        assertThat(score.isStrike()).isTrue();
    }

    @Test
    void 합이10초과일경우() {
        NormalFlame score = new NormalFlame();
        score.addScore(Pins.of(9));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> score.addScore(Pins.of(2)));
    }

    @Test
    void 이미2번한경우() {
        NormalFlame score = new NormalFlame();
        score.addScore(Pins.of(2));
        score.addScore(Pins.of(2));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> score.addScore(Pins.of(2)));
    }

}
