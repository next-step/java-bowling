package bowling.domain.state;

import bowling.domain.HitCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RunningTest {

    @DisplayName("Running 추상 클래스 인스턴스로 생성 여부 테스트")
    @Test
    void 생성() {
        Running running = new Running() {

            @Override
            public State bowl(HitCount hitCount) {
                return null;
            }
        };

        assertThat(running).isNotNull();
    }
}