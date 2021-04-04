package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("볼링핀")
public class PinsTest {

    @Test
    @DisplayName("스트라이크 확인")
    public void strike() throws Exception {
        //given
        Pins pins = NormalPins.init().first(10);

        //when

        //then
        assertThat(pins.scoreRule()).isEqualTo(ScoreRule.STRIKE);
    }

    @ParameterizedTest
    @CsvSource(value = {"9,1", "5,5", "1,9", "0,10"})
    @DisplayName("스페어 확인")
    public void spare(int first, int second) throws Exception {
        //given
        Pins pins = NormalPins.init().first(first).next(second);

        //when
        //then
        assertThat(pins.scoreRule()).isEqualTo(ScoreRule.SPARE);
    }

    @Test
    @DisplayName("마지막 프레임 스트라이크 확인")
    public void finalStrike() throws Exception {
        //given
        Pins pins = FinalPins.init().first(10);

        //when

        //then
        assertThat(pins.scoreRule()).isEqualTo(ScoreRule.STRIKE);
    }

    @ParameterizedTest
    @CsvSource(value = {"9,1", "5,5", "1,9", "0,10"})
    @DisplayName("마지막 프레임 스페어 확인")
    public void fianlSpare(int first, int second) throws Exception {
        //given
        Pins pins = FinalPins.init().first(first).next(second);

        //when
        //then
        assertThat(pins.scoreRule()).isEqualTo(ScoreRule.SPARE);
    }

    @Test
    @DisplayName("첫구 거터 확인")
    public void firstBallGutter() throws Exception {
        //given
        Pins pins = NormalPins.init().first(0);

        //when

        //then
        assertThat(pins.scoreRule()).isEqualTo(ScoreRule.GUTTER);
    }

    @ParameterizedTest
    @CsvSource(value = {"1", "5", "9"})
    @DisplayName("첫구 MISS 확인")
    public void miss(int first) throws Exception {
        //given
        Pins pins = NormalPins.init().first(first);

        //when

        //then
        assertThat(pins.scoreRule()).isEqualTo(ScoreRule.MISS);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,10", "9,2", "10,1"})
    @DisplayName("첫구,2구 총개수 초과 에러")
    public void totalDownPin10OverException(int first, int second) {
        assertThatThrownBy(() -> NormalPins.init().first(first).next(second))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
