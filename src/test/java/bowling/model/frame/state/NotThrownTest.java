package bowling.model.frame.state;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("안던진 상태")
class NotThrownTest {

    @Test
    @DisplayName("종료 여부는 항상 거짓")
    void isEnd() {
        assertThat(NotThrown.INSTANCE.isEnd()).isFalse();
    }

    @Test
    @DisplayName("남은 핀들이 없으면 Strike, 남으면 FirstThrown")
    void state() {
        //given
        NotThrown notThrown = NotThrown.INSTANCE;
        //when, then
        assertAll(
                () -> assertThat(notThrown.state(Pins.MAX)).isEqualTo(Strike.INSTANCE),
                () -> assertThat(notThrown.state(Pins.ZERO)).isEqualTo(FirstThrown.from(Pins.ZERO))
        );
    }

    @Test
    @DisplayName("남은 투구는 2")
    void restCount() {
        assertThat(NotThrown.INSTANCE.restCount()).isEqualTo(2);
    }
}
