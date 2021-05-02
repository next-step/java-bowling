package bowling.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BowlingFixtureTest {

    @DisplayName("BowlingFixture 제공 상수 테스트")
    @Test
    void 상수들() {
        assertAll(
                () -> assertThat(BowlingFixture.MINIMUM_COUNT).isEqualTo(0),
                () -> assertThat(BowlingFixture.MAXIMUM_COUNT).isEqualTo(10),
                () -> assertThat(BowlingFixture.FRAME_START_INDEX).isEqualTo(1),
                () -> assertThat(BowlingFixture.FRAME_LAST_INDEX).isEqualTo(10),
                () -> assertThat(BowlingFixture.ONE).isEqualTo(1)
        );
    }


}