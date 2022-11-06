package bowling.domain.status;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BowlStateTest {

    @Test
    void strike() {
        assertThat(BowlStatus.valueOf(1, 10)).isEqualTo(BowlStatus.STRIKE);
    }

    @Test
    void spare() {
        assertThat(BowlStatus.valueOf(2, 10)).isEqualTo(BowlStatus.SPARE);
    }

    @Test
    void miss() {
        assertThat(BowlStatus.valueOf(2, 9)).isEqualTo(BowlStatus.MISS);
    }

    @Test
    void gutter() {
        assertAll(
                () ->assertThat(BowlStatus.valueOf(1, 0)).isEqualTo(BowlStatus.GUTTER),
                () ->assertThat(BowlStatus.valueOf(2, 0)).isEqualTo(BowlStatus.GUTTER)
                );
    }
}