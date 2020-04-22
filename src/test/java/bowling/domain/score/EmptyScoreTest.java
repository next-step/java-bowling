package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmptyScoreTest {
    @DisplayName("complete는 false이다")
    @Test
    void isCompleted() {
        assertThat(EmptyScore.valueOf().isCompleted()).isFalse();
    }

    @DisplayName("어떤값을 더하여도 EmptyScore가 반환된다")
    @Test
    void returnEmptyScoreAfterAdd() {
        CompleteScore completeScore = new CompleteScore(10);
        assertThat(EmptyScore.valueOf().add(completeScore))
                .isEqualTo(EmptyScore.valueOf());
    }
}
