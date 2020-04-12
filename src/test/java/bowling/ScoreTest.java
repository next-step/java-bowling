package bowling;

import bowling.domain.Score;
import bowling.domain.Scores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        assertThatThrownBy(() -> scores.checkBeforeAddNormal(6)).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("10을 넘으면 안됩니다.");
    }

    @Test
    @DisplayName("프레임끝, 계속던지는지테스트")
    public void continueCheckTest() {
        Scores scores = new Scores(new Score(9));
        assertThat(scores.nextFrame()).isFalse();
    }

    @Test
    @DisplayName("처음에스트라이크했을경우다음프레임으로넘어가는지테스트")
    public void continueCheckStrikeTest() {
        Scores scores = new Scores(new Score(10));
        assertThat(scores.nextFrame()).isTrue();
    }
}


