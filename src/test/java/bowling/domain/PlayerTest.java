package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class PlayerTest {
    @Test
    void 생성() {
        assertThatNoException().isThrownBy(() -> new Player("abc"));
    }

    @Test
    void 투구_스코어_입력() {
        assertThatNoException().isThrownBy(() -> new Player("abc").pitch(0, new Score(1)));
    }

    @Test
    void 프레임_진행_확인() {
        assertThat(new Player("abc").process(0)).isTrue();
        assertThat(new Player("abc", new Frames(new NormalFrame(new Scores(1, 2)))).process(0)).isFalse();
    }
}
