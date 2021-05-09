package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FirstBowlTest {

    @DisplayName("FirstBowl 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        Pins pins = Pins.valueOf(9);

        // when
        State firstBowl = FirstBowl.from(pins);

        // then
        assertAll(
                () -> assertThat(firstBowl).isNotNull(),
                () -> assertThat(firstBowl).isInstanceOf(FirstBowl.class)
        );
    }

}