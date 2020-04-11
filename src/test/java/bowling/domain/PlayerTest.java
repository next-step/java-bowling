package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @Test
    @DisplayName("플레이어 생성 테스트")
    void createPlayerTest() {
        assertThatCode(
                () -> new Player("nam")
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어 생성 실패 테스트")
    void failCreatePlayerTest() {
        assertThatThrownBy(
                () -> new Player("namasdf")
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
