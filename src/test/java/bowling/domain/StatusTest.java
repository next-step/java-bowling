package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @Test
    void strikeTest() {
        assertThat(Status.of(true, 1)).isEqualTo(Status.STRIKE);
    }

    @Test
    void spareTest() {
        assertThat(Status.of(true, 2)).isEqualTo(Status.SPARE);
    }

    @Test
    void missTest() {
        assertThat(Status.of(false, 2)).isEqualTo(Status.MISS);
    }

    @Test
    void ingTest() {
        assertThat(Status.of(false, 1)).isEqualTo(Status.ING);
    }
}
