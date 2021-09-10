package bowling.domain;

import bowling.exception.InvalidScoreException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {
    @DisplayName("Score 생성 성공 테스트")
    @Test
    public void create_score() {
        Score score = new Score(10);
        assertThat(score).isEqualTo(Score.valueOf(10));
    }

    @DisplayName("Score 생성 실패 테스트(핀 갯수 초과)")
    @Test
    public void create_score_error() {
        assertThatThrownBy(() -> Score.valueOf(11))
                .isInstanceOf(InvalidScoreException.class);
    }

    @DisplayName("Score 스트라이크 문자열 성공 테스트")
    @Test
    public void score_change_strike_symbol() {
        Score score = new Score(10);
        assertThat(score.changeScoreToSymbol()).isEqualTo("X");
    }

    @DisplayName("Score 스트라이크 문자열 성공 테스트")
    @Test
    public void score_change_guttor_symbol() {
        Score score = new Score(0);
        assertThat(score.changeScoreToSymbol()).isEqualTo("-");
    }
}
