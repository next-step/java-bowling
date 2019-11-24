package bowling;

import bowling.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ScoreTest {

    private Score score;

    @BeforeEach
    void setUp() {
        score = Score.first(5).second(5);
    }

    @Test
    @DisplayName("첫 구 점수 테스트")
    void firstTryScoreTest() {
        // give
        Score score = Score.first(5);
        // when
        int firstPoint = score.getFirstPoint();
        // then
        assertThat(firstPoint).isEqualTo(5);
    }

    @Test
    @DisplayName("두 번째 구 점수 테스트")
    void secondTryScoreTest() {
        // give
        Score score = Score.first(5).second(3);
        // when
        int firstPoint = score.getFirstPoint();
        int secondPoint = score.getSecondPoint();

        // then
        assertThat(firstPoint).isEqualTo(5);
        assertThat(secondPoint).isEqualTo(3);
    }

    @Test
    @DisplayName("포인트 값 유효성 체크")
    void checkScorePointValidationTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Score score = Score.first(11);
        });

        assertThatIllegalArgumentException().isThrownBy(() -> {
            Score score = Score.first(5).second(6);
        });
    }

    @Test
    @DisplayName("스코어별 상태 체크")
    void checkStatusByScorePoints() {
        // when
        boolean status = score.isSpare();
        // then
        assertThat(status).isTrue();
    }
}
