package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BowlingPinsTest {
    @DisplayName("넘어뜨린 볼린핀 개수에 대한 기능 확인")
    @Test
    void bowlingPinNumberTest() {
        BowlingPins bowlingPins = new BowlingPins(10);
        bowlingPins.knowDown(4);
        assertThat(bowlingPins).isEqualTo(new BowlingPins(6));
    }
}