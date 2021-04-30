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
        State ready = Ready.initialize();

        // then
        assertThat(ready).isNotNull();
    }

    @DisplayName("Ready 인스턴스가 종료 여부를 알맞게 반환하는지 테스트")
    @Test
    void 반환_종료_여부() {
        // when
        State ready = State.initialize();

        // then
        assertThat(ready.isFinish()).isFalse();
    }
}