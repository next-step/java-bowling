package bowling.domain.engine.roll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RollTest {

    @Test
    @DisplayName("하나도 쓰러트리지 못했다면 Gutter 이다.")
    void gutter() {
        assertThat(Roll.result(0)).isInstanceOf(GutterResult.class);
    }

    @Test
    @DisplayName("1 개 이상의 핀을 쓰러트린 결과는 일반적인 결과로 본다.")
    void normalResult() {
        for (int i = 1; i <= 10; i++) {
            assertThat(Roll.result(i)).isInstanceOf(NormalResult.class);
        }
    }
    
}
