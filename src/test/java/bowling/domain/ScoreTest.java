package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {
    @Test
    @DisplayName("남아있는 볼링핀이 없는 경우, 점수 입력이 불가능하다.")
    void cannotInputScoreIfNoPins() {
        Score score = new Score();

        score.updateScore(10);

        assertThatThrownBy(() -> score.updateScore(3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("점수가 10을 넘을 경우, 예외를 발생시킨다.")
    void inputOverScoreTest() {
        Score score = new Score();

        score.updateScore(9);

        assertThatThrownBy(() -> score.updateScore(2))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
