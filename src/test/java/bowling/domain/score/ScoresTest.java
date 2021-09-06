package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoresTest {

    @DisplayName("0과 10사이의 정수를 입력 받으면, Score객체를 생성한다")
    @Test
    void createTest() {
        assertThat(Scores.of(1, 2, 3)).isInstanceOf(Scores.class);
    }

    @DisplayName("0과 10를 벗어나는 정수를 입력 받으면, 예외를 던진다.")
    @Test
    void exceptionTest() {
        assertThatThrownBy(() -> Scores.of(-1, 0, 1, 2)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Scores.of(11, 0, 1, 2)).isInstanceOf(IllegalArgumentException.class);
    }
}
