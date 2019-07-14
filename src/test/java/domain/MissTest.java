package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MissTest {
    private Miss miss;

    @Test
    void getPointTest() {
        miss = new Miss(Pin.of(3), Pin.of(2));

        assertThat(miss.getPoint()).isEqualTo("3|2 ");
    }
}
