package bowling.domain.status;

import bowling.domain.status.running.Ready;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class StatusTest {

    @Test
    void createStatusTest(){
        assertThatCode(
                () -> Ready.of()
        ).doesNotThrowAnyException();
    }
}
