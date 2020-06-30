package bowling;

import bowling.domain.Score;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.domain.UserTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    @Test
    void minScoreTest() {
        assertThatThrownBy(() -> {
            Score score = new Score(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void maxScoreTest() {
        assertThatThrownBy(() -> {
            Score score = new Score(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
