package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SpareTest {

    @DisplayName("Spare 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        Pins firstPins = Pins.valueOf(0);
        Pins secondPins = Pins.valueOf(10);

        // when
        State spare = Spare.of(firstPins, secondPins);

        // then
        assertAll(
                () -> assertThat(spare).isNotNull(),
                () -> assertThat(spare).isInstanceOf(Spare.class)
        );
    }
}