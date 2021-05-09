package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @DisplayName("FirstBowl 인스턴스에 null 입력시 예외처리 테스트")
    @Test
    void 검증_null() {
        // given
        Pins pins = null;

        // when and then
        assertThatThrownBy(() -> FirstBowl.from(pins))
                .isInstanceOf(PinsNullPointerException.class)
                .hasMessage("Pins 인스턴스가 null 입니다.");
    }

}