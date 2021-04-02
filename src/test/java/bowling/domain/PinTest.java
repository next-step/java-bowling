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
        ScoreEnum scoreEnum = ScoreEnum.of(pin);

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
        ScoreEnum scoreEnum = ScoreEnum.of(pin);

        //then
        assertThat(scoreEnum).isEqualTo(ScoreEnum.SPARE);
    }
    
    @Test
    public void gutter() throws Exception {
        //given
        
        //when
        
        //then
        
    }
}
