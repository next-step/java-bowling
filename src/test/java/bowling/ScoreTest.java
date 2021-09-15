package bowling;

import bowling.domain.Score;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {
    Score score;

    @BeforeEach
    void init() {
        score = new Score(8);
    }

    @Test
    @DisplayName("스코어 생성")
    void createScore() {
        score.secondBall(2);

        Assertions.assertAll(
                () -> assertThat(score.firstScore()).isEqualTo(8),
                () -> assertThat(score.secondScore()).isEqualTo(2));
    }

    @Test
    @DisplayName("두번째 공 입력 오류")
    void secondBallException() {
        assertThatThrownBy(() ->
                 score.secondBall(3))
        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("두번째 점수 오류")
    void secondScoreException() {
        assertThatThrownBy(() ->
                score.secondScore())
                .isInstanceOf(IllegalArgumentException.class);
    }
}
