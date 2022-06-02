package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FirstTryTest {

    @Test
    void isStrike() {
        FirstTry firstTry = new FirstTry(10);

        Assertions.assertThat(firstTry.isStrike()).isTrue();

    }
}
