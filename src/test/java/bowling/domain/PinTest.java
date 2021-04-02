package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("볼링핀")
public class PinTest {

    @Test
    @DisplayName("스트라이크 확인")
    public void strike() throws Exception {
        //given
        Pin pin = new Pin(10);

        //when
        ScoreEnum scoreEnum = ScoreEnum.of(pin, true);

        //then
        assertThat(scoreEnum).isEqualTo(ScoreEnum.STRIKE);
    }

    @ParameterizedTest
    @CsvSource(value = {"9,1", "5,5", "1,9"})
    @DisplayName("스페어 확인")
    public void spare(int first, int second) throws Exception {
        //given
        Pin pin = new Pin(first, second);

        //when
        ScoreEnum scoreEnum = ScoreEnum.of(pin, false);

        //then
        assertThat(scoreEnum).isEqualTo(ScoreEnum.SPARE);
    }
    
    @Test
    @DisplayName("첫구 거터 확인")
    public void firstBallGutter() throws Exception {
        //given
        Pin pin = new Pin(0);

        //when
        ScoreEnum scoreEnum = ScoreEnum.of(pin, true);

        //then
        assertThat(scoreEnum).isEqualTo(ScoreEnum.FIRST_GUTTER);
    }

    @ParameterizedTest
    @CsvSource(value = {"0,0", "1,0", "9,0"})
    @DisplayName("2구 거터 확인")
    public void secondBallGutter(int first, int second) throws Exception {
        //given
        Pin pin = new Pin(first, second);

        //when
        ScoreEnum scoreEnum = ScoreEnum.of(pin, false);

        //then
        assertThat(scoreEnum).isEqualTo(ScoreEnum.SECOND_GUTTER);
    }

    @ParameterizedTest
    @CsvSource(value = {"1", "5", "9"})
    @DisplayName("첫구 MISS 확인")
    public void firstBallMiss(int first) throws Exception {
        //given
        Pin pin = new Pin(first);

        //when
        ScoreEnum scoreEnum = ScoreEnum.of(pin, true);

        //then
        assertThat(scoreEnum).isEqualTo(ScoreEnum.MISS);
    }

    @ParameterizedTest
    @CsvSource(value = {"0,1", "1,1", "1,8"})
    @DisplayName("2구 MISS 확인")
    public void secondBallMiss(int first, int second) throws Exception {
        //given
        Pin pin = new Pin(first, second);

        //when
        ScoreEnum scoreEnum = ScoreEnum.of(pin, false);

        //then
        assertThat(scoreEnum).isEqualTo(ScoreEnum.MISS);
    }
}
