package bowling.domain.status;

import bowling.domain.status.finished.Strike;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class StatusTest {

    @Test
    void createStatusTest(){
        assertThatCode(
                () -> Strike.of()
        ).doesNotThrowAnyException();
    }
}
