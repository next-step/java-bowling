package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReadyTest {

    @DisplayName("Ready 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        Ready ready = State.initialize();

        // then
        assertThat(ready).isNotNull();
    }
}