package bowling.domain;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class KnockDownPinsTest {
    @Test
    public void createTest() {
        assertThatCode(() -> KnockDownPins.valueOf(10))
                .doesNotThrowAnyException();
    }
}
