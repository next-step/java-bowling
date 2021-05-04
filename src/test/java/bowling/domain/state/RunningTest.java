package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RunningTest {

    @DisplayName("Running 추상 클래스 인스턴스로 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        Running running = new TestRunning();

        // then
        assertThat(running).isNotNull();
    }

    @DisplayName("Running 추상 클래스 인스턴스가 알맞은 종료 여부를 반환하는지 테스트")
    @Test
    void 반환_종료_여부() {
        // when
        Running running = new TestRunning();

        // then
        assertThat(running.isFinish()).isFalse();
    }
}