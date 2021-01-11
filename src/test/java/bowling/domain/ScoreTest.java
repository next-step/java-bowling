package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

public class ScoreTest {

    @ParameterizedTest
    @NullSource
    public void check_null(Pins pins) {
        Assertions.assertThatThrownBy(() -> {
           Score score = new Score(pins);
        }).isInstanceOf(NullPointerException.class);
    }
}
