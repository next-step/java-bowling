package bowling.domain.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CompleteScoreTest {
    private CompleteScore completeScore;
    private CompleteScore additionalScore;

    @BeforeEach
    void setUp() {
        completeScore = new CompleteScore(9);
        additionalScore = new CompleteScore(1);
    }

    @DisplayName("complete는 true이다")
    @Test
    void isCompleted() {
        assertThat(completeScore.isCompleted()).isTrue();
    }

    @DisplayName("값을 더할 수 있다")
    @Test
    void add() {

        assertThat(completeScore.add(additionalScore).getScore()).isEqualTo(10);
    }

    @DisplayName("EmptyScore를 더하면 EmptyScore 반환")
    @Test
    void returnEmptyScore() {
        assertThat(completeScore.add(EmptyScore.valueOf()))
                .isEqualTo(EmptyScore.valueOf());
    }
}
