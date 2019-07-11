package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    void 쓰러진_핀의_개수를_입력받아_스코어를_생성한다() {
        int fallenPins = 9;
        Score score = Score.from(fallenPins);

        assertThat(score.isSame(fallenPins)).isTrue();
    }
}
