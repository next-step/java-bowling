package bowling.domain.state;

import bowling.domain.HitCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RunningTest {

    @DisplayName("Running 추상 클래스가 State 구현하면서 정의한 메서드 테스트")
    @Test
    void 완료_false() {

        // when
        Running running = new Running() {
            @Override
            public State bowl(HitCount hitCount) {
                return null;
            }
        };

        // then
        assertThat(running.isFinish()).isFalse();
    }
}