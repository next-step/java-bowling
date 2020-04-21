package seul.bowling.domain.status;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest {
    @Test
    void addPins() {
        Status status = new Ready();
        status = status.addPins(10);

        assertThat(status.getClass()).isEqualTo(Strike.class);
    }
}
