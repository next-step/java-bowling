package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class RollsTest {
    @Test
    void 스코어_생성() {
        assertThatNoException().isThrownBy(() -> new Rolls(0, 10));
    }

    @Test
    void 스코어_합_구하기() {
        assertThat(new Rolls(1, 9).sum()).isEqualTo(new Pin(10));
    }

    @Test
    void 스코어_개수_구하기() {
        assertThat(new Rolls(9, 1, 2).size()).isEqualTo(3);
    }

    @Test
    void 스코어_추가() {
        Rolls rolls = new Rolls();
        rolls.add(new Pin(1));
        assertThat(rolls.getScores()).contains(new Pin(1));
    }
}
