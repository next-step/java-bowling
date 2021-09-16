package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 15, 30})
    @DisplayName("점수")
    public void score(int result) throws Exception {
        //given
        Score score1 = Score.of(result);

        //when

        //then
        assertThat(score1.score()).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"9,5,14", "1,0,1", "10,10,20"})
    @DisplayName("점수 추가 확인")
    public void addScore(int first, int second, int result) throws Exception {
        //given
        Score score1 = Score.of(first).add(second);

        //when

        //then
        assertThat(score1.score()).isEqualTo(result);
    }

    @Test
    @DisplayName("최대 점수 초과 에러")
    public void maxOverException() throws Exception {
        assertThatThrownBy(() -> Score.of(31))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("최소 점수 미만 에러")
    public void minUnderException() throws Exception {
        assertThatThrownBy(() -> Score.of(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
