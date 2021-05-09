package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MissTest {

    @DisplayName("Miss 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        Pins firstPins = Pins.valueOf(9);
        Pins secondPins = Pins.valueOf(0);

        State miss = Miss.from(firstPins, secondPins);

        assertAll(
                () -> assertThat(miss).isNotNull(),
                () -> assertThat(miss).isInstanceOf(Miss.class)
        );
    }
}