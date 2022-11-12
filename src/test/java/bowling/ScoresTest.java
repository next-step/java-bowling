package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class ScoresTest {
    @Test
    void 스코어_생성() {
        assertThatNoException().isThrownBy(() -> new Scores(0, 10));
    }

    @Test
    void 스코어_합_구하기() {
        assertThat(new Scores(1, 9).sum()).isEqualTo(new Score(10));
    }

    @Test
    void 스코어_개수_구하기() {
        assertThat(new Scores(9, 1, 2).size()).isEqualTo(3);
    }
}
