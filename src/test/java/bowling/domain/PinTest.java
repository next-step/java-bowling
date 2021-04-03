package bowling.domain;

import org.junit.Test;
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
        Pin pin = Pin.first(10);

        //when
        Rule scoreEnum = Rule.of(pin, true);

        //then
        assertThat(scoreEnum).isEqualTo(Rule.STRIKE);
    }

    @ParameterizedTest
    @CsvSource(value = {"9,1", "5,5", "1,9"})
    @DisplayName("스페어 확인")
    public void spare(int first, int second) throws Exception {
        //given
        Pin pin = Pin.first(first).next(second);

        //when
        Rule scoreEnum = Rule.of(pin, false);

        //then
        assertThat(scoreEnum).isEqualTo(Rule.SPARE);
    }
    
    @Test
    @DisplayName("첫구 거터 확인")
    public void firstBallGutter() throws Exception {
        //given
        Pin pin = Pin.first(0);

        //when
        Rule scoreEnum = Rule.of(pin, true);

        //then
        assertThat(scoreEnum).isEqualTo(Rule.FIRST_GUTTER);
    }

    @ParameterizedTest
    @CsvSource(value = {"0,0", "1,0", "9,0"})
    @DisplayName("2구 거터 확인")
    public void secondBallGutter(int first, int second) throws Exception {
        //given
        Pin pin = Pin.first(first).next(second);

        //when
        Rule scoreEnum = Rule.of(pin, false);

        //then
        assertThat(scoreEnum).isEqualTo(Rule.SECOND_GUTTER);
    }

    @ParameterizedTest
    @CsvSource(value = {"1", "5", "9"})
    @DisplayName("첫구 MISS 확인")
    public void firstBallMiss(int first) throws Exception {
        //given
        Pin pin = Pin.first(first);

        //when
        Rule scoreEnum = Rule.of(pin, true);

        //then
        assertThat(scoreEnum).isEqualTo(Rule.MISS);
    }

    @ParameterizedTest
    @CsvSource(value = {"0,1", "1,1", "1,8"})
    @DisplayName("2구 MISS 확인")
    public void secondBallMiss(int first, int second) throws Exception {
        //given
        Pin pin = Pin.first(first).next(second);

        //when
        Rule scoreEnum = Rule.of(pin, false);

        //then
        assertThat(scoreEnum).isEqualTo(Rule.MISS);
    }

    @Test
    @DisplayName("첫구 잘못된 핀개수 초과 에러")
    public void downPin10OverException() {
        assertThatThrownBy(() -> Pin.first(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,10", "9,2", "10,1"})
    @DisplayName("첫구,2구 총개수 초과 에러")
    public void totalDownPin10OverException(int first, int second) {
        assertThatThrownBy(() -> Pin.first(first).next(second))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("첫구 핀 0개 미만 에러")
    public void firstPin0UnderException() {
        assertThatThrownBy(() -> Pin.first(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("2구 0개 미만 에러")
    public void secondPin0UnderException() {
        assertThatThrownBy(() -> Pin.first(0).next(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
