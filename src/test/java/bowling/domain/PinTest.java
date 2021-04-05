package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("볼링핀")
public class PinTest {

    @Test
    @DisplayName("스트라이크 확인")
    public void strike() throws Exception {
        //given
        ScoreRule scoreEnum = ScoreRule.of(10, true);
        //when

        //then
        assertThat(scoreEnum).isEqualTo(ScoreRule.STRIKE);
    }

    @Test
    @DisplayName("스페어 확인")
    public void spare() throws Exception {
        //given
        //when
        ScoreRule scoreEnum = ScoreRule.of(10, false);

        //then
        assertThat(scoreEnum).isEqualTo(ScoreRule.SPARE);
    }
    
    @Test
    @DisplayName("거터 확인")
    public void gutter() throws Exception {
        //given
        //when
        ScoreRule scoreEnum = ScoreRule.of(0, true);

        //then
        assertThat(scoreEnum).isEqualTo(ScoreRule.GUTTER);
    }

    @ParameterizedTest
    @CsvSource(value = {"1", "5", "9"})
    @DisplayName("MISS 확인")
    public void miss(int first) throws Exception {
        //given
        //when
        ScoreRule scoreEnum = ScoreRule.of(first, true);

        //then
        assertThat(scoreEnum).isEqualTo(ScoreRule.MISS);
    }

    @Test
    @DisplayName("잘못된 핀개수 초과 에러")
    public void downPin10OverException() {
        assertThatThrownBy(() -> Pin.of(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("핀 0개 미만 에러")
    public void firstPin0UnderException() {
        assertThatThrownBy(() -> Pin.of(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
