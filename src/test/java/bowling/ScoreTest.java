package bowling;

import bowling.domain.Score;
import bowling.domain.Scores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ScoreTest {
    @Test
    @DisplayName("스코어테스트")
    public void scoreTest() {
        assertThatThrownBy(() -> new Score(-1)).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("0~10 입력");
    }

    @Test
    @DisplayName("합이10을넘지않도록테스트")
    public void checkScoresTest() {
        Scores scores = new Scores(new Score(5));
        assertThat(scores.size()).isEqualTo(1);
        assertThatThrownBy(() -> scores.add(new Score(6))).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("10을 넘으면 안됩니다.");
    }

    @Test
    @DisplayName("프레임끝, 계속던지는지테스트")
    public void continueCheckTest() {
        Scores scores = new Scores(new Score(5));
        assertThat(scores.getContinueFlag()).isTrue();
        scores.add(new Score(4));
        assertThat(scores.getContinueFlag()).isTrue();
    }
}


