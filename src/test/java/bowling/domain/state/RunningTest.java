package bowling.domain.state;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RunningTest {

    @DisplayName("Running 추상 클래스가 State 구현하면서 정의한 메서드 테스트")
    @Test
    void 완료_false() {

        // when
        Running running = new Running() {};

        // then
        assertThat(running.isEnd()).isFalse();
    }
}