package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinishTest {

    @DisplayName("Finish 인스턴스가 끝났는지 반환 테스트")
    @Test
    void 검증() {
        Finish finish = new Finish() {
            @Override
            public boolean isFinish() {
                return super.isFinish();
            }
        };
        assertThat(finish.isFinish()).isTrue();
    }
}