package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class RollsTest {
    @Test
    void 회차_초기화() {
        assertThatNoException().isThrownBy(() -> new Rolls(0, 10));
    }

    @Test
    void 회차_합산() {
        assertThat(new Rolls(1, 9).sum()).isEqualTo(10);
    }

    @Test
    void 진행_회차_구하기() {
        assertThat(new Rolls(9, 1, 2).size()).isEqualTo(3);
    }

    @Test
    void 스코어_추가() {
        Rolls rolls = new Rolls();
        rolls.add(new Pin(1));
        assertThat(rolls.getScores()).contains(new Pin(1));
    }
}
