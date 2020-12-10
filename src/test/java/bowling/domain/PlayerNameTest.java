package bowling.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatCode;


public class PlayerNameTest {
    @Test
    public void createTest() {
        assertThatCode(() -> PlayerName.valueOf("JSY"))
                .doesNotThrowAnyException();
    }
}
