package step2.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoreTest {

    @Test
    @DisplayName("점수 객체 생성")
    void createScore() {
        assertThat(Score.of(5, 1)).isInstanceOf(Score.class);
    }

    @Test
    @DisplayName("볼링 진행 점수")
    void bowl() {
        Score score = Score.of(0, 2);

        Score newScore = Score.of(5, 1);

        assertThat(score.bowl(5)).isEqualTo(newScore);
    }

    @Test
    @DisplayName("남은 기회가 있을 때, 점수를 가져올 수 없는 예외 처리")
    void canGetScore() {
        Score score = Score.of(5, 1);
        assertThrows(IllegalArgumentException.class,
                () -> score.getScore());
    }

}