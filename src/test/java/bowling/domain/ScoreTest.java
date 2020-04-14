package bowling.domain;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @DisplayName("0~300 점 사이의 점수 생성 가능")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 100, 150, 200, 299, 300})
    public void validate_success(int count) throws Exception {
        new Score(count);
    }

    @DisplayName("0~300 범위 밖의 점수 생성시 exception")
    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 301, 400})
    public void validate_fail(int count) throws Exception {
        assertThatThrownBy(
                () -> new Score(count)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("점수를 더해준다")
    @Test
    public void addScore_success() throws Exception {
        //given
        Score current = new Score();
        Score added = new Score(5);

        //when
        current = current.addScore(added);

        //then
        assertThat(current).isEqualTo(added);
    }
}
