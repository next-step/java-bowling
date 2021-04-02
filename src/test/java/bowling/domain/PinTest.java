package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

public class PinTest {

    @Test
    @DisplayName("첫구에 스트라이크")
    public void firstballToStrike() throws Exception {
        //given
        Pin pin = new Pin(10);

        //when
        ScoreEnum scoreEnum = ScoreEnum.of(pin);

        //then
        assertThat(scoreEnum).isEqualTo(ScoreEnum.STRIKE);
    }
}
