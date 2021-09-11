package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinsTest {

    @Test
    @DisplayName("스트라이크 확인")
    public void strike() throws Exception {

        Pins pins = NormalPins.init().first(10);

        assertThat(pins.scoreRule()).isEqualTo(ScoreRule.STRIKE);
    }

    @ParameterizedTest
    @CsvSource(value = {"9,1", "5,5", "1,9", "0,10"})
    @DisplayName("스페어 확인")
    public void spare(int first, int second) throws Exception {

        Pins pins = NormalPins.init().first(first).next(second);

        assertThat(pins.scoreRule()).isEqualTo(ScoreRule.SPARE);
    }

    @Test
    @DisplayName("마지막 프레임 첫구 스트라이크 확인")
    public void finalStrike() throws Exception {

        Pins pins = FinalPins.init().first(10);

        assertThat(pins.scoreRule()).isEqualTo(ScoreRule.STRIKE);
    }

    @ParameterizedTest
    @CsvSource(value = {"9,1", "5,5", "1,9", "0,10"})
    @DisplayName("마지막 프레임 스페어 확인")
    public void fianlSpare(int first, int second) throws Exception {

        Pins pins = FinalPins.init().first(first).next(second);

        assertThat(pins.scoreRule()).isEqualTo(ScoreRule.SPARE);
    }

    @ParameterizedTest
    @CsvSource(value = {"9,1,10", "5,5,1", "1,9,2", "10,0,1"})
    @DisplayName("마지막 프레임 투구 3번 확인")
    public void final3Chance(int first, int second, int third) throws Exception {
        Pins pins = FinalPins.init().first(first).next(second).next(third);

        assertThat(pins.pins().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("첫구 거터 확인")
    public void firstBallGutter() throws Exception {
        Pins pins = NormalPins.init().first(0);

        assertThat(pins.scoreRule()).isEqualTo(ScoreRule.GUTTER);
    }

    @ParameterizedTest
    @CsvSource(value = {"1", "5", "9"})
    @DisplayName("첫구 MISS 확인")
    public void miss(int first) throws Exception {
        Pins pins = NormalPins.init().first(first);

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
