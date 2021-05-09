package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StrikeTest {

    @DisplayName("Strike 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        State strike = Strike.initialize();

        assertAll(
                () -> assertThat(strike).isNotNull(),
                () -> assertThat(strike).isInstanceOf(Strike.class)
        );
    }
}